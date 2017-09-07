package com.guarented.ecommerce.pageUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.pages.CandidateListViewPage;
import com.spire.base.controller.Assertion;
import com.spire.base.controller.Logging;
import com.thoughtworks.selenium.webdriven.commands.IsSomethingSelected;

public class CandidateListViewUtils extends CandidateListViewPage {

	CommonUtils cu = new CommonUtils();
	public String[] experiencevalue;
	private String Actualtext;

	public CandidateListViewUtils(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void verifyNavigationToCandidateListPage() {
		cu.isElementPreset(refineSearch, driver);
		Logging.log("Refine search is displayed");
	}

	public void verifySearchresultDivisionText(String skill_one, String op, String skill_two) {
		cu.isElementPreset(searchResultDivision, driver);
		String actualText = this.driver.findElement(searchResultDivision).getText();
		Assertion.assertTrue(actualText.contains(skill_one), skill_one + " not present");
		Assertion.assertTrue(actualText.contains(op), op + " not present");
		Assertion.assertTrue(actualText.contains(skill_two), skill_two + " not present");
	}

	public void verifySkillsCheckedInRefineSearch(String skill_one, String skill_two) {
		Assertion.assertTrue(this.driver.findElement(By.xpath("//input[@id='facetCheck_skill_java']")).isSelected(),
				"Skills are not selected");
	}

	public void verifySkillInSearchedCriteria(String skill) {

	}

	// 18-12-2016
	public void verifyResultAppears() {
		int candidateListSizeInOnePage = getCandidateList().size();
		System.out.println("Candidate List Size in one page : " + candidateListSizeInOnePage);
		Assert.assertTrue(candidateListSizeInOnePage >= 1, "Result did not appear");
	}

	public void validateResultBySkillCloud(String[] matchCloudArr) {

		int totalCandidateInPage = getCandidateCountInPage();
		System.out.println("totalCandidateInPage : " + totalCandidateInPage);
		List<WebElement> list = driver
				.findElements(By.xpath("//div[@ng-show='spireRi']/preceding-sibling::div[1]/div/div[1]"));
		for (int candidateIndex = 1; candidateIndex < totalCandidateInPage; candidateIndex++) {
			int count = 0;
			//System.out.println("Skill set other than More link for the candidate no. "+candidateIndex+" is :"+list.get(candidateIndex).getText());
			//System.out.println("Rahul : "+ driver
			//		.findElement(By.xpath("(//div[@ng-show='spireRi']/preceding-sibling::div[1]/div/div[1])["+candidateIndex+"]")).getText());
			String candidateExtractedSkillSet = getExtractedSkillSetforCandidate(candidateIndex) + ","
					+ list.get(candidateIndex).getText();
			
			//String candidateExtractedSkillSet = getExtractedSkillSetforCandidate(candidateIndex) + ","
			//		+driver.findElement(By.xpath("(//div[@ng-show='spireRi']/preceding-sibling::div[1]/div/div[1])["+candidateIndex+"]")).getText();
			System.out.println(">>>>>>>>>>" + candidateExtractedSkillSet);
			for (String cloudString : matchCloudArr) {

				if (candidateExtractedSkillSet.toLowerCase().contains(cloudString.toLowerCase())) {
					System.out.println("Cloud string " + cloudString + " found in Extracted Skill set :"
							+ candidateExtractedSkillSet + "for the Candidate no. " + candidateIndex + " ");
					count++;
				}
			}
			System.out.println("Cloud skill match count : " + count);
			Assert.assertEquals(count > 0, true,
					"Given Skill Cloud did not match for the Candidate no. " + candidateIndex + " ");
		}

	}

	/* Added by Radha */
	public void verifyCityResults(TestDataSet testData) {
		List<WebElement> list = driver.findElements(By.xpath("//span[@title='" + testData.getvalue2() + "']"));
		for (int i = 0; i < list.size(); i++) {
			Assert.assertTrue(list.get(i).getText().equalsIgnoreCase(testData.getvalue1()));
		}
	}

	public void validateBreadCrumbs(TestDataSet testData) throws InterruptedException {
		cu.explicitWait(driver, breadcrumbs, 30L);
		System.out.println(">>" + driver.findElement(breadcrumbs).getText());
		Thread.sleep(10000);
		Assert.assertTrue(driver.findElement(breadcrumbs).getText().equalsIgnoreCase(testData.getvalue1() + "." + testData.getvalue2() + "-" + testData.getvalue3() + "."
						+ testData.getvalue4() + " years")
				|| driver.findElement(breadcrumbs).getText().equalsIgnoreCase(testData.getvalue1()) || driver
						.findElement(breadcrumbs).getText().toLowerCase().contains(testData.getvalue1().toLowerCase()));
	}

	public void validateBreadCrumbsDate(TestDataSet testData) {
		cu.explicitWait(driver, breadcrumbs, 30L);
		System.out.println(">>" + driver.findElement(breadcrumbs).getText());
		String[] date = testData.getvalue1().split("-");
		String breadCrumbFromdate = date[2] + "-" + date[1] + "-" + date[0];
		System.out.println(breadCrumbFromdate);
		String[] date1 = testData.getvalue2().split("-");
		String breadCrumbTodate = date1[2] + "-" + date1[1] + "-" + date1[0];
		System.out.println(breadCrumbTodate);
		Assert.assertTrue(driver.findElement(breadcrumbs).getText().contains(breadCrumbFromdate)
				&& driver.findElement(breadcrumbs).getText().contains(breadCrumbTodate));
	}

	public String getNumberOfRecords() {
		//cu.explicitWait(driver, totalResultCount, 60L);
		cu.explicitWait(driver, totalResultCount);
		String res = driver.findElement(totalResultCount).getText();
		System.out.println("Result: " + res);
		String[] arr = res.split(" ");
		String count = arr[1];
		System.out.println("Count: " + count);
		return count;
	}

	public void verifyExperienceResults(TestDataSet testData) throws InterruptedException {
		cu.explicitWait(driver, experience, 30L);
		List<WebElement> list = driver.findElements(experience);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Total Experience: " + list.get(i).getAttribute("title"));
			String exp = list.get(i).getAttribute("title");
			String[] arr = exp.split(" ");
			System.out.println(">>>" + Float.parseFloat(arr[0]));
			Assert.assertTrue(Float.parseFloat(arr[0]) > Float.parseFloat(testData.getvalue1())
					&& Float.parseFloat(arr[0]) < Float.parseFloat(testData.getvalue3()));
		}
	}

	public void verifyJobTitle(TestDataSet testData, String customer) {
		cu.explicitWait(driver, candidateJobTitle, 30L);
		if (customer.equals("AccentureNA")) {
			List<WebElement> list = driver.findElements(candidateJobTitle);
			for (int i = 0; i < list.size(); i++) {
				Assert.assertTrue(list.get(1).getText().equalsIgnoreCase(testData.getvalue1()));
			}
		}
	}

	public void verifySource(TestDataSet testData) throws InterruptedException {
		cu.explicitWait(driver, source, 30L);
		List<WebElement> list = driver.findElements(source);
		//System.out.println(">>>" + list.get(0).getAttribute("title"));
		System.out.println(">>>" + list.get(0).getText());
	//	for (int i = 0; i < list.size()-2; i++) {
			System.out.println(">>>" + list.get(1).getText());
			Assert.assertTrue(list.get(1).getText().equalsIgnoreCase(testData.getvalue1()));
		
	}

