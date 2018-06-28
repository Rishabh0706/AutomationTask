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
	public void createReportTemplateTest(){
		
		String templateName = homePage.createReportTemplate();
		Assert.assertEquals(templateName,prop.getProperty("template_name"));
	}
	
	@AfterMethod
	public void teardown(){
		
		homePage.logOut();
		//driver.quit();
	}

}
