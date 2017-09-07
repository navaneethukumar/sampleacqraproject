package com.guarented.ecommerce.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.driver.Driver;
import com.spire.base.controller.Logging;

public class ManageSaveSearchPage extends Driver {
	
	
	public ManageSaveSearchPage(WebDriver driver) {
		super(driver);
	}

	CommonUtils cu = new CommonUtils();
	boolean ssFlag = false;
	String executeSaveSearchTitle;
	
	/*
	 * 
	 */
	//protected By createSaveSearchButton = By.xpath(".//*[@id='saveSearchIconSpan'] | .//*[@id='crmCandProfileList']/ul/div[3]/div");
	protected By createSaveSearchButton = By.xpath(".//*[@id='crmCandProfileList']/ul/div[3]/div");
	//protected By saveSearchButton = By.id("saveSearch");.//*[@id='crmCandProfileList']/ul/div[3]/div/div/div[3]/input[1]
	protected By saveSearchButton = By.xpath(".//*[@id='crmCandProfileList']/ul/div[3]/div/div/div[3]/input[1]");
	//protected By saveSearchNameTextbox = By.xpath(".//*[@id='saveSearchTitle']|.//*[@id='crmCandProfileList']/ul/div[3]/div/div/div[2]/input");
	protected By saveSearchNameTextbox = By.xpath(".//*[@id='crmCandProfileList']/ul/div[3]/div/div/div[2]/input");
	protected By cancelSaveSearchButton = By.id("cancelSaveSearch");

	protected By manageSaveSearch = By.xpath("//*[@id='headerColDiv']/div/div/div/div[2]/ul/li[2]/span/span[1] | .//*[@id='headerColDiv']/div/div/div/div[3]/ul/li[2]/span/span[1]");
	protected By saveSearchBoxTextbox = By.xpath("//*[@id='saveSearchBox']");
//	protected By saveSearchIcon = By.xpath("//span[@role='button']/i");
	protected By saveSearchIcon = By.xpath("//*[@id='basic-addon2']/i");
	protected By closeButton = By.id("saveSearchClose");
	protected By manageSaveSearchLabel = By.id("manageSaveSearch");

	protected By nameSortButton = By.xpath("//html/body/div[5]/div/div/div[2]/div[1]/div[1]/div/table/thead/tr/th[1]/span[2]");
	//protected By searchCriteriaText = By.xpath("//b[contains(@class,'save-search-name')]");
	protected By searchCriteriaText = By.xpath("//td[@class='ng-scope']");
	protected By modifiedOnSortButton = By.xpath("//html/body/div[5]/div/div/div[2]/div[1]/div[1]/div/table/thead/tr/th[3]/span[2]");
	protected By modifiedOnText = By.xpath("//html/body/div[5]/div/div/div[2]/div[1]/div[1]/div/table/tbody/tr[1]/td[3]");
	
	protected By executeSaveSearchNameLink = By.xpath("//html/body/div[6]/div/div/div[2]/div[1]/div[1]/div/table/thead/following-sibling::tbody/tr[1]/td[1]/a");
	protected By editSaveSearchButton = By.id("attachEditBtn");
	protected By editSaveSearchNameTextbox = By.id("editAttachment");
	protected By editSaveSearchNameButton = By.xpath("//*[@id='detProNameSave']");
	protected By cancelSaveSearchNameButton = By.xpath("//*[@id='detProNameCancel']");
	protected By shareSaveSearchIcon = By.xpath("//*[@id='shareSearch']");
	protected By shareSaveSearchNameTextbox = By.xpath("//*[@id='shareSearchInput']");
	protected By cancelShareSearchNameButton = By.xpath("//*[@id='detProNameCancel']");
	protected By deleteSaveSearchButton = By.xpath("//*[@id='AttachDeleteBtn']");
	protected By saveSearchCriteria = By.xpath("//html/body/div[4]/div/div/div[2]/div[1]/div[1]/div/table/tbody/tr[1]/td[2]/b");
	public By firstPaginationButton = By.xpath("//*[@id='hPaginationDiv']/ul/li[1]/a");
	//public By lastPaginationButton = By.xpath("//*[@id='hPaginationDiv']/ul/li[10]/a");
	public By lastPaginationButton = By.xpath("(//a[text()='»'])[2]");
	
