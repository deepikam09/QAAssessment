package com.qa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.TestUtil;

public class ManagerPage extends com.qa.base.TestBase {

	// Page Factory -OR
	@FindBy(xpath = "//button[@ng-class = 'btnClass1']")
	@CacheLookup
	public WebElement AddCustomer;
	@FindBy(xpath = "//button[@ng-class = 'btnClass3']")
	@CacheLookup
	public WebElement Customers;
	@FindBy(xpath = "//input[@placeholder='First Name']")
	@CacheLookup
	public WebElement FirstName;
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	@CacheLookup
	public WebElement LastName;
	@FindBy(xpath = "//input[@placeholder='Post Code']")
	@CacheLookup
	public WebElement PostCode;
	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	public WebElement Addcust;
	@FindBy(xpath = "//input[@type='text']")
	@CacheLookup
	public WebElement SearchCust;
	@FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr")
	@CacheLookup
	public List<WebElement> NoofRows;

//Variables

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\USER\\Desktop\\Assessment_QA\\XYZAssessment\\src\\main\\java\\TestData\\XYZBankTestData.xlsx";
	static int TotalRows;
	static int TotalCols;
	String data[][];

// Initializing the Page Objects:
	public ManagerPage() {
		PageFactory.initElements(driver, this);
	}

	public void adcustomer() {
		AddCustomer.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			data = getTestData("CreateCustomer");
			for (int i = 1; i <= TotalRows; i++) {
				Row celldata = sheet.getRow(i);
				FirstName.sendKeys(celldata.getCell(0).getStringCellValue());
				LastName.sendKeys(celldata.getCell(1).getStringCellValue());
				PostCode.sendKeys(celldata.getCell(2).getStringCellValue());
				Addcust.click();
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: Im In " + alertText);
				alert.accept();

			}

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Navigate to Customer page
		Customers.click();

	}

	public static String[][] getTestData(String sheetName)
			throws EncryptedDocumentException, IOException, InvalidFormatException {

		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		TotalRows = sheet.getLastRowNum();
		System.out.println(TotalRows);
		Row rowcells = sheet.getRow(0);
		TotalCols = rowcells.getLastCellNum();
		System.out.println(TotalCols);
		String data[][] = new String[TotalRows][TotalCols];

		DataFormatter format = new DataFormatter();

		for (int i = 1; i <= TotalRows; i++) {
			for (int j = 0; j < TotalCols; j++) {
				data[i - 1][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
				System.out.println(data[i - 1][j]);
			}
		}

		return data;
	}

	public void validateCustAdded() throws InvalidFormatException, IOException {

		try {
			for (int i = 1; i <= TotalRows; i++) {
				Row celldata = sheet.getRow(i);
				// for (WebElement cell : cells.get(0)) {
				for (WebElement row : NoofRows) {
					List<WebElement> cells = row.findElements(By.tagName("td"));
					String fnamewbtbl = cells.get(0).getText();
					String fnamexcl = celldata.getCell(0).getStringCellValue();
					String lnamewbtbl = cells.get(1).getText();
					String lnamexcl = celldata.getCell(1).getStringCellValue();
					String pstcodewbtbl = cells.get(2).getText();
					String pstcodeexcl = celldata.getCell(2).getStringCellValue();

					if (fnamexcl.equalsIgnoreCase(fnamewbtbl) && lnamexcl.equalsIgnoreCase(lnamewbtbl)
							&& pstcodeexcl.equalsIgnoreCase(pstcodewbtbl)) {
						System.out.println(
								"Customer Added Succesfully:" + cells.get(0).getText() + " " + cells.get(1).getText());
					}
					// System.out.println("Please check the Customer details an Try adding again"+
					// celldata.getCell(0).getStringCellValue() + " " +
					// celldata.getCell(1).getStringCellValue());
				}
			}

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteCustomer(String fname,String Lname) {
		WebElement DelButton;
		DelButton = driver.findElement(By.xpath("//td[contains(text(),'"+fname+"')]/following::td[contains(text(),'"+Lname+"')]/following::td[3]//button"));
		DelButton.click();
			
//		List<WebElement> NoofRowsDel1 = driver.findElements(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr"));
//		for (WebElement row : NoofRowsDel1) {
//			
//			List<WebElement> cells = row.findElements(By.tagName("td"));
//			String fnamewbtbl = cells.get(0).getText();
//			String lnamewbtbl = cells.get(1).getText();
//			String pstcodewbtbl = cells.get(2).getText();
//			
//
//			if (fnamewbtbl.equalsIgnoreCase("Jackson") && lnamewbtbl.equalsIgnoreCase("Frank")) {
//				DelButton.click();
//				System.out
//						.println("Customer Deleted Succesfully:" + cells.get(0).getText() + " " + cells.get(1).getText());
//			}else System.out.println("Customer Not Found");
//		}

		// WebElement mytable = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/"));
		// To locate rows of table.
		

	}
}
