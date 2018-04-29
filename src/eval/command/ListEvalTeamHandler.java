package eval.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eval.service.EvalTeamPage;
import eval.service.ListEvalTeamService;
import mvc.command.CommandHandler;

public class ListEvalTeamHandler implements CommandHandler {

		private ListEvalTeamService listService = new ListEvalTeamService();

		@Override
		public String process(HttpServletRequest req, HttpServletResponse res) 
				throws Exception {
			String pageNoVal = req.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			EvalTeamPage evalteampage = listService.getEvalTeamPage(pageNo);
			req.setAttribute("evalteamPage",evalteampage);
			return "/WEB-INF/view/listTeam.jsp";
	}
}
