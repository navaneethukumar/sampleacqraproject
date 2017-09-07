package com.guarented.scripts;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pageUtils.CandidateDetailsUtils;
import com.guarented.ecommerce.pageUtils.CandidateSearchUtils;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pages.CandidateListViewPage;
import com.guarented.ecommerce.pages.CandidateSearchPage;
import com.guarented.ecommerce.pages.CommonHeader;
import com.guarented.ecommerce.pojo.User;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "AccentureIDCAutomatchReport" }, retryAnalyzer = TestRetryAnalyzer.class)
public class AccentureIDCAutomatchReport extends TestPlan {

	HomePageUtils homePgUtils = null;
	CandidateSearchUtils candidateSrchUtils = null;

	@DataProvider(name = "AccentureIDCAutomatchReport")
	public static Iterator<Object[]> getCandidateInfo(Method method) {

		Iterator<Object[]> objectsFromCsv = null;

		try {
			String fileName = "./src/test/java/com/spire/scripts/AccentureIDCAutomatchReport.csv";
			LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
			LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
			methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
			entityClazzMap.put("SpireTestObject", SpireTestObject.class);
			entityClazzMap.put("User", User.class);
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(AccentureIDCAutomatchReport.class, entityClazzMap, fileName,
					null, methodFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}

	CommonUtils cu = new CommonUtils();

	@Test(groups = { "getAutomatchReport", "sanity" }, dataProvider = "AccentureIDCAutomatchReport")
	public void getAutomatchReport(SpireTestObject testObject, User user) throws Exception {

		System.out.println("OS is : " + System.getProperty("os.name"));

		LoginPageUtils loginPageUtils = new LoginPageUtils(null, true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		homePgUtils = new HomePageUtils(driver);
		homePgUtils.clickCandidateSearch();
		candidateSrchUtils = new CandidateSearchUtils(driver);
		candidateSrchUtils.validateDownloadAutomatchReport();

	}

}
