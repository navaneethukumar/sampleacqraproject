package com.guarented.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CandidateDetailsPage extends CommonHeader{

	public CandidateDetailsPage(WebDriver driver) {
		super(driver);		
	}
	
	protected By employerLabel = By.xpath("//div[contains(text(),'Employer: ')]");
	protected By seeMoreLink=By.id("showMoreLink");
	
	public void getEmployerLabel(){
		driver.findElement(employerLabel);
	}
	public void clickSeeMoreLink(){
		driver.findElement(seeMoreLink).click();
	}

}
