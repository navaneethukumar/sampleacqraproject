package com.guarented.ecommerce.commonUtils;

public class ApplicationAccessbility {
	CommonUtils cu=new CommonUtils();

	public String[] applicationAccessbility(String appUrl,String userName,String password) throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		WebDriver driver = new ChromeDriver(cap);
		
		String loginResult="";
		String universalSearchResult="";

		driver.get(appUrl);
		cu.implicitWait(driver, 20L);
		
		try {
			
			driver.findElement(By.id("username")).sendKeys(userName);
			driver.findElement(By.id("password")).sendKeys(password);		
			driver.findElement(By.id("kc-login")).click();
			//loginApp(appUrl,userName,password,customerName);
			
			//String reqData=driver.findElement(By.className("spire-list-div width-18 ng-binding")).getText();
			//String reqData=driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[1]/div/div[1]/div[1]")).getText();
			try {
				cu.explicitWait(driver, By.xpath("//*[contains(text(),'Req ')]"), 30L);
				int reqListSize=driver.findElements(By.xpath("//*[contains(text(),'Req ')]")).size();
				 if(reqListSize>=1)
					 loginResult="Pass";		 
			} catch (Exception e) {
				try{
				cu.explicitWait(driver, By.xpath("//*[contains(text(),'SR NO:')]"), 30L);
				int reqListSize=driver.findElements(By.xpath("//*[contains(text(),'SR NO:')]")).size();
				 if(reqListSize>=1)
					 loginResult="Pass";
				}catch(Exception e1) {
				loginResult="Fail, Data is not loaded in Req Grid";
				}
			}
			
			
			/*
			//starting home page search
			
				Thread.sleep(4000);
			
			String homePageSearchResult="";
			try {
				String reqDivText=driver.findElement(By.xpath("(//div[contains(text(),'Req ID:')])[1]")).getText();
				String[] firstReqNoArr=reqDivText.split(":");
				String firstReq=firstReqNoArr[1];
				driver.findElement(By.xpath("//input[@class='ui-select-search input-xs ng-pristine ng-untouched ng-valid ng-empty']")).sendKeys(firstReq);
				Thread.sleep(4000);
				
				//driver.findElement(By.xpath("//*[@id='ui-select-choices-row-0-2']/a/span")).click();
				driver.findElement(By.xpath("//*[contains(@id,'ui-select-choices-row-')]/a/span")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				Thread.sleep(4000);
				
				int searchedListSize=driver.findElements(By.xpath("(//*[@ng-repeat='req in reqGridObj.list'])")).size();
				System.out.println("searchedListSize : " + searchedListSize);
				String searchedReqText=driver.findElement(By.xpath("(//div[contains(text(),'Req ID:')])[1]")).getText();
				if(searchedListSize==1 && searchedReqText.contains(firstReq)){
					homePageSearchResult="Pass";
				}
				else{
					homePageSearchResult="Fail";
					}
			} catch (Exception e) {
				homePageSearchResult="Fail, Req Grid is not loading";
				e.printStackTrace();
			}
			*/
			
			//starting universal search
			
			//Thread.sleep(4000);
			
			
			
			try {
				cu.explicitWait(driver, By.id("search-bar-button"), 5L);
				driver.findElement(By.id("search-bar-button")).click();
				//Thread.sleep(4000);
				try {
					cu.explicitWait(driver, By.xpath(".//*[@id='headerColDiv']/div/div/div/div[2]/form/div/div/div/div/div/input"), 10L);
					driver.findElement(By.xpath(".//*[@id='headerColDiv']/div/div/div/div[2]/form/div/div/div/div/div/input")).sendKeys("java");
					
				} catch (Exception e) {
					cu.explicitWait(driver, By.xpath(".//*[@id='headerColDiv']/div/div/div/div[3]/form/div/div/div/div/div/input"), 10L);
					driver.findElement(By.xpath(".//*[@id='headerColDiv']/div/div/div/div[3]/form/div/div/div/div/div/input")).sendKeys("java");
					
				}
				//Thread.sleep(4000);
				cu.explicitWait(driver, By.xpath("(//span[text()='Skill'])[1]"), 10L);
				driver.findElement(By.xpath("(//span[text()='Skill'])[1]")).click();
				//Thread.sleep(1000);
				cu.explicitWait(driver, By.id("search-btn"), 2L);
				driver.findElement(By.id("search-btn")).click();
				//Thread.sleep(10000);
				try{
					cu.explicitWait(driver, By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]"), 2*50L);	
					//cu.explicitWait(driver, By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]"), 50L);
				driver.findElement(By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]")).click();
				universalSearchResult="Pass";
				}catch(Exception e){
					universalSearchResult="Fail";
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				universalSearchResult="Fail, Due to some other reason like App slowness , office netowrk slowness etc";
				e.printStackTrace();
			}
		} catch (Exception e) {
			loginResult="Fail, Application is not accesibile, login page not appeared";
			universalSearchResult="Fail,Application is not accesibile, login page itself not appeared";
			e.printStackTrace();
		}
		
		
		
		String []result= new String[5];
		
		result[0]=appUrl;
		result[1]=loginResult;
		//result[2]=homePageSearchResult;
		result[3]=universalSearchResult;
		
		if(loginResult.contains("Pass") && universalSearchResult.contains("Pass"))
		result[4]="Pass";
		else result[4]= "Fail";
		
		driver.close();
		return result;
	}
	
public String[] applicationAccessbility(String appUrl,String userName,String password,String customerName) throws InterruptedException{
	
	System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
	
	DesiredCapabilities cap = DesiredCapabilities.chrome();
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
	WebDriver driver = new ChromeDriver(cap);
	
	String loginResult="";
	String universalSearchResult="";

	driver.get(appUrl);
	cu.implicitWait(driver, 20L);
	
	try {
		if(customerName.equalsIgnoreCase("AccentureNA")){
			driver.findElement(By.xpath("//*[@id='kc-form-wrapper']/a")).click();
		}
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);		
		driver.findElement(By.id("kc-login")).click();
		//loginApp(appUrl,userName,password,customerName);
		
		//String reqData=driver.findElement(By.className("spire-list-div width-18 ng-binding")).getText();
		//String reqData=driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[1]/div/div[1]/div[1]")).getText();
		try {
			cu.explicitWait(driver, By.xpath("//div[contains(text(),'Req ID:')]"), 30L);
			int reqListSize=driver.findElements(By.xpath("//div[contains(text(),'Req ID:')]")).size();
			 if(reqListSize>=1)
				 loginResult="Pass";		 
		} catch (Exception e) {
			try{
			cu.explicitWait(driver, By.xpath("//*[contains(text(),'SR NO:')]"), 30L);
			int reqListSize=driver.findElements(By.xpath("//*[contains(text(),'SR NO:')]")).size();
			 if(reqListSize>=1)
				 loginResult="Pass";
			}catch(Exception e1) {
			loginResult="Fail, Data is not loaded in Req Grid";
			}
		}
		
		
		/*
		//starting home page search
		
			Thread.sleep(4000);
		
		String homePageSearchResult="";
		try {
			String reqDivText=driver.findElement(By.xpath("(//div[contains(text(),'Req ID:')])[1]")).getText();
			String[] firstReqNoArr=reqDivText.split(":");
			String firstReq=firstReqNoArr[1];
			driver.findElement(By.xpath("//input[@class='ui-select-search input-xs ng-pristine ng-untouched ng-valid ng-empty']")).sendKeys(firstReq);
			Thread.sleep(4000);
			
			//driver.findElement(By.xpath("//*[@id='ui-select-choices-row-0-2']/a/span")).click();
			driver.findElement(By.xpath("//*[contains(@id,'ui-select-choices-row-')]/a/span")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			Thread.sleep(4000);
			
			int searchedListSize=driver.findElements(By.xpath("(//*[@ng-repeat='req in reqGridObj.list'])")).size();
			System.out.println("searchedListSize : " + searchedListSize);
			String searchedReqText=driver.findElement(By.xpath("(//div[contains(text(),'Req ID:')])[1]")).getText();
			if(searchedListSize==1 && searchedReqText.contains(firstReq)){
				homePageSearchResult="Pass";
			}
			else{
				homePageSearchResult="Fail";
				}
		} catch (Exception e) {
			homePageSearchResult="Fail, Req Grid is not loading";
			e.printStackTrace();
		}
		*/
		
		//starting universal search
		
		//Thread.sleep(4000);
		
		
		
		try {
			cu.explicitWait(driver, By.id("search-bar-button"), 5L);
			driver.findElement(By.id("search-bar-button")).click();
			//Thread.sleep(4000);
			cu.explicitWait(driver, By.xpath(".//*[@id='headerColDiv']/div/div/div/div[2]/form/div/div/div/div/div/input"), 10L);
			driver.findElement(By.xpath(".//*[@id='headerColDiv']/div/div/div/div[2]/form/div/div/div/div/div/input")).sendKeys("java");
			//Thread.sleep(4000);
			cu.explicitWait(driver, By.xpath("(//span[text()='Skill'])[1]"), 10L);
			driver.findElement(By.xpath("(//span[text()='Skill'])[1]")).click();
			//Thread.sleep(1000);
			cu.explicitWait(driver, By.id("search-btn"), 2L);
			driver.findElement(By.id("search-btn")).click();
			//Thread.sleep(10000);
			try{
				cu.explicitWait(driver, By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]"), 50L);	
				//cu.explicitWait(driver, By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]"), 50L);
			driver.findElement(By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]")).click();
			universalSearchResult="Pass";
			}catch(Exception e){
				universalSearchResult="Fail";
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			universalSearchResult="Fail, Due to some other reason like App slowness , office netowrk slowness etc";
			e.printStackTrace();
		}
	} catch (Exception e) {
		loginResult="Fail, Application is not accesibile, login page not appeared";
		universalSearchResult="Fail,Application is not accesibile, login page itself not appeared";
		e.printStackTrace();
	}
	
	
	
	String []result= new String[5];
	
	result[0]=appUrl;
	result[1]=loginResult;
	//result[2]=homePageSearchResult;
	result[3]=universalSearchResult;
	
	if(loginResult.contains("Pass") && universalSearchResult.contains("Pass"))
	result[4]="Pass";
	else result[4]= "Fail";
	
	driver.close();
	return result;
}

public String[] applicationAccessbility_AccentureNA(String appUrl,String userName,String password) throws InterruptedException{
	
	System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
	
	DesiredCapabilities cap = DesiredCapabilities.chrome();
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
	WebDriver driver = new ChromeDriver(cap);
	CommonUtils cu=new CommonUtils();
	String loginResult="";
	String universalSearchResult="";

	driver.get(appUrl);
	cu.implicitWait(driver, 20L);
	//Thread.sleep(8000);
	try {
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("kc-login")).click();
		//Thread.sleep(10000);
		
		//String reqData=driver.findElement(By.className("spire-list-div width-18 ng-binding")).getText();
		//String reqData=driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[1]/div/div[1]/div[1]")).getText();
		try {
			cu.explicitWait(driver, By.xpath("//*[contains(text(),'Req ')]"), 30L);
			int reqListSize=driver.findElements(By.xpath("//*[contains(text(),'Req ')]")).size();
			 if(reqListSize>=1)
				 loginResult="Pass";		 
		} catch (Exception e) {
			try{
			cu.explicitWait(driver, By.xpath("//*[contains(text(),'SR NO:')]"), 30L);
			int reqListSize=driver.findElements(By.xpath("//*[contains(text(),'SR NO:')]")).size();
			 if(reqListSize>=1)
				 loginResult="Pass";
			}catch(Exception e1) {
			loginResult="Fail, Data is not loaded in Req Grid";
			}
		}
		
		
		
		//starting universal search
		
		//Thread.sleep(4000);
		
		
		
		try {
			cu.explicitWait(driver, By.xpath("//a[text()='Search']"), 5L);
			driver.findElement(By.xpath("//a[text()='Search']")).click();
			//Thread.sleep(5000);
			driver.manage().window().maximize();
			cu.explicitWait(driver, By.xpath("//*[@id='main']"), 10L);
			//Thread.sleep(5000);
			System.out.println("Element Display Status : "+driver.findElement(By.xpath("//*[@id='main']")).isDisplayed());
			System.out.println("Element Enable Status : "+driver.findElement(By.xpath("//*[@id='main']")).isEnabled());
			
			/*//To enable Last Name text box 
			JavascriptExecutor javascript = (JavascriptExecutor) driver;
			String toenable = "document.getElementById('autocomplete')[0].removeAttribute('disabled');"; 
			//String toenable = "document.getElementById(\"autocomplete\")[0].disabled=false;"; 
			
			javascript.executeScript(toenable); 
			//document.getElementById("autocomplete").
			Thread.sleep(3000);*/
			
			
			driver.findElement(By.xpath("//*[@id='main']")).click();
			driver.findElement(By.xpath("//*[@id='main']")).sendKeys("java");
			//Thread.sleep(4000);
			//cu.explicitWait(driver, By.xpath("(//span[text()='skill'])[1]"), 10L);
			//driver.findElement(By.xpath("(//span[text()='skill'])[1]")).click();
			//Thread.sleep(1000);
			cu.explicitWait(driver, By.xpath("//*[@id='action-bar-top-div']/div/div/div[2]/div[1]/form/button"), 2L);
			driver.findElement(By.xpath("//*[@id='action-bar-top-div']/div/div/div[2]/div[1]/form/button")).click();
			
			try{
				cu.explicitWait(driver, By.xpath("//*[@id='candProcandImg']"), 50L);	
				//cu.explicitWait(driver, By.xpath("(//*[@src='/images/unknown.jpg'])[2]"), 50L);	
				//Thread.sleep(20000);
			driver.findElement(By.xpath("(//*[@id='candProcandImg'])[2]")).click();
				//driver.findElement(By.xpath("//*[@id='candName']/span[1]")).click();
				
			universalSearchResult="Pass";
			}catch(Exception e){
				universalSearchResult="Fail";
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			universalSearchResult="Fail, Due to some other reason like App slowness , office netowrk slowness etc";
			e.printStackTrace();
		}
	} catch (Exception e) {
		loginResult="Fail, Application is not accesibile, login page not appeared";
		universalSearchResult="Fail,Application is not accesibile, login page itself not appeared";
		e.printStackTrace();
	}
	
	
	
	String []result= new String[5];
	
	result[0]=appUrl;
	result[1]=loginResult;
	//result[2]=homePageSearchResult;
	result[3]=universalSearchResult;
	
	if(loginResult.contains("Pass") && universalSearchResult.contains("Pass"))
	result[4]="Pass";
	else result[4]= "Fail";
	
	driver.close();
	return result;
}

public String[] applicationAccessbility_AccentureIDC(String appUrl,String userName,String password) throws InterruptedException{
	
	System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
	
	DesiredCapabilities cap = DesiredCapabilities.chrome();
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
	WebDriver driver = new ChromeDriver(cap);
	CommonUtils cu=new CommonUtils();
	String loginResult="";
	String universalSearchResult="";

	driver.get(appUrl);
	cu.implicitWait(driver, 20L);
	//Thread.sleep(8000);
	try {
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("kc-login")).click();
		//Thread.sleep(10000);
		
		//String reqData=driver.findElement(By.className("spire-list-div width-18 ng-binding")).getText();
		//String reqData=driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[1]/div/div[1]/div[1]")).getText();
		try {
			cu.explicitWait(driver, By.xpath("//*[@id='reqid_0']"), 30L);
			int reqListSize=driver.findElements(By.xpath("//*[@id='reqid_0']")).size();
			 if(reqListSize>=1)
				 loginResult="Pass";		 
		} catch (Exception e) {
			
			loginResult="Fail, Data is not loaded in Req Grid";
			
		}
		
		
		
		//starting universal search
		
		//Thread.sleep(4000);
		
		
		
		try {
			cu.explicitWait(driver, By.id("searchField"), 5L);
			driver.findElement(By.id("searchField")).click();
			
			driver.findElement(By.xpath("//*[@id='searchField']")).sendKeys("java");
			//Thread.sleep(4000);
			cu.explicitWait(driver, By.xpath("(//span[text()='skill'])[1]"), 10L);
			driver.findElement(By.xpath("(//span[text()='skill'])[1]")).click();
			//Thread.sleep(1000);
			cu.explicitWait(driver, By.id("main-search"), 2L);
			driver.findElement(By.id("main-search")).click();
			//Thread.sleep(90000);
			try{
				cu.explicitWait(driver, By.xpath("//*[@id='candidateInfoID']/div[1]/img"), 60L);
				//cu.explicitWait(driver, By.xpath("//*[@id='candidateInfoID']/div[1]/img"), 60L);
				Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='candidateInfoID']/div[1]/img")).click();
			universalSearchResult="Pass";
			}catch(Exception e){
				universalSearchResult="Fail";
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			universalSearchResult="Fail, Due to some other reason like App slowness , office netowrk slowness etc";
			e.printStackTrace();
		}
	} catch (Exception e) {
		loginResult="Fail, Application is not accesibile, login page not appeared";
		universalSearchResult="Fail,Application is not accesibile, login page itself not appeared";
		e.printStackTrace();
	}
	
	
	
