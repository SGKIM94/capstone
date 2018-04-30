package team.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import team.dao.*;
import member.dao.*;
import member.model.Professor;
import member.service.DuplicateIdException;
import team.model.*;
import team.service.MakeTeamRequest;;


public class MakeTeamService {

   private TeamDao teamDao = new TeamDao();
   
   public void MakeTeam(MakeTeamRequest mtReq) {
      Connection conn = null;
      try {
         conn = ConnectionProvider.getConnection();
         conn.setAutoCommit(false);
//         Team team = teamDao.selectByteam(conn, mtReq.getTeamNo());
//		 if (team != null) {
//			JdbcUtil.rollback(conn);		
//			throw new DuplicateIdException();
//			}
         /*
         ArrayList<String> students = mtReq.getStuIds();
         
         for(int i = 0 ; i < students.size() ; i++) {
            Team team = teamDao.selectByteam(conn, students.get(i));
            if(true) {
                	이부분은 만약 추가하려는 팀원이 이미 다른 팀에 있는 경우 오류 처리하기 위해 작성 
            }
         }*/
         teamDao.insert(conn, 
               new Team(
            		 mtReq.getTeamNo(), 
                     mtReq.getTeamName(), 
                     mtReq.getTeamSubject(),
                     mtReq.getAdvisor(),                     
                     mtReq.getGroupNo(),
                     true,
                     new Date())
               , mtReq.getId()
               , mtReq.getS_groupNo());
         conn.commit();
      } catch (SQLException e) {
         JdbcUtil.rollback(conn);
         throw new RuntimeException(e);
      } finally {
         JdbcUtil.close(conn);
      }
   }
   
   public boolean searchTeam(MakeTeamRequest stReq) {
	   Connection conn = null;
	      try {
	         conn = ConnectionProvider.getConnection();
	         conn.setAutoCommit(false);
	         Team team = teamDao.selectByteam(conn, stReq.getTeamNo());
			 if (team != null) {				
				JdbcUtil.rollback(conn);
				return false;
			 }else {
					return true;
				}	         
	      } catch (SQLException e) {
	          JdbcUtil.rollback(conn);
	          throw new RuntimeException(e);
	      } finally {
	          JdbcUtil.close(conn);
	      }
   }
}