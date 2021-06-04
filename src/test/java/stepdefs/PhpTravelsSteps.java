package stepdefs;

import com.pages.PhpTravels;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Properties;

public class PhpTravelsSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private PhpTravels phpTravels = new PhpTravels(driver);
    Properties prop = new ConfigReader().getGlobalConfigProp();

    @Given("User is on php travels' homepage")
    public void user_is_on_php_travels_homepage() {
        driver.get(phpTravels.getHomepageUrl());
    }

    @Then("User validates the php travels' homepage title")
    public void user_validates_the_php_travels_homepage_title() {
        Assert.assertEquals(driver.getTitle(), phpTravels.getExpectedHomepageTitle());
    }

}
