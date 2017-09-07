package com.guarented.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.driver.Driver;

public class AdvanceSearchPage extends Driver {

	public AdvanceSearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	CommonUtils cu = new CommonUtils();

	public By advanceSearchLabel = By.xpath("//div[text()='Advanced Search'] | //span[text()='Advanced Search']");
	public By searchButton = By.xpath("//button[contains(text(),'Search')]");
	public By labelSkill = By.xpath("//label[text()='Skill']");
	public By labelCandidateID = By.xpath("//label[text()='Candidate ID']");
	public By verticalScrollInner = By.xpath("//div[@class='ps-scrollbar-y']");
	// public By
	// verticalScrollInner=By.xpath("//*[@id='ngViewDiv']/div/div/div[2]/div[3]/div");
	// public By
	// getAllLabels=By.xpath("//div[@id='ngViewDiv']/div/div/div[2]/div/div/form/div/label");
	public By getAllLabels = By.xpath("//div[@id='ngViewDiv']/div/div/div[2]/div/div/form/div/label");
	public By getAllInputFields = By.xpath("//div[@id='ngViewDiv']/div/div/div[2]/div/div/form/div/div/div/div/input");
	public By getAllAutoSuggestion = By.xpath("//div[@id='ui-select-choices-row-2-']/a/span");
    public By searchButtonAdvancedSearch = By.xpath("//button[@type='submit'] | html/body/div[1]/div[2]/div/div/div[2]/div/div[2]/form/div[21]/div/div/button[1] | //button[contains(@type,'submit')]/span | //button[contains(text(),'Search')] | html/body/div[1]/div[2]/div/div/div[2]/div/div[2]/form/div[14]/div/div/button[1]");
 // public By searchButtonAdvancedSearch = By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div[2]/form/div[21]/div/div/button[1]");
  // public By searchButtonAdvancedSearch = By.xpath("//button[@type='submit']");
   
