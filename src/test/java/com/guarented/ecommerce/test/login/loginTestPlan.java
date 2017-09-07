package com.guarented.ecommerce.test.login;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pages.HomePage;
import com.guarented.ecommerce.pojo.User;
import com.spire.base.controller.Assertion;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

	
	/**
	 * @uthor: Rahul - 31-05-2016.
	 */

	@Test(groups = { "loginTest"},retryAnalyzer=TestRetryAnalyzer.class)
	public class loginTestPlan extends TestPlan{
		@DataProvider(name = "loginTestData")
		public static Iterator<Object[]> getCandidateInfo(Method method) {

			Iterator<Object[]> objectsFromCsv = null;
			
			try {
				String fileName = "./src/test/java/com/spire/acqura2/test/login/loginTestData.csv";
				LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
				LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
				methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
				entityClazzMap.put("SpireTestObject", SpireTestObject.class);
				entityClazzMap.put("User", User.class);
				objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(
						loginTestPlan.class, entityClazzMap, fileName,
						null, methodFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return objectsFromCsv;
		}
		
		
		/**
		 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Candidate Pool
		 * Tab 4) verify the title of the new window opened
		 * 
		 * Validations: Verify the title of the page
		 */

		@Test(groups = { "validateLoginPageScreen", "sanity" }, dataProvider = "loginTestData")
		public void validateLoginPageScreen(SpireTestObject testObject,User user) throws Exception {

			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.validateLoginPage();
			
		}
		
		@Test(groups = { "validateLogin", "sanity" }, dataProvider = "loginTestData")
		public void validateLogin(SpireTestObject testObject,User user) throws Exception {

			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin(user);
			HomePageUtils hpu=new HomePageUtils(driver);
			CommonUtils cu=new CommonUtils();
			cu.explicitWait(driver,hpu.homeLink, 20L);
			String customerName=cu.getCustomerName();
			hpu.validateHomePage(customerName);
			
		}
		@Test(groups = { "validateLogout", "sanity" }, dataProvider = "loginTestData")
		public void validateLogout(SpireTestObject testObject,User user) throws Exception {
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin(user);
			CommonUtils cu=new CommonUtils();
			HomePageUtils hpu=new HomePageUtils(driver);
			cu.explicitWait(driver, hpu.dropdownMenuToGetLogout, 30L);			
			
			hpu.validateLogout();
		}
}
