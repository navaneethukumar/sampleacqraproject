package com.guarented.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.driver.Driver;

public class UniversalSearch extends Driver {
	
	public UniversalSearch(WebDriver driver) {
		super(driver);
	}
	CommonUtils commonUtils = new CommonUtils();
	String searchParamaterType;
	
	public By universalSearchBar=By.id("search-bar-button");
	
	
	public By universalSearchBarTextField=By.xpath("//*[@id='headerColDiv']/div/div/div/div[2]/form/div/div/div/div/div/input | //*[@id='headerColDiv']/div/div/div/div[3]/form/div/div/div/div/div/input");
	public By universalSearchSelectSkill=By.xpath("//span[text()='Skill'])[1]");
	public By universalSearchSelectLocation=By.xpath("//span[text()='Location'])[1]");
	public By universalSearchSelectFullName=By.xpath("//span[text()='Full Name'])[1]");
	public By universalSearchSelectInstitute=By.xpath("//span[text()='Institute'])[1]");
	public By universalSearchSelectSourceName=By.xpath("//span[text()='Source Name'])[1]");
	public By universalSearchSelectEmployer=By.xpath("//span[text()='Employer'])[1]");
	public By universalSearchSelectDesignation=By.xpath("//span[text()='Designation'])[1]");
	
	public By universalSearchSelectSearchedParameter=By.xpath("//span[text()='"+searchParamaterType+"'])[1]");
	//public By universalSearchButton=By.id("search-btn");
	public By universalSearchButton=By.xpath("html/body/div[1]/div[1]/div/div/div/div[3]/form/button | //*[@id='headerColDiv']/div/div/div/div[2]/form/button | //*[@id='headerColDiv']/div/div/div/div[3]/form/button");
	
	public By searchedPamameterResultList=By.xpath("//*[contains(@id,'ui-select-choices-row-')]/a/div");
	public By searchedPamameterTypeList=By.xpath("//*[contains(@id,'ui-select-choices-row-')]/a/div/span[2]");
	// Added For Free TextSearch:@Ganesh
	public By ResumeSearch = By.xpath("//*[@id='crmCandProfileList']/ul/div[4]/input");

	
	public WebElement getUniversalSearchBar(){
    	//commonUtils.explicitWait(driver, universalSearchBar, 50L);
    	commonUtils.explicitWait(driver, universalSearchBar);
    	return this.driver.findElement(universalSearchBar);
    }
    
    public WebElement getUniversalSearchBarTextField(){
    	//commonUtils.explicitWait(driver, universalSearchBarTextField, 30L);
    	commonUtils.explicitWait(driver, universalSearchBarTextField);
    	return this.driver.findElement(universalSearchBarTextField);
    }
     
    public void clickSearchedParameterType(String searchParameterType){
    	//commonUtils.explicitWait(driver, universalSearchSelectSearchedParameter, 30L);
    	commonUtils.explicitWait(driver, universalSearchSelectSearchedParameter);
    	this.driver.findElement(universalSearchSelectSearchedParameter).click();
    }
   
    public void clickUniversalSearchButton(){
    	this.driver.findElement(universalSearchButton).click();
    	}
    
    public List<WebElement> getSearchedPamameterResultList() throws InterruptedException{
    	//commonUtils.explicitWait(driver, searchedPamameterResultList, 30L);
    	commonUtils.explicitWait(driver, searchedPamameterResultList);
		return driver.findElements(searchedPamameterResultList);
	}
    
   public List<WebElement> getSearchedPamameterTypeList() throws InterruptedException{
	    //commonUtils.explicitWait(driver, searchedPamameterTypeList, 30L);
	    commonUtils.explicitWait(driver, searchedPamameterTypeList);
		return driver.findElements(searchedPamameterTypeList);
	}
	
	
}
