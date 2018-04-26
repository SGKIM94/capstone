package eval.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

import eval.dao.EvalplanDao;
import eval.model.Evalplan;

public class MakeEvalplanService {
	EvalplanDao evalplanDao = new EvalplanDao();
	
	public void Make(MakeRequest req){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Evalplan evalplan = toEvalplan(req);
			Evalplan savedEvalplan = evalplanDao.insert(conn, evalplan);
			if (savedEvalplan == null) {
				throw new RuntimeException("fail to insert teamboard");
			}
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	private Evalplan toEvalplan(MakeRequest req) {
		Date now = new Date();
		return new Evalplan(toGetEvalNo(req), req.getDean(), req.getProNum(),req.gettNo(), 
			req.getPflist(), req.getTlist(), req.getRegDate(), now, 0);
	}
	private String toGetEvalNo(MakeRequest req) {
		Calendar c = Calendar.getInstance();
		String evalNo = Integer.toString(c.get(Calendar.YEAR))+"-01";
		return evalNo;
	}
}
