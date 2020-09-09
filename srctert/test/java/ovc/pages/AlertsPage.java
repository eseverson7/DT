package ovc.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by eseverson on 6/27/2017.
 */
public class AlertsPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(AlertsPage.class.getName());
    private OvcData ovcData, featureData;
    private CommonActions commonActions;
    private ParentElementsPage parentElementPage;

    public AlertsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        ovcData = new OvcData();
        commonActions = new CommonActions(driver);
        parentElementPage = new ParentElementsPage(driver);
    }

    public static final String CONFIRM = "Confirm";
    public static final String VR = "Virtual Receipt";
    public static final String MODIFY = "Modify";
    public static final String CANCEL = "Cancel";
    public static final String COMPLETE = "Complete";
    public static final String NOTES = "Notes";
    public static final String UNDO = "Undo";
    public static final String SEARCH = "Search";
    public static final String REFRESH = "Refresh";
    public static final String TRANSACTION_SEARCH = "Go to transaction search";
    public static final String DEFAULT_NOTE = "Default note testing string";
    public static final String NONE = "None";
    public static final String FIRST = "First";
    public static final String SECOND = "Second";
    public static final String THIRD = "Third";
    public static final String FOURTH = "Fourth";
    public static final String FIFTH = "Fifth";
    private static final String selectAllCheckboxesJs = "return document.getElementsByClassName('faChkSqr')[0].click()";


    @FindBy(className = "fa-thumbs-o-up")
    public static WebElement confirmIcon;

    @FindBy(className = "fa-file-text-o")
    public static WebElement virtualReceiptIcon;

    @FindBy(className = "fa-edit")
    public static WebElement ModifyIcon;

    @FindBy(className = "fa-remove")
    public static WebElement cancelIcon;

    @FindBy(className = "fa-check")
    public static WebElement completeIcon;

    @FindBy(className = "fa-book")
    public static WebElement notesIcon;

    @FindBy(className = "fa-undo")
    public static WebElement undoBtn;

    @FindBy(className = "fa-search")
    public static WebElement searchBtn;

    @FindBy(className = "fa-refresh")
    public static WebElement refreshBtn;

    @FindBy(className = "fa-calendar")
    public static WebElement apptCalendarIcon;

    @FindBy(className = "fa-share")
    public static WebElement transSearch;

    @FindBy(className = "appointmentListWrapper")
    public static WebElement appointmentListWrapper;

    @FindBy(className = "faChkSqr")
    public static WebElement selectCheckBox;

    @FindBy(className = "widget-textarea")
    public static WebElement notesTextBox;

    @FindBy(className = "fa-refresh")
    private static WebElement alertsRefreshButton;

    @FindBy(id = "alertDetailsView")
    public static WebElement headerConfirmationBar;

    @FindBy(id = "sortDropdown")
    public static WebElement dropdownButtons;

    @FindBy(id = "dropdownMenu1")
    public static WebElement dropdownMenuBtn;

    @FindBy(className = "searchClose")
    public static WebElement searchField;

    @FindBy(className = "customer-details")
    public static WebElement orderCustomerDetails;

    public static final By apptCalendarIconBy = By.className("fa-calendar");

    public static final By headerConfirmationBy = By.className("closed");

    public static final By notesTextBoxBy = By.className("needsclick");

    public static final By apptDetailListEntry = By.className("appoitmentDetilsList");

    public static final By dropdownCaretsClassBy = By.id("sortDropdown");


    /**
     * Selects the first appointment in the appointmentListWrapper with the calendar icon
     */
    public void selectAppointment() {
        LOGGER.info("selectAppointment started");
        driver.waitForElementClickable(appointmentListWrapper);
        WebElement child = appointmentListWrapper.findElement(apptCalendarIconBy);
        child.click();
        LOGGER.info("selectAppointment completed");
    }

    /**
     * Selects a button on the alert page with an appointment specified
     *
     * @param icon Icon of button to click
     */
    public void selectAlertsPageButton(String icon) {
        LOGGER.info("selectAlertPageButton started");
        driver.waitForPageToLoad();
        WebElement button = returnAlertsPageButton(icon);
        button.click();
        LOGGER.info("selectAlertPageButton completed");
    }

    /**
     * Returns alert page button WebElement
     *
     * @param icon Text of the icon on the Alert Page to return
     * @return WebElement
     */
    public WebElement returnAlertsPageButton(String icon) {
        LOGGER.info("returnAlertsPageButton started");
        WebElement iconToClick = null;

        if (icon.equalsIgnoreCase(CONFIRM)) {
            iconToClick = confirmIcon;
        } else if (icon.equalsIgnoreCase(VR)) {
            iconToClick = virtualReceiptIcon;
        } else if (icon.equalsIgnoreCase(MODIFY)) {
            iconToClick = ModifyIcon;
        } else if (icon.equalsIgnoreCase(CANCEL)) {
            iconToClick = cancelIcon;
        } else if (icon.equalsIgnoreCase(COMPLETE)) {
            iconToClick = completeIcon;
        } else if (icon.equalsIgnoreCase(NOTES)) {
            iconToClick = notesIcon;
        } else if (icon.equalsIgnoreCase(UNDO)) {
            iconToClick = undoBtn;
        } else if (icon.equalsIgnoreCase(SEARCH)) {
            iconToClick = searchBtn;
        } else if (icon.equalsIgnoreCase(REFRESH)) {
            iconToClick = refreshBtn;
        } else if (icon.equalsIgnoreCase(TRANSACTION_SEARCH)) {
            iconToClick = transSearch;
        }
        LOGGER.info("returnAlertsPageButton completed");
        return iconToClick;
    }

    /**
     * Asserts text of confirmation header matches text
     */
    public void verifyConfirmationHeader(String text) {
        LOGGER.info("verifyConfirmationHeader started");
        driver.waitForElementClickable(headerConfirmationBar);
        WebElement headerMessage = headerConfirmationBar.findElement(headerConfirmationBy);
        String headerConfirmationString = headerMessage.getText();
        Assert.assertTrue("FAIL: Header Confirmation message not found!", headerConfirmationString.contains(text));
        LOGGER.info("verifyConfirmationHeader completed");
    }

    /**
     * Inputs a default string to text box
     */
    public void defaultNoteEntry() {
        LOGGER.info("defaultNoteEntry started");
        driver.waitForElementVisible(notesTextBox);
        WebElement textbox = notesTextBox.findElement(notesTextBoxBy);
        textbox.click();
        textbox.clear();
        textbox.sendKeys(DEFAULT_NOTE);
        LOGGER.info("defaultNoteEntry completed");
    }

    /**
     * Selects the row in the Alerts appointment table that matches the customer name and previously created
     * appointment date + time
     * @param feature feature to pull customer from
     * @throws Exception General exception
     */
    public void selectCreatedAppointmentForCustomer(String feature) throws Exception {
        LOGGER.info("selectCreatedAppointmentForCustomer started");
        featureData = ovcData.getOvcData(feature);
        String firstName = featureData.FirstName;
        String lastName = featureData.LastName;
        String customerName = firstName + " " + lastName;
        String apptDateTime = AppointmentPage.getSelectedApptDate() + " "
                + AppointmentPage.getSelectedApptTime();

        driver.waitForElementClickable(appointmentListWrapper);
        List<WebElement> appointmentEntriesList = webDriver.findElements(apptDetailListEntry);

        for (WebElement appointmentEntry : appointmentEntriesList){
            if (appointmentEntry.getText().contains(customerName)
                    && appointmentEntry.getText().contains(apptDateTime)){
                appointmentEntry.click();
                break;
            }
        }
        LOGGER.info("selectCreatedAppointmentForCustomer completed");
    }

    /**
     * Verifies that a modified appointment appears in the Alerts table for a specified customer.
     * Specific validations include: a previous appointment (date & time) was set, a modified appointment (date & time)
     * was set, the previous appointment does not match the modified appointment, and finally an appointment appears
     * in the Alerts table with the modified date and time for the specified customer with a status of "Modified"
     *
     * @param feature       Feature to pull data from
     * @throws Exception    General exception
     */
    public void verifyModifiedAppointmentForCustomerInAlertsTable(String feature) throws Exception {
        LOGGER.info("verifyModifiedAppointmentForCustomerInAlertsTable started");
        featureData = ovcData.getOvcData(feature);
        String firstName = featureData.FirstName;
        String lastName = featureData.LastName;
        String customerName = firstName + " " + lastName;
        String prevAppt = AppointmentPage.getSelectedApptDate() + " " + AppointmentPage.getSelectedApptTime();
        String modifiedAppt = AppointmentPage.getModifiedApptDate() + " " + AppointmentPage.getModifiedApptTime();

        driver.waitForElementVisible(appointmentListWrapper);

        Assert.assertTrue("FAIL: A previous (initial) appointment time was NOT set!",
                !prevAppt.trim().isEmpty());
        Assert.assertTrue("FAIL: A modified appointment time was NOT set!", !modifiedAppt.trim().isEmpty());

        Assert.assertFalse("FAIL: The previous (initial) appointment:'" + prevAppt
                        + "' MATCHED the modified appointment:'" + modifiedAppt + "' when it was expected to differ!",
                prevAppt.equalsIgnoreCase(modifiedAppt));

        verifyCustomerAppointmentEntryFound(customerName, modifiedAppt, ConstantsOvc.MODIFIED);
        LOGGER.info("verifyModifiedAppointmentForCustomerInAlertsTable completed");
    }

    /**
     * Verifies an appointment appears in the Alert table for a specified customer (first name & last name) on a
     * specific date with a specific status (New, Modified, etc.)
     * @param customerName Customer's name (first and last) as it should appear in the appointment entry
     * @param apptDateTime The combined date and time (Aug 8, 2017 3:00 PM) of the appointment entry to find
     * @param apptStatus Status (New, Modified, etc.) of the appointment entry to find
     */
    private void verifyCustomerAppointmentEntryFound(String customerName, String apptDateTime, String apptStatus) {
        List<WebElement> appointmentEntriesList = webDriver.findElements(apptDetailListEntry);
        boolean entryFound = false;

        for (WebElement appointmentEntry : appointmentEntriesList){
            String apptEntryText = appointmentEntry.getText();
            if (apptEntryText.contains(customerName)
                    && apptEntryText.contains(apptDateTime)
                    && apptEntryText.contains(apptStatus)){
                entryFound = true;
                break;
            }
        }
        Assert.assertTrue("FAIL: Unable to find an entry for customer: '" + customerName
                + "' with an appointment date of:'" + apptDateTime + "' and a status of:'" + apptStatus
                + "'!", entryFound);
    }

    public void selectRefreshIcon() {
        LOGGER.info("selectRefreshIcon started");
        driver.waitForElementVisible(alertsRefreshButton);
        alertsRefreshButton.click();
        commonActions.selectButtonWithText(ParentElementsPage.POPUP, ConstantsOvc.OK);
        driver.waitForPageToLoad();
        commonActions.selectButtonWithText(ParentElementsPage.POPUP, ConstantsOvc.OK);
        LOGGER.info("selectRefreshIcon completed");
    }

    /**
     * Expands a dropdown on page based on selection input
     *
     * @param selection Numerical value of dropdown to expand
     */
    public void expandPageDropdown(String selection) {
        LOGGER.info("expandPageDropdown started");
        driver.waitForElementClickable(dropdownMenuBtn);

        WebElement page = parentElementPage.returnPageObjectElement(ConstantsOvc.ALERTS);
        List <WebElement> dropdowns = page.findElements(dropdownCaretsClassBy);

        switch(selection) {
            case FIRST:
                dropdownMenuBtn.click();
                break;
            case SECOND:
                dropdowns.get(0).click();
                break;
            case THIRD:
                dropdowns.get(1).click();;
                break;
            case FOURTH:
                dropdowns.get(2).click();
                break;
            case FIFTH:
                dropdowns.get(3).click();
                break;
        }
        LOGGER.info("expandPageDropdown completed");
    }

    /**
     * Asserts that an order number stored from a web/hybris cross-application test appears when searching
     * for it in the Alerts center
     */
    public void assertWebOrderReceived() {
        LOGGER.info("assertWebOrderReceived started");
        String orderNumber = driver.scenarioData.getCurrentOrderNumber();
        driver.waitForElementClickable(searchField);
        searchField.sendKeys(orderNumber);
        searchField.sendKeys(Keys.ENTER);
        driver.waitForPageToLoad();
        driver.waitForElementVisible(orderCustomerDetails);
        Assert.assertTrue("FAIL: Stored order number \"" + orderNumber + "\" not displayed in order " +
                "customer details information.", orderCustomerDetails.getText().contains(orderNumber));
        LOGGER.info("assertWebOrderReceived completed");
    }

    /**
     * Clicks the select all checkbox to check all items if present
     */
    public void selectAllItemsIfPresent() {
        LOGGER.info("selectAllItemsIfPresent started");
        driver.waitForPageToLoad();
        try {
            JavascriptExecutor jse = (JavascriptExecutor) webDriver;
            jse.executeScript(selectAllCheckboxesJs);
        } catch (Exception e) {
            LOGGER.info("Item checkboxes were not present.");
        }
        LOGGER.info("selectAllItemsIfPresent completed");
    }
}