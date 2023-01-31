package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CustomersBalancePage extends com.qa.base.TestBase {

	// Page Factory -OR
	@FindBy(xpath = "//select[@name='accountSelect']")
	@CacheLookup
	public WebElement AcctDroDwn;
	@FindBy(xpath = "//span[@class='fontBig ng-binding']']")
	@CacheLookup
	public WebElement CustName;
	@FindBy(xpath = "//div[@class='center']/strong[2]")
	@CacheLookup
	public WebElement balance;
	@FindBy(xpath = "//button[@ng-click='deposit()']")
	@CacheLookup
	public WebElement deposit;
	@FindBy(xpath = "//button[@ng-click='withdrawl()']")
	@CacheLookup
	public WebElement withdraw;
	@FindBy(xpath = "//form[@ng-submit='withdrawl()']/div/input")
	@CacheLookup
	public WebElement withdrawAmtInput;
	@FindBy(xpath = "//form[@ng-submit='deposit()']/div/input")
	@CacheLookup
	public WebElement DepositAmtInput;
	@FindBy(xpath = "//button[@type='submit'][contains(text(),'Deposit')]")
	@CacheLookup
	public WebElement DepositAmtsubmit;
	@FindBy(xpath = "//button[@type='submit'][contains(text(),'Withdraw')]")
	@CacheLookup
	public WebElement WithdrawAmtsubmit;

	// Initializing the Page Objects:
	public CustomersBalancePage() {
		PageFactory.initElements(driver, this);
	}

	public String validateCustName() {
		return driver.getTitle();
	}

	public void SelectAccount() {
		Select dropdown = new Select(AcctDroDwn);
		dropdown.selectByVisibleText("1003");
	}

	public String AmountTransaction(int i, String Trans) {
		String Status = null;
		if (Trans.equalsIgnoreCase("Withdraw")) {
			WebElement balance = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
			int bal = Integer.parseInt(balance.getText());
			int AfterTrans = bal - i;
			WebElement withdraw = driver.findElement(By.xpath("//button[@ng-click='withdrawl()']"));
			withdraw.click();
			WebElement withdrawAmtInput = driver.findElement(By.xpath("//form[@ng-submit='withdrawl()']/div/input"));
			withdrawAmtInput.sendKeys(Integer.toString(i));
			WebElement WithdrawAmtsubmit = driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Withdraw')]"));
			WithdrawAmtsubmit.click();
			WebElement Afterbalance = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
			int Aftbal = Integer.parseInt(Afterbalance.getText());
			if (AfterTrans == Aftbal) {
				System.out.println("Transaction Amount Processed Accurately");
				Status= "Success";
			} else {
				System.out.println("Kindly check the balance");
				Status=  "Amount Failed";
			}
		}
		if (Trans.equalsIgnoreCase("Deposit")) {
			WebElement balance = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
			int bal = Integer.parseInt(balance.getText());
			int AfterTrans = bal + i;
			WebElement deposit = driver.findElement(By.xpath("//button[@ng-click='deposit()']"));
			deposit.click();
			WebElement DepositAmtInput = driver.findElement(By.xpath("//form[@ng-submit='deposit()']/div/input"));
			DepositAmtInput.sendKeys(Integer.toString(i));
			WebElement DepositAmtsubmit = driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Deposit')]"));
			DepositAmtsubmit.click();
			WebElement Afterbalance = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
			int Aftbal = Integer.parseInt(Afterbalance.getText());
			if (AfterTrans == Aftbal) {
				System.out.println("Transaction Amount Processed Accurately" + AfterTrans + Aftbal);;
				Status= "Success";
			} else {
				System.out.println("Kindly check the balance" + AfterTrans + Aftbal);
				Status= "Amount Failed";
			}
		}
		return Status;
		
	}



}
