package com.guarented.ecommerce.pageUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.pages.AdvanceSearchPage;
import com.spire.base.controller.Logging;

public class AdvancedSearchPageUtils extends AdvanceSearchPage {

	public AdvancedSearchPageUtils(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	CommonUtils cu = new CommonUtils();

	public void validateAdvanceSearchPage() throws InterruptedException {
		//cu.explicitWait(driver, advanceSearchLabel, 30L);
		cu.explicitWait(driver, advanceSearchLabel);
		Assert.assertTrue(cu.isElementPreset(advanceSearchLabel, driver),
				"advanceSearchLabel not found means Advance Search Page is not loaded");
		Assert.assertTrue(cu.isElementPreset(labelSkill, driver),
				"Skill Label not found means Advance Search Page is not loaded");
		/*
		 * scrollVerticalBar(220);
		 * Assert.assertTrue(cu.isElementPreset(labelCandidateID, driver),
		 * "Candiadte Label ID not found means Advance Search Page is not loaded"
		 * ); Assert.assertTrue(cu.isElementPreset(searchButton, driver),
		 * "searchbutton not found means Advance Search Page is not loaded ");
		 */
		System.out.println("Validated Advanced Search Page");
	}

	public void scrollVerticalBar(int pix) {
		System.out.println(getVerticalScrollInner().isDisplayed());
		// making scroll bar active by clicking in innner part of window i.e.
		// clicking on label skill
		//cu.explicitWait(driver, labelSkill, 50L);
		cu.explicitWait(driver, labelSkill);
		driver.findElement(labelSkill).click();
		System.out.println(getVerticalScrollInner().isDisplayed());
		Actions action = new Actions(driver);
		action.clickAndHold(getVerticalScrollInner()).moveByOffset(0, pix).release().perform();

	}

	public Boolean validateAllLabels(TestDataSet testData) {
		int i, j;
		Boolean flag = false;
		//cu.explicitWait(driver, getAllLabels, 30L);
		cu.explicitWait(driver, getAllLabels);
		String[] label = testData.getvalue1().split("-");
		for (i = 0; i < label.length; i++) {
			System.out.println(label[i]);
		}
		List<WebElement> list = driver.findElements(getAllLabels);
		System.out.println(list.size());
		for (j = 0; j < list.size(); j++) {
			System.out.println(list.get(j).getText());
		}

		if (label.length == list.size()) {
			for (int k = 0; k < list.size(); k++) {

				if (label[k].equalsIgnoreCase(list.get(k).getText())) {
					flag = true;
					Logging.log("Labels present in advanced search page :" + list.get(k).getText());
				}
			}
		}

		return flag;
	}

	public void validateAutoSuggestion() throws InterruptedException {
		//cu.explicitWait(driver, labelSkill, 30L);
		cu.explicitWait(driver, labelSkill);
		driver.findElement(labelSkill).click();
		Thread.sleep(2000);
		//cu.explicitWait(driver, getAllInputFields, 50L);
		cu.explicitWait(driver, getAllInputFields);
		List<WebElement> list = driver.findElements(getAllInputFields);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).sendKeys("a");
			//cu.explicitWait(driver, getAllAutoSuggestion, 50L);
			cu.explicitWait(driver, getAllAutoSuggestion);
			List<WebElement> ele = driver.findElements(getAllAutoSuggestion);
			System.out.println("***" + ele.size());

			try {
				for (int j = 0; j < ele.size(); j++) {
					System.out.println("******" + ele.get(j).getText());
					Thread.sleep(2000);
					Logging.log(ele.get(j).getText());
				}
			} catch (ElementNotVisibleException ex) {
				cu.moveVerticalScrollBar(driver, 100);
			}

		}

	}

	public void skillSearch(String testdata) throws InterruptedException {
		//cu.explicitWait(driver, skillInputField, 60L);
		cu.explicitWait(driver, skillInputField);
		driver.findElement(skillInputField).sendKeys(testdata);
		//cu.explicitWait(driver, By.xpath("//a[contains(@class,'ui-select-choices-row-inner')]"), 30L);
		cu.explicitWait(driver, By.xpath("//a[contains(@class,'ui-select-choices-row-inner')]"));
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();		
		ClickSearchButton();
	}

	public void candidateCitySearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, candidateCityInputField, 30L);
		cu.explicitWait(driver, candidateCityInputField);
		driver.findElement(candidateCityInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void candidateStateSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, candidateStateInputField, 30L);
		cu.explicitWait(driver, candidateStateInputField);
		driver.findElement(candidateStateInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void candidateCountrySearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, candidateCountryInputField, 30L);
		cu.explicitWait(driver, candidateCountryInputField);
		driver.findElement(candidateCountryInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void locationSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, locationInputField, 30L);
		cu.explicitWait(driver, locationInputField);
		driver.findElement(locationInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void experienceRangeSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, frmExperienceYears, 30L);
		cu.explicitWait(driver, frmExperienceYears);
		new Select(driver.findElement(frmExperienceYears)).selectByVisibleText(testdata.getvalue1());
		//cu.explicitWait(driver, frmExperienceMonths, 30L);
		cu.explicitWait(driver, frmExperienceMonths);
		new Select(driver.findElement(frmExperienceMonths)).selectByVisibleText(testdata.getvalue2());
		//cu.explicitWait(driver, toExperienceYears, 30L);
		cu.explicitWait(driver, toExperienceYears);
		new Select(driver.findElement(toExperienceYears)).selectByVisibleText(testdata.getvalue3());
		//cu.explicitWait(driver, toExperienceMonths, 30L);
		cu.explicitWait(driver, toExperienceMonths);
		new Select(driver.findElement(toExperienceMonths)).selectByVisibleText(testdata.getvalue4());
		ClickSearchButton();

	}

	public void jobTitleSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, jobTitleInputField, 30L);
		cu.explicitWait(driver, jobTitleInputField);
		driver.findElement(jobTitleInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void instituteSearch(TestDataSet testdata) throws InterruptedException {
		cu.explicitWait(driver, instituteInputField, 30L);
		cu.explicitWait(driver, instituteInputField);
		driver.findElement(instituteInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void sourceTypeSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, sourceTypeInputField, 30L);
		cu.explicitWait(driver, sourceTypeInputField);
		driver.findElement(sourceTypeInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void sourceNameSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, sourceNameInputField, 30L);
		cu.explicitWait(driver, sourceNameInputField);
		driver.findElement(sourceNameInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		ClickSearchButton();
		
	}

	public void employerSearch(TestDataSet testdata, String customerName) throws InterruptedException {
		if (customerName.equalsIgnoreCase("AccentureNA") || customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccenturePhilTech") || customerName.equalsIgnoreCase("AccentureIndOps") || customerName.equalsIgnoreCase("IDC") ) {
			//cu.explicitWait(driver, employerInputField, 30L);
			cu.explicitWait(driver, employerInputField);
			driver.findElement(employerInputField).sendKeys(testdata.getvalue1());
		} else {
			try {
				//cu.explicitWait(driver, companyInputField, 30L);
				cu.explicitWait(driver, companyInputField);
				driver.findElement(companyInputField).sendKeys(testdata.getvalue1());
			} catch (ElementNotVisibleException e) {
				driver.findElement(labelSkill).click();
				cu.moveVerticalScrollBar(driver, 500);
				//cu.explicitWait(driver, companyInputField, 30L);
				cu.explicitWait(driver, companyInputField);
				driver.findElement(companyInputField).sendKeys(testdata.getvalue1());
			}
		}
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void educationSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, educationInputField, 30L);
			cu.explicitWait(driver, educationInputField);
			driver.findElement(educationInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, educationInputField, 30L);
			cu.explicitWait(driver, educationInputField);
			driver.findElement(educationInputField).sendKeys(testdata.getvalue1());
		}
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void lastModifiedDateSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, lastModifiedFromInputField, 30L);
			cu.explicitWait(driver, lastModifiedFromInputField);
			driver.findElement(lastModifiedFromInputField).sendKeys(testdata.getvalue1());
			Thread.sleep(1000);
			//cu.explicitWait(driver, lastModifiedToInputField, 30L);
			cu.explicitWait(driver, lastModifiedToInputField);
			driver.findElement(lastModifiedToInputField).sendKeys(testdata.getvalue2());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, lastModifiedFromInputField, 30L);
			cu.explicitWait(driver, lastModifiedFromInputField);
			driver.findElement(lastModifiedFromInputField).sendKeys(testdata.getvalue1());
			Thread.sleep(1000);
			//cu.explicitWait(driver, lastModifiedToInputField, 30L);
			cu.explicitWait(driver, lastModifiedToInputField);
			driver.findElement(lastModifiedToInputField).sendKeys(testdata.getvalue2());
		}
		Thread.sleep(2000);
		ClickSearchButton();
	}

	public void createdDateSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, createFromInputField, 30L);
			cu.explicitWait(driver, createFromInputField);
			driver.findElement(createFromInputField).sendKeys(testdata.getvalue1());
			Thread.sleep(1000);
			//cu.explicitWait(driver, createToInputField, 30L);
			cu.explicitWait(driver, createToInputField);
			driver.findElement(createToInputField).sendKeys(testdata.getvalue2());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, createFromInputField, 30L);
			cu.explicitWait(driver, createFromInputField);
			driver.findElement(createFromInputField).sendKeys(testdata.getvalue1());
			Thread.sleep(1000);
			//cu.explicitWait(driver, createToInputField, 30L);
			cu.explicitWait(driver, createToInputField);
			driver.findElement(createToInputField).sendKeys(testdata.getvalue2());
		}
		Thread.sleep(2000);
		ClickSearchButton();
	}

	public void candidateIdSearch(TestDataSet testdata, String customerName) throws InterruptedException {
		if (customerName.equalsIgnoreCase("AccentureNA")) {
			try {
				//cu.explicitWait(driver, candidateId_NA, 30L);
				cu.explicitWait(driver, candidateId_NA);
				driver.findElement(candidateId_NA).sendKeys(testdata.getvalue1());
			} catch (ElementNotVisibleException e) {
				driver.findElement(labelSkill).click();
				cu.moveVerticalScrollBar(driver, 500);
				//cu.explicitWait(driver, candidateId_NA, 30L);
				cu.explicitWait(driver, candidateId_NA);
				driver.findElement(candidateId_NA).sendKeys(testdata.getvalue1());
			}
		} else {
			try {
				//cu.explicitWait(driver, candidateId, 30L);
				cu.explicitWait(driver, candidateId);
				driver.findElement(candidateId).sendKeys(testdata.getvalue1());
			} catch (ElementNotVisibleException e) {
				driver.findElement(labelSkill).click();
				cu.moveVerticalScrollBar(driver, 500);
				//cu.explicitWait(driver, candidateId, 30L);
				cu.explicitWait(driver, candidateId);
				driver.findElement(candidateId).sendKeys(testdata.getvalue1());
			}
		}
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		ClickSearchButton();
	}

	public void primarySkillSearch(TestDataSet testdata) throws InterruptedException {
		cu.explicitWait(driver, primarySkillInputField, 50L);
		driver.findElement(primarySkillInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		/*driver.findElement(automatchedRadioButton).click();
		cu.moveVerticalScrollBar(driver, 500);
		automatchedSearch(testdata);*/		
		Thread.sleep(5000);
		ClickSearchButton();
		

	}

	public void firstPreferredLocationSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, firstPreferredLocationInputField, 50L);
		cu.explicitWait(driver, firstPreferredLocationInputField);
		driver.findElement(firstPreferredLocationInputField).sendKeys(testdata.getvalue1());
		//cu.explicitWait(driver, suggestDropdown, 50L);
		cu.explicitWait(driver, suggestDropdown);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		ClickSearchButton();

	}

	public void statusGroupSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, statusGroupInputField, 30L);
			cu.explicitWait(driver, statusGroupInputField);
			driver.findElement(statusGroupInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, statusGroupInputField, 30L);
			cu.explicitWait(driver, statusGroupInputField);
			driver.findElement(statusGroupInputField).sendKeys(testdata.getvalue1());
		}
		//cu.explicitWait(driver, suggestDropdown, 50L);
		cu.explicitWait(driver, suggestDropdown);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		ClickSearchButton();

	}

	public void fullNameSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, fullNameInputField, 30L);
			cu.explicitWait(driver, fullNameInputField);
			driver.findElement(fullNameInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, fullNameInputField, 30L);
			cu.explicitWait(driver, fullNameInputField);
			driver.findElement(fullNameInputField).sendKeys(testdata.getvalue1());
		}
		//cu.explicitWait(driver, suggestDropdown, 50L);
		cu.explicitWait(driver, suggestDropdown);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		ClickSearchButton();

	}

	public void sRNOSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, sRNOInputField, 30L);
			cu.explicitWait(driver, sRNOInputField);
			driver.findElement(sRNOInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, sRNOInputField, 30L);
			cu.explicitWait(driver, sRNOInputField);
			driver.findElement(sRNOInputField).sendKeys(testdata.getvalue1());
		}
		//cu.explicitWait(driver, suggestDropdown, 50L);
		cu.explicitWait(driver, suggestDropdown);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		ClickSearchButton();

	}

	public void numberSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, numberInputField, 30L);
			cu.explicitWait(driver, numberInputField);
			driver.findElement(numberInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, numberInputField, 30L);
			cu.explicitWait(driver, numberInputField);
			driver.findElement(numberInputField).sendKeys(testdata.getvalue1());
		}
		ClickSearchButton();

	}

	public void emailIdSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, emailInputField, 30L);
			cu.explicitWait(driver, emailInputField);
			driver.findElement(emailInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, emailInputField, 30L);
			cu.explicitWait(driver, emailInputField);
			driver.findElement(emailInputField).sendKeys(testdata.getvalue1());
		}
		ClickSearchButton();

	}

	public void abacusStatusSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, abacusStatusInputField, 30L);
			cu.explicitWait(driver, abacusStatusInputField);
			driver.findElement(abacusStatusInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, abacusStatusInputField, 30L);
			cu.explicitWait(driver, abacusStatusInputField);
			driver.findElement(abacusStatusInputField).sendKeys(testdata.getvalue1());
		}
		//cu.explicitWait(driver, suggestDropdown, 50L);
		cu.explicitWait(driver, suggestDropdown);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		ClickSearchButton();

	}
	
	public void CandidateStatusSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, abacusStatusInputField, 30L);
			cu.explicitWait(driver, candidateStatusInputField);
			driver.findElement(candidateStatusInputField).sendKeys(testdata.getvalue1());
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, abacusStatusInputField, 30L);
			cu.explicitWait(driver, candidateStatusInputField);
			driver.findElement(candidateStatusInputField).sendKeys(testdata.getvalue1());
		}
		//cu.explicitWait(driver, suggestDropdown, 50L);
		cu.explicitWait(driver, suggestDropdown);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		ClickSearchButton();

	}
	public void untaggedSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, untaggedRadioButton, 30L);
			cu.explicitWait(driver, untaggedRadioButton);
			driver.findElement(untaggedRadioButton).click();
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, untaggedRadioButton, 30L);
			cu.explicitWait(driver, untaggedRadioButton);
			driver.findElement(untaggedRadioButton).click();
		}
		ClickSearchButton();

	}

	public void taggedSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, taggedRadioButton, 30L);
			cu.explicitWait(driver, taggedRadioButton);
			driver.findElement(taggedRadioButton).click();
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, taggedRadioButton, 30L);
			cu.explicitWait(driver, taggedRadioButton);
			driver.findElement(taggedRadioButton).click();
		}
		ClickSearchButton();

	}

	public void automatchedSearch(TestDataSet testdata) throws InterruptedException {
		try {
			//cu.explicitWait(driver, automatchedRadioButton, 30L);
			cu.explicitWait(driver, automatchedRadioButton);
			driver.findElement(automatchedRadioButton).click();
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, automatchedRadioButton, 30L);
			cu.explicitWait(driver, automatchedRadioButton);
			driver.findElement(automatchedRadioButton).click();
		}
		ClickSearchButton();

	}

	public void ClickSearchButton() throws InterruptedException {
		System.out.println("Enter");
		Logging.log("Click Search Button after filling all data in Advanced Search.");
		try {
			//cu.explicitWait(driver, searchButtonAdvancedSearch, 30L);
			
			cu.explicitWait(driver, searchButtonAdvancedSearch);			
			//((JavascriptExecutor) driver).executeScript("return window.stop");			
			JavascriptExecutor js =(JavascriptExecutor) driver;
		    js.executeScript("document.querySelector(\"[type='submit'][class='btn btn-small spire-btn-orange ng-binding']\").click();");
			System.out.println("Clicked");
		    //driver.findElement(searchButtonAdvancedSearch).click();
						
		} catch (ElementNotVisibleException e) {
			
			System.out.println("in catch");
			driver.findElement(labelSkill).click();			
			cu.moveVerticalScrollBar(driver, 500);
			
			//cu.explicitWait(driver, searchButtonAdvancedSearch, 30L);
			//Assert.assertEquals(true, ((WebElement) searchButtonAdvancedSearch).isDisplayed());
			cu.explicitWait(driver, searchButtonAdvancedSearch);
			driver.findElement(searchButtonAdvancedSearch).click();
		}
		Logging.log("Validating result in candidate list page.");
	}

	public void searchMultipleSkills(String skillOne, String operator, String skillTwo) throws InterruptedException {
		
		enterSkill(skillOne);
		enterOperator(operator);
		enterSkill(skillTwo);
		ClickSearchButton();
	}
	public void searchMultipleSkillsinFIL(String skillOne, String operator, String skillTwo) throws InterruptedException {
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div/form/div[1]/div/div/div/div/span/span/span/span[2]/span[2]")).click();
		enterSkill(skillOne);
		enterOperator(operator);
		enterSkill(skillTwo);
		ClickSearchButton();
	}
	public void selectWorkdayCandidateStatus(String testdata) throws InterruptedException {
		//cu.explicitWait(driver, workdayCandidateStatus, 30L);
		cu.explicitWait(driver, workdayCandidateStatus);
		/*Select sel = new Select(driver.findElement(workdayCandidateStatus));
		sel.selectByVisibleText(testdata);*/
		driver.findElement(workdayCandidateStatus).sendKeys(testdata);
		cu.explicitWait(driver, By.xpath("//a[contains(@class,'ui-select-choices-row-inner')]"));
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		ClickSearchButton();
	}
	
	public void selectSpireStatus(String testdata) throws InterruptedException {
		//cu.explicitWait(driver, workdaySpireStatus, 30L);
		cu.explicitWait(driver, workdaySpireStatus);
		/*Select sel = new Select(driver.findElement(workdaySpireStatus));
		sel.selectByVisibleText(testdata);*/
		driver.findElement(workdaySpireStatus).sendKeys(testdata);
		cu.explicitWait(driver, By.xpath("//a[contains(@class,'ui-select-choices-row-inner')]"));
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();	
		ClickSearchButton();
	}
	
	public void searchMultiplePrimarySkills(String skillOne, String operator, String skillTwo) throws InterruptedException {
		enterPrimarySkill(skillOne);
		Thread.sleep(10000);
		enterOperatorPrimarySkill(operator);
		Thread.sleep(10000);
		enterPrimarySkill(skillTwo);
		ClickSearchButton();
	}
	
	public void searchMultipleLocation(String skillOne, String operator, String skillTwo) throws InterruptedException {
		enterLocation(skillOne);
		enterOperatorLocation(operator);
		enterLocation(skillTwo);
		ClickSearchButton();
	}
	
	public void searchMultipleFirstPreferLocation(String skillOne, String operator, String skillTwo) throws InterruptedException {
		enterFirstPreferLocation(skillOne);
		enterOperatorFirstPreferLocation(operator);
		enterFirstPreferLocation(skillTwo);
		ClickSearchButton();
	}
	
	public void searchMultipleJobTitle(String skillOne, String operator, String skillTwo) throws InterruptedException {
		enterJobTitle(skillOne);
		enterOperatorJobTitle(operator);
		enterJobTitle(skillTwo);
		ClickSearchButton();
	}
	
	public void skillSearch(TestDataSet testdata) throws InterruptedException {
		//cu.explicitWait(driver, skillInputField, 60L);
		cu.explicitWait(driver, skillInputField);
		driver.findElement(skillInputField).sendKeys(testdata.getvalue1());
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		try {
			//cu.explicitWait(driver, searchButtonAdvancedSearch, 30L);
			cu.explicitWait(driver, searchButtonAdvancedSearch);
			
			JavascriptExecutor js =(JavascriptExecutor) driver;
		    js.executeScript("document.querySelector(\"[type='submit'][class='btn btn-small spire-btn-orange ng-binding']\").click();");
			//driver.findElement(searchButtonAdvancedSearch).click();
		} catch (ElementNotVisibleException e) {
			driver.findElement(labelSkill).click();
			cu.moveVerticalScrollBar(driver, 500);
			//cu.explicitWait(driver, searchButtonAdvancedSearch, 30L);
			cu.explicitWait(driver, searchButtonAdvancedSearch);
			driver.findElement(searchButtonAdvancedSearch).click();
		}
		Thread.sleep(2000);
	}
	
	
}
