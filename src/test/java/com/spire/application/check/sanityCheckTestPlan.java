package com.spire.application.check;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guarented.ecommerce.candidateListView.candidateListViewTestPlan;
import com.guarented.ecommerce.commonUtils.ApplicationAccessbility;
import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.commonUtils.PingMachine;
import com.guarented.ecommerce.pojo.User;
import com.spire.base.controller.Logging;
import com.spire.base.controller.TestPlan;
import com.spire.base.util.SpireCsvUtil;
import com.spire.base.util.internal.entity.SpireTestObject;

//public class sanityCheckTestPlan extends TestPlan{
public class sanityCheckTestPlan{
	/*@DataProvider(name = "sanityCheckTestData")
	public static Iterator<Object[]> getCandidateInfo(Method method) {

		Iterator<Object[]> objectsFromCsv = null;

		try {
			String fileName = "./src/test/java/com/spire/application/check/sanityCheckTestData.csv";
			LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
			LinkedHashMap<String, String> methodFilter = new LinkedHashMap<String, String>();
			methodFilter.put(SpireTestObject.TEST_TITLE, method.getName());
			entityClazzMap.put("SpireTestObject", SpireTestObject.class);
			entityClazzMap.put("User", User.class);
			objectsFromCsv = SpireCsvUtil.getObjectsFromCsv(
					candidateListViewTestPlan.class, entityClazzMap, fileName,
					null, methodFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectsFromCsv;
	}*/

	CommonUtils cu=new CommonUtils();

	StringBuilder htmlStringBuilder=new StringBuilder();
	StringBuilder htmlStringBuilder1=new StringBuilder();
	String []pingDetails;
	String finalString="";
	WebDriver driver ;
	@BeforeTest
	public void startHTML(){
		htmlStringBuilder.append("<html><head><title>Ping and Application Accessbility Report</title></head>");
		htmlStringBuilder.append("<body><center>");
	}

