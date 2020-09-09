package ovc.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Constants;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

import static ovc.pages.ParentElementsPage.clientDetailsPage;

/**
 * Created by eseverson on 9/13/2017.
 */
public class CustomerProfilePage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CustomerProfilePage.class.getName());
    private CommonActions commonActions;
    private ParentElementsPage parentElementsPage;
    private HomePage homePage;

    public CustomerProfilePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
        parentElementsPage = new ParentElementsPage(driver);
        homePage = new HomePage(driver);
    }

    @FindBy(className = "clientVehicle")
    public static WebElement customerVehicle;

    @FindBy(className = "clientProfile")
    public static WebElement customerProfile;

    @FindBy(className = "clientAppointment")
    public static WebElement customerAppointment;

    @FindBy(id = "orderSearch")
    public static WebElement transactionSearchContainer;

    public static final By lookupIconBy = By.className("fa-search");

    public static final By transactionSearchContainerBy = By.id("orderSearch");

    private static final String customerProfileTextField = "//label[contains(text(),'%s')]/following-sibling::input";

    private static final String TRANSACTION_TYPE = "TransactionType";

    public String barcode;

    /**
     * Selects order off client transaction order table with matching text to previous order number
     */
    public void selectPreviousOrderFromTable() {
        LOGGER.info("selectPreviousOrderFromTable started");
        driver.waitForElementVisible(transactionSearchContainerBy);
        String orderNumberClean = homePage.getCleanOrderNumber();
        WebElement temp = webDriver.findElement(transactionSearchContainerBy);
        List<WebElement> tableRows = temp.findElements(By.tagName("tr"));
        boolean found = false;

        for (WebElement row : tableRows) {
            if (row.getText().contains(orderNumberClean)) {
                row.click();
                found = true;
                break;
            }
        } if (!found) {
            Assert.fail("Previous order " + orderNumberClean + "not found in order list");
        }

        LOGGER.info("selectPreviousOrderFromTable completed");
    }

    /**
     * Verify Input-field text contains the expected text
     *
     * @param customerField Customer field placeholder text
     * @param expectedText  Expected text to verify
     */
    public void verifyCustomerFieldTextSetTo(String customerField, String expectedText) {
        LOGGER.info("verifyCustomerFieldTextSetTo started");
        driver.waitForMilliseconds();
        WebElement customerInputElement = commonActions.getDisplayedElementWithPlaceholder(CustomerPage.inputTagBy, customerField);
        String actualText = customerInputElement.getAttribute(Constants.VALUE);
        Assert.assertTrue("FAIL: Customer page \"" + customerField + "\", input field actual text " + actualText
                + " didnt match with expected text " + expectedText, actualText.equalsIgnoreCase(expectedText));
        LOGGER.info("verifyCustomerFieldTextSetTo completed");
    }

    /**
     * Asserts the passed in vehicle is present in the Vehicles section
     *
     * @param vehicle Vehicle to assert
     */
    public void assertCustomerVehicle(String vehicle) {
        LOGGER.info("assertCustomerVehicle started");
        driver.waitForElementClickable(customerVehicle);
        Assert.assertTrue("FAIL: Customer details Vehicles section did not contain: " + vehicle,
                customerVehicle.getText().contains(vehicle));
        LOGGER.info("assertCustomerVehicle completed");
    }

    /**
     * Asserts the passed in text is present in the Profile section
     *
     * @param text Text to assert
     */
    public void assertTextInCustomerProfile(String text) {
        LOGGER.info("assertTextInCustomerProfile started");
        driver.waitForElementClickable(customerProfile);
        Assert.assertTrue("FAIL: Customer details Profile section did not contain: " + text,
                customerProfile.getText().contains(text));
        LOGGER.info("assertTextInCustomerProfile completed");
    }

    /**
     * Asserts Appointment section is displayed
     * Will get refactored with Appointments fix
     */
    public void assertAppointmentSectionDisplayed() {
        LOGGER.info("assertAppointmentSectionDisplayed started");
        driver.waitForElementClickable(customerAppointment);
        Assert.assertTrue("FAIL: Customer details Appointments section was not displayed.",
                customerAppointment.isDisplayed());
        LOGGER.info("assertAppointmentSectionDisplayed completed");
    }

    /**
     * Verifies table has appointment. Then selects the first one.
     */
    public void selectFirstOrderFromAppoimnmentTable() {
        LOGGER.info("selectFirstOrderFromAppoimnmentTable started");
        driver.waitForElementVisible(transactionSearchContainerBy);
        List<WebElement> tableRows =  webDriver.findElement(transactionSearchContainerBy).findElements(CommonActions.trBy);
        if (tableRows.size()==0)
            Assert.fail("FAIL: No transactions present in Appointment table");
        tableRows.get(1).click();
        LOGGER.info("selectFirstOrderFromAppoimnmentTable completed");
    }

    /**
     * Selects appointment from table based on transaction type parameter
     *
     * @param transactionType Type of transaction to select from table
     */
    public void selectOrderFromAppointmentTableViaTransactionType(String transactionType) {
        LOGGER.info("selectOrderFromAppointmentTableViaTransactionType started");
        driver.waitForElementVisible(transactionSearchContainerBy);
        driver.waitForPageToLoad();

        List<WebElement> tableRows =  webDriver.findElement(transactionSearchContainerBy).findElements(CommonActions.trBy);
        if (tableRows.size()==0) {
            Assert.fail("FAIL: No transactions present in Appointment table");
        } else {
            for (WebElement row : tableRows) {
                if (row.getText().contains(transactionType)) {
                    driver.jsScrollToElement(row);
                    row.click();
                    break;
                }
            }
        }

        LOGGER.info("selectOrderFromAppointmentTableViaTransactionType completed");
    }
    
    /**
     * Enters text into the provided text box
     *
     * @param text    Text to enter
     * @param textbox Placeholder text of the textbox label
     */
    public void enterTextIntoCustomerProfileInputBox(String text, String textbox) {
        LOGGER.info("enterTextIntoCustomerProfileInputBox started");
        driver.waitForMilliseconds();
        WebElement customerInputElement = commonActions.getDisplayedElementWithPlaceholder(CustomerPage.inputTagBy, textbox);
        customerInputElement.clear();
        customerInputElement.sendKeys(text);
        LOGGER.info("enterTextIntoCustomerProfileInputBox completed");
    }

    /**
     * Selects the rows of the Customer Profile page transactions table one at
     * a time until the button with the passed in text appears on page
     *
     * @param transactionType   Transaction type listed in Customer Profile table
     * @param buttonText        Text that appears on buttons below
     */
    public void selectTableRowAndButtonThatAppears(String transactionType, String buttonText) {
        LOGGER.info("selectTableRowAndButtonThatAppears started");
        driver.waitForElementClickable(CommonActions.homeIcon);
        driver.waitForMilliseconds();
        WebElement button;
        boolean found = false;

        List<WebElement> tableRows = transactionSearchContainer.findElements(CommonActions.trBy);
        for (WebElement tableRow : tableRows) {
            if (tableRow.getText().toLowerCase().contains(transactionType.toLowerCase())) {
                tableRow.click();
                driver.waitForMilliseconds();
                button = commonActions.getOVCElementWithText(clientDetailsPage, commonActions.buttonTagBy, buttonText);
                if (button != null) {
                    button.click();
                    found = true;
                    break;
                }
            }
        }

        if (!found)
            Assert.fail("FAIL: Expected button: \"" + buttonText + "\" was not displayed on the page \"");

        LOGGER.info("selectTableRowAndButtonThatAppears completed");
    }
    
    /**
     * Selects the desired state from state dropdown
     * 
     * @param stateField State/Province field text
     * @param desiredState State to select from the dropdown
     */
    public void selectStateDropDownValue(String stateField, String desiredState) {
        LOGGER.info("selectStateDropDownValue started.");
        WebElement dropdownEle = driver.getElementWithText(commonActions.selectTagBy, stateField);
        driver.selectFromDropdownByVisibleText(dropdownEle, desiredState);
        LOGGER.info("selectStateDropDownValue completed");
    }

    /**
     * Selects the latest entry in the Customer Profile table and returns the barcode
     *
     * @param salesType Type of sale to search for
     * @param lastName  Last name of the customer we're searching
     * @param barcodeTimestamp Timestamp of the transaction ID we're searching for
     * @return barcode  The string value of the barcode from the table
     */
    public String returnLatestCustomerBarcodeViaTimestamp(String salesType, String lastName, String barcodeTimestamp) {
        LOGGER.info("returnLatestCustomerBarcodeViaTimestamp completed");

        driver.waitForElementClickable(transactionSearchContainer);

        WebElement salesTypeInput = commonActions.getDisplayedElementWithPlaceholder(CommonActions.inputTagBy, TRANSACTION_TYPE);
        salesTypeInput.sendKeys(salesType);
        transactionSearchContainer.findElement(lookupIconBy).click();
        driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        driver.waitForPageToLoad();

        List<WebElement> transactions = webDriver.findElements(TransactionsSearchPage.firstTransactionBy);
        for (WebElement transaction : transactions) {
            if (transaction.getText().contains(lastName.toUpperCase()) &&
                    transaction.getText().contains(barcodeTimestamp)) {
                WebElement transactionID = transaction.findElement(CommonActions.tdBy);
                barcode = transactionID.getText();
                break;
            }
        }

        homePage.storeOvcOrderNumber(barcode);
        LOGGER.info("Transaction ID: " + barcode);
        LOGGER.info("returnLatestCustomerBarcodeViaTimestamp completed");
        return barcode;
    }
}