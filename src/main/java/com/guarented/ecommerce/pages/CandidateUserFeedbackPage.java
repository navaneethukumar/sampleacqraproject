package com.guarented.ecommerce.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.driver.Driver;
import com.spire.base.controller.Logging;

public class CandidateUserFeedbackPage extends Driver {
	public CandidateUserFeedbackPage(WebDriver driver) {
		super(driver);
	}

	CommonUtils cu = new CommonUtils();
	String searchParameterType;

	public By thumsUpSaveButton=By.xpath("//Button[contains(text(), 'Save')][1]");
	public By thumsup=By.xpath("//*[@id='userfeedback']/div/div[1]/span");
	public By thumsDown=By.xpath(".//*[@id='userfeedback']/div/div[2]/span");

	public By thumsDownFeedbackTextBox=By.xpath("//*[@id='userfeedback']/div/div[2]/div/textarea");
	public By thumsUpfeedbackTextbox=By.xpath(".//*[@id='userfeedback']/div/div[1]/div/textarea| .//*[@id='userfeedback']/div/div[1]/div/textarea");
	public By thumsDownSaveButton=By.xpath(".//*[@id='userfeedback']/div/div[2]/div/div[4]/button[1] | .//*[@id='userfeedback']/div/div[2]/div/div[3]/button[1] | .//*[@id='userfeedback']/div/div[2]/div/div[3]/button[1]");




