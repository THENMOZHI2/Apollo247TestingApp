package com.pages;

import com.objectRepository.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;

import com.parameters.ExcelReader;
import com.setup.Reporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrescriptionPage {

	WebDriver driver;
    WebDriverWait wait;
    String[][] excelData;
    ExtentTest extTest;  // Add this

    public PrescriptionPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.excelData = ExcelReader.readdata();
    }

   
    
 // ✅ Separate method for clicking Add Patient button
    public void addPatient() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement addPatientBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(Locators.addPatientBtn));

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addPatientBtn);

            try {
                addPatientBtn.click(); // normal click
            } catch (Exception e) {
                // fallback with JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addPatientBtn);
            }

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Add Patient button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Add Patient button - " + e.getMessage());
            throw e;
        }
    }

    public void fillPatientForm() throws Exception {
        try {
            if (excelData == null || excelData.length == 0) {
                throw new RuntimeException("excelData is empty. Make sure ExcelReader.readdata() returns data.");
            }

            // Find first non-empty row with firstName(3) and lastName(4)
            int rowIndex = -1;
            for (int i = 0; i < excelData.length; i++) {
                if (excelData[i] != null && excelData[i].length > 4) {
                    String fn = excelData[i][3] == null ? "" : excelData[i][3].trim();
                    String ln = excelData[i][4] == null ? "" : excelData[i][4].trim();
                    if (!fn.isEmpty() && !ln.isEmpty()) {
                        rowIndex = i;
                        break;
                    }
                }
            }
            if (rowIndex == -1) {
                throw new RuntimeException("No valid patient row found in excelData");
            }

            // Extract safely
            String firstName = excelData[rowIndex].length > 3 ? excelData[rowIndex][3].trim() : "";
            String lastName  = excelData[rowIndex].length > 4 ? excelData[rowIndex][4].trim() : "";

            Reporter.generateReport(driver, extTest, Status.INFO,
                    "Filling patient from Excel row " + (rowIndex + 1) + ": firstName=" + firstName
                            + ", lastName=" + lastName);

            // Fill First Name
            if (!firstName.isEmpty()) {
                WebElement firstField = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.firstNameField));
                firstField.click();
                firstField.clear();
                firstField.sendKeys(firstName);
                Thread.sleep(500);
            }

            // Fill Last Name
            if (!lastName.isEmpty()) {
                WebElement lastField = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lastNameField));
                lastField.click();
                lastField.clear();
                lastField.sendKeys(lastName);
                Thread.sleep(500);
            }

            // 👉 Hardcoded DOB (example: 01-06-2004)
            String day   = "01";
            String month = "06";
            String year  = "2004";

            WebElement dayField = wait.until(ExpectedConditions.elementToBeClickable(Locators.dobDayField));
            dayField.click(); dayField.clear(); dayField.sendKeys(day);
            ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", dayField);
            Thread.sleep(300);

            WebElement monthField = wait.until(ExpectedConditions.elementToBeClickable(Locators.dobMonthField));
            monthField.click(); monthField.clear(); monthField.sendKeys(month);
            ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", monthField);
            Thread.sleep(300);

            WebElement yearField = wait.until(ExpectedConditions.elementToBeClickable(Locators.dobYearField));
            yearField.click(); yearField.clear(); yearField.sendKeys(year);
            ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", yearField);
            Thread.sleep(500);

            // Gender selection
            try {
                WebElement genderBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.genderFemaleBtn));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", genderBtn);
                genderBtn.click();
                Thread.sleep(500);
            } catch (Exception e) {
                Reporter.generateReport(driver, extTest, Status.WARNING, "Could not click gender button: " + e.getMessage());
            }

            // Relation dropdown
            try {
                WebElement relation = wait.until(ExpectedConditions.elementToBeClickable(Locators.relationDropdown));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", relation);
                relation.click();
                Thread.sleep(500);
                wait.until(ExpectedConditions.elementToBeClickable(Locators.relationDaughterOption)).click();
                Thread.sleep(500);
            } catch (Exception e) {
                // fallback
                try {
                    By daughterBy = By.xpath("//li[contains(normalize-space(), 'Daughter') or contains(., 'Daughter')]");
                    WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(daughterBy));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opt);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt);
                    Thread.sleep(500);
                } catch (Exception ex) {
                    Reporter.generateReport(driver, extTest, Status.WARNING, "Relation selection failed: " + ex.getMessage());
                }
            }

            Reporter.generateReport(driver, extTest, Status.PASS, "Patient form filled (row " + (rowIndex + 1) + ")");

            // Scroll to Save button and click
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.saveBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
            Thread.sleep(500);
            saveBtn.click();

            // Confirm button
            WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.confirmBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmBtn);
            Thread.sleep(300);
            confirmBtn.click();

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to fill patient form - " + e.getMessage());
            throw e;
        }
    }

    
    

}
