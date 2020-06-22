package domain;

public class User {
	private String loginName;
	private String name;
	private int userType;

	public User() {
		super();
	}

	public User(String loginName, String name, int userType) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.userType = userType;
	}

	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}	
}
