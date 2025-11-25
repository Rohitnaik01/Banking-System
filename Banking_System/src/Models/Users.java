package Models;

public class Users {
	private String name;
	private String email;
	private String mobile;
	private String password;
	
	public Users(String name, String email, long mobile, String password) {
		this.name = name;
		this.email = email;
		this.mobile = mobile + "";
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getPassword() {
		return password;
	}
}
