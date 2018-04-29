package team.service;

import java.sql.Connection;
import java.sql.SQLException;
import auth.service.LoginFailException;

import jdbc.connection.ConnectionProvider;
import team.dao.*;
import team.model.*;


public class SelectTeamService {

private TeamDao teamDao = new TeamDao();
	
	public Team searchTeam(String teamNo) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			if(teamNo != null) {	
			Team team = teamDao.selectByteam(conn, teamNo);
			if (team == null) {
				throw new LoginFailException();
			}			
			return team;
			}else {
				return null;
			}						
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}