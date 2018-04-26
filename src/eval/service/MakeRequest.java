package eval.service;

import java.util.ArrayList;
import java.util.Date;

import member.model.Professor;

public class MakeRequest {
	private String evalNo;
	private Professor dean;					//학과장님
	private int proNum;						//평가 참여 교수 숫자
	private int tNo;						//졸업작품 참여 팀 숫자
	private ArrayList<String> pflist; 		//평가 참여 교수 리스트
	//private ArrayList<Eteam> eteamlist;		//평가 참여 팀 리스트 - 참여 1팀당 팀 넘버, 교수님 개인 평가지 arraylist, 최종 평가지 번호
	private ArrayList<String> tlist;
	private Date regDate;
	private Date endDate;
	private int evalState;
	
	public MakeRequest(String e, Professor d, int pn, int tn, ArrayList<String> pl,
			ArrayList<String> tl, Date reg, Date end, int es) {
		evalNo = e;
		dean = d;
		proNum = pn;
		tNo = tn;
		pflist = pl;
		tlist = tl;
		regDate = reg;
		endDate = end;
		evalState = es;
	}

	public String getEvalNo() {
		return evalNo;
	}

	public void setEvalNo(String evalNo) {
		this.evalNo = evalNo;
	}

	public Professor getDean() {
		return dean;
	}

	public void setDean(Professor dean) {
		this.dean = dean;
	}

	public int getProNum() {
		return proNum;
	}

	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	public ArrayList<String> getPflist() {
		return pflist;
	}

	public void setPflist(ArrayList<String> pflist) {
		this.pflist = pflist;
	}

	public ArrayList<String> getTlist() {
		return tlist;
	}

	public void setTlist(ArrayList<String> tlist) {
		this.tlist = tlist;
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
