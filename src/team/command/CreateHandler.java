package team.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.*;
import member.service.DuplicateIdException;
import team.service.*;
import team.service.MakeTeamService;
import mvc.command.CommandHandler;

public class CreateHandler implements CommandHandler {
   
   private static final String FORM_VIEW = "/WEB-INF/view/createTeam_new.jsp";
   private MakeTeamService teamService = new MakeTeamService();

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
	  MakeTeamRequest mtReq = new MakeTeamRequest();
      StudentUser user = (StudentUser)req.getSession(false).getAttribute("authStdUser");
      
      mtReq.setTeamNo(req.getParameter("teamNo"));
      mtReq.setTeamName(req.getParameter("teamName"));
      mtReq.setTeamSubject(req.getParameter("teamSubject"));
      mtReq.setAdvisor(req.getParameter("advisor"));
      mtReq.setGroupNo(req.getParameter("groupNo"));
     
      mtReq.SetId(user.getId()); //Student 테이블 TeamNo 값 update 하기 위함.
      
      Map<String, Boolean> errors = new HashMap<>();
      req.setAttribute("errors", errors);
      
      mtReq.validate(errors);
      /*
      if (!errors.isEmpty()) {
         return "/index.jsp";
      }
      */
     try {
         if(teamService.searchTeam(mtReq) == true){
        	 mtReq.setS_groupNo(3);
            teamService.MakeTeam(mtReq);         
            return "/WEB-INF/view/createTeamSuccess.jsp";
         }
         else{
            errors.put("ExistTeam", Boolean.TRUE);
            return FORM_VIEW;
         }
      } catch (DuplicateIdException e) {
         errors.put("duplicateId", Boolean.TRUE);
         return FORM_VIEW;
      }
   }
}