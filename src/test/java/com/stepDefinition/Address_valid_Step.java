package com.stepDefinition;

import io.cucumber.java.en.*;
import com.pages.AddressPage;

public class Address_valid_Step {

    private AddressPage addressPage;

    public Address_valid_Step() {
        // ✅ Reuse AddressPage instance from Hooks
        this.addressPage = Hooks.addressPage;
    }

    @When("user clicks on change in my cart")
    public void user_clicks_on_change_in_my_cart() {
        addressPage.clickChangeAddress();
    }

    @When("user see the deliverto and click add new address")
    public void user_see_deliver_to_and_click_add_new_address() {
        addressPage.validateDeliverToHeader();
        addressPage.clickAddNewAddress();
    }

    @And("user selects the address by typing in the textarea")
    public void user_selects_address_in_textarea() {
        addressPage.typeAndSelectAddress();
    }

    @And("user validates the enter address details page")
    public void user_validates_enter_address_details_page() {
        addressPage.validateEnterAddressDetails();
    }

    @And("user enters house number and and click save and next")
    public void user_enters_house_number_and_click_save_and_next() {
        addressPage.enterHouseNumberAndSaveNext();
    }

    @And("choose save the address as")
    public void choose_save_the_address_as() {
        addressPage.chooseSaveAddressAs();
    }

    @And("user enters recipient and click save address")
    public void user_enters_recipient_and_click_save_address() {
        addressPage.enterRecipientAndSave();
    }

    @Then("user validates after clicking the page returned to cart page")
    public void user_validates_returned_to_cart_page() {
        addressPage.validateCartPageReturned();
    }
}
