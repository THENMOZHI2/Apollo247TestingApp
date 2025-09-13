package com.stepDefinition;

import com.pages.ProfilePage;
import com.parameters.ExcelReader;
import com.setup.Base;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

public class ProfileStep extends Base {

    WebDriver driver = getDriver();
    ProfilePage profilePage =Hooks.profilepage;

    // Load Excel data once
    static String[][] data = ExcelReader.readdata();

    // === Negative Scenario ===
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

    @When("the user enters invalid data from excel row {int}")
    public void the_user_enters_invalid_data_from_excel_row(Integer rowIndex) {
        String fname = data[rowIndex][3];
        String lname = data[rowIndex][4];
        String dob   = data[rowIndex][5];

        profilePage.enterInvalidFirstName(fname);
        profilePage.enterInvalidLastName(lname);
        profilePage.enterInvalidDOB(dob);
        profilePage.enterInvalidEmail("@@@@");  // Hardcoded invalid email
    }

    // === Positive Scenario ===
    @When("the user enters valid data from excel row {int}")
    public void the_user_enters_valid_data_from_excel_row(Integer rowIndex) {
        String fname = data[rowIndex][3];
        String lname = data[rowIndex][4];
        String dob   = data[rowIndex][5];

        profilePage.enterFirstName(fname);
        profilePage.enterLastName(lname);
        profilePage.enterDOB(dob);
        profilePage.chooseGender();
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
}
