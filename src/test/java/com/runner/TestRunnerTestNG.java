package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		  features = {
			        "src\\test\\resources\\Features",
    },
	glue="com.stepDefinition",
	plugin= {"pretty","html:reports/Cucumber-html-report.html"},
	tags="not @Ignore"
	
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

}