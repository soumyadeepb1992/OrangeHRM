package com.orangehrm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.TestBase;
import com.orangehrm.qa.pages.AdminPage;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.util.TestUtil;

public class DashboardPageTest extends TestBase{
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AdminPage adminPage;
	
	public DashboardPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		
		loginPage = new LoginPage();
		
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
	
	
	@Test(priority = 1)
	public void verifyDashboardPageTitleTest() {
		
		String title = dashboardPage.verifyDashboardPageTitle();
		Assert.assertEquals(title, "OrangeHRM", "Dashboard page title not matched");
	}
	
	@Test(priority = 2)
	public void verifyUserNameTest() throws InterruptedException {
		Assert.assertTrue(dashboardPage.verifyCorrectUserName(), "User Name label is not correct");
		Thread.sleep(3000);
	}
	
	@Test(priority = 3)
	public void verifyAdminLinkTest() throws InterruptedException {
		adminPage = dashboardPage.clickOnadminLink();
		Thread.sleep(3000);
		Assert.assertTrue(adminPage.verifyAdminLabel(), "Admin Label is not visible");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();  //Why I am getting configuration error in this one?
		
	}

}
