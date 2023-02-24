package com.orangehrm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.TestBase;
import com.orangehrm.qa.pages.AdminPage;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.util.TestUtil;

public class AdminPageTest extends TestBase{
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AdminPage adminPage;
	
	public AdminPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		
		loginPage = new LoginPage();
		
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		adminPage = dashboardPage.clickOnadminLink();
	}
	
	
	@Test(priority = 1)
	public void verifyAdminLabelTest() throws InterruptedException {
		
		Assert.assertTrue(adminPage.verifyAdminLabel(), "Admin Label is not visible");
		Thread.sleep(3000);
	}
	
	@Test(priority = 2)
	public void searchUserByUsernameTest() throws InterruptedException {
		
		adminPage = adminPage.searchUserByUsername("admin");
		Thread.sleep(3000);
	}
	
	@Test(priority = 3)
	public void resetSearchUserTest() throws InterruptedException {
		
		adminPage = adminPage.searchUserByUsername("admin");
		Thread.sleep(3000);
		adminPage = adminPage.resetSearchUser();
		Thread.sleep(3000);
		
	}
	
	@Test(priority = 4)
	public void searchUserByUserRoleTest() throws InterruptedException {
		
		adminPage = adminPage.searchUserByUserRole();
		Thread.sleep(3000);
	}
	
	@Test(priority = 5)
	public void selectUserTest() throws InterruptedException {
		
		adminPage.selectContactsByName("12werrty");
		Thread.sleep(3000);
	}
	
	@Test(priority = 6)
	public void deleteMultipleUserTest() throws InterruptedException {
		
		adminPage.selectContactsByName("12werrty");
		adminPage.selectContactsByName("12werrty");
		WebElement deleteSelectedBtn = driver.findElement(By.xpath("//button[text()=' Delete Selected ']"));
		//deleteSelectedBtn.click();
		Thread.sleep(3000);
	}
	
	@Test(priority = 7)
	public void deleteSingleUserTest() throws InterruptedException {
		adminPage.deleteContactsByName("Goutam.Ganesh");
		
		Thread.sleep(3000);
	}
	
	
	
	
	
	@AfterMethod
	public void tearDown()  {
		
		driver.quit();  //Why I am getting configuration error in this one?
		
	}

}
