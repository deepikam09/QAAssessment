package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UserPage extends com.qa.base.TestBase {

	// Page Factory -OR
	@FindBy(xpath = "//select[@name='userSelect']")
	@CacheLookup
	public WebElement UsrDroDwn;
	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	public WebElement LgnBt;

	// Initializing the Page Objects:
	public UserPage() {
		PageFactory.initElements(driver, this);
	}

	public UserPage gotoCustomerLogin() {
		Select dropdown = new Select(UsrDroDwn);
		dropdown.selectByVisibleText("Hermoine Granger");
		LgnBt.click();
		return new UserPage();

	}

}
