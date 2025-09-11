package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectRepository.Locators;
import com.setup.Reporter;

import java.time.Duration;
import java.util.List;

public class BuyMedicinePage {

    WebDriver driver;
    WebDriverWait wait;
    ExtentTest extTest;

    public BuyMedicinePage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

 // ✅ Click Buy Medicines Tab with scroll + JS fallback
    public void clickBuyMedicinesTab() {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.buyMedicinesTab));

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Wait until clickable
            wait.until(ExpectedConditions.elementToBeClickable(element));

            try {
                element.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Buy Medicines tab");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Buy Medicines tab - " + e.getMessage());
        }
    }


    
 // 
 // In BuyMedicinePage.java
    public boolean validateBuyMedicinesTitle() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Buy Medicines')]")
            ));
            return titleElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


 // ✅ Search for Medicine
    public void searchMedicine(String medicineName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.searchBox)).click();

            // Small pause to allow animation / input load
            Thread.sleep(1000);

            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(Locators.searchInput));
            input.clear();
            input.sendKeys(medicineName);
            input.sendKeys(Keys.ENTER);

            Reporter.generateReport(driver, extTest, Status.PASS, "Searched for medicine: " + medicineName);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to search for medicine: " + medicineName + " - " + e.getMessage());
        }
    }

    public boolean isMedicineNotFoundMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'We’re sorry, the item you searched could not be found')]")
            ));
            Reporter.generateReport(driver, extTest, Status.PASS, "Medicine not found message is displayed: " + message.getText());
            return message.isDisplayed();
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Medicine not found message is NOT displayed");
            return false;
        }
    }


    // ✅ Apply Filters
    public void applyFilters() {
        try {
            List<WebElement> filters = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.FILTER_CHIPS)
            );

            boolean applied = false;
            for (WebElement filter : filters) {
                String text = filter.getText().trim();
                if (text.equalsIgnoreCase("In-stock")) {
                    filter.click();
                    applied = true;
                    Reporter.generateReport(driver, extTest, Status.PASS, "Applied filter: In-stock");
                }
            }

            if (!applied) {
                Reporter.generateReport(driver, extTest, Status.WARNING, "No In-stock filter found to apply");
            }

        } catch (TimeoutException e) {
            Reporter.generateReport(driver, extTest, Status.SKIP, "No filters available, skipped filter step");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Error while applying filters");
        }
    }

    // ✅ Validate Product Availability
    public boolean validateProductAvailability() {
        try {
            boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.productSearchResult)).isDisplayed();
            Reporter.generateReport(driver, extTest, Status.PASS, "Validated product availability");
            return result;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Product not available");
            return false;
        }
    }

    // ✅ Add Product to Cart
    public void addProductToCart(int quantity) {
        try {
            WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(Locators.addProductButton)
            );

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);

            try {
                addBtn.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
            }
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Add button for product");

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
                Reporter.generateReport(driver, extTest, Status.PASS, "Increased product quantity by 1");
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to add product to cart");
        }
    }

    // ✅ Click View Cart
    public void clickViewCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.viewCartButton)).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on View Cart button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click on View Cart button");
        }
    }
}

