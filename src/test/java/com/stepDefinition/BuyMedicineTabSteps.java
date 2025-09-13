package com.stepDefinition;




import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuyMedicineTabSteps {

    @When("user clicks on Buy Medicines tab")
    public void user_clicks_on_buy_medicines_tab() {
        Hooks.buymedicinePage.clickBuyMedicinesTab();
    }

    @Then("user should see Buy Medicines and Essentials page")
    public void user_should_see_buy_medicines_and_essentials_page() {
        Hooks.buymedicinePage.validateBuyMedicinesTitle(Hooks.driver, Hooks.extTest);
    }
}
