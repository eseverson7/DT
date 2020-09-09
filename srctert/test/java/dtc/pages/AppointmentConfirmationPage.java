package dtc.pages;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
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

import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Channing Luden on 10/17/2016.
 */
public class AppointmentConfirmationPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Customer customer;
    private final Logger LOGGER = Logger.getLogger(AppointmentPage.class.getName());
    private final CommonActions commonActions;

    public AppointmentConfirmationPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        customer = new Customer();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
    }

    @FindBy(className = "cart-confirmation__order-message")
    public static WebElement confirmationMessage;

    @FindBy(xpath = "//div[h5/text()='Appointment Details']")
    public static WebElement appointmentDetailsSection;
    
    @FindBy(className = "cart-confirmation__order")
    public static WebElement appointmentOrderMessage;

    @FindBy(className = "cart-confirmation__store-address")
    public static WebElement confirmationStoreName;

    public static final String CART_CONFIRMATION_ORDER_MESSAGE  =  "cart-confirmation__order-message";

    private static final By appointmentInfoWrapper = By.className("cart-wrapper");

    private static final By appointmentDetailsEmail =
            By.xpath("//p[contains(text(),\"A copy of your appointment details has been sent to\")]");

    private static final By appointmentDetailsMyNameInfoWebBy = By.xpath("//div[@class='cart-confirmation__column display-sm']//span[@class='cart-confirmation__my-info-name']");
    
    private static final By appointmentDetailsMyEmailInfoWebBy = By.xpath("//div[@class='cart-confirmation__column display-sm']//span[@class='cart-confirmation__my-info-email']");

    private static final By appointmentDetailsMyPhoneInfoWebBy = By.xpath("//div[@class='cart-confirmation__column display-sm']//span[@class='cart-confirmation__my-info-phone']");

    private static final By scheduledServices = By.className("cart-confirmation__section-list-item");

    private static final By appointmentDetailsMyNameInfoMobileBy = By.xpath("//div[@class='cart-confirmation__column']//span[@class='cart-confirmation__my-info-name']");

    private static final By appointmentDetailsMyEmailInfoMobileBy = By.xpath("//div[@class='cart-confirmation__column']//span[@class='cart-confirmation__my-info-email']");

    private static final By appointmentDetailsMyPhoneInfoMobileBy = By.xpath("//div[@class='cart-confirmation__column']//span[@class='cart-confirmation__my-info-phone']");

    /**
     * Confirms an appointment has successfully been created for the specified customer. Validates messaging, an
     * appointment number, email to receive details, and My Info section contents match with customer info.
     *
     * @param customerType name of customer enum to be used from Customer.java
     */
    public void confirmAnAppointmentPlaced(String customerType) {
        LOGGER.info("confirmAnAppointmentPlaced started");
        int i = 0;

        if (Config.isMobile()){
            driver.waitForPageToLoad();
        }

        driver.waitForClassPresent(CART_CONFIRMATION_ORDER_MESSAGE, Constants.FIVE_SEC_WAIT);
        Customer apptCust = customer.getCustomer(customerType);
        String email = apptCust.email;

        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT))
            email = email.replaceAll(ConstantsDtc.DISCOUNT_TIRE, ConstantsDtc.AMERICAS_TIRE);

        Assert.assertTrue("FAIL: The confirmation message for a successfully placed order was NOT displayed!",
                confirmationMessage.getText().contains("Your order has been placed successfully"));

        if (!Config.isSafari()) {
            Pattern pattern = Pattern.compile("Order No\\. [0-9]{8}", Pattern.CASE_INSENSITIVE);
            Matcher match = pattern.matcher(webDriver.findElement(appointmentInfoWrapper).getText());

            Boolean tempBool = match.find();
            Assert.assertTrue("FAIL: An appointment number was NOT displayed for the customer", tempBool);
        }

        Assert.assertTrue("FAIL: Details NOT sent to expected email " + email,
        		confirmationMessage.getText().contains(email));

        List<String> myInfoDetails = Arrays.asList(
                apptCust.firstName.concat(" "+ apptCust.lastName), email, apptCust.phone);

        List<By> myInfoElements;
        if (Config.isMobile()) {
            myInfoElements = Arrays.asList(appointmentDetailsMyNameInfoMobileBy, appointmentDetailsMyEmailInfoMobileBy,
                    appointmentDetailsMyPhoneInfoMobileBy);
        } else {
            myInfoElements = Arrays.asList(appointmentDetailsMyNameInfoWebBy, appointmentDetailsMyEmailInfoWebBy,
                    appointmentDetailsMyPhoneInfoWebBy);
        }

        for (String detail : myInfoDetails) {
            Assert.assertTrue("FAIL: My Info section does NOT contain expected detail: " + detail,
                    webDriver.findElement(myInfoElements.get(i)).getText().contains(detail));
            i++;
        }
        LOGGER.info("confirmAnAppointmentPlaced completed");
    }

    /**
     * Validates expected service option(s) is/are displayed on the appointment confirmation page.
     *
     * @param serviceOptions service option(s) expected to be on confirmation page
     */
    public void assertServiceOnConfirmationPage(String serviceOptions) {
        LOGGER.info("assertServiceOnConfirmationPage started");
        List<String> expectedOptions = Lists.newArrayList(Splitter.on(",").trimResults().split(serviceOptions));
        List<WebElement> displayedOptionElements = webDriver.findElements(scheduledServices);
        List<String> displayedOptions = new ArrayList<String>(displayedOptionElements.size());

        for (WebElement displayedOptionEle : displayedOptionElements) {
            displayedOptions.add(displayedOptionEle.getText());
        }

        Assert.assertTrue("FAIL: Unable to find previously selected service options: " + expectedOptions +
                        " in displayed section: " + displayedOptions,
                displayedOptions.containsAll(expectedOptions));
        LOGGER.info("assertServiceOnConfirmationPage completed");
    }

    /**
     * Validates the expected store, date, and time are displayed in the Appointment Details section of the Appointment
     * confirmation page.
     *
     * @param store expected store to validate
     * @param date  expected date to validate
     * @param time  expected time to validate
     */
    public void verifyAppointmentDetailsSectionInfo(String store, String date, String time) {
        LOGGER.info("verifyAppointmentDetailsSectionInfo started");
        driver.waitForElementVisible(appointmentDetailsSection);

        date = commonActions.convertDateToDayMonthDateFormat(date);
        date = CommonUtils.replaceLongMonthWithShortMonth(date);
        String[] dateText = date.split(", ");
        date = dateText[0] + ", " + dateText[1];
        if (time.substring(0, 1).equals("0")) {
        	time = time.substring(1, time.length()-1);
        }
        List<String> appointmentDetails = Lists.newArrayList(store, date, time);
        for (String details : appointmentDetails) {
            Assert.assertTrue("FAIL: The appointment detail: '" + details
                    + "' was not found in the Appointment Details section",
                    appointmentDetailsSection.getText().toLowerCase().contains(details.toLowerCase()));
        }
        LOGGER.info("verifyAppointmentDetailsSectionInfo completed");
    }

    /**
     * Verifies whether the confirmation email shows up in Upper or Lower case
     *
     * @param letterCasing Email displays in 'UPPERCASE' or 'LOWERCASE'
     */
    public void confirmEmailLetterCasing(String letterCasing) {
        LOGGER.info("confirmEmailLetterCasing started");
        driver.waitForElementVisible(appointmentDetailsEmail);

        WebElement emailDetail = webDriver.findElement(appointmentDetailsEmail);

        if (letterCasing.equals(ConstantsDtc.LOWERCASE)) {
            Assert.assertTrue("FAIL: Order Confirmation page WAS NOT LOWER CASE: " + customer.emailLower  + ".",
                    emailDetail.getText().contains(customer.emailLower));
        } else if (letterCasing.equals(ConstantsDtc.UPPERCASE)) {
            Assert.assertTrue("FAIL: Order Confirmation page WAS NOT UPPER CASE: " + customer.emailUpper  + ".",
                    emailDetail.getText().contains(customer.emailUpper));
        }

        LOGGER.info("confirmEmailLetterCasing completed");
    }

    /**
     * verify the displayed order placed date is correct
     */
    public void assertOrderPlacedDate() {
        LOGGER.info("assertOrderPlacedDate started");
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Date date = new Date();
        String expectedDate = dateFormat.format(date);
        int len = expectedDate.length();
        driver.waitForElementVisible(appointmentOrderMessage);
        String displayDate = appointmentOrderMessage.getText()
        		.split("Order Placed: ")[1];
        Assert.assertTrue("FAIL: Displayed date order placed "
        		+ "was incorrect.  Expected:  " + expectedDate
        		+ ".  Displayed:  " + displayDate,
        	displayDate.substring(0, len).equals(expectedDate));
        LOGGER.info("assertOrderPlacedDate completed");
    }

    /**
     * Verify the store name on the confirmation page matches the
     * store name on the cart page
     */
    public void assertConfirmationAndCartStoreName() {
        LOGGER.info("assertConfirmationAndCartStoreName started");
        driver.waitForElementVisible(confirmationStoreName);
        String confStoreName = confirmationStoreName.getText();
        String[] storeNameText = confStoreName.split(", ");
        confStoreName = storeNameText[0] + ", " + storeNameText[1];
        Assert.assertTrue("FAIL: Expected Store Name: ("
        	+ CommonActionsSteps.storeName + ") did not match the "
        	+ "displayed Store Name: (" + confStoreName + ")",
        	confStoreName.equals(CommonActionsSteps.storeName.replace(" - ", ", ")));
        LOGGER.info("assertConfirmationAndCartStoreName completed");
    }

    /**
     * Verify the sales tax on the confirmation page matches the
     * sales tax on the cart page
     */
    public void assertConfirmationAndCartSalesTax() {
        LOGGER.info("assertConfirmationAndCartSalesTax started");
        WebElement tax = driver.getElementWithText(
        		CartPage.cartSummaryBreakDownNameBy, ConstantsDtc.SALES_TAX);
        WebElement taxParent = driver.getParentElement(
        		driver.getParentElement(tax));
        double confSalesTax = commonActions.cleanMonetaryString(
        		taxParent.findElement(CartPage.cartSummaryBreakDownPrice).getText());
        Assert.assertTrue("FAIL: Expected Sales Tax: ("
        		+ CommonActionsSteps.salesTax + ") did not match the "
                + "displayed Sales Tax: ("+ confSalesTax + ")",
                confSalesTax == CommonActionsSteps.salesTax);
        LOGGER.info("assertConfirmationAndCartSalesTax completed");
    }

    /**
     * Verify the order total on the confirmation page matches the
     * order total on the cart page
     */
    public void assertCheckoutAndCartOrderTotal(String currencyString) {
        LOGGER.info("assertCheckoutAndCartOrderTotal started");
        double confTotal = Double.parseDouble(currencyString.replaceAll("[^0-9.]", ""));
        Assert.assertTrue("FAIL: Expected Order Total: ("
        		+ CommonActionsSteps.orderTotal + ") did not match the "
                + "displayed Order Total: ("+ confTotal + ")",
                confTotal == CommonActionsSteps.orderTotal);
        LOGGER.info("assertCheckoutAndCartOrderTotal completed");
    }
}
