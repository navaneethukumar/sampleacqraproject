package com.guarented.ecommerce.pageUtils;

import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.pages.AdvancedSearchPageNA;
import com.spire.base.controller.Logging;

/**
 * @author Radharani Patra 20/07/2016
 */
public class AdvancedSearchPageUtilsNA extends AdvancedSearchPageNA {

	public AdvancedSearchPageUtilsNA(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	CommonUtils cu = new CommonUtils();

	
	/**verify navigation to advanced search page*/
	public void verifyNavigationToAdvancedSearchPage() {
		cu.isElementPreset(advancedSearchLabel, driver);
		Logging.log("Navigated to AdvancedSearch Page");

	}

	/**Verify all the fields present in Advanced search page*/
	public void verifyAllFields() {
		cu.isElementPreset(skillsLabel, driver);
		cu.isElementPreset(skillsField, driver);
		Logging.log("Skills Field is displayed");
		cu.isElementPreset(locationLabel, driver);
		cu.isElementPreset(locationField, driver);
		Logging.log("Location Field is displayed");
		cu.isElementPreset(experienceLabel, driver);
		cu.isElementPreset(experienceField, driver);
		Logging.log("Experience Field is displayed");
		cu.isElementPreset(jobTitleLabel, driver);
		cu.isElementPreset(jobTitleField, driver);
		Logging.log("Job Title Field is displayed");
		cu.isElementPreset(instituteLabel, driver);
		cu.isElementPreset(instituteField, driver);
		Logging.log("Institute Field is displayed");
		cu.isElementPreset(sourceTypesourceNameLabel, driver);
		cu.isElementPreset(sourceTypeField, driver);
		cu.isElementPreset(sourceNameField, driver);
		Logging.log("source typeNName is displayed");
		cu.isElementPreset(employerLabel, driver);
		cu.isElementPreset(employerField, driver);
		Logging.log("Employer Field is displayed");
		cu.isElementPreset(educationLabel, driver);
		cu.isElementPreset(educationField, driver);
		Logging.log("Education Field is displayed");
		cu.isElementPreset(ATASIDLabel, driver);
		cu.isElementPreset(ATASIDField, driver);
		Logging.log("ATAS ID Field is displayed");
		cu.isElementPreset(searchButton, driver);
		cu.isElementPreset(cancelButton, driver);
		Logging.log("Search Button is displayed");
		Logging.log("Cancel Button is displayed");
	}

	/**Added 21/07/16
	 * @throws InterruptedException */
	public void enterMultipleSkillsWithOperator(String skill,String operator,String skill_two) throws InterruptedException{
		enterSkill(skill);
		clickSkill(skill);
		enterOperator(operator);
		clickoperator(operator);
		enterSkill(skill_two);
		clickSkill(skill_two);
		
	}
	
	public void searchByAtasId(String atasId) throws InterruptedException{
		enterAtasId(atasId);
	}
	public void clickCandidateSearch() throws InterruptedException{
		clickSearchButton();
		Thread.sleep(5000);
	}
	
}
