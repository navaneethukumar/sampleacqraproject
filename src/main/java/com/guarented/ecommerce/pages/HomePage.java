package com.guarented.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.driver.Driver;
import com.spire.base.controller.Logging;

public class HomePage extends Driver {
	public HomePage(WebDriver driver) {
		super(driver);
	}

	CommonUtils commonUtils = new CommonUtils();
	String searchParameterType;

	protected By popularPoolLabel = By.xpath("//label[contains(text(),'Popular Pools')]");
	protected By openRequisitionLabel = By.xpath("//label[contains(text(),'Open Requisitions')]");

	protected By logoutButton = By.xpath("//*[contains(text(),'Logout')]");
	protected By universalSearch = By.id("searchField");
	protected By autoSuggestList = By.xpath("//ul[contains(@id,'ui-id')]/li");
	protected By srStatusTextBox = By.id("reqStatusId");
	protected By srStatusListInDropdown = By
			.xpath("//*[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'][1]/li");
	protected By srStatusListInResultGrid = By.xpath("//div[contains(@id,'reqid_')]/following-sibling::div[1]");
	protected By firstPopularPoolCountLink = By.xpath("(//a[@id='popularPoolListId'])[1]/span");
	protected By candidateManagement = By.xpath("//a[contains(text(),'Candidate Management')]");
	protected By candidatesearch = By.xpath("//a[contains(text(),'Search')]");
	protected By advancedSearchLink = By.xpath("//button[contains(text(),'Advanced')]");

	public By homeLink = By.xpath("//a[text()='Home'] | //a[contains(text(),'Home')]");
	public By universalSearchBar = By.id("search-bar-button");// *[@id='headerColDiv']/div/div/div/div[2]/ul/li[1]/a
	public By advanceSearch = By.xpath("//li/a[contains(@href,'Advanced-Search')] | //li[2]/a[contains(@title,'Advanced Search')] | //*[@id='headerColDiv']/div/div/div/div[2]/ul/li[1]/a | //*[@id='headerColDiv']/div/div/div/div[3]/ul/li[1]/a");
	//	public By advanceSearch = By.xpath("//*[@id='headerColDiv']/div/div/div/div[3]/ul/li[1]/a");

	public By advanceSearch_AccGlobal = By.xpath("//*[@id='ngViewDiv']/div/form/span/span/a");

	public By dropdownMenuToGetLogout = By.xpath("//*[@class='current-user']/div/span[2]");
	public By searchedPamameterResultList = By.xpath("//*[contains(@id,'ui-select-choices-row-')]/a/div");
	public By searchedPamameterTypeList = By.xpath("//*[contains(@id,'ui-select-choices-row-')]/a/div/span[2]");
	public By reqIdTextBox = By.xpath(
			"(//input[contains(@class,'ui-select-search') and contains(@class ,'input-xs')and contains(@class ,'ng-valid')])[2]");
	public By reqIdTextBoxNA = By.xpath(
			"(//input[contains(@class,'ui-select-search') and contains(@class ,'input-xs')and contains(@class ,'ng-valid')])[1]");
	public By indopsDemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-container')]");
	// public By
	// reqIdTextBoxNA=By.xpath("(//input[contains(@class,'ui-select-search') and
	// contains(@class ,'input-xs')and contains(@class ,'ng-valid') and
	// contains(@class ,'ng-empty') and contains(@class ,'ng-touched')and
	// contains(@class ,'ng-dirty') and contains(@class ,'ng-valid-parse')
	// ])[1]");
	// *[@id='ngViewDiv']/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div/div/div/input
	// public By reqIdTextBoxNA=By.xpath("//input[@class='ui-select-search
	// input-xs ng-valid ng-dirty ng-touched ng-valid-parse ng-empty']");

