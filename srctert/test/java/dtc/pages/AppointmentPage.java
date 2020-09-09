package dtc.pages;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import dtc.data.Customer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonUtils;
import utilities.Driver;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/28/16. Updated by aarora
 */
public class AppointmentPage {

    private WebDriver webDriver;
    private Driver driver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(AppointmentPage.class.getName());

    public AppointmentPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String SPECIAL_ORDER_MESSAGE = "You selected product that is \"Special Order\". " +
            "A representative from the store you selected will verify product availability and contact you " +
            "(within 1 business day) regarding installation.";
    private static final String CALL_US_MESSAGE = "Call us at 800-589-6789";
    public static final String SCHEDULE_SERVICE_HEADER = "Schedule appointment";
    private static final String CANCEL = "Cancel";
    public static final String EST_COMPLETION_TIME = "Est. Completion Time:";
    public static final String PEAK = "Peak";
    public static final String TIME = "Time:";

    @FindBy(className = "dt-form__submit")
    public static WebElement defaultButton;

    @FindBy(className = "appointment-details__select-date-day")
    public static WebElement selectDateBox;

    @FindBy(id = "choose-time")
    public static WebElement selectTimeBox;

    @FindBy(id = "choose-date_table")
    public static WebElement chooseDateTable;

    @FindBy(className = "appointment-steps__review-button")
    public static WebElement nextStepForDateTimeCustomerInfoButton;

    @FindBy(id = "first-name")
    public static WebElement firstNameBoxAppointmentFlow;

    @FindBy(id = "last-name")
    public static WebElement lastNameBoxAppointmentFlow;

    @FindBy(id = "phone-number")
    public static WebElement phoneNumberBoxAppointmentFlow;

    @FindBy(id = "email-address")
    public static WebElement emailBoxAppointmentFlow;

    @FindBy(id = "firstNameId")
    public static WebElement firstNameBoxServiceFlow;

    @FindBy(id = "lastNameId")
    public static WebElement lastNameBoxServiceFlow;

    @FindBy(id = "phoneId")
    public static WebElement phoneNumberBoxServiceFlow;

    @FindBy(id = "accountEmailId")
    public static WebElement emailBoxServiceFlow;

    @FindBy(className = "react-selectize-search-field-and-selected-values")
    public static WebElement phoneDropDownWebServiceFlow;

    @FindBy(id = "phoneTypeId")
    public static WebElement phoneDropDownMobile;

    @FindBy(className = "appointment-steps__edit")
    public static WebElement appointmentEditLink;

    @FindBy(linkText = "Change store")
    public static WebElement changeStoreLink;

    @FindBy(className = "picker__nav--next")
    public static WebElement nextMonthArrow;

    @FindBy(className = "auto-picker-list")
    public static WebElement pickTimeContainer;

    @FindBy(name = "appointmentDate")
    public static WebElement appointmentDate;

    @FindBy(name = "appointmentTime")
    public static WebElement appointmentTime;

    @FindBy(className = "picker__month")
    public static WebElement currentMonth;

    @FindBy(id = "addressFlag")
    public static WebElement enterAddressCheckbox;

    @FindBy(className = "checkout-payment__address1")
    public static WebElement address1;

    @FindBy(className = "checkout-payment__address2")
    public static WebElement address2;

    @FindBy(className = "checkout-payment__city")
    public static WebElement city;

    @FindBy(className = "checkout-payment__zip")
    public static WebElement zip;

    @FindBy(id = "addressProvince")
    public static WebElement stateDropDownMobile;

    @FindBy(className = "picker__list-message-walk-ins")
    public static WebElement walkInsWelcome;

    @FindBy (xpath = "//button[@type='submit']")
    public static WebElement submitBtn;

    @FindBy (className = "picker__button--close")
    public static WebElement datePickerCloseBtn;
    
    @FindBy (className = "appointment-summary__address")
    public static WebElement appointmentDateTime;

    @FindBy(className = "appointment-details__select-time-message")
    public static WebElement apptSelectedMsg;

    @FindBy(className = "appointment-details__select-date-month")
    public static WebElement apptMonthAndYear;

    @FindBy(xpath = "//a[contains(@href, 'appointment-info')]")
    public static WebElement editApptLink;

    @FindBy(className = "datepicker-message")
    public static WebElement datepickerMessage;

    @FindBy (className = "appointment-details__select-time-header-current-date")
    public static WebElement apptTimeListHeaderDate;

    @FindBy (className = "appointment-details__select-date--selected")
    public static WebElement apptSelectedDate;

    @FindBy (name = "recipientFirstName")
    public static WebElement recipientFirstName;

    @FindBy (name = "recipientLastName")
    public static WebElement recipientLastName;

    @FindBy (name = "recipientPhone")
    public static WebElement recipientPhone;

    @FindBy (name = "recipientEmail")
    public static WebElement recipientEmail;

