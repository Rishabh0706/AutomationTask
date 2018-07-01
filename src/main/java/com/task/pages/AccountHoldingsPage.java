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
		
		return currentValueUSD.isDisplayed();

	}
	
	public boolean totalNetworh(){
		
		String currentValue = currentValueUSD.getText();
		currentValue = currentValue.replaceAll(",", "");
		System.out.println("Current value : "+currentValue);
		
		List<WebElement> panelList = driver.findElements(By.xpath("//div[@data-bind='foreach: sectionsArray']//span[contains(@data-bind,'resultCount') and not(contains(text(),'0'))]"));
		
		double GrandTotal = 0;
		
		for (int i = 0; i < panelList.size(); i++) {
            panelList.get(i).click();
		}
		
		for (int i = 0; i < panelList.size(); i++) {
			
            List<WebElement> panelDivList = driver.findElements(By.xpath("//div[@data-bind='foreach: sectionsArray']//span[contains(@data-bind,'resultCount') and not(contains(text(),'0'))]"
					+"//parent::span//following-sibling::div//descendant::div[contains(@id,'Grid')]"));

            String ID = panelDivList.get(i).getAttribute("id");
            
            String totalValue = driver.findElement(By.xpath("//div[@id='"+ID+"']//td[contains(text(),'Total')]")).getText();
            
            String[] actualValue = totalValue.split(" ");
            actualValue[1] = actualValue[1].replaceAll(",", "");
            System.out.println(ID+" panel total : "+actualValue[1]);
            
            if(actualValue[1].contains("(")) {
            	String actualValue1 = actualValue[1].replace("(", "");
            	actualValue1 = actualValue1.replace(")", "");
            	GrandTotal = GrandTotal + Double.parseDouble(actualValue1);
            }else{
            	GrandTotal = GrandTotal + Double.parseDouble(actualValue[1]);
            }    
		}
		
		//System.out.println("Grand Total value : "+GrandTotal);
		System.out.printf("Grand Total value : %.2f\n", GrandTotal);
		
		if(GrandTotal == Double.parseDouble(currentValue))
			return true;
		
		return false;	
	}
	

}
