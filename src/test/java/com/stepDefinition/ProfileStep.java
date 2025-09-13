package com.stepDefinition;

import com.pages.ProfilePage;

import io.cucumber.java.en.*;

public class ProfileStep {

    ProfilePage profilePage = Hooks.profilepage;

    // ==================== Navigation Steps ====================

    @When("user clicks first proceed button")
    public void user_clicks_first_proceed_button() {
        profilePage.clickFirstProceed();
    }

    @When("user clicks second proceed button")
    public void user_clicks_second_proceed_button() {
        profilePage.clickSecondProceed();
    }

    @When("user clicks skip savings")
    public void user_clicks_skip_savings() {
        profilePage.clickSkipSavings();
    }

    @Then("user should see the upload prescription tab")
    public void user_should_see_the_upload_prescription_tab() {
        profilePage.validateUploadPrescription();
    }

    @Then("user clicks Add patient button")
    public void user_clicks_add_patient_button() {
        profilePage.clickAddPatient();
    }

    // ==================== Valid Input Flow ====================

    @When("the user enters the first name as {string}")
    public void the_user_enters_the_first_name_as(String fname) {
        profilePage.enterFirstName(fname);
    }

    @When("the user enters the last name as {string}")
    public void the_user_enters_the_last_name_as(String lname) {
        profilePage.enterLastName(lname);
    }

    @When("the user enters the dob as {string}")
    public void the_user_enters_the_dob_as(String dob) {
        profilePage.enterDOB(dob);
    }

    @When("the user choose the gender")
    public void the_user_choose_the_gender() {
        profilePage.chooseGender();
    }

    @When("the user choose the relation")
    public void the_user_choose_the_relation() {
        profilePage.chooseRelation();
    }

    @When("the user clicks on save and confirm button")
    public void the_user_clicks_on_save_and_confirm_button() {
        profilePage.clickSaveAndConfirm();
    }

    @Then("the new patient is added successfully")
    public void the_new_patient_is_added_successfully() {
        profilePage.validatePatientAdded();
    }

    // ==================== Invalid Input Flow ====================

    @When("the user enters the invalid first name as {string}")
    public void the_user_enters_the_invalid_first_name_as(String invalidFirstName) {
        profilePage.enterInvalidFirstName(invalidFirstName);
    }

    @When("the user enters the invalid last name as {string}")
    public void the_user_enters_the_invalid_last_name_as(String invalidLastName) {
        profilePage.enterInvalidLastName(invalidLastName);
    }

    @When("the user enters the invalid dob as {string}")
    public void the_user_enters_the_invalid_dob_as(String invalidDob) {
        profilePage.enterInvalidDOB(invalidDob);
    }

    @When("the user enters the invalid email as {string}")
    public void the_user_enters_the_invalid_email_as(String invalidEmail) {
        profilePage.enterInvalidEmail(invalidEmail);
    }
}

