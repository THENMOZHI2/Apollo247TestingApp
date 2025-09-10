package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectRepository.Locators;
import com.setup.Reporter;

public class ViewCartPage {
    WebDriver driver;
    WebDriverWait wait;
    ExtentTest extTest;

    public ViewCartPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ✅ Validate My Cart Page
    public boolean validateMyCartPage() {
        try {
        	WebElement cartTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.myCartPageTitle));
            Reporter.generateReport(driver, extTest, Status.PASS, "My Cart page is visible");
            return  cartTitle.isDisplayed();
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "My Cart page is not visible");
            return false;
        }
    }

    // ✅ Click Change Address
    public void clickChangeAddress() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.changeAddressBtn)).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Change Address button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Change Address button");
            Assert.fail("Change Address button not clickable");
        }
    }

    
    // ✅ Click Add New Address
    public void clickAddNewAddress() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.addNewAddressBtn)).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Add New Address button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Add New Address button");
            Assert.fail("Add New Address button not clickable");
        }
    }
    public boolean validateDeliverToTab() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.deliverToHeader));
            Reporter.generateReport(driver, extTest, Status.PASS, "Deliver To tab is visible");
            return driver.findElement(Locators.deliverToHeader).isDisplayed();
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Deliver To tab is not visible");
            return false;
        }
    }
}