	public void verifySourceType(TestDataSet testData) {
		cu.explicitWait(driver, sourceType, 30L);
		List<WebElement> list = driver.findElements(sourceType);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(">>>" + list.get(i).getAttribute("title"));
			Assert.assertTrue(list.get(i).getAttribute("title").equalsIgnoreCase(testData.getvalue1()));
		}
	}

	public void verifySourceName(TestDataSet testData) {
		cu.explicitWait(driver, sourceName, 30L);
		List<WebElement> list = driver.findElements(sourceName);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(">>>" + list.get(i).getAttribute("title"));
			Assert.assertTrue(list.get(i).getAttribute("title").equalsIgnoreCase(testData.getvalue1()));
		}
	}

	public void verifyEducationName(TestDataSet testData) {
		cu.explicitWait(driver, educationLevel, 30L);
		List<WebElement> list = driver.findElements(educationLevel);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(">>>" + list.get(i).getAttribute("title"));
			Assert.assertTrue(list.get(i).getAttribute("title").contains(testData.getvalue1()));
		}
	}

	public void verifyNumber(TestDataSet testData) throws InterruptedException {
		cu.explicitWait(driver, number, 30L);
		List<WebElement> list = driver.findElements(number);
		for (int i = 0; i < list.size(); i++) {			
			System.out.println(">>>" + list.get(i).getAttribute("title").trim());			
			Thread.sleep(10000);
			Assert.assertTrue(list.get(i).getAttribute("title").contains(testData.getvalue1()));
		}
	}

	public void verifyEmailId(TestDataSet testData) {
		cu.explicitWait(driver, emailId, 30L);
		List<WebElement> list = driver.findElements(emailId);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(">>>" + list.get(i).getAttribute("title"));
			Assert.assertTrue(list.get(i).getAttribute("title").contains(testData.getvalue1()));
		}
	}

	public void verifyAbacusStatus(TestDataSet testData) {
		cu.explicitWait(driver, abacusStatus, 30L);
		List<WebElement> list = driver.findElements(abacusStatus);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(">>>" + list.get(i).getAttribute("title"));
			Assert.assertTrue(
					list.get(i).getAttribute("title").toLowerCase().contains(testData.getvalue1().toLowerCase()));
		}
			}
	public void verifyCandidateStatus(TestDataSet testData) {
		cu.explicitWait(driver, CandidateStatus, 30L);
		List<WebElement> list = driver.findElements(CandidateStatus);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(">>>" + list.get(i).getAttribute("title"));
			Assert.assertTrue(
					list.get(i).getAttribute("title").toLowerCase().contains(testData.getvalue1().toLowerCase()));
		}
	}
	/* Added by sridhar */
	/*
	 * public void moveVerticalScrollBar(WebDriver driver, int pix ) throws
	 * InterruptedException{ Thread.sleep(3000); WebElement
	 * scrollbar=driver.findElement(By.xpath(
	 * ".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[3]/div"));
	 * 
	 * Actions action = new Actions(driver);
	 * action.clickAndHold(scrollbar).moveByOffset(0, pix).release().perform();
	 */
	/*
	 * Actions actions = new Actions(driver); WebElement refinesearch =
	 * driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
	 * actions.moveToElement(refinesearch);
	 */
	// WebElement lastmodifieddate =
	// driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[6]"));
	// actions.moveToElement(lastmodifieddate);
	// actions.click().build().perform();
	// Thread.sleep(3000);
	/*
	 * if (
	 * !driver.findElement(By.xpath(".//*[@id='facet_5']/li[1]")).isSelected() )
	 * { driver.findElement(By.xpath(".//*[@id='facet_5']/li[1]")).click();
	 * Thread.sleep(3000); }
	 * 
	 * }
	 */
	public void validateAutoSuggestionInUniversalSearch(String customerName, String searchString)
			throws InterruptedException {
		typeInUniversalSearch(customerName, searchString);
		List<WebElement> list = getSearchedPamameterResultList();
		System.out.println("Auto Suggest List size : " + list.size());
		for (int i = 0; i < list.size(); i++)
			System.out.println("list element is : " + list.get(i).getText());
		Logging.log("Asserting that auto suggest list is available or not");
		Assert.assertTrue(list.size() >= 1, "Auto Suggest List is not Available for : " + searchString);
		Logging.log("Verifying Auto Suggest List Data ");
		boolean flag;
		for (int i = 0; i < list.size(); i++) {
			flag = list.get(i).getText().toLowerCase().contains(searchString.toLowerCase());
			Assert.assertTrue(flag,
					"Auto suggest list is not as per expectation as searched element does not contain " + searchString);
		}
	}

	private List<WebElement> getSearchedPamameterResultList() {

		return null;
	}

	public void typeInUniversalSearch(String customerName, String searchParameter) {
		if (customerName.equalsIgnoreCase("FIL")) {
			// put customized action here
			driver.findElement(By.xpath(".//*[@id='search-bar-button']")).click();
			driver.findElement(By.xpath(".//*[@id='headerColDiv']/div/div/div/div[3]/form/div/div/div/div/div/input"));
			getUniversalSearchBar().click();
			getUniversalSearchBarTextField().sendKeys(searchParameter);
		} else {
			getUniversalSearchBar().click();
			getUniversalSearchBarTextField().sendKeys(searchParameter);
		}
	}

	private WebElement getUniversalSearchBarTextField() {
		return null;
	}

	private WebElement getUniversalSearchBar() {

		return null;
	}

	public void universalSearch(String customerName, String searchParameter, String searchParameterType) {
		if (customerName.equalsIgnoreCase("ABC")) {
			// put customized action here
		} else {
			typeInUniversalSearch(customerName, searchParameter);

			// getUniversalSearchBar().click();
			// getUniversalSearchBarTextField().sendKeys(searchParameter);

			cu.explicitWait(driver, By.xpath("(//span[text()='" + searchParameterType + "'])[1]"), 30L);
			driver.findElement(By.xpath("(//span[text()='" + searchParameterType + "'])[1]")).click();
			clickUniversalSearchButton();
		}
	}

	private void clickUniversalSearchButton() {

	}

	public void validateAutoSuggestListAppearsWithParamTypeUniversalSearch() throws InterruptedException {

		By searchedPamameterResultList = null;
		cu.explicitWait(driver, searchedPamameterResultList, 30L);
		List<WebElement> parameterTypeList = getSearchedPamameterTypeList();
		List<String> typeList = new ArrayList<String>();
		for (WebElement we : parameterTypeList) {
			typeList.add(we.getText());
		}
		boolean flag = false;

		if (typeList.contains("Skill") || typeList.contains("skill") || typeList.contains("Location")
				|| typeList.contains("Full Name") || typeList.contains("Institute") || typeList.contains("Source Name")
				|| typeList.contains("Employer") || typeList.contains("Designation"))

			flag = true;
		else {
			flag = false;
		}

		Assert.assertTrue(flag, "Parameter Type is not available with searched parameter in autosuggest List");
	}

	private List<WebElement> getSearchedPamameterTypeList() {
		return null;
	}

	/*
	 * public void downloadResume_VerifyDownloadWithFileExtension() {
	 * //driver.get(URL); driver.findElement(By.linkText(".csv")).click();
	 * Assert.assertTrue(isFileDownloaded_Ext(downloadPath, ".csv"),
	 * "Failed to download document which has extension .xls");
	 * System.out.println("Done"); }
	 */
	/*
	 * private boolean isFileDownloaded_Ext(String downloadPath, String string)
	 * { // TODO Auto-generated method stub return false; }
	 */
	/*
	 * public void checkskillinCandidateListView(String customerName ) throws
	 * InterruptedException{ if (
	 * !driver.findElement(By.xpath(".//*[@id='facet_0']/li[1]")).isSelected() )
	 * { driver.findElement(By.xpath(".//*[@id='facet_0']/li[1]")).click();
	 * Thread.sleep(3000); } }
	 */
	/*
	 * public void checklastModifiedinCandidateListView(String customerName )
	 * throws InterruptedException{ Actions actions = new Actions(driver);
	 * WebElement lastmodifieddate = driver.findElement(By.xpath(
	 * ".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[6]"));
	 * actions.moveToElement(lastmodifieddate);
	 * actions.click().build().perform(); Thread.sleep(3000); if (
	 * !driver.findElement(By.xpath(".//*[@id='facet_5']/li[1]")).isSelected() )
	 * { driver.findElement(By.xpath(".//*[@id='facet_5']/li[1]")).click();
	 * Thread.sleep(3000); } }
	 */
	public void checklastModifiedinCandidateListView(String customerName) {
		if (customerName.equalsIgnoreCase("FIL")){
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
		actions.moveToElement(refinesearch);
		WebElement lastmodifieddate = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[7]"));
		actions.moveToElement(lastmodifieddate);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_6']/li[2]/div/label/i[3]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_6']/li[2]/div/label/i[3]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else if (customerName.equalsIgnoreCase("AccenturePhilTech")){
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);
			WebElement lastmodifieddate = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[10]"));
			actions.moveToElement(lastmodifieddate);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[10]/div[2]/li[1]/div/label/i[3]")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[10]/div[2]/li[1]/div/label/i[3]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}else if (customerName.equalsIgnoreCase("IDC")){
				Actions actions = new Actions(driver);
				WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
				actions.moveToElement(refinesearch);
				WebElement lastmodifieddate = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[9]"));
				actions.moveToElement(lastmodifieddate);
				actions.click().build().perform();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[9]/div[2]/li[1]/div/label/i[3]")).isSelected()) {
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[9]/div[2]/li[1]/div/label/i[3]")).click();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
		else {
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);
			WebElement lastmodifieddate = driver
					.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[6]"));
			actions.moveToElement(lastmodifieddate);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_5']/li[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_5']/li[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}}
		
		}

