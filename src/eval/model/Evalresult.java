package eval.model;

import member.model.Professor;
import team.model.Team;

/* 팀별 최종 평가서 */
public class Evalresult {
	
	final private int DEFAULT_TOTAL = 0;
	final private int DEFAULT_RESULT = 0;
	
	private String evalNo;
	private String finalNo;
	private Team team;
	private Professor dean;
	private String comment;
	private int total;
	private int result;
	
	public Evalresult() {
		evalNo = null;
		finalNo = null;
		team = new Team();
		dean = new Professor();
		comment = null;
		total = DEFAULT_TOTAL;
		result = DEFAULT_RESULT;
	}
	
	public Evalresult(String e, String f, Team t, Professor d, String c, int tt, int r) {
		evalNo = e;
		finalNo = f;
		team = t;
		dean = d;
		comment = c;
		total = tt;
		result = r;	
	}
	
	/* 팀별 최종 평가서 번호 만들기 */
	public String makeFinalNo(String e, String t) {
		return e+"-"+t+"-20";								//최종 평가서 끝 일련번호 : 20
	}
	
	public String getEvalNo() {
		return evalNo;
	}

	public void setEvalNo(String evalNo) {
		this.evalNo = evalNo;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Professor getDean() {
		return dean;
	}

	public void setDean(Professor dean) {
		this.dean = dean;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getFinalNo() {
		return finalNo;
	}

	public void setFinalNo(String finalNo) {
		this.finalNo = finalNo;
	}
}
