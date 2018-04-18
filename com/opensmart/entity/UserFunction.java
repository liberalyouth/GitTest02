package com.opensmart.entity;

public class UserFunction {

	String userId;
	String user_function;
	String user_account;
	String password;
	public UserFunction() {
		super();
	}
	public UserFunction(String userId, String user_function,
			String user_account, String password) {
		super();
		this.userId = userId;
		this.user_function = user_function;
		this.user_account = user_account;
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUser_function() {
		return user_function;
	}
	public void setUser_function(String user_function) {
		this.user_function = user_function;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserFunction [userId=" + userId + ", user_function="
				+ user_function + ", user_account=" + user_account
				+ ", password=" + password + "]";
	}
}
