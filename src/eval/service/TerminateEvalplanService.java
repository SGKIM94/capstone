package eval.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import auth.service.Authority;
import eval.dao.EvalFinalDao;
import eval.dao.EvalProfDao;
import eval.dao.EvalplanDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.ProfessorDao;
import team.dao.TeamDao;

public class TerminateEvalplanService {
	
	EvalProfDao evalProfDao = new EvalProfDao();
	EvalFinalDao evalFinalDao = new EvalFinalDao();
	TeamDao teamDao = new TeamDao();
	ProfessorDao professorDao = new ProfessorDao();
	EvalplanDao evalplanDao = new EvalplanDao();
	
	public boolean NotComplete(String strYear, List<ShowTeam> st) {
		Connection conn = null;
		List<String> finallist = null;
		List<ShowTeam> restlist = new ArrayList<ShowTeam>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			finallist = evalFinalDao.selectNotCompleteFinal(conn, strYear);
			if(finallist==null) {
				return false;
			}
			ShowTeam temp = null;
			String teamNo = null;
			for(String var : finallist) {
				teamNo = TrimTeamNo(var);
				temp = teamDao.selectShowTeam(conn, teamNo);
				restlist.add(temp);
			}
			
			st = restlist;
			return true;	
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private String TrimTeamNo(String finalNo) {
		String temp =  finalNo.replace(("_"+AllEvalStatusValue.getDefaultFinalDocu()), "");
		System.out.println("팀 번호 : "+temp);
		return temp;
	}
	
	public void Terminate(String deanNo) {
		Connection conn = null;
		List<String> pflinst = null;
		
		String evalNo = AllEvalStatusValue.togetStrYear()+AllEvalStatusValue.getEvalPlanDocuNo();
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			evalplanDao.update_eval_complete(conn, evalNo);
			pflinst = evalProfDao.selectAllProf(conn, evalNo);
			
			for(String var : pflinst) {
				if(!var.equals(deanNo)){
					professorDao.updateAuthority(conn, var, Authority.getProNotEval());
				}
			}
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
