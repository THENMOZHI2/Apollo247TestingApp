package com.stepDefinition;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.pages.LaunchBrowserPage;
import com.setup.Base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LaunchBrowserStep {
	
	LaunchBrowserPage launchbrowserpage;
	WebDriver driver = Hooks.driver ;
	ExtentTest extTest = Hooks.extTest;
	
	
	@Given("user launch the browser")
	public void user_launch_the_browser() {
		
		System.out.print("Browser launched from the hooks class");
		
		launchbrowserpage = new LaunchBrowserPage(driver,extTest);
	    
	}
	@When("user open the website")
	public void user_open_the_website() {
		launchbrowserpage.openwebsite();

	}
	@Then("the browser should display the Apollo home Page")
	public void the_browser_should_display_the_apollo_home_page() {
		launchbrowserpage.homepage();
	    
	}

}