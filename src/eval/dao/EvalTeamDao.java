package eval.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eval.model.EvalTeam;
import jdbc.JdbcUtil;

public class EvalTeamDao {
	
	public String selectByEvalNoToFindFinal(Connection conn, String teamNo) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt = conn.prepareStatement(
	               "select * from eteam where teamNo = ?");
	         pstmt.setString(1, teamNo);
	         rs = pstmt.executeQuery();   
	         return rs.getString("finalNo");
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	}
	
	public List<EvalTeam> select(Connection conn, int startRow, int size) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	     
	      try {
	         pstmt = conn.prepareStatement("select * from eteam " +
	               "where teamNo like "+countYear()+"%"+
	        		 "order by teamNo desc limit ?, ?");
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, size);
	         rs = pstmt.executeQuery();
	         List<EvalTeam> result = new ArrayList<>();
	         while (rs.next()) {
	            result.add(convertArticle(rs));
	         }
	         return result;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	 public int selectCount(Connection conn) throws SQLException {
	      Statement stmt = null;
	      ResultSet rs = null;
	      try {
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery("select count(*) from eteam where teamno like "+ countYear()+"%");
	         if (rs.next()) {
	            return rs.getInt(1);
	         }
	         return 0;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(stmt);
	      }
	   }
	
	 private EvalTeam convertArticle(ResultSet rs) throws SQLException {
	      return new EvalTeam(rs.getString("teamNo"), rs.getString("finalNo"));
	 }
	 
	 private String countYear() {
		 DecimalFormat df = new DecimalFormat("00"); // 연도 구하기위한 포맷 형식 지정
	      Calendar currentCalendar = Calendar.getInstance();
	      String Year = Integer.toString(currentCalendar.get(Calendar.YEAR));
	      Year = Year.substring(2);
	      return Year;
	 }
	 
}
