package com.myko.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginNEHA {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Load properties file
        try {
            Properties prop = new Properties();
            
            FileInputStream fis = new FileInputStream("C:\\Users\\nsehrawat\\Automation -Neha\\MyKO\\myko-test-automation\\src\\main\\resources\\test.properties");
            prop.load(fis);
            
//            Path filePath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "test.properties");
//            FileInputStream fis = new FileInputStream(filePath.toFile());
//            prop.load(fis);
 
        // Fill the form using data from file

            String custReferenceNum = prop.getProperty("custRef");
            String custPin = prop.getProperty("custPin");

        // Launch browser and open URL
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.get("https://my-hnz-st.signify.nz");
            Thread.sleep(3000);

            driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        // Fill Customer Reference Number
            driver.findElement(By.id("customReference")).sendKeys(custReferenceNum);

       // Fill PIN (character by character into password0 to password5)
            
            
       /*This starts a loop that runs once for each digit in the PIN. If the PIN is "123456", Repeat this loop 6 times (because the PIN has 6 digits) (from i = 0 to i = 5).*/            
            for (int i = 0; i < custPin.length(); i++) {  
    
       /*Take one digit at a time from the PIN. If the PIN is 123456, on the first loop, it takes 1, then 2, and so on.*/	
             char digit = custPin.charAt(i);

                               
       /*Makes the correct ID of the input box. For the first digit, it becomes password0. For the second digit, it becomes password1, and so on.*/   
             String inputId = "password" + i;
         
        
      /*Finds the box on the screen using its ID*/
              WebElement pinField = driver.findElement(By.id(inputId));
 
                
      /* Types the digit into that box */         
              pinField.sendKeys(Character.toString(digit));
                
              Thread.sleep(200);  
            }
            
            Thread.sleep(2000);

            // You can click Login if needed
             driver.findElement(By.id("loginbtnhnz")).click();
             
         Thread.sleep(5000);
         
         //Click on 'Remind me later' button in the banner
         
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement remindMeLater = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[text()='Remind me later']]")));
         remindMeLater.click();
         
         //reload the page and confirm if you can see the banner again
         driver.navigate().refresh();
         
         Thread.sleep(10000);
         
         
         // Click 'No' and banner should disappear and should not show even after refreshing the page/re-loading the page
         
         WebElement clickNO = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[text()='NO']]"))); 
         clickNO.click();
         
         Thread.sleep(3000);
         
         
         //Logout and login again and the banner should appear this time - click on 'yes' button ad form should load , fill and submit the form
                  
         
         // logout and login with another customer 
         
         
         //Banner should load , click on No button, confirm banner is disappeared 
         
         //logout and login again, banner should load, click on No button and banner should disappear 
         
         //logout and login again, repeat same scenario 
         
         //logout and login , Banner should not appear. 
         
         


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
