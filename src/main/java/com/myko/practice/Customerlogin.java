package com.myko.practice;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Customerlogin {

	WebDriver driver;
	Properties prop;
	String custReferenceNum;
	String custPin;

	// Storing locaters into variables

	By customerRef = By.xpath("//*[@id='customReference']");

	// By pinInputs = By.cssSelector("input[type='tel']");

	By loginBtn = By.id("loginbtnhnz");

	// Constructor & // reading password and customer ref number from file
	public Customerlogin(WebDriver driver) {
		this.driver = driver;

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\nsehrawat\\Automation -Neha\\MyKO\\myko-test-automation\\src\\main\\resources\\test.properties");
			prop.load(fis);

			custReferenceNum = prop.getProperty("custRef");
			custPin = prop.getProperty("custPin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void customerNumber() {
		driver.findElement(customerRef).sendKeys(custReferenceNum);
	}

	public void enterPin() throws InterruptedException {
		// driver.findElement(pinInputs).sendKeys(password);
		// Fill PIN (character by character into password0 to password5)

		/*
		 * This starts a loop that runs once for each digit in the PIN. If the PIN is
		 * "123456", Repeat this loop 6 times (because the PIN has 6 digits) (from i = 0
		 * to i = 5).
		 */
		for (int i = 0; i < custPin.length(); i++) {

			/*
			 * Take one digit at a time from the PIN. If the PIN is 123456, on the first
			 * loop, it takes 1, then 2, and so on.
			 */
			char digit = custPin.charAt(i);

			/*
			 * Makes the correct ID of the input box. For the first digit, it becomes
			 * password0. For the second digit, it becomes password1, and so on.
			 */
			String inputId = "password" + i;

			/* Finds the box on the screen using its ID */
			WebElement pinField = driver.findElement(By.id(inputId));

			/* Types the digit into that box */
			pinField.sendKeys(Character.toString(digit));
			Thread.sleep(200);
		}

		Thread.sleep(3000);
	}

	public void clickLogin1() {
		driver.findElement(loginBtn).click();

	}
}
 
