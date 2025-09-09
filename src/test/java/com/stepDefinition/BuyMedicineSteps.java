package com.stepDefinition;

import io.cucumber.java.en.*;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.stepDefinition.Hooks;
import com.aventstack.extentreports.ExtentTest;
import com.pages.BuyMedicinePage;
import com.setup.PropertyReader;

public class BuyMedicineSteps {
	BuyMedicinePage buyMedicinePage = Hooks.buymedicinePage;
   
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
        Assert.assertTrue(buyMedicinePage.validateBuyMedicinesTitle(), "Buy Medicines page not loaded");
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
        Assert.assertTrue(buyMedicinePage.validateProductAvailability(), "Product not available: " + medicine);
    }

    @And("user adds the product to cart and increases quantity by {int}")
    public void user_adds_the_product_to_cart_and_increases_quantity_by(Integer times) {
        
        buyMedicinePage.addProductToCart(times);
    }

    @And("user clicks on View Cart button")
    public void user_clicks_on_view_cart_button() {
        buyMedicinePage.clickViewCart();
    }
  
    
}


