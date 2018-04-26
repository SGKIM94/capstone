package team.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.service.*;
import mvc.command.CommandHandler;

public class CreateHandler implements CommandHandler {
   
   private static final String FORM_VIEW = "/WEB-INF/view/createTeam.jsp";
   
   @Override
   public String process(HttpServletRequest req, HttpServletResponse res) {
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

   private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
      MakeTeamRequest mtReq = new MakeTeamRequest();
      mtReq.setTeamNo(req.getParameter("teamno"));
      mtReq.setTeamName(req.getParameter("teamname"));
      mtReq.setTeamSubject(req.getParameter("teamsubject"));
      mtReq.setAdvisor(req.getParameter("advisor"));
            
      Map<String, Boolean> errors = new HashMap<>();
      req.setAttribute("errors", errors);
      
      mtReq.validate(errors);
      
      if (!errors.isEmpty()) {
         return FORM_VIEW;
      }else {
         return "/index.jsp";
      }
   }      
   

/*      try {
         if(mtReq.getGroupNo()==groupnum.pronumber){
            MakeTeamService.join_pro(mtReq);   
            return "/index.jsp";
         }
         else if(joinReq.getGroupNo()==groupnum.stunumber){
            joinService.join_stu(joinReq);
            return "/index.jsp";
         }
         else{
            errors.put("choose group!!", Boolean.TRUE);
            return FORM_VIEW;
         }
      } catch (DuplicateIdException e) {
         errors.put("duplicateId", Boolean.TRUE);
         return FORM_VIEW;
      }
   }
*/
   //필요없을 수도 있음
  
}