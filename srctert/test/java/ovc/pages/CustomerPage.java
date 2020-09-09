package ovc.pages;

import common.Constants;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by eseverson on 6/27/2017.
 */
public class CustomerPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CustomerPage.class.getName());
    private CommonActions commonActions;
    private ParentElementsPage parentElementsPage;

    public CustomerPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
        parentElementsPage = new ParentElementsPage(driver);
    }

    private static final String FIRST_NAME = "First Name";

    public static final String COMPANY_NAME = "Company Name";

    public static final By customerTableBy = By.className("table");

    public static final By tableRowBy = By.tagName("tr");

    public static final By inputTagBy = By.tagName("input");

    public static final By loyaltyCustomerBy = By.id("loyaltyUserName");

    public static final By vehicleListBy = By.className("custVehItemBtn");

    public static final By customerProfileNameBy = By.className("name");
    
    private static final String AR_ACCOUNT_NAME = "AR Account Name";
    
    public static final String AR_ACCOUNT_NUMBER = "AR Account Number";
    
    public static final String arCustomerAttributeValue = "arCustomerObj[obj.name]";

    //TODO:Refactoring Needed When Unique ID or Class Value is Added to DOM
    public static final By customerOrdersTableCellBy = By.cssSelector("#orderSearchContainer tr");

    @FindBy(className = "moreFields")
    public static WebElement moreFilters;

    /**
     * Clicks the cell in the customer table with according text
     *
     * @param text Text of the cell
     */
    public void selectCustomerTableCell(String text) {
        LOGGER.info("selectCustomerTableCell started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        WebElement customerPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.CUSTOMER);

        try {
            driver.waitForElementVisible(commonActions.getOVCElementWithText(customerPage, CommonActions.tableHeaderBy, FIRST_NAME));
        } catch (Exception e) {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
            driver.waitForElementVisible(commonActions.getOVCElementWithText(customerPage, CommonActions.tableHeaderBy, FIRST_NAME));
        }
        WebElement table = driver.getDisplayedElement(customerTableBy, Constants.ONE_SEC_WAIT);
        List<WebElement> rows = table.findElements(tableRowBy);
        for (WebElement row : rows) {
            if (row.getText().toLowerCase().contains(text.toLowerCase())) {
                row.click();
                break;
            }
        }
        LOGGER.info("selectCustomerTableCell completed");
    }

    /**
     * Verify Input-field Element is displayed or not
     *
     * @param textbox Placeholder text of the box itself
     */
    public void verifyCustomerPageInputBoxElementDisplayed(String textbox) {
        LOGGER.info("verifyCustomerPageInputBoxElementDisplayed started");
        driver.waitForPageToLoad();
        WebElement customerInputElement = commonActions.getDisplayedElementWithPlaceholder(inputTagBy, textbox);
        Assert.assertTrue("FAIL: Customer page element \"" + textbox + "\", was not displayed", customerInputElement.isDisplayed());
        LOGGER.info("verifyCustomerPageInputBoxElementDisplayed completed");
    }

    // TODO: Method needs to move to CustomerProfilePage.java

    /**
     * Verify customer vehicle detail exists in Vehicle List
     *
     * @param expectedVehicleDetail Detail of the Vehicle to match
     */
    public void verifyCustomerVehicleExistsInVehicleList(String expectedVehicleDetail) {
        LOGGER.info("verifyCustomerVehicleExistsInVehicleList started");
        driver.waitForMilliseconds();
        boolean found = false;
        List<WebElement> rows = webDriver.findElements(vehicleListBy);
        for (WebElement row : rows) {
            if (row.getText().contains(expectedVehicleDetail)) {
                found = true;
                break;
            }
        }
        if (!found) {
            Assert.fail("Customer vehicle " + expectedVehicleDetail + " not found in vehicle list");
        }
        LOGGER.info("verifyCustomerVehicleExistsInVehicleList completed");
    }

    // TODO: Method needs to move to CustomerProfilePage.java

    /**
     * Verify customer vehicle is not present in Vehicle List
     *
     * @param expectedVehicleDetail Detail of the Vehicle to match
     */
    public void verifyCustomerVehicleIsNotPresentInVehicleList(String expectedVehicleDetail) {
        LOGGER.info("verifyCustomerVehicleIsNotPresentInVehicleList started");
        driver.waitForMilliseconds();
        List<WebElement> rows = webDriver.findElements(vehicleListBy);
        for (WebElement row : rows) {
            if (row.getText().contains(expectedVehicleDetail)) {
                Assert.fail("Customer vehicle " + expectedVehicleDetail + "was present in vehicle list");
                break;
            }
        }
        LOGGER.info("verifyCustomerVehicleIsNotPresentInVehicleList completed");
    }

    /**
     * Verify customer vehicle detail exists on Customer Vehicle Popup
     *
     * @param expectedVehicleDetail Detail of the Vehicle to match
     */
    public void verifyCustomerVehicleExistsOnCustomerVehiclePopup(String expectedVehicleDetail) {
        LOGGER.info("verifyCustomerVehicleExistsOnCustomerVehiclePopup started");
        driver.waitForMilliseconds();
        boolean found = false;
        List<WebElement> rows = webDriver.findElements(CommonActions.btnClearClassBy);
        for (WebElement row : rows) {
            if (row.getText().contains(expectedVehicleDetail)) {
                found = true;
                break;
            }
        }
        if (!found) {
            Assert.fail("Customer vehicle " + expectedVehicleDetail + " not found in vehicle list");
        }
        LOGGER.info("verifyCustomerVehicleExistsOnCustomerVehiclePopup completed");
    } 
    
    /**
     * Clicks the cell in the customer table with according text
     *
     * @param text Text of the cell
     */
    public void selectDesiredCustomerTableCell(String text) {
        LOGGER.info("selectDesiredCustomerTableCell started " + text);
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        WebElement customerPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.CUSTOMER);

        try {
            driver.waitForElementVisible(commonActions.getOVCElementWithText(customerPage, CommonActions.tableHeaderBy, AR_ACCOUNT_NAME));
        } catch (Exception e) {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
            driver.waitForElementVisible(commonActions.getOVCElementWithText(customerPage, CommonActions.tableHeaderBy, AR_ACCOUNT_NAME));
        }
        WebElement table = driver.getDisplayedElement(customerTableBy, Constants.ONE_SEC_WAIT);
        List<WebElement> rows = table.findElements(tableRowBy);
        for (WebElement row : rows) {
            if (row.getText().toLowerCase().contains(text.toLowerCase())) {
                row.click();
                break;
            }
        }
        LOGGER.info("selectDesiredCustomerTableCell completed ");
    }
    
    /**
     * Grabs a list of elements and returns the one with the specific attribute text
     * this method uses two attributes of the element: placeholder and ng-model
     *
     * @param element By selector of element
     * @param text    Text element should contain
     * @return WebElement The WebElement to return
     */
    public WebElement getDisplayedElementWithPlaceholderNgModel(By element, String text) {
        LOGGER.info("getDisplayedElementWithPlaceholderNgModel started");

        List<WebElement> objects = webDriver.findElements(element);
        WebElement returnElement = null;

        for (WebElement object : objects) {
            if (object.getAttribute(CommonActions.PLACEHOLDER).equalsIgnoreCase(text) &&
            		object.isDisplayed() && object.getAttribute(ConstantsOvc.NG_MODEL)
            		.equalsIgnoreCase(CustomerPage.arCustomerAttributeValue)) {
                LOGGER.info("String '" + text + "' matched with rendered  ==>"
                        + object.getAttribute(CommonActions.PLACEHOLDER));
                returnElement = object;
                break;
            }
        }
        LOGGER.info("getDisplayedElementWithPlaceholderNgModel completed " + returnElement);
        return returnElement;
    }
    
     // TODO: Following method and enterTextIntoInputBox to be re written to use unique modal class name
    /**
     * Enters text into the provided text box
     * This method uses the method the placehoderNgModel method above two attributes (placeholder and ng-model)
     *
     * @param text    Text to enter
     * @param textbox Placeholder text of the box itself
     */
    public void enterTextIntoInputBoxWithNgModel(String text, String textbox) {
        LOGGER.info("enterTextIntoInputBoxWithNgModel started");
        driver.waitForMilliseconds();
        WebElement inputBox = getDisplayedElementWithPlaceholderNgModel(inputTagBy, textbox);
        inputBox.clear();
        inputBox.sendKeys(text);
        LOGGER.info("enterTextIntoInputBoxWithNgModel completed");
    }

    /**
     * Creates a list of all the inputs on the customer details page and loops through them asserting they are disabled
     */
    public void verifyInputsOnCustomerDetailsDisabled() {
        LOGGER.info("verifyInputsOnCustomerDetailsDisabled started");
        List<WebElement> detailInputs = webDriver.findElements(CommonActions.inputTagBy);
        for (WebElement detailInput : detailInputs) {
            if (detailInput.isDisplayed()) {
                Boolean inputEnabled = detailInput.isEnabled();
                Assert.assertFalse("FAIL: input is enabled", inputEnabled);
            }
        }
        LOGGER.info("verifyInputsOnCustomerDetailsDisabled completed");
    }
}
