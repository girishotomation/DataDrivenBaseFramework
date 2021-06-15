package com.upgrade.poc.base;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public static boolean elementPresence(WebDriver driver, String xpath, long timeOutChange) {
		// checks for an element presence and returns boolean
		try {
			driver.manage().timeouts().implicitlyWait(timeOutChange, TimeUnit.SECONDS);
			driver.findElement(By.xpath(xpath));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// System.out.println("Element found");
			return true;
		} catch (Exception e) {
			// System.out.println("Element NOT found");
			return false;
		}

	}

	public static void takesSnapshotReport(String fileName, ExtentTest extentTest, WebDriver driver) {
		// takes screenshot and attaches to HTMl report of extent reports 
		try {
			String rand = BaseClass.generateNumericData(5);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(System.getProperty("user.dir") + "/Reports/" + fileName + rand + ".png"));
			extentTest.log(LogStatus.INFO, "Snapshot below: " + extentTest.addScreenCapture(fileName + rand + ".png"));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static String generateNumericData(int length) {
		// generate a random numeric string of passed length
		String numeric = "1234567890";
		String outputNumeric = "";
		Random random = new Random();

		int len = length;
		for (int i = 0; i < len; i++) {
			char n = numeric.charAt(random.nextInt(10));
			outputNumeric = outputNumeric + n;
		}
		return outputNumeric;

	}
}
