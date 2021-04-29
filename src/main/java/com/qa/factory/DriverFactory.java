package com.qa.factory;

import org.openqa.selenium.WebDriver;
import com.qa.factory.Browser.browserName;
import com.qa.factory.Browser.browserState;

public class DriverFactory {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public Browser objBrowser; 
	
	/**
	 * This is used to initialise the ThreadLocal driver based on the passed browserName and browserState
	 * @param browser
	 * @param state
	 */
	public WebDriver initDriver(browserName browser, browserState state) {
		System.out.println("Initialising " + state + " " + browser + " driver");
		objBrowser = new Browser();
		switch(browser) {
			case CHROME:
				tlDriver.set(objBrowser.setupChromeDriver(state));
				break;
			case FIREFOX:
				tlDriver.set(objBrowser.setupFirefoxDriver(state));
				break;
			default:
				System.out.println("Invalid browser name: " + browser);	
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		return getDriver();
	}
	
	/**
	 * This is used to get the driver with ThreadLocal
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
