package eval.service;

import java.util.List;

import eval.model.EvalTeam;

public class EvalTeamPage {
		private int total;
		private int currentPage;
		private List<EvalTeam> content;	//코드 재사용이 안됨.... 공지사항과 따로??
		private int totalPages;
		private int startPage;
		private int endPage;

		public EvalTeamPage(int total, int currentPage, int size, List<EvalTeam> content) {
			this.total = total;
			this.currentPage = currentPage;
			this.content = content;
			if (total == 0) {
				totalPages = 0;
				startPage = 0;
				endPage = 0;
			} else {
				totalPages = total / size;
				if (total % size > 0) {
					totalPages++;
				}
				int modVal = currentPage % 5;
				startPage = currentPage / 5 * 5 + 1;
				if (modVal == 0) startPage -= 5;
				
				endPage = startPage + 4;
				if (endPage > totalPages) endPage = totalPages;
			}
		}

		public int getTotal() {
			return total;
		}

		public boolean hasNoArticles() {
			return total == 0;
		}

		public boolean hasArticles() {
			return total > 0;
		}
		
		public int getCurrentPage() {
			return currentPage;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public List<EvalTeam> getContent() {
			return content;
		}

		public int getStartPage() {
			return startPage;
		}
		
		public int getEndPage() {
			return endPage;
		}
	}