	public By reqSuggestedList = By.xpath("//*[contains(@id,'ui-select-choices-row-')]/a/span");
	public By reqStatusLabel = By.xpath("//*[@class='demandFilterLeft col-md-4 col-sm-4 ng-binding']");
	public By reqIdsInResultGrid = By.xpath("//*[@id='ngViewDiv']/descendant::li/div/div/div[1]");
	public By reqIdsInResultGridNA = By.xpath("//*[@id='ngViewDiv']/descendant::li/div/div[1]/div[1]");
	public By submitButton = By.xpath("//button[text()='Submit']");
	public By AccenturePhilTecDemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-container')]");
	public By agingDropDownStartingValueinPhilTec=By.xpath("(//*[contains(@title,'Aging')])[1]");
	public By agingForwardButtton=By.xpath("//*[@id='homePagePagination']/li[10]/a");
	// FIL related
	public By recruiterEmailIdTextBox = By
			.xpath("//div[contains(text(),'Recruiter')]/following-sibling::div/div/div/div/div/input");
	public By recruiterEmailId = By
			.xpath("html/body/div[5]/ul/li/div[3]/a/span");
	public By recruiterEmailIdlabel = By.xpath("//div[contains(text(),'Recruiter EmailId')]");
	public By experienceLabel = By.xpath("//div[contains(text(),'Experience')");
	public By selectEmailIdFromlist = By.cssSelector("span.ng-binding.ng-scope");
	public By fromExpYear = By.xpath("(//input[@type='number'])[1]");
	public By fromExpmonth = By.xpath("(//input[@type='number'])[2]");
	public By toExpYear = By.xpath("(//input[@type='number'])[3]");
	public By toExpmonth = By.xpath("(//input[@type='number'])[4]");
	public By expInReqGrid = By
			.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[4]/span | html/body/div[1]/div[2]/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[1]/div/div/div[5] | html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[7]/div/div/div[4] | //label[contains(text(),'Experience:')]/ancestor::div[@class='col-md-2 spire-list-div'] | //*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/ul/li[1]/div/div/div[4]/div");
	public By emailIdlist = By.xpath(
			"//ul[@class='ui-select-choices ui-select-choices-content ui-select-dropdown dropdown-menu ng-scope']");
	// public By
	// selectEmailIdFromlist=By.xpath("(//a[@class='ui-select-choices-row-inner']/span)[1]");
	// public By recruiterEmailIdTextBox=By.className("ui-select-search input-xs
	// ng-pristine ng-untouched ng-valid ng-empty");
	// public By JobTitleTextBox=By.xpath(("//input[contains(text(),'Job
	// Title'])[5]"));
	// public By JobTitleInResultGrid=By.xpath(("//*[@class='spire-list-div
	// width-14 ng-binding']"));
	public By JobTitleInResultGrid = By
			.xpath("//span[contains(@title,'Title')]/b | //div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@ng-if,'JOB_TITLE')]/input | //*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/ul/li[1]/div/div/div[2]/div/b");
	public By firstJobTitleInGrid = By.xpath("//span[contains(@title,'Title')]/b | //div[contains(@title,'Designation')] | //div[contains(text(),'Title')] | //div[contains(@title,'Requisition Title')] | //div[contains(@title,'Title')] | //*[@id='ngViewDiv']/div/div/div/div/div[4]/div[3]/div/div[1]/div[1]/ul/li[1]/div/div/div[2]/div/b");

	// public By JobTitleTextBox=By.xpath(("//input[@type='text'])[5]"));
	// public By
	// JobTitleTextBox=By.xpath(("//*[@id='ngViewDiv']/div/div/div/div[3]/div/div[2]/input"));
	public By JobTitleTextBox = By.xpath("//input[contains(@ng-model,'reqId.jobTitle')]");
	// public By JobTitleTextBox=By.xpath(("//input[@class='ng-pristine ng-valid
	// ng-empty ng-touched']"));

	public By JobTitleTextBoxsearchedparameter = By.xpath(("//input[@type='" + searchParameterType + "'])[5]"));

