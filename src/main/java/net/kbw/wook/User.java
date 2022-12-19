package net.kbw.wook;

public class User {
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String UserId;
	private String passWord;
	private String name;
	
	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", passWord=" + passWord + ", name=" + name + ", email=" + email + "]";
	}

	private String email;
	
	
}