	public By secondPageButton = By.xpath("//*[@id='hPaginationDiv']/ul/li[4]/a");
	
	protected By popUpForSaveSearch = By.xpath("//md-toast/div/span");

	public By firstSaveSearch = By.xpath("//html/body/div[4]/div/div/div[2]/div[1]/div[1]/div/table/tbody/tr[1]/td[1]/a/strong");

	public void getsaveSearchTextboxclick() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(saveSearchBoxTextbox).click();
		System.out.println("Clicked on save search name text box");
		
	}
	
	public void getsaveSearchIconclick() throws InterruptedException {
	
		driver.findElement(saveSearchIcon).click();
		System.out.println("saveSearchIcon clicked");
		Thread.sleep(5000);
	}

	public void typeInSaveSearchTextbox(String saveSearchTitle)
			throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(saveSearchBoxTextbox).sendKeys(saveSearchTitle);
		System.out.println(saveSearchTitle);
		System.out.println("dgdfgdfgdasdasdasd");
	}
	
	public void typeInEditSaveSearchNameTextbox(String searchString)
			throws InterruptedException {
		System.out.println("Typing in Save search name");
		driver.findElement(editSaveSearchNameTextbox).sendKeys(searchString);
		Thread.sleep(2000);
	}

	public void getEditSaveSearchTextboxclick() throws InterruptedException {

		driver.findElement(editSaveSearchNameTextbox).click();

	}

	public void getcloseButton() {
		driver.findElement(closeButton).click();
	}

	public void getnameSortButton() {
		driver.findElement(nameSortButton).click();

	}

	public void getmodifiedOnSortButton() {
		driver.findElement(modifiedOnSortButton).click();
	}

	public void geteditSaveSearchButton() {
		driver.findElement(editSaveSearchButton).click();
	}

	public void geteditSaveSearchNameButton() {
		driver.findElement(editSaveSearchNameButton).click();
	}

	public void getcancelSaveSearchNameButton() {
		driver.findElement(cancelSaveSearchNameButton).click();
	}

	public void getshareSaveSearchButton() {
		driver.findElement(shareSaveSearchIcon).click();
	}

	public void getshareSaveSearchNameTextbox() {
		driver.findElement(shareSaveSearchNameTextbox).click();
	}

	public void getcancelShareSearchNameButton() {
		driver.findElement(cancelShareSearchNameButton).click();
	}

	public void getmanageSaveSearchLabel() {
		driver.findElement(manageSaveSearchLabel);
	}

	public boolean isElementPresent(By object, WebDriver driver) {

		try {
			return driver.findElement(object).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickCreateSaveSearchButton() throws InterruptedException {
		Thread.sleep(2000);
		Logging.log("Clicking Create Save Search");
		this.driver.findElement(createSaveSearchButton).click();

	}

	public void clickExecuteSaveSearchNameLink() throws InterruptedException {
		Thread.sleep(2000);
		Logging.log("Clicking the Save Search Name");
		this.driver.findElement(executeSaveSearchNameLink).click();
	}

	public void clickdeleteSaveSearchButton() {
		Logging.log("Clicking the delete save search button");
		this.driver.findElement(deleteSaveSearchButton).click();
	}

	public void clickcloseButton() {
		Logging.log("Clicking the close button");
		this.driver.findElement(closeButton).click();
	}

	public void clickLastPage() {
		Logging.log("Clicking the last page in save search");
		this.driver.findElement(lastPaginationButton).click();
	}

	public void clickfirstPage() {
		Logging.log("Clicking the first page in save search");
		this.driver.findElement(firstPaginationButton).click();
	}

	public void clickfirstSaveSearchListed() {
		Logging.log("Clicking the first save search");
		this.driver.findElement(firstSaveSearch).click();
	}

	public void clickManageSaveSearchButton() throws InterruptedException {
		//cu.explicitWait(driver, manageSaveSearch, 30L);
		cu.explicitWait(driver, manageSaveSearch);
		Logging.log("Manage Save Search Button is being clicked.");
		System.out.println("Manage Save Search Search Button is being clicked.");
		this.driver.findElement(manageSaveSearch).click();
		Thread.sleep(3000);
	}

	public void clickcreateSaveSearch(String saveSearchTitle) {
		Logging.log("Save Search  Button is being clicked.");
		driver.findElement(saveSearchButton).click();
		System.out.println("Save Search is created");
		System.out.println(saveSearchTitle);
		Logging.log("Save Search is created :::" + saveSearchTitle);
	}

	public void getShareSaveSearch() throws InterruptedException {
		Thread.sleep(2000);
		Logging.log("Getting the share save search url");
		String getShareSaveSearchURL = driver.findElement(
				shareSaveSearchNameTextbox).getAttribute("ng-model");
		System.out.println("getShareSaveSearchURL : " + getShareSaveSearchURL);

	}

	public String typeInSaveSearchName() throws InterruptedException {
		System.out.println("Typing in savesearchName");
		driver.findElement(saveSearchNameTextbox).clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String saveSearchTitle = "Save Search" + randomNumber;
		executeSaveSearchTitle=saveSearchTitle;
		System.out.println("SavesearchName is " + saveSearchTitle);
		driver.findElement(saveSearchNameTextbox).sendKeys(saveSearchTitle);
		return saveSearchTitle;
	}

	public void getSaveSearchName(String saveSearchTitle)
			throws InterruptedException {
		Thread.sleep(2000);
		Logging.log("Getting the first save search name");
		String getSaveSearchTitle = this.driver
				.findElement(
						By.xpath("//strong[contains(text(),'" + saveSearchTitle
								+ "')]")).getText();
		System.out.println("getSaveSearchTitle  " + getSaveSearchTitle);
		System.out.println("saveSearchTitle  " + saveSearchTitle);
		if (getSaveSearchTitle.equalsIgnoreCase(saveSearchTitle)) {
			System.out
					.println("Created save search is listed in Manage Save Search Page");
			Logging.log("Created save search is listed in Manage Save Search Page");
		} else {
			System.out
					.println("Created save search is not listed in Manage Save Search Page");
			Logging.log("Created save search is not listed in Manage Save Search Page");
		}

	}

	public boolean getPopupTextInSaveSearch() throws InterruptedException {

		Logging.log("Getting Text In SaveSearch");
		cu.explicitWait(driver, popUpForSaveSearch, 30L);
		String saveSearchPopupMsg = this.driver.findElement(popUpForSaveSearch)
				.getText();
		// System.out.println(saveSearchPopupMsg);
		String duplicatNameCheckMsg = "Invalid input. Savedsearch cannot be created using existing name. Please try with different name.";

		if (saveSearchPopupMsg.contentEquals(duplicatNameCheckMsg)) {
			System.out.println("Save Search with that name already exist");
			Logging.log("Save Search with that name already exist");
			ssFlag = true;
		} else {
			ssFlag = false;
		}

		return ssFlag;
	}

	public void getPopupTextInUpdateSaveSearchName()
			throws InterruptedException {

		Logging.log("Getting Text In UpdateSaveSearchName");
		cu.explicitWait(driver, popUpForSaveSearch, 30L);
		String saveSearchPopupMsg = this.driver.findElement(popUpForSaveSearch)
				.getText();
		String updateNameMsg = "Search Updated Successfully";

		if (saveSearchPopupMsg.contentEquals(updateNameMsg)) {
			System.out.println("Save Search name is updated successfully");
			Logging.log("Save Search name is updated successfully");

		} else {
			System.out
					.println("Invalid input. Savedsearch cannot be updated using existing name. Please try with different name.");
			Logging.log("Invalid input. Savedsearch cannot be updated using existing name. Please try with different name.");
		}

	}

	public void getPopupTextInDeleteSaveSearch() throws InterruptedException {

		Alert deleteAlert = driver.switchTo().alert();
		Logging.log("Getting Text In Delete SaveSearchName");
		String alertText = deleteAlert.getText();
		System.out.println("Alert text is " + alertText);

		if (alertText.contentEquals("Delete Saved Search ?")) {
			deleteAlert.accept();
			System.out.println("Save Search is deleted successfully");
			Logging.log("Save Search is deleted successfully");

		} else {
			deleteAlert.dismiss();
			System.out.println("Save Search delete is cancelled");
			Logging.log("Save Search delete is cancelled");
		}

	}

	
}