	// public By JobTitleInResultGrid
	// =By.xpath("//div[@id='ngViewDiv']/div/div/div/div[3]/div[2]/div/div/ul/li/div/div/div[2]");
	// public By firstJobTitleInGrid=
	// By.xpath("//div[@id='ngViewDiv']/div/div/div/div[3]/div[2]/div/div/ul/li/div/div/div[2][1]");
	// Accenture NA related
	public By Requisitionstatus = By.id("req_status");
	public By ClosedRequisitionstatus=By.xpath("//label[contains(@for,'list_2')]");
	public By PassiveCandidatesIcon = By.xpath("html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div/div[1]/div[1]/ul/li[1]/div/div[2]/div[7]/div[1]");
	// public By
	// Requisitionstatus=By.xpath("//div[contains(@class,'reqStatusDiv')][1]");
	public By SearchbyORGUNITLEVEL1fromFacet = By.xpath("//*[contains(text(),'ORGUNITLEVEL1')]");
	public By SearchelementinORGUNIT = By.xpath("//label[contains(@id,'candCbLabel_1_0')]");
	public By SearchelementinLocation = By.xpath("//label[contains(@id,'candCbLabel_2_1')]");
	public By LocationInResultGrid = By.xpath("//div[contains(text(),'Location:')] | //div[contains(@title,'Location')]");
	public By LocationInResultGridforFII = By.xpath("//div[contains(@title,'Location')] | html/body/div[1]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[4]/span");
	public By firstLocationInGrid = By.xpath("//div[contains(text(),'Location:')][1] | //div[contains(@title,'Location')]");
	public By firstLocationInGridforFII = By.xpath("//div[contains(@title,'Location')][1] | //span[contains(@title,'Location')] | html/body/div[1]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/div[1]/div/div[1]/div/div[4]/span/b");
	
	public By SearchelementinJobLevel = By.xpath("//label[contains(@id,'candCbLabel_2_2')]");
	// public By
	// FiltersIcon=By.xpath(".//*[@id='ngViewDiv']/div/div/div/div[1]/div/label/span/img
	// OR
	// html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[1]/label/span/img");
	public By FiltersIcon = By.xpath(
			"//img[contains(@src,'assets/images/criticalReq.png')] | html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[1]/label/span/img | .//*[@id='ngViewDiv']/div/div/div/div[1]/div/label/span/img");
	// public By
	// FiltersIcon=By.xpath("html/body/div[1]/div[2]/div/div/div/div[1]/div/label/span/img");
	public By LocationfromResultGrid = By
			.xpath("//label[contains(text(),'Location:')]/ancestor::div[@class='spire-list-div width-12 ng-binding']");
	public By firstLocationfromGrid = By.xpath(
			"(//label[contains(text(),'Location:')]/ancestor::div[@class='spire-list-div width-12 ng-binding'])[1]");
	// FIL & IDFC
	public By IndianServiceLeader = By.xpath("//label[contains(@id,'candCbLabel_0_1')]");
	public By SearchelementinLocationforFII = By.xpath("//label[contains(@id,'candCbLabel_2_0')] | //label[contains(@id,'candCbLabel_3_0')]");
	public By StatusinResultGrid = By.xpath("*//div[contains(@class,'spire-list-div width-10 ng-binding')]");
	public By FirstStatusinResultGrid = By.xpath("*//div[contains(@class,'spire-list-div width-10 ng-binding')][1]");
	public By EmploymenttypeFacet = By.xpath(
			"html/body/div[1]/div[2]/div/div/div/div/div[4]/div[1]/div/div/div/div[1]/div/div[3]/div/ul/li[2]/div/label[1]");
	// public By BusinessUnitFacet = By.xpath("//label[@id='candCbLabel_4_1']");
	// //For IDFC
	public By BusinessUnitFacet = By.xpath(
			"html/body/div[1]/div[2]/div/div/div/div/div[4]/div[1]/div/div/div/div[1]/div/div[4]/div/ul/li[1]/div/label[1]"); // only
																																// for
																																// FIL
	// public By BusinessUnitFacet =
	// By.xpath("//label//span[contains(@id,'candCbSpan_0_3')] |
	// //label[@id='candCbLabel_4_1']");
	// IDC
	public By AutomatchReport = By.xpath("//div[contains(@title,'Automatch Report')]");
	public By SRList = By.xpath("//span[contains(@title,'Download SR List')] | //span[contains(@ng-click,'downlaodSrList')]");
	public By RequisitionInResultGrid = By.xpath("//div[contains(@title,'Req ID:')] | //span[contains(@title,'Req ID')] | //span[contains(@title,'SR NO:')] | html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div/div[1]/div[1]/ul/li[1]/div/div[1]/div[1]");
	public By firstRequisitionInGrid = By
			.xpath("//span[contains(@title,'Req ID')][1] | //span[contains(@title,'SR NO:')][1] | html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div/div[1]/div[1]/ul/li[1]/div/div[1]/div[1]");
	 //public By
	// RequisitionNoTextBox=By.xpath("//div[@id='ngViewDiv']/div/div/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div/input");
	public By RequisitionNoTextBoxLabel = By.xpath("//div[contains(text(),'Requisition I')] | //div[contains(text(),'SR NO')]/following-sibling::div/div/div/div/div/input");
	public By RequisitionNoTextBox = By.xpath("//div[contains(text(),'Requisition I')]/following-sibling::div/div/div/div/div/input | //div[contains(text(),'SR NO')]/following-sibling::div/div/div/div/div/input | //*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[5]/div[2]/div/div/div/div/input");
	//public By RequisitionNoTextBox = By.xpath("//*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[5]/div[2]/div/div/div/div/input");
	
