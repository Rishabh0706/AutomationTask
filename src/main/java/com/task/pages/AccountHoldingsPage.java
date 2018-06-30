package com.task.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.task.base.TestBase;

public class AccountHoldingsPage extends TestBase{
	
	@FindBy(xpath="//input[@class='k-input k-readonly']")
	WebElement accountBox;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement applyFiltersBtn;
	
	@FindBy(xpath="//div[@class='col-sm-offset-3 col-sm-5']//span[contains(@data-bind,'totalNetworthInUSDFormatted')]")
	WebElement currentValueUSD;
	
	public AccountHoldingsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean currentValueUSD(String accountName) throws InterruptedException{
		
		accountBox.sendKeys(accountName);
		WebElement account = driver.findElement(By.xpath("//li[contains(text(),'"+accountName+"')]"));
		account.click();
		applyFiltersBtn.click();
		Thread.sleep(1500);
		System.out.println("Current value : "+currentValueUSD.getText());
		
		return currentValueUSD.isDisplayed();

	}
	
	public boolean totalNetworh(){
		
		String currentValue = currentValueUSD.getText();
		
		List<WebElement> panelList = driver.findElements(By.xpath("//div[@data-bind='foreach: sectionsArray']//span[contains(@data-bind,'resultCount') and not(contains(text(),'0'))]"));
		
		long GrandTotal = 0;
		
		for (int i = 0; i < panelList.size(); i++) {
			
            panelList.get(i).click();
            String ID = panelList.get(i).findElement(By.xpath("//parent::span//following-sibling::div//descendant::div[contains(@id,'Grid')]")).getAttribute("id");
            WebElement total = driver.findElement(By.xpath("//div[@id='"+ID+"']//td[contains(text(),'Total')]"));
            String totalValue = total.getText();
            
            System.out.println(ID+" panel "+totalValue);
            String[] actualValue = totalValue.split(" ");
            
            if(actualValue[1].contains("(")) {
            	String[] actualValue1 = actualValue[1].split("(");
            	if(actualValue1[1].contains(")")){
            		String[] actualValue2 = actualValue1[1].split(")");
            		GrandTotal = GrandTotal + Integer.parseInt(actualValue2[0]);
            	}
            }else{
            	GrandTotal = GrandTotal + Integer.parseInt(actualValue[1]);
            }    
		}
		
		System.out.println("Grand Total value : "+GrandTotal);
		
		if(GrandTotal==Integer.parseInt(currentValue))
			return true;
		
		return false;	
	}
	

}
