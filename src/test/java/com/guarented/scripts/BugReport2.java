package com.guarented.scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class BugReport2 {

	@Parameters({"customerName"})
	@Test
	public void bugReportDownload(String customerName) throws InterruptedException, IOException{
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
		//String downloadFilepath = "D:\\acc-candidate-demand\\bugzilla-report";
		String downloadFilepath = "E:\\bug_report";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("download.prompt_for_download", false);		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriver driver = new ChromeDriver(cap);

		StringBuilder htmlStringBuilder=new StringBuilder();


		File dir = new File(downloadFilepath);
		FileUtils.cleanDirectory(dir);

		driver.get("http://bugdb.spire2grow.com/");
		
		
		//driver.get("https://app.spire2grow.com/spire-bugzilla/");
		
		driver.findElement(By.xpath(".//*[@id='login_link_top']")).click();
		driver.findElement(By.xpath(".//*[@id='Bugzilla_login_top']")).clear();
		driver.findElement(By.xpath(".//*[@id='Bugzilla_login_top']")).sendKeys("rahul.yadav@spire2grow.com");
		driver.findElement(By.xpath(".//*[@id='Bugzilla_password_top']")).clear();
		driver.findElement(By.xpath(".//*[@id='Bugzilla_password_top']")).sendKeys("rahul@123");

		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='log_in_top']")).click();

		Thread.sleep(2000);
		try{
			driver.findElement(By.xpath("//*[@id='Bugzilla_login']")).sendKeys("rahul.yadav@spire2grow.com");
		    driver.findElement(By.xpath("//*[@id='Bugzilla_password']")).sendKeys("rahul@123");
		    driver.findElement(By.xpath(".//*[@id='log_in']")).click();
		}catch(Exception e) {
			System.out.println("Rahul1");
			// TODO: handle exception
		}
		try {
			Alert alert=driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO: handle exception
		}

        String url= "http://bugdb.spire2grow.com/buglist.cgi?columnlist=bug_id%2Cproduct%2Ccomponent%2Creporter_realname%2Ccf_customer%2Cbug_status%2Cassigned_to_realname%2Cpriority%2Ccf_impact_type%2Copendate%2Cshort_desc&f1=cf_customer&list_id=15903&o1=equals&order=priority%2Cbug_id&query_format=advanced&resolution=---&resolution=---&v1="+customerName;
    	System.out.println("Accessed URL : " +url);	
		driver.navigate().to(url);
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//*[@id='bugzilla-body']/div[2]/div[2]/a[1]/img")).click();

			Thread.sleep(5000);
			driver.close();
			
			String csvFileToRead = downloadFilepath+"\\bugs-"+LocalDate.now()+".csv";
			BufferedReader br=null ;
			String line = "";
			String splitBy = ",";

			try {

				br = new BufferedReader(new FileReader(csvFileToRead));

				htmlStringBuilder.append("<html><head><title>Bug Report</title></head>");
				htmlStringBuilder.append("<body><center>");
				htmlStringBuilder.append("<b><u>Bug Report On : "+LocalDate.now()+"</u></b>");
				htmlStringBuilder.append("<br>");
				htmlStringBuilder.append("<br>");
				htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
				int count=1;
				String[] columns;
				while ((line = br.readLine()) != null) {

					columns = line.split(splitBy);
					if(count==1){
						htmlStringBuilder.append("<tr><td><b>"+columns[0]+"</b></td><td><b>"+columns[2]+"</b></td><td><b>"+columns[3]+"</b></td><td><b>"+columns[5]+"</b></td><td><b>"+columns[6]+"</b></td><td><b>"+columns[7]+"</b></td><td><b>"+columns[8]+"</b></td><td><b>"+columns[9]+"</b></td><td><b>"+columns[10]+"</b></td></tr>");
					}else{
						htmlStringBuilder.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[3]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[8]+"</td><td>"+columns[9]+"</td><td>"+columns[10]+"</td></tr>");

					}   
					count++;

				}

				htmlStringBuilder.append("</table></center></body></html>");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			driver.close();
			System.out.println("In catch block");
			htmlStringBuilder.append("<html><head><title>Bug Report</title></head>");
			htmlStringBuilder.append("<body><center>");
			htmlStringBuilder.append("<b><u>Bug Report On : "+LocalDate.now()+"</u></b>");
			htmlStringBuilder.append("<br>");
			htmlStringBuilder.append("<br>");
			htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
			htmlStringBuilder.append("<tr><td><b>No Bug Found</b></td></tr>");
			htmlStringBuilder.append("</table></center></body></html>");
		}
		String preparedHtmlString=htmlStringBuilder.toString();
		System.out.println("HTML String : "+preparedHtmlString);

		String htmlfile="./test-output/BugReport-"+customerName+".html";
		File file = new File(htmlfile);

		if (file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer=new OutputStreamWriter(outputStream);
		writer.write(preparedHtmlString);
		writer.close();

	}
}