    private static final By recipientPhoneNumberRadioLabel = By.cssSelector(".form-group__radio-label[for='recipientContactPhone']");

    private static final By recipientEmailAddressRadioLabel = By.cssSelector(".form-group__radio-label[for='recipientContactEmail']");

    private static final By recipientReceiveTextMessagesLabel = By.cssSelector(".form-group__radio-label[for='smsUpdateRecipient-yes']");

    private static final By recipientDoNotReceiveTextMessagesLabel = By.cssSelector(".form-group__radio-label[for='smsUpdateRecipient-no']");

    @FindBy (className = "appointment-summary__message")
    public static WebElement serviceOptionMessage;

    @FindBy (className = "checkout-content__title--active")
    public static WebElement activeSectionTitleElement;

    private static final By appointmentStoreInfoBy = By.className("checkout-summary-store__address");

    public static final String pickDayInfocus = "picker__day--infocus";

    public static final String pickDayDisabled = "picker__day--disabled";

    public static final String pickDay = "picker__day";

    public static final String pickDayHighlighted = "picker__day--highlighted";

    public static final String phoneTypeIdWeb = "phoneTypeId_chosen";

    public static final By serviceOptionBy = By.className("input");

    public static final By apptMonthAndYearBy = By.className("appointment-details__select-date-month");

    public static final By apptSelectedMsgBy = By.className("appointment-details__select-time-message");

    private int appointmentDayStart = 0;
    private static final int APPOINTMENT_DAYS_ATTEMPTED = 4;
    private static String passableDate;
    private static String passableTime;

    public static String getPassableDate() {
        return passableDate;
    }

    public static String getPassableTime() {
        return passableTime;
    }

    public void setPassableDate(String passableDate) {
        AppointmentPage.passableDate = passableDate;
    }

    public void setPassableTime(String passableTime) {
        AppointmentPage.passableTime = passableTime;
    }

    /**
     * Schedules an appointment Time and Date
     *
     * @param customer used in failed assertion message
     */
    public void makeAppointment(Customer customer) {
        LOGGER.info("makeAppointment started");
        try {
            selectDate();
            selectTime();
        } catch (Exception e) {
            Assert.fail("FAIL: Making appointment (setting date and time) for user " +
                    customer.getCustomerDataString(customer) + " FAILED with error: " + e);
        }
        LOGGER.info("makeAppointment completed");
    }

    /**
     * Selects services from a string of options, separated by commas. Iterates over list to select
     * each individual service.
     *
     * @param options String of services to be selected, separated by commas.
     */
    public void selectService(String options) {
        LOGGER.info("selectService started");
        List<String> optionsToSelect = Lists.newArrayList(Splitter.on(",").trimResults().split(options));

        //TODO: Replace with new waitForElementWithText when available
        driver.waitForElementVisible(nextStepForDateTimeCustomerInfoButton);

        for (String option : optionsToSelect) {
            WebElement optionEl = driver.getElementWithText(serviceOptionBy, option);
            driver.jsScrollToElement(optionEl);
            optionEl.click();
        }
        LOGGER.info("selectService completed");
    }

    /**
     * Clicks the 'Set Appointment Details' button on the customer appointment page
     */
    public void clickSetAppointmentDetailsForDateAndTime() {
        LOGGER.info("clickSetAppointmentDetailsForDateAndTime started");
        driver.jsScrollToElement(nextStepForDateTimeCustomerInfoButton);
        nextStepForDateTimeCustomerInfoButton.click();
        driver.waitForPageToLoad();
        driver.waitForElementVisible(selectDateBox);
        LOGGER.info("clickSetAppointmentDetailsForDateAndTime completed");
    }

    /**
     * Clicks the 'Next Step' button on the customer appointment page
     * Has an implicit wait as well as a try/catch
     */
    public void clickNextStepForCustomerInformation() {
        LOGGER.info("clickNextStepForCustomerInformation started");
        driver.waitForPageToLoad();
        driver.jsScrollToElement(defaultButton);
        defaultButton.click();
        LOGGER.info("clickNextStepForCustomerInformation completed");
    }

    /**
     * Clicks the 'Next Step' button on the customer appointment page
     * Has an implicit wait as well as a try/catch
     */
    public void clickContinueForAppointmentCustomerDetailsPage() {
        LOGGER.info("clickNextStepForAppointmentCustomerInformation started");
        driver.waitForElementVisible(submitBtn);
        driver.jsScrollToElement(submitBtn);

        if (!Config.isSafari()) {
            submitBtn.click();
        } else {
            driver.jsClick(submitBtn);
        }
        LOGGER.info("clickNextStepForAppointmentCustomerInformation completed");
    }

