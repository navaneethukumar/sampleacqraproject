package com.guarented.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.spire.base.controller.Assertion;

public class CandidateListViewPage extends CommonHeader {
	CommonUtils cu = new CommonUtils();

	public CandidateListViewPage(WebDriver driver) {
		super(driver);
	}

	protected By firstCandidateNameLink = By.xpath("(.//*[@id='candidateInfoID'])[1]/div[2]/div[1]/a");
	protected By refineSearch = By.xpath("//div[text()='Refine Search']");
	protected By searchResultDivision = By.xpath(".//*[@id='__blaze-root']/div[2]/div/div[2]/div[1]/div[1]/span");
	// protected By searchResultDivision = By.className("new-wrapper");
	protected By candidateExtractedSkillSet = By.xpath("//div[@ng-attr-title=\"{{skills.skill_val}}\"]");

	// public By candidateList=By.xpath("//*[@id='candName']/div/b/span[1]");
	/* Added by radha */
	public By candidateList = By.xpath(
			"//div[@class='col-xs-2 col-sm-1 col-md-1 candidate-thumbnail']/img[@src='assets/images/unknown.jpg'] | //*[@id='candName']/div/b/span[1]");
	public By moreLink = By.xpath("//div[contains(text(),'More')]");
	//public By breadcrumbs = By.xpath("//div[@class='col-md-12 col-sm-9 col-xs-9 breadcrumbs-widget bulk-action-div ng-scope']/div[@class='pull-left ng-binding']");
	//public By breadcrumbs = By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[2]/ul/li[29]/ul/li/div/div[1]/span | //div[contains(@id,'ngViewDiv')]/div/div[8]/div[1]/div[1]/div/ul/div[1]/div[1] | //ul[contains(@class,'rich-list')]/div/div[contains(@class,'col-sm-6 col-xs-6 ng-binding')] | //ul[contains(@class,'rich-list')]/div/div[contains(@class,'pull-left ng-binding')] | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[1]/div[1] | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[1]/div[1]");
	public By breadcrumbs = By.xpath("//ul[contains(@class,'rich-list')]/div/div[contains(@class,'col-sm-6 col-xs-6 ng-binding')] | //ul[contains(@class,'rich-list')]/div/div[contains(@class,'pull-left ng-binding')] | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[1]/div[1] | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/div[1]/div[1] | html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[1]/div[1]");

	
	public By totalResultCount = By.xpath("//b[@class='ng-binding']");
	public By experience = By.xpath(
			"//strong[contains(text(),'Experience :')]/following-sibling::span|//strong[contains(text(),'Exprience :')]/following-sibling::span");
	public By candidateJobTitle = By.xpath("//span[@id='candProJob'] | html/body/div[1]/div[2]/div/div[9]/div[1]/div[1]/div/ul/li[1]/div/div/div[2]/span/div[11]/div/strong | //strong[contains(text(),'Candidate Job Title : ')]");
	public By source = By.xpath("//strong[contains(text(),'Source :')]/following-sibling::span | //strong[text()='Source :']/following-sibling::span");
	public By sourceType = By.xpath("//strong[contains(text(),'Source Type')]/following-sibling::span");
	public By sourceName = By.xpath("//strong[contains(text(),'Source Name')]/following-sibling::span");
	public By educationLevel = By.xpath("//strong[contains(text(),'Education Level :')]/following-sibling::span");
	public By number = By.xpath(".//*[@id='candProLi']/div/div/div[3]/span/div[6]/div/span | //strong[contains(text(),'Number :')]/following-sibling::span");
	//span/div[6]/div/strong[contains(text(),'Number :')]/following-sibling::span | 
	public By emailId = By.xpath("//strong[contains(text(),'Email ID :')]/following-sibling::span");
	public By abacusStatus = By.xpath("//strong[contains(text(),'Abacus Status')]/following-sibling::span");
	public By CandidateStatus = By.xpath("//strong[contains(text(),'Client Status')]/following-sibling::span");
	/* Added By Sridhar */
	public By skillinCandidateListView = By.xpath(".//*[@id='facet_0']/li[1]/div/label/span");
	public By locationinCandidateListView = By.xpath(".//*[@id='facet_1']/li[1]/div/label/span");
	public By experienceinCandidateListView = By.xpath(".//*[@id='facet_2']/li[1]/div/label/span");
	public By sourcenameinCandidateListView = By.xpath(".//*[@id='facet_3']/li[1]/div/label/span");
	public By sourcetypeinCandidateListView = By.xpath(".//*[@id='facet_4']/li[1]/div/label/span");
	public By lastmodifieddateinCandidateListView = By.xpath(".//*[@id='facet_5']/li[1]/div/label/span");
	public By spirestatusinCandidateListView = By.xpath(".//*[@id='facet_6']/li[1]/div/label/span");
	public By candidateprofilelistinCandidateListView = By.xpath(".//*[@id='candProLi']/div/div/div[2]");
	public By downloadresumelistinCandidateListView = By
			.xpath(".//*[@id='crmCandProfileList']/ul/div[1]/div[2]/button");
	// public String downloadPath = "C:/Users/Sridhar P/Downloads";
	public By candidateListresult = By.xpath("//*[@id='candEngSpane']");
	public By jobTitleinCandidateListView = By.xpath(".//*[@id='facet_10']/li[6]/div/label");
	public By jobTitleCheckboxinCandidateListView = By.xpath(".//*[@id='facet_10']/li[2]/div/label/span");
	public By selectJobTitleinCandidateResultPage = By
			.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[21]/ul/li/div/div[1]/span");
	public By selectClienStatusinCandidateResultPage = By
			.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[19]/ul/li/div[1]/span");
	public By accenturenaexpectedjobtitle1 = By
			.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[26]/ul/li/div[1]/span | html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[11]/div[2]/li[1]/div/label/i[1]");
	public By jobTitleinCandidateListViewinIndops = By.xpath(".//*[@id='facet_11']/li[4]/div/label/i[1]");
//	public By jobTitleCheckboxinCandidateListView = By.xpath(".//*[@id='facet_10']/li[2]/div/label/span");
	public By selectJobTitleinCandidateResultPageinIndops = By
				.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[8]/ul/li/div[1]/span");

