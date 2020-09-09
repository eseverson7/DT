package ovc.pages;

import common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import ovc.pages.MainMenuPage;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mginevan on 8/15/2017.
 */
public class ReturnExchangeAdjustPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private HomePage homePage;
    private final Logger LOGGER = Logger.getLogger(ReturnExchangeAdjustPage.class.getName());

    public ReturnExchangeAdjustPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        homePage = new HomePage(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "scanTargetReturnTxtBx")
    private static WebElement returnReceiptInputBox;

    @FindBy(id = "check_all")
    private static WebElement selectAllCheckBox;

    private final static String REFUND = "Refund";
    

    /**
     * Enters a previously stored receipt number
     */
    public void enterPreviousReceipt() {
        LOGGER.info("enterPreviousReceipt started");
        driver.waitForElementVisible(returnReceiptInputBox);
        String orderNumberClean = homePage.getCleanOrderNumber();
        returnReceiptInputBox.sendKeys(orderNumberClean);
        commonActions.selectButtonWithText(ParentElementsPage.OVC, REFUND);
        LOGGER.info("enterPreviousReceipt completed");
    }

    /**
     * clicks on the 'Select All' checkbox on the return page
     */
    public void clickSelectAllCheckBox() {
        LOGGER.info("clickSelectAllCheckBox started");
        driver.waitForElementVisible(selectAllCheckBox);
        selectAllCheckBox.click();
        LOGGER.info("clickSelectAllCheckBox completed");
    }

    /**
     * Selects value from dropdown
     *
     * @param value Text to be selected
     */
    public void selectReturnPageDropdownValue(String value) {
        LOGGER.info("selectReturnPageDropdownValue started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(MainMenuPage.orderDetailsContainerBy);
        WebElement container = driver.getDisplayedElement(MainMenuPage.orderDetailsContainerBy, Constants.ONE_SEC_WAIT);
        WebElement dropdown = container.findElement(CommonActions.selectTagBy);
        driver.selectFromDropdownByVisibleText(dropdown, value);
        LOGGER.info("selectReturnPageDropdownValue completed");
    }

    /**
     * Selects a button based of specific text on the return page, as this page has multiple buttons that are similar but slightly different, and .contains() grabs wrong element
     *
     * @param text specific text button you are looking to select
     */
    public void selectButtonWithExactTextOnReturnPage(String text) {
        LOGGER.info("selectButtonWithExactTextOnReturnPage started");
        driver.waitForElementVisible(ParentElementsPage.orderDetailsPage);
        List<WebElement> buttons = ParentElementsPage.orderDetailsPage.findElements(CommonActions.buttonTagBy);

        for(WebElement button : buttons) {
            if(button.getText().trim().equalsIgnoreCase(text)) {
                button.click();
            }
        }
        driver.waitForPageToLoad();
        LOGGER.info("selectButtonWithExactTextOnReturnPage completed");
    }
}