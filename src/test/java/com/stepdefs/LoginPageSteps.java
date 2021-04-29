package com.stepdefs;

import java.util.Properties;

import org.junit.Assert;

import com.pages.LoginPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private String pageTitle;
	private ConfigReader configReader;
	private Properties prop;

	@Given("user is on login page")
	public void user_is_on_login_page() {
		configReader = new ConfigReader();
		prop = configReader.getGlobalConfigProp();
		DriverFactory.getDriver().get(prop.getProperty("LOGIN_URL"));
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		pageTitle = loginPage.getPageTitle();
		System.out.println("Page title: " + pageTitle);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedPageTitle) {
		Assert.assertEquals(expectedPageTitle, pageTitle);
	}

	@Then("forgot your password link should be displayed")
	public void forgot_your_password_link_should_be_displayed() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}

	@When("user enters username {string}")
	public void user_enters_username(String email) {
		loginPage.enterEmail(email);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		loginPage.enterPassword(password);
	}

	@When("user clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.clickOnLogin();
	}
}
