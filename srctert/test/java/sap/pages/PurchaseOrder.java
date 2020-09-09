package sap.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

import utilities.Driver;
import org.junit.Assert;
import common.Constants;
import commonUtils.ExcelUtils;

/**
 * Created by mnabizadeh on 5/14/18.
 */
public class PurchaseOrder {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private ExcelUtils excelUtils;
    private final Logger LOGGER = Logger.getLogger(PurchaseOrder.class.getName());

    public PurchaseOrder(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        excelUtils = new ExcelUtils(Constants.PURCHASE_ORDER_EXCEL);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "M0:D-title")
    public static WebElement pageTitle;

    @FindBy(id = "M0:U:1:1::0:0")
    private static WebElement poActionTypeDropDown;

    @FindBy(id = "M0:U:1:1::0:20")
    private static WebElement poOrderTypeDropDown;

    @FindBy(id = "M0:U:1:1:1::0:0")
    private static WebElement poOrderInput;

    @FindBy(id = "M0:U:1:3:1-content")
    private static WebElement poItemListTable;

    @FindBy(id = "M0:U:1:4:1:26::0:0-lbl")
    private static WebElement itemOKCheckBoxContatiner;

    @FindBy(className = "lsMsgBarTxt")
    private static WebElement alertBanner;

    @FindBy(id = "M0:U")
    private static WebElement applicationBackground;

    @FindBy(id = "M1:U:1::0:21")
    private static WebElement purchaseOrderPopupInput;

    @FindBy(id = "M1:D:13::btn[0]")
    private static WebElement otherDocumentPopupButton;

    @FindBy(id = "M0:U:1:4:2:1:2:1:2B271:1::0:58")
    private static WebElement acknowledgementInput;

    @FindBy(id = "M0:U:1:4:2:1:2:1:2B271:1::0:80-img")
    private static WebElement acknowledgementCheckbox;

    @FindBy(id = "MEPO1334-BSTAESAPLMEGUI-key-2")
    private static WebElement dtcConfirmationOptionalSelection;

    @FindBy(id = "MEPO1334-BSTAESAPLMEGUI-r")
    private static WebElement confirmationDropDownContainer;

    @FindBy(id = "M0:U:1:1:2::0:0")
    private static WebElement purchaseOrderTypeDropDown;

    @FindBy(id = "wnd[0]/sbar_msg-txt")
    private static WebElement purchaseOrderNumberAlertContainer;

    @FindBy(id = "M0:D:13::btn[17]-img")
    private static WebElement otherPurchaseOrder;

    @FindBy(className = "lsButton__text urBtnCntTxt")
    private static WebElement otherDocButton;

    private static final By poItemInTableBy = By.className("lsTbarBtnStd");

    private static final By vendorPOInputBy = By.id("M0:U:1:1:2::0:49");

    private static final By purchaseOrderGroupBy = By.id("M0:U:1:2:2:1:1:2B264::1:16");

    private static final By purchaseOrderPopupInputBy = By.id("M1:U:1::0:21");

    private static final By purchaseOrderTableArticleBy = By.xpath("//*[contains(@id,\"[1,5]_c\")]");

    private static final By purchaseOrderTableQuantityBy = By.xpath("//*[contains(@id,\"[1,7]_c\")]");

    private static final By firstPOTableSiteCellBy = By.xpath("//*[contains(@id,\"[1,16]_c\")]");

    private static final String articleDocumentPostedRegex = "Article document [0-9]{10} posted";

    private static final String storeMerchPOChangedRegex = "Store Merch PO [0-9]{10} changed";

    private static final String ORDER_ACKNOWLEDGED = "Order Acknowledged";
    private static final String VENDOR = "Vendor";
    private static final String PURCHASE_GROUP = "Purchase Group";
    private static final String ARTICLE_NUMBER = "Article Number";
    private static final String QUANTITY = "Quantity";
    private static final String SITE = "Site";

