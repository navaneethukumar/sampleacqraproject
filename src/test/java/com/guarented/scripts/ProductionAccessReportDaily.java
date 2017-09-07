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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class ProductionAccessReportDaily {

	@Test
	public void productionAccessReportDownload() throws InterruptedException, IOException{
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/browserDriver/chromedriver.exe");
		//String downloadFilepath = "D:\\acc-candidate-demand\\bugzilla-report";
		String downloadFilepath = "E:\\production_access_report";
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

		StringBuilder htmlStringBuilderHead=new StringBuilder();
		StringBuilder htmlStringBuilderTail=new StringBuilder();

		StringBuilder htmlStringBuilder_IDFC=new StringBuilder();
		StringBuilder htmlStringBuilder_AccentureIDC=new StringBuilder();
		StringBuilder htmlStringBuilder_AccentureUS=new StringBuilder();
		StringBuilder htmlStringBuilder_JLL=new StringBuilder();
		StringBuilder htmlStringBuilder_Cognizant=new StringBuilder();
		StringBuilder htmlStringBuilder_TCL=new StringBuilder();
		StringBuilder htmlStringBuilder_Tmobile=new StringBuilder();

		StringBuilder htmlStringBuilder_IDFC1=new StringBuilder();
		StringBuilder htmlStringBuilder_AccentureIDC1=new StringBuilder();
		StringBuilder htmlStringBuilder_AccentureUS1=new StringBuilder();
		StringBuilder htmlStringBuilder_JLL1=new StringBuilder();
		StringBuilder htmlStringBuilder_Cognizant1=new StringBuilder();
		StringBuilder htmlStringBuilder_TCL1=new StringBuilder();
		StringBuilder htmlStringBuilder_Tmobile1=new StringBuilder();
		



		File dir = new File(downloadFilepath);
		FileUtils.cleanDirectory(dir);

		driver.get("https://app.spire2grow.com/spire-bugzilla/");
		driver.findElement(By.xpath(".//*[@id='login_link_top']")).click();
		driver.findElement(By.xpath(".//*[@id='Bugzilla_login_top']")).clear();
		driver.findElement(By.xpath(".//*[@id='Bugzilla_login_top']")).sendKeys("udhayamurthy.alwar@spire2grow.com");
		driver.findElement(By.xpath(".//*[@id='Bugzilla_password_top']")).clear();
		driver.findElement(By.xpath(".//*[@id='Bugzilla_password_top']")).sendKeys("udhayamurthy");

		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='log_in_top']")).click();

		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//*[@id='Bugzilla_login']")).sendKeys("udhayamurthy.alwar@spire2grow.com");
			driver.findElement(By.xpath("//*[@id='Bugzilla_password']")).sendKeys("udhayamurthy");
			driver.findElement(By.xpath(".//*[@id='log_in']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			Alert alert=driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO: handle exception
		}


		driver.navigate().to("https://app.spire2grow.com/spire-bugzilla/query.cgi?bug_status=UNCONFIRMED&bug_status=TO%20FILER%20-%20FOR%20INPUTS&bug_status=TO%20QE%20-%20FOR%20INPUTS&bug_status=TO%20DEV%20-%20FOR%20INPUTS&bug_status=NEW&bug_status=OPEN&bug_status=ON%20HOLD&bug_status=RESOLVED&bug_status=RE-OPENED&bug_status=CLOSED&classification=Production%20Access&columnlist=cf_customer%2Creporter_realname%2Cassigned_to_realname%2Cbug_status%2Copendate%2Cchangeddate%2Cshort_desc%2Cpriority%2Clongdescs.count%2Ccf_access_type&component=Production%20Access%20Request%20C&list_id=31243&product=Production%20Access%20Request&query_format=advanced&resolution=---&resolution=FIXED&resolution=NOT%20AN%20ISSUE&resolution=DUPLICATE&resolution=TECH%20REVIEW&resolution=FUTURE%20DEVELOPMENT&resolution=IN%20UAT&resolution=IN%20PRODUCTION");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='history_filter']/a")).click();

		//to get the bugs of last 1 days
		LocalDate fromDate = LocalDate.now().minusDays(1);
		System.out.println(fromDate);

		driver.findElement(By.xpath(".//*[@id='chfieldfrom']")).sendKeys(fromDate.toString());	
		//driver.findElement(By.xpath(".//*[@id='chfieldto']")).sendKeys("2016-07-18");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='Search']")).click();
		Thread.sleep(5000);
		try{
			driver.findElement(By.xpath("//*[@id='bugzilla-body']/div[2]/div[2]/a[1]/img")).click();
			Thread.sleep(5000);
			driver.close();

			String csvFileToRead = downloadFilepath+"\\bugs-"+LocalDate.now()+".csv";
			BufferedReader br=null ;
			String line = "";
			String splitBy = ",";

			try {

				br = new BufferedReader(new FileReader(csvFileToRead));

				htmlStringBuilderHead.append("<html><head><title>Production Access Daily Report</title></head>");
				htmlStringBuilderHead.append("<body><center>");
				htmlStringBuilderHead.append("<b><u>Production Access Report For : "+LocalDate.now().minusDays(1)+"</u></b>");
				htmlStringBuilderHead.append("<br>");
				htmlStringBuilderHead.append("<br>");
				htmlStringBuilderHead.append("<table border=\"1\" bordercolor=\"#000000\">");
				//htmlStringBuilderHead.append("<table>");
				int count=1,c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0;
				List<String>al= new ArrayList<String>();
				String customerNames="";
				String[] columns;
				while ((line = br.readLine()) != null) {

					//String[] columns = line.split(splitBy);
					columns = line.split(splitBy);

					if(count==1){
						htmlStringBuilderHead.append("<tr><td><b>"+columns[0]+"</b></td><td><b>"+columns[2]+"</b></td><td><b>"+columns[4]+"</b></td><td><b>"+columns[5]+"</b></td><td><b>"+columns[6]+"</b></td><td><b>"+columns[7]+"</b></td><td><b>"+columns[10]+"</b></td></tr>");

					}else{
						al.add(columns[1]);
						customerNames=customerNames+columns[1];

						if(columns[1].contains("IDFC")){
							c1++;
							if(c1==1){
								htmlStringBuilder_IDFC.append("<tr><td><b><font color=\"#0000ff\">Customer : IDFC</font></b></td></tr>");
								htmlStringBuilder_IDFC.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");

							}else{
								htmlStringBuilder_IDFC.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");
							}
						}else if(columns[1].contains("Accenture IDC")) {
							c2++;
							if(c2==1){
								htmlStringBuilder_AccentureIDC.append("<tr><td><b><font color=\"#0000ff\">Customer : Accenture IDC</font></b></td></tr>");
								htmlStringBuilder_AccentureIDC.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");

							}else{
								htmlStringBuilder_AccentureIDC.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");
							}   
						}
						else if(columns[1].contains("Accenture US")) {
							c3++;
							if(c3==1){
								htmlStringBuilder_AccentureUS.append("<tr><td><b><font color=\"#0000ff\">Customer : Accenture US</font></b></td></tr>");
								htmlStringBuilder_AccentureUS.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");

							}else{
								htmlStringBuilder_AccentureUS.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");
							}   
						}

						else if(columns[1].contains("JLL")) {
							c4++;
							if(c4==1){
								htmlStringBuilder_JLL.append("<tr><td><b><font color=\"#0000ff\">Customer : JLL</font></b></td></tr>");
								htmlStringBuilder_JLL.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");

							}else{
								htmlStringBuilder_JLL.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");
							}   
						}

						else if(columns[1].contains("Cognizant")) {
							c5++;
							if(c5==1){
								htmlStringBuilder_Cognizant.append("<tr><td><b><font color=\"#0000ff\">Customer : Cognizant</font></b></td></tr>");
								htmlStringBuilder_Cognizant.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");

							}else{
								htmlStringBuilder_Cognizant.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");
							}   
						}

						else if(columns[1].contains("TCL")) {
							c6++;
							if(c6==1){
								htmlStringBuilder_TCL.append("<tr><td><b><font color=\"#0000ff\">Customer : TCL</font></b></td></tr>");
								htmlStringBuilder_TCL.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");

							}else{
								htmlStringBuilder_TCL.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");
							}   
						}

						else if(columns[1].contains("Tmobile")) {
							c7++;
							if(c7==1){
								htmlStringBuilder_Tmobile.append("<tr><td><b><font color=\"#0000ff\">Customer : Tmobile</font></b></td></tr>");
								htmlStringBuilder_Tmobile.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");

							}else{
								htmlStringBuilder_Tmobile.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[4]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[10]+"</td></tr>");
							}   
						}


					}
					count++;

				}
				System.out.println(customerNames);
				System.out.println("Customer list >> " + al);
			    

				if(!customerNames.contains("Tmobile")) {

					htmlStringBuilder_Tmobile1.append("<tr><td><b><font color=\"#0000ff\">Customer : Tmobile</font></b></td><td><b><center>No Access<center></b></td></tr>");

				}
				if(!customerNames.contains("TCL")) {

					htmlStringBuilder_TCL1.append("<tr><td><b><font color=\"#0000ff\">Customer : TCL</font></b></td><td><b><center>No Access<center></b></td></tr>");

				}
				if(!customerNames.contains("Cognizant")) {

					htmlStringBuilder_Cognizant1.append("<tr><td><b><font color=\"#0000ff\">Customer : Cognizant</font></b></td><td><b><center>No Access<center></b></td></tr>");

				}
				if(!customerNames.contains("JLL")) {

					htmlStringBuilder_JLL1.append("<tr><td><b><font color=\"#0000ff\">Customer : JLL</font></b></td><td><b><center>No Access<center></b></td></tr>");

				}
				if(!customerNames.contains("Accenture US")) {

					htmlStringBuilder_AccentureUS1.append("<tr><td><b><font color=\"#0000ff\">Customer : Accenture US</font></b></td><td><b><center>No Access<center></b></td></tr>");

				}
				if(!customerNames.contains("Accenture IDC")) {

					htmlStringBuilder_AccentureIDC1.append("<tr><td><b><font color=\"#0000ff\">Customer : Accenture IDC</font></b></td><td><b><center>No Access<center></b></td></tr>");

				}
				if(!customerNames.contains("IDFC")) {
					System.out.println("Rahul IDFC");

					htmlStringBuilder_IDFC1.append("<tr><td><b><font color=\"#0000ff\">Customer : IDFC</font></b></td><td><b><center>No Access<center></b></td></tr>");

				}

				htmlStringBuilderTail.append("</table></center></body></html>");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}


		}catch(Exception e){
			driver.close();

			htmlStringBuilderHead.append("<html><head><title>Production Access Daily Report</title></head>");
			htmlStringBuilderHead.append("<body><center>");
			htmlStringBuilderHead.append("<b>Production Access Report For : "+LocalDate.now().minusDays(1)+"</b>");
			htmlStringBuilderHead.append("<br>");
			htmlStringBuilderHead.append("<br>");
			htmlStringBuilderHead.append("<table border=\"1\" bordercolor=\"#000000\">");

			htmlStringBuilder_IDFC.append("<tr><td><b><font color=\"#0000ff\">Customer : IDFC</font></b></td><td><b><center>No Access<center></b></td></tr>");
			htmlStringBuilder_AccentureIDC.append("<tr><td><b><font color=\"#0000ff\">Customer : Accenture IDC</font></b></td><td><b><center>No Access<center></b></td></tr>");
			htmlStringBuilder_AccentureUS.append("<tr><td><b><font color=\"#0000ff\">Customer : Accenture US</font></b></td><td><b><center>No Access<center></b></td></tr>");
			htmlStringBuilder_Cognizant.append("<tr><td><b><font color=\"#0000ff\">Customer : Cognizant</font></b></td><td><b><center>No Access<center></b></td></tr>");
			htmlStringBuilder_JLL.append("<tr><td><b><font color=\"#0000ff\">Customer : JLL</font></b></td><td><b><center>No Access<center></b></td></tr>");
			htmlStringBuilder_TCL.append("<tr><td><b><font color=\"#0000ff\">Customer : TCL</font></b></td><td><b><center>No Access<center></b></td></tr>");
			htmlStringBuilder_Tmobile.append("<tr><td><b><font color=\"#0000ff\">Customer : Tmobile</font></b></td><td><b><center>No Access<center></b></td></tr>");

			htmlStringBuilderTail.append("</table></center></body></html>");

		}

		String preparedHtmlString=htmlStringBuilderHead.toString()+htmlStringBuilder_IDFC.toString()+htmlStringBuilder_AccentureIDC.toString()+htmlStringBuilder_AccentureUS.toString()+htmlStringBuilder_Cognizant.toString()+htmlStringBuilder_JLL.toString()+htmlStringBuilder_TCL.toString()+htmlStringBuilder_Tmobile.toString() + htmlStringBuilder_IDFC1.toString()+htmlStringBuilder_AccentureIDC1.toString()+htmlStringBuilder_AccentureUS1.toString()+htmlStringBuilder_Cognizant1.toString()+htmlStringBuilder_JLL1.toString()+htmlStringBuilder_TCL1.toString()+htmlStringBuilder_Tmobile1.toString()+htmlStringBuilderTail.toString();
		System.out.println("HTML String : "+preparedHtmlString);

		String htmlfile="./test-output/BugzillaProductionAccessDaily.html";
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


