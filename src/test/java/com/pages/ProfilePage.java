package com.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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

    // ==================== Navigation ====================

    public void clickFirstProceed() {
        try {
            By firstProceedBtn = By.xpath("//button[@aria-label='Button']//span[text()='PROCEED']");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(firstProceedBtn));
            safeClick(btn);
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked First Proceed button");
        } catch (Exception e) {
            failStep("First Proceed", e);
        }
    }

    public void clickSecondProceed() {
        try {
            By secondProceedBtn = By.xpath("//button[@aria-label='Button']//span[text()='Proceed']");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(secondProceedBtn));
            safeClick(btn);
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Second Proceed button");
        } catch (Exception e) {
            failStep("Second Proceed", e);
        }
    }

    public void clickSkipSavings() {
        try {
            By skipSavingsBtn = By.xpath("//button[.//span[normalize-space()='Skip Savings']]");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(skipSavingsBtn));
            safeClick(btn);
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Skip Savings");
        } catch (Exception e) {
            failStep("Skip Savings", e);
        }
    }

    public void validateUploadPrescription() {
        try {
            By uploadPrescriptionHeader = By.xpath("//h2[contains(text(),'Upload Prescription')]");
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadPrescriptionHeader)).isDisplayed();
            Assert.assertTrue(visible, "Upload Prescription tab not visible");
            Reporter.generateReport(driver, extTest, Status.PASS, "Upload Prescription tab is visible");
        } catch (Exception e) {
            failStep("Upload Prescription Tab", e);
        }
    }

    public void clickAddPatient() {
        try {
            By addPatientBtn = By.xpath("//button//span[contains(.,'Add Patient Name')]");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addPatientBtn));
            safeClick(btn);
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Add Patient button");
        } catch (Exception e) {
            failStep("Add Patient Button", e);
        }
    }

    // ==================== Form Inputs ====================

    // Validate Add Profile Form
    public void validateProfileFormOpened() {
        try {
            By formHeader = By.xpath("//h2[contains(text(),'Add New Family Member')]");
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(formHeader)).isDisplayed();
            Assert.assertTrue(visible, "Add New Family Member form not visible");
            Reporter.generateReport(driver, extTest, Status.PASS, "Profile form is opened successfully");
        } catch (Exception e) {
            failStep("Profile Form Open", e);
        }
    }

    // ✅ Enter First Name
    public void enterFirstName(String fname) {
        try {
            By firstNameField = By.xpath("//input[@placeholder='First name']");
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
            scrollIntoView(field);
            field.clear();
            field.sendKeys(fname);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered First Name: " + fname);
        } catch (Exception e) {
            failStep("First Name Field", e);
        }
    }

    // ✅ Enter Last Name
    public void enterLastName(String lname) {
        try {
            By lastNameField = By.xpath("//input[@placeholder='Last name']");
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
            scrollIntoView(field);
            field.clear();
            field.sendKeys(lname);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered Last Name: " + lname);
        } catch (Exception e) {
            failStep("Last Name Field", e);
        }
    }

    // ✅ Enter DOB
    public void enterDOB(String dob) {
        try {
            String[] parts = dob.split("[-/]"); // Handles both 01-06-2004 and 01/06/2004
            By dayField = By.xpath("//input[@placeholder='dd']");
            By monthField = By.xpath("//input[@placeholder='mm']");
            By yearField = By.xpath("//input[@placeholder='yyyy']");

            WebElement day = wait.until(ExpectedConditions.visibilityOfElementLocated(dayField));
            scrollIntoView(day);
            day.clear(); day.sendKeys(parts[0]);

            WebElement month = wait.until(ExpectedConditions.visibilityOfElementLocated(monthField));
            month.clear(); month.sendKeys(parts[1]);

            WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(yearField));
            year.clear(); year.sendKeys(parts[2]);

            Reporter.generateReport(driver, extTest, Status.PASS, "Entered DOB: " + dob);
        } catch (Exception e) {
            failStep("DOB Field", e);
        }
    }

    // ✅ Choose Gender
    public void chooseGender() {
        try {
            By genderFemaleBtn = By.xpath("//span[normalize-space()='Female']");
            WebElement genderBtn = wait.until(ExpectedConditions.elementToBeClickable(genderFemaleBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderBtn);
            Reporter.generateReport(driver, extTest, Status.PASS, "Selected Gender: Female");
        } catch (Exception e) {
            failStep("Gender Select", e);
        }
    }

    // ✅ Choose Relation
    public void chooseRelation() {
        try {
            By relationDropdown = By.xpath("//button[contains(@id,'headlessui-listbox-button')]");
            By relationOption = By.xpath("//li//span[normalize-space()='DAUGHTER']");

            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(relationDropdown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(relationOption));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            Reporter.generateReport(driver, extTest, Status.PASS, "Selected Relation: Daughter");
        } catch (Exception e) {
            failStep("Relation Select", e);
        }
    }

    // ✅ Enter Email
    public void enterEmail(String email) {
        try {
            By emailField = By.xpath("//input[@placeholder='name@email.com']");
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            scrollIntoView(field);
            field.clear();
            field.sendKeys(email);
            Reporter.generateReport(driver, extTest, Status.PASS, "Entered Email: " + email);
        } catch (Exception e) {
            failStep("Email Field", e);
        }
    }

    // ✅ Save & Confirm
    public void clickSaveAndConfirm() {
        try {
            By saveBtn = By.xpath("//button[.//span[normalize-space()='Save']]");
            By confirmBtn = By.xpath("//button[.//span[normalize-space()='CONFIRM']]");

            WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);

            WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(confirmBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirm);

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Save & Confirm");
        } catch (Exception e) {
            failStep("Save & Confirm", e);
        }
    }

    // ==================== Negative Inputs ====================

    public void enterInvalidFirstName(String fname) {
        enterFirstName(fname);
        Reporter.generateReport(driver, extTest, Status.PASS, "Entered invalid First Name: " + fname);
    }

    public void enterInvalidLastName(String lname) {
        enterLastName(lname);
        Reporter.generateReport(driver, extTest, Status.PASS, "Entered invalid Last Name: " + lname);
    }

    public void enterInvalidDOB(String dob) {
        enterDOB(dob);
        Reporter.generateReport(driver, extTest, Status.PASS, "Entered invalid DOB: " + dob);
    }

    public void enterInvalidEmail(String email) {
        enterEmail(email);
        Reporter.generateReport(driver, extTest, Status.PASS, "Entered invalid Email: " + email);
    }

    // ==================== Helpers ====================

    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void failStep(String step, Exception e) {
        Reporter.generateReport(driver, extTest, Status.FAIL, "❌ " + step + " failed: " + e.getMessage());
        Assert.fail("❌ " + step + " failed: " + e.getMessage());
    }
}
