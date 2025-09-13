package com.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectRepository.Locators;
import com.setup.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProfilePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;

    public ProfilePage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    

    // ✅ First Proceed
    public void clickFirstProceed() {
        try {
            WebElement firstProceed = wait.until(ExpectedConditions.elementToBeClickable(Locators.firstProceedBtn));
            try {
                firstProceed.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProceed);
            }
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked First Proceed button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ First Proceed not clickable: " + e.getMessage());
            Assert.fail("❌ First Proceed not clickable: " + e.getMessage());
        }
    }

    public void clickSecondProceed() {
        try {
            // Define locator inside the method itself ✅
            By secondProceedBtn = By.xpath("//button[@aria-label='Button']//span[normalize-space()='Proceed']");

            WebElement secondProceed = wait.until(
                ExpectedConditions.elementToBeClickable(secondProceedBtn)
            );

            try {
                secondProceed.click();
            } catch (ElementClickInterceptedException e) {
                // Fallback: scroll + JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondProceed);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondProceed);
            }

            Reporter.generateReport(driver, extTest, Status.PASS, "✅ Clicked Second Proceed button");

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Second Proceed not clickable: " + e.getMessage());
            Assert.fail("❌ Second Proceed not clickable: " + e.getMessage());
        }
    }


    // ✅ Skip Savings
    public void clickSkipSavings() {
        try {
            WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.skipSavingsBtn));
            skipBtn.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Skip Savings button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Skip Savings not clickable: " + e.getMessage());
            Assert.fail("❌ Skip Savings not clickable: " + e.getMessage());
        }
    }

    // ✅ Validate Upload Prescription tab
    public void validateUploadPrescription() {
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.uploadPrescriptionHeader)).isDisplayed();
            Assert.assertTrue(visible, "Upload Prescription tab not visible");
            Reporter.generateReport(driver, extTest, Status.PASS, "Upload Prescription tab is visible");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Upload Prescription tab not found: " + e.getMessage());
            Assert.fail("❌ Upload Prescription tab not found: " + e.getMessage());
        }
    }

    // ✅ Click Add Patient
    public void clickAddPatient() {
        try {
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.addPatientBtn));
            addBtn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.addNewFamilyMemberHeader));
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Add Patient button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Add Patient button not clickable: " + e.getMessage());
            Assert.fail("❌ Add Patient button not clickable: " + e.getMessage());
        }
    }

    // ✅ Enter First Name
    public void enterFirstName(String fname) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.addNewFamilyMemberHeader));
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.firstNameField));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", field);
            field.clear();
            field.sendKeys(fname);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered First Name: " + fname);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Failed entering First Name: " + e.getMessage());
            Assert.fail("❌ First Name field not found: " + e.getMessage());
        }
    }

    // ✅ Enter Last Name
    public void enterLastName(String lname) {
        try {
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lastNameField));
            field.clear();
            field.sendKeys(lname);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered Last Name: " + lname);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Failed entering Last Name: " + e.getMessage());
            Assert.fail("❌ Last Name field not found: " + e.getMessage());
        }
    }

    // ✅ Enter DOB
    public void enterDOB(String dob) {
        try {
            String[] parts = dob.split("-");
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dobDayField)).sendKeys(parts[0]);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dobMonthField)).sendKeys(parts[1]);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dobYearField)).sendKeys(parts[2]);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered DOB: " + dob);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Failed entering DOB: " + e.getMessage());
            Assert.fail("❌ DOB field issue: " + e.getMessage());
        }
    }

    // ✅ Choose Gender
    public void chooseGender() {
        try {
            WebElement genderBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.genderFemaleBtn));
            genderBtn.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Selected Gender: Female");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Gender not selectable: " + e.getMessage());
            Assert.fail("❌ Gender not selectable: " + e.getMessage());
        }
    }

    // ✅ Choose Relation
    public void chooseRelation() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.relationDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Locators.relationDaughterOption)).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Selected Relation: Daughter");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Relation not selectable: " + e.getMessage());
            Assert.fail("❌ Relation not selectable: " + e.getMessage());
        }
    }

    // ✅ Save & Confirm
    public void clickSaveAndConfirm() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.saveBtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Locators.confirmBtn)).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Save & Confirm");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Save & Confirm failed: " + e.getMessage());
            Assert.fail("❌ Save & Confirm failed: " + e.getMessage());
        }
    }

    // ✅ Validate Patient Added
    public void validatePatientAdded() {
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.paymentsTab)).isDisplayed();
            By patientRadioBtn = By.xpath("//input[@type='radio' and @name='product-sort']");
            WebElement radioBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(patientRadioBtn));
            Assert.assertTrue(visible && radioBtn.isDisplayed(), "Patient addition not confirmed properly.");
            Reporter.generateReport(driver, extTest, Status.PASS, "✅ New patient successfully added.");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Patient addition validation failed: " + e.getMessage());
            Assert.fail("❌ Patient not added successfully: " + e.getMessage());
        }
    }

    // ================= NEGATIVE INPUTS =================
    public void enterInvalidFirstName(String fname) {
        enterFirstName(fname);
        Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid First Name: " + fname);
    }

    public void enterInvalidLastName(String lname) {
        enterLastName(lname);
        Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid Last Name: " + lname);
    }

    public void enterInvalidDOB(String dob) {
        enterDOB(dob);
        Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid DOB: " + dob);
    }

    public void enterInvalidEmail(String email) {
        try {
            WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email']"));
            emailField.clear();
            emailField.sendKeys(email);
            Reporter.generateReport(driver, extTest, Status.FAIL, "Entered invalid Email: " + email);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "❌ Failed entering Email: " + e.getMessage());
            Assert.fail("❌ Email field not found: " + e.getMessage());
        }
    }
}

