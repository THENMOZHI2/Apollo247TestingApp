package com.stepDefinition;

import com.pages.HomePage;
import com.setup.BaseSteps;
import com.utility.ConfigReader;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;

public class LaunchSteps extends BaseSteps {

    HomePage homePage;

    @Given("User opens the browser")
    public void user_opens_the_browser() {
        launchBrowser(ConfigReader.getProperty("browser"));
        System.out.println("Browser launched: " + ConfigReader.getProperty("browser"));
    }

    @When("User navigates to the application")
    public void user_navigates_to_the_application() {
        String url = ConfigReader.getProperty("url");
        getDriver().get(url);
        System.out.println(" Navigated to: " + url);
    }
    @Then("The Apollo 24\\/7 homepage should be displayed")
    public void the_homepage_should_be_displayed() {
        homePage = new HomePage(getDriver());
        if (homePage.isHomePageDisplayed()) {
            System.out.println("Homepage displayed successfully!");
        } else {
            throw new AssertionError("Homepage NOT displayed!");
        }
    }

}

