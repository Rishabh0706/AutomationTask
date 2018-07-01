package com.task.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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
	
	@FindBy(xpath="//span[text()='In Progress']//ancestor::div[@class='col-sm-9']")
	WebElement statusDropDown;
	
	@FindBy(xpath="//div[@id='reportStatus-list' and @class='k-list-container k-popup k-group k-reset k-state-border-up']/span/input")
	WebElement statusInputBox;
	
	@FindBy(xpath="//a[@class='k-button k-grid-deleteReport grid-delete-button']")
	WebElement deleteBtn;
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement okBtn;
	
	public TemplatesDesignPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String createReportTemplate(String tname, String tstyle){
		
		createBtn.click();
		templateName.sendKeys(tname);
		styleTemplateBox.click();
		styleTemplateSearchBox.sendKeys(tstyle);
		WebElement templateStyle = driver.findElement(By.xpath("//li[contains(text(),'"+tstyle+"')]"));
		templateStyle.click();
		
		saveBtn.click();
		
		nameColumn.sendKeys(prop.getProperty("template_name"));
		nameColumn.sendKeys(Keys.ENTER);
			
		return templateNameColumn.getText();
	}
	
	public String editReportTemplate(String tname, String tstaus){
		
		WebElement editBtn = driver.findElement(By.xpath("//td[text()='"+tname+"']/preceding-sibling::td[@class='k-command-cell kgrid-wrap-cells']/a[@class='k-button k-button-icontext k-grid-editCustom']"));
		editBtn.click();
		
		statusDropDown.click();
		statusInputBox.sendKeys(tstaus);
		statusInputBox.sendKeys(Keys.DOWN);
		statusInputBox.sendKeys(Keys.DOWN);
		statusInputBox.sendKeys(Keys.ENTER);

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
		
		String newTemplateCopy = driver.findElement(By.xpath("//td[text()='"+tnameNew+"']/self::td")).getText();
		
		return newTemplateCopy;
		
	}
	
	public boolean deleteReportTemplate( String tname,String tnameNew){
		
		WebElement radioBtn1 = driver.findElement(By.xpath("//td[text()='"+tname+"']/preceding-sibling::td[@class='kgrid-wrap-cells']/input"));
		radioBtn1.click();
		WebElement radioBtn2 = driver.findElement(By.xpath("//td[text()='"+tnameNew+"']/preceding-sibling::td[@class='kgrid-wrap-cells']/input"));
		radioBtn2.click();
		
		deleteBtn.click();
		okBtn.click();
		
		List<WebElement> template1 = driver.findElements(By.xpath("//td[text()='"+tname+"']"));
		List<WebElement> template2 = driver.findElements(By.xpath("//td[text()='"+tnameNew+"']"));
		
		if(template1.size()==0 && template2.size()==0)
			return true;
		
		return false;
		
	}

}
