package com.guarented.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.driver.Driver;

public class CommonHeader extends Driver{

	public CommonHeader(WebDriver driver) {
		super(driver);
	}
	
	protected By candidateManagementLink=By.xpath("//a[contains(text(),'Candidate Management')]");
	protected By candidateSearchLink=By.xpath("//a[contains(text(),'Candidate Management')]/following-sibling::ul/li[1]");
	protected By candidatePoolLink=By.xpath("//a[contains(text(),'Candidate Management')]/following-sibling::ul/li[2]");
	
	public void clickCandidateManagementLink(){
		driver.findElement(candidateManagementLink).click();
	}
	public void clickCandidateSearchLink(){
		driver.findElement(candidateSearchLink).click();
	}

}
