package com.spire.acqura2.test.accentureNA.candidateListView;


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
import com.guarented.ecommerce.pageUtils.LoginPageUtils;
import com.guarented.ecommerce.pages.CandidateListViewPage;
import com.guarented.ecommerce.pages.CandidateSearchPage;
import com.guarented.ecommerce.pages.CommonHeader;
import com.guarented.ecommerce.pojo.User;
import com.spire.base.controller.TestPlan;
import com.spire.base.controller.TestRetryAnalyzer;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

@Test(groups = { "candidateListViewTest"},retryAnalyzer=TestRetryAnalyzer.class)
public class candidateListViewTestPlan extends TestPlan{
	@DataProvider(name = "candidateListViewTestData")
	public static Iterator<Object[]> getCandidateInfo(Method method) {

		Iterator<Object[]> objectsFromCsv = null;
		
		try {
			String fileName = "./src/test/java/com/spire/acqura2/test/accenture/candidateListView/candidateListViewTestData.csv";
			LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
			LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
			methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
			entityClazzMap.put("SpireTestObject", SpireTestObject.class);
			entityClazzMap.put("User", User.class);
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(
					candidateListViewTestPlan.class, entityClazzMap, fileName,
					null, methodFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}
	
	CommonUtils cu=new CommonUtils();
	
	@Test(groups = { "validateCandidateDetails", "sanity" }, dataProvider = "candidateListViewTestData")
	public void validateCandidateDetails(SpireTestObject testObject,User user) throws Exception {
		
		
		System.out.println("OS is : "+ System.getProperty("os.name"));

		LoginPageUtils loginPageUtils = new LoginPageUtils(null,true);
		initializeDriver(loginPageUtils.driver, testObject);
		loginPageUtils.signin(user);
		CommonHeader ch=new CommonHeader(driver);
		ch.clickCandidateManagementLink();
		ch.clickCandidateSearchLink();
		
		CandidateSearchPage csp=new CandidateSearchPage(driver);
		csp.enterFullName(GuarentedConstants.CANDIDATE_NAME);
		csp.clickSearchButton();
		//Thread.sleep(10000);
	
		
		CandidateListViewPage clv=new CandidateListViewPage(driver);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cu.implicitWait(driver, 30L);
		clv.clickFirstCandidateNameLink();
		Thread.sleep(5000);
		
		/*String parentWindow,childWindow;
		Set set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		parentWindow=it.next();
		childWindow=it.next();
		driver.switchTo().window(childWindow);
		*/
		
		List ls=cu.getWindows(driver);
		System.out.println("parentWindow:" + ls.get(0).toString());
		System.out.println("childWindow:" + ls.get(1).toString());
		driver.switchTo().window(ls.get(1).toString());
		
		
		cu.implicitWait(driver, 10L);
		CandidateDetailsUtils cdu=new CandidateDetailsUtils(driver);
		cdu.clickSeeMoreLink();	
		
	}
	
	
	
	
	
}
