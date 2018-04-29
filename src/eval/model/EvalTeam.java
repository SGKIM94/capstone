package eval.model;

//import java.util.ArrayList;

public class EvalTeam {
	
	private String teamNo;
	private String finalNo;
	
	public EvalTeam() {
		teamNo = null;
		finalNo = null;
	}
	public EvalTeam(String t, String f) {
		teamNo = t;
		finalNo = f;
	}
	
	public String getTeamNo() {
		return teamNo;
	}
	
	public void setTeamNo(String t) {
		this.teamNo = t;
	}
	public String getFinalNo() {
		return finalNo;
	}
	
	public void setFinalNo(String f) {
		this.finalNo = f;
	}
	
	/*
	private ArrayList<Teamset> etl;

	public ArrayList<Teamset> getEtl() {
		return etl;
	}

	public void setEtl(ArrayList<Teamset> etl) {
		this.etl = etl;
	}
	
	public void addTeam(String t, String f) {
		Teamset newTeam = new Teamset(t,f);
		etl.add(newTeam);
	}
	
	public String searchFinalByTeam(String t) {
		for(int i = 0; i<etl.size(); i++) {
			if(etl.get(i).getTeamNo() == t) {
				return etl.get(i).getFinalNo(); 
			}
		}
		return null;
	}*/
}

/*
class Teamset{
	
}*/