package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	public enum browserState {
        HEADLESS,
        NON_HEADLESS
    }
	
	public enum browserName {
		CHROME,
		FIREFOX
	}
	
	/**
	 * This is used to get the driver object for Firefox browser based on the passed browserState
	 * @param state
	 * @return
	 */
	public WebDriver setupFirefoxDriver(browserState state) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(state.equals(browserState.HEADLESS));
        return new FirefoxDriver(options);
    }
	
	/**
	 * This is used to get the driver object for Chrome browser based on the passed browserState
	 * @param state
	 * @return
	 */
	public WebDriver setupChromeDriver(browserState state) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(state.equals(browserState.HEADLESS));
        return new ChromeDriver(options);
    }
}
