package eval.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eval.service.MakeEvalplanService;
import eval.service.MakeRequest;
import jdbc.connection.ConnectionProvider;
import member.dao.StudentDao;
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
		writeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		String newFileNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newFileNo);
		
		ListArticleHandler listarticlehandler = new ListArticleHandler();
		
		String listjsp = listarticlehandler.process(req, res);
		
		return listjsp;
		//return "/WEB-INF/view/listTeam.jsp";
	}
	private MakeRequest createMakeRequest(User user, HttpServletRequest req) {
		StudentDao studentDao = new StudentDao();
		Student student;
		try (Connection conn = ConnectionProvider.getConnection()) {
			student = studentDao.selectById(conn, user.getId());
			if (student == null) {
				throw new LoginFailException();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
}
