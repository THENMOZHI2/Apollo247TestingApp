package com.setup;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Reporter {

    public static void generateReport(WebDriver driver, ExtentTest test, Status status, String message) {
        try {
            if (driver != null) {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = "reports/screenshots/" + System.currentTimeMillis() + ".png";
                File dest = new File(path);
                FileHandler.createDir(new File("reports/screenshots/"));
                FileHandler.copy(src, dest);

                test.log(status, message).addScreenCaptureFromPath(path);
            } else {
                test.log(status, message + " (⚠️ no driver available)");
            }
        } catch (NoSuchWindowException e) {
            test.log(status, message + " (⚠️ browser already closed, no screenshot)");
        } catch (IOException e) {
            test.log(status, message + " (⚠️ screenshot save failed: " + e.getMessage() + ")");
        } catch (Exception e) {
            test.log(status, message + " (⚠️ unexpected error: " + e.getMessage() + ")");
        }
    }
}

