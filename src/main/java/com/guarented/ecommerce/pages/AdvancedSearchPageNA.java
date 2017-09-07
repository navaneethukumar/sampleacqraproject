package com.guarented.ecommerce.pages;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.driver.Driver;

/**
 * @author Radharani Patra 20/07/2016
 */
public class AdvancedSearchPageNA extends Driver {

	public AdvancedSearchPageNA(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	CommonUtils cu = new CommonUtils();
	//String enteredLoc=SpireConstants.LOCATION;
	
	protected By advancedSearchLabel = By.xpath("//label[contains(text(),'Advanced Search')]");
	protected By skillsLabel = By.xpath("//label[contains(text(),'Skills')]");
	protected By skillsField = By.id("js-fidSkill");
	protected By locationLabel = By.xpath("//label[contains(text(),'Location')]");
	protected By locationField = By.id("js-fidCpLoc");
	protected By experienceLabel = By.xpath("//label[contains(text(),'Experience (In Years)')]");
	protected By experienceField = By.id("js-fidExperience");
	protected By jobTitleLabel = By.xpath("//label[contains(text(),'Job Title')]");
	protected By jobTitleField = By.id("js-fidrole");
	protected By instituteLabel = By.xpath("//label[contains(text(),'Institute')]");
	protected By instituteField = By.id("js-fidInstitute");
	protected By sourceTypesourceNameLabel = By.xpath("//label[contains(text(),'Source Type/Source Name')]");
	protected By sourceTypeField = By.id("fidSourceType");
	protected By sourceNameField = By.id("fidSourceName");
	protected By employerLabel = By.xpath("//label[contains(text(),'Employer')]");
	protected By employerField = By.id("js-fidEmployer");
	protected By educationLabel = By.xpath("//label[contains(text(),'Education')]");
	protected By educationField = By.id("js-fidEducationLevel");
	protected By ATASIDLabel = By.xpath("//label[contains(text(),'ATAS Id')]");
	protected By ATASIDField = By.id("js-abacusId");
	protected By searchButton = By.id("adSearchBtn");
	protected By cancelButton = By.id("adSearchClose");
	protected By selectFirstSkillFromDropdown = By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']/li/a/span[1]");
	//protected By selectFirstLocation=By.xpath("(//li[contains(@id,'ui-id-')]/a)[1]");
	
	//protected By selectFirstLocation=By.xpath("//span[contains(text(),'"+enteredLoc+"')]");

	public void enterSkill(String skill){
		cu.explicitWait(driver, skillsField, 10L);
		this.driver.findElement(skillsField).sendKeys(skill);
		Logging.log("skill entered: "+skill);
	}

	
	public void clickSkill(String skill) throws InterruptedException{
		Thread.sleep(2000);
		this.driver.findElement(selectFirstSkillFromDropdown).click();
		//this.driver.findElement(By.xpath("//span[text()='"+skill+"']")).click();
		Logging.log("skill selected: "+skill);
	}
	
	public void enterOperator(String operator){
		cu.explicitWait(driver, skillsField, 10L);
		this.driver.findElement(skillsField).sendKeys(operator);
		Logging.log("Operator entered: "+operator);
	}
	
	public void clickoperator(String operator) throws InterruptedException{
		Thread.sleep(2000);
		this.driver.findElement(selectFirstSkillFromDropdown).click();
		//this.driver.findElement(By.xpath("//span[text()='"+skill+"']")).click();
		Logging.log("Operator selected: "+operator);
	}
	
	public void clickSearchButton(){
		cu.explicitWait(driver, searchButton, 10L);
		this.driver.findElement(searchButton).click();
		Logging.log("Search button clicked");
	}

   public void enterExp(String expYrs){
		driver.findElement(experienceField).sendKeys(expYrs);
	}
	public void enterAtasId(String atasId){
		this.driver.findElement(ATASIDField).sendKeys(atasId);
		Logging.log("Atas Id entered: "+atasId);
	}
	public void enterLocation(String location){
		driver.findElement(locationField).sendKeys(location);
	}
	
	public void selectLocation(String location) throws InterruptedException{
		
		enterLocation(location);
		Thread.sleep(2000);
		//System.out.println("xpath : "+selectFirstLocation);
		//driver.findElement(selectFirstLocation).click();
		
		/*writing xpath here to make it dynamic as per searched item*/
		driver.findElement(By.xpath("//span[contains(text(),'"+location+"')]")).click();
	}
	
}
