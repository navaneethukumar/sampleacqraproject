package com.spire.acqura2.test.manageSaveSearch;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pageUtils.AdvancedSearchPageUtils;
import com.guarented.ecommerce.pageUtils.AdvancedSearchPageUtilsNA;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtils;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtilsNA;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pageUtils.ManageSaveSearchPageUtils;
import com.guarented.ecommerce.pageUtils.UniversalSearchNAUtils;
import com.guarented.ecommerce.pageUtils.UniversalSearchUtils;
import com.guarented.ecommerce.pojo.User;
import com.spire.acqura2.test.accentureNA.universalSearch.UniversalSearchTestPlan;
import com.spire.base.controller.Logging;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "manageSaveSearchTest" }, retryAnalyzer = TestRetryAnalyzer.class)
public class manageSaveSearchTestPlan extends TestPlan {
	public static String clientName = null;
	public static String filePathForCSV = null;
	String saveSearchTitle;
	String newSaveSearchName;
	String getSaveSearchTitle;
	String getShareSaveSearchURL;
	HomePageUtils homePgUtils;
	AdvancedSearchPageUtilsNA advanceSrchPageUtils;
	CandidateListViewUtils candListViewUtils;
	CandidateListViewUtilsNA candListViewUtilsNA;

	@Parameters({ "client" })
	@BeforeClass(alwaysRun = true)
	public static void init(String client) {

		clientName = client;

		System.out.println("Client app for which tests being run is" + " "
				+ clientName);

		filePathForCSV = "./src/test/java/com/spire/acqura2/test/data/"
				+ clientName + "TestData.csv";

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
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(TestDataSet.class,
					entityClazzMap, filePathForCSV, null, methodFilter);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}

	CommonUtils cu = new CommonUtils();

	/**
	 * Flow:1. Login to application 2. Click on Manage Save search 3. Navigate
	 * to Manage Save search
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "validateManageSaveSearchPage", "sanity" }, dataProvider = "UITestData")
	public void validateManageSaveSearchPage(SpireTestObject testObject,
			User user) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.ValidateManageSaveSearch();
		mss.

	}

	/**
	 * Flow:2. Login to application 2. Click on Manage Save search 3. Navigate
	 * to Manage Save search 3. Verify all the elements present in Manage Save
	 * search Screen
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "verifyAllFieldsInManageSaveSearchPage", "sanity" }, dataProvider = "UITestData")
	public void verifyAllFieldsInManageSaveSearchPage(
			SpireTestObject testObject, User user) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.verifyAllFieldsInManageSaveSearch();

	}

	/**
	 * Flow:3. Login to application 2. Search for a combination of skills from
	 * advance search 3. Click on Create Save search 4. Save it 5. Navigate to
	 * Manage Save search 3. Verify its present in Manage Save search Screen
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "verifyCreateSaveSearch", "sanity" }, dataProvider = "UITestData")
	public void verifyCreateSaveSearch(SpireTestObject testObject,
		TestDataSet testData) throws Exception {
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
		advanceUtil.skillSearch(testData);
		clu.verifyResultAppears();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.createSaveSearch(saveSearchTitle);

	}

	/**
	 * Flow:4. Login to application 2. Search for a combination of skills from
	 * advance search 3. Click on Create Save search 4. Save it 5. Again try
	 * creating save search with same name 6. Shouldnt create again with same
	 * name
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "verifyDuplicacyInSaveSearchName", "sanity" }, dataProvider = "UITestData")
	public void verifyDuplicacyInSaveSearchName(SpireTestObject testObject,
		TestDataSet testData) throws Exception {
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
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		advanceUtil.skillSearch(testData);
		mss.duplicacyInSaveSearchName(false);
	}

	/**
	 * Flow:5. Login to application 2. Search for a combination of skills from
	 * universal search 3. Click on Create Save search 4. Save it 5. Navigate to
	 * Manage Save search 3. Verify its present in Manage Save search Screen
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "validateSaveSearchListing", "sanity" }, dataProvider = "UITestData")
	public void validateSaveSearchListing(SpireTestObject testObject,TestDataSet testData) throws Exception {
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
		advanceUtil.skillSearch(testData);
		clu.verifyResultAppears();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.validateSaveSearchListing(saveSearchTitle);
	}

	/**
	 * Flow:5. Login to application 2. Click on Manage Save search 3. Click on
	 * Save search Name
	 * 
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "verifyExecuteSaveSearch", "sanity" }, dataProvider = "UITestData")
	public void verifyExecuteSaveSearch(SpireTestObject testObject,
		TestDataSet testData) throws Exception {
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
		AdvancedSearchPageUtils advanceUtil = new AdvancedSearchPageUtils(
				driver);
		advanceUtil.skillSearch(testData);
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.executeSaveSearch(saveSearchTitle);
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		clu.verifyResultAppears();
	}

	@Test(groups = { "verifyEditSaveSearchName", "sanity" }, dataProvider = "UITestData")
	public void verifyEditSaveSearchName(SpireTestObject testObject,
			TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.verifyEditSaveSearchName();

	}

	@Test(groups = { "verifyShareSaveSearch", "sanity" }, dataProvider = "UITestData")
	public void verifyShareSaveSearch(SpireTestObject testObject,
			TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.verifyShareSaveSearch();

	}

	@Test(groups = { "verifyDeleteSaveSearch", "sanity" }, dataProvider = "UITestData")
	public void verifyDeleteSaveSearch(SpireTestObject testObject,
			TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.verifyDeleteSaveSearch();

	}

	@Test(groups = { "verifySearchInSaveSearch", "sanity" }, dataProvider = "UITestData")
	public void verifySearchInSaveSearch(SpireTestObject testObject,
			TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		String SearchCriteria = testData.getvalue1();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.verifySearchInSaveSearch(SearchCriteria);
		mss.validateSearchResult(testData);
		Logging.log("SearchCriteria validated in Manage Save Search Page page: "
				+ testData.getvalue1());

	}

	@Test(groups = { "verifyPaginationOnSaveSearch", "sanity" }, dataProvider = "UITestData")
	public void verifyPaginationOnSaveSearch(SpireTestObject testObject,
			TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.verifyPaginationOnSaveSearch();
	}

	@Test(groups = { "verifySortOnModifiedOnSaveSearch", "sanity" }, dataProvider = "UITestData")
	public void verifySortOnModifiedOnSaveSearch(SpireTestObject testObject,
			TestDataSet testData) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin();
		ManageSaveSearchPageUtils mss = new ManageSaveSearchPageUtils(driver);
		mss.verifySortOnModifiedOnSaveSearch();

	}

}
