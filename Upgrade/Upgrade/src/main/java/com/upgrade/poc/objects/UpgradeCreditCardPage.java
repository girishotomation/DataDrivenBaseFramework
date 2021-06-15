package com.upgrade.poc.objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpgradeCreditCardPage {

	private static WebElement element = null;

	private static List<WebElement> elementlist = null;

	public static WebElement buttonCaseRewardsCard(WebDriver driver) {

		element = driver.findElement(By.xpath("//a[@class='header-nav-menu__link'][@href='/personal-loans/']"));

		return element;
	}

	public static WebElement buttonTripleCashRewardsCard(WebDriver driver) {

		element = driver.findElement(By.xpath(".//a[@href='/funnel/pcl/pi1?rp_code=01']"));

		return element;
	}

	public static WebElement buttonBasicCard(WebDriver driver) {

		element = driver.findElement(By.xpath(".//a[@href='/funnel/pcl/pi1?rp_code=00']"));

		return element;
	}
}
