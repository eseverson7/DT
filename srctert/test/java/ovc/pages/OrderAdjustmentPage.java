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
 * Created by mginevan on 9/13/2017.
 */
public class OrderAdjustmentPage {
    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private ParentElementsPage parentElementsPage;
    private final Logger LOGGER = Logger.getLogger(OrderAdjustmentPage.class.getName());

    public OrderAdjustmentPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        parentElementsPage = new ParentElementsPage(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String DOT_NUMBER = "Dot Number";

    private static final String CURRENT_DEPTH = "Current Depth";

    private static final String ORIGINAL_DEPTH = "Original Depth";

    private static final String PLEASE_SELECT = "Please Select";

    @FindBy(className = "orderAdjustmentsContainer")
    private static WebElement orderAdjustmentsContainer;

    @FindBy(xpath = "//input[contains(@ng-model, 'dot_number')]")
    private static WebElement DOTNumberInput;

    @FindBy(xpath = "//input[contains(@ng-model, 'current_depth')]")
    private static WebElement currentDepthInput;

    @FindBy(xpath = "//input[contains(@ng-model, 'original_depth')]")
    private static WebElement originalDepthInput;

    /**
     * Enters a value into the specified input box
     *
     * @param value     String to enter into input box
     * @param inputBox  Name of input box
     */
    public void enterValueIntoOrderAdjustmentInputBox(String value, String inputBox) {
        LOGGER.info("enterValueIntoInputBox started");

        if(inputBox.equals(DOT_NUMBER)) {
            DOTNumberInput.clear();
            DOTNumberInput.sendKeys(value);
        } else if(inputBox.equals(CURRENT_DEPTH)) {
            currentDepthInput.clear();
            currentDepthInput.sendKeys(value);
        } else if(inputBox.equals(ORIGINAL_DEPTH)) {
            originalDepthInput.clear();
            originalDepthInput.sendKeys(value);
        } else {

            List<WebElement> inputContainerRows = orderAdjustmentsContainer.findElements(CashManagementPage.formGroupBy);
            WebElement returnRow = null;

            for (WebElement inputContainerRow : inputContainerRows) {
                String str = inputContainerRow.findElement(CommonActions.ngBindingClassBy).getText();
                LOGGER.info("found row with text " + str + "which hopefully will match " + inputBox);
                if (inputContainerRow.findElement(CommonActions.ngBindingClassBy).getText().contains(inputBox)) {
                    LOGGER.info("String '" + inputBox + "' matched with found row "
                            + inputContainerRow.getText());
                    returnRow = inputContainerRow;
                    break;
                }
            }
            if (returnRow != null) {
                WebElement textInputBox = returnRow.findElement(CommonActions.inputTagBy);
                textInputBox.clear();
                textInputBox.sendKeys(value);
            }
        }
        LOGGER.info("enterValueIntoInputBox completed");
    }

    /**
     * Chooses a selection from the drop down on the Order Adjustment page
     *
     * @param selection Value in the dropdown to select
     */
    public void selectFromOrderAdjustmentDropDown(String selection) {
        LOGGER.info("selectFromOrderAdjustmentDropDown started");
        WebElement returnPage = parentElementsPage.returnPageObjectElement(ParentElementsPage.ORDER_ADJUSTMENT);
        WebElement dropDown = commonActions.getOVCElementWithText(returnPage, By.tagName("select"), PLEASE_SELECT);
        driver.selectFromDropdownByVisibleText(dropDown, selection);
        LOGGER.info("selectFromOrderAdjustmentDropDown completed");
    }

    /**
     * Finds and selects radio button on Order Adjustment page
     *
     * @param buttonText Text of the value associated with the radio button
     */
    public void selectOrderAdjustmentRadioButton(String buttonText) {
        LOGGER.info("selectOrderAdjustmentRadioButton started");
        driver.waitForElementClickable(ParentElementsPage.orderAdjustmentPage);
        boolean found = false;

        List <WebElement> radios = ParentElementsPage.orderAdjustmentPage.findElements(CommonActions.liTagBy);
        for (WebElement radio : radios) {
            if (radio.getText().contains(buttonText)) {
                radio.findElement(CommonActions.labelTagBy).click();
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: Radio button '" + buttonText + "' was not found on the Order Adjustment page.");

        LOGGER.info("selectOrderAdjustmentRadioButton completed");
    }
}

