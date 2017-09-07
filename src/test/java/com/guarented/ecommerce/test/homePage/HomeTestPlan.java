package com.guarented.ecommerce.test.homePage;

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
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "homeTest"},retryAnalyzer=TestRetryAnalyzer.class)

public class HomeTestPlan extends TestPlan{
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
	
	
	
	@Test(groups = { "validateDefaultSRStatusInResultGrid", "sanity" }, dataProvider = "UITestData")
	public void validateDefaultSRStatusInResultGrid(SpireTestObject testObject, TestDataSet testData) throws Exception {
		
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		HomePageUtils hpu=new HomePageUtils(driver);
		hpu.validateSRStatusListInResultGrid();
		
	}
	
		
	@Test(groups ={"checkAdvanceSearchPageLoaded"},dataProvider="UITestData")
	public void checkAdvanceSearchPageLoaded(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		HomePageUtils hpu=new HomePageUtils(driver);
		AdvancedSearchPageUtils apu=new  AdvancedSearchPageUtils(driver);
		CommonUtils cu=new CommonUtils();
		String customerName=cu.getCustomerName();	
		hpu.clickAdvanceSearch(customerName);
		apu.validateAdvanceSearchPage();
		
	}
	
	
	@Test(groups ={"checkReqSuugestedListInDropdown","1Sanity1"},dataProvider="UITestData")
	public void checkReqSuugestedListInDropdown(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
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
	
	

	@Test(groups ={"checkSearchedReqListInResultGrid"},dataProvider="UITestData")
	public void checkSearchedReqListInResultGrid(SpireTestObject testObject,TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
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
	//@Author Priti/ modified by Rahul
	
		@Test(groups ={"searchByRecruiterEmailid","Sanity"},dataProvider="UITestData")
		public void searchByRecruiterEmailid(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			CommonUtils cu=new CommonUtils();
			String searchParameter=testData.getvalue1();
			String mongoDBUri=testData.getvalue2();
			String dbName=testData.getvalue3();
			String collectionName=testData.getvalue4();
			System.out.println("searchparameter is "+searchParameter);
			System.out.println("mongoDBUri : "+mongoDBUri);
			System.out.println("dbName : "+dbName);
			System.out.println("collectionName : "+collectionName);
			hpu.searchByRecruiterEmailId(customerName,searchParameter);
			String firstReqInResultGrid=hpu.getReqNoinResultGrid(customerName).get(0).getText().trim();
			System.out.println("firstReqInGrid : "+firstReqInResultGrid);
					
			hpu.validateResultInMongoDB(customerName, mongoDBUri, dbName, collectionName, "displayId", firstReqInResultGrid, "recruiterEmail");
			
		}
		
		//@Author Pritisudha
		
		@Test(groups ={"searchByExperience","Sanity"},dataProvider="UITestData")
		public void searchByExperience(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			CommonUtils cu=new CommonUtils();
			String searchParameter[]=new String[4];
			searchParameter[0]=testData.getvalue1();
			searchParameter[1]=testData.getvalue2();
			searchParameter[2]=testData.getvalue3();
			searchParameter[3]=testData.getvalue4();
			hpu.searchByExperiencernage(customerName, searchParameter);
			
		}
		//@Author Supraja
		@Test(groups ={"testSearchbyJobTitle","Sanity"},dataProvider="UITestData")
		public void testSearchbyJobTitle(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
		   // hpu.typeInJobTitleTextBox(customerName, searchParameter);
			hpu.validateJobTitleInResultGrid(customerName, searchParameter, testData);
					}
		@Test(groups ={"testSearchbyORGUNITLEVEL1inFacet","Sanity"},dataProvider="UITestData")
		public void testSearchbyORGUNITLEVEL1inFacet(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.clickelementinORGUNITLEVEL1facet();
}
		@Test(groups ={"testSearchbyLocationinFacet","Sanity"},dataProvider="UITestData")
		public void testSearchbyLocationinFacet(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.validateLocationInResultGrid(customerName);
		}
		@Test(groups ={"testSearchbyJobLevelinFacet","Sanity"},dataProvider="UITestData")
		public void testSearchbyJobLevelinFacet(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			//hpu.clickelementinLocationfacet();
			hpu.clickelementinJobLevelfacet();
		}
		@Test(groups ={"testSelectRequisitionstatus","Sanity"},dataProvider="UITestData")
		public void testSelectRequisitionstatus(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.validateRequisitionstatusInResultGrid(customerName);
			
		}
		@Test(groups ={"testSelectClosedRequisitionstatus","Sanity"},dataProvider="UITestData")
		public void testSelectClosedRequisitionstatus(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.validateClosedRequisitionstatusInResultGrid(customerName);
			
		}
		@Test(groups = { "testSelectIndianServiceLeaderFromFacet", "Sanity" }, dataProvider = "UITestData")
		public void testSelectIndianServiceLeaderFromFacet(SpireTestObject testObject,TestDataSet testData) throws Exception {
			
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			hpu.clickelementinIndianServiceLeader();
			
		}
		@Test(groups ={"testsearchbyLocationinFacetforFII","Sanity"},dataProvider="UITestData")
		public void testsearchbyLocationinFacetforFII(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			//hpu.clickelementinLocationfacet();
			hpu.validateLocationInResultGridforFII(customerName);
		}
		@Test(groups ={"testSearchbyEmploymentTypeinFacet","Sanity"},dataProvider="UITestData")
		public void testSearchbyEmploymentTypeinFacet(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.clickelementinEmploymentTypefacet();
		}
		@Test(groups ={"testSearchbyBusinessUnitinFacet","Sanity"},dataProvider="UITestData")
		public void testSearchbyBusinessUnitinFacet(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.clickelementinBusinessUnitfacet(customerName);
		}
		@Test(groups ={"testDownloadAutomatchReport","Sanity"},dataProvider="UITestData")
		public void testDownloadAutomatchReport(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.clickAutomatchReporticon();
		}
		
		@Test(groups ={"testDownloadSRList","Sanity"},dataProvider="UITestData")
		public void testDownloadSRList(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			hpu.clickSRListIcon();
		}
		@Test(groups ={"testtoSearchbyCapability","Sanity"},dataProvider="UITestData")
		public void testtosearchbyCapability(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
			//hpu.typeInCapabilityTextBox(customerName, searchParameter);
			hpu.validatePrimarySkillInReqGridforCapability(customerName, searchParameter);
			
		}
		@Test(groups ={"testtoSearchbyDeal","Sanity"},dataProvider="UITestData")
		public void testtoSearchbyDeal(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
			//hpu.typeInCapabilityTextBox(customerName, searchParameter);
			
			hpu.validatePrimarySkillInReqGridforDeal(customerName, searchParameter);
		}

		@Test(groups ={"testSearchByReqNoinHomePage","Sanity"},dataProvider="UITestData")
		public void testSearchByReqNoinHomePage(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
			if(customerName.equalsIgnoreCase("IDC") || customerName.equalsIgnoreCase("IDFC") || customerName.equalsIgnoreCase("FIL") ){
			hpu.typeInReqNoTextBox(customerName, searchParameter);
			}else{
			hpu.validateRequisitionInResultGrid(customerName, searchParameter);
			}
		}
		@Test(groups ={"testSearchByLocationinHomePage","Sanity"},dataProvider="UITestData")
		public void testSearchByLocationinHomePage(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
			//hpu.typeInLocationTextBox(customerName, searchParameter);
			String searchParameterType = null;
			//hpu.clickSearchedParameterType(searchParameterType);
			hpu.validateLocationInResultGrid(customerName, searchParameter);
			
		}
		@Test(groups ={"testDownloadJDfromRequisition","Sanity"},dataProvider="UITestData")
		public void testDownloadJDfromRequisition(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			String customerName=clientName;
			HomePageUtils hpu=new HomePageUtils(driver);
			//String customerName=cu.getCustomerName();
			hpu.clickPlusIconForRequisition(customerName);			
		}
		@Test(groups ={"testVerifyCandidateCountfromRequisition","Sanity"},dataProvider="UITestData")
		public void testVerifyCandidateCountfromRequisition(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
			//hpu.typeInReqNoTextBox(customerName, searchParameter);
			hpu.VerifyCandidateCountInRequisitionGrid(customerName, searchParameter);
			
		}
		@Test(groups ={"testSearchByPrimarySkillinHomepage","Sanity"},dataProvider="UITestData")
		public void testSearchByPrimarySkillinHomepage(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
			//hpu.typeInPrimarySkillTextBox(customerName, searchParameter);
			hpu.validatePrimarySkillInReqGrid(customerName, searchParameter);
			
		}
		@Test(groups ={"testSearchBySLLinHomepage","Sanity"},dataProvider="UITestData")
		public void testSearchBySLLinHomepage(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			String customerName=clientName;
			String searchParameter=testData.getvalue1();
			System.out.println("searchParameter :"+searchParameter);
			hpu.typeInSLLTextBox(customerName, searchParameter);
			
		}
		
		 @Test(groups ={"VerifyPassiveCandidatesinHomepage","Sanity"},dataProvider="UITestData")
		public void VerifyPassiveCandidatesinHomepage(SpireTestObject testObject,TestDataSet testData) throws Exception{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();
			HomePageUtils hpu=new HomePageUtils(driver);
			hpu.clickPassiveCandidatesIcon(); 
		 
		 }
		 // author @ Naresh
		 @Test(groups = { "validateDemandChartLables", "Sanity" }, dataProvider = "UITestData")
			public void validateDemandChartLables(SpireTestObject testObject, TestDataSet testData) throws Exception {
				LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
				initializeDriver(loginPageUtils.driver, testObject);
				loginPageUtils.signin();
				HomePageUtils hu = new HomePageUtils(driver);
				String customerName=clientName;
				hu.validateDemandChart(customerName);
			}

			@Test(groups = { "agingSortOrderinIDFCHomePage", "Sanity" }, dataProvider = "UITestData")
			public void agingSortOrderinIDFCHomePage(SpireTestObject testObject, TestDataSet testData) throws Exception {
				LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
				initializeDriver(loginPageUtils.driver, testObject);
				loginPageUtils.signin();
				HomePageUtils hu = new HomePageUtils(driver);
				hu.clickAgingDropdowninHome();
				
				hu.validateAgingSortOrderinHomePage();
			}

			@Test(groups = { "agingSortOrderinHomePageinAccFil", "Sanity" }, dataProvider = "UITestData")
			public void agingSortOrderinHomePageinAccFil(SpireTestObject testObject, TestDataSet testData) throws Exception {
				LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
				initializeDriver(loginPageUtils.driver, testObject);
				loginPageUtils.signin();
				HomePageUtils hu = new HomePageUtils(driver);
				this.driver.manage().window().maximize();
				
				hu.clickAgingDropdowninHome();
				
				hu.validateAgingSortOrderinFILHomePage();

			}
			@Test(groups = { "agingSortOrderinPhilTecHomePage", "Sanity" }, dataProvider = "UITestData")
			public void agingSortOrderinPhilTecHomePage(SpireTestObject testObject, TestDataSet testData) throws Exception {
				LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
				initializeDriver(loginPageUtils.driver, testObject);
				loginPageUtils.signin();
				HomePageUtils hu = new HomePageUtils(driver);
				hu.clickAgingDropdowninHomeinPhilTec();
				
				hu.validateAgingSortOrderinPhilTecHomePage();
			}
			@Test(groups ={"testvalidateHeadersinReqGrid","Sanity"},dataProvider="UITestData")
			public void testvalidateHeadersinReqGrid(SpireTestObject testObject,TestDataSet testData) throws Exception{
				LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
				initializeDriver(loginPageUtils.driver, testObject);
				loginPageUtils.signin();
				HomePageUtils hu = new HomePageUtils(driver);
				CommonUtils cu=new CommonUtils();
				String customerName=clientName;
				hu.validateHeadersinReqGrid();
				
			}
			// Added For Free TextSearch:@Ganesh
			@Test(groups = { "testvalidateToggle", "Sanity" }, dataProvider = "UITestData")
			public void testvalidateToggle(SpireTestObject testObject, TestDataSet testData) throws Exception {
			LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();

			HomePageUtils hu = new HomePageUtils(driver);
			CommonUtils cu = new CommonUtils();
			// String customerName = clientName;
			hu.ValidateToggleButton();

			}

			// Added For Free TextSearch:@Ganesh
			@Test(groups = { "testvalidateToggleClick", "Sanity" }, dataProvider = "UITestData")
			public void testvalidateToggleClick(SpireTestObject testObject, TestDataSet testData) throws Exception {
			LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin();

			HomePageUtils hu = new HomePageUtils(driver);
			CommonUtils cu = new CommonUtils();
			// String customerName = clientName;
			hu.ValidateToggleButtonClick();

			}

			

}
