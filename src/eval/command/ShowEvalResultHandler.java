package eval.command;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.Member;
import auth.service.StudentUser;
import auth.service.User;
import eval.model.Evalpaper;
import eval.model.Questions;
import eval.service.EvaluateTeamService;
import eval.service.ShowTeamMember;
import mvc.command.CommandHandler;

public class ShowEvalResultHandler implements CommandHandler {
	
		private static final String EVAL_VIEW = "/WEB-INF/view/EvalTeamList.jsp";
		private static final String RESULT_VIEW = "/WEB-INF/view/EvalResult.jsp";
		private EvaluateTeamService evaluateTeamService = new EvaluateTeamService();

		public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
			HttpSession session = req.getSession();		
			
			String teamNo = req.getParameter("team_no");
			
			/* 평가가 끝났는지 안끝났는지 점검할 필요 있음 */
			String ep = (String)req.getSession(false).getAttribute("epaperNo");
			
			String confirm = (String)req.getSession(false).getAttribute("confirm");
			if((confirm!=null) && confirm.equals("confirmed")) {
				return EVAL_VIEW;
			}else {
				req.setAttribute("confirm", "no");
			}
			
			if(ep==null) {
				req.setAttribute("noselected2", "yes");
				return "/WEB-INF/view/EvalTeamList.jsp";
			} else {
				req.setAttribute("noselected2", "no");
			}
			
			if(!evaluateTeamService.IsEvalCompleted(ep)) {
				req.setAttribute("finished", "no");
				return "/WEB-INF/view/EvalTeamList.jsp";
			}
			
			
			String team_Name = (String)req.getSession(false).getAttribute("epaperNo");
			
			Evalpaper evalpaper = evaluateTeamService.SelectEvalResult(ep);
			List<ShowTeamMember> sl = evaluateTeamService.SelectTeamMembers(teamNo);
			
			if(evalpaper == null) {
				System.out.println(ep);
				System.out.println("not found");
				req.setAttribute("notstarted", "yes");
				return EVAL_VIEW;
			}
			Questions qs = evalpaper.getQs();
			
			SetItems(req,res,qs);
			
			session.setAttribute("memberList", sl);
			String result = req.getParameter("result");
			System.out.println(result);
			if((result!=null) && (result.equals("resultview"))) {
				return RESULT_VIEW;
			}

			return EVAL_VIEW;
			
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
			String comment = "c";
			String point = "v";
			
			String setvalue1 = null;
			String setvalue2 = null;
			
			for(int i=0;i<7;i++) {
				setvalue1 = comment + Integer.toString(i+1);
				setvalue2 = point + Integer.toString(i+1);
				req.setAttribute(setvalue1, qs.getQsItemComment(i));
				req.setAttribute(setvalue2, qs.getQsItemScore(i));
			}
	}

}
