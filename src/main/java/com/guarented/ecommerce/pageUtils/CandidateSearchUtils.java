package com.guarented.ecommerce.pageUtils;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.WebDriver;

import com.guarented.ecommerce.commonUtils.SendMailUtility;
import com.guarented.ecommerce.constants.GuarentedConstants;
import com.guarented.ecommerce.pages.CandidateSearchPage;
import com.spire.base.controller.Logging;

public class CandidateSearchUtils extends CandidateSearchPage {
	SendMailUtility sendMailUtil = null;

	public CandidateSearchUtils(WebDriver driver) {
		super(driver);
	}

	public void validateDownloadAutomatchReport() throws InterruptedException {
		clickAutomatchedCheckbox();
		clickAutomatchReportButton();
		Thread.sleep(10000);
		Logging.log("Automatch report downloaded");
		validateReoprtDownloadedInDownloadFolder();
	}

	public void validateReoprtDownloadedInDownloadFolder() {
		java.io.File file = getLastModifiedFile();
		System.out.println(file);
		ArrayList<String> arr = checkLastModifiedFileDate(file);
		String fileDate = arr.get(0);
		String todaysDate = arr.get(1);
		if (fileDate.contains(todaysDate)) {
			System.out.println("sending mail");
			sendMailUtil = new SendMailUtility();
			sendMailUtil.sendMail(file);
		} else {
            System.out.println("File not found");
		}

	}

	public static File getLastModifiedFile() {
		// String downloadFolderPath = "C:\\Users\\Radharani Patra\\Downloads";
		String downloadFolderPath = "C:\\Users\\tester2\\Downloads";
		File newFile = null;
		File dir = new File(downloadFolderPath);
		FileFilter fileFilter = new WildcardFileFilter("*." + "csv");
		File[] list = dir.listFiles(fileFilter);
		if (list.length > 0) {
			Arrays.sort(list, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			newFile = list[0];
		}
		System.out.println(newFile);
		return newFile;

	}

	public ArrayList<String> checkLastModifiedFileDate(File file) {
		ArrayList<String> array = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String fileDate = sdf.format(file.lastModified());
		Date todaysDate = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		String todaysFormattedDate = sdf1.format(todaysDate);
		array.add(fileDate);
		array.add(todaysFormattedDate);
		return array;
	}

}
