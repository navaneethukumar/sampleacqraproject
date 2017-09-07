package com.spire.acqura2.test.CandidateUserFeedback;

import org.testng.annotations.Test;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pageUtils.AdvancedSearchPageUtils;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtils;
import com.guarented.ecommerce.pageUtils.CandidateUserFeedbackUtils;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pageUtils.UniversalSearchUtils;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "homeTest"},retryAnalyzer=TestRetryAnalyzer.class)

public class CandidateUserFeedbackTestplan extends TestPlan{
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
	@Test(groups = { "createPositiveFeeBbackinThumsupThroughUniversalSearch", "Sanity" }, dataProvider = "UITestData")
	public void createPositiveFeeBbackinThumsupThroughUniversalSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);

		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();

		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);

	
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		cv.positiveFeedBackData(customerName);

	}

@Test(groups = { "createNegativeFeeBbackinThumsDownThroughUniversalSearch", "Sanity" }, dataProvider = "UITestData")
	public void createNegativeFeeBbackinThumsDownThroughUniversalSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);

		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();

		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);

	
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		cv.NegativeFeedBackData(customerName);

	}
@Test(groups = { "createThumsDownNegativeFeedBackThroughcount", "Sanity" }, dataProvider = "UITestData")
	public void createThumsDownNegativeFeedBackThroughcount(SpireTestObject testObject, TestDataSet testData)
			throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		HomePageUtils hpu = new HomePageUtils(driver);
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);
		String customerName = clientName;
		hpu.clickonStatusbutton(customerName);
		cv.windowhandleforPhilTech(customerName);
		cv.NegativeFeedBackDataThroughCount(customerName);

		//cv.windowhandle();
		//cv.createPositiveFeedBackData();
	}
@Test(groups = { "createThumsupPositiveFeedBackThroughcount", "Sanity" }, dataProvider = "UITestData")
	public void createThumsupPositiveFeedBackThroughcount(SpireTestObject testObject, TestDataSet testData)
			throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		HomePageUtils hpu = new HomePageUtils(driver);
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);
        //hu.clickorgunitylevel();
		//cv.windowhandle();
		String customerName = clientName;
		hpu.clickonStatusbutton(customerName);
		cv.windowhandleforPhilTech(customerName);
		cv.positiveFeedBackDataThroughCount(customerName);
		
		}

@Test(groups = { "createAndVerifyNegativeFeeBbackinThumsDownThroughCount", "Sanity" }, dataProvider = "UITestData")
       public void createAndVerifyNegativeFeeBbackinThumsDownThroughCount(SpireTestObject testObject, TestDataSet testData) throws Exception {
       LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
       initializeDriver(loginPageUtils.driver, testObject);
       loginPageUtils.signin();
       UniversalSearchUtils usu = new UniversalSearchUtils(driver);
       CommonUtils cu = new CommonUtils();
       CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);
       HomePageUtils hpu = new HomePageUtils(driver);
       String customerName = clientName;
       //String searchParameter=testData.getvalue1();
       hpu.clickonStatusbutton(customerName);
	   cv.windowhandleforPhilTech(customerName);	
	   cv.createAndVerifyNegativeFeedBackDatafromcount(customerName);

}

	@Test(groups = { "createAndVerifyNegativeFeeBbackinThumsDownThroughUniversalSearch", "Sanity" }, dataProvider = "UITestData")
	public void createAndVerifyNegativeFeeBbackinThumsDownThroughUniversalSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);

		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();

		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);

	
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		cv.createAndVerifyNegativeFeedBackData(customerName);

	}
@Test(groups = { "createandVerifyPositiveFeeBbackinThumsUpThroughCount", "Sanity" }, dataProvider = "UITestData")
	public void createandVerifyPositiveFeeBbackinThumsUpThroughCount(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		HomePageUtils hpu = new HomePageUtils(driver);
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);
        String customerName = clientName;
        hpu.clickonStatusbutton(customerName);
		cv.windowhandleforPhilTech(customerName);
		cv.createAndVerifyPositiveFeedBackDatathroughcount(customerName);
}
@Test(groups = { "createAndVerifyPositiveFeeBbackinThumsUpThroughUniversalSearch", "Sanity" }, dataProvider = "UITestData")
	public void createAndVerifyPositiveFeeBbackinThumsUpThroughUniversalSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);

		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();

		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);

		
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		
		cv.createAndVerifyPositiveFeedBackData(customerName);

	
	}
@Test(groups = { "updatePositiveFeeBbackinThumsUpThroughUniversalSearch", "Sanity" }, dataProvider = "UITestData")
	public void updatePositiveFeeBbackinThumsUpThroughUniversalSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);

		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();

		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);

		System.out.println("universal page");
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		
		cv.updatePositiveFeedBackData(customerName);

		
	}
@Test(groups = { "updatePositiveFeeBbackinThumsUpThroughCount", "Sanity" }, dataProvider = "UITestData")
	public void updatePositiveFeeBbackinThumsUpThroughCount(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		HomePageUtils hpu = new HomePageUtils(driver);
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);
		String customerName = clientName;
		hpu.clickonStatusbutton(customerName);		
		cv.windowhandleforPhilTech(customerName);
		cv.updatePositiveFeedBackDatathroughcount(customerName);
		}
@Test(groups = { "updateNegativeFeeBbackinThumsDownThroughCount", "Sanity" }, dataProvider = "UITestData")
	public void updateNegativeFeeBbackinThumsDownThroughCount(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		HomePageUtils hpu = new HomePageUtils(driver);
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);
		String customerName = clientName;
		hpu.clickonStatusbutton(customerName);
		cv.windowhandleforPhilTech(customerName);
		cv.updateNegativeFeedBackDatathroughcount(customerName);
		}
@Test(groups = { "updateNegativeFeeBbackinthumsDownThroughUniversalSearch", "Sanity" }, dataProvider = "UITestData")
	public void updateNegativeFeeBbackinthumsDownThroughUniversalSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		CandidateUserFeedbackUtils cv = new CandidateUserFeedbackUtils(driver);
		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();
		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		cv.updateNegativeFeedBackData(customerName);
		}
}
	