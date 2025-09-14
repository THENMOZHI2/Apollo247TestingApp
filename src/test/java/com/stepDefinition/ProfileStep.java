package com.stepDefinition;

import com.pages.ProfilePage;
import com.parameters.ExcelReader;
import io.cucumber.java.en.*;

public class ProfileStep {

    ProfilePage profilePage = Hooks.profilepage;

    // ==================== Navigation Steps ====================

    @When("user clicks first proceed button")
    public void user_clicks_first_proceed_button() {
        profilePage.clickFirstProceed();
    }

    @When("user clicks Second proceed button")
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

    @And("user clicks Add patient button")
    public void user_clicks_add_patient_button() {
        profilePage.clickAddPatient();
    }

    // ==================== VALID INPUT FLOW ====================

    @When("the user enters the first name as {string}")
    public void the_user_enters_the_first_name_as(String firstname) {
        profilePage.enterFirstName("Thenmozhi");
    }

    @When("the user enters the last name as {string}")
    public void the_user_enters_the_last_name_as(String lastname) {
        profilePage.enterLastName("Nagaiya");
    }

    @When("the user enters the dob as {string}")
    public void the_user_enters_the_dob_as(String dob) {
        profilePage.enterDOB("01/06/2004");
    }

    @When("the user enters the email as {string}")
    public void the_user_enters_the_email_as(String email) {
        profilePage.enterEmail("thenmozhiuma0@gmail.com");
    }

    @When("the user choose the gender")
    public void the_user_choose_the_gender() {
        profilePage.chooseGender(); // always Female
    }

    @When("the user choose the relation")
    public void the_user_choose_the_relation() {
        profilePage.chooseRelation(); // always Daughter
    }

    @When("the user clicks on save and confirm button")
    public void the_user_clicks_on_save_and_confirm_button() {
        profilePage.clickSaveAndConfirm();
    }

    

    // ==================== INVALID INPUT FLOW ====================

    @When("the user enters the invalid first name as {string}")
    public void the_user_enters_the_invalid_first_name_as(String invalidFirstName) {
        String[][] data = ExcelReader.readdata();
        profilePage.enterInvalidFirstName(data[1][3]);   // Row 1 → invalid
    }

    @When("the user enters the invalid last name as {string}")
    public void the_user_enters_the_invalid_last_name_as(String invalidLastName) {
        String[][] data = ExcelReader.readdata();
        profilePage.enterInvalidLastName(data[1][4]);   // Row 1 → invalid
    }

    @When("the user enters the invalid dob as {string}")
    public void the_user_enters_the_invalid_dob_as(String invalidDob) {
        String[][] data = ExcelReader.readdata();
        profilePage.enterInvalidDOB(data[1][5]);        // Row 1 → invalid
    }

    @When("the user enters the invalid email")
    public void the_user_enters_the_invalid_email() {
        profilePage.enterInvalidEmail("$$124@@");       // Hardcoded invalid
    }
}


