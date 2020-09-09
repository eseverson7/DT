package ovc.pages;

import common.Config;
import common.Constants;
import org.junit.Assert;
import ovc.data.ConstantsOvc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 5/18/17.
 */
public class LoginPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());

    public LoginPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "logonTo")
    public static WebElement serverUrlOn;

    @FindBy(id = "username")
    public static WebElement username;
    
    @FindBy(id = "email")
    public static WebElement dashboardUserName;

    @FindBy(id = "password")
    public static WebElement password;

    @FindBy(id = "login")
    public static WebElement loginButton;
    
    @FindBy(id = "log")
    public static WebElement dashboardLoginButton;

    @FindBy(id = "trainingLogin")
    private static WebElement trainingLoginButton;

    @FindBy(id = "center-logo")
    public static WebElement centerLogo;

    public static final By loader = By.className("loaderFontStyle");

    public static final By tillFieldBy = By.className("till");

    private static final By usernameBy = By.id("username");
    
    public static final String LOGOFF = "LOGOFF";

    /**
     * Navigates the page to the main home page
     * Dynamically generated based on which site and region is being tested
     */
    public void goToHome() {
        LOGGER.info("goToHome started");
        driver.getUrl(Config.getBaseUrl());
        driver.waitForPageToLoad();
        LOGGER.info("goToHome completed");
    }

    /**
     * Logs in to OVC via both login pages
     * Username and password are system variables now
     */
    public void login(String loginArea) {
        LOGGER.info("login started");

        driver.waitForElementVisible(username);
        driver.waitForElementNotVisible(tillFieldBy);
        serverUrlOn.sendKeys(Config.getOvcServer());
        username.sendKeys(Config.getOvcUserName());
        password.sendKeys(Config.getOvcUserPswd());
        loginButton.click();

        driver.waitForElementNotVisible(CommonActions.pagePreLoaderBy,Constants.NINETY_SEC_WAIT);
        driver.waitForElementNotVisible(loader);
        driver.waitForPageToLoad();
        driver.waitForElementNotVisible(tillFieldBy);
        driver.waitForElementVisible(username, Constants.TEN_SEC_WAIT);

        //serverUrlOn.clear();
        //serverUrlOn.sendKeys(Config.getOvcServer());
        username.clear();
        username.sendKeys(Config.getOvcUserName());
        password.clear();
        password.sendKeys(Config.getOvcUserPswd());
        driver.waitForPageToLoad();
        
        if(loginArea.equalsIgnoreCase(ConstantsOvc.TRAINING_LOGIN)) {
           trainingLoginButton.click();
        } else {
            loginButton.click();
        }
        
        driver.waitForElementNotVisible(loader, Constants.ONE_MIN_WAIT);
        driver.waitForPageToLoad();

        LOGGER.info("login completed");
    }

    /**
     * Attempts to login to OVC with invalid credentials
     */
    public void InvalidLogin() {
        LOGGER.info("InvalidLogin started");

        driver.waitForElementVisible(username);
        serverUrlOn.sendKeys(Config.getOvcServer());
        username.sendKeys(ConstantsOvc.INVALID_USERNAME_PASSWORD);
        password.sendKeys(ConstantsOvc.INVALID_USERNAME_PASSWORD);
        loginButton.click();

        LOGGER.info("InvalidLogin completed");
    }

    /**
     * Verifies OVC login via both login pages, attempted for a specified number of times
     *
     * @param timesValue Number of times to execute login check
     */
    public void verifyFirstLogin(String timesValue) {
        LOGGER.info("verifyFirstLogin started");
        int times = Integer.parseInt(timesValue);
        boolean loginCheck;

        for (int attempt = 1; attempt <= times; attempt++) {
            webDriver.navigate().refresh();
            goToHome();
            loginCheck = driver.isElementVisible(username, Constants.DEFAULT_SEC_WAIT);
            //timer filler required for subsequent validation
            driver.isElementNotVisible(tillFieldBy, Constants.DEFAULT_SEC_WAIT);

            if (loginCheck) {
                serverUrlOn.sendKeys(Config.getOvcServer());
                username.sendKeys(Config.getOvcUserName());
                password.sendKeys(Config.getOvcUserPswd());
                loginButton.click();

                if (driver.isElementNotVisible(usernameBy, Constants.DEFAULT_SEC_WAIT)) {
                    LOGGER.info("Login to initial OVC page was successful.");
                    verifySecondLogin(times);
                    break;
                } else {
                    if (attempt == times) {
                        Assert.fail("FAIL: Login was unsuccessful after " + attempt + " attempts.");
                    }
                    LOGGER.info("Login was unsuccessful for attempt \"" + attempt + "\", attempting again...");
                }
            } else {
                if (attempt == times) {
                    Assert.fail("FAIL: Initial OVC login page did not load after " + attempt + " attempts.");
                }
                LOGGER.info("Loading of initial OVC login page failed for attempt \"" + attempt + "\", " +
                        "attempting again...");
            }
        }

        LOGGER.info("verifyFirstLogin completed");
    }

    /**
     * Verifies OVC login to second login page, attempted for specified number of times
     *
     * @param  times    Number of times to attempt login
     */
    public void verifySecondLogin(int times) {
        LOGGER.info("verifySecondLogin started");
        boolean loginCheck;

        for (int attempt = 1; attempt <= times; attempt++) {
            driver.waitForPageToLoad();
            //timer filler required for subsequent validation
            driver.isElementNotVisible(tillFieldBy, Constants.DEFAULT_SEC_WAIT);
            loginCheck = driver.isElementVisible(username, Constants.TEN_SEC_WAIT);

            if (loginCheck) {
                //serverUrlOn.clear();
                //serverUrlOn.sendKeys(Config.getOvcServer());
                username.clear();
                username.sendKeys(Config.getOvcUserName());
                password.clear();
                password.sendKeys(Config.getOvcUserPswd());
                loginButton.click();
            } else {
                if (attempt == times) {
                    Assert.fail("FAIL: Second OVC login page did not load after " + attempt + " attempts.");
                }
                LOGGER.info("Loading of second OVC login page failed for attempt \"" + attempt + "\", " +
                        "attempting again...");
                webDriver.navigate().refresh();
            }

            if (driver.isElementNotVisible(usernameBy, Constants.ONE_MIN_WAIT)) {
                LOGGER.info("Login to second OVC page was successful.");
                break;
            } else {
                if (attempt == times) {
                    Assert.fail("FAIL: Login was unsuccessful after " + attempt + " attempts.");
                }
                LOGGER.info("Login was unsuccessful for attempt \"" + attempt + "\", attempting again...");
                webDriver.navigate().refresh();
            }
        }

        LOGGER.info("verifySecondLogin completed");
    }
    
    /**
     * Verifies login page is present 
     */
    public void verifyLoginPage() {
        LOGGER.info("verifyLoginPage started");

       if(driver.isElementVisible(username, Constants.DEFAULT_SEC_WAIT)){
        	LOGGER.info("OVC login page is present.");
        } else {
        	Assert.fail("FAIL: Login page was not present.");
        }
        LOGGER.info("verifyLoginPage completed");
    }
    
    
    /**
     * Logs in to OVC dashboard
     * Username and password are system variables now
     */
    public void dashboardLogin() {
        LOGGER.info("dashboardLogin started");

        driver.waitForElementVisible(dashboardUserName);
        dashboardUserName.sendKeys(Config.getOvcUserName());
        password.sendKeys(Config.getOvcUserPswd());
        
        driver.waitForPageToLoad();
        driver.waitForElementVisible(dashboardUserName, Constants.TEN_SEC_WAIT);

        dashboardUserName.clear();
        dashboardUserName.sendKeys(Config.getOvcUserName());
        password.clear();
        password.sendKeys(Config.getOvcUserPswd());

        driver.waitForElementNotVisible(loader, Constants.ONE_MIN_WAIT);
        driver.waitForPageToLoad();
        
        dashboardLoginButton.click();

        LOGGER.info("dashboardLogin completed");
    }
    
    /**
     * Login by entering server, username, password on the first login page  
     */
    public void loginOnFirstLoginPage() {
       LOGGER.info("loginOnFirstLoginPage started");

       driver.waitForElementVisible(username);
       serverUrlOn.sendKeys(Config.getOvcServer());
       username.sendKeys(Config.getOvcUserName());
       password.sendKeys(Config.getOvcUserPswd());
       loginButton.click();

       driver.waitForElementNotVisible(loader);
       driver.waitForPageToLoad();
       driver.waitForElementVisible(username, Constants.TEN_SEC_WAIT);

       LOGGER.info("loginOnFirstLoginPage completed");
    }
    
    /**
     * Verifies till is not present on the second login page  
     */
    public void verifyTillNotPresentOnLoginPage() {
       LOGGER.info("verifyTillNotPresentOnLoginPage started");
       
       driver.waitForElementVisible(username, Constants.TEN_SEC_WAIT);
       
       if(driver.isElementNotVisible(tillFieldBy, Constants.DEFAULT_SEC_WAIT)){
        	LOGGER.info("Till is not present on the Login Page.");
        } else {
        	Assert.fail("FAIL: Till is present on the Login page.");
        }
        LOGGER.info("verifyTillNotPresentOnLoginPage completed");
    }
}