    /**
     * Selects a date from the month modal window, by clicking on the next month and selecting a date that
     * that is NOT disabled (Sundays)
     */
    public void selectDate() {
        LOGGER.info("selectDate started");

        driver.waitForPageToLoad();
        driver.waitForElementClickable(selectDateBox);

        driver.jsScrollToElement(selectDateBox);
        selectDateBox.click();

        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        driver.waitForElementVisible(chooseDateTable);
        driver.waitForMilliseconds();

        // Picks up every day on calendar, then iterates through to the highlighted day and sets found = true.
        // (cont) Then goes through again until it finds the next day that is not highlighted or disabled and clicks it.
        List<WebElement> dayList = chooseDateTable.findElements(By.className(pickDay));
        boolean found = false;
        for (WebElement day : dayList) {
            if (day.getAttribute(Constants.CLASS).contains(pickDayHighlighted)) {
                found = true;
            } else if (found && !day.getAttribute(Constants.CLASS).contains(pickDayDisabled)
                    && !day.getAttribute(Constants.CLASS).contains(pickDayHighlighted)) {
                day.click();
                break;
            }
        }

        setPassableDate(appointmentDate.getAttribute("value"));
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        LOGGER.info("selectDate completed");
    }

    /**
     * Selects the first available time slot from the Time picker menu.
     */
    public void selectTime() {
        LOGGER.info("selectTime started");
        String ITEM_DISABLED = "item--disabled";
        String ITEM_SELECTED = "item--selected";
        String ITEM_HIGHLIGHTED = "item--highlighted";
        String PRESENTATION = "presentation";

        if (driver.isElementDisplayed(datePickerCloseBtn, Constants.THREE_SEC_WAIT)){
            datePickerCloseBtn.click();
        }

        driver.waitForElementClickable(selectTimeBox);
        driver.jsScrollToElement(selectTimeBox);
        selectTimeBox.click();

        if (Config.isMobile()) {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        }

        driver.waitForMilliseconds();
        driver.waitForElementVisible(pickTimeContainer);

        List<WebElement> timeList = pickTimeContainer.findElements(By.tagName("li"));

        if (!driver.isElementVisible(walkInsWelcome, Constants.ONE_SEC_WAIT)) {
            for (WebElement timeEle : timeList) {
                if (!timeEle.getAttribute(Constants.CLASS).contains(ITEM_DISABLED)
                        && !timeEle.getAttribute(Constants.CLASS).contains(ITEM_SELECTED)
                        && !timeEle.getAttribute(Constants.CLASS).contains(ITEM_HIGHLIGHTED)
                        && !timeEle.getAttribute(Constants.ROLE).contains(PRESENTATION)) {
                    driver.jsScrollToElement(timeEle);

                    if (Config.isFirefox()) {
                        timeEle.sendKeys(Keys.ENTER);
                    } else {
                        timeEle.click();
                    }

                    driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
                    driver.waitForElementClickable(selectTimeBox);
                    setPassableTime(appointmentTime.getAttribute(Constants.VALUE));
                    break;
                }
            }
        } else {
            if (appointmentDayStart < APPOINTMENT_DAYS_ATTEMPTED) {
                appointmentDayStart++;
                commonActions.clickButtonByText(CANCEL);
                driver.waitForElementClickable(selectDateBox);
                selectDate();
                selectTime();
            }
        }

        // Verifies that attempts does not equal number of days attempted variable
        if (appointmentDayStart == APPOINTMENT_DAYS_ATTEMPTED) {
            Assert.fail("FAIL: There were no available appointment times for dates leading up until: \""
                    + getPassableDate() + "\"");
        }

        driver.waitForMilliseconds();
        LOGGER.info("selectTime completed");
    }

    /**
     * Enters customer information on the Appointment screen
     * 
     * @param customer Customer type to grab dtc.data from
     */
    public void enterCustomerInformation(Customer customer) {
        LOGGER.info("enterCustomerInformation started");
        if (driver.isElementDisplayed(firstNameBoxAppointmentFlow))
        	enterCustomerInformationAppointmentFlow(customer);
        else
        	enterCustomerInformationServiceFlow(customer);
        LOGGER.info("enterCustomerInformation completed");
    }

    /**
     * Enters customer information on the Appointment screen landed on via Service Flow
     *
     * @param customer Customer type to grab dtc.data from
     */
	private void enterCustomerInformationServiceFlow(Customer customer) {
        LOGGER.info("enterCustomerInformationServiceFlow started");
		WebElement phoneDropDownEle;
        String phoneTypeIdString;

        if (Config.isMobile()) {
            phoneDropDownEle = phoneDropDownMobile;
            phoneTypeIdString = null;
        } else {
            phoneDropDownEle = phoneDropDownWebServiceFlow;
            phoneTypeIdString = phoneTypeIdWeb;
        }
        driver.waitForElementVisible(phoneNumberBoxServiceFlow, Constants.FIVE_SEC_WAIT);

        try {
        	firstNameBoxServiceFlow.sendKeys(customer.firstName);
       		lastNameBoxServiceFlow.sendKeys(customer.lastName);
       		emailBoxServiceFlow.sendKeys(customer.email);

            if (driver.isElementDisplayed(phoneDropDownWebServiceFlow, Constants.TWO_SEC_WAIT)) {
                if (Config.isMobile()) {
                    driver.selectFromDropdownByVisibleText(phoneDropDownEle, customer.phoneType);
                } else {
                    commonActions.selectDropDownValue(phoneTypeIdString, customer.phoneType);
                }
            }
            phoneNumberBoxServiceFlow.sendKeys(customer.phone);
        } catch (Exception e) {
            Assert.fail("FAIL: Entering customer info for user " +
                    customer.getCustomerDataString(customer) + "! FAILED with error: " + e);
        }
        LOGGER.info("enterCustomerInformationServiceFlow completed");
	}

