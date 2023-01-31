package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends com.qa.base.TestBase {

	//Page Factory -OR
		@FindBy(xpath="//button[contains(text(),'Bank Manager Login')]")
		@CacheLookup
		public WebElement managerLogin;	
		@FindBy(xpath="//button[contains(text(),'Customer Login')]")
		@CacheLookup
		public WebElement custLogin;
		
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	public ManagerPage gotoManagerLogin() {
		managerLogin.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return new ManagerPage();

	}
	public UserPage gotoCustomerLogin() {
		custLogin.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return new UserPage();

	}
}
