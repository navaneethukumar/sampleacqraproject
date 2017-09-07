package com.spire.acqura2.test.accentureNA.advancedSearch;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guarented.ecommerce.candidateListView.candidateListViewTestPlan;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pageUtils.AdvancedSearchPageUtilsNA;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtils;
import com.guarented.ecommerce.pageUtils.CandidateListViewUtilsNA;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pojo.User;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

/**
 * @author Radharani Patra 20/07/2016
 */

@Test(groups = { "advancedSearchTest" }, retryAnalyzer = TestRetryAnalyzer.class)
public class AdvancedSearchTestPlan extends TestPlan {


	HomePageUtils homePgUtils ;
	AdvancedSearchPageUtilsNA advanceSrchPageUtils;
	CandidateListViewUtils candListViewUtils;
	CandidateListViewUtilsNA candListViewUtilsNA;

	@DataProvider(name = "AdvancedSearchTestData")
	public static Iterator<Object[]> getCandidateInfo(Method method) {

		Iterator<Object[]> objectsFromCsv = null;

		try {
			String fileName = "./src/test/java/com/spire/acqura2/test/accentureNA/advancedSearch/AdvancedSearchTestData.csv";
			LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
			LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
			methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
			entityClazzMap.put("SpireTestObject", SpireTestObject.class);
			entityClazzMap.put("User", User.class);
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(candidateListViewTestPlan.class, entityClazzMap, fileName,
					null, methodFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}

	/**
	 * Flow:1. Login to application 2. Click on Advanced link 3. Navigate to
	 * Advanced Search page Verify all the elements present in Advanced Search
	 * Screen
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "verifyAllFieldsInAdvancedSearchScreen", "sanity" }, dataProvider = "AdvancedSearchTestData")
	public void verifyAllFieldsInAdvancedSearchScreen(SpireTestObject testObject, User user) throws Exception {

		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		homePgUtils = new HomePageUtils(driver);
		homePgUtils.clickAdvancedSearchLink();
		advanceSrchPageUtils = new AdvancedSearchPageUtilsNA(driver);
		advanceSrchPageUtils.verifyNavigationToAdvancedSearchPage();
		advanceSrchPageUtils.verifyAllFields();
	}
	
	/**
	 * Flow:1. Login to application 2. Click on Advanced link 3. Navigate to
	 * Advanced Search page 3. Search multiple skills with AND combination
	 * Verify the result
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "searchMultipleSkillWithAndCombination", "sanity" }, dataProvider = "AdvancedSearchTestData")
	public void searchMultipleSkillWithAndCombination(SpireTestObject testObject, User user) throws Exception {

		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		homePgUtils = new HomePageUtils(driver);
		homePgUtils.clickAdvancedSearchLink();
		advanceSrchPageUtils = new AdvancedSearchPageUtilsNA(driver);
		advanceSrchPageUtils.enterMultipleSkillsWithOperator(GuarentedConstants.SKILL_TO_SEARCH,GuarentedConstants.AND_OPERATOR,GuarentedConstants.SKILL_TWO);
		advanceSrchPageUtils.clickSearchButton();
		candListViewUtils = new CandidateListViewUtils(driver);
		candListViewUtils.verifyNavigationToCandidateListPage();
		candListViewUtils.verifySearchresultDivisionText(GuarentedConstants.SKILL_TO_SEARCH,GuarentedConstants.AND_OPERATOR,GuarentedConstants.SKILL_TWO);
		//candListViewUtils.verifySkillsCheckedInRefineSearch(SpireConstants.SKILL_TO_SEARCH,SpireConstants.SKILL_TWO);
	}
	
	/**
	 * Flow:1. Login to application 2. Click on Advanced link 3. Navigate to
	 * Advanced Search page 3. Search multiple skills with OR combination
	 * Verify the result
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "searchMultipleSkillWithORCombination", "sanity" }, dataProvider = "AdvancedSearchTestData")
	public void searchMultipleSkillWithORCombination(SpireTestObject testObject, User user) throws Exception {

		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		homePgUtils = new HomePageUtils(driver);
		homePgUtils.clickAdvancedSearchLink();
		advanceSrchPageUtils = new AdvancedSearchPageUtilsNA(driver);
		advanceSrchPageUtils.enterMultipleSkillsWithOperator(GuarentedConstants.SKILL_TO_SEARCH,GuarentedConstants.OR_OPERATOR,GuarentedConstants.SKILL_THREE);
		advanceSrchPageUtils.clickSearchButton();
		candListViewUtils = new CandidateListViewUtils(driver);
		candListViewUtils.verifyNavigationToCandidateListPage();

}
	
	/**
	 * @author Rahul Kumar
	 * Flow:1. Login to application 2. Click on Advanced link 3. Navigate to
	 * Advanced Search page 4. Enter a skill 5. Enter the Exp 6. Search
	 * 7. Validate the searched result
	 * @throws Exception
	 */
	@Test(groups = { "searchBySkillandExperienceCombination", "sanity" }, dataProvider = "AdvancedSearchTestData")
	public void searchBySkillandExperienceCombination(SpireTestObject testObject, User user) throws Exception {
		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		homePgUtils = new HomePageUtils(driver);
		homePgUtils.clickAdvancedSearchLink();
		advanceSrchPageUtils = new AdvancedSearchPageUtilsNA(driver);

		advanceSrchPageUtils.enterSkill(GuarentedConstants.SKILL_TO_SEARCH);
		advanceSrchPageUtils.clickSkill(GuarentedConstants.SKILL_TO_SEARCH);
		advanceSrchPageUtils.selectLocation("Bangalore");
		advanceSrchPageUtils.enterExp(GuarentedConstants.EXP);
		
		
		advanceSrchPageUtils.clickSearchButton();
		candListViewUtilsNA =new CandidateListViewUtilsNA(driver);
		Thread.sleep(15000);
		System.out.println(candListViewUtilsNA.getDataCountListInFacet("net"));
		System.out.println(candListViewUtilsNA.getDataCountListInFacet("atlanta"));
		System.out.println(candListViewUtilsNA.getDataCountListInFacet("candidate referral"));
		candListViewUtilsNA.validateSearchedResultBySkillandExperienceCombination(GuarentedConstants.SKILL_TO_SEARCH, GuarentedConstants.EXP);
		
	}
	
	
	/**
     * Flow:1. Login to application 2. Click on Advanced link 3. Navigate to
     * Advanced Search page 3. Search Candidate by Atas Id
     * Verify the result
     * 
     * @throws Exception
     */
    @Test(groups = { "searchByAtasId", "sanity" }, dataProvider = "AdvancedSearchTestData")
    public void searchByAtasId(SpireTestObject testObject, User user) throws Exception {

        LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
        initializeDriver(loginPageUtils.driver, testObject);
        loginPageUtils.signin(user);
        homePgUtils = new HomePageUtils(driver);
        homePgUtils.clickAdvancedSearchLink();
        advanceSrchPageUtils = new AdvancedSearchPageUtilsNA(driver);
        advanceSrchPageUtils.searchByAtasId(GuarentedConstants.ATASID_TO_SEARCH);
        advanceSrchPageUtils.clickCandidateSearch();
        
    }
	
}
