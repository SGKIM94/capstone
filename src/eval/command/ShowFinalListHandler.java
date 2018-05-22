package eval.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.StudentUser;
import auth.service.User;
import eval.service.EvalpaperListService;
import eval.service.ShowResultListService;
import mvc.command.CommandHandler;

public class ShowFinalListHandler implements CommandHandler{

	ShowResultListService showResultListService = new ShowResultListService();
	EvalpaperListService evalpaperListService = new EvalpaperListService();
	
	private static final String EVAL_TEAM_VIEW = "/WEB-INF/view/EvalTeamList.jsp";
	private static final String STU_MAIN_VIEW = "/index.jsp";
	private static final String EVAL_VIEW = "/WEB-INF/view/FinalEval.jsp";
	private static final String PRO_RESULT_VIEW = "/WEB-INF/view/FinalList.jsp";
	
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		User user = (User)req.getSession(false).getAttribute("authProUser");
		//학과장일때를 확인해야하는지 필요할까?
		
		StudentUser stu = (StudentUser)req.getSession(false).getAttribute("authStuUser");
		/* 학생 결과 */
		if(user == null) {
			return process_stu(req,res);
		}
//		else if(stu == null) {	//교수 결과
//			return process_dean(req,res);
//		}
		return null;
	}
}
