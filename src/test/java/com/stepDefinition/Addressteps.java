package com.stepDefinition;

import com.pages.AddressPage;

import io.cucumber.java.en.*;

public class Addressteps {

    AddressPage addressPage = Hooks.addressPage;

    // ----------- Invalid Pincode -----------
    @Given("user clicks on change")
    public void user_clicks_on_change() {
        addressPage.clickChangeButton(Hooks.extTest);
    }

    @When("user validates deliver to tab")
    public void user_validates_deliver_to_tab() {
        addressPage.validateSidebar(Hooks.extTest);
    }

    @And("user clicks on add button")
    public void user_clicks_on_add_button() {
        addressPage.clickAddButton(Hooks.extTest);
    }

    @Then("user types invalid pincode and validates that no results found")
    public void user_types_invalid_pincode_and_validates_no_results_found() {
        addressPage.enterInvalidPincodeAndValidate(Hooks.extTest);
    }


    // ----------- Flat Number Validation -----------
    @When("user enters the valid pincode from text data")
    public void user_enters_the_valid_pincode_from_text_data() {
    	addressPage.enterValidPincode("600003", Hooks.extTest);

    }

    @And("user enters a single character flat number")
    public void user_enters_a_single_character_flat_number() {
        addressPage.enterSingleCharFlatNumber(Hooks.extTest);
        addressPage.clickSaveAndNext(Hooks.extTest);  // trigger validation
    }

    @Then("user validates that minimum two characters are required")
    public void user_validates_that_minimum_two_characters_are_required() {
        addressPage.validateMinTwoCharError(Hooks.extTest);
    }


    // ----------- Invalid Recipient Name -----------
    @When("user enters valid character flat number and clicks save")
    public void user_enters_valid_character_flat_number_and_clicks_save() {
        addressPage.enterValidFlatNumberAndSave(Hooks.extTest);
    }

    @Then("user sees an information message that home address type must be selected")
    public void user_sees_information_message_that_home_address_type_must_be_selected() {
    	addressPage.clickHomeAddressType(Hooks.extTest);
    }

    @And("user enters an invalid recipient name {string}")
    public void user_enters_an_invalid_recipient_name(String name) {
        addressPage.enterRecipientName(name, Hooks.extTest);
    }

    @And("user clicks on Save Address")
    public void user_clicks_on_save_address() {
        addressPage.clickSaveAddress(Hooks.extTest);
    }

   }

