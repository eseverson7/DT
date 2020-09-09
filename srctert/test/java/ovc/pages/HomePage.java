package ovc.pages;

import common.Config;
import common.Constants;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.util.StringUtils;
import ovc.data.ConstantsOvc;
import ovc.utilities.OvcExcel;
import utilities.CommonUtils;
import utilities.Driver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by eseverson on 6/27/2017.
 */
public class HomePage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private OvcExcel ovcExcel;
    private ParentElementsPage parentElementsPage;
    private final Logger LOGGER = Logger.getLogger(HomePage.class.getName());
    public static String itemPrice;

    public HomePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        parentElementsPage = new ParentElementsPage(driver);
        ovcExcel = new OvcExcel();
        PageFactory.initElements(webDriver, this);
    }

    public static String orderNumber;

    private static final String TENDER_AMOUNT = "Please provide tender amount.";

    public static String SPOT_CHECK_VALUES = "Till, User, Date, Time, Count Tenders, Check, Cash, " +
            "Total Count, Track Tenders, Total Track, Count & Track";

    public static String TRAINING_SPOT_CHECK_VALUES = "Till, User, Date, Time, Count Tenders, Check, Cash, " +
            "Total Count, Track Tenders";

    public static final String SUBTOTAL = "Subtotal";

    private static final String BARCODE_NUMBER = "Barcode Number";

    private static final String GREEN_COLOR = "rgba(121, 200, 121, 1)";

    private static String orderBalanceTotal;

    public static String orderSubTotal;

    @FindBy(className = "fa-info-circle")
    public static WebElement informationIcon;

    @FindBy(className = "fa-globe")
    public static WebElement globeIcon;

    @FindBy(className = "fa-upload")
    public static WebElement uploadIcon;

    @FindBy(className = "fa-credit-card")
    public static WebElement creditCardIcon;

    @FindBy(id = "scanProductTxtBox")
    public static WebElement articleNumTextbox;

    @FindBy(id = "posReceiptBalance")
    public static WebElement balanceAmount;

    @FindBy(id = "virtualReceiptPane")
    private static WebElement vrReceiptPane;

    @FindBy(className = "dgrid-column-description")
    public static WebElement itemDescription;

    @FindBy(className = "dgrid-column-qty")
    public static WebElement quantity;

    @FindBy(className = "dgrid-column-unitPrice")
    public static WebElement unitPrice;

    @FindBy(className = "dgrid-column-price")
    public static WebElement price;

    @FindBy(className = "dgrid-column-tax")
    public static WebElement tax;

    @FindBy(className = "dgrid-column-voidIcon")
    public static WebElement deleteVRRow;

    @FindBy(id = "receiptNode")
    private static WebElement orderReceipt;

    @FindBy(className = "orderSearchbarcodeTxtBx")
    private static WebElement barcodeSearchTextbox;

    @FindBy(className = "vehicleContainer")
    private static WebElement vehicleContainer;

    @FindBy(id = "ng-cm-dialog")
    private static WebElement tillSpotCheckPopup;

    @FindBy(className = "giftcertentrytxt")
    private static WebElement giftCertEntryTextbox;

    @FindBy(className = "tranDiscount-link")
    private static WebElement transactionDiscount;

    @FindBy(className = "mblSwitchInner")
    private static WebElement priceToggleContainer;

    @FindBy(className = "mblSwitchBgLeft")
    private static WebElement priceToggleDollar;

    @FindBy(className = "mblSwitchBgRight")
    private static WebElement priceTogglePercent;

    @FindBy(id = "bannerCarousel")
    private static WebElement bannerCarousel;

    @FindBy(className = "fitmentQualifierDialog")
    private static WebElement fitmentQualifierDialog;

    @FindBy(id = "targetTime")
    private static WebElement CSLTargetTime;

    @FindBy(id = "phoneNumber")
    private static WebElement phoneNumberCheck;

    @FindBy(id = "businessName")
    private static WebElement businessNameCheck;

    @FindBy(id = "licenseNumber")
    private static WebElement licenseNumberCheck;

    @FindBy(id = "licenseState")
    private static WebElement licenseStateCheck;

    @FindBy(id = "licenseExpiration")
    private static WebElement licenseExpirationCheck;

    @FindBy(id = "routingNumber")
    private static WebElement routingNumberCheck;

    @FindBy(id = "accountNumber")
    private static WebElement accountNumberCheck;

    @FindBy(id = "checkNumber")
    private static WebElement checkNumberInput;

    @FindBy(className = "fa-file")
    private static WebElement systemOnSideBar;

    @FindBy(className = "fa-angle-double-right")
    private static WebElement scheduledJob;

    @FindBy(className = "name-job")
    private static WebElement jobName;

    @FindBy(className = "center-layout")
    private static WebElement jobStatus;

    @FindBy(id = "menuViewContainer")
    private static WebElement receiptContainer;

    @FindBy(className = "lineAbove")
    private static WebElement subtotal;

    @FindBy(className = "addNewVehicleBtn")
    private static WebElement addNewButton;

    @FindBy(id = "customerVehicle")
    private static WebElement homePageVehicleDescription;

    public static final By listGroupBy = By.className("list-group");

    private static final By itemNumberBy = By.className("dgrid-column-itemNum");

    private static final By vrRowBy = By.className("ui-state-default");

    private static final By balanceTotalBy = By.id("posReceiptBalance");

    private static final By mblSimpleDialogBy = By.className("mblSimpleDialogContainer");

    private static final By itemQuantityBy = By.className("vrQtyTextBox");

    private static final By orderSearchContainerBy = By.id("orderSearchContainer");

    private static final By loyaltyCustomerBy = By.id("loyaltyUserName");

    private static final By virtualReceiptRowsBy =
            By.xpath("//td[contains(@class, 'dgrid-column-itemNum')]//parent::tr");

    private static final By quantityFieldBy = By.xpath("//input[@name='productUnitPrice']");

    private static final By itemPriceBy = By.className("dgrid-column-price");

    private static final By ovcDynamicDialogBy = By.className("OVCDynamicDialog");

    private static final By overrideOptionLabelsBy = By.xpath("//label[@name=\"itemVoidReasonCodes\"]");

    private static final By voidIconBy = By.cssSelector("[src='/images/dynamic/posMClient/voidIcon.png']");

    private static final By fitmentQualifierCloseBy = By.className("pull-right");

    private static final By yellowWarningTriangleBy = By.className("fa-exclamation-triangle");

    private static final By rowBy = By.className("dgrid-row-table");

    private static final By priceBy = By.className("dgrid-column-price");

    private static final By promoSummaryAlertBy = By.className("alert");

    /***
     * Extracts the currently displayed Balance total from the "home" page
     * @return String containing the currently displayed value of Balance...will contain "$" & "."
     */
    public String extractCurrentBalanceTotal() {
        LOGGER.info("extractCurrentBalanceTotal started");
        driver.waitForElementVisible(balanceTotalBy);
        driver.waitForElementClickable(balanceTotalBy);
        orderBalanceTotal = webDriver.findElement(balanceTotalBy).getText();
        LOGGER.info("extractCurrentBalanceTotal completed");
        return orderBalanceTotal;
    }

    /***
     * Extracts the currently displayed sub total from the "home" page
     * @return String containing the currently displayed value of Balance...will contain "$" & "."
     */
    public String extractCurrentSubtotal() {
        LOGGER.info("extractCurrentSubtotal started");
        driver.waitForElementVisible(balanceTotalBy);
        driver.waitForElementClickable(balanceTotalBy);
        WebElement subtotal = driver.getElementWithText(rowBy, SUBTOTAL);
        orderSubTotal = subtotal.getText().replaceAll("\n", "").split("\\$")[1];
        LOGGER.info("extractCurrentSubtotal completed");
        return orderSubTotal;
    }

    /***
     * Verifies the currently displayed Balance total from the "home"/Virtual Receipt page
     * @param price String containing the currently displayed value of Balance...will contain "$" & "."
     */
    public void assertCurrentBalanceTotal(String price) {
        LOGGER.info("assertCurrentBalanceTotal started");
        driver.waitForElementClickable(balanceTotalBy);
        orderBalanceTotal = webDriver.findElement(balanceTotalBy).getText();
        Assert.assertTrue("FAIL: Expected price: " + price + ". Actual price: " + orderBalanceTotal,
                orderBalanceTotal.equalsIgnoreCase(price));
        LOGGER.info("assertCurrentBalanceTotal completed");
    }

    /***
     * Verifies the currently displayed Balance total from the "home"/Virtual Receipt page using contains
     * @param price Price to validate
     */
    public void assertBalanceTotalContains(String price) {
        LOGGER.info("assertBalanceTotalContains started");
        driver.waitForElementClickable(balanceTotalBy);
        orderBalanceTotal = webDriver.findElement(balanceTotalBy).getText();
        Assert.assertTrue("FAIL: Expected price: " + price + ". Actual price: " + orderBalanceTotal,
                orderBalanceTotal.contains(price));
        LOGGER.info("assertBalanceTotalContains completed");
    }

    /**
     * Verifies that a particular line item appears on the Virtual Receipt
     *
     * @param number String of item number to verify appears
     */
    public void verifyItemNumberAppearsOnVR(String number) {
        LOGGER.info("verifyItemNumberAppearsOnVR started");
        driver.waitForElementClickable(vrReceiptPane);
        boolean found = false;
        List<WebElement> rows = vrReceiptPane.findElements(vrRowBy);
        for (WebElement row : rows) {
            if (row.getText().contains(number)) {
                WebElement finalNum = row.findElement(itemNumberBy);
                Assert.assertTrue("FAIL: Item number " + number + " did not match item number on VR: " +
                        finalNum.getText(), finalNum.getText().trim().equals(number));
                found = true;
                break;
            }
        }
        if (!found)
            Assert.fail("FAIL: Item number " + number + " was not found on the VR.");
        LOGGER.info("verifyItemNumberAppearsOnVR completed");
    }

    /**
     * Pull out receipt data and stores Barcode number
     */
    public void pullOrderReceiptText() {
        LOGGER.info("pullOrderReceiptText started");
        driver.waitForElementClickable(orderReceipt);

        List<WebElement> elements = receiptContainer.findElements(CommonActions.pTagBy);
        for (WebElement element : elements) {
            if (element.getText().contains(BARCODE_NUMBER)) {
                String[] barcode = element.getText().split(":");
                orderNumber = barcode[1].trim();
                break;
            }
        }

        if (orderNumber == null)
            Assert.fail("FAIL: Order number was not found on receipt.");
        else {
            LOGGER.info("Order successful. Order number is: " + orderNumber);
        }
        LOGGER.info("pullOrderReceiptText completed");
    }

    /**
     * Writes order number and timestamp to txt file for post-run integration testing
     */
    public void storeOvcOrderNumber() {
        LOGGER.info("storeOvcOrderNumber started");
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String orderInfo = "Order Number: " + orderNumber + ", Date/Time: " + dateFormat.format(date);
            LOGGER.info(orderInfo);
            driver.scenarioData.setCurrentOrderNumber(orderNumber);
            CommonUtils.appendFile(ConstantsOvc.OVC_ORDERS_FILE, orderInfo + "\n");
        } catch (Exception e) {
            String msg = "There was an error when attempting to store the order number: " + e.toString();
            LOGGER.info(msg);
            CommonUtils.appendFile(ConstantsOvc.OVC_ORDERS_FILE, msg + "\n");
        }
        LOGGER.info("storeOvcOrderNumber completed");
    }

    /**
     * Writes order number and timestamp to txt file for post-run integration testing
     */
    public void storeOvcOrderNumber(String newOrderNumber) {
        LOGGER.info("storeOvcOrderNumber started");
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String orderInfo = "Order Number: " + newOrderNumber + ", Date/Time: " + dateFormat.format(date);
            LOGGER.info(orderInfo);
            driver.scenarioData.setCurrentOrderNumber(newOrderNumber);
            CommonUtils.appendFile(ConstantsOvc.OVC_ORDERS_FILE, orderInfo + "\n");
        } catch (Exception e) {
            String msg = "There was an error when attempting to store the order number: " + e.toString();
            LOGGER.info(msg);
            CommonUtils.appendFile(ConstantsOvc.OVC_ORDERS_FILE, msg + "\n");
        }
        LOGGER.info("storeOvcOrderNumber completed");
    }
    /**
     * Passes in scenario data and path for saving OVC order data to excel
     */
    public void storeOvcOrderNumberExcel(String file, String orderNumber) {
        try {
            driver.scenarioData.setCurrentOrderNumber(orderNumber);
            String location = ConstantsOvc.OVC_ORDER_DATA_FILE_IN + file + ConstantsOvc.OVC_EXCEL_FILE_EXTENSION;
            ovcExcel.OVCOrderNumberToExcel(location, orderNumber);
        } catch (Exception e) {
            Assert.fail("FAIL: Exception occurred in writing '" + orderNumber +
                    "' to the excel file: " + file);
        }
    }

    /**
     * Asserts tender into popup window
     *
     * @param amountString Amount in dollars to verify
     */
    public void assertTenderAmount(String amountString) {
        LOGGER.info("assertTenderAmount started");
        driver.waitForTextPresent(mblSimpleDialogBy, TENDER_AMOUNT, Constants.TWO_SEC_WAIT);
        amountString = amountString.replaceAll("[^0-9.]", "");
        WebElement mblWindow = driver.getDisplayedElement(mblSimpleDialogBy, Constants.TWO_SEC_WAIT);
        WebElement text = mblWindow.findElement(CommonActions.mblTxtBy);
        String value = text.getAttribute(Constants.VALUE);
        Assert.assertTrue("FAIL: Tender amount in text box (" + value + ") " +
                "does not equal the order price total: (" + amountString + ")", value.trim().equalsIgnoreCase(amountString.trim()));
        LOGGER.info("assertTenderAmount completed");
    }

    /**
     * Selects the vehicle description appears on the home page
     *
     * @param vehicleDescription Description of the current vehicle
     */
    public void selectCurrentVehicleDescription(String vehicleDescription) {
        LOGGER.info("selectCurrentVehicleDescription started");
        driver.waitForPageToLoad();
        WebElement textEle = driver.getElementWithText(CommonActions.btnLink, vehicleDescription);
        textEle.click();
        LOGGER.info("selectCurrentVehicleDescription completed");
    }

    /**
     * Verifies the vehicle description appears on the home page
     *
     * @param vehicleDescription Description of the current vehicle
     */
    public void assertVehicleDescription(String vehicleDescription) {
        LOGGER.info("assertVehicleDescription started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: Element with text \'" + vehicleDescription + "\' did not appear. Instead was: " +
                homePageVehicleDescription.getText(), homePageVehicleDescription.getText().contains(vehicleDescription));
        LOGGER.info("assertVehicleDescription completed");
    }

    /**
     * Validates a receipt is displayed and also contains a barcode / receipt number
     */
    public void verifyReceiptDisplaysWithBarcode() {
        LOGGER.info("verifyReceiptDisplaysWithBarcode started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(orderReceipt);
        Assert.assertTrue("FAIL: The order receipt was NOT displayed!", orderReceipt.isDisplayed());

        if (StringUtils.isEmpty(orderNumber)) {
            pullOrderReceiptText();
        }

        Assert.assertTrue("FAIL: An order number was NOT found on the currently displayed receipt",
                !orderNumber.isEmpty());
        LOGGER.info("verifyReceiptDisplaysWithBarcode completed");
    }

    /**
     * Conducts a Transaction Search using the previously created order / barcode number
     */
    public void searchForTransactionByReceiptNumber() {
        LOGGER.info("searchForTransactionByReceiptNumber started");
        driver.waitForElementVisible(barcodeSearchTextbox);
        String orderNumberClean = getCleanOrderNumber();
        barcodeSearchTextbox.clear();
        barcodeSearchTextbox.sendKeys(orderNumberClean);
        commonActions.selectButtonWithText(ParentElementsPage.TRANSACTION_VIEW, ConstantsOvc.SEARCH);
        LOGGER.info("searchForTransactionByReceiptNumber completed");
    }

    /**
     * Verifies the order number and the balance total appear in the table data for Transaction Details
     */
    public void verifyTransactionDetailsDisplayForReceiptNumber() {
        LOGGER.info("verifyTransactionDetailsDisplayForReceiptNumber started");

        WebElement orderSearchTable;
        driver.waitForElementVisible(orderSearchContainerBy);
        orderSearchTable = webDriver.findElement(orderSearchContainerBy);

        String orderSearchTableData = orderSearchTable.findElement(By.tagName("TABLE")).getText();
        String cleanedOrderNumber = getCleanOrderNumber();

        Assert.assertTrue("FAIL: The order number: '" + cleanedOrderNumber + "' was NOT found in the trans. " +
                "details table!", orderSearchTableData.contains(cleanedOrderNumber));
        Assert.assertTrue("FAIL: The order balance: '" + orderBalanceTotal + "' was NOT found in the trans. " +
                "details table!", orderSearchTableData.contains(orderBalanceTotal));
        LOGGER.info("verifyTransactionDetailsDisplayForReceiptNumber completed");
    }

    /**
     * Cleans the orderNumber of any non-digit characters so long as orderNumber is NOT null or empty.
     *
     * @return Numbered portion of the orderNumber; Empty string if orderNumber was found to be null or empty.
     */
    public String getCleanOrderNumber() {
        LOGGER.info("getCleanOrderNumber started");
        if (!StringUtils.isEmpty(orderNumber)) {
            LOGGER.info("cleaning orderNumber");
            LOGGER.info("getCleanOrderNumber completed");
            return orderNumber.replaceAll("[^\\d]", "");
        }
        LOGGER.info("orderNumber was EMPTY or NULL! Returning EMPTY string");
        LOGGER.info("getCleanOrderNumber completed");
        return "";
    }

    /**
     * Clicks the visible vehicle from customer vehicle pill
     */
    public void selectVehicleFromCustomerVehiclePill() {
        LOGGER.info("selectVehicleFromCustomerVehiclePill started");
        driver.waitForMilliseconds();
        List<WebElement> elements = webDriver.findElements(CommonActions.btnLink);
        WebElement vehicleBtn = driver.getDisplayedElement(elements, Constants.TWO_SEC_WAIT);
        vehicleBtn.click();
        LOGGER.info("selectVehicleFromCustomerVehiclePill completed");
    }

    /**
     * Selects the row matching the previous order number
     */
    public void selectTransactionWithPreviousReceiptNumber() {
        LOGGER.info("selectTransactionWithPreviousReceiptNumber started");
        driver.waitForPageToLoad();
        String cleanedOrderNumber = getCleanOrderNumber();
        WebElement orderContainer = webDriver.findElement(orderSearchContainerBy);
        List<WebElement> tableRows = orderContainer.findElements(By.tagName("tr"));

        for (WebElement row : tableRows) {
            if (row.getText().contains(cleanedOrderNumber)) {
                row.click();
                break;
            }
        }
        LOGGER.info("selectTransactionWithPreviousReceiptNumber completed");
    }

    /**
     * Verifies the passed in vehicle appears in the customers Vehicle Container
     *
     * @param vehicle Vehicle name to assert
     */
    public void verifyVehicleInVehicleContainer(String vehicle) {
        LOGGER.info("verifyVehicleInVehicleContainer started");
        driver.waitForElementVisible(vehicleContainer);
        Assert.assertTrue("FAIL: Customer vehicle list did not contain " + vehicle,
                vehicleContainer.getText().contains(vehicle));
        LOGGER.info("verifyVehicleInVehicleContainer completed");
    }

    /**
     * Asserts the passed in text is present in the Till Spot Check window
     *
     * @param text Text to verify appears
     */
    public void verifyTextTillSpotCheckWindow(String text) {
        LOGGER.info("verifyTextTillSpotCheckWindow started");
        driver.waitForElementVisible(tillSpotCheckPopup);
        String[] values;
        if (text.contains(",")) {
            values = text.split(",");
            for (String value : values) {
                Assert.assertTrue("FAIL: Till Spot Check popup did not contain \'" + value.trim() + "\'.",
                        tillSpotCheckPopup.getText().contains(value.trim()));
            }
        } else {
            Assert.assertTrue("FAIL: Till Spot Check popup did not contain \'" + text + "\'.",
                    tillSpotCheckPopup.getText().contains(text));
        }
        LOGGER.info("verifyTextTillSpotCheckWindow completed");

    }

    /**
     * Clicks on the Customer name
     *
     * @param name name of the customer
     */
    public void clickCustomerName(String name) {
        LOGGER.info("clickCustomerName started");
        driver.waitForPageToLoad();
        driver.getElementWithText(loyaltyCustomerBy, name).click();
        LOGGER.info("clickCustomerName completed");
    }

    /**
     * Enters details into the Manager Approval popup dialogue
     */
    public void enterManagerApprovalDetails(String selection) {
        LOGGER.info("enterManagerApprovalDetails started");
        driver.waitForPageToLoad();

        WebElement user = commonActions.getOVCElementWithAttribute(ParentElementsPage.POPUP, CommonActions.inputTagBy, CommonActions.PLACEHOLDER, ConstantsOvc.USERNAME);
        WebElement password = commonActions.getOVCElementWithAttribute(ParentElementsPage.POPUP, CommonActions.inputTagBy, CommonActions.PLACEHOLDER, ConstantsOvc.PASSWORD);

        System.out.println(Config.getMngrUser());
        System.out.println(Config.getMngrPswd());
        user.sendKeys(Config.getMngrUser());
        password.sendKeys(Config.getMngrPswd());

        WebElement button = driver.getElementWithText(CommonActions.buttonTagBy, selection);
        button.click();
        driver.waitForPageToLoad();
        LOGGER.info("enterManagerApprovalDetails completed");
    }

    /**
     * Takes in arguments from step to find and change the quantity of an item
     *
     * @param item     item number to be changed
     * @param quantity quantity you are changing to
     */
    public void adjustItemQuantity(String item, String quantity) {
        LOGGER.info("adjustItemQuantity started");
        driver.waitForElementClickable(vrReceiptPane);
        List<WebElement> rows = vrReceiptPane.findElements(vrRowBy);

        for (WebElement row : rows) {
            if (row.getText().contains(item)) {
                WebElement quantityInput = row.findElement(itemQuantityBy);
                quantityInput.click();
                quantityInput.clear();
                quantityInput.sendKeys(quantity, Keys.ENTER);
                break;
            }
        }

        driver.waitForPageToLoad();
        LOGGER.info("adjustItemQuantity completed");
    }

    /**
     * Verifies a specified item with a specified quantity can be found
     *
     * @param item     Article Number of item to be found on the VR
     * @param quantity quantity you are asserting it contains
     */
    public void verifyAdjustedItemQuantity(String item, String quantity) {
        LOGGER.info("verifyAdjustedItemQuantity started");
        driver.waitForElementClickable(vrReceiptPane);
        driver.waitForMilliseconds();
        boolean found = false;
        List<WebElement> rows = vrReceiptPane.findElements(vrRowBy);

        for (WebElement row : rows) {
            if (row.getText().contains(item)) {
                WebElement quantityInput = row.findElement(itemQuantityBy);
                if (quantityInput.getAttribute(Constants.VALUE).equals(quantity)) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            Assert.fail("FAIL: item quantity was not set to \'" + quantity + "\'.");
        }
        LOGGER.info("verifyAdjustedItemQuantity completed");
    }

    /**
     * Modifies the price for an item on the Virtual Receipt
     *
     * @param itemNumber The item number of the item needing price modification
     * @param newPrice   The new price of the item after modification
     */
    public void modifyPriceForItemNumberOnVirtualReceipt(String itemNumber, String newPrice) {
        LOGGER.info("modifyPriceForItemNumberOnVirtualReceipt started with item number: '" + itemNumber
                + "' and new price of: '" + newPrice + "'");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(balanceTotalBy);

        List<WebElement> virtualReceiptRowsList = getValidVirtualReceiptRows();
        WebElement itemNumberRow = getVirtualReceiptRowByItemNumber(virtualReceiptRowsList, itemNumber);

        if (itemNumberRow == null) {
            Assert.fail("FAIL: Unable to find a row on the Virtual Receipt with item number:'" + itemNumber + "'!");
        }

        WebElement itemQuantityField = itemNumberRow.findElement(quantityFieldBy);

        itemQuantityField.clear();
        itemQuantityField.sendKeys(newPrice.replace(".", ""));
        itemQuantityField.sendKeys(Keys.ENTER);
        LOGGER.info("modifyPriceForItemNumberOnVirtualReceipt completed with item number: '" + itemNumber
                + "' and new price of: '" + newPrice + "'");
    }

    /**
     * Gets the valid (excludes empty rows that exist for table padding) Virtual Receipt rows
     *
     * @return List of WebElements containing only the VR rows that have data (not used for spacing)
     */
    private List<WebElement> getValidVirtualReceiptRows() {
        LOGGER.info("getValidVirtualReceiptRows started");
        List<WebElement> validVirtualReceiptRowsList = new ArrayList<>();
        List<WebElement> allVirtualReceiptRowsList = webDriver.findElements(virtualReceiptRowsBy);

        for (WebElement row : allVirtualReceiptRowsList) {
            if (!row.getText().equals("")) {
                validVirtualReceiptRowsList.add(row);
            }
        }
        LOGGER.info("getValidVirtualReceiptRows completed");
        return validVirtualReceiptRowsList;
    }

    /**
     * Gets the row of the Virtual Receipt containing the specified item number
     *
     * @param virtualReceiptRowsList List of WebElements representing the rows of the Virtual Receipt
     * @param itemNumber             Number of the item used to search for the proper row
     * @return WebElement representing the row containing the specified item number
     */
    private WebElement getVirtualReceiptRowByItemNumber(List<WebElement> virtualReceiptRowsList, String itemNumber) {
        LOGGER.info("getVirtualReceiptRowByItemNumber started");
        for (WebElement row : virtualReceiptRowsList) {
            if (row.getText().contains(itemNumber)) {
                LOGGER.info("getVirtualReceiptRowByItemNumber completed -- corresponding row found!");
                return row;
            }
        }
        LOGGER.info("getVirtualReceiptRowByItemNumber completed -- corresponding row NOT found!");
        return null;
    }

    /**
     * Selects the specified Price Override option from the popup and clicks the "OK" button
     *
     * @param overrideReason String of the option to select in the Price Override popup
     */
    public void selectPriceOverrideOption(String overrideReason) {
        LOGGER.info("selectPriceOverrideOption started with reason:'" + overrideReason + "'");

        if (driver.waitForTextPresent(ovcDynamicDialogBy, ConstantsOvc.PRICE_OVERRIDE, Constants.THREE_SEC_WAIT)) {
            WebElement priceOverrideEle = driver.getElementWithText(ovcDynamicDialogBy, ConstantsOvc.PRICE_OVERRIDE);

            List<WebElement> overrideOptionsList = priceOverrideEle.findElements(overrideOptionLabelsBy);

            for (WebElement option : overrideOptionsList) {
                if (option.getText().equalsIgnoreCase(overrideReason)) {
                    option.click();

                    List<WebElement> overrideButtonList = priceOverrideEle.findElements(CommonActions.buttonTagBy);
                    for (WebElement button : overrideButtonList) {
                        if (button.getText().equalsIgnoreCase(ConstantsOvc.OK)) {
                            button.click();
                            driver.waitForPageToLoad();
                        }
                    }
                    break;
                }
            }
        } else {
            Assert.fail("FAIL: Price Override popup dialog was NOT displayed!");
        }
        LOGGER.info("selectPriceOverrideOption completed with reason:'" + overrideReason + "'");
    }


    /**
     * Verifies the Balance total on the VR matches an expected amount
     *
     * @param currentBalanceTotal The current balance from the Virtual Receipt
     * @param expectedAmount      The expected balance on the Virtual Receipt
     */
    public void verifyBalanceTotalMatchesAmount(String currentBalanceTotal, String expectedAmount) {
        LOGGER.info("verifyBalanceTotalMatchesAmount started");
        driver.waitForPageToLoad();
        currentBalanceTotal = currentBalanceTotal.replaceAll("[^0-9.]", "");
        Assert.assertTrue("FAIL: Expected amount:'" + expectedAmount.trim()
                        + "' DID NOT match the current balance amount of:'" + currentBalanceTotal + "'!",
                expectedAmount.trim().equals(currentBalanceTotal.trim()));
        LOGGER.info("verifyBalanceTotalMatchesAmount completed");
    }

    /**
     * Enters the gift certificate number into the Gift Certificate popup
     *
     * @param certNumber The gift certificate number (valid or invalid) to be entered
     */
    public void enterGiftCertificateNumber(String certNumber) {
        LOGGER.info("enterGiftCertificateNumber started with certificate number:'" + certNumber + "'");
        driver.waitForElementVisible(giftCertEntryTextbox);
        giftCertEntryTextbox.sendKeys(certNumber);
        commonActions.selectButtonWithText(ParentElementsPage.POPUP, Constants.NEXT);
        LOGGER.info("enterGiftCertificateNumber completed with certificate number:'" + certNumber + "'");
    }

    /**
     * Verifies the specified item appears on the Virtual Receipt
     *
     * @param item Item expected to be found on the Virtual Receipt. E.g. product name, number,
     *             full line item name + price, etc.
     */
    public void verifyItemAppearsOnVirtualReceipt(String item) {
        LOGGER.info("verifyItemAppearsOnVirtualReceipt started with item:'" + item + "'");
        driver.waitForElementClickable(vrReceiptPane);
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        List<WebElement> vrRowsList = vrReceiptPane.findElements(vrRowBy);
        boolean itemFound = false;
        for (WebElement vrRow : vrRowsList) {
            if (vrRow.getText().toLowerCase().contains(item.toLowerCase())) {
                itemFound = true;
                break;
            }
        }
        Assert.assertTrue("FAIL: Item - '" + item + "' was NOT found on the Virtual Receipt!", itemFound);
        LOGGER.info("verifyItemAppearsOnVirtualReceipt completed with item:'" + item + "'");
    }

    /**
     * Enters a string into the article search input text box and presses the enter key to perform search
     *
     * @param articleNumber item number that is to be searched
     */
    public void searchForArticle(String articleNumber) {
        LOGGER.info("searchForArticle started");
        driver.waitForElementClickable(articleNumTextbox);
        articleNumTextbox.sendKeys(articleNumber, Keys.ENTER);
        driver.waitForElementNotVisible(CommonActions.pagePreLoaderBy);
        LOGGER.info("searchForArticle completed");
    }

    /**
     * Selects the red cancel "x" next to the total dollar amount
     */
    public void selectTotalDollarX() {
        LOGGER.info("selectTotalDollarX started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(balanceTotalBy);
        WebElement orderTotalContainer = driver.getDisplayedElement(balanceTotalBy, Constants.ONE_SEC_WAIT);
        WebElement voidIcon = orderTotalContainer.findElement(voidIconBy);
        voidIcon.click();
        driver.waitForPageToLoad();
        LOGGER.info("selectTotalDollarX completed");
    }

    /**
     * Selects the red cancel "x" for a specified line matching articleNumber
     *
     * @param articleNumber item passed in which is to be canceled
     */
    public void selectArticleNumberX(String articleNumber) {
        LOGGER.info("selectArticleNumberX started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(balanceTotalBy);

        List<WebElement> virtualReceiptRowsList = getValidVirtualReceiptRows();
        WebElement itemNumberRow = getVirtualReceiptRowByItemNumber(virtualReceiptRowsList, articleNumber);

        if (itemNumberRow == null) {
            Assert.fail("FAIL: Unable to find a row on the Virtual Receipt with item number:'" + articleNumber + "'!");
        }

        WebElement voidIcon = itemNumberRow.findElement(voidIconBy);
        voidIcon.click();
        driver.waitForPageToLoad();
        LOGGER.info("selectArticleNumberX completed");
    }

    /**
     * Verify the red cancel "x" not visible for a specified line matching articleNumber
     *
     * @param articleNumber item passed in which is to be canceled
     */
    public void verifyArticleNumberXNotVisible(String articleNumber) {
        LOGGER.info("verifyArticleNumberXNotVisible started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(balanceTotalBy);

        List<WebElement> virtualReceiptRowsList = getValidVirtualReceiptRows();
        WebElement itemNumberRow = getVirtualReceiptRowByItemNumber(virtualReceiptRowsList, articleNumber);

        if (itemNumberRow == null) {
            Assert.fail("FAIL: Unable to find a row on the Virtual Receipt with item number:'" + articleNumber + "'!");
        }

        Assert.assertTrue("FAIL: Red cancel \"x\" for " + articleNumber + " specified line matching articleNumber visible '",
                itemNumberRow.findElements(voidIconBy).size() == Constants.ZERO);
        LOGGER.info("verifyArticleNumberXNotVisible completed");
    }

    /**
     * Searches the VR item table for a specified article number, and asserts that it's not found
     *
     * @param articleNumber number for item that should not be present
     */
    public void verifyArticleRemovedFromVR(String articleNumber) {
        LOGGER.info("verifyArticleRemovedFromVR started");
        List<WebElement> virtualReceiptRowsList = webDriver.findElements(virtualReceiptRowsBy);
        WebElement itemNumberRow = getVirtualReceiptRowByItemNumber(virtualReceiptRowsList, articleNumber);
        Assert.assertNull("FAIL: row with article number " + articleNumber + " found!", itemNumberRow);
        LOGGER.info("verifyArticleRemovedFromVR completed");
    }

    /**
     * Verifies the appearance of the "Appointment Details" section on the receipt, and that it contains the appointment
     * date and start time
     */

    public void verifyApptDateTimeAppearOnReceipt() {
        LOGGER.info("verifyApptDateTimeAppearOnReceipt started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(orderReceipt);

        String apptDetailsSectionHeader = "Appointment Details:\n";
        String apptDate = AppointmentPage.getSelectedApptDate();
        String apptTime = commonActions.convertHourTime(AppointmentPage.getSelectedApptTime(), true);
        apptTime = apptTime.replaceAll("[^0-9:]", "");

        Assert.assertTrue("FAIL: The order receipt did not contain an 'Appointment Details' section with a " +
                        "date of:'" + apptDate + "' and time:'" + apptTime + "'!",
                orderReceipt.getText().contains(apptDetailsSectionHeader + "\n" + apptDate + "@" + apptTime));
        LOGGER.info("verifyApptDateTimeAppearOnReceipt completed");
    }

    /**
     * Verifies the expected text string appears on the order receipt
     *
     * @param expectedText The text string that is expected to be displayed on the order receipt
     */
    public void verifyTextAppearsOnReceipt(String expectedText) {
        LOGGER.info("verifyTextAppearsOnReceipt started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(orderReceipt);
        Assert.assertTrue("FAIL: The order receipt did NOT contain the expected text of:'"
                + expectedText + "'!", orderReceipt.getText().contains(expectedText));
        LOGGER.info("verifyTextAppearsOnReceipt completed");
    }

    /**
     * Selects the blue "Total" link on Virtual Receipt that opens the discount options
     */
    public void selectDiscountLinkOnVR() {
        LOGGER.info("selectDiscountLinkOnVR started");
        driver.waitForElementVisible(transactionDiscount);
        transactionDiscount.click();
        LOGGER.info("selectDiscountLinkOnVR completed");
    }

    /**
     * Selects the price toggle to change between Dollar and Percent
     *
     * @param selection value of either Dollar or Percent that you would like the toggle to be set to
     */
    public void selectPriceTypeToggle(String selection) {
        LOGGER.info("selectPriceTypeToggle started");
        String toggleSelection = returnPriceTypeToggleSelection();
        String dollarSelection = ConstantsOvc.ON;
        String percentSelection = ConstantsOvc.OFF;

        if (selection.equalsIgnoreCase(ConstantsOvc.DOLLAR) && !toggleSelection.equalsIgnoreCase(dollarSelection)) {
            priceTogglePercent.click();
        } else if (selection.equalsIgnoreCase(ConstantsOvc.PERCENT) && !toggleSelection.equalsIgnoreCase(percentSelection)) {
            priceToggleDollar.click();
        } else {
            LOGGER.info("Designated selection: " + selection + ", already matches what is currently toggled");
        }
        LOGGER.info("selectPriceTypeToggle completed");
    }

    /**
     * Asserts what the price toggle is currently set to, either "on" for "Dollar" and "off" for "Percent"
     *
     * @param selection String value of either "Dollar" or "Percent" defined in step
     */
    public void verifyPriceTypeToggle(String selection) {
        LOGGER.info("verifyPriceToggle started");
        String toggleSelection = returnPriceTypeToggleSelection();
        String dollarSelection = ConstantsOvc.ON;
        String percentSelection = ConstantsOvc.OFF;

        if (selection.equalsIgnoreCase(ConstantsOvc.DOLLAR)) {
            Assert.assertTrue("FAIL: toggle selection is currently set to: " + toggleSelection + " but expected a value of: "
                    + dollarSelection, toggleSelection.equalsIgnoreCase(dollarSelection));
        } else if (selection.equalsIgnoreCase(ConstantsOvc.PERCENT)) {
            Assert.assertTrue("FAIL: toggle selection is currently set to: " + toggleSelection + " but expected a value of: "
                    + percentSelection, toggleSelection.equalsIgnoreCase(percentSelection));
        }
        LOGGER.info("verifyPriceToggle completed");
    }

    /**
     * returns what the current selection of the dollar/percent toggle is
     *
     * @return String of either "on" for Dollar or "off" for percent
     */
    public String returnPriceTypeToggleSelection() {
        LOGGER.info("returnPriceTypeToggleSelection started");
        WebElement priceToggle = priceToggleContainer.findElement(CommonActions.inputTagBy);
        String toggleSelection = priceToggle.getAttribute(Constants.VALUE);
        LOGGER.info("returnPriceTypeToggleSelection completed");
        return toggleSelection;
    }

    /**
     * Sends the previously extracted Balance total as string to the discount text box
     *
     * @param value String value of either dollar or percent that will be discounted
     */
    public void enterDiscountValue(String value) {
        LOGGER.info("enterDiscountPrice started");
        WebElement discountContainer = driver.getDisplayedElement(CommonActions.dialogModalBy, Constants.ONE_SEC_WAIT);
        WebElement discountTextBox = discountContainer.findElement(CommonActions.mblTxtBy);
        discountTextBox.clear();
        discountTextBox.sendKeys(value);
        LOGGER.info("enterDiscountPrice completed");
    }

    /**
     * Selects the previous transaction by index
     *
     * @param transactionIndex Index of the transaction to click on
     */
    public void selectPreviousTransactionFromBottomBar(int transactionIndex) {
        LOGGER.info("selectPreviousTransactionFromBottomBar started");
        driver.waitForPageToLoad();
        List<WebElement> transactions = bannerCarousel.findElements(CommonActions.divTagBy);
        driver.waitForElementClickable(transactions.get(transactionIndex));
        transactions.get(transactionIndex).click();
        driver.waitForPageToLoad();
        LOGGER.info("selectPreviousTransactionFromBottomBar completed");
    }

    /**
     * Verify verbiage in the Virtual Receipt contains text
     *
     * @param phrase Words to verify appear on the VR
     */
    public void assertVRTextContainsPhrase(String phrase) {
        LOGGER.info("assertVRTextContainsPhrase started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(orderReceipt);
        Assert.assertTrue("FAIL: The order receipt was NOT displayed!", orderReceipt.isDisplayed());
        Assert.assertTrue("FAIL: Text \'" + phrase + "\' was NOT found on the currently displayed receipt",
                orderReceipt.getText().contains(phrase));
        LOGGER.info("assertVRTextContainsPhrase completed");
    }

    /**
     * Verifies the Yellow Fitment Warning triangle appears in VR line item
     *
     * @param articleNumber Item number on VR to check for warning
     */
    public void assertVRContainsWarning(String articleNumber) {
        LOGGER.info("assertVRContainsWarning started");
        driver.waitForPageToLoad();
        boolean found = false;

        List<WebElement> rows = ParentElementsPage.homePage.findElements(virtualReceiptRowsBy);
        for (WebElement row : rows) {
            if (row.getText().contains(articleNumber)) {
                WebElement warning = ParentElementsPage.homePage.findElement(yellowWarningTriangleBy);
                Assert.assertTrue("FAIL: Yellow warning triangle did not appear in row with item: " +
                        articleNumber + ".", warning.isDisplayed());
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: Fitment warning triangle was NOT displayed on page.");

        LOGGER.info("assertVRContainsWarning completed");
    }

    /**
     * Verifies the Yellow Fitment Warning triangle DOES NOT appears in VR line item
     *
     * @param articleNumber Item number on VR to check for warning
     */
    public void assertVRDoesNotContainsWarning(String articleNumber) {
        LOGGER.info("assertVRDoesNotContainsWarning started");
        driver.waitForPageToLoad();
        Boolean notFound = false;

        List<WebElement> rows = ParentElementsPage.homePage.findElements(virtualReceiptRowsBy);
        for (WebElement row : rows) {
            if (row.getText().contains(articleNumber)) {
                try {
                    WebElement warning = ParentElementsPage.homePage.findElement(yellowWarningTriangleBy);
                    Assert.assertFalse("FAIL: Yellow warning triangle DID appear in row with item: " +
                            articleNumber + ".", warning.isDisplayed());
                } catch (Exception e) {
                    LOGGER.info("Yellow warning triangle was NOT found");
                    notFound = true;
                    break;
                }
            }
        }

        if (!notFound)
            Assert.fail("FAIL: Yellow warning triangle WAS found on page.");

        LOGGER.info("assertVRDoesNotContainsWarning completed");
    }

    /**
     * Closes Fitment Qualifiers dialog box
     */
    public void closeFitmentQualifiersDialog() {
        LOGGER.info("closeFitmentQualifiersDialog started");
        driver.waitForElementVisible(fitmentQualifierDialog);
        WebElement button = fitmentQualifierDialog.findElement(fitmentQualifierCloseBy);
        button.click();
        LOGGER.info("closeFitmentQualifiersDialog completed");
    }

    /**
     * Adds hour to current time, then formats it to be input into CSL Target Time
     */
    public void enterCSLTargetTime() {
        LOGGER.info("enterCSLTargetTime started");
        driver.waitForElementVisible(CSLTargetTime);
        LocalDateTime targetTime = LocalDateTime.now().plusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String inputTime = targetTime.format(formatter);
        CSLTargetTime.click();
        CSLTargetTime.clear();
        CSLTargetTime.sendKeys(inputTime);
        LOGGER.info("enterCSLTargetTime completed");
    }

    /**
     * Handles the series of popups that are displayed when attempting to pay via Credit Card. Method attempts to find
     * the 'OK' button on the 'Printer Not Configured' message in an Error dialog (expected behavior). Method throws a
     * failure if the 'OK' button is not found or the final dialog does NOT contain a printing message
     */
    public void handleCreditCardPopupsUntilCheckoutOptions() {
        LOGGER.info("handleCreditCardPopupsUntilCheckoutOptions started");
        driver.waitForPageToLoad();

        WebElement button = null;
        WebElement pageEle = parentElementsPage.returnPageObjectElement(ParentElementsPage.POPUP);

        int i = 0;
        while (i < 4) {
            button = commonActions.getOVCElementWithText(pageEle, CommonActions.buttonTagBy, ConstantsOvc.OK);
            if (button == null) {
                button = commonActions.getOVCElementWithAttribute(ParentElementsPage.POPUP, CommonActions.inputTagBy,
                        Constants.VALUE, ConstantsOvc.OK);
                if (button != null) {
                    break;
                } else {
                    driver.waitForMilliseconds();
                    i++;
                }
            } else {
                break;
            }
        }

        driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        WebElement dialog = driver.getDisplayedElement(CommonActions.dialogModalBy, Constants.ONE_SEC_WAIT);
        if (!dialog.getText().toLowerCase().contains(ConstantsOvc.PRINTING_MSG.toLowerCase())
                || !dialog.getText().toLowerCase().contains(ConstantsOvc.PRINTING_RECEIPT_MSG.toLowerCase()))
            Assert.fail("FAIL: Not on Printer Dialog - Normal Credit Payment flow disrupted!");

        Assert.assertTrue("FAIL: Unable to click 'OK' button! Button was null!", button != null);
        button.click();
        LOGGER.info("handleCreditCardPopupsUntilCheckoutOptions completed");
    }

    /**
     * Selects the vehicle currently assigned to customer VR
     *
     * @param vehicle Vehicle description to click on
     */
    public void selectCurrentlySelectedVehicle(String vehicle) {
        LOGGER.info("selectCurrentlySelectedVehicle started");
        driver.waitForElementClickable(CommonActions.homeIcon);
        driver.clickElementWithText(CommonActions.btnLink, vehicle);
        LOGGER.info("selectCurrentlySelectedVehicle completed");
    }

    /**
     * Inputs default data into empty fields for check payment
     *
     * @param phoneNumber
     * @param licenseNumber
     * @param licenseState
     * @param licenseExpiration
     * @param routingNumber
     * @param accountNumber
     * @param checkNumber       to populate check payment fields
     */
    public void fillCheckPaymentDetails(String phoneNumber, String licenseNumber, String licenseState, String licenseExpiration, String routingNumber, String accountNumber, String checkNumber) {
        LOGGER.info("fillCheckPaymentDetails started");
        phoneNumberCheck.clear();
        phoneNumberCheck.sendKeys(phoneNumber);
        licenseNumberCheck.clear();
        licenseNumberCheck.sendKeys(licenseNumber);
        licenseStateCheck.clear();
        licenseStateCheck.sendKeys(licenseState);
        licenseExpirationCheck.clear();
        licenseExpirationCheck.sendKeys(licenseExpiration);
        routingNumberCheck.clear();
        routingNumberCheck.sendKeys(routingNumber);
        accountNumberCheck.clear();
        accountNumberCheck.sendKeys(accountNumber);
        checkNumberInput.clear();
        checkNumberInput.sendKeys(checkNumber);
        LOGGER.info("fillCheckPaymentDetails completed");
    }

    /**
     * Clicks on System on the left Nav bar
     */
    public void clickSystemOnDashHome() {

        LOGGER.info("clickSystemOnDashHome started");
        driver.waitForElementVisible(systemOnSideBar);
        systemOnSideBar.click();
        LOGGER.info("clickSystemOnDashHome completed");
    }

    /**
     * Clicks on Scheduled Jobs under System on Dashboard
     */
    public void clickScheduledJobOnDashHome() {

        LOGGER.info("clickScheduledJobOnDashHome started");
        driver.waitForElementVisible(scheduledJob);
        scheduledJob.click();
        LOGGER.info("clickScheduledJobOnDashHome completed");
    }

    /**
     * Verify verbiage on job dashboard contains text
     *
     * @param phrase Words to verify Job status appears on dashboard
     */
    public void assertJobDashContainsPhrase(String phrase) {
        LOGGER.info("assertJobDashContainsPhrase started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(jobName);
        Assert.assertTrue("FAIL: The Job Status was NOT displayed!", driver.isElementDisplayed(jobStatus));
        Assert.assertTrue("FAIL: Text \'" + phrase + "\' was NOT found on the Dashboard",
                jobStatus.getText().contains(phrase));
        LOGGER.info("assertJobDashContainsPhrase completed");
    }

    /**
     * Fills in business check input fields with data from ovcData.Java
     *
     * @param businessName  data corresponding to business name input
     * @param phoneNumber   data corresponding to phone number input
     * @param routingNumber data corresponding to routing number input
     * @param accountNumber data corresponding to account number input
     * @param checkNumber   data corresponding to check number input
     */
    public void fillBusinessCheckPaymentDetails(String businessName, String phoneNumber, String routingNumber, String accountNumber, String checkNumber) {
        LOGGER.info("fillBusinessCheckPaymentDetails started");
        businessNameCheck.clear();
        businessNameCheck.sendKeys(businessName);
        phoneNumberCheck.clear();
        phoneNumberCheck.sendKeys(phoneNumber);
        routingNumberCheck.clear();
        routingNumberCheck.sendKeys(routingNumber);
        accountNumberCheck.clear();
        accountNumberCheck.sendKeys(accountNumber);
        checkNumberInput.clear();
        checkNumberInput.sendKeys(checkNumber);
        LOGGER.info("fillBusinessCheckPaymentDetails completed");
    }

    /**
     * Sets the automation to pause for 65 second hard wait for inactive timeout testing
     */
    public void inactivePOSSleep() {
        LOGGER.info("inactivePOSSleep started");
        driver.waitForMilliseconds(Constants.SIXTY_FIVE_SECOND_WAIT);
        LOGGER.info("inactivePOSSleep completed");
    }

    /**
     * inputs first and last name then send enter key to search on the transaction search page
     *
     * @param firstName First name of customer to search
     * @param lastName  Last name of customer to search
     */
    public void searchForTransactionByName(String firstName, String lastName) {
        LOGGER.info("searchForTransactionByName started");
        commonActions.enterTextIntoInputBox(firstName, ConstantsOvc.CUSTOMER_FIRST_NAME_TEXTBOX);
        commonActions.enterTextIntoInputBoxWithEnter(lastName, ConstantsOvc.CUSTOMER_LAST_NAME_TEXTBOX);
        LOGGER.info("searchForTransactionByName completed");
    }

    /**
     * Verifies text shows up on VR in the defined color
     *
     * @param text  String of text to appear
     * @param color Color text should be shown in
     */
    public void verifyTextAndColorOnVR(String text, String color) {
        LOGGER.info("verifyTextAndColorOnVR started");

        boolean found = false;
        if (color.equals((Constants.GREEN)))
            color = GREEN_COLOR;

        List<WebElement> rows = ParentElementsPage.homePage.findElements(virtualReceiptRowsBy);
        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                List<WebElement> spans = row.findElements(CommonActions.spanBy);
                for (WebElement span : spans) {
                    if (span.getText().contains(text)) {
                        String actualColor = span.getCssValue(Constants.COLOR);
                        Assert.assertTrue("FAIL: Text was not found in color: " + color + ". " +
                                actualColor + ": did not equal: " + color, actualColor.equals(color));
                        found = true;
                        break;
                    }
                }
            }
        }

        if (!found)
            Assert.fail("FAIL: " + text + " was not found on Virtual Receipt.");

        LOGGER.info("verifyTextAndColorOnVR started");
    }

    /**
     * Extracts listed subtotal
     *
     * @return float subtotal
     */
    public float extractSubtotal() {
        LOGGER.info("extractSubtotal started");
        driver.waitForElementVisible(subtotal);
        float subtotalPrice = Float.parseFloat(driver.getLastSubstring(subtotal, "$"));
        LOGGER.info("extractSubtotal completed");
        return subtotalPrice;
    }

    /**
     * Extracts the subtotal or row price of an item
     *
     * @param itemCode The item number to extract the row price for
     * @return float The row price of the item
     */
    public float extractSubtotalForItem(String itemCode) {
        LOGGER.info("extractSubtotalForItem started");
        float itemSubtotal = 0.0f;

        List<WebElement> rows = webDriver.findElements(rowBy);
        for (WebElement row : rows) {
            if (row.getText().contains(itemCode)) {
                WebElement itemPrice = row.findElement(priceBy);
                itemSubtotal = Float.parseFloat(driver.getLastSubstring(itemPrice, "$"));
                break;
            }
        }

        LOGGER.info("extractSubtotalForItem completed");
        return itemSubtotal;
    }

    /**
     * Asserts that extracted subtotal equals subtotal of row items minus
     * specified discount
     *
     * @param actualSubtotal Subtotal extracted from home page
     * @param itemSubtotal   Subtotal pulled from item rows
     * @param discount       The discount to check for
     */
    public void assertDiscountInSubtotal(float actualSubtotal, float itemSubtotal, float discount) {
        LOGGER.info("assertDiscountInSubtotal started");
        float actualDiscount = CommonUtils.round((itemSubtotal - actualSubtotal), 2);
        Assert.assertTrue("FAIL: actual subtotal (" + actualSubtotal + ") " +
                        "did not equal item subtotal (" + itemSubtotal + ") minus specified discount (" + discount + ").",
                actualDiscount == discount);
        LOGGER.info("assertDiscountInSubtotal completed");

    }

    /**
     * Asserts extracted subtotal equals an expected value passed in
     *
     * @param subtotal     extracted subtotal of a specific item
     * @param itemSubtotal passed in value asserted against
     */
    public void assertExtractedItemSubtotal(float subtotal, float itemSubtotal) {
        LOGGER.info("assertExtractedItemSubtotal started");
        Assert.assertTrue("FAIL: subtotal extracted for the item did not match expected value of " + itemSubtotal, itemSubtotal == subtotal);
        LOGGER.info("assertExtractedItemSubtotal completed");
    }

    /**
     * Asserts that extracted subtotal equals subtotal with a new price of one of the
     * items adjusted in
     *
     * @param newSubtotal New subtotal after new price adjusted in
     */
    public void assertSubtotalNewPrice(float newSubtotal) {
        LOGGER.info("assertSubtotalNewPrice started");
        float listedSubtotal = extractSubtotal();
        Assert.assertTrue("FAIL: actual subtotal (" + listedSubtotal + ") " +
                        "did not equal expected adjusted subtotal (" + newSubtotal + ").",
                listedSubtotal == newSubtotal);
        LOGGER.info("assertSubtotalNewPrice completed");
    }

    /**
     * Asserts that extracted subtotal equals subtotal minus
     * specified certificate amount
     *
     * @param oldBalance  Balance pulled after certificate applied
     * @param certificate The certificate amount to check for
     */
    public void assertCertificateAppliedToBalance(String oldBalance, String certificate) {
        LOGGER.info("assertCertificateAppliedToBalance started");
        driver.waitForPageToLoad();
        oldBalance = oldBalance.replaceAll("\n", "").split("\\$")[1];
        String listedBalance = extractCurrentBalanceTotal().replaceAll("\n", "").split("\\$")[1];
        float oldBalanceFloat = Float.parseFloat(oldBalance);
        float listedBalanceFloat = Float.parseFloat(listedBalance);
        float certificateFloat = Float.parseFloat(certificate);
        float newBalance = CommonUtils.round((oldBalanceFloat - certificateFloat), 2);
        Assert.assertTrue("FAIL: actual balance (" + listedBalance + ") " +
                "did not equal old subtotal (" + oldBalance + ") minus specified certificate (" +
                certificate + ").", newBalance == listedBalanceFloat);
        LOGGER.info("assertCertificateAppliedToBalance completed");
    }

    /**
     * Captures the price for an item on the Virtual Receipt
     *
     * @param itemNumber The item number that the price is captured for
     */
    public String extractPriceForItemNumberOnVirtualReceipt(String itemNumber) {
        LOGGER.info("extractPriceForItemNumberOnVirtualReceipt started with item number: '" + itemNumber);
        driver.waitForPageToLoad();
        driver.waitForElementVisible(balanceTotalBy);

        List<WebElement> virtualReceiptRowsList = getValidVirtualReceiptRows();
        WebElement itemNumberRow = getVirtualReceiptRowByItemNumber(virtualReceiptRowsList, itemNumber);

        if (itemNumberRow == null) {
            Assert.fail("FAIL: Unable to find a row on the Virtual Receipt with item number:'" + itemNumber + "'!");
        }

        WebElement itemQuantityField = itemNumberRow.findElement(itemPriceBy);

        String price = itemQuantityField.getText();
        price = price.replace("$", "");

        LOGGER.info("extractPriceForItemNumberOnVirtualReceipt completed with item number: '" + itemNumber + " and the price: " + price);
        return price;
    }

    /**
     * Verifies promotion /rebates shows on the virtual receipt
     */
    public void verifyPromoSummaryAlert() {
        LOGGER.info("verifyPromoSummaryAlert started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        Assert.assertTrue("FAIL: Promo didn't display",
                driver.isElementDisplayed(promoSummaryAlertBy));
        LOGGER.info("verifyPromoSummaryAlert completed");
    }

    /**
     * Grabs timestamp for searching for transaction ID
     */
    public String captureTimestamp() {
        LOGGER.info("captureTimestamp started");
        String barcodeTimestamp = new SimpleDateFormat("hh:mm a").format(new Date());
        LOGGER.info("captureTimestamp completed");
        return barcodeTimestamp;
    }

    /**
     * Clicks the Add New button on the SELECT A CUSTOMER VEHICLE popup
     */
    public void clickAddNewVehicle() {
        LOGGER.info("clickAddNewVehicle started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(addNewButton);
        addNewButton.click();
        LOGGER.info("clickAddNewVehicle completed");
    }
}