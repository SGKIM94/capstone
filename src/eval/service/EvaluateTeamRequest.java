package eval.service;

import java.util.Map;

import eval.model.Questions;

public class EvaluateTeamRequest {
		final private int DEFAULT_TOTAL = 0;
		final private int DEFAULT_QUESTION_NO = 7;
		
		private Questions qs;
		private int total;
		
		public EvaluateTeamRequest() {
			qs = new Questions();
			total = DEFAULT_TOTAL;
		}
		
		public EvaluateTeamRequest(Questions q, int tt) {
			qs = q;
			total = tt;
		}
		
		public int countTotal() {
			int total = 0;
			for(int i = 0; i < DEFAULT_QUESTION_NO; i++) {
				total += qs.getQsItemScore(i);
			}
			return total;
		}

		public Questions getQs() {
			return qs;
		}

		public void setQs(Questions qs) {
			this.qs = qs;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
		
		public void validate(Map<String, Boolean> errors) {
			String temps = null;
			String tempc = null;
			for(int i = 0; i < DEFAULT_QUESTION_NO; i++) {
				temps = "score" + ((Integer)(i+1)).toString();
				tempc = "comment" + ((Integer)(i+1)).toString();
				
				checkEmpty(errors, qs.getQsItemScore(i), temps);
				checkEmpty(errors, qs.getQsItemComment(i), tempc);
			}
			//이걸 체크가 가능한가?
			checkEmpty(errors, total, "total");
		}
		
		private void checkEmpty(Map<String, Boolean> errors, 
				String value, String fieldName) {
			if (value == null || value.isEmpty())
				errors.put(fieldName, Boolean.TRUE);
		}
		/* id, group의 입력값이 0인지 아닌지 확인 */
		private void checkEmpty(Map<String, Boolean> errors, 
				Integer id, String fieldName) {
			if (id == 0)
				errors.put(fieldName, Boolean.TRUE);
		}

}
