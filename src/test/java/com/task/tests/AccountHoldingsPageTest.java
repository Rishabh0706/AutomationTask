package com.task.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.task.base.TestBase;
import com.task.pages.AccountHoldingsPage;
import com.task.pages.HomePage;
import com.task.pages.LoginPage;
import com.task.pages.TemplatesDesignPage;
import com.task.util.TestUtil;

public class AccountHoldingsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	TemplatesDesignPage templatesDesignPage;
	AccountHoldingsPage accountHoldingsPage;
	
	public AccountHoldingsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setup(){
		
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority=1)
	public void totalNetworhTest() throws InterruptedException{
		accountHoldingsPage = homePage.clickAccountHoldingsTab();
		boolean currentValueUSD = accountHoldingsPage.currentValueUSD(prop.getProperty("account_name"));
		Assert.assertTrue(currentValueUSD, "Current USD Value is not displayed");
		System.out.println("Current USD vlue is diplayed on page");
		boolean netWorth = accountHoldingsPage.totalNetworh();
		Assert.assertTrue(netWorth, "Current USD Value is not equal to Total Networth Value");
	}
	
	@AfterMethod
	public void teardown(){
		
		homePage.logOut();
		driver.quit();
	}
	

}