   public By skillInputField = By.xpath("//label[text()='Skill']/following-sibling::div/div/div/input");
	public By educationLabel = By
			.xpath("//div[@id='ngViewDiv']/div/div/div[2]/div[1]/div/div/form/div[2]/div[1]/label");
	public By candidateCityInputField = By
			.xpath("//label[text()='Candidate City']/following-sibling::div/div/div/input");
	public By candidateStateInputField = By
			.xpath("//label[text()='Candidate State']/following-sibling::div/div/div/input");
	public By candidateCountryInputField = By
			.xpath("//label[text()='Candidate Country']/following-sibling::div/div/div/input");
	public By locationInputField = By.xpath("//label[text()='Location']/following-sibling::div/div/div/input");
	public By frmExperienceYears = By.xpath("//select[@id='exprienceFrm']");
	public By frmExperienceMonths = By.xpath("//select[@ng-model='searchForm.Experience.from.months']");
	public By toExperienceYears = By.xpath("//select[@id='exprienceTo']");
	public By toExperienceMonths = By.xpath("//select[@ng-model='searchForm.Experience.to.months']");
	public By jobTitleInputField = By.xpath("//label[contains(text(),'Title')]/following-sibling::div/div/div/input");
	public By instituteInputField = By
			.xpath("//label[contains(text(),'Institute')]/following-sibling::div/div/div/input");
	public By sourceTypeInputField = By
			.xpath("//span[contains(text(),'Source Type')]/following-sibling::div/div/input | //div[contains(@ng-model,'sourceType')]/div/input");
	public By sourceNameInputField = By
			.xpath("//span[contains(text(),'Source Name')]/following-sibling::div/div/input | html/body/div[1]/div[2]/div/div/div[2]/div/div[2]/form/div[8]/div/div[2]/div/div/input | //div[contains(@ng-model,'sourceName')]/div/input");
	public By employerInputField = By
			.xpath("//label[contains(text(),'Employer')]/following-sibling::div/div/div/input");
	public By companyInputField = By.xpath("//label[contains(text(),'Company')]/following-sibling::div/div/div/input");
	public By educationInputField = By
			.xpath("//label[contains(text(),'Education')]/following-sibling::div/div/div/input");
	public By lastModifiedFromInputField = By
			.xpath("//label[contains(text(),'Last Modified')]/following-sibling::div/div[1]/p/input");
	public By lastModifiedToInputField = By
			.xpath("//label[contains(text(),'Last Modified')]/following-sibling::div/div[2]/p/input");
	public By createFromInputField = By
			.xpath("//label[contains(text(),'Created Date')]/following-sibling::div/div[1]/p/input");
	public By createToInputField = By
			.xpath("//label[contains(text(),'Created Date')]/following-sibling::div/div[2]/p/input");
	public By candidateId_NA = By.xpath("//label[contains(text(),'ID')]/following-sibling::div/textarea");
	public By candidateId = By.xpath("//textarea");
	public By primarySkillInputField = By.xpath("//div/label[contains(text(),'Primary Skills')]/following-sibling::div/div/div/input | //label[text()='Primary Skill']/following-sibling::div/div/div/input");
	public By firstPreferredLocationInputField = By.xpath("//label[text()='First Preferred Location']/following-sibling::div/div/div/input");
	public By suggestDropdown = By.xpath("//span[@class='ng-binding ng-scope']");
	public By statusGroupInputField = By.xpath("//label[text()='Status Group']/following-sibling::div/div/div/input");
	public By fullNameInputField = By.xpath("//label[text()='Full Name']/following-sibling::div/div/div/input");
	public By sRNOInputField = By.xpath("//label[text()='SR NO']/following-sibling::div/div/div/input");
	public By numberInputField = By.xpath("//label[contains(text(),'Number')]/following-sibling::div/input");
	public By emailInputField = By.xpath("//label[contains(text(),'Email')]/following-sibling::div/input");
	public By abacusStatusInputField = By.xpath("//label[text()='Abacus Status']/following-sibling::div/div/div/input");
	public By candidateStatusInputField = By.xpath("//label[text()='Candidate Status']/following-sibling::div/div/div/input");
	public By untaggedRadioButton = By.xpath("//input[@value='N']");
	public By taggedRadioButton = By.xpath("//input[@value='Y']");
	public By automatchedRadioButton = By.xpath("//input[@value='Automatched']");
	public By popUpElement = By.xpath("//md-toast/div/span");
	public By cancelButton = By.xpath("//button[contains(@ng-click,'redirectTOHome')]/span | //button[contains(text(),'Cancel')] | //div[contains(@class,'search-actions')]/div/button[2]");
	public By workdayCandidateStatus = By.xpath("//label[contains(text(),'Workday Candidate Status')]/following-sibling::div/div/div/input | //label[contains(text(),'Workday Candidate Status')]/following-sibling::div/div/div/input//label[contains(text(),'Workday Candidate Status')]/following-sibling::div/select");
	public By workdaySpireStatus = By.xpath("//label[contains(text(),'Spire Status')]/following-sibling::div/div/div/input");

	
	public WebElement getAdvanceSearchLabel() {
		//cu.explicitWait(driver, advanceSearchLabel, 25L);
		cu.explicitWait(driver, advanceSearchLabel);
		return driver.findElement(By.xpath("advanceSearchLabel"));
	}

	public WebElement getVerticalScrollInner() {
		//cu.explicitWait(driver, verticalScrollInner, 50L);
		cu.explicitWait(driver, verticalScrollInner);
		return driver.findElement(verticalScrollInner);

	}

	public void clickSearchButton() throws InterruptedException {
		//cu.explicitWait(driver, searchButton, 25L);
		cu.explicitWait(driver, searchButton);
		this.driver.findElement(searchButton).click();
	}
	
