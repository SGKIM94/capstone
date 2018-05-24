package eval.command;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import eval.dao.EvalTeamDao;
import eval.model.EvalFinal;
import eval.service.AllEvalStatusValue;
import eval.service.EvaluateFinalService;
import eval.service.EvaluateTeamService;
import eval.service.ShowTeam;
import eval.service.ShowTeamMember;
import mvc.command.CommandHandler;

public class EvaluateFinalHandler implements CommandHandler {
	
	private static final String EVAL_VIEW = "/WEB-INF/view/FinalList.jsp";
	private static final String FORM_VIEW = "/WEB-INF/view/FinalForm.jsp";
	private EvaluateFinalService evaluateFinalService = new EvaluateFinalService();
	private EvaluateTeamService evaluateTeamService = new EvaluateTeamService();
	
	final public static int DEFAULT_LIST_NO = 7;	
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User)req.getSession(false).getAttribute("authProUser");
		
		String teamNo = req.getParameter("team_Noo");
		String finalNo = teamNo + "_"+AllEvalStatusValue.getDefaultFinalDocu();
		/* 최종 평가를 진행할 팀이 선택되지 않았을 경우 */
		if(teamNo==null) {
			req.setAttribute("noselected3", "yes");
			return EVAL_VIEW;
		}
		else {
			req.setAttribute("noselected3", "no");
		}
		
		if(evaluateFinalService.IsFinalCompleted(finalNo)) {
			req.setAttribute("completed1", "yes");
			return EVAL_VIEW;
		}else {
			req.setAttribute("completed1", "no");
		}
		
		String finalbtn = req.getParameter("finalbtn");
		
		if(finalbtn!=null && finalbtn.equals("show")) {
			return processForm(req,res);
		}else{
			return processSubmit(req, res);
		}
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();		
		
		String team_No = req.getParameter("team_Noo");	
		String finalNo = team_No + "_"+AllEvalStatusValue.getDefaultFinalDocu();
		
		EvalFinal evalfinal = evaluateFinalService.SelectEvalFinal(finalNo);
		List<ShowTeamMember> sl = evaluateTeamService.SelectTeamMembers(team_No);
		ShowTeam eteam = evaluateFinalService.selectEteam(team_No);
		if(evalfinal == null) {
			return EVAL_VIEW;
		}
		
		session.setAttribute("teamName", eteam.getTeamName());
		session.setAttribute("efinal", evalfinal);
		session.setAttribute("memberList", sl);
	
		return FORM_VIEW;
	}
	/* 평가 세션을 만들어야할 듯 한데 */
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		String select2 = req.getParameter("select2");
		
		String teamNo = req.getParameter("team_Noo");
		System.out.println("이거 출력좀 : "+ teamNo);
		String team_Name = (String)req.getSession(false).getAttribute("team_name");
		String finalNo = teamNo+"_"+AllEvalStatusValue.getDefaultFinalDocu();
		int state = AllEvalStatusValue.getDefaultEfinalState();
		
		if(select2.equals("complete")) {
			state=AllEvalStatusValue.getEfinalEvalEnded();
		}else {
			state=AllEvalStatusValue.getEfinalEvalStarted();
		}
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		Date now = new Date();
		
		EvalFinal evalfinal = new EvalFinal(
				finalNo,req.getParameter("comment"),now,now, state,
				Integer.parseInt(req.getParameter("total")));
		
		/* 평가 종료를 선택했을때만 빈칸 체크를 해야함 */
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
	
		evaluateFinalService.EvaluateFinal(evalfinal);
		
		return EVAL_VIEW;
		
//		try {
//				evaluateTeamService.EvaluateTeam(evalReq);
//				return "/index.jsp";	
//		} catch (DuplicateIdException e) {		//이미 평가 했는지 에러 처리 필요
//			errors.put("duplicateId", Boolean.TRUE);
//			return FORM_VIEW;
//		}
	}
}