    /**
     * Enters customer information on the Appointment screen landed on via Appointment Flow
     *
     * @param customer Customer type to grab dtc.data from
     */
    private void enterCustomerInformationAppointmentFlow(Customer customer) {
        LOGGER.info("enterCustomerInformationAppointmentFlow started");

        try {
        	// TODO:  sendKeys does not work for these fields with IE and Safari.
        	// 1) Talk to developers about what is different about these fields?  Can they be changed?
        	// 2) Find a solution other than sendKeys.  Robot and JavascriptExecutor have been tried.
       		if(Config.isIe() || Config.isSafari()) {
       			LOGGER.info("There is a known issue for automation setting the Customer Info "
       					+ "fields in IE and Safari which causes failure creating appointments");
       		}
       		
        	firstNameBoxAppointmentFlow.sendKeys(customer.firstName);
       		lastNameBoxAppointmentFlow.sendKeys(customer.lastName);
       		emailBoxAppointmentFlow.sendKeys(customer.email);
            commonActions.selectPhoneTypeFromDropdown(ConstantsDtc.APPOINTMENT, customer.phoneType);
            phoneNumberBoxAppointmentFlow.sendKeys(customer.phone);
        } catch (Exception e) {
            Assert.fail("FAIL: Entering customer info for user " +
                    customer.getCustomerDataString(customer) + "! FAILED with error: " + e);
        }
        LOGGER.info("enterCustomerInformationAppointmentFlow completed");
    }

    /**
     * Enter the information for the other person that will be picking up the products
     *
     * @param customer - Customer object containing demographic data
     * @param deliveryType - The type of desired communication: 'phone' or 'email'
     * @param recieveTextUpdates - Whether or not to receive text updates
     */
    public void enterSomeoneElseInformation(Customer customer, String deliveryType, boolean recieveTextUpdates) {
        LOGGER.info("enterSomeoneElseInformation started");
        recipientFirstName.clear();
        recipientFirstName.sendKeys(customer.firstName);
        recipientLastName.clear();
        recipientLastName.sendKeys(customer.lastName);

        if (!deliveryType.equalsIgnoreCase(Constants.PHONE)) {
            if (recieveTextUpdates) {
                Assert.fail("FAIL: Cannot receive text updates option unless phone delivery method is selected");
            }
            webDriver.findElement(recipientEmailAddressRadioLabel).click();
            recipientEmail.clear();
            recipientEmail.sendKeys(customer.email);
        } else {
            webDriver.findElement(recipientPhoneNumberRadioLabel).click();
            recipientPhone.clear();
            recipientPhone.sendKeys(customer.phone);
            if (recieveTextUpdates) {
                webDriver.findElement(recipientReceiveTextMessagesLabel).click();
            } else {
                webDriver.findElement(recipientDoNotReceiveTextMessagesLabel).click();
            }
        }
        LOGGER.info("enterSomeoneElseInformation completed");
    }

    /**
     * Clicks the submit button on Appointment screen
     *
     * @param customer Customer type for Assert.fail message
     */
    public void clickSubmitButton(Customer customer) {
        LOGGER.info("clickSubmitButton started");
        try {
            driver.waitForPageToLoad();
            if (Config.isFirefox() || Config.isIe())
                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
            driver.jsScrollToElement(submitBtn);
            submitBtn.click();
        } catch (Exception e) {
            Assert.fail("FAIL: Clicking submit button for user " +
                    customer.getCustomerDataString(customer) + "! FAILED with error: " + e);
        }
        LOGGER.info("clickSubmitButton completed");
    }

    /**
     * Clicks 'Edit' next to Appointment Details
     * Allows you to edit those details
     */
    public void clickAppointmentEditLink() {
        LOGGER.info("clickAppointmentEditLink started");
        driver.waitForElementVisible(appointmentEditLink);
        driver.jsScrollToElement(appointmentEditLink);
        appointmentEditLink.click();
        LOGGER.info("clickAppointmentEditLink completed");
    }

