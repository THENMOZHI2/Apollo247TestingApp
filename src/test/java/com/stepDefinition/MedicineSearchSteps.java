package com.stepDefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.pages.MedicineSearchPage;
import com.parameters.ExcelReader;
import com.stepDefinition.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MedicineSearchSteps {

    String[][] testData = ExcelReader.readdata();
    String medicineName = "";
    MedicineSearchPage medicineSearchPage = Hooks.medicineSearchPage;

    @When("the user enters numeric value  in the search box")
    public void the_user_enters_numeric_value_in_the_search_box() {
        // Write code here that turns the phrase above into concrete actions
    	 medicineName = testData[2][2];  // Row 3 → numeric input
         medicineSearchPage.enterMedicineInSearchBox(medicineName, Hooks.extTest);
    
    }


    @When("the user enters special characters in the search box")
    public void the_user_enters_special_characters_in_the_search_box() {
        medicineName = testData[3][2];  // Row 4 → special chars
        medicineSearchPage.enterMedicineInSearchBox(medicineName, Hooks.extTest);
    }

    @When("user searches for a medicine from test data marked as out of stock")
    public void user_searches_for_a_medicine_from_test_data_marked_as_out_of_stock() {
        // Pick the last non-empty medicine name from the Excel data
        for (int i = testData.length - 1; i >= 0; i--) {
            if (testData[i][2] != null && !testData[i][2].trim().isEmpty()) {
                medicineName = testData[i][2].trim();
                break;
            }
        }

        // Normalize (remove non-breaking spaces)
        medicineName = medicineName.replace("\u00A0", " ").trim();

        System.out.println("Out of stock medicine from Excel: " + medicineName);
        medicineSearchPage.enterMedicineInSearchBox(medicineName, Hooks.extTest);
    }

    @And("user applies the {string} filter")
    public void user_applies_the_filter(String filter) {
        medicineSearchPage.applyFilters(filter, Hooks.extTest);
    }
    @Then("the system should display an error message")
    public void the_system_should_display_an_error_message() {
        // Re-check with the last searched medicine
    	  medicineSearchPage.validateNoSearchResultsError(Hooks.extTest);
    }
   

    @When("the user enters valid medicine name in the search box")
    public void the_user_enters_valid_medicine_name_in_the_search_box() {
        // Write code here that turns the phrase above into concrete actions
    	  medicineName = testData[0][2];
    	  medicineSearchPage.enterMedicineInSearchBox(medicineName, Hooks.extTest);
        
    }
    @Then("the system should display medicine name")
    public void the_system_should_display_medicine_name() {
        String expectedMedicineName="Paracip-650 Tablet 10's";
;
		// Write code here that turns the phrase above into concrete actions
    	medicineSearchPage.validateMedicineNameDisplayed(expectedMedicineName, Hooks.extTest);
    }
    
    @When("user adds the product to cart and increases quantity by {int}")
    public void user_adds_the_product_to_cart_and_increases_quantity_by(Integer quantity) {
        String medicineName =  testData[0][2];
        // Example: row 0, column 2 → "Thyronorm 50 mcg Tablet 120's"
        
        medicineSearchPage.addMedicineToCart(medicineName, quantity);
    }


    @When("user clicks on View Cart button after adding products")
    public void user_clicks_on_view_cart_button_after_adding_products() {
        medicineSearchPage.clickViewCart();
    }

    @Then("user should see the My Cart page")
    public void user_should_see_the_my_cart_page() {
        medicineSearchPage.validateMyCartPage();
    }

    @When("user clicks on {string} button in my cart")
    public void user_clicks_on_button_in_my_cart(String string) {
        

         if (string.equalsIgnoreCase("Add Items")) {
             // Just call the method; assertions (if any) are inside the page method
             medicineSearchPage.clickAddItems();
         } else {
             Assert.fail("Button not implemented: " + string);
         }
    }
    @When("user clicks add items in my cart")
    public void user_clicks_add_items_in_my_cart() {
        medicineSearchPage.clickAddItems();
    }
   

    





}
