package com.asianpaints.test;

import io.appium.java_client.android.AndroidDriver;
import com.asianpaints.util.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;
import java.util.List;
public class NewTest {
	
	   ExtentReports extent=exmanager.getreport();
	   
    @Test
 
    public void appium() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:udid", "emulator-5554");
        caps.setCapability("appium:deviceName", "Android Emulator");
        caps.setCapability("appium:autoGrantPermissions", true);
        caps.setCapability("appium:noReset", true);
        caps.setCapability("appium:appPackage", "com.google.android.youtube");
        caps.setCapability("appium:appActivity", "com.google.android.apps.youtube.app.WatchWhileActivity");
        caps.setCapability("appium:appWaitActivity", "com.google.android.apps.youtube.app.*");
        
       
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        ExtentTest test = extent.createTest("Appium youtube application ");
        
        test.info("Youtube started");
        
        System.out.println("YouTube App started");
        System.out.println("Current Activity: " + driver.currentActivity());
        test.info("Clicked on Youtube");
        
        By searchXpath = By.xpath("//android.view.ViewGroup[@content-desc=\"Search YouTube\"]");
        test.info("Search Button Found");
        boolean found = false;
        WebElement searchEl = null;
        long end = System.currentTimeMillis() + 40000; // 40 sec
        while (System.currentTimeMillis() < end) {
            List<WebElement> list = driver.findElements(searchXpath);
            if (list.size() > 0) {
                searchEl = list.get(0);
                found = true;
                break;
            }
            Thread.sleep(10000);
        }
        if (found) {
            System.out.println(" Search element IS PRESENT");
            System.out.println("Displayed: " + searchEl.isDisplayed());
            
        } else {
            System.out.println(" Search element NOT PRESENT after waiting");
            System.out.println("Current Activity: " + driver.currentActivity());
            
        }
        Thread.sleep(2000);
        extent.flush();
        String ss=screenshot.capturescreenshot(driver);
	  	test.pass("Test passed").addScreenCaptureFromPath(ss);
	  		extent.flush();
    }
}