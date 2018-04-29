package eval.model;


import java.util.ArrayList;
import java.util.Date;

import member.dao.ProfessorDao;
import member.model.Professor;


/* 평가 계획서 */
public class Evalplan {
	
	//final private String DEFAULT_EVAL_NO= "2018-01";			//2018년 5월 20일 4학년
	final private int DEFAULT_EVAL_STATE = 0;
	final private int DEFAULT_PRO_NO = 0;
	final private int DEFAULT_TEAM_NO = 0;
	final private int THERE_IS_NO_TEAM = 100;
	final private int EVAL_START_YET = 0;						//쓸모 있는지 모르겠음 밑에 3개
	final private int EVAL_IS_ON_GOING = 1;
	final private int EVAL_ENDED = 2;								
	
	
	private String evalNo;
	private String dean;					//학과장님
	private int proNum;						//평가 참여 교수 숫자
	private int tNo;						//졸업작품 참여 팀 숫자
	private ArrayList<String> pflist; 		//평가 참여 교수 리스트
	//private ArrayList<Eteam> eteamlist;		//평가 참여 팀 리스트 - 참여 1팀당 팀 넘버, 교수님 개인 평가지 arraylist, 최종 평가지 번호
	private ArrayList<String> tlist;
	private Date regDate;
	private Date endDate;
	private int evalState;
	
	public Evalplan() {
		evalNo = null;
		dean = null;
		proNum = DEFAULT_PRO_NO;
		tNo = DEFAULT_TEAM_NO;
		pflist = new ArrayList<String>();
		tlist = new ArrayList<String>();
		regDate = null;
		endDate = null;
		evalState = EVAL_START_YET;
	}

	public Evalplan(String e, String d, int pn, int tn, ArrayList<String> pl,
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
	
	/* 평가 시작버튼 누르면 생성 */
	public Evalplan(String e, String s, int pn, ArrayList<String> pl, Date reg, Date end) {		
		evalNo = e;
		dean = s;
		proNum = pn;
		tNo = 0;
		pflist = pl;
		tlist = null;
		regDate = reg;
		endDate = end;
		evalState = EVAL_IS_ON_GOING;
	}
	
	/*
	public int getIndexEteamNo(String t) {
		int index = 0;
		
		if(eteamlist.size()==0) {
			return THERE_IS_NO_TEAM;
		}
		
		for(int i = 0; i<eteamlist.size();i++) {
			if(eteamlist.get(i).getTeamNo() == t) {
				index = i;
			}
        }
		return index;
	}*/
	
	public ArrayList<String> getTlist()  {
		/* try catch 로 바궈야하지 않을까 싶음
		if(tlist.size() == 0) {
			return null;
		}*/
		return tlist;
	}

	public void setTlist(ArrayList<String> tlist) {
		this.tlist = tlist;
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
	
	
	/*
	public ArrayList<Eteam> getEteamlist() {
		return eteamlist;
	}
	
	public String getEteamNo(int n) {
		return eteamlist.get(n).getTeamNo();
	}
	public String getEteamPfNo(int n, int pn) {
		return eteamlist.get(n).getPfNo(pn);
	}
	public String getEteamFinalNo(int n) {
		return eteamlist.get(n).getFinalNo();
	}
	*/
	/* 팀번호로 개별 평가지 번호 읽어오기 */
	/*
	public String getEteamPfNo(int n, String t) {
		int index = getIndexEteamNo(t);
		if(index == THERE_IS_NO_TEAM) {
			return null;
		}
		return eteamlist.get(index).getPfNo(n);
	}
	*/
	/* 팀번호로 최종 평가지 번호 읽어오기 */
	/*
	public String getEteamFinalNo(String t) {
		int index = getIndexEteamNo(t);
		if(index == THERE_IS_NO_TEAM) {
			return null;
		}
		return eteamlist.get(index).getFinalNo();
	}
	
	public void setEteamNo(int n, String t) {
		eteamlist.get(n).setTeamNo(t);
	}
	public void setEteamPfNo(int n, int pn, String p) {
		eteamlist.get(n).setPfNo(pn, p);
	}
	public void setEteamFinalNo(int n, String f) {
		eteamlist.get(n).setFinalNo(f);
	}
	
	public void setEteamlist(ArrayList<Eteam> eteamlist) {
		this.eteamlist = eteamlist;
	}*/

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

/*
class Eteam{
	private String teamNo;
	private ArrayList<String> pfNoList;
	private String finalNo;
	
	public Eteam() {
		teamNo = null;
		pfNoList = new ArrayList<String>();
		finalNo = null;
	}
	
	public Eteam(String t, ArrayList<String> pfl, String f) {
		teamNo = t;
		pfNoList = pfl;
		finalNo = f;
	}
	
	public String getTeamNo() {
		return teamNo;
	}

	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}
	public String getPfNo(int n) {
		return pfNoList.get(n);
	}

	public void setPfNo(int n, String pfNo) {
		this.pfNoList.set(n, pfNo);
	}
	public String getFinalNo() {
		return finalNo;
	}

	public void setFinalNo(String finalNo) {
		this.finalNo = finalNo;
	}
}*/
