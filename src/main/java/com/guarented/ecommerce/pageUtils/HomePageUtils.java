package com.guarented.ecommerce.pageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;





import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.MongoDBConnect;
import com.guarented.ecommerce.commonUtils.TestDataSet;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pages.CandidatePoolPage;
import com.guarented.ecommerce.pages.HomePage;
import com.guarented.ecommerce.pages.LoginPage;
import com.spire.base.controller.Assertion;
import com.spire.base.controller.Logging;

import java.io.PrintStream;





import static org.testng.Assert.*;

public class HomePageUtils extends HomePage {
	CommonUtils commonUtils = new CommonUtils();
	String customer;
	static String firstRecruiterInDropDown;

	public HomePageUtils(WebDriver driver) {
		super(driver);
	}

	/*
	 * public void validateHomePage() throws InterruptedException {
	 * 
	 * Logging.log("Checking Popular Pool Label in Home Page");
	 * Assertion.assertTrue(cu.isElementPreset(popularPoolLabel, driver),
	 * "Popular Pool Label element is not present"); Logging.log(
	 * "Checking Open Requisition Label in Home Page");
	 * Assertion.assertTrue(cu.isElementPreset(openRequisitionLabel, driver),
	 * "Open Requisition Label element is not present");
	 * 
	 * }
	 */

	public void validateHomePage(String customer) throws InterruptedException {

		if (customer.equalsIgnoreCase("AccentureGlobal")) {
			System.out.println("Accenture Global");
			Logging.log("Checking advance search option is in center of screen");
			Assert.assertTrue(commonUtils.isElementPreset(advanceSearch_AccGlobal, driver),
					"advance search element is not present in centre of screen");
			Logging.log("Checking Home tab in Home Page");
			Assert.assertTrue(commonUtils.isElementPreset(homeLink, driver), "Home tab element is not present");

		}else {
		}
			System.out.println("Other");
			Logging.log("Checking advance search option is on top of screen");

			Assert.assertTrue(commonUtils.isElementPreset(advanceSearch, driver),
					"advance search element is not present on top of screen");
			Logging.log("Checking Home tab in Home Page");
			Assert.assertTrue(commonUtils.isElementPreset(homeLink, driver), "Home tab element is not present");

		}
	

	public void validateLogout() {
		Logging.log("Logging out");
		clickDropdownMenuToGetLogoutButton();
		clickLogout();
		Logging.log("Validating logout happened or not");
		LoginPage lp = new LoginPage(driver);
		Assertion.assertTrue(!commonUtils.isElementPreset(homeLink, driver),
				"After clicking logout button ,Home still available");
		Assertion.assertTrue(commonUtils.isElementPreset(lp.textMsg, driver), "Login Page not found after Logout");
	}