	public By SLLTextBox = By.xpath("//div[contains(text(),'SLL')]/following-sibling::div/div/div/div/div/input");
	public By LocationTextBox = By.xpath("//div[contains(text(),'Location')]/following-sibling::div/div/div/div/div/input | (//input[@type='text'])[3]");
	public By DownloadJDIcon = By.xpath("//div[contains(@class,'spire-downloadJD-icon')]");
    //Ganesh
	// Added For Free TextSearch:@Ganesh
	public By ToggleButton = By.xpath("//*[@id='tagMdSwitch']/div[1]/div[2]/div[1]");
	public By SearchPlaceHolder = By.xpath("//*[@id='search-bar-button']/span/span[1]");
	public By ToolTip = By.xpath("//*[@id='tagMdSwitch']");
	public By OpenStatusbutton=By.xpath("//label[contains(@for,'list_2')]");
	
	
	// Phil BPO
	public By CapabilityTextBox = By
			.xpath("//input[contains(@class,'ui-select-search input-xs ng-valid ng-dirty ng-touched ng-empty')]");
	public By PrimarySkillTextBox = By.xpath("//div[contains(text(),'Primary Skill')]/following-sibling::div/div/div/div/div/input | //*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/input | //*[@id='ngViewDiv']/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div/div[1]");
	public By PrimarySkillfromReqGrid = By.xpath("//div[contains(@title,'Primary Skill')] | //span[contains(@title,'Primary Skill')]");
	public By firstPrimarySkillfromGrid = By.xpath("//div[contains(@title,'Primary Skill')][1] | //span[contains(@title,'Primary Skill')] | //span[contains(@title,'Primary Skill: Claims Processing')]");
 //Added by Naresh
	public By accentureIDFCdemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-xaxis-0-gridlabels')]");
	public By accentureNADemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-xaxis-0-gridlabels')]");
	public By idcDemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-container')]");
	public By accenturePhilBPODemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-xaxis-0-gridlabels')]");
	//public By accentureNADemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-xaxis-0-gridlabels')]");
	public By accentureFILDemandChartLables = By.xpath("//*[contains(@class,'fusioncharts-container')]");
	public By agingDropDowninHomePage = By.xpath("//*[@id='asSort']");
	public By agingDropDownStartingValueinIDFC = By.xpath("html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[1]/div/div/div[6]");
	public By agingDropDownEndingValueinIDFC = By.xpath("html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[5]/div/div/div[6]");
	public By agingDropDownMiddleValueinIDFC=By.xpath("html/body/div[1]/div[2]/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[9]/div/div/div[6]");
	public By lastDropDownValueinAginginFIL=By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/ul/li[1]/div/div/div[6]/div");
	public By firstDropDownValueinAginginFIL=By.xpath("html/body/div[1]/div[2]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/ul/li[2]/div/div/div[6]/div");
//Phil Tech -@Supraja	
	public By ReqIDHeader = By.xpath("//div/span[contains(text(),'Req ID')]");
	public By StatusHeader = By.xpath("//div/span[contains(text(),'Status')]");
	public By TitleHeader = By.xpath("//div/span[contains(text(),'Title')]");
	public By LocationHeader = By.xpath("//div/span[contains(text(),'Location')]");
	public By LevelHeader = By.xpath("//div/span[contains(text(),'Level')]");
	public By CreatedOnHeader = By.xpath("//div/span[contains(text(),'Created On')]");
	public By AgingHeader = By.xpath("//div/span[contains(text(),'Aging')]");
	public By PrimarySkillHeader = By.xpath("//div/span[contains(text(),'Primary Skill')]");
	public By firstReqInDropDownList=By.xpath("//div[contains(@id,'ui-select-choices-row-3-3')]/a | (//*[contains(@id,'ui-select-choices-row-')]/a/span)[1]");
	public By firstRecruiterInDropDownList=By.xpath("(//*[contains(@id,'ui-select-choices-row-')]/a/span)[1]");
	public By requisitionStatusLabel=By.xpath("(//div[contains(text(),'Requisition')])[1] | //div[contains(@title,'Req ID')]");
	
	
	
