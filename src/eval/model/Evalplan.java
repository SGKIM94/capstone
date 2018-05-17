package eval.model;

import java.util.Date;
import java.util.Map;

/* 평가 계획서 */
public class Evalplan {
	
	//final private String DEFAULT_EVAL_NO= "2018-01";			//2018년 5월 20일 4학년
	final private int EVAL_START_YET = 0;						//쓸모 있는지 모르겠음 밑에 3개
	final private int EVAL_IS_ON_GOING = 1;							
	
	private String evalNo;
	private String dean;					//학과장님
	private Date regDate;
	private Date endDate;
	private int evalState;
	
	public Evalplan() {
		evalNo = null;
		dean = null;
		regDate = null;
		endDate = null;
		evalState = EVAL_START_YET;
	}

	public Evalplan(String e, String d, Date reg, Date end, int es) {
		evalNo = e;
		dean = d;
		regDate = reg;
		endDate = end;
		evalState = es;
	}
	
	/* 평가 시작버튼 누르면 생성 */
	public Evalplan(String e, String s, Date reg, Date end) {		
		evalNo = e;
		dean = s;
		regDate = reg;
		endDate = end;
		evalState = EVAL_IS_ON_GOING;
	}
	
	public void validate(Map<String, Boolean> errors) {	
		checkEmpty(errors, dean, "dean");
	}
	
	private void checkEmpty(Map<String, Boolean> errors, 
			String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}	
	public String getEvalNo() {
		return evalNo;
	}

	public void setEvalNo(String evalNo) {
		this.evalNo = evalNo;
	}

	public String getDean() {
		return dean;
	}

	public void setDean(String dean) {
		this.dean = dean;
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

	public int getEvalState() {
		return evalState;
	}

	public void setEvalState(int evalState) {
		this.evalState = evalState;
	}
}
