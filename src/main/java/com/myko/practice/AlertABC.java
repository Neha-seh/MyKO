package com.myko.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class AlertABC {
	
	WebDriver driver;
	WebDriverWait wait;
	
	By alertType = By.xpath("//div[contains(@class, 'jss105')]");
	By closeAlertButton = By.xpath("//button[.//span[text()='Close']]");

	
	// TEST 1: Verify which alert appear upon logging in, Alert A or Alert B or Alert C or Nothing 
	
	public AlertABC (WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		}
	
	// Main alert handler: returns message + closes it
	public String handleAlert() throws TimeoutException {
	    try {
	        WebElement alertBox = wait.until(ExpectedConditions
	                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'jss105')]")));

	        String heading = "";
	        String body = "";

	        try {
	            heading = alertBox.findElement(By.tagName("h3")).getText().trim();
	        } catch (Exception e) {
	            System.out.println("No <h3> found in alert.");
	        }

	        try {
	            body = alertBox.findElement(By.tagName("p")).getText().trim();
	        } catch (Exception e) {
	            System.out.println("No <p> found in alert.");
	        }

	        String fullAlertText = heading + " " + body;
	        System.out.println("Alert: " + fullAlertText.toLowerCase());

	        closeAlert(); // Click 'Close' button

	        if (fullAlertText.toLowerCase().contains("your account is currently in debt")) {
	            return "Your account is currently in debt and we can help you get back on track";
	        } else if (fullAlertText.toLowerCase().contains("maintenance arrangement")) {
	            return "It looks like you have a maintenance arrangement while owing rent.Please contact us so we can update your arrangement";
	        } else if (fullAlertText.toLowerCase().contains("balance is currently in credit")) {
	            return "Your rent balance is currently in credit. To discuss what this credit means, please credit us";
	        } else if (fullAlertText.toLowerCase().contains("would you like to make an arrangement")) {
	            return "Would you like to make an arrangement to pay this off? If you need support, please contact us.";
	        } else {
	            return "unknown";
	        }

	    } catch (Exception e) {
	        System.out.println("❌ Unexpected error in handleAlert(): " + e.getMessage());
	        e.printStackTrace();
	        return "error";
	    }
	}

    // Reusable closeAlert method
	public void closeAlert() {
	    try {
	        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[.//span[text()='Close']]")));
	        closeButton.click();
	        System.out.println("Closed alert modal.");
	    } catch (Exception e) {
	        System.out.println("Could not close alert: " + e.getMessage());
	    }
	}
}
	
	/*
	
	public String handleAlert() {
	    try {
	        WebElement alertBox = wait.until(ExpectedConditions.visibilityOfElementLocated(alertType));

	        String alertText = alertBox.getText();
	        System.out.println("Alert detected: " + alertText);

	        // Determine alert type
	        if (alertText.contains("Your rent balance is currently in credit")) {
	            closeAlert(alertBox);
	            return "credit";
	        } else if (alertText.contains("Your account is currently in debt")) {
	            closeAlert(alertBox);
	            return "debt"; 
	        } else if (alertText.contains("Your account is currently in debt") &&
	                   alertText.contains("get back on track")) {
	            closeAlert(alertBox);
	            return "debt-support";
	        } else {
	            closeAlert(alertBox);
	            return "unknown";
	        }

	    } catch (org.openqa.selenium.TimeoutException e) {
	        // No alert found
	        System.out.println("✅ No alert found after login."); 
	        return "none";
	    } catch (Exception e) {
	        System.out.println("❌ Unexpected error in handleAlert: " + e.getMessage());
	        return "error";
	    }
	}


	    public void closeAlert(WebElement alertBox) {
	       
	            WebElement closeButton = driver.findElement(closeAlertButton);
	            closeButton.click();
	            System.out.println("Alert closed.");
	        }
	    
	    public void closeIfAlertPresent() throws TimeoutException {
	    	try {
	            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeAlertButton));
	            closeButton.click();
	            System.out.println("✅ Alert found and closed.");
	        } catch (Exception e) {
	            System.out.println("❌ Error while closing alert: " + e.getMessage());
	        }
	    }
	    }

*/


