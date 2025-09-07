package com.stepDefinition;

import com.setup.BaseSteps;
import com.utility.ConfigReader;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class Hooks {

    private static boolean isBrowserLaunched = false;

    @BeforeAll
    public static void setUp() {
        if (!isBrowserLaunched) {
            ConfigReader.loadConfig();
            String browser = ConfigReader.getProperty("browser");
            String url = ConfigReader.getProperty("url");

            BaseSteps.initializeBrowser(browser, url);
            isBrowserLaunched = true;
        }
    }

    @AfterAll
    public static void tearDown() {
        if (BaseSteps.driver != null) {
            BaseSteps.driver.quit();
        }
    }
}

