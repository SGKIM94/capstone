package auth.service;

public class Authority {
	public final int ADMIN = 100;			//관리자
	public final int PRO_DEAN = 5;			//학과장
	public final int PRO_NOT_EVAL=10;		//평가 참여 X 교수
	public final int PRO_EVAL=20;			//평가 참여 O 교수
	public final int STU_NOT_TEAM_=30;		//팀 없는 학생
	public final int STU_TEAM=40;			//팀 있는 학생
}