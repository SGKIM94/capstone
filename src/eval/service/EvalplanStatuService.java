package eval.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import eval.dao.EvalplanDao;
import eval.model.Evalpaper;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class EvalplanStatuService {
	
	EvalplanDao evalplanDao = new EvalplanDao();
	
	public int CheckEvalState() {
		Connection conn = null;
		String evalNo = AllEvalStatusValue.togetStrYear()+AllEvalStatusValue.getEvalPlanDocuNo();
		String state=null;
		try {
			conn = ConnectionProvider.getConnection();
		
			if(evalplanDao.DoesEvalPlanExist(conn, evalNo)) {
				state=evalplanDao.getEvalState();
			}
			if(state==null) {
				return AllEvalStatusValue.getDefaultEvalPlanState();		//존재하지 않음을 의미
			}
			return Integer.parseInt(state);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
