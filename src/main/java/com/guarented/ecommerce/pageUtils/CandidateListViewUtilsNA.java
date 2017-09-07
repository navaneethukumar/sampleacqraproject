package com.guarented.ecommerce.pageUtils;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.guarented.ecommerce.commonUtils.CommonUtils;
import com.guarented.ecommerce.pages.CandidateListViewPageNA;
import com.spire.base.controller.Assertion;
import com.spire.base.controller.Logging;

public class CandidateListViewUtilsNA extends CandidateListViewPageNA{
	
	CommonUtils cu = new CommonUtils();

	public CandidateListViewUtilsNA(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void verifySkillsCheckedInRefineSearch(String skill_one,String skill_two){
		Assertion.assertTrue(this.driver.findElement(By.xpath("//input[@id='facetCheck_skill_java']")).isSelected(),"Skills are not selected");
	}
	
	public void verifySkillInSearchedCriteria(String enteredSkill){
		cu.explicitWait(driver, searchedCriteria, 120L);
		String skillInSearchedCriteria=getSearchedCriteriaList().get(0).getText();
		Assertion.assertTrue(skillInSearchedCriteria.equalsIgnoreCase(enteredSkill),"Entered skill and skill in searched criteria did not match");
	}
	
	public void verifyExpInSearchedCriteria(String exp){
		String expInSearchedCriteria=getSearchedCriteriaList().get(1).getText();
		System.out.println("expInSearchedCriteria : "+expInSearchedCriteria);
		Assertion.assertTrue(expInSearchedCriteria.contains(exp),"Entered exp and exp-range in searched criteria did not match");
	}
	
	public void validateSearchedResultBySkillandExperienceCombination(String skill,String exp){
		verifySkillInSearchedCriteria(skill);
		verifyExpInSearchedCriteria(exp);
		verifyDataInFacet(skill);
		int dataIndex=getDataIndexInFacet(skill);
		boolean flag=getCheckBoxListInFacet().get(dataIndex).isSelected();
		
		Assertion.assertTrue(flag, "Skill "+skill+" is not selected");
		dataIndex=getDataIndexInFacet("Bangalore");
		System.out.println("Index"+dataIndex);
		flag=false;
		flag=getCheckBoxListInFacet().get(dataIndex).isSelected();
		Assertion.assertTrue(flag, "Location "+"Bangalore"+" is not selected");
		
	}
	
	int index=0;
	public int getDataIndexInFacet(String parameter){
		
		for(WebElement we:getDataListInFacet()) {
			if(we.getText().equalsIgnoreCase(parameter)){
				break;
			}
			index++;
		}
		System.out.println("Data Index : "+index);
		return index;
	}
	
	public void verifyDataInFacet(String searchedData){
		ArrayList <String> facetDataList=new ArrayList<String>();
		System.out.println("facetDataList >>"+facetDataList);
		for(WebElement we:getDataListInFacet()) {
			facetDataList.add(we.getText().toLowerCase());
			}
		System.out.println("facetDataList >>"+facetDataList);
			Assertion.assertTrue(facetDataList.contains(searchedData.toLowerCase()), "Facet does not contain "+searchedData); 
		}
		
    public int getDataCountListInFacet(String parameter){
    	int count;
		int dataIndex=getDataIndexInFacet(parameter);
		String []arr=getCountListInFacet().get(dataIndex).getText().split(" ");
		count=Integer.parseInt(arr[0]);
		return count;
	}

}
