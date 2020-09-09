package ovc.pages;

import com.google.common.base.CaseFormat;
import common.Constants;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by eseverson on 6/27/2017.
 */
public class LookupPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(LookupPage.class.getName());
    private CommonActions commonActions;
    private ParentElementsPage parentElementsPage;


    public LookupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
        parentElementsPage = new ParentElementsPage(driver);
    }

    @FindBy(id = "scanTargetnameTxtBx")
    public static WebElement tireName;

    @FindBy(id = "scanTargetskuTxtBx")
    public static WebElement tireSku;

    @FindBy(className = "fa-angle-down")
    public static WebElement moreOptionsArrow;

    @FindBy(className = "pdp-cartbutton")
    public static WebElement pdpCartButton;

    @FindBy(id = "scanTargetgtinTxtBx")
    public static WebElement miscTireGtin;

    @FindBy(id = "scanTargetvpnTxtBx")
    public static WebElement miscTireVpn;

    public static final By searchContainerBy = By.className("srchContainer");

    public static final By lookupPageBindingBy = By.className("ng-binding");

    public static final By productWrapperBy = By.className("productWrapper");

    public static final By lookupPageProductBy = By.className("productDetails");

    public static final By lookupPageDropDownsBy = By.className("col-sm-10");

    public static final By pdpPageBy = By.className("page-productDetails");

    public static final By pdpCartButtonBy = By.className("pdp-cartbutton");

    public static String[] tireDropDownLookup = {"brandCategory", "loadRange", "aspectRatio", "runFlat", "wheelRimDiameter"};

    public static String[] wheelDropDownLookup = {"brandCategory", "rimDiameter", " wheelFinish", " wheelColor"};

    public static final String MISC = "Misc";

    public static final String GTIN = "gtin";

    public static String GTIN_NUMBER = "";

    /**
     * Selects value from dropdown
     *
     * @param value Text to be selected
     */
    public void selectOvcDropdownValue(String value) {
        LOGGER.info("selectOvcDropdownValue started");

        driver.waitForPageToLoad();
        WebElement container = driver.getDisplayedElement(CommonActions.searchContainerBy, Constants.ONE_SEC_WAIT);
        WebElement dropdown = container.findElement(CommonActions.selectTagBy);
        driver.selectFromDropdownByVisibleText(dropdown, value);

        LOGGER.info("selectOvcDropdownValue completed");
    }

    /**
     * Selects dropdown container for **Applitools** verification
     */
    public void selectOvcDropdownContainer() {
        LOGGER.info("selectOvcDropdownContainer started");
        WebElement container = driver.getDisplayedElement(CommonActions.searchContainerBy, Constants.ONE_SEC_WAIT);
        WebElement dropdown = container.findElement(CommonActions.selectTagBy);
        dropdown.click();
        LOGGER.info("selectOvcDropdownContainer started");
    }

    /**
     * Selects an item from within the table results
     *
     * @param item Value within item row
     */
    public void selectTableRowResult(String item) {
        LOGGER.info("selectTableRowResult started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        boolean found = false;

        List<WebElement> rows = CommonActions.resultsTable.findElements(CommonActions.tableRowBy);
        for (WebElement row : rows) {
            if (row.getText().contains(item)) {
                row.click();
                found = true;
                break;
            }
        }
        if (!found)
            Assert.fail("FAIL: Item " + item + " was not found on the results screen.");
        LOGGER.info("selectTableRowResult completed");
    }

    /**
     * Enters tires name and SKU on Lookup page
     *
     * @param name is tire name
     * @param sku  is tire SKU
     */
    public void enterTireNameAndSku(String name, String sku) {
        LOGGER.info("enterTireNameAndSku started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(tireName);
        tireName.sendKeys(name);
        tireSku.sendKeys(sku);
        LOGGER.info("enterTireNameAndSku completed");
    }

    /**
     * Selects an option from the specified dropdown on the Lookup page
     *
     * @param selectionOption String containing the displayed text value of the dropdown option to be selected
     * @param dropdownName    String containing the dropdown name
     */
    public void selectOptionFromLookupPageDropdown(String selectionOption, String dropdownName) {
        LOGGER.info("selectOptionFromLookupPageDropdown started with option: '" + selectionOption
                + "' and dropdown: '" + dropdownName + "'");
        driver.waitForPageToLoad();
        dropdownName = dropdownName.replaceAll(" ", "_").toUpperCase();

        if (driver.isElementDisplayed(moreOptionsArrow, Constants.TWO_SEC_WAIT))
            moreOptionsArrow.click();

        WebElement dropdownEle = driver.getElementWithText(CommonActions.selectTagBy,
                CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, dropdownName));
        driver.selectFromDropdownByVisibleText(dropdownEle, selectionOption);
        LOGGER.info("selectOptionFromLookupPageDropdown completed with option: '" + selectionOption
                + "' and dropdown: '" + dropdownName + "'");
    }

    /**
     * Selects values for "ADD MISC" Wheel and Tire dropdown values
     *
     * @param selectionOption String containing the displayed text value of the dropdown option to be selected
     * @param dropdownName    String containing the dropdown name
     */
    public void selectOptionFromAddMiscProductDropdown(String selectionOption, String dropdownName) {
        LOGGER.info("selectOptionFromAddMiscProductDropdown started with option: '" + selectionOption
                + "' and dropdown: '" + dropdownName + "'");

        if (dropdownName.contains(MISC));
            dropdownName = dropdownName.substring(5);
        dropdownName = dropdownName.replaceAll("_", " ");

        By locatorString = By.xpath("//label[contains(text(),'" + dropdownName + "')]//following-sibling::select");
        WebElement dropdown = webDriver.findElement(locatorString);
        driver.selectFromDropdownByVisibleText(dropdown, selectionOption);
        LOGGER.info("selectOptionFromAddMiscProductDropdown completed with option: '" + selectionOption
                + "' and dropdown: '" + dropdownName + "'");
    }

    /**
     * Method takes in String[] and verifies all values are present on Page
     *
     * @param values String of values that should be present on page
     */
    public void verifyLookupDropDownExists(String[] values) {
        LOGGER.info("verifyLookupDropDownExists started");

        driver.waitForPageToLoad();
        if (driver.isElementDisplayed(moreOptionsArrow, Constants.TWO_SEC_WAIT))
            moreOptionsArrow.click();

        List<String> valuesNotFound = new ArrayList<String>();
        WebElement returnPage = parentElementsPage.returnPageObjectElement(ConstantsOvc.LOOKUP);

        for (String value : values) {
            try {
                driver.waitForMilliseconds();
                WebElement element = commonActions.getOVCElementWithText(returnPage, lookupPageBindingBy, value);
                if (element == null) {
                    valuesNotFound.add(value);
                } else {
                    LOGGER.info("Lookup option found: " + value);
                }
            } catch (NullPointerException e) {
                valuesNotFound.add(value);
            }
        }
        Assert.assertTrue("FAIL: The following lookup options were not found: " + valuesNotFound,
                valuesNotFound.size() == 0);
        LOGGER.info("verifyLookupDropDownExists completed");
    }


    /**
     * Method takes in String[] and verifies all are removed from the Page
     *
     * @param values String of values that should be removed from the page
     */
    public void verifyLookupDropDownIsRemoved(String[] values) {
        LOGGER.info("verifyLookupDropDownIsRemoved started");

        boolean isPresent = false;

        for (String value : values) {

            WebElement lookupDropDownEle = driver.getElementWithText(lookupPageDropDownsBy, value);

            if (lookupDropDownEle != null && lookupDropDownEle.isDisplayed()) {
                Assert.fail("Expected: Lookup dropdowns should not be displayed. Actual: Lookup dropdowns were displayed.");
            }
        }
        LOGGER.info("verifyLookupDropDownIsRemoved completed");
    }

    /**
     * Verifies that each product in the Lookup results contains the specified text
     *
     * @param text The text to verify
     */
    public void verifyProductsContainText(String text) {
        LOGGER.info("verifyProductsContainText started");
        List<WebElement> products = webDriver.findElements(productWrapperBy);

        if (text.equalsIgnoreCase(GTIN))
            text = GTIN_NUMBER;

        for (WebElement product : products) {
            Assert.assertTrue("FAIL: A product in Lookup results did not contain the text: " +
                    text, product.getText().toUpperCase().contains(text.toUpperCase()));
        }

        LOGGER.info("verifyProductsContainText completed");
    }

    /**
     * Verifies that no products are listed on the Lookup page
     */
    public void verifyNoProductsListed() {
        LOGGER.info("verifyNoProductsListed started");
        Assert.assertTrue("FAIL: No products should have been listed on the Lookup page",
                !driver.isElementDisplayed(productWrapperBy));
        LOGGER.info("verifyNoProductsListed completed");
    }

    /**
     * Verifies PDP page is present
     */
    public void verifyProductListPage() {
        LOGGER.info("verifyProductListPage started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        Assert.assertTrue("FAIL: PDP page didn't display",
                driver.isElementDisplayed(pdpPageBy));
        LOGGER.info("verifyProductListPage completed");
    }

    /**
     * Clicks on add to receipt button
     *
     * @param text The text on the button
     */
    public void clicksOnButton(String text) {
        LOGGER.info("clicksOnButton started");
        driver.waitForMilliseconds();
        driver.waitForPageToLoad();
        driver.jsClick(pdpCartButton);
        driver.waitForMilliseconds();
        LOGGER.info("clicksOnButton completed" + text);
    }

    /**
     * Enters Misc GTIN and VPN on Lookup page
     *
     * @param gtin is Misc. Tire/Wheel GTIN
     * @param vpn  is Misc. Tire/Wheel VPN
     */
    public void enterMiscGtinVpn(String gtin, String vpn) {
        LOGGER.info("enterMiscGtinVpn started");
        driver.waitForElementVisible(miscTireVpn);
        miscTireGtin.sendKeys(gtin);
        //TODO - For now we are not passing vpn - will be added in future
        //miscTireVpn.sendKeys(vpn);
        LOGGER.info("enterMiscGtinVpn completed");
    }
}