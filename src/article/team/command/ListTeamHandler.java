package article.team.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.team.service.ListTeamService;
import member.service.DuplicateIdException;
import team.model.Team;
import team.service.*;
import mvc.command.CommandHandler;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ListTeamHandler implements CommandHandler {
   
   private static final String FORM_VIEW = "/index.jsp";
   private ListTeamService searchService = new ListTeamService();
     
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

	 
      String groupNo;
      String teamNo;
      String teamName;
      String date;
      String filetype;
      
      teamNo = req.getParameter("teamNo");
      groupNo = req.getParameter("groupNo");
      date = req.getParameter("date");
      filetype = req.getParameter("filetype");
      String subYear = date.substring(2,4);
      String tNo = (subYear + "_" + teamNo + "_" + groupNo);
      
      Map<String, Boolean> errors = new HashMap<>();
      req.setAttribute("errors", errors);
      
      
      try {
         if(searchService.searchTeam(tNo) == true){        	
        	Team team = searchService.selectTeam(tNo);        	
        	teamName = team.getTeamName();
        	req.setAttribute("main_tName", teamName);
        	req.setAttribute("listTno", tNo);
        	req.setAttribute("fileType", filetype);
            return FORM_VIEW;
         }
         else {
        	 req.setAttribute("main_tName", null);
        	 errors.put("listTeamNotExist", Boolean.TRUE);
        	 return FORM_VIEW;		// 에러처리 다시해야됌
         }
      } catch (DuplicateIdException e) {
    	 req.setAttribute("error", errors);
         return null;
      }
   }
}