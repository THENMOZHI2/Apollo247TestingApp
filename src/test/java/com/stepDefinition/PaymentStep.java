package com.stepDefinition;



import com.pages.PaymentPage;
import io.cucumber.java.en.*;

public class PaymentStep {

    PaymentPage paymentPage = Hooks.paymentpage;

    @When("user clicks third proceed button")
    public void user_clicks_third_proceed_button() {
        paymentPage.clickThirdProceed();
    }

    @And("user clicks fourth proceed button")
    public void user_clicks_fourth_proceed_button() {
        paymentPage.clickFourthProceed();
    }

    @Then("user should see the payments tab")
    public void user_should_see_the_payments_tab() {
        paymentPage.validatePaymentsTab();
    }
}
