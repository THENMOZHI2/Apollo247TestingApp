package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By logo = By.cssSelector("img[alt='Online Doctor Consultation & Medicines']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }
}

