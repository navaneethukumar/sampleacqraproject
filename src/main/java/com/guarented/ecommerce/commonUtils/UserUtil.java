package com.guarented.ecommerce.commonUtils;

import java.util.Properties;

import com.guarented.ecommerce.pojo.User;

public class UserUtil {

	static Properties prop = SpireProperties.loadDbProperties();

	public static User populateUserDetails(User user) {

		String host = ContextManager.getThreadContext().getInstance();

		if (user.getRole().equalsIgnoreCase("ADMIN")) {

			user.setUserName(prop.getProperty(host.toUpperCase()
					+ "_ADMIN_USER_ID"));
			user.setPassword(prop.getProperty(host.toUpperCase()
					+ "_ADMIN_PASSWORD"));

		} else if (user.getRole().equalsIgnoreCase("RECRUITER")) {

			user.setUserName(prop.getProperty(host.toUpperCase()
					+ "_RECRUITER_USER_ID"));
			user.setPassword(prop.getProperty(host.toUpperCase()
					+ "_RECRUITER_PASSWORD"));

		} else if (user.getRole().equalsIgnoreCase("RECPRODUCTION")) {

			user.setUserName(prop.getProperty(host.toUpperCase()
					+ "_RECPRODUCTION_USER_ID"));
			user.setPassword(prop.getProperty(host.toUpperCase()
					+ "_RECPRODUCTION_PASSWORD"));

		} else if (user.getRole().equalsIgnoreCase("NARECRUITER")) {

			user.setUserName(prop.getProperty(host.toUpperCase()
					+ "_NARECRUITER_USER_ID"));
			user.setPassword(prop.getProperty(host.toUpperCase()
					+ "_NARECRUITER_PASSWORD"));

		} 
		

		return user;

	}

	/*public static void main(String[] args) {

		User user = new User();
		user.setRole("rec");

		user = populateUserDetails(user);

		System.out.println(user.getUserName());
		System.out.println(user.getPassword());

	}*/

}
