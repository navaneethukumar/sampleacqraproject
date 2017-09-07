package com.guarented.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.driver.Driver;

public class CandidatePoolPage extends Driver {
	protected By candidatePoolLabel=By.xpath("//label[contains(text(),'Candidate Pool')]");
	
public CandidatePoolPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

public void getCandidatePoolLabel(){
	driver.findElement(candidatePoolLabel);
}


}
