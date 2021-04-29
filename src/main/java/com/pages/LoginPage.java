package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	// Locators
	private By tfEmail = By.id("email");
	private By tfPassword = By.id("passwd");
	private By btnSignIn = By.id("SubmitLogin");
	private By linkForgotPassword = By.linkText("Forgot your password?");
	
	// Constructor of the page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Page actions
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public boolean isForgotPasswordLinkExist() {
		return driver.findElement(linkForgotPassword).isDisplayed();
	}
	
	public void enterEmail(String value) {
		driver.findElement(tfEmail).sendKeys(value);
	}
	
	public void enterPassword(String value) {
		driver.findElement(tfPassword).sendKeys(value);
	}
	
	public void clickOnLogin() {
		driver.findElement(btnSignIn).click();
	}
	
	public AccountsPage doLogin(String email, String password) {
		this.enterEmail(email);
		this.enterPassword(password);
		this.clickOnLogin();
		return new AccountsPage(driver);
	}

}
