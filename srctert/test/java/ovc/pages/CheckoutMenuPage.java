package ovc.pages;

import common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by cluden on 7/12/2017.
 */
public class CheckoutMenuPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(CheckoutMenuPage.class.getName());

    public CheckoutMenuPage(Driver driver){
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    // TODO: Temp price. Remove after actual price is implemented in application
    private static final String INSPECTION_PRICE = "10.00";

    private static final By paymentOptionBy = By.cssSelector("form tr");

    private static final By mblSimpleDialogBy = By.className("mblSimpleDialogContainer");

    /**
     * Selects the specified option from the "Special Payment" menu via Checkout Menu
     * @param paymentOption Option to select from the "Special Payment" pop-up menu
     */
    public void selectSpecialPaymentOption(String paymentOption) {
        LOGGER.info("selectSpecialPaymentOption started w/ payment option '" + paymentOption + "'");
        driver.clickElementWithText(paymentOptionBy, paymentOption);
        commonActions.selectButtonWithText(ParentElementsPage.OVC, ConstantsOvc.OK);
        LOGGER.info("selectSpecialPaymentOption completed w/ payment option '" + paymentOption + "'");
    }

    /**
     * Change the Cash Tender Amount via Checkout Menu
     * @param tenderAmount Field to change Cash Amount in Checkout Menu
     */
    public void changeTenderAmount(String tenderAmount) {
        LOGGER.info("changeTenderAmount started w/ payment option");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(driver.getDisplayedElement(mblSimpleDialogBy, Constants.ONE_SEC_WAIT));
        WebElement priceAlert = driver.getDisplayedElement(mblSimpleDialogBy, Constants.ONE_SEC_WAIT);
        WebElement priceInputBox = priceAlert.findElement(CommonActions.mblTxtBy);
        priceInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        priceInputBox.sendKeys(tenderAmount);
        LOGGER.info("changeTenderAmount complete");
    }

    /**
     * Passes in price for tire inspection
     */
    public void enterTireInspectionPrice() {
        LOGGER.info("enterTireInspectionPrice started");
        WebElement priceAlert = driver.getDisplayedElement(mblSimpleDialogBy, Constants.ONE_SEC_WAIT);
        WebElement priceInputBox = priceAlert.findElement(CommonActions.mblTxtBy);
        priceInputBox.click();
        priceInputBox.clear();
        priceInputBox.sendKeys(INSPECTION_PRICE);
        LOGGER.info("enterTireInspectionPrice completed");
    }
    
    /**
     * Enter tender data on travelers check input fields
     * @param phoneNumber
     * @param licenseNumber
     * @param licenseState
     * @param licenseExpiration
     * @param routingNumber
     * @param accountNumber
     * @param checkNumber to populate tender details popup
     */
    public void enterTenderDetailsForTavelersCheck(String phoneNumber, String licenseNumber, String licenseState, String licenseExpiration, String routingNumber, String accountNumber, String checkNumber) {
        LOGGER.info("enterTenderDetailsForTavelersCheck started");
        commonActions.enterTextIntoInputBox(phoneNumber,"Phone Number");
        commonActions.enterTextIntoInputBox(licenseNumber,"License Number");
        commonActions.enterTextIntoInputBox(licenseState,"License State");
        commonActions.enterTextIntoInputBox(licenseExpiration,"License Expiration");
        commonActions.enterTextIntoInputBox(routingNumber,"Routing Number");
        commonActions.enterTextIntoInputBox(accountNumber,"Account Number");
        commonActions.enterTextIntoInputBox(checkNumber,"Check Number");
        LOGGER.info("enterTenderDetailsForTavelersCheck complete");
    }
    
    /**
     * Enter tender data on money order input fields
     * @param routingNumber
     * @param accountNumber
     * @param checkNumber to populate tender details popup
     */
    public void enterTenderDetailsForMoneyOrder(String routingNumber, String accountNumber, String checkNumber,
                                                String issuingInstitution) {
        LOGGER.info("enterTenderDetailsForMoneyOrder started");
        commonActions.enterTextIntoInputBox(routingNumber, ConstantsOvc.ROUTING_NUMBER);
        commonActions.enterTextIntoInputBox(accountNumber, ConstantsOvc.ACCOUNT_NUMBER);
        commonActions.enterTextIntoInputBox(checkNumber, ConstantsOvc.CHECK_NUMBER);
        commonActions.enterTextIntoInputBox(issuingInstitution, ConstantsOvc.ISSUING_INSTITUTION);
        LOGGER.info("enterTenderDetailsForMoneyOrder complete");
    }
}