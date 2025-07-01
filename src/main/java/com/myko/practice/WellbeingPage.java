package com.myko.practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WellbeingPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//Find elements 
	
	By wellbeingMenu = By.xpath("//span[normalize-space()='Wellbeing']");
    By allH3Headings = By.tagName("h3"); 
    By allH4Headings = By.tagName("h4");
    By ViewMore = By.xpath("//button[normalize-space()='View more']");
   
    
    //Constructor 
    
    public WellbeingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    //Find 'Wellbeing' from left menu nav and click on it
    public void WellbeingMenuItem () {
    	wait.until(ExpectedConditions.elementToBeClickable(wellbeingMenu)).click();
    }
    
    //confirm if page get loaded or not by URL (true or false)
    public boolean WellbeingPageLoad() {
        System.out.println("Current URL: " + driver.getCurrentUrl());
        return wait.until(ExpectedConditions.urlContains("#/wellbeing"));
    }
    
    //get all H3 headings from Wellbeing page, there are multiple headings so we will use list array here 
    public  List<String> GetH3Headings() {
    	
    	/* wait.until(ExpectedConditions.presenceOfElementLocated(h3Headings));
         return driver.findElements(h3Headings).stream()
                      .map(WebElement::getText)
                      .collect(Collectors.toList()); */
    	
    	
    	/*for (WebElement h3 : h3Elements) {
    	    h3Texts.add(h3.getText()); 
    	}*/
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(allH3Headings));
    	
    	List<String> h3Texts = new ArrayList<>();  // Create an empty list to store texts
    	
        List<WebElement> h3Headings = driver.findElements(allH3Headings);  // Find all <h4> elements
        
        
        

        for (int i = 0; i < h3Headings.size(); i++) {
            WebElement h3 = h3Headings.get(i); // Get each <h3> element one by one
            String text = h3.getText();        // Get the text from that element
            h3Texts.add(text);                 // Add the text to our list
        }

        return h3Texts;  // Return the list of <h4> texts
    }
    
    
    public List<String> GetH4Headings(){
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(allH4Headings));
    	
    	List<String> h4Text = new ArrayList<>();
    	
    	List<WebElement> h4Headings = driver.findElements(allH4Headings);
    	
    	for(int i=0; i < h4Headings.size(); i++) {
    		WebElement h4 = h4Headings.get(i);
    		String text = h4.getText();
    		h4Text.add(text);
    	}
		return h4Text;
    	
    }
    
    
    public void clickEachViewMoreAndCheckUrl() throws InterruptedException {
        // Get all "View more" buttons
        List<WebElement> viewMoreButtons = driver.findElements(ViewMore);

        String originalUrl = driver.getCurrentUrl(); // This should be something like /#/wellbeing/Healthy%20Homes

        for (int i = 0; i < viewMoreButtons.size(); i++) {
            // Refetch the buttons every time (because DOM refreshes)
            viewMoreButtons = driver.findElements(ViewMore);

            WebElement button = viewMoreButtons.get(i);
            
            // Wait until the button is clickable
            wait.until(ExpectedConditions.elementToBeClickable(button));
           
            button.click(); // click on the button

         // Wait for the URL to change from original
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));

            String newUrl = driver.getCurrentUrl();

            if (!newUrl.equals(originalUrl)) {
                System.out.println("✅ URL changed after clicking button " + (i + 1) + ": " + newUrl);
            } else {
                System.out.println("❌ URL did NOT change after clicking button " + (i + 1));
            }

            // Go back to the original page
            driver.navigate().back();

            // Wait for page to reload
            Thread.sleep(2000);
        }
    }

}



