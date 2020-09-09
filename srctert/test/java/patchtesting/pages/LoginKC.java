package patchtesting.pages;

import common.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

public class LoginKC {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(LoginKC.class.getName());

    public LoginKC(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy (css = "#pt-login-username-textbox")
    public static WebElement usernameField;

    @FindBy (css = "#pt-login-password-field")
    public static WebElement passwordField;

    @FindBy (name = "in_bu_Login")
    public static WebElement loginButton;

    /**
     * Go to login Knowledge Center website home
     */
    public void goToKCHome() {
        LOGGER.info("goToKCHome started");
        driver.getUrl("http://portalprd.discounttire.com/portal/server.pt/community/corporate/210/corporate_home");
        driver.waitForPageToLoad();
        LOGGER.info("goToKCHome started");
    }

    /**
     * Logs in to Knowledge Center using system env. parameters
     */
    public void loginKC() {
        LOGGER.info("loginKC started");
        driver.waitForElementClickable(usernameField);
        usernameField.sendKeys(Config.getKCClientUserName());
        passwordField.sendKeys(Config.getKCClientPassword());
        driver.waitForElementClickable(loginButton);
        loginButton.click();
        LOGGER.info("loginKC completed");
    }
}