	public void clickThumsUpSymbol() throws InterruptedException{
		Thread.sleep(7000);
		Logging.log("Clicking Thums up Symbol Icon");
		cu.explicitWait(driver, thumsup, 30L);
		this.driver.findElement(thumsup).click();
		
	}
	public void positivefeedback() throws InterruptedException {
		getthumsUpfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		
		getthumsUpfeedbackTextbox().clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsUpfeedbackTextbox().sendKeys(randomfeedbackdata);
		
		Logging.log("thumsup Symbol feedback Save   Button is being clicked.");
		//windowScrollBarinFeedbackPage();
			this.driver.findElement(thumsUpSaveButton).click();
		Thread.sleep(5000);
		
		String actualPopupmessage=driver.findElement(By.xpath("html/body/md-toast/div/span")).getText();
		String expectedPopupmessage="Feedback submitted successfully";
		Assert.assertEquals(actualPopupmessage, expectedPopupmessage);
		Logging.log("positive feed back created " +getthumsUpfeedbackTextbox().getAttribute("value"));
		}
	public WebElement getthumsUpfeedbackTextbox(){
		cu.explicitWait(driver, thumsUpfeedbackTextbox, 30L);
		return this.driver.findElement(thumsUpfeedbackTextbox);
	}
	public void clickThumsDownSymbol() throws InterruptedException{
		Thread.sleep(10000);
		Logging.log("Clicking Thums Down Symbol Icon");
		cu.explicitWait(driver, thumsDown, 30L);
		this.driver.findElement(thumsDown).click();}
	public void Nagativefeedback() throws InterruptedException {
		getthumsDownfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		
		driver.findElement(thumsDownFeedbackTextBox).clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		driver.findElement(thumsDownFeedbackTextBox).sendKeys(randomfeedbackdata);
		
		Logging.log("thumsdown Symbol feedback Save   Button is being clicked.");
		//windowScrollBarinFeedbackPage();
			this.driver.findElement(thumsDownSaveButton).click();
		Thread.sleep(5000);
		String actualPopupmessage=driver.findElement(By.xpath("html/body/md-toast/div/span")).getText();
		String expectedPopupmessage="Feedback submitted successfully";
		Assert.assertEquals(actualPopupmessage, expectedPopupmessage);
		Logging.log("negative feed back created  " + 	getthumsDownfeedbackTextbox().getAttribute("value"));
		}
	public WebElement getthumsDownfeedbackTextbox(){
		cu.explicitWait(driver, thumsDownFeedbackTextBox, 30L);
		return this.driver.findElement(thumsDownFeedbackTextBox);
	}
	public void NagativefeedbackThroughCount() throws InterruptedException {
		getthumsDownfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		
		getthumsDownfeedbackTextbox().clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsDownfeedbackTextbox().sendKeys(randomfeedbackdata);
		
		Logging.log("thumsup Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		
		
	windowScrollBarinFeedbackPageForThumsDownSave();
			this.driver.findElement(thumsDownSaveButton).click();
		Thread.sleep(5000);
		
		String actualPopupmessage=driver.findElement(By.xpath("html/body/md-toast/div/span")).getText();
		String expectedPopupmessage="Feedback submitted successfully";
		Assert.assertEquals(actualPopupmessage, expectedPopupmessage);
		Logging.log("Negative feed back created " +getthumsDownfeedbackTextbox().getAttribute("value"));
		}
	public void windowScrollBarinFeedbackPageForThumsDownSave() throws InterruptedException{
		Actions dragger = new Actions(driver);
		WebElement draggablePartOfScrollbar = driver.findElement(By.xpath(".//*[@id='userfeedback']/div/div[2]/div/div[3]/button[1]"));
		int numberOfPixelsToDragTheScrollbarDown = 400;
		Thread.sleep(5000);
		dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
		  Thread.sleep(5000);
	}
	public void positivefeedbackThroughCount() throws InterruptedException {
		getthumsUpfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		
		getthumsUpfeedbackTextbox().clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsUpfeedbackTextbox().sendKeys(randomfeedbackdata);
		
		Logging.log("thumsup Symbol feedback Save   Button is being clicked.");
	windowScrollBarinFeedbackPage();
			this.driver.findElement(thumsUpSaveButton).click();
		Thread.sleep(5000);
		
		String actualPopupmessage=driver.findElement(By.xpath("html/body/md-toast/div/span")).getText();
		String expectedPopupmessage="Feedback submitted successfully";
		Assert.assertEquals(actualPopupmessage, expectedPopupmessage);
		Logging.log("positive feed back created " +getthumsUpfeedbackTextbox().getAttribute("value"));
		}
	public void createAndVerifyNegativefeedbackThroughCount() throws InterruptedException {

		getthumsDownfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		getthumsDownfeedbackTextbox().clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsDownfeedbackTextbox().sendKeys(randomfeedbackdata);
		Logging.log("after adding feedback in textbox for new candidate    "+getthumsDownfeedbackTextbox().getAttribute("value"));
		Logging.log("thumsdown Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		windowScrollBarinFeedbackPageForThumsDownSave();
			this.driver.findElement(thumsDownSaveButton).click();
			Thread.sleep(5000);
		clickThumsDownSymbolIcon();
		Thread.sleep(5000);
		getthumsDownfeedbackTextbox();
		String expectedfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");
		
		Thread.sleep(5000);
		Assert.assertEquals(randomfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the value from Nagative feedbacktextbox after saving the feedback   "+expectedfeedbackdata);
		System.out.println("feedback is created for candidate");
		}
	public void clickThumsDownSymbolIcon() throws InterruptedException {
		Thread.sleep(2000);
		Logging.log("Clicking Thums Down Symbol Icon");
		cu.explicitWait(driver, thumsup, 30L);
		this.driver.findElement(thumsup).click();
		}
	public void createAndVerifyNegativefeedback() throws InterruptedException {
		getthumsDownfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		getthumsDownfeedbackTextbox().clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsDownfeedbackTextbox().sendKeys(randomfeedbackdata);
		Logging.log("after adding feedback in textbox for new candidate    "+getthumsDownfeedbackTextbox().getAttribute("value"));
		Logging.log("thumsdown Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		getthumsDownfeedbackTextbox().click();
		Thread.sleep(5000);
		
		//clickThumsDownSymbolIcon();
		Thread.sleep(5000);
		getthumsDownfeedbackTextbox();
		String expectedfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");

		Thread.sleep(5000);
		Assert.assertEquals(randomfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the value from Nagative feedbacktextbox after saving the feedback   "+expectedfeedbackdata);
		System.out.println("feedback is created for candidate");
		}
	public void createAndVerifyPositivefeedbackthroughcount() throws InterruptedException {
		
		getthumsUpfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		getthumsUpfeedbackTextbox().clear();
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsUpfeedbackTextbox().sendKeys(randomfeedbackdata);
		Logging.log("after adding feedback in textbox for new candidate    "+getthumsUpfeedbackTextbox().getAttribute("value"));
		
		//Logging.log("after adding feedback in textbox for new candidate    "+getthumsUpfeedbackTextbox().getAttribute("value"));
		Logging.log("thumsup Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		windowScrollBarinFeedbackPage();
		
		driver.findElement(thumsUpSaveButton).click();
		Thread.sleep(5000);
		clickThumsUpSymbolIcon();
		Thread.sleep(5000);
		String expectedfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");
		
		Thread.sleep(5000);
		Assert.assertEquals(randomfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the value from feedbacktextbox after saving the feedback previously  "+expectedfeedbackdata);}
	public void windowScrollBarinFeedbackPage() throws InterruptedException{
		Actions dragger = new Actions(driver);
		WebElement draggablePartOfScrollbar = driver.findElement(By.xpath("//Button[contains(text(), 'Save')][1]"));
		int numberOfPixelsToDragTheScrollbarDown = 300;
		Thread.sleep(5000);
		dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
		  Thread.sleep(5000);
	}
	public void clickThumsUpSymbolIcon() throws InterruptedException {
		Thread.sleep(8000);
		Logging.log("Clicking Thums up Symbol Icon");
		cu.explicitWait(driver, thumsup, 30L);
		this.driver.findElement(thumsup).click();
		}
	public void createAndVerifyPositivefeedback() throws InterruptedException {
		
		getthumsUpfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		getthumsUpfeedbackTextbox().clear();
		
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsUpfeedbackTextbox().sendKeys(randomfeedbackdata);
		Logging.log("after adding feedback in textbox for new candidate    "+getthumsUpfeedbackTextbox().getAttribute("value"));
		Logging.log("thumsup Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		driver.findElement(thumsUpSaveButton).click();
		Thread.sleep(5000);
		clickThumsUpSymbolIcon();
		Thread.sleep(5000);
		String expectedfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");
		Thread.sleep(5000);
		Assert.assertEquals(randomfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the value from feedbacktextbox after saving the feedback   "+expectedfeedbackdata);
		Logging.log("Positive feed back is created for candidate");
		
		}
	public void updatePositivefeedback() throws InterruptedException {
		
		getthumsUpfeedbackTextbox();
		String previousfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");
		Logging.log("getting the  Previous feedback data from feedbacktextbox is  "   +previousfeedbackdata);
		getthumsUpfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsUpfeedbackTextbox().sendKeys(randomfeedbackdata);
		String addingfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");
		Logging.log("getting the total adding  feedbackdata from feedbacktextbox is "   +addingfeedbackdata);
		Logging.log("thumsup Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		driver.findElement(thumsUpSaveButton).click();
		Thread.sleep(5000);
		clickThumsUpSymbolIcon();
		Thread.sleep(5000);
		String expectedfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");
	     Thread.sleep(5000);
		Assert.assertEquals(addingfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the updated feedback data from feedbacktextbox after saving the feedback is   "    +expectedfeedbackdata);
	   Logging.log("positive feedback updated successfully");	
	}
	public void updatePositivefeedbackthroughcount() throws InterruptedException {
		
		getthumsUpfeedbackTextbox();
		String previousfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");
		Logging.log("getting the  Previous feedback data from feedbacktextbox is  "   +previousfeedbackdata);
		getthumsUpfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsUpfeedbackTextbox().sendKeys(randomfeedbackdata);
		String addingfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");
		Logging.log("getting the total adding  feedbackdata from feedbacktextbox is "   +addingfeedbackdata);
		
		//Logging.log("after adding feedback in textbox for new candidate    "+getthumsUpfeedbackTextbox().getAttribute("value"));
		Logging.log("thumsup Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		windowScrollBarinFeedbackPage();
		
		driver.findElement(thumsUpSaveButton).click();
		Thread.sleep(5000);
		clickThumsUpSymbolIcon();
		Thread.sleep(5000);
		String expectedfeedbackdata=getthumsUpfeedbackTextbox().getAttribute("value");

		Thread.sleep(5000);
		Assert.assertEquals(addingfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the updated feedback data from feedbacktextbox after saving the feedback is   "    +expectedfeedbackdata);
	Logging.log("positive feedback updated successfully");	
	}
	public void updateNegativefeedbackthroughcount() throws InterruptedException {
		
		getthumsDownfeedbackTextbox();
		String previousfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");
		Logging.log("getting the  Previous feedback data from feedbacktextbox is  "   +previousfeedbackdata);
		getthumsDownfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsDownfeedbackTextbox().sendKeys(randomfeedbackdata);
		String addingfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");
		Logging.log("getting the total adding  feedbackdata from feedbacktextbox is "   +addingfeedbackdata);
		
		//Logging.log("after adding feedback in textbox for new candidate    "+getthumsUpfeedbackTextbox().getAttribute("value"));
		Logging.log("thumsdown Symbol feedback Save   Button is being clicked.");
		Thread.sleep(8000);
		windowScrollBarinFeedbackPageForThumsDownSave();
		
		driver.findElement(thumsDownSaveButton).click();
		Thread.sleep(5000);
		clickThumsDownSymbolIcon();
		Thread.sleep(5000);
		String expectedfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");

		Thread.sleep(5000);
		Assert.assertEquals(addingfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the updated feedback data from feedbacktextbox after saving the feedback is   "    +expectedfeedbackdata);
	Logging.log("Negative feedback updated successfully");	
	}
	public void updateNegativefeedback() throws InterruptedException {
		
		getthumsDownfeedbackTextbox();
		String previousfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");
		Logging.log("getting the  Previous feedback data from feedbacktextbox is  "   +previousfeedbackdata);
		getthumsDownfeedbackTextbox();
		System.out.println("Typing in Feedback textbox");
		Logging.log("Typing in Feedback textbox");
		Random t = new Random();
		int randomNumber = t.nextInt(1000);
		String randomfeedbackdata = "Save feedback" + randomNumber;
		System.out.println("feedback data is "+randomfeedbackdata);
		getthumsDownfeedbackTextbox().sendKeys(randomfeedbackdata);
		String addingfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");
		Logging.log("getting the total adding  feedbackdata from feedbacktextbox is "   +addingfeedbackdata);
		
		
		Logging.log("thumsdown Symbol feedback Save   Button is being clicked.");
		Thread.sleep(5000);
		driver.findElement(thumsDownSaveButton).click();
		Thread.sleep(5000);
		clickThumsDownSymbolIcon();
		Thread.sleep(5000);
		String expectedfeedbackdata=getthumsDownfeedbackTextbox().getAttribute("value");

		Thread.sleep(5000);
		Assert.assertEquals(addingfeedbackdata, expectedfeedbackdata);
		Logging.log("getting the updated feedback data from feedbacktextbox after saving the feedback is   "    +expectedfeedbackdata);
	Logging.log("Negative  feedback updated successfully");	
	}

	
	}

