package com.guarented.ecommerce.driver;

import java.util.Properties;

import com.guarented.ecommerce.commonUtils.FileReader;



public class Driver {
	public WebDriver driver = null;

	public Driver(WebDriver driver) {
		this.driver = getDriver(driver);
		System.out.println("driver object is " + this.driver);
	}

	public WebDriver getDriver(WebDriver driver) {

		try {

			System.out.println("am in getDriver method");
			Properties prop = new FileReader().loadPropertiesFile("/properties/config.properties");

			if (driver == null) {
				String browserName = ContextManager.getThreadContext().getBrowser();

				if (browserName.equalsIgnoreCase("*Firefox")) {
					ContextManager.getGlobalContext().setOpenReportInBrowser("firefox");
					driver = new FirefoxDriver(downloadFxProfile());
					driver.manage().window().maximize();
				} else if (browserName.equalsIgnoreCase("Chrome")) {
					ContextManager.getGlobalContext().setOpenReportInBrowser("chrome");
					System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverPath"));
					driver = new ChromeDriver(downloadChProfile());
					driver.manage().window().maximize();
				} else if (browserName.equalsIgnoreCase("IE")) {
					ContextManager.getGlobalContext().setOpenReportInBrowser("chrome");
					System.setProperty("webdriver.ie.driver", prop.getProperty("IEDriverPath"));
					driver = new InternetExplorerDriver();
					driver.manage().window().maximize();
				} else {
					driver = new HtmlUnitDriver();
					driver.manage().window().maximize();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logging.log("Exception thrown while intializing the driver " + e.getMessage());
		}

		System.out.println("getDriver method completed");

		return driver;

	}

}
