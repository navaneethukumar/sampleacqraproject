package com.spire.acqura2.test.home;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pageUtils.AdvancedSearchPageUtils;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtils;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pojo.User;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "homeTest"},retryAnalyzer=TestRetryAnalyzer.class)

public class homeTestPlan extends TestPlan{
	
	@DataProvider(name = "homeTestData")
	public static Iterator<Object[]> getCandidateInfo(Method method) {

		Iterator<Object[]> objectsFromCsv = null;
		
		try {
			String fileName = "./src/test/java/com/spire/acqura2/test/home/homeTestData.csv";
			LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
			LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
			methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
			entityClazzMap.put("SpireTestObject", SpireTestObject.class);
			entityClazzMap.put("User", User.class);
			
			entityClazzMap.put("TestDataSet", TestDataSet.class);
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(TestDataSet.class, entityClazzMap, fileName, null,
					methodFilter);
			//objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(
			//		homeTestPlan.class, entityClazzMap, fileName,
			//		null, methodFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}
	
	
	
	/*
	 	@Test(groups = { "validateSRStatusListInDropDown", "sanity" }, dataProvider = "homeTestData")
	public void validateSRStatusListInDropDown(SpireTestObject testObject,User user) throws Exception {
		
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		HomePageUtils hpu=new HomePageUtils(driver);
		hpu.validateSRStatusListInDropDown();
	
		
	}
	*/
	
	@Test(groups = { "validateDefaultSRStatusInResultGrid", "sanity" }, dataProvider = "homeTestData")
	public void validateDefaultSRStatusInResultGrid(SpireTestObject testObject,User user) throws Exception {
		
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		HomePageUtils hpu=new HomePageUtils(driver);
		hpu.validateSRStatusListInResultGrid();
		
	}
	
	/*@Test(groups = { "validatePopularPoolCountClick", "sanity" }, dataProvider = "homeTestData")
	public void validatePopularPoolCountClick(SpireTestObject testObject,User user) throws Exception {

		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		HomePageUtils hpu=new HomePageUtils(driver);		
		hpu.validatePopularPoolCountClick();
		
		
	}*/
	
	@Test(groups ={"checkAdvanceSearchPageLoaded","Sanity"},dataProvider="homeTestData")
	public void checkAdvanceSearchPageLoaded(SpireTestObject testObject,User user,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		HomePageUtils hpu=new HomePageUtils(driver);
		AdvancedSearchPageUtils apu=new  AdvancedSearchPageUtils(driver);
		CommonUtils cu=new CommonUtils();
		String customerName=cu.getCustomerName();	
		hpu.clickAdvanceSearch(customerName);
		apu.validateAdvanceSearchPage();
		
	}
	
	
	@Test(groups ={"checkReqSuugestedListInDropdown","1Sanity1"},dataProvider="homeTestData")
	public void checkReqSuugestedListInDropdown(SpireTestObject testObject,User user,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		HomePageUtils hpu=new HomePageUtils(driver);
		
		CommonUtils cu=new CommonUtils();
		String customerName=cu.getCustomerName();
		if(customerName.equalsIgnoreCase("AccentureNA")){
		cu.explicitWait(driver, hpu.reqStatusLabel, 30L);
	    hpu.clickReqStatusLabel(); 
		cu.moveVerticalScrollBar(driver, 150);
		hpu.enterReqId(customerName, GuarentedConstants.TYPE_REQ_NA);
		hpu.validateReqSuggestedList(customerName, GuarentedConstants.TYPE_REQ_NA);
		}else{
			cu.explicitWait(driver, hpu.reqStatusLabel, 30L);
			hpu.clickReqStatusLabel(); 
			cu.moveVerticalScrollBar(driver, 150);
			hpu.enterReqId(customerName, GuarentedConstants.TYPE_REQ);
			hpu.validateReqSuggestedList(customerName, GuarentedConstants.TYPE_REQ);
		}
		
		
	}
	
	

	@Test(groups ={"checkSearchedReqListInResultGrid","Sanity"},dataProvider="homeTestData")
	public void checkSearchedReqListInResultGrid(SpireTestObject testObject,User user,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		HomePageUtils hpu=new HomePageUtils(driver);
		
		CommonUtils cu=new CommonUtils();
		String customerName=cu.getCustomerName();
		if(customerName.equalsIgnoreCase("AccentureNA")){
		cu.explicitWait(driver, hpu.reqStatusLabel, 30L);
	    hpu.clickReqStatusLabel(); 		
		cu.moveVerticalScrollBar(driver, 150);
		hpu.enterReqId(customerName, GuarentedConstants.TYPE_REQ_NA);
		
		cu.explicitWait(driver, hpu.reqSuggestedList, 30L);
		cu.mouseOverToElement(driver, hpu.getReqSuggestedListWebElement(customerName).get(0));
		String firstReqInDropdown=hpu.getReqSuggestedListWebElement(customerName).get(0).getText().trim();
		System.out.println("firstReqInDropdown : "+firstReqInDropdown);
		hpu.clickFirstReqInDropDownList(customerName);
		hpu.clickSubmitButton();
		//Thread.sleep(10000);
		cu.explicitWait(driver, hpu.reqIdsInResultGridNA, 45L);
		hpu.validateReqInResultGrid(customerName, firstReqInDropdown);
		
		}else{
			cu.explicitWait(driver, hpu.reqStatusLabel, 30L);
			hpu.clickReqStatusLabel(); 
			cu.moveVerticalScrollBar(driver, 150);
			
			String firstReqId=hpu.getFirstReqIdInReqGrid(customerName);
			hpu.enterReqId(customerName, firstReqId);
			cu.explicitWait(driver, hpu.reqSuggestedList, 30L);
			cu.mouseOverToElement(driver, hpu.getReqSuggestedListWebElement(customerName).get(0));
			hpu.clickFirstReqInDropDownList(customerName);
			hpu.clickSubmitButton();
			//Thread.sleep(10000);
			cu.explicitWait(driver, hpu.reqIdsInResultGrid, 45L);
			hpu.validateReqInResultGrid(customerName, firstReqId);
		}
		
		
	}
}
