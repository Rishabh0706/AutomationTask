package com.task.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.task.base.TestBase;
import com.task.pages.HomePage;
import com.task.pages.LoginPage;
import com.task.pages.TemplatesDesignPage;
import com.task.util.TestUtil;


public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	TemplatesDesignPage templatesDesignPage;
	
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
		templatesDesignPage = homePage.clickReportsTemplatesDesignTab();
		String templateName = templatesDesignPage.createReportTemplate();
		Assert.assertEquals(templateName,prop.getProperty("template_name"));
	}
	
	@Test(priority=2)
	public void editReportTemplateTest(){
		templatesDesignPage = homePage.clickReportsTemplatesDesignTab();
		String templateStatus = templatesDesignPage.editReportTemplate(prop.getProperty("template_name"));
		Assert.assertEquals(templateStatus,"Ready To Assign");
	}
	
	@Test(priority=3)
	public void copyReportTemplateTest(){
		templatesDesignPage = homePage.clickReportsTemplatesDesignTab();
		String templateNameNew = templatesDesignPage.copyReportTemplate(prop.getProperty("template_name"),prop.getProperty("template_name_new"));
		Assert.assertEquals(templateNameNew,prop.getProperty("template_name_new"));
	}
	
	@Test(priority=4)
	public void deleteReportTemplateTest(){
		templatesDesignPage = homePage.clickReportsTemplatesDesignTab();
		boolean templateNameNew = templatesDesignPage.deleteReportTemplate(prop.getProperty("template_name"),prop.getProperty("template_name_new"));
		Assert.assertEquals(templateNameNew,true );
	}
	
	@AfterMethod
	public void teardown(){
		
		homePage.logOut();
		//driver.quit();
	}

}
