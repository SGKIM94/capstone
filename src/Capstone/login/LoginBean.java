package Capstone.login;

public class LoginBean {
	private String userid;
	private String passwd;
	private int access;				//1이면 교수, 2이면 학생
	
	final String _userid_p="professor";
	final String _passwd_p="1234";
	
	final String _userid_s="student";
	final String _passwd_s="1234";
	
	public boolean check_User(){
		access=1;
		if(access==1){
			return check_PUser();
		}
		else{
			return check_SUser();
		}
	}
	
	public boolean check_PUser(){
		System.out.print("Professor");
		if(userid.equals(_userid_p)&&passwd.equals(_passwd_p)){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean check_SUser(){
		System.out.print("Student");
		if(userid.equals(_userid_s)&&passwd.equals(_passwd_s)){
			return true;
		}
		else{
			return false;
		}
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}
}
