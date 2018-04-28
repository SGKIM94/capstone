package eval.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import article.common.ArticleNotFoundException;
import eval.model.Evalplan;
import jdbc.JdbcUtil;
import member.dao.ProfessorDao;

public class EvalplanDao {
	/* pulic? */
	final private int EVAL_IS_ON_GOING = 1;
	final private int EVAL_ENDED = 2;
			/* 평가계획서 평가번호로 읽어오기 */
		   public Evalplan selectByEvalNo(Connection conn, String evalNo) throws SQLException {
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      
		      try {
		         pstmt = conn.prepareStatement(
		               "select * from eplan where planNo = ?");
		         pstmt.setString(1, evalNo);
		         rs = pstmt.executeQuery();
		         Evalplan evalplan = null;
		         String pNo = rs.getString("planNo");
		         if (rs.next()) {
		        	 evalplan = new Evalplan(
		        			 pNo,
		        		  rs.getString("dean"),
		                  selectPfCount(conn, pNo),
		                  selectTeamCount(conn, pNo),
		                  selectByEvalNoFromEprof(conn, rs.getString("evalNo")),
		                  selectByEvalNoFromEteam(conn, rs.getString("evalNo")),
		                  toDate(rs.getTimestamp("stDate")),
		                  toDate(rs.getTimestamp("endDate")),
		                  rs.getInt("state"));
		         }
		         //test
		         return evalplan;
		      } finally {
		         JdbcUtil.close(rs);
		         JdbcUtil.close(pstmt);
		      }
		   }
		   
