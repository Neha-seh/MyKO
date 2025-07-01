package com.myko.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyKOLoginPage {
	
	WebDriver  driver;

	By loginBtn =  By.xpath("//button[contains(text(),'Login')]");
	
	public MyKOLoginPage (WebDriver driver) {
		this.driver= driver;
		}
	
	public void clickLogin() {
		driver.findElement(loginBtn).click();
	}
	
}
