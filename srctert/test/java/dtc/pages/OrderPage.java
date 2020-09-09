package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import dtc.data.Customer;
import dtc.steps.CommonActionsSteps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtils;
import utilities.Driver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/27/16.
 */
public class OrderPage {

    private Driver driver;
    private WebDriver webDriver;
    private final CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(OrderPage.class.getName());

    public OrderPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String ORDER_CONFIRMATION_MESSAGE = "Thank you for your order";
    private static String ORDER_CONFIRMATION_MESSAGE_SENT_TO = "An order confirmation has been sent to ";
    private static final String SURVEY_FEEDBACK_LINK_TEXT = "Tell us about your web experience";
    
    //TODO: Update to use auto className when available
    @FindBy(css = "span.cart-confirmation__order-info")
    public static WebElement orderNumber;

    @FindBy(className = "order-summary__price")
    public static WebElement orderTotal;

    @FindBy(className = "confirmation")
    public static WebElement confirmationContainer;
    
    @FindBy(className = "cart-confirmation__section")
    public static WebElement customerConfirmationContainer;
    
    @FindBy(className = "cart-confirmation__order-message")
    public static WebElement confirmationMessageContainer;

    @FindBy(className = "cart-confirmation__action")
    public static WebElement confirmationSurveyContainer;
    
    @FindBy(className = "cart-summary__breakdown-price")
    public static WebElement orderPageCartSummaryItemsPrice;

    @FindBy(className = "cart-confirmation__appointment-date")
    public static WebElement orderPageDateTime;
    
    @FindBy(className = "cart-item__fee-details-price")
    public static WebElement envFeeSubtotalPrice;
    
    @FindBy(css = ".cart-item__fee-details")
    public static WebElement cartItemFeeDetails;

    public static By orderConfirmationTitleBy = By.className("page-title");
    public static final By orderCartSummaryBy = By.className("cart-summary__breakdown");
    public static final By feeInfoBy = By.className("cart-item__fee-details");
    public static final By cartItemDetailsBy = By.className("cart-wrapper");
    public static final By cartItemColumnDetailsBy = By.className("cart-item__column");

