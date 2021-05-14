package com.kosta.model;

public class UserVO {
	String userid;
	int userpass;
	String email;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getUserpass() {
		return userpass;
	}
	public void setUserpass(int userpass) {
		this.userpass = userpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVO [userid=").append(userid).append(", userpass=").append(userpass).append(", email=")
				.append(email).append("]");
		return builder.toString();
	}
	
	
	
}
