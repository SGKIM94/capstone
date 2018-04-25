package team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.Calendar;

import member.model.*;
import jdbc.JdbcUtil;
import team.model.Team;
import member.dao.*;

public class TeamDao {
	
	private StudentDao studentDao = new StudentDao();
	
   public Team selectByteam(Connection conn, String teamNo) throws SQLException {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         pstmt = conn.prepareStatement(
               "select * from team where teamNo = ?");
         pstmt.setString(1, teamNo);
         rs = pstmt.executeQuery();
         Team team = null;
         if (rs.next()) {
            team = new Team(
            	  rs.getString("teamNo"),         //이거 db int -> str 수정해야함 
                  rs.getString("teamName"), 
                  rs.getString("teamSubject"),
                  rs.getString("advisor"),
                  rs.getString("groupNo"),
            	  Boolean.parseBoolean(rs.getString("state")),                           
                  toDate(rs.getTimestamp("TeamJoinDate")));      //db 이름을 teamRegDate로 바꿧으면함.
       }
         return team;
      } finally {
         JdbcUtil.close(rs);
         JdbcUtil.close(pstmt);
      }
   }
   
   private Date toDate(Timestamp date) {
      return date == null ? null : new Date(date.getTime());
   }
   
   DecimalFormat df = new DecimalFormat("00"); // 연도 구하기위한 포맷 형식 지정
   Calendar currentCalendar = Calendar.getInstance();
   String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
 
   public void insert(Connection conn, Team team, String stu_Id) throws SQLException {
      try (PreparedStatement pstmt = 
            conn.prepareStatement("insert into team(teamNo,teamName,teamSubject"
            		+ ",advisor,groupNo,state,TeamJoinDate) values(?,?,?,?,?,?,?)")) {
    	   
    	 strYear = strYear.substring(2);
    	 String tNo = (strYear + "_" + team.getTeamNo() + "_" + team.getGroupNo()); //팀 고유 번호 구하는 방식(생성연도 + 팀조번호 + 반)       
    	 pstmt.setString(1,  tNo);
         pstmt.setString(2,  team.getTeamName());
         pstmt.setString(3,  team.getTeamSubject());
         pstmt.setString(4,  team.getAdvisor());
         pstmt.setString(5,  team.getGroupNo());
         pstmt.setBoolean(6,  team.isState());            //boolean 값 db에 어떻게 저장하는지    
         pstmt.setTimestamp(7, new Timestamp(team.getTeamJoinDate().getTime()));
         pstmt.executeUpdate();
         
         studentDao.update_tNo(conn, tNo, stu_Id);
      }
   }
   /* 정보 변경 메소드 */
   public void update(Connection conn, Student member) throws SQLException {
      try (PreparedStatement pstmt = conn.prepareStatement(
            "update student set team = ? where stuId = ?")) {
          pstmt.setString(1, member.getTeamNo());
    	  pstmt.setString(2, member.getStuId());
          pstmt.executeUpdate();
      }
   }
   
   public void delete(Connection conn, Team team) throws SQLException {
	   try (PreparedStatement pstmt = conn.prepareStatement(
			   "delect from team where teamNo = ?")) {
		   pstmt.setString(1, team.getTeamNo());
		   pstmt.executeUpdate();				   
	   }
   }
   public void delete_teamNo(Connection conn, Student student) throws SQLException {
	   try (PreparedStatement pstmt = conn.prepareStatement(
			   "update stdent set teamNo where stuId = ?")){
		   )
	   })
   }

}