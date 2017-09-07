package com.spire.acqura2.test.accentureNA.universalSearch;


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
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pageUtils.UniversalSearchNAUtils;
import com.guarented.ecommerce.pages.CandidateListViewPage;
import com.guarented.ecommerce.pages.CandidateSearchPage;
import com.guarented.ecommerce.pages.CommonHeader;
import com.guarented.ecommerce.pojo.User;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "universalSearchTest"},retryAnalyzer=TestRetryAnalyzer.class)
public class UniversalSearchTestPlan extends TestPlan{
	@DataProvider(name = "UniversalSearchTestData")
	public static Iterator<Object[]> getCandidateInfo(Method method) {

		Iterator<Object[]> objectsFromCsv = null;
		
		try {
			String fileName = "./src/test/java/com/spire/acqura2/test/accentureNA/universalSearch/UniversalSearchTestData.csv";
			LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
			LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
			methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
			entityClazzMap.put("SpireTestObject", SpireTestObject.class);
			entityClazzMap.put("User", User.class);
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(
					UniversalSearchTestPlan.class, entityClazzMap, fileName,
					null, methodFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}
	
	CommonUtils cu=new CommonUtils();
	
	
	//@author- pritisudha
		//date-20-07-2016
		
		@Test(groups={"NA_universalSearch_with_skill", "sanity"}, dataProvider = "UniversalSearchTestData")
		public void NA_universalSearch_with_skill(SpireTestObject testObject,User user) throws Exception
		{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin(user);
			
			//Thread.sleep(20000);
			UniversalSearchNAUtils us=new UniversalSearchNAUtils(driver);
			us.validateskillsearchInUniversalSearch(GuarentedConstants.AUTOSUGGEST_OPTION_TO_SEARCH);
			
		}
		
		//Vasista-21-07-2016
		@Test(groups={"HomePage_Validation", "sanity"}, dataProvider = "UniversalSearchTestData")
		public void HomePage_Validation(SpireTestObject testObject,User user) throws Exception
		{
			LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
			initializeDriver(loginPageUtils.driver, testObject);
			loginPageUtils.signin(user);
			
			//Thread.sleep(20000);
			UniversalSearchNAUtils us=new UniversalSearchNAUtils(driver);
			us.ValidateHomePage();
			
		}
	
		             // vasista 21 -07-2016
				/*
				 * search by Skills
				 */
			
				@Test(groups={"SearchBySkill", "sanity"}, dataProvider = "UniversalSearchTestData")
				public void SearchBySkill(SpireTestObject testObject,User user) throws Exception
				{
					LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
					initializeDriver(loginPageUtils.driver, testObject);
					loginPageUtils.signin(user);
					
					//Thread.sleep(20000);
					UniversalSearchNAUtils us=new UniversalSearchNAUtils(driver);
					us.SearchWithSkill();
					
				}
			
	
	
}
