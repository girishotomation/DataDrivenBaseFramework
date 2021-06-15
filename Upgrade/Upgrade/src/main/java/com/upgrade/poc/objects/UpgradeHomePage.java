package com.upgrade.poc.objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpgradeHomePage {

	private static WebElement element = null;

	private static List<WebElement> elementlist = null;

	public static WebElement linkPersonalLoans(WebDriver driver) {

		element = driver.findElement(By.xpath("//a[@class='header-nav-menu__link'][@href='/personal-loans/']"));

		return element;
	}

	public static WebElement linkUpgradeCard(WebDriver driver) {

		element = driver.findElement(By.xpath("//a[@class='header-nav-menu__link'][@href='/upgrade-card/']"));

		return element;
	}

	public static String xp_UpgradeLogo = ".//a[@class='header__logo']";

	public static WebElement imgUpgradeLogo(WebDriver driver) {

		element = driver.findElement(By.xpath(xp_UpgradeLogo));

		return element;
	}

	public static String xp_ButtonSignIn = "//a[normalize-space()='Sign In']";

	public static WebElement buttonSingIN(WebDriver driver) {

		element = driver.findElement(By.xpath(xp_ButtonSignIn));

		return element;
	}

}
