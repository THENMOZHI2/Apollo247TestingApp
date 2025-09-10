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
import com.pages.AddressDetails;
import com.pages.BuyMedicinePage;
import com.pages.LoginPage;
import com.pages.PaymentPage;
import com.pages.PrescriptionPage;
import com.pages.ViewCartPage;

import io.cucumber.java.*;

public class Hooks extends Base {
	public static String[][] excelData; 

    static ExtentSparkReporter spark;
    static ExtentReports extReports;
    public static ExtentTest extTest;

    public static BuyMedicinePage buymedicinePage;
    public static LoginPage loginPage;
    public static ViewCartPage viewCartPage;
    public static AddressDetails addressDetails;
    public static PrescriptionPage prescriptionpage;
    public static PaymentPage paymentPage;

    public static int currentrow = 0;

	public static int firstrow;

    @BeforeAll
    public static void beforeAll() {
        spark = new ExtentSparkReporter("ExtentReport.html");
        extReports = new ExtentReports();
        extReports.attachReporter(spark);

        // ✅ Start browser only once
        launchBrowser();
    }

    @AfterAll
    public static void afterAll() {
        extReports.flush();
        if (driver != null) {
            driver.quit();   // ✅ Close browser only once at the end
        }
    }

    @Before
    public void setUp(Scenario scenario) {
        extTest = extReports.createTest(scenario.getName());

        // ✅ Initialize page objects
        buymedicinePage = new BuyMedicinePage(driver, extTest);
        loginPage = new LoginPage(driver, extTest);
        viewCartPage = new ViewCartPage(driver, extTest);
        addressDetails = new AddressDetails(driver, extTest);
        prescriptionpage = new PrescriptionPage(driver, extTest);
        paymentPage = new PaymentPage(driver, extTest);
    }

    @After
    public void tearDown(Scenario scenario) {
        currentrow++;
        if (scenario.isFailed()) {
            extTest.fail("❌ Scenario failed: " + scenario.getName());
        } else {
            extTest.pass("✅ Scenario passed: " + scenario.getName());
        }
    }

    @AfterStep
    public void closePopupIfPresent() {
        try {
            List<WebElement> overlays = driver.findElements(By.cssSelector("div.ProfileNew_modalBackground__tCWPu"));
            if (!overlays.isEmpty()) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div.ProfileNew_icon-ic_cross__itnw_")
                ));
                closeBtn.click();
                wait.until(ExpectedConditions.invisibilityOf(overlays.get(0)));
                extTest.info("Closed login modal popup after step");
            }
        } catch (org.openqa.selenium.NoSuchWindowException e) {
            extTest.warning("⚠️ Browser window already closed, skipping popup handling");
        } catch (Exception e) {
            extTest.warning("⚠️ Popup close failed: " + e.getMessage());
        }
    }
}