/*	public void validateLastModifiedDate() {

		if (driver.findElements(By.xpath(".//*[@id='candProLi']/div/div[2]/div/div[5]/span")).size() != 0) {
			System.out.println("Element is Present");
		} else {
			System.out.println("Element is Absent");
		}
	}*/
	
	public void checkCurrentRoleinCandidateListView(String customerName) throws InterruptedException {
		if(customerName.equalsIgnoreCase("FIL")){
			Actions actions2 = new Actions(driver);		
			WebElement CurrentRole1 = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[9]/div[2]/li[6]/div/label/i[1]"));		                                                     
			actions2.moveToElement(CurrentRole1);
			actions2.build().perform();
			Thread.sleep(3000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[9]/div[2]/li[1]/div/label/span")).click();
			System.out.println("click");
		}else{
		    Actions actions = new Actions(driver);
		    WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
		    actions.moveToElement(refinesearch);
		    WebElement CurrentRole = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[12]"));		                                                     
		    actions.moveToElement(CurrentRole);
		    actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (customerName.equalsIgnoreCase("AccenturePhilTech")) {
			if (!driver.findElement(By.id("currentRole0")).isSelected()) {
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[contains(@id,'facet_11')]/li[1]/div/label[contains(@for,'currentRole0')]/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		if (customerName.equalsIgnoreCase("AccentureIndOps") || customerName.equalsIgnoreCase("IDC")) {
			if (!driver.findElement(By.id("currentRole0")).isSelected()) {
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[contains(@id,'facet_12')]/li[1]/div/label[contains(@for,'currentRole0')]/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (customerName.equalsIgnoreCase("IDC")) {
				if (!driver.findElement(By.id("currentRole0")).isSelected()) {
					Thread.sleep(5000);
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[12]/div[2]/li[1]/div/label/span")).click();
					  //div[contains(@id,'facet_12')]/li[1]/div/label[contains(@for,'currentRole0')]/i[1]
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
			
			/*else if (customerName.equalsIgnoreCase("AccentureIndOps")) {
				if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[13]/div[2]/li[1]/div/label/i[1]")).isSelected()) {
					Thread.sleep(5000);
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[13]/div[2]/li[1]/div/label/i[1]")).click();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}*/				////driver.quit();
			}
		}
		}
	}
	}
		public void checkskillinCandidateListView(String customerName) {
        
	if (customerName.equalsIgnoreCase("IDC")) {
		// put customized action here
		if (!driver.findElement(By.xpath(".//*[@id='facet_0']/li[2]/div/label/i[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_0']/li[2]/div/label/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//driver.quit();
		}
	} else {
		if (!driver.findElement(By.xpath(".//*[@id='facet_1']/li[1] | .//*[@id='facet_0']/li[2]/div/label/i[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_1']/li[1] | .//*[@id='facet_0']/li[2]/div/label/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//driver.quit();
		}
	}

}

public void ExperiencevalueinCandidateListView(String customerName) {
		Object Actions;
		if (customerName.equalsIgnoreCase("AccentureNA")) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[3]"));
			actions.moveToElement(experience);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "0.0-3.0");
			System.out.print(" Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[2]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "3.1-6.0");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[3]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "6.1-9.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[4]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "9.1+");
			System.out.print("\n Experience Matches");
			
		} else if (customerName.equalsIgnoreCase("FIL")) {
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
			actions.moveToElement(experience);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "0.0-2.0");
			System.out.print("Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[2]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "2.1-4.0");
			System.out.print("\n Experience Matches");  
			  
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[3]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "4.1-6.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[4]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "6.1-8.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[5]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "8.1-10.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[6]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "10.1-12.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[7]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "12.1-14.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_3']/li[8]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "14.1+");
			System.out.print("\n Experience Matches");
			
		}
		/*else if (customerName.equalsIgnoreCase("AccentureNA")) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[3]"));
			actions.moveToElement(experience);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			if ("listedValue".equals("0.0-3.0")) {
				System.out.println("Experience does not match ");
			} else {
				System.out.println("Experience matches ");
			}
			if ("listedValue".equals("3.1-6.0")) {
				System.out.println("Experience does not match ");
			} else {
				System.out.println("Experience matches ");
			}
			if ("listedValue".equals("6.1-9.0")) {
				System.out.println("Experience does not match ");
			} else {
				System.out.println("Experience matches ");
			}
			if ("listedValue".equals("9.1+")) {
				System.out.println("Experience does not match ");
			} else {
				System.out.println("Experience matches ");
			}
		}*/
		else if (customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps")) {
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[3]"));
			actions.moveToElement(experience);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[1]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "0.0-1.0");
			System.out.print("Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[2]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "1.1-2.0");
			System.out.print("\n Experience Matches");  
			  
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[3]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "2.1-3.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[4]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "3.1-4.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[5]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "4.1-5.0");
			System.out.print("\n Experience Matches");			
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[6]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "5.1+");
			System.out.print("\n Experience Matches");
			
		}
		else if (customerName.equalsIgnoreCase("IDC")) {
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[3]"));
			actions.moveToElement(experience);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[1]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "0.0-3.0");
			System.out.print("Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[2]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "3.1-6.0");
			System.out.print("\n Experience Matches");  
			  
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[3]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "6.1-9.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[4]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "9.1+");
			System.out.print("\n Experience Matches");*/
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[1]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "0.0-1.0");
			System.out.print("Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[2]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "1.1-2.0");
			System.out.print("\n Experience Matches");  
			  
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[3]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "2.1-3.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[4]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "3.1-4.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[5]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "4.1-5.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[6]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "5.1+");
			System.out.print("\n Experience Matches");
			
		}
		else if (customerName.equalsIgnoreCase("AccenturePhilTech")) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[3]"));
			actions.moveToElement(experience);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "0.0-1.0");
			System.out.print("Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[2]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "1.1-2.0");
			System.out.print("\n Experience Matches");  
			  
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[3]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "2.1-3.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[4]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "3.1-4.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[5]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "4.1-5.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[6]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "5.1+");
			System.out.print("\n Experience Matches");
			
			
			/*Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "0.0-3.0");
			System.out.print(" Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[2]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "3.1-6.0");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[3]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "6.1-9.0");
			System.out.print("\n Experience Matches");
			
			Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[4]/div/label/i[2]")).getText();
			Assert.assertEquals(Actualtext, "9.1+");
			System.out.print("\n Experience Matches");*/
			
			
			
			
		}else {
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[3]"));
		actions.moveToElement(experience);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[2]")).getText();
		Assert.assertEquals(Actualtext, "0.0-3.0");
		System.out.print("Experience Matches");
		
		Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[2]/div/label/i[2]")).getText();
		Assert.assertEquals(Actualtext, "3.1-6.0");
		System.out.print("\n Experience Matches");  
		  
		Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[3]/div/label/i[2]")).getText();
		Assert.assertEquals(Actualtext, "6.1-9.0");
		System.out.print("\n Experience Matches");
		
		Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[4]/div/label/i[2]")).getText();
		Assert.assertEquals(Actualtext, "9.1-12.0");
		System.out.print("\n Experience Matches");
		
		Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[5]/div/label/i[2]")).getText();
		Assert.assertEquals(Actualtext, "12.1-15.0");
		System.out.print("\n Experience Matches");
		
		Actualtext = driver.findElement(By.xpath(".//*[@id='facet_2']/li[6]/div/label/i[2]")).getText();
		Assert.assertEquals(Actualtext, "15.1+");
		System.out.print("\n Experience Matches");
		

		}}
	private void assertEquals(String string, String text) {
		// TODO Auto-generated method stub

	}

	public void clickExperienceinCandidateListView(String customerName) {
		// TODO Auto-generated method stub
		if (customerName.equalsIgnoreCase("FIL")){
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
		actions.moveToElement(experience);
		actions.click().build().perform();

		if (!driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/i[2]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/i[2]")).click();

		}
		}else if (customerName.equalsIgnoreCase("AccenturePhilBPO")){
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[3]"));
			actions.moveToElement(experience);
			actions.click().build().perform();

			if (!driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[1]/div/label/i[2]")).isSelected()) {
				driver.findElement(By.xpath("//div[contains(@id,'facet_2')]/li[1]/div/label/i[2]")).click();

			}
			}else{
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[3]"));
			actions.moveToElement(experience);
			actions.click().build().perform();

			if (!driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[2]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[2]")).click();

			}
		}
	}

	public String[] getMatchCloudArray(String[] experiencevalue) {
		String[] matchcase = experiencevalue;
		for (String cloud : matchcase) {
			System.out.println("Cloud String : " + cloud);
			Logging.log("Cloud String : " + cloud);
		}
		return matchcase;
	}

	public void locationinCandidateListView(String customerName) throws InterruptedException {
        if(customerName.equalsIgnoreCase("FIL")){
        	Actions actions = new Actions(driver);
    		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
    		actions.moveToElement(refinesearch);

    		WebElement experience = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[3]"));
    		actions.moveToElement(experience);
    		actions.click().build().perform();
		if (!driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_2']/li[1]/div/label/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//driver.quit();
		}
        }else if(customerName.equalsIgnoreCase("IDFC")){
            cu.explicitWait(driver, By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/span"), 120L);
        		if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/span")).isSelected()) {
        			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/span")).click();
        			Thread.sleep(6000);
        			System.out.println("click");
        			try {
        				Thread.sleep(8000);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		} else {
        			//driver.quit();
        		}
            }else if(customerName.equalsIgnoreCase("IDC")){
        	Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]"));
			actions.moveToElement(refinesearch);
        	
        	WebElement Location = driver.findElement(By.xpath(".//*[@id='facet_1']/li[7]/div/label"));
    		actions.moveToElement(Location);        		
    		actions.click().build().perform();
    		
            cu.explicitWait(driver, By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/span"), 120L);
        		if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/span")).isSelected()) {
        			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/span")).click();
        			System.out.println("CLICK");
        			try {
        				Thread.sleep(3000);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		} 
         }else if(customerName.equalsIgnoreCase("AccentureNA")){
        
    		if (!driver.findElement(By.xpath(".//*[@id='facet_1']/li[1]/div/label/i[1]")).isSelected()) {
    			driver.findElement(By.xpath(".//*[@id='facet_1']/li[1]/div/label/i[1]")).click();
    			try {
    				Thread.sleep(3000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		} else {
    			//driver.quit();
    		}
        }else if(customerName.equalsIgnoreCase("AccenturePhilTech")){
            cu.explicitWait(driver, By.xpath(".//*[@id='facet_1']/li[1]/div/label/i[1]"), 120L);
        		if (!driver.findElement(By.xpath(".//*[@id='facet_1']/li[1]/div/label/i[1]")).isSelected()) {
        			driver.findElement(By.xpath(".//*[@id='facet_1']/li[1]/div/label/i[1]")).click();
        			try {
        				Thread.sleep(3000);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		} else {
        			//driver.quit();
        		}
            }else if(customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps")){
            	Actions actions = new Actions(driver);
    			WebElement refinesearch = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]"));
    			actions.moveToElement(refinesearch);
            	
            	WebElement Location = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]"));
        		actions.moveToElement(Location);        		
        		//actions.click().build().perform();
        		
        		if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/i[1]")).isSelected()) {
        			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/i[1]")).click();
        			try {
        				Thread.sleep(3000);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		} else {
        			//driver.quit();
        		}
            }else{
        	if (!driver.findElement(By.xpath(".//*[@id='facet_1']/li[1]")).isSelected()) {
    			driver.findElement(By.xpath(".//*[@id='facet_1']/li[1]")).click();
    			try {
    				Thread.sleep(3000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		} else {
    			//driver.quit();
    		}
        }
	}	

	public void validateResultByLocation(String customerName) {
		// TODO Auto-generated method stub
		if(customerName.equalsIgnoreCase("AccenturePhilTech")||customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps")){
			
		}else if(customerName.equalsIgnoreCase("IDC")){
			String searchedValue = driver
					.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/i[1]")).getText();
			System.out.println("searchedValue is "  +searchedValue);
			
			Assert.assertEquals(searchedValue, "bangalore");
			System.out.println("test case pass in idc");
			
		}else if(customerName.equalsIgnoreCase("FIL")){
			String searchedValue = driver
					.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[3]/div[2]/li[1]/div/label/i[1]")).getText();
			System.out.println("searchedValue is "  +searchedValue);
			
			Assert.assertEquals(searchedValue, "india");
			System.out.println("test case pass in FIL");
			
		}else if(customerName.equalsIgnoreCase("AccentureNA")){
			String searchedValue = driver
					.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/i[1]")).getText();
			System.out.println("searchedValue is "  +searchedValue);
			
			Assert.assertEquals(searchedValue, "chicago");
			System.out.println("test case pass in Accenture NA");
			
		}else if(customerName.equalsIgnoreCase("IDFC")){
			String searchedValue = driver
					.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[2]/div[2]/li[1]/div/label/i[1]")).getText();
			System.out.println("searchedValue is "  +searchedValue);
			
			String expectedValue = driver
			.findElement(By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[3]/ul/li/div/div[1]/span| .//*[@id='crmCandProfileList']/ul/div[2]/ul/li[19]/ul/li/div/div[1]/span")).getText();
	        System.out.println("expectedValue is "  +expectedValue);
			
		    Assert.assertEquals(searchedValue, expectedValue);
			System.out.println("test case pass in IDFC");
			
		}
        else{
        	System.out.println("Enter");
		List<WebElement> searchedValue = driver
				.findElements(By.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[3]/ul/li/div/div[1]/span"));
		System.out.println("searchedValue :" + searchedValue);
		List<WebElement> listedValue = driver.findElements(By.xpath(".//*[@id='candName']/div/b/span[3]"));
		System.out.println("listedValue :" + listedValue);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*if ("listedValue".equals("searchedValue")) {
			System.out.println("Location does not match ");
		} else {
			System.out.println("Location matches ");
		}*/
		Assert.assertEquals(searchedValue, "listedValue");
		System.out.println("test case pass");

	}}

	private WebElement getExtractedLocationforCandidate(int candidateIndex) {
		// TODO Auto-generated method stub
		// return
		// driver.findElement(candidateExtractedSkillSet).getAttribute("title").toLowerCase();
		List<WebElement> extractedExperienceforCandidate = driver
				.findElements((By.xpath(("//span[contains(@title,'Years')])[" + candidateIndex + "]"))));
		System.out.println("extractedExperienceforCandidate : " + extractedExperienceforCandidate);
		return (WebElement) extractedExperienceforCandidate;

	}

	public void validateResultByExperiencevalue(String customerName) throws InterruptedException {
		// TODO Auto-generated method stub
        if(customerName.equalsIgnoreCase("AccenturePhilTech")){
        	List<WebElement> searchedValue = driver.findElements(By.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[2]/ul/li/div/div[2]/span"));
    		List<WebElement> listedValue = driver.findElements(By.xpath(".//*[@id='candProLi']/div/div/div[3]/span/div[4]/div/span"));
    		Thread.sleep(3000);

    		if ("listedValue".equals("searchedValue")) {
    		System.out.println("Experience does not match ");
    	} else {
    		System.out.println("Experience matches ");
    	}
    		

    	}else{
        
		List<WebElement> searchedValue = driver.findElements(By.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[2]/ul/li/div/div[2]/span"));
		List<WebElement> listedValue = driver.findElements(By.xpath(".//*[@id='candProLi']/div/div[2]/div/div[4]/strong"));
		Thread.sleep(3000);

		if ("listedValue".equals("searchedValue")){
		System.out.println("Experience does not match ");
	} else
				System.out.println("Experience matches ");

	}}

	public void clickSourceNameinCandidateListView(String customerName) throws InterruptedException {
		
		if (customerName.equalsIgnoreCase("AccentureNA")){
			
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcename = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[7]"));
			actions.moveToElement(sourcename);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(10000);
			if (!driver.findElement(By.xpath(".//*[@id='facet_6']/li[4]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_6']/li[4]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (customerName.equalsIgnoreCase("IDFC") ){
			
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcename = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[4]/div/label/span"));
			actions.moveToElement(sourcename);
			actions.build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[2]/div/label/span")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[2]/div/label/span")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (customerName.equalsIgnoreCase("AccenturePhilTech")){
			
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcename = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[7]"));
			actions.moveToElement(sourcename);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_6']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_6']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (customerName.equalsIgnoreCase("FIL")){
			
	Actions actions = new Actions(driver);

			WebElement sourcename = driver.findElement(By.xpath(".//*[@id='facet_4']/li[6]/div/label"));
			actions.moveToElement(sourcename);
			actions.click().build().perform();
			
			if (!driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/span")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/span")).click();
				Thread.sleep(5000);
			}
			
		}else if (customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps") || customerName.equalsIgnoreCase("IDC") ){
			
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcename = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]"));
			actions.moveToElement(sourcename);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]/div[2]/li[1]/div/label")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]/div[2]/li[1]/div/label")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
else if (customerName.equalsIgnoreCase("IDC") ){
			
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcename = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]/div[2]/li[5]/div/label"));
			actions.moveToElement(sourcename);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]/div[2]/li[4]/div/label")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]/div[2]/li[4]/div/label")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement sourcename = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
		actions.moveToElement(sourcename);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_3']/li[1] | .//*[@id='facet_3']/li[1]/div/label/i[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_3']/li[1] | .//*[@id='facet_3']/li[1]/div/label/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}}

	public void clickSourceTypeinCandidateListView(String customerName) {
		// TODO Auto-generated method stub
		if (customerName.equalsIgnoreCase("FIL")) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcetype = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[8] | .//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[6]"));
			actions.moveToElement(sourcetype);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1] | .//*[@id='facet_5']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1] | .//*[@id='facet_5']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (customerName.equalsIgnoreCase("AccentureNA")) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcetype = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[8]"));
			actions.moveToElement(sourcetype);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (customerName.equalsIgnoreCase("AccenturePhilTech")) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcetype = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[8]"));
			actions.moveToElement(sourcetype);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps") ) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcetype = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]"));
			actions.moveToElement(sourcetype);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]/div[2]/li[1]/div/label")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]/div[2]/li[1]/div/label")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (customerName.equalsIgnoreCase("IDFC")) {
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcetype = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[6]/div/label/i[1]"));
			actions.moveToElement(sourcetype);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[4]/div/label/span")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[4]/div/label/span")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}else if (customerName.equalsIgnoreCase("IDC")) {
			// put customized action here
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcetype = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]/div[2]/li[6]/div/label/i[1]"));
			actions.moveToElement(sourcetype);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]/div[2]/li[1]/div/label/span")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]/div[2]/li[1]/div/label/span")).click();
				System.out.println("CLICK");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
 else {
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement sourcetype = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[5]"));
			actions.moveToElement(sourcetype);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		
	}


	public void clickDownloadResumeinCandidateListView(String customerName) {
		// TODO Auto-generated method stub
		if (customerName.equalsIgnoreCase("FIL")) {
		if (!driver.findElement(By.xpath("//span[contains(@ng-click,'downloadResume')]")).isSelected()) {
			driver.findElement(By.xpath("//span[contains(@ng-click,'downloadResume')]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}else if(customerName.equalsIgnoreCase("AccentureNA")) {
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/ul/li[2]/div/div[1]/div[2]/div/div[9]/img")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/ul/li[2]/div/div[1]/div[2]/div/div[9]/img")).click();
				System.out.println("clicked on download");
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}}else if(customerName.equalsIgnoreCase("AccenturePhilTech")) {
				if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/div/span")).isSelected()) {
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/div/span")).click();
					System.out.println("clicked on download");
					try {
						Thread.sleep(9000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}else {
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/div/span")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/div/span")).click();
				System.out.println("clicked on download");
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		}
		
		/*
		 * //driver.findElement(By.xpath(
		 * ".//*[@id='candProLi']/div/div[1]/div[3]/div/div[10]/img")).click();
		 * //Thread.sleep(5000); //clu.downloadResume_VerifyExpectedFileName();
		 * //driver.findElement(By.linkText(".doc")).click(); String
		 * downloadPath = "C:/Users/Sridhar P/Downloads";
		 * //Assert.assertTrue(isFileDownloaded_Ext(downloadPath, ".doc"),
		 * "Failed to download document which has extension .xls");
		 * //System.out.println("Done");
		 * driver.findElement(By.linkText("mailmerge.xls")).click(); File
		 * getLatestFile = getLatestFilefromDir(downloadPath); String fileName =
		 * getLatestFile.getName();
		 * Assert.assertTrue(fileName.equals("mailmerge.xls"),
		 * "Downloaded file name is not matching with expected file name");
		 * 
		 */
	

	public void clickDownloadListinCandidateListView(String customerName) {
		// TODO Auto-generated method stub
		if (customerName.equalsIgnoreCase("FIL")) {
		if (!driver.findElement(By.xpath("//span[contains(@title,'Download List')]")).isSelected()) {
			driver.findElement(By.xpath("//span[contains(@title,'Download List')]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}else if (customerName.equalsIgnoreCase("AccenturePhilTech")) {
			if (!driver.findElement(By.xpath(".//*[@id='crmCandProfileList']/ul/div[1]/div[2]/span")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='crmCandProfileList']/ul/div[1]/div[2]/span")).click();
				System.out.println("Downloaded List");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}else {
				if (!driver.findElement(By.xpath("//span[contains(@title,'Download List')] | .//*[@id='crmCandProfileList']/ul/div[1]/div[2]/button | html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/ul/div[1]/div[2]/div/span")).isSelected()) {
					driver.findElement(By.xpath("//span[contains(@title,'Download List')] | .//*[@id='crmCandProfileList']/ul/div[1]/div[2]/button | html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/ul/div[1]/div[2]/div/span")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		
	}
		/*
		 * driver.findElement(By.xpath(
		 * ".//*[@id='crmCandProfileList']/ul/div[1]/div[2]/button")).click();
		 * Thread.sleep(3000); String downloadPath =
		 * "C:/Users/Sridhar P/Downloads";
		 * driver.findElement(By.linkText("CandidateListReport_List.csv")).click
		 * (); Assert.assertTrue(isFileDownloaded(downloadPath,
		 * "CandidateListReport_List.csv"),
		 * "Failed to download Expected document");
		 */
	

	public void clickTagResumeinCandidateListView() {
	
		if (!driver.findElement(By.xpath(".//*[@id='candProLi']/div/div[1]/div[1]/input")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='candProLi']/div/div[1]/div[1]/input")).click();
			driver.findElement(By.xpath(".//*[@id='saveSearchIconSpan']")).click();

			driver.findElement(By.xpath(".//*[@id='crmCandProfileList']/ul/div[1]/div[2]/button")).click();
			driver.findElement(By.xpath(".//*[@id='saveSearchIconSpan']")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Candidate is Tagged");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void clickTestCompanyinCandidateListView(String customerName) throws InterruptedException {
		// TODO Auto-generated method stub
		if(customerName.equalsIgnoreCase("AccentureNA")){
		
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement company = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
			actions.moveToElement(company);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(customerName.equalsIgnoreCase("AccenturePhilTech")){
		
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement company = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
			actions.moveToElement(company);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/i[4]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/i[4]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(customerName.equalsIgnoreCase("AccenturePhilBPO")){
		
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement company = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
			actions.moveToElement(company);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[1]/div/label/i[4]")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[1]/div/label/i[4]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(customerName.equalsIgnoreCase("AccentureIndOps")){
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement company = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
			actions.moveToElement(company);
			actions.click().build().perform();
			
			if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[1]/div/label/span")).isSelected()) {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[1]/div/label/span")).click();
				Thread.sleep(5000);
				
			}
		}else if(customerName.equalsIgnoreCase("IDC")){
			
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement company = driver.findElement(By.xpath(".//*[@id='facet_3']/li[7]/div/label/i[1]"));
			actions.moveToElement(company);
			actions.click().build().perform();
			
			if (!driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/span")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]/div/label/span")).click();
				
				
				
			}
		}

		else{
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement company = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[4]"));
		actions.moveToElement(company);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_3']/li[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}}

	public void clickTestInstituteinCandidateListView(String customerName) throws InterruptedException {
		// TODO Auto-generated method stub
		if (customerName.equalsIgnoreCase("AccentureIndOps")){
			Thread.sleep(10000);
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement institute = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[4]/div/label/i[1]"));
		actions.moveToElement(institute);
		actions.click().build().perform();
	
		if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[1]/div/label/span")).isSelected()) {
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[1]/div/label/span")).click();
			
		}
		
	}else if (customerName.equalsIgnoreCase("AccentureNA")){
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement institute = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]"));
		actions.moveToElement(institute);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_4']/li[4]/div/label/i[1]")).isSelected()) {
			Thread.sleep(10000);
			driver.findElement(By.xpath(".//*[@id='facet_4']/li[4]/div/label/i[1]")).click();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}else if (customerName.equalsIgnoreCase("AccenturePhilTech")){
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement institute = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]"));
		actions.moveToElement(institute);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/i[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}  else if (customerName.equalsIgnoreCase("AccenturePhilBPO")){
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement institute = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]"));
		actions.moveToElement(institute);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[1]/div/label/i[1]")).isSelected()) {
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[1]/div/label/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} else if(customerName.equalsIgnoreCase("IDC")){
		
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement institute = driver.findElement(By.xpath(".//*[@id='facet_4']/li[7]/div/label/i[1]"));
		actions.moveToElement(institute);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/span")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]/div/label/span")).click();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

   else{
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement institute = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[5]"));
		actions.moveToElement(institute);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_4']/li[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}}
	}


	public void clickEducationLevelinCandidateListView(String customerName) {
		// TODO Auto-generated method stub
		if(customerName.equalsIgnoreCase("AccentureNA")){
		
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement educationLevel = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[9]"));
			actions.moveToElement(educationLevel);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_8']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_8']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(customerName.equalsIgnoreCase("AccenturePhilTech")){
		
			Actions actions = new Actions(driver);
			WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
			actions.moveToElement(refinesearch);

			WebElement educationLevel = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[9]"));
			actions.moveToElement(educationLevel);
			actions.click().build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!driver.findElement(By.xpath(".//*[@id='facet_8']/li[1]/div/label/i[1]")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='facet_8']/li[1]/div/label/i[1]")).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement educationLevel = driver
				.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[9]"));
		actions.moveToElement(educationLevel);
		actions.click().build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!driver.findElement(By.xpath(".//*[@id='facet_8']/li[1]")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='facet_8']/li[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}}

	public void clickCandidateStatusinCandidateListView() throws InterruptedException {
		// TODO Auto-generated method stub
	/*	Thread.sleep(3000);
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		//cu.explicitWait(driver, By.xpath(".//*[@id='ngViewDiv']/div/div[1] | html/body/div[1]/div[2]/div/div[1]"), 30L);
*/		Actions actions = new Actions(driver);
		WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1] | html/body/div[1]/div[2]/div/div[1]"));
		actions.moveToElement(refinesearch);

		WebElement Status = driver
				.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[6]"));
		actions.moveToElement(Status);
		actions.click().build().perform();
/*		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block.//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[6] |  .//*[@id='facet_5']/li[1] | 
			e.printStackTrace();
		}*/
		if (!driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[6]/div[2]/li/div/label/i[1]")).isSelected()) {
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[6]/div[2]/li/div/label/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void clickUntagResumeinCandidateListView(String customerName) throws InterruptedException {
        if(customerName.equalsIgnoreCase("AccenturePhiltech") || customerName.equalsIgnoreCase("IDC")){
		if (!driver.findElement(By.xpath(".//*[@id='candProLi']/div/div/div[1]/label/span")).isSelected()) {
			driver.findElement(By.xpath(".//*[@id='candProLi']/div/div/div[1]/label/span")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//li[@id='candProLi']/div/div/div/label/span")).click();
			driver.findElement(By.xpath("//li[@id='candProLi']/div/div/div/label/span")).click();
			//*[@id='tagOverLayImg'] | 
			System.out.println("Candidate is UnTagged");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
        }else if(customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps")){
    		if (!driver.findElement(By.xpath(".//*[@id='candProLi']/div/div/div[1]/label/span")).isSelected()) {
    			driver.findElement(By.xpath(".//*[@id='candProLi']/div/div/div[1]/label/span")).click();
    			Thread.sleep(5000);
    			driver.findElement(By.xpath("//li[@id='candProLi']/div/div/div/label/span")).click();
    			driver.findElement(By.xpath("//li[@id='candProLi']/div/div/div/label/span")).click();
    			
    			//*[@id='tagOverLayImg'] | 
    			
    			
    			System.out.println("Candidate is UnTagged");
    			try {
    				Thread.sleep(3000);
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
            }else{
			if (!driver.findElement(By.xpath(".//*[@id='candProLi']/div/div[1]/div[1]/input")).isSelected()) {
				driver.findElement(By.xpath(".//*[@id='candProLi']/div/div[1]/div[1]/input")).click();
				driver.findElement(By.xpath(".//*[@id='tagOverLayImg']")).click();
				System.out.println("Candidate is UnTagged");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
	}

public void validateResultBysourceType(String customerName) throws InterruptedException {
	if(customerName.equalsIgnoreCase("IDC")){
		String expectedresult = driver.findElement(By.xpath("//*[@id='facet_7']/li[1]/div/label/i[1] | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[12]/ul/li/div/div[1]/span")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]/div[2]/li/div/label/i[1]")).getText();
		System.out.println("Actualresult :" + Actualresult);
		Logging.log("Asserting the Source Type in result");
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source type does not match in result");

	
	}	else if(customerName.equalsIgnoreCase("FIL")){
		Thread.sleep(10000);
		String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[6]/div[2]/li[1]/div/label/i[1]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[9]/div/span")).getText();
		System.out.println("Actualresult :" + Actualresult);
		/*String[] Final = Actualresult.split("/");
		System.out.println("Final :" + Final[0]);
		
		Logging.log("Asserting the Source Type in result");
		Assert.assertTrue(Final[0].trim().equalsIgnoreCase(expectedresult), "Source type does not match in result");
		*/
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source type does not match in result");

		
		
	} else if(customerName.equalsIgnoreCase("IDFC")){
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li/div/label/i[1]")).getText();
		System.out.println("Actualresult :" + Actualresult);
		String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[12]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[29]/ul/li/div/div[1]/span")).getText();
		System.out.println("expectedresult :" + expectedresult);
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source type does not match in result");
		
	}  else if(customerName.equalsIgnoreCase("AccenturePhilTech")){
		String Actualresult = driver.findElement(By.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[12]/ul/li/div/div[1]/span")).getText();
		System.out.println("Actualresult :" + Actualresult);
		String expectedresult = driver.findElement(By.xpath(".//*[@id='facet_7']/li/div/label/i[1]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source type does not match in result");
		
	} else if(customerName.equalsIgnoreCase("AccenturePhilBPO")){
		String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[8]/div[2]/li/div/label/i[1]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[12]/div/span")).getAttribute("title");
		System.out.println("Actualresult :" + Actualresult);
		String[] Final = Actualresult.split("/");
		System.out.println("Final :" + Final[0]);
		Assert.assertTrue(Final[0].trim().equalsIgnoreCase(expectedresult), "Source type does not match in result");
		
	}
	else{
		String expectedresult = driver.findElement(By.xpath("//*[@id='crmCandProfileList']/ul/div[2]/ul/li[12]/ul/li/div/div[1]/span")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[12]/div/span | .//*[@id='candProLi']/div/div[2]/div/div[7]/span | .//*[@id='candProLi']/div/div[2]/div/div[4]/span | .//*[@id='candProLi']/div/div/div[2]/span/div[11]/div/span | .//*[@id='candProLi']/div/div/div[2]/span/div[12]/div/span")).getText();
		System.out.println("Actualresult :" + Actualresult);
		String[] Final = Actualresult.split("/");
		System.out.println("Final :" + Final[0]);
		
		Logging.log("Asserting the Source Type in result");
		Assert.assertTrue(Final[0].trim().equalsIgnoreCase(expectedresult), "Source type does not match in result");
	}
	}
public void validateResultBysourcename(String customerName) throws InterruptedException {
	if(customerName.equalsIgnoreCase("IDC")){
		String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]/div[2]/li[1]/div/label/i[1]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[12]/div/span | .//*[@id='candProLi']/div/div[2]/div/div[7]/span | .//*[@id='candProLi']/div/div[2]/div/div[4]/span | .//*[@id='candProLi']/div/div/div[2]/span/div[11]/div/span | .//*[@id='candProLi']/div/div/div[2]/span/div[12]/div/span")).getAttribute("title").toLowerCase().trim();
		System.out.println("Actualresult :" + Actualresult);
		
		
		Logging.log("Asserting the Source Type in result");
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source type does not match in result");
		Logging.log("both are same");
	//	html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/ul/div[2]/ul/li[11]/ul/li/div[1]/span
	}else if(customerName.equalsIgnoreCase("AccentureNA")){
		
		String expectedresult = driver.findElement(By.xpath(".//*[@id='facet_6']/li[4]/div/label/i[1]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/ul/div[2]/ul/li[11]/ul/li/div[1]/span")).getText();
		System.out.println("Actualresult :" + Actualresult);
		Logging.log("Asserting the Source name in result");
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source name does not match in result");
		Logging.log("both are same");
		
	}else if(customerName.equalsIgnoreCase("FIL")){
		
		String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[1]/div/label/i[1] | html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[5]/div[2]/li[1]/div/label/i[1]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[11]/div/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[11]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[11]/div/span")).getAttribute("title");
		System.out.println("Actualresult :" + Actualresult);
		Logging.log("Asserting the Source name in result");
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source name does not match in result");
		Logging.log("both are same");
		
	}else if(customerName.equalsIgnoreCase("IDFC")){
		
		String expectedresult = driver.findElement(By.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[11]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[4]/div[2]/li[1]/div/label/i[1]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[33]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[11]/ul/li/div/div[1]/span")).getText();
		System.out.println("Actualresult :" + Actualresult);
		Logging.log("Asserting the Source name in result");
		Assert.assertTrue(Actualresult.equalsIgnoreCase(expectedresult), "Source name does not match in result");
		Logging.log("both are same");
		
	}else{
	String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[7]/div[2]/li[1]/div/label/i[1]")).getText();
	System.out.println("expectedresult :" + expectedresult);
	String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[12]/div/span | .//*[@id='candProLi']/div/div[2]/div/div[7]/span | .//*[@id='candProLi']/div/div[2]/div/div[4]/span | .//*[@id='candProLi']/div/div/div[2]/span/div[11]/div/span | .//*[@id='candProLi']/div/div/div[2]/span/div[12]/div/span")).getAttribute("title").toLowerCase().trim();
	System.out.println("Actualresult :" + Actualresult);
	String[] Final = Actualresult.split("/");
	System.out.println("Final :" + Final[1]);
	
	Logging.log("Asserting the Source Type in result");
	Assert.assertTrue(Final[1].trim().equalsIgnoreCase(expectedresult), "Source type does not match in result");
	}
}

public void SpireStatusinCandidateListView(String customerName) {
	// TODO Auto-generated method stub
	Actions actions = new Actions(driver);
	WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]"));
	actions.moveToElement(refinesearch);

	WebElement candidateStatus = driver
			.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[8]"));
	actions.moveToElement(candidateStatus);
	actions.click().build().perform();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (!driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1]")).isSelected()) {
		driver.findElement(By.xpath(".//*[@id='facet_7']/li[1]/div/label/i[1]")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public void verifyResultAppearsinCandateResultPage() {
	int candidateListSizeInOnePage = getCandidateListinResultPage().size();
	System.out.println("Candidate List Size in one page : " + candidateListSizeInOnePage);
	Assert.assertTrue(candidateListSizeInOnePage >= 1, "Result did not appear");
}
public void checkJobtitleinCandidateListView() {
	WebElement validateclient = this.driver.findElement(jobTitleinCandidateListView);

	Coordinates coordinate = ((Locatable) validateclient).getCoordinates();
	coordinate.inViewPort();
	cu.explicitWait(driver, jobTitleinCandidateListView, 9L);

	cu.explicitWait(driver, jobTitleCheckboxinCandidateListView, 30L);

	this.driver.findElement(jobTitleCheckboxinCandidateListView).click();
}
public void validateaccentureNAtitleinCandidateResultPage() {
	String ExpectedJobtitleNameinCandidateResultPage = this.driver.findElement(accenturenaexpectedjobtitle1)
			.getText();
	boolean ExpectedJobtitleNameinCandidateResult = this.driver.findElement(accenturenaexpectedjobtitle1)
			.isDisplayed();
	System.out.println("selected jobtitle name is displaying in candidate result page"
			+ ExpectedJobtitleNameinCandidateResultPage);
	Logging.log("selected accenturena jobtitle name is displaying in candidate result page");

}
public void validateIDCtitleinCandidateResultPage() {
	cu.explicitWait(driver, selectJobTitleinCandidateResultPage, 30L);
	String ExpectedJobtitleNameinCandidateResultPage = this.driver.findElement(selectJobTitleinCandidateResultPage)
			.getText();
	boolean ExpectedJobtitleNameinCandidateResult = this.driver.findElement(selectJobTitleinCandidateResultPage)
			.isDisplayed();
	System.out.println("selected jobtitle name is displaying in candidate result page"
			+ ExpectedJobtitleNameinCandidateResultPage);
	Logging.log("selected IDC jobtitle name is displaying in candidate result page");

}

public void validateCurrentRoleinCandidateResultPage(String customerName) {
if(customerName.equalsIgnoreCase("IDC")){
		
		//cu.explicitWait(driver, By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[13]"), 30L);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[12]/div[2]/li/div/label/i[1]")).getText().toLowerCase();
		System.out.println("Actualresult :" + Actualresult);
		String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[13]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[19]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[33]/ul/li/div/div[1]/span")).getText().toLowerCase();
		System.out.println("expectedresult :" +expectedresult.trim());
		Assert.assertEquals(Actualresult, expectedresult);
		Logging.log("selected Current Role is displaying in candidate result page");
		System.out.println("test case pass");
	}
	
	else if(customerName.equalsIgnoreCase("FIL")){
		String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[9]/div[2]/li[1]/div/label/i[1] | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[13]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[19]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[33]/ul/li/div/div[1]/span")).getText();
        System.out.println("expectedresult :" +expectedresult);
		String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[13]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[33]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[33]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[2]/ul/li[13]/ul/li/div/div[1]/span | html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[19]/ul/li/div/div[1]/span")).getText().toLowerCase();
		System.out.println("Actualresult :" + Actualresult);
		Assert.assertEquals(Actualresult, expectedresult);
		Logging.log("selected Current Role is displaying in candidate result page");
		System.out.println("test case pass");
		
	}
	else{
	
	cu.explicitWait(driver, By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[11]/div/span"), 30L);
	String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[11]/div/span")).getAttribute("title").toLowerCase();
	System.out.println("expectedresult :" +expectedresult.trim());
	String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/span/div[11]/div/span | //div[contains(@id,'facet_12')]/li[1]/div/label[contains(@for,'currentRole0')]/i[1]")).getText().toLowerCase();
	System.out.println("Actualresult :" + Actualresult.trim());
	Assert.assertEquals(Actualresult, expectedresult);
	Logging.log("selected Current Role is displaying in candidate result page");
	System.out.println("test case pass");
	}
}
public void checkClientStatusinCandidateListView() {

	Actions actions = new Actions(driver);
	WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
	actions.moveToElement(refinesearch);
	WebElement ClientStatus = driver
			.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[2]/div[1]/ul[10]"));

	actions.moveToElement(ClientStatus);
	actions.click().build().perform();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (!driver.findElement(By.xpath(".//*[@id='facet_9']/li[1]/div/label/i[1]")).isSelected()) {
		driver.findElement(By.xpath(".//*[@id='facet_9']/li[1]/div/label/i[1]")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

public void verifySelectClientStatusinCandidateListPage() {
	cu.isElementPreset(selectClienStatusinCandidateResultPage, driver);
	Logging.log("The selected client status is display properly in Candidate Result page");

}
public void checkJobtitleinCandidateListViewinIndops() {
	WebElement validateclient = this.driver.findElement(jobTitleinCandidateListViewinIndops);

	Coordinates coordinate = ((Locatable) validateclient).getCoordinates();
	coordinate.inViewPort();
	cu.explicitWait(driver, jobTitleinCandidateListViewinIndops, 9L);

	cu.explicitWait(driver, jobTitleCheckboxinCandidateListViewinIndops, 30L);

	this.driver.findElement(jobTitleCheckboxinCandidateListViewinIndops).click();
}
public void validateSelecttitleinCandidateResultPageinIndops() {
	cu.explicitWait(driver, selectJobTitleinCandidateResultPageinIndops, 30L);
	String ExpectedJobtitleNameinCandidateResultPage = this.driver.findElement(selectJobTitleinCandidateResultPageinIndops)
			.getText();
	boolean ExpectedJobtitleNameinCandidateResult = this.driver.findElement(selectJobTitleinCandidateResultPageinIndops)
			.isDisplayed();
	System.out.println("selected jobtitle name is displaying in candidate result page"
			+ ExpectedJobtitleNameinCandidateResultPage);
	Logging.log("selected Indops jobtitle name is displaying in candidate result page");

}
/*public void verifyResultAppearsinCandateResultPage() {
int candidateListSizeInOnePage = getCandidateListinResultPage().size();
System.out.println("Candidate List Size in one page : " + candidateListSizeInOnePage);
Assert.assertTrue(candidateListSizeInOnePage >= 1, "Result did not appear");
}*/
public void checkJobtitleinCandidateListViewinPhilTech(String customerName)throws Exception {
	Actions actions = new Actions(driver);
	WebElement refinesearch = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div[1]/div[1]"));
	actions.moveToElement(refinesearch);
	WebElement JobTitle = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[11]"));
	actions.moveToElement(JobTitle);
	actions.click().build().perform();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (customerName.equalsIgnoreCase("AccenturePhilTech")) {
		if (!driver.findElement(By.xpath("//div[contains(@id,'facet_10')]/li[1]/div/label[contains(@for,'roleName0')]/i[1]")).isSelected()) {
			driver.findElement(By.xpath("//div[contains(@id,'facet_10')]/li[1]/div/label[contains(@for,'roleName0')]/i[1]")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			//driver.quit();
		}
	} 
}


public void validateJobTitleinCandidateResultPage() {
	cu.explicitWait(driver, accenturenaexpectedjobtitle1, 50L);
String ExpectedJobtitleNameinCandidateResultPage = this.driver.findElement(accenturenaexpectedjobtitle1)
		.getText();
boolean ExpectedJobtitleNameinCandidateResult = this.driver.findElement(accenturenaexpectedjobtitle1)
		.isDisplayed();
System.out.println("selected jobtitle name is displaying in candidate result page"
		+ ExpectedJobtitleNameinCandidateResultPage);
Logging.log("selected  jobtitle name is displaying in candidate result page");}
/*public void verifySelectClientStatusinCandidateListPage() {
cu.isElementPreset(selectClienStatusinCandidateResultPage, driver);
Logging.log("The selected client status is display properly in Candidate Result page");

}*/

public void clickDownloadBulkResumeinCandidateListView(String customerName) {
// TODO Auto-generated method stub
if (customerName.equalsIgnoreCase("AccenturePhilTech")) {
if (!driver.findElement(By.xpath(".//*[@id='crmCandProfileList']/ul/div[1]/div[5]/label/span")).isSelected()) {
	driver.findElement(By.xpath(".//*[@id='crmCandProfileList']/ul/div[1]/div[5]/label/span")).click();
	driver.findElement(By.xpath(".//*[@id='simpleResumeDownload']/span")).click();
	System.out.println("Downloaded Bulk Resume");
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}else {
	if (!driver.findElement(By.xpath("//label[contains(@for,'selectAllCandidates')] | .//*[@id='candProLi']/div/div[1]/div[3]/div/div[9]/img | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[1]/div[5]/label/span")).isSelected()) {
		driver.findElement(By.xpath("//label[contains(@for,'selectAllCandidates')] | .//*[@id='candProLi']/div/div[1]/div[3]/div/div[9]/img | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[1]/div[5]/label/span")).click();
		driver.findElement(By.xpath("//span[contains(@title,'Download Bulk Resume')]")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	}}
public void validateLastModifiedDate() {

	if (driver.findElements(By.xpath(".//*[@id='candProLi']/div/div[2]/div/div[5]/span")).size() != 0) {
		System.out.println("Element is Present");
	} else {
		System.out.println("Element is Absent");
	}
}
//Added For Free TextSearch:@Ganesh

public void verifyResumeSearch() throws InterruptedException {
System.out.println("In Verify Resume Search");
this.driver.manage().window().maximize();
Thread.sleep(10000);
cu.explicitWait(driver, ResumeSearch, 20L);
this.driver.findElement(ResumeSearch);
System.out.println("DEBUG-----Asserting for Resume Search Input Field Presence");
Assert.assertTrue(cu.isElementPreset(ResumeSearch, driver), "No Resume Search Input Field");
}

//Added For Free TextSearch:@Ganesh
public void verifyResumeSearchButton() throws InterruptedException {
this.driver.manage().window().maximize();
Thread.sleep(10000);
cu.explicitWait(driver, ResumeSearch);
if (this.driver.findElement(ResumeSearch).getText() == "") {
System.out.println("DEBUG-----Asserting for Resume Search Button default Disable");
Assert.assertTrue(this.driver.findElement(ResumeSearchButton).isEnabled(), "Enabled");
}

}


//Added For Free TextSearch:@Ganesh
public void verifyResumeSearchfunc(String customerName, TestDataSet testData) throws InterruptedException {
this.driver.manage().window().maximize();
Thread.sleep(10000);
cu.explicitWait(driver, ResumeSearch);
//String customerName = clientName;
this.driver.findElement(ResumeSearch).click();
String searchParameterforResumeSearch = testData.getvalue4();
this.driver.findElement(ResumeSearch).sendKeys(searchParameterforResumeSearch);
Thread.sleep(2000);
this.driver.findElement(ResumeSearchButton).click();
Thread.sleep(3000);
cu.explicitWait(driver, DownloadResume);
this.driver.findElement(DownloadResume).click();
 Thread.sleep(10000);
 
}


}
