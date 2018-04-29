package eval.service;

import java.util.ArrayList;
import java.util.Map;

public class MakeRequest {
	
	private String evalNo;
	private String dean;					//학과장님
	private int proNum;						//평가 참여 교수 숫자
	private ArrayList<String> pflist; 		//평가 참여 교수 리스트
	
	public MakeRequest() {
		evalNo = null;
		dean = null;
		proNum = 0;
		pflist = null;
	}
	
	public MakeRequest(String e, String d, int pn, ArrayList<String> pl) {
		evalNo = e;
		dean = d;
		proNum = pn;
		pflist = pl;
	}

	public void validate(Map<String, Boolean> errors) {
		String pro = "proNo";
		String temp = null;
		
		checkEmpty(errors, dean, "dean");
		checkEmpty(errors, proNum, "proNum");
		if (!errors.containsKey("proNum")) {
			for(int i = 0; i < proNum ; i++) {
				temp = pro + ((Integer)i).toString();
				checkEmpty(errors, pflist.get(i), temp);
			}
		}
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


	public ArrayList<String> getPflist() {
		return pflist;
	}

	public void setPflist(ArrayList<String> pflist) {
		this.pflist = pflist;
	}
	
	public void setPf(int n, String p) {
		this.pflist.add(n, p);
	}

	public String getPf(int n) {
		return this.pflist.get(n);
	}
}
