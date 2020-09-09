package ovc.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created by aaronbriel on 8/28/17.
 */
public class TransactionsSearchPage {

    private Driver driver;
    private WebDriver webDriver;
    private ParentElementsPage parentElementsPage;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(TransactionsSearchPage.class.getName());

    public TransactionsSearchPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        parentElementsPage = new ParentElementsPage(driver);
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "orderSearchbarcodeTxtBx")
    public static WebElement transactionIdInput;

    //TODO: Refactor when ID is available or when page refactor solution is implemented
    public static final By firstTransactionBy = By.xpath("//*[@id=\"orderSearchContainer\"]//table/tbody/tr");

    public static final By transactionTable = By.cssSelector("#ovcOrderSearchView tbody");

    /**
     * Asserts that we are on one of the transaction search pages
     */
    public void assertOnTransactionsSearchPage() {
        LOGGER.info("assertOnTransactionsSearchPage started");
        driver.waitForElementVisible(transactionIdInput);
        LOGGER.info("assertOnTransactionsSearchPage completed");
    }

    /**
     * Selects table row based on value of Resume/Cancel Special Order
     *
     * @param text      Value to click on in the table row
     * @param selection Resume or Cancel
     */
    public void selectRecordAndButtonFromSpecialOrderTable(String text, String selection) {
        LOGGER.info("selectRecordAndButtonFromSpecialOrderTable started");
        driver.waitForElementVisible(transactionTable);
        List<WebElement> rows = webDriver.findElement(transactionTable).findElements(CommonActions.trBy);

        for (WebElement row : rows) {
            if (row.getText().toLowerCase().contains(text.toLowerCase())) {
                row.click();
                if (driver.getElementWithText(CommonActions.buttonTagBy, selection).isDisplayed()) {
                    driver.getElementWithText(CommonActions.buttonTagBy, selection).click();
                    break;
                }
            }
        }
        LOGGER.info("selectRecordAndButtonFromSpecialOrderTable completed");
    }

    /**
     * Verifies a certain button does not appear on the transactions page
     *
     * @param textValue Value to click on in the table row
     */
    public void assertButtonNotPresentOnScreen(String textValue) {
        LOGGER.info("assertButtonNotPresentOnScreen started");
        driver.waitForElementVisible(transactionTable);
        WebElement button = driver.getElementWithText(CommonActions.buttonTagBy, textValue);
        Assert.assertNull("FAIL: Element with text value: '" + textValue + "' " +
                        "was displayed on Transaction View page.", button);
        LOGGER.info("assertButtonNotPresentOnScreen completed");
    }

    /**
     * Selects first Transaction from the transactions table
     */
    public void selectFirstTransaction() {
        LOGGER.info("selectFirstTransaction started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(transactionTable);
        WebElement transaction = webDriver.findElement(firstTransactionBy);
        transaction.click();
        LOGGER.info("selectFirstTransaction completed");
    }

    /**
     * Selects the rows of the transactions search table one at
     * a time until the button with the passed in text appears on page
     *
     * @param transactionType   Transaction type listed in Customer Profile table
     * @param buttonText        Text to appear on button below
     */
    public void selectTransactionSearchTableRowAndButtonThatAppears(String transactionType, String buttonText) {
        LOGGER.info("selectTransactionSearchTableRowAndButtonThatAppears started");
        driver.waitForElementClickable(CommonActions.homeIcon);
        driver.waitForMilliseconds();
        WebElement button;
        boolean found = false;

        List<WebElement> tableRows = webDriver.findElement(transactionTable).findElements(CommonActions.trBy);
        for (WebElement tableRow : tableRows) {
            if (tableRow.getText().toLowerCase().contains(transactionType.toLowerCase())) {
                tableRow.click();
                driver.waitForMilliseconds();

                button = commonActions.getOVCElementWithText(parentElementsPage.transOrderSearchPage,
                        commonActions.buttonTagBy, buttonText);
                if (button != null) {
                    button.click();
                    driver.waitForPageToLoad();
                    found = true;
                    break;
                }
            }
        }

        if (!found)
            Assert.fail("FAIL: Expected button: \"" + buttonText + "\" was not displayed on the transaction search page.");

        LOGGER.info("selectTransactionSearchTableRowAndButtonThatAppears completed");
    }
}