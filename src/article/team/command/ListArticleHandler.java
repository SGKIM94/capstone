package article.team.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.team.service.ArticlePage;
import article.team.service.ListArticleService;
import auth.service.StudentUser;
import mvc.command.CommandHandler;

public class ListArticleHandler implements CommandHandler {

	private ListArticleService listService = new ListArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {		
		HttpSession session = req.getSession();
		
		String filetype = req.getParameter("filetype");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		if(filetype == null) {
			filetype = "00";
		}
		ArticlePage articlePage = null;
	      StudentUser team = (StudentUser)req.getSession(false).getAttribute("authStdUser");
	      
	      if(team == null) {
	         String teamNo = req.getParameter("team_no");
	         articlePage = listService.getArticlePage(pageNo, teamNo, filetype);
	      } else {
	         articlePage = listService.getArticlePage(pageNo, team.getTeamNo(), filetype);
	      }
		
		session.setAttribute("articleTeamPage", articlePage);
		return "/index.jsp";
	}

}