    /**
     * Asserts text displayed as page title
     *
     * @param text page title to verify
     */
    public void verifyPageTitle(String text) {
        LOGGER.info("verifyPageTitle started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds(Constants.SEVEN_MILLISEC_WAIT);
        driver.waitForElementVisible(pageTitle);
        String titleString = pageTitle.getText();
        Assert.assertTrue("FAIL: Page title was not found!", titleString.contains(text));
        LOGGER.info("verifyPageTitle completed");
    }

    /**
     * Asserts text displayed in the po dropdown selections
     *
     * @param dropDownDefaultAction expected text of po action dropdown
     * @param dropDownDefaultOrder  expected text of po order type dropdown
     */
    public void verifyDefaultDropDowns(String dropDownDefaultAction, String dropDownDefaultOrder) {
        LOGGER.info("verifyDefaultDropDowns started");
        driver.waitForElementVisible(poActionTypeDropDown);
        String assertTextAction = poActionTypeDropDown.getAttribute(Constants.VALUE);
        String assertTestOrder = poOrderTypeDropDown.getAttribute(Constants.VALUE);
        Assert.assertTrue("FAIL: PO action drop down did not contain expected result " + dropDownDefaultAction,
                assertTextAction.equalsIgnoreCase(dropDownDefaultAction));
        Assert.assertTrue("FAIL: PO order type drop down did not contain expected result " +
                dropDownDefaultOrder, assertTestOrder.equalsIgnoreCase(dropDownDefaultOrder));
        LOGGER.info("verifyDefaultDropDowns completed");
    }

    /**
     * Calls the getPONumber and takes the returned number and sends to the purchase order input.
     */
    public void enterPurchaseOrderNumber() {
        LOGGER.info("enterPurchaseOrderNumber started");
        driver.waitForElementVisible(poOrderInput);
        String purchaseOrderNumber = getPONumber();
        poOrderInput.click();
        poOrderInput.clear();
        poOrderInput.sendKeys(purchaseOrderNumber, Keys.ENTER);
        driver.waitForPageToLoad();
        LOGGER.info("enterPurchaseOrderNumber completed");
    }

    /**
     * gets excel data for the purchase order.
     *
     * @return string value of purchase order cell in excel.
     */
    public String getPONumber() {
        LOGGER.info("getPONumber started");
        String purchaseOrderNumber = null;
        try {
            return purchaseOrderNumber = excelUtils.getCellValue(1, 7, 1);
        } catch (Exception e) {
            Assert.fail("FAIL: Was unable to get purchase order number from the excel");
        }
        LOGGER.info("getPONumber completed");
        return null;
    }

    /**
     * Gets a list of all the articles in the purchase order and loops through them checking the ItemOkay checkbox.
     */
    public void selectItemOkayCheckBox() {
        LOGGER.info("selectItemOkayCheckBox started");
        driver.waitForElementVisible(poItemListTable);

        int poItemListSize = poItemListTable.findElements(poItemInTableBy).size();

        for (int i = 0; i < poItemListSize; i++) {
            List<WebElement> poItemList = poItemListTable.findElements(poItemInTableBy);
            WebElement poItem = poItemList.get(i);
            poItem.click();
            driver.waitForMilliseconds();
            itemOKCheckBoxContatiner.findElement(CommonActions.imgTagBy).click();
            driver.waitForMilliseconds();
        }
        LOGGER.info("selectItemOkayCheckBox completed");
    }

    /**
     * Gets a list of all articles in the purchase order and loops through filling our confirmation header and order
     * acknowledgement inputs and checkbox.
     */
    public void fillConfirmationHeaderAndOrderAcknowledgement() {
        LOGGER.info("fillConfirmationHeaderAndOrderAcknowledgement started");
        driver.waitForElementVisible(poItemListTable);

        int poItemListSize = poItemListTable.findElements(poItemInTableBy).size();

        for (int i = 0; i < poItemListSize; i++) {
            List<WebElement> poItemList = poItemListTable.findElements(poItemInTableBy);
            WebElement poItem = poItemList.get(i);
            poItem.click();
            poItem.sendKeys(Keys.ENTER);
            driver.waitForMilliseconds();
            acknowledgementInput.click();
            acknowledgementInput.sendKeys(ORDER_ACKNOWLEDGED);
            acknowledgementCheckbox.click();
            confirmationDropDownContainer.click();
            dtcConfirmationOptionalSelection.click();
            driver.waitForMilliseconds();
        }
        LOGGER.info("fillConfirmationHeaderAndOrderAcknowledgement completed");
    }

    /**
     * Asserts the alert banner contains expected text.
     */
    public void verifyArticleDocumentPosted() {
        LOGGER.info("verifyArticleDocumentPosted started");
        driver.waitForElementVisible(alertBanner);
        Assert.assertTrue("FAIL: did not find the alert banner's expected text!",
                alertBanner.getText().matches(articleDocumentPostedRegex));
        LOGGER.info("verifyArticleDocumentPosted completed");
    }

    /**
     * Asserts the alert banner contains the expected text
     */
    public void verifyStoreMerchPOChanged() {
        LOGGER.info("verifyStoreMerchPOChanged started");
        Assert.assertTrue("FAIL: did not find the alert banner's expected text!",
                alertBanner.getText().matches(storeMerchPOChangedRegex));
        LOGGER.info("verifyStoreMerchPOChanged completed");
    }

    /**
     * Sends keyboard shortcut to application background to open the 'Other Purchase Order' search popup.
     */
    public void sendOtherPOShortcut() {
        LOGGER.info("sendOtherPOShortcut started");
        driver.waitForElementVisible(poItemInTableBy);
        applicationBackground.sendKeys(Keys.chord(Keys.SHIFT, Keys.F6));
        LOGGER.info("sendOtherPOShortcut completed");
    }

    /**
     * Takes passed in purchase order value and sends to the purchase order input on 'Other Purhcase Order' popup.
     *
     * @param purchaseOrder string of purchase order number gathered at step level from getPONumber method.
     */
    public void enterPurchaseOrderNumberInPopupSearch(String purchaseOrder) {
        LOGGER.info("enterPurchaseOrderNumberInPopupSearch started");
        driver.waitForElementVisible(purchaseOrderPopupInput);
        purchaseOrderPopupInput.click();
        purchaseOrderPopupInput.clear();
        purchaseOrderPopupInput.sendKeys(purchaseOrder);
        LOGGER.info("enterPurchaseOrderNumberInPopupSearch completed");
    }

    /**
     * Searches the purchase order passed to it by clicking on other purchase order icon - switches to the iFrame
     *
     * @param purchaseOrder string of purchase order number
     */
    public void searchOtherPurchaseOrderNumber(String purchaseOrder) {
        LOGGER.info("searchOtherPurchaseOrderNumber started");
        driver.waitForElementVisible(otherPurchaseOrder);
        otherPurchaseOrder.click();
        commonActions.switchToSelectDocPopupFrame();
        WebElement purOrder = webDriver.findElement(purchaseOrderPopupInputBy);
        purOrder.click();
        purOrder.clear();
        purOrder.sendKeys(purchaseOrder);
        LOGGER.info("searchOtherPurchaseOrderNumber completed");
    }

    /**
     * Selects the button on the other document popup labeled 'Other Document'.
     */
    public void selectOtherDocumentButton() {
        LOGGER.info("selectOtherDocumentButton started");
        driver.waitForElementVisible(purchaseOrderPopupInput);
        otherDocumentPopupButton.click();
        LOGGER.info("selectOtherDocumentButton completed");
    }

    /**
     * Selects the target field and sends the string value to fill it
     *
     * @param field input to have value set
     * @param value value sent as string to be input into field
     */
    public void enterValueIntoPOField(String field, String value) {
        LOGGER.info("enterValueIntoPOField started");
        driver.waitForMilliseconds();
        WebElement input = getPOInputField(field);
        input.click();
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
        driver.waitForMilliseconds();
        LOGGER.info("enterValueIntoPOField completed");
    }

    /**
     * Returns a specific input field for purchase order creation.
     *
     * @param field string used to match correct input element
     * @return WebElement returned for input
     */
    private WebElement getPOInputField(String field) {
        switch (field) {
            case VENDOR:
                return webDriver.findElement(vendorPOInputBy);
            case PURCHASE_GROUP:
                return webDriver.findElement(purchaseOrderGroupBy);
            case ARTICLE_NUMBER:
                return webDriver.findElements(purchaseOrderTableArticleBy).get(1);
            case QUANTITY:
                return webDriver.findElements(purchaseOrderTableQuantityBy).get(1);
            case SITE:
                return webDriver.findElements(firstPOTableSiteCellBy).get(1);
            default:
                return null;
        }
    }

    /**
     * Grabs purchase order number after PO creation from the alert banner displayed
     *
     * @return String purchase order number
     */
    public String returnPurchaseOrderNumber() {
        LOGGER.info("returnPurchaseOrderNumber started");
        String purchaseOrderAlertText = purchaseOrderNumberAlertContainer.getText();
        String cleanPurchaseOrderNumber = purchaseOrderAlertText.replaceAll("\\D+", "").trim();
        LOGGER.info("returnPurchaseOrderNumber completed");
        return cleanPurchaseOrderNumber;
    }

    /**
     * saves purchase order number to scenario data memory
     *
     * @param purchaseOrderNumber string number value of purchase order number
     */
    public void savePurchaseOrderNumberToScenarioData(String purchaseOrderNumber) {
        LOGGER.info("savePurchaseOrderNumberToScenarioData started");
        driver.scenarioData.setCurrentPurchaseOrderNumber(purchaseOrderNumber);
        LOGGER.info("savePurchaseOrderNumberToScenarioData completed");
    }

    /**
     * Sends string value to Purchase Order Type dropdown
     *
     * @param poType String value of PO type
     */
    public void inputPOTypeByText(String poType) {
        LOGGER.info("inputPOTypeByText started");
        driver.waitForElementClickable(purchaseOrderTypeDropDown);
        purchaseOrderTypeDropDown.click();
        purchaseOrderTypeDropDown.sendKeys(poType);
        purchaseOrderTypeDropDown.sendKeys(Keys.ENTER);
        driver.waitForMilliseconds();
        LOGGER.info("inputPOTypeByText completed");
    }
}