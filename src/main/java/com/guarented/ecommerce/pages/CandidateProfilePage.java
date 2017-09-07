package com.guarented.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.driver.Driver;
import com.spire.base.controller.Logging;


//Vasista
public class CandidateProfilePage extends Driver {
	
	public CandidateProfilePage(WebDriver driver) {
		super(driver);
	}
	protected CommonUtils commonUtils = new CommonUtils();
	String searchParamaterType;
	
  //public By firstcandi=By.id("//ul//li[@id='candProLi'][1]//b[1]/span[2]");
	public By firstcandi=By.xpath("(//*[@id='candName']/div/b/span[2])[1] | .//*[@id='candProLi']/div/div/div[3]/span/div[1]/b/span");
	public By stepStatusCount=By.id("//*[contains(@ng-click,'getStepAndStatusCount')]");
	public By goBack=By.xpath(".//*[@id='goBack']"); 
	public By downloadresume=By.id("downloadJDIcon_");
	public By downloadresumeicon=By.xpath("//span[contains(@id,'downloadResumeIcon')]");
	public By simillerProfiles=By.xpath("//*[contains(text(),'Similar Profiles')] | .//*[@id='ngViewDiv']/div/div[1]/div/div[2]/div/div/div[1]/label");
	public By recommendedReq =By.xpath("//*[contains(text(),'Recommended Requisition')]");
	public By CandidateData =By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div/div[1]/div[2]/div");
	public By requisitionCount=By.xpath(".//*[contains(@ng-click,'getStepAndStatusCount')]");
	public By riscore=By.xpath(".//*[contains(@ng-show,'spireRi')]");
	public By activitySteam=By.xpath(".//*[contains(text(),'Activity Stream')]");
	public By enterNotes=By.xpath(".//*[contains(@ng-model,'notes.notesTitle')]");
	public By sumitNotes=By.xpath(".//*[contains(@ng-click,'submitNotes()')]");
	public By enterNoteDes=By.xpath(".//*[contains(@ng-model,'notes.notesDescription')]");
	public By searchNotes=By.xpath(".//*[contains(@ng-model,'notes.searchText')] | .//*[@id='ngViewDiv']/div/div[1]/div/div[1]/div[3]/div/div/div/div[2]/div/div[2]/div[1]/div[1]/div[1]/input");
	public By totalProfilepage=By.xpath("//*[contains(@ng-if,'notesList')]");
	public By clickSearchNotes=By.xpath("//*[contains(@ng-click,'searchNotes()')] | //span[contains(@ng-click,'searchNotes()')]/i | .//*[@id='ngViewDiv']/div/div[1]/div/div[1]/div[3]/div/div/div/div[2]/div/div[2]/div[1]/div[1]/div[2]/span");
	public By UntagfromRequisition=By.xpath("//button[contains(@id,'tagToReq')] | //button[contains(@ng-click,'unTagCandidate')]");
	public By simillerProfilesbox=By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div/div[2]/div/div");
	public By simillerProfilescandidate=By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div/div[2]/div/div/div[2]");
	public By CandidateID = By.xpath("//div[contains(@id,'candidateID')]/span");
	
	
	
    public void clickStepStatausCount(String searchParameterType){
    	commonUtils.explicitWait(driver, stepStatusCount, 30L);
    	this.driver.findElement(stepStatusCount).click();
    }	
    
    public void clickOnFirstCandi() {
    	
	     Logging.log("Clicking the first candidate");
	     this.driver.findElement(firstcandi).click();
    }
	
    public void clickBack() {
	     Logging.log("Clicking the first candidate");
	     this.driver.findElement(goBack).click();
   }
    
    public void clickRequisitionCount() {
	     Logging.log("Clicking the Requisition Count");
	     this.driver.findElement(requisitionCount).click();
   }
    
    public void clickActivityStram() {
	     Logging.log("Clicking the Requisition Count");
	     this.driver.findElement(activitySteam).click();
  }
    
    public WebElement typeInNotesTittle() {
	     Logging.log("Enter the notes tittle ");
	     return this.driver.findElement(enterNotes);
    }
    
    public void clickResumeDownload(){
    	this.driver.findElement(downloadresumeicon).click();
    }
    public WebElement typeInNoteDescription() {
	     Logging.log("Enter the notes description");
	     return this.driver.findElement(enterNoteDes);
   }
    
    public WebElement enterSearchNotes() {
	     Logging.log("Enter the notes description");
	     return this.driver.findElement(searchNotes);
  }
    public void verifyprofilepage(){
    	this.driver.findElement(totalProfilepage).click();
    }
    
    public void clickSearchNotes(){
    	this.driver.findElement(clickSearchNotes).click();
    	//driver.findElement(clickSearchNotes).click();
    	 Logging.log("Enter the  search notes ");
    }
    public void clickSubmitNotes(){
    	this.driver.findElement(sumitNotes).click();
    }
    
 /*   public void typeInNotesTittle(String searchString)
			throws InterruptedException {
		System.out.println("Typing in text field");
		driver.findElement(enterNotes).sendKeys(searchString);
		Thread.sleep(5000);
	}*/
	
	/*public WebElement getUniversalSearchBar(){
    	commonUtils.explicitWait(driver, universalSearchBar, 30L);
    	return this.driver.findElement(universalSearchBar);
    }
    
    public WebElement getUniversalSearchBarTextField(){
    	commonUtils.explicitWait(driver, universalSearchBarTextField, 30L);
    	return this.driver.findElement(universalSearchBarTextField);
    }
     

   
    public void clickUniversalSearchButton(){
    	this.driver.findElement(universalSearchButton).click();
    	}
    
    public List<WebElement> getSearchedPamameterResultList() throws InterruptedException{
    	commonUtils.explicitWait(driver, searchedPamameterResultList, 30L);
		return driver.findElements(searchedPamameterResultList);
	}
    
   public List<WebElement> getSearchedPamameterTypeList() throws InterruptedException{
	    commonUtils.explicitWait(driver, searchedPamameterTypeList, 30L);
		return driver.findElements(searchedPamameterTypeList);
	}
	*/
    public boolean isElementPreset(By object, WebDriver driver) {

		try {
			return driver.findElement(object).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
}
