package article.team.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import article.team.model.TeamArticleWriter;
import article.team.service.WriteArticleService;
import article.team.service.WriteRequest;
import auth.service.LoginFailException;
import auth.service.StudentUser;
import auth.service.User;
import jdbc.connection.ConnectionProvider;
import member.model.Student;
import member.dao.StudentDao;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/index.jsp";
	private WriteArticleService writeService = new WriteArticleService();
	
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

		StudentUser user = (StudentUser)req.getSession(false).getAttribute("authStdUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		String newFileNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newFileNo);
		
//		ListArticleHandler listarticlehandler = new ListArticleHandler();
//		
//		String listjsp = listarticlehandler.process(req, res);
		
		return FORM_VIEW;
		//return "/WEB-INF/view/listTeam.jsp";
	}

	private WriteRequest createWriteRequest(StudentUser user, HttpServletRequest req) {
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
		
		MultipartRequest multi = null;
		int sizeLimit = 10 * 1024 * 1024 ; // 10메가입니다.

		String savePath = req.getSession().getServletContext().getRealPath("/upload");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준
		
		savePath = makeDirectory(savePath, student.getTeamNo());
		
		try{
		multi=new MultipartRequest(req, savePath, sizeLimit, "euc-kr", new DefaultFileRenamePolicy()); 
		}catch (Exception e) {
			e.printStackTrace();
		} 

		/*여기서의 이름과 뷰.jsp 파일에서의 이름이 같아야함.*/
		/* 파일 시스템상의 이름을 구하는 방법을 알아보고 코드 다시 수정해야함. */
		return new WriteRequest(null,
				multi.getParameter("title"),
				multi.getOriginalFileName("file"),
				multi.getFilesystemName("file"),
				new TeamArticleWriter(user.getTeamNo(), user.getId()),	//분,초 + 문서번호
				multi.getFile("file").length(),
				multi.getContentType("file"),
				multi.getParameter("filetype"));
	}
	/* 팀폴더 생성 함수 */
	public String makeDirectory(String path, String teamNo) {
		String savePath = path + "/" + teamNo;
		File file = new File(path);
        //upload 폴더가 있는지 점검하는 것 -> 필요한지는 모르겠음
		/*
		if(!file.mkdirs()) {
        	return savePath;
        }*/
        file = new File(savePath);
        if(!file.mkdirs()){
        	return savePath;
        }
		return savePath;
	}
}