	public By jobTitleCheckboxinCandidateListViewinIndops = By.xpath(".//*[@id='facet_11']/li[2]/div/label/span");
	//public By selectClienStatusinCandidateResultPage = By.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[19]/ul/li/div[1]/span");
    //public By candidateListresult = By.xpath("//*[@id='candEngSpane']");
    public By jobTitleinCandidateListViewinPhilTech = By.xpath(".//*[@id='facet_11']/li[4]/div/label/i[1] | html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[12]/div[1]");
    public By jobTitleCheckboxinCandidateListViewinPhilTech = By.xpath(".//*[@id='facet_11']/li[2]/div/label/span");
    public By expectedjobtitleinPhitech = By
			.xpath(".//*[@id='crmCandProfileList']/ul/div[2]/ul/li[26]/ul/li/div[1]/span | html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul[11]/div[2]/li[1]/div/label/i[1]");
	
 // Added For Free TextSearch:@Ganesh
    public By ResumeSearch = By.xpath("//*[@id='crmCandProfileList']/ul/div[4]/input");
    public By ResumeSearchButton = By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/div[4]/button | //*[@id='ngViewDiv']/div/div[2]/div[1]/div[1]/div/ul/div[4]/button");
    public By CandidateClick = By.xpath("//*[@id='candName']/div/b/span[1]");
    public By ShowMore = By.xpath("//*[@id='ngViewDiv']/div/div[1]/div/div[1]/div[2]/div/div[3]/div[2]/div/a[1]");
    public By candidateSkillMap = By.xpath("//*[@id='ngViewDiv']/div/div[1]/div/div[1]/div[2]/div/div[3]/div[2]/div/span/span[contains(text(),'HTML')]");
    public By DownloadResume= By.xpath("html/body/div[1]/div[2]/div/div[8]/div[1]/div[1]/div/ul/li[1]/div/div/div[3]/div[2]/span/span | //*[@id='ngViewDiv']/div/div[2]/div[1]/div[1]/div/ul/li[1]/div/div[1]/div[3]/div/div[10]/img");

    
    
