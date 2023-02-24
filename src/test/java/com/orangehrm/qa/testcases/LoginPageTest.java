package com.orangehrm.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.TestBase;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.pages.ResetPasswordPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	ResetPasswordPage resetPasswordPage;
	
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		
		loginPage = new LoginPage();
	}
	
	
	@Test(priority = 1)
	public void orangeHRLogoImageTest() {
		boolean flag = loginPage.validateOrangeHRLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 2)
	public void loginPageTitleTest() {
		
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "OrangeHRM");
	}
		
	
	@Test(priority = 3)
	public void loginTest() throws InterruptedException {
		
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(4000);
		
	}
	
	@Test(priority = 4)
	public void loginTestNegative() throws InterruptedException {
		
		dashboardPage = loginPage.login(prop.getProperty("username1"), prop.getProperty("password1"));
		Thread.sleep(4000);
		// Check if error message is displayed
	    boolean isErrorMessageDisplayed = loginPage.isErrorMessageDisplayed();
	    //Assert.assertFalse(isErrorMessageDisplayed, "Login failed. Error message not displayed.");
	    Assert.assertTrue(isErrorMessageDisplayed, "Login failed. Error message not displayed.");
	}
	
	@Test(priority = 5)
	public void resetPasswordTest() throws InterruptedException {
		resetPasswordPage = loginPage.clickOnForgotPasswordLink();
		resetPasswordPage = resetPasswordPage.resetPassword(prop.getProperty("username"));
		Thread.sleep(4000);
			
	}
	
	@Test(priority = 6)
	public void resetPasswordCancel() throws InterruptedException {
		resetPasswordPage = loginPage.clickOnForgotPasswordLink();
		loginPage = resetPasswordPage.resetPasswordCancel(prop.getProperty("username"));
		
		Thread.sleep(4000);
			
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
