package com.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
    
    public static WebDriver driver;
    static final int TIME = 2000;
    
    public static void launchBrowser() {
        Properties prop = PropertyReader.readProperties();
        String browser = prop.getProperty("Browser");
        String url = prop.getProperty("URL");

        if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_leak_detection", false);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        } 
        else if (browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        
        driver.get(url);
    }

    // ✅ Added this method
    public static WebDriver getDriver() {
        if (driver == null) {
            launchBrowser();
        }
        return driver;
    }

    public static void sleep() {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





