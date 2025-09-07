package com.stepDefinition;

import com.pages.LoginPage;
import com.setup.BaseSteps;
import io.cucumber.java.en.*;
import org.testng.Assert;
import java.util.Scanner;

public class LoginSteps {

    LoginPage loginPage = new LoginPage(BaseSteps.driver);

    @When("user clicks on Login icon")
    public void user_clicks_on_login_icon() {
        loginPage.clickLoginIcon();
    }

    @When("user enters valid phone number")
    public void user_enters_valid_phone_number() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your phone number: ");
        String phoneNumber = sc.nextLine();
        loginPage.enterPhoneNumber(phoneNumber);
    }

    @When("user clicks Continue button")
    public void user_clicks_continue_button() {
        loginPage.clickContinueBtn();
    }

    @When("user enters the OTP received")
    public void user_enters_the_otp_received() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter OTP received: ");
        String otp = sc.nextLine();
        loginPage.enterOtpFromConsole(otp);
    }

    @When("user clicks Verify OTP button")
    public void user_clicks_verify_otp_button() {
        loginPage.clickVerifyOtpBtn();
    }

    @Then("user should be navigated to the Home Page")
    public void user_should_be_navigated_to_the_home_page() {
        Assert.assertTrue(loginPage.isHomePageDisplayed(), "Home page not displayed after login!");
    }
}

