package com.guarented.ecommerce.pageUtils;

import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.pages.CandidateDetailsPage;
import com.spire.base.controller.Assertion;
import com.spire.base.controller.Logging;

public class CandidateDetailsUtils extends CandidateDetailsPage {
	CommonUtils cu=new CommonUtils();
	public CandidateDetailsUtils(WebDriver driver) {
		super(driver);
	}
	
	public void validateCandidateDetailsPage(){
		Logging.log("Checking employerLabel in Candidate Details Page Page");
		Assertion.assertTrue(cu.isElementPreset(employerLabel, driver),
				"employerLabel element is not present");
	}

}
