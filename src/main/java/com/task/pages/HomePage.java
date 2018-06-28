package com.task.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.task.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//a[@id='95']")
	WebElement reportsTab;
	
	@FindBy(xpath="//ul[@aria-labelledby='95']//li[@class='k-item k-state-default k-first']")
	WebElement reportsTemplateDesignTab;
	
	@FindBy(xpath="//a[@class='k-button k-grid-addReport grid-add-button']")
	WebElement createBtn;
	
	@FindBy(id="reportName")
	WebElement templateName;
	
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
	
	@FindBy(xpath="//a[@class='dropdown-toggle']")
	WebElement logOutDropDown;
	
	@FindBy(xpath="//i[@class='fa fa-sign-out']")
	WebElement logOutBtn;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public String createReportTemplate(){
		
		reportsTab.click();
		reportsTemplateDesignTab.click();
		createBtn.click();
		templateName.sendKeys(prop.getProperty("template_name"));
		styleTemplateBox.click();
		styleTemplateSearchBox.sendKeys("BaseTemplateDL");
		nameColumn.sendKeys(prop.getProperty("template_name"));
		nameColumn.click();
		
		return templateNameColumn.getText();
	}
	
	public void logOut(){
		
		logOutDropDown.click();
		logOutBtn.click();
	}
	
	
}