	public WebElement getPrimarySkillTextBox() {
		commonUtils.explicitWait(driver, PrimarySkillTextBox, 30L);
		return this.driver.findElement(PrimarySkillTextBox);
	}

	public WebElement getSLLTextBox() {
		commonUtils.explicitWait(driver, SLLTextBox, 30L);
		return this.driver.findElement(SLLTextBox);

	}

	public WebElement getLocationTextBox() {
		commonUtils.explicitWait(driver, LocationTextBox, 30L);
		return this.driver.findElement(LocationTextBox);
	}

	public WebElement getDownloadJDIcon() {
		commonUtils.explicitWait(driver, DownloadJDIcon, 30L);
		return this.driver.findElement(DownloadJDIcon);
	}

	public WebElement getRequisitionNoTextBox() {
		//commonUtils.explicitWait(driver, RequisitionNoTextBox, 30L);
		return this.driver.findElement(RequisitionNoTextBox);
	}

	public void getPopularPoolLabel() {
		driver.findElement(popularPoolLabel);
	}

	public void getOpenRequisitionLabel() {
		driver.findElement(openRequisitionLabel);
	}

	public WebElement clickFiltersIcon() {
		return driver.findElement(FiltersIcon);
	}

	public void clickLogout() {
		driver.findElement(logoutButton).click();
	}

	public void typeInUniversalSearch(String searchString) throws InterruptedException {
		System.out.println("Typing in Universal search");
		driver.findElement(universalSearch).sendKeys(searchString);
		Thread.sleep(5000);
	}
	public WebElement getPassiveCandidatesIcon(){
		commonUtils.explicitWait(driver, PassiveCandidatesIcon, 30L);
    	return this.driver.findElement(PassiveCandidatesIcon);
	}
	public List<WebElement> getAutoSuggestListInUniversalSearch(String searchString) throws InterruptedException {

		typeInUniversalSearch(searchString);
		Thread.sleep(7000);

		return driver.findElements(autoSuggestList);
	}

	public void typeEnterInSRStausBox() throws InterruptedException {
		System.out.println("Typing in SR Status Box");
		driver.findElement(srStatusTextBox).sendKeys(Keys.ENTER);
		driver.findElement(srStatusTextBox).sendKeys(Keys.ENTER);
		Thread.sleep(15000);
	}

	public List<WebElement> getSrStatusList() throws InterruptedException {
		Thread.sleep(10000);// waiting for graph to load after that only drop
							// down list is coming
		typeEnterInSRStausBox();

		return driver.findElements(srStatusListInDropdown);
	}

	public List<WebElement> getSrStatusListInResultGrid() throws InterruptedException {

		return driver.findElements(srStatusListInResultGrid);
	}

	public void clickFirstPopularPoolCount() {
		driver.findElement(firstPopularPoolCountLink).click();
	}

	public void clickCandidateManagement() {
		commonUtils.explicitWait(driver, candidateManagement, 10L);
		driver.findElement(candidateManagement).click();
		Logging.log("Candidate Management is clicked");
	}

	public void clickCandSearch() {
		commonUtils.explicitWait(driver, candidatesearch, 10L);
		driver.findElement(candidatesearch).click();
		Logging.log("Search is clicked");
	}