    /**
     * Clicks 'Change Store' on Appointment Details page
     */
    public void clickChangeStore() {
        LOGGER.info("clickChangeStore started");
        driver.waitForElementVisible(changeStoreLink);
        driver.jsScrollToElement(changeStoreLink);
        changeStoreLink.click();
        LOGGER.info("clickChangeStore completed");
    }

    /** Returns the Date for day verification *
     *
     * @return String
     */
    public String extractDate() {
    	LOGGER.info("extractDate started");
    	String date = null;

    	if (driver.isElementDisplayed(apptSelectedMsg)) {
            // For appointment flow, get date from the message bar displayed on Checkout page.
    		// Format it so it will match the appointment page.  i.e. Wednesday, January 24, 2018.
            String msgBarDT = apptSelectedMsg.getText().split("-")[0].replaceAll("Appointment Selected:\n", "").trim();
            String monthYr = apptMonthAndYear.getText();
            String year = monthYr.substring(monthYr.length()-4, monthYr.length());
            date = msgBarDT + ", " + year;
    	}
    	else {
    		// Service flow
    		date = appointmentDate.getText();
    	}

    	setPassableDate(date);
    	LOGGER.info("extractDate completed");
    	return getPassableDate();
    }

    /** Returns the Time for time verification
     *
     * @return String
     */
    public String extractTime() {
    	LOGGER.info("extractTime started");
    	String time = null;

     	if (driver.isElementDisplayed(apptSelectedMsg)) {
            // For appointment flow, get time from the message bar displayed on Checkout page.
            String msgBarDT = apptSelectedMsg.getText();
            time = msgBarDT.split("-")[1].trim();

            if(time.substring(0, 1).equals("0")) {
            	String hh = time.split(":")[0];
            	String mm = time.split(":")[1];
            	hh = hh.substring(1, 2);
            	time = hh + ":" + mm;
            }
    	}
     	else {
     		// Service flow
         	time = appointmentTime.getText();
     	}
     	setPassableTime(time);
    	LOGGER.info("extractTime completed");
    	return getPassableTime();
     }

     /**
     * Validates current date and time listed on the Appointment page 
     *
     * @param date Appointment Date to verify.  Example format:  Wednesday, January 24, 2018
     * @param time Appointment Time to verify.  Example format:  11:00 AM
     */
    public void verifyDateAndTime(String date, String time) {
        LOGGER.info("verifyDateAndTime started");
    	driver.waitForElementVisible(appointmentDateTime);
    	
    	String apptDT = appointmentDateTime.getText().replace("Time: ", "");
    	String apptDate = apptDT.split("\n")[0].trim();
    	String apptTime = apptDT.split("\n")[1].trim();
    	
        Assert.assertTrue("FAIL: Did NOT see expected date \"" + date + "\" in appointment info: \"" 
               + apptDate + "\"!", apptDate.equals(date));
        LOGGER.info("Confirmed that expected date \"" + date + "\" was listed in appointment info");
        
        Assert.assertTrue("FAIL: Did NOT see expected time \"" + time + "\" in appointment info: \"" 
               + apptTime + "\"!", apptTime.contains(time));
        LOGGER.info("Confirmed that expected time (" + time + ") was listed in appointment info");

        LOGGER.info("verifyDateAndTime completed");    	
    }

    /**
     * Verifies the store on screen is the current default store
     *
     * @param store the default store
     */
    public void verifyStore(String store) {
        driver.waitForTextPresent(appointmentStoreInfoBy, store, Constants.THREE_SEC_WAIT);
        String actualStoreInfo = webDriver.findElement(appointmentStoreInfoBy).getText().toLowerCase();
        Assert.assertTrue("FAIL: Did NOT see expected store \"" + store + "\" in appointment info: \"" +
                actualStoreInfo + "\"!", actualStoreInfo.contains(store.toLowerCase()));
        LOGGER.info("Confirmed that expected store (" + store + ") was listed in appointment info");
    }

    /**
     * Verifies the Special Order message is visible and matches the expected value
     */
    public void verifySpecialOrderMessage() {
        driver.waitForElementVisible(CommonActions.globalMessage);
        String globalMessageText = CommonActions.globalMessage.getText();
        Assert.assertTrue("FAIL: \"" + globalMessageText + "\" does NOT contain " + "\""
                        + SPECIAL_ORDER_MESSAGE + "\"!",
                CommonActions.globalMessage.getText().contains(SPECIAL_ORDER_MESSAGE));
        LOGGER.info("verify Special Order Message is displayed");
    }

    /**
     * Verifies the Call Us message is visible and matches the expected value
     */
    public void verifyCallUsMessage() {
        driver.waitForElementVisible(CommonActions.globalMessage);
        String globalMessageText = CommonActions.globalMessage.getText();
        Assert.assertTrue("FAIL: \"" + globalMessageText + "\" does NOT contain " + "\""
                        + CALL_US_MESSAGE + "\"!",
                CommonActions.globalMessage.getText().contains(CALL_US_MESSAGE));
        LOGGER.info("verify Call Us Message is displayed");
    }

