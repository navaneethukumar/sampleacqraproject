package com.guarented.ecommerce.pojo;

import com.spire.base.controller.Logging;

public class User {

	String userName;
	String password;
	String email;
	String role;

	public String getUserName() {

		Logging.log("Getting UserName from Property File");
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {

		Logging.log("Getting Password from Property File");
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {

		Logging.log("Getting Role for the test case");

		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
