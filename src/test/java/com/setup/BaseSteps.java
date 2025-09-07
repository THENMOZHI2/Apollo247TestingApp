package com.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseSteps {

    public static WebDriver driver;

    public static void initializeBrowser(String browser, String url) {
        if (driver == null) { // Only initialize once
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver(new ChromeOptions());
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver(new FirefoxOptions());
            } else {
                throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            driver.manage().window().maximize();
            driver.get(url);
        }
    }
}




