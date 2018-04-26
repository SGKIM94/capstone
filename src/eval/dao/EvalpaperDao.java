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
import eval.model.Questions;

public class EvalpaperDao {

	public Evalpaper selectEvalPaper(Connection conn, String evalNo, String teamNo, String proId) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      ProfessorDao professordao = new ProfessorDao();
	      TeamDao teamdao = new TeamDao();
	      try {
	         pstmt = conn.prepareStatement(
	               "select * from evalpaper where evalNo = ? and teamNo = ? and proId = ?");
	         pstmt.setString(1, evalNo);
	         pstmt.setString(2, teamNo);
	         pstmt.setString(3, proId);
	         rs = pstmt.executeQuery();
	         Evalpaper evalpaper = null;
	         
        	 evalpaper = new Evalpaper(
	          rs.getString("evalNo"), 
	          selectQuestions(conn, evalNo, teamNo, proId),
	          professordao.selectById(conn, rs.getString("proId")),
	          teamdao.selectByteam(conn, teamNo),
	          toDate(rs.getTimestamp("evalDate")),
	          rs.getInt("totalscore"),
	          rs.getInt("state"));
	         
	         return evalpaper;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	public Questions selectQuestions(Connection conn, String evalNo, String teamNo, String proId) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	    	  Questions qlist = new Questions();
	
	    	  for(int i = 0; i < Questions.DEFAULT_LIST_NO; i++) {
	    		 pstmt = conn.prepareStatement(
	   	               "select * from evalitem where evalNo = ? and teamNo = ? and proId = ? and qNo = ?");
	   	         pstmt.setString(1, evalNo);
	   	         pstmt.setString(2, teamNo);
	   	         pstmt.setString(3, proId);
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
	         	conn.prepareStatement("insert into evalpaper(evalNo,teamNo,proId, paperNo,totalScore,state,evalDate) values(?,?,?,?,?,?,null)")){ 
	         pstmt.setString(1,  evalpaper.getEvalNo());
	         pstmt.setString(2,  evalpaper.getTeam().getTeamNo());
	         pstmt.setString(3,  evalpaper.getPro().getProId());
	         pstmt.setString(3,  evalpaper.getPaperNo());
	         pstmt.setInt(4,  evalpaper.getTotal());
	         pstmt.setInt(5,  evalpaper.getResultState());
	         pstmt.setTimestamp(6, new Timestamp(evalpaper.getEvalDate().getTime()));
	         pstmt.executeUpdate();
	      }	
	   }
	   
	   public void insertToEvalitem(Connection conn, Evalpaper evalpaper) throws SQLException {
		   for(int i = 0; i < Questions.DEFAULT_LIST_NO; i++) {
		   try (PreparedStatement pstmt =		         
		    		  conn.prepareStatement("insert into evalitem(paperNo, qNo, score, comment) values(?,?,?,?,?,?)")) {
		        	 pstmt.setString(1, evalpaper.getPaperNo());
		        	 pstmt.setInt(2, i+1);
			         pstmt.setInt(3, evalpaper.getQs().getQsItemScore(i));
			         pstmt.setString(4, evalpaper.getQs().getQsItemComment(i));
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
