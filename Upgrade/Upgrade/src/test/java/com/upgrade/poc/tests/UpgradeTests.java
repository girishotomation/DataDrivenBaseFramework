package com.upgrade.poc.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.upgrade.poc.base.BaseClass;
import com.upgrade.poc.data.Constants;
import com.upgrade.poc.objects.UpgradeCreditCardPage;
import com.upgrade.poc.objects.UpgradeCustInfoPage;
import com.upgrade.poc.objects.UpgradeHomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UpgradeTests {

	private WebDriver d;
	public static ExtentReports report;
	public static ExtentTest test;
	public static String reportDir;

	@BeforeTest
	public void initialize() {
		try {
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + Constants.REPORT_FOLDER));
			reportDir = System.getProperty("user.dir") + Constants.REPORT_PATH;
			report = new ExtentReports(reportDir, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void testPreReq() {
		try {
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();
			d.manage().window().maximize();
			d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 0, enabled = true)
	public void upgradeHomePageTest() {
		try {
			test = report.startTest("Verify Upgrade HomePage");
			test.log(LogStatus.INFO, "Navigating to Prod URL");
			d.get(Constants.PROD_URL);
			if (BaseClass.elementPresence(d, UpgradeHomePage.xp_ButtonSignIn, 3)) {
				test.log(LogStatus.PASS, "Sign in button is found as expected");
			} else {
				test.log(LogStatus.FAIL, "Sign in button is NOT found");
			}

			if (BaseClass.elementPresence(d, UpgradeHomePage.xp_UpgradeLogo, 3)) {
				test.log(LogStatus.PASS, "Upgrade Logo is found as expected");
			} else {
				test.log(LogStatus.FAIL, "Upgrade Logo in button is NOT found");
			}
			BaseClass.takesSnapshotReport("HomepAge", test, d);
			report.endTest(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1, dataProvider = "custInfoData", enabled = true)
	public void upgradeCustInfoFormTest(String testcase, String fname, String lname, String address, String city,
			String state, String zip, String dob, String income, String addincome, String email, String password) {
		try {
			test = report.startTest(testcase);
			test.log(LogStatus.INFO, "Verifying customer info form");
			test.log(LogStatus.INFO, "Navigating to Prod URL");
			d.get(Constants.PROD_URL);
			UpgradeHomePage.linkUpgradeCard(d).click();
			BaseClass.takesSnapshotReport("CustInfo", test, d);
			test.log(LogStatus.INFO, "Navigating to Basic card application form");
			// UpgradeCreditCardPage.buttonBasicCard(d).click();
			d.navigate().to(Constants.PROD_CUSTINFO);
			BaseClass.takesSnapshotReport("CustInfo", test, d);
			test.log(LogStatus.INFO, "Filling customer details");
			UpgradeCustInfoPage.tboxFirstName(d).sendKeys(fname);
			UpgradeCustInfoPage.tboxLastName(d).sendKeys(lname);
			UpgradeCustInfoPage.tboxAddress(d).sendKeys(address);
			UpgradeCustInfoPage.tboxCity(d).sendKeys(city);
			UpgradeCustInfoPage.tboxState(d).sendKeys(state);
			UpgradeCustInfoPage.tboxZip(d).sendKeys(zip);
			UpgradeCustInfoPage.tboxDob(d).sendKeys(dob);
			UpgradeCustInfoPage.tboxBorrowerIncome(d).sendKeys(income);
			UpgradeCustInfoPage.tboxAdditionalIncome(d).sendKeys(addincome);
			UpgradeCustInfoPage.tboxUserName(d).sendKeys(email);
			UpgradeCustInfoPage.tboxPassword(d).sendKeys(password);
			BaseClass.takesSnapshotReport("CustInfo", test, d);
			if (BaseClass.elementPresence(d, UpgradeCustInfoPage.xp_chkBoxAgree, 3)) {
				test.log(LogStatus.PASS, "Checkbox is present");
				BaseClass.takesSnapshotReport("CustInfo", test, d);
			} else {
				test.log(LogStatus.FAIL, "Checkbox is NOT present");
				BaseClass.takesSnapshotReport("CustInfo", test, d);
			}
			test.log(LogStatus.INFO, "Clicking on Check CredtiLine Button");
			UpgradeCustInfoPage.buttonCheckCreditLine(d).click();
			BaseClass.takesSnapshotReport("CustInfo", test, d);
			report.endTest(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void testPostReq() {

		d.close();

	}

	@AfterTest
	public void tearDown() {

		d.quit();
		report.flush();
		System.out.println("Test Report found in: " + reportDir);

	}

	@DataProvider(name = "custInfoData")
	public Object[][] cutsInfoData() {
		Object data[][] = null;
		try {

			DataFormatter format = new DataFormatter();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.PATH_EXCEL);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("DATA_CUSTINFO");
			int sheetRowCount = sheet.getPhysicalNumberOfRows();
			XSSFRow row = sheet.getRow(0);
			int sheetColCount = row.getLastCellNum();
			data = new Object[sheetRowCount - 1][sheetColCount];

			for (int r = 0; r < sheetRowCount - 1; r++) {
				row = sheet.getRow(r + 1);
				for (int c = 0; c < sheetColCount; c++) {
					XSSFCell cell = row.getCell(c);
					data[r][c] = format.formatCellValue(cell);

				}

			}
			wb.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}

}
