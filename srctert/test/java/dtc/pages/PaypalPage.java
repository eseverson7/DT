package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.Customer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/27/16.
 */
public class PaypalPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(PaypalPage.class.getName());

    public PaypalPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "addressDisplay")
    private static WebElement addressDisplay;

    @FindBy(tagName = "iframe")
    public static WebElement frame;

    @FindBy(id = "email")
    public static WebElement emailTextField;

    @FindBy(id = "btnNext")
    public static WebElement nextButton;

    @FindBy(id = "password")
    public static WebElement passwordTextField;

    @FindBy(id = "btnLogin")
    public static WebElement logInButton;

    @FindBy(id = "confirmButtonTop")
    public static WebElement continueButton;

    @FindBy(id = "continue_abovefold")
    public static WebElement continueButtonUpper;

    @FindBy(css = ".full.ng-binding")
    public static WebElement paypalLogin;

    public static String spinnerPreloaderDisplayJs = "return document.getElementById('preloaderSpinner')." +
            "getAttribute('style').indexOf('none')>-1";

    public static String spinnerNotDisplayedJs = "return document.getElementById('spinner')===null";

    public static String spinnerDisplayedJs = "return document.getElementById('spinner')!==null";

    private static final By address = By.className("full-address");


    /**
     * Logs into paypal account using given credentials
     *
     * @param username Username for login
     * @param password Password for login
     */
    public void login(String username, String password) {
        LOGGER.info("login started");
        try {
            driver.waitForMilliseconds();

            //TODO: retest when new safaridriver is stable
            if (Config.isSafari() || Config.isIphone() || Config.isIpad() || Config.isFirefox() || Config.isIe())
                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT * 3);

            driver.pollUntil(spinnerPreloaderDisplayJs, Constants.DEFAULT_SEC_WAIT);
            driver.waitForElementClickable(paypalLogin);
            paypalLogin.click();
            driver.waitForPageToLoad();
            driver.waitForElementClickable(emailTextField);
            emailTextField.click();
            emailTextField.clear();
            emailTextField.sendKeys(username);
            nextButton.click();
            driver.waitForElementClickable(passwordTextField);
            passwordTextField.click();
            passwordTextField.clear();
            passwordTextField.sendKeys(password);

            if (Config.isSafari()) {
                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
                driver.jsClick(logInButton);
            } else {
                logInButton.click();
            }

            //TODO: Retest parentFrame call when new safaridriver is stable
            //webDriver.switchTo().parentFrame();
            webDriver.switchTo().defaultContent();
            driver.pollUntil(spinnerDisplayedJs, Constants.DEFAULT_SEC_WAIT);
            driver.pollUntil(spinnerNotDisplayedJs, Constants.DEFAULT_SEC_WAIT);
            if (!driver.isElementDisplayed(addressDisplay, Constants.TWO_SEC_WAIT))
                throw new Exception("Login failed.");
        } catch (Exception e) {
            Assert.fail("FAIL: Paypal login with user \"" + username + "\" and password \"" +
                    password + "\"! FAILED with error: " + e);
        }
        LOGGER.info("login ended");
    }

    /**
     * Selects address based on passed in customer value
     * Then click the continue button
     *
     * @param customer Type of customer to be used for checkout and address selection
     */
    public void selectAddressAndContinue(Customer customer) {
        LOGGER.info("selectAddressAndContinue started");
        try {
            driver.pollUntil(spinnerNotDisplayedJs, Constants.DEFAULT_SEC_WAIT);
            driver.waitForElementClickable(addressDisplay);
            addressDisplay.click();
            driver.pollUntil(spinnerNotDisplayedJs, Constants.DEFAULT_SEC_WAIT);
            driver.waitForMilliseconds();
            //clicks address element that contains customer address
            driver.getElementWithText(address, customer.paypalAddress).click();
            driver.pollUntil(spinnerNotDisplayedJs, Constants.DEFAULT_SEC_WAIT);
            driver.waitForElementClickable(continueButton);
            continueButton.click();
        } catch (Exception e) {
            Assert.fail("FAIL: Selecting Paypal address for user \"" +
                    customer.getCustomerDataString(customer) + "\"! FAILED with error: " + e);
        }
        LOGGER.info("selectAddressAndContinue completed");
    }

    /**
     * Clicks the continue button
     */
    public void clickContinue() {
        LOGGER.info("clickContinue started");
        driver.waitForElementClickable(continueButton);
        continueButton.click();
        LOGGER.info("clickContinue completed");
    }
}