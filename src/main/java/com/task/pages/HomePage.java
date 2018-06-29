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
	
	@FindBy(xpath="//a[@class='dropdown-toggle']")
	WebElement logOutDropDown;
	
	@FindBy(xpath="//i[@class='fa fa-sign-out']")
	WebElement logOutBtn;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public TemplatesDesignPage clickReportsTemplatesDesignTab(){
		
		reportsTab.click();
		reportsTemplateDesignTab.click();
		
		return new TemplatesDesignPage();
	}
	
	public void logOut(){
		
		logOutDropDown.click();
		logOutBtn.click();
	}
	
	
}
