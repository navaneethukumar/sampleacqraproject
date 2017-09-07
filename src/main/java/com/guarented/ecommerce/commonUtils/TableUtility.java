package com.guarented.ecommerce.commonUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.spire.base.controller.Logging;
import com.spire.base.util.TableUtil;

public class TableUtility {

	public List<WebElement> getResultTableRows(String id, WebDriver driver) {
		WebElement table = driver.findElement(By.id(id));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		return allRows;

	}

	public int getResultTableRowsCount(String id, WebDriver driver) {
		WebElement table = driver.findElement(By.id(id));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		return allRows.size();

	}

	public List<WebElement> getResultTableColumnsHeaders(String id,
			WebDriver driver) {
		WebElement table = driver.findElement(By.id(id));
		List<WebElement> allHeaders = table.findElements(By.tagName("th"));
		return allHeaders;
	}

	public int resultTableColumnIndex(String header_id, String columnName,
			WebDriver driver) {
		int i = 0;
		for (WebElement headerName : getResultTableColumnsHeaders(header_id,
				driver)) {
			i++;

			if (columnName.equalsIgnoreCase(headerName.getText().trim()))
				break;
		}
		return i;

	}

	public String getCellData(String tableData_id, String header_id,
			String columnName, int rowNo, WebDriver driver) {
		int columnIndex = resultTableColumnIndex(header_id, columnName, driver);
		System.out.println("columnIndex  " + columnIndex);
		Logging.log("columnIndex  " + columnIndex);
		// String
		// cellData=driver.findElement(By.xpath("//*[@id='"+tableData_id+"']/tbody/tr["+rowNo+"]/td["+columnIndex+"]/div")).getText().trim();
		String cellData = driver
				.findElement(
						By.xpath("//*[@id='" + tableData_id + "']/tbody/tr["
								+ rowNo + "]/td[" + columnIndex + "]"))
				.getText().trim();
		System.out.println("Cell Data : " + cellData);
		return cellData;
	}

	public Boolean validateCellData(String tableData_id, String header_id,
			String columnName, String dataTextToValidate, WebDriver driver) {
		int totalRows = getResultTableRowsCount(tableData_id, driver);
		System.out.println("totalRows : " + totalRows);
		Logging.log("totalRows : " + totalRows);
		Boolean result = false;
		// comparing searched data with given data
		for (int rowNo = 1; rowNo < totalRows; rowNo++) {
			System.out.println("Cell Data : "
					+ getCellData(tableData_id, header_id, columnName, rowNo,
							driver).toLowerCase());
			if (!getCellData(tableData_id, header_id, columnName, rowNo, driver)
					.toLowerCase().contains(dataTextToValidate.toLowerCase())) {
				result = false;
				break;
			}
			result = true;
		}
		return result;
	}
	//added to get cell data when we dont get proper column index using column name 
	public String getCellDataBYColumnIndex(String tableData_id, String header_id,
			int columnIndex, int rowNo, WebDriver driver) {
		String cellData = driver
				.findElement(
						By.xpath("//*[@id='" + tableData_id + "']/tbody/tr["
								+ rowNo + "]/td[" + columnIndex + "]"))
				.getText().trim();
		System.out.println("Cell Data : " + cellData);
		return cellData;
	}
	//added to validate cell data when we dont get proper column index using column name 
	public Boolean validateCellDataByColumnIndex(String tableData_id, String header_id,
			int columnIndex, String dataTextToValidate, WebDriver driver) {
		int totalRows = getResultTableRowsCount(tableData_id, driver);
		System.out.println("totalRows : " + totalRows);
		Logging.log("totalRows : " + totalRows);
		Boolean result = false;
		// comparing searched data with given data
		for (int rowNo = 1; rowNo < totalRows; rowNo++) {
			
			if (!getCellDataBYColumnIndex(tableData_id, header_id, columnIndex, rowNo, driver)
					.toLowerCase().contains(dataTextToValidate.toLowerCase())) {
				result = false;
				break;
			}
			result = true;
		}
		return result;
	}

	public boolean validateResultSetTable(String header_id, WebDriver driver) {

		boolean flag1;
		try {
			getResultTableColumnsHeaders(header_id, driver).get(0).getText();
			System.out.println("First Column: "
					+ getResultTableColumnsHeaders(header_id, driver).get(1)
							.getText());
			flag1 = false;
			System.out.println("In try block");
		} catch (Exception e) {
			flag1 = true;
			System.out.println("In Catch block");
		}
		return flag1;
	}

	// CTS @Author Udhaya
	public void expandGroup(WebDriver driver) throws InterruptedException {
		List<WebElement> arrowlist = driver.findElements(By
				.xpath("//img[contains(@id,'toggleImg')]"));
		for (int k = 0; k < arrowlist.size(); k++) {
			// System.out.println(arrowlist.get(k));
			arrowlist.get(k).click();
			Thread.sleep(2000);

		}

	}

	/**
	 * 
	 * @param id
	 */
	// CTS @Author Udhaya
	public String checkRowGroup(String id, WebDriver driver, int row, int column) {
		String value = "Group";
		String flag = driver.findElement(
				By.xpath("//*[@id='" + id + "']/tbody/tr[" + row + "]"))
				.getAttribute("class");
		if (flag.equals("group")) {
			Logging.log("Skipping row: Its Header for Group");
		} else if (flag.equals("")) {
			Logging.log("Group ends");
		} else {
			value = "normalrow";
		}
		return value;
	}

	// CTS @Author Udhaya
	public String getCellValue2(String id1, WebDriver driver, int row1,
			int column1) {
		String value = checkRowGroup(id1, driver, row1, column1);
		if (value.equals("normalrow")) {
			value = driver.findElement(
					By.xpath("//*[@id='" + id1 + "']/tbody/tr[" + row1
							+ "]/td[" + column1 + "]")).getText();
		}

		return value;
	}

	/**
	 * This method will validate the data in respective column parameters to
	 * pass: Table id, driver, Column name, data to validate
	 * 
	 * It will validate the data in the column, if fails it will display
	 * assertion error and tells the Requisition number
	 */
	public void validateColumnData(String id, WebDriver driver,
			String columnName, String expected) {

		List<WebElement> thList = driver.findElements(By.xpath(".//*[@id='"
				+ id + "']/thead/tr/th"));

		int j = 0;
		for (j = 0; j < thList.size(); j++) {
			int count = 0;

			if (thList.get(j).getText().contains(columnName)) {
				break;
			} else
				count++;
		}

		int col = j + 1;
		TableUtil td = new TableUtil();
		int rowCount = td.getRowCount(id, driver);
		String actual = null;

		for (int i = 1; i < rowCount; i++) {
			actual = td.getCellValue(id, driver, i, col);
			String req = driver
					.findElement(
							By.xpath(".//*[@id='" + id + "']/tbody/tr[" + i
									+ "]/td[3]")).getText();
			System.out.println("Validating in Row No: " + i);
			if (columnName.equals("Name")) {
				Logging.log("Execution for Many to One Search");
				Assert.assertTrue(
						actual.toUpperCase().contains(expected.toUpperCase()),
						"For Requisition: " + req);
				System.out.println("Searched: " + expected + ",, Found: "
						+ actual);
			} else {
				Logging.log("Execution for One to Many Search");
				Assert.assertTrue(
						expected.toUpperCase().contains(actual.toUpperCase()),
						"For Requisition: " + req);
				System.out.println("Searched: " + actual + ",, Found: "
						+ expected);
			}
		}
	}

	/**
	 * CTS - Added - Abhishiktha Dasari - 26-08-2015 Show Candidate Screen -
	 * Including Scroll This method will validate the data in respective column
	 * parameters to pass: Table Id, Table-Header Id, Driver, Column Name, Data
	 * to Validate
	 * 
	 * It will validate the data in the column, if fails it will display
	 * assertion error and tells the Requisition number
	 */

	public void validateColumnDataCTS(String tableid, String headerid,
			WebDriver driver, String columnName, String expected) {

		List<WebElement> thList = driver.findElements(By.xpath(".//*[@id='"
				+ headerid + "']/div[2]/div[1]/div/table/thead/tr/th"));

		System.out.println("Size : " + thList.size());
		int j = 0;
		for (j = 0; j < thList.size(); j++) {
			int count = 0;
			String name = thList.get(j).getText();
			if (name.equals(null)) {
				System.out.println("Scroll Perform");
				WebElement element = driver.findElement(By.xpath(".//*[@id='"
						+ headerid + "']/div[2]/div[2]"));
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].scrollIntoView(true);", element);
				// Actions dragger = new Actions(driver);
				// WebElement draggablePartOfScrollbar =
				// driver.findElement(By.xpath(".//*[@id='" +headerid
				// +"']/div[2]/div[2]"));
				// int numberOfPixelsToDragTheScrollbarDown = 5000;
				// dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
			} else if (name.contains(columnName)) {
				break;
			} else
				count++;
		}

		int col = j + 1;
		TableUtil td = new TableUtil();
		int rowCount = td.getRowCount(tableid, driver);
		String actual = null;
		System.out.println("Expected : " + expected);
		for (int i = 1; i < rowCount; i++) {
			actual = td.getCellValue(tableid, driver, i, col);

			System.out.println("Actual : " + actual);

			String req = driver.findElement(
					By.xpath(".//*[@id='" + tableid + "']/tbody/tr[" + i
							+ "]/td[" + col + "]")).getText();
			System.out.println("Validating in Row No: " + i);
			if (columnName.equals("BU")) {
				Logging.log("Execution for Many to One Search");
				Assert.assertTrue(
						actual.toUpperCase().contains(expected.toUpperCase()),
						"For Requisition: " + req);
				System.out.println("Searched: " + expected + ",, Found: "
						+ actual);
			} else {
				Logging.log("Execution for One to Many Search");
				Assert.assertTrue(
						expected.toUpperCase().contains(actual.toUpperCase()),
						"For Requisition: " + req);
				System.out.println("Searched: " + actual + ",, Found: "
						+ expected);
			}
		}
	}

	/**
	 * 
	 */
	// @Author Subha
	public void AlreadyexpandGroup(WebDriver driver)
			throws InterruptedException {
		List<WebElement> arrowdown = driver.findElements(By
				.xpath("//img[contains(@class,'arrowDown')]"));
		for (int k = 0; k < arrowdown.size(); k++) {
			// System.out.println(arrowlist.get(k));
			arrowdown.get(k);
			Thread.sleep(2000);

		}

	}
	
	public boolean noDataInTable(WebDriver driver,String tableData_id){
		String data=driver.findElement(By.xpath("//*[@id='"+tableData_id+"']/tbody/tr[1]/td[1]")).getText();
		
		if(data.contains("No data available in table")||data.contains("No matching records found")){
			return true;
		}
		else{
			return false;
		}
		
	}

}