	public void clickAdvancedSearchLink() {
		commonUtils.explicitWait(driver, advancedSearchLink, 10L);
		this.driver.findElement(advancedSearchLink).click();
		Logging.log("Advanced Search link is clicked.");
	}

	public WebElement getSearchelementinORGUNIT() {
		commonUtils.explicitWait(driver, SearchelementinORGUNIT, 30L);
		return this.driver.findElement(SearchelementinORGUNIT);
	}

	public WebElement getIndianServiceLeaderInFacet() {
		commonUtils.explicitWait(driver, IndianServiceLeader, 30L);
		return this.driver.findElement(IndianServiceLeader);
	}

	public WebElement getSearchelementinLocation() {
		commonUtils.explicitWait(driver, SearchelementinLocation, 30L);
		return this.driver.findElement(SearchelementinLocation);

	}

	public WebElement getSearchelementinLocationforFII() {
		commonUtils.explicitWait(driver, SearchelementinLocationforFII, 30L);
		return this.driver.findElement(SearchelementinLocationforFII);
	}

	public WebElement getSearchelementinJoblevel() {
		commonUtils.explicitWait(driver, SearchelementinJobLevel, 30L);
		return this.driver.findElement(SearchelementinJobLevel);

	}

	public WebElement getSearchelementinEmploymentType() {
		commonUtils.explicitWait(driver, EmploymenttypeFacet, 30L);
		return this.driver.findElement(EmploymenttypeFacet);

	}

	public WebElement getBusinessUnitFacet() {
		commonUtils.explicitWait(driver, BusinessUnitFacet, 30L);
		return this.driver.findElement(BusinessUnitFacet);
	}

	public WebElement getAutomatchReport() {
		commonUtils.explicitWait(driver, AutomatchReport, 30L);
		return this.driver.findElement(AutomatchReport);

	}

	public WebElement getSRList() {
		commonUtils.explicitWait(driver, SRList, 30L);
		return this.driver.findElement(SRList);
	}

	public WebElement getJobTitleTextBox() {
		commonUtils.explicitWait(driver, JobTitleTextBox, 30L);
		return this.driver.findElement(JobTitleTextBox);
	}

	public WebElement getCapabilityTextBox() {
		commonUtils.explicitWait(driver, CapabilityTextBox, 30L);
		return this.driver.findElement(CapabilityTextBox);
	}

	public List<WebElement> getJobTitleInResultGrid(String customerName) {
		if (customerName.equals("ABC")) {
			commonUtils.explicitWait(driver, firstJobTitleInGrid, 20L);
			return this.driver.findElements(JobTitleInResultGrid);
		} else {
			commonUtils.explicitWait(driver, firstJobTitleInGrid, 20L);
			System.out.println("hvhhvhvnhv spire");
			List<WebElement> lst = driver.findElements(JobTitleInResultGrid);
			System.out.println("size =" + lst.size());
			// for(int i=0;i<lst.size();i++){
			System.out.println("JobTitle in first row is " + lst.get(0).getText());
			// }
			return this.driver.findElements(JobTitleInResultGrid);
		}
	}

	public WebElement SelectRequisitionStatus() {
		commonUtils.explicitWait(driver, Requisitionstatus, 30L);
		return this.driver.findElement(Requisitionstatus);
	}
	public WebElement SelectRequisitionStatusClosed() {
		commonUtils.explicitWait(driver, ClosedRequisitionstatus, 30L);
		return this.driver.findElement(ClosedRequisitionstatus);
	}
	
	public List<WebElement> getstatusinResultGrid(String customerName) {
		if (customerName.equals("ABC")) {
			commonUtils.explicitWait(driver, FirstStatusinResultGrid, 20L);
			return this.driver.findElements(StatusinResultGrid);
		} else {
			commonUtils.explicitWait(driver, FirstStatusinResultGrid, 20L);
			System.out.println("hvhhvhvnhv spire");
			List<WebElement> lst = driver.findElements(StatusinResultGrid);
			System.out.println("size =" + lst.size());
			System.out.println("first data is" + lst.get(5).getText());
			return this.driver.findElements(StatusinResultGrid);
		}

	}

