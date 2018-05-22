package eval.model;

import java.util.Date;

import member.model.Professor;
import team.model.Team;

/* 팀별 개별 교수님 평가서 */
public class Evalpaper {
	
	final private int DEFAULT_TOTAL = 0;
	final private int DEFAULT_STATE = 0;
	final private int PASS = 3;
	final private int AGAIN = 2;
	final private int FAIL = 1;
	
	final private int PASS_SCORE = 44;
	final private int AGAIN_SCORE = 34;
	
	private String paperNo;
	private Questions qs;					
	private Date regDate;
	private Date endDate;
	private int total;
	private int State;
	
	public Evalpaper() {
		paperNo = null;
		qs = new Questions();
		regDate = null;
		endDate = null;
		total = DEFAULT_TOTAL;
		State = DEFAULT_STATE;
	}
	
	public Evalpaper(String pn, Questions q, Date reg, Date end, int tt, int r) {
		paperNo = pn;
		qs = q;
		regDate = reg;
		endDate = end;
		total = tt;
		State = r;
	}
	
	public Evalpaper(String pn, Questions q, Date reg, Date end) {
		paperNo = pn;
		qs = q;
		regDate = reg;
		endDate = end;
		total = countTotal();
		State = selectState();
	}
	
	private int countTotal() {
		int total = 0;
		for(int i = 0; i < 7 ; i++) {
			total += qs.getQsItemScore(i);
		}
		return total;
	}
	
	private int selectState() {
		if(total > PASS_SCORE) {
			return PASS;
		}else if(total > AGAIN_SCORE) {
			return AGAIN;
		}
		else {
			return FAIL;
		}
	}
	
	/* 개별 교수님 평가서 번호 */
	public String makePaperNo(String eNo, String tNo, String pId) {
		return eNo+"-"+tNo+"-"+pId;
	}

	public String getPaperNo() {
		return paperNo;
	}

	public void setPaperNo(String paperNo) {
		this.paperNo = paperNo;
	}

	public Questions getQs() {
		return qs;
	}

	public void setQs(Questions qs) {
		this.qs = qs;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}
}
