package com.stepDefinition;

import com.aventstack.extentreports.Status;
import com.setup.Reporter;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddressInvalidStep {

    private WebDriver driver;
    private WebDriverWait wait;

    public AddressInvalidStep() {
        this.driver = Hooks.getDriver(); // get driver from your Hooks class
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Given("user clicks on change")
    public void user_clicks_on_change() {
        WebElement changeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Change']")));
        changeBtn.click();
 }
}


