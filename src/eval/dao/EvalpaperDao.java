package eval.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import article.common.ArticleNotFoundException;
import eval.model.Evalpaper;
import jdbc.JdbcUtil;
import eval.model.Questions;
import java.util.StringTokenizer;

public class EvalpaperDao {
	
	/* 개별 교수님 평가서 번호 */
	public String makePaperNo(String eNo, String tNo, String pId) {
		return eNo+"-"+tNo+"-"+pId;
	}
	
	public Evalpaper selectEvalPaper(Connection conn, String evalNo, String teamNo, String proId) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String pfNo = makePaperNo(evalNo, teamNo, proId);
	      try {
	         pstmt = conn.prepareStatement(
	               "select * from epfss where pfNo = ?");
	         pstmt.setString(1, pfNo);
	         rs = pstmt.executeQuery();
	         Evalpaper evalpaper = null;
	      
	         String pfno = rs.getString("pfNo");
	         
	         evalpaper = new Evalpaper(evalNo,
        			 selectQuestions(conn, pfno),
        			 proId,
        			 teamNo,
        			 pfno,
        			 toDate(rs.getTimestamp("evalDate")),
        			 rs.getInt("totalscore"),
        			 rs.getInt("state"));
	         return evalpaper;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	public Evalpaper selectEvalPaper(Connection conn, String pfNo) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt = conn.prepareStatement(
	               "select * from epfss where pfNo = ?");
	         pstmt.setString(1, pfNo);
	         rs = pstmt.executeQuery();
	         Evalpaper evalpaper = null;
	         
	         if(rs.getString("paperNo") == pfNo) {
	        	 
	        	 String pfno = rs.getString("paperNo");
	         
	        	 evalpaper = new Evalpaper(
	        			 selectQuestions(conn, pfno),
	        			 pfno,
	        			 toDate(rs.getTimestamp("evalDate")),
	        			 rs.getInt("totalscore"),
	        			 rs.getInt("state"));
	         	}
	         /* 평가지 번호로 불러와 평가지 번호에서 평가 번호, 팀번호, 교수 번호 추출하기 */
	         StringTokenizer st = new StringTokenizer(pfNo,"-");
	         evalpaper.setEvalNo(st.nextToken());
	         evalpaper.setTeam(st.nextToken());
	         evalpaper.setPro(st.nextToken());
	         return evalpaper;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	
	public Questions selectQuestions(Connection conn, String pfno) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	    	  Questions qlist = new Questions();
	    	  	
	    	  /* DB서버와 웹 서버 간의 통신량이 너무 많아 지지 않을까? */
	    	  for(int i = 0; i < Questions.DEFAULT_LIST_NO; i++) {
	    		 pstmt = conn.prepareStatement(
	   	               "select * from epf where pfNo  = ? and qNo = ?");
	   	         pstmt.setString(1, pfno);
	   	         pstmt.setInt(2, i+1);
	   	         rs = pstmt.executeQuery();
	   	         
	   	         qlist.setQsItemScore(i, rs.getInt("score"));
	   	         qlist.setQsItemComment(i, rs.getString("comment"));
	    	  }
	         return qlist;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
		}   
	
	   private Date toDate(Timestamp date) {
	      return date == null ? null : new Date(date.getTime());
	   }

	   public void insert(Connection conn, Evalpaper evalpaper) throws SQLException {
		   insertToEvalitem(conn,evalpaper);
		   try (
	    	 PreparedStatement pstmt = 
	         	conn.prepareStatement("insert into epfss(pfNo, state, evalDate, total) values(?,?,?,?)")){ 
	         pstmt.setString(1,  evalpaper.getPaperNo());
	         pstmt.setInt(2,  evalpaper.getResultState());
	         pstmt.setTimestamp(3,  new Timestamp(evalpaper.getEvalDate().getTime()));
	         pstmt.setInt(4,  evalpaper.getTotal());
	         pstmt.executeUpdate();
	      }	
	   }
	   
	   public void insertToEvalitem(Connection conn, Evalpaper evalpaper) throws SQLException {
		   for(int i = 0; i < Questions.DEFAULT_LIST_NO; i++) {
		   try (PreparedStatement pstmt =		         
		    		  conn.prepareStatement("insert into epf(pfNo, qNo, score, comment) values(?,?,?,?)")) {
		        	 pstmt.setString(1, evalpaper.getPaperNo());
		        	 pstmt.setInt(2, i+1);
			         pstmt.setInt(3, evalpaper.getQs().getQsItemScore(i));
			         pstmt.setString(4, evalpaper.getQs().getQsItemComment(i));
			         pstmt.executeUpdate();	 
		     }
		  }
	   }
	   /* 평가 완료 변경 메소드 */
	   public void update_eval_complete(Connection conn, Evalpaper eval) throws SQLException {
	      try (PreparedStatement pstmt = conn.prepareStatement(
	    		  "update evaluate set state = ?, endDate = ? where evalNo = ?")) {
	         pstmt.setInt(1, eval.getResultState());
	         pstmt.setTimestamp(2, new Timestamp(eval.getEvalDate().getTime()));
	         pstmt.setString(3, eval.getEvalNo());
	         pstmt.executeUpdate();
	      }
	   }
	   /* 평가 진행중 변경 메소드 */
	   public void update_eval_ongoing(Connection conn, Evalpaper eval) throws SQLException {
		      try (PreparedStatement pstmt = conn.prepareStatement(
		    		  "update evaluate set state = ? where evalNo = ?")) {
		         pstmt.setInt(1, eval.getResultState());
		         pstmt.setString(2, eval.getEvalNo());
		         pstmt.executeUpdate();
		      }
		   }
	   
	   /* 평가 삭제 메소드 */
	   public Evalpaper deleteByPfNo(Connection conn, String pfNo) throws SQLException {
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				Evalpaper rm_eval_paper = selectEvalPaper(conn, pfNo);
				if(rm_eval_paper == null) {
					//평가가 찾아지지 않습니다. 라는 오류 함수로 넘어가게끔. 수정하자
					throw new ArticleNotFoundException();
				}
				
				deleteQlistByPfNo(conn, pfNo);
				
				pstmt = conn.prepareStatement("delete from evaluate where pfNo = ? ");
				pstmt.setString(1, pfNo);
				int insertedCount = pstmt.executeUpdate();		//리턴값은 성골했을 때 : 성공한 행의 개수, 실패시 : 0
				
				if(insertedCount>0) {
						return rm_eval_paper;
				}
				return null;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(pstmt);
			}
		}
	   public Questions deleteQlistByPfNo(Connection conn, String pfNo) throws SQLException {
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				Questions rm_qlist = selectQuestions(conn, pfNo);
				if(rm_qlist == null) {
					//평가가 찾아지지 않습니다. 라는 오류 함수로 넘어가게끔. 수정하자
					throw new ArticleNotFoundException();
				}
				
				pstmt = conn.prepareStatement("delete from evaluate where pfNo = ? ");
				pstmt.setString(1, pfNo);
				int insertedCount = pstmt.executeUpdate();		//리턴값은 성골했을 때 : 성공한 행의 개수, 실패시 : 0
				
				if(insertedCount>0) {
						return rm_qlist;
				}
				return null;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(pstmt);
			}
		}

}
