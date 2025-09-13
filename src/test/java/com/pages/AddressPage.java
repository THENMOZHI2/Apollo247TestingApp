package com.pages;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.setup.Reporter;

public class AddressPage {

    public WebDriver driver;
    WebDriverWait wait;
    ExtentTest extTest;

    public AddressPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.extTest = extTest;
    }
 // Click on Change button
    public void clickChangeButton(ExtentTest test) {
        WebElement changeBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[text()='Change']")));

        try {
            changeBtn.click(); // normal click
        } catch (ElementClickInterceptedException e) {
            // fallback: JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", changeBtn);
        }

        Reporter.generateReport(driver, test, Status.INFO, "Clicked on Change button");
    }


    // Validate sidebar opens
    public void validateSidebar(ExtentTest test) {
        try {
            // Wait for the "Deliver to" heading instead of generic sidebar
            WebElement deliverToHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[normalize-space(text())='Deliver to']")
            ));

            Assert.assertTrue(deliverToHeader.isDisplayed(), "'Deliver to' tab is not visible!");
            Reporter.generateReport(driver, test, Status.PASS, "'Deliver to' tab is visible");

        } catch (Exception e) {
            Reporter.generateReport(driver, test, Status.FAIL, "Error while validating sidebar: " + e.getMessage());
            Assert.fail("Error in validateSidebar: " + e.getMessage());
        }
    }

  
    public void clickAddButton(ExtentTest test) {
        try {
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Add New Address')]")
            ));

            try {
                addBtn.click(); // normal Selenium click
            } catch (ElementClickInterceptedException e) {
                // Scroll into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);
                // Fallback: force JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
            }

            Reporter.generateReport(driver, test, Status.PASS, "Clicked on Add New Address button");
        } catch (Exception e) {
            Reporter.generateReport(driver, test, Status.FAIL, "Error while clicking Add New Address: " + e.getMessage());
            Assert.fail("Error in clickAddButton: " + e.getMessage());
        }
    }


 // Enter invalid pincode and validate no results found
 // Enter invalid pincode and validate no results found
    public void enterInvalidPincodeAndValidate(ExtentTest test) {
        try {
            WebElement pincodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search for society, locality, pincode...']")));
            pincodeInput.clear();
            pincodeInput.sendKeys("605204", Keys.ENTER); // invalid pincode

            Reporter.generateReport(driver, test, Status.INFO, "Entered invalid pincode");

            // Wait for "No Result found" message
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'NewSearchLocationSuggestor_noResults__')]")
            ));

            Assert.assertTrue(errorMsg.getText().contains("No Result found"), 
                "Expected 'No Result found' message not displayed!");

            Reporter.generateReport(driver, test, Status.PASS, 
                "Validated error: " + errorMsg.getText());

        } catch (Exception e) {
            Reporter.generateReport(driver, test, Status.FAIL, "Error in enterInvalidPincodeAndValidate: " + e.getMessage());
            Assert.fail("Error in enterInvalidPincodeAndValidate: " + e.getMessage());
        }
    }

    // Enter invalid pincode
 // Enter valid pincode from test data and select first dropdown suggestion
 // In AddressPage.java
    public void enterValidPincode(String pincode, ExtentTest test) {
        WebElement pincodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='Search for society, locality, pincode...']")));
        pincodeInput.clear();
        pincodeInput.sendKeys(pincode);

        Reporter.generateReport(driver, test, Status.INFO, "Entered valid pincode: " + pincode);

        WebElement firstSuggestion = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//div[contains(@class,'NewSearchLocationSuggestor_searchItemList__')])[1]")));
        firstSuggestion.click();

        Reporter.generateReport(driver, test, Status.PASS, "Selected first suggestion from dropdown");
    }



 // Enter single character flat number
    public void enterSingleCharFlatNumber(ExtentTest test) {
        WebElement flatInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//textarea[@name='address1']")));
        flatInput.clear();
        flatInput.sendKeys("A");

        Reporter.generateReport(driver, test, Status.INFO, "Entered single char flat number");

        // trigger validation
        clickSaveAndNext(test);
    }


    // Click Save & Next (trigger validation)
    public void clickSaveAndNext(ExtentTest test) {
        try {
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button//span[contains(text(),'Save & Next')]")
            ));

            try {
                saveBtn.click(); // try normal click
            } catch (ElementClickInterceptedException e) {
                // Scroll & force JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
            }

            Reporter.generateReport(driver, test, Status.PASS, "Clicked on Save & Next button");
        } catch (Exception e) {
            Reporter.generateReport(driver, test, Status.FAIL, "Save & Next not clickable: " + e.getMessage());
            Assert.fail("Save & Next not clickable: " + e.getMessage());
        }
    }



    // Validate minimum characters required error
    public void validateMinTwoCharError(ExtentTest test) {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[@class='NewAddressForm_errorClass__sXKwt' and contains(text(),'Minimum 2 characters')]")));
        Assert.assertTrue(error.isDisplayed(), "Error not displayed!");
        Reporter.generateReport(driver, test, Status.PASS, "Minimum two characters validation displayed");
    }

    public void validateAddressTypeRequired(ExtentTest test) {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'NewAddressForm_errorClass__') and " +
                         "(contains(text(),'select address type') or contains(text(),'Please select address type'))]")
            ));
            Assert.assertTrue(error.isDisplayed(), "Address type error not displayed!");
            Reporter.generateReport(driver, test, Status.PASS, "Address type validation displayed: " + error.getText());
        } catch (Exception e) {
            Reporter.generateReport(driver, test, Status.FAIL, "Address type error not found: " + e.getMessage());
            Assert.fail("Address type validation failed: " + e.getMessage());
        }
    }


    // If you need to click "Home"
    public void clickHomeAddressType(ExtentTest test) {
        WebElement homeBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@id='HOME' and @name='addressType']")));
        homeBtn.click();
        Reporter.generateReport(driver, test, Status.PASS, "Clicked HOME address type");
    }

    // Select Myself or Someone else
    public void selectOrderingFor(String option, ExtentTest test) {
        String id = option.equalsIgnoreCase("Myself") ? "myself" : "someone else";
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        radio.click();
        Reporter.generateReport(driver, test, Status.INFO, "Selected ordering for: " + option);
    }

    // Select address type
    public void selectAddressType(String type, ExtentTest test) {
        WebElement addressTypeBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@name='addressType' and @id='" + type.toUpperCase() + "']")));
        addressTypeBtn.click();
        Reporter.generateReport(driver, test, Status.INFO, "Selected address type: " + type);
    }
   
    public void enterRecipientName(String name, ExtentTest test) {
        WebElement recipientInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.name("recipientName")
        ));

        // Force clear properly (React-safe way)
        recipientInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        recipientInput.sendKeys(Keys.DELETE);

        recipientInput.sendKeys(name);

        Reporter.generateReport(driver, test, Status.INFO, "Entered recipient name: " + name);
    }


    // Enter phone number
    public void enterPhoneNumber(String phone, ExtentTest test) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.name("phoneNumber")));
        phoneInput.clear();
        phoneInput.sendKeys(phone);
        Reporter.generateReport(driver, test, Status.INFO, "Entered phone number: " + phone);
    }

    // Click Save Address
    public void clickSaveAddress(ExtentTest test) {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button//span[text()='Save Address']")));
        saveBtn.click();
        Reporter.generateReport(driver, test, Status.INFO, "Clicked on Save Address");
    }

    // Validate error message
    public void validateErrorMessage(String expected, ExtentTest test) {
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(@class,'NewAddressForm_errorClass__sXKwt')]")));
        String actual = errorMsg.getText().trim();
        Assert.assertEquals(actual, expected, "Validation message mismatch!");
        Reporter.generateReport(driver, test, Status.PASS, "Validation message displayed: " + actual);
    }
 // Enter valid flat number and save
    public void enterValidFlatNumberAndSave(ExtentTest test) {
        try {
            // Locate flat number textarea
            WebElement flatInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='address1']")));
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", flatInput);
            flatInput.clear();
            flatInput.sendKeys("12B");  // enter valid flat number
            
            Reporter.generateReport(driver, test, Status.INFO, "Entered valid flat number");

            // Locate Save & Next button
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button//span[contains(text(),'Save & Next')]")));
            
            try {
                saveBtn.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
            }

            Reporter.generateReport(driver, test, Status.PASS, "Clicked on Save & Next button");
        } catch (Exception e) {
            Reporter.generateReport(driver, test, Status.FAIL, "Error in enterValidFlatNumberAndSave: " + e.getMessage());
            Assert.fail("Failed in enterValidFlatNumberAndSave: " + e.getMessage());
        }
    }

    // Validate address saved
    public void validateAddressSaved(String context, ExtentTest test) {
        WebElement savedAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'CartAddress_addActions__V6XKJ')]//span[text()='Change']")));
        Assert.assertTrue(savedAddress.isDisplayed(), "Address not saved!");
        Reporter.generateReport(driver, test, Status.PASS, "Address saved successfully (" + context + ")");
    }
}