package com.stepDefinition;

import com.pages.HomePage;
import com.setup.BaseSteps;
import io.cucumber.java.en.Then;

public class LaunchSteps {

    @Then("The Apollo homepage should be displayed")
    public void the_homepage_should_be_displayed() {
        HomePage homePage = new HomePage(BaseSteps.driver);
        if (homePage.isHomePageDisplayed()) {
            System.out.println("Homepage displayed successfully!");
        } else {
            throw new AssertionError("Homepage NOT displayed!");
        }
    }
}
