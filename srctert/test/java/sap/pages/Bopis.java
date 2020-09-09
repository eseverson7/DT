package sap.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.util.logging.Logger;

public class Bopis {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(Bopis.class.getName());

    public Bopis(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "M0:U:3:4B270::0:30")
    private static WebElement postingDateInput;

    @FindBy(id = "M0:D:13::btn[8]-r")
    private static WebElement executeButton;

    @FindBy(id = "userpanel")
    private static WebElement sapUserPanelHome;

    @FindBy(id = "ITSFRAME1")
    private static WebElement rtciFrame;

    @FindBy(id = "M0:U:1:2B265::0:34")
    private static WebElement storeFromInput;

    @FindBy(id = "M0:U:1:2B265::0:59")
    private static WebElement storetoInput;

    @FindBy(xpath = "//span[contains(@id, '1,11#if-r')]")
    private static WebElement transactionNumberGridCell;

    @FindBy(xpath = "//span[contains(@id, '1,5#if-r')]")
    private static WebElement transactionListNumber;

    @FindBy(xpath = "//a[contains(text(), 'Customer Information')]")
    private static WebElement customerInformationButton;

    private static final By goodsMovementNavConatinerBy = By.className("lsTbarOvfl_ITSmode");

    private static final By goodsMovementTableBy = By.className("urST5SelColUiGeneric");

    private static final By loadingImageBy = By.id("ImgSplash");

    private static final By customerInformationEmailBy = By.className("lsTblEdf3WhlRo ");

    private static final String STORE_NUMBER = "1002";
    private static final String POSTING_DATE = "05/14/2018";
    private static final String EXPECTED_EMAIL = "ALLAARUNREDDY@GMAIL.COM";
    private static final String GOODS_MOVEMENTS = "Goods Movements";


    /**
     * Fills out inputs for Store range and Posting Date, followed by selecting the Execute button.
     */
    public void enterStoreAndPostingDate() {
        LOGGER.info("enterStoreAndPostingDate started");
        driver.waitForElementClickable(storeFromInput);
        storeFromInput.click();
        storeFromInput.sendKeys(STORE_NUMBER);
        driver.waitForMilliseconds();
        storetoInput.click();
        storetoInput.sendKeys(STORE_NUMBER);
        driver.waitForMilliseconds();
        postingDateInput.click();
        postingDateInput.clear();
        postingDateInput.sendKeys(POSTING_DATE);
        driver.waitForMilliseconds();
        executeButton.click();
        driver.waitForMilliseconds();
        LOGGER.info("enterStoreAndPostingDate completed");
    }

    /**
     * Expands list nodes and selects the first Goods Movement link on the page.
     */
    public void selectGoodsMovementOption() {
        LOGGER.info("selectGoodsMovementOption started");

        Actions action = new Actions(webDriver);

        //Expanding all nodes in list
        driver.waitForElementClickable(goodsMovementNavConatinerBy);
        WebElement navContainer = webDriver.findElements(goodsMovementNavConatinerBy).get(1);
        navContainer.findElements(CommonActions.spanTagBy).get(0).click();
        driver.waitForMilliseconds();

        //Double clicks on the first Goods Movements in the list
        WebElement goodsMovements = driver.getElementsWithText(CommonActions.spanTagBy, GOODS_MOVEMENTS).get(1);
        action.moveToElement(goodsMovements).doubleClick(goodsMovements).build().perform();
        driver.waitForMilliseconds();
        LOGGER.info("selectGoodsMovementOption completed");
    }

    /**
     * Selects the first transaction in the goods movement list.
     */
    public void selectFirstTransactionFromLeftTable() {
        LOGGER.info("selectFirstTransactionFromLeftTable started");
        Actions action = new Actions(webDriver);
        driver.waitForElementVisible(transactionNumberGridCell);
        action.moveToElement(transactionNumberGridCell).doubleClick(transactionNumberGridCell).build().perform();
        driver.waitForMilliseconds();
        LOGGER.info("selectFirstTransactionFromLeftTable completed");
    }

    /**
     * Extracts transaction number from table and saves to scenario data.
     */
    public void saveTransactionNumberToScenarioData() {
        LOGGER.info("saveTransactionNumberToScenarioData started");
        driver.waitForElementVisible(transactionListNumber);
        String transactionNumber = transactionListNumber.getText();
        driver.scenarioData.setCurrentOrderNumber(transactionNumber);
        LOGGER.info("saveTransactionNumberToScenarioData completed");
    }

    /**
     * Waits for the customer information button to be clickable and then clicks on it.
     */
    public void selectCustomerInformationButton() {
        LOGGER.info("selectCustomerInformationButton started");
        driver.waitForElementClickable(customerInformationButton);
        customerInformationButton.click();
        driver.waitForMilliseconds();
        LOGGER.info("selectCustomerInformationButton completed");
    }

    /**
     * Extracts the loyalty email and asserts it matched what is expected
     */
    public void verifyCustomerInformationDisplayed() {
        LOGGER.info("verifyCustomerInformationDisplayed started");
        driver.waitForElementVisible(customerInformationEmailBy);
        WebElement customerInformationEmail = webDriver.findElements(customerInformationEmailBy).get(2);
        String foundText = customerInformationEmail.getText();
        Assert.assertTrue("FAIL: did not find the expected customer email of 'email' instead found: " +
        foundText, foundText.contains(EXPECTED_EMAIL));
        LOGGER.info("verifyCustomerInformationDisplayed completed");
    }

    /**
     * Waits for the splash page loading image is not displayed and the application background is. Also selects the
     * iFrame for RTC.
     */
    public void waitForRTCLoad() {
        LOGGER.info("waitForRTCLoad started");
        driver.waitForMilliseconds();
        driver.switchFrameContext(rtciFrame, Constants.TO);
        driver.waitForElementVisible(sapUserPanelHome);
        driver.waitForElementNotVisible(loadingImageBy);
        LOGGER.info("waitForRTCLoad completed");
    }
}
