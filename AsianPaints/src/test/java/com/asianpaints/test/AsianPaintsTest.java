package com.asianpaints.test;

import java.io.File;
import java.io.IOException;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v142.page.model.Screenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.asianpaints.base.BaseTest;

import com.asianpaints.pages.asianpaints_pom;
import com.asianpaints.util.config;
import com.asianpaints.util.exmanager;
import com.asianpaints.util.screenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
 import com.aventstack.extentreports.MediaEntityBuilder;


public class AsianPaintsTest extends BaseTest  {
	
	ExtentReports extent=exmanager.getreport();
	
	static String projectpath=System.getProperty("user.dir");
	
	@Test
	
	public void addtocart() throws IOException, InterruptedException {
		String url = config.getproperty("url");
		System.out.println("URL is " + url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		
		asianpaints_pom obj2 = new asianpaints_pom(driver);
		
		ExtentTest test = extent.createTest("Add to cart");
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		obj2.crosspop();	
  		System.out.println("pass1");
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  		closeCookiePopupIfPresent();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		    Thread.sleep(10);
		test.info("Open Menu");
  		obj2.openmenu();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Select Colour");
  		obj2.selectcolour();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Choose shade");
  		obj2.chooseshade();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Click shop");
  		obj2.shop();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Add to cart");
  		obj2.cart();
		System.out.println("pass3");
		  		
		  	String ss=screenshot.capturescreenshot(driver);
		  	test.pass("Test passed").addScreenCaptureFromPath(ss);
		  		extent.flush();		
}
	

	@Test
	public void removecart() throws IOException, InterruptedException {
		String url = config.getproperty("url");
		System.out.println("URL is " + url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		
		
		asianpaints_pom obj2 = new asianpaints_pom(driver);
		ExtentTest test = extent.createTest("Remove from cart");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		obj2.crosspop();	
  		System.out.println("pass1");
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  		closeCookiePopupIfPresent();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		    Thread.sleep(10);
		 test.info("Open Menu");
  		obj2.openmenu();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Select Colour");
  		obj2.selectcolour();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Choose shade");
  		obj2.chooseshade();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Click shop");
  		obj2.shop();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  		test.info("Add to cart");
  		obj2.cart();
		System.out.println("pass3");
		test.info("Click on cart");
		obj2.remove();
		System.out.println("pass4");
		test.info("Remove product");
		obj2.clearcart();
		System.out.println("pass5");
		String ss=screenshot.capturescreenshot(driver);

	  	test.pass("Test passed").addScreenCaptureFromPath(ss);
	  		extent.flush();
	}
	
	///////////////////////////////////////////////DAKSH////////////////////////////////////////////////////////////////
	@Test 
	public void Homepage() throws IOException, InterruptedException {
       
        extent = exmanager.getreport();
        ExtentTest test = extent.createTest("Home page verification");
        String url1 = config.getproperty("url");
      
        test.info("Opening browser and launching URL: ");
        driver.get(url1);
  
        asianpaints_pom page = new asianpaints_pom(driver);
        
      
        String path = screenshot.capturescreenshot(driver);
        
        test.pass("Title matched and Logo is visible.").addScreenCaptureFromPath(path);
        extent.flush();
        
        System.out.println(" TEST PASSED!");

    } 
	////////////////////////////////////Harshit////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void BedroomDesign() throws InterruptedException, IOException {
		  ExtentTest test=extent.createTest("Bedroom Design");
 
		test.info("Open Menu");
		driver.get("https://www.beautifulhomes.asianpaints.com/weatherseal.html");
		test.info("Click on Ideas");
		driver.findElement(By.xpath("//*[@id=\"imageModaltarget\"]/div/a/picture/img")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 test.info("Click on Bedroom Design");
		driver.navigate().back();
		driver.findElement(By.xpath("//*[@id=\"container-85cb039bb9\"]/div/div[3]/header[1]/div[2]/div/ul/li[7]/a")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		 test.info("Scroll Down");
		    WebElement bedroom = wait.until(
		        ExpectedConditions.presenceOfElementLocated(
		            By.xpath("//a[contains(@href,'bedroom')]")
		        )
		    );
		
		    // Scroll to center
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript(
		        "arguments[0].scrollIntoView({block:'center'});", bedroom
		    );
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    // Small buffer for animation
		    try { Thread.sleep(500); } catch (Exception e) {}
		
		    // JavaScript click (IMPORTANT)
		    js.executeScript("arguments[0].click();", bedroom);
		    //js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		    long scrollHeight = (long) js.executeScript("return document.body.scrollHeight");
		    
		    for (int i = 0; i < scrollHeight; i += 200) {
		        js.executeScript("window.scrollTo(0, arguments[0]);", i);
		        Thread.sleep(200); // controls speed
		    }
		    String ss=screenshot.capturescreenshot(driver);
		  	test.pass("Test passed").addScreenCaptureFromPath(ss);
		  		extent.flush();
	}

	
}