	public WebElement getHomeTab() {
		commonUtils.explicitWait(driver, homeLink, 30L);
		return this.driver.findElement(homeLink);
	}

	public WebElement getUniversalSearchBar() {
		commonUtils.explicitWait(driver, universalSearchBar, 30L);
		return this.driver.findElement(universalSearchBar);
	}

	public void clickDropdownMenuToGetLogoutButton() {
		this.driver.findElement(dropdownMenuToGetLogout).click();
	}

	public List<WebElement> getSearchedPamameterResultList() throws InterruptedException {

		return driver.findElements(searchedPamameterResultList);
	}

	public List<WebElement> getSearchedPamameterTypeList() throws InterruptedException {

		return driver.findElements(searchedPamameterTypeList);
	}

	public void clickAdvanceSearch(String customerName) {
		if (customerName.equalsIgnoreCase("AccentureGlobal")) {
			commonUtils.explicitWait(driver, advanceSearch_AccGlobal, 30L);
			this.driver.findElement(advanceSearch_AccGlobal).click();
		} else {
			//commonUtils.explicitWait(driver, advanceSearch, 90L);
			commonUtils.explicitWait(driver, advanceSearch);
			this.driver.findElement(advanceSearch).click();
			/*((JavascriptExecutor) driver).executeScript("return window.stop");
			JavascriptExecutor js =(JavascriptExecutor) driver;
			js.executeScript("return window.stop");
		    js.executeScript("document.querySelector(\"[class='hidden-xs sprite-advance-search']\").click();");
		   */
		}
	}

	public void enterReqId(String customerName, String searchReq) {
		if (customerName.equalsIgnoreCase("AccentureNA")) {
			commonUtils.explicitWait(driver, reqIdTextBoxNA, 30L);
			this.driver.findElement(reqIdTextBoxNA).clear();
			this.driver.findElement(reqIdTextBoxNA).sendKeys(searchReq);
		} else {
			commonUtils.explicitWait(driver, reqIdTextBox, 30L);
			this.driver.findElement(reqIdTextBox).sendKeys(searchReq);
		}
	}

	public List<WebElement> getReqSuggestedListWebElement(String customerName) throws InterruptedException {
		if (customerName.equalsIgnoreCase("ABC")) {
			commonUtils.explicitWait(driver, reqSuggestedList, 45L);
			// Thread.sleep(5000);
			return this.driver.findElements(reqSuggestedList);
		} else {
			commonUtils.explicitWait(driver, reqSuggestedList, 45L);
			// Thread.sleep(5000);
			return this.driver.findElements(reqSuggestedList);
		}
	}

	public void enterRecruiterEmailId(String searchParameter) throws InterruptedException {
		//Thread.sleep(1000);
		commonUtils.explicitWait(driver, requisitionStatusLabel, 40L);
		WebElement wb = driver.findElement(requisitionStatusLabel);
		commonUtils.mouseOverToElement(this.driver, wb);
		System.out.println("clicked");
		commonUtils.moveVerticalScrollBar(driver, 100);
		this.driver.findElement(recruiterEmailIdTextBox).sendKeys(searchParameter);
		System.out.println("entering email id");
		Logging.log("entering email id");
		Thread.sleep(5000);
		Logging.log("selecting from the list");
	}

	public List<WebElement> enterExperience(String searchParameter[]) throws InterruptedException {
		Thread.sleep(8000);
		driver.findElement(fromExpYear).sendKeys(searchParameter[0]);
		driver.findElement(fromExpmonth).sendKeys(searchParameter[1]);
		driver.findElement(toExpYear).sendKeys(searchParameter[2]);
		driver.findElement(toExpmonth).sendKeys(searchParameter[3]);
		Logging.log("entered in experience range");
		System.out.println("entered in experience range");
		Thread.sleep(2000);
		clickSubmitButton();
		System.out.println("submit button pressed");
		commonUtils.explicitWait(driver,expInReqGrid,50L);
		return this.driver.findElements(expInReqGrid);

	}

