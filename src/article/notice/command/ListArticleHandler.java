package article.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.notice.service.ArticlePage;
import article.notice.service.ListArticleService;
import auth.service.Authority;
import auth.service.StudentUser;
import mvc.command.CommandHandler;

public class ListArticleHandler implements CommandHandler {

	 private static final String FORM_VIEW = "/index.jsp";
	
   private ListArticleService listService = new ListArticleService();
   private static StudentUser studentuser = new StudentUser();
   
   @Override
   public String process(HttpServletRequest req, HttpServletResponse res) 
         throws Exception {
      String pageNoVal = req.getParameter("pageNo");
      int pageNo = 1;
      if (pageNoVal != null) {
         pageNo = Integer.parseInt(pageNoVal);
      }
      ArticlePage articlePage = listService.getArticlePage(pageNo);
      req.setAttribute("articlePage", articlePage);
      
      if(studentuser.getTeamNo() != null) {
			res.sendRedirect("authTeam.do");
      }
      return FORM_VIEW;
   }
}