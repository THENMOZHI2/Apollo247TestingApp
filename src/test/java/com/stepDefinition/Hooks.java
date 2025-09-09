package com.stepDefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.setup.Base;
import com.pages.BuyMedicinePage;
import com.pages.LoginPage;


import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks extends Base {

    static ExtentSparkReporter spark;
    static ExtentReports extReports;
    public static ExtentTest extTest;

    public static BuyMedicinePage buymedicinePage;  // ✅ Add this
    public static LoginPage loginPage;             // ✅ Add if needed
// ✅ Add if needed

    public static int currentrow = 0;

    @BeforeAll
    public static void beforeAll() {
        spark = new ExtentSparkReporter("ExtentReport.html");
        extReports = new ExtentReports();
        extReports.attachReporter(spark);

        // Start browser only once
        launchBrowser();
    }

    @AfterAll
    public static void afterAll() {
        extReports.flush();
    }

    @Before()
    public void setUp(Scenario scenario) {
        extTest = extReports.createTest(scenario.getName());

        // ✅ Initialize page objects here with driver + extTest
        buymedicinePage = new BuyMedicinePage(driver, extTest);
        loginPage = new LoginPage(driver, extTest);
        
    }

    @After
    public void tearDown() {
        currentrow++;
    }
    // ✅ Add this method at the end of the class
    @AfterStep
    public void closePopupIfPresent() {
        List<WebElement> overlays = driver.findElements(By.cssSelector("div.ProfileNew_modalBackground__tCWPu"));
        if (!overlays.isEmpty()) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div.ProfileNew_icon-ic_cross__itnw_")
                ));
                closeBtn.click();
                // Wait for the overlay to disappear
                wait.until(ExpectedConditions.invisibilityOf(overlays.get(0)));
                extTest.info("Closed login modal popup after step");
            } catch (Exception e) {
                extTest.warning("Popup close failed or already closed");
            }
        }
    }

}


