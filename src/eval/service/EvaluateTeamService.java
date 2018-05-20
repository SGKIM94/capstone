package eval.service;

import java.sql.Connection;
import java.sql.SQLException;

import eval.dao.EvalpaperDao;
import eval.model.Evalpaper;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.service.DuplicateIdException;

public class EvaluateTeamService {
	
	private final int EVAL_END=3;
	
	EvalpaperDao evalpaperdao = new EvalpaperDao();
	
	
	/* 개별 교수님 평가서 번호 */
	/* 평가 세션 값 가져와서 만들자 */
	public String makePaperNo(String tNo, String pId) {
		return tNo+"_"+pId;
	}
	
	public Evalpaper SelectEvalpaper(String epaperNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			Evalpaper evalpaper = evalpaperdao.selectEvalPaper(conn, epaperNo);
			/* 평가지 못찾았을 경우 에러 처리 */
//			if (evalpaper != null) {
//				JdbcUtil.rollback(conn);
//				throw new DuplicateIdException();
//			}
			/* 해당 팀에 대한 평가가 끝남 */
			if(evalpaper.getState() == EVAL_END) {
				return null;
			}
			return evalpaper;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
			
	
	
	public void EvaluateTeam(EvaluateTeamRequest evalReq) {
		Connection conn = null;
		
		Evalpaper evalpaper = evalReq.getEp();
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			evalpaperdao.update(conn, evalpaper);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
