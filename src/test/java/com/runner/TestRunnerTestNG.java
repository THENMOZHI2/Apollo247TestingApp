package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features="src/test/resources/features",
	glue="com.stepDefinition",
	plugin= {"pretty","html:reports/Cucumber-html-report.html"}
	
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

}