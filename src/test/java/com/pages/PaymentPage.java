package com.pages;

import com.aventstack.extentreports.ExtentTest;
import com.objectRepository.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
	 WebDriver driver;
	    WebDriverWait wait;
	    ExtentTest extTest; // Add this

	    public PaymentPage(WebDriver driver, ExtentTest extTest) {
	        this.driver = driver;
	        this.extTest = extTest;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }

    public boolean isPaymentTabDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.paymentsTab)).isDisplayed();
  }
}
