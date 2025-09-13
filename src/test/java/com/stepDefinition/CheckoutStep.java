package com.stepDefinition;

import com.pages.ProfilePage;
import com.setup.Base;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

public class CheckoutStep extends Base {
   
    WebDriver driver = getDriver();
    ProfilePage profilePage =Hooks.profilepage;

    @When("user clicks first proceed button")
    public void user_clicks_first_proceed_button() {
        profilePage.clickFirstProceed();
    }

    @When("user clicks second proceed button")
    public void user_clicks_second_proceed_button() {
        profilePage.clickSecondProceed();
    }

    @Then("user should see the payments tab")
    public void user_should_see_the_payments_tab() {
        profilePage.validatePatientAdded(); // This already asserts Payments tab is visible
    }
}
