package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectRepository.Locators;
import com.parameters.ExcelReader;
import com.setup.Reporter;
import com.stepDefinition.Hooks;

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
       String medicineName;

        // Locators
        public static By buyMedicinesTab = By.xpath("//a[contains(text(),'Buy Medicines')]");
        public static By buyMedicinesTitle = By.xpath("//h1[contains(@class,'buyMedicineTitle') and contains(normalize-space(),'Buy Medicines')]");
        
        public static By searchBox = By.xpath("//div[@data-placeholder='Search Medicines']");
        public static By searchInput = By.id("searchProduct"); // actual input after clicking search box

        public static By errorMessage = By.xpath("//div[contains(@class,'errorMessage')]"); // update as per your app

        // Methods
       
    

        // ---------- Filters ----------
        public void applyFilter(String filterName) {
            try {
                WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'FilterSearchMedicine_chipsUI')]//span[normalize-space()='" + filterName + "']")));
                filter.click();
                Reporter.generateReport(driver, Hooks.extTest, Status.PASS, "Applied filter: " + filterName);
            } catch (Exception e) {
                Reporter.generateReport(driver, Hooks.extTest, Status.FAIL, "Failed to apply filter: " + filterName);
                throw e;
            }
        }

        // ---------- Validation ----------
        public void validateFilteredSearchError() {
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(@class,'ProductSortSearch_title__aAuhf')]")));
                String actual = errorMessage.getText();

                Assert.assertTrue(actual.startsWith("No search results for"),
                        "Unexpected error message: " + actual);

                Reporter.generateReport(driver, Hooks.extTest, Status.PASS, "Validation passed for filter error");
            } catch (AssertionError e) {
                Reporter.generateReport(driver, Hooks.extTest, Status.FAIL, "Validation failed - " + e.getMessage());
                throw e;
            }
        }

        // ---------- Helper: Search ----------
        private void performSearch(String medicineName, String logMessage) {
            try {
                WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@data-placeholder='Search Medicines']")));
                searchBox.click();

                WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("searchProduct")));
                searchInput.clear();
                searchInput.sendKeys(medicineName, Keys.ENTER);

                Reporter.generateReport(driver, Hooks.extTest, Status.PASS, logMessage + ": " + medicineName);
            } catch (Exception e) {
                Reporter.generateReport(driver, Hooks.extTest, Status.FAIL, "Search failed for " + medicineName + " - " + e.getMessage());
                throw e;
            }
        }

        // ---------- Helper: Error Message Validation ----------
        private void validateErrorMessage(String expectedMessage) {
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(@class,'ProductSortSearch_title__aAuhf')]")));
                String actual = errorMessage.getText();

                Assert.assertEquals(actual.trim(), expectedMessage.trim(), "Error message mismatch!");
                Reporter.generateReport(driver, Hooks.extTest, Status.PASS, "Validation passed: " + expectedMessage);
            } catch (AssertionError e) {
                Reporter.generateReport(driver, Hooks.extTest, Status.FAIL, "Validation failed - " + e.getMessage());
                throw e;
            }
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
    public boolean validateBuyMedicinesTitle(WebDriver driver, ExtentTest extTest) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Buy Medicines')]")
            ));

            if (titleElement.isDisplayed()) {
                Reporter.generateReport(driver, extTest, Status.PASS, "Buy Medicines page loaded successfully");
                Assert.assertTrue(true, "Buy Medicines page loaded successfully");
                return true;
            } else {
                Reporter.generateReport(driver, extTest, Status.FAIL, "Buy Medicines page not loaded");
                Assert.fail("Buy Medicines page not loaded");
                return false;
            }
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Buy Medicines page not loaded - Exception: " + e.getMessage());
            Assert.fail("Buy Medicines page not loaded due to exception: " + e.getMessage());
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
            input.click();

            Reporter.generateReport(driver, extTest, Status.PASS, "Searched for medicine: " + medicineName);
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to search for medicine: " + medicineName + " - " + e.getMessage());
        }
    }
    public boolean isMedicineDisplayed(String medicineName) {
        try {
            WebElement medicineElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(text(),'" + medicineName + "')]")
            ));
            return medicineElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    
    
    public String getSearchErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate <p> element with the error message
            WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("p.RecentSearch_text__RGAxy")
            ));

            String actualMessage = errorMessageElement.getText().trim();

            Reporter.generateReport(driver, Hooks.extTest, Status.FAIL,
                    "Error message displayed: " + actualMessage);

            return actualMessage;

        } catch (Exception e) {
            Reporter.generateReport(driver, Hooks.extTest, Status.WARNING,
                    "No error message displayed or could not capture message: " + e.getMessage());
            return "";
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
    public void applyFilters(String filterName) {
        try {
            List<WebElement> filters = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.FILTER_CHIPS)
            );

            boolean applied = false;
            for (WebElement filter : filters) {
                String text = filter.getText().trim();
                if (text.equalsIgnoreCase(filterName) ||text.equalsIgnoreCase(filterName) ) {
                    filter.click();
                    applied = true;
                    Reporter.generateReport(driver, extTest, Status.PASS, "Applied filter: " + filterName);
                    break; // stop once filter is applied
                }
            }

            if (!applied) {
                Reporter.generateReport(driver, extTest, Status.WARNING, "No filter found for: " + filterName);
            }

        } catch (TimeoutException e) {
            Reporter.generateReport(driver, extTest, Status.SKIP, "No filters available, skipped filter step");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Error while applying filter: " + filterName + " - " + e.getMessage());
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
 // ✅ Add Product to Cart
    public void addProductToCart(int quantity) {
        try {
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//span[text()='Add'])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);

            try {
                addBtn.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
            }
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on Add button for product");

            WebElement increaseBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//span[@role='button' and @aria-label='Increase button'])[1]")));

            for (int i = 1; i < quantity; i++) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(increaseBtn)).click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", increaseBtn);
                }
                Reporter.generateReport(driver, extTest, Status.PASS, "Increased product quantity by 1");
            }

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to add product to cart: " + e.getMessage());
            Assert.fail("Exception in addProductToCart: " + e.getMessage());
        }
    }

    // ✅ Click View Cart
    public void clickViewCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Button']//span[text()='View Cart']"))).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked on View Cart button");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click on View Cart button: " + e.getMessage());
            Assert.fail("Exception in clickViewCart: " + e.getMessage());
        }
    }

    // ✅ Validate My Cart Page
    public void validateMyCartPage() {
        try {
            WebElement cartTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//li[contains(@class,'CartFlowBreadcrumbs_active') and normalize-space()='MY CART']")));
            Assert.assertTrue(cartTitle.isDisplayed(), "My Cart page is not visible");
            Reporter.generateReport(driver, extTest, Status.PASS, "My Cart page is visible");
        } catch (AssertionError e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "My Cart page is not visible: " + e.getMessage());
            Assert.fail("Exception in validateMyCartPage: " + e.getMessage());
        }
    }

    
    

    
}

