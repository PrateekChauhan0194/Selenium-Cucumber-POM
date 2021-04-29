package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage {
	private WebDriver driver;
	
	// Locators
	private By accountSections = By.cssSelector("#center_column ul.myaccount-link-list span");
		
	// Constructor of the page class
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}
		
	// Page actions
	public String getAccountsPageTitle() {
		return driver.getTitle();
	}
	
	public int getAccountSectionCount() {
		return driver.findElements(accountSections).size();
	}
	
	public List<String> getAccountSectionsList() {
		List<WebElement> eleSectionsList = driver.findElements(accountSections);
		List<String> strSectionsList = new ArrayList<>();
		for (WebElement element : eleSectionsList) {
			strSectionsList.add(element.getText().toUpperCase());
		}
		
		return strSectionsList;
	}
}
