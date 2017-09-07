package com.guarented.ecommerce.pageUtils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.pages.UniversalSearch;
import com.spire.base.controller.Logging;

public class UniversalSearchUtils extends UniversalSearch{
	private static final String ËNTER = null;
	CommonUtils cu=new CommonUtils();
	String customer;
	public UniversalSearchUtils(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void validateAutoSuggestionInUniversalSearch(String customerName,String searchString) throws InterruptedException{

		typeInUniversalSearch(customerName,searchString);
		Thread.sleep(5000);
		List <WebElement>list=getSearchedPamameterResultList();
		System.out.println("Auto Suggest List size : " + list.size());
		for(int i=0;i<list.size();i++)
			System.out.println("list element is : "+list.get(i).getText());
		Logging.log("Asserting that auto suggest list is available or not");
		Assert.assertTrue(list.size()>=1, "Auto Suggest List is not Available for : "+searchString);
		Logging.log("Verifying Auto Suggest List Data ");
		boolean flag;
		for(int i=0;i<list.size();i++){
			flag=list.get(i).getText().toLowerCase().contains(searchString.toLowerCase());
			Assert.assertTrue(flag, "Auto suggest list is not as per expectation as searched element does not contain "+searchString);
		}

	}
	
	public void typeInUniversalSearch(String customerName,String searchParameter){
		if(customerName.equalsIgnoreCase("ABC")){
			//put customized action here
			}else{
				getUniversalSearchBar().click();
				getUniversalSearchBarTextField().sendKeys(searchParameter);
			}
	}
	public void universalSearch(String customerName, String searchParameter, String searchParameterType ) throws InterruptedException{
		if(customerName.equalsIgnoreCase("ABC")){
			//put customized action here
			}else{
				typeInUniversalSearch(customerName,searchParameter);
				
				//getUniversalSearchBar().click();
				//getUniversalSearchBarTextField().sendKeys(searchParameter);
				
				//cu.explicitWait(driver, By.xpath("(//span[text()='"+searchParameterType+"'])[1]"), 50L);
				cu.explicitWait(driver, By.xpath("(//span[text()='"+searchParameterType+"'])[1]"));
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//span[text()='"+searchParameterType+"'])[1]")).click();
				clickUniversalSearchButton();
		}
		
		
	}
	public void typeInUniversalSearchwithBrackets(String customerName,String searchParameter) throws InterruptedException{
		if(customerName.equalsIgnoreCase("ABC")){
			//put customized action here
			}else{
				getUniversalSearchBar().click();
				getUniversalSearchBarTextField().sendKeys("(");
				getUniversalSearchBarTextField().sendKeys(Keys.ENTER);
				getUniversalSearchBarTextField().sendKeys(searchParameter);
				
				
			}
	}
	public void universalSearchwithBrackets(String customerName, String searchParameter, String searchParameterType )throws InterruptedException{
		if(customerName.equalsIgnoreCase("ABC")){
			//put customized action here
			}else{
				typeInUniversalSearchwithBrackets(customerName,searchParameter);
				
				//getUniversalSearchBar().click();
				//getUniversalSearchBarTextField().sendKeys(searchParameter);
				
				//cu.explicitWait(driver, By.xpath("(//span[text()='"+searchParameterType+"'])[1]"), 30L);
				cu.explicitWait(driver, By.xpath("(//span[text()='"+searchParameterType+"'])[1]"));
				driver.findElement(By.xpath("(//span[text()='"+searchParameterType+"'])[1]")).click();
				getUniversalSearchBarTextField().sendKeys(")");
				getUniversalSearchBarTextField().sendKeys(Keys.ENTER);
				clickUniversalSearchButton();
		}
		
		
	}
	
	public void validateAutoSuggestListAppearsWithParamTypeUniversalSearch() throws InterruptedException{
		
				
		//cu.explicitWait(driver, searchedPamameterResultList, 30L);
		cu.explicitWait(driver, searchedPamameterResultList);
		List<WebElement> parameterTypeList= getSearchedPamameterTypeList();
		List<String> typeList = new ArrayList<String>();
		for(WebElement we:parameterTypeList){
			typeList.add(we.getText());
		}
		  
		boolean flag=false;
		
		  if(typeList.contains("Skill")||typeList.contains("skill") ||  typeList.contains("Location")|| typeList.contains("Full Name")
				||typeList.contains("Institute") ||typeList.contains("Source Name")||typeList.contains("Employer")
				||typeList.contains("Designation"))
		  
		  flag=true;
			  else{
				  flag=false;
				}
			  
		 Assert.assertTrue(flag, "Parameter Type is not available with searched parameter in autosuggest List");		
	}
	
	
}
