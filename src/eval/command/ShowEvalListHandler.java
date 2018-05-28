package eval.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.Member;
import auth.service.StudentUser;
import auth.service.User;
import eval.model.Evalpaper;
import eval.service.AllEvalStatusValue;
import eval.service.EpaperResult;
import eval.service.EvalPaperList;
import eval.service.EvalpaperListService;
import eval.service.EvalplanStatuService;
import eval.service.ShowResultListService;
import mvc.command.CommandHandler;

public class ShowEvalListHandler implements CommandHandler {
	
	ShowResultListService showResultListService = new ShowResultListService();
	EvalpaperListService evalpaperListService = new EvalpaperListService();
	EvalplanStatuService evalplanStatuService = new EvalplanStatuService();
	
	private static final String EVAL_TEAM_VIEW = "/WEB-INF/view/EvalTeamList.jsp";
	private static final String STU_MAIN_VIEW = "/index.jsp";
	private static final String STU_RESULT_VIEW = "/WEB-INF/view/StudentResultList.jsp";
	private static final String DEAN_RESULT_VIEW = "/WEB-INF/view/FinalList.jsp";
	private static final String PRO_RESULT_VIEW = "/WEB-INF/view/ShowFinalResult.jsp";
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		User user = (User)req.getSession(false).getAttribute("authProUser");
		//학과장일때를 확인해야하는지 필요할까?
		String before = req.getParameter("beforeview");
		if((before!=null)&&(before.equals("yes"))) {
			return EVAL_TEAM_VIEW;
		}
		
		String before1 = req.getParameter("beforeview1");
		if((before1!=null)&&(before1.equals("yes"))) {
			return EVAL_TEAM_VIEW;
		}
		
		StudentUser stu = (StudentUser)req.getSession(false).getAttribute("authStuUser");
		String who = req.getParameter("who");
		
		if((who!=null)&&(who.equals("pro"))) {
			return process_pro(req,res);
		}
		
		if((who!=null)&&(who.equals("dean"))) {
			return process_dean(req,res);
		}
		/* 학생 결과 */
		if(user == null) {
			return process_stu(req,res);
		}
//		else if(stu == null) {	//교수 결과
//			return process_dean(req,res);
//		}
		return null;
	}
	
	public String process_stu(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(evalplanStatuService.CheckEvalState()==AllEvalStatusValue.getDefaultEvalPlanState()) {
   		  req.setAttribute("notstarted", "yes");
   		  return STU_MAIN_VIEW;
   	  	}
		
		HttpSession session = req.getSession();		
		
		StudentUser stu = (StudentUser)req.getSession(false).getAttribute("authStuUser");
		Member team = (Member)req.getSession(false).getAttribute("authTeam");
		/* 평가가 끝났는지 안끝났는지 점검할 필요 있음 */		
		
		EvalPaperList el = evalpaperListService.getEvalPaperList(team.getTeamNo());
		List<EpaperResult> eprl = showResultListService.MakeResultList(el.getList());	
		session.setAttribute("EvalList", eprl);

		return STU_RESULT_VIEW;
	}
	
public String process_dean(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession();		
		
		String teamNo = req.getParameter("teamNo");
		EvalPaperList el = evalpaperListService.getEvalPaperList(teamNo);
		
		List<EpaperResult> eprl = showResultListService.MakeResultList(el.getList());
		
		session.setAttribute("team_No", teamNo);
		session.setAttribute("SelectEvalList", eprl);
		session.setAttribute("tteamNo", teamNo);
		
		return DEAN_RESULT_VIEW;
	}

public String process_pro(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		HttpSession session = req.getSession();		
		
		String teamNo = req.getParameter("teamNo");
		EvalPaperList el = evalpaperListService.getEvalPaperList(teamNo);
		
		List<EpaperResult> eprl = showResultListService.MakeResultList(el.getList());
		
		session.setAttribute("team_No", teamNo);
		session.setAttribute("SelectEvalList", eprl);
		session.setAttribute("tteamNo", teamNo);
		
		return PRO_RESULT_VIEW;
	}
}
