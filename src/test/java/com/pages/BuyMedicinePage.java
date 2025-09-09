package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.objectRepository.Locators;

import java.time.Duration;
import java.util.List;

public class BuyMedicinePage {

    WebDriver driver;
    WebDriverWait wait;
    ExtentTest test;

    public BuyMedicinePage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickBuyMedicinesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.buyMedicinesTab)).click();
        test.info("Clicked on Buy Medicines tab");
    }

    public boolean validateBuyMedicinesTitle() {
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.buyMedicinesTitle)).isDisplayed();
        test.info("Validated Buy Medicines page title");
        return result;
    }

    public void searchMedicine(String medicineName) {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.searchBox)).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.searchInput));
        input.sendKeys(medicineName);
        input.sendKeys(Keys.ENTER);
        test.info("Searched for medicine: " + medicineName);
    }

    public void applyFilters() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            List<WebElement> filters = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.FILTER_CHIPS)
            );

            for (WebElement filter : filters) {
                String text = filter.getText().trim();
                if (text.equalsIgnoreCase("In-stock")) {
                    filter.click();
                }
            }

        } catch (TimeoutException e) {
            System.out.println("No filters found. Skipping filter step.");
        }
    }

    public boolean validateProductAvailability() {
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.productSearchResult)).isDisplayed();
        test.info("Validated product availability");
        return result;
    }

    public void addProductToCart(int quantity) {
        WebElement addBtn = wait.until(
            ExpectedConditions.elementToBeClickable(Locators.addProductButton)
        );

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);

        try {
            addBtn.click();
        } catch (ElementClickInterceptedException e) {
            // Fallback to JS click if intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        }
        test.info("Clicked on Add button for product");

        // Wait for Increase (+) button to appear
        WebElement increaseBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(Locators.increaseProductQuantity)
        );

        // Click Increase button (quantity - 1) times
        for (int i = 1; i < quantity; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(increaseBtn)).click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", increaseBtn);
            }
            test.info("Increased product quantity by 1");
        }
    }



    public void clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.viewCartButton)).click();
        test.info("Clicked on View Cart button");
    }
    
}
