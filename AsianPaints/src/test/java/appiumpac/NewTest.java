package appiumpac;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import com.aventstack.extentreports.*;
import com.asianpaints.util.exmanager;      // your singleton report manager
import com.asianpaints.util.screenshot;    // your screenshot util

public class NewTest {

    private AndroidDriver driver;
    private ExtentReports extent;   // <-- class field (shared)
    private ExtentTest test;        // <-- class field (per method)

    // --- Report init: grab the shared ExtentReports instance once for this class
    @BeforeClass(alwaysRun = true)
    public void initReportForThisClass() {
        extent = exmanager.getreport();         // DO NOT shadow with 'ExtentReports extent = ...'
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is null. Ensure exmanager.getreport() returns a singleton.");
        }
    }

    // --- Create an ExtentTest node per test method
    @BeforeMethod(alwaysRun = true)
    public void startTestNode(Method m) {
        test = extent.createTest("MOBILE :: " + m.getName())
                     .assignCategory("Mobile")
                     .assignDevice("Android Emulator");
    }

    // --- Quit driver + final status logging
    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test passed");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                test.fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("Test skipped: " + result.getThrowable());
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
        // DO NOT call extent.flush() here. Flush once per suite in your Selenium class / ReportInit.
    }

    // --- Your Appium test
    @Test
    public void f() throws Exception {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");
            options.setDeviceName("Android Emulator");
            options.setUdid("emulator-5554");

            // ⚠ Optional: only set if you are 100% sure. Mismatch causes session failures.
            // options.setPlatformVersion("14"); // or comment out entirely

            options.setAutoGrantPermissions(true);
            options.setNoReset(true);

            // YouTube app
            options.setAppPackage("com.google.android.youtube");
            options.setAppActivity("com.google.android.apps.youtube.app.WatchWhileActivity");
            options.setAppWaitActivity("com.google.android.apps.youtube.app.*");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            test.info("YouTube app started");
            test.info("Current activity: " + driver.currentActivity());
            test.addScreenCaptureFromPath(screenshot.capturescreenshot(driver));

            // --- Permission popup (if shown)
            By allowBtnLocator = By.id("com.android.permissioncontroller:id/permission_allow_button");
            List<WebElement> allowButtons = driver.findElements(allowBtnLocator);

            if (!allowButtons.isEmpty() && allowButtons.get(0).isDisplayed()) {
                allowButtons.get(0).click();
                test.info("Permission popup shown → clicked Allow");
                test.addScreenCaptureFromPath(screenshot.capturescreenshot(driver));
            } else {
                test.info("Permission popup not shown");
            }

            // --- Verify "Home" tab is visible
            WebElement home = driver.findElement(AppiumBy.accessibilityId("Home"));
            if (home.isDisplayed()) {
                test.pass("Home is displayed");
                test.addScreenCaptureFromPath(screenshot.capturescreenshot(driver));
            } else {
                test.fail("Home is NOT displayed");
                test.addScreenCaptureFromPath(screenshot.capturescreenshot(driver));
            }

        } catch (Exception e) {
            // Best-effort failure screenshot
            try {
                if (driver != null) {
                    test.addScreenCaptureFromPath(screenshot.capturescreenshot(driver));
                }
            } catch (Exception ignore) {}
            // Let @AfterMethod log final status via ITestResult, but also add a message here:
            test.fail("Exception in test: " + e.getMessage());
            throw e; // rethrow so TestNG marks it as FAIL
        }
    }
}
