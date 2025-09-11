package com.pages;

import com.objectRepository.Locators;
import com.parameters.ExcelReader;
import com.setup.Reporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddressDetails {

    WebDriver driver;
    WebDriverWait wait;
    String[][] excelData;
    ExtentTest extTest;

    public AddressDetails(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.excelData = ExcelReader.readdata();
    }

    // Reusable scroll and click
    private void scrollAndClick(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void enterPincodeAndSelect() {
        try {
            String pincode = excelData[0][1];
            WebElement pinBox = wait.until(ExpectedConditions.elementToBeClickable(Locators.pincodeSearchBox));
            pinBox.clear();
            pinBox.sendKeys(pincode);

            List<WebElement> options = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'NewSearchLocationSuggestor_searchItemList')]")));

            if (options.size() >= 3) {
                scrollAndClick(options.get(2));
                Reporter.generateReport(driver, extTest, Status.PASS, "Entered pincode and selected 3rd option");
            } else {
                Reporter.generateReport(driver, extTest, Status.FAIL, "Less than 3 options found for pincode: " + pincode);
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter/select pincode: " + e.getMessage());
        }
    }

    public void enterHouseNumberAndSaveNext(String houseNumber) {
        try {
            WebElement houseField = wait.until(ExpectedConditions.elementToBeClickable(Locators.houseNumberField));
            houseField.clear();
            houseField.sendKeys(houseNumber);

            WebElement saveNextBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.saveAndNextBtn));
            scrollAndClick(saveNextBtn);

            Reporter.generateReport(driver, extTest, Status.PASS, "Entered house number and clicked Save & Next");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter house number: " + e.getMessage());
        }
    }
 // Fill recipient details and save address
    public void fillRecipientDetailsAndSave(String recipientName) {
        try {
            // Click 'Someone else' radio button
            WebElement someoneElse = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.someoneElseRadio));
            scrollIntoView(someoneElse);
            safeClick(someoneElse);

            // Click 'Home' button
            WebElement home = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.homeButton));
            scrollIntoView(home);
            safeClick(home);

            // Fill recipient name
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.recipientNameField));
            nameField.clear();
            nameField.sendKeys(recipientName);

            // Click 'Save Address' button
            WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.saveAddressBtn));
            scrollIntoView(saveBtn);
            safeClick(saveBtn);

            // Wait until Save button disappears (confirmation handled)
            wait.until(ExpectedConditions.invisibilityOf(saveBtn));

            Reporter.generateReport(driver, extTest, Status.PASS,
                    "Recipient details filled and address saved successfully");

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL,
                    "Failed to fill recipient details: " + e.getMessage());
            throw e; // ensures test fails if anything goes wrong
        }
    }

    /**
     * Helper to click element safely:
     * 1. Try normal Selenium click
     * 2. If fails, fallback to JavaScript click
     */
    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // Reusable scroll only
    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void clickFirstProceed() {
        try {
            WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(Locators.firstProceedBtn));
            scrollAndClick(proceed);
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked first Proceed button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click first Proceed button: " + e.getMessage());
        }
    }

    // Click second proceed button
    public void clickSecondProceed() {
        try {
            WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(Locators.secondProceedBtn));
            scrollAndClick(proceed);
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked second Proceed button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click second Proceed button: " + e.getMessage());
        }
    }

    // Click skip savings button
    public void clickSkipSavings() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.skipSavingsBtn));

            // Scroll and JS fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", skipBtn);
            try {
                skipBtn.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", skipBtn);
            }

            // ✅ Wait for Upload Prescription tab after click
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.uploadPrescriptionHeader ));

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Skip Savings and navigated to Upload Prescription");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Skip Savings - " + e.getMessage());
            throw e; // rethrow so Cucumber marks this step as failed
        }
    }


    // Validate Upload Prescription tab
    public void validateUploadPrescriptionTab() {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.uploadPrescriptionHeader));
            if (header.isDisplayed()) {
                Reporter.generateReport(driver, extTest, Status.PASS, "Upload Prescription tab is displayed");
            } else {
                Reporter.generateReport(driver, extTest, Status.FAIL, "Upload Prescription tab is not displayed");
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to validate Upload Prescription tab: " + e.getMessage());
        }
    }

   
}

