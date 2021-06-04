import io.cucumber.java.cs.Ale;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Ignore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PracticeSelenium {

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void validateTitleAndUrl() {
        /**
         * Validate the title of the pages
         *
         * 1. Go to homepage
         * 2. Validate the title
         * 3. Navigate to the demo page by clicking the 'Overview demo' link
         * 4. Validate the page title
         * 5. Validate the page URL
         */
        driver.get("https://phptravels.com/");
        String expectedTitle = "PHPTRAVELS booking script and system for hotels airline flights tours cars online application - PHPTRAVELS";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        WebElement linkOverviewDemo = driver.findElement(By.linkText("Overview Demo"));
        linkOverviewDemo.click();
        expectedTitle = "Demo Script Test drive - PHPTRAVELS";
        actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        String expectedUrl = "https://phptravels.com/demo";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        System.out.println(">>>>>>>'validateTitleAndUrl' test completed.");
    }

    /**
     * Validate the join newsletter section at the bottom of the page
     *
     * 1. Navigate to the php travels demo page
     * 2. Click the subscribe button with blank email id textfield
     * 3. Interact with the presented Alert
     * 4. Enter email
     * 5. Click the subscribe button
     * 6. Interact with the presented Alert
     * @throws InterruptedException
     */
    @Test
    public void validateJoinNewsletterSection() {
        driver.get("https://phptravels.com/demo");
        WebElement txtEmail = driver.findElement(By.cssSelector("input#address"));
        WebElement btnSubscribe = driver.findElement(By.cssSelector("div#email button"));

        // Clicking the subscribe button with blank email value
        txtEmail.clear();
        btnSubscribe.click();
        Alert alert1 = driver.switchTo().alert();
        System.out.println(alert1.getText());
        alert1.accept();

        // Entering the email value and then subscribing
        txtEmail.sendKeys("test@test.com");
        btnSubscribe.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        System.out.println(alert2.getText());
        alert2.accept();

        System.out.println(">>>>>>>'validateJoinNewsletterSection' test completed.");
    }

    /**
     * Validate the links present on the navbar
     *
     * 1. Navigate to the php travels demo page
     * 2. Validate the links present on the navbar
     */
    @Test
    public void validateNavBarLinks() {
        List<String> expectedLinks = Arrays.asList("Demo", "Pricing", "Integrations", "Docs", "Blog", "Login");
        driver.get("https://phptravels.com/demo");
        List<WebElement> navbarLinks = driver.findElements(By.cssSelector("header.BS-header nav.clearfix > a"));
        for (WebElement element : navbarLinks) {
            Assert.assertTrue(expectedLinks.contains(element.getText()));
        }

        System.out.println(">>>>>>>'validateNavBarLinks' test completed.");
    }

    /**
     * Validate phptravels.net page title
     *
     * 1. Navigate to the php travels demo page
     * 2. Click on the homepage front end link
     * 3. Switch to the new window
     * 4. Validate the page title
     * 5. Close the new window
     * 6. Click on the Admin Back end link
     * 7. Terminate the test
     */
    @Test
    public void validateTitleForPhpTravelsPageViaDemoPage() throws InterruptedException {
        driver.get("https://phptravels.com/demo");
        WebElement homepageLink = driver.findElement(By.cssSelector("div.container div.resource-box a.btn"));

        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(homepageLink));
        homepageLink.click();

        String mainWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String curWinHandle : windowHandles) {
            if (!curWinHandle.equalsIgnoreCase(mainWindowHandle)) {
                driver.switchTo().window(curWinHandle);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("header.header-main"))));
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "PHPTRAVELS | Travel Technology Partner");
        driver.close();
        driver.switchTo().window(mainWindowHandle);
        String overlayText = homepageLink.getText();
        System.out.println(overlayText);
        Assert.assertEquals(overlayText, "http://www.phptravels.net");
        System.out.println(">>>>>>>'validateTitleForPhpTravelsPageViaDemoPage' test completed.");
    }

    @Test @Ignore
    public void validateMyAccountLogin() {
        driver.get("https://www.energyaustralia.com.au/");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement btnLogin = driver.findElement(By.cssSelector("#navbar ul.nav a[href*='login'].ea-button"));
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();
    }

    /**
     * Validate the recaptcha on php travels login
     */
    @Test
    public void validatePhpTravelsLogin() throws InterruptedException {
        driver.get("https://phptravels.com/demo");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement btnLogin = driver.findElement(By.linkText("Login"));
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();
        String mainWinHandle = driver.getWindowHandle();
        Set<String> winHandles = driver.getWindowHandles();
        for (String winHandle : winHandles) {
            if (!winHandle.equalsIgnoreCase(mainWinHandle)) {
               driver.switchTo().window(winHandle);
               System.out.println(driver.getTitle());
               break;
            }
        }
        WebElement recaptchaContainer = driver.findElement(By.id("google-recaptcha-domainchecker1"));
        wait.until(ExpectedConditions.visibilityOf(recaptchaContainer));

        WebElement captchaIframe = driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']"));
        String loginPageHandle = driver.getWindowHandle();
        driver.switchTo().frame(captchaIframe);

        WebElement cbRecaptcha = driver.findElement(By.id("recaptcha-anchor"));
        cbRecaptcha.click();
        Thread.sleep(2000);

//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeAsyncScript("arguments[0].click()", cbRecaptcha);

        driver.switchTo().window(loginPageHandle);
        System.out.println(driver.getTitle());
        driver.close();
        driver.switchTo().window(mainWinHandle);
        System.out.println(driver.getTitle());

        System.out.println(">>>>>>>'validatePhpTravelsLogin' test completed.");
    }

    // Actions class

    @After
    public void tearDown() {
        driver.quit();
    }

//    public static void main(String[] args) {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//        /**
//         * Validate the title of the pages
//         *
//         * 1. Go to homepage
//         * 2. Validate the title
//         * 3. Navigate to the demo page by clicking the 'Overview demo' link
//         * 4. Validate the page title
//         * 5. Validate the page URL
//         */
//        driver.get("https://phptravels.com/");
//        String expectedTitle = "PHPTRAVELS booking script and system for hotels airline flights tours cars online application - PHPTRAVELS";
//        String actualTitle = driver.getTitle();
//        Assert.assertEquals(actualTitle, expectedTitle);
//        WebElement linkOverviewDemo = driver.findElement(By.linkText("Overview Demo"));
//        linkOverviewDemo.click();
//        expectedTitle = "Demo Script Test drive - PHPTRAVELS";
//        actualTitle = driver.getTitle();
//        Assert.assertEquals(actualTitle, expectedTitle);
//        String expectedUrl = "https://phptravels.com/demo";
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl);
//
//
//
//        driver.quit();
//    }

}
