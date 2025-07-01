package myKaingaOra;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.myko.practice.AlertABC;
import com.myko.practice.Banner;
import com.myko.practice.Customerlogin;
import com.myko.practice.MyKOLoginPage;
import com.myko.practice.WellbeingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;

	
	//Launch browser and open URL 
	@BeforeMethod
	public void setUp() throws InterruptedException, TimeoutException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://my-hnz-st.signify.nz");
		Thread.sleep(3000);

		// Initialize page objects with driver
		MyKOLoginPage Loginandregister = new MyKOLoginPage(driver);
		Customerlogin custLogin = new Customerlogin(driver);
		AlertABC alerts = new AlertABC(driver);
		Banner homebanner = new Banner(driver);
		WellbeingPage wellbeingPage = new WellbeingPage(driver);

	}

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}



	
	
