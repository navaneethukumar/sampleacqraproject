package com.spire.acqura2.test.universalSearch;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pageUtils.AdvancedSearchPageUtils;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtils;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pageUtils.UniversalSearchUtils;
import com.guarented.ecommerce.pojo.User;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "universalSearchTest"},retryAnalyzer=TestRetryAnalyzer.class)

public class UniversalSearchTestPlan extends TestPlan{
	
	public static String clientName = null;	
	public static String filePathForCSV = null;

	@Parameters({ "client" })
	@BeforeClass(alwaysRun = true)
	public static void init(String client) {

		clientName = client;

		System.out.println("Client app for which tests being run is" + " " + clientName);

		filePathForCSV = "./src/test/java/com/spire/acqura2/test/data/" + clientName + "TestData.csv";

		System.out.println("CSV file with test Data is" + " " + filePathForCSV);
	}
	
	@DataProvider(name = "UITestData")
	public static Iterator<Object[]> getCandidateInfo(Method method) {

		Iterator<Object[]> objectsFromCsv = null;
		
		try {
			
			LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
			LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
			methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
			entityClazzMap.put("SpireTestObject", SpireTestObject.class);			
			entityClazzMap.put("TestDataSet", TestDataSet.class);
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(TestDataSet.class, entityClazzMap, filePathForCSV, null,
					methodFilter);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}
	
	
	
	@Test(groups = { "testValidateAutoSuggestionInUniversalSearch", "Sanity" }, dataProvider = "UITestData" )
	public void testValidateAutoSuggestionInUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception {
		
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		usu.validateAutoSuggestionInUniversalSearch(customerName,searchParameter);
	}
	
	
	
	
	@Test(groups ={"testSearchBySkillUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchBySkillUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		String commaSeparatedCloudString=testData.getvalue3();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
		
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		clu.validateResultBySkillCloud(matchCloudArr);
		
	}
	@Test(groups ={"testSearchBySkillUniversalSearchwithBrackets","Sanity"},dataProvider="UITestData")
	public void testSearchBySkillUniversalSearchwithBrackets(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		String commaSeparatedCloudString=testData.getvalue3();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
		
		usu.universalSearchwithBrackets(customerName, searchParameter, searchParameterType);
		String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		clu.validateResultBySkillCloud(matchCloudArr);
		
	}
	
	@Test(groups ={"testSearchByLocationUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchByLocationUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();	
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		
		usu.universalSearch(customerName, searchParameter, searchParameterType);	
		clu.verifyResultAppears();
		
	}
	
	@Test(groups ={"testSearchByEmployerUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchByEmployerUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();	
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		clu.verifyResultAppears();
		
	}
	
	
	@Test(groups ={"testSearchByDesignationUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchByDesignationUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();
		String customerName=clientName;		
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		clu.verifyResultAppears();
		
	}
	
	
	@Test(groups ={"testSearchByFullNameUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchByFullNameUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();	
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		
		clu.verifyResultAppears();
		
	}
	
	
	@Test(groups ={"testSearchBySourceNameUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchBySourceNameUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();	
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		clu.verifyResultAppears();
		
	}
	
	@Test(groups ={"testSearchBySourceTypeUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchBySourceTypeUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();	
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		clu.verifyResultAppears();
		
	}
	
	@Test(groups ={"testSearchByInstituteUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchByInstituteUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		clu.verifyResultAppears();
		
	}
	
	@Test(groups ={"testCheckAutoSuggestionListAppearsWithParamTypeUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testCheckAutoSuggestionListAppearsWithParamTypeUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();	
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.typeInUniversalSearch(customerName, searchParameter);
		
		usu.validateAutoSuggestListAppearsWithParamTypeUniversalSearch();
		
	}	
	
	@Test(groups ={"testSearchBySpireStatusUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchBySpireStatusUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		clu.verifyResultAppears();
		
	}
	
	@Test(groups ={"testSearchByClientStatusUniversalSearch","Sanity"},dataProvider="UITestData")
	public void testSearchByClientStatusUniversalSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu=new UniversalSearchUtils(driver);
		CandidateListViewUtils clu=new CandidateListViewUtils(driver);
		CommonUtils cu=new CommonUtils();
		//String customerName=cu.getCustomerName();	
		String customerName=clientName;
		String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		clu.verifyResultAppears();
		
	}
	
}
