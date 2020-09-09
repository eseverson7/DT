package ovc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

/**
 * Created by eseverson on 6/27/2017.
 */
public class AppointmentPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(AppointmentPage.class.getName());
    private CommonActions commonActions;
    private OvcData ovcData, featureData;
    private ParentElementsPage parentElementsPage;

    public AppointmentPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
        ovcData = new OvcData();
        parentElementsPage = new ParentElementsPage(driver);
    }

    @FindBy(xpath = "//textarea[@ng-model='form.notes']")
    public static WebElement apptNotesField;

    @FindBy(xpath = "//input[@ng-model='form.firstName']")
    public static WebElement apptFirstNameField;

    @FindBy(xpath = "//input[@ng-model='form.lastName']")
    public static WebElement apptLastNameField;

    @FindBy(xpath = "//input[@ng-model='form.email']")
    public static WebElement apptEmailField;

    @FindBy(xpath = "//input[@ng-model='form.phone']")
    public static WebElement apptPhoneField;

    @FindBy(id = "appointmentDate")
    public static WebElement selectDateField;

    @FindBy(id = "modifyAppointmentDate")
    public static WebElement modifyDateField;

    @FindBy(className = "apntmentSlots")
    public static WebElement apptSegmentTabsContainer;

    private static final By nextMonthArrowBy = By.cssSelector("th.next.available");

    private static final By availableDaysBy = By.cssSelector("td.available");

    private static final By dateTableBy = By.className("calendar-table");

    private static final By reasonDropdownBy = By.xpath("//select[contains(@ng-options, 'reason.reasonCodeId')]");

    private static final By timeSlotsBy = By.className("slotTime");

    private static final By labelBy = By.tagName("LABEL");

    //TODO: Refactor needed if we plan to support Firefox browser in near future 
    private static final By eveningTabBy = By.linkText("Evening");

    private static final By availableApptTimesBy =
            By.xpath("//div[@class=\"timeSlotsWrapper\"]//label[not(contains(@class, \"block\"))]");

    private static List<String> apptTabNameList = new ArrayList<>(Arrays.asList(ConstantsOvc.MORNING,
            ConstantsOvc.AFTERNOON, ConstantsOvc.EVENING));

    private static List<WebElement> apptSegTabsList;

    private static String daysTurnedOff = "off";

    private static String selectedApptDate = null;

    private static String selectedApptTime = null;

    private static String rawApptTime = null;

    public static String getSelectedApptDate() {
        return selectedApptDate;
    }

    public static String getSelectedApptTime() {
        return selectedApptTime;
    }

    public static String getRawApptTime() {
        return rawApptTime;
    }

    private static String modifiedApptDate;

    private static String modifiedApptTime;

    public static String getModifiedApptDate() {
        return modifiedApptDate;
    }

    public static String getModifiedApptTime() {
        return modifiedApptTime;
    }

    private static String defaultApptDate;

    public static String getDefaultApptDate() {
        return defaultApptDate;
    }

    public static final String REASON_ERROR = "Please Select reason";
    public static final String AVAILABLE_TIME_ERROR = "Please provide start time";
    public static final String CUSTOMER_ERROR = "Please provide valid email or phone number";

    /**
     * Selects the specified option from the Appt. Reason dropdown
     *
     * @param apptReason String for the option to be selected from apptReason dropdown
     */
    public void selectAppointmentReason(String apptReason) {
        LOGGER.info("selectAppointmentReason started w/ appointment reason: '" + apptReason + "'");
        driver.waitForElementVisible(reasonDropdownBy);
        driver.waitForElementNotVisible(dateTableBy);
        driver.selectFromDropdownByVisibleText(reasonDropdownBy, apptReason);
        LOGGER.info("selectAppointmentReason completed, selected appointment reason: '" + apptReason + "'");
    }

    /**
     * Selects and expands the Appointment Reason dropdown for visual validation
     */
    public void expandAppointmentReasonDropdown() {
        LOGGER.info("expandAppointmentReasonDropdown started");
        driver.waitForElementVisible(reasonDropdownBy);
        driver.waitForElementNotVisible(dateTableBy);
        webDriver.findElement(reasonDropdownBy).click();
        LOGGER.info("expandAppointmentReasonDropdown completed");
    }

    /**
     * Selects the first available Appt. time for the currently selected date. Looks for the first available Appt.
     * Day segment (Morning, Afternoon, Evening) tab, then the first available time slot for that tab
     */
    public void selectFirstAvailableAppointmentTime() {
        LOGGER.info("selectFirstAvailableAppointmentTime started");
        driver.waitForElementVisible(selectDateField);
        apptSegTabsList = apptSegmentTabsContainer.findElements(CommonActions.liTagBy);

        WebElement firstSegTab = apptSegTabsList.get(0);
        firstSegTab.click();
        String timeSegment = capitalize(firstSegTab.getText().toLowerCase().trim());
        WebElement segmentTimeContainer = webDriver.findElement(By.id(timeSegment));
        List<WebElement> segmentApptTimes = segmentTimeContainer.findElements(timeSlotsBy);
        WebElement firstApptTime = segmentApptTimes.get(0);
        firstApptTime.click();
        setApptTime(firstSegTab.getText(), firstApptTime.getText());
        LOGGER.info("selectFirstAvailableAppointmentTime completed");
    }

    /**
     * Selects the first available Evening Appt. time for the currently selected date. Looks for the first available Evening Appt.
     * Day segment Evening tab, then the first available time slot for that tab
     */
    public void selectFirstEveningAvailableAppointmentTime() {
        LOGGER.info("selectFirstEveningAvailableAppointmentTime started");
        driver.waitForElementVisible(selectDateField);
        apptSegTabsList = apptSegmentTabsContainer.findElements(CommonActions.liTagBy);
        
        WebElement firstSegTab = apptSegTabsList.get(1);
        firstSegTab.click();
        String timeSegment = capitalize(firstSegTab.getText().toLowerCase().trim());
        WebElement segmentTimeContainer = webDriver.findElement(By.id(timeSegment));
        List<WebElement> segmentApptTimes = segmentTimeContainer.findElements(timeSlotsBy);
        WebElement firstApptTime = segmentApptTimes.get(0);
        firstApptTime.click();
        setApptTime(firstSegTab.getText(), firstApptTime.getText());
        
        LOGGER.info("selectFirstEveningAvailableAppointmentTime completed");
    }


    /**
     * Sets selectedApptTime to the one chosen, in addition to adding the appropriate suffix for AM vs PM based on
     * appointment day segment tab selected
     *
     * @param dayTimeSegmentTab String containing Morning, Afternoon, or Evening
     * @param chosenApptTime    String containing the chosen appt time (xx:xx)
     */
    private void setApptTime(String dayTimeSegmentTab, String chosenApptTime) {
        LOGGER.info("setApptTime started");
        String apptTimeDayPeriod;
        if (dayTimeSegmentTab.equalsIgnoreCase(ConstantsOvc.MORNING)) {
            apptTimeDayPeriod = Constants.DAY_AM;
        } else {
            apptTimeDayPeriod = Constants.DAY_PM;
        }

        rawApptTime = chosenApptTime;
        selectedApptTime = commonActions.convertHourTime(chosenApptTime + " " + apptTimeDayPeriod, false);
        LOGGER.info("setApptTime completed");
    }


    /**
     * Enters OVC customer data into the required appt. information fields (fist name, last name, email, phone)
     *
     * @param feature The feature to pull customer data from
     * @throws Exception General exception
     */
    public void enterCustomerInformation(String feature) throws Exception {

        featureData = ovcData.getOvcData(feature);
        String customerId = featureData.CustomerId;
        String firstName = featureData.FirstName;
        String lastName = featureData.LastName;
        String email = featureData.Email;
        String phone = featureData.Phone;

        LOGGER.info("enterCustomerInformation started for customer with id: '" + customerId + "'");

        try {
            driver.waitForElementVisible(apptFirstNameField);

            List<WebElement> apptCustFields = new ArrayList<>(Arrays.asList
                    (apptFirstNameField, apptLastNameField, apptEmailField, apptPhoneField));

            List<String> custOvcValuesList = new ArrayList<>(Arrays.asList
                    (firstName, lastName, email, phone));

            for (int i = 0; i < apptCustFields.size(); i++) {
                if (!apptCustFields.get(i).getAttribute(Constants.VALUE).equalsIgnoreCase(custOvcValuesList.get(i))) {
                    apptCustFields.get(i).clear();
                    apptCustFields.get(i).click();
                    apptCustFields.get(i).sendKeys(custOvcValuesList.get(i));
                }
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Entering appointment info for OVC customer with id \"" +
                    customerId + "\" FAILED with error: " + e);
        }

        LOGGER.info("enterCustomerInformation completed for customer with id: '" + customerId + "'");
    }

    /**
     * Enters OVC AR customer data into the required appt. information fields (fist name, last name, email, phone)
     *
     * @param feature The feature to pull customer data from
     * @throws Exception General exception
     */
    public void enterArCustomerInformation(String feature) throws Exception {

        featureData = ovcData.getOvcData(feature);
        String firstName = featureData.ArFirstName;
        String lastName = featureData.ArLastName;
        String email = featureData.ArEmail;
        String phone = featureData.ArPhone;

        LOGGER.info("enterCustomerInformation started for AR customer with first name: '" + firstName + "'");


        try {
            driver.waitForElementVisible(apptFirstNameField);

            List<WebElement> apptCustFields = new ArrayList<>(Arrays.asList(apptLastNameField, apptEmailField, apptPhoneField));

            List<String> custOvcValuesList = new ArrayList<>(Arrays.asList(lastName, email, phone));

            for (int i = 0; i < apptCustFields.size(); i++) {
                if (!apptCustFields.get(i).getAttribute(Constants.VALUE).equalsIgnoreCase(custOvcValuesList.get(i))) {
                    apptCustFields.get(i).clear();
                    apptCustFields.get(i).click();
                    apptCustFields.get(i).sendKeys(custOvcValuesList.get(i));
                }
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Entering appointment info for OVC AR customer with first name \"" +
                    firstName + "\" FAILED with error: " + e);
        }

        LOGGER.info("enterCustomerInformation completed for customer with first name: '" + firstName + "'");
    }

    /**
     * Selects the 'Schedule' button for an OVC customer to create an appt.
     *
     * @param feature feature to pull customer from
     * @throws Exception General exception
     */
    public void selectScheduleButtonForCustomer(String feature) throws Exception {

        featureData = ovcData.getOvcData(feature);
        String customerId = featureData.CustomerId;

        LOGGER.info("selectScheduleButtonForCustomer started for customer with id: '" + customerId + "'");

        try {
            commonActions.selectButtonWithText(ConstantsOvc.APPOINTMENT, ConstantsOvc.SCHEDULE);
        } catch (Exception e) {
            Assert.fail("FAIL: Selecting 'Schedule' button for customer with id " +
                    customerId + "! FAILED with error: " + e);
        }

        LOGGER.info("selectScheduleButtonForCustomer completed for customer with id: '" + customerId + "'");
    }
    //region Verification methods

    /**
     * Verifies that at least on day segment (Morning, Afternoon, Evening) is displayed, and that for each tab
     * displayed there is at least one available appt. time
     */
    public void verifyAvailableTimesForCreateApptDaySegments() {
        LOGGER.info("verifyAvailableTimesForCreateApptDaySegments started");
        driver.waitForElementVisible(selectDateField);
        apptSegTabsList = apptSegmentTabsContainer.findElements(CommonActions.liTagBy);

        Assert.assertTrue("FAIL: The appointment time segments (Morning, Afternoon, Evening) did NOT "
                + "display for the selected date!", apptSegTabsList.size() > 0);

        for (WebElement segTab : apptSegTabsList) {
            verifyApptSegmentTabName(segTab);
            segTab.click();
            String timeSegment = capitalize(segTab.getText().toLowerCase().trim());
            WebElement segmentTimeContainer = webDriver.findElement(By.id(timeSegment));
            List<WebElement> segmentApptTimes = segmentTimeContainer.findElements(timeSlotsBy);
            Assert.assertTrue("FAIL: There were no appointment times available for time segment: '"
                    + segTab.getText() + "'!", segmentApptTimes.size() > 0);

        }
        LOGGER.info("verifyAvailableTimesForCreateApptDaySegments completed");
    }

    /**
     * Verifies a provided WebElement Appt. Day segment tab contains text matching one of the expected tab names
     * (Morning, Afternoon, or Evening)
     *
     * @param segTab WebElement representing an Appt. Day segment tab
     */
    private void verifyApptSegmentTabName(WebElement segTab) {
        boolean tabNameMatchFound = false;
        for (String apptTabName : apptTabNameList) {
            if (segTab.getText().equalsIgnoreCase(apptTabName)) {
                tabNameMatchFound = true;
                break;
            }
        }
        Assert.assertTrue("FAIL: The appt. segment tab name: '" + segTab.getText()
                + "' did NOT match any of the expected options (Morning, Afternoon, Evening)!", tabNameMatchFound);
    }

    /**
     * Verifies the selected appt. time slot appears in red. Matches the css background value for the time slot
     * against the rgba value for "red" (237, 28, 36, 1)
     */
    public void verifySelectedAppointmentTimeIsRed() {
        LOGGER.info("verifySelectedAppointmentTimeIsRed started");
        driver.waitForElementVisible(selectDateField);
        WebElement selectedApptTimeEle = driver.getElementWithText(timeSlotsBy, getRawApptTime());
        String selectedApptColor = selectedApptTimeEle.getCssValue(Constants.BACKGROUND_COLOR);
        Assert.assertTrue("FAIL: The rgba value of selected appointment - '" + selectedApptColor
                        + "' did NOT match the expected -'" + Constants.RED_COLOR_FOUR_DIGIT + "'!",
                selectedApptColor.equals(Constants.RED_COLOR_FOUR_DIGIT));
        LOGGER.info("verifySelectedAppointmentTimeIsRed completed");
    }
    //endregion Verification methods


    /**
     * Enters just the customer notes when scheduling an appointment
     *
     * @param customerNotes CustomerNotes from ovc.data.OvcData
     */
    public void enterCustomerNotes(String customerNotes) {
        LOGGER.info("enterCustomerNotes started");
        apptNotesField.clear();
        apptNotesField.click();
        apptNotesField.sendKeys(customerNotes);
        LOGGER.info("enterCustomerNotes completed");
    }


    /**
     * Enters just the phone number when scheduling an appointment
     *
     * @param phoneNumber The number to enter
     */
    public void enterPhoneNumber(String phoneNumber) {
        LOGGER.info("enterPhoneNumber started");
        apptPhoneField.clear();
        apptPhoneField.click();
        apptPhoneField.sendKeys(phoneNumber);
        LOGGER.info("enterPhoneNumber completed");
    }

    /**
     * Selects tomorrow as the default appointment date e.g. current date +1
     */
    public void selectDefaultAppointmentDate() {
        LOGGER.info("selectDefaultAppointmentDate started");

        driver.waitForPageToLoad();
        driver.waitForElementNotVisible(CommonActions.pagePreLoaderBy);
        WebElement dateFieldEle = getDateField(false);
        List<WebElement> dayList = getAvailableDaysList(dateFieldEle);

        defaultApptDate = dateFieldEle.getAttribute(Constants.VALUE);
        WebElement tomorrowApptDate = dayList.get(1);
        tomorrowApptDate.click();
        selectedApptDate = dateFieldEle.getAttribute(Constants.VALUE);

        driver.waitForMilliseconds();
        LOGGER.info("selectDefaultAppointmentDate completed");
    }

    /**
     * Returns a list of WebElements representing the currently available dates on the calendar popup
     *
     * @param dateTableEle WebElement with children that represents available appt. dates
     * @return List of WebElements for currently available appt. dates
     */
    private List<WebElement> getAvailableDateElements(WebElement dateTableEle) {
        List<WebElement> dayList = dateTableEle.findElements(availableDaysBy);
        for (int i = dayList.size() - 1; i >= 0; i--) {
            if (dayList.get(i).getAttribute(Constants.CLASS).contains(daysTurnedOff)) {
                dayList.remove(i);
            }
        }
        return dayList;
    }

    /**
     * Modifies the current appointment time in the Modify appointment popup
     */
    private boolean modifyAppointmentTime() {
        LOGGER.info("modifyAppointmentTime started");
        driver.waitForElementVisible(modifyDateField);

        List<String> apptTimeSegTabNames = new ArrayList<>(Arrays.asList(ConstantsOvc.MORNING, ConstantsOvc.AFTERNOON,
                ConstantsOvc.EVENING));
        List<WebElement> segTabAvailableApptTimes;

        for (String segTab : apptTimeSegTabNames) {
            driver.clickElementWithText(labelBy, segTab);
            segTabAvailableApptTimes = webDriver.findElements(availableApptTimesBy);

            if (segTabAvailableApptTimes.size() != 0) {
                for (WebElement apptTime : segTabAvailableApptTimes) {
                    if (!apptTime.getText().equalsIgnoreCase(AppointmentPage.getSelectedApptTime())) {
                        apptTime.click();
                        modifiedApptTime = apptTime.getText();
                        LOGGER.info("modifyAppointmentTime completed - time selected: " + modifiedApptTime + "!");
                        return true;
                    }
                }
            }
        }
        LOGGER.info("modifyAppointmentTime completed - a new time was NOT found!");
        return false;
    }

    /**
     * Appointment Required Dialog Boxes Error Message for Reason Dropdown.
     */
    public void verifyReasonError() {
        LOGGER.info("verifyReasonError started");
        driver.waitForElementVisible(reasonDropdownBy);
        driver.waitForElementNotVisible(dateTableBy);
        commonActions.selectButtonWithText(ConstantsOvc.APPOINTMENT, ConstantsOvc.SCHEDULE);
        commonActions.assertTextPresentInPage(REASON_ERROR);
        LOGGER.info("verifyReasonError Completed");
    }

    /**
     * Appointment Required Dialog Boxes Error Message for Available Time.
     */
    public void verifyAvailableTimeError() {
        LOGGER.info("verifyAvailableTimeError started");
        commonActions.selectButtonWithText(ConstantsOvc.APPOINTMENT, ConstantsOvc.SCHEDULE);
        commonActions.assertTextPresentInPage(AVAILABLE_TIME_ERROR);
        LOGGER.info("verifyAvailableTimeError Completed");
    }

    /**
     * Appointment Required Dialog Boxes Error Message for Customer Email and Phone Number
     */
    public void validateCustomerError() {
        LOGGER.info("validateCustomerError started");
        apptEmailField.clear();
        apptPhoneField.clear();
        commonActions.selectButtonWithText(ConstantsOvc.APPOINTMENT, ConstantsOvc.SCHEDULE);
        commonActions.assertTextPresentInPage(CUSTOMER_ERROR);
        LOGGER.info("validateCustomerError Completed");
    }

    /**
     * Modifies the appointment date and time for the previously selected appointment (from the Alert center)
     */
    public void modifyAppointmentDateAndTime() {
        LOGGER.info("modifyAppointmentDateAndTime started");
        WebElement dateFieldEle = getDateField(true);
        List<WebElement> dayList = getAvailableDaysList(dateFieldEle);

        List<String> numericalDateList = new ArrayList<>();
        for (WebElement day : dayList) {
            numericalDateList.add(day.getText());
        }

        for (String numericalDate : numericalDateList) {
            WebElement modDateEle = driver.getElementWithText(availableDaysBy, numericalDate);
            modDateEle.click();

            if (!dateFieldEle.getAttribute(Constants.VALUE).contains(getSelectedApptDate())
                    && !dateFieldEle.getAttribute(Constants.VALUE).contains(getDefaultApptDate())) {

                if (modifyAppointmentTime()) {
                    modifiedApptDate = dateFieldEle.getAttribute(Constants.VALUE);
                    break;
                }
            }
            dateFieldEle.click();
        }
        LOGGER.info("modifyAppointmentDateAndTime completed");
    }

    /**
     * Retrieves the dateField element dependent on the type of appointment action (Create OR Modify)
     * @return The element for date field. i.e. False = scheduling from appt. page. True = modifying appt. from alerts page
     */
    private WebElement getDateField(boolean modifyDate) {
        if (modifyDate) {
            return modifyDateField;
        } else {
            return selectDateField;
        }
    }

    /**
     * Retrieves a list of web elements representing the available dates for selection
     * @param dateField Element that when selected opens the appointment date picker
     * @return List of web elements representing the available dates that can be selected
     */
    private List<WebElement> getAvailableDaysList(WebElement dateField) {
        driver.waitForElementVisible(dateField);
        driver.waitForElementClickable(dateField, Constants.TWO_SEC_WAIT);
        dateField.click();

        WebElement dateTableEle = driver.getDisplayedElement(dateTableBy, Constants.TWO_SEC_WAIT);
        WebElement nextMonthEle = driver.getDisplayedElement(nextMonthArrowBy, Constants.TWO_SEC_WAIT);

        List<WebElement> dayList = getAvailableDateElements(dateTableEle);
        if (dayList.size() <= 1) {
            nextMonthEle.click();
            driver.waitForMilliseconds();
            dateTableEle = driver.getDisplayedElement(dateTableBy, Constants.TWO_SEC_WAIT);
            dayList = getAvailableDateElements(dateTableEle);
        }
        return dayList;
    }
}