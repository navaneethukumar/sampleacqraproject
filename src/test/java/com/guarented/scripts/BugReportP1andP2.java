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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import jxl.write.DateFormat;
import jxl.write.DateFormats;

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


public class BugReportP1andP2 {

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

		int p1count = 0;
		int p2count = 0;
		
		int p1bcount =0;
		int p2bcount =0;
		int p1nscount = 0;
		int p2nscount = 0;
		int total= 0;
		int P2total=0;

		StringBuilder htmlStringBuilder=new StringBuilder();


		File dir = new File(downloadFilepath);
		FileUtils.cleanDirectory(dir);

		driver.get("http://bugdb.spire2grow.com/");
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

     
		
		//String url= "http://bugdb.spire2grow.com/buglist.cgi?columnlist=bug_id%2Cproduct%2Ccomponent%2Creporter_realname%2Ccf_customer%2Cbug_status%2Cassigned_to_realname%2Cpriority%2Ccf_impact_type%2Copendate%2Cshort_desc&f1=cf_customer&list_id=22325&o1=equals&priority=P1-Critical&priority=P2-High&query_format=advanced&resolution=---&resolution=---&v1="+customerName;
		//http://bugdb.spire2grow.com/buglist.cgi?columnlist=bug_id%2Cproduct%2Ccomponent%2Creporter_realname%2Ccf_customer%2Cbug_status%2Cassigned_to_realname%2Cpriority%2Ccf_impact_type%2Copendate%2Cshort_desc&f1=cf_customer&f2=cf_reported_environment&list_id=22552&o1=equals&o2=equals&priority=P1-Critical&priority=P2-High&query_format=advanced&resolution=---&resolution=---&v1=Accenture%20US&v2=Prod
		//	String url= "http://bugdb.spire2grow.com/buglist.cgi?columnlist=bug_id%2Cproduct%2Ccomponent%2Creporter_realname%2Ccf_customer%2Cbug_status%2Cassigned_to_realname%2Cpriority%2Ccf_impact_type%2Copendate%2Cshort_desc&f1=cf_customer&f2=cf_reported_environment&list_id=22552&o1=equals&o2=equals&priority=P1-Critical&priority=P2-High&query_format=advanced&resolution=---&resolution=---&v1="+customerName+"&v2=Prod";
		//String url= "http://bugdb.spire2grow.com/buglist.cgi?columnlist=bug_id%2Cproduct%2Ccomponent%2Creporter_realname%2Ccf_customer%2Cbug_status%2Cassigned_to_realname%2Cpriority%2Ccf_impact_type%2Copendate%2Cshort_desc%2Ccf_reported_environment&f1=cf_customer&f2=cf_reported_environment&list_id=22552&o1=equals&o2=equals&priority=P1-Critical&priority=P2-High&query_format=advanced&resolution=---&resolution=---&v1="+customerName+"&v2=Prod";
		String url= "http://bugdb.spire2grow.com/buglist.cgi?columnlist=bug_id%2Cproduct%2Ccomponent%2Creporter_realname%2Ccf_customer%2Cbug_status%2Cassigned_to_realname%2Cpriority%2Ccf_impact_type%2Copendate%2Ccf_reported_environment%2Cshort_desc&f1=cf_customer&f2=cf_reported_environment&list_id=22552&o1=equals&o2=equals&order=priority%2Cbug_id&priority=P1-Critical&priority=P2-High&query_format=advanced&resolution=---&resolution=---&v1="+customerName+"&v2=Prod";
		System.out.println("Accessed URL : " +url);	
		driver.navigate().to(url);
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//*[@id='bugzilla-body']/div[2]/div[2]/a[1]/img")).click();

			Thread.sleep(5000);
			driver.close();
			
			String csvFileToRead = downloadFilepath+"\\bugs-"+LocalDate.now()+".csv";
			BufferedReader br=null ;
			BufferedReader br1=null ;
			String line = "";
			String splitBy = ",";
			String line1 = "";
			String splitBy1 = ",";

			try {

				br = new BufferedReader(new FileReader(csvFileToRead));
				br1 = new BufferedReader(new FileReader(csvFileToRead));

				htmlStringBuilder.append("<html><head><title>Bug Report</title></head>");
				htmlStringBuilder.append("<body><center>");
				htmlStringBuilder.append("<b><u>Bug Report On : "+LocalDate.now()+"</u></b>");
				htmlStringBuilder.append("<br>");
				htmlStringBuilder.append("<br>");
				
				//////////
				
				int count1=1;
				String[] columns1;
				while ((line1 = br1.readLine()) != null) {

					columns1 = line1.split(splitBy1);
					if(count1==1){
						
							System.out.println("TEST1");
						
					}else{
							
						String word1 = columns1[9];
						 String replaced1 = word1.replace("\"", "");
						System.out.println(replaced1);
						System.out.println(LocalDateTime.now());
												
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
						 Date startDate1 = df1.parse(replaced1);
						 SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						 Date date1 = new Date();
						 
						 String dateStart1 = dateFormat1.format(date1);
						 String dateStop1 = dateFormat1.format(startDate1);

						 		Date d11 = null;
						 		Date d21 = null;
							
								d21 = dateFormat1.parse(dateStart1);
								d11 = dateFormat1.parse(dateStop1);
								
							//in milliseconds
								long diff1 = d21.getTime() - d11.getTime();

								long diffSeconds1 = diff1 / 1000 % 60;
								long diffMinutes1 = diff1 / (60 * 1000) % 60;
								long diffHours1 = diff1 / (60 * 60 * 1000) % 24;
								long diffDays1 = diff1/ (24 * 60 * 60 * 1000);

								System.out.print(diffDays1 + " days, ");
								System.out.print(diffHours1 + " hours, ");
								System.out.print(diffMinutes1 + " minutes, ");
								System.out.print(diffSeconds1 + " seconds.");

				 
						long check1;
						check1 = diffDays1;
						long checkhours1 = diffHours1;
						
						//if(check1>=1)
						//{
				
						 if (check1>=1 && columns1[7].contains("P1"))
		 						
						 {
							 p1count++;
							 System.out.println("p1:");
							 System.out.println(p1count);
						 }
						 
						 if (check1>=3 && columns1[7].contains("P2"))
							 {
								 p2count++;
							 System.out.println("p2:");
							 System.out.println(p2count);
							 				 }
						 //changing p1 p p2
						 if(checkhours1>=12  &&   checkhours1<24 && check1 <1 && columns1[7].contains("P1")) 
								 {
							 p1bcount++;
							 System.out.println("p1:");
							 System.out.println(p1bcount);
							// SlaYetToReachP1=24 - checkhours1 ;
								 
								 }
								 
								 
					if(check1 >2 && check1<3 &&  columns1[7].contains("P2") )
						 {
						p2bcount++;
							 System.out.println("p1:");
							 System.out.println(p2bcount);
							// SlaYetToReachP2= 24 - checkhours1 ;
								 }
						//}
						//else
						//{
				
							 if (checkhours1<12 &&   check1 <1 && columns1[7].contains("P1"))
			 						
							 {
								 p1nscount++;
								 System.out.println("p1:");
								 System.out.println(p1nscount);
							 }
							 
							 if (check1<2 && columns1[7].contains("P2"))
								 {
								 p2nscount++;
								 System.out.println("p2:");
								 System.out.println(p2nscount);
								 				 }
						//}
					}   
					count1++;

				}
				htmlStringBuilder.append("<br>");
				htmlStringBuilder.append("<br>");
			 int P1total1= p1count+p1nscount;
			 int P2total1=p2nscount+p2count;
			 total = P1total1+P2total1;
				htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
				htmlStringBuilder.append("<tr><td bgcolor=\"red\">SLA Crossed</td><td>"+p1count+" P1 Bugs</td><td>"+p2count+" P2 Bugs</td></tr>");
				htmlStringBuilder.append("<tr><td bgcolor=\"yellow\">Reaching SLA</td><td>"+p1bcount+" P1 Bugs</td><td>"+p2bcount+" P2 Bugs</td></tr>");
				htmlStringBuilder.append("<tr><td>Others</td><td>"+p1nscount+" P1 Bugs</td><td>"+p2nscount+" P2 Bugs</td></tr>");
				htmlStringBuilder.append("<tr><td>Total P1 & P2 Bugs</td><td>"+P1total1+" P1 Bugs</td><td>"+P2total1+" P2 Bugs</td></tr>");
				htmlStringBuilder.append("<tr><td>Total Bugs</td><td>"+total+" Bugs</td></tr>");
				htmlStringBuilder.append("</table></center></body></html>");
				htmlStringBuilder.append("<br>");
				htmlStringBuilder.append("<br>");

				//////////////
				
				htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
				
			   //////////////////////
				int count=1;
				String[] columns;
				while ((line = br.readLine()) != null) {

					columns = line.split(splitBy);
					if(count==1){
						
							
						htmlStringBuilder.append("<tr bgcolor= \"#66ffff\"><td><b>"+columns[0]+"</b></td><td><b>"+columns[2]+"</b></td><td><b>"+columns[3]+"</b></td><td><b>"+columns[5]+"</b></td><td><b>"+columns[6]+"</b></td><td><b>"+columns[7]+"</b></td><td><b>"+columns[8]+"</b></td><td><b>"+columns[9]+"</b></td><td><b>"+columns[10]+"</b></td><td><b>"+columns[11]+"</b></td><td><b>SLA STATUS</b></td></tr>");
					}else{
						
						
						String word = columns[9];
						 String replaced = word.replace("\"", "");
						System.out.println(replaced);
						System.out.println(LocalDateTime.now());
												
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
						 Date startDate = df.parse(replaced);
						 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						 Date date = new Date();
						 
						 String dateStart = dateFormat.format(date);
						 String dateStop = dateFormat.format(startDate);

						 		Date d1 = null;
						 		Date d2 = null;
							
								d2 = dateFormat.parse(dateStart);
								d1 = dateFormat.parse(dateStop);
								
							//in milliseconds
								long diff = d2.getTime() - d1.getTime();

								long diffSeconds = diff / 1000 % 60;
								long diffMinutes = diff / (60 * 1000) % 60;
								long diffHours = diff / (60 * 60 * 1000) % 24;
								long diffDays = diff / (24 * 60 * 60 * 1000);

								System.out.print(diffDays + " days, ");
								System.out.print(diffHours + " hours, ");
								System.out.print(diffMinutes + " minutes, ");
								System.out.print(diffSeconds + " seconds.");

				 
						long check;
						check = diffDays;
						long checkhours = diffHours;
						System.out.println(diffHours);
						if(check>=1 && columns[7].contains("P1") || check>=3 && columns[7].contains("P2") )
						{
							long SlacrossedP1 = 0;
							long SlaCrossedP2 = 0;
							
							SlacrossedP1=  check-1;
							SlaCrossedP2= check -3;
						if (columns[7].contains("P1"))	{
							
							htmlStringBuilder.append("<tr bgcolor =\"red\" ><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[3]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[8]+"</td><td>"+columns[9]+"</td><td>"+columns[10]+"</td><td>"+columns[11]+"</td><td>SLA crossed "+SlacrossedP1+" days ago </td></tr>");
						}
						else{
							htmlStringBuilder.append("<tr bgcolor =\"red\" ><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[3]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[8]+"</td><td>"+columns[9]+"</td><td>"+columns[10]+"</td><td>"+columns[11]+"</td><td>SLA crossed "+SlaCrossedP2+" days ago </td></tr>");

						}
						}
						else if(checkhours>=12 &&   checkhours<24 && check<1 && columns[7].contains("P1") || check >2 && check<3 &&  columns[7].contains("P2") )
						{
						long SlaYetToReachP1 =0;
							SlaYetToReachP1= 24 - checkhours ;
							htmlStringBuilder.append("<tr bgcolor =\"yellow\" ><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[3]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[8]+"</td><td>"+columns[9]+"</td><td>"+columns[10]+"</td><td>"+columns[11]+"</td><td>TimeToReach SLA :"+SlaYetToReachP1+"</td></tr>");
						
					
						}
						else
						{
							
							long SlaYetToReachdays=0;
							long SlaYetToReachhours=0;
							SlaYetToReachdays=2-check;
							SlaYetToReachhours=24-checkhours;
							if  (columns[7].contains("P1"))	{
							htmlStringBuilder.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[3]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[8]+"</td><td>"+columns[9]+"</td><td>"+columns[10]+"</td><td>"+columns[11]+"</td><td> TimeToReach SLA: "+SlaYetToReachhours+" Hours</td></tr>");
							}
							else{
								htmlStringBuilder.append("<tr><td>"+columns[0]+"</td><td>"+columns[2]+"</td><td>"+columns[3]+"</td><td>"+columns[5]+"</td><td>"+columns[6]+"</td><td>"+columns[7]+"</td><td>"+columns[8]+"</td><td>"+columns[9]+"</td><td>"+columns[10]+"</td><td>"+columns[11]+"</td><td> TimeToReach SLA: "+SlaYetToReachdays+" Days - "+SlaYetToReachhours+" Hours</td></tr>");
							}
						}
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
			htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
			htmlStringBuilder.append("<tr><td bgcolor=\"red\">SLA</td><td> 0 P1 Bugs</td><td> 0 P2 Bugs</td></tr>");
			htmlStringBuilder.append("<tr><td bgcolor=\"yellow\">Reaching SLA</td><td> 0 P1 Bugs</td><td>0 P2 Bugs</td></tr>");
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




