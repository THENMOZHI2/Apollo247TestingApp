package com.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectRepository.Locators;
import com.setup.Reporter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PaymentPage {
	 WebDriver driver;
	    WebDriverWait wait;
	    ExtentTest extTest; // Add this
	    // 16. Payments tab title validation
	    public static By paymentsTab = By.xpath("//div[text()='Payments']");

	    public PaymentPage(WebDriver driver, ExtentTest extTest) {
	        this.driver = driver;
	        this.extTest = extTest;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }

	    public void validatePaymentsTab() {
	        try {
	            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentsTab)).isDisplayed();
	            Reporter.generateReport(driver, extTest, Status.PASS, "Payments tab is visible after checkout");
	            Assert.assertTrue(visible, "❌ Payments tab is not visible after checkout.");
	        } catch (Exception e) {
	            Reporter.generateReport(driver, extTest, Status.FAIL, "Error validating Payments tab: " + e.getMessage());
	            Assert.fail("❌ Exception while validating Payments tab: " + e.getMessage());
	        }
	    }

}
