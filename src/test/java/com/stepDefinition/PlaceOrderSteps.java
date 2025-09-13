/*package com.stepDefinition;

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
import com.pages.AddressPage;
import com.pages.BuyMedicinePage;
import com.pages.PaymentPage;
import com.pages.PrescriptionPage;

import com.parameters.ExcelReader;
import com.setup.PropertyReader;
import com.setup.Reporter;
import com.stepDefinition.Hooks;

public class PlaceOrderSteps {

    BuyMedicinePage buyMedicinePage = Hooks.buymedicinePage;
   
   
    PrescriptionPage prescriptionpage=Hooks.prescriptionpage;
    PaymentPage paymentpage=Hooks.paymentPage;

    WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    static String[][] excelData;
    Properties prop = PropertyReader.readProperties();
    
    

   

    

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
    
    
    @Then("error message {string} should be displayed for invalid pincode")
    public void error_message_should_be_displayed_for_invalid_pincode(String expectedMessage) {
        String actualMessage = addressDetails.getInvalidPincodeErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "Mismatch in error message for invalid pincode.");
        Reporter.generateReport(driver, extTest, Status.PASS, "Validated error message: " + actualMessage);
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


    @Then("error message {string} should be displayed")
    public void error_message_should_be_displayed1(String expectedMessage) {
        String actualMessage =  prescriptionpage.getFirstNameErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "First name error message mismatch!");
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
    public void user_fill_the_patient_form_details() throws Exception {
        prescriptionpage.fillPatientForm();
    }

  
   
    
    
    @Then("user clicks  proceed\\(first one) and proceed\\(second one)")
    public void user_clicks_proceed_first_one_and_proceed_second_one() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Proceed (first one)
            WebElement proceed1 = wait.until(ExpectedConditions.elementToBeClickable(Locators. proceedBtn1));
            proceed1.click();

            // Proceed (second one)
            WebElement proceed2 = wait.until(ExpectedConditions.elementToBeClickable(Locators. proceedBtn2));
            proceed2.click();

            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Proceed (first & second) successfully");
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Proceed buttons - " + e.getMessage());
            throw e;
        }
    }
    @Then("user should see the payments tab")
    public void user_should_see_the_payments_tab() {
        try {
            // Use locator from Locators class
            WebElement paymentsTab = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(Locators.paymentsTab));
            
            // Use isDisplayed() to verify
            if (paymentsTab.isDisplayed()) {
                System.out.println("Payments tab is visible");
            } else {
                System.out.println("Payments tab is NOT visible");
            }
        } catch (Exception e) {
            System.out.println("Payments tab not found: " + e.getMessage());
            throw e;
        }
    }
*/






    
    


