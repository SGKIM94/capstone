package article.team.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.DuplicateIdException;
import team.model.Team;
import team.service.*;
import mvc.command.CommandHandler;

import java.text.DecimalFormat;
import java.util.Calendar;


public class ListTeamHandler implements CommandHandler {
   
   private static final String FORM_VIEW = "/index.jsp";
   private SelectTeamService searchService = new SelectTeamService();
     
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

	  HttpSession session = req.getSession();
      
      String groupNo;
      String teamNo;
      String teamName;
      String date;
      String error = "존재하지 않습니다.";
      
      teamNo = req.getParameter("teamNo");
      groupNo = req.getParameter("groupNo");
      date = req.getParameter("date");
      
      String subYear = date.substring(2,4);
      String tNo = (subYear + "_" + teamNo + "_" + groupNo);
      
      Team team = searchService.searchTeam(tNo);
      teamName = team.getTeamName();
      
     try {
         if(team!=null){        	
        	session.setAttribute("main_tName", teamName);
            return FORM_VIEW;
         }
         else {
        	 req.setAttribute("error", error);
        	 return null;		// 에러처리 다시해야됌
         }
      } catch (DuplicateIdException e) {
    	 req.setAttribute("error", error);
         return null;
      }
   }
}