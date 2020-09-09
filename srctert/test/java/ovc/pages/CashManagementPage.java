package ovc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import utilities.CommonUtils;
import utilities.Driver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 8/7/17.
 */
public class CashManagementPage {

    private Driver driver;
    private WebDriver webDriver;
    private OvcData ovcData, featureData;
    private final Logger LOGGER = Logger.getLogger(CashManagementPage.class.getName());
    private CommonActions commonActions;

    public CashManagementPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        ovcData = new OvcData();
        commonActions = new CommonActions(driver);
    }

    public static String barCode;
    
    @FindBy(name = "amountInput_comment")
    public static WebElement comments;

    @FindBy(className = "btn-primary")
    public static WebElement button;

    @FindBy(className = "cashDenomination")
    public static WebElement cashDenomination;

    @FindBy(id = "customer_NSF_yes")
    public static WebElement nsfYesOption;

    @FindBy(id = "customer_NSF_no")
    public static WebElement nsfNoOption;

    @FindBy(id = "cancelBtn")
    public static WebElement cancelBtn;

    private static By travelersCheckQDBy = By.id("travelers");

    private static By moneyOrderQDBy = By.id("moneyOrder");

    private static By cashQDBy = By.id("cash");

    private static By qdReceiptDetailsBy = By.className("receiptDetailsDiv");

    private static By ndReceiptDetailsBy = By.className("tillCountContainer");

    private static By firstNameBy = By.id("customer_firstName");

    public static By formGroupBy = By.className("form-group");

    private static By inputGroupBy = By.className("input-group");

    private static By tenderSectionBy = By.className("tenderSection");

    public static By formControlBy = By.className("form-control");

    private static By scopeBy = By.className("ng-scope");

    private static By addItemsInputBy = By.xpath("//*[@id=\"floatSwitch\"]//input");

    private static By commentsBy = By.name("amountInput_comment");

    public static By widgetContainer = By.className("denominationWidget");

    private static By addCurrencyBy = By.className("addCurrencyInput");
    
    public static By dateIconBy = By.className("add-on");
    
    public static By payInPopupBy = By.className("borderless");

    private final static String AR_FIRST_NAME_LABEL = "Ar First Name";
    private final static String AR_LAST_NAME_LABEL = "Ar Last Name";
    private final static String AR_ADDRESS_LABEL = "Ar Address";
    private final static String AR_ROA_LABEL = "AR Account #";
    private final static String AR_PHONE_LABEL = "Ar Phone";
    private final static String FIRST_NAME_LABEL = "First Name";
    private final static String LAST_NAME_LABEL = "Last Name";
    private final static String ADDRESS_LABEL = "Address";
    private final static String CITY_STATE_ZIP_LABEL = "CityStateZIP Code";
    private final static String ROA_LABEL = "AR Account #";
    private final static String PHONE_LABEL = "Phone";
    private static final String SELECT_TENDER_LABEL = "Select Tender";
    private static final String AMOUNT_LABEL = "Amount";
    private static final String SERIAL_NUMBER_LABEL = "Serial #";
    public static final String INVOICE_LABEL = "Add Invoice";
    private static final String ADD_TRIP_LABEL = "Add Trip";
    public static final String ADD_TRIP = "Add Trip";
    private static final String ADD_SKU_DESCRIPTION = "SKU Description";
    private static final String AR = "AR";

    private static final String COMPANY_LABEL = "Company";

    private static final String PERSONAL_CHECK = "Personal Check";
    private static final String MONEY_ORDER = "Money Order";
    private static final String CASH = "Cash";
    private static final String TRAVELERS_CHECK = "Travelers Check";
    public static final String REASON_LABEL = "Reason";
    public static final String EQUIPMENT_TYPE_LABEL = "Equipment Type";
    public static final String APPROX_YEAR_ACQUIRED_LABEL = "Approx Year Acquired";
    private static final String QUICK_DEPOSIT = "Quick Deposit";
    private static final String NIGHTLY_DEPOSIT = "Nightly Deposit";
    private static final String FLOAT_TILL = "Float left in Till";
    private static final String CHECK_DATE = "Check Date";

    private static final String SELECT_TENDER_INPUT_TYPE = "Select Tender";
    private static final String DEFAULT_INPUT_TYPE = "Default";
    private static final String FORM_GROUP_INPUT_TYPE = "Form Group";
    public static final String SCOPE_INPUT_TYPE = "Scope";
    public static final String ADD_CURRENCY_INPUT_TYPE = "Add Currency";

    private final static String PERSONAL_CHECK_QD = "0.00";
    private final static String BUSINESS_CHECK_QD = "0.00";
    private final static String MONEY_ORDER_QD = "15.00";
    private final static String TRAVELERS_CHECK_QD = "0.00";
    private final static String CASH_QD = "15.00";
    private final static String FLOAT_TILL_ND = "250.00";

    /**
     * Enters comments into dialog
     *
     * @param commentsValue Comments for reimbursement
     */
    public void enterDialogComments(String commentsValue) {
        LOGGER.info("enterDialogComments started");
        driver.waitForElementVisible(comments);
        comments.clear();
        comments.sendKeys(commentsValue);
        LOGGER.info("enterDialogComments completed");
    }

    /**
     * Enters payment info into the Employee Reimbursement Pay In dialog
     *
     * @param paymentType  Type of payment (Cash, Check, Card Payment, Money Order, Travelers Check)
     * @param amountValue  Amount of money for reimbursement
     * @param serialNumber Serial number for certain kinds of payment (Check, Money Order, Travelers Check)
     */
    public void enterPayment(String paymentType, String amountValue, String serialNumber) {
        LOGGER.info("enterPayment started");
        Select selectTender;
        WebElement input = getSelectElement(SELECT_TENDER_LABEL);

        /* TODO - This will need to be revisited once defect 8588 is resolved as the flow has changed
        selectTender = new Select(input);
        selectTender.selectByVisibleText(paymentType);

        if (paymentType.equalsIgnoreCase(PERSONAL_CHECK) ||
                paymentType.equalsIgnoreCase(MONEY_ORDER) ||
                paymentType.equalsIgnoreCase(TRAVELERS_CHECK)) {
            enterInput(SELECT_TENDER_INPUT_TYPE, SERIAL_NUMBER_LABEL, serialNumber);
        }*/

        enterInput(SELECT_TENDER_INPUT_TYPE, AMOUNT_LABEL, amountValue);
        LOGGER.info("enterPayment completed");
    }

    /**
     * Selects a dropdown value from a Cash Management dialog
     *
     * @param label Dropdown label to select from
     * @param value Dropdown value to select
     */
    public void selectDropdown(String label, String value) {
        LOGGER.info("selectDropdown started");

        driver.waitForElementVisible(tenderSectionBy);

        List<WebElement> sections = webDriver.findElements(tenderSectionBy);
        Select selectTender;

        for (WebElement section : sections) {
            if (section.getText().contains(label)) {
                WebElement input = section.findElement(formControlBy);
                selectTender = new Select(input);
                selectTender.selectByVisibleText(value);
                break;
            }
        }

        LOGGER.info("selectDropdown completed");
    }

    /**
     * Enter today's date into Check Date
     *
     * @param inputValue Date which you'd like to enter
     */
    public void enterCheckDateInput(String inputValue) {
        LOGGER.info("enterCheckDateInput started");
        WebElement input;
        input = getInputElement(SELECT_TENDER_INPUT_TYPE, CHECK_DATE);
        input.click();
        input.sendKeys(Keys.ARROW_LEFT);
        input.sendKeys(Keys.ARROW_LEFT);
        input.sendKeys(inputValue);
        LOGGER.info("enterCheckDateInput completed");
    }

    /**
     * Enters data into the specified Cash Management dialog input field
     *
     * @param inputType  The type of input field to enter input into
     * @param inputField The input field to enter data into
     * @param inputValue The value to enter into the input field
     */
    public void enterInput(String inputType, String inputField, String inputValue) {
        LOGGER.info("enterInput started");
        WebElement input;

        if (inputType.equalsIgnoreCase(ADD_CURRENCY_INPUT_TYPE)) {
            input = getAddCurrencyInputElement(inputField);
        } else {
            input = getInputElement(inputType, inputField);
        }

        input.clear();
        input.sendKeys(inputValue);
        LOGGER.info("enterInput completed");
    }

    /**
     * Gets input field element for specified input type based on label passed in
     *
     * @param inputType  The type of dialog (SelectTender, Standard)
     * @param inputField The label of the input field to enter data into
     */
    private WebElement getInputElement(String inputType, String inputField) {
        LOGGER.info("getInputElement started");
        List<WebElement> sections;
        WebElement input = null;

        driver.waitForElementVisible(formControlBy);

        if (inputType.equalsIgnoreCase(SELECT_TENDER_INPUT_TYPE)) {
            sections = webDriver.findElements(tenderSectionBy);
        } else if (inputType.equalsIgnoreCase(DEFAULT_INPUT_TYPE)) {
            sections = webDriver.findElements(inputGroupBy);
        } else if (inputType.equalsIgnoreCase(FORM_GROUP_INPUT_TYPE)) {
            sections = webDriver.findElements(formGroupBy);
        } else if (inputType.equalsIgnoreCase(SCOPE_INPUT_TYPE)) {
            sections = webDriver.findElements(scopeBy);
        } else if (inputType.equalsIgnoreCase(ADD_CURRENCY_INPUT_TYPE)) {
            sections = webDriver.findElements(addCurrencyBy);
        } else {
            sections = webDriver.findElements(formGroupBy);
        }

        if (inputField.equalsIgnoreCase(ADD_SKU_DESCRIPTION)) {
            for (WebElement section : sections) {
                if (section.getText().trim().replaceAll("\n\\$", "").contains(inputField)) {
                    input = section.findElement(formControlBy);
                    break;
                }
            }
        } else {
            for (WebElement section : sections) {
                if (section.getText().trim().replaceAll("\n\\$", "").equalsIgnoreCase(inputField)) {
                    input = section.findElement(formControlBy);
                    break;
                }
            }
        }


        //TODO: An unfortunate but necessary conditional that should be removed if parent classes are standardized
        if ((input == null) && (inputType.equalsIgnoreCase(FORM_GROUP_INPUT_TYPE))) {
            sections = webDriver.findElements(inputGroupBy);
            for (WebElement section : sections) {
                if (section.getText().trim().replaceAll("\n", "").equalsIgnoreCase(inputField)) {
                    input = section.findElement(formControlBy);
                    break;
                }
            }
        }

        if (input == null)
            Assert.fail("FAIL: Input field with label \"" + inputField + "\" was not found.");

        LOGGER.info("getInputElement completed");

        return input;
    }

    /**
     * Gets input field element for addCurrency input type based on label passed in
     *
     * @param inputField The label of the input field to enter data into
     */
    private WebElement getAddCurrencyInputElement(String inputField) {
        LOGGER.info("getAddCurrencyInputElement started");
        List<WebElement> sections = webDriver.findElements(addCurrencyBy);
        WebElement input = null;

        driver.waitForElementVisible(formControlBy);

        for (WebElement section : sections) {
            if (section.getText().contains(inputField)) {
                input = section.findElement(formControlBy);
                break;
            }
        }

        if (input == null)
            Assert.fail("FAIL: Input field with label \"" + inputField + "\" was not found.");

        LOGGER.info("getAddCurrencyInputElement completed");

        return input;
    }

    /**
     * Gets select input field element for a based on label passed in
     *
     * @param inputField The select input field label
     */
    private WebElement getSelectElement(String inputField) {
        LOGGER.info("getSelectElement started");
        WebElement input = null;

        driver.waitForElementVisible(tenderSectionBy);

        List<WebElement> sections = webDriver.findElements(tenderSectionBy);

        for (WebElement section : sections) {
            if (section.getText().contains(inputField)) {
                input = section.findElement(formControlBy);
                break;
            }
        }

        LOGGER.info("getSelectElement completed");

        return input;
    }

    /**
     * Verifies customer value is visible and read-only in the dialog
     *
     * @param inputType       The type of input field to check
     * @param inputFieldLabel The input field to check
     * @param inputFieldValue The input field value to verify
     */
    public void verifyCustomerValueInDialog(String inputType, String inputFieldLabel, String inputFieldValue) {
        LOGGER.info("verifyCustomerValueInDialog started");

        WebElement input;
        String actualValue;

        driver.waitForElementVisible(cancelBtn);
        input = getInputElement(inputType, inputFieldLabel);
        actualValue = input.getAttribute(Constants.VALUE);

        Assert.assertTrue("FAIL: Input field \"" + inputFieldLabel + "\" did not contain value \"" + inputFieldValue +
                        "\", but instead contained \"" + actualValue + "\"",
                actualValue.toLowerCase().contains(inputFieldValue.toLowerCase()));
        LOGGER.info("Confirmed input field \"" + inputFieldLabel + "\" contains value \"" + inputFieldValue + "\".");

        LOGGER.info("verifyCustomerValueInDialog completed");
    }

    /**
     * Verifies customer value is visible and read-only in the dialog
     *
     * @param inputType       The type of input field to check
     * @param inputFieldLabel The input field to check
     */
    public void verifyCustomerValueInDialogIsPopulated(String inputType, String inputFieldLabel) {
        LOGGER.info("verifyCustomerValueInDialogIsPopulated started");

        WebElement input;
        String actualValue;

        driver.waitForElementVisible(cancelBtn);
        input = getInputElement(inputType, inputFieldLabel);
        actualValue = input.getAttribute(Constants.VALUE);

        Assert.assertTrue("FAIL: Input field \"" + inputFieldLabel + "\" was not populated.",
                actualValue.length() > 0);
        LOGGER.info("Confirmed input field \"" + inputFieldLabel + "\" was populated.");

        LOGGER.info("verifyCustomerValueInDialogIsPopulated completed");
    }

    /**
     * Verifies customer value is visible and read-only in the dialog
     *
     * @param feature           The feature to pull the customer from
     * @param inputFieldsString Comma separated string of inputFields to check
     * @throws Exception        General exception
     */
    public void verifyReadOnlyCustomerValuesInDialog(String feature, String inputFieldsString) throws Exception {
        LOGGER.info("verifyReadOnlyCustomerValuesInDialog started");

        String inputFieldLabel = "";
        String inputFieldValue = "";
        List<String> inputFields = Arrays.asList(inputFieldsString.split("\\s*,\\s*"));
        featureData = ovcData.getOvcData(feature);

        driver.waitForElementVisible(firstNameBy);

        for (String inputField : inputFields) {
            switch (inputField) {
                case FIRST_NAME_LABEL:
                    inputFieldLabel = FIRST_NAME_LABEL;
                    inputFieldValue = featureData.FirstName;
                    break;
                case LAST_NAME_LABEL:
                    inputFieldLabel = LAST_NAME_LABEL;
                    inputFieldValue = featureData.LastName;
                    break;
                case ADDRESS_LABEL:
                    inputFieldLabel = ADDRESS_LABEL;
                    inputFieldValue = featureData.Address;
                    break;
                case PHONE_LABEL:
                    inputFieldLabel = PHONE_LABEL;
                    inputFieldValue = featureData.Phone;
                    break;
                case AR_FIRST_NAME_LABEL:
                    inputFieldLabel = FIRST_NAME_LABEL;
                    inputFieldValue = featureData.FirstName;
                    break;
                case AR_LAST_NAME_LABEL:
                    inputFieldLabel = LAST_NAME_LABEL;
                    inputFieldValue = featureData.ArLastName;
                    break;
                case AR_ADDRESS_LABEL:
                    inputFieldLabel = ADDRESS_LABEL;
                    inputFieldValue = featureData.ArAddress;
                    break;
                case AR_ROA_LABEL:
                    inputFieldLabel = ROA_LABEL;
                    inputFieldValue = featureData.ArRoa;
                    break;
                case AR_PHONE_LABEL:
                    inputFieldLabel = PHONE_LABEL;
                    inputFieldValue = featureData.ArPhone;
                    break;
            }
            verifyCustomerValueInDialog(FORM_GROUP_INPUT_TYPE, inputFieldLabel, inputFieldValue);
        }

        LOGGER.info("verifyReadOnlyCustomerValuesInDialog completed");
    }

    /**
     * Verifies customer value is visible and read-only in the dialog
     *
     * @param customerType AR or non AR
     * @param feature The feature to pull customer from
     * @throws Exception General exception
     */
    public void verifyCityStateZipReadOnlyCustomerValuesInDialog(String customerType, String feature) throws Exception {
        LOGGER.info("verifyCityStateZipReadOnlyCustomerValuesInDialog started");
        featureData = ovcData.getOvcData(feature);
        String city;
        String state;
        String zip;

        if (customerType.equals(AR)) {
            city = featureData.ArCity;
            state = featureData.ArState;
            zip = featureData.ArZip;
        } else {
            city = featureData.City;
            state = featureData.State;
            zip = featureData.Zip;
        }

        List<WebElement> inputs = null;

        driver.waitForElementVisible(formControlBy);

        List<WebElement> sections = webDriver.findElements(formGroupBy);
        for (WebElement section : sections) {
            if (section.getText().trim().replaceAll("\n", "").equalsIgnoreCase(CITY_STATE_ZIP_LABEL)) {
                inputs = section.findElements(formControlBy);
                break;
            }
        }

        Assert.assertTrue("FAIL: Input field \"City\" did not contain value \"" + city + "\"",
                inputs.get(0).getAttribute("value").contains(city));
        LOGGER.info("Confirmed input field \"City\" contains value \"" + city + "\".");

        Assert.assertTrue("FAIL: Input field \"State\" did not contain value \"" + state + "\"",
                inputs.get(1).getAttribute("value").contains(state));
        LOGGER.info("Confirmed input field \"State\" contains value \"" + state + "\".");

        Assert.assertTrue("FAIL: Input field \"ZIP Code\" did not contain value \"" + zip + "\"",
                inputs.get(2).getAttribute("value").contains(zip));
        LOGGER.info("Confirmed input field \"ZIP Code\" contains value \"" + zip + "\".");

        LOGGER.info("verifyCityStateZipReadOnlyCustomerValuesInDialog completed");
    }

    /**
     * Clicks add button to the right of scoped input fields
     */
    public void clickAddButton() {
        LOGGER.info("clickAddButton started");
        driver.waitForElementVisible(button);
        button.click();
        LOGGER.info("clickAddButton completed");
    }

    /**
     * Enters data into specified Count field in the Manual Denomination Count
     * Cash table based on the given row
     *
     * @param dollars   Amount of money to enter
     * @param rowSelect Row to enter the input
     */
    public void enterTextCashDenominationTable(String dollars, String rowSelect) {
        LOGGER.info("enterTextCashDenominationTable started");
        driver.waitForElementClickable(cashDenomination);
        List<WebElement> rows = cashDenomination.findElements(CommonActions.trBy);
        for (WebElement row : rows) {
            if (row.getText().contains(rowSelect)) {
                WebElement input = row.findElement(CommonActions.inputTagBy);
                input.clear();
                input.click();
                input.sendKeys(dollars);
                break;
            }
        }
        LOGGER.info("enterTextCashDenominationTable completed");
    }

    /**
     * Asserts that NSF options are visible
     */
    public void assertNsfOptionsDisplayed() {
        LOGGER.info("assertNsfOptionsDisplayed started");
        if (!driver.isElementDisplayed(nsfNoOption, Constants.TWO_SEC_WAIT) ||
                !driver.isElementDisplayed(nsfYesOption, Constants.TWO_SEC_WAIT)) {
            Assert.fail("FAIL: One or both of the NSF options were not displayed.");
        }
        LOGGER.info("Confirmed that both NSF options were displayed");
        LOGGER.info("assertNsfOptionsDisplayed completed");
    }

    /**
     * Selects NSF letter option
     *
     * @param option Yes or No
     */
    public void selectNsfOption(String option) {
        LOGGER.info("selectNsfOption started");

        driver.waitForElementVisible(nsfNoOption);

        if (option.equalsIgnoreCase(Constants.YES)) {
            nsfYesOption.click();
        } else {
            nsfNoOption.click();
        }

        LOGGER.info("selectNsfOption completed");
    }


    /**
     * Enters a new trip into a dialog
     *
     * @param from From location for trip
     * @param to   To location for trip
     */
    public void enterTrip(String from, String to) {
        LOGGER.info("enterTrip started");

        WebElement addTripContainer = getAddCurrencyInputElement(ADD_TRIP_LABEL);
        List<WebElement> inputs = addTripContainer.findElements(addItemsInputBy);

        inputs.get(0).clear();
        inputs.get(0).sendKeys(from);
        inputs.get(1).clear();
        inputs.get(1).sendKeys(to);

        LOGGER.info("enterTrip completed");
    }

    /**
     * Enters a new item into a dialog
     *
     * @param sku           SKU number
     * @param description   Description of item
     */
    public void enterItem(String sku, String description) {
        LOGGER.info("enterItem started");

        WebElement addItemContainer = getInputElement(SCOPE_INPUT_TYPE, ADD_SKU_DESCRIPTION);
        List<WebElement> inputs = addItemContainer.findElements(addItemsInputBy);

        inputs.get(1).clear();
        inputs.get(1).sendKeys(sku);
        inputs.get(2).clear();
        inputs.get(2).sendKeys(description);

        LOGGER.info("enterItem completed");
    }

    /**
     * Enter amounts into Quick Deposit Fields
     *
     * @param field Five total Quick Deposit Fields
     */
    public void quickDepositAmounts(String field) {
        LOGGER.info("quickDepositAmounts started");
        By fieldBy = null;
        String amount = null;
        if (field.equalsIgnoreCase(MONEY_ORDER)) {
            fieldBy = moneyOrderQDBy;
            amount = MONEY_ORDER_QD;

        } else if (field.equalsIgnoreCase(CASH)) {
            fieldBy = cashQDBy;
            amount = CASH_QD;

        } else {
            Assert.fail("Quick Deposit Field Input Failed");
        }
        WebElement input = driver.getDisplayedElement(fieldBy, Constants.ONE_SEC_WAIT);
        input.click();
        input.sendKeys(amount.replace(".", ""));

        LOGGER.info("quickDepositAmounts completed");
    }


    /**
     * Validation of Quick Deposit Receipt
     *
     * @param countTenders Quick Deposit Field.
     */
    public void quickDepositReceipt(String countTenders) {
        LOGGER.info("quickDepositReceipt started");

        String receiptValue = null;
        List<WebElement> quickDepositDetails = null;
        WebElement quickDepositPopup = null;

        driver.waitForPageToLoad();
        driver.waitForMilliseconds(Constants.SEVEN_MILLISEC_WAIT);

        List<WebElement> qdList = webDriver.findElements(qdReceiptDetailsBy);

        for (WebElement popup : qdList) {
            if (popup.getText().contains(QUICK_DEPOSIT)) {
                quickDepositPopup = popup;
                break;
            }
        }

        if (quickDepositPopup != null) {
            quickDepositDetails = quickDepositPopup.findElements(CommonActions.trBy);
        } else {
            Assert.fail("Quick Deposit was not found");
        }

        if (countTenders.equalsIgnoreCase(MONEY_ORDER)) {
            receiptValue = MONEY_ORDER_QD;
        } else if (countTenders.equalsIgnoreCase(CASH)) {
            receiptValue = CASH_QD;
        } else {
            Assert.fail("Quick Deposit Receipt Total Count Tenders Failed");
        }

        for (WebElement row : quickDepositDetails) {
            if (row.getText().contains(CASH)) {
                Assert.assertTrue("row did not contain expected value of :" + receiptValue, row.getText().contains(receiptValue));
                break;
            } else if (row.getText().contains(MONEY_ORDER)) {
                Assert.assertTrue("row did not contain expected value of :" + receiptValue, row.getText().contains(receiptValue));
                break;
            }

            LOGGER.info("quickDepositReceipt completed");
        }
    }

    /**
     * Validation of Nightly Deposit Receipt for Float Till
     *
     */
    public void verifyNightlyDepositReceipt() {
        LOGGER.info("verifyNightlyDepositReceipt started");

        String receiptValue = FLOAT_TILL_ND;
        List<WebElement> nightlyDepositDetails = null;
        WebElement nightlyDepositPopup = null;

        driver.waitForPageToLoad();

        List<WebElement> ndList = webDriver.findElements(ndReceiptDetailsBy);

        for (WebElement popup : ndList) {
            if (popup.getText().contains(NIGHTLY_DEPOSIT)) {
                nightlyDepositPopup = popup;
                break;
            }
        }

        if (nightlyDepositPopup != null) {
            nightlyDepositDetails = nightlyDepositPopup.findElements(CommonActions.trBy);
        } else {
            Assert.fail("Nightly Deposit was not found");
        }

        for (WebElement row : nightlyDepositDetails) {
            if (row.getText().contains(FLOAT_TILL)) {
                Assert.assertTrue("row did not contain expected value of :" + receiptValue, row.getText().contains(receiptValue));
                break;
            }
        }

        LOGGER.info("verifyNightlyDepositReceipt completed");
    }

    /**
     * Verifies vendor values are visible and read-only in the dialog
     *
     * @param feature           The feature to pull the customer from
     * @param inputFieldsString Comma separated string of inputFields to check
     * @throws Exception        General exception
     */
    public void verifyReadOnlyVendorValuesInDialog(String feature, String inputFieldsString) throws Exception {
        LOGGER.info("verifyReadOnlyVendorValuesInDialog started");

        String inputFieldLabel = "";
        String inputFieldValue = "";
        List<String> inputFields = Arrays.asList(inputFieldsString.split("\\s*,\\s*"));
        featureData = ovcData.getOvcData(feature);

        driver.waitForElementVisible(commentsBy);

        for (String inputField : inputFields) {
            switch (inputField) {
                case COMPANY_LABEL:
                    inputFieldLabel = COMPANY_LABEL;
                    inputFieldValue = featureData.Vendor;
                    break;
            }
            verifyValueInDialog(SELECT_TENDER_INPUT_TYPE, inputFieldLabel, inputFieldValue);
        }
        LOGGER.info("verifyReadOnlyVendorValuesInDialog completed");
    }

    /**
     * Verifies value is visible and read-only in the dialog
     *
     * @param inputType       The type of input field to check
     * @param inputFieldLabel The input field to check
     * @param inputFieldValue The input field value to verify
     */
    private void verifyValueInDialog(String inputType, String inputFieldLabel, String inputFieldValue) {
        LOGGER.info("verifyValueInDialog started");

        WebElement input;
        String actualValue;

        driver.waitForElementVisible(cancelBtn);
        input = getInputElement(inputType, inputFieldLabel);
        actualValue = input.getAttribute("value");

        Assert.assertTrue("FAIL: Input field \"" + inputFieldLabel + "\" did not contain value \"" + inputFieldValue +
                        "\", but instead contained \"" + actualValue + "\"",
                actualValue.toLowerCase().contains(inputFieldValue.toLowerCase()));
        LOGGER.info("Confirmed input field \"" + inputFieldLabel + "\" contains value \"" + inputFieldValue + "\".");

        LOGGER.info("verifyValueInDialog completed");
    }

    /**
     * Verifies that a dialog window contains specific text
     *
     * @param text Text of the dialog to verify
     */
    public void assertDenominationDialogTextContains (String text) {
        LOGGER.info("assertDenominationDialogTextContains started");
        driver.waitForPageToLoad();
        WebElement dialog = driver.getDisplayedElement(widgetContainer, Constants.ONE_SEC_WAIT);
        Assert.assertTrue("FAIL: Expected dialog to contain text '" + text + "', contained text was:'"
                + dialog.getText().trim() + "'!", dialog.getText().trim().contains(text.trim()));
        LOGGER.info("assertDenominationDialogTextContains completed");
    }

    /**
     * Enter tender amount for Pay In
     * @param amount The tender amount
     */
    public void enterPayInInput(String amount) {
        LOGGER.info("enterPayInInput started");
        WebElement amountInput = webDriver.findElements(addCurrencyBy).get(1).findElement(CommonActions.inputTagBy);
        amountInput.clear();
        amountInput.sendKeys(amount);
        LOGGER.info("enterPayInInput completed");
    }
    
    /**
     * Select date for Pay In Voucher
     */
    public void selectVoucherDate(){
    	LOGGER.info("selectVoucherDate started");
        driver.waitForPageToLoad();
        List <WebElement> calendar = webDriver.findElements(dateIconBy);
        calendar.get(1).click();
        LOGGER.info("selectVoucherDate completed");
    }
    
    /**
     * Extract Barcode number from pay in popup
     */
    public String extractBarCodePayIn() {
        LOGGER.info("extractBarCodePayIn started");

        WebElement borderlessTable = webDriver.findElement(payInPopupBy);
        List<WebElement> tableRows = borderlessTable.findElements(CommonActions.trBy);
        barCode = tableRows.get(5).getText();
        
        if (barCode == null){
            Assert.fail("FAIL: Order number was not found on the popup.");
        }else {
            LOGGER.info("Order successful. Order number is: " + barCode);
        }
        
        LOGGER.info("extractBarCodePayIn completed" + barCode);
        barCode = barCode.replaceAll("[^\\d]", "");
        return barCode;
    }
    
    /**
     * Search for the voucher# by barcode number
     */
    public void searchForVoucherByBarCodeNumber(String textbox) {
        LOGGER.info("searchForVoucherByBarCodeNumber started" + barCode);
        driver.waitForMilliseconds();
        WebElement inputBox = commonActions.getDisplayedElementWithPlaceholder(CommonActions.inputTagBy, textbox);
        inputBox.clear();
        inputBox.sendKeys(barCode);
        LOGGER.info("searchForVoucherByBarCodeNumber completed");
    }
    
    /**
     * Writes barcode number and timestamp to txt file for post-run integration testing
     */
    public void storeOvcBarCode() {
        LOGGER.info("storeOvcBarCode started");
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String orderInfo = "Order Number: " + barCode + ", Date/Time: " + dateFormat.format(date);
            LOGGER.info(orderInfo);
            driver.scenarioData.setCurrentOrderNumber(barCode);
            CommonUtils.appendFile(ConstantsOvc.OVC_ORDERS_FILE, orderInfo + "\n");
        } catch (Exception e) {
            String msg = "There was an error when attempting to store the barcode number: " + e.toString();
            LOGGER.info(msg);
            CommonUtils.appendFile(ConstantsOvc.OVC_ORDERS_FILE, msg + "\n");
        }
        LOGGER.info("storeOvcBarCode completed");
    }
}