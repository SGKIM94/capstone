package eval.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eval.model.Questions;
import eval.service.EvaluateTeamRequest;
import eval.service.EvaluateTeamService;


public class EvaluateTeamHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/evaluate_team.jsp";
	private EvaluateTeamService evaluateTeamService = new EvaluateTeamService();
	
	final public static int DEFAULT_LIST_NO = 7;	
	
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
	/* 평가 세션을 만들어야할 듯 한데 */
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String temp = null; //임시로 그냥 만든 변수
		
		EvaluateTeamRequest evalReq = new EvaluateTeamRequest();
		evalReq.setQs(processQuestionsSubmit(req,res));
		evalReq.countTotal();
	
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
	
		evalReq.validate(errors);
	
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
	
		try {
				evaluateTeamService.EvaluateTeam(evalReq);
				return "/index.jsp";	
		} catch (DuplicateIdException e) {		//이미 평가 했는지 에러 처리 필요
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
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
	
	private Questions processQuestionsSubmit(HttpServletRequest req, HttpServletResponse res) {
		Questions qs = new Questions();
		String temps = null;
		String tempc = null;
		for(int i =0 ;i<DEFAULT_LIST_NO; i++) {
			
			temps = "score" + ((Integer)(i+1)).toString();
			tempc = "comment" + ((Integer)(i+1)).toString();
			
			qs.setQsItemScore(i+1, parseint(req.getParameter(temps)));
			qs.setQsItemComment(i+1, req.getParameter(tempc));
		}
		return qs;
	}
}



