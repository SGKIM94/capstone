package eval.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.notice.service.ArticlePage;
import article.team.service.WriteRequest;
import eval.dao.EvalplanDao;
import eval.model.Evalplan;
import eval.service.EvalPlanList;
import eval.service.EvalProfList;
import eval.service.EvalTeamList;
import eval.service.MakeEvalplanService;
import eval.service.MakeRequest;
import jdbc.connection.ConnectionProvider;
import member.dao.ProfessorDao;
import member.model.Professor;
import member.service.DuplicateIdException;
import auth.service.Authority;
import auth.service.LoginFailException;
import auth.service.StudentUser;
import auth.service.User;
import mvc.command.CommandHandler;
import team.dao.TeamDao;

public class MakeEvalplanHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/makeEvalPlanForm.jsp";	//수정과 같은 뷰면 될듯
	private MakeEvalplanService makeService = new MakeEvalplanService();
	private EvalPlanList evalplanlist = new EvalPlanList();
	
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
		HttpSession session = req.getSession();
		
		EvalTeamList evalteamlist = evalplanlist.getEvalTeamList();
		EvalProfList evalproflist = evalplanlist.getEvalProfList();
		
		session.setAttribute("teamList", evalteamlist);
		session.setAttribute("proList", evalproflist);
		
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String value[] = req.getParameterValues("selectprof");
		
		/* 아무 교수도 선택안하고 넘기면 오류 처리해야함. */
		if(value == null) {
			return FORM_VIEW;
		}
		/* 평가 참여 교수 권한 변경 */
		changeProfAuthority(value);
		
		//이 부분 평가 화면으로 넘겨야함.
		return "/index.jsp";
	}
	
	/* 평가 참여 교수 권한 변경 */
	private void changeProfAuthority(String value[]) {
		ProfessorDao professorDao = new ProfessorDao();
		try (Connection conn = ConnectionProvider.getConnection()) {
			for(String var : value) {
				System.out.print(var);
				professorDao.updateAuthority(conn, var, Authority.getProEval());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private MakeRequest createMakeEvalRequest(User user, HttpServletRequest req) throws Exception {
		
	}	
}
