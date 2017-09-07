package com.guarented.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.spire.base.controller.Logging;


public class CandidateSearchPage extends CommonHeader {
	
	CommonUtils commonUtils = null;

	public CandidateSearchPage(WebDriver driver) {
		super(driver);
	}
	
	protected By fullNameTextBox=By.id("firstName");
	protected By searchButton=By.id("button");
	protected By automatchedCheckbox=By.xpath("//label[@for='automatched']/span");
	protected By automatchButton=By.id("automatchbutton");
	
	public void enterFullName(String name){
		driver.findElement(fullNameTextBox).sendKeys(name);
	}
	
	public void clickSearchButton(){
		driver.findElement(searchButton).click();
	}
	
	public void clickAutomatchedCheckbox(){
		commonUtils=new CommonUtils();
    	commonUtils.explicitWait(driver,automatchedCheckbox,10L );
		driver.findElement(automatchedCheckbox).click();
		Logging.log("Automatched checkbox is clicked.");
	}
	
	public void clickAutomatchReportButton(){
		commonUtils=new CommonUtils();
    	commonUtils.explicitWait(driver,automatchButton,10L );
		driver.findElement(automatchButton).click();
		Logging.log("Automatched Button is clicked.");
	}

}
