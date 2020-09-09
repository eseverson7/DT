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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtils;
import utilities.Driver;

import static java.util.Calendar.MINUTE;

import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/27/16.
 */
public class CheckoutPage {

    private Driver driver;
    private WebDriver webDriver;
    private final CommonUtils commonUtils;
    private final CommonActions commonActions;
    private final Customer customer;
    private final AppointmentPage appointmentPage;
    private static final Logger LOGGER = Logger.getLogger(CheckoutPage.class.getName());

    public CheckoutPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonUtils = new CommonUtils();
        commonActions = new CommonActions(driver);
        customer = new Customer();
        appointmentPage = new AppointmentPage(driver);
        PageFactory.initElements(webDriver, this);
    }

    public static final String UPS_FREE = "UPS Ground - Free Shipping";
    private static final String SHIPPING_ADDRESS = "Shipping Address";
    private static final String DELIVERY_METHOD = "Delivery Method";
    private static final String PAYMENT_BILLING_ADDRESS = "Payment & Billing Address";
    private static final String AMERICAN_EXPRESS = "American Express";
    private static final String DISCOVER = "Discover";
    private static final String MASTERCARD = "MasterCard";
    public static final String VISA = "Visa";
    private static final String CAR_CARE_ONE = "CarCareOne";
    private static final String VISA_BOPIS = "Visa Bopis";
    private static final String AMERICAN_EXPRESS_BOPIS = "American Express Bopis";
    private static final String DISCOVER_BOPIS = "Discover Bopis";
    private static final String MASTERCARD_BOPIS = "MasterCard Bopis";
    private static final String CAR_CARE_ONE_BOPIS = "CarCareOne Bopis";
    private static final String FIRST_NAME = "First Name";
    private static final String LAST_NAME = "Last Name";
    private static final String ADDRESS_LINE_1 = "Address Line 1";
    private static final String ADDRESS_LINE_2 = "Address Line 2";
    private static final String STATE = "State";
    private static final String COUNTRY = "Country";
    private static final String ZIP = "Zip / Postal Code";
    private static final String EMAIL = "Email";
    private static final String WALKINS_WELCOME = "Walk-Ins Welcome";
    private static final String SHIPPING_FREE = "FREE";
    private static final String VIEW_TIMES = "View Times";
    private static final String STORE_CLOSED = "Store Closed";
    private static final String PEAK_TIMES_MESSAGE = "Peak Times: Expect a longer wait for service";
    private static final String SERVICE_TIME_LONGER_THAN_USUAL =
            "Service time for this appointment may be longer than usual.";
    private static final String EST_TIME_OF_COMPLETION = "Est. time of completion:";
    private static final String ACCEPT = "accept";
    private static final String DECLINE = "decline";
    private static final String ADDRESS_VERIFICATION = "Address Verification";
    private static final String SHIPPING_RESTRICTION = "Shipping Restriction";
    private static final String EDIT_CART = "Edit Cart";
    private static final String REMOVE_ITEMS = "Remove Items";
    private static final String SHIPPING_RESTRICTION_MESSAGING =
            "The following brands may not be shipped to the location you specified:";
    private static final String CHANGE_ADDRESS = "Change Address";
    private static final String FULLDAY = "Fullday";
    private static final String UNABLE_TO_PROCESS_CARD = "We're sorry, we were unable to process the card";
    private static final String SECONDARY = "secondary";

    private static final String countryDropDownWebString = "country_chosen";
    private static final String countryDropDownMobileString = "country";
    private static final String stateDropDownWebString = "addressProvince_chosen";
    private static final String phoneTypeDropDownWebString = "phoneTypeId_chosen";
    private static final String verifyAddressPopupClosedJs =
            "return document.getElementById('popup_suggested_delivery_addresses_form')===null;";
    private static final String[] CART_SUMMARY_PROMOTIONS_DISPLAY_CHECKOUT_DTAT = {"summary-product__header",
            "checkout-summary-cart__fees", "cart-item__section summary-product__promotions", "summary-product__header",
            "checkout-summary-cart__fees", "collapsible-section checkout-summary-cart__fees",
            "cart-item__section summary-product__promotions", "summary-product__subtotal"};
    private static final String[] CART_SUMMARY_PROMOTIONS_DISPLAY_CHECKOUT_DTD = {"summary-product__header",
            "checkout-summary-cart__fees", "cart-item__section summary-product__promotions", "summary-product__header",
            "checkout-summary-cart__fees", "cart-item__section summary-product__promotions", "summary-product__subtotal"};

    public static String stateTaxString = "0.00";
    public static String cityTaxString = "0.00";
    public static String otherTaxString = "0.00";
    private static String splitPayRemainingAmount = "0";
    private static final String availTimeClassString = "appointment-details__select-time-row-time--available";

    private static final By summaryProductProductTotalBy = By.className("summary-product__product-total");

    public static final By salesTaxRowBy = By.className("checkout-summary-cart__tax-row");

    public static final By feeDetailsBy = By.className("summary-product__fees-container");

    private static final By sectionHeadersBy = By.className("checkout-steps__header");

    private static final By verifyAddressContinuebtnBy = By.id("use_suggested_address_button");

    private static final By formGroupBy = By.className("form-group");

    private static final By continueToButtonBy = By.className("dt-form__submit");

    private static final By paymentTypesContainerBy = By.className("payment-details__checkout-types");

    private static final By availableApptDatesBy = By.className("appointment-details__select-date--active");

    private static final By allAvailableApptDatesBy = By.className("appointment-details__select-date");

    private static final By availableApptTimesBy = By.className("appointment-details__select-time-row-time--available");

    private static final By allApptTimesBy = By.className("appointment-details__select-time-row-time");

    private static final By apptDatesDayBy = By.className("appointment-details__select-date-day");

    private static final By tooltipBtnBy = By.xpath("//BUTTON[@class=\"tooltip-toggle icon-info\" and text() = \"i\"]");

    private static final By correctedAddressBtnBy = By.className("dt-form__submit");

    private static final By summaryProductBy = By.className("summary-product");

    private static final By checkoutSummaryCertificateBy = By.className("checkout-summary-cart__fees-certificates");

    private static final By checkoutSummaryFeeTpmsBy = By.className("checkout-summary-cart__fees-tpms");

    private static final By dtModalTitleBy = By.className("dt-modal__title");

    private static final By dtModalButtonBy = By.className("dt-button");

    private static final By peakHoursRowContainerBy =
            By.className("appointment-details__select-time-row-container--peak");

    private static final By appointmentRowLabelBy = By.className("appointment-details__select-time-row-label");

    private static final By checkoutOrderSummaryBy = By.className("checkout-summary-cart__products");

    private static final By productQuantityBy = By.className("summary-product__product-quantity");

    private static final By ropisBy = By.cssSelector(".form-group__radio-label[for='checkoutType-ropis']");

    private static final By bopisBy = By.cssSelector(".form-group__radio-label[for='checkoutType-bopis']");

    private static final By ccNumberBy = By.xpath("//input[@name='cardNumber']");

    private static final By ccNameBy = By.xpath("//input[@name='nameOnCard']");

    private static final By ccCvnBy = By.xpath("//input[@name='cardCVN']");

    private static final By addressLine1By = By.name("billingAddress1");

    private static final By billingZipCodeBy = By.name("billingZipCode");

    private static final By amountBy = By.name("amount");

    private static final By summaryProductDisplayBy = By.cssSelector(".summary-product > div");

    private static final By editCardDetailsLinkBy = By.linkText("Edit card details");

    private static final By checkoutErrorBy = By.className("checkout-general-error");

    @FindBy(id = "first-name")
    public static WebElement firstName;

    @FindBy(id = "last-name")
    public static WebElement lastName;

    @FindBy(id = "email-address")
    public static WebElement email;

    @FindBy(id = "phone-number")
    public static WebElement phone;

    @FindBy(id = "address-line-1")
    public static WebElement address1;

    @FindBy(id = "address-line-2")
    public static WebElement address2;

    @FindBy(name = "postcode")
    public static WebElement zip;

    @FindBy(className = "react-selectize-toggle-button")
    public static WebElement withoutApptReasonToggleBtn;

    @FindBy(id = "addressSubmit")
    public static WebElement addressSubmitButton;

    @FindBy(id = "placeOrder")
    public static WebElement placeOrderButton;

    @FindBy(xpath = "//input[@id='scheduledAppointment']/following-sibling::span[contains(@class,'dt-radio__display')]")
    public static WebElement scheduleAppointmentRadio;

    @FindBy(xpath = "//input[@id='reserveItemsOnly']/following-sibling::span[contains(@class,'dt-radio__display')]")
    public static WebElement reserveItemsRadio;

    @FindBy(id = "month")
    public static WebElement ccMonthExpiryMobile;

    @FindBy(id = "year")
    public static WebElement ccYearExpiryMobile;

    @FindBy(className = "delivery-method__radio")
    public static WebElement deliverySection;

    @FindBy(className = "modal-box")
    public static WebElement creditCardConsentModal;

    @FindBy(className = "consentDisclosure")
    public static WebElement acceptConsentButton;

    @FindBy(className = "declineDisclosure")
    public static WebElement declineConsentButton;

    @FindBy(className = "termsOfAgreement")
    public static WebElement creditCardToaModal;

    @FindBy(className = "acceptAndSubmitTerms")
    public static WebElement toaAcceptAndSubmitButton;

    @FindBy(className = "declineTerms")
    public static WebElement toaDeclineButton;

    @FindBy(className = "checkout-summary-cart__cart-items-price")
    public static WebElement checkoutSubtotal;

    @FindBy(className = "checkout-summary-cart__order-total-price")
    public static WebElement checkoutOrdTotal;

    @FindBy(className = "auto-cart-summary-fee-detail")
    public static WebElement feeCartSummaryItem;

    @FindBy(className = "cart-summary__opt-fee-detail")
    public static WebElement serviceCartSummaryItem;

    @FindBy(className = "checkout-steps__edit")
    public static WebElement edit;

    @FindBy(className = "checkout-form__optional-address-toggle")
    public static WebElement expandOptionalAddressLine;

    @FindBy(className = "checkout-summary__edit-link")
    public static WebElement editShippingDetailsLink;

    @FindBy(className = "delivery-method__fetch")
    public static WebElement seeMoreOptionsLink;

    @FindBy(className = "payment-details-form__use-shipping-address-label")
    public static WebElement billingSameAsShippingOption;

    @FindBy(xpath = "//label[@for='reserveItemsOnly']")
    public static WebElement installWithoutApptBtn;

    @FindBy(xpath = "//label[@for='scheduledAppointment']")
    public static WebElement installWithApptBtn;

    @FindBy(className = "appointment-details__select-appointment-date-container")
    public static WebElement apptDateContainer;

    @FindBy(className = "appointment-details__select-time-message")
    public static WebElement apptSelectedMsg;

    @FindBy(className = "appointment-details__select-time")
    public static WebElement apptTimeContainer;

    @FindBy(className = "dt-form__submit")
    public static WebElement defaultButton;

    @FindBy(className = "checkout-summary-cart__cart-items-label")
    public static WebElement expandCartItemsLabel;

    @FindBy(className = "checkout-summary-cart__fees-header-label")
    public static WebElement expandFeeDetailsLabel;

    @FindBy(className = "checkout-summary-cart__cart-items-price")
    public static WebElement checkoutPageCartSummaryItemsPrice;

    @FindBy(className = "checkout-summary-cart__line-item-price")
    public static WebElement checkoutAndOrderCartSummarySalesTax;

    @FindBy(xpath = "//span[contains(.,'Shipping')]/following-sibling::span")
    public static WebElement shipping;

    @FindBy(id = "customerInformationForm")
    public static WebElement customerInfoField;

    @FindBy(className = "tool-tip__content")
    public static WebElement tooltipContent;

    @FindBy(className = "appointment-details__select-time-no-availability-text")
    public static WebElement noAppointmentTimesAvailable;

    @FindBy(className = "checkout-summary-store__address")
    public static WebElement checkoutCartSummaryStoreName;

    @FindBy(className = "appiontment-summary__reason")
    public static WebElement checkoutApptDetailsReason;

    @FindBy(className = "checkout-summary__customer-details")
    public static WebElement checkoutSummaryCustomerDetails;

    @FindBy(className = "checkout-summary-cart__order-total-price")
    private static WebElement orderTotal;

    @FindBy(className = "react-selectize-placeholder")
    private static WebElement installWithoutAppointmentSelectedReason;

    @FindBy(className = "form-group__select")
    private static WebElement installWithoutAppointmentReasonGroup;

    @FindBy(className = "appointment-details__reserve-message")
    private static WebElement reserveAppointmentMessage;

    @FindBy(xpath = "//a[contains(@href, 'make-an-appointment')]")
    public static WebElement makeAnAppointmentLink;

    @FindBy(className = "appointment-details__select-time-row-peak-message")
    private static WebElement peakTimesMessage;

    @FindBy(className = "appointment-details__select-time-row-peak-selected-message-body")
    private static WebElement peakTimeSelectedMessage;

    @FindBy(className = "appointment-details__select-time-row-time--selected")
    private static WebElement selectedAppointmentTime;

    @FindBy(className = "dt-modal__container")
    public static WebElement dtModal;

    @FindBy(className = "checkout-form__shipping-restriction")
    public static WebElement shippingRestrictionMessagingContainer;

    @FindBy(className = "js-nav-checkout-need-help-button")
    public static WebElement needHelpElement;

    @FindBy(className = "fa-envelope")
    public static WebElement needHelpEmailIcon;

    @FindBy(css = "div.popover--checkout-need-help-block")
    public static WebElement needHelpPhoneElement;

    @FindBy(className = "min-header__nav-button--my-account")
    public static WebElement userSignedInLabel;

    @FindBy(className = "appointment-details__select-time-header-previous-date")
    public static WebElement prevDayArrowControl;

    @FindBy(className = "appointment-details__select-time-header-next-date")
    public static WebElement nextDayArrowControl;

    @FindBy(className = "bar-chart__values")
    public static WebElement barChartGraph;

    @FindBy(className = "appointment-details__select-time-first-available")
    public static WebElement firstAvailableAppointmentTimeMessage;

    @FindBy(className = "appointment-details__select-time-row-partial-closed-message")
    public static WebElement partialHolidayClosedMessage;

    @FindBy(className = "payment-details__primary-card")
    public static WebElement paymentDetailsPrimaryCard;

    @FindBy(className = "payment-details__secondary-card")
    public static WebElement paymentDetailsSecondaryCard;

    private class PaymentFields {
        private WebElement ccName;
        private WebElement ccNumber;
        private WebElement ccCvn;
        private WebElement addressLine1;
        private WebElement billingZipCode;

        public WebElement getCcName() {
            return ccName;
        }

        public WebElement getCcNumber() {
            return ccNumber;
        }

        public WebElement getCcCvn() {
            return ccCvn;
        }

        public WebElement getAddressLine1() {
            return addressLine1;
        }

        public WebElement getBillingZipCode() {
            return billingZipCode;
        }

        /**
         * Initializes the PaymentFields private class, and instantiates the class objects. Object instantiation
         *
         * @return PaymentFields class with instantiated objects (payment field WebElements)
         */
        public PaymentFields invokePaymentFields() {
            LOGGER.info("invokePaymentFields started");
            int formIndex = 0;
            if(!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)){
                if (driver.isElementDisplayed(amountBy))
                    formIndex = 1;
            }

            if (Config.isSafari())
                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);

            ccName = webDriver.findElements(ccNameBy).get(formIndex);
            ccNumber = webDriver.findElements(ccNumberBy).get(formIndex);
            ccCvn = webDriver.findElements(ccCvnBy).get(formIndex);
            if (!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
                addressLine1 = webDriver.findElements(addressLine1By).get(formIndex);
                billingZipCode = webDriver.findElements(billingZipCodeBy).get(formIndex);
            }
            LOGGER.info("invokePaymentFields completed");
            return this;
        }
    }

    /**
     * Clicks one of the reservation radio button options on the checkout page
     *
     * @param checkoutType With appointment or without appointment
     */
    public void clickReservationRadioButton(String checkoutType) {
        LOGGER.info("clickReservationRadioButton started");
        if (checkoutType.equalsIgnoreCase(ConstantsDtc.INSTALL_WITHOUT_APPOINTMENT)) {
            driver.waitForElementClickable(reserveItemsRadio, Constants.FIVE_SEC_WAIT);
            reserveItemsRadio.click();
        } else if (checkoutType.equalsIgnoreCase(ConstantsDtc.INSTALL_WITH_APPOINTMENT)) {
            driver.waitForElementClickable(scheduleAppointmentRadio, Constants.FIVE_SEC_WAIT);
            scheduleAppointmentRadio.click();
        } else {
            Assert.fail("FAIL: Did not indicate which of the reservation radio buttons should be selected!");
        }
        LOGGER.info("clickReservationRadioButton completed");
    }

    /**
     * Validates the expected reservation radio button was selected on the checkout page
     *
     * @param checkoutType With appointment or without appointment
     */
    public void assertAppointmentOption(String checkoutType) {
        LOGGER.info("assertAppointmentOption started");
        //TODO: retest when new safaridriver is stable
        //Oddly SafariDriver as of 12/5/16 ends up with schedule appointment checked no matter the prior click.
        // This is not reproducible manually, and various pauses did not resolve it
        if (Config.isSafari()) {
            clickReservationRadioButton(checkoutType);
        }

        driver.waitForElementVisible(CommonActions.radioButtonBy);

        if (checkoutType.equalsIgnoreCase(ConstantsDtc.INSTALL_WITHOUT_APPOINTMENT)) {
            Assert.assertTrue("FAIL: \"" + ConstantsDtc.INSTALL_WITHOUT_APPOINTMENT + "\" option was NOT enabled!",
                    reserveItemsRadio.isEnabled());
            LOGGER.info("Confirmed that \"" + ConstantsDtc.INSTALL_WITH_APPOINTMENT + "\" option was enabled.");
        } else if (checkoutType.equalsIgnoreCase(ConstantsDtc.INSTALL_WITH_APPOINTMENT)) {
            Assert.assertTrue("FAIL: \"" + ConstantsDtc.INSTALL_WITH_APPOINTMENT + "\" option was NOT enabled!",
                    scheduleAppointmentRadio.isEnabled());
            LOGGER.info("Confirmed that \"" + ConstantsDtc.INSTALL_WITH_APPOINTMENT + "\" option was enabled.");
        }
        LOGGER.info("assertAppointmentOption completed");
    }

    /**
     * Fills out all address fields
     *
     * @param customer        Type of customer dtc.data to use in Customers class
     * @param shippingAddress True / False on whether or not to use Customer's shipping address info
     */
    public void enterAddressForCustomer(Customer customer, boolean shippingAddress) {
        LOGGER.info("enterAddressForCustomer started");
        try {
            driver.waitForElementClickable(firstName);
            ArrayList<WebElement> addressFields;
            ArrayList<String> customerInfo;
            WebElement countryDropDown;
            WebElement submitButton;
            String submitButtonText;

            driver.jsScrollToElement(expandOptionalAddressLine);
            expandOptionalAddressLine.click();

            if (shippingAddress) {
                addressFields = new ArrayList<>(
                        Arrays.asList(firstName, lastName, email, phone, address1, address2, zip));
                customerInfo = new ArrayList<>(Arrays.asList
                        (customer.firstName, customer.lastName, customer.email,
                                customer.phone.replaceAll("-", ""), customer.address1,
                                customer.address2, customer.zip));
                submitButtonText = ConstantsDtc.CONTINUE_TO_SHIPPING;
            } else {
                addressFields = new ArrayList<>(
                        Arrays.asList(address1, address2, zip));
                customerInfo = new ArrayList<>(Arrays.asList
                        (customer.address1, customer.address2, customer.zip));
                submitButtonText = ConstantsDtc.PLACE_ORDER;
            }

            // TODO:  sendKeys does not work for these fields with IE
            // 1) Talk to developers about what is different about these fields?  Can they be changed?
            // 2) Find a solution other than sendKeys.  Robot and JavascriptExecutor have been tried.
            for (int i = 0; i < addressFields.size(); i++) {
                if (!customerInfo.get(i).equalsIgnoreCase("")) {
                    if (Config.isAndroidTablet() || Config.isAndroidPhone())
                        driver.waitForMilliseconds();

                    if (Config.isSafari()) {
                        driver.jsScrollToElement(addressFields.get(i));
                        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
                    }

                    addressFields.get(i).click();
                    if (Config.isFirefox()) {
                        addressFields.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a"));
                        addressFields.get(i).sendKeys(Keys.DELETE);
                    } else {
                        addressFields.get(i).clear();
                    }
                    addressFields.get(i).sendKeys(customerInfo.get(i));
                }
            }

            if (Config.isMobile()) {
                //TODO: Here be a dragon... O_O!! On Mobile, the APO & FPO customers state is not accepted
                //(con't) the dropdown option appears and is selectable, but always throws an address issue when
                //(con't) attempting to go to delivery options. Workaround forces DT site to make a suggestion for
                //(con't) the APO / FPO customer's address, which is then accepted. CANNOT reproduce manually on
                //(con't) chrome emulator or android emulator. A Dragon.
                if (customer.lastName.equalsIgnoreCase("APO-TEST")
                        || customer.lastName.equalsIgnoreCase("FPO-TEST")) {
                    customer.state = "Georgia";
                }
            }

            countryDropDown = driver.getElementWithText(formGroupBy, ConstantsDtc.COUNTRY);

            if (shippingAddress) {
                commonActions.selectPhoneTypeFromDropdown(ConstantsDtc.CHECKOUT_WITH_SHIPPING, customer.phoneType);
            }

            driver.jsScrollToElement(countryDropDown);
            countryDropDown.click();
            driver.clickElementWithText(CommonActions.spanTagNameBy, customer.country);

            submitButton = driver.getElementWithText(continueToButtonBy, submitButtonText);
            driver.jsScrollToElement(submitButton);
            submitButton.click();
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            driver.waitForPageToLoad();

            if (driver.isElementDisplayed(dtModal, Constants.THREE_SEC_WAIT)) {
                if (driver.isElementDisplayed(dtModal.findElement(dtModalTitleBy))
                        && dtModal.findElement(dtModalTitleBy).getText().equalsIgnoreCase(ADDRESS_VERIFICATION)) {
                    driver.getElementWithText(correctedAddressBtnBy, ConstantsDtc.USE_USPS_CORRECTED_ADDRESS).click();
                }
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Entering shipping address for user \"" +
                    customer.getCustomerDataString(customer) + "\"! FAILED with error: " + e);
        }
        LOGGER.info("enterAddressForCustomer completed");
    }

    /**
     * Edits specified address field
     *
     * @param field      The address field to edit
     * @param fieldValue The value to set the address field to
     */
    public void editShippingAddressField(String field, String fieldValue) {
        LOGGER.info("enterAddressForCustomer started");
        String countryString;

        driver.waitForElementClickable(firstName);

        switch (field) {
            case FIRST_NAME:
                firstName.clear();
                firstName.sendKeys(fieldValue);
                break;
            case LAST_NAME:
                lastName.clear();
                lastName.sendKeys(fieldValue);
                break;
            case ADDRESS_LINE_1:
                address1.clear();
                address1.sendKeys(fieldValue);
                break;
            case ADDRESS_LINE_2:
                address2.clear();
                address2.sendKeys(fieldValue);
                break;
            case ZIP:
                zip.clear();
                zip.sendKeys(fieldValue);
                break;
            case EMAIL:
                email.clear();
                email.sendKeys(fieldValue);
                break;
            case STATE:
                commonActions.selectDropDownValue(stateDropDownWebString, fieldValue);
                break;
            case COUNTRY:
                if (Config.isMobilePhone()) {
                    countryString = countryDropDownMobileString;
                } else {
                    countryString = countryDropDownWebString;
                }
                commonActions.selectDropDownValue(countryString, fieldValue);
                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
                break;
            case Constants.PHONE:
                commonActions.selectDropDownValue(phoneTypeDropDownWebString, fieldValue);
                break;
        }

        LOGGER.info("enterAddressForCustomer completed");
    }

    /**
     * Clicks the submit button on the edit shipping address page
     */
    public void submitUpdatedAddress() {
        LOGGER.info("submitUpdatedAddress started");
        driver.waitForElementClickable(addressSubmitButton);
        driver.jsScrollToElement(addressSubmitButton);
        addressSubmitButton.click();
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        checkForVerifyAddressPopup();
        LOGGER.info("submitUpdatedAddress completed");
    }

    /**
     * Clicks the edit link for shipping address page
     */
    public void clickEdit() {
        LOGGER.info("clickEdit started");
        driver.waitForElementClickable(edit);
        driver.jsScrollToElement(edit);
        try {
            edit.click();
        } catch (Exception e) {
            driver.jsClick(edit);
        }
        LOGGER.info("clickEdit completed");
    }

    /**
     * Selects the deliver method for shipping products
     *
     * @param deliveryMethod Type of delivery used in failure message
     * @param customer       Type of customer to pull dtc.data from and put into failure message
     */
    public void selectDeliveryMethod(String deliveryMethod, Customer customer) {
        LOGGER.info("selectDeliveryMethod started");
        try {
            driver.waitForElementClickable(editShippingDetailsLink);
            selectSeeMoreOptionsLink();
            driver.waitForPageToLoad();

            WebElement delMethodEle = driver.getElementWithText(CommonActions.radioLabelBy, deliveryMethod);
            driver.waitForElementVisible(delMethodEle);
            driver.jsScrollToElement(delMethodEle);
            delMethodEle.click();
            driver.waitForMilliseconds();

            WebElement continueToPaymentButton = driver.getElementWithText(continueToButtonBy,
                    ConstantsDtc.CONTINUE_TO_PAYMENT);
            driver.jsScrollToElement(continueToPaymentButton);
            continueToPaymentButton.click();
        } catch (Exception e) {
            Assert.fail("FAIL: Selecting delivery method \"" + deliveryMethod + "\" for user \"" +
                    customer.getCustomerDataString(customer) + "\"! FAILED with error: " + e);
        }
        LOGGER.info("selectDeliveryMethod completed");
    }

    /**
     * Selects the default delivery method for shipping products
     *
     * @param customer Type of customer to pull dtc.data from and put into failure message
     */
    public void selectDefaultDeliveryMethod(Customer customer) {
        LOGGER.info("selectDefaultDeliveryMethod started");
        selectDeliveryMethod(UPS_FREE, customer);
        LOGGER.info("selectDefaultDeliveryMethod completed");
    }

    /**
     * Enters all payment info based on customer type
     *
     * @param customer Type of customer to pull dtc.data from and pass to payment fields
     */
    public void enterPaymentInfo(Customer customer) {
        enterPaymentInfo(VISA, customer, true);
    }

    /***
     * Enters all payment info based on customer type
     *
     * @param creditCard            Name of the credit card to use for payment
     * @param customer              Type of customer to pull data from and pass to payment fields
     * @param useShippingAddress    Whether to use the current shipping address
     */
    public void enterPaymentInfo(String creditCard, Customer customer, boolean useShippingAddress) {
        LOGGER.info("enterPaymentInfo started");
        try {
            driver.waitForElementVisible(paymentTypesContainerBy);

            PaymentFields paymentFields = new PaymentFields().invokePaymentFields();
            WebElement ccName = paymentFields.getCcName();

            String expYear = setCustomerCreditCardExpirationYear(customer);
            String expMonth = getCustomerCreditCardExpirationMonth(customer);

            ccName.sendKeys(customer.firstName + " " + customer.lastName);
            if (Config.isAndroidPhone() || Config.isAndroidTablet()) {
                driver.waitForMilliseconds();
            }

            enterCreditCardNumberCvvCvnForCustomer(creditCard, customer);
            if (Config.isAndroidPhone() || Config.isAndroidTablet())
                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

            selectCreditCardExpirationMonthYear(expMonth, expYear);
            if (Config.isAndroidPhone() || Config.isAndroidTablet()) {
                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            }

            if(!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)){
                enterBillingAddressForCustomer(customer, useShippingAddress);
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Entering payment info for user \"" +
                    customer.getCustomerDataString(customer) + "\"! FAILED with error: " + e);
        }
        LOGGER.info("enterPaymentInfo completed");
    }

    /**
     * Gets the credit card expiration year for a specified customer
     *
     * @param customer Type of customer to pull dtc.data from and use as the credit card expiration year
     * @return String containing the customer's credit card expiration year
     */
    private String getCustomerCreditCardExpirationYear(Customer customer) {
        LOGGER.info("getCustomerCreditCardExpirationYear started");
        String expYear = customer.expDateYY;

        // Default if not defined for customer - Get year ahead of current year
        if (expYear == null) {
            expYear = Integer.toString(commonUtils.getExpYear(1));
            LOGGER.info("Customer was not found! Default value for expiration year = \"" + expYear + "\"");
        }

        LOGGER.info("getCustomerCreditCardExpirationYear completed");
        return expYear;
    }

    /**
     * Sets the credit card expiration year for a specified customer. If the customer is not found, returns a default
     * value of the current year + 1
     *
     * @param customer Type of customer from which to pull credit card data
     * @return Credit Card expiration year (2 digit format)
     */
    private String setCustomerCreditCardExpirationYear(Customer customer) {
        LOGGER.info("setCustomerCreditCardExpirationYear started");
        String expYear = getCustomerCreditCardExpirationYear(customer);

        if (expYear.length() == 4)
            expYear = expYear.substring(2);
        LOGGER.info("setCustomerCreditCardExpirationYear completed. Trimmed expiration year = \"" + expYear
                + "\"");
        return expYear;
    }

    /**
     * Gets the credit card expiration month for a specified customer
     *
     * @param customer Type of customer to pull dtc.data from and use as the credit card expiration month
     * @return String containing the customer's credit card expiration month
     */
    private String getCustomerCreditCardExpirationMonth(Customer customer) {
        String expMonth = customer.expDateMM;

        // Default if not defined for customer - Set month to 1
        if (expMonth == null) {
            expMonth = "1";
        }
        return expMonth;
    }

    /**
     * Sets the credit card info (# & CVN / CVV) for a specified customer
     *
     * @param creditCard Credit card with values used to update customer credit card fields
     * @param customer   Customer object with the credit card fields to be set
     */
    private void setCreditCardForCustomer(String creditCard, Customer customer) {
        LOGGER.info("setCreditCardForCustomer started");
        switch (creditCard) {
            case AMERICAN_EXPRESS:
                customer.ccNum = customer.ccNumAmEx;
                break;
            case DISCOVER:
                customer.ccNum = customer.ccNumDiscover;
                break;
            case MASTERCARD:
                customer.ccNum = customer.ccNumMaster;
                break;
            case VISA:
                customer.ccNum = customer.ccNumVisa;
                break;
            case VISA_BOPIS:
                customer.ccNum = customer.ccNumVisa2;
                customer.cvn = customer.cvn2;
                break;
            case CAR_CARE_ONE:
                customer.ccNum = customer.ccNumCCO;
                break;
            case AMERICAN_EXPRESS_BOPIS:
                customer.ccNum = customer.ccNumAmEx_bopis;
                break;
            case MASTERCARD_BOPIS:
                customer.ccNum = customer.ccNumMaster_bopis;
                customer.cvn = customer.cvn2;
                break;
            case DISCOVER_BOPIS:
                customer.ccNum = customer.ccNumDiscover_bopis;
                customer.cvn = customer.cvn3;
                break;
            case CAR_CARE_ONE_BOPIS:
                customer.ccNum = customer.ccNumCCO_bopis;
                customer.cvv = customer.cvv2;
                break;
            default:
                customer.ccNum = customer.ccNumVisa;
                break;
        }
        LOGGER.info("setCreditCardForCustomer completed");
    }

    /**
     * Enters the credit card number and CVV / CVN for a specified customer
     *
     * @param creditCard Credit card with the values to be used by the customer
     * @param customer   Customer using the values provided by the credit card
     */
    private void enterCreditCardNumberCvvCvnForCustomer(String creditCard, Customer customer) {
        LOGGER.info("enterCreditCardNumberCvvCvnForCustomer started");

        PaymentFields paymentFields = new PaymentFields().invokePaymentFields();
        WebElement ccNumber = paymentFields.getCcNumber();
        WebElement ccCvn = paymentFields.getCcCvn();

        setCreditCardForCustomer(creditCard, customer);

        ccNumber.sendKeys(customer.ccNum);
        if (Config.isFirefox()) {
            driver.waitForMilliseconds();
            ccNumber.sendKeys(Keys.ENTER);
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        }

        driver.jsScrollToElement(ccCvn);
        if (creditCard.equalsIgnoreCase(CAR_CARE_ONE) || creditCard.equalsIgnoreCase(CAR_CARE_ONE_BOPIS)) {
            ccCvn.sendKeys(customer.cvv);
        } else {
            ccCvn.sendKeys(customer.cvn);
        }
        LOGGER.info("enterCreditCardNumberCvvCvnForCustomer completed");
    }

    /**
     * Selects the expiration month and year from the corresponding 'Payment Details' controls
     *
     * @param expMonth Credit card expiration month
     * @param expYear  Credit card expiration year
     */
    private void selectCreditCardExpirationMonthYear(String expMonth, String expYear) {
        LOGGER.info("selectCreditCardExpirationMonthYear started");
        if (!Config.isMobilePhone()) {
            WebElement ccMonthExpireDropdown = driver.getElementWithText(formGroupBy, ConstantsDtc.CC_EXPIRE_MONTH);
            WebElement ccYearExpireDropdown = driver.getElementWithText(formGroupBy, ConstantsDtc.CC_EXPIRE_YEAR);

            driver.jsScrollToElement(ccMonthExpireDropdown);
            ccMonthExpireDropdown.click();
            driver.clickElementWithExactText(CommonActions.spanTagNameBy, expMonth);
            driver.jsScrollToElement(ccYearExpireDropdown);
            ccYearExpireDropdown.click();
            driver.clickElementWithExactText(CommonActions.spanTagNameBy, expYear);
        } else {
            driver.selectFromDropdownByVisibleText(ccMonthExpiryMobile, expMonth);
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            driver.selectFromDropdownByVisibleText(ccYearExpiryMobile, expYear);
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        }
        LOGGER.info("selectCreditCardExpirationMonthYear completed");
    }

    /**
     * Enters the billing address for a specified customer. Optionally, can opt to use the shipping address as the
     * billing address
     *
     * @param customer           Type of customer to pull data from and pass to address fields
     * @param useShippingAddress True if using the shipping address as the billing address, else false to enter a
     *                           separate address for billing
     */
    private void enterBillingAddressForCustomer(Customer customer, boolean useShippingAddress) {
        LOGGER.info("enterBillingAddressForCustomer started");
        PaymentFields paymentFields = new PaymentFields().invokePaymentFields();
        WebElement addressLine1 = paymentFields.getAddressLine1();
        WebElement billingZipCode = paymentFields.getBillingZipCode();

        if (!useShippingAddress) {
            driver.jsScrollToElement(billingSameAsShippingOption);
            billingSameAsShippingOption.click();
            enterAddressForCustomer(customer, false);
        } else {
            if (driver.isElementDisplayed(addressLine1, Constants.ONE_SEC_WAIT)) {
                addressLine1.sendKeys(customer.address1);
                WebElement countryDropdown = driver.getElementWithText(formGroupBy, Constants.UNITED_STATES);
                driver.jsScrollToElement(countryDropdown);
                countryDropdown.click();
                driver.clickElementWithText(CommonActions.spanTagNameBy, customer.country);

                // It is necessary to enter the zip code characters one by one with a wait between each entry
                // for the zip to register properly in the form. Otherwise the payment will not be processed and
                // there will be an error even when it works manually.
                for (int i = 0; i < customer.zip.length(); i++) {
                    billingZipCode.sendKeys(String.valueOf(customer.zip.charAt(i)));
                    driver.waitForMilliseconds(Constants.FIVE_HUNDRED_MILLISEC_WAIT);
                }
                billingZipCode.sendKeys(Keys.TAB);
            }
        }
        LOGGER.info("enterBillingAddressForCustomer completed");
    }

    /**
     * Handles making a split payment when there is more than one payment amount field. Assumes that the cost will
     * be split evenly
     */
    private void handleSplitPayments() {
        LOGGER.info("handleSplitPayments started");
        List<WebElement> amountFields = webDriver.findElements(amountBy);

        // Handle the second payment in a split payment
        if (amountFields.size() > 1) {
            driver.waitForMilliseconds();
            String amountValue = amountFields.get(1).getAttribute(Constants.VALUE);

            // It is necessary to backspace over the amount rather than clear the field. Otherwise the old value is
            // retained (although briefly hidden), and the new value is appended to it rather than overwriting it.
            for (int i = 0; i < amountValue.length(); i++) {
                amountFields.get(1).sendKeys(Keys.BACK_SPACE);
            }

            amountFields.get(1).sendKeys(splitPayRemainingAmount);
            amountFields.get(1).sendKeys(Keys.TAB);
        }
        LOGGER.info("handleSplitPayments completed");
    }

    /**
     * Clicks the 'Place Order' button
     *
     * @param customer Type of customer to pull dtc.data from and use in failure message
     */
    public void placeOrder(Customer customer) {
        LOGGER.info("placeOrder started");
        try {
            WebElement placeOrderButton = driver.getElementWithText(continueToButtonBy, ConstantsDtc.PLACE_ORDER);
            driver.waitForElementClickable(placeOrderButton);
            driver.jsScrollToElement(placeOrderButton);
            placeOrderButton.click();
        } catch (Exception e) {
            Assert.fail("FAIL: Placing order as user \"" + customer.getCustomerDataString(customer) +
                    "\"! FAILED with error: " + e);
        }
        LOGGER.info("placeOrder completed");
    }

    /***
     * Clicks the 'See more options' link in the Delivery Methods section if available
     */
    public void selectSeeMoreOptionsLink() {
        LOGGER.info("selectSeeMoreOptionsLink started");
        if (driver.isElementDisplayed(seeMoreOptionsLink, Constants.TWO_SEC_WAIT)) {
            driver.jsScrollToElement(seeMoreOptionsLink);
            seeMoreOptionsLink.click();
            driver.waitForPageToLoad();
        }
        LOGGER.info("selectSeeMoreOptionsLink completed");
    }

    /***
     * Confirms the available shipping options in the Delivery Methods section match the expected values
     * @param expectedOptions String comprised of expected shipping options separated by a comma
     */
    public void confirmAvailableShippingOptions(String expectedOptions) {
        LOGGER.info("confirmAvailableShippingOptions started with options: \"" + expectedOptions + "\"");
        List<String> optionsToConfirm = Lists.newArrayList(Splitter.on(",").trimResults().split(expectedOptions));
        for (String option : optionsToConfirm) {
            Assert.assertTrue("FAIL: Could not find Delivery Method: \"" + option + "\"!",
                    driver.isElementDisplayed(driver.getElementWithText(CommonActions.radioLabelBy, option)));
        }
        LOGGER.info("confirmAvailableShippingOptions completed with options: \"" + expectedOptions + "\"");
    }

    /***
     * Verifies a specified delivery option is not present for selection
     *
     * @param deliveryOption String - delivery option that should not be present
     */
    public void assertOptionNotInDeliveryMethod(String deliveryOption) {
        LOGGER.info("assertOptionNotInDeliveryMethod started");
        selectSeeMoreOptionsLink();
        Assert.assertFalse("FAIL: Delivery Method section DID contain option: " + deliveryOption,
                deliverySection.getText().contains(deliveryOption));
        LOGGER.info("assertOptionNotInDeliveryMethod completed");
    }

    /***
     * Verifies the "Shipping Address" and "Payment &#38; Billing Address" header sections are unable to be edited when
     * their dtc.data comes from Paypal. Verification is based on a check for the presence of an "Edit" link in the headers
     * specified.
     */
    public void verifySectionsWithPaypalInfoNotEditable() {
        LOGGER.info("verifySectionsWithPaypalInfoNotEditable started");
        driver.waitForElementClickable(placeOrderButton);
        webDriver.manage().timeouts().implicitlyWait(Constants.FIVE_SEC_WAIT, TimeUnit.SECONDS);
        List<WebElement> checkoutSectionsHeaderList = webDriver.findElements(sectionHeadersBy);
        if (checkoutSectionsHeaderList.size() != 0) {
            for (WebElement checkoutSection : checkoutSectionsHeaderList) {
                if (checkoutSection.getText().toLowerCase().contains(SHIPPING_ADDRESS.toLowerCase()) ||
                        checkoutSection.getText().toLowerCase().contains(DELIVERY_METHOD.toLowerCase()) ||
                        checkoutSection.getText().toLowerCase().contains(PAYMENT_BILLING_ADDRESS.toLowerCase())) {
                    Assert.assertTrue("FAIL: One of the Checkout headers for a section containing Paypal " +
                                    "info also contained an \"Edit\" link!",
                            checkoutSection.findElements(CommonActions.anchorTagBy).size() == 0);
                }
            }
        } else {
            Assert.fail("FAIL: No Checkout section headers were found!");
        }
        driver.resetImplicitWaitToDefault();
        LOGGER.info("verifySectionsWithPaypalInfoNotEditable completed");
    }

    /***
     * Verifies the specified Checkout section can be edited. Verification based on the presence of an "Edit" link in
     * the section header
     * @param editableSection Section header that should contain a link
     */
    public void verifySectionIsEditable(String editableSection) {
        LOGGER.info("verifySectionIsEditable started");
        driver.waitForElementClickable(placeOrderButton);
        List<WebElement> checkoutSectionsHeaderList = webDriver.findElements(sectionHeadersBy);
        if (checkoutSectionsHeaderList.size() != 0) {
            for (WebElement checkoutSection : checkoutSectionsHeaderList) {
                if (checkoutSection.getText().toLowerCase().contains(editableSection.toLowerCase())) {
                    Assert.assertTrue("FAIL: Checkout section \"" + editableSection + "\" did not contain an " +
                            "\"Edit\" link", checkoutSection.findElement(CommonActions.anchorTagBy).isDisplayed());
                }
            }
        } else {
            Assert.fail("FAIL: Section headers were NOT found. Unable to verify \"" + editableSection
                    + "\" section is editable!");
        }
        LOGGER.info("verifySectionIsEditable completed");
    }

    /***
     * Clicks the "Edit" link for a specified Checkout section
     * @param sectionToEdit Section of the Checkout page to be edited/modified
     */
    public void clickEditLinkForSection(String sectionToEdit) {
        LOGGER.info("clickEditLinkForSection started");
        driver.waitForElementClickable(placeOrderButton);
        List<WebElement> checkoutSectionsHeaderList = webDriver.findElements(sectionHeadersBy);
        if (checkoutSectionsHeaderList.size() != 0) {
            for (WebElement checkoutSection : checkoutSectionsHeaderList) {
                if (checkoutSection.getText().toLowerCase().contains(sectionToEdit.toLowerCase())) {
                    WebElement sectionEditLinkEle = checkoutSection.findElement(CommonActions.anchorTagBy);
                    //TODO: Clicking "Edit" link doesn't register on mobile; Troubleshoot when possible
                    driver.jsScrollToElement(sectionEditLinkEle);
                    sectionEditLinkEle.click();
                    break;
                }
            }
        } else {
            Assert.fail("FAIL: Section headers were NOT found. Unable to click \"Edit\" link for \""
                    + sectionToEdit + "\"!");
        }
        LOGGER.info("clickEditLinkForSection completed");
    }

    /***
     * Checks for and handles the "Verify your Address" pop-up when attempting to move from the shipping
     * address to the delivery method section OR when entering a billing address different from the shipping address.
     */
    private void checkForVerifyAddressPopup() {
        LOGGER.info("checkForVerifyAddressPopup started");
        driver.waitForPageToLoad();
        if (driver.isElementDisplayed(verifyAddressContinuebtnBy, Constants.FIVE_SEC_WAIT)) {
            WebElement continueBtnEle = webDriver.findElement(verifyAddressContinuebtnBy);
            continueBtnEle.click();
            driver.pollUntil(verifyAddressPopupClosedJs, Constants.FIVE_SEC_WAIT);
        } else {
            LOGGER.info("\"Verify your address\" popup was NOT displayed");
        }
        LOGGER.info("checkForVerifyAddressPopup completed");
    }

    /***
     * Performs the action to either accept or decline the credit card disclosure consent   
     * @param action String Action to be taken (Accept/Decline)
     */
    public void creditCardDisclosureModalAction(String action) {
        LOGGER.info("creditCardDisclosureModalAction started ");
        driver.waitForElementVisible(creditCardConsentModal);
        if (action.contains(ACCEPT)) {
            acceptConsentButton.click();
        } else if (action.contains(DECLINE)) {
            declineConsentButton.click();
        }
        LOGGER.info("Credit Card Disclosure Consent " + action + " successfully ");
        LOGGER.info("creditCardDisclosureModalAction completed ");
    }

    /***
     * Performs the action to either accept or decline the credit card terms of agreement   
     * @param action String Action to be taken (Accept/Decline)
     */
    public void ccTermsOfAgreementAction(String action) {
        LOGGER.info("ccTermsOfAgreementAction started ");
        driver.waitForElementVisible(creditCardToaModal);
        if (action.contains(ACCEPT)) {
            toaAcceptAndSubmitButton.click();
        } else if (action.contains(DECLINE)) {
            toaDeclineButton.click();
        }
        LOGGER.info("Credit Card Terms of Agreement " + action + " successfully ");
        LOGGER.info("ccTermsOfAgreementAction completed ");
    }

    /**
     * Verifies the Checkout and Cart subtotal amounts match
     */
    public void assertCheckoutAndCartSubtotalMatch() {
        LOGGER.info("assertCheckoutAndCartSubtotalMatch started");
        driver.waitForElementVisible(checkoutSubtotal);
        Assert.assertTrue("FAIL: Expected checkout subtotal of:\""
                        + CommonActionsSteps.checkoutSubtotal + "\" to match the cart subtotal:\""
                        + CommonActionsSteps.cartSubtotal + "\"!",
                CommonActionsSteps.checkoutSubtotal == CommonActionsSteps.cartSubtotal);
        LOGGER.info("assertCheckoutAndCartSubtotalMatch completed");
    }

    /**
     * Verifies the specified fee or service appears as a line item in the cart summary of the Checkout page
     *
     * @param itemType String representing the itemType of either "fee" or "service"
     * @param itemName String representing text value expected to be present in the cart summary line item
     */
    public void assertItemPresentInCartSummary(String itemType, String itemName) {
        LOGGER.info("assertItemPresentInCartSummary started");
        WebElement itemEle = null;

        if (itemType.equalsIgnoreCase(Constants.FEE)) {
            itemEle = feeCartSummaryItem;
        } else if (itemType.equalsIgnoreCase(Constants.SERVICE)) {
            itemEle = serviceCartSummaryItem;
        } else {
            Assert.fail("FAIL: itemType of:\"" + itemType + "\" was NOT recognized!");
        }

        Assert.assertTrue("FAIL: Did NOT find a \"" + itemType
                        + "\" line item containing the expected text:\"" + itemName + "\"!",
                driver.checkIfElementContainsText(itemEle, itemName));
        LOGGER.info("assertItemPresentInCartSummary completed");
    }

    /**
     * Select the install without appointment option based on the type of reason passed in
     *
     * @param reasonText type of installment reason according to scenario
     */
    public void selectCheckoutWithoutInstallReason(String reasonText) {
        LOGGER.info("selectCheckoutWithoutInstallReason started");
        driver.waitForElementClickable(installWithoutApptBtn);
        driver.waitForMilliseconds();
        installWithoutApptBtn.click();

        driver.waitForElementClickable(withoutApptReasonToggleBtn);
        driver.jsScrollToElement(withoutApptReasonToggleBtn);
        withoutApptReasonToggleBtn.click();

        // TODO: Get this item selection working in Firefox
        driver.waitForMilliseconds();
        driver.clickElementWithText(CommonActions.spanTagNameBy, reasonText);

        driver.waitForPageToLoad();
        LOGGER.info("selectCheckoutWithoutInstallReason ended");
    }

    /**
     * Select the install with appointment option
     */
    private void selectInstallWithAppointment() {
        LOGGER.info("selectInstallWithAppointment started");
        driver.waitForElementClickable(installWithApptBtn);
        installWithApptBtn.click();
        driver.waitForPageToLoad();
        LOGGER.info("selectInstallWithAppointment completed");
    }

    /**
     * Selects the Checkout install type (Appointment Details section) either with or without an appointment
     * NOTE: Default assumes checkout w/o appointment and passes in the default option to be selected
     *
     * @param installType type of checkout install i.e. with appointment OR without appointment
     */
    public void selectCheckoutInstallType(String installType) {
        if (installType.equalsIgnoreCase(ConstantsDtc.DEFAULT)) {
            selectCheckoutWithoutInstallReason(ConstantsDtc.APPT_NOT_SURE_OF_AVAILABILITY);
        } else if ((installType.equalsIgnoreCase(ConstantsDtc.WITH_APPOINTMENT))) {
            selectInstallWithAppointment();
        }
        LOGGER.info("selectCheckoutInstallType ended");
    }

    /**
     * Clicks the payment radio button specified (credit or paypal)
     *
     * @param paymentType Type of payment (credit or paypal)
     */
    public void clickPaymentType(String paymentType) {
        LOGGER.info("clickPaymentType started");
        WebElement paymentTypeRadioBtn;

        if (!Config.isSafari()) {
            driver.waitForPageToLoad();
        } else {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        }
        driver.waitForElementVisible(paymentTypesContainerBy);

        paymentTypeRadioBtn = driver.getElementWithText(CommonActions.radioLabelBy, paymentType);
        driver.jsScrollToElement(paymentTypeRadioBtn);
        paymentTypeRadioBtn.click();
        LOGGER.info("clickPaymentType ended");
    }

    /**
     * Waits for and selects the "Continue to Paypal" button
     */
    public void continueToPayPalCheckout() {
        LOGGER.info("continueToPayPalCheckout started");
        driver.waitForElementVisible(CommonActions.radioLabelBy);
        WebElement continueToPayPalButton =
                driver.getElementWithText(continueToButtonBy, ConstantsDtc.CONTINUE_TO_PAYPAL);
        driver.jsScrollToElement(continueToPayPalButton);
        continueToPayPalButton.click();
        LOGGER.info("continueToPayPalCheckout ended");
    }

    /**
     * Selects the first available date and then time in order to create an appointment from the Checkout page
     */
    public void selectFirstAvailableApptDateTime() {
        LOGGER.info("selectFirstAvailableApptDateTime started");
        driver.waitForElementVisible(apptDateContainer);
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();
        if (availableApptDateList.size() > 0) {
            for (WebElement apptDate : availableApptDateList) {
                apptDate.click();
                driver.waitForPageToLoad();

                if (selectFirstAvailableApptTime())
                    break;
            }
        } else {
            Assert.fail("FAIL: None of the listed dates were active / had available appointment time slots!");
        }
        LOGGER.info("selectFirstAvailableApptDateTime completed");
    }

    /**
     * Clicks the default submit button for the checkout page
     * Has an implicit wait as well as a try/catch
     */
    public void clickDefaultSubmitButton() {
        LOGGER.info("clickDefaultSubmitButton started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(defaultButton);
        driver.jsScrollToElement(defaultButton);
        defaultButton.click();
        LOGGER.info("clickDefaultSubmitButton completed");
    }

    /**
     * Enters (non-DTD) customer information on the Checkout page
     *
     * @param customer Customer type to grab dtc.data from
     */
    public void enterCustomerInformationForCheckout(Customer customer) {
        LOGGER.info("enterCustomerInformationForCheckout started");
        By phoneDropdownFieldBy = CommonActions.dropdownBy;
        WebElement phoneDropdown;

        driver.waitForElementClickable(firstName, Constants.FIVE_SEC_WAIT);
        try {
            firstName.sendKeys(customer.firstName);
            lastName.sendKeys(customer.lastName);
            email.sendKeys(customer.email);

            phoneDropdown = driver.getElementWithText(formGroupBy, ConstantsDtc.PHONE_TYPE);

            if (Config.isMobile()) {
                phoneDropdown = driver.getParentElement(phoneDropdown).findElement(phoneDropdownFieldBy);
            }

            driver.jsScrollToElement(phoneDropdown);
            phoneDropdown.click();
            driver.clickElementWithText(CommonActions.spanTagNameBy, customer.phoneType);
            driver.jsScrollToElement(phone);
            phone.sendKeys(customer.phone.replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            Assert.fail("FAIL: Entering customer info for user " +
                    customer.getCustomerDataString(customer) + "! FAILED with error: " + e);
        }
        LOGGER.info("enterCustomerInformationForCheckout completed");
    }

    /**
     * Selects / expands the Cart items section in the Order Summary on the Checkout page
     */
    public void expandCartSummaryCartItems() {
        LOGGER.info("expandCartSummaryCartItems started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(expandCartItemsLabel);
        driver.jsScrollToElement(expandCartItemsLabel);
        expandCartItemsLabel.click();
        driver.waitForElementVisible(checkoutOrderSummaryBy);
        LOGGER.info("expandCartSummaryCartItems completed");
    }

    /**
     * Selects / expands the "show fee details" section for an item in the Order Summary on the Checkout page
     * Could be improved to select the section based on a specified item name, id, position, etc.
     */
    public void expandFeeDetailsForCartItem() {
        LOGGER.info("expandFeeDetailsForCartItem started");

        if(driver.isElementDisplayed(expandFeeDetailsLabel)){
            driver.waitForElementClickable(expandFeeDetailsLabel);
            expandFeeDetailsLabel.click();
            driver.waitForElementVisible(expandFeeDetailsLabel);
        }
        LOGGER.info("expandFeeDetailsForCartItem completed");
    }

    /***
     * Sets the Regional based different sales tax factors
     *
     * @param customer     Type of customer to pull data from and pass to payment fields
     *
     */
    public static void setRegionalTaxesFactor(Customer customer) {
        LOGGER.info("setRegionalTaxesFactor started");
        if (Config.getDataSet().equalsIgnoreCase(Constants.QA)) {
            stateTaxString = customer.stateTaxQA;
            cityTaxString = customer.cityTaxQA;
            otherTaxString = customer.otherTaxQA;
        } else {
            stateTaxString = customer.stateTaxSTG;
            cityTaxString = customer.cityTaxSTG;
            otherTaxString = customer.otherTaxSTG;
        }
        LOGGER.info("setRegionalTaxesFactor completed");
    }

    /**
     * Selects the first or last available date and then the first available time on those dates in order to create an
     * appointment from the Checkout page. Will fail if there are no time slots available on the first or last available
     * date
     *
     * @param selectFirstDate True for the first available day, False for the last available day
     */
    public void selectFirstOrLastAvailableApptDateTime(boolean selectFirstDate) {
        LOGGER.info("selectFirstOrLastAvailableApptDateTime started");
        driver.waitForElementVisible(apptDateContainer);
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();

        if (availableApptDateList.size() > 0) {
            if (!selectFirstDate) {
                WebElement apptDateEle = availableApptDateList.get(availableApptDateList.size() - 1);
                driver.jsScrollToElement(apptDateEle);
                apptDateEle.click();
                driver.waitForPageToLoad();
                Assert.assertTrue("FAIL: Unable to select a time slot for the first or last appointment day!",
                        selectFirstAvailableApptTime());
            } else {
                selectFirstAvailableApptDateTime();
            }
        } else {
            Assert.fail("FAIL: There were no appt. days listed as active / available for selection!");
        }
        // give time for screen to update so data can be pulled
        driver.waitForMilliseconds();
        LOGGER.info("selectFirstOrLastAvailableApptDateTime completed");
    }

    /**
     * Selects the first available appointment time slot
     *
     * @return True if a time was selected, False if no time was selected
     */
    private boolean selectFirstAvailableApptTime() {
        LOGGER.info("selectFirstAvailableApptTime started");
        List<WebElement> availableApptTimeList = getAvailableApptTimesForDateCheckout();
        if (availableApptTimeList.size() > 0) {
            WebElement firstAvailableTime = availableApptTimeList.get(0);
            driver.jsScrollToElement(firstAvailableTime);
            firstAvailableTime.click();
            LOGGER.info("selectFirstAvailableApptTime completed w/ true");
            return true;
        }
        LOGGER.info("selectFirstAvailableApptTime completed w/ false");
        return false;
    }

    /**
     * Retrieves all the available appointment times for a given date on the Checkout page
     *
     * @return Group of elements representing the available appointment times
     */
    private List<WebElement> getAvailableApptTimesForDateCheckout() {
        return webDriver.findElements(availableApptTimesBy);
    }

    /**
     * Retrieves all appointment times for a given date on the Checkout page
     *
     * @return Group of elements representing the appointment times
     */
    private List<WebElement> getAllApptTimesForDateCheckout() {
        return webDriver.findElements(allApptTimesBy);
    }

    /**
     * Retrieves all the available appointment dates
     *
     * @return Group of elements representing the available appointment dates
     */
    private List<WebElement> getAvailableApptDatesForCheckout() {
        return webDriver.findElements(availableApptDatesBy);
    }

    /**
     * Retrieves all the unAvailable appointment dates
     *
     * @return Group of elements representing the unAvailable appointment dates
     */
    private List<WebElement> getUnavailableApptDatesForCheckout() {
        LOGGER.info("getUnavailableApptDatesForCheckout started");
        List<WebElement> allApptDatesList = webDriver.findElements(allAvailableApptDatesBy);
        List<WebElement> unavailableApptDatesList = new ArrayList<>();

        for (WebElement apptDate : allApptDatesList) {
            if (!apptDate.getAttribute(Constants.CLASS).contains("appointment-details__select-date--active"))
                unavailableApptDatesList.add(apptDate);
        }
        LOGGER.info("getUnavailableApptDatesForCheckout completed");
        return unavailableApptDatesList;
    }

    /**
     * Verifies that the total number of appointment days displayed to the user for a checkout with an item in the cart
     * equals 10 business days
     */
    public void verifyTotalNumberOfApptDaysForCheckoutWithItem() {
        LOGGER.info("verifyTotalNumberOfApptDaysForCheckoutWithItem started");
        driver.waitForElementVisible(apptDateContainer);
        List<WebElement> apptDateDayList = webDriver.findElements(apptDatesDayBy);
        Assert.assertTrue("FAIL: The total number of business days displayed was '"
                + apptDateDayList.size() + "' but the expectation was '10'!", apptDateDayList.size() == 10);
        LOGGER.info("verifyTotalNumberOfApptDaysForCheckoutWithItem completed");
    }

    /**
     * Waits for 'Customer Information' section to load on page
     */
    public void assertCustomerInfoPageLoaded() {
        LOGGER.info("assertCustomerInfoPageLoaded started");
        driver.waitForElementVisible(customerInfoField);
        LOGGER.info("assertCustomerInfoPageLoaded completed - Customer Information Form Displayed!");
    }

    /**
     * Verifies the tooltip is displayed for Install without appointment
     */
    public void assertInstallWithoutApptToolTip() {
        LOGGER.info("assertInstallWithoutApptToolTip started");
        driver.waitForPageToLoad();
        Assert.assertTrue("The Install without appointment tooltip was not displayed",
                driver.isElementDisplayed(tooltipBtnBy));
        LOGGER.info("assertInstallWithoutApptToolTip completed");
    }

    /**
     * Verifies valid tooltip message displays when clicked on
     */
    public void assertInstallWithoutApptToolTipMessage() {
        LOGGER.info("assertInstallWithoutApptToolTipMessage started");
        driver.waitForPageToLoad();
        driver.jsScrollToElement(webDriver.findElement(tooltipBtnBy));
        driver.jsClick(webDriver.findElement(tooltipBtnBy));
        driver.waitForMilliseconds();
        String contentText = tooltipContent.getText();
        Assert.assertTrue("The tooltip message did not display with the text:"
                        + "  'If you don't want these items installed, contact your selected store.'",
                contentText.matches("If you don.*t want these items installed, contact your selected store."));
        LOGGER.info("assertInstallWithoutApptToolTipMessage completed");
    }

    /**
     * Retrieves all the unavailable appointment times for a given date on the Checkout page
     *
     * @return Group of elements representing the unavailable appointment times
     */
    private List<WebElement> getUnavailableApptTimesForDateCheckout() {
        LOGGER.info("getUnavailableApptTimesForDateCheckout started");
        List<WebElement> apptTimeList = getAllApptTimesForDateCheckout();
        List<WebElement> unAvailTimeList = new ArrayList<>();

        for (WebElement apptTime : apptTimeList) {
            if (!apptTime.getAttribute(Constants.CLASS).contains(availTimeClassString))
                unAvailTimeList.add(apptTime);
        }
        LOGGER.info("getUnavailableApptTimesForDateCheckout completed");
        return unAvailTimeList;
    }

    /**
     * Verifies that unavailable time slots are disabled
     */
    public void assertUnavailableTimeSlotsAreDisabled() {
        LOGGER.info("assertUnavailableTimeSlotsAreDisabled started");
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();
        driver.waitForElementVisible(apptDateContainer);
        boolean found = false;

        if (availableApptDateList.size() > 0) {
            for (WebElement apptDate : availableApptDateList) {
                apptDate.click();
                String date = apptDate.getText().replace("\n", " ").replace(VIEW_TIMES, "").trim();
                LOGGER.info("Selected Appointment date:  " + date);

                List<WebElement> unAvailApptTimeList = getUnavailableApptTimesForDateCheckout();
                if (unAvailApptTimeList.size() > 0) {
                    for (WebElement apptTime : unAvailApptTimeList) {
                        if ((apptTime.getText().equals("8:00") || apptTime.getText().equals("8:30")) &&
                                (driver.isElementDisplayed(AppointmentPage.apptSelectedMsg))) {
                            appointmentPage.closeAppointmentSelectedMessageBar();
                        }
                        apptTime.click();
                        // It takes some time for button to become enabled when available time slots are clicked.
                        // For test validity, include wait time even when clicking on unavailable time slots.
                        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
                        WebElement continueToCustDetailsButton = driver.getElementWithText(continueToButtonBy,
                                ConstantsDtc.CONTINUE_TO_CUSTOMER_DETAILS);

                        String time = apptTime.getText();
                        boolean enabled = continueToCustDetailsButton.isEnabled();

                        Assert.assertTrue("The 'CONTINUE TO CUSTOMER DETAILS' button was not enabled when "
                                + "clicking on unavailable time slot:  " + time, !enabled);
                        LOGGER.info("Appt time:  " + apptTime.getText() + ".  Button enabled = " + enabled);
                    }

                    found = true;
                    break;
                }
            }

            if (!found) {
                LOGGER.info("No appointment dates found with unavailable time slots");
            }
        } else {
            Assert.fail("FAIL: None of the listed dates were active");
        }
        LOGGER.info("assertUnavailableTimeSlotsAreDisabled completed");
    }

    /**
     * Verifies that available time slots are enabled
     */
    public void assertAvailableTimeSlotsAreEnabled() {
        LOGGER.info("assertAvailableTimeSlotsAreEnabled started");
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();
        driver.waitForElementVisible(apptDateContainer);
        boolean found = false;

        if (availableApptDateList.size() > 0) {
            for (WebElement apptDate : availableApptDateList) {
                apptDate.click();
                String date = apptDate.getText().replace("\n", " ").replace(VIEW_TIMES, "").trim();
                LOGGER.info("Selected Appointment date:  " + date);

                List<WebElement> availApptTimeList = getAvailableApptTimesForDateCheckout();
                if (availApptTimeList.size() > 0) {
                    for (WebElement apptTime : availApptTimeList) {
                        if ((apptTime.getText().equals("8:00") || apptTime.getText().equals("8:30")) &&
                                (driver.isElementDisplayed(AppointmentPage.apptSelectedMsg))) {
                            appointmentPage.closeAppointmentSelectedMessageBar();
                        }
                        apptTime.click();
                        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

                        WebElement continueToCustDetailsButton = driver.getElementWithText(continueToButtonBy,
                                ConstantsDtc.CONTINUE_TO_CUSTOMER_DETAILS);

                        String time = apptTime.getText();
                        boolean enabled = continueToCustDetailsButton.isEnabled();

                        Assert.assertTrue("The 'CONTINUE TO CUSTOMER DETAILS' button not enabled when "
                                + "clicking on available time slot:  " + time, enabled);

                        LOGGER.info("Appt time:  " + apptTime.getText() + ".  Button enabled = " + enabled);
                    }

                    found = true;
                    break;
                }
            }

            if (!found) {
                Assert.fail("FAIL: No appointment dates found with available time slots");
            }
        } else {
            Assert.fail("FAIL: None of the listed dates were active");
        }
        LOGGER.info("assertAvailableTimeSlotsAreEnabled completed");
    }

    /**
     * Verifies the time slots are ordered in ascending order from top to bottom
     */
    public void assertTimeSlotsAscendingOrderTopToBottom() {
        LOGGER.info("assertTimeSlotsAscendingOrderTopToBottom started");
        SimpleDateFormat stdTime = new SimpleDateFormat("hh:mm:ss aa");
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();
        driver.waitForElementVisible(apptDateContainer);

        if (availableApptDateList.size() == 0) {
            Assert.fail("FAIL: None of the listed dates were active");
        }

        WebElement apptDate = availableApptDateList.get(0);
        apptDate.click();

        List<WebElement> apptTimeList = getAllApptTimesForDateCheckout();
        String curTime = null;
        String prevTime = null;
        Date curDateTime = null;
        Date prevDateTime = null;

        for (WebElement apptTime : apptTimeList) {
            String AMPM = Constants.DAY_AM;

            curTime = apptTime.getText();
            String hh = curTime.split(":")[0];
            String mm = curTime.split(":")[1];

            if (Integer.parseInt(hh) <= 5 || Integer.parseInt(hh) == 12)
                AMPM = Constants.DAY_PM;

            curTime = hh + ":" + mm + ":00 " + AMPM;

            if (prevTime != null) {
                try {
                    curDateTime = stdTime.parse(curTime);
                    prevDateTime = stdTime.parse(prevTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Assert.assertTrue("The time " + curDateTime +
                                " is not greater than the previous time " + prevDateTime,
                        curDateTime.after(prevDateTime));
            }

            prevTime = curTime;
        }
        LOGGER.info("assertTimeSlotsAscendingOrderTopToBottom completed");
    }

    /**
     * Verifies user can scroll from top to bottom and from bottom to top of appointment list
     */
    public void assertScrollableTimeSlotList() {
        LOGGER.info("assertScrollableTimeSlotList started");
        LOGGER.info("assertAppointmentMessageBar started");
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();

        if (availableApptDateList.size() == 0) {
            Assert.fail("FAIL: None of the listed dates were active");
        }

        WebElement apptDate = availableApptDateList.get(0);
        apptDate.click();

        List<WebElement> apptTimeList = this.getAllApptTimesForDateCheckout();
        WebElement firstApptTime = apptTimeList.get(0);
        WebElement lastApptTime = apptTimeList.get(apptTimeList.size() - 1);
        String time1 = firstApptTime.getText();
        String time2 = lastApptTime.getText();
        try {
            driver.jsScrollToElement(driver.getElementWithText(allApptTimesBy, time2));
        } catch (Exception e) {
            Assert.fail("Could not scroll from top to bottom of appointment list");
        }
        try {
            driver.jsScrollToElement(driver.getElementWithText(allApptTimesBy, time1));
        } catch (Exception e) {
            Assert.fail("Could not scroll from bottom to top of appointment list");
        }
        LOGGER.info("assertScrollableTimeSlotList completed");
    }

    /**
     * Verifies the appointment message bar appears with the expected text when an
     * available appointment is clicked on
     */
    public void assertAppointmentMessageBar() {
        LOGGER.info("assertAppointmentMessageBar started");
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();
        String displayedMsg = null;
        String expectedMsg = null;
        boolean found = false;

        if (availableApptDateList.size() == 0) {
            Assert.fail("FAIL: None of the listed dates were active");
        }

        //TODO:  Need to fix in Safari
        for (WebElement apptDate : availableApptDateList) {
            apptDate.click();
            String date = apptDate.getText().replace("\n", " ").replace(VIEW_TIMES, "").trim();
            String day = date.split(" ")[0];
            String dayOfMonth = date.split(" ")[1];
            String month = apptDateContainer.getText().split(" ")[0].substring(0, 3);
            date = month + " " + dayOfMonth;

            switch (day) {
                case "Mon":
                    day = Constants.MONDAY;
                    break;
                case "Tue":
                    day = Constants.TUESDAY;
                    break;
                case "Wed":
                    day = Constants.WEDNESDAY;
                    break;
                case "Thu":
                    day = Constants.THURSDAY;
                    break;
                case "Fri":
                    day = Constants.FRIDAY;
                    break;
                case "Sat":
                    day = Constants.SATURDAY;
                    break;
            }

            List<WebElement> availableApptTimeList = getAvailableApptTimesForDateCheckout();
            if (availableApptTimeList.size() > 0) {
                List<WebElement> apptTimeList = this.getAllApptTimesForDateCheckout();

                for (WebElement apptTime : apptTimeList) {
                    String AMPM = Constants.DAY_AM;

                    if (apptTime.getAttribute(Constants.CLASS).contains(availTimeClassString)) {
                        apptTime.click();
                        // give time for message to appear
                        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
                        String time = apptTime.getText();
                        String hh = time.split(":")[0];
                        String mm = time.split(":")[1];

                        if (Integer.parseInt(hh) <= 5 || Integer.parseInt(hh) == 12)
                            AMPM = Constants.DAY_PM;

                        time = hh + ":" + mm + " " + AMPM;

                        Assert.assertTrue("The appointment message bar did not appear when selecting"
                                + " an available appointment", driver.isElementDisplayed(apptSelectedMsg));

                        expectedMsg = "Appointment Selected:\n"
                                + day + ", " + month + " " + dayOfMonth + " - " + time;
                        displayedMsg = apptSelectedMsg.getText();

                        Assert.assertTrue("The message bar text is incorrect.  Expected:  " + expectedMsg
                                + ".  Actual:  " + displayedMsg, expectedMsg.equals(displayedMsg));

                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        LOGGER.info("assertAppointmentMessageBar completed");
    }

    /**
     * Verifies the order total amounts on checkout page matches with Shopping cart order total
     */
    public void assertCheckoutAndCartOrderTotal() {
        LOGGER.info("assertCheckoutAndCartOrderTotal started");
        driver.waitForElementVisible(checkoutOrdTotal);
        double checkoutOrderTotal = commonActions.cleanMonetaryString(checkoutOrdTotal.getText());
        Assert.assertTrue("FAIL: Expected order total on cart page: ("
                + CommonActionsSteps.orderTotal + ") did not match to the actual order total on checkout: ("
                + checkoutOrderTotal + ")", CommonActionsSteps.orderTotal == checkoutOrderTotal);
        LOGGER.info("assertCheckoutAndCartOrderTotal completed");
    }

    /**
     * Verifies the sales tax amount on checkout page matches with sales tax on Shopping cart
     */
    public void assertCheckoutAndCartSalesTax() {
        LOGGER.info("assertCheckoutAndCartSalesTax started");
        WebElement salesTaxElement = checkoutAndOrderCartSummarySalesTax;

        driver.waitForElementVisible(salesTaxElement);
        double checkoutSalesTax = commonActions.cleanMonetaryString(salesTaxElement.getText());
        Assert.assertTrue("FAIL: Expected sales tax on checkout page: ("
                + CommonActionsSteps.salesTax + ") did not match to the actual sales tax: ("
                + checkoutSalesTax + ")", CommonActionsSteps.salesTax == checkoutSalesTax);
        LOGGER.info("assertCheckoutAndCartSalesTax completed");
    }

    /**
     * Verifies the store name on checkout page matches the store name on Shopping cart
     */
    public void assertCheckoutAndCartStoreName() {
        LOGGER.info("assertCheckoutAndCartStoreName started");
        driver.waitForElementVisible(checkoutCartSummaryStoreName);
        String checkoutStoreName = checkoutCartSummaryStoreName.getText().replaceAll("\n", " - ");
        Assert.assertTrue("FAIL: Expected store name on checkout page: (" + checkoutStoreName +
                        ") did not match to the actual store name: (" + CommonActionsSteps.storeName + ")",
                checkoutStoreName.contains(CommonActionsSteps.storeName));
        LOGGER.info("assertCheckoutAndCartStoreName completed");
    }

    /**
     * Verifies the reason displayed on the Appointment Details section of the Checkout page is correct
     *
     * @param reason - The expected reason to be validated on the checkout page
     */
    public void assertCheckoutApptDetailsReason(String reason) {
        LOGGER.info("assertCheckoutApptDetailsReason started");
        String displayReason = checkoutApptDetailsReason.getText().split("\n")[1].replace("Reason:", "").trim();
        Assert.assertTrue("The expected reason was not displayed on the Appointment Details section of the Checkout " +
                        "page! Expected: " + reason + ".  Actual: " + displayReason + ".",
                displayReason.equals(reason));
        LOGGER.info("assertCheckoutApptDetailsReason completed");
    }

    /**
     * Verify Walkins Welcome message is displayed
     */
    public void assertWalkInsWelcomeMessage() {
        LOGGER.info("assertWalkInsWelcomeMessage started");
        Assert.assertTrue("The '" + WALKINS_WELCOME + "' message did not appear!",
                noAppointmentTimesAvailable.getText().equals(WALKINS_WELCOME));
        LOGGER.info("assertWalkInsWelcomeMessage completed");
    }

    /**
     * Verify the appointment dates and appointment times sections displayed
     */
    public void assertAppointmentSelectionDisplayed() {
        LOGGER.info("assertAppointmentSelectionDisplayed started");
        Assert.assertTrue("The appointment dates and appointment times sections did not display",
                driver.isElementDisplayed(apptDateContainer) && driver.isElementDisplayed(apptTimeContainer));
        LOGGER.info("assertAppointmentSelectionDisplayed completed");
    }

    /**
     * Verify the first appointment date listed is correct
     *
     * @param stockStatus - 'In Stock' or 'On Order'.  If 'In Stock', the
     *                    expected date is current day.  If 'On Order',
     *                    expected date is a number of days in the
     *                    future determined by current day of week.
     */
    public void verifyFirstAppointmentDate(String stockStatus) {
        LOGGER.info("verifyFirstAppointmentDate started with stock status '" + stockStatus + "'");

        driver.waitForElementVisible(apptDateContainer);
        List<WebElement> apptDateDayList = webDriver.findElements(apptDatesDayBy);
        int dateListSize = apptDateDayList.size();

        if (dateListSize == 0) {
            Assert.fail("There were no appointment dates listed on the checkout page");
        }

        Date currentDay = new Date();
        Calendar calendar = Calendar.getInstance();

        // Add an extra day if it is 5:00 PM or later
        if (Integer.parseInt(currentDay.toString().split(" ")[3].substring(0, 2)) >= 17) {
            calendar.add(Calendar.DATE, 1);
            currentDay = calendar.getTime();
        }

        String dayOfWeek = currentDay.toString().substring(0, 3);
        String[] dateText = null;

        // If In Stock, then expected date is current day.
        // Otherwise it is a future date based on current day of week.
        if (stockStatus.equalsIgnoreCase(ConstantsDtc.IN_STOCK)) {
            dateText = currentDay.toString().split(" ");
        } else {
            switch (dayOfWeek) {
                case "Sun":
                case "Mon":
                case "Tue":
                    calendar.add(Calendar.DATE, 3);
                    break;

                case "Wed":
                case "Thu":
                case "Fri":
                    calendar.add(Calendar.DATE, 5);
                    break;
                case "Sat":
                    calendar.add(Calendar.DATE, 4);
                    break;
            }
            Date future = calendar.getTime();
            dateText = future.toString().split(" ");
        }

        if (Integer.parseInt(dateText[2]) < 10) {
            dateText[2] = dateText[2].substring(1, 2);
        }

        String expectedDate = dateText[0] + " " + dateText[2];
        String displayedDate = apptDateDayList.get(0).getText().replace("\n", " ");

        Assert.assertTrue("The first appointment date listed (" + displayedDate + ") did not match the expected date "
                + "(" + expectedDate + ")", expectedDate.equals(displayedDate));

        LOGGER.info("verifyFirstAppointmentDate completed");
    }

    /**
     * Verify the Appointment Date list header contains the current month and year
     */
    public void assertMonthYearAppointmentDateHeader() {
        LOGGER.info("assertMonthYearAppointmentDateHeader started");
        Date curDay = new Date();
        String curDate = CommonUtils.replaceShortMonthWithLongMonth(String.valueOf(curDay));
        String[] dateText = curDate.split(" ");
        String expectedMonth = dateText[1];
        String expectedYear = dateText[dateText.length - 1];
        String expectedMonthYear = expectedMonth + " " + expectedYear;

        String displayMonthYear = AppointmentPage.apptMonthAndYear.getText();

        Assert.assertTrue("The Appointment Date Header did not contain the correct Month and Year.  Expected: "
                + expectedMonthYear + ".  Displayed: " + displayMonthYear, displayMonthYear.equals(expectedMonthYear));
        LOGGER.info("assertMonthYearAppointmentDateHeader completed");
    }

    /**
     * Verify the Appointment Date list divider contains the next month and year
     */
    public void assertMonthYearAppointmentDateDivider() {
        LOGGER.info("assertMonthYearAppointmentDateDivider started");

        // Verify that there is a divider only when appointments fall across two months
        Date curDay = new Date();
        Date futureDay = new Date();
        int dayIncrement = 10;
        futureDay.setTime(curDay.getTime() + dayIncrement * 1000 * 60 * 60 * 24);

        List<WebElement> headers = webDriver.findElements(AppointmentPage.apptMonthAndYearBy);

        boolean sameMonth = String.valueOf(curDay).split(" ")[1].
                equals(String.valueOf(futureDay).split(" ")[1]);

        if (sameMonth && headers.size() > 1) {
            Assert.fail("A month and year divider for next month is displayed "
                    + "when 10 days in the future is in the current month");
        }

        if (!sameMonth && headers.size() == 1) {
            Assert.fail("10 days in the future is in the next month but "
                    + "no month divider is displayed.");
        }

        if (sameMonth && headers.size() == 1) {
            LOGGER.info("10 days in the future is in the current month.  "
                    + "Month and Year divider can only be tested when the "
                    + "appointment dates fall in different months.");
            return;
        }

        // Get the expected next month and year
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date futureDate = new Date();
        futureDate = calendar.getTime();
        String futureDateText = CommonUtils.replaceShortMonthWithLongMonth(futureDate.toString());
        String[] futureTextArray = futureDateText.split(" ");
        String expectedMonth = futureTextArray[1];
        String expectedYear = futureTextArray[futureTextArray.length - 1];
        String expectedMonthYear = expectedMonth + " " + expectedYear;

        // Get the displayed divider text
        String displayDividerMonthYear = headers.get(1).getText();

        // Verify the divider text shows the next month and year
        Assert.assertTrue("The Appointment Date divider did not contain the correct Month"
                + " and Year.  Expected:  " + expectedMonthYear + ".  Displayed:  "
                + displayDividerMonthYear, displayDividerMonthYear.equals(expectedMonthYear));

        LOGGER.info("assertMonthYearAppointmentDateDivider completed");
    }

    /**
     * Verify the the appointment time list is displayed
     */
    public void assertAppointmentTimeListDisplayed() {
        LOGGER.info("assertAppointmentTimeListDisplayed started");
        Assert.assertTrue("The appointment time list was not displayed",
                getAllApptTimesForDateCheckout().size() > 0);
        LOGGER.info("assertAppointmentTimeListDisplayed completed");
    }

    /**
     * Verify the date listed in the appointment time list header is displayed and correct
     */
    public void assertAppointmentTimeListHeaderDate() {
        LOGGER.info("assertAppointmentTimeListHeaderDate started");

        // Verify a date is present
        Assert.assertTrue("The appointment time list header does not contain a date",
                driver.isElementDisplayed(AppointmentPage.apptTimeListHeaderDate));

        // Get the day of week and the day of month from the header date
        String headerDate = AppointmentPage.apptTimeListHeaderDate.getText();
        String[] headerDateText = headerDate.split(" ");
        String headerDayOfWeek = headerDateText[0].replace(",", "").trim();
        String headerMonthDay = headerDateText[headerDateText.length - 1].trim();
        String headerDayOfMonth = headerDayOfWeek + " " + headerMonthDay;

        // Get the day of week and day of month from the selected date
        String selectedDate = AppointmentPage.apptSelectedDate.getText().
                replace("\n", " ").replace(VIEW_TIMES, "").trim();
        String expectedDayOfMonth = CommonUtils.replaceShortDayWithLongDay(selectedDate);

        // Verify the header date is correct
        Assert.assertTrue("The appointment time list header date is incorrect.  "
                        + "Selected date is '" + selectedDate + "'.  "
                        + "Time list header date is '" + headerDate + "'",
                expectedDayOfMonth.equals(headerDayOfMonth));

        LOGGER.info("assertAppointmentTimeListHeaderDate completed");
    }

    /**
     * Calculate Tax on checkout page after Shipping details and Shipping Method provided
     */
    public double calculateTaxOnCheckout() {
        LOGGER.info("calculateTaxOnCheckout started");
        double taxOnCheckout;
        double totalTaxOnCheckout = 0.00;
        List<WebElement> items = webDriver.findElements(summaryProductBy);
        String stateOnCart = (checkoutSummaryCustomerDetails.getText().split(",")[1].split("\\s+")[1]).trim();
        if (stateOnCart.equalsIgnoreCase(Constants.STATE_AZ) || stateOnCart.equalsIgnoreCase(Constants.STATE_TX)
                || stateOnCart.equalsIgnoreCase(Constants.STATE_GA) || stateOnCart.equalsIgnoreCase(Constants.STATE_OH)) {
            for (WebElement item : items) {
                String customerType;
                if (stateOnCart.equalsIgnoreCase(Constants.STATE_TX) || stateOnCart.equalsIgnoreCase(Constants.STATE_OH)
                        || stateOnCart.equalsIgnoreCase(Constants.STATE_GA)) {
                    customerType = "default_customer_".concat(stateOnCart.toLowerCase());
                } else {
                    customerType = "default_customer_dtd";
                }
                CheckoutPage.setRegionalTaxesFactor(customer.getCustomer(customerType));

                driver.waitForElementVisible(summaryProductProductTotalBy);
                String itemPriceTotal = item.findElement(summaryProductProductTotalBy).getText();
                taxOnCheckout = commonActions.getCalculatedSalesPriceForDTCRegion(itemPriceTotal);
                if (!stateOnCart.equalsIgnoreCase(Constants.STATE_TX) && !stateOnCart.equalsIgnoreCase(Constants.STATE_GA)) {
                    String envFee = item.findElement(CartPage.orderSummaryRows).findElement(CartPage.feeDetailsItemsRowPriceBy).getText();
                    taxOnCheckout = taxOnCheckout + commonActions.getCalculatedSalesPriceForDTCRegion(envFee);
                }
                if (driver.isElementDisplayed(checkoutSummaryCertificateBy)) {
                    String certFee = item.findElement(checkoutSummaryCertificateBy).findElement(CartPage.feeDetailsItemsRowPriceBy).getText();
                    taxOnCheckout = taxOnCheckout + commonActions.getCalculatedSalesPriceForDTCRegion(certFee);
                }
                if (driver.isElementDisplayed(checkoutSummaryFeeTpmsBy)) {
                    String optionalFee = item.findElement(checkoutSummaryFeeTpmsBy).findElement(CartPage.feeDetailsItemsRowPriceBy).getText();
                    taxOnCheckout = taxOnCheckout + commonActions.getCalculatedSalesPriceForDTCRegion(optionalFee);
                }
                if (driver.isElementDisplayed(CartPage.qualifyPromotionBy)) {
                    String promotion = item.findElement(CartPage.qualifyPromotionBy).getText();
                    taxOnCheckout = taxOnCheckout
                            - commonActions.getCalculatedSalesPriceForDTCRegion(promotion);
                }
                totalTaxOnCheckout = totalTaxOnCheckout + taxOnCheckout;
                if (!shipping.getText().equalsIgnoreCase(SHIPPING_FREE)) {
                    totalTaxOnCheckout = totalTaxOnCheckout + commonActions.getCalculatedSalesPriceForDTCRegion(shipping.getText());
                }
            }
        }
        LOGGER.info("calculateTaxOnCheckout completed");
        return commonActions.twoDForm(totalTaxOnCheckout, 2);
    }

    /**
     * Verifies the Tax on checkout page with calculated tax based on shipping address and fees applicable
     */
    public void assertTaxOnCheckout() {
        LOGGER.info("assertTaxOnCheckout started");
        driver.waitForElementVisible(checkoutAndOrderCartSummarySalesTax);
        double checkoutSalesTax = commonActions.cleanMonetaryString(checkoutAndOrderCartSummarySalesTax.getText());
        double expectedSalesTax = calculateTaxOnCheckout();
        Assert.assertTrue("FAIL: Expected sales tax on checkout page: (" + expectedSalesTax + ") did not match to the actual sales tax: ("
                + checkoutSalesTax + ")", expectedSalesTax == checkoutSalesTax);
        LOGGER.info("assertTaxOnCheckout completed");
    }

    /**
     * Verifies the order total on checkout page matches to sum of item total, calculated sales tax and shipping fee applied
     */
    public void assertCheckoutOrderPriceTotal() {
        LOGGER.info("assertCheckoutOrderPriceTotal started");
        driver.waitForElementVisible(orderTotal);
        double expectedOrderTotal = commonActions.cleanMonetaryString(checkoutAndOrderCartSummarySalesTax.getText())
                + commonActions.cleanMonetaryString(checkoutSubtotal.getText());
        if (!shipping.getText().equalsIgnoreCase(SHIPPING_FREE)) {
            expectedOrderTotal = expectedOrderTotal + commonActions.cleanMonetaryString(shipping.getText());
        }
        double actualOrderTotal = commonActions.cleanMonetaryString(orderTotal.getText());
        Assert.assertTrue("FAIL: The actual order total: \"" + actualOrderTotal
                        + "\" did not match to expected order total: \"" + commonActions.twoDForm(expectedOrderTotal, 2) + "\"!",
                actualOrderTotal == commonActions.twoDForm(expectedOrderTotal, 2));
        LOGGER.info("assertCheckoutOrderPriceTotal completed");
    }

    /**
     * Verify that the specified with or without appointment option is selected on the Checkout page
     *
     * @param appointmentOption - 'Install with appointment' or 'Install without appointment'
     */
    public void assertAppointmentOptionSelected(String appointmentOption) {
        LOGGER.info("assertAppointmentOptionSelected started");
        if (appointmentOption.equalsIgnoreCase(ConstantsDtc.INSTALL_WITH_APPOINTMENT)) {
            Assert.assertTrue("FAIL:  The 'install with appointment' option was not selected on the "
                    + "Appointment page!", driver.isElementDisplayed(apptDateContainer));
        } else {
            Assert.assertTrue("FAIL:  The 'install without appointment' option was not selected on the "
                    + "Appointment page!", !driver.isElementDisplayed(apptDateContainer));
        }
        LOGGER.info("assertAppointmentOptionSelected completed");
    }

    /**
     * Verify the selected 'Install without appointment' reason is correct
     *
     * @param expectedReason - the expected selected reason
     */
    public void assertInstallWithoutAppointmentSelectedReason(String expectedReason) {
        LOGGER.info("assertInstallWithoutAppointmentSelectedReason started");
        String selectedReason = installWithoutAppointmentSelectedReason.getText();
        Assert.assertTrue("FAIL:  The selected reason was not correct.  "
                        + "Expected:  " + expectedReason + ".  Actual:  " + selectedReason + "!",
                selectedReason.equals(expectedReason));
        LOGGER.info("assertInstallWithoutAppointmentSelectedReason completed");
    }

    /**
     * Verify the listed 'Install without appointment' reasons are correct
     */
    public void assertInstallWithoutAppointmentReasonOptions() {
        LOGGER.info("assertInstallWithoutAppointmentReasonOptions started");
        driver.waitForPageToLoad();
        withoutApptReasonToggleBtn.click();
        driver.waitForMilliseconds();
        List<WebElement> listedReasons = installWithoutAppointmentReasonGroup.
                findElements(CommonActions.dropdownOptionBy);
        String[] expectedReasons = {ConstantsDtc.APPT_NOT_SURE_OF_AVAILABILITY,
                ConstantsDtc.APPT_MAKE_AN_APPOINTMENT_AT_A_LATER_TIME,
                ConstantsDtc.APPT_THESE_ITEMS_ARE_FOR_MULTIPLE_VEHICLES};

        // Validate the reasons that are listed are correct
        for (WebElement listedReason : listedReasons) {
            String reasonText = listedReason.findElement(CommonActions.spanTagNameBy).getText();
            boolean found = false;
            for (int i = 0; i < expectedReasons.length; i++) {
                if (reasonText.equals(expectedReasons[i])) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue("FAIL:  Unexpected reason (" + reasonText + ") listed "
                    + "in the Install Without Appointment Reason dropdown!", found);
        }

        // Validate all of the expected reasons are listed
        for (String expectedReason : expectedReasons) {
            boolean found = false;
            for (int i = 0; i < listedReasons.size(); i++) {
                if (expectedReason.equals(listedReasons.get(i).findElement(CommonActions.spanTagNameBy).getText())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue("FAIL:  Expected reason (" + expectedReason + ") not listed "
                    + "in the Install Without Appointment Reason dropdown!", found);
        }
        withoutApptReasonToggleBtn.click();
        LOGGER.info("assertInstallWithoutAppointmentReasonOptions completed");
    }

    /**
     * Verify the expected Reserve Without Appointment message displayed
     */
    public void assertReserveWithoutAppointmentMessage(String installWithoutAppointmentReason) {
        LOGGER.info("assertReserveAppointmentMessage started");
        String expectedMessage = null;

        switch (installWithoutAppointmentReason) {
            case ConstantsDtc.APPT_NOT_SURE_OF_AVAILABILITY:
            case ConstantsDtc.APPT_MAKE_AN_APPOINTMENT_AT_A_LATER_TIME:
                expectedMessage = ConstantsDtc.RESERVE_WITHOUT_APPOINTMENT_MESSAGE;
                break;
            case ConstantsDtc.APPT_THESE_ITEMS_ARE_FOR_MULTIPLE_VEHICLES:
                expectedMessage = ConstantsDtc.RESERVE_WITHOUT_APPOINTMENT_MULTIPLE_VEHICLES_MESSAGE;
                break;
        }

        Assert.assertTrue("FAIL:  The expected reserve appointment message "
                + "(" + expectedMessage + ") did not display!", reserveAppointmentMessage.getText().equals(expectedMessage));
        LOGGER.info("assertReserveAppointmentMessage completed");
    }

    /**
     * Click the 'Make an Appointment' link
     */
    public void clickMakeAnAppointmentLink() {
        LOGGER.info("clickMakeAnAppointmentLink started");
        driver.waitForPageToLoad();
        makeAnAppointmentLink.click();
        LOGGER.info("clickMakeAnAppointmentLink completed");
    }

    /**
     * Verify the 'Continue to Customer Details' button is enabled or disabled
     *
     * @param enabledDisabled - expected status:  'enabled' or 'disabled'
     */
    public void verifyContinueToCustomerDetailsButtonStatus(String enabledDisabled) {
        LOGGER.info("verifyContinueToCustomerDetailsButtonStatus started");
        WebElement continueToCustomerDetailsButton = driver.getElementWithText(continueToButtonBy,
                ConstantsDtc.CONTINUE_TO_CUSTOMER_DETAILS);
        if (enabledDisabled.equalsIgnoreCase(Constants.ENABLED)) {
            Assert.assertTrue("FAIL: The 'Continue to Customer Details' button was disabled. "
                    + "Expected it to be enabled!", continueToCustomerDetailsButton.isEnabled());
        } else {
            Assert.assertTrue("FAIL: The 'Continue to Customer Details' button was enabled. "
                    + "Expected it to be disabled!", !continueToCustomerDetailsButton.isEnabled());
        }
        LOGGER.info("verifyContinueToCustomerDetailsButtonStatus completed");
    }

    /**
     * Verify 'View Times' did not display on any unavailable appointment dates
     */
    public void verifyViewTimesNotDisplayedForUnavailableAppointmentDate() {
        LOGGER.info("verifyViewTimesDisplayedNotDisplayed started");
        List<WebElement> unavailableApptDateList = getUnavailableApptDatesForCheckout();
        if (unavailableApptDateList.size() == 0) {
            Assert.fail("There are no unavailable appointment dates");
        }
        for (WebElement unavailableApptDate : unavailableApptDateList) {
            Assert.assertTrue("'View Times' displayed on an unavailable appointment date:  " + unavailableApptDate.getText(),
                    !unavailableApptDate.getText().contains(VIEW_TIMES));
        }
        LOGGER.info("verifyViewTimesDisplayedNotDisplayed completed");
    }

    /**
     * Verify 'Store Closed' is displayed on Sunday appointment date
     */
    public void verifyStoreClosedDisplayedOnSundayAppointmentDate() {
        LOGGER.info("verifyStoreClosedDisplayedOnSundayAppointmentDate started");
        List<WebElement> unavailableApptDateList = getUnavailableApptDatesForCheckout();
        boolean found = false;
        if (unavailableApptDateList.size() == 0) {
            Assert.fail("There are no unavailable appointment dates");
        }
        for (WebElement unavailableApptDate : unavailableApptDateList) {
            if (unavailableApptDate.getText().contains(Constants.SUN)) {
                Assert.assertTrue("'Store Closed' did not display on Sunday appointment date:  " + unavailableApptDate.getText(),
                        unavailableApptDate.getText().contains(STORE_CLOSED));
                found = true;
            }
        }
        if (!found) {
            Assert.fail("Unable to find Sunday appointment date");
        }
        LOGGER.info("verifyStoreClosedDisplayedOnSundayAppointmentDate completed");
    }

    /**
     * Select the first appointment date found for the specified day of the week
     *
     * @param dayOfWeek - Full name for day of the week
     */
    public void selectAppointmentDate(String dayOfWeek) {
        LOGGER.info("selectAppointmentDate started");
        driver.waitForElementVisible(apptDateContainer);
        boolean found = false;
        List<WebElement> availableApptDateList = getAvailableApptDatesForCheckout();
        for (WebElement apptDate : availableApptDateList) {
            if (apptDate.getText().contains(CommonUtils.replaceLongDayWithShortDay(dayOfWeek))) {
                apptDate.click();
                driver.waitForPageToLoad();
                found = true;
                break;
            }
        }
        if (!found) {
            Assert.fail("FAIL: None of the active dates listed were on '" + dayOfWeek + "'!");
        }
        LOGGER.info("selectAppointmentDate completed");
    }

    /**
     * Verify the appropriate peak times message is displayed for specified day of the week
     *
     * @param dayOfWeek - Full name for day of the week
     */
    public void verifyPeakTimesMessage(String dayOfWeek) {
        LOGGER.info("verifyPeakTimesMessage started");
        if (dayOfWeek.equalsIgnoreCase(Constants.SATURDAY)) {
            Assert.assertTrue("FAIL:  The message, 'Our busiest day of the week expect a longer wait for service', "
                    + "did not display for Saturday!", peakTimesMessage.getText().trim().equals(Constants.BUSIEST_DAY_OF_WEEK_MESSAGE));
        } else {
            if (dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.MONDAY) ||
                    dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.FRIDAY) ||
                    dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.TUESDAY) ||
                    dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.WEDNESDAY) ||
                    dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.THURSDAY)) {
                Assert.assertTrue("The 'Peak Times: Expect a longer wait for service' message did not display",
                        driver.isElementDisplayed(peakTimesMessage) && peakTimesMessage.getText().equals(PEAK_TIMES_MESSAGE));
            } else {
                Assert.fail("FAIL:  The input date is not valid.  Expected:  "
                        + "Any day Monday through Saturday.  Actual:  " + dayOfWeek + "!");
            }
        }
        LOGGER.info("verifyPeakTimesMessage completed");
    }

    /**
     * Verify the peak hours are defined correctly for specified day of the week
     *
     * @param dayOfWeek - Full name for day of the week
     */
    public void verifyPeakHours(String dayOfWeek) {
        LOGGER.info("verifyPeakHours started");
        String expectedPeakHoursStartTime = null;
        String expectedPeakHoursEndTime = Constants.ONE_PM;

        List<WebElement> peakHoursRows = webDriver.findElements(peakHoursRowContainerBy);

        if (dayOfWeek.equalsIgnoreCase(Constants.SATURDAY)) {
            Assert.assertTrue("FAIL:  Unexpected peak hours are defined when a Saturday date is selected!",
                    peakHoursRows.size() == 0);
            return;
        } else {
            if (dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.MONDAY) || dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.FRIDAY)) {
                expectedPeakHoursStartTime = Constants.EIGHT_AM;
            } else {
                if (dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.TUESDAY) ||
                        dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.WEDNESDAY) ||
                        dayOfWeek.toUpperCase().equalsIgnoreCase(Constants.THURSDAY)) {
                    expectedPeakHoursStartTime = Constants.ELEVEN_AM;
                } else {
                    Assert.fail("FAIL:  The input date is not valid.  Expected:  "
                            + "Any day Monday through Saturday.  Actual:  " + dayOfWeek + "!");
                }
            }
        }

        String displayedPeakHoursStartTime = peakHoursRows.get(0).findElement(appointmentRowLabelBy).getText().trim();
        String displayedPeakHoursEndTime = peakHoursRows.get(peakHoursRows.size() - 1).findElement(appointmentRowLabelBy).getText().trim();
        Assert.assertTrue("FAIL:  The peak hours block is incorrect for " + dayOfWeek + "!"
                        + "  Expected: " + expectedPeakHoursStartTime + " - " + expectedPeakHoursEndTime
                        + ".  Actual: " + displayedPeakHoursStartTime + " - " + displayedPeakHoursEndTime + "!",
                displayedPeakHoursStartTime.equals(expectedPeakHoursStartTime) &&
                        displayedPeakHoursEndTime.equals(expectedPeakHoursEndTime));
        LOGGER.info("verifyPeakHours completed");
    }

    /**
     * Select the first available appointment time within the peak hours for the selected date
     */
    public void selectFirstAvailablePeakHoursAppointmentTimeForSelectedDate() {
        LOGGER.info("selectFirstAvailablePeakHoursAppointmentTimeForSelectedDate started");
        List<WebElement> peakHoursRows = webDriver.findElements(peakHoursRowContainerBy);
        List<WebElement> apptTimes = null;

        for (WebElement peakHourRow : peakHoursRows) {
            try {
                apptTimes = peakHourRow.findElements(availableApptTimesBy);
                break;
            } catch (NoSuchElementException noElement) {
                LOGGER.info("No available appointments found on peak hour row "
                        + peakHourRow.getText().replace("Peak Times: Expect a longer wait for service", "").replace("\n", " "));
            }
        }

        Assert.assertTrue("FAIL:  No available appointments were found within peak hours for selected date!", apptTimes != null);
        apptTimes.get(0).click();
        LOGGER.info("selectFirstAvailablePeakHoursAppointmentTimeForSelectedDate completed.  Peak time " + apptTimes.get(0).getText() + " selected.");
    }

    /**
     * Verify message containing 'Service time for this appointment may be longer than usual'
     * and estimated time of completion displayed in the appointment time list
     */
    public void verifyPeakTimeSelectedMessage() {
        LOGGER.info("verifyPeakTimeSelectedMessage started");
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        String selectedTime = selectedAppointmentTime.getText();
        String AMPM = Constants.DAY_PM;
        Time timeValue = null;

        try {
            timeValue = new java.sql.Time(formatter.parse(selectedTime).getTime());
        } catch (ParseException e) {
            e.getMessage();
            Assert.fail("FAIL:  Unable to convert " + selectedTime + " to Time object!");
        }

        calendar.setTime(timeValue);
        calendar.add(MINUTE, 45);
        timeValue.setTime(calendar.getTimeInMillis());
        String timeString = timeValue.toString();
        timeString = timeString.substring(0, timeString.length() - 3);

        if (timeString.charAt(0) == '0') {
            timeString = timeString.substring(1, timeString.length());
            AMPM = Constants.DAY_AM;
        }

        String displayedMessage = peakTimeSelectedMessage.getText().replace("\n", "");
        String expectedMessage = SERVICE_TIME_LONGER_THAN_USUAL + EST_TIME_OF_COMPLETION + timeString + " " + AMPM;
        Assert.assertTrue("FAIL:  The expected message containing 'Service time for this appointment may be longer than usual. "
                + "Est. time of completion: " + timeString + " " + AMPM + "' did not display!", displayedMessage.equals(expectedMessage));
        LOGGER.info("verifyPeakTimeSelectedMessage completed");
    }

    /**
     * IF displayStatus == Displayed, the method verifies the current modal is 'Shipping Restriction', that the
     * messaging matches expected, as well as the controls / buttons (EDIT CART & REMOVE ITEMS) being active / enabled.
     * ELSE verifies that the modal is NOT displayed
     *
     * @param displayStatus Determines if the 'Shipping Restrictions' modal (and messaging) should or should not be
     *                      displayed after entering a customer's shipping information
     */
    public void verifyShippingRestrictionModalMessagingControls(String displayStatus) {
        LOGGER.info("verifyShippingRestrictionModalMessagingControls started");
        driver.waitForPageToLoad();

        if (displayStatus.equalsIgnoreCase(Constants.DISPLAYED)) {
            driver.waitForElementVisible(dtModal);
            Assert.assertTrue("FAIL: The currently displayed modal is not titled '" + SHIPPING_RESTRICTION + "'!",
                    dtModal.findElement(dtModalTitleBy).getText().equalsIgnoreCase(SHIPPING_RESTRICTION));

            Assert.assertTrue("FAIL: The 'Shipping Restriction' modal did NOT contain the expected messaging: '"
                            + SHIPPING_RESTRICTION_MESSAGING + "' in its displayed text of: '"
                            + shippingRestrictionMessagingContainer.getText() + "'!",
                    shippingRestrictionMessagingContainer.getText().contains(SHIPPING_RESTRICTION_MESSAGING));

            String[] controlNames = {EDIT_CART, REMOVE_ITEMS};
            for (String name : controlNames) {
                WebElement controlEle = driver.getElementWithText(dtModalButtonBy, name);

                if (controlEle != null) {
                    Assert.assertTrue("FAIL: The modal control: '" + name + "' was NOT enabled!",
                            controlEle.isEnabled());
                } else {
                    Assert.fail("FAIL: Could NOT find modal control: '" + name + "'!");
                }
            }
        } else {
            Assert.assertTrue("FAIL: The 'Shipping Restriction modal was displayed when it was expected"
                    + " NOT to be!", !driver.isElementDisplayed(dtModal, Constants.TWO_SEC_WAIT));
        }
        LOGGER.info("verifyShippingRestrictionModalMessagingControls completed");
    }

    /**
     * Selects / performs the desired action on the 'Shipping Restriction' modal window
     */
    public void selectActionFromShippingRestrictionModal(String action) {
        LOGGER.info("selectActionFromShippingRestrictionModal started with action: '" + action + "'");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(dtModal);

        switch (action) {
            case CHANGE_ADDRESS:
                if (!Config.isSafari()) {
                    driver.clickElementWithLinkText(CHANGE_ADDRESS);
                } else {
                    dtModal.findElement(CommonActions.anchorTagBy).click();
                }
                break;
            case Constants.CLOSE:
                commonActions.closeModalWindow();
                break;
            case REMOVE_ITEMS:
                driver.clickElementWithText(dtModalButtonBy, REMOVE_ITEMS);
                break;
            default:
                Assert.fail("FAIL: The specified action: '" + action + "' is NOT currently a valid selection on the"
                        + " 'Shipping Restriction' modal!");
                break;
        }
        LOGGER.info("selectActionFromShippingRestrictionModal completed with action: '" + action + "'");
    }

    /**
     * Verifies the specified product is either displayed or not displayed in the 'Order Summary' of the Checkout page
     *
     * @param productName   Name of the product to search for in the 'Order Summary'
     * @param displayStatus Display expectation for the product - displayed or not displayed
     */
    public void verifyProductDisplayInOrderSummary(String productName, String displayStatus) {
        LOGGER.info("verifyProductDisplayInOrderSummary started with product: '" + productName
                + "', verifying that it is '" + displayStatus + "'");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(checkoutOrderSummaryBy);
        if (displayStatus.equalsIgnoreCase(Constants.DISPLAYED)) {
            Assert.assertTrue("FAIL: Product \"" + productName + "\" was NOT displayed in the"
                    + " 'Order Summary' of the Checkout page!", driver.checkIfElementContainsTextLowerCase(
                    checkoutOrderSummaryBy, productName));
        } else {
            Assert.assertTrue("FAIL: Product \"" + productName + "\" was expected to NOT be displayed but"
                            + " was present in the 'Order Summary' of the Checkout page!",
                    !driver.checkIfElementContainsTextLowerCase(checkoutOrderSummaryBy, productName));
        }
        LOGGER.info("verifyProductDisplayInOrderSummary completed with product: '" + productName
                + "', verified that it was '" + displayStatus + "'");
    }

    /**
     * Verify Need Help link is displayed
     */
    public void assertNeedHelpLinkDisplayed() {
        LOGGER.info("assertNeedHelpLinkDisplayed started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: Need Help link did NOT display", driver.isElementDisplayed(needHelpElement));
        LOGGER.info("assertNeedHelpLinkDisplayed completed");
    }

    /**
     * Verify Need Help popup values are displayed
     */
    public void assertNeedHelpPopUpValuesDisplayed() {
        LOGGER.info("assertNeedHelpPopUpValuesDisplayed started");
        driver.waitForPageToLoad();
        if (!(driver.getParentElement(needHelpEmailIcon).getText().contains(EMAIL)
                && needHelpPhoneElement.getText().contains(Config.getDefaultStorePhoneNumber()))) {
            Assert.fail("FAIL: Need Help PopUp values did NOT match");
        }
        LOGGER.info("assertNeedHelpPopUpValuesDisplayed completed");
    }

    /**
     * Verify display of "Sign In to Skip this step" in Checkout Page
     *
     * @param text displayed or not displayed
     */
    public void assertSignInToSkipThisStepDisplay(String text) {
        LOGGER.info("assertSignInToSkipThisStepDisplay started");
        driver.waitForPageToLoad();
        if (text.equalsIgnoreCase(Constants.DISPLAYED)) {
            WebElement signInToSkipThisStep =
                    driver.getElementWithText(CommonActions.dtLinkBy, ConstantsDtc.SIGN_IN_TO_SKIP_THIS_STEP);
            WebElement joinSignIn = driver.getElementWithText(CommonActions.dtLinkBy, ConstantsDtc.JOIN_SIGN_IN);
            Assert.assertTrue("FAIL: User is showing already Signed In",
                    driver.isElementDisplayed(joinSignIn));
            Assert.assertTrue("FAIL: 'sign in to skip this step' NOT displayed in Checkout Page!",
                    driver.isElementDisplayed(signInToSkipThisStep));
        } else {
            Assert.assertTrue("FAIL: User is not Signed In!",
                    !driver.isTextPresentInPageSource(ConstantsDtc.JOIN_SIGN_IN));
            Assert.assertTrue("FAIL: 'sign in to skip this step' is displayed in Customer Details Checkout"
                            + " Page for already logged in customer!",
                    !driver.isTextPresentInPageSource(ConstantsDtc.SIGN_IN_TO_SKIP_THIS_STEP));
        }
        LOGGER.info("assertSignInToSkipThisStepDisplayed completed");
    }

    /**
     * Clicks "Sign In to Skip this step" link in Customer Details Checkout Page
     */
    public void clickSignInToSkipThisStep() {
        LOGGER.info("clickSignInToSkipThisStep started");
        driver.waitForPageToLoad();
        driver.getElementWithText(CommonActions.dtLinkBy, ConstantsDtc.SIGN_IN_TO_SKIP_THIS_STEP).click();
        LOGGER.info("clickSignInToSkipThisStep completed");
    }

    /**
     * Verify Signed in user first name is displayed on checkout page header
     *
     * @param firstName String to validate after user signed in
     */
    public void assertUserNameDisplayedOnGlobalHeader(String firstName) {
        LOGGER.info("assertUserNameDisplayedOnGlobalHeader started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: " + firstName + " name was NOT displayed on Global header,  Actual value :"
                        + userSignedInLabel.getText() + " did NOT match with expected name: \"" + firstName + "\"!",
                userSignedInLabel.getText().contains(firstName));
        LOGGER.info("assertUserNameDisplayedOnGlobalHeader completed");
    }

    /**
     * Verify pre-populated shipping user details on checkout page
     *
     * @param userDetails  Comma separated string of inputFields to be verified
     * @param customerType customer user profile
     */
    public void assertPrePopulatedShippingDetails(String userDetails, String customerType) {
        LOGGER.info("assertPrePopulatedShippingDetails started");
        String inputFieldLabel = "";
        String inputFieldValue = "";
        List<String> inputValues = Arrays.asList(userDetails.split("\\s*,\\s*"));

        Customer expectedUser = customer.getCustomer(customerType);

        for (String inputValue : inputValues) {
            switch (inputValue) {
                case FIRST_NAME:
                    inputFieldLabel = FIRST_NAME;
                    inputFieldValue = expectedUser.firstName;
                    break;
                case LAST_NAME:
                    inputFieldLabel = LAST_NAME;
                    inputFieldValue = expectedUser.lastName;
                    break;
                case ADDRESS_LINE_1:
                    inputFieldLabel = ADDRESS_LINE_1;
                    inputFieldValue = expectedUser.address1;
                    break;
                case ADDRESS_LINE_2:
                    inputFieldLabel = ADDRESS_LINE_2;
                    inputFieldValue = expectedUser.address2;
                    break;
                case ConstantsDtc.ZIP_CODE:
                    inputFieldLabel = ConstantsDtc.ZIP_CODE;
                    inputFieldValue = expectedUser.zip;
                    break;
                case EMAIL:
                    inputFieldLabel = EMAIL;
                    inputFieldValue = expectedUser.email;
                    break;
                case Constants.PHONE_NUMBER:
                    inputFieldLabel = Constants.PHONE_NUMBER;
                    inputFieldValue = expectedUser.phone;
                    break;
            }
            assertPopulatedCustomerInfo(inputFieldLabel, inputFieldValue);
        }
        LOGGER.info("assertPrePopulatedShippingDetails completed");
    }

    /**
     * Verify populated customer details
     *
     * @param inputFieldLabel displayed form label
     * @param inputFieldValue displayed form value
     */
    public void assertPopulatedCustomerInfo(String inputFieldLabel, String inputFieldValue) {
        LOGGER.info("assertPopulatedCustomerInfo started");
        List<WebElement> formValues = webDriver.findElements(CommonActions.formGroupBy);
        String actualValue = "";
        for (WebElement formValue : formValues) {
            if (formValue.getText().contains(inputFieldLabel)) {
                actualValue = formValue.findElement(CommonActions.inputBy).getAttribute(Constants.VALUE);
                break;
            }
        }
        Assert.assertTrue("FAIL: Input field \"" + inputFieldLabel + "\" did not contain value \""
                        + inputFieldValue + "\", but instead contained \"" + actualValue + "\"",
                actualValue.contains(inputFieldValue));
        LOGGER.info("assertPopulatedCustomerInfo completed");
    }

    /**
     * Select either 'ROPIS' or 'BOPIS' option
     *
     * @param selection - The option to choose: 'ROPIS' or 'BOPIS'
     */
    public void selectPickUpInStoreOption(String selection) {
        LOGGER.info("selectPickUpInStoreOption started selecting '" + selection + "'");
        if (selection.equalsIgnoreCase(ConstantsDtc.ROPIS)) {
            webDriver.findElements(CommonActions.radioButtonBy).get(0).click();
        } else {
            webDriver.findElements(CommonActions.radioButtonBy).get(1).click();
        }
        LOGGER.info("selectPickUpInStoreOption completed selecting '" + selection + "'");
    }

    /**
     * Verify the product name and quantity are correct on the Checkout page
     *
     * @param productName     - name of the product
     * @param productQuantity - expected quantity
     */
    public void verifyProductQuantity(String productName, String productQuantity) {
        LOGGER.info("verifyProductQuantity started for product '" + productName + "'");
        List<WebElement> items = webDriver.findElements(summaryProductBy);
        String quantity = "";
        boolean found = false;

        for (WebElement item : items) {
            if (CommonUtils.containsIgnoreCase(item.getText(), productName)) {
                WebElement productQuantityElement = item.findElement(productQuantityBy);
                quantity = productQuantityElement.getText().split(":")[1].trim();
                found = true;
                break;
            }
        }

        if (!found) {
            Assert.fail("FAIL: The product summary did not contain the expected product: " + productName);
        }

        Assert.assertTrue("FAIL: The quantity displayed was not correct for '" + productName +
                        "'. Expected: '" + productQuantity + "'. Actual: '" + quantity + "'",
                Integer.parseInt(quantity) == Integer.parseInt(productQuantity));
        LOGGER.info("verifyProductQuantity completed for product '" + productName + "'");
    }

    /**
     * Verify Instant Savings with Cart page Instant Savings
     *
     * @param page Web Page Header
     */
    public void assertInstantSavings(String page) {
        LOGGER.info("assertInstantSavings started for " + page);
        driver.waitForPageToLoad();
        int i = 0;
        List<WebElement> Savings = driver.getElementsWithText(CartPage.cartItemRowBy, ConstantsDtc.INSTANT_SAVINGS);
        for (WebElement saving : Savings) {
            String expectedPromotion = CommonActionsSteps.cartInstantPromotionPrice.get(i);
            String instantSavingText = saving.getText().split("-")[0].trim();
            String instantSavingPrice = saving.getText().split("\\-\\$")[1].trim();
            Assert.assertTrue(
                    "FAIL: Instant Savings displayed not matching with Instant Savings applied from Cart Page!",
                    expectedPromotion.contains(instantSavingText) &&
                            expectedPromotion.contains(instantSavingPrice));
            i++;
        }
        Assert.assertTrue("FAIL: Instant Savings not displayed on page " + page,
                !(driver.getElementsWithText(CartPage.cartItemRebateNameBy, ConstantsDtc.INSTANT_SAVINGS).size() == 0));
        LOGGER.info("assertInstantSavings completed for " + page);
    }

    /**
     * Verify the display of 'Reserve now, pickup in-store' on Checkout Page
     *
     * @param display displayed or not displayed
     */
    public void assertReserveNowPickupInStoreDisplay(String display) {
        LOGGER.info("assertReserveNowPickupInStoreDisplay started");
        if (display.equalsIgnoreCase(Constants.DISPLAYED)) {
            Assert.assertTrue("FAIL:  'Reserve now, pickup in-store' not displayed!",
                    driver.isElementDisplayed(ropisBy));
        } else {
            Assert.assertTrue("FAIL:  'Reserve now, pickup in-store' is displayed!",
                    !driver.isElementDisplayed(ropisBy));
        }
        LOGGER.info("assertReserveNowPickupInStoreDisplay completed");
    }

    /**
     * Verify the display of 'Pay now, pick up in-store' on Checkout Page
     *
     * @param display displayed or not displayed
     */
    public void assertPayNowPickupInStoreDisplay(String display) {
        LOGGER.info("assertPayNowPickupInStoreDisplay started");
        if (display.equalsIgnoreCase(Constants.DISPLAYED)) {
            Assert.assertTrue("FAIL:  'Pay now, pick up in-store' not displayed!",
                    driver.isElementDisplayed(bopisBy));
        } else {
            Assert.assertTrue("FAIL:  'Pay now, pick up in-store' is displayed!",
                    !driver.isElementDisplayed(bopisBy));
        }
        LOGGER.info("assertPayNowPickupInStoreDisplay completed");
    }

    /**
     * Expand 'Split credit card payment' to be able to enter a second credit card
     */
    public void expandSplitCreditCardPayment() {
        LOGGER.info("expandSplitCreditCardPayment started");
        if (!driver.isElementDisplayed(CommonActions.openCollapsibleSection)) {
            driver.clickElementWithText(CommonActions.collapsibleToggleBy, Constants.SPLIT_CREDIT_CARD_PAYMENT);
        }
        LOGGER.info("expandSplitCreditCardPayment completed");
    }

    /**
     * Expand 'Someone else will pickup my order' to be able to enter recipient information
     */
    public void expandSomeoneElseWillPickupMyOrder() {
        LOGGER.info("expandSomeoneElseWillPickupMyOrder started");
        if (!driver.isElementDisplayed(CommonActions.openCollapsibleSection)) {
            driver.clickElementWithText(CommonActions.collapsibleToggleBy,
                    Constants.SOMEONE_ELSE_WILL_PICK_UP_MY_ORDER);
        }
        LOGGER.info("expandSomeoneElseWillPickupMyOrder completed");
    }

    /**
     * Reduce the paid amount by the specified amount
     *
     * @param amount - Either the word 'half' or a numeric value that can be converted to a Double
     */
    public void reducePayment(String amount) {
        LOGGER.info("reducePayment started");
        DecimalFormat df = new DecimalFormat("#.##");
        Double newAmount = 0.00;

        List<WebElement> amountFields = driver.getDisplayedElementsList(amountBy);

        String amountValue = amountFields.get(0).getAttribute("value");
        Double dollarAmount = Double.parseDouble(amountValue);

        if (amount.equalsIgnoreCase(Constants.HALF)) {
            newAmount = dollarAmount / 2;
        } else {
            try {
                newAmount = Double.parseDouble(amount);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Assert.fail("FAIL: Invalid amount: '" + amount +
                        "'. The amount specified must be either a decimal or the word 'half'");
            }
        }

        driver.waitForMilliseconds();

        // It is necessary to backspace over the amount rather than clear the field. Otherwise the old value is
        // retained (although briefly hidden), and the new value is appended to it rather than overwriting it.
        for (int i = 0; i < amountValue.length(); i++) {
            amountFields.get(1).sendKeys(Keys.BACK_SPACE);
        }

        String newAmountText = String.valueOf(df.format(newAmount));
        amountFields.get(1).sendKeys(newAmountText);
        amountFields.get(1).sendKeys(Keys.TAB);
        splitPayRemainingAmount = df.format(dollarAmount - Double.parseDouble(newAmountText));
        LOGGER.info("reducePayment completed");
    }

    /**
     * Click the 'SAVE CARD DETAILS' button at the bottom of the second credit card form
     */
    public void clickSaveCardDetails() {
        LOGGER.info("clickSaveCardDetails started");
        try {
            WebElement saveCardDetailsButton = driver.getElementWithText(continueToButtonBy,
                    ConstantsDtc.SAVE_CARD_DETAILS);
            driver.waitForElementClickable(saveCardDetailsButton);
            driver.jsScrollToElement(saveCardDetailsButton);
            saveCardDetailsButton.click();

            if (driver.isElementDisplayed(checkoutErrorBy, Constants.THREE_SEC_WAIT)) {
                Assert.fail("FAIL: The credit card was unable to be validated!");
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Saving card details failed with error: " + e);
        }
        LOGGER.info("clickSaveCardDetails completed");
    }

    /**
     * Scrolls to and selects the date slot (row) for the specified holiday type (fullday or partial)
     *
     * @param holidayType fullday (store closed for full day) or partial (store closed for only part of the day)
     */
    public void scrollTimeSlotToHoliday(String holidayType) {
        LOGGER.info("scrollTimeSlotToHoliday started with holiday type: \"" + holidayType + "\"");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(apptDateContainer);

        if (holidayType.equalsIgnoreCase(FULLDAY)) {
            selectFulldayHolidayDate();
        } else {
            selectPartialHolidayDate();
        }

        LOGGER.info("scrollTimeSlotToHoliday completed with holiday type: \"" + holidayType + "\"");
    }

    /**
     * Iterates over the currently displayed date rows, looking for the store closed due to partial holiday message
     * container
     */
    private void selectPartialHolidayDate() {
        LOGGER.info("selectPartialHolidayDate started");
        boolean partialHolidayFound = false;

        List<WebElement> apptDatesList = webDriver.findElements(availableApptDatesBy);
        for (WebElement apptDate : apptDatesList) {
            driver.jsScrollToElement(apptDate);
            apptDate.click();

            if (driver.isElementDisplayed(partialHolidayClosedMessage, Constants.ONE_SEC_WAIT)) {
                partialHolidayFound = true;
                break;
            }
        }

        Assert.assertTrue("FAIL: Could not find any dates considered partial holidays!", partialHolidayFound);
        LOGGER.info("selectPartialHolidayDate completed");
    }

    /**
     * Iterates over the currently displayed date rows, looking for the text of the row to contain either "Store Closed"
     * or "Thanksgiving Day" (should always be present in QA environments). Once the fullday holiday is found, method
     * looks for the first day with available appointment times after the holiday. If none are found, method attempts
     * to look for days before the holiday.
     */
    private void selectFulldayHolidayDate() {
        LOGGER.info("selectFulldayHolidayDate started");
        List<WebElement> apptDatesList = webDriver.findElements(allAvailableApptDatesBy);

        for (WebElement apptDate : apptDatesList) {
            if (apptDate.getText().contains(ConstantsDtc.THANKSGIVING_DAY)
                    || apptDate.getText().contains(ConstantsDtc.STORE_CLOSED)) {
                int fullHolidayDateIndex = apptDatesList.indexOf(apptDate);
                int nextPrevControlClicks = 0;
                boolean dateAfterHolidayFound = false;
                WebElement closestAvailableApptDate = null;

                for (int i = fullHolidayDateIndex; i < apptDatesList.size(); i++) {
                    if (apptDatesList.get(i).getText().contains(VIEW_TIMES)) {
                        closestAvailableApptDate = apptDatesList.get(i);
                        dateAfterHolidayFound = true;
                        break;
                    }
                    nextPrevControlClicks++;
                }

                if (!dateAfterHolidayFound) {
                    for (int i = fullHolidayDateIndex; i >= 0; i--) {
                        if (apptDatesList.get(i).getText().contains(VIEW_TIMES)) {
                            closestAvailableApptDate = apptDatesList.get(i);
                            break;
                        }
                        nextPrevControlClicks++;
                    }
                }

                if (closestAvailableApptDate == null)
                    Assert.fail("FAIL: Could not find any dates before OR after the holiday with available appointment"
                            + " times!");

                driver.jsScrollToElement(closestAvailableApptDate);
                closestAvailableApptDate.click();
                driver.waitForMilliseconds();
                navToFulldayHolidayDate(nextPrevControlClicks, dateAfterHolidayFound);
            }
        }
        LOGGER.info("selectFulldayHolidayDate completed");
    }

    /**
     * Uses the 'Popular Times' control in the 'Appointment Details' section to navigate to the fullday holiday
     * date. If a date is found after the fullday holiday, the number of clicks from that date back (or forward) to the
     * holiday are then used with the prev (or next) control. End result of this method is date focus has been moved to
     * the fullday holiday (since user cannot click to select that day)
     *
     * @param nextPrevControlClicks number of clicks from the first available date with appointment times back to the
     *                              fullday holiday
     * @param dateAfterHolidayFound True if a date with available appointment times was found after the holiday
     */
    private void navToFulldayHolidayDate(int nextPrevControlClicks, boolean dateAfterHolidayFound) {
        LOGGER.info("navToFulldayHolidayDate started with # of clicks set to: \"" + nextPrevControlClicks
                + "\" and dateAfterHolidayFound: \"" + dateAfterHolidayFound + "\"");

        WebElement dateControl;
        if (dateAfterHolidayFound) {
            dateControl = prevDayArrowControl;
        } else {
            dateControl = nextDayArrowControl;
        }

        do {
            driver.jsScrollToElement(dateControl);
            dateControl.click();
            driver.waitForMilliseconds();
            nextPrevControlClicks--;
        } while (nextPrevControlClicks > 0);

        LOGGER.info("navToFulldayHolidayDate completed with # of clicks set to: \"" + nextPrevControlClicks
                + "\" and dateAfterHolidayFound: \"" + dateAfterHolidayFound + "\"");
    }

    /**
     * Verifies the 'Store Schedule' bar graph is either displayed or not displayed
     *
     * @param displayExpectation True if the bar graph is expected to be displayed, otherwise False
     */
    public void verifyGraphShowingStoreScheduleDisplay(String displayExpectation) {
        LOGGER.info("verifyGraphShowingStoreScheduleDisplay started with displayedExpectation: \""
                + displayExpectation + "\"");
        driver.waitForElementVisible(apptDateContainer);
        boolean storeScheduleBarGraphDisplayed = driver.isElementDisplayed(barChartGraph, Constants.ONE_SEC_WAIT);

        if (displayExpectation.equalsIgnoreCase(Constants.DISPLAYED)) {
            Assert.assertTrue("FAIL: The bar chart graph for the current store's schedule was NOT displayed!",
                    storeScheduleBarGraphDisplayed);
        } else {
            Assert.assertFalse("FAIL: The bar chart graph for the current store's schedule WAS displayed!",
                    storeScheduleBarGraphDisplayed);
        }
        LOGGER.info("verifyGraphShowingStoreScheduleDisplay completed with displayedExpectation: \""
                + displayExpectation + "\"");
    }

    /**
     * Verifies the 'First Available Appointment Time' message is either displayed or not displayed
     *
     * @param displayExpectation True if the messaging is expected to be displayed, otherwise False
     */
    public void verifyFirstAvailableAppointmentTimeMessageDisplay(String displayExpectation) {
        LOGGER.info("verifyFirstAvailableAppointmentTimeMessageDisplay started with displayedExpectation: \""
                + displayExpectation + "\"");
        driver.waitForElementVisible(apptDateContainer);
        boolean messageDisplayed = driver.isElementDisplayed(firstAvailableAppointmentTimeMessage,
                Constants.ONE_SEC_WAIT);

        if (displayExpectation.equalsIgnoreCase(Constants.DISPLAYED)) {
            Assert.assertTrue("FAIL: The \"First Available Appointment Time\" message was NOT displayed!",
                    messageDisplayed);
        } else {
            Assert.assertFalse("FAIL: The \"First Available Appointment Time\" message WAS displayed!",
                    messageDisplayed);
        }
        LOGGER.info("verifyFirstAvailableAppointmentTimeMessageDisplay completed with displayedExpectation: \""
                + displayExpectation + "\"");
    }

    /**
     * Verify promotions display order for staggered/two products on Checkout Page
     */
    public void assertPromotionDisplayOrder() {
        LOGGER.info("assertPromotionDisplayOrder started for ");
        driver.waitForPageToLoad();
        List<WebElement> displayedSummary = webDriver.findElements(summaryProductDisplayBy);
        for (int i = 0; i < displayedSummary.size(); i++) {
            String summaryItem = displayedSummary.get(i).getAttribute(Constants.CLASS);
            if (!(Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD))) {
                Assert.assertTrue("FAIL: Displayed class on order summary: '" + summaryItem + "' expected: '"
                                + CART_SUMMARY_PROMOTIONS_DISPLAY_CHECKOUT_DTAT[i],
                        CART_SUMMARY_PROMOTIONS_DISPLAY_CHECKOUT_DTAT[i].equalsIgnoreCase(summaryItem));
            } else {
                Assert.assertTrue("FAIL: Displayed class on order summary: '" + summaryItem + "' expected: '"
                                + CART_SUMMARY_PROMOTIONS_DISPLAY_CHECKOUT_DTD[i],
                        CART_SUMMARY_PROMOTIONS_DISPLAY_CHECKOUT_DTD[i].equalsIgnoreCase(summaryItem));
            }
        }
        LOGGER.info("assertPromotionDisplayOrder completed for ");
    }

    /**
     * Verify Mail In rebate on checkout page
     *
     * @param page Web Page Header
     */
    public void assertMailInRebate(String page) {
        LOGGER.info("assertMailInRebate started for " + page);
        driver.waitForPageToLoad();
        int i = 0;
        List<WebElement> items = driver.getElementsWithText(CartPage.cartItemRebateNameBy, ConstantsDtc.MAIL_IN_REBATE);
        for (WebElement item : items) {
            String expectedPromotion = CommonActionsSteps.cartMailInPromotion.get(i);
            String mailInRebate = item.getText().split("-")[0].trim();
            Assert.assertTrue("FAIL: Mail In Rebates displayed: " + mailInRebate
                            + " .not matching with Mail In Rebates applied from Cart Page!: " + expectedPromotion,
                    expectedPromotion.contains(mailInRebate));
            i++;
        }
        Assert.assertTrue("FAIL: Instant Savings not displayed on page " + page,
                !(items.size() == 0));
        LOGGER.info("assertMailInRebate completed for " + page);
    }

    /**
     * Verifies the global message header displays the "Unable to process the card..." message to the user
     */
    public void verifyUnableToProcessCardMessaging() {
        LOGGER.info("verifyUnableToProcessCardMessaging started");
        driver.waitForElementVisible(CommonActions.globalMessage);
        Assert.assertTrue("FAIL: The message - \"" + UNABLE_TO_PROCESS_CARD + "\" was NOT displayed!",
                CommonActions.globalMessage.getText().contains(UNABLE_TO_PROCESS_CARD));
        LOGGER.info("verifyUnableToProcessCardMessaging completed");
    }

    /**
     * Enters a user specific string into the CVN / CVV field. Primary use is for entering an incorrect CVN / CVV
     * number
     *
     * @param invalidCvn User specified string to enter as the CVN / CVV
     */
    public void enterInvalidCvnNumber(String invalidCvn) {
        enterCvnNumber(invalidCvn, null);
    }

    /**
     * Enters a valid CVN / CVV number for a specified credit card
     *
     * @param cardType Credit card from which to set the CVN / CVV
     */
    public void enterValidCvnNumberForCard(String cardType) {
        enterCvnNumber(null, cardType);
    }

    /**
     * Enters either a user specified CVN or the CVN for a specific credit card
     *
     * @param cvnNumber CVN / CVV to be entered
     * @param cardType  Credit card from which to set the CVN / CVV
     */
    private void enterCvnNumber(String cvnNumber, String cardType) {
        LOGGER.info("enterCvnNumber started with cvn number: \"" + cvnNumber + "\" and cardType: \""
                + cardType + "\"");
        driver.waitForElementVisible(paymentTypesContainerBy);

        if (cardType != null) {
            setCreditCardForCustomer(cardType, customer);
            cvnNumber = customer.cvn;
        }

        PaymentFields paymentFields = new PaymentFields().invokePaymentFields();
        WebElement ccCvn = paymentFields.getCcCvn();

        ccCvn.clear();
        ccCvn.sendKeys(cvnNumber);
        LOGGER.info("enterCvnNumber completed with cvn number: \"" + cvnNumber + "\" and cardType: \""
                + cardType + "\"");
    }

    /**
     * Selects the "Edit card details" link for either the primary or secondary credit card on an order
     *
     * @param cardRole Primary or Secondary which decides which "Edit card details" link to select
     */
    public void editCardDetailsForCreditCard(String cardRole) {
        LOGGER.info("editCardDetailsForCreditCard started for card in the \"" + cardRole + "\" role");
        driver.waitForPageToLoad();
        WebElement paymentDetailsEditLink;
        WebElement paymentDetailsCardContainer = paymentDetailsPrimaryCard;
        driver.waitForElementVisible(paymentDetailsCardContainer);

        if (cardRole.equalsIgnoreCase(SECONDARY))
            paymentDetailsCardContainer = paymentDetailsSecondaryCard;

        paymentDetailsEditLink = paymentDetailsCardContainer.findElement(editCardDetailsLinkBy);
        driver.jsScrollToElement(paymentDetailsEditLink);
        paymentDetailsEditLink.click();
        LOGGER.info("editCardDetailsForCreditCard completed for card in the \"" + cardRole + "\" role");
    }
}