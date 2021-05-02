package stepdefs;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.pages.ContactUsPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsPageSteps {
	
	private final WebDriver driver = DriverFactory.getDriver();
	private final ContactUsPage contactUsPage = new ContactUsPage(driver);
	private final Properties globalConfigProp = new ConfigReader().getGlobalConfigProp();

	@Given("User is on the contact us page")
	public void user_is_on_the_contact_us_page() {
		driver.get(globalConfigProp.getProperty("CONTACT_US_URL"));
	}

	@When("User enters the data for {string} from {string} sheet")
	public void user_enters_the_data_from_contact_us_sheet(String username, String sheetName) {
		String testDataFilePath = globalConfigProp.getProperty("TESTDATA_FILEPATH");
		ExcelUtils excelUtils = new ExcelUtils();
		XSSFSheet sheet = excelUtils.getSheetByName(testDataFilePath, sheetName);

		int usernameCellIndex, subjectCellIndex, emailCellIndex, messageCellIndex;
		String usernameTestData, subjectTestData, emailTestData, messageTestData;
		for(Row row : sheet) {
			usernameCellIndex = row.getFirstCellNum();
			subjectCellIndex = usernameCellIndex + 1;
			emailCellIndex = subjectCellIndex + 1;
			messageCellIndex = emailCellIndex + 1;
			
			usernameTestData = row.getCell(usernameCellIndex).getStringCellValue();
			subjectTestData = row.getCell(subjectCellIndex).getStringCellValue();
			emailTestData = row.getCell(emailCellIndex).getStringCellValue();
			messageTestData = row.getCell(messageCellIndex).getStringCellValue();

			if(username.equals(usernameTestData)) {
				System.out.println("Inside if for " + username);
				System.out.println(subjectTestData);
				System.out.println(emailTestData);
				System.out.println(messageTestData);

				contactUsPage.selectSubjectHeading(subjectTestData);
				contactUsPage.enterEmail(emailTestData);
				contactUsPage.enterMessage(messageTestData);
				break;
			}
		}
	}

	@When("User clicks on the send button")
	public void user_clicks_on_the_send_button() {
		contactUsPage.clickSend();
	}

	@Then("A success message is presented to the user")
	public void a_success_message_is_presented_to_the_user() {
		String expectedMessageText = "Your message has been successfully sent to our team.";
		Assert.assertEquals(expectedMessageText, contactUsPage.getSuccessMessage());
	}
}