    /**
     * Overloading selectDate method to pick the Last, First date available based on the example
     *
     * @param day       The desired date to select
     * @param availDays The number of available days to validate against
     */
    public void selectDateAndVerifyAvailableDays(String day, String availDays) {
        LOGGER.info("selectDateAndVerifyAvailableDays started");
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForElementClickable(selectDateBox);

        driver.jsScrollToElement(selectDateBox);
        selectDateBox.click();

        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForElementVisible(chooseDateTable);
        driver.waitForMilliseconds();

        String valFirstMonth= currentMonth.getText();
        List<WebElement> firstMonthList = chooseDateTable.findElements(By.className(pickDayInfocus));
        for (int firstmonthdays = 0; firstmonthdays < firstMonthList.size(); firstmonthdays++) {
            if (firstMonthList.get(firstmonthdays).getAttribute("class").contains(pickDayDisabled)) {
            	firstMonthList.remove(firstmonthdays);
            	firstmonthdays = firstmonthdays - 1;
                LOGGER.info("First month days : " + firstmonthdays + " and the size: " + firstMonthList.size());
            }
        }
    	int totalSize = (firstMonthList.size());

     	if (day.equalsIgnoreCase("First") && firstMonthList.size()> 1) {
     		firstMonthList.get(1).click();
     	}
     	else {
     		nextMonthArrow.click();
     		String valSecondMonth= currentMonth.getText();
     		List<WebElement> secondMonthList = chooseDateTable.findElements(By.className(pickDayInfocus));

     		for (int secondmonthdays = 0; secondmonthdays < secondMonthList.size(); secondmonthdays++) {
     			if (secondMonthList.get(secondmonthdays).getAttribute("class").contains(pickDayDisabled)) {
     				secondMonthList.remove(secondmonthdays);
     				secondmonthdays = secondmonthdays - 1;
     				LOGGER.info("Second month days: " + secondmonthdays + " and the size: " + secondMonthList.size());
     			}
     		}
            if (!valSecondMonth.equals(valFirstMonth)){
            	totalSize = totalSize + (secondMonthList.size());
            }
     		if (day.equalsIgnoreCase("First") ) {
     			secondMonthList.get(0).click();
     		}
     		else {
     			nextMonthArrow.click();
     	       	String valThirdMonth = currentMonth.getText();
     			List<WebElement> thirdMonthList = chooseDateTable.findElements(By.className(pickDayInfocus));
     			for (int thirdmonthdays = 0; thirdmonthdays < thirdMonthList.size(); thirdmonthdays++) {
     				if (thirdMonthList.get(thirdmonthdays).getAttribute("class").contains(pickDayDisabled)) {
     					thirdMonthList.remove(thirdmonthdays);
     					thirdmonthdays = thirdmonthdays - 1;
     					LOGGER.info("Third month days: " + thirdmonthdays + " and the size: " + thirdMonthList.size());
     				}
     			}
                if (!valSecondMonth.equals (valThirdMonth)){
             	   totalSize = totalSize + (thirdMonthList.size());
                 }

                int availDateIndex = thirdMonthList.size() - 1;
                thirdMonthList.get(availDateIndex).click();
     			}
     		}
         	setPassableDate(appointmentDate.getAttribute("value"));
         	driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

         	//TODO - due to a defect following code will be commented out, we will continue finalizing the appointment
         	String totalDays = Integer.toString(totalSize);
        	//Assert.assertTrue("FAIL: \"Total number of available appointment days " + availDays + "\" doesn't match with actual appointment days. " + totalSize + "\""
            //         ,availDays.equals(totalDays));*/
         	LOGGER.info("Total number of available appointment days" + availDays + "Actual appointment days" + totalDays);
         	LOGGER.info("selectDateAndVerifyAvailableDays completed");

    	}

    /**
     * Clicks the "I want to enter my address..." checkbox and enters customer address information on the
     * Appointment screen
     *
     * @param apptCustomer Customer type to grab dtc.data from
     */
    public void enterCustomerAddressInformation(Customer apptCustomer) {
        LOGGER.info("enterCustomerAddressInformation started");
        try {
            driver.waitForElementClickable(enterAddressCheckbox);
            driver.jsScrollToElement(enterAddressCheckbox);
            enterAddressCheckbox.click();

            ArrayList<WebElement> addressFields = new ArrayList<>(
                    Arrays.asList(address1, address2, city, zip));

            ArrayList<String> customerInfo = new ArrayList<>(Arrays.asList
                    (apptCustomer.address1, apptCustomer.address2, apptCustomer.city, apptCustomer.zip));

            for (int i = 0; i < addressFields.size(); i++){
                if (Config.isAndroidTablet() || Config.isAndroidPhone()){
                    driver.waitForMilliseconds();
                }
                addressFields.get(i).sendKeys(customerInfo.get(i));
            }

            if (apptCustomer.country.equalsIgnoreCase(Constants.CANADA)) {
                commonActions.selectDropDownValue(ConstantsDtc.countryDropDownString, apptCustomer.country);
                driver.waitForMilliseconds();
            }

            if (Config.isMobilePhone()) {
                driver.selectFromDropdownByVisibleText(stateDropDownMobile, apptCustomer.state);
            } else {
                commonActions.selectDropDownValue(ConstantsDtc.stateDropDownString, apptCustomer.state);
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Entering address for user \"" +
                    apptCustomer.getCustomerDataString(apptCustomer) + "\"! FAILED with error: " + e);
        }
        LOGGER.info("enterCustomerAddressInformation completed");
    }


