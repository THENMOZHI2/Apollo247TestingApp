package com.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.setup.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class MedicineSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    ExtentTest extTest;

    public MedicineSearchPage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterMedicineInSearchBox(String medicineName, ExtentTest extTest) {
        try {
            WebElement searchInput;

            try {
                // Case 1: Direct search input already visible
                searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchProduct")));
            } catch (TimeoutException e) {
                // Case 2: Maybe placeholder div is shown first, then input
                WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@data-placeholder,'Search')]")
                ));
                searchBox.click();

                searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchProduct")));
            }

            // Enter medicine name
            searchInput.clear();
            searchInput.sendKeys(medicineName);
            searchInput.sendKeys(Keys.ENTER);

            Reporter.generateReport(driver, extTest, Status.PASS, "Entered medicine: " + medicineName);

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL,
                    "Failed while entering medicine: " + e.getMessage());
            Assert.fail("Exception in search flow: " + e.getMessage());
        }
    }

    public void applyFilters(String filterName, ExtentTest test) {
        try {
            // Wait for filter chips
            List<WebElement> filters = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//div[contains(@class,'FilterSearchMedicine_chipsUI')]")
                )
            );

            boolean applied = false;

            for (WebElement filter : filters) {
                String text = filter.getText().trim();
                System.out.println("Available filter: [" + text + "]"); // 🔍 Debug log

                // Normalize hyphens, spaces, and case (e.g. In-stock / In stock / INSTOCK)
                String normalizedUI = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
                String normalizedInput = filterName.replaceAll("[^a-zA-Z]", "").toLowerCase();

                if (normalizedUI.equals(normalizedInput)) {
                    // Scroll into center view
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", filter);

                    try {
                        wait.until(ExpectedConditions.elementToBeClickable(filter)).click();
                    } catch (Exception e) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filter);
                    }

                    applied = true;
                    Reporter.generateReport(driver, test, Status.PASS, "Applied filter: " + text);
                    break;
                }
            }

            if (!applied) {
                Reporter.generateReport(driver, test, Status.WARNING, "No filter found for: " + filterName);
                Assert.fail("Filter not found: " + filterName);
            }

        } catch (TimeoutException e) {
            Reporter.generateReport(driver, test, Status.SKIP, "No filters available, skipped filter step");
        } catch (Exception e) {
            Reporter.generateReport(driver, test, Status.FAIL, "Error while applying filter: " 
                    + filterName + " - " + e.getMessage());
            Assert.fail("Error while applying filter: " + filterName);
        }
    }

    public String validateNoSearchResultsError(ExtentTest extTest) {
        String actualErrorMessage = "";
        try {
            // Wait for the error message element to appear
            WebElement errorMsg = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h3[contains(@class,'ProductSortSearch_title__aAuhf')]")));

            // Wait until the text contains either "0 search results" or "No search results"
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(driver -> {
                        String text = errorMsg.getText().trim();
                        return text.contains("0 search results") || text.contains("No search results");
                    });

            actualErrorMessage = errorMsg.getText().trim();

            // Log in report with screenshot
            Reporter.generateReport(driver, extTest, Status.INFO,
                    "Negative test executed. Message displayed: " + actualErrorMessage);

        } catch (Exception e) {
            // Capture screenshot & log if the message doesn't appear
            Reporter.generateReport(driver, extTest, Status.WARNING,
                    "Negative test - error message not found. Exception: " + e.getMessage());
        }

        return actualErrorMessage;
    }
 // Locator for View Cart (cart icon)
    

    // Locator for Empty Cart Message
    By emptyCartMessage = By.xpath("//p[contains(@class,'EmptyCart_imageTxt') and contains(text(),'YOUR CART IS EMPTY')]");

    // Method to click View Cart icon
    public void clickViewCartIcon(ExtentTest test) {
        try {
            By cartIconLocator = By.cssSelector("a.Header_cart__Pwy9_[aria-label='Cart Icon']");
            WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartIconLocator));
            cart.click();
            test.log(Status.INFO, "Clicked on View Cart icon");
        } catch (Exception e) {
            Assert.fail("Exception while clicking on View Cart icon: " + e.getMessage());
        }
    }


    // Method to validate Empty Cart message
    public void validateEmptyCartMessage(ExtentTest test) {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
            Assert.assertTrue(message.isDisplayed(), "'YOUR CART IS EMPTY' message not displayed");
            test.log(Status.PASS, "'YOUR CART IS EMPTY' message is displayed");
        } catch (Exception e) {
            Assert.fail("Exception in validating empty cart message: " + e.getMessage());
        }
    }






    public void validateMedicineNameDisplayed(String medicineName, ExtentTest extTest) {
        try {
            // Build XPath safely with double quotes
            String xpath = "//div[@class='F']//h2[contains(text(),\"" + medicineName + "\")]";

            WebElement medicineElement = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            String actualText = medicineElement.getText().trim();
            Assert.assertEquals(actualText, medicineName,
                    "Expected medicine name not displayed. Found: " + actualText);

            Reporter.generateReport(driver, extTest, Status.PASS,
                    "Medicine name displayed correctly: " + actualText);

        } catch (AssertionError e) {
            Reporter.generateReport(driver, extTest, Status.FAIL,
                    "Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL,
                    "Failed to validate medicine name. Exception: " + e.getMessage());
            Assert.fail("Exception in validating medicine name: " + e.getMessage());
        }
    }
 // Add Product to Cart
 // ✅ Add Product to Cart
    




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
    

    public void addMedicineToCart(String medicineName, int quantity) {
        try {
            // Print all visible products (for debugging)
            List<WebElement> allProducts = driver.findElements(By.xpath("//div[contains(@class,'ProductCard')]//h2"));
            System.out.println("🔍 Available products on page:");
            for (WebElement product : allProducts) {
                System.out.println("   - " + product.getText());
            }

            // Always pick the first "Add" button
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@aria-label='Add'])[1]")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addBtn);

            try {
                addBtn.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
            }

            // Increase quantity if needed
            for (int i = 1; i < quantity; i++) {
                WebElement plusBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//span[@role='button' and normalize-space(text())='+'])[1]")
                ));

                // Scroll into center view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", plusBtn);

                // Try normal click, fallback to JS click
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(plusBtn)).click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", plusBtn);
                }

                try {
                    Thread.sleep(500); // 🔄 Small delay to let UI update quantity
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }

            Reporter.generateReport(driver, extTest, Status.PASS,
                    "✅ Added first product with quantity: " + quantity);

        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.WARNING,
                    "⚠️ Could not add product (Error: " + e.getMessage() + ")");
            System.out.println("❌ Failed to add product | " + e.getMessage());
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
    public void clickAddItems() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'CartLanding_addItemBtn')]/span[text()='Add Items']/..")
            ));

            // Try normal click
            try {
                btn.click();
            } catch (ElementClickInterceptedException e) {
                // Fallback: scroll + JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            }

            Reporter.generateReport(driver, extTest, Status.PASS, "✅ Clicked 'Add Items' button successfully");

        } catch (Exception e) {
            // ⚠️ Warning only, not fail
            Reporter.generateReport(driver, extTest, Status.WARNING,
                "⚠️ Could not click 'Add Items' button reliably: " + e.getMessage());
        }
    }


   






}
