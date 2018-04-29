package team.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import team.dao.*;
import member.service.DuplicateIdException;
import team.model.*;


public class JoinTeamService {

   private TeamDao teamDao = new TeamDao();
   
   public void JoinTeam(String teamNo, String stuId) {
      Connection conn = null;
      try {
         conn = ConnectionProvider.getConnection();
         conn.setAutoCommit(false);
         Team team = teamDao.selectByteam(conn, teamNo);
         
         teamDao.insert(conn, 
               new Team(
            		 team.getTeamNo(), 
                     team.getTeamName(), 
                     team.getTeamSubject(),
                     team.getAdvisor(),                     
                     team.getGroupNo(),
                     team.isState(),
                     new Date())
               , stuId);
         conn.commit();
      } catch (SQLException e) {
         JdbcUtil.rollback(conn);
         throw new RuntimeException(e);
      } finally {
         JdbcUtil.close(conn);
      }
   }
}