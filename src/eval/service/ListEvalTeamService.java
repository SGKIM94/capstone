package eval.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import eval.dao.EvalTeamDao;
import eval.model.EvalTeam;
import jdbc.connection.ConnectionProvider;
import team.dao.TeamDao;

public class ListEvalTeamService {

		private EvalTeamDao teamDao = new EvalTeamDao();
		private int size = 10;

		public EvalTeamPage getEvalTeamPage(int pageNum) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				int total = teamDao.selectCount(conn);
				List<EvalTeam> content = teamDao.select(
						conn, (pageNum - 1) * size, size);
				return new EvalTeamPage(total, pageNum, size, content);
			} catch (SQLException e) {
				throw new RuntimeException(e);
		}
	}
}
