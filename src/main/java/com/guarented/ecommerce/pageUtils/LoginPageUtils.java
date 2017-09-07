package com.guarented.ecommerce.pageUtils;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.UserUtil;
import com.guarented.ecommerce.pages.LoginPage;
import com.guarented.ecommerce.pojo.User;

public class LoginPageUtils extends LoginPage{
	CommonUtils cu=new CommonUtils();
	public LoginPageUtils(WebDriver driver) {
		super(driver);
	}
	
	public LoginPageUtils(WebDriver driver, boolean openPageUrl) {
		super(driver, openPageUrl);
	}
	
	public void signin(User user) throws Exception {

		user = UserUtil.populateUserDetails(user);
		//Thread.sleep(8000);

		Logging.log("Entering the Logging Details");
		//cu.explicitWait(driver, userName, 25L);
		cu.explicitWait(driver, userName);
		//enterUserName(user.getUserName());
		//enterPassword(user.getPassword());
		
		enterUserName(getUserName());
		enterPassword(getPassword());
		clickSubmit();
		//cu.explicitWait(driver, elementTowait, seconds);
		//Thread.sleep(8000);
	}
	
	public void signin() throws Exception {

		Logging.log("Entering the Logging Details");
		//cu.explicitWait(driver, userName, 120L);
		cu.explicitWait(driver, userName);
		//enterUserName(user.getUserName());
		//enterPassword(user.getPassword());
		
		enterUserName(getUserName());
		enterPassword(getPassword());
		clickSubmit();
		//cu.explicitWait(driver, elementTowait, seconds);
		//Thread.sleep(8000);
	}
	
	public void validateLoginPage() throws InterruptedException {
		
		Logging.log("Checking TextMsg in Login Page");
		Assertion.assertTrue(cu.isElementPreset(textMsg, driver),
				"Text Msg element is not present");
		Logging.log("Checking UserName in Login Page");
		Assertion.assertTrue(cu.isElementPreset(userName, driver),
				"user name element is not present");
		Logging.log("Checking Password in Login Page");
		Assertion.assertTrue(cu.isElementPreset(password, driver),
				"password element is not present");
		Logging.log("Checking Submit button in Login Page");
		Assertion.assertTrue(cu.isElementPreset(submit, driver),
				"submit button is not present");
	}
}
