package pdl.pages;

/**
 * Created by aaronbriel on 9/16/16.
 */

import common.Config;
import common.Constants;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * @author mnabizadeh
 *
 */
public class HomePage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(HomePage.class.getName());

    public HomePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public static final String ENTER = "Enter";

    @FindBy(id = "auto_store_ID")
    public static WebElement storeId;

    @FindBy(id = "auto_payroll_ID")
    public static WebElement payrollId;

    @FindBy(id = "auto_enter")
    public static WebElement enterButton;
        
    public static final String STORE_ID_PLACEHOLDER_VAL = "Store ID";
    
    public static final String PAYROLL_ID_PLACEHOLDER_VAL = "Payroll ID";
    
    public static final String PAYROLL_ERROR_MESSAGE = "Please enter a 6 digit payroll ID";
    
    public static final String STORE_ERROR_MESSAGE = "Unable to find store information";

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
     * Logs in to PDL
     *
     * @param   storeIdVal      Store ID
     * @param   payrollIdVal    Payroll ID
     */
    public void login(String storeIdVal, String payrollIdVal) {
        LOGGER.info("login started");
        driver.waitForElementVisible(storeId);
        storeId.sendKeys(storeIdVal);
        payrollId.sendKeys(payrollIdVal);
        driver.waitForElementClickable(enterButton);
        enterButton.click();
        LOGGER.info("login completed");
    }
    
    
    
    /**
     * Verifies store Id element is present
     * 
     */
    public void assertStoreIDIsPresent() {
        LOGGER.info("assertStoreIDIsPresent started");
        driver.waitForPageToLoad();
        if (!(driver.isElementDisplayed(storeId))){
			Assert.fail("FAIL: Store ID Element not present.");
		}
        LOGGER.info("assertStoreIDIsPresent completed");
    }
    
    /**
     * Verifies payroll Id element is present
     * 
     */
    public void assertPayrollIDIsPresent() {
        LOGGER.info("assertPayrollIDIsPresent started");
        driver.waitForElementVisible(storeId);
		if (!(driver.isElementDisplayed(payrollId))){
			Assert.fail("FAIL: Payroll ID Element not present.");
		}
        LOGGER.info("assertPayrollIDIsPresent completed");	
    }
    
    /**
     * Verifies store Id placeholder value
     * 
     */
    public void assertStoreIDPlaceholderValue() {
        LOGGER.info("assertStoreIDPlaceholderValue started");
        driver.waitForElementVisible(storeId);
        String storeIdPlaceholder = storeId.getAttribute("placeholder");
		if (!(storeIdPlaceholder.equalsIgnoreCase(STORE_ID_PLACEHOLDER_VAL))){
			Assert.fail("FAIL: StoreId placeholder Element not present.");
		}
        LOGGER.info("assertStoreIDPlaceholderValue completed");	
    }
    
    /**
     * Verifies payroll Id placeholder value
     * 
     */
    public void assertPayrollIDPlaceholderValue() {
        LOGGER.info("assertPayrollIDPlaceholderValue started");
        driver.waitForElementVisible(payrollId);
        String payrollIdPlaceholder = payrollId.getAttribute("placeholder");
		if (!(payrollIdPlaceholder.equalsIgnoreCase(PAYROLL_ID_PLACEHOLDER_VAL))){
			Assert.fail("FAIL: PayrollId placeholder Element not present.");
		}
        LOGGER.info("assertPayrollIDPlaceholderValue completed");	
    }
    
    
    /**
     * Verifies payroll Id six digit error message
     * 
     */
    public void assertPayrollIdSixDigitTextErrorMessage(String text) {
        LOGGER.info("assertPayrollIdSixDigitTextErrorMessage started");
        driver.waitForElementVisible(payrollId);
        Assert.assertTrue("FAIL: pdl error message for 6 digit payroll NOT displayed!",
        		driver.isElementDisplayed(CommonActions.errorMessageBy));
        Assert.assertTrue("FAIL: pdl error message did NOT contain \"" + text + "\"!",
        		driver.getElementWithText(CommonActions.errorMessageBy, PAYROLL_ERROR_MESSAGE).getText().contains(text));
        LOGGER.info("assertPayrollIdSixDigitTextErrorMessage completed");
    }
}