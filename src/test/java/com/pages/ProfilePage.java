package com.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.setup.Reporter;
import org.testng.Assert;

public class ProfilePage {

    WebDriver driver;
    WebDriverWait wait;
    ExtentTest extTest;

    public ProfilePage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== Locators =====
    public static By firstProceedBtn = By.xpath("//button[@aria-label='Button']//span[text()='PROCEED']");
    public static By secondProceedBtn = By.xpath("//button[@aria-label='Button']//span[text()='Proceed']");
    public static By skipSavingsBtn = By.xpath("//button[.//span[normalize-space()='Skip Savings']]");
    public static By uploadPrescriptionHeader = By.xpath("//h2[text()='Upload Prescription']");
    public static By addPatientBtn = By.xpath("//button//span[contains(.,'Add Patient Name')]");
    public static By firstNameField = By.xpath("//input[@placeholder='First name']");
    public static By lastNameField = By.xpath("//input[@placeholder='Last name']");
    public static By dobDayField = By.xpath("//input[@placeholder='dd']");
    public static By dobMonthField = By.xpath("//input[@placeholder='mm']");
    public static By dobYearField = By.xpath("//input[@placeholder='yyyy']");
    public static By genderFemaleBtn = By.xpath("//button[span[text()='Female']]");
    public static By relationDropdown = By.xpath("//button[contains(@id,'headlessui-listbox-button')]");
    public static By relationDaughterOption = By.xpath("//li[.//span[text()='DAUGHTER']]");
    public static By saveBtn = By.xpath("//button[span[normalize-space(text())='Save']]");
    public static By confirmBtn = By.xpath("//button[span[normalize-space(text())='CONFIRM']]");
    public static By paymentsTab = By.xpath("//div[text()='Payments']");

    // ===== Actions =====

    public void clickFirstProceed() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProceedBtn)).click();
        Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on First Proceed button");
    }

    public void clickSecondProceed() {
        wait.until(ExpectedConditions.elementToBeClickable(secondProceedBtn)).click();
        Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Second Proceed button");
    }

    public void clickSkipSavings() {
        wait.until(ExpectedConditions.elementToBeClickable(skipSavingsBtn)).click();
        Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Skip Savings");
    }

    public void validateUploadPrescription() {
        boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadPrescriptionHeader)).isDisplayed();
        Reporter.generateReport(driver, extTest, Status.PASS, "Upload Prescription tab is visible");
        Assert.assertTrue(visible, "❌ Upload Prescription tab is not visible");
    }

    public void clickAddPatient() {
        wait.until(ExpectedConditions.elementToBeClickable(addPatientBtn)).click();
        Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Add Patient button");
    }

    // ===== Negative field inputs =====

    public void enterInvalidFirstName(String fname) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(fname);
            Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid First Name: " + fname);

            // ASSERT: Check error message (adjust locator as per UI)
            WebElement errorMsg = driver.findElement(By.xpath("//span[contains(text(),'Invalid First Name')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "❌ Error not shown for invalid First Name");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Error entering invalid First Name: " + e.getMessage());
            Assert.fail("❌ Exception while entering invalid First Name: " + e.getMessage());
        }
    }

    public void enterInvalidLastName(String lname) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lname);
            Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid Last Name: " + lname);

            WebElement errorMsg = driver.findElement(By.xpath("//span[contains(text(),'Invalid Last Name')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "❌ Error not shown for invalid Last Name");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Error entering invalid Last Name: " + e.getMessage());
            Assert.fail("❌ Exception while entering invalid Last Name: " + e.getMessage());
        }
    }

    public void enterInvalidDOB(String dob) {
        try {
            String[] parts = dob.split("-");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dobDayField)).sendKeys(parts[0]);
            wait.until(ExpectedConditions.visibilityOfElementLocated(dobMonthField)).sendKeys(parts[1]);
            wait.until(ExpectedConditions.visibilityOfElementLocated(dobYearField)).sendKeys(parts[2]);

            Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid DOB: " + dob);

            WebElement errorMsg = driver.findElement(By.xpath("//span[contains(text(),'Invalid DOB')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "❌ Error not shown for invalid DOB");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Error entering invalid DOB: " + e.getMessage());
            Assert.fail("❌ Exception while entering invalid DOB: " + e.getMessage());
        }
    }

    public void enterInvalidEmail(String email) {
        try {
            WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email']"));
            emailField.sendKeys(email);
            Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid Email: " + email);

            WebElement errorMsg = driver.findElement(By.xpath("//span[contains(text(),'Invalid email')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "❌ Error not shown for invalid Email");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Error entering invalid Email: " + e.getMessage());
            Assert.fail("❌ Exception while entering invalid Email: " + e.getMessage());
        }
    }

    // ===== Positive data entry =====

    public void enterFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(fname);
        Reporter.generateReport(driver, extTest, Status.PASS, "Entered First Name: " + fname);
    }

    public void enterLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lname);
        Reporter.generateReport(driver, extTest, Status.PASS, "Entered Last Name: " + lname);
    }

    public void enterDOB(String dob) {
        String[] parts = dob.split("-");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dobDayField)).sendKeys(parts[0]);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dobMonthField)).sendKeys(parts[1]);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dobYearField)).sendKeys(parts[2]);
        Reporter.generateReport(driver, extTest, Status.PASS, "Entered DOB: " + dob);
    }

    public void chooseGender() {
        wait.until(ExpectedConditions.elementToBeClickable(genderFemaleBtn)).click();
        Reporter.generateReport(driver, extTest, Status.PASS, "Selected Gender: Female");
    }

    public void chooseRelation() {
        wait.until(ExpectedConditions.elementToBeClickable(relationDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(relationDaughterOption)).click();
        Reporter.generateReport(driver, extTest, Status.PASS, "Selected Relation: Daughter");
    }

    public void clickSaveAndConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Save & Confirm");
    }

    public void validatePatientAdded() {
        boolean paymentsVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentsTab)).isDisplayed();
        Reporter.generateReport(driver, extTest, Status.PASS, "New patient successfully added, Payments tab visible");
        Assert.assertTrue(paymentsVisible, "❌ Payments tab not visible. Patient may not have been added.");
    }
}
