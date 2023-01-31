package com.qa.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.pages.CustomersBalancePage;
import com.qa.pages.HomePage;
import com.qa.pages.UserPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase2_Q2 extends TestBase {


	public HomePage homePage;
	public UserPage userPage;
	public CustomersBalancePage customerBalancePage;
	String homeurl = prop.getProperty("url");

	public TestCase2_Q2() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		setUrl(homeurl);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		homePage = new HomePage();
		userPage = new UserPage();
		customerBalancePage = new CustomersBalancePage();
	}

	// Add Customer
	@Test
	public void SelectCustomer() {
		String homePageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(homePageTitle, "XYZ Bank");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		userPage = homePage.gotoCustomerLogin();
		userPage.gotoCustomerLogin();
		
	}

	// Perform Transaction
	@Test
	public void performTransactions() {
		customerBalancePage.SelectAccount();
		String status;
		status =customerBalancePage.AmountTransaction(50000, "Deposit");
		Assert.assertEquals(status, "Success");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		status = customerBalancePage.AmountTransaction(3000, "Withdraw");
		Assert.assertEquals(status, "Success");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		status = customerBalancePage.AmountTransaction(2000, "Withdraw");
		Assert.assertEquals(status, "Success");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		status = customerBalancePage.AmountTransaction(5000, "Deposit");
		Assert.assertEquals(status, "Success");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		status = customerBalancePage.AmountTransaction(10000, "Withdraw");
		Assert.assertEquals(status, "Success");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		status = customerBalancePage.AmountTransaction(15000, "Withdraw");
		Assert.assertEquals(status, "Success");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		status = customerBalancePage.AmountTransaction(1500, "Deposit");
		Assert.assertEquals(status, "Success");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	

}
