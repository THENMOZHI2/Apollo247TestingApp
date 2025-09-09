package com.setup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;

public class Reporter {

    public static void generateReport(WebDriver driver, ExtentTest test, Status status, String stepName) {
        try {
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            // File name with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + stepName + "_" + timestamp + ".png";

            File dest = new File(screenshotPath);
            FileUtils.copyFile(src, dest);

            // Log in Extent report with screenshot
            test.log(status, stepName);
            test.addScreenCaptureFromPath(screenshotPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
