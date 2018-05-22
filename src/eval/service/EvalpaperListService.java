package eval.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import eval.dao.EvalpaperDao;
import eval.dao.EvalplanDao;
import eval.model.Evalpaper;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class EvalpaperListService {
	
	
	EvalpaperDao evalpaperDao = new EvalpaperDao();
	EvalplanDao evalPlanDao = new EvalplanDao();
	
	private EvalPaperList elist;
	//private List<Evalpaper> eplist;
	//private EvalFinal  efinal;
	
	public EvalpaperListService() {
		elist = new EvalPaperList();
	}
	public EvalpaperListService(String teamNo) {
		elist = new EvalPaperList();
	}
	
	public EvalPaperList getEvalPaperList(String teamNo) {
		Connection conn = null;
		List<Evalpaper> newlist = null;
		try {
			conn = ConnectionProvider.getConnection();
			newlist = evalpaperDao.selectEvalTeamAllPaper(conn, teamNo);
			EvalPaperList el = new EvalPaperList(newlist);
			return el;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	public boolean IsEvalFinished() {
		Connection conn = null;
		int state = 0;
		try {
			conn = ConnectionProvider.getConnection();
			state = Integer.parseInt(evalPlanDao.getEvalState());
			if(state == AllEvalStatusValue.getEpaperEvalEnded()) {
				return true; 
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
//	public int IsEvalStarted() {
//		
//	}
}
