package eval.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import eval.model.Evalplan;
import member.dao.ProfessorDao;
import member.model.Professor;


/* 평가 계획서 */
public class MakeRequest {
	
	//final private String DEFAULT_EVAL_NO= "2018-01";			//2018년 5월 20일 4학년
	final private int DEFAULT_EVAL_STATE = 0;
	final private int DEFAULT_PRO_NO = 0;
	final private int DEFAULT_TEAM_NO = 0;
	final private int THERE_IS_NO_TEAM = 100;
	final private int EVAL_START_YET = 0;						//쓸모 있는지 모르겠음 밑에 3개
	final private int EVAL_IS_ON_GOING = 1;
	final private int EVAL_ENDED = 2;								
	
	private String evalNo;
	private String dean;
	private List<String> pflist; 		//평가 참여 교수 리스트
	//private ArrayList<Eteam> eteamlist;		//평가 참여 팀 리스트 - 참여 1팀당 팀 넘버, 교수님 개인 평가지 arraylist, 최종 평가지 번호
	private List<String> tlist;
	
	public MakeRequest() {
		evalNo = null;
		dean = null;
		pflist = new ArrayList<String>();
		tlist = new ArrayList<String>();
	}
	/* 멤버 변수 전부 매개변수로 받는 생성자 */
	public MakeRequest(String d, List<ShowProf> pl,
			List<ShowTeam> tl) {
		evalNo = MakeEvalNo();
		dean = d;
		pflist = getProIdList(pl);
		tlist = getTeamList(tl);
//		regDate = new Date(date.getTime());
//		endDate = null;
	}
	
	/* 평가 계획서, 평가 교수 리스트, 평가 팀 리스트 완성 생성자 */
	public MakeRequest(String e, String d, int pn, List<ShowProf> pl, List<ShowTeam> tl, Date reg, Date end) {		
		evalNo = e;
		dean = d;
		pflist = getProIdList(pl);
		tlist = getTeamList(tl);
	}
	
	public void validate(Map<String, Boolean> errors) {	
		checkEmpty(errors, dean, "dean");
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
	
	
	private List<String> getProIdList(List<ShowProf> pl){
		List<String> templist = new ArrayList<String>();
		for(ShowProf pro : pl) {
			templist.add(pro.getProId());
		}
		return templist;
	}
	
	private List<String> getTeamList(List<ShowTeam> tl){
		List<String> templist = new ArrayList<String>();
		for(ShowTeam team : tl) {
			templist.add(team.getTeamNo());
		}
		return templist;
	}
	
	private String MakeEvalNo() {
		Calendar currentCalendar = Calendar.getInstance();
		String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
		String evalNo = strYear+"-01";
		return evalNo;
	}
	
	public List<String> getTlist()  {
		/* try catch 로 바궈야하지 않을까 싶음
		if(tlist.size() == 0) {
			return null;
		}*/
		return tlist;
	}

	public void setTlist(List<String> tlist) {
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

	public List<String> getPflist() {
		return pflist;
	}

	public void setPflist(List<String> pflist) {
		this.pflist = pflist;
	}
}