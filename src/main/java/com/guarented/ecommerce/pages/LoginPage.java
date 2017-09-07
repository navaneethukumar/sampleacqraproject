package com.guarented.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.driver.Driver;
import com.spire.base.controller.ContextManager;
import com.spire.base.controller.Logging;

public class LoginPage extends Driver{
	

	public By textMsg = By.id("kc-header-wrapper");
	protected By userName = By.id("username");
	protected By password = By.id ("password");
	protected By submit = By.name("login");
	protected By cancel = By.name("cancel");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	 public LoginPage(WebDriver driver, boolean openPageUrl) {
	        super(driver);
	        if (openPageUrl) {
	        	System.out.println("opening page url");
	            this.driver.navigate().to(getPageUrl());
	            System.out.println("URL opened");
	        }
	    }

	    public String getPageUrl() {

	        String instanceName = (String) ContextManager.getThreadContext().getInstance();
	        String host = (String) ContextManager.getThreadContext().getHostAddress();
	        System.out.println("HOST : "+host);
	        return host;
	        
	    }
	    
	    public String getUserName() {

	        String userName = (String) ContextManager.getThreadContext().getUserid();
	        System.out.println("User Name from xml : "+userName);
	        return userName;
	        
	    }
	    
	    public String getPassword() {

	        String password = (String) ContextManager.getThreadContext().getPassword();
	        System.out.println("Password from xml : "+password);
	        return password;
	        
	    }

	    public String getTextMsg(){
			return driver.findElement(textMsg).getText();
		}
	    
	    public void enterUserName(String name) {
	    	
	    	Logging.log("Entering the Username");
	        this.driver.findElement(userName).sendKeys(name);
	        //return this;
	    }

	    public void enterPassword(String password) {
	    	
	    	Logging.log("Entering the Password");
	        this.driver.findElement(this.password).sendKeys(password);
	       // return this;

	    }

	    public void clickSubmit() throws Exception {
	    	
	    	Logging.log("filled details and clicking on submit");
	        this.driver.findElement(this.submit).click();
	        //Thread.sleep(8000);

	    }
	
	    
	    
}
