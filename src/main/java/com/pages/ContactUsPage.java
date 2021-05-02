package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {

	private WebDriver driver;
	
	// Constrctor
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Locators
	private final By subjectHeadingDropDown = By.id("id_contact");
	private final By tfEmail = By.id("email");
	private final By tfMessage = By.id("message");
	private final By btnSend = By.id("submitMessage");
	private final By txtSuccessMessage = By.cssSelector("#center_column p.alert");
	
	// Page Actions
	public void selectSubjectHeading(String subHeading) {
		Select select = new Select(driver.findElement(subjectHeadingDropDown));
		select.selectByVisibleText(subHeading);
	}
	
	public void enterEmail(String email) {
		driver.findElement(tfEmail).sendKeys(email);
	}
	
	public void enterMessage(String message) {
		driver.findElement(tfMessage).sendKeys(message);
	}
	
	public void clickSend() {
		driver.findElement(btnSend).click();
	}
	
	public String getSuccessMessage() {
		return driver.findElement(txtSuccessMessage).getText();
	}
}
