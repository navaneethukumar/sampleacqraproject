package com.guarented.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.spire.base.controller.Assertion;



public class CandidateListViewPageNA extends CommonHeader{
    CommonUtils cu =new CommonUtils();
	public CandidateListViewPageNA(WebDriver driver) {
		super(driver);
	}
	
	protected By firstCandidateNameLink=By.xpath("(.//*[@id='candidateInfoID'])[1]/div[2]/div[1]/a");
	protected By refineSearch = By.xpath("//div[text()='Refine Search']");
	protected By searchedCriteria = By.xpath(".//*[@id='__blaze-root']/div[2]/div/div[2]/div[1]/div[1]/span");
	protected By candidatename=By.xpath(".//*[@id='candidateInfo']");
	protected By employer=By.xpath(".//*[@id='candidateInfoID']/div[2]/div[2]/label");
	protected By id=By.xpath(".//*[@id='candidateInfoID']/div[2]/div[3]/label");
	protected By educationallevel=By.xpath(".//*[@id='candidateInfoID']/div[2]/div[4]/div/label");
	protected By jobtitle=By.xpath(".//*[@id='candidateInfoID']/div[2]/div[5]/div/label");
	protected By reqno=By.xpath(".//*[@id='reqnumId']");
	protected By downloadresume=By.xpath(".//*[@id='candidateInfoID']/div[2]/div[6]/div[2]/div");
	protected By emailId=By.xpath(".//*[@id='candidateInfoID_']/td[2]/div[1]/label");
	protected By phoneNo=By.xpath(".//*[@id='candidateInfoID_']/td[2]/div[2]/label");
	protected By lastmodifieddate=By.xpath(".//*[@id='candidateInfoID_']/td[2]/div[3]/label");
	protected By source=By.xpath(".//*[@id='candidateInfoID_']/td[2]/div[4]/div/label");
	protected By moreskills=By.xpath(".//*[@id='candidateInfoID_']/td[3]/div[2]");
	protected By riscore=By.xpath(".//*[@id='candidateInfoID_']/td[4]");
	protected By refinesearch=By.xpath(".//*[@id='__blaze-root']/div[2]/div/div[1]/div/div[1]/div");
	protected By skillslabel=By.xpath(".//*[@id='__blaze-root']/div[2]/div/div[1]/div/div[2]/div[1]/h4/div");
	protected By locationlabel=By.xpath(".//*[@id='__blaze-root']/div[2]/div/div[1]/div/div[3]/div[1]/h4/div");
	protected By image=By.xpath(".//*[@id='candidateInfoID']/div[1]/img");
	//protected By checkBoxListFacet=By.xpath("//div[contains(@id,'facet_')]/div/div[1]/label/span/img[2]");
	protected By checkBoxListFacet=By.xpath("//div[contains(@id,'facet_')]/div/div[1]/input");
	protected By dataListFacet=By.xpath("//div[contains(@id,'facet_')]/div/div[2]");
	protected By countListFacet=By.xpath("//div[contains(@id,'facet_')]/div/div[3]");
	
	
	public void clickFirstCandidateNameLink(){
		Assertion.assertTrue(cu.isElementPreset(firstCandidateNameLink, driver),"Candidate Name is not present");
		driver.findElement(firstCandidateNameLink).click();
		
	}
	
	public List<WebElement> getSearchedCriteriaList(){
		return driver.findElements(searchedCriteria);
	}
    
	public List<WebElement> getCheckBoxListInFacet(){
		return driver.findElements(checkBoxListFacet);
	}
	public List<WebElement> getDataListInFacet(){
		return driver.findElements(dataListFacet);
	}
    
	public List<WebElement> getCountListInFacet(){
		return driver.findElements(countListFacet);
	}
}
