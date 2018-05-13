package eval.service;

import java.util.List;

public class EvalTeamList {

	private int total;
	private List<String> list;	//코드 재사용이 안됨.... 공지사항과 따로??

	public EvalTeamList(int total, List<String> list) {
		this.total = total;
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}