	public void clickSearchedParameterType(String searchParameterType) {
		commonUtils.explicitWait(driver, JobTitleTextBoxsearchedparameter, 30L);
		this.driver.findElement(JobTitleTextBoxsearchedparameter).click();
	}

	public List<WebElement> getLocationResultGrid(String customerName) {
		if (customerName.equals("ABC")) {
			commonUtils.explicitWait(driver, firstLocationInGrid, 45L);
			return this.driver.findElements(LocationInResultGrid);
		} else {
			commonUtils.explicitWait(driver, firstLocationInGrid, 45L);
			System.out.println("hvhhvhvnhv spire");
			List<WebElement> lst = driver.findElements(LocationInResultGrid);
			System.out.println("size =" + lst.size());
			System.out.println("Location in First row is " + lst.get(1).getText());
			return this.driver.findElements(LocationInResultGrid);
		}
	}

	public List<WebElement> getLocationResultGridforFII(String customerName) {
		if (customerName.equals("ABC")) {
			commonUtils.explicitWait(driver, firstLocationInGridforFII, 45L);
			return this.driver.findElements(LocationInResultGridforFII);
		} else {
			commonUtils.explicitWait(driver, firstLocationInGridforFII, 45L);
			System.out.println("12345");
			List<WebElement> lst = driver.findElements(LocationInResultGridforFII);
			System.out.println("size =" + lst.size());
			// System.out.println("first data is"+lst.get(1).getText());
			return this.driver.findElements(LocationInResultGridforFII);
		}
	}

	public List<WebElement> getReqNoinResultGrid(String customerName) {
		if (customerName.equals("ABC")) {
			commonUtils.explicitWait(driver, firstRequisitionInGrid, 45L);
			return this.driver.findElements(RequisitionInResultGrid);
		} else {
			commonUtils.explicitWait(driver, firstRequisitionInGrid, 45L);
			System.out.println("hvhhvhvnhv spire");
			List<WebElement> lst = driver.findElements(RequisitionInResultGrid);
			System.out.println("size =" + lst.size());
			System.out.println("Searched Requisition in Result grid is " + lst.get(0).getText());
			return this.driver.findElements(RequisitionInResultGrid);
		}
	}

	public List<WebElement> getLocationfromResultGrid(String customerName) {
		if (customerName.equals("ABC")) {
			commonUtils.explicitWait(driver, firstLocationfromGrid, 45L);
			return this.driver.findElements(LocationfromResultGrid);
		} else {
			commonUtils.explicitWait(driver, firstLocationfromGrid, 45L);
			System.out.println("hvhhvhvnhv spire");
			List<WebElement> lst = driver.findElements(LocationfromResultGrid);
			System.out.println("size =" + lst.size());
			System.out.println("first data is" + lst.get(1).getText());
			return this.driver.findElements(LocationfromResultGrid);
		}
	}

	public List<WebElement> getPrimarySkillfromReqGrid(String customerName) {
		if (customerName.equals("ABC")) {
			commonUtils.explicitWait(driver, firstPrimarySkillfromGrid, 45L);
			return this.driver.findElements(PrimarySkillfromReqGrid);
		} else {
			commonUtils.explicitWait(driver, firstPrimarySkillfromGrid, 45L);
			System.out.println("hvhhvhvnhv spire");
			List<WebElement> lst = driver.findElements(PrimarySkillfromReqGrid);
			System.out.println("size =" + lst.size());
			System.out.println("Searched Primary Skill is " + lst.get(0).getText());
			return this.driver.findElements(PrimarySkillfromReqGrid);
		}
	}

	public void clickReqStatusLabel() {
		this.driver.findElement(reqStatusLabel).click();
	}

	public List<WebElement> getReqIdsInResultGrid(String customerName) {
		if (customerName.equals("AccentureNA")) {
			commonUtils.explicitWait(driver, reqIdsInResultGridNA, 45L);
			return this.driver.findElements(reqIdsInResultGridNA);
		} else {
			commonUtils.explicitWait(driver, reqIdsInResultGrid, 45L);
			return this.driver.findElements(reqIdsInResultGrid);
		}

	}

	public void clickSubmitButton() {
		this.driver.findElement(submitButton).click();
	}
	
	}

