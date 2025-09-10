package com.stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pages.BuyMedicinePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchInvalidMedicineStep {



    WebDriver driver = Hooks.driver;
    BuyMedicinePage buyMedicinePage = Hooks.buymedicinePage;

    @When("user clicks on Buy Medicines tab")
    public void user_clicks_on_buy_medicines_tab() {
        buyMedicinePage.clickBuyMedicinesTab();
    }

    @Then("user should see Buy Medicines and Essentials page")
    public void user_should_see_buy_medicines_and_essentials_page() {
        boolean isPageDisplayed = buyMedicinePage.validateBuyMedicinesTitle();
        Assert.assertTrue(isPageDisplayed, "Buy Medicines page is not displayed!");
    }

    @When("user searches for medicine {string}")
    public void user_searches_for_medicine(String medicineName) {
       
        buyMedicinePage.searchMedicine(medicineName);
    }
    @Then("medicine should not be available in search results")
    public void medicine_should_not_be_available_in_search_results() {
        boolean messageDisplayed = buyMedicinePage.isMedicineNotFoundMessageDisplayed();
        Assert.assertTrue(messageDisplayed, "Medicine not found message was not displayed!");
    }



}
