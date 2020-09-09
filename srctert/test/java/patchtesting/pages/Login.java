package patchtesting.pages;

import common.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by mnabizadeh on 5/11/18.
 */
public class Login {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(Login.class.getName());

    public Login(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }
    
    @FindBy(id = "sysid")
    public static WebElement sapSystem;
    
    @FindBy(id = "sap-client")
    public static WebElement sapClient;
    
    @FindBy(id = "sap-user")
    public static WebElement sapUser;
    
    @FindBy(id = "sap-password")
    public static WebElement sapPassword;
    
    @FindBy(className = "urBtnCntTxt")
    public static WebElement submitButton;
    
    /**
     *Goes to SAP home page
     *
     */
    public void goToSAPHome() {
        LOGGER.info("goToSAPHome started");
        driver.getUrl(Config.getBaseUrl());
        driver.waitForPageToLoad();
        LOGGER.info("goToSAPHome completed");
    }
    
    /**
     * Logs in to SAP using system, client, UN and PW
     */
    public void login() {
        LOGGER.info("login started");
        driver.waitForElementVisible(sapSystem);
        sapSystem.sendKeys(Config.getSapSystem());
        sapClient.sendKeys(Config.getSapClient());
        sapUser.sendKeys(Config.getSapUserName());
        sapPassword.sendKeys(Config.getSapPassword());
        driver.waitForElementClickable(submitButton);
        submitButton.click();
        LOGGER.info("login completed");
    }
}
