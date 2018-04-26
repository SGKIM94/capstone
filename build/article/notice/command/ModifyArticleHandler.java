package article.notice.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import article.common.ArticleNotFoundException;
import article.common.PermissionDeniedException;
import article.notice.service.ModifyArticleService;
import article.notice.service.ReadArticleService;
import article.team.model.TeamArticleWriter;
import article.notice.service.ModifyRequest;
import article.notice.service.NoticeData;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";

	private ReadArticleService readService = new ReadArticleService();
	private ModifyArticleService modifyService = new ModifyArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			NoticeData noticeData = readService.getNotice(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if (!canModify(authUser, noticeData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(no, noticeData.getNotice().getWriter(), noticeData.getNotice().getTitle(),
					noticeData.getContent(), noticeData.getOrigin(), noticeData.getStored(), 
					noticeData.getFileSize(), noticeData.getFileType());

			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canModify(User authUser, NoticeData noticeData) {
		String writerId = noticeData.getNotice().getWriter().getId();
		//정수 -> 문자열 변환함
		String temp = authUser.getId();
		return temp.equals(writerId);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		
		User authUser = (User) req.getSession().getAttribute("authUser");
		
		MultipartRequest multi = null;
		int sizeLimit = 10 * 1024 * 1024 ; // 10메가입니다.

		String savePath = req.getSession().getServletContext().getRealPath("/upload");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준

		try{
		multi=new MultipartRequest(req, savePath, sizeLimit, "euc-kr", new DefaultFileRenamePolicy()); 
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
		String noVal = multi.getParameter("no");
		int no = Integer.parseInt(noVal);

		ModifyRequest modReq = new ModifyRequest(no,
				multi.
				multi.getParameter("title"),
				multi.getParameter("content"),
				multi.getOriginalFileName("file"),
				multi.getFilesystemName("file"),
				new TeamArticleWriter("021569", authUser.getId()),
				multi.getFile("file").length(),
				multi.getContentType("file")
				
				);
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
