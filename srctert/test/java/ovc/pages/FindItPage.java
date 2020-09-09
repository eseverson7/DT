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
 * Created by eseverson on 9/15/2017.
 */
public class FindItPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private ParentElementsPage parentElementsPage;
    private final Logger LOGGER = Logger.getLogger(FindItPage.class.getName());

    public FindItPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        parentElementsPage = new ParentElementsPage(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "findItInfo")
    private static WebElement findItHeader;

    public static final By quantityTxtBox = By.xpath("//input[@ng-model=\'form.qty\']");

    public static final By commentsTxtBox = By.cssSelector(".stockReviewFields textarea");

    public static final By contactNumberTxtBox = By.xpath("//input[@ng-model=\'form.contactNo\']");

    private static final By vendorNumber = By.xpath("//input[@ng-model=\'form.vendorId\']");

    public static final String QUANTITY = "Quantity";

    public static final String CONTACT_NUMBER = "Contact Number";

    private static final String VENDOR_NUMBER = "Vendor Number";

    public static final String NEARBY_STORES = "Nearby Stores";

    public static final String MANAGED_INVENTORY = "Managed Inventory";

    /**
     * Asserts page title contains displayed text
     *
     * @param text String that the title should display
     */
    public void assertFindItPageTitle(String text) {
        LOGGER.info("assertFindItPageTitle started");
        driver.waitForElementClickable(findItHeader);
        Assert.assertTrue("FAIL: Find It! page header actually was: " + findItHeader.getText(),
                findItHeader.getText().contains(text));
        LOGGER.info("assertFindItPageTitle completed");
    }

    /**
     * Selects expandable field on page
     *
     * @param text Name of the field to click
     */
    public void selectExpandableField(String text) {
        LOGGER.info("selectExpandableField started");
        driver.waitForElementClickable(findItHeader);
        WebElement pageEle = parentElementsPage.returnPageObjectElement(ConstantsOvc.FIND_IT);
        WebElement field = commonActions.getOVCElementWithText(pageEle, By.tagName(Constants.SPAN), text);
        field.click();
        LOGGER.info("selectExpandableField completed");
    }

    /**
     * Fills the specified field with the specified value
     *
     * @param field Field of Find It element to fill
     * @param value String value to fill field with
     */
    public void enterFindItTextBoxValue(String field, String value) {
        LOGGER.info("enterFindItTextBoxValue started");
        WebElement fieldEle = null;
        WebElement findItPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.FIND_IT);

        if (field.equalsIgnoreCase(QUANTITY)) {
            fieldEle = findItPage.findElement(quantityTxtBox);
        } else if (field.equalsIgnoreCase(ConstantsOvc.COMMENTS)) {
            fieldEle = findItPage.findElement(commentsTxtBox);
        } else if (field.equalsIgnoreCase(CONTACT_NUMBER)) {
            fieldEle = findItPage.findElement(contactNumberTxtBox);
        }

        fieldEle.click();
        fieldEle.clear();
        fieldEle.sendKeys(value);
        driver.waitForPageToLoad();

        LOGGER.info("enterFindItTextBoxValue completed");
    }

    /**
     * Verifies the specified field with the specified value
     *
     * @param field Field of Find It element
     * @param value String value the field should have
     */
    public void assertFindItTextBoxValue(String field, String value) {
        LOGGER.info("assertFindItTextBoxValue started");
        WebElement fieldEle = null;
        WebElement findItPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.FIND_IT);

        if (field.equalsIgnoreCase(QUANTITY)) {
            fieldEle = findItPage.findElement(quantityTxtBox);
        } else if (field.equalsIgnoreCase(ConstantsOvc.COMMENTS)) {
            fieldEle = findItPage.findElement(commentsTxtBox);
        } else if (field.equalsIgnoreCase(CONTACT_NUMBER)) {
            fieldEle = findItPage.findElement(contactNumberTxtBox);
        } else if (field.equalsIgnoreCase(VENDOR_NUMBER)) {
            fieldEle = findItPage.findElement(vendorNumber);
        }

        Assert.assertTrue("FAIL: Find It page text box: " + field + " did not contain the value: " + value +
                ". Instead was: " + fieldEle.getAttribute("value"), fieldEle.getAttribute("value").equals(value));

        driver.waitForPageToLoad();
        LOGGER.info("assertFindItTextBoxValue completed");
    }

    /**
     * Clicks the displayed "Order" button on the page
     *
     * @param site Numerical value of the site displayed
     */
    public void selectDisplayedOrderButton(String site) {
        LOGGER.info("selectDisplayedOrderButton started");
        driver.waitForPageToLoad();
        WebElement button;
        boolean found = false;

        WebElement returnPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.FIND_IT);
        List<WebElement> rows = returnPage.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                if (row.getText().contains(site)) {
                    button = row.findElement(By.cssSelector(Constants.BUTTON));
                    button.click();
                    found = true;
                    break;
                }
            }
        }

        if (!found)
            Assert.fail("FAIL: Unable to find Order button with Location site: " + site);
        driver.waitForPageToLoad();

        LOGGER.info("selectDisplayedOrderButton completed");
    }
}
