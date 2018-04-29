package auth.service;

import java.util.Calendar;

import auth.service.User;

public class EvaluateProf {
	
	private User user;
	private String EvalNo;
	private String TeamNo;
	
	EvaluateProf(String id, String name, int access, String teamNo){
		user = new User(id, name, access);
		EvalNo = toGetEvalNo();
		TeamNo = teamNo;
	}

	private String toGetEvalNo() {
		Calendar c = Calendar.getInstance();
		String evalNo = Integer.toString(c.get(Calendar.YEAR))+"-01";
		return evalNo;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEvalNo() {
		return EvalNo;
	}

	public void setEvalNo(String evalNo) {
		EvalNo = evalNo;
	}

	public String getTeamNo() {
		return TeamNo;
	}

	public void setTeamNo(String teamNo) {
		TeamNo = teamNo;
	}
}