	//@Test(groups = { "validatePingMachine", "sanity" }, dataProvider = "sanityCheckTestPlan")
	@Test(groups = { "validatePingMachine", "sanity" })
	public void avalidatePingMachine() throws Exception {
		PingMachine pm=new PingMachine();	


		//htmlStringBuilder.append("<html><head><title>Ping and Application Accessbility Report</title></head>");
		//htmlStringBuilder.append("<body><center>");
		htmlStringBuilder.append("<b><u>Ping Report</u></b>");
		htmlStringBuilder.append("<br>");
		//htmlStringBuilder.append("<br>");
		htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");

		/*header*/
		htmlStringBuilder.append("<tr><td><b>Machine</b></td><td><b>Pinging</b></td><td><b>Packet Loss(%)</b></td></tr>");

		/*Machine 1*/
		pingDetails=pm.pingMachine("accentureusauat.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 2*/
		//pingDetails=pm.pingMachine("192.168.2.4");
		//htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 3*/
		pingDetails=pm.pingMachine("philtech.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 4*/
		pingDetails=pm.pingMachine("indiaops.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 5*/
		pingDetails=pm.pingMachine("accinduat.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 6*/
		pingDetails=pm.pingMachine("accentureind.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 7*/
		pingDetails=pm.pingMachine("philbpo.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 8*/
		/*pingDetails=pm.pingMachine("accglobaluat.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");
		 */
		/*Machine 9*/
		/*pingDetails=pm.pingMachine("idfcbankuat.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");
		 */
		/*Machine 10*/
		pingDetails=pm.pingMachine("phil-bpo.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 11*/
		pingDetails=pm.pingMachine("phil-tech.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 12*/
		pingDetails=pm.pingMachine("accentureindopsuat.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 13*/
		pingDetails=pm.pingMachine("accenture.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 14*/
		pingDetails=pm.pingMachine("fidelitynew.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");

		/*Machine 15*/
		pingDetails=pm.pingMachine("fidelitybrspl.spire2grow.com");
		htmlStringBuilder.append("<tr><td>"+pingDetails[0]+"</td><td>"+pingDetails[1]+"</td><td>"+pingDetails[2]+"</td></tr>");



		/*table ends*/

		htmlStringBuilder.append("</table>");
		htmlStringBuilder.append("<br>");
		htmlStringBuilder.append("<br>");

		//htmlStringBuilder.append("</table></center></body></html>");
		System.out.println("htmlStringBuilder :"+htmlStringBuilder);


		/*String htmlfile="./test-output/MachineApplicationReport.html";
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
		writer.write(htmlStringBuilder.toString());
		writer.close();*/


	}

	@Test(groups = { "machineAccessbility", "sanity" })
	public void bmachineAccessbility() throws InterruptedException, IOException{

		String url="",user="",password="";
		String [] result= new String[5];
		ApplicationAccessbility aa=new ApplicationAccessbility();

		htmlStringBuilder1.append("<b><u>Application Accessbility Report</u></b>");
		htmlStringBuilder1.append("<br>");
		//htmlStringBuilder1.append("<br>");
		htmlStringBuilder1.append("<table border=\"1\" bordercolor=\"#000000\">");		


		//htmlStringBuilder1.append("<tr><td><b>Application</b></td><td><b>Login</b></td><td><b>Home Page Search</b></td><td><b>Universal Search</b></td></tr>");

		htmlStringBuilder1.append("<tr><td><b>Application</b></td><td><b>Login</b></td><td><b>Universal Search</b></td></tr>");

		//app1

		url="https://accentureusauat.spire2grow.com/generic-ui/src/app/index.html";
		user="recruiteruat@accentureusuat.com";
		password="spire@123";
		//result=aa.applicationAccessbility(url,user,password,"");
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			//System.out.println("Retrying one more time for Instance : "+url);
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp1;		
		AccessResultApp1=result[4];


		//app3
		url="https://philtech.spire2grow.com/generic-ui/src/app/index.html";
		user="admin@accenphiltechprd.com";
		password="A2trmLmv";
		//result=aa.applicationAccessbility(url,user,password,"");
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp3;
		AccessResultApp3=result[4];
		//AccessResultApp3="Fail";


		//app4
		url="https://indiaops.spire2grow.com/generic-ui/src/app/index.html";
		user="admin@accentindopsprd.com";
		password="Ah1kxJ7M";
		//result=aa.applicationAccessbility(url,user,password,"");
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");

		String AccessResultApp4;
		AccessResultApp4=result[4];
		/*
		//app5
		url="https://accentureus.spire2grow.com/";
		user="recruiter@accenture.com";
		password="Spire@123";
		result=aa.applicationAccessbility_AccentureNA(url,user,password);
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp5;
		AccessResultApp5=result[4];
		 */ 
		//app6
		url="https://accinduat.spire2grow.com/generic-ui/src/app/index.html";
		user="user@accentureidcuat.com";
		password="spire@123";
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp6;
		AccessResultApp6=result[4]; 

		//app7
		url="https://accentureind.spire2grow.com/generic-ui/src/app/index.html";
		user="recruiter1@accentureind.com";
		password="spire@123";
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp7;
		AccessResultApp7=result[4];

		//app8
		url="https://phil-tech.spire2grow.com/generic-ui/src/app/index.html#/";
		user="recruiter@accphiltechuat.com";
		password="dIETKEtY";
		//result=aa.applicationAccessbility(url,user,password,"");
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp8;
		AccessResultApp8=result[4];

		//app9
		url="https://accentureindopsuat.spire2grow.com/generic-ui/src/app/index.html";
		user="recruiter@accopsuat.com";
		password="G3tGGbGt";
		//result=aa.applicationAccessbility(url,user,password,"");
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp9;
		AccessResultApp9=result[4];

		//app10
		url="https://philbpo.spire2grow.com/generic-ui/src/app/index.html#/";
		user="admin@accentphilbpoprd.com";
		password="TpRWJyMV";
		//result=aa.applicationAccessbility(url,user,password,"");
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp10;
		AccessResultApp10=result[4];

		//app11
		/*url="https://idfcbankuat.spire2grow.com/generic-ui/src/app/index.html#/";
		user="recruiteruat@idfcuat.com";
		password="spire@123";
		//result=aa.applicationAccessbility(url,user,password,"");
		result=aa.applicationAccessbility(url,user,password);

		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp11;
		AccessResultApp11=result[4];
		 */

		//app12
		/*url="https://accglobaluat.spire2grow.com/generic-ui/src/app/index.html";
		user="admin@accentglobaluat.com";
		password="spire@123";
		result=aa.applicationAccessbility_AccentureGlobal(url,user,password);
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp12;
		AccessResultApp12=result[4];*/

		//app13
		url="https://accenture.spire2grow.com/generic-ui/src/app/index.html";
		user="recruiter@accenture.com";
		password="Spire@123";
		//result=aa.applicationAccessbility(url,user,password,"");
		//result=aa.applicationAccessbility(url,user,password);
		result=aa.applicationAccessbility_AccentureNA(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility_AccentureNA(url,user,password);
		}

		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp13;
		AccessResultApp13=result[4];

		//app14
		url="https://fidelitynew.spire2grow.com/generic-ui/src/app/index.html";
		user="recruiter@fildduat.com";
		password="spire@123";
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		//htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[2]+"</td><td>"+result[3]+"</td></tr>");
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp14;
		AccessResultApp14=result[4];


		//app15
		url="https://fidelitybrspl.spire2grow.com/generic-ui/src/app/index.html";
		user="recruiter@filprod.com";
		password="spire@123";
		result=aa.applicationAccessbility(url,user,password);
		/** Retrying one more time if Login or Universal search fails */
		if(result[4].contains("Fail")){
			Logging.log("Either Login or Universal Search Failed, Retrying one more time for Instance : "+url);
			result=aa.applicationAccessbility(url,user,password);
		}
		htmlStringBuilder1.append("<tr><td>"+result[0]+"</td><td>"+result[1]+"</td><td>"+result[3]+"</td></tr>");
		String AccessResultApp15;
		AccessResultApp15=result[4];


		htmlStringBuilder1.append("</table></center></body></html>");

		System.out.println("htmlStringBuilder1 : "+htmlStringBuilder1.toString());
		String finalResult="";
		if(AccessResultApp1.contains("Pass")&&AccessResultApp3.contains("Pass")&&AccessResultApp4.contains("Pass")/*&&AccessResultApp5.contains("Pass")*/&&AccessResultApp6.contains("Pass")&&AccessResultApp7.contains("Pass")&&AccessResultApp8.contains("Pass")&&AccessResultApp9.contains("Pass")&&AccessResultApp10.contains("Pass")/*&&AccessResultApp11.contains("Pass")*/&&AccessResultApp13.contains("Pass")&&AccessResultApp14.contains("Pass")&&AccessResultApp15.contains("Pass"))
			//if(AccessResultApp1.contains("Pass")&&AccessResultApp3.contains("Pass")&&AccessResultApp4.contains("Pass")/*&&AccessResultApp5.contains("Pass")*//*&&AccessResultApp6.contains("Pass")*/&&AccessResultApp7.contains("Pass")&&AccessResultApp8.contains("Pass")&&AccessResultApp9.contains("Pass")&&AccessResultApp10.contains("Pass")&&AccessResultApp11.contains("Pass")&&AccessResultApp12.contains("Pass")&&AccessResultApp13.contains("Pass")&&AccessResultApp14.contains("Pass"))
			//if(AccessResultApp1.contains("Pass")&&AccessResultApp3.contains("Pass")&&AccessResultApp4.contains("Pass")&&AccessResultApp5.contains("Pass")&&AccessResultApp6.contains("Pass")&&AccessResultApp7.contains("Pass")&&AccessResultApp8.contains("Pass")&&AccessResultApp9.contains("Pass")&&AccessResultApp10.contains("Pass")&&AccessResultApp11.contains("Pass")&&AccessResultApp12.contains("Pass"))
			//if(AccessResultApp7.contains("Pass")&&AccessResultApp12.contains("Pass"))
			//if(AccessResultApp14.contains("Pass"))
			finalResult="Pass";
		else finalResult="Fail";

		Assert.assertEquals(finalResult, "Pass");
	}


	@AfterTest
	public void htmlEnd() throws IOException{
		finalString=htmlStringBuilder.toString()+htmlStringBuilder1.toString();
		System.out.println("Rahul");
		System.out.println("finalString :"+finalString);
		String htmlfile="./test-output/MachineApplicationReport.html";
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
		writer.write(finalString);
		writer.close();
		//driver.close();
	}
}
