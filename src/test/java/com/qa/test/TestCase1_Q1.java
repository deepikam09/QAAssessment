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
import com.qa.pages.ManagerPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase1_Q1 extends TestBase {

	public ManagerPage managerPage;
	public HomePage homePage;
	String homeurl = prop.getProperty("url");
	String DelfNm1= prop.getProperty("fNm1");
	String DelfNm2= prop.getProperty("LNm1");		
	String DelLNm1= prop.getProperty("fNm2");
	String DelLNm2= prop.getProperty("LNm2");
	public TestCase1_Q1() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		setUrl(homeurl);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		homePage = new HomePage();
		managerPage = new ManagerPage();
	}

	// Add Customer
	@Test
	public void InsertCustomer() {
		String homePageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(homePageTitle, "XYZ Bank");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		managerPage = homePage.gotoManagerLogin();
		managerPage.adcustomer();

	}

	// Verify Customer Added
	@Test
	public void VerifyCustomer() {
		try {
			managerPage.validateCustAdded();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//delete Customer
	@Test
	public void ZDeleteCustomer() {
	managerPage.deleteCustomer(DelfNm1,DelLNm1);
	managerPage.deleteCustomer(DelfNm2,DelLNm2);	
	}

}
