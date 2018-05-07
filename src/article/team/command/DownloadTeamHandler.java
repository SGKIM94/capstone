package article.team.command;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.notice.command.DownloadUtil;
import article.team.dao.TeamContentDao;
import article.team.model.TeamContent;
import article.team.service.DownloadTeamService;
import auth.service.Member;
import auth.service.StudentUser;
import member.service.DuplicateIdException;
import mvc.command.CommandHandler;

public class DownloadTeamHandler implements CommandHandler {
   
   private static final String FORM_VIEW = "/index.jsp";
   @Override
   public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
      if (req.getMethod().equalsIgnoreCase("GET")) {
         return processForm(req, res);
      } else if (req.getMethod().equalsIgnoreCase("POST")) {
         return processSubmit(req, res);
      } else {
         res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
         return null;
      }
   }

   private String processForm(HttpServletRequest req, HttpServletResponse res) {
      return FORM_VIEW;
   }

   private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	  DownloadTeamService content = new DownloadTeamService();
	  
	  Member member = (Member)req.getSession(false).getAttribute("authTeam"); 
	  String filename = req.getParameter("fileNo");
	  
	  TeamContent teamContent = content.selectTeam(filename);
	  String Docname = URLEncoder.encode(filename,"UTF-8");
	  String path = req.getSession().getServletContext().getRealPath("/upload/"+member.getTeamNo());

	  System.out.println("경로 = " + path + " file 이름 = " + teamContent.getOrigin());
	  
	  File file = new File(path, teamContent.getOrigin());
      Map<String, Boolean> errors = new HashMap<>();
      req.setAttribute("errors", errors);

     try {
         if(file.exists()){
        	 DownloadUtil.download(req, res, file);
        	 return FORM_VIEW;      
         }else{
        	errors.put("NotExistNoticeFile", Boolean.TRUE);
        	return FORM_VIEW;
         }
      } catch (DuplicateIdException e) {
         errors.put("duplicateId", Boolean.TRUE);
         return FORM_VIEW;
      }
   }
}