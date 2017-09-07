package com.spire.acqura2.test.advancedSearch;

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
import com.spire.base.controller.Logging;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "advancedSearchTest" }, retryAnalyzer = TestRetryAnalyzer.class)

public class AdvancedSearchTestPlan extends TestPlan {

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

	@Test(groups = { "testNavigationToAdvancedSearch", "Sanity" }, dataProvider = "UITestData")
	public void testNavigationToAdvancedSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {

		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		// String customerName=cu.getCustomerName();
		hpu.goToAdvanceSearchPage(clientName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.validateAdvanceSearchPage();
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify the fields/label in the page
	 */

	@Test(groups = { "validateAllLabelsAdvancedPage" }, dataProvider = "UITestData")
	public void validateAllLabelsAdvancedPage(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		CommonUtils cu = new CommonUtils();
		String customerName = cu.getCustomerName();
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		// advanceUtil.scrollVerticalBar(100);
		Boolean flag = advanceUtil.validateAllLabels(testData);
		Logging.log("Validate all the labels in Advanced search page successfully.");
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in all the fields
	 */

	@Test(groups = { "validateAutoSuggest" }, dataProvider = "UITestData")
	public void validateAutoSuggest(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		CommonUtils cu = new CommonUtils();
		String customerName = cu.getCustomerName();
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		// advanceUtil.scrollVerticalBar(2);
		advanceUtil.validateAutoSuggestion();
		Logging.log("Validate all the labels in Advanced search page successfully.");
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Skill fields and Search
	 */
	@Test(groups = { "ValidateSkillSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateSkillSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		CommonUtils cu = new CommonUtils();
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		Thread.sleep(5000);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.skillSearch(testData.getvalue1());
		String[] matchCloudArr = cu.getMatchCloudArray(testData.getvalue2());
		clu.verifyResultAppears();
		clu.validateResultBySkillCloud(matchCloudArr);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Candidate City fields and Search
	 */

	@Test(groups = { "ValidateCandidateCitySearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateCandidateCitySearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.candidateCitySearch(testData);
		clu.verifyCityResults(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate City validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Candidate State fields and Search
	 */

	@Test(groups = { "validateCandidateStateSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateCandidateStateSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.candidateStateSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate State validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Candidate State fields and Search
	 */

	@Test(groups = { "validateCandidateCountrySearch" }, dataProvider = "UITestData")
	public void validateCandidateCountrySearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.candidateCountrySearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Country validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Location fields and Search
	 */

	@Test(groups = { "validateLocationSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateLocationSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.locationSearch(testData);
		clu.verifyCityResults(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Location validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Candidate State fields and Search
	 */

	@Test(groups = { "validateExperienceRangeSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateExperienceRangeSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.experienceRangeSearch(testData);
		clu.verifyExperienceResults(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Experience range validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in JobTitle fields and Search
	 */

	@Test(groups = { "validateJobTitleSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateJobTitleSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.jobTitleSearch(testData);
		if(customerName.contains("AccentureNA")){
		clu.verifyJobTitle(testData, customerName);
		}
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Job Title validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Location fields and Search
	 */

	@Test(groups = { "validateInstituteSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateInstituteSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.instituteSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Institute validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in SourceType fields and Search
	 */

	@Test(groups = { "validateSourceTypeSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateSourceTypeSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.sourceTypeSearch(testData);
		if (customerName.equalsIgnoreCase("AccentureNA") || customerName.equalsIgnoreCase("IDFC")
				|| customerName.equalsIgnoreCase("IDC")) {
			System.out.println("Customer Name: " + customerName);
			clu.verifySource(testData);
		} else if (customerName.equalsIgnoreCase("FIL")) {
			System.out.println("Customer Name: " + customerName);
			clu.verifySourceType(testData);
		}
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Source Type validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in SourceType fields and Search
	 */

	@Test(groups = { "validateSourceNameSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateSourceNameSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		Thread.sleep(6000);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.sourceNameSearch(testData);
		if (customerName.equalsIgnoreCase("IDC") || customerName.equalsIgnoreCase("FIL")) {
			System.out.println("Customer Name: " + customerName);
			clu.verifySourceName(testData);
		}
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Source Name validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Location fields and Search
	 */

	@Test(groups = { "validateEmployerSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateEmployerSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.employerSearch(testData, customerName);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Employer validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Education fields and Search
	 */

	@Test(groups = { "validateEducationSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateEducationSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.educationSearch(testData);
		if (customerName.equalsIgnoreCase("AccentureNA")) {
			clu.verifyEducationName(testData);
		}
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Employer validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Candidate Last Modified Date fields Search
	 */

	@Test(groups = { "validateCandidateLastModifiedDateSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateCandidateLastModifiedDateSearch(SpireTestObject testObject, TestDataSet testData)
			throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.lastModifiedDateSearch(testData);
		if (customerName.equalsIgnoreCase("FIL") || customerName.equalsIgnoreCase("IDFC")
				|| customerName.equalsIgnoreCase("IDC") || customerName.equalsIgnoreCase("AccentureIndOps") || customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccenturePhilTech")) {
			clu.validateBreadCrumbsDate(testData);
		} else {
			clu.validateBreadCrumbs(testData);
		}
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Last Modified Date validated in list page From: " + testData.getvalue1() + " to: "
				+ testData.getvalue2());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Candidate Created Date fields Search
	 */

	@Test(groups = { "validateCandidateCreatedDateSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateCandidateCreatedDateSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.createdDateSearch(testData);
		if (customerName.equalsIgnoreCase("FIL") || customerName.equalsIgnoreCase("IDFC")
				|| customerName.equalsIgnoreCase("IDC") || customerName.equalsIgnoreCase("AccentureIndOps") || customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccenturePhilTech")) {
			clu.validateBreadCrumbsDate(testData);
		} else {
			clu.validateBreadCrumbs(testData);
		}
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Created Date validated in list page From: " + testData.getvalue1() + " to: "
				+ testData.getvalue2());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Location fields and Search
	 */

	@Test(groups = { "validateCandidateIdSearch", "Sanity" }, dataProvider = "UITestData")
	public void validateCandidateIdSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.candidateIdSearch(testData, customerName);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Employer validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Primary Skill fields and Search
	 */
	@Test(groups = { "ValidatePrimarySkillSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidatePrimarySkillSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.primarySkillSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Primary Skill validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in First prefered location fields and
	 * Search
	 */
	@Test(groups = { "ValidateFirstPreferedLocationSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateFirstPreferedLocationSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.firstPreferredLocationSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate First prefered location validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Status group fields and Search
	 */
	@Test(groups = { "ValidateStatusGroupSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateStatusGroupSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.statusGroupSearch(testData);
		Thread.sleep(5000);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Status group validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Full Name fields and Search
	 */
	@Test(groups = { "ValidateFullNameSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateFullNameSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.fullNameSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Full Name validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in SR NO fields and Search
	 */
	@Test(groups = { "ValidateSRNOSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateSRNOSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.sRNOSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate SR NO validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Number fields and Search
	 */
	@Test(groups = { "ValidateNumberSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateNumberSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.numberSearch(testData);
		clu.verifyNumber(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Number validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Email Id fields and Search
	 */
	@Test(groups = { "ValidateEmailIdSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateEmailIdSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.emailIdSearch(testData);
		clu.verifyEmailId(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Email ID validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in Abacus Status fields and Search
	 */
	@Test(groups = { "ValidateAbacusStatusSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateAbacusStatusSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.abacusStatusSearch(testData);
		clu.verifyAbacusStatus(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Abacus Status validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify autosuggestion in candidate Status fields and Search
	 */
	@Test(groups = { "ValidateCandidateStatusSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateCandidateStatusSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.CandidateStatusSearch(testData);
		clu.verifyCandidateStatus(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Abacus Status validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}
    
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify Untagged radio button click fields and Search
	 */
	@Test(groups = { "ValidateUntaggedSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateUntaggedSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.untaggedSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Untagged Search validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify tagged radio button click fields and Search
	 */
	@Test(groups = { "ValidateTaggedSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateTaggedSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.taggedSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Tagged Search validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify Untagged radio button click fields and Search
	 */
	@Test(groups = { "ValidateAutomatchedSearch", "Sanity" }, dataProvider = "UITestData")
	public void ValidateAutomatchedSearch(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.automatchedSearch(testData);
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Automatched Search validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}

	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon3) Click on search button
	 * 
	 * Validations: Verify pop up message
	 */
	@Test(groups = { "ValidateOnSearchButton", "Sanity" }, dataProvider = "UITestData")
	public void ValidateOnSearchButton(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		Thread.sleep(10000);
		advanceUtil.ClickSearchButton();
		//Thread.sleep(1000);
		Assert.assertTrue(advanceUtil.getToastMessage().equalsIgnoreCase("Please Enter Search Criteria"), "Search Button is not validated.");
		Logging.log("Search Button is validated. Message: "+advanceUtil.getToastMessage());
	}
	
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon3) Click on search button
	 * 
	 * Validations: Verify pop up message
	 */
	@Test(groups = { "ValidateOnCancelButton", "Sanity" }, dataProvider = "UITestData")
	public void ValidateOnCancelButton(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.clickCancelButton();
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Acqura"), "Not Navigating to homepage on click cancel button");
		Logging.log("Navigating to Homepage, Page title validation: "+driver.getTitle());
	}
	
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify skill search with and operation
	 */
	@Test(groups = { "ValidateSkillSearchWithAndOperation", "Sanity" }, dataProvider = "UITestData")
	public void ValidateSkillSearchWithAndOperation(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.skillSearch(testData.getvalue1());
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single skill: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultipleSkills(testData.getvalue1(),"AND",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)>=Integer.parseInt(count2));
		Logging.log("Searched with AND Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}
	@Test(groups = { "ValidateSkillSearchWithAndOperationinFIL", "Sanity" }, dataProvider = "UITestData")
	public void ValidateSkillSearchWithAndOperationinFIL(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.skillSearch(testData.getvalue1());
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single skill: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultipleSkillsinFIL(testData.getvalue1(),"AND",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)>=Integer.parseInt(count2));
		Logging.log("Searched with AND Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}
	
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify skill search with or operation
	 */
	@Test(groups = { "ValidateSkillSearchWithOROperation", "Sanity" }, dataProvider = "UITestData")
	public void ValidateSkillSearchWithOROperation(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.skillSearch(testData.getvalue1());
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single skill: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultipleSkills(testData.getvalue1(),"OR",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)<=Integer.parseInt(count2));
		Logging.log("Searched with OR Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}
	@Test(groups = { "ValidateSkillSearchWithOROperationinFIL", "Sanity" }, dataProvider = "UITestData")
	public void ValidateSkillSearchWithOROperationinFIL(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.skillSearch(testData.getvalue1());
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single skill: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultipleSkillsinFIL(testData.getvalue1(),"OR",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)<=Integer.parseInt(count2));
		Logging.log("Searched with OR Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}

	
	@Test(groups = { "validateWorkdayCandidateStatus", "Sanity" }, dataProvider = "UITestData")
	public void validateWorkdayCandidateStatus(SpireTestObject testObject, TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.selectWorkdayCandidateStatus(testData.getvalue1());
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Workday Status validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}
	
	@Test(groups = { "validateSpireStatus", "Sanity" }, dataProvider = "UITestData")
	public void validateSpireStatus(SpireTestObject testObject, TestDataSet testData) throws Exception{
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.selectSpireStatus(testData.getvalue1());
		clu.validateBreadCrumbs(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Candidate Workday Status validated in list page: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
	}
	
		
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify skill search with or operation
	 */
	@Test(groups = { "ValidatePrimarySkillSearchWithOROperation", "Sanity" }, dataProvider = "UITestData")
	public void ValidatePrimarySkillSearchWithOROperation(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.primarySkillSearch(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single Primary skill: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultiplePrimarySkills(testData.getvalue1(),"OR",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)<=Integer.parseInt(count2));
		Logging.log("Searched with OR Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}
	
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify skill search with or operation
	 */
	@Test(groups = { "ValidateLocationWithOROperation", "Sanity" }, dataProvider = "UITestData")
	public void ValidateLocationWithOROperation(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.locationSearch(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single Location: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultipleLocation(testData.getvalue1(),"OR",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)<=Integer.parseInt(count2));
		Logging.log("Searched with OR Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}
	
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify skill search with or operation
	 */
	@Test(groups = { "ValidateFirstPreferLocationWithOROperation", "Sanity" }, dataProvider = "UITestData")
	public void ValidateFirstPreferLocationWithOROperation(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.firstPreferredLocationSearch(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single First Prefered Location: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultipleFirstPreferLocation(testData.getvalue1(),"OR",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)<=Integer.parseInt(count2));
		Logging.log("Searched with OR Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}
	
	/**
	 * Steps: 1) Login 2) click on Recruiter workbench link 3) Click on Advanced
	 * Search Icon
	 * 
	 * Validations: Verify skill search with or operation
	 */
	@Test(groups = { "ValidateJobTitleWithOROperation", "Sanity" }, dataProvider = "UITestData")
	public void ValidateJobTitleWithOROperation(SpireTestObject testObject, TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		if(clientName.equals("IDC")){
			System.out.println("wait in case of idc only");
			Thread.sleep(5000);			
		}
		HomePageUtils hpu = new HomePageUtils(driver);
		String customerName = clientName;
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		hpu.goToAdvanceSearchPage(customerName);
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(driver);
		advanceUtil.jobTitleSearch(testData);
		String count = clu.getNumberOfRecords();
		Logging.log("Searched with Single First Prefered Location: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count);
		hpu.goToAdvanceSearchPage(customerName);
		advanceUtil.searchMultipleJobTitle(testData.getvalue1(),"OR",testData.getvalue2());
		String count2 = clu.getNumberOfRecords();
		Assert.assertTrue(Integer.parseInt(count)<=Integer.parseInt(count2));
		Logging.log("Searched with OR Operator: " + testData.getvalue1());
		Logging.log("Number Of Results found: " + count2);
	}
}