	public ArrayList<String> getSrStatusListInDropDown() throws InterruptedException {
		List<WebElement> list = getSrStatusList();
		ArrayList<String> SRStatusDropDwnList = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			SRStatusDropDwnList.add(list.get(i).getText());
		}
		return SRStatusDropDwnList;
	}

	public void validateSRStatusListInDropDown() throws InterruptedException {

		ArrayList<String> SRStatusDropDwnList = getSrStatusListInDropDown();
		System.out.println("SRStatusDropDwnList : " + SRStatusDropDwnList);
		System.out.println("-------------");
		System.out.println("SpireConstants.SR_STATUS_LIST : " + GuarentedConstants.SR_STATUS_LIST);
		Logging.log("Asserting that SR Status list is available or not");
		Assertion.assertTrue(SRStatusDropDwnList.size() >= 1, "SR Status list is not Available");
		Logging.log("Verifying SR Status list Data ");
		boolean flag;
		flag = SRStatusDropDwnList.containsAll(GuarentedConstants.SR_STATUS_LIST);
		System.out.println("flag : " + flag);
		Assertion.assertTrue(flag, "SR Status list is not as per expectation");

	}

	public ArrayList<String> getSrStatusListResultGrid() throws InterruptedException {
		List<WebElement> list = getSrStatusListInResultGrid();
		ArrayList<String> SRStatusListInResultGrid = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			SRStatusListInResultGrid.add(list.get(i).getText());
		}
		return SRStatusListInResultGrid;
	}

	public void validateSRStatusListInResultGrid() throws InterruptedException {

		ArrayList<String> SRStatusListInGrid = getSrStatusListResultGrid();
		System.out.println("SRStatusListInGrid : " + SRStatusListInGrid);
		System.out.println("-------------");
		System.out.println("SpireConstants.DEFAULT_SR_STATUS : " + GuarentedConstants.DEFAULT_SR_STATUS);
		Logging.log("Verifying SR Status list Data ");
		boolean flag;
		for (String status : SRStatusListInGrid) {
			System.out.println("Status : " + status);
			flag = status.contains(GuarentedConstants.DEFAULT_SR_STATUS);
			Assertion.assertTrue(flag, "SR Status is not as per expectation");
		}

	}

	public void validatePopularPoolCountClick() throws InterruptedException {
		boolean flag1, flag2;
		try {
			clickFirstPopularPoolCount();
			flag1 = true;
		} catch (Exception e) {
			flag1 = false;
		}
		Assertion.assertTrue(flag1, "Popular Pool Not found");

		CandidatePoolPage cpp = new CandidatePoolPage(driver);

		try {
			cpp.getCandidatePoolLabel();
			flag2 = true;
		} catch (Exception e) {
			flag2 = false;
		}

		Assertion.assertTrue(flag2, "After Clicking  Popolar Pool count, Candidate Pool Page not found");
	}

	public void clickCandidateSearch() throws InterruptedException {
		clickCandidateManagement();
		clickCandSearch();
		Thread.sleep(5000);
	}

	public void goToAdvanceSearchPage(String customerName) {
		clickAdvanceSearch(customerName);

	}

	public List<String> getReqSuggestedList(String customerName) throws InterruptedException {

		List<String> reqList = new ArrayList<String>();
		List<WebElement> wl = getReqSuggestedListWebElement(customerName);

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("(//div[contains(text(),'Req ID')])[1]"))).build().perform();
		System.out.println("Rahul Test : " + driver
				.findElement(By.xpath("(//*[contains(@id,'ui-select-choices-row-')]/a/span)[1]")).getText().trim());

		String sr;

		for (int i = 1; i <= wl.size(); i++) {
			System.out.println("(//*[contains(@id,'ui-select-choices-row-')]/a/span)[" + i + "]");
			sr = driver.findElement(By.xpath("(//*[contains(@id,'ui-select-choices-row-')]/a/span)[" + i + "]"))
					.getText().trim();
			System.out.println("SR : " + sr);
			reqList.add(sr);
		}

		/*
		 * for(WebElement we: wl){ sr=we.getText().trim(); System.out.println(
		 * "SR : "+sr); reqList.add(sr); }
		 */
		return reqList;
	}

	public void validateReqSuggestedList(String customerName, String searchReq) throws InterruptedException {
		boolean flag = false;
		List<String> reqList = getReqSuggestedList(customerName);
		System.out.println("reqList size : " + reqList.size());
		for (String req : reqList) {
			System.out.println(req.toLowerCase());
			System.out.println(searchReq.toLowerCase());
			if (req.toLowerCase().contains(searchReq.toLowerCase())) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		Assert.assertTrue(flag, "Suggested Req list is not as per expectation");
	}

	public void clickFirstReqInDropDownList(String customerName) throws InterruptedException {

		List<WebElement> wl = getReqSuggestedListWebElement(customerName);
		WebElement we = wl.get(0);
		commonUtils.mouseOverToElement(driver, we);
		we.click();
	}

	public void validateReqInResultGrid(String customerName, String searchReq) {
		List<WebElement> wl = getReqIdsInResultGrid(customerName);
		boolean flag = false;
		for (WebElement we : wl) {
			System.out.println("SR in Result Grid : " + we.getText());
			if ((we.getText().trim().split(":"))[1].trim().equalsIgnoreCase(searchReq)) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		Assert.assertTrue(flag, "Result in req grid is not as per searched req");
	}

	public String getFirstReqIdInDropdownList(String customerName) throws InterruptedException {
		String reqId;
		List<String> list = getReqSuggestedList(customerName);
		reqId = list.get(0);
		return reqId;

	}

	public String getFirstReqIdInReqGrid(String customerName) throws InterruptedException {
		String reqIdText, reqId;
		List<WebElement> list = getReqIdsInResultGrid(customerName);
		System.out.println("list size :" + list.size());
		reqIdText = list.get(0).getText().trim();
		reqId = (reqIdText.split(":"))[1].trim();
		System.out.println("reqId: " + reqId);

		return reqId;

	}

	
	public void searchByRecruiterEmailId(String customerName, String searchParameter) throws InterruptedException {
		if (customerName.equalsIgnoreCase("ABC")) {
			// Customsied one if required		  
			
		} else {
			enterRecruiterEmailId(searchParameter);
		}
		
		firstRecruiterInDropDown=driver.findElement(firstRecruiterInDropDownList).getText();
		System.out.println("firstRecruiterInDropDown : "+firstRecruiterInDropDown);
		commonUtils.explicitWait(driver, firstRecruiterInDropDownList, 30L);
		driver.findElement(firstRecruiterInDropDownList).click();
		//driver.findElement(firstRecruiterInDropDownList).sendKeys(Keys.ENTER);
		clickSubmitButton();
		commonUtils.explicitWait(driver, RequisitionInResultGrid, 30L);
		
		}
	
	public void validateResultInMongoDB(String customerName,String mongo_uri,String DBName,String CollectionName,String uniqueKey,String keyValue,String atrributeName) throws InterruptedException{
		MongoDBConnect mng=new MongoDBConnect();
		Thread.sleep(10000);
		String recEmailInDB=mng.getFlatAttribute(mongo_uri, DBName, CollectionName, uniqueKey, keyValue, atrributeName);
		System.out.println("recEmailInDB :"+recEmailInDB);
		System.out.println("firstRecruiterInDropDown : >>> "+firstRecruiterInDropDown);
 		  Assert.assertTrue(firstRecruiterInDropDown.equalsIgnoreCase(recEmailInDB), "Requisition does not belong to searched Recruiter");
	
	}

	public List<String> searchByExperiencernage(String customerName, String searchParameter[])
			throws InterruptedException {
		if (customerName.equalsIgnoreCase("AccenturePhilBPO")) {
			commonUtils.explicitWait(driver, By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[6]"), 30L); 
			Actions actions = new Actions(driver);
		    WebElement ExperienceTextbox = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[6]"));
		    actions.moveToElement(ExperienceTextbox);
		    actions.click().build().perform();
		    List<WebElement> wl = enterExperience(searchParameter);
			System.out.println("list size :" + wl.size());
			String exp = wl.get(0).getText().replaceFirst("Experience: ", "");
			System.out.println("exp" + exp);
		    return null;
		} else {
		// List<>enterExperience(searchParameter);
		List<WebElement> wl = enterExperience(searchParameter);
		System.out.println("list size :" + wl.size());
		String exp = wl.get(0).getText().replaceFirst("Experience: ", "");

		System.out.println("exp" + exp);
		return null;
		}
	}

	public void typeInJobTitleTextBox(String customerName, String searchParameter) throws InterruptedException {
		if (customerName.equalsIgnoreCase("ABC")) {
			// put customized action here
		} else {
			getJobTitleTextBox().click();
			getJobTitleTextBox().sendKeys(searchParameter);
			Thread.sleep(3000);
		}
	}

	public void validateJobTitleInResultGrid(String customerName, String JobTitle,TestDataSet testdata) throws InterruptedException {
		if (customerName.equalsIgnoreCase("AccenturePhilTech") || customerName.equalsIgnoreCase("FIL")) {
			commonUtils.explicitWait(driver,
					By.xpath("//div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@ng-if,'JOB_TITLE')]/input"), 30L);
					typeInJobTitleTextBox(customerName, JobTitle);
					//String expectedresult = driver.findElement(By.xpath("//div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@ng-if,'JOB_TITLE')]/input")).getText();
					String expectedresult = testdata.getvalue1();
					System.out.println("expectedresult :" + expectedresult);
					Logging.log("Entering the Job Title");
					Thread.sleep(2000);
					commonUtils.explicitWait(driver,
							By.xpath("//span[contains(@title,'Designation')]/b | //div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@title,'Title')] | //span[contains(text(),'Title')]"), 30L);
					clickFiltersIcon().click();
					Thread.sleep(3000);					
					String Actualresult = driver.findElement(By.xpath("//span[contains(@title,'Designation')]/b | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/ul/li[7]/div/div/div[3]/div/b | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div/div[3]/span/b"))
							.getText();
					System.out.println("Actualresult :" + Actualresult);
					Logging.log("Asserting the Requisition Status selected");
					Assert.assertEquals(Actualresult, expectedresult);
					System.out.println("test case pass");
		}else if (customerName.equalsIgnoreCase("IDFC")) {
			commonUtils.explicitWait(driver,
					By.xpath("//div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@ng-if,'JOB_TITLE')]/input"), 30L);
					typeInJobTitleTextBox(customerName, JobTitle);
					Logging.log("Entering the Job Title");
					Thread.sleep(2000);
					commonUtils.explicitWait(driver,
							By.xpath("//div[contains(@title,'Designation')] | //span[contains(@title,'Title')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@title,'Title')]"), 30L);
					clickFiltersIcon().click();
					Thread.sleep(3000);					
					String Actualresult = driver.findElement(By.xpath("//span[contains(@title,'Title')]/b | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/ul/li[7]/div/div/div[3]/div/b | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div/div[3]/span/b"))
							.getText();
					System.out.println("Actualresult :" + Actualresult);
					String expected = driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div/div/div[2]/span"))
							.getAttribute("title");
					System.out.println("expected :" + expected);
					Logging.log("Asserting the Requisition Status selected");
					String[] FinalResult = expected.split(":");
					System.out.println("FinalResult :" + FinalResult[1]);
				    Assert.assertTrue(FinalResult[1].trim().equalsIgnoreCase(Actualresult), "jobtitle does not match in result");
					System.out.println("test case pass");
		}else if (customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps") ) {
			commonUtils.explicitWait(driver,
					By.xpath("//div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@ng-if,'JOB_TITLE')]/input"), 30L);
					typeInJobTitleTextBox(customerName, JobTitle);
					//String expectedresult = driver.findElement(By.xpath("//div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@ng-if,'JOB_TITLE')]/input")).getText();
					String expectedresult = testdata.getvalue1();
					System.out.println("expectedresult :" + expectedresult);
					Logging.log("Entering the Job Title");
					Thread.sleep(2000);
					commonUtils.explicitWait(driver,
							By.xpath("//span[contains(@title,'Title')]/b | //div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@title,'Title')] | //span[contains(text(),'Title')]"), 30L);
					clickFiltersIcon().click();
					Thread.sleep(3000);					
					String Actualresult = driver.findElement(By.xpath("//span[contains(@title,'Title')]/b | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/ul/li[7]/div/div/div[3]/div/b | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div/div[3]/span/b"))
							.getText();
					System.out.println("Actualresult :" + Actualresult);
					Logging.log("Asserting the Requisition Status selected");
					Assert.assertEquals(Actualresult, expectedresult);
					System.out.println("test case pass");
		} else {
		commonUtils.explicitWait(driver,
		By.xpath("//div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@ng-if,'JOB_TITLE')]/input"), 30L);
		typeInJobTitleTextBox(customerName, JobTitle);
		Logging.log("Entering the Job Title");
		Thread.sleep(2000);
		commonUtils.explicitWait(driver,
				By.xpath("//div[contains(@title,'Designation')] | //span[contains(@title,'Title')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@title,'Title')]"), 30L);
		clickFiltersIcon().click();
		Thread.sleep(3000);
		List<WebElement> wl = getJobTitleInResultGrid(customerName);
		Logging.log("Validating the Job Title in Requisition Grid");
		boolean flag = false;
		for (int i = 0; i < wl.size(); i++) {
			System.out.println("JobTitle in Result Grid : " + wl.get(i).getText());
			if ((wl.get(i).getText().trim().split(":"))[1].trim().equalsIgnoreCase(JobTitle)) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		}
		clickDropdownMenuToGetLogoutButton();
		Thread.sleep(2000);
	}

	public void clickelementinORGUNITLEVEL1facet() throws InterruptedException {
		Thread.sleep(3000);
		getSearchelementinORGUNIT().click();
		Logging.log("Clicking the ORGUNITLEVEL in Facet");
		clickDropdownMenuToGetLogoutButton();
	}

	public void clickelementinLocationfacet() throws InterruptedException {
		commonUtils.explicitWait(driver,
				By.xpath("//label[starts-with(@class,'col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus')][1]"),
				30L);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[starts-with(@class,'col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus')][1]"))
				.click();
		Thread.sleep(3000);
		getSearchelementinLocation().click();
		clickFiltersIcon().click();
	}

	public void validateLocationInResultGrid(String customerName) throws InterruptedException {
		clickelementinLocationfacet();
		Logging.log("Clicking the Location in Facet");
		List<WebElement> wl = getLocationResultGrid(customerName);
		boolean flag = false;
		Logging.log("Validating the result in Requisition Grid");
		for (int i = 0; i < wl.size(); i++) {
			System.out.println("Location in Result Grid : " + wl.get(i).getText());
			if ((wl.get(i).getText().trim().split(":"))[1].trim().equalsIgnoreCase(customerName)) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		clickDropdownMenuToGetLogoutButton();
	}

	public void clickelementinJobLevelfacet() throws InterruptedException {
		commonUtils.explicitWait(driver,
				By.xpath("//label[starts-with(@class,'col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus')][1]"),
				30L);
		driver.findElement(
				By.xpath("//label[starts-with(@class,'col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus')][1]"))
				.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus']")).click();
		Thread.sleep(3000);
		getSearchelementinJoblevel().click();
		Logging.log("Clicking the JobLevel in Facet");
		clickFiltersIcon().click();
		clickDropdownMenuToGetLogoutButton();

	}

	public void clickelementinEmploymentTypefacet() throws InterruptedException {
		commonUtils.explicitWait(driver,
				By.xpath("//label[starts-with(@class,'col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus')][1]"),
				30L);
		driver.findElement(
				By.xpath("//label[starts-with(@class,'col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus')][1]"))
				.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='col-sm-2 col-md-2 collapse-icon glyphicon glyphicon-minus']")).click();
		Thread.sleep(3000);
		getSearchelementinEmploymentType().click();
		Logging.log("Clicking the EmploymentType in Facet");
		clickFiltersIcon().click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//span[contains(@title,'Location')]")) != null) {
			System.out.println("TestCase Pass");
		} else {
			System.out.println("TestCase Fail");
		}
		Thread.sleep(3000);
		clickDropdownMenuToGetLogoutButton();
		System.out.println("Done");
	}

	public void clickelementinBusinessUnitfacet(String customerName) throws InterruptedException {
		if (customerName.equalsIgnoreCase("IDFC")) {
			commonUtils.explicitWait(driver, By.xpath("//label[@id='candCbLabel_4_1']"), 30L);
			commonUtils.mouseOverToElement(this.driver, driver.findElement(By.xpath("//label[@id='candCbLabel_4_1']")));
			commonUtils.moveVerticalScrollBar(driver, 300);
			driver.findElement(By.xpath("//label[@id='candCbLabel_4_1']")).click();
		} else {
			commonUtils.explicitWait(driver, By.xpath("//label//span[contains(@id,'candCbSpan_0_3')]"), 90L);
			commonUtils.mouseOverToElement(this.driver, getBusinessUnitFacet());
			commonUtils.moveVerticalScrollBar(driver, 300);
			getBusinessUnitFacet().click();
		}
		Logging.log("Clicking the BusinessUnit in Facet");		
		commonUtils.explicitWait(driver, By.xpath("//span[contains(@title,'Location')] | //div[contains(@title,'Location')]"), 30L);
		if (driver.findElement(By.xpath("//span[contains(@title,'Location')] | //div[contains(@title,'Location')]")) != null) {
			Thread.sleep(3000);
			// if(driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/ul/li[1]/div/div/div[3]/div"))!=
			// null){
			// div[contains(@title,'Location')] |
			System.out.println("Testcase Pass");
		} else {
			System.out.println("TestCase Fail");
		}
		clickDropdownMenuToGetLogoutButton();
		System.out.println("Done");
	}

	public void clickAutomatchReporticon() throws Exception {
		getAutomatchReport().click();
		Logging.log("Clicking the Automatch Report Icon ");
		Thread.sleep(3000);
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if ("Download Successful".equals(driver.findElement(By.cssSelector("span.ng-binding")).getText()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		try {
			assertEquals(driver.findElement(By.cssSelector("span.ng-binding")).getText(), "Download Successful");
		} catch (Error e) {
			PrintStream verificationErrors = null;
			verificationErrors.append(e.toString());
		}
		Thread.sleep(9000);
		/*
		 * String downloadPath = "C:\\Users\\Supraja K\\Downloads";
		 * 
		 * File getLatestFile = getLatestFilefromDir(downloadPath); String
		 * fileName = getLatestFile.getName(); System.out.println("fileName :"
		 * +fileName); Assert.assertTrue(isFileDownloaded_Ext(downloadPath,
		 * ".csv"), "Failed to download document which has extension .csv");
		 * Thread.sleep(9000);
		 */
		clickDropdownMenuToGetLogoutButton();
		System.out.println("Testcase Pass");
	}

	private File getLatestFilefromDir(String downloadPath) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isFileDownloaded_Ext(String downloadPath, String string) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clickSRListIcon() throws Exception {
		getSRList().click();
		Logging.log("Clicking the SR List Icon ");
		Thread.sleep(3000);
		clickDropdownMenuToGetLogoutButton();
		System.out.println("Testcase Pass");

	}

	public void clickelementinRequisitionstatus() throws InterruptedException {
		SelectRequisitionStatus().click();
		Thread.sleep(3000);

	}
	
	public void clickelementClosedinRequisitionstatus() throws InterruptedException {
		SelectRequisitionStatusClosed().click();
		Thread.sleep(3000);

	}
	public void validateRequisitionstatusInResultGrid(String customerName) throws InterruptedException {
		clickelementinRequisitionstatus();
		Logging.log("Selecting the Requisition Status");
		if (customerName.equalsIgnoreCase("FIL") || customerName.equalsIgnoreCase("IDC")) {
			driver.findElement(By.id("req_status")).click();
		} 
		
		else if(customerName.equalsIgnoreCase("AccenturePhilTech")){	
			driver.findElement(By.id("req_status")).click();
			driver.findElement(By.xpath("//label[contains(@for,'list_2')]")).click();
		}
		
		else
		{
			
		}
		Thread.sleep(3000);
		clickSubmitButton();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span > img")).click();
		String expectedresult = driver.findElement(By.xpath("//span[contains(text(),'Status')]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("//span[contains(@class,'search-criteria ng-binding')]"))
				.getText();
		System.out.println("Actualresult :" + Actualresult);
		Logging.log("Asserting the Requisition Status selected");
		Assert.assertEquals(Actualresult, expectedresult);
		System.out.println("test case pass");

	}
	
	public void validateClosedRequisitionstatusInResultGrid(String customerName) throws InterruptedException {
		clickelementClosedinRequisitionstatus();
		Logging.log("Selecting the Requisition Status");
		if (customerName.equalsIgnoreCase("FIL")) {
			driver.findElement(By.id("req_status")).click();
		} else 
		{
			
		}
		Thread.sleep(3000);
		clickSubmitButton();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span > img")).click();
		String expectedresult = driver.findElement(By.xpath("//span[contains(text(),'Status')]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("//span[contains(@class,'search-criteria ng-binding')]"))
				.getText();
		System.out.println("Actualresult :" + Actualresult);
		Logging.log("Asserting the Requisition Status selected");
		Assert.assertEquals(Actualresult, expectedresult);
		System.out.println("test case pass");

	}
	
	
	public void clickelementinIndianServiceLeader() throws InterruptedException {

		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		WebElement Textbox = driver.findElement(By.xpath("//div[@class='col-md-12 col-sm-12 col-xs-12 ng-scope']"));
		actions.moveToElement(Textbox);
		actions.moveToElement(getIndianServiceLeaderInFacet());
		actions.click().build().perform();
		Logging.log("clicking IndianServiceLeader in Facet");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[1]/label/span/img"))
				.click();
		clickFiltersIcon();
		Thread.sleep(3000);
		String expectedresult = driver.findElement(By.xpath("//label[contains(@id,'candCbLabel_0_1')]")).getText();
		System.out.println("expectedresult :" + expectedresult);
		String Actualresult = driver.findElement(By.xpath("//label[contains(@id,'candCbLabel_0_1')]")).getText();
		System.out.println("Actualresult :" + Actualresult);
		Assert.assertEquals(Actualresult, expectedresult);
		System.out.println("test case pass");
		clickDropdownMenuToGetLogoutButton();
	}

	public void clickelementinLocationfacetforFII() throws InterruptedException {
		Thread.sleep(3000);
		getSearchelementinLocationforFII().click();
		Thread.sleep(5000);
		clickFiltersIcon().click();
	}

	public void validateLocationInResultGridforFII(String customerName) throws InterruptedException {
		clickelementinLocationfacetforFII();
		Thread.sleep(3000);
		if (customerName.equalsIgnoreCase("AccenturePhilTech")){
			String expectedresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[1]/div/div/div/div[1]/div/div/div/ul/li[3]/div/label[1]")).getAttribute("title").toLowerCase();
			System.out.println("expectedresult :" +expectedresult.trim());
			String[] FinalResult = expectedresult.split(" ");
			System.out.println("FinalResult :" + FinalResult[0]);
			String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div/div[4]/span/b")).getText().toLowerCase();
			System.out.println("Actualresult :" + Actualresult.trim());
			
			//Assert.assertEquals(expectedresult, FinalResult);
			Assert.assertTrue(FinalResult[0].trim().equalsIgnoreCase(Actualresult), "Location does not match in result");
			
			
		}else{
		List<WebElement> wl = getLocationResultGridforFII(customerName);
		boolean flag = false;
		for (int i = 0; i < wl.size(); i++) {
			System.out.println("Location in Result Grid : " + wl.get(i).getText());
			if ((wl.get(i).getAttribute("title").trim().split(":"))[1].trim().equalsIgnoreCase(customerName)) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		}
		
		clickDropdownMenuToGetLogoutButton();
	}

	private String FinalResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public void typeInCapabilityTextBox(String customerName, String searchParameter) throws InterruptedException {
	if (customerName.equalsIgnoreCase("ABC")) {
			// put customized action here
		} else {
			/*Thread.sleep(10000);
			WebElement TextBox = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/input"));
		//	driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/input")).clear();
		//	driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/input")).click();
			Actions actions = new Actions(driver);
			actions.doubleClick(TextBox);*/
			Thread.sleep(8000);
			WebElement ele=this.driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/input"));
			Actions action=new Actions(driver);
			action.doubleClick(ele);
			System.out.println("clicked 1st time");
			ele.sendKeys(searchParameter);
			Thread.sleep(10000);
			commonUtils.explicitWait(driver, By.xpath(".//*[@id='ui-select-choices-row-0-0']/a/span"), 30L);
			driver.findElement(By.xpath(".//*[@id='ui-select-choices-row-0-0']/a/span")).click();
			clickSubmitButton();
			Thread.sleep(3000);
			
		}
	}
	public void typeDealinTextbox(String clientname, String searchParameter) throws InterruptedException{
		if (clientname.equalsIgnoreCase("ABC")) {
			// put customized action here
		} else {
			Thread.sleep(8000);
			
			WebElement ele=this.driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/input"));
			Actions action=new Actions(driver);
			action.doubleClick(ele);
			System.out.println("clicked 1st time");
			ele.sendKeys(searchParameter);
			Thread.sleep(10000);
			commonUtils.explicitWait(driver, By.xpath(".//*[@id='ui-select-choices-row-1-1']/a/span"), 30L);
			driver.findElement(By.xpath(".//*[@id='ui-select-choices-row-1-1']/a/span")).click();
			clickSubmitButton();
			Thread.sleep(3000);
			
			}
	}

	public void typeInReqNoTextBox(String customerName, String searchParameter) throws InterruptedException {
		if(customerName.equalsIgnoreCase("AccenturePhilBPO") || customerName.equalsIgnoreCase("AccentureIndOps")) {
			commonUtils.explicitWait(driver, By.xpath("//div[contains(text(),'Requisition I')]/following-sibling::div/div/div/div/div/input"), 30L); 
			Actions actions = new Actions(driver);
		    WebElement Textbox = driver.findElement(By.xpath("//div[contains(text(),'Requisition I')]/following-sibling::div/div/div/div/div/input"));
		    actions.moveToElement(Textbox);
		    actions.click().build().perform();							
				}
		else if(customerName.equalsIgnoreCase("AccenturePhilTech")){
			commonUtils.explicitWait(driver, By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[6]/div[2]/div/div/div/div/input"), 30L); 
			Actions actions = new Actions(driver);
		    WebElement Textbox = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[6]/div[2]/div/div/div/div/input"));
		    actions.moveToElement(Textbox);
		    actions.click().build().perform();
		    Thread.sleep(10000);
			getRequisitionNoTextBox().sendKeys(searchParameter);
				}
		else if(customerName.equalsIgnoreCase("IDC") || customerName.equalsIgnoreCase("FIL") ){
			/*commonUtils.explicitWait(driver, By.xpath("//div[contains(text(),'SR NO')]/following-sibling::div/div/div/div/div/input"), 30L); 
			Actions actions = new Actions(driver);
		    WebElement Textbox = driver.findElement(By.xpath("//div[contains(text(),'SR NO')]/following-sibling::div/div/div/div/div/input"));
		    actions.moveToElement(Textbox);
		    actions.click().build().perform();	*/		
			Thread.sleep(10000);
			getRequisitionNoTextBox().sendKeys(searchParameter);
			commonUtils.explicitWait(driver, By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span"), 30L); 
			
			driver.findElement(By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span")).click();
			String actual=driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/span/span/span/span[2]/span")).getText();
			
			System.out.println("actual is  "+actual);
			//String value=driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/span/span/span/span[2]/span")).getAttribute("value");
			//System.out.println("value "+value);
			//commonUtils.explicitWait(driver, By.xpath("//div[contains(text(),'Requisition I')]/following-sibling::div/div/div/div/div/input"), 30L); 
			
			Logging.log("Entering the Requisition Number");
			Thread.sleep(3000);	
			clickSubmitButton();
			Thread.sleep(5000);
			driver.findElement(By
					.xpath(".//*[@id='ngViewDiv']/div/div/div[1]/div[1]/label/span/img | //img[contains(@src,'assets/images/criticalReq.png')]"))
					.click();
			Thread.sleep(5000);
			String expected=driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[3]/span")).getText().trim();
		System.out.println("expected is "+ expected);	
		String[] FinalResult = expected.split(": ");
		System.out.println("FinalResult :" + FinalResult[1].trim());
		Assert.assertTrue(FinalResult[1].trim().equalsIgnoreCase(actual), "SR NO does not match in result");
		
		}
		
		else if(customerName.equalsIgnoreCase("IDFC"))  {
			
			Thread.sleep(10000);
			getRequisitionNoTextBox().sendKeys(searchParameter);
			commonUtils.explicitWait(driver, By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span"), 30L); 
			
			driver.findElement(By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span")).click();
			String actual=driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/span/span/span/span[2]/span")).getText();
			
			System.out.println("actual is  "+actual);
			Logging.log("Entering the Requisition Number");
			Thread.sleep(3000);	
			clickSubmitButton();
			Thread.sleep(5000);
			driver.findElement(By
					.xpath(".//*[@id='ngViewDiv']/div/div/div[1]/div[1]/label/span/img | //img[contains(@src,'assets/images/criticalReq.png')]"))
					.click();
			Thread.sleep(5000);
			String expected=driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/span")).getText().trim();
		System.out.println("expected is "+ expected);
		String[] FinalResult = expected.split(":");
	    System.out.println("FinalResult :" + FinalResult[1].trim());
	    Assert.assertTrue(FinalResult[1].trim().equalsIgnoreCase(actual), "SR NO does not match in result");
			
				}
				
		 else
		 {			 
		 //Thread.sleep(45000);
		//commonUtils.explicitWait(driver,By.xpath("//div[contains(text(),'SR NO')]/following-sibling::div/div/div/div/div/input | //div[contains(text(),'Requisition I')]/following-sibling::div/div/div/div/div/input"),
		//		30L);
		driver.findElement(RequisitionNoTextBoxLabel).click();
		getRequisitionNoTextBox().sendKeys(searchParameter);
		 }
		Logging.log("Entering the Requisition Number");
		Thread.sleep(3000);
}


	public void validateRequisitionInResultGrid(String customerName, String searchParameter) throws InterruptedException {
		typeInReqNoTextBox(customerName, searchParameter);
		if(customerName.equalsIgnoreCase("ABC")){
			
		}else{
		String reqToClick=driver.findElement(firstReqInDropDownList).getText();
		System.out.println("reqToClick :"+reqToClick);
		driver.findElement(firstReqInDropDownList).click();
		clickSubmitButton();
		
		Thread.sleep(2000);
		driver.findElement(By
				.xpath(".//*[@id='ngViewDiv']/div/div/div[1]/div[1]/label/span/img | //img[contains(@src,'assets/images/criticalReq.png')]"))
				.click();
		List<WebElement> wl = getReqNoinResultGrid(customerName);
		int resultListSize=wl.size();
		Logging.log("Validating the Requisition Number in Requisition Grid");
		boolean flag = false;
		//for (int i = 0; i < wl.size(); i++) {
		for (WebElement we:wl){
			System.out.println("Requisition Number in Result Grid : " + we.getText());
			try {
				if ((we.getText().trim().split(":"))[1].trim().equalsIgnoreCase(reqToClick)) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			} catch (Exception e) {
				if ((we.getText().trim().equalsIgnoreCase(reqToClick))) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
			
		}
		System.out.println("Flag : "+flag);
		boolean finalResult=false;
		if(flag && resultListSize==1)
			finalResult=true;
		else finalResult=false;
		System.out.println("finalResultFlag : "+finalResult);
		Assert.assertTrue(finalResult, "Either searched result is not matching or its appearing multiple times");
		}
		/*
		 * driver.findElement(By.cssSelector(
		 * "div.dropdown-toggle.dropdown > span.glyphicon.glyphicon-menu-down"
		 * )).click(); driver.findElement(By.linkText("Logout")).click();
		 */
	}

	public void typeInLocationTextBox(String customerName, String searchParameter) throws InterruptedException {
		commonUtils.explicitWait(driver, By.xpath("//div[contains(text(),'Location')]/following-sibling::div/div/div/div/div/input | html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[4]/div[2]/div/div/div/div/input"), 30L); 
		
		Actions actions = new Actions(driver);
	    WebElement Textbox = driver.findElement(By.xpath("//div[contains(text(),'Location')]/following-sibling::div/div/div/div/div/input | html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[4]/div[2]/div/div/div/div/input"));
	    actions.moveToElement(Textbox);
	    actions.click().build().perform();
	    
	    JavascriptExecutor js =(JavascriptExecutor) driver;
	    js.executeScript("document.querySelector(\"[type='text'][class='ui-select-search input-xs ng-pristine ng-valid ng-empty ng-touched']\").click();");
		System.out.println("Clicked");
	    String Location = searchParameter;
	    actions.sendKeys(Textbox, Location).perform();
		
		  //  getLocationTextBox().click();
		 //	getLocationTextBox().sendKeys(searchParameter);
			commonUtils.explicitWait(driver, By.xpath("//div[@id='ui-select-choices-row-2-3']/a"), 30L);
			driver.findElement(By.xpath("//div[@id='ui-select-choices-row-2-3']/a")).click();
			Thread.sleep(3000);
			clickSubmitButton();
			Thread.sleep(3000);
		
	}

	public void validateLocationInResultGrid(String customerName, String searchLocation) throws InterruptedException {
		typeInLocationTextBox(customerName, searchLocation);
		Logging.log("Entering the Location");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[contains(@src,'assets/images/criticalReq.png')]")).click();
		if (customerName.equalsIgnoreCase("IDC")) {
			String expectedresult =searchLocation;
			System.out.println("expectedresult :" +expectedresult.trim());
			String Actualresult = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[6]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[4]/span")).getAttribute("title");
			System.out.println("Actualresult :" + Actualresult.trim());
			String[] FinalResult = Actualresult.split(": ");
			System.out.println("FinalResult :" + FinalResult[1].trim());
			//Assert.assertEquals(expectedresult, FinalResult);
			Assert.assertTrue(FinalResult[1].trim().equalsIgnoreCase(expectedresult), "Location does not match in result");
		
		}else{
		List<WebElement> wl = getLocationfromResultGrid(customerName);
		Logging.log("Validating the Location in Requisition Grid");
		boolean flag = false;
		for (int i = 0; i < wl.size(); i++) {
			System.out.println("Location in Result Grid : " + wl.get(i).getText());
			if ((wl.get(i).getText().trim().split(":"))[1].trim().equalsIgnoreCase(searchLocation)) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		}
		
	}

	public void LocationTextBox(String customerName, String searchParameter, String searchParameterType)
			throws InterruptedException {
		if (customerName.equalsIgnoreCase("ABC")) {
			// put customized action here
		} else {
			typeInLocationTextBox(customerName, searchParameter);
			commonUtils.explicitWait(driver, By.xpath("//*[contains(@id,'ui-select-choices-row-2-3')]/a"), 30L);
			driver.findElement(By.xpath("//*[contains(@id,'ui-select-choices-row-2-3')]/a")).click();
			Thread.sleep(4000);
		}
	}

	public void clickPlusIconForRequisition(String customerName) throws InterruptedException {
		if (customerName.equalsIgnoreCase("AccentureNA")) {
			commonUtils.explicitWait(driver, By.xpath("//div[contains(@class,'spire-downloadJD-icon')]"));
			getDownloadJDIcon().click();
		} else {
			commonUtils.explicitWait(driver,
					By.xpath(
							"//span[contains(@ng-click,'req.transformedData.show')]/i | //span[contains(@ng-init,'reqData.show')]/i[contains(@class,'glyphicon action-item-glyphicon glyphicon-plus')] | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[8]/span[2]/i"),
					60L);
			driver.findElement(By
					.xpath("//span[contains(@ng-click,'req.transformedData.show')]/i | //span[contains(@ng-init,'reqData.show')]/i[contains(@class,'glyphicon action-item-glyphicon glyphicon-plus')] | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[8]/span[2]/i"))
					.click();
			Thread.sleep(3000);
			Logging.log("Click on the download JD icon");
			getDownloadJDIcon().click();
		}
		Thread.sleep(6000);
		System.out.println("test case pass");
		// clickDropdownMenuToGetLogoutButton();
	}

	public void VerifyCandidateCountInRequisitionGrid(String customerName,String searchParameter) throws InterruptedException {
	
			Thread.sleep(10000);
			getRequisitionNoTextBox().sendKeys(searchParameter);
			Thread.sleep(10000);
			if(customerName.equalsIgnoreCase("FIL")){
            commonUtils.explicitWait(driver, By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span"), 30L); 
			
			driver.findElement(By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span")).click();
			}else{
			getRequisitionNoTextBox().sendKeys(Keys.ENTER);
			}
			Logging.log("Entering the Requisition Number");
			Thread.sleep(3000);	
			clickSubmitButton();
			Thread.sleep(5000);
	         driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[7]/span[2]/i | //span[contains(@role,'button')]/i | //div[contains(@class,'action-items-header-div')]/div[10]/span[2]/i")).click();
		//	driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[7]/span[2]/i")).click();
		Thread.sleep(3000);
	
		String parentHandle = driver.getWindowHandle(); // get the current
														// window handle
		
		commonUtils.explicitWait(driver,
				By.xpath(
						".//*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div/div[2]/div[2] | html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li/div/div[2]/div[2] | //div[contains(@class,'spire-statusGroup-div ng-scope')]//span[contains(text(),'Experience Knockout')] | //span[contains(@ng-if,'statusLabel')]//span[contains(text(),'Automatched')]"),
				30L);
		String expectedresult = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div/div[2]/div[2] | html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li/div/div[2]/div[2] | html/body/div[1]/div[2]/div/div/div[4]/div[2]/div[1]/div[1]/ul/li/div/div[2]/div[2]/span | html/body/div[1]/div[2]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/ul/li/div/div[2]/div[3]/span/span/span[2]"))
.getText();
		System.out.println("expectedresult :" + expectedresult);
		driver.findElement(By
				.xpath("html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li/div/div[2]/div[2] | //div[contains(@class,'spire-statusGroup-div ng-scope')]//span[contains(text(),'Experience Knockout')] | //span[contains(@ng-if,'statusLabel')]//span[contains(text(),'Automatched')]"))
				.click();
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)

		}
		String Actualresult = driver
				.findElement(By
						.xpath("html/body/div[1]/div[2]/div/div/div[1]/div[1]/div[1]/div[2]/div | html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/div[2]/div[2]/div/span[2]"))
				.getText();
		System.out.println("Actualresult :" + Actualresult);
		Assert.assertEquals(Actualresult, expectedresult);
		Logging.log("Asserting the candidate count in Requisition Grid");
		System.out.println("TestCase Pass");
		Thread.sleep(3000);
		clickDropdownMenuToGetLogoutButton();
	
	}
	public By CandidateListViewPage = By.xpath("//title[contains(text(),'CandidateList-View')]");

	public WebElement getWindowHandles() {
		commonUtils.explicitWait(driver, CandidateListViewPage, 30L);
		return this.driver.findElement(CandidateListViewPage);
	}

	public void typeInPrimarySkillTextBox(String customerName, String searchParameter) throws InterruptedException {
		if (customerName.equalsIgnoreCase("AccenturePhilBPO")) {
			/*commonUtils.explicitWait(driver,
					By.xpath("//div[contains(text(),'Primary Skill')]/following-sibling::div/div/div/div/div/input | //*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/input | //*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div/div[1]"),
					30L);
			this.driver.findElement(By.xpath("//div[@id='ngViewDiv']/div/div/div/div/div[2]/div/div/div/div/div/div/div[4]/div[2]/div/div/div/div/input")).click();
			this.driver.findElement(By.xpath("//div[@id='ngViewDiv']/div/div/div/div/div[2]/div/div/div/div/div/div/div[4]/div[2]/div/div/div/div/input")).click();
			this.driver.findElement(By.xpath("//div[@id='ngViewDiv']/div/div/div/div/div[2]/div/div/div/div/div/div/div[4]/div[2]/div/div/div/div/input")).sendKeys(searchParameter);*/
			commonUtils.explicitWait(driver, By.xpath("//div[contains(text(),'Primary Skill')]/following-sibling::div/div/div/div/div/input"), 30L); 
			Actions actions = new Actions(driver);
		    WebElement Textbox = driver.findElement(By.xpath("//div[contains(text(),'Primary Skill')]/following-sibling::div/div/div/div/div/input"));
		    actions.moveToElement(Textbox);
		    actions.click().build().perform();
		
		
		} else {
			commonUtils.explicitWait(driver,
					By.xpath("//div[contains(text(),'Primary Skill')]/following-sibling::div/div/div/div/div/input | //*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/input | //*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div/div[1]"),
					30L);
			getPrimarySkillTextBox().click();
			getPrimarySkillTextBox().click();
			getPrimarySkillTextBox().sendKeys(searchParameter);
			Logging.log("Entering the Primary Skill");
			commonUtils.explicitWait(driver, By.xpath("//div[@id='ui-select-choices-row-0-1']/a"), 30L);
			driver.findElement(By.xpath("//div[@id='ui-select-choices-row-0-1']/a")).click();
			clickSubmitButton();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//img[contains(@src,'criticalReq.png')]")).click();
		}
	}

	public void validatePrimarySkillInReqGrid(String customerName, String searchPrimarySkill)
			throws InterruptedException {
		typeInPrimarySkillTextBox(customerName, searchPrimarySkill);
		List<WebElement> wl = getPrimarySkillfromReqGrid(searchPrimarySkill);
		Logging.log("Validating the Primary Skill in Requisition Grid");
		boolean flag = false;
		for (int i = 0; i < wl.size(); i++) {
			System.out.println("Primary Skill in Requisition Grid : " + wl.get(i).getText());
		//	if ((wl.get(i).getText().trim().split(":"))[1].trim().equalsIgnoreCase(searchPrimarySkill))
			if ((wl.get(i).getText().equalsIgnoreCase(searchPrimarySkill)))
			{
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		clickDropdownMenuToGetLogoutButton();
	}
	public void typeInSLLTextBox(String customerName, String searchParameter) throws InterruptedException {
		if (customerName.equalsIgnoreCase("IDC")) {
			Thread.sleep(10000);
			Actions actions = new Actions(driver);
			WebElement SLLTextbox = driver.findElement(By.xpath("//div[contains(text(),'SLL')]/following-sibling::div/div/div/div/div/input"));
			actions.moveToElement(SLLTextbox);			
			actions.click().build().perform();
			
			/*JavascriptExecutor js =(JavascriptExecutor) driver;
		    js.executeScript("document.querySelector(\"div [ng-model='selectedFilters[demandFilter.requestKey]'] div input\").click();");*/
			System.out.println("Clicked");
		    String SLL = searchParameter;
		    actions.sendKeys(SLLTextbox, SLL).perform();
		    
		    commonUtils.explicitWait(driver, By.xpath(".//*[@id='ui-select-choices-row-1-2']/a/span"), 30L);
			driver.findElement(By.xpath(".//*[@id='ui-select-choices-row-1-2']/a/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
			
			//clickSubmitButton();
			Thread.sleep(3000);
		    
		    
		    Logging.log("Entering the Location");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//img[contains(@src,'assets/images/criticalReq.png')]")).click();
			String expectedresult = searchParameter;
			System.out.println("expectedresult :" +expectedresult.trim());
			String Actualresult = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[6]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[3]/span")).getAttribute("title");
			
			
			
			System.out.println("Actualresult :" + Actualresult.trim());
			String[] FinalResult = Actualresult.split(": ");
			System.out.println("FinalResult :" + FinalResult[1].trim());
			//Assert.assertEquals(expectedresult, FinalResult);
			Assert.assertTrue(FinalResult[1].trim().equalsIgnoreCase(expectedresult), "SLL does not match in result");
		    
		    
		}  else {
			/*getSLLTextBox().click();
			getSLLTextBox().click();*/
			getSLLTextBox().sendKeys(searchParameter);
			Logging.log("Entering the Primary Skill");
			getSLLTextBox().sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			String expectedresult = driver
					.findElement(By
							.xpath("html/body/div[1]/div[2]/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div/div/div/span/span/span/span[2]/span"))
					.getText();
			System.out.println("expectedresult :" + expectedresult);
			clickSubmitButton();
			driver.findElement(By.xpath("//img[contains(@src,'assets/images/criticalReq.png')]")).click();
			Thread.sleep(3000);
			String SLLInSelection = driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/span")).getText();
			String[] parts = SLLInSelection.split(": ");
			String Actualresult = parts[1];
			System.out.println("Actualresult :" + Actualresult);
			Assert.assertEquals(Actualresult, expectedresult);
			Logging.log("Asserting the Searched SLL");
			System.out.println("TestCase Pass");

		}
	}
	
	           public void clickPassiveCandidatesIcon() throws InterruptedException{
				commonUtils.explicitWait(driver,By.xpath("//div[contains(@title,'Passive Candidates')]"),30L);
				Thread.sleep(3000);
				String parentHandle = driver.getWindowHandle(); // get the current window handle
				//driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div/div[1]/div[1]/ul/li[1]/div/div[2]/div[7]/div[1]")).click();
				getPassiveCandidatesIcon().click();
				Thread.sleep(3000);
				for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				    
				}
				String Actualresult= driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div")).getText();
			    System.out.println("Actualresult :" +Actualresult);
				Logging.log("Asserting the candidate count");
				System.out.println("TestCase Pass");
				Thread.sleep(3000);
				clickDropdownMenuToGetLogoutButton();
				}
	   //Naresh        
	
	  public void validateDemandChart(String clientName) throws InterruptedException {

	       		// String productName =
	       		// ContextManager.getThreadContext().getProductName();

	       		if (clientName.equalsIgnoreCase("IDFC")) {
	       			commonUtils.explicitWait(driver, accentureIDFCdemandChartLables, 20L);

	       			String demandchartlables = this.driver.findElement(accentureIDFCdemandChartLables).getText();
	       			Assert.assertEquals(demandchartlables.contains("Duplicate"), true);
	       			Assert.assertEquals(demandchartlables.contains("SpireRecommend"), true);
	       			//Assert.assertEquals(demandchartlables.contains("FitmentSelect"), true);
	       			Assert.assertEquals(demandchartlables.contains("Screen Select"), true);
	       			Assert.assertEquals(demandchartlables.contains("ExperienceKnockout"), true);
	       			Assert.assertEquals(demandchartlables.contains("Total"), true);
	       			Assert.assertEquals(demandchartlables.contains("Screen Reject"), true);
	       			Assert.assertEquals(demandchartlables.contains("LocationKnockout"), true);
	       			Logging.log("demand chart values are display properly in home page");

	       		} else if (clientName.equalsIgnoreCase("AccentureNA")) {
	       			commonUtils.explicitWait(driver, accentureNADemandChartLables, 20L);

	       			String accentureNAdemandchartlables = this.driver.findElement(accentureNADemandChartLables).getText();
	       			Assert.assertEquals(accentureNAdemandchartlables.contains("Applied"), true);
	       			Assert.assertEquals(accentureNAdemandchartlables.contains("New"), true);
	       			Assert.assertEquals(accentureNAdemandchartlables.contains("Inactive"), true);
	       			Assert.assertEquals(accentureNAdemandchartlables.contains("Active"), true);
	       			Assert.assertEquals(accentureNAdemandchartlables.contains("Hired"), true);

	       			Logging.log("demand chart values are display properly in  AccentureNA home page");

	       		} else if (clientName.equalsIgnoreCase("IDC")) {
	       			commonUtils.explicitWait(driver, idcDemandChartLables, 20L);
	       			Thread.sleep(10000);

	       			String idcdemandchartlables = this.driver.findElement(idcDemandChartLables).getText();
	       			//this.driver.manage().window().maximize();
	       			
	       			Assert.assertEquals(idcdemandchartlables.contains("Total"), true);
	       			Assert.assertEquals(idcdemandchartlables.contains("Automatch"), true);
	       			Assert.assertEquals(idcdemandchartlables.contains("Tagged"), true);

	       			Logging.log("demand chart values are display properly in  IDC home page");

	       		}else if (clientName.equalsIgnoreCase("AccPhilBPO")) {
	       			commonUtils.explicitWait(driver, accenturePhilBPODemandChartLables, 20L);

	       			String accenturephilbpodemandlables = this.driver.findElement(accenturePhilBPODemandChartLables).getText();
	       			Assert.assertEquals(accenturephilbpodemandlables.contains("Offered"), true);
	       			Assert.assertEquals(accenturephilbpodemandlables.contains("Applied"), true);

	       			Logging.log("demand chart values are display properly in  Accenturephilbpo home page");

	       		}
	       		
	       		else if (clientName.equalsIgnoreCase("FIL")) {
	       			commonUtils.explicitWait(driver, accentureFILDemandChartLables, 20L);

	       			String accentureFILDemanChartLables = this.driver.findElement(accentureFILDemandChartLables).getText();
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("Total"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("ScreenReject"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("ExperienceKnockout"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("Automatched"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("Hired"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("SpireRecommend"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("Duplicate"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("Interview"), true);
	       			Assert.assertEquals(accentureFILDemanChartLables.contains("Offer"), true);
	       			Logging.log("demand chart values are display properly in  AccentureFIL home page");

	       		}
	       			else if (clientName.equalsIgnoreCase("AccenturePhilTech")) {
		    			commonUtils.explicitWait(driver, AccenturePhilTecDemandChartLables,50L);
                        //commonUtils.explicitWait(driver, By.xpath("//*[contains(@id,'raphael-paper-')]/following-sibling::rect[1]"), 50L);
		    		    Thread.sleep(30000);
		    			String accenturePhiltecdemandchartlables = this.driver.findElement(AccenturePhilTecDemandChartLables)
		    					.getText();
		    			System.out.println("accenturePhiltecdemandchartlables : " +accenturePhiltecdemandchartlables);
		    			Assert.assertEquals(accenturePhiltecdemandchartlables.contains("Total"), true);
		    			Assert.assertEquals(accenturePhiltecdemandchartlables.contains("Automatch"), true);
		    			Assert.assertEquals(accenturePhiltecdemandchartlables.contains("Tagged"), true);
		    			
		                Logging.log("demand chart values are display properly in  AccenturePhilTech home page");


		    		}

	       		else if (clientName.equalsIgnoreCase("IndOPS")) {
	    			commonUtils.explicitWait(driver, indopsDemandChartLables, 20L);

	    			String indopsdemandchartlables = this.driver.findElement(indopsDemandChartLables).getText();
	    			this.driver.manage().window().maximize();
	    			Assert.assertEquals(indopsdemandchartlables.contains("Automatched"), true);
	    			Assert.assertEquals(indopsdemandchartlables.contains("Tech Interviewed"), true);
	    			Assert.assertEquals(indopsdemandchartlables.contains("Offered"), true);
	    			Assert.assertEquals(indopsdemandchartlables.contains("Applied"), true);
	    			Assert.assertEquals(indopsdemandchartlables.contains("HR Interviewed"), true);
	    		

	    			Logging.log("demand chart values are display properly in  Indops home page");}


	       	}
	  public void clickAgingDropdowninHome() {
			this.driver.manage().window().maximize();
			commonUtils.explicitWait(driver, agingDropDowninHomePage, 20L);

			this.driver.findElement(agingDropDowninHomePage).click();
		}
	  public void validateAgingSortOrderinHomePage() throws Exception {
			commonUtils.explicitWait(driver, agingDropDownStartingValueinIDFC, 20L);
			String actualvalue = this.driver.findElement(agingDropDownStartingValueinIDFC).getText();
			System.out.println("leastvalue is " + agingDropDownStartingValueinIDFC);
			Thread.sleep(3000);
			
			
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[2]/ul/li[6]/a")).click();
			Thread.sleep(5000);
			commonUtils.explicitWait(driver, agingDropDownMiddleValueinIDFC, 20L);
			String expectedmiddlevalue = this.driver.findElement(agingDropDownMiddleValueinIDFC).getText();

			
			System.out.println("middle value is " + agingDropDownMiddleValueinIDFC);

			
			
			driver.findElement(By.xpath("//*[@id='homePagePagination']/li[10]/a")).click();
			Thread.sleep(3000);
			commonUtils.explicitWait(driver, agingDropDownEndingValueinIDFC, 20L);
			String expectedvalue = this.driver.findElement(agingDropDownEndingValueinIDFC).getText();

			
			System.out.println("highest value is " + agingDropDownEndingValueinIDFC);

			String[] parts = actualvalue.split(":");
			String part1 = parts[0]; // 004
			String part2 = parts[1]; // 034556
			System.out.println("part1 is " + part1);
			System.out.println("part2 is " + part2);
			String[] middleparts = expectedmiddlevalue.split(":");
			String middlepart1 = parts[0]; // 004
			String middlepart2 = parts[1]; // 034556
			System.out.println("part1 is " + middlepart1);
			System.out.println("part2 is " + middlepart2);
			String[] values = expectedvalue.split(":");
			String value1 = values[0]; // 004
			String value2 = values[1]; // 034556
			System.out.println("value1 is " + value1);
			System.out.println("value2 is " + value2);
			
			int x = Integer.parseInt(part2);
			System.out.println("x value is" + x);
			
			int z = Integer.parseInt(middlepart2);
			System.out.println("z value is" + z);
			
			
			int y = Integer.parseInt(value2);
			System.out.println("y value is" + y);
			if (x > y) {
				Logging.log("aging  dropdown values are sorting order");
			} else if ( y>z) {
				Logging.log("aging  dropdown values are sorting order");
				
			} 
			else if ( x<z) {
				Logging.log("aging  dropdown values are not sorting order");}
				else {
				Logging.log("aging  dropdown values are not sorting order");
			    
			}	
			}
	  public void validateAgingSortOrderinFILHomePage() throws Exception {
			commonUtils.explicitWait(driver, lastDropDownValueinAginginFIL, 10L);
			String actualvalue = this.driver.findElement(lastDropDownValueinAginginFIL).getText();
			System.out.println("highvalue is " + lastDropDownValueinAginginFIL);
			commonUtils.explicitWait(driver, lastDropDownValueinAginginFIL, 10L);

			
			driver.findElement(By.xpath("//*[@id='homePagePagination']/li[10]/a")).click();
			Thread.sleep(5000);
			commonUtils.explicitWait(driver, firstDropDownValueinAginginFIL, 10L);
			// Thread.sleep(3000);
			String expectedvalue = this.driver.findElement(firstDropDownValueinAginginFIL).getText();

			System.out.println("lowest value is " + firstDropDownValueinAginginFIL);
			String[] parts = actualvalue.split(":");
			String part3 = parts[0]; // 004
			String part4 = parts[1]; // 034556
			System.out.println("part3 is " + part3);
			System.out.println("part4 is " + part4);

			String[] values = expectedvalue.split(":");
			String value3 = values[0]; // 004
			String value4 = values[1]; // 034556
			System.out.println("value3 is " + value3);
			System.out.println("value4 is " + value4);
			int z = Integer.parseInt(part4);
			System.out.println("x value is" + z);
			int u = Integer.parseInt(value4);
			System.out.println("y value is" + u);
			if (z > u) {
				Logging.log("aging dropdown values are sorting order in FIL Homapage");

			} else {
				Logging.log("aging dropdown values are  not sorting order in FIL Homapage");

			}

		}
	  public void clickAgingDropdowninHomeinPhilTec() throws InterruptedException {
			this.driver.manage().window().maximize();
			Thread.sleep(10000);

			commonUtils.explicitWait(driver, agingDropDowninHomePage, 20L);

			this.driver.findElement(agingDropDowninHomePage).click();

		}
		  public void validateAgingSortOrderinPhilTecHomePage() throws Exception {
			commonUtils.explicitWait(driver, agingDropDownStartingValueinPhilTec, 20L);
			String actualvalue = this.driver.findElement(agingDropDownStartingValueinPhilTec).getText();
			System.out.println("leastvalue is " + actualvalue);
			Thread.sleep(3000);

			driver.findElement(By.xpath("//*[@id='homePagePagination']/li[6]/a")).click();
			Thread.sleep(5000);
			commonUtils.explicitWait(driver, agingDropDownStartingValueinPhilTec, 20L);
			String expectedmiddlevalue = this.driver.findElement(agingDropDownStartingValueinPhilTec).getText();

			System.out.println("middle value is " + expectedmiddlevalue);
			this.driver.findElement(agingForwardButtton).click();

			Thread.sleep(3000);
			commonUtils.explicitWait(driver, agingDropDownStartingValueinPhilTec, 20L);
			String expectedvalue = this.driver.findElement(agingDropDownStartingValueinPhilTec).getText();

			System.out.println("highest value is " + expectedvalue);

			/*String[] Startingvalue = actualvalue.split(":");
			String Startingvalue1 = Startingvalue[0]; // 004
			String Startingvalue2 = Startingvalue[1]; // 034556
			System.out.println("Startingvalue1 is " + Startingvalue1);
			System.out.println("Startingvalue2 is " + Startingvalue2);
			String[] middlevalue = expectedmiddlevalue.split(":");
			String middlevalue1 = middlevalue[0]; // 004
			String middlevalue2 = middlevalue[1]; // 034556
			System.out.println("middlevalue1 is " + middlevalue1);
			System.out.println("middlevalue2 is " + middlevalue2);
			String[] endingvalues = expectedvalue.split(":");
			String endingvalue1 = endingvalues[0]; // 004
			String endingvalue2 = endingvalues[1]; // 034556
			System.out.println("endingvalue1 is " + endingvalue1);
			System.out.println("endingvalue2 is " + endingvalue2);
		    String afterremovespacesstartingvalue=Startingvalue2.trim();
			System.out.println(afterremovespacesstartingvalue);
			String afterremovespacesmiddlevalue=middlevalue2.trim();
			System.out.println(afterremovespacesmiddlevalue);
			String afterremovespacesendingvalue=endingvalue2.trim();
			System.out.println(afterremovespacesendingvalue);
			*/

			int x = Integer.parseInt(actualvalue);
			System.out.println("x value is" + x);
			Logging.log("aging starting  value "+x);

			int z = Integer.parseInt(expectedmiddlevalue);
			System.out.println("z value is" + z);
			Logging.log("aging middle  value "+z);

			int y = Integer.parseInt(expectedvalue);
			System.out.println("y value is" + y);
			Logging.log("aging ending value "+y);
			Logging.log("comparing aging values");
			if((x== y) && (y == z)) {
				Logging.log("aging  dropdown values are not  sorting order");
			}
			else if ((x >= y) && (x>= z)){
				Logging.log("aging  dropdown values are not  sorting order");
			}
			else if((x==z)&&(x<z)){
				Logging.log("aging  dropdown values are   sorting order");	
			}
			else if((x<y)&&(x<z)){
				Logging.log("aging  dropdown values are   sorting order");	
			}
			
			  else {
				Logging.log("aging  dropdown values are not sorting order");
				}}
	public void validateHeadersinReqGrid() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(ReqIDHeader));
			commonUtils.explicitWait(driver, By.xpath("//div[contains(@class,'sr-grid-label-top')]"), 30L);
			Logging.log("Validating headers of Requisition Grid");
			System.out.println("Validating headers of Requisition Grid");
			Assert.assertTrue(commonUtils.isElementPreset(ReqIDHeader, driver));
			Assert.assertTrue(commonUtils.isElementPreset(StatusHeader, driver));
			Assert.assertTrue(commonUtils.isElementPreset(TitleHeader, driver));
			Assert.assertTrue(commonUtils.isElementPreset(LocationHeader, driver));
			Assert.assertTrue(commonUtils.isElementPreset(LevelHeader, driver));
			Assert.assertTrue(commonUtils.isElementPreset(CreatedOnHeader, driver));
			Assert.assertTrue(commonUtils.isElementPreset(AgingHeader, driver));
			Assert.assertTrue(commonUtils.isElementPreset(PrimarySkillHeader, driver));
		    System.out.println("Validated headers of Requisition Grid");
		}	
	public void clickorgunitylevelinPhilTech(String customerName) throws InterruptedException {
		if (customerName.equalsIgnoreCase("AccenturePhilTech")){
		//driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div/div/div[3]/label/span")).click();
		Thread.sleep(10000);
			driver.findElement(By.xpath("//label[contains(@for,'list_2')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
		Thread.sleep(5000);
		//clicks second element :: driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[2]/div/div[9]/span[2]/i")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[3]/div/div[9]/span[2]/i")).click();
		Thread.sleep(10000); 
			System.out.println("facet");
			commonUtils.moveVerticalScrollBar(driver, 150);
			Thread.sleep(10000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/span[1]/span[1]")).click();
			Thread.sleep(5000);
			System.out.println("automatched");
		}else if(customerName.equalsIgnoreCase("AccentureNA")){
			Thread.sleep(12000);
			driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/div/div/div[4]/div[2]/div/div[1]/div[1]/ul/li[1]/div/div[2]/div[1]/span[1]")).click();	
		}else{
		Thread.sleep(12000);
		driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div/div[9]/span[2]/i")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]/span[1]")).click();
	    }
	}
	public void validatePrimarySkillInReqGridforCapability(String customerName, String searchPrimarySkill)
			throws InterruptedException {
		typeInCapabilityTextBox(customerName, searchPrimarySkill);
		List<WebElement> wl = getPrimarySkillfromReqGrid(searchPrimarySkill);
		Logging.log("Validating the Primary Skill in Requisition Grid");
		boolean flag = false;
		for (int i = 0; i < wl.size(); i++) {
			System.out.println("Primary Skill in Requisition Grid : " + wl.get(i).getText());
		//	if ((wl.get(i).getText().trim().split(":"))[1].trim().equalsIgnoreCase(searchPrimarySkill))
			if ((wl.get(i).getText().equalsIgnoreCase(searchPrimarySkill)))
			{
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		clickDropdownMenuToGetLogoutButton();
	}
	public void validatePrimarySkillInReqGridforDeal(String customerName, String searchPrimarySkill)
			throws InterruptedException {
		typeDealinTextbox(customerName, searchPrimarySkill);
		List<WebElement> wl = getPrimarySkillfromReqGrid(searchPrimarySkill);
		Logging.log("Validating the Primary Skill in Requisition Grid");
		boolean flag = false;
		for (int i = 0; i < wl.size(); i++) {
			System.out.println("Primary Skill in Requisition Grid : " + wl.get(i).getText());
		//	if ((wl.get(i).getText().trim().split(":"))[1].trim().equalsIgnoreCase(searchPrimarySkill))
			if ((wl.get(i).getText().equalsIgnoreCase(searchPrimarySkill)))
			{
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		clickDropdownMenuToGetLogoutButton();
	}
	// Added For Free TextSearch:@Ganesh
	public void ValidateToggleButton() throws InterruptedException {
	this.driver.manage().window().maximize();
	Thread.sleep(10000);
	commonUtils.explicitWait(driver, ToggleButton, 20L);
	this.driver.findElement(ToggleButton);
	System.out.println("DEBUG-----Asserting for ToggleButton Presence");
	Assert.assertTrue(commonUtils.isElementPreset(ToggleButton, driver), "No ToggleButtonPresent");

	}

	// Added For Free TextSearch:@Ganesh
	public void ValidateToggleButtonClick() throws InterruptedException {
	// this.driver.manage().window().maximize();
	Thread.sleep(10000);
	commonUtils.explicitWait(driver, ToggleButton, 20L);

	if (this.driver.findElement(ToolTip).getAttribute("tooltip") == "Free text search") {
	this.driver.findElement(ToggleButton).click();
	System.out.println("DEBUG-----Again Asserting for Free Text");
	Assert.assertEquals(this.driver.findElement(SearchPlaceHolder).getText(), "Free Text Search");
	}

	Thread.sleep(10000);

	if (this.driver.findElement(ToolTip).getAttribute("tooltip") == "Guided search") {
	this.driver.findElement(ToggleButton).click();
	System.out.println("DEBUG-----Again Asserting for Guided Search");
	Assert.assertEquals(this.driver.findElement(SearchPlaceHolder).getText(),
	"Search for Skills, Location, Designation, Employer, Candidate Name, University");
	}

	}
	public void clickonStatusbutton(String customerName) throws InterruptedException {
		commonUtils.explicitWait(driver, OpenStatusbutton, 30L);
		if (!driver.findElement(OpenStatusbutton).isSelected()) {
			driver.findElement(OpenStatusbutton).click();
			commonUtils.explicitWait(driver, By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/input"), 30L); 
			Actions actions = new Actions(driver);
		    WebElement Textbox = driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/input"));
		    actions.moveToElement(Textbox);
		    actions.click().build().perform();
		    Thread.sleep(3000);
		    Textbox.sendKeys("service management");
		    commonUtils.explicitWait(driver, By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span"), 50L); 
			driver.findElement(By.xpath(".//*[@id='ui-select-choices-row-0-1']/a/span")).click();
		    Thread.sleep(5000);
			clickSubmitButton();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//img[contains(@src,'criticalReq.png')]")).click();
			driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[6]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div/div[9]/span[2]/i")).click();
			Thread.sleep(3000);
			commonUtils.moveVerticalScrollBar(driver, 300);
			driver.findElement(By.xpath(".//*[@id='ngViewDiv']/div/div/div/div/div[6]/div[3]/div/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/span[1]/span[1]")).click();
			Thread.sleep(3000);
			
		}
	}	


}
