package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by Channing Luden on 2/13/2017.
 */
public class HybrisManagementConsolePage {
    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(HybrisManagementConsolePage.class.getName());

    public HybrisManagementConsolePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static final String ORDER_HMC = "Order";
    private static final String ORDERS_HMC = "Orders";
    private static final String ORDER_PLACED_HMC = "Order placed.";

    public static final String ADMINISTRATION_TAB_HMC = "Administration";

    @FindBy(id = "Main_user")
    public static WebElement userLogin;

    @FindBy(id = "Main_password")
    public static WebElement userPswd;

    @FindBy(id = "Main_a")
    public static WebElement loginBtn;

    @FindBy(id = "Content/StringEditor[in Content/GenericCondition[Order.code]]_input")
    public static WebElement orderNumberInput;

    @FindBy(id = "Content/StringDisplay[Order placed.]_div")
    public static WebElement orderResultStatus;
    
    @FindBy(id = "Content/ItemDisplay[Succeeded]_div")
    public static WebElement appointmentOrderResultStatus;

    @FindBy(id = "Toolbar/ImageToolbarAction[closesession]_img")
    public static WebElement closeSessionBtn;

    public static final By hmcSearchBtn = By.className("label");

    public static final By hmcSearchResultItem = By.className("sorted");
    
    public static final String APPOINTMENT_SUCCEEDED_HMC = "Succeeded";

    /***
     * Logs the user into the Hybris Management Console
     * Note: 1. HMC environment is set by dataSet VM build command
     *       2. User and Pswd are set by Environment vars
     */
    public void loginToHmc() {
        LOGGER.info("loginToHmc started");
        try {
            driver.getUrl(Config.getHmcUrl());
            driver.waitForPageToLoad();
            driver.waitForElementClickable(userLogin);
            userLogin.clear();
            userLogin.sendKeys(Config.getHmcUserName());
            userPswd.clear();
            userPswd.sendKeys(Config.getHmcUserPswd());
            loginBtn.click();
        } catch (Exception e){
            Assert.fail("FAIL: Attempting to login to HMC FAILED with error: " + e);
        }
        LOGGER.info("loginToHmc completed");
    }

    /***
     * Selects the specified HMC directory or sub-directory
     *
     * @param directory The HMC directory to select
     */
    public void selectHmcDirectory(String directory) {
        LOGGER.info("selectHmcDirectory started with directory = \"" + directory + "\"");
        driver.clickElementWithLinkText(directory);
        LOGGER.info("selectHmcDirectory completed with directory = \"" + directory + "\"");
    }

    /***
     * Searches HMC by the specified order number
     * @param orderNumber Order number used in the search
     */
    public void searchHmcByOrderNumber(String orderNumber) {
        LOGGER.info("searchHmcByOrderNumber started for order number = \"" + orderNumber + "\"");
        selectHmcDirectory(ORDER_HMC);
        selectHmcDirectory(ORDERS_HMC);
        driver.waitForElementClickable(orderNumberInput);
        orderNumberInput.clear();
        orderNumberInput.sendKeys(orderNumber);
        driver.clickElementWithText(hmcSearchBtn, ConstantsDtc.SEARCH);
        LOGGER.info("searchHmcByOrderNumber completed for order number = \"" + orderNumber + "\"");
    }

    /***
     * Selects the HMC search result that is an exact match of the order number provided
     * @param orderNumber Order number a result item must match in order to be selected (via double-click)
     */
    public void selectSearchResultByOrderNumber(String orderNumber) {
        LOGGER.info("selectSearchResultByOrderNumber started for started for order number = \""
                + orderNumber + "\"");
        driver.waitForElementClickable(hmcSearchResultItem);
        WebElement searchResultItem = driver.getElementWithText(hmcSearchResultItem, orderNumber);
        driver.doubleClickControl(searchResultItem);
        LOGGER.info("selectSearchResultByOrderNumber completed for order number = \"" + orderNumber + "\"");
    }

    /***
     * Selects the specified HMC Editor tab
     * @param tabName Editor tab to be selected
     */
    public void selectTabForCurrentOrder(String tabName) {
        LOGGER.info("selectTabForCurrentOrder started for tab name = \"" + tabName + "\"");
        driver.clickElementWithLinkText(tabName);
        LOGGER.info("selectTabForCurrentOrder completed for tab name = \"" + tabName + "\"");
    }

    /***
     * Validates the currently displayed order item has an order process result state of 'Order placed.'
     */
    public void validateCurrentOrderResultStatus() {
        LOGGER.info("validateCurrentOrderResultStatus started");
        Assert.assertTrue("FAIL: The currently displayed order does not have successful order process status!",
                orderResultStatus.getText().equalsIgnoreCase(ORDER_PLACED_HMC));
        LOGGER.info("validateCurrentOrderResultStatus completed");
    }

    /***
     * Validates the currently displayed appointment order has an order process result state of 'Succeeded'
     */
    public void validateCurrentAppointmentResultStatus() {
        LOGGER.info("validateCurrentAppointmentResultStatus started");
        Assert.assertTrue("FAIL: The currently displayed appointment order does not have successful appointment process status!",
                appointmentOrderResultStatus.getText().equalsIgnoreCase(APPOINTMENT_SUCCEEDED_HMC));
        LOGGER.info("validateCurrentAppointmentResultStatus completed");
    }
    
    /***
     * Selects the HMC 'Close Session' toolbar button
     */
    public void closeCurrentHmcSession() {
        LOGGER.info("closeCurrentHmcSession started");
        driver.waitForElementClickable(closeSessionBtn, Constants.FIVE_SEC_WAIT);
        closeSessionBtn.click();
        LOGGER.info("closeCurrentHmcSession completed");
    }

    /***
     * Verifies the user has been returned to the HMC login page. To be used in combination w/ closeCurrentHmcSession().
     */
    public void verifyReturnToHmcLoginPage() {
        LOGGER.info("verifyReturnToHmcLoginPage started");
        Assert.assertTrue("FAIL: The login button was not displayed! User was not returned to HMC login page!",
                driver.isElementDisplayed(loginBtn));
        LOGGER.info("verifyReturnToHmcLoginPage completed");
    }
}