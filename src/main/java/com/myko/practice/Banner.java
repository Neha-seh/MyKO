package com.myko.practice;

import java.io.OutputStream;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Banner {

	WebDriver driver;
	WebDriverWait wait;

	By bannerExist = By.xpath("//p[contains(text(), 'Would you like to provide feedback on the MyKāingaOra portal')]");
	By remindMe = By.xpath("//button[.//span[text()='Remind me later']]");
	By noButton = By.xpath("//button[.//span[text()='No']]");
	By logoutBtn = By.xpath("//button[.//span[text()='Logout']]");
	By yesButton = By.xpath("//button[.//span[text()='Yes']]");
	By optionYes = By.xpath("//input[@value='Yes']");
	By optionNo = By.xpath("//input[@value='No']");
	By next1 = By.xpath("//button[text()='Next']");
	By maintenanceReason = By.xpath("//input[@type='radio' and @value='To log a new maintenance request']");
	By easyness = By.xpath("//input[@type='radio' and @value='difficult']");
	By reasonTextarea = By.xpath("//textarea[@name='data[Reason_Easiness_of_action]']");
	By smiley = By.xpath("//input[@type='radio' and @value='Satisfied']");
	By optionReasonTextarea = By.xpath("//textarea[@name='data[Reason_Experience_of_portal]']");
	By improveCheckBox = By.xpath("//input[@value='To live chat with the Customer Service Centre']");
	By improveCheckBox2 = By.xpath("//input[@type='checkbox' and @value='Something else']");
	By improveTextField = By.xpath("//textarea[@name='data[Other_improvement_suggestion]']");
	By submitForm = By.xpath("//button[text()='Submit']");
	By CloseButton = By.xpath("//button[span[text()='Close']]");

	public Banner(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	//// TEST 1: Verify if there is banner appear on the home page if 'yes' - see
	//// next test cases
	public boolean isBannerVisible(WebDriver driver) {
		return driver.findElements(bannerExist).size() > 0;
	}

	// Test 2: Click on 'Remind me later' button in the banner
	public void BannerRML() throws InterruptedException {
		driver.findElement(remindMe).click();
		System.out.println("Remind me button got clicked");
		Thread.sleep(3000);
	}

	// TEST 3: Banner should re-appear again if page is refreshed
	public void refresh() {
		driver.navigate().refresh();
	}

	// Test 4: 'No' and banner should disappear and should not show even after
	// refreshing the page/re-loading the page

	public void clickNoButton() throws InterruptedException {
		driver.findElement(noButton).click();
		System.out.println("NO button got clicked");
		Thread.sleep(3000);
		// Banner is not visibile
		driver.navigate().refresh();
		// banner is still not visible
	}

	// Test 5: Logout and login again and the banner should appear this time - click
	// on 'yes' button ad form should load , fill and submit the form

	public void logout() {
		driver.findElement(logoutBtn).click();
		System.out.println("Customer has logged out and will login again");
	}

	// Scenario 1
	// First pop-up, select YES and click on NEXT
	public void firstForm() throws InterruptedException {
		driver.findElement(yesButton).click();
		System.out.println("Yes button got clicked");
		Thread.sleep(2000);

		// select option 'yes'

		driver.findElement(optionYes).click();
		System.out.println("YES got clicked on 1/7 page for the Survey form");
		Thread.sleep(2000);

		// select option 'next'
		driver.findElement(next1).click();
		Thread.sleep(2000);
	}

	// Second pop-up, select 'to log a new maintenance request'
	public void secondForm() throws InterruptedException {
		driver.findElement(maintenanceReason).click();
		System.out.println("an option got selected on 2/7 page");
		Thread.sleep(3000);

		driver.findElement(next1).click();
		Thread.sleep(2000);
	}

	// Third pop-up, select 'difficult'
	public void thirdForm() throws InterruptedException {
		driver.findElement(easyness).click();
		System.out.println("an option got selected on 3/7 page");
		Thread.sleep(3000);

		driver.findElement(next1).click();
		Thread.sleep(2000);
	}

	// Forth pop-up, Tell us why you have selected that option
	public void forthForm() throws InterruptedException {
		driver.findElement(reasonTextarea).sendKeys("adding this test text in the text field 4/7");
		System.out.println("Test content add on the 4/7 page");
		Thread.sleep(2000);

		driver.findElement(next1).click();
		Thread.sleep(2000);

	}

	// Fifth pop-up, kaingaora experience by selecting smileys
	public void fifthForm() throws InterruptedException {
		driver.findElement(smiley).click();
		System.out.println("A smiley got selected on 5/7 page");
		Thread.sleep(2000);

		driver.findElement(next1).click();
		Thread.sleep(2000);

	}

	// Sixth pop-up, tell us why have you selected that option?
	public void sixthForm() throws InterruptedException {
		driver.findElement(optionReasonTextarea)
				.sendKeys("filling test text in 'tell us why have you selected that option' text field");
		System.out.println("Test content add on the 6/7 page");
		Thread.sleep(2000);

		driver.findElement(next1).click();
		Thread.sleep(2000);

	}

	// Seventh pop-up,select multiple option, What would you like to see improved or
	// added to MyKāingaOra?
	public void seventhForm() throws InterruptedException {
		driver.findElement(improveCheckBox).click();
		Thread.sleep(2000);

		driver.findElement(improveCheckBox2).click();
		Thread.sleep(3000);

		driver.findElement(improveTextField).sendKeys("adding some test text in here ");
		Thread.sleep(2000);

		driver.findElement(submitForm).click();
		Thread.sleep(5000);

		System.out.println("Two multicheck option got selected and test content added to the textfield on 7/7 page, and submitted the form");
	}

	public String thankyouMessage() {

		return driver.findElement(CloseButton).getText();
		

	}

	public void closebutton() throws InterruptedException {
		Thread.sleep(2000);

		driver.findElement(CloseButton).click();

	}

	// Scenario 2
	// a method is already written above for this test
	// First pop-up, select YES and click on NEXT
//	public void firstForm() throws InterruptedException {
//		driver.findElement(yesButton).click();
//		Thread.sleep(2000);

	// First pop-up, select YES and click on NEXT
	public void firstForm1() throws InterruptedException {
		driver.findElement(yesButton).click();
		Thread.sleep(2000);

		// select option 'yes'

		driver.findElement(optionNo).click();
		System.out.println("NO got clicked on 1/7 page for the Survey form");
		Thread.sleep(2000);

		// select option 'yes'
		driver.findElement(next1).click();
		Thread.sleep(2000);
		
		
	}
	// Repeat seventhForm method again and submit

}

// Test 6: logout and login with another customer

// Test 7 :Banner should load , click on No button, confirm banner is
// disappeared

// logout and login again, banner should load, click on No button and banner
// should disappear

// logout and login again, repeat same scenario

// logout and login , Banner should not appear.

//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		 
//	     WebElement remindMeLater = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[text()='Remind me later']]")));
//		 remindMeLater.click();	    
