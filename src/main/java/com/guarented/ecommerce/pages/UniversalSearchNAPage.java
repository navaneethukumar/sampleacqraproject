package com.guarented.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.driver.Driver;
import com.spire.base.controller.Logging;

public class UniversalSearchNAPage extends Driver {

	public UniversalSearchNAPage(WebDriver driver) {
		super(driver);

	}

	CommonUtils commonUtils = new CommonUtils();
	protected By universalSearchfirstclick = By
			.xpath("//button[@id='searchFieldbtn']");
	protected By universalSearchsecondclick = By.id("searchField");
	protected By autoSuggestList = By.xpath("//ul[contains(@id,'ui-id')]/li");
	protected By searchButton = By.id("main-search");
	protected By selectsearchparameterbyskill = By.xpath("//span[.='java']");
	protected By checkbox_skill_select = By
			.xpath("//input[@id='facetCheck_skill_java']");
	protected By userDetails = By
			.xpath(".//*[@id='userProfile pull-right']/span[2]");
	protected By advancedSearchLink = By
			.xpath(".//*[@id='__blaze-root']/div/div/div[1]/div/div[3]/div[2]/button");

             //  vasista
	protected By firstCandidate=By.xpath("(.//*[@id='candidateInfo'])[1]");
	protected By shomeMoreLink=By.xpath(".//*[@id='showMoreLink']");
    protected By searchSkill1=By.xpath("//div[@id='showMore']");
	protected By lastpageofSearchresult=By.xpath(".//*[@id='__blaze-root']/div[2]/div/div[2]/div[3]/div[2]/ul/li[9]/a");
	
	
	
	
	public void getuniversalSearchfirstclick() {
		driver.findElement(universalSearchfirstclick).click();
	}

	public void getuniversalSearchsecondclick() {
		driver.findElement(universalSearchsecondclick).click();
	}

	public void typeInUniversalSearch(String searchString)
			throws InterruptedException {
		System.out.println("Typing in Universal search");
		driver.findElement(universalSearchsecondclick).sendKeys(searchString);
		Thread.sleep(5000);
	}

	public List<WebElement> getAutoSuggestListInUniversalSearch(
			String searchString) throws InterruptedException {

		typeInUniversalSearch(searchString);
		Thread.sleep(7000);

		return driver.findElements(autoSuggestList);
	}

	public void getsearchButton() {
		driver.findElement(searchButton).click();
	}

	public void getselectsearchparameterbyskill() {
		driver.findElement(selectsearchparameterbyskill).click();
		;

	}

	public Boolean getcheckbox_skill_select() {
		return driver.findElement(checkbox_skill_select).isSelected();
	}

	// vasista - Eleement is present
	public boolean isElementPreset(By object, WebDriver driver) {

		try {
			return driver.findElement(object).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	// vasista  21-07-2016
    
    public void clickCandidate() {
	     Logging.log("Clicking the first candidate");
	     this.driver.findElement(firstCandidate).click();
   }

    public void clickShomeMoreLink() {
	     Logging.log("Clicking the shome Moree in skill");
	     this.driver.findElement(shomeMoreLink).click();
   }
 // vasista  22-07-2016
    public void clickLastPage() {
	     Logging.log("Clicking the first candidate");
	     this.driver.findElement(lastpageofSearchresult).click();
  }

}