		   /* 평가 참여 교수 숫자 읽어오기  */
		   public int selectPfCount(Connection conn, String e) throws SQLException {
			   	PreparedStatement pstmt = null;		//Statement와 캐시 기능의 차이
			   	//Statement stmt = null;
			   	ResultSet rs = null;
				try {
					 pstmt = conn.prepareStatement(
				               "select count(*) from eprof when planNo = ?");	//평가 참여 교수 저장 티이블 : eprof
			         pstmt.setString(1, e);
			         rs = pstmt.executeQuery();
				     if (rs.next()) {
						return rs.getInt(1);
				     }
				     return 0;
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
			}
		   
		   /* 평가 팀 수 읽어오기 */
		   public int selectTeamCount(Connection conn, String e) throws SQLException {
			   	PreparedStatement pstmt = null;		//Statement와 캐시 기능의 차이
			   	//Statement stmt = null;
			   	ResultSet rs = null;
				try {
					 pstmt = conn.prepareStatement(
				               "select count(*) from eteam when planNo = ?");	//평가 팀 저장 티이블 : eteam
			         pstmt.setString(1, e);
			         rs = pstmt.executeQuery();
				     if (rs.next()) {
						return rs.getInt(1);
				     }
				     return 0;
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
			}
		   
		   /* 평가 팀 번호 전부 가져오기 */
		   public ArrayList<String> selectByEvalNoFromEteam(Connection conn, String e) throws SQLException {
			      PreparedStatement pstmt = null;
			      ResultSet rs = null;
			      try {
			         pstmt = conn.prepareStatement(
			               "select * from eteam where planNo = ?");
			         pstmt.setString(1, e);
			         rs = pstmt.executeQuery();
			         ArrayList<String> pl = new ArrayList<String>();
			         while (rs.next()) {	      
			        	 pl.add(rs.getString("teamNo"));
			         }
			         return pl;
			      } finally {
			         JdbcUtil.close(rs);
			         JdbcUtil.close(pstmt);
			      }
		   }
		   /* 평가 참여 교수 아이디 전부 가져오기 */
		   public ArrayList<String> selectByEvalNoFromEprof(Connection conn, String e) throws SQLException {
			      PreparedStatement pstmt = null;
			      ResultSet rs = null;
			      try {
			         pstmt = conn.prepareStatement(
			               "select * from eprof where planNo = ?");
			         pstmt.setString(1, e);
			         rs = pstmt.executeQuery();
			         ArrayList<String> pl = new ArrayList<String>();
			         
			         while (rs.next()) {	      
			        	 pl.add(rs.getString("proId"));
			         }
			         return pl;
			      } finally {
			         JdbcUtil.close(rs);
			         JdbcUtil.close(pstmt);
			      }
			   }

		   private Date toDate(Timestamp date) {
		      return date == null ? null : new Date(date.getTime());
		   }
		   
		   /* 평가계획서 DB입력  */
		   public Evalplan insert(Connection conn, Evalplan eval) throws SQLException {
			   PreparedStatement pstmt = null;
			   try {
		    	  pstmt = conn.prepareStatement("insert into eplan(planNo,dean,regDate,endDate,state) values(?,?,?,null,?)");
		         pstmt.setString(1,  eval.getEvalNo());
		         pstmt.setString(2,  eval.getDean());
		         pstmt.setTimestamp(3, new Timestamp(eval.getRegDate().getTime()));
		         pstmt.setTimestamp(4,  new Timestamp(eval.getEndDate().getTime()));
		         pstmt.setInt(5,  eval.getEvalState());
		         int insertedCount = pstmt.executeUpdate();
		         if(insertedCount>0) {
		        	 return selectByEvalNo(conn,eval.getEvalNo());
		         }
		         return null;
		      }
		      finally {
					
					JdbcUtil.close(pstmt);
		      }
		   }
		   /* 평가 참여 교수 목록 DB insert */
		   public void insertToEprof(Connection conn, Evalplan e) throws SQLException {
			      try (PreparedStatement pstmt =		         
			    		  conn.prepareStatement("insert into eprof(planNo,proId) values(?,?)")) {
			         final String evalno = e.getEvalNo();
			         ArrayList<String> pl = e.getPflist();
			    	  for(int i = 0; i<e.getProNum();i++) {
			        	 pstmt.setString(1, evalno);
				         pstmt.setString(2, pl.get(i));
				         pstmt.executeUpdate();	 
			         }
			      }
			   }
		   /* 평가 팀 목록 DB insert */
		   public void insertToEvalteam(Connection conn, Evalplan eval) throws SQLException {
			      try (PreparedStatement pstmt =		         
			    		  conn.prepareStatement("insert into eteam(planNo,teamNo,finalNo) values(?,?)")) {
			         final String evalno = eval.getEvalNo();
			         ArrayList<String> tl = eval.getTlist();
			    	  for(int i = 0; i<eval.gettNo();i++) {
			        	 pstmt.setString(1, evalno);
				         pstmt.setString(2, tl.get(i));
				         pstmt.executeUpdate();	 
			         }
			      }
			   }
		   /* 평가 완료 변경 메소드 */
		   public void update_eval_complete(Connection conn, Evalplan eval) throws SQLException {
		      try (PreparedStatement pstmt = conn.prepareStatement(
		    		  "update evalplan set state = ?, endDate = ? where evalNo = ?")) {
		         pstmt.setInt(1, EVAL_ENDED);
		         pstmt.setTimestamp(2, new Timestamp(eval.getEndDate().getTime()));
		         pstmt.setString(3, eval.getEvalNo());
		         pstmt.executeUpdate();
		      }
		   }
		   /* 평가 진행중 변경 메소드 */
		   public void update_eval_ongoing(Connection conn, Evalplan eval) throws SQLException {
			      try (PreparedStatement pstmt = conn.prepareStatement(
			    		  "update evalplan set state = ? where evalNo = ?")) {
			         pstmt.setInt(1, EVAL_IS_ON_GOING);
			         pstmt.setString(2, eval.getEvalNo());
			         pstmt.executeUpdate();
			      }
			   }
		   
		   /* 평가 팀 전부 삭제 메소드 */
		   public ArrayList<String> deleteEvalTeam(Connection conn, Evalplan e) throws SQLException {
			   PreparedStatement pstmt = null;
				Statement stmt = null;
				ResultSet rs = null;   

			   try {
			    	  ArrayList<String> tlist = selectByEvalNoFromEteam(conn, e.getEvalNo());
			    	  
			    	  pstmt = conn.prepareStatement("delete from eteam where planNo = ? ");
						pstmt.setString(1, e.getEvalNo());
						int insertedCount = pstmt.executeUpdate();		//리턴값은 성골했을 때 : 성공한 행의 개수, 실패시 : 0
						
						if(insertedCount>0) {
								return tlist;
						}
						return null;
			   		} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(stmt);
					JdbcUtil.close(pstmt);
			   		}
			   }
		   
		   
		   /* 평가 교수 전부 삭제 메소드 */
		   public ArrayList<String> deleteEvalProf(Connection conn, Evalplan e) throws SQLException {
			   PreparedStatement pstmt = null;
				Statement stmt = null;
				ResultSet rs = null;   

			   try {
			    	  ArrayList<String> plist = selectByEvalNoFromEprof(conn, e.getEvalNo());
			    	  
			    	  pstmt = conn.prepareStatement("delete from evalplan where planNo = ? ");
						pstmt.setString(1, e.getEvalNo());
						int insertedCount = pstmt.executeUpdate();		//리턴값은 성골했을 때 : 성공한 행의 개수, 실패시 : 0
						
						if(insertedCount>0) {
								return plist;
						}
						return null;
			   		} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(stmt);
					JdbcUtil.close(pstmt);
			   		}
			   }
		   
		   /* 평가 삭제 메소드 */
		   public Evalplan deleteByEvalNo(Connection conn, Evalplan eval) throws SQLException {
				PreparedStatement pstmt = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					Evalplan rm_eval = selectByEvalNo(conn, eval.getEvalNo());
					if(rm_eval == null) {
						//평가가 찾아지지 않습니다. 라는 오류 함수로 넘어가게끔. 수정하자
						throw new ArticleNotFoundException();
					}
					
					pstmt = conn.prepareStatement("delete from evalplan where planNo = ? ");
					pstmt.setString(1, eval.getEvalNo());
					int insertedCount = pstmt.executeUpdate();		//리턴값은 성골했을 때 : 성공한 행의 개수, 실패시 : 0
					
					if(insertedCount>0) {
							return rm_eval;
					}
					return null;
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(stmt);
					JdbcUtil.close(pstmt);
				}
			}

	


}