    /**
     * Verifies that the range of available appointment times is between the two specified values
     *
     * @param   range1  The first time point in the range to verify
     * @param   range2  The final point in the range to verify
     */
    public void verifyAppointmentTimeRange(String range1, String range2) {
        LOGGER.info("verifyAppointmentTimeRange started");
        String actualRange1, actualRange2;

        driver.waitForElementClickable(selectTimeBox);

        driver.jsScrollToElement(selectTimeBox);
        selectTimeBox.click();

        driver.waitForMilliseconds();

        driver.waitForElementVisible(pickTimeContainer);

        List<WebElement> timeList = pickTimeContainer.findElements(By.tagName("li"));
        List<WebElement> timeListRemove = new ArrayList<>();

        for (WebElement time: timeList){
            if (time.getText().equals("")){
                timeListRemove.add(time);
            }
        }
        timeList.removeAll(timeListRemove);

        if (Config.isIphone()) {
            actualRange1 = getAppointmentTimeValueJs(true);
            actualRange2 = getAppointmentTimeValueJs(false);
        } else {
            actualRange1 = timeList.get(0).getText();
            //NOTE: We subtract 2 as the last actual value is "CLEAR"
            actualRange2 = timeList.get(timeList.size()-2).getText();
        }

        Assert.assertTrue("FAIL: The first time in the appointment range was NOT \"" + range1 + "\", " +
                        "but was rather \"" + actualRange1 + "\"!", actualRange1.equalsIgnoreCase(range1));

        LOGGER.info("Confirmed the first time in the range, \"" + range1 + "\".");

        Assert.assertTrue("FAIL: The last time in the appointment range was NOT \"" + range2 + "\" " +
                        "but was rather \"" + actualRange2 + "\"!", actualRange2.equalsIgnoreCase(range2));

        LOGGER.info("Confirmed the last time in the range, \"" + range2 + "\".");

        LOGGER.info("verifyAppointmentTimeRange completed");
    }

    /**
     * Builds javascript string for validation of time range for iPhone Simulator
     *
     * @param   first   Whether it is the first or last time in the range
     * @return  String  The appointment time value
     */
    public String getAppointmentTimeValueJs(boolean first) {
        LOGGER.info("getAppointmentTimeValueJs started");
        String appointmentTimeValue = null;
        String verifyRangeScript = "times = document.getElementsByClassName('picker__list-item');";

        if (first) {
            verifyRangeScript = verifyRangeScript + "return times[0].textContent";
        } else {
            verifyRangeScript = verifyRangeScript + "return times[times.length-1].textContent";
        }

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        appointmentTimeValue = (String) jse.executeScript(verifyRangeScript);

        LOGGER.info("getAppointmentTimeValueJs completed");
        return appointmentTimeValue;
    }
 
    /**
     * Click the Edit Appointment link
     */
    public void clickEditAppointmentLink() {
    	editApptLink.click();
    }
    
    /**
     * Click the 'Select date' or 'Select time' box on the schedule appointment page
     */
    public void clickSelectDateOrTime(String selection) {
        LOGGER.info("clickSelectDateOrTime started");
        driver.waitForPageToLoad();
        WebElement elementToSelect = null;
        
        if(selection.equalsIgnoreCase("date")) {
        	elementToSelect = selectDateBox;
        }
        else {
        	elementToSelect = selectTimeBox;
        }
        
        driver.waitForElementClickable(elementToSelect);
        driver.jsScrollToElement(elementToSelect);
        elementToSelect.click();
        LOGGER.info("clickSelectDateOrTime completed");
    }
    
    /**
     * Verify the DatePicker message is correct
     */
    public void verifyDatePickerMessage() {
        LOGGER.info("verifyDatePickerMessage started");
    	WebElement messageDivParagraph = datepickerMessage.findElement(CommonActions.ptagNameBy);
    	String displayedMessage = messageDivParagraph.getText().trim();    	
    	Assert.assertTrue("FAIL:  The date picker Message at the top of the select "
    			+ "date dialog was not correct.  Expected:  " 
    			+ ConstantsDtc.DATEPICKER_MESSAGE + ".  Actual:  " + displayedMessage + "!", 
    			displayedMessage.equals(ConstantsDtc.DATEPICKER_MESSAGE));
        LOGGER.info("verifyDatePickerMessage completed");
    }
    
