package com.upgrade.poc.objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpgradeCustInfoPage {

	private static WebElement element = null;

	private static List<WebElement> elementlist = null;

	public static WebElement tboxFirstName(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerFirstName']"));

		return element;
	}

	public static WebElement tboxLastName(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerLastName']"));

		return element;
	}

	public static WebElement tboxAddress(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@id='geosuggest__input--borrowerStreet']"));

		return element;
	}

	public static WebElement tboxCity(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerCity']"));

		return element;
	}

	public static WebElement tboxState(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerState']"));

		return element;
	}

	public static WebElement tboxZip(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerZipCode']"));

		return element;
	}

	public static WebElement tboxDob(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerDateOfBirth']"));

		return element;
	}

	public static WebElement tboxBorrowerIncome(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerIncome']"));

		return element;
	}

	public static WebElement tboxAdditionalIncome(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='borrowerAdditionalIncome']"));

		return element;
	}

	public static WebElement tboxUserName(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='username']"));

		return element;
	}

	public static WebElement tboxPassword(WebDriver driver) {

		element = driver.findElement(By.xpath("//input[@name='password']"));

		return element;
	}
	
	public static String xp_chkBoxAgree = "//div[@class='sc-ljRboo sc-jmhFOf gTusOv fFGAIg']";

	public static WebElement chkboxAgree(WebDriver driver) {

		element = driver.findElement(By.xpath(xp_chkBoxAgree));

		return element;
	}

	public static WebElement buttonCheckCreditLine(WebDriver driver) {

		element = driver.findElement(By.xpath(".//button[@type='submit']"));

		return element;
	}

	public static String xp_labelFieldError = ".//div[@data-error='true']";

	public static WebElement labelFieldError(WebDriver driver) {

		element = driver.findElement(By.xpath(xp_labelFieldError));

		return element;
	}

}