	String []result= new String[5];
	
	result[0]=appUrl;
	result[1]=loginResult;
	//result[2]=homePageSearchResult;
	result[3]=universalSearchResult;
	
	if(loginResult.contains("Pass") && universalSearchResult.contains("Pass"))
	result[4]="Pass";
	else result[4]= "Fail";
	
	driver.close();
	return result;
}

public String[] applicationAccessbility_AccentureGlobal(String appUrl,String userName,String password) throws InterruptedException{
	
	System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
	
	DesiredCapabilities cap = DesiredCapabilities.chrome();
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
	WebDriver driver = new ChromeDriver(cap);
	CommonUtils cu=new CommonUtils();
	String loginResult="";
	String universalSearchResult="";

	driver.get(appUrl);
	cu.implicitWait(driver, 20L);
	//Thread.sleep(8000);
	try {
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("kc-login")).click();
		//Thread.sleep(10000);
		
		//String reqData=driver.findElement(By.className("spire-list-div width-18 ng-binding")).getText();
		//String reqData=driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/div/div/div[4]/div[2]/div[1]/div[1]/ul/li[1]/div/div[1]/div[1]")).getText();
		try {
			cu.explicitWait(driver, By.xpath("//*[@id='ngViewDiv']/div/form/span/span/a"), 20L);		
				 loginResult="Pass";		 
		} catch (Exception e) {
			loginResult="Fail, Login did not happen";
			e.printStackTrace();
		}
		
		
		
		//starting universal search
		
		//Thread.sleep(4000);
		
		
		
		try {
			//System.out.println("10");
			//Thread.sleep(10000);
			cu.explicitWait(driver, By.xpath("(//*[@id='search-bar-button'])[2]"), 15L);
			driver.findElement(By.xpath("(//*[@id='search-bar-button'])[2]")).click();
			//Thread.sleep(4000);
			//System.out.println("11");
			cu.explicitWait(driver, By.xpath("//*[@id='ngViewDiv']/div/form/div/div/div/div/div/input"), 10L);
			//System.out.println("12");
			driver.findElement(By.xpath("//*[@id='ngViewDiv']/div/form/div/div/div/div/div/input")).sendKeys("java");
			//Thread.sleep(4000);
			cu.explicitWait(driver, By.xpath("(//span[text()='Skill'])[1]"), 10L);
			driver.findElement(By.xpath("(//span[text()='Skill'])[1]")).click();
			//System.out.println("13");
			Thread.sleep(1000);
			cu.explicitWait(driver, By.xpath("(//*[@id='search-btn'])[2]"), 2L);
			driver.findElement(By.xpath("(//*[@id='search-btn'])[2]")).click();
			//System.out.println("14");
			//Thread.sleep(50000);
			
			try{
				cu.explicitWait(driver, By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]"), 60L);
				//cu.explicitWait(driver, By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]"), 60L);
			driver.findElement(By.xpath("(//*[@src='assets/images/unknown.jpg'])[2]")).click();
			universalSearchResult="Pass";
			}catch(Exception e){
				universalSearchResult="Fail";
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			universalSearchResult="Fail, Due to some other reason like App slowness , office netowrk slowness etc";
			e.printStackTrace();
		}
	} catch (Exception e) {
		loginResult="Fail, Application is not accesibile, login page not appeared";
		universalSearchResult="Fail,Application is not accesibile, login page itself not appeared";
		e.printStackTrace();
	}
	
	
	
	String []result= new String[5];
	
	result[0]=appUrl;
	result[1]=loginResult;
	//result[2]=homePageSearchResult;
	result[3]=universalSearchResult;
	
	if(loginResult.contains("Pass") && universalSearchResult.contains("Pass"))
	result[4]="Pass";
	else result[4]= "Fail";
	
	driver.close();
	return result;
}


public void loginApp(String appUrl,String userName,String password,String customerName){
    System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
	
	DesiredCapabilities cap = DesiredCapabilities.chrome();
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
	WebDriver driver = new ChromeDriver(cap);
	driver.get(appUrl);
	cu.implicitWait(driver, 20L);
	if (customerName.equalsIgnoreCase("AccentureNA"))
		driver.findElement(By.xpath("//*[@id='kc-form-wrapper']/a")).click();
	driver.findElement(By.id("username")).sendKeys(userName);
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.id("kc-login")).click();
}
}
