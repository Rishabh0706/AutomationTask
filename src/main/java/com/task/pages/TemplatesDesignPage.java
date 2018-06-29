package com.task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.task.base.TestBase;

public class TemplatesDesignPage extends TestBase{
	
	@FindBy(xpath="//a[@class='k-button k-grid-addReport grid-add-button']")
	WebElement createBtn;
	
	@FindBy(id="reportName")
	WebElement templateName;
	
	@FindBy(id="baseReports")
	WebElement selectBox;
	
	@FindBy(xpath="//span[@aria-owns='baseReports_listbox']")
	WebElement styleTemplateBox;
	
	@FindBy(xpath="//*[@id='baseReports-list' and @class='k-list-container k-popup k-group k-reset k-state-border-up']/span/input")
	WebElement styleTemplateSearchBox;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//*[@id='reportingManage']/div[4]/div/table/thead[1]/tr[2]/th[5]/span/span/span/input")
	WebElement nameColumn;
	
	@FindBy(xpath="//*[@id='reportingManage']/div[5]/table/tbody/tr[3]/td[5]")
	WebElement templateNameColumn;
	
	@FindBy(id="reportStatus")
	WebElement statusDropDown;
	
	@FindBy(xpath="//a[@class='k-button k-grid-deleteReport grid-delete-button']")
	WebElement deleteBtn;
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement okBtn;
	
	public TemplatesDesignPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String createReportTemplate(){
		
		createBtn.click();
		
		templateName.sendKeys(prop.getProperty("template_name"));
		styleTemplateBox.click();
		styleTemplateSearchBox.sendKeys("BaseTemplateDL");
		Select select = new Select(selectBox);
		select.selectByVisibleText("BaseTemplateDL");
		saveBtn.click();
		
		nameColumn.sendKeys(prop.getProperty("template_name"));
		nameColumn.click();
			
		return templateNameColumn.getText();
	}
	
	public String editReportTemplate(String tname){
		
		WebElement editBtn = driver.findElement(By.xpath("//td[text()='"+tname+"']/preceding-sibling::td[@class='k-command-cell kgrid-wrap-cells']/a[@class='k-button k-button-icontext k-grid-editCustom']"));
		editBtn.click();
		
		Select select = new Select(statusDropDown);
		select.selectByVisibleText("Ready To Assign");
		saveBtn.click();
		
		String status = driver.findElement(By.xpath("//td[text()='"+tname+"']/parent::tr/td[8]")).getText();
		
		return status;
		
	}
	
	public String copyReportTemplate( String tname,String tnameNew){
		
		WebElement copyBtn = driver.findElement(By.xpath("//td[text()='"+tname+"']/preceding-sibling::td[@class='k-command-cell kgrid-wrap-cells']/a[@class='k-button k-button-icontext k-grid-copyCustom']"));
		copyBtn.click();
		
		templateName.clear();
		templateName.sendKeys(tnameNew);
		saveBtn.click();
		
		return templateNameColumn.getText();
		
	}
	
	public boolean deleteReportTemplate( String tname,String tnameNew){
		
		WebElement radioBtn1 = driver.findElement(By.xpath("//td[text()='"+tname+"']/preceding-sibling::td[@class='kgrid-wrap-cells']/input"));
		radioBtn1.click();
		WebElement radioBtn2 = driver.findElement(By.xpath("//td[text()='"+tnameNew+"']/preceding-sibling::td[@class='kgrid-wrap-cells']/input"));
		radioBtn2.click();
		
		deleteBtn.click();
		okBtn.click();
		
		WebElement template1 = driver.findElement(By.xpath("//td[text()='"+tname+"']"));
		WebElement template2 = driver.findElement(By.xpath("//td[text()='"+tnameNew+"']"));
		
		if(!template1.isDisplayed() && !template2.isDisplayed())
			return true;
		
		return false;
		
	}

}