	public String getToastMessage(){
		//cu.explicitWait(driver, popUpElement, 30L);
		cu.explicitWait(driver, popUpElement);
		return this.driver.findElement(popUpElement).getText();
	}
	
	public void clickCancelButton(){
		try {
			//cu.explicitWait(driver, cancelButton, 30L);
			cu.explicitWait(driver, cancelButton);
			driver.findElement(cancelButton).click();
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, cancelButton, 30L);
			cu.explicitWait(driver, cancelButton);
			driver.findElement(cancelButton).click();
		}
	}
	
	public void enterSkill(String testdata) throws InterruptedException{
		//cu.explicitWait(driver, skillInputField, 60L);
		cu.explicitWait(driver, skillInputField);
		driver.findElement(skillInputField).sendKeys(testdata);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void enterOperator(String op) throws InterruptedException{
		//cu.explicitWait(driver, skillInputField, 60L);
		cu.explicitWait(driver, skillInputField);
		driver.findElement(skillInputField).sendKeys(op);
		Thread.sleep(2000);
//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.ENTER).build().perform();
		this.driver.findElement(By.xpath("//a/span[contains(text(),'"+op+"')]")).click();
	}
	
	public void enterPrimarySkill(String testdata) throws InterruptedException{
		//cu.explicitWait(driver, primarySkillInputField, 60L);
		cu.explicitWait(driver, primarySkillInputField);
		driver.findElement(primarySkillInputField).sendKeys(testdata);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void enterOperatorPrimarySkill(String op) throws InterruptedException{
		//cu.explicitWait(driver, primarySkillInputField, 60L);
		cu.explicitWait(driver, primarySkillInputField);
		driver.findElement(primarySkillInputField).sendKeys(op);
		Thread.sleep(2000);
		this.driver.findElement(By.xpath("//a/span[contains(text(),'"+op+"')]")).click();
	}
	
	public void enterLocation(String testdata) throws InterruptedException{
		//cu.explicitWait(driver, locationInputField, 60L);
		cu.explicitWait(driver, locationInputField);
		driver.findElement(locationInputField).sendKeys(testdata);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void enterOperatorLocation(String op) throws InterruptedException{
		//cu.explicitWait(driver, locationInputField, 60L);
		cu.explicitWait(driver, locationInputField);
		driver.findElement(locationInputField).sendKeys(op);
		Thread.sleep(2000);
		this.driver.findElement(By.xpath("//a/span[contains(text(),'"+op+"')]")).click();
	}
	
	public void enterFirstPreferLocation(String testdata) throws InterruptedException{
		//cu.explicitWait(driver, firstPreferredLocationInputField, 60L);
		cu.explicitWait(driver, firstPreferredLocationInputField);
		driver.findElement(firstPreferredLocationInputField).sendKeys(testdata);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void enterOperatorFirstPreferLocation(String op) throws InterruptedException{
		//cu.explicitWait(driver, firstPreferredLocationInputField, 60L);
		cu.explicitWait(driver, firstPreferredLocationInputField);
		driver.findElement(firstPreferredLocationInputField).sendKeys(op);
		Thread.sleep(2000);
		this.driver.findElement(By.xpath("//a/span[contains(text(),'"+op+"')]")).click();
	}
	
	public void enterJobTitle(String testdata) throws InterruptedException{
		//cu.explicitWait(driver, jobTitleInputField, 60L);
		cu.explicitWait(driver, jobTitleInputField);
		driver.findElement(jobTitleInputField).sendKeys(testdata);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void enterOperatorJobTitle(String op) throws InterruptedException{
		//cu.explicitWait(driver, jobTitleInputField, 60L);
		cu.explicitWait(driver, jobTitleInputField);
		driver.findElement(jobTitleInputField).sendKeys(op);
		Thread.sleep(2000);
		this.driver.findElement(By.xpath("//a/span[contains(text(),'"+op+"')]")).click();
	}

}
