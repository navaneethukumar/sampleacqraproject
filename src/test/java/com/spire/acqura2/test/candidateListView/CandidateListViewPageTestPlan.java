package com.spire.acqura2.test.candidateListView;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gdata.data.geo.Point;
import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pageUtils.AdvancedSearchPageUtils;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtils;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pageUtils.ManageSaveSearchPageUtils;
import com.guarented.ecommerce.pageUtils.UniversalSearchUtils;
import com.guarented.ecommerce.pojo.User;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.spire.base.controller.Logging;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;



public class CandidateListViewPageTestPlan extends TestPlan{
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
	@Test(groups ={"testlocationinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testlocationinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
	    clu.locationinCandidateListView(customerName);
		clu.verifyResultAppears();
		clu.validateResultByLocation(customerName);
	}
	@Test(groups ={"testexperienceinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testexperienceinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		String []matchcase=clu.experiencevalue;
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.clickExperienceinCandidateListView(customerName);
		clu.verifyResultAppears();
		clu.validateResultByExperiencevalue(customerName);
		

	}
	@Test(groups ={"testsourcenameinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testsourcenameinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.clickSourceNameinCandidateListView(customerName);
		clu.verifyResultAppears();
		clu.validateResultBysourcename(customerName);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Number Of Results found: " + count);
	}
	@Test(groups ={"testsourcetypeinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testsourcetypeinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.clickSourceTypeinCandidateListView(customerName);
		clu.verifyResultAppears();
		clu.validateResultBysourceType(customerName);
		
		
	}
	@Test(groups ={"testlastmodifieddateinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testlastmodifieddateinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.checklastModifiedinCandidateListView(customerName);
        clu.verifyResultAppears();
        //clu.validateLastModifiedDate();
	}
	@Test(groups ={"testCurrentRoleinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testCurrentRoleinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		clu.verifyResultAppears();
		clu.checkCurrentRoleinCandidateListView(customerName);
        clu.verifyResultAppears();
        clu.validateCurrentRoleinCandidateResultPage(customerName);
        clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Job Title validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
        
      
	}
	@Test(groups ={"testskillinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testskillinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		clu.checkskillinCandidateListView(customerName);
		clu.verifyResultAppears();
	}
	@Test(groups ={"testdownloadresumeinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testdownloadresumeinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		clu.clickDownloadResumeinCandidateListView(customerName);
	}
	
	@Test(groups ={"testdownloadbulkresumeinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testdownloadbulkresumeinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		clu.clickDownloadBulkResumeinCandidateListView(customerName);
	}
	

	@Test(groups ={"testdownloadListinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testdownloadListinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();	
		clu.clickDownloadListinCandidateListView(customerName);
		
	}
	

	@Test(groups ={"testtagresumeinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testtagresumeinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		String searchParameterType1=testData.getvalue4();  
		clu.clickTagResumeinCandidateListView();
		
		
		
	}
	@Test(groups ={"testcompanyinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testcompanyinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.clickTestCompanyinCandidateListView(customerName);
		clu.verifyResultAppears();
		
		
	}
	@Test(groups ={"testinstituteinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testinstituteinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.clickTestInstituteinCandidateListView(customerName);
		clu.verifyResultAppears();
		
		
	}
	
	@Test(groups ={"testEducationLevelinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testEducationLevelinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.clickEducationLevelinCandidateListView(customerName);
		clu.verifyResultAppears();
		
	
	}
	
	@Test(groups ={"testcandidateStatusinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testcandidateStatusinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.clickCandidateStatusinCandidateListView();
		clu.verifyResultAppears();
		
	
	}
	@Test(groups ={"testuntagresumeinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testuntagresumeinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		clu.clickUntagResumeinCandidateListView(customerName);
		
		
		
	}
	
	@Test(groups ={"testverifyexperiencevalueinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testverifyexperiencevalueinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
		//clu.moveVerticalScrollBar(driver,800);
		clu.ExperiencevalueinCandidateListView(customerName);
		
	}
	
	@Test(groups ={"testSpireStatusinCandidateListView","Sanity"},dataProvider="UITestData")
	public void testSpireStatusinCandidateListView(SpireTestObject testObject,TestDataSet testData) throws Exception{
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
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();		
		//clu.validateResultBySkillCloud(matchCloudArr);
	    clu.SpireStatusinCandidateListView(customerName);
		clu.verifyResultAppears();
		
	}
	//author @Naresh
	
	@Test(groups = { "testJobtitleinCandidateListView", "Sanity" }, dataProvider = "UITestData")
	public void testJobtitleinCandidateListView(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CommonUtils cu = new CommonUtils();
		//CandidateListUtils cuv = new CandidateListUtils(driver);
		CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();
		String commaSeparatedCloudString = testData.getvalue3();
		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);
		System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
		usu.universalSearch(customerName, searchParameter, searchParameterType);

		//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);
		cuv.verifyResultAppearsinCandateResultPage();

		cuv.checkJobtitleinCandidateListView();
		cuv.validateIDCtitleinCandidateResultPage();
		
		cuv.verifyNavigationToCandidateListPage();

	}

	@Test(groups = { "testClientStatusinCandidateListView", "Sanity" }, dataProvider = "UITestData")
	public void testClientStatusinCandidateListView(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
		CommonUtils cu = new CommonUtils();
		String customerName = clientName;
		String searchParameter = testData.getvalue1();

		String searchParameterType = testData.getvalue2();

		String commaSeparatedCloudString = testData.getvalue3();

		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);
		System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);
		cuv.verifyResultAppearsinCandateResultPage();
		//cuv.verifyResultAppears();
		//cuv.validateResultBySkillCloud(matchCloudArr);
		cuv.checkClientStatusinCandidateListView();

		cuv.verifySelectClientStatusinCandidateListPage();
	

		//cuv.verifyResultAppearsinResult();
		cuv.verifyNavigationToCandidateListPage();

	}

	@Test(groups = { "testJobtitleinCandidateListViewinAccentureNA", "Sanity" }, dataProvider = "UITestData")
	public void testJobtitleinCandidateListViewinAccentureNA(SpireTestObject testObject, TestDataSet testData)
			throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		
		CommonUtils cu = new CommonUtils();
		CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
	
		
		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();
		String commaSeparatedCloudString = testData.getvalue3();
		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);
		System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
		usu.universalSearch(customerName, searchParameter, searchParameterType);

		//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);

		cuv.verifyResultAppearsinCandateResultPage();

		cuv.checkJobtitleinCandidateListView();
		cuv.validateaccentureNAtitleinCandidateResultPage();
        cuv.verifyNavigationToCandidateListPage();

	}
	@Test(groups = { "testJobtitleinCandidateListViewinindops", "Sanity1" }, dataProvider = "UITestData")
	public void testJobtitleinCandidateListViewinindops(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		CommonUtils cu = new CommonUtils();
		//CandidateListUtils cuv = new CandidateListUtils(driver);
		CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();
		String commaSeparatedCloudString = testData.getvalue3();
		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);
		System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
		usu.universalSearch(customerName, searchParameter, searchParameterType);

		//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);

		//clu.validateResultBySkillCloud(matchCloudArr);
		//cuv.verifyResultAppears();
		cuv.verifyResultAppearsinCandateResultPage();

		
		cuv.checkJobtitleinCandidateListViewinIndops();
		//cuv.verifyResultAppearsinCandidateResultPage();
		cuv.validateSelecttitleinCandidateResultPageinIndops();
		
		cuv.verifyNavigationToCandidateListPage();

	}
	@Test(groups = { "testClientStatusinCandidateListViewinindops", "Sanity" }, dataProvider = "UITestData")
		public void testClientStatusinCandidateListViewinindops(SpireTestObject testObject, TestDataSet testData) throws Exception {
			LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			UniversalSearchUtils usu = new UniversalSearchUtils(driver);
			//CandidateListViewUtils clu = new CandidateListViewUtils(driver);
			CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
			CommonUtils cu = new CommonUtils();
			//CandidateListUtils cuv = new CandidateListUtils(driver);
			String customerName = clientName;
			String searchParameter = testData.getvalue1();

			String searchParameterType = testData.getvalue2();

			String commaSeparatedCloudString = testData.getvalue3();

			System.out.println("searchParameterType :" + searchParameterType);
			System.out.println("searchParameter :" + searchParameter);
			System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
			usu.universalSearch(customerName, searchParameter, searchParameterType);
			//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);
			cuv.verifyResultAppearsinCandateResultPage();
			//cuv.verifyResultAppears();
			//cuv.validateResultBySkillCloud(matchCloudArr);
			cuv.checkClientStatusinCandidateListView();

			cuv.verifySelectClientStatusinCandidateListPage();
		

			//cuv.verifyResultAppearsinResult();
			cuv.verifyNavigationToCandidateListPage();

		}
	@Test(groups = { "testClientStatusinCandidateListViewinPhilTech", "Sanity" }, dataProvider = "UITestData")
	public void testClientStatusinCandidateListViewinPhilTech(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
		CommonUtils cu = new CommonUtils();
		String customerName = clientName;
		String searchParameter = testData.getvalue1();

		String searchParameterType = testData.getvalue2();

		String commaSeparatedCloudString = testData.getvalue3();

		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);
		System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);
		cuv.verifyResultAppearsinCandateResultPage();
		//cuv.verifyResultAppears();
		//cuv.validateResultBySkillCloud(matchCloudArr);
		cuv.checkClientStatusinCandidateListView();

		cuv.verifySelectClientStatusinCandidateListPage();
	

		//cuv.verifyResultAppearsinResult();
		cuv.verifyNavigationToCandidateListPage();
		

	}
@Test(groups = { "testJobtitleinCandidateListViewinPhilTech", "Sanity" }, dataProvider = "UITestData")
	public void testJobtitleinCandidateListViewinPhilTech(SpireTestObject testObject, TestDataSet testData)
			throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		
		CommonUtils cu = new CommonUtils();
		CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
	
		
		String customerName = clientName;
		String searchParameter = testData.getvalue1();
		String searchParameterType = testData.getvalue2();
		String commaSeparatedCloudString = testData.getvalue3();
		System.out.println("searchParameterType :" + searchParameterType);
		System.out.println("searchParameter :" + searchParameter);
		System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
		usu.universalSearch(customerName, searchParameter, searchParameterType);

		//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);

		cuv.verifyResultAppearsinCandateResultPage();
		cuv.checkJobtitleinCandidateListViewinPhilTech(customerName);
	          cuv.validateJobTitleinCandidateResultPage();
                 cuv.verifyNavigationToCandidateListPage();

	}
@Test(groups = { "testJobtitleinCandidateListViewinPhilBPO", "Sanity" }, dataProvider = "UITestData")
public void testJobtitleinCandidateListViewinPhilBPO(SpireTestObject testObject, TestDataSet testData)
		throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);	
	CommonUtils cu = new CommonUtils();
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);

	
	String customerName = clientName;
	String searchParameter = testData.getvalue1();
	String searchParameterType = testData.getvalue2();
	String commaSeparatedCloudString = testData.getvalue3();
	System.out.println("searchParameterType :" + searchParameterType);
	System.out.println("searchParameter :" + searchParameter);
	System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);
	usu.universalSearch(customerName, searchParameter, searchParameterType);

	//String[] matchCloudArr = cu.getMatchCloudArray(commaSeparatedCloudString);

	cuv.verifyResultAppearsinCandateResultPage();
	cuv.checkJobtitleinCandidateListViewinPhilTech(customerName);
          cuv.validateJobTitleinCandidateResultPage();
             cuv.verifyNavigationToCandidateListPage();

}

//author @supraja

@Test(groups ={"testskillinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testskillinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.checkskillinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testLocationinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testLocationinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.locationinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateResultByLocation(customerName);
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
	
}
@Test(groups ={"testExperienceinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testExperienceinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.ExperiencevalueinCandidateListView(customerName);
	clu.clickExperienceinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateResultByExperiencevalue(customerName);
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
	
}
@Test(groups ={"testCandidateStatusinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testCandidateStatusinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickCandidateStatusinCandidateListView();
	clu.verifyResultAppears();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
	
}
@Test(groups ={"testinstituteinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testinstituteinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickTestInstituteinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
	
}
@Test(groups ={"testEducationLevelinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testEducationLevelinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickEducationLevelinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
	
}
@Test(groups ={"testlastmodifieddateinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testlastmodifieddateinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.checklastModifiedinCandidateListView(customerName);
    clu.verifyResultAppears();
    //clu.validateLastModifiedDate();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testsourcenameinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testsourcenameinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickSourceNameinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testcompanyinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testcompanyinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickTestCompanyinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testClientStatusinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testClientStatusinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.checkClientStatusinCandidateListView();
	clu.verifySelectClientStatusinCandidateListPage();
	//cuv.verifyResultAppearsinResult();
	clu.verifyNavigationToCandidateListPage();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testCurrentRoleinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testCurrentRoleinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.checkCurrentRoleinCandidateListView(customerName);
    clu.verifyResultAppears();
    clu.validateCurrentRoleinCandidateResultPage(customerName);
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testsourcetypeinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testsourcetypeinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickSourceTypeinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateResultBysourceType(customerName);
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testJobtitleinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testJobtitleinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.checkJobtitleinCandidateListViewinPhilTech(customerName);
	clu.validateJobTitleinCandidateResultPage();
	clu.verifyNavigationToCandidateListPage();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
@Test(groups ={"testdownloadresumeinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testdownloadresumeinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickDownloadResumeinCandidateListView(customerName);
	
	/*clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);*/
}
@Test(groups ={"testdownloadbulkresumeinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testdownloadbulkresumeinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickDownloadBulkResumeinCandidateListView(customerName);
	
	/*clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);*/
}
@Test(groups ={"testdownloadListinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testdownloadListinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();
	clu.clickDownloadListinCandidateListView(customerName);
	
	/*clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);*/
}

@Test(groups ={"testuntagresumeinCandidateListViewfromAdvanceSearch","Sanity"},dataProvider="UITestData")
public void testuntagresumeinCandidateListViewfromAdvanceSearch(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
	HomePageUtils hpu = new HomePageUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
	hpu.goToAdvanceSearchPage(clientName);
	advanceUtil.skillSearch(testData.getvalue1());
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.verifyResultAppears();	
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.clickUntagResumeinCandidateListView(customerName);
}

//@Sridhar

@Test(groups ={"testlocationinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testlocationinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	if(clientName.equals("IDC")){
		System.out.println("wait in case of idc only");
		Thread.sleep(5000);			
	}
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
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
    clu.locationinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateResultByLocation(customerName);
}
@Test(groups ={"testexperienceinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testexperienceinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.clickExperienceinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateResultByExperiencevalue(customerName);
	

}
@Test(groups ={"testsourcenameinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testsourcenameinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.clickSourceNameinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateResultBysourcename(customerName);
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
	
	
}
@Test(groups ={"testsourcetypeinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testsourcetypeinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.clickSourceTypeinCandidateListView(customerName);
	clu.verifyResultAppears();
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
	
}
@Test(groups ={"testlastmodifieddateinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testlastmodifieddateinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.checklastModifiedinCandidateListView(customerName);
    clu.verifyResultAppears();
    clu.validateLastModifiedDate();
	
	
}
@Test(groups ={"testskillinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testskillinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);*/
	
	//usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	//clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
//	clu.validateResultBySkillCloud(matchCloudArr);
	clu.checkskillinCandidateListView(customerName);
	clu.verifyResultAppears();
}
@Test(groups ={"testdownloadresumeinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testdownloadresumeinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.clickDownloadResumeinCandidateListView(customerName);
}

@Test(groups ={"testdownloadbulkresumeinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testdownloadbulkresumeinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.clickDownloadBulkResumeinCandidateListView(customerName);
}


@Test(groups ={"testdownloadListinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testdownloadListinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();	
	clu.clickDownloadListinCandidateListView(customerName);
	
}


@Test(groups ={"testtagresumeinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testtagresumeinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	String searchParameterType1=testData.getvalue4();  
	clu.clickTagResumeinCandidateListView();
	
	
	
}
@Test(groups ={"testcompanyinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testcompanyinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.clickTestCompanyinCandidateListView(customerName);
	clu.verifyResultAppears();
	
	
}
@Test(groups ={"testinstituteinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testinstituteinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.clickTestInstituteinCandidateListView(customerName);
	clu.verifyResultAppears();
	
	
}

@Test(groups ={"testEducationLevelinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testEducationLevelinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.clickEducationLevelinCandidateListView(customerName);
	clu.verifyResultAppears();
	

}

@Test(groups ={"testcandidateStatusinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testcandidateStatusinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.clickCandidateStatusinCandidateListView();
	clu.verifyResultAppears();
	

}
@Test(groups ={"testuntagresumeinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testuntagresumeinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	clu.clickUntagResumeinCandidateListView(customerName);
	
	
	
}

@Test(groups ={"testverifyexperiencevalueinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testverifyexperiencevalueinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
	//clu.moveVerticalScrollBar(driver,800);
	clu.ExperiencevalueinCandidateListView(customerName);
	
}

@Test(groups ={"testSpireStatusinCandidateListViewbyMSS","Sanity"},dataProvider="UITestData")
public void testSpireStatusinCandidateListViewbyMSS(SpireTestObject testObject,TestDataSet testData) throws Exception{
	LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu=new UniversalSearchUtils(driver);
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
	CommonUtils cu=new CommonUtils();
	//String customerName=cu.getCustomerName();
	String customerName=clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	
	clu.verifyResultAppears();		
	//clu.validateResultBySkillCloud(matchCloudArr);
    clu.SpireStatusinCandidateListView(customerName);
	clu.verifyResultAppears();
	
}
//author @Naresh

@Test(groups = { "testJobtitleinCandidateListViewbyMSS", "Sanity" }, dataProvider = "UITestData")
public void testJobtitleinCandidateListViewbyMSS(SpireTestObject testObject, TestDataSet testData) throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	CommonUtils cu = new CommonUtils();
	//CandidateListUtils cuv = new CandidateListUtils(driver);
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
	String customerName = clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	cuv.verifyResultAppearsinCandateResultPage();

	cuv.checkJobtitleinCandidateListView();
	cuv.validateIDCtitleinCandidateResultPage();
	
	cuv.verifyNavigationToCandidateListPage();

}

@Test(groups = { "testClientStatusinCandidateListViewbyMSS", "Sanity" }, dataProvider = "UITestData")
public void testClientStatusinCandidateListViewbyMSS(SpireTestObject testObject, TestDataSet testData) throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
	CommonUtils cu = new CommonUtils();
	String customerName = clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	cuv.verifyResultAppearsinCandateResultPage();
	//cuv.verifyResultAppears();
	//cuv.validateResultBySkillCloud(matchCloudArr);
	cuv.checkClientStatusinCandidateListView();

	cuv.verifySelectClientStatusinCandidateListPage();


	//cuv.verifyResultAppearsinResult();
	cuv.verifyNavigationToCandidateListPage();

}

@Test(groups = { "testJobtitleinCandidateListViewinAccentureNAbyMSS", "Sanity" }, dataProvider = "UITestData")
public void testJobtitleinCandidateListViewinAccentureNAbyMSS(SpireTestObject testObject, TestDataSet testData)
		throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	
	CommonUtils cu = new CommonUtils();
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);

	
	String customerName = clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();

	cuv.verifyResultAppearsinCandateResultPage();

	cuv.checkJobtitleinCandidateListView();
	cuv.validateaccentureNAtitleinCandidateResultPage();
    cuv.verifyNavigationToCandidateListPage();

}
@Test(groups = { "testJobtitleinCandidateListViewinindopsbyMSS", "Sanity1" }, dataProvider = "UITestData")
public void testJobtitleinCandidateListViewinindopsbyMSS(SpireTestObject testObject, TestDataSet testData) throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	CandidateListViewUtils clu = new CandidateListViewUtils(driver);
	CommonUtils cu = new CommonUtils();
	//CandidateListUtils cuv = new CandidateListUtils(driver);
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
	String customerName = clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();

	//clu.validateResultBySkillCloud(matchCloudArr);
	//cuv.verifyResultAppears();
	cuv.verifyResultAppearsinCandateResultPage();

	
	cuv.checkJobtitleinCandidateListViewinIndops();
	//cuv.verifyResultAppearsinCandidateResultPage();
	cuv.validateSelecttitleinCandidateResultPageinIndops();
	
	cuv.verifyNavigationToCandidateListPage();

}
@Test(groups = { "testClientStatusinCandidateListViewinindopsbyMSS", "Sanity" }, dataProvider = "UITestData")
	public void testClientStatusinCandidateListViewinindopsbyMSS(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		UniversalSearchUtils usu = new UniversalSearchUtils(driver);
		//CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
		CommonUtils cu = new CommonUtils();
		//CandidateListUtils cuv = new CandidateListUtils(driver);
		String customerName = clientName;
		/*String searchParameter=testData.getvalue1();
		String searchParameterType=testData.getvalue2();
		String commaSeparatedCloudString=testData.getvalue3();
		System.out.println("searchParameterType :"+searchParameterType);
		System.out.println("searchParameter :"+searchParameter);
		System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
		
		usu.universalSearch(customerName, searchParameter, searchParameterType);
		
		//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
		
		clu.verifyResultAppears();*/		
		//clu.validateResultBySkillCloud(matchCloudArr);
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.clickonManageSaveSearch();
		cuv.verifyResultAppearsinCandateResultPage();
		//cuv.verifyResultAppears();
		//cuv.validateResultBySkillCloud(matchCloudArr);
		cuv.checkClientStatusinCandidateListView();

		cuv.verifySelectClientStatusinCandidateListPage();
	

		//cuv.verifyResultAppearsinResult();
		cuv.verifyNavigationToCandidateListPage();

	}
@Test(groups = { "testClientStatusinCandidateListViewinPhilTechbyMSS", "Sanity" }, dataProvider = "UITestData")
public void testClientStatusinCandidateListViewinPhilTechbyMSS(SpireTestObject testObject, TestDataSet testData) throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);
	CommonUtils cu = new CommonUtils();
	String customerName = clientName;
	/*String searchParameter=testData.getvalue1();
	String searchParameterType=testData.getvalue2();
	String commaSeparatedCloudString=testData.getvalue3();
	System.out.println("searchParameterType :"+searchParameterType);
	System.out.println("searchParameter :"+searchParameter);
	System.out.println("commaSeparatedCloudString :"+commaSeparatedCloudString);
	
	usu.universalSearch(customerName, searchParameter, searchParameterType);
	
	//String []matchCloudArr=cu.getMatchCloudArray(commaSeparatedCloudString);
	
	clu.verifyResultAppears();*/		
	//clu.validateResultBySkillCloud(matchCloudArr);
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	cuv.verifyResultAppearsinCandateResultPage();
	//cuv.verifyResultAppears();
	//cuv.validateResultBySkillCloud(matchCloudArr);
	cuv.checkClientStatusinCandidateListView();

	cuv.verifySelectClientStatusinCandidateListPage();


	//cuv.verifyResultAppearsinResult();
	cuv.verifyNavigationToCandidateListPage();
	

}
@Test(groups = { "testJobtitleinCandidateListViewinPhilTechbyMSS", "Sanity" }, dataProvider = "UITestData")
public void testJobtitleinCandidateListViewinPhilTechbyMSS(SpireTestObject testObject, TestDataSet testData)
		throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	
	CommonUtils cu = new CommonUtils();
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);

	
	String customerName = clientName;	
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();

	cuv.verifyResultAppearsinCandateResultPage();
	cuv.checkJobtitleinCandidateListViewinPhilTech(customerName);
          cuv.validateJobTitleinCandidateResultPage();
             cuv.verifyNavigationToCandidateListPage();

}
@Test(groups = { "testJobtitleinCandidateListViewinPhilBPObyMSS", "Sanity" }, dataProvider = "UITestData")
public void testJobtitleinCandidateListViewinPhilBPObyMSS(SpireTestObject testObject, TestDataSet testData)
		throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	
	CommonUtils cu = new CommonUtils();
	CandidateListViewUtils cuv=new CandidateListViewUtils(driver);

	
	String customerName = clientName;	
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();

	cuv.verifyResultAppearsinCandateResultPage();
	cuv.checkJobtitleinCandidateListViewinPhilTech(customerName);
          cuv.validateJobTitleinCandidateResultPage();
             cuv.verifyNavigationToCandidateListPage();

}
@Test(groups = { "testCurrentRoleinCandidateListViewbyMSS", "Sanity" }, dataProvider = "UITestData")
public void testCurrentRoleinCandidateListViewbyMSS(SpireTestObject testObject, TestDataSet testData)
		throws Exception {
	LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
	initializeDriver(loginPageUtils.driver, testObject);
	loginPageUtils.signin();
	UniversalSearchUtils usu = new UniversalSearchUtils(driver);
	CommonUtils cu = new CommonUtils();
	CandidateListViewUtils clu=new CandidateListViewUtils(driver);
    String customerName = clientName;
	ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
	mss.clickonManageSaveSearch();
	clu.verifyResultAppears();
	clu.checkCurrentRoleinCandidateListView(customerName);
    clu.verifyResultAppears();
    clu.validateCurrentRoleinCandidateResultPage(customerName);
	clu.validateBreadCrumbs(testData);
	String count = clu.getNumberOfRecords();
	Logging.log("Number Of Results found: " + count);
}
//Added For Free TextSearch:@Ganesh
@Test(groups = { "TestResumeSearchField", "Sanity" }, dataProvider = "UITestData")
public void TestResumeSearchField(SpireTestObject testObject, TestDataSet testData) throws Exception {
LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
initializeDriver(loginPageUtils.driver, testObject);
loginPageUtils.signin();
UniversalSearchUtils usu = new UniversalSearchUtils(driver);
CandidateListViewUtils clu = new CandidateListViewUtils(driver);
CommonUtils cu = new CommonUtils();
//String customerName=cu.getCustomerName();
String customerName = clientName;
String searchParameter = testData.getvalue1();
String searchParameterType = testData.getvalue2();
String commaSeparatedCloudString = testData.getvalue3();
System.out.println("searchParameterType :" + searchParameterType);
System.out.println("searchParameter :" + searchParameter);
System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);

usu.universalSearch(customerName, searchParameter, searchParameterType);
clu.verifyResumeSearch();

/*
* String[] matchCloudArr =
* cu.getMatchCloudArray(commaSeparatedCloudString);
* 
* clu.verifyResultAppears();
* clu.validateResultBySkillCloud(matchCloudArr);
* 
*/

}

//Added For Free TextSearch:@Ganesh
@Test(groups = { "TestResumeSearchFieldButton", "Sanity" }, dataProvider = "UITestData")
public void TestResumeSearchFieldButton(SpireTestObject testObject, TestDataSet testData) throws Exception {
LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
initializeDriver(loginPageUtils.driver, testObject);
loginPageUtils.signin();
UniversalSearchUtils usu = new UniversalSearchUtils(driver);
CandidateListViewUtils clu = new CandidateListViewUtils(driver);
CommonUtils cu = new CommonUtils();
//String customerName=cu.getCustomerName();
String customerName = clientName;
String searchParameter = testData.getvalue1();
String searchParameterType = testData.getvalue2();
String commaSeparatedCloudString = testData.getvalue3();
System.out.println("searchParameterType :" + searchParameterType);
System.out.println("searchParameter :" + searchParameter);
System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);

usu.universalSearch(customerName, searchParameter, searchParameterType);
clu.verifyResumeSearchButton();

/*
* * String[] matchCloudArr =
* cu.getMatchCloudArray(commaSeparatedCloudString);
* 
* clu.verifyResultAppears();
* clu.validateResultBySkillCloud(matchCloudArr);
*/
}

//Added For Free TextSearch:@Ganesh
@Test(groups={"TestResumeSearch","Sanity"}, dataProvider="UITestData")
public void TestResumeSearch(SpireTestObject testObject, TestDataSet testData) throws Exception{
LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
initializeDriver(loginPageUtils.driver, testObject);
loginPageUtils.signin();
UniversalSearchUtils usu = new UniversalSearchUtils(driver);
CandidateListViewUtils clu = new CandidateListViewUtils(driver);
CommonUtils cu = new CommonUtils(); 
String customerName = clientName;
String searchParameter = testData.getvalue1();
String searchParameterType = testData.getvalue2();
String commaSeparatedCloudString = testData.getvalue3();
System.out.println("searchParameterType :" + searchParameterType);
System.out.println("searchParameter :" + searchParameter);
System.out.println("commaSeparatedCloudString :" + commaSeparatedCloudString);

usu.universalSearch(customerName, searchParameter, searchParameterType);
clu.verifyResumeSearchfunc(customerName,testData);
}


}

