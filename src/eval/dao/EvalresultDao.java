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
import eval.model.Evalpaper;
import eval.model.Evaluate;
import jdbc.JdbcUtil;
import member.dao.ProfessorDao;
import team.model.Team;
import team.dao.TeamDao;

public class EvalresultDao {
	public Evalpaper selectEvalPaper(Connection conn, String evalNo, String teamNo, String proId) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      ProfessorDao professordao = new ProfessorDao();
	      TeamDao teamdao = new TeamDao();
	      
	      try {
	         pstmt = conn.prepareStatement(
	               "select * from evalpaper where evalNo = ?");
	         pstmt.setString(1, evalNo);
	         rs = pstmt.executeQuery();
	         Evaluate evaluate = null;
	         
	         if (rs.next()) {
	        	 evaluate = new Evaluate(
	                  rs.getString("evalNo"), 
	                  rs.getInt("proNum"),
	                  professordao.selectById(conn, rs.getString("proId")),
	                  rs.getInt("totalTeam"),
	                  selectByEvalNoFromEvalpro(conn, rs.getString("evalNo")),
	                  selectByEvalNoFromEvalteam(conn, rs.getString("evalNo")),
	                  toDate(rs.getTimestamp("regDate")),
	                  toDate(rs.getTimestamp("endDate")),
	                  rs.getInt("state"));
	         }
	         return evaluate;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	   
	   public Questions selectQuestions(Connection conn, String evalNo, String teamNo, String proId) throws SQLException {
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         pstmt = conn.prepareStatement(
		               "select * from evalitem where evalNo = ?");
		         pstmt.setString(1, evalNo);
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
	   public ArrayList<String> selectByEvalNoFromEvalpro(Connection conn, String evalNo) throws SQLException {
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         pstmt = conn.prepareStatement(
		               "select * from evalpro where evalNo = ?");
		         pstmt.setString(1, evalNo);
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

	   public void insert(Connection conn, Evaluate eval) throws SQLException {
	      try (PreparedStatement pstmt = 
	            conn.prepareStatement("insert into evaluate(evalNo,dean,proNum,totalTeam,regDate,endDate,state) values(?,?,?,?,?,null,?)")) {
	         pstmt.setString(1,  eval.getEvalNo());
	         pstmt.setString(2,  eval.getDean().getProId());
	         pstmt.setInt(3,  eval.getProNum());
	         pstmt.setInt(4,  eval.gettNo());
	         pstmt.setTimestamp(5, new Timestamp(eval.getRegDate().getTime()));
	         pstmt.setTimestamp(6,  new Timestamp(eval.getEndDate().getTime()));
	         pstmt.setInt(7,  eval.getEvalState());
	         pstmt.executeUpdate();
	      }
	   }
	   
	   public void insertToEvalpro(Connection conn, Evaluate eval) throws SQLException {
		      try (PreparedStatement pstmt =		         
		    		  conn.prepareStatement("insert into evalpro(evalNo,proId) values(?,?)")) {
		         final String evalno = eval.getEvalNo();
		         ArrayList<String> pl = eval.getPflist();
		    	  for(int i = 0; i<eval.getProNum();i++) {
		        	 pstmt.setString(1, evalno);
			         pstmt.setString(2, pl.get(i));
			         pstmt.executeUpdate();	 
		         }
		      }
		   }
	   public void insertToEvalteam(Connection conn, Evaluate eval) throws SQLException {
		      try (PreparedStatement pstmt =		         
		    		  conn.prepareStatement("insert into evalteam(evalNo,teamNo) values(?,?)")) {
		         final String evalno = eval.getEvalNo();
		         ArrayList<String> tl = eval.getTeamlist();
		    	  for(int i = 0; i<eval.getProNum();i++) {
		        	 pstmt.setString(1, evalno);
			         pstmt.setString(2, tl.get(i));
			         pstmt.executeUpdate();	 
		         }
		      }
		   }
	   /* 평가 완료 변경 메소드 */
	   public void update_eval_complete(Connection conn, Evaluate eval) throws SQLException {
	      try (PreparedStatement pstmt = conn.prepareStatement(
	    		  "update evaluate set state = ?, endDate = ? where evalNo = ?")) {
	         pstmt.setInt(1, eval.getEvalState());
	         pstmt.setTimestamp(2, new Timestamp(eval.getEndDate().getTime()));
	         pstmt.setString(3, eval.getEvalNo());
	         pstmt.executeUpdate();
	      }
	   }
	   /* 평가 진행중 변경 메소드 */
	   public void update_eval_ongoing(Connection conn, Evaluate eval) throws SQLException {
		      try (PreparedStatement pstmt = conn.prepareStatement(
		    		  "update evaluate set state = ? where evalNo = ?")) {
		         pstmt.setInt(1, eval.getEvalState());
		         pstmt.setString(2, eval.getEvalNo());
		         pstmt.executeUpdate();
		      }
		   }
	   
	   /* 평가 삭제 메소드 */
	   public Evaluate deleteByEvalNo(Connection conn, Evaluate eval) throws SQLException {
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				Evaluate rm_eval = selectByEvalNo(conn, eval.getEvalNo());
				if(rm_eval == null) {
					//평가가 찾아지지 않습니다. 라는 오류 함수로 넘어가게끔. 수정하자
					throw new ArticleNotFoundException();
				}
				
				pstmt = conn.prepareStatement("delete from evaluate where evalNo = ? ");
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
