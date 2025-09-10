package com.stepDefinition;

import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentTest;
import com.pages.LoginPage;
import com.parameters.ExcelReader;
import com.setup.PropertyReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	LoginPage loginpage;
	WebDriver driver = Hooks.driver;
	ExtentTest extTest = Hooks.extTest;
	static String[][] excelData;
	Properties prop = PropertyReader.readProperties();

	
	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
		loginpage = new LoginPage(driver,Hooks.extTest);
		loginpage.clickloginbutton();
		
		if(excelData == null) {
			excelData = ExcelReader.readdata();
		}

	}
	@When("the user enter the valid Mobile Number as {string}")
	public void the_user_enter_the_valid_mobile_number_as(String number) {
		int row = Hooks.firstrow;
	    number = excelData[row][0];
		loginpage.entermobilenumber(number);
	}
	@When("user request an otp")
	public void user_request_an_otp() {
		//loginpage.clickcontinuebutton();
	    
	}
	@When("user enter the otp")
	public void user_enter_the_otp() {
		loginpage.enterotp();
	   
	}
	@Then("user should be successfully logged in")
	public void user_should_be_successfully_logged_in() {
	   loginpage.validatelogin();
	}
	
	@When("the user enter the invalid Mobile Number as {string}")
	public void the_user_enter_the_invalid_mobile_number_as(String invalidmobile_number) {
		int row = Hooks.firstrow;
	    invalidmobile_number = excelData[1][row];
	    loginpage.enterinvalidmobilenumber(invalidmobile_number);
	    
	}
	@Then("an error message is displayed {string}")
	public void an_error_message_is_displayed(String string) {
		
	}
	@When("the user enter the invalid otp")
	public void the_user_enter_the_invalid_otp() {
	   loginpage.enterinvalidotp();
	}

}