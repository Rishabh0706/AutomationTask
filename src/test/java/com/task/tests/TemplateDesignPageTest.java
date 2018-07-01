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

public class TemplateDesignPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	TemplatesDesignPage templatesDesignPage;
	
	public TemplateDesignPageTest(){
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
		String templateName = templatesDesignPage.createReportTemplate(prop.getProperty("template_name"),prop.getProperty("template_style"));
		Assert.assertEquals(templateName,prop.getProperty("template_name"),"Template Creation Failed");
		System.out.println("Template Created Successfully");
	}
	
	@Test(priority=2)
	public void editReportTemplateTest(){
		templatesDesignPage = homePage.clickReportsTemplatesDesignTab();
		String templateStatus = templatesDesignPage.editReportTemplate(prop.getProperty("template_name"),prop.getProperty("template_status"));
		Assert.assertEquals(templateStatus,prop.getProperty("template_status"),"Template editing Failed");
		System.out.println("Template Edited Successfully");
	}
	
	@Test(priority=3)
	public void copyReportTemplateTest(){
		templatesDesignPage = homePage.clickReportsTemplatesDesignTab();
		String templateNameNew = templatesDesignPage.copyReportTemplate(prop.getProperty("template_name"),prop.getProperty("template_name_new"));
		Assert.assertEquals(templateNameNew,prop.getProperty("template_name_new"),"Template Copy Failed");
		System.out.println("Template Copied Successfully");
	}
	
	@Test(priority=4)
	public void deleteReportTemplateTest(){
		templatesDesignPage = homePage.clickReportsTemplatesDesignTab();
		boolean templateNameNew = templatesDesignPage.deleteReportTemplate(prop.getProperty("template_name"),prop.getProperty("template_name_new"));
		Assert.assertEquals(templateNameNew,true,"Template Deletion Failed" );
		System.out.println("Template Deleted Successfully");
	}
	
	@AfterMethod
	public void teardown(){
		
		homePage.logOut();
		driver.quit();
	}

}
