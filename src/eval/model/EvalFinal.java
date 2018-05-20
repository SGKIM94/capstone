package eval.model;

import java.util.Date;

public class EvalFinal {
	private final int DEFAULT_TOTAL = 0;
	private final int DEFAULT_STATE = 0;
	
	private String finalNo;
	private String comment;
	private Date regDate;
	private Date endDate;
	private int total;
	private int state;
	
	public EvalFinal() {
		finalNo = null;
		comment = null;
		regDate = null;
		endDate = null;
		total = DEFAULT_TOTAL;
		state = DEFAULT_STATE;
	}
	
	/* 모든 멤버변수 받는 생성자 */
	public EvalFinal(String fno, String c, Date r, Date e, int t, int s) {
		finalNo = fno;
		comment = c;
		regDate = r;
		endDate = e;
		total = t;
		state = s;
	}
	
	/* 평가 계획시 생성되는 최종 평가서 */
	public EvalFinal(String fno) {
		finalNo = fno;
		comment = null;
		regDate = null;
		endDate = null;
		total = DEFAULT_TOTAL;
		state = DEFAULT_STATE;
	}

	public String getFinalNo() {
		return finalNo;
	}

	public void setFinalNo(String finalNo) {
		this.finalNo = finalNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
