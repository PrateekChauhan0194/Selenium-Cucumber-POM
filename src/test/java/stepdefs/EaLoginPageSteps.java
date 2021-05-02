package stepdefs;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.EaLoginPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

public class EaLoginPageSteps {

	private ConfigReader configReader;
	private Properties prop;
	private EaLoginPage eaLoginPage = new EaLoginPage(DriverFactory.getDriver());

	@Given("User is on the EA Login page")
	public void user_is_on_the_ea_login_page() {
		configReader = new ConfigReader();
		prop = configReader.getGlobalConfigProp();
		DriverFactory.getDriver().get(prop.getProperty("EA_LOGIN_URL"));
	}

	@Then("User sees the crisis message on the screen")
	public void user_sees_the_crisis_message_on_the_screen() {
		Assert.assertTrue(eaLoginPage.isCrisisMessageExist());
	}

	@Then("User validates the crisis message text")
	public void user_validates_the_crisis_message_text() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(
				eaLoginPage.getExpectedCrisisMessageContent(),
				eaLoginPage.getCrisisMessageContent()
		);
//		Assert.assertEquals(
//				eaLoginPage.getExpectedCrisisMessageContent(),
//				eaLoginPage.getCrisisMessageContent()
//			);
	}
	
	@Then("User validates the crisis message close link text")
	public void user_validates_the_crisis_message_close_link_text() {
		Assert.assertEquals(
				eaLoginPage.getExpectedCrisisMessageCloseLinkText(),
				eaLoginPage.getCrisisMessageCloseLinkText()
			);
	}

	@When("User clicks on the Hide message button")
	public void user_clicks_on_the_hide_message_button() {
		eaLoginPage.clickCrisisMessageCloseLink();
	}
	
	@Then("User validate the absence of crisis message on the screen")
	public void user_validate_the_absence_of_crisis_message_on_the_screen() {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.crisis-message-wrapper")));
		Assert.assertFalse(eaLoginPage.isCrisisMessageExist());
	}
	
	@Then("User sees the page Header")
	public void user_sees_the_page_header() {
		Assert.assertTrue(eaLoginPage.isPageHeaderExist());
	}

	@Then("User validate the page Header Text")
	public void user_validate_the_page_header_text() {
	    Assert.assertEquals(
	    		eaLoginPage.getExpectedPageHeaderText(),
	    		eaLoginPage.getPageHeaderText()
    		);
	}
}
