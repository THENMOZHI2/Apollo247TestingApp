package com.pages;

import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectRepository.Locators;
import com.setup.Base;
import com.setup.PropertyReader;
import com.setup.Reporter;
import com.stepDefinition.Hooks;

public class LoginPage{
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest extTest;
	ExtentReports extReports;
	String mobile_number;
	String invalidmobile_no;
	String invalidotp;
	String validotp;
	Scanner scanner = new Scanner(System.in);
	Properties prop = PropertyReader.readProperties();
	
	public LoginPage(WebDriver driver, ExtentTest extTest2) {
		this.driver = Base.driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		this.extTest = extTest2;
	}
	
	public void clickloginbutton() {
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(Locators.login)).click();
	}
	
	public void entermobilenumber(String mnumber) {
		
		try {
		
		WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.mobileNumberEntry));
		mobile.click();
		mobile_number = mnumber;
		mobile.sendKeys(mobile_number);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Locators.continueButton)).click();	
		Reporter.generateReport(driver,extTest,Status.PASS,"Valid mobile number is accepted");
		}
		catch(TimeoutException te) {
			Reporter.generateReport(driver,extTest,Status.FAIL,"Valid mobile number is not acccepted");
		}
		
		
	}
	public void clickcontinuebutton() {
		driver.findElement(Locators.continueButton).click();
	}
	
//	public void enterotp(String otp) {
//		try {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otpentry));
//        driver.findElement(Locators.otpentry).sendKeys(otp);
//		driver.findElement(Locators.verifybutton).click();
//		Reporter.generateReport(driver,extTest,Status.PASS,"Valid otp number is accepted");
//		}
//		catch(TimeoutException te) {
//			//fail the extent report
//			Reporter.generateReport(driver,extTest,Status.FAIL,"Valid otp number is not acccepted");
//		}
//	}
	
	public void enterotp() {
		
	    try {
	        System.out.println("Enter the OTP received (you have 40 seconds): ");
	        String userInput = waitForConsoleInput(40);  // first 40s

	        if (userInput == null || userInput.isEmpty()) {
	            // No OTP typed in console → click resend
	            WebElement resendBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.resendbtn));
	            resendBtn.click();
	            Reporter.generateReport(driver, extTest, Status.INFO, "No OTP entered in 40s. Resend OTP clicked.");

	            // ✅ wait again for OTP after resend
	            System.out.println("Enter the OTP received after resend (you have 40 seconds): ");
	            userInput = waitForConsoleInput(40);
	        }

	        if (userInput == null || userInput.isEmpty()) {
	            Reporter.generateReport(driver, extTest, Status.FAIL, "No OTP entered even after resend.");
	            return;
	        }

	        // ✅ send OTP to webpage
	        validotp = userInput;
	        WebElement otp_input = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otpentry));
	        otp_input.click();
	        otp_input.sendKeys(validotp);
	        driver.findElement(Locators.verifybutton).click();

	        Reporter.generateReport(driver, extTest, Status.PASS, "Valid OTP entered and verified");

	    } catch (TimeoutException te) {
	        Reporter.generateReport(driver, extTest, Status.FAIL, "OTP input field not available");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Utility method: waits for console input for given seconds.
	 */
	private String waitForConsoleInput(int seconds) throws Exception {
	    long startTime = System.currentTimeMillis();
	    String input = null;

	    while ((System.currentTimeMillis() - startTime) < seconds * 1000L) {
	        if (System.in.available() > 0) {
	            input = scanner.nextLine().trim();
	            if (!input.isEmpty()) {
	                break;
	            }
	        }
	        Thread.sleep(500); // check every 0.5s
	    }
	    return input;
	}
	
	public void validatelogin() {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.apolloLogo));
		//WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.mobilefield));
		Reporter.generateReport(driver,extTest,Status.PASS,"User log in success");
		
	  }
		catch (TimeoutException te) {
			Reporter.generateReport(driver,extTest,Status.FAIL,"User log in failed");
		}
	}
	
	public void enterinvalidmobilenumber(String invalidmobile_number) {
		try {
			
			WebElement invalidmobile = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.mobileNumberEntry));
			invalidmobile.click();
			invalidmobile_no = invalidmobile_number;
			invalidmobile.sendKeys(invalidmobile_no);
			String errmsg = driver.findElement(Locators.invalidMobileErrorMsg ).getText();	
			Reporter.generateReport(driver,extTest,Status.FAIL,errmsg);
			driver.findElement(Locators.closebtn).click();
			}
			catch(TimeoutException te) {
				Reporter.generateReport(driver,extTest,Status.PASS,"Invalid mobile number accepted");
			}
	}
	
	public void enterinvalidotp() {
		try {
			System.out.print("Enter the otp : ");
			invalidotp = scanner.next();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otpentry)).sendKeys(invalidotp);
			driver.findElement(Locators.verifybutton).click();
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			Reporter.generateReport(driver,extTest,Status.FAIL,"Invalid otp number is not accepted");
			driver.findElement(Locators.closebtn).click();
		}
		catch (TimeoutException te) {
			Reporter.generateReport(driver,extTest,Status.PASS,"Invalid otp number is acccepted");
			
			
		}
	}

}


