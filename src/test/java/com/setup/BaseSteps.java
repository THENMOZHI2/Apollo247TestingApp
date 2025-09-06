package com.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseSteps {
    // Each thread gets its own WebDriver instance for parallel tests
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void launchBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
