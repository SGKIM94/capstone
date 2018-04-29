package eval.service;

import java.sql.Connection;
import java.sql.SQLException;

import eval.dao.EvalpaperDao;
import eval.model.Evalpaper;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.service.DuplicateIdException;

public class EvaluateTeamService {
	
	EvalpaperDao evalpaperdao = new EvalpaperDao();
	
	/* 개별 교수님 평가서 번호 */
	/* 평가 세션 값 가져와서 만들자 */
	public String makePaperNo(String eNo, String tNo, String pId) {
		return eNo+"-"+tNo+"-"+pId;
	}
	
	public void EvaluateTeam(EvaluateTeamRequest evalReq) {
		Connection conn = null;
		
		String pfNo = makePaperNo();
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Evalpaper evalpaper = evalpaperdao.selectEvalPaper(conn, pfNo);
			if (evalpaper != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			evalpaperdao.insert(conn, evalpaper);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
