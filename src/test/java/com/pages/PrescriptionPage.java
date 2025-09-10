package com.pages;

import com.objectRepository.Locators;
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


    public void addPatient() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.addPatientBtn)).click();
    }

    public void fillPatientForm() {
        try {
            String firstName = excelData[0][2];
            String lastName = excelData[0][3];
            String dob = excelData[0][4]; // format: dd-MM-yyyy
            String[] dobParts = dob.split("-");

            wait.until(ExpectedConditions.elementToBeClickable(Locators.firstNameField)).sendKeys(firstName);
            driver.findElement(Locators.lastNameField).sendKeys(lastName);
            driver.findElement(Locators.dobDayField).sendKeys(dobParts[0]);
            driver.findElement(Locators.dobMonthField).sendKeys(dobParts[1]);
            driver.findElement(Locators.dobYearField).sendKeys(dobParts[2]);

            driver.findElement(Locators.genderFemaleBtn).click();
            driver.findElement(Locators.relationDropdown).click();
            wait.until(ExpectedConditions.elementToBeClickable(Locators.relationDaughterOption)).click();

            Reporter.generateReport(driver,extTest, Status.PASS, "Patient form filled with Excel data");
        } catch (Exception e) {
            Reporter.generateReport(driver,extTest, Status.FAIL, "Failed to fill patient form");
        }
    }

    public void saveAndConfirm() {
        driver.findElement(Locators.saveBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(Locators.confirmBtn)).click();
    }

    public void proceed() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.proceedBtn1)).click();
        wait.until(ExpectedConditions.elementToBeClickable(Locators.proceedBtn2)).click();
    }
}
