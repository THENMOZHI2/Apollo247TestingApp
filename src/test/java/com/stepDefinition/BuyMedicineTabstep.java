package com.stepDefinition;

import org.openqa.selenium.WebDriver;

import com.pages.BuyMedicinePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuyMedicineTabstep {

    WebDriver driver = Hooks.driver;
    BuyMedicinePage buyMedicinePage;

    @When("user clicks on Buy Medicines tab")
    public void user_clicks_on_buy_medicines_tab() {
        buyMedicinePage = new BuyMedicinePage(driver, Hooks.extTest);
        buyMedicinePage.clickBuyMedicinesTab();
    }

    @Then("user should see Buy Medicines and Essentials page")
    public void user_should_see_buy_medicines_and_essentials_page() {
        boolean isPageVisible = buyMedicinePage.validateBuyMedicinesTitle();
        if (!isPageVisible) {
            throw new AssertionError("Buy Medicines and Essentials page is not visible");
        }
    }
}
