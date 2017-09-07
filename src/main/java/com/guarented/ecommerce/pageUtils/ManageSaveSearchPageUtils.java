package com.guarented.ecommerce.pageUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.jsoup.select.Evaluator.ContainsText;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.pages.ManageSaveSearchPage;
import com.spire.base.controller.Logging;

public class ManageSaveSearchPageUtils extends ManageSaveSearchPage {

	CommonUtils cu = new CommonUtils();
	String saveSearchPopupMsg;
	String SearchCriteria;
	String getSaveSearchCriteria;

	public ManageSaveSearchPageUtils(WebDriver driver) {
		super(driver);

	}

	/**
	 * Validate Manage Save Search page
	 * 
	 * @throws InterruptedException
	 **/

	public void ValidateManageSaveSearch() throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		System.out.println("clicking search savesearch textbox");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getsaveSearchTextboxclick();
		Logging.log("Asserting label");
		System.out.println("Asserting label");
		cu.isElementPreset(manageSaveSearchLabel, driver);
		Logging.log("Manage SaveSearch Label is displayed");
		System.out.println("Manage SaveSearch Page is displayed");

	}

	/**
	 * Verify all the fields present in Manage Save Search page
	 * 
	 * @throws InterruptedException
	 **/

	public void verifyAllFieldsInManageSaveSearch() throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		driver.switchTo().window(handle);
		getsaveSearchTextboxclick();
		Logging.log("Asserting label");
		System.out.println("Asserting label");
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cu.isElementPreset(saveSearchBoxTextbox, driver);
		cu.isElementPreset(saveSearchIcon, driver);
		Logging.log("Search savesearch is displayed");
		System.out.println("Search savesearch is displayed");
		cu.isElementPreset(closeButton, driver);
		Logging.log("Close Button is displayed");
		System.out.println("Close Button is displayed");
		cu.isElementPreset(nameSortButton, driver);
		cu.isElementPreset(modifiedOnSortButton, driver);
		Logging.log("Sort Option is displayed");
		System.out.println("Sort Option is displayed");
		cu.isElementPreset(executeSaveSearchNameLink, driver);
		Logging.log("Execute SaveSearch Option is displayed");
		System.out.println("Execute SaveSearch Option is displayed");
		cu.isElementPreset(editSaveSearchButton, driver);
		cu.isElementPreset(editSaveSearchNameTextbox, driver);
		cu.isElementPreset(editSaveSearchNameButton, driver);
		cu.isElementPreset(cancelSaveSearchNameButton, driver);
		Logging.log("Edit SaveSearch is displayed");
		System.out.println("Edit SaveSearch is displayed");
		cu.isElementPreset(shareSaveSearchNameTextbox, driver);
		cu.isElementPreset(cancelShareSearchNameButton, driver);
		System.out.println("Share SaveSearch is displayed");
		Logging.log("Share SaveSearch is displayed");
		cu.isElementPreset(deleteSaveSearchButton, driver);
		Logging.log("Delete SaveSearch is displayed");
		System.out.println("Delete SaveSearch is displayed");
		cu.isElementPreset(firstPaginationButton, driver);
		cu.isElementPreset(lastPaginationButton, driver);
		Logging.log("Pagination is displayed");
		System.out.println("Pagination is displayed");
	}

	/**
	 * Verify Create Save Search
	 * 
	 * @throws InterruptedException
	 **/

	public void createSaveSearch(String saveSearchTitle)throws InterruptedException {
		clickCreateSaveSearchButton();
		typeInSaveSearchName();
		clickcreateSaveSearch(saveSearchTitle);
	}

	public void duplicacyInSaveSearchName(boolean ssFlag)
			throws InterruptedException {
		clickCreateSaveSearchButton();
		driver.findElement(saveSearchNameTextbox).clear();
		driver.findElement(saveSearchNameTextbox).sendKeys("TestSaveSearch");
		driver.findElement(saveSearchButton).click();
		getPopupTextInSaveSearch();

		if (ssFlag == false) {
			clickCreateSaveSearchButton();
			driver.findElement(saveSearchNameTextbox).clear();
			driver.findElement(saveSearchNameTextbox)
					.sendKeys("TestSaveSearch");
			driver.findElement(saveSearchButton).click();
			getPopupTextInSaveSearch();

		}
	}

	public void validateSaveSearchListing(String saveSearchTitle)
			throws InterruptedException {
		clickCreateSaveSearchButton();
		String title = typeInSaveSearchName();
		System.out.println(title);
		clickcreateSaveSearch(saveSearchTitle);
		Thread.sleep(9000);
		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getsaveSearchTextboxclick();
		typeInSaveSearchTextbox(title);
		getsaveSearchIconclick();
		getSaveSearchName(title);

	}

	public void executeSaveSearch(String exeSaveSearchTitle)
			throws InterruptedException {

		clickCreateSaveSearchButton();
		Thread.sleep(2000);
		driver.findElement(saveSearchNameTextbox).clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		exeSaveSearchTitle = "Save Search" + randomNumber;
		System.out.println("SavesearchName is " + exeSaveSearchTitle);
		driver.findElement(saveSearchNameTextbox).sendKeys(exeSaveSearchTitle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		clickcreateSaveSearch(exeSaveSearchTitle);
		Thread.sleep(10000);
		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getsaveSearchTextboxclick();
		Logging.log("Getting the first save search name");
		this.driver.findElement(
				By.xpath("//strong[contains(text(),'" + exeSaveSearchTitle
						+ "')]")).click();
		CandidateListViewUtils clu = new CandidateListViewUtils(driver);
		clu.verifyResultAppears();
	}

	public void executeSaveSearchNameLink(TestDataSet testData)
			throws InterruptedException {
		Thread.sleep(2000);
		Logging.log("Executing Save Search");

		/*
		 * this.driver.findElement( By.xpath("//strong[contains(text(),'" +
		 * testData.getvalue1() + "')]")).click();
		 */
		Thread.sleep(2000);

	}

	public void verifyEditSaveSearchName() throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		geteditSaveSearchButton();
		getEditSaveSearchTextboxclick();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.findElement(editSaveSearchNameTextbox).clear();
		typeInEditSaveSearchNameTextbox("SaveSearchName Updated");
		geteditSaveSearchNameButton();
		getPopupTextInUpdateSaveSearchName();

	}

	public void verifyShareSaveSearch() throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getshareSaveSearchButton();
		getshareSaveSearchNameTextbox();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getShareSaveSearch();

	}

	public void verifyDeleteSaveSearch() throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		clickdeleteSaveSearchButton();
		getPopupTextInDeleteSaveSearch();
	}

	public void verifySearchInSaveSearch(String SearchCriteria)
			throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getsaveSearchTextboxclick();
		System.out.println("            :::" + SearchCriteria);

		driver.findElement(saveSearchBoxTextbox).sendKeys(SearchCriteria);
		getsaveSearchIconclick();

	}

	public void validateSearchResult(TestDataSet testData) {

		cu.explicitWait(driver, searchCriteriaText, 120L);
		String searchCriteria = driver.findElement(searchCriteriaText)
				.getText();

		System.out.println("getSaveSearchCriteria From Table : "
				+ searchCriteria);
		System.out.println("SearchCriteria From CSV : " + testData.getvalue1());
		Assert.assertTrue(searchCriteria.contains(testData.getvalue1()),
				"Save search listed does not contain the search criteria");

		/*
		 * cu.explicitWait(driver, searchCriteriaText, 80L); List<WebElement>
		 * list = driver.findElements(searchCriteriaText); for (int i = 0; i <
		 * list.size(); i++) { System.out.println("getSaveSearchCriteria : " +
		 * list.get(i).getText()); System.out.println("SearchCriteria :::: " +
		 * testData.getvalue1());
		 * Assert.assertTrue(list.get(i).getText().contains
		 * (testData.getvalue1()),
		 * "Save search listed does not contain the search criteria");
		 * 
		 * }
		 */
	}

	public void verifyPaginationOnSaveSearch() throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (cu.isElementPreset(secondPageButton, driver)) {
			clickLastPage();
			cu.explicitWait(driver, searchCriteriaText, 150L);
			System.out.println("Pagination is working, navigated to last page");
			Logging.log("Pagination is working, navigated to last page");
			Assert.assertTrue(cu.isElementPreset(searchCriteriaText, driver),
					"Save Search data didnt load");
		} else {
			System.out
					.println("Only one page data is present, hence no pagination");
			Logging.log("Only one page data is present, hence no pagination");
		}

	}

	public void verifySortOnModifiedOnSaveSearch() throws InterruptedException {

		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String modifiedOnBeforeSort = driver.findElement(modifiedOnText)
				.getText();
		getmodifiedOnSortButton();
		String modifiedOnAfterSort = driver.findElement(modifiedOnText)
				.getText();
		if (modifiedOnBeforeSort.contentEquals(modifiedOnAfterSort)) {
			System.out.println("Sort by Modified Date didnt happen");
			Logging.log("Sort by Modified Date didnt happen");

		} else {
			System.out.println("Save search is Sorted on Modified Date");
			Logging.log("Save search is Sorted on Modified Date");
		}

	}
	//@Sridhar
	public void clickonManageSaveSearch() throws InterruptedException {
		// TODO Auto-generated method stub
		clickManageSaveSearchButton();
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		driver.switchTo().window(handle);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Thread.sleep(3000);
		//if (!driver.findElement(By.xpath("html/body/div[4]/div/div/div[2]/div[1]/div[1]/div/table/tbody/tr[1]/td[1]/a/strong")).isSelected()) {
			driver.findElement(By.xpath("//tbody/tr[1]/td/a[contains(@ng-click,'searchresults.candidate(item)')]/strong")).click();
		//	driver.findElement(By.xpath("//strong[contains(text(),'Save Search974')]")).click();
			/*try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}*/
}



}
