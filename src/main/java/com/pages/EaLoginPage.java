package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EaLoginPage {

	private WebDriver driver;
	private String expectedCrisisMessageContent = "Find out how we're supporting customers impacted by COVID-19.";
	private String expectedCrisisMessageCloseLinkText = "Hide message";
	private String expectedPageHeaderText = "Log in to My Account";
	
	// Locators
	private By crisisMessageWrapper = By.cssSelector("div.crisis-message-wrapper");
	private By crisisMessageContent = By.cssSelector("div.crisis-message-wrapper p");
	private By crisisMessageCloseLink = By.cssSelector("div.crisis-message-wrapper a.crisis-message__close");
	private By pageHeader = By.cssSelector("div.page-header h1");
	
	// Constructor
	public EaLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Page Actions
	public boolean isCrisisMessageExist() {
		return driver.findElement(crisisMessageWrapper).isDisplayed();
	}
	
	public String getCrisisMessageContent() {
		return driver.findElement(crisisMessageContent).getText();
	}
	
	public String getExpectedCrisisMessageContent() {
		return this.expectedCrisisMessageContent;
	}
	
	public String getExpectedCrisisMessageCloseLinkText() {
		return this.expectedCrisisMessageCloseLinkText;
	}
	
	public String getCrisisMessageCloseLinkText() {
		return driver.findElement(crisisMessageCloseLink).getText();
	}
	
	public void clickCrisisMessageCloseLink() {
		driver.findElement(crisisMessageCloseLink).click();
	}
	
	public boolean isPageHeaderExist() {
		return driver.findElement(pageHeader).isDisplayed();
	}
	
	public String getExpectedPageHeaderText() {
		return expectedPageHeaderText;
	}
	
	public String getPageHeaderText() {
		return driver.findElement(pageHeader).getText();
	}
}