	public void clickFirstCandidateNameLink() {
		Assertion.assertTrue(cu.isElementPreset(firstCandidateNameLink, driver), "Candidate Name is not present");
		driver.findElement(firstCandidateNameLink).click();

	}

	public List<WebElement> getCandidateList() {
		//cu.explicitWait(driver, candidateList, 120L);
		cu.explicitWait(driver, candidateList);
		return driver.findElements(candidateList);
	}

	public String getExtractedSkillSetforCandidate(int candidateIndex) {
		// return
		// driver.findElement(candidateExtractedSkillSet).getAttribute("title").toLowerCase();
		String extractedSkillSetforCandidate;
		try{
			extractedSkillSetforCandidate = driver
				.findElement(By.xpath("(//div[@ng-attr-title=\"{{skills.skill_val}}\"])[" + candidateIndex + "]"))
				.getAttribute("title").toLowerCase();
		}
		catch(Exception e){
			extractedSkillSetforCandidate = driver
					.findElement(By.xpath("(//div[@ng-init=\"skills=candidateSkills(element.searchResultOnFields)\"])[" + candidateIndex + "]"))
					.getAttribute("title").toLowerCase();
		}
		
		
		// String
		// skillInList=driver.findElement(By.xpath("//li[@id='candProLi']["+candidateIndex+"]/div/div[3]/div[1]/div[1]")).getText();
		System.out.println("extractedSkillSetforCandidate : " + extractedSkillSetforCandidate);
		return extractedSkillSetforCandidate;
	}

	public int getCandidateCountInPage() {
		//cu.explicitWait(driver, moreLink, 60L);
		cu.explicitWait(driver, moreLink);
		return driver.findElements(moreLink).size();
	}

	/* Added By Sridhar */
	public void clickskillinCandidateListView() {
		this.driver.findElement(skillinCandidateListView).click();
	}

	public void clicklocationinCandidateListView() {
		this.driver.findElement(locationinCandidateListView).click();
	}

	public void clickexperienceinCandidateListView() {
		this.driver.findElement(experienceinCandidateListView).click();
	}

	public void clicksourcenameinCandidateListView() {
		this.driver.findElement(sourcenameinCandidateListView).click();
	}

	public void clicksourcetypeinCandidateListView() {
		this.driver.findElement(sourcetypeinCandidateListView).click();
	}

	public void clicklastmodifieddateinCandidateListView() {
		this.driver.findElement(lastmodifieddateinCandidateListView).click();
	}

	public void clickspirestatusinCandidateListView() {
		this.driver.findElement(spirestatusinCandidateListView).click();
	}

	public WebElement getcandidateprofilelistinCandidateListView() {
		CommonUtils commonUtils = null;
		commonUtils.explicitWait(driver, candidateprofilelistinCandidateListView, 30L);
		return this.driver.findElement(candidateprofilelistinCandidateListView);
	}

	public void downloadresumeinCandidateListView() {
		By downloadresumeinCandidateListView = null;
		this.driver.findElement(downloadresumeinCandidateListView).click();
	}

	public WebElement getExtractedExperienceforCandidate(int candidateIndex) {
		// driver.findElement(candidateExtractedSkillSet).getAttribute("title").toLowerCase();
		List<WebElement> extractedExperienceforCandidate = driver
				.findElements((By.xpath(("//span[contains(@title,'Years')])[" + candidateIndex + "]"))));
		System.out.println("extractedExperienceforCandidate : " + extractedExperienceforCandidate);
		return (WebElement) extractedExperienceforCandidate;

	}
	public List<WebElement> getCandidateListinResultPage() {
		cu.explicitWait(driver, candidateListresult, 60L);
		return driver.findElements(candidateListresult);
	}
}
