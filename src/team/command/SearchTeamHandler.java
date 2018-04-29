package team.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.DuplicateIdException;
import team.model.Team;
import team.service.*;
import mvc.command.CommandHandler;

import java.text.DecimalFormat;
import java.util.Calendar;


public class SearchTeamHandler implements CommandHandler {
   
   private static final String FORM_VIEW = "/WEB-INF/view/teamSearchForm.jsp";
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
      Calendar currentCalendar = Calendar.getInstance();
         
      String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
      String groupNo;
      String teamNo;
      String teamName;
      String advisor;
      String error = "존재하지 않습니다.";
      
      String subYear = strYear.substring(2);
      teamNo = req.getParameter("teamNo");
      groupNo = req.getParameter("groupNo");      
      String tNo = (subYear + "_" + teamNo + "_" + groupNo);
      
      Team team = searchService.searchTeam(tNo);
      teamName = team.getTeamName();
      advisor = team.getAdvisor();
      
      
      if(advisor.equals("0"))
    	  advisor = "김점구";
      else if(advisor.equals("1"))
    	  advisor = "정지문";      
      else if(advisor.equals("2"))
    	  advisor = "송은지";
      else if(advisor.equals("3"))
    	  advisor = "나상엽";
      else if(advisor.equals("4"))
    	  advisor = "황정희";
      else if(advisor.equals("5"))
    	  advisor = "김현철";
      else if(advisor.equals("6"))
    	  advisor = "김정길";
      else if(advisor.equals("7"))
    	  advisor = "문송철";
      else if(advisor.equals("8"))
    	  advisor = "Matthew Oakley";
      else if(advisor.equals("9"))
    	  advisor = "기창진";
      else
    	  advisor = "알수 없음";
      
      System.out.println(advisor);
      
     try {
         if(team!=null){
            session.setAttribute("tName", teamName);
            session.setAttribute("advisor", advisor);
            return FORM_VIEW;
         }
         else {
        	 req.setAttribute("error", error);
        	 return "/index.jsp";		// 에러처리 다시해야됌
         }
      } catch (DuplicateIdException e) {
    	 req.setAttribute("error", error);
         return "/index.jsp";
      }
   }
}