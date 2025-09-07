
package com.stepDefinition;

import com.pages.BuyMedicinePage;
import com.setup.BaseSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class BuyMedicineSteps {

    BuyMedicinePage buyMedicinePage = new BuyMedicinePage(BaseSteps.driver);

    @When("user clicks on Buy Medicine tab")
    public void user_clicks_on_buy_medicine_tab() {
        buyMedicinePage.clickBuyMedicineTab();
    }

    @Then("Buy Medicines page should be displayed")
    public void buy_medicines_page_should_be_displayed() {
        Assert.assertTrue(buyMedicinePage.isBuyMedicineHeaderDisplayed(), "Buy Medicines page not displayed!");
    }

    @When("user searches for {string}")
    public void user_searches_for(String medicine) {
        buyMedicinePage.searchMedicine(medicine);
    }

    @When("user applies In-stock filter")
    public void user_applies_in_stock_filter() {
        buyMedicinePage.applyInStockFilter();
    }

    @When("user applies Dolo brand filter")
    public void user_applies_dolo_brand_filter() {
        buyMedicinePage.applyDoloBrandFilter();
    }

    @Then("product results should be displayed")
    public void product_results_should_be_displayed() {
        Assert.assertTrue(buyMedicinePage.isProductResultDisplayed(), "No product results displayed!");
    }

    @When("user adds the product to cart")
    public void user_adds_the_product_to_cart() {
        buyMedicinePage.clickAddButton();
    }

    @When("user increases quantity by {int}")
    public void user_increases_quantity_by(Integer qty) {
        buyMedicinePage.increaseQuantity(qty);
    }

    @When("user clicks on View Cart button")
    public void user_clicks_on_view_cart_button() {
        buyMedicinePage.clickViewCart();
    }
}
