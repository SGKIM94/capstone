package article.team.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.team.dao.TeamArticleDao;
import article.team.model.TeamArticle;
import jdbc.connection.ConnectionProvider;

public class ListArticleService {

	private TeamArticleDao articleDao = new TeamArticleDao();
	private int size = 100;

	public ArticlePage getArticlePage(int pageNum, String teamNo, String filetype) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = articleDao.selectCount(conn, teamNo);
			List<TeamArticle> content = null;
			
			if(teamNo == null) {
				return null;
			}
			
			if(filetype.equals("00")) {
				content = articleDao.select(
						conn, (pageNum - 1) * size, size, teamNo);
			}
			else {
				content = articleDao.selectByFiletype(
						conn, (pageNum - 1) * size, size, teamNo, filetype);
			}
			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