    /**
     * Writes order number, store number, and timestamp to txt file for post-run integration testing
     */
    public void storeOrderNumber() {
        LOGGER.info("storeOrderNumber started");
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String cleanedOrderNumber = orderNumber.getText().replaceAll("[^\\d]", "");
            String orderInfo = "Order Number: " + cleanedOrderNumber + ", Store: " +
                    Config.getDefaultStore() + ", Date/Time: " + dateFormat.format(date);
            LOGGER.info(orderInfo);
            driver.scenarioData.setCurrentOrderNumber(cleanedOrderNumber);
            CommonUtils.appendFile(Constants.ORDERS_FILE_LOC.concat(ConstantsDtc.ORDERS_FILE), orderInfo + "\n");
        } catch (Exception e) {
            String msg = "There was an error when attempting to store the order number: " + e.toString();
            LOGGER.info(msg);
            CommonUtils.appendFile(Constants.ORDERS_FILE_LOC.concat(ConstantsDtc.ORDERS_FILE), msg + "\n");
        }
        LOGGER.info("storeOrderNumber completed");
    }

    /**
     * Stores order number in scenario data for integration testing
     */
    public void storeOrderNumberInScenarioData() {
        LOGGER.info("storeOrderNumberInScenarioData started");
        String cleanedOrderNumber = orderNumber.getText().replaceAll("[^\\d]", "");
        driver.scenarioData.setCurrentOrderNumber(cleanedOrderNumber);
        LOGGER.info("Successfully stored order number: " + cleanedOrderNumber);
        LOGGER.info("storeOrderNumberInScenarioData completed");
    }

    /**
     * Asserts order confirmation message appears on page
     */
    public void confirmOrder() {
        LOGGER.info("confirmOrder started");
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (Config.isSafari()) {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        } else {
            driver.waitForPageToLoad();
        }
        Assert.assertTrue("FAIL: The order confirmation page was NOT displayed!",
                driver.waitForTextPresent(orderConfirmationTitleBy,
                        ORDER_CONFIRMATION_MESSAGE, Constants.DEFAULT_SEC_WAIT));
        LOGGER.info("Confirmed that order success message was displayed.");
        LOGGER.info("confirmOrder completed");
    }

    /**
     * Asserts product name and item code appear on order list
     *
     * @param product product to validate appears
     * @param item    item to validate appears
     */
    public void assertItemOnOrderConfirmationPage(String product, String item) {
        LOGGER.info("assertItemOnOrderConfirmationPage started");
        String productToVerify;

        if (Config.isSafari()) {
            productToVerify = product;
        } else {
            productToVerify = product.toUpperCase();
        }

        LOGGER.info("cartItemDetails contains" + webDriver.findElement(cartItemDetailsBy).getText());
        Assert.assertTrue("FAIL: The expected product: " + productToVerify
                        + " was NOT found on the final order confirmation page!",
                driver.waitForTextPresent(cartItemDetailsBy, productToVerify, Constants.TEN_SEC_WAIT));
        Assert.assertTrue("FAIL: The expected item code: " + item
                        + " was NOT found on the final order confirmation page!",
                driver.waitForTextPresent(cartItemDetailsBy, item, Constants.TEN_SEC_WAIT));
        LOGGER.info("Confirmed \"" + product + "\" and \"" + item + "\" found on final order confirmation page.");
        LOGGER.info("assertItemOnOrderConfirmationPage completed");
    }

    /**
     * Asserts tha Total on screen matches the price from before
     *
     * @param total Price that should appear in the Total section
     */
    public void assertOrderTotal(String total) {
        LOGGER.info("assertOrderTotal started");
        driver.waitForElementVisible(orderTotal);
        Assert.assertTrue("FAIL: Order Summary Total price did not equal " + orderTotal.getText(),
                total.equalsIgnoreCase(orderTotal.getText()));
        LOGGER.info("assertOrderTotal completed");
    }

    /**
     * Asserts that specified text is visible on the order confirmation page
     *
     * @param text Text to verify on order confirmation page
     */
    public void assertTextOnOrderConfirmationPage(String text) {
        LOGGER.info("assertTextOnOrderConfirmationPage started");
        driver.waitForElementVisible(confirmationContainer);
        Assert.assertTrue("FAIL: Confirmation page did not contain \"" + text + "\"",
                confirmationContainer.getText().contains(text));
        LOGGER.info("assertTextOnOrderConfirmationPage completed");
    }

    /**
     * Selects / expands the "show fee details" section for an item on the Order confirmation page
     */
    public void expandFeeDetailsForItem() {
        LOGGER.info("expandFeeDetailsForItem started");
        driver.waitForElementVisible(feeInfoBy);
        webDriver.findElement(feeInfoBy).click();
        LOGGER.info("expandFeeDetailsForItem completed");
    }

    /**
     * Verifies the order total amounts on confirmation page matches with Shopping cart order total
     */
    public void assertOrderConfirmationAndCartOrderTotal() {
        LOGGER.info("assertConfirmationAndCartOrderTotal started");
        driver.waitForElementVisible(CartPage.orderTotal);
        double confirmationOrderTotal = commonActions.cleanMonetaryString(CartPage.orderTotal.getText());
        Assert.assertTrue("FAIL: Expected order total on Order Confirmation page: ("
                        + CommonActionsSteps.orderTotal + ") did not match to the actual order total: ("
                        + confirmationOrderTotal + ")",
                CommonActionsSteps.orderTotal == confirmationOrderTotal);
        LOGGER.info("assertConfirmationAndCartOrderTotal completed");
    }

    /**
     * Extracts the sales tax from Order Confirmation page
     *
     * @return salesTax typed as a Double
     */
    public double extractSalesTaxOnOrderConfirmation() {
        WebElement tax = driver.getElementWithText(CartPage.cartSummaryBreakDownNameBy, ConstantsDtc.SALES_TAX);
        WebElement taxParent = driver.getParentElement(driver.getParentElement(tax));
        return commonActions.cleanMonetaryString(taxParent.findElement(CartPage.cartSummaryBreakDownPrice).getText());
    }

    /**
     * Verifies the sales tax amount on Order confirmation page matches with estimated tax on Shopping cart
     */
    public void assertOrderConfirmationAndCartSalesTax() {
        LOGGER.info("assertConfirmationAndCartSalesTax started");
        driver.waitForElementVisible(CartPage.cartSummaryBreakDownNameBy);
        double orderConfirmationSalesTax = extractSalesTaxOnOrderConfirmation();
        Assert.assertTrue("FAIL: Expected sales tax on Order confirmation page: ("
                        + CommonActionsSteps.salesTax + ") did not match to the actual sales tax: ("
                        + orderConfirmationSalesTax + ")",
                CommonActionsSteps.salesTax == orderConfirmationSalesTax);
        LOGGER.info("assertConfirmationAndCartSalesTax completed");
    }

    /**
     * Validates current date and time listed on the OrderConfirmation page
     *
     * @param date Appointment Date to verify.  Example format:  Wednesday, January 24, 2018
     * @param time Appointment Time to verify.  Example format:  11:00 AM
     */
    public void verifyDateAndTime(String date, String time) {
        LOGGER.info("Order Page verifyDateAndTime started");
        driver.waitForElementVisible(orderPageDateTime);
        String apptDT = orderPageDateTime.getText();
        String apptDate = apptDT.split("-")[0].trim();
        String apptTime = apptDT.split("-")[1].trim();

        date = CommonUtils.replaceLongMonthWithShortMonth(date);
        apptDate = CommonUtils.replaceLongMonthWithShortMonth(apptDate);

        Assert.assertTrue("FAIL: Did NOT see expected date \"" + date + "\" in appointment info: \""
                + apptDate + "\"!", date.contains(apptDate));
        LOGGER.info("Confirmed that expected date \"" + date + "\" was listed in appointment info");

        Assert.assertTrue("FAIL: Did NOT see expected time \"" + time + "\" in appointment info: \""
                + apptTime + "\"!", apptTime.equals(time));
        LOGGER.info("Confirmed that expected time (" + time + ") was listed in appointment info");
        LOGGER.info("Order Page verifyDateAndTime completed");
    }

    /**
     * Verifies the order total amounts on confirmation page matches with checkout order total
     */
    public void assertOrderConfirmationAndCheckoutOrderTotal() {
        LOGGER.info("assertOrderConfirmationAndCheckoutOrderTotal started");
        driver.waitForElementVisible(CartPage.orderTotal);
        double confirmationOrderTotal = commonActions.cleanMonetaryString(CartPage.orderTotal.getText());
        Assert.assertTrue("FAIL: Expected order total on Order Confirmation page: ("
                        + CommonActionsSteps.orderTotalCheckout + ") did not match to the actual order total: ("
                        + confirmationOrderTotal + ")",
                CommonActionsSteps.orderTotalCheckout == confirmationOrderTotal);
        LOGGER.info("assertOrderConfirmationAndCheckoutOrderTotal completed");
    }

    /**
     * Verifies the sales tax amount on Order confirmation page matches with estimated tax on checkout page
     */
    public void assertOrderConfirmationAndCheckoutSalesTax() {
        LOGGER.info("assertOrderConfirmationAndCheckoutSalesTax started");
        driver.waitForElementVisible(CartPage.cartSummaryBreakDownNameBy);
        double orderConfirmationSalesTax = extractSalesTaxOnOrderConfirmation();
        Assert.assertTrue("FAIL: Expected sales tax on Order confirmation page: ("
                        + CommonActionsSteps.salesTaxCheckout + ") did not match to the actual sales tax: ("
                        + orderConfirmationSalesTax + ")",
                CommonActionsSteps.salesTaxCheckout == orderConfirmationSalesTax);
        LOGGER.info("assertOrderConfirmationAndCheckoutSalesTax completed");
    }

    /**
     * Asserts that specified customer text is visible on the order confirmation page
     *
     * @param text Text to verify on order confirmation page
     */
    public void assertCustomerDetailsOnOrderConfirmationPage(String text) {
        LOGGER.info("assertCustomerDetailsOnOrderConfirmationPage started");
        driver.waitForElementVisible(customerConfirmationContainer);
        Assert.assertTrue("FAIL: Confirmation page did not contain \"" + text + "\"",
        		customerConfirmationContainer.getText().contains(text));
        LOGGER.info("assertCustomerDetailsOnOrderConfirmationPage completed");
    }

    /**
     * Asserts that specified text is visible on the order confirmation page
     *
     * @param element WebElement to refer
     * @param text Text to verify on order confirmation page
     */
    public void assertTextOnOrderConfirmationPage(WebElement element, String text) {
        LOGGER.info("assertTextOnOrderConfirmationPage started");
        driver.waitForElementVisible(element);
        Assert.assertTrue("FAIL: Confirmation page did not contain \"" + text + "\"",
        		element.getText().contains(text));
        LOGGER.info("assertTextOnOrderConfirmationPage completed");
    }

    /**
     * Verify Customer details on order confirmation page
     *
     * @param customer        Type of customer dtc.data to use in Customers class
     */
    public void verifyCustomerDetailsOnOrderConfirmation(Customer customer) {
        LOGGER.info("verifyCustomerDetailsOnOrderConfirmation started");
        ORDER_CONFIRMATION_MESSAGE_SENT_TO += customer.email;
        assertTextOnOrderConfirmationPage(confirmationMessageContainer, ORDER_CONFIRMATION_MESSAGE_SENT_TO);
        assertCustomerDetailsOnOrderConfirmationPage(customer.firstName);
        assertCustomerDetailsOnOrderConfirmationPage(customer.lastName);
        assertCustomerDetailsOnOrderConfirmationPage(customer.address1);
        assertCustomerDetailsOnOrderConfirmationPage(customer.zip);
        assertCustomerDetailsOnOrderConfirmationPage(customer.email);
        assertCustomerDetailsOnOrderConfirmationPage(customer.phone);
        LOGGER.info("verifyCustomerDetailsOnOrderConfirmation completed");
	}
    
    /**
     * Verify Customer email confirmation message on order confirmation page
     *
     * @param customer        Type of customer dtc.data to use in Customers class
     */
    public void verifyCustomerEmailMessageOnOrderConfirmation(Customer customer) {
        LOGGER.info("verifyCustomerEmailMessageOnOrderConfirmation started");
        ORDER_CONFIRMATION_MESSAGE_SENT_TO += customer.email;
        assertTextOnOrderConfirmationPage(confirmationMessageContainer, ORDER_CONFIRMATION_MESSAGE_SENT_TO);
        LOGGER.info("verifyCustomerEmailMessageOnOrderConfirmation completed");
	}

    /**
     * Verify Survey feedback link is displayed on the order confirmation page
     */
    public void verifySurveyLinkDisplayedOnOrderConfirmationPage() {
        LOGGER.info("verifySurveyLinkDisplayedOnOrderConfirmationPage started");
        driver.waitForElementVisible(confirmationSurveyContainer);
        Assert.assertTrue("FAIL: Confirmation page did not contain \"" + SURVEY_FEEDBACK_LINK_TEXT + "\" Survey Feedback link",
        		confirmationSurveyContainer.getText().contains(SURVEY_FEEDBACK_LINK_TEXT));
        LOGGER.info("verifySurveyLinkDisplayedOnOrderConfirmationPage completed");
    }
    
    /**
     * Clicks Survey feedback link via linkText
     */
    public void clickSurveyLink() {
        LOGGER.info("clickSurveyLink started");
        driver.clickElementWithLinkText(SURVEY_FEEDBACK_LINK_TEXT);
        LOGGER.info("clickSurveyLink completed");
    }
    
    /**
     * Verify environment fee label & subtotal price on order confirmation page
     */
    public void verifyEnvironmentFeeDetailsOnOrderConfirmation() {
        LOGGER.info("verifyEnvironmentFeeDetailsOnOrderConfirmation started");
        WebElement envFeeElement = cartItemFeeDetails.findElement(cartItemColumnDetailsBy);
        assertTextOnOrderConfirmationPage(envFeeElement, ConstantsDtc.ENVIRONMENTAL_FEE);
        assertTextOnOrderConfirmationPage(envFeeSubtotalPrice, CartPage.environmentFee);
        LOGGER.info("verifyEnvironmentFeeDetailsOnOrderConfirmation completed");
    }
}