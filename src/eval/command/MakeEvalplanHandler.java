package eval.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eval.dao.EvalplanDao;
import eval.model.Evalplan;
import eval.service.MakeEvalplanService;
import eval.service.MakeRequest;
import jdbc.connection.ConnectionProvider;
import member.dao.ProfessorDao;
import member.model.Professor;
import member.service.DuplicateIdException;
import auth.service.LoginFailException;
import auth.service.User;
import mvc.command.CommandHandler;

public class MakeEvalplanHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/makeEvalPlanForm.jsp";	//수정과 같은 뷰면 될듯
	private MakeEvalplanService makeService = new MakeEvalplanService();
	
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
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		User user = (User)req.getSession(false).getAttribute("authUser");
		MakeRequest makeReq = createMakeRequest(user, req);
		makeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			makeService.Make(makeReq);
			return "/index.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
		//return "/WEB-INF/view/listTeam.jsp";
	}
	private MakeRequest createMakeRequest(User user, HttpServletRequest req) {
		ProfessorDao professorDao = new ProfessorDao();
		Professor professor = new Professor();
		try (Connection conn = ConnectionProvider.getConnection()) {
			professor = professorDao.selectById(conn, user.getId());
			if (professor == null) {
				throw new LoginFailException();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		MakeRequest makeReq = new MakeRequest();
		
		int proNo = (int)(req.getAttribute("proNo"));
		String pro = "proNo";
		String temp = null;
		
		makeReq.setDean(professor.getProId());
		makeReq.setProNum((int)(req.getAttribute("proNo")));
		for(int i = 0; i < proNo ; i++) {
			temp = pro + ((Integer)i).toString();
			makeReq.setPf(i, temp);
		}
		/*
		makeReq.setRegDate(regDate);
		makeReq.setEndDate(endDate);
		*
		*/
		return makeReq;
	}
}
