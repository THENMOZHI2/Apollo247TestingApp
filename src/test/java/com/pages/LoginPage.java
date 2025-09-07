package com.pages;

import com.objectRepository.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickLoginIcon() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.loginIcon));
        loginBtn.click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.mobileNumberInput));
        phoneInput.clear();
        phoneInput.sendKeys(phoneNumber);
    }

    public void clickContinueBtn() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.continueBtn));
        continueBtn.click();
    }

    public void enterOtpFromConsole(String otp) {
        WebElement otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otpInput));
        otpInput.sendKeys(otp);
    }

    public void clickVerifyOtpBtn() {
        WebElement verifyBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.verifyOtpBtn));
        verifyBtn.click();
    }

    public boolean isHomePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.homeLogo));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
