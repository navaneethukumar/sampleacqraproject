package com.guarented.ecommerce.pageUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pages.UniversalSearchNAPage;
import com.spire.base.controller.Assertion;
import com.spire.base.controller.Logging;

public class UniversalSearchNAUtils extends UniversalSearchNAPage  {
	CommonUtils cu=new CommonUtils();
	public UniversalSearchNAUtils(WebDriver driver) {
		super(driver);
		
	}
	public void validateAutoSuggestionInUniversalSearch(String searchString) throws InterruptedException{

		List <WebElement>list=getAutoSuggestListInUniversalSearch(searchString);
		System.out.println("Auto Suggest List size : " + list.size());
		for(int i=0;i<list.size();i++)
			System.out.println("list element is : "+list.get(i).getText());
		Logging.log("Asserting that auto suggest list is available or not");
		Assertion.assertTrue(list.size()>=1, "Auto Suggest List is not Available for : "+searchString);
		Logging.log("Verifying Auto Suggest List Data ");
		boolean flag;
		for(int i=0;i<list.size();i++){
			flag=list.get(i).getText().toLowerCase().contains(searchString.toLowerCase());
			Assertion.assertTrue(flag, "Auto suggest list is not as per expectation as searched element does not contain "+searchString);
		}

	}
	public void validateskillsearchInUniversalSearch(String searchString) throws InterruptedException
	{
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//WebDriverWait wait=new WebDriverWait(driver, 40);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(universalSearchfirstclick));
		Logging.log("clicking on the first searchbox");
		getuniversalSearchfirstclick();
		Thread.sleep(20000);
		Logging.log("clicking on the second searchbox");
		getuniversalSearchsecondclick();
		Logging.log("typing in universalsearch");
		typeInUniversalSearch(searchString);
		Logging.log("selecting skill from drop down");
		getselectsearchparameterbyskill();
		Logging.log("clicking on search");
		getsearchButton();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assertion.assertTrue(getcheckbox_skill_select(), "not selected");
		
		
		
	}
	//vasista
	
	public void ValidateHomePage(){
		Logging.log("Validating Home Page");
		
		Assert.assertTrue(isElementPreset(userDetails, driver),"home page user name-mismatch");
		
		Assert.assertTrue(isElementPreset(advancedSearchLink,driver), "advancedLink missing");
		
		Assert.assertTrue(isElementPreset(searchButton,driver), "SearchTextfield is missing");
		
	}
	
	 // vasista    Search skill java 
	
		/*
		 * 1. Login 2. Search with skill java 
		 * 3. click on 1st candidate  4. click on More skills 
		 * 5. Verify java is there or not
		 */
		
		
		public void SearchWithSkill() throws InterruptedException{
			
			getuniversalSearchfirstclick();
			Thread.sleep(20000);
			Logging.log("clicking on the second searchbox");
			getuniversalSearchsecondclick();
			Logging.log("typing in universalsearch");
			typeInUniversalSearch(GuarentedConstants.ENTER_SKILL_1);
			Logging.log("selecting skill from drop down");
			getselectsearchparameterbyskill();
			Logging.log("clicking on search");
			getsearchButton();
			Thread.sleep(20000);
//			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Assertion.assertTrue(getcheckbox_skill_select(), "not selected");
			clickLastPage();
			Thread.sleep(20000);
			clickCandidate();
			Thread.sleep(20000);
			clickShomeMoreLink();
			Thread.sleep(20000);
			System.out.println("clicked on  show more link");
			Thread.sleep(1000);
			String text = driver.findElement(searchSkill1).getText();
			Logging.log("Now java will find ");
			//asser.assert.tr(text.contains(SpireConstants.ENTER_SKILL_1)){
			System.out.println("pass");
			Assert.assertTrue(text.contains(GuarentedConstants.ENTER_SKILL_1));
			Logging.log(GuarentedConstants.ENTER_SKILL_1);

			}
						

	

}

