package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    // ===== VALID FLOW LOCATORS =====
    public static final By cartPageTitle = By.xpath("//h2[(text()='OFFERS & DISCOUNTS')]");
    public static final By suggestedArea = By.xpath("//h3[contains(text(),'Shiv Colony')]");
    public static final By enterDetailsHeader = By.xpath("//h2[text()='Enter Address Details']");
    public static final By flatNumberField = By.xpath("//textarea[@name='address1']");
    public static final By saveAndNextBtn = By.xpath("//button[contains(.,'Save & Next')]");
    public static final By someoneElseRadio = By.xpath("//input[@id='someone else']");
    public static final By otherBtn = By.xpath("//span[normalize-space()='other']");
    public static final By recipientField = By.xpath("//input[@name='recipientName']");
    public static final By saveBtn = By.xpath("//button[contains(.,'Save Address')]");
    public static final By viewCartIcon = By.cssSelector("a[aria-label='Cart Icon']");

    // ===== INVALID FLOW LOCATORS =====
    public static final By changeBtn = By.xpath("//span[normalize-space()='Change']");
    public static final By addNewAddressLink = By.xpath("//span[contains(text(),'Add New Address')]");
    public static final By deliverToHeader = By.xpath("//h2[contains(normalize-space(.),'Deliver to')]");
    public static final By pincodeSearchBox = By.xpath("//input[@placeholder='Search for society, locality, pincode...']");
    public static final By noResultMsg = By.xpath("//div[text()='No Result found, Try searching for other location']");
    public static final By flatErrorMsg = By.xpath("//span[text()='Minimum 2 characters are required']");
    public static final By saveOptionError = By.xpath("//span[contains(text(),'Select any one option')]");
    public static final By backIcon = By.xpath("//img[@alt='back' or contains(@src,'ic_back.svg')]");

    // ================== HELPER METHODS ==================
    private void safeClick(By locator, String elementName) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (Exception ex) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on " + elementName);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click " + elementName + ". Error: " + e.getMessage());
        }
    }

    // ================== VALID FLOW METHODS ==================
    public void clickViewCartIcon() {
        safeClick(viewCartIcon, "'View Cart' icon");
    }

    public void textField() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pincodeSearchBox)).sendKeys("9874");
            Reporter.generateReport(driver, extTest, Status.PASS, "Typed in pincode search box");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed typing in pincode search. Error: " + e.getMessage());
        }
    }

    public void suggLocation() {
        safeClick(suggestedArea, "'Suggested Area'");
    }

    public void validateEnterDetailsPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(enterDetailsHeader));
            Reporter.generateReport(driver, extTest, Status.PASS, "Verified 'Enter Address Details' page");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed verifying Enter Address Details. Error: " + e.getMessage());
        }
    }

    public void flatNumber() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(flatNumberField)).sendKeys("123");
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered flat number");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed entering flat number. Error: " + e.getMessage());
        }
    }

    public void saveNext() {
        safeClick(saveAndNextBtn, "'Save & Next'");
    }

    public void radioBtn() {
        safeClick(someoneElseRadio, "'Someone Else' radio");
    }

    public void otherClick() {
        safeClick(otherBtn, "'Other'");
    }

    public void recipient() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(recipientField)).sendKeys("Priya");
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered recipient name");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed entering recipient. Error: " + e.getMessage());
        }
    }

    public void savebtn() {
        safeClick(saveBtn, "'Save Address'");
    }

    public void validateCartPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageTitle));
            Reporter.generateReport(driver, extTest, Status.PASS, "Returned to Cart Page");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed verifying Cart Page. Error: " + e.getMessage());
        }
    }

    // ================== INVALID FLOW METHODS ==================
    public void changeClick() {
        safeClick(changeBtn, "'Change'");
    }

    public void deliverTo() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(deliverToHeader));
            Reporter.generateReport(driver, extTest, Status.PASS, "Verified 'Deliver To' header");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed verifying Deliver To. Error: " + e.getMessage());
        }
    }

    public void newaddressClick() {
        safeClick(addNewAddressLink, "'Add New Address'");
    }

    public void textAreafield() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pincodeSearchBox)).sendKeys("14756548");
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered invalid pincode");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed typing invalid pincode. Error: " + e.getMessage());
        }
    }

    public void validateNoResult() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(noResultMsg));
            Reporter.generateReport(driver, extTest, Status.PASS, "No results found message displayed");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "No results validation failed. Error: " + e.getMessage());
        }
    }

    public void clickBackFromDeliverTab() {
        safeClick(backIcon, "'Back Icon'");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pincodeSearchBox));
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Back navigation failed. Error: " + e.getMessage());
        }
    }

    public void clickAddNewAddress() {
        By addNewAddressBtn = By.xpath("//button[contains(@class,'AddNewAddressRevamped_locationBtn__')]//span[text()='Add New Address']");
        safeClick(addNewAddressBtn, "'Add New Address'");
    }

    // Enter valid pincode
    public void enterValidPincode(String pincode) {
        try {
            // Relaxed Deliver To header check
            wait.until(ExpectedConditions.visibilityOfElementLocated(deliverToHeader));

            // Try Add New Address if available
            try {
                safeClick(By.xpath("//button[contains(@class,'AddNewAddressRevamped_locationBtn__')]//span[text()='Add New Address']"),
                        "'Add New Address'");
                Reporter.generateReport(driver, extTest, Status.INFO, "Clicked Add New Address before entering pincode");
            } catch (Exception e) {
                Reporter.generateReport(driver, extTest, Status.WARNING, "Add New Address not found, continuing...");
            }

            // Enter pincode
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(pincodeSearchBox));
            input.clear();
            input.sendKeys(pincode);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered valid pincode: " + pincode);

            // Click suggestion dynamically
            By suggestion = By.xpath("//div[contains(@class,'NewSearchLocationSuggestor_searchItemList')]//h3[contains(text(),'" + pincode + "')]");
            safeClick(suggestion, "pincode suggestion");

            // Validate Enter Address Details page
            wait.until(ExpectedConditions.visibilityOfElementLocated(enterDetailsHeader));
            Reporter.generateReport(driver, extTest, Status.PASS, "Navigated to Enter Address Details page");

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed entering valid pincode: " + e.getMessage());
        }
    }

    public void enterInvalidFlatNumber(String flatNo) {
        try {
            WebElement flat = wait.until(ExpectedConditions.visibilityOfElementLocated(flatNumberField));
            flat.clear();
            flat.sendKeys(flatNo);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered flat number: " + flatNo);

            safeClick(saveAndNextBtn, "'Save & Next'");

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Invalid flat number entry failed: " + e.getMessage());
        }
    }

    public void enterValidFlatNumberAndSave(String flatNo) {
        try {
            WebElement flat = wait.until(ExpectedConditions.visibilityOfElementLocated(flatNumberField));
            flat.clear();
            flat.sendKeys(flatNo);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered flat number: " + flatNo);

            safeClick(saveAndNextBtn, "'Save & Next'");

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Valid flat number save failed: " + e.getMessage());
        }
    }

    public void validateFlatNumberError() {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(flatErrorMsg));
            if (msg.isDisplayed()) {
                Reporter.generateReport(driver, extTest, Status.PASS, "Error displayed: Minimum 2 characters are required");
            } else {
                Reporter.generateReport(driver, extTest, Status.WARNING, "Flat number error element found but not visible");
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Flat number error not shown. Error: " + e.getMessage());
        }
    }

    public void validateSaveAddressOptionError() {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(saveOptionError));
            if (msg.isDisplayed()) {
                Reporter.generateReport(driver, extTest, Status.PASS, "Error displayed: Select any one option");
            } else {
                Reporter.generateReport(driver, extTest, Status.WARNING, "Save option error element found but not visible");
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Save address option validation failed. Error: " + e.getMessage());
        }
    }

    public void enterInvalidRecipientName(String name) {
        try {
            WebElement rec = wait.until(ExpectedConditions.visibilityOfElementLocated(recipientField));
            rec.clear();
            rec.sendKeys(name);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered invalid recipient name: " + name);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Invalid recipient entry failed. Error: " + e.getMessage());
        }
    }

    public void saveInvalidAddress() {
        safeClick(saveBtn, "'Save Address' (Invalid Flow)");
        Reporter.generateReport(driver, extTest, Status.FAIL, "Saved invalid address (DEFECT)");
    }
}


