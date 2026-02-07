package com.asianpaints.base;

import java.util.List;
import java.util.*;
	
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	 
	 
	 protected WebDriver driver;
	 
	 protected void closeCookiePopupIfPresent() {
	        List<WebElement> buttons =
	                driver.findElements(By.id("onetrust-accept-btn-handler"));
	 
	        if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
	            WebDriverWait wait =
	                    new WebDriverWait(driver, Duration.ofSeconds(5));
	 
	            wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0)))
	                .click();
	        }
	    }
	 
	 
		@BeforeMethod
		  public void beforeMethod() {
			  System.out.println("This is Before Method");
				
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		  }
	 
		  @AfterMethod
		  public void afterMethod() {
			  System.out.println("This is After Method");
		  driver.quit();
	 

}
}

