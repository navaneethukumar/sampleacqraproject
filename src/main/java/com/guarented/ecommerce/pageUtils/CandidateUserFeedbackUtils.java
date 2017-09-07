package com.guarented.ecommerce.pageUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.pageUtils.HomePageUtils;
import com.guarented.ecommerce.pages.CandidateListViewPage;
import com.guarented.ecommerce.pages.CandidateUserFeedbackPage;

public class CandidateUserFeedbackUtils extends CandidateUserFeedbackPage {

	CommonUtils cu = new CommonUtils();
	HomePageUtils hu = new HomePageUtils(driver);
	public String[] experiencevalue;
	private String Actualtext;

	public CandidateUserFeedbackUtils(WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	public void positiveFeedBackData(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		
		clickThumsUpSymbol();
		Thread.sleep(5000);
		positivefeedback();
		
	}
		else if (clientName.equalsIgnoreCase("AccentureNA")) {
			clickThumsUpSymbol();
			Thread.sleep(5000);
		positivefeedback();
			
		}
		}
	public void NegativeFeedBackData(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsDownSymbol();
		Thread.sleep(5000);
	Nagativefeedback();
		
		
	}
		else if (clientName.equalsIgnoreCase("AccentureNA")) {
			clickThumsDownSymbol();
			Thread.sleep(5000);

			
			driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[2]/div/div[3]/div[1]/input")).click();
			
			Nagativefeedback();
			
			
		}
		}
	public void windowhandleforPhilTech(String customerName) throws InterruptedException{
			 String parentHandle = driver.getWindowHandle();
			 HomePageUtils hpu=new HomePageUtils(driver);
			//hu.clickorgunitylevelinPhilTech(customerName);
		for (String winHandle : driver.getWindowHandles()) {
		driver.switchTo().window(winHandle); // switch focus of WebDriver to
			
		}
		}
	public void NegativeFeedBackDataThroughCount(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsDownSymbol();
		Thread.sleep(5000);
	NagativefeedbackThroughCount();
		
		
	}
		else if (clientName.equalsIgnoreCase("AccentureNA")) {
			clickThumsDownSymbol();
			Thread.sleep(5000);

			
			driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[2]/div/div[3]/div[1]/input")).click();
			
			NagativefeedbackThroughCount();
			
			
		}
		}
	public void positiveFeedBackDataThroughCount(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		
		clickThumsUpSymbol();
		Thread.sleep(5000);
		positivefeedbackThroughCount();
		
	}
		else if (clientName.equalsIgnoreCase("AccentureNA")) {
			clickThumsUpSymbol();
			Thread.sleep(5000);
		positivefeedbackThroughCount();
			
		}
		}
	public void createAndVerifyNegativeFeedBackDatafromcount(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		
		clickThumsDownSymbol();
		Thread.sleep(5000);
		createAndVerifyNegativefeedbackThroughCount();
	}
		else if (clientName.equalsIgnoreCase("AccentureNA")) {
			clickThumsDownSymbol();
			Thread.sleep(5000);

			
			//driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[2]/div/div[3]/div[1]/input")).click();
			
			createAndVerifyNegativefeedbackThroughCount();
		
		
	}}
	public void createAndVerifyNegativeFeedBackData(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		
		clickThumsDownSymbol();
		Thread.sleep(5000);
	createAndVerifyNegativefeedback();
	}
		else if (clientName.equalsIgnoreCase("AccentureNA")) {
			clickThumsDownSymbol();
			Thread.sleep(5000);

			
			//driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[2]/div/div[3]/div[1]/input")).click();
			createAndVerifyNegativefeedback();
		
		
	}}
	public void createAndVerifyPositiveFeedBackDatathroughcount(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsUpSymbol();
		Thread.sleep(5000);
		createAndVerifyPositivefeedbackthroughcount();
		}
	else if (clientName.equalsIgnoreCase("AccentureNA")) {
		clickThumsUpSymbol();
		Thread.sleep(5000);
		
	//	driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[1]/div/div[3]/div[1]/input")).click();
		createAndVerifyPositivefeedbackthroughcount();
		}
		
	}
	public void createAndVerifyPositiveFeedBackData(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsUpSymbol();
		Thread.sleep(5000);
		createAndVerifyPositivefeedback();
		}
	else if (clientName.equalsIgnoreCase("AccentureNA")) {
		clickThumsUpSymbol();
		Thread.sleep(5000);
		
	//	driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[1]/div/div[3]/div[1]/input")).click();
		
		createAndVerifyPositivefeedback();
		}
		
	}
	public void updatePositiveFeedBackData(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsUpSymbol();
		Thread.sleep(5000);
		updatePositivefeedback();
		}
	else if (clientName.equalsIgnoreCase("AccentureNA")) {
		clickThumsUpSymbol();
		Thread.sleep(5000);
		
	//	driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[1]/div/div[3]/div[1]/input")).click();
		
		updatePositivefeedback();
		}
		
	}
	public void updatePositiveFeedBackDatathroughcount(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsUpSymbol();
		Thread.sleep(5000);
		updatePositivefeedbackthroughcount();
		}
	else if (clientName.equalsIgnoreCase("AccentureNA")) {
		clickThumsUpSymbol();
		Thread.sleep(5000);
		
	//	driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[1]/div/div[3]/div[1]/input")).click();
		updatePositivefeedbackthroughcount();
		}
		
	}
	public void updateNegativeFeedBackDatathroughcount(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsDownSymbol();
		Thread.sleep(5000);
		updateNegativefeedbackthroughcount();
		}
	else if (clientName.equalsIgnoreCase("AccentureNA")) {
		clickThumsDownSymbol();
		Thread.sleep(5000);
		
	//	driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[1]/div/div[3]/div[1]/input")).click();
		updateNegativefeedbackthroughcount();
		}
		
	}
	public void updateNegativeFeedBackData(String clientName) throws InterruptedException{
		if (clientName.equalsIgnoreCase("AccenturePhilTech")){
		clickThumsDownSymbol();
		Thread.sleep(5000);
		updateNegativefeedback();
		}
	else if (clientName.equalsIgnoreCase("AccentureNA")) {
		clickThumsUpSymbol();
		Thread.sleep(5000);
		
	//	driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[1]/div/div[3]/div[1]/input")).click();
		updateNegativefeedback();
		}
		
	}

	

}
