package domain;

public class Admin {
	private String loginName;
	private String name;
	private String password;

	public Admin() {
	}

	public Admin(String loginName, String name, String password) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"loginName='" + loginName + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
