package eval.command;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import eval.model.Evalpaper;
import eval.model.Questions;
import eval.service.EvaluateTeamRequest;
import eval.service.EvaluateTeamService;
import mvc.command.CommandHandler;


public class EvaluateTeamHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/evaluateTeam.jsp";
	private static final String EVAL_VIEW = "/WEB-INF/view/EvalTeamList.jsp";
	private EvaluateTeamService evaluateTeamService = new EvaluateTeamService();
	
	final public static int DEFAULT_LIST_NO = 7;	
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		/* 평가가 끝났는지 안끝났는지 점검할 필요 있음 */
		
		String eval = req.getParameter("eval");
		
		if(eval.equals("true")) {
			return processForm(req,res);
		}else {
			return processSubmit(req, res);
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		String ep = (String)req.getSession(false).getAttribute("epaperNo");
		
		Evalpaper evalpaper = evaluateTeamService.SelectEvalpaper(ep);
		if(evalpaper == null) {
			return EVAL_VIEW;
		}
		Questions qs = evalpaper.getQs();
		
		SetItems(req,res,qs);
		
		return FORM_VIEW;
	}
	/* 평가 세션을 만들어야할 듯 한데 */
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Questions qs = GetItems(req,res);
		
		String epaperNo = (String)req.getSession(false).getAttribute("epaperNo");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		Date now = new Date();
		EvaluateTeamRequest evalReq = new EvaluateTeamRequest(
				epaperNo,qs,now,now);
		
		/* 평가 종료를 선택했을때만 빈칸 체크를 해야함 */
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
	
		evaluateTeamService.EvaluateTeam(evalReq);
		return EVAL_VIEW;
		
//		try {
//				evaluateTeamService.EvaluateTeam(evalReq);
//				return "/index.jsp";	
//		} catch (DuplicateIdException e) {		//이미 평가 했는지 에러 처리 필요
//			errors.put("duplicateId", Boolean.TRUE);
//			return FORM_VIEW;
//		}
	}

	//필요있네
	private int parseint(String str) {
		String temp = str;
	try{
		return Integer.parseInt(temp);
	} catch(NumberFormatException nfe){
		System.err.println(nfe);
		//점수 미선택 오류 지정
		throw new LoginFailException(); 
	}
	}
	
	private void SetItems(HttpServletRequest req, HttpServletResponse res, Questions qs) {
		String comment = "comment";
		String point = "val";
		
		String setvalue1 = null;
		String setvalue2 = null;
		
		for(int i=1;i<8;i++) {
			setvalue1 = comment + Integer.toString(i);
			setvalue2 = point + Integer.toString(i);
			req.setAttribute(setvalue1, qs.getQsItemComment(i));
			req.setAttribute(setvalue2, qs.getQsItemScore(i));
		}
	}
	
	private Questions GetItems(HttpServletRequest req, HttpServletResponse res) {
		String comment = "comment";
		String point = "val";
		Questions qs = new Questions();
		String getvalue1 = null;
		String getvalue2 = null;
		
		for(int i=1;i<8;i++) {
			getvalue1 = comment + Integer.toString(i);
			getvalue2 = point + Integer.toString(i);
			
			qs.setQsItemScore(i, Integer.parseInt(req.getParameter(getvalue2)));
			qs.setQsItemComment(i, req.getParameter(getvalue1));
		}
		return qs;
	}
}



