package com.apphooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.Browser.browserName;
import com.qa.factory.Browser.browserState;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	private Properties prop;
	private String propFilePath;
	
	@Before(order = 0)
	public void getProperty() {
		propFilePath = "./src/test/resources/config/config.properties";
		configReader = new ConfigReader();
		prop = configReader.initProp(propFilePath);
	}
	
	@Before(order = 1)
	public void launchBrowser() {
		browserName browser = browserName.valueOf(prop.getProperty("BROWSER_NAME"));
		browserState state = browserState.valueOf(prop.getProperty("BROWSER_STATE"));
		driverFactory = new DriverFactory();
		driver = driverFactory.initDriver(browser, state);
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			// Take screenshot
			String ssName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", ssName);
		}
	}
	
	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}
}
