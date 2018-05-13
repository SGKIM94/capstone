package eval.service;

import java.util.List;

import member.model.Professor;

public class EvalProfList {
	private int total;
	private List<Professor> list;	//코드 재사용이 안됨.... 공지사항과 따로??

	public EvalProfList(int total, List<Professor> list) {
		this.total = total;
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Professor> getList() {
		return list;
	}

	public void setList(List<Professor> list) {
		this.list = list;
	}
}
