package com.task.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.task.base.TestBase;
import com.task.pages.HomePage;
import com.task.pages.LoginPage;
import com.task.util.TestUtil;



public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	
	public HomePageTest(){
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
	public void verifyHomePageTitleTest(){
		System.out.println("tests");
		//Assert.assertEquals(homePagetitle,"CRMPRO","Home Page Title not matched");
	}
	
	@Test(priority=2)
	public void verifyUsernmaeTest(){
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@AfterMethod
	public void teardown(){
		//driver.quit();
	}

}
