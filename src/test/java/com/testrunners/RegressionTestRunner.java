package com.testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions (
		features = {"src/test/resources/com/features"},
		glue = {"com/stepdefs", "com/apphooks"},
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","timeline:test-output-thread"},
		tags = "@regression and not @ignore"
		)

public class RegressionTestRunner {
}
