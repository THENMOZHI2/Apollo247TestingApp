package com.pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.setup.Base;
import com.setup.PropertyReader;
import com.setup.Reporter;
import com.stepDefinition.Hooks;

public class LaunchBrowserPage {
	 WebDriver driver;
	 WebDriverWait wait;
	 ExtentTest extTest;
	 ExtentReports extReports;
	 Properties prop = PropertyReader.readProperties();
	 
	 public LaunchBrowserPage(WebDriver driver,ExtentTest extTest) {
		 this.driver = Base.driver;
		 wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		 this.extTest = Hooks.extTest;
	 }
	 
	 public void openwebsite() {
		 try {
				driver.get(prop.getProperty("URL"));
				Reporter.generateReport(driver,extTest,Status.PASS," Apollo 24/7 Website Opened");
				}
				catch(TimeoutException te) {
					//fail the extent report
					Reporter.generateReport(driver,extTest,Status.FAIL,"Apollo 24/7 Website not opened");
				}
	 }
	 
	 public boolean homepage() {
		 
		 try {
			String currentUrl = driver.getCurrentUrl();
		    Assert.assertTrue(currentUrl.contains("apollo247"), 
		        "Expected Apollo URL but found: " + currentUrl);
		    Reporter.generateReport(driver,extTest,Status.PASS,"Correct Apollo 24/7 is Website Opened");
			}
			catch(TimeoutException te) {
				//fail the extent report
				Reporter.generateReport(driver,extTest,Status.FAIL,"Incorrect Website Opened");
			}
			return true ;
	 }
}

