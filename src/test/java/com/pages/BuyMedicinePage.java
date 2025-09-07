 package com.pages;

import com.objectRepository.Locators;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuyMedicinePage {

    WebDriver driver;
    WebDriverWait wait;

    public BuyMedicinePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Click Buy Medicine Tab
    public void clickBuyMedicineTab() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(Locators.buyMedicineTab));
        tab.click();
    }

    // Validate Buy Medicine Header
    public boolean isBuyMedicineHeaderDisplayed() {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.buyMedicineHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void searchMedicine(String medicineName) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(Locators.searchBox));
        input.clear();
        input.sendKeys(medicineName);
        input.sendKeys(Keys.ENTER);
    }


    // Apply In-stock Filter
    public void applyInStockFilter() {
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(Locators.inStockFilter));
        filter.click();
    }

    // Apply Dolo Brand Filter
    public void applyDoloBrandFilter() {
        WebElement brand = wait.until(ExpectedConditions.elementToBeClickable(Locators.doloBrandFilter));
        brand.click();
    }

    // Validate product result
    public boolean isProductResultDisplayed() {
        try {
            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.productResultHeader));
            return result.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Add product
    public void clickAddButton() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.addButton));
        addBtn.click();
    }

    // Increase product quantity
    public void increaseQuantity(int times) {
        WebElement plusBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.increaseButton));
        for (int i = 0; i < times; i++) {
            plusBtn.click();
        }
    }

    // Click View Cart
    public void clickViewCart() {
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(Locators.viewCartButton));
        viewCart.click();
    }
}
