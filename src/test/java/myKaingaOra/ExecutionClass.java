package myKaingaOra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.myko.practice.Customerlogin;
import com.myko.practice.AlertABC;
import com.myko.practice.Banner;
import com.myko.practice.MyKOLoginPage;
import com.myko.practice.WellbeingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

//This is the main execution class containing setup and test logic for all other classes

public class ExecutionClass {

	WebDriver driver;

	// Declare page object references
	MyKOLoginPage Loginandregister;
	Customerlogin custLogin;
	AlertABC alerts;
	Banner homebanner;
	WellbeingPage wellbeingPage;

	// Launch browser and open URL
	@BeforeTest
	public void setUp() throws InterruptedException, TimeoutException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://my-hnz-st.signify.nz");
		Thread.sleep(3000);

		// Initialize page objects with driver
		Loginandregister = new MyKOLoginPage(driver);
		custLogin = new Customerlogin(driver);
		alerts = new AlertABC(driver);
		homebanner = new Banner(driver);
		wellbeingPage = new WellbeingPage(driver);

	}

	// Test 1: Customer can click on login button from landing page
	// Test 2: Customer can enter "Customer Ref number/ Pin" and click on login
	// button

	@Test(priority = 1)

	public void loginToApp() throws InterruptedException, TimeoutException {

	    Loginandregister.clickLogin();

	    Thread.sleep(3000);

	    custLogin.customerNumber();

	    custLogin.enterPin();

	    custLogin.clickLogin1();

	    Thread.sleep(15000);
	 
	    // Test alerts

	    String alertType = alerts.handleAlert();
	 
	    // Define expected alerts

	    Map<String, String> expectedAlerts = new HashMap<>();

	    expectedAlerts.put("AlertA", "Your account is currently in debt and we can help you get back on track");

	    expectedAlerts.put("AlertB", "It looks like you have a maintenance arrangement while owing rent.Please contact us so we can update your arrangement");

	    expectedAlerts.put("AlertC", "Your rent balance is currently in credit. To discuss what this credit means, please credit us");

	    expectedAlerts.put("AlertD", "Would you like to make an arrangement to pay this off? If you need support, please contact us.");

	    expectedAlerts.put("AlertE", "Rent balance is zero");

	    expectedAlerts.put("None", "none");
	 
	    // Assertion using HashMap

	    Assert.assertTrue(expectedAlerts.containsValue(alertType), "Unexpected alert type noticed: " + alertType);
	 
	    // Optional: Print alert key for clarity

	    expectedAlerts.forEach((key, value) -> {

	        if (value.equals(alertType)) {

	            System.out.println("alert detected: " + key + " → " + value);

	        }

	    });
	 
	    // Test 1: Confirm if banner is visible or not for the logged in customer
	 
	    // Test 2: if visible then, Click on 'Remind me button' and banner should disappear

	    homebanner.BannerRML();
	 
	    // Test 3: Upon Refreshing the page, Banner should appear again

	    homebanner.refresh();

	    Thread.sleep(10000);
	 
	    // Test 4: Click on 'NO' button and banner should disappear and should not

	    // appearing even after refreshing the page

	    homebanner.clickNoButton();

	    Thread.sleep(10000);

	    homebanner.logout();

	}

	 

	// Test 5: Now, Logout and login again and the banner should appear again
	// Test 6: Now, Click on 'YES' button and make a submission

	@Test(enabled=false)
	public void yesButtonAndSubmitSurvey() throws InterruptedException, TimeoutException {

		driver.get("https://my-hnz-st.signify.nz");
		Thread.sleep(6000);

		Loginandregister.clickLogin();
		Thread.sleep(15000);

		// Login again
		custLogin.customerNumber();
		custLogin.enterPin();
		custLogin.clickLogin1();
		Thread.sleep(15000); // wait for home again

		// if there is an alert, close it
		try {
			// Try to handle alert if present
			alerts = new AlertABC(driver);
			alerts.closeAlert();
		} catch (NoAlertPresentException e) {
			// No alert present, continue execution
			System.out.println("No alert present, continuing...");
		} catch (Exception e) {
			// Handle any unexpected exceptions
			System.out.println("Unexpected error while handling alert: " + e.getMessage());
			e.printStackTrace();
		}

		try {
			// Submit the survey via home banner

			homebanner.firstForm();
			Thread.sleep(2000);
			homebanner.secondForm();
			Thread.sleep(2000);
			homebanner.thirdForm();
			Thread.sleep(2000);
			homebanner.forthForm();
			Thread.sleep(2000);
			homebanner.fifthForm();
			Thread.sleep(2000);
			homebanner.sixthForm();
			Thread.sleep(2000);
			homebanner.seventhForm();

			// Verify thank-you message
			String expvalue = "Close";
			String actvalue = homebanner.thankyouMessage();
			Assert.assertEquals(expvalue, actvalue);
			Thread.sleep(2000);

			homebanner.closebutton();

			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted: " + e.getMessage());
			Thread.currentThread().interrupt(); // Good practice
		} catch (Exception e) {
			System.out.println("Error while submitting the form: " + e.getMessage());
			e.printStackTrace();
		}
		Thread.sleep(15000);
		homebanner.logout();

	}

	@Test(enabled = false)
	public void noButtonAndSubmitSurvey() throws InterruptedException, TimeoutException {

		Thread.sleep(15000);

		driver.get("https://my-hnz-st.signify.nz");
		Thread.sleep(6000);

		Loginandregister.clickLogin();
		Thread.sleep(15000);

		// Login again
		custLogin.customerNumber();
		custLogin.enterPin();
		custLogin.clickLogin1();
		Thread.sleep(15000); // wait for home again

		// Re-initialize page objects // Loginandregister = new
		// new MyKOLoginPage(driver); // custLogin = new Customerlogin(driver); //
		// alerts =
		// AlertABC(driver); // homebanner = new Banner(driver);

		// if there is an alert, close it alerts = new AlertABC(driver);
		alerts.closeAlert();

		Thread.sleep(2000);
		// Submit the survey
		homebanner.firstForm1();
		Thread.sleep(2000);

		homebanner.seventhForm();
		Thread.sleep(5000);

		String expvalue = "Close";
		String actvalue = homebanner.thankyouMessage();
		Assert.assertEquals(expvalue, actvalue);
		Thread.sleep(2000);

		homebanner.closebutton();
	}

	@Test(enabled = false)
	public void wellBeing() throws InterruptedException, TimeoutException {
		Thread.sleep(3000);

		// Click on Wellbeing in the left menu
		wellbeingPage.WellbeingMenuItem();

		// Wait and assert URL changed
		Assert.assertTrue(wellbeingPage.WellbeingPageLoad(), "Wellbeing URL did not load correctly.");
		System.out.println("Wellbeing page loaded successfully.");

		// Fetch and print h3 & h4 headings
		List<String> h3s = wellbeingPage.GetH3Headings();
		List<String> h4s = wellbeingPage.GetH4Headings();

		System.out.println("H3 Headings:");
		for (int i = 0; i < h3s.size(); i++) {
			System.out.println(h3s.get(i));
		}

		System.out.println("H4 Headings:");
		for (int i = 0; i < h4s.size(); i++) {
			System.out.println(h4s.get(i));
		}

		// Click each "View more" button and print new URLs
		wellbeingPage.clickEachViewMoreAndCheckUrl();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
