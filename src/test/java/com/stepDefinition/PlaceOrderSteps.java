package com.stepDefinition;

import io.cucumber.java.en.*;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectRepository.Locators;
import com.pages.AddressDetails;
import com.pages.BuyMedicinePage;
import com.pages.PaymentPage;
import com.pages.PrescriptionPage;
import com.pages.ViewCartPage;
import com.parameters.ExcelReader;
import com.setup.PropertyReader;
import com.setup.Reporter;
import com.stepDefinition.Hooks;

public class PlaceOrderSteps {

    BuyMedicinePage buyMedicinePage = Hooks.buymedicinePage;
    ViewCartPage viewCartPage = Hooks.viewCartPage;
    AddressDetails addressDetails=Hooks.addressDetails;
    PrescriptionPage prescriptionpage=Hooks.prescriptionpage;
    PaymentPage paymentpage=Hooks.paymentPage;

    WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    static String[][] excelData;
    Properties prop = PropertyReader.readProperties();
    
    

    @When("user clicks on Buy Medicines tab")
    public void user_clicks_on_buy_medicines_tab() {
        buyMedicinePage.clickBuyMedicinesTab();
    }

    @Then("user should see Buy Medicines and Essentials page")
    public void user_should_see_buy_medicines_and_essentials_page() {
        boolean result = buyMedicinePage.validateBuyMedicinesTitle();
        if (result) {
            Reporter.generateReport(driver, extTest, Status.PASS, "Buy Medicines page loaded successfully");
        } else {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Buy Medicines page not loaded");
        }
        Assert.assertTrue(result, "Buy Medicines page not loaded");
    }

    @When("user searches for medicine {string}")
    public void user_searches_for_medicine(String medicine) {
        buyMedicinePage.searchMedicine(medicine);
    }

    @And("user applies in-stock and brand filters")
    public void user_applies_in_stock_and_brand_filters() {
        buyMedicinePage.applyFilters();
    }

    @Then("medicine {string} should be available")
    public void medicine_should_be_available(String medicine) {
        boolean result = buyMedicinePage.validateProductAvailability();
        if (result) {
            Reporter.generateReport(driver, extTest, Status.PASS, "Medicine available: " + medicine);
        } else {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Medicine not available: " + medicine);
        }
        Assert.assertTrue(result, "Product not available: " + medicine);
    }

    @And("user adds the product to cart and increases quantity by {int}")
    public void user_adds_the_product_to_cart_and_increases_quantity_by(Integer times) {
        buyMedicinePage.addProductToCart(times);
    }

    @And("user clicks on View Cart button")
    public void user_clicks_on_view_cart_button() {
        buyMedicinePage.clickViewCart();
    }

    @Then("user should see the My cart page")
    public void user_should_see_the_my_cart_page() {
        boolean result = viewCartPage.validateMyCartPage();
        if (result) {
            Reporter.generateReport(driver, extTest, Status.PASS, "My Cart page is visible");
        } else {
            Reporter.generateReport(driver, extTest, Status.FAIL, "My Cart page not visible");
        }
        Assert.assertTrue(result, "My Cart page not visible");
    }

    @Then("user clicks on change address")
    public void user_clicks_on_change_address() {
        viewCartPage.clickChangeAddress();
    }

    

    @Then("user clicks on the Add new Address")
    public void user_clicks_on_the_add_new_address() {
        viewCartPage.clickAddNewAddress();
    }
    @Then("user should see the Deliver To Tab")
    public void user_should_see_the_deliver_to_tab() {
        boolean result = viewCartPage.validateDeliverToTab();
        if (result) {
            Reporter.generateReport(driver, extTest, Status.PASS, "Deliver To tab is visible");
        } else {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Deliver To tab not visible");
        }
        Assert.assertTrue(result, "Deliver To tab not visible");
    }

    @Then("user enter the valid pincode and select the pincode")
    public void user_enter_the_valid_pincode_and_select_the_pincode() {
        try {
            // Load Excel data if null
            if (excelData == null) {
                excelData = ExcelReader.readdata();
            }
            String pincode = excelData[0][1]; // get pincode from Excel
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // 1️⃣ Wait until the pincode input is clickable
            WebElement pinBox = wait.until(ExpectedConditions.elementToBeClickable(Locators.pincodeSearchBox));
            pinBox.clear();
            pinBox.sendKeys(pincode);

            // 2️⃣ Small pause to allow dropdown to populate
            Thread.sleep(1000);

            // 3️⃣ Wait for the dropdown options to be present
            List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[contains(@class,'NewSearchLocationSuggestor_searchItemList')]")));

            // 4️⃣ Filter visible options
            WebElement firstVisibleOption = options.stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("No visible pincode dropdown options found"));

            // 5️⃣ Click the first visible option
            firstVisibleOption.click();

            System.out.println("Pincode entered and dropdown option selected successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter pincode and select dropdown option. " + e.getMessage());
        }

    }

    @And("user enter the house number and clicks save and next")
    public void user_enter_the_house_number_and_clicks_save_and_next() {
        addressDetails.enterHouseNumberAndSaveNext("123");
    }

    @And("user fill the recipient details and save address")
    public void user_fill_the_recipient_details_and_save_address() {
        String recipientName = "John Doe";
        addressDetails.fillRecipientDetailsAndSave(recipientName);
    }

    @And("user clicks first proceed button")
    public void user_clicks_first_proceed_button() {
        addressDetails.clickFirstProceed();
    }

    @And("user clicks Second proceed button")
    public void user_clicks_second_proceed_button() {
        addressDetails.clickSecondProceed();
    }

    @And("user clicks skip savings")
    public void user_clicks_skip_savings() {
        addressDetails.clickSkipSavings();
    }

    @Then("user should see the upload prescription tab")
    public void user_should_see_the_upload_prescription_tab() {
        addressDetails.validateUploadPrescriptionTab();
    }

    @And("user clicks Add patient button")
    public void user_clicks_add_patient_button() {
        prescriptionpage.addPatient();
    }

    @And("user fill the patient form details")
    public void user_fill_the_patient_form_details() {
        prescriptionpage.fillPatientForm();
    }

    @And("user clicks save button and confirm button")
    public void user_clicks_save_button_and_confirm_button() {
        prescriptionpage.saveAndConfirm();
    }

    @And("user clicks proceed(first one) and proceed(second one)")
    public void user_clicks_proceed_first_one_and_proceed_second_one() {
        prescriptionpage.proceed();
    }

    @Then("user should see the payments tab")
    public void user_should_see_the_payments_tab() {
        boolean isVisible = paymentpage.isPaymentTabDisplayed();
        Assert.assertTrue(isVisible, "Payments tab is not visible");
    }



    
   
   
    
}


