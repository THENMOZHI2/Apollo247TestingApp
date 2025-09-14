package com.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.setup.Reporter;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;  // ✅ Add ExtentTest reference

    // ✅ Constructor accepts driver + ExtentTest
    public PaymentPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ✅ Third Proceed Button
    public void clickThirdProceed() {
        try {
            By thirdProceedBtn = By.xpath("(//button[@aria-label='Button']//span[normalize-space()='Proceed'])[1]/parent::button");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(thirdProceedBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

            Reporter.generateReport(driver, extTest, Status.PASS, "✅ Clicked Third Proceed button successfully");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Failed to click Third Proceed button: " + e.getMessage());
            throw new AssertionError("Third Proceed button click failed", e);
        }
    }

    // ✅ Fourth Proceed Button
    public void clickFourthProceed() {
        try {
            By fourthProceedBtn = By.xpath("//button[contains(@class,'PZ') and @aria-label='Button']//span[normalize-space()='Proceed']/parent::button");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(fourthProceedBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

            Reporter.generateReport(driver, extTest, Status.PASS, "✅ Clicked Fourth Proceed button successfully");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Failed to click Fourth Proceed button: " + e.getMessage());
            throw new AssertionError("Fourth Proceed button click failed", e);
        }
    }

    // ✅ Validate Payments Tab
    public void validatePaymentsTab() {
        try {
            By paymentsTab = By.xpath("//div[contains(@class,'Juspay_title__') and normalize-space(text())='Payments']");

            // Wait longer and retry with JS
            WebElement tab = new WebDriverWait(driver, Duration.ofSeconds(200))
                    .until(ExpectedConditions.presenceOfElementLocated(paymentsTab));

            // Use JS to scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);

            // Double check visibility using JS
            boolean isVisible = (boolean) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].offsetParent !== null;", tab);

            if (isVisible && tab.isDisplayed()) {
                Reporter.generateReport(driver, extTest, Status.PASS, "✅ Payments tab is visible");
            } else {
                throw new Exception("Payments tab found but not visible on screen");
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL,
                    "❌ Payments tab not found/visible: " + e.getMessage());

            // Force failure to stop test
            throw new AssertionError("Payments tab validation failed", e);
        }
    }

}

