package com.guarented.ecommerce.pageUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.pages.*;
import com.spire.base.controller.Assertion;
import com.spire.base.controller.Logging;

// Vasista
public class CandidateProfilePageUtils extends CandidateProfilePage{
	CommonUtils cu=new CommonUtils();
	
	public static String clientName = null;	
	public static String filePathForCSV = null;

	String customer;
	//CandidateProfilePage cpp=new CandidateProfilePage();
	public CandidateProfilePageUtils(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//
	public void validateprofilepage () throws InterruptedException
	{
		// 
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(riscore)); 
		clickOnFirstCandi();
		
	}
	
	public void ValidateProfilepage1() throws InterruptedException{
		Logging.log("Validating profile Page");
		System.out.println("Validating profile Page");
        cu.explicitWait(driver, simillerProfiles, 40L);
		//Assert.assertTrue(isElementPreset(goBack, driver),"no back button");
		Assert.assertTrue(isElementPreset(simillerProfiles, driver),"no back button");
		Thread.sleep(3000);
		Assert.assertTrue(isElementPreset(CandidateData, driver),"no back button");
		Thread.sleep(3000);
		//Assert.assertTrue(isElementPreset(recommendedReq, driver),"no back button");

	}
	
	
	public void validateRequisitionCount() throws InterruptedException
	{
		  
		clickOnFirstCandi();
		System.out.println("star");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(goBack)); 
        Logging.log("Able to verify back link in profiel page");
		clickRequisitionCount();
		
	}
	/*
	 * create note -only with tittle
	 */
	
	public void createNoteWithTittle(String enterText) throws InterruptedException {
		clickOnFirstCandi();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(goBack));
        Logging.log("Able to verify back link in profiel page");
        Thread.sleep(3000);
        clickActivityStram();
        typeInNotesTittle().sendKeys(enterText);
        commonUtils.moveVerticalScrollBar(driver, 300);
        Thread.sleep(3000);
        clickSubmitNotes();
	}
	
	
	/*public void createNoteWithFullDetails(String enterText, TestDataSet testData) throws InterruptedException {
	
}*/
	
	/*
	 * search notes
	 */
	
	public void searchNotes(String enterText) throws InterruptedException {
		clickOnFirstCandi();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(goBack));
        Logging.log("Able to verify back link in profiel page");
        Thread.sleep(3000);
        clickActivityStram();
        commonUtils.moveVerticalScrollBar(driver, 300);
        enterSearchNotes().sendKeys(enterText);
        //typeInNotesTittle().sendKeys(enterText);
        clickSearchNotes();
        Thread.sleep(5000);
      
 
       List<WebElement>  eles = driver.findElements(totalProfilepage);
       for(WebElement ele : eles){
       String label = ele.getText();
       System.out.println("response of search" +label);
       if("star".equalsIgnoreCase(label)){
    	   System.out.println("able to find the search title");
       }else{}
       //ele.click();
        driver.getPageSource().contains("input");
        
/*        String pageSource = driver.getPageSource();
        if(pageSource.contains("")){
            System.out.println("2) Expected text '"+expText+"' present in the web page.");
        }else{
            System.out.println("2) Expected text '"+expText+"' is not present in the web page.");
        */}
/*	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));*/

       }
	/*
	 * create notes with full details
	 */
	public void createNoteWithFullDetails(String tittle, TestDataSet testData) throws InterruptedException {
		// TODO Auto-generated method stub
		clickOnFirstCandi();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(goBack));
        Logging.log("Able to verify back link in profiel page");
        Thread.sleep(3000);
        clickActivityStram();
        typeInNotesTittle().sendKeys(testData.getvalue3());
        commonUtils.moveVerticalScrollBar(driver, 300);
        typeInNoteDescription().sendKeys(testData.getvalue4());
        commonUtils.moveVerticalScrollBar(driver, 300);
        Thread.sleep(3000);
        clickSubmitNotes();
	}
	
	/*
	 * Time perioed
	 */
	public void ClickDownloadResume() throws InterruptedException
	{
		commonUtils.explicitWait(driver, By.xpath("//span[contains(@id,'downloadResumeIcon')]"), 30L);
		clickResumeDownload();
		Thread.sleep(3000);
	}
	public void VerifyUntagfromRequisition() throws InterruptedException
	{
		Logging.log("Validating Untag From Requisition In profile Page");
		commonUtils.explicitWait(driver, By.xpath("//button[contains(@id,'tagToReq')] | //button[contains(@ng-click,'unTagCandidate')]"), 30L);
		System.out.println("Validating Untag From Requisition In profile Page");
		Thread.sleep(3000);
		Assert.assertTrue(isElementPreset(UntagfromRequisition, driver),"no back button");
		System.out.println("Validated Untag From Requisition");

	}
	public void validatesimilarprofiles() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(riscore)); 
		clickOnFirstCandi();
		Logging.log("Validating Similar profiles ");
		System.out.println("Validating Similar profiles");
        Thread.sleep(3000);
		//Assert.assertTrue(isElementPreset(goBack, driver),"no back button");
		Assert.assertTrue(isElementPreset(simillerProfiles, driver),"no back button");
		Assert.assertTrue(isElementPreset(simillerProfilesbox, driver),"no back button");
		Assert.assertTrue(isElementPreset(simillerProfilescandidate, driver),"no back button");
		//Assert.assertTrue(isElementPreset(recommendedReq, driver),"no back button");
	}
	public void validatecandidatedisplayID() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(riscore)); 
		clickOnFirstCandi();
		Logging.log("Validating Candidate Display ID ");
		System.out.println("Validating Candidate Display ID");
        Thread.sleep(3000);
		Assert.assertTrue(isElementPreset(CandidateID, driver),"no back button");
	}
	
}