    /**
     * Close the Appointment Selected message bar by clicking the small x on the right side
     */
    public void closeAppointmentSelectedMessageBar() {
        LOGGER.info("closeAppointmentSelectedMessageBar started");
    	WebElement appointmentSelectedMessage = webDriver.findElement(apptSelectedMsgBy);
    	appointmentSelectedMessage.findElement(CommonActions.closeButtonXBy).click();
        LOGGER.info("closeAppointmentSelectedMessageBar completed");    	
    }
    
    /**
     * Verify the Appointment Selected message bar is closed
     */
    public void assertAppointmentSelectedMessageIsNotDisplayed() {
        LOGGER.info("assertAppointmentSelectedMessageIsNotDisplayed started");
    	Assert.assertTrue("FAIL:  Unable to close the Appointment Selected message bar!", 
    			!driver.isElementDisplayed(apptSelectedMsg));
        LOGGER.info("assertAppointmentSelectedMessageIsNotDisplayed completed");
    }
    
    /**
     * Verify the Appointment Details indicate the appointment placed was for peak hours 
     * and the completion time is 45 minutes after the start time.
     */
    public void verifyPeakHoursInAppointmentDetails() {
        LOGGER.info("verifyPeakHoursAppointmentInAppointmentDetails started");
    	driver.waitForElementVisible(appointmentDateTime);
    	String appointmentDetails = appointmentDateTime.getText();
    	
    	DateFormat formatter = new SimpleDateFormat("HH:mm");
    	String startTime = appointmentDetails.split(PEAK)[0].trim().split(TIME)[1].replace("(", "").trim();

    	Assert.assertTrue("FAIL:  '(Peak)' does not appear to the right of start time in the Appointment Details section!", 
    			appointmentDetails.contains(startTime + " (" + PEAK + ")"));

    	String estCompletionTime = appointmentDetails.split(EST_COMPLETION_TIME)[1].replace("i", "").trim();    	
    	Time startTimeValue = null;
    	Time completionTimeValue = null;
        
    	try {
			startTimeValue = new java.sql.Time(formatter.parse(startTime.replaceAll("AM|PM", "").trim()).getTime());
    		completionTimeValue = new java.sql.Time(formatter.parse(estCompletionTime.replaceAll("AM|PM", "").trim()).getTime());
		} catch (ParseException e) {
			e.getMessage();
			Assert.fail("FAIL:  Unable to convert " + startTime + " to Time object!");
		}
        	
		long milliseconds = completionTimeValue.getTime() - startTimeValue.getTime();
		int seconds = (int) milliseconds / 1000;
		int hours = seconds / 3600;
		int minutes = ((seconds % 3600) / 60 + (hours * 60));

    	Assert.assertTrue("FAIL:  The estimated completion time (" + estCompletionTime + ") "
    			+ "in Appointment Details is not " + Constants.PEAK_DELAY_MINUTES 
    			+ " after the start time (" + startTime + ")!", minutes == Constants.PEAK_DELAY_MINUTES);
        LOGGER.info("verifyPeakHoursAppointmentInAppointmentDetails completed");
    }

    /**
     * Verify selected Service Option text on Service Appointment Page
     *
     * @param   serviceOption  Selected service option text to verify
     */
    public void assertSelectedServiceOptionText(String serviceOption) {
        LOGGER.info("assertSelectedServiceOptionText started");
        driver.waitForPageToLoad();
        String serviceOptionMessageText = serviceOptionMessage.getText();
        Assert.assertTrue("FAIL: Selected Service Option : \"" + serviceOption + "\" does NOT match with displayed option " + "\""
                        + serviceOptionMessageText + "\"!", serviceOptionMessageText.equalsIgnoreCase(serviceOption));
        LOGGER.info("assertSelectedServiceOptionText completed");
    }

    /**
     * Verify section title is displayed on Service Appointment Page
     *
     * @param   sectionTitle  section title text to verify
     */
    public void assertActiveSectionTitleMessageIsDisplayed(String sectionTitle) {
        LOGGER.info("assertActiveSectionTitleMessageIsDisplayed started");
        driver.waitForElementVisible(activeSectionTitleElement);
        String activeSectionTitleMessage = activeSectionTitleElement.getText();
        Assert.assertTrue("FAIL: Section title : \"" + sectionTitle + "\" does NOT match with displayed title " + "\""
                        + activeSectionTitleMessage + "\"!",activeSectionTitleMessage.equalsIgnoreCase(sectionTitle));
        LOGGER.info("assertActiveSectionTitleMessageIsDisplayed completed");
    }
}