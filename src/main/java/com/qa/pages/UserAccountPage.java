package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountPage extends com.qa.base.TestBase {

	// Page Factory -OR
	@FindBy(xpath = "//button[@ng-class = 'btnClass1']")
	@CacheLookup
	public WebElement AddCustomer;
	
	// Initializing the Page Objects:
		public UserAccountPage() {
			PageFactory.initElements(driver, this);
		}

}
