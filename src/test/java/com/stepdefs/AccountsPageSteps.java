package com.stepdefs;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

import com.pages.AccountsPage;
import com.pages.LoginPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsPageSteps {
	
	private Properties prop;
	private ConfigReader configReader;
	private LoginPage loginPage;
	private AccountsPage accountsPage;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps();
		String email = data.get(0).get("username");
		String password = data.get(0).get("password");
		
		configReader = new ConfigReader();
		prop = configReader.getGlobalConfigProp();
		DriverFactory.getDriver().get(prop.getProperty("LOGIN_URL"));
		loginPage = new LoginPage(DriverFactory.getDriver());
		accountsPage = loginPage.doLogin(email, password);
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		accountsPage.getAccountsPageTitle();
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable dataTable) {
		List<String> expectedList = dataTable.asList();
		List<String> actualList = accountsPage.getAccountSectionsList();
		System.out.println("Expected list: " + expectedList);
		System.out.println("Actual list: " + actualList);
		Assert.assertTrue(expectedList.equals(actualList));
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedCount) {
		int actualCount = accountsPage.getAccountSectionCount();
		Assert.assertEquals((Integer)expectedCount, (Integer)actualCount);
	}

}
