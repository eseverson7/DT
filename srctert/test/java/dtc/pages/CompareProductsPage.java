package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/22/16.
 */
public class CompareProductsPage {

    private final Driver driver;
    private final WebDriver webDriver;
    private final CommonActions commonActions;
    private final ProductListPage productListPage;
    private final Logger LOGGER = Logger.getLogger(CompareProductsPage.class.getName());

    public CompareProductsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        productListPage = new ProductListPage(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String BADGE = "BADGE";
    private static final String VALUE = "VALUE";
    private static final String GREEN_COLOR_THREE_DIGIT = "rgb(70, 186, 43)";
    private static final String GREEN_COLOR_FOUR_DIGIT = "rgba(70, 186, 43, 1)";
    private static final String GREY_COLOR_THREE_DIGIT = "rgb(153, 153, 153)";
    private static final String GREY_COLOR_FOUR_DIGIT = "rgba(153, 153, 153, 1)";
    private static final String BLUE_COLOR_THREE_DIGIT = "rgb(0, 174, 239)";
    private static final String BLUE_COLOR_FOUR_DIGIT = "rgba(0, 174, 239, 1)";
    private static final String GOOD = "GOOD";
    private static final String BETTER = "BETTER";
    private static final String BEST = "BEST";
    private static final String SIZE_SPECIFICATIONS = "Size specifications";
    private static final String TREAD_TRACTION_SPECIFICATIONS = "Tread and traction specifications";
    private static final String SAFETY_PERFORMANCE_SPECIFICATIONS = "Safety and performance specifications";
    private static final String SHIPPING_RESTRICTIONS = "Shipping Restrictions";
    private static final String EMPTY_STRING = "";

    private static String[] compareDtValues = {
            "Good, Better, Best", "Customer Rating", "Wheel Diameter", "Aspect Ratio", "Section Width",
            "Overall Diameter", "Rim Width Range", "Weight", "Tread Depth", "Vendor Product Number / MPN"
    };

    private static String[] compareH3Values = {
            "customer reviews", "Size specifications", "Tread and traction specifications",
            "Safety and performance specifications"
    };

    private static String[] compareH3ValuesMobile = {
            "customer reviews", "Size specifications"
    };

    private static String[] compareH5Values = {
            "Overall Rating", "Ride Comfort", "Cornering/Steering", "Ride Noise", "Tread Life", "Dry Traction",
            "Wet Traction", "Winter Traction", "Buy tire again"
    };

    private static String[] compareH5ValuesMobile = {
            "Overall Rating", "Ride Comfort", "Cornering / Steering", "Ride Noise", "Tread Life", "Dry Traction",
            "Wet Traction", "Winter Traction", "Buy tire again"
    };

    private static String[] compareDivValues = {
            "Warranty", "Tread Grade", "Traction Grade", "Max Load", "Speed Rating", "Temperature Grade"
    };

    private static List<String> sizeSectionAttributes = new ArrayList<>(Arrays.asList("Wheel Diameter", "Aspect Ratio",
            "Section Width (Cross Section)", "Overall Diameter", "Rim Width Range", "Weight"));

    private static List<String> treadSectionAttributes = new ArrayList<>(Arrays.asList("Tread Depth",
            "Tread Grade", "Traction Grade"));

    private static List<String> safetyPerformanceSectionAttributes = new ArrayList<>(Arrays.asList("Max Load",
            "Max PSI", "Speed Rating", "Temperature Grade", "Vendor Product Number / MPN"));

    private String productTitleByItemNumber = "//a[contains(@href, \"%s\")]//h5";

    private static final Integer COMPARE_MEDIUM = 2;

    private static final By compareProductHeadersBy = By.cssSelector(".compare-main-section__columns");

    private static final By actualProdcutPriceBy = By.cssSelector(".pdp-info__newprice");

    private static final By addToCartBy = By.className("compare-main-section__cart-button");

    private static final By compareMainSectionBy = By.className("compare-main-section__action");

    private static final By mobileColumnsBy = By.className("compare-column");

    private static final By mobileProductBy = By.className("compare-pdp__img-info");

    private static final By iconCloseBy = By.className("icon-close");

    private static final By compareSectionHeaderBy = By.className("specWrap");

    private static final By compareDescriptionListTagBy = By.tagName("dl");

    private static final By compareDescriptionDataTagBy = By.tagName("dd");

    private static final By compareAddItemBy = By.className("js-compare-add");

    @FindBy(className = "compare")
    public static WebElement compareClass;

    @FindBy(className = "compare-mobile")
    public static WebElement compareMobile;

    @FindBy(id = "productgbbDlId")
    public static WebElement gBBContainer;

    @FindBy(linkText = "Remove All")
    public static WebElement removeAll;

    @FindBy(linkText = "Undo Remove")
    public static WebElement undoRemove;

    @FindBy(className = "icon-close")
    public static WebElement closeButton;

    /**
     * Select first item on the Compare Products page to add to the shopping cart
     */
    public void addFirstItemToCartAndClose() {
        LOGGER.info("addFirstItemToCartAndClose started");
        if (Config.isMobilePhone()) {
            driver.waitForPageToLoad();
            driver.waitForElementClickable(CommonActions.btnDefault);
        } else {
            driver.waitForElementClickable(webDriver.findElement(addToCartBy));
        }

        List<WebElement> addToCartButtons = webDriver.findElements(addToCartBy);

        try {
            WebElement firstCartBtn = addToCartButtons.get(0);
            driver.jsScrollToElement(firstCartBtn);
            driver.jsClick(firstCartBtn);
            productListPage.selectAction(Constants.CLOSE);
        } catch (Exception ex) {
            Assert.fail("FAIL: There was an error when attempting to add the first item on the compare product page" +
                    " to the cart! Stack trace: " + ex);
        }
        LOGGER.info("addFirstItemToCartAndClose completed");
    }

    /**
     * Returns the VALUE of the element associated with the passed in tag attribute
     *
     * @param row    Parent webelement
     * @param itemID The ID of the item you'd like returned
     * @return String
     */
    public String returnTagValue(WebElement row, String itemID) {
        LOGGER.info("returnTagValue started");
        WebElement addSection = row.findElement(compareMainSectionBy);
        List<WebElement> inputs = addSection.findElements(By.tagName("input"));
        String inputValue = null;

        for (WebElement input : inputs) {
            inputValue = input.getAttribute(VALUE);
            if (inputValue.equalsIgnoreCase(itemID)) {
                LOGGER.info("returnTagValue completed");
                return inputValue;
            }
        }
        LOGGER.info("returnTagValue completed");
        return inputValue;
    }


    /**
     * Verifies all the values in associated strings
     * compareDTValues, compareH3Values, compareH5Values and compareDivValues (above)
     * are present on the page
     */
    public void verifyCompareProductsPageElements() {
        LOGGER.info("verifyCompareProductsPageElements started");
        WebElement compareElement;

        if (Config.isMobilePhone()) {
            compareElement = compareMobile;
        } else {
            compareElement = compareClass;
        }

        verifyTableValues(compareElement, compareDtValues);
        verifyTableValues(compareElement, compareDivValues);

        if (Config.isMobilePhone()) {
            verifyTableValues(compareElement, compareH5ValuesMobile);
            verifyTableValues(compareElement, compareH3ValuesMobile);
        } else {
            verifyTableValues(compareElement, compareH5Values);
            verifyTableValues(compareElement, compareH3Values);
        }
        LOGGER.info("verifyCompareProductsPageElements ended");
    }


    /**
     * Method takes in String[] and verifies all values are present on page
     *
     * @param compareElement WebElement to use for mobile vs. web page
     * @param values         String of values that should be present on page
     */
    private void verifyTableValues(WebElement compareElement, String[] values) {
        LOGGER.info("verifyCompareElements started");
        for (String value : values) {
            boolean isPresent;
            isPresent = compareElement.getText().toLowerCase().contains(value.toLowerCase());
            if (!value.equalsIgnoreCase(ConstantsDtc.WARRANTY)) {
                Assert.assertTrue("\"" + value + "\" was not found on Compare Products page!", isPresent);
            } else {
                if (isPresent) {
                    LOGGER.info("Warranty found on Compare Products page!");
                } else {
                    LOGGER.info("Warranty was not found on Compare Products page!");
                }
            }
        }
        LOGGER.info("verifyCompareElements ended");
    }


    /**
     * Verifies values being passed in are present on the compare products screen
     *
     * @param map      Input values from PLP to verify appear on the compare products page
     * @param quantity Number of products being compared (2 or 3)
     */
    public void verifyTopProductDetails(HashMap map, int quantity) {
        LOGGER.info("verifyTopProductDetails started");
        HashMap<String, ArrayList<String>> values = map;

        //TODO: retest once new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForElementClickable(removeAll);

        if (Config.isMobilePhone()) {

            List<WebElement> details = webDriver.findElements(mobileColumnsBy);
            int i = details.size() - 1;
            int end = details.size() - 1;
            if (quantity == COMPARE_MEDIUM) {
                i = details.size() - 2;
            }

            for (WebElement detail : details.subList(0, end)) {
                WebElement product = detail.findElement(mobileProductBy);
                String expectedProduct = values.get(ConstantsDtc.PRODUCT).get(i);

                //TODO: AB, 6/15/2017, mobile says See Price in Cart or Out of Stock for certain products, making
                //TODO(cont): this validation too brittle
                //WebElement price = detail.findElement(actualProdcutPriceBy);
                //String previousPrice = values.get(ConstantsDtc.PRICE).get(i);
                //String actualPrice = price.getAttribute(Constants.OUTERTEXT);
                //Assert.assertEquals("Product prices do not match. Expected: " + previousPrice +
                //        ". Actual was: " + actualPrice, previousPrice, actualPrice);

                Assert.assertEquals("Product names do not match. Expected: " + expectedProduct +
                        ". Actual was: " + product, expectedProduct, product.getText());
                i--;
            }
        } else {
            List<WebElement> details = webDriver.findElements(compareProductHeadersBy);
            int start = 1;
            int end = details.size();

            if (quantity == COMPARE_MEDIUM) {
                end = end - 1;
            }
            for (WebElement detail : details.subList(start, end)) {
                String actualValue = detail.getText();
                String actualBrand = actualValue.split("\\s")[0].trim();
                String actualProduct = actualValue.split("\\$")[0].split("\\s", 2)[1].split("\\s")[0].trim();
                String inventoryMessageMystore = detail.findElement(CommonActions.inventoryMessageBy).getText();

                Assert.assertTrue("FAIL: Expected BRAND VALUE: " + values.get(ConstantsDtc.BRAND)
                        + ", but actual VALUE was: " + actualBrand + " ! "
                        , Arrays.asList(values.get(ConstantsDtc.BRAND)).toString().contains(actualBrand));

                Assert.assertTrue("FAIL: Expected PRODUCT VALUE: " + values.get(ConstantsDtc.PRODUCT)
                        + ", but actual VALUE was: " + actualProduct + " ! "
                        , Arrays.asList(values.get(ConstantsDtc.PRODUCT)).toString().contains(actualProduct));

                Assert.assertTrue("FAIL: Expected inventory message mystore VALUE: "
                        + values.get(ConstantsDtc.INVENTORY_MESSAGE)
                        + ", but actual VALUE was: " + inventoryMessageMystore
                        + " ! ", Arrays.asList(values.get(ConstantsDtc.INVENTORY_MESSAGE)).toString().contains(inventoryMessageMystore));

                if (values.get(ConstantsDtc.PRICE).toString().equalsIgnoreCase(ConstantsDtc.PRICE_NOT_DISPLAYED)) {
                    break;
                } else {
                    String actualPrice = actualValue.split("\\$")[1].split("\\s")[0];
                    Assert.assertTrue("FAIL: Expected PRICE VALUE " + values.get(ConstantsDtc.PRICE) + " did NOT contain: "
                                    + actualPrice + "!",
                            Arrays.asList(values.get(ConstantsDtc.PRICE)).toString().contains(actualPrice));
                }
            }
            LOGGER.info("verifyTopProductDetails completed");
        }
    }

    /**
     * Verifies values Good, Better and Best appear on the page
     * as well as the associated color of each tag
     */
    public void assertGoodBetterBestBackgroundColor() {
        LOGGER.info("assertGoodBetterBestBackgroundColor started");
        List<WebElement> buttons = gBBContainer.findElements(By.className(BADGE));
        for (WebElement button : buttons) {
            String text = button.getText();
            String color = button.getCssValue(Constants.BACKGROUND_COLOR);
            String colorValueFourDigit = "";
            String colorValueThreeDigit = "";
            boolean colorMatchFound = false;

            switch (text.toUpperCase()) {
                case GOOD:
                    colorValueFourDigit = GREY_COLOR_FOUR_DIGIT;
                    colorValueThreeDigit = GREY_COLOR_THREE_DIGIT;
                    break;
                case BETTER:
                    colorValueFourDigit = BLUE_COLOR_FOUR_DIGIT;
                    colorValueThreeDigit = BLUE_COLOR_THREE_DIGIT;
                    break;
                case BEST:
                    colorValueFourDigit = GREEN_COLOR_FOUR_DIGIT;
                    colorValueThreeDigit = GREEN_COLOR_THREE_DIGIT;
                    break;
                default:
                    Assert.fail("FAIL: Button text was NOT: GOOD, BETTER or BEST!");
            }

            if (color.equalsIgnoreCase(colorValueFourDigit) || color.equalsIgnoreCase(colorValueThreeDigit))
                colorMatchFound = true;

            Assert.assertTrue("FAIL: The expected color values (4 digit: \""
                            + colorValueFourDigit + "\" OR 3 digit: \"" + colorValueThreeDigit
                            + "\") did NOT match the actual color: \"" + color + "\"!",
                    colorMatchFound);
        }
        LOGGER.info("assertGoodBetterBestBackgroundColor Completed");
    }


    /**
     * Performs remove all actions
     */
    public void clickRemoveAll() {
        LOGGER.info("clickRemoveAll started");
        removeAll.click();
        if (Config.getBrowser().equalsIgnoreCase(Constants.SAFARI_BROWSER))
            driver.waitForMilliseconds();
        LOGGER.info("clickRemoveAll completed");
    }


    /**
     * Clicks X button next to the first item being compared
     */
    public void clickXNextToFirstComparedItem() {
        LOGGER.info("clickXNextToComparedItem started");
        if (Config.isMobilePhone()) {
            List<WebElement> columns = webDriver.findElements(mobileColumnsBy);
            for (WebElement column : columns) {
                if (column.isDisplayed()) {
                    column.findElement(iconCloseBy).click();
                    break;
                }
            }
        } else {
            driver.waitForElementVisible(closeButton);
            closeButton.click();
        }

        LOGGER.info("clickXNextToComparedItem completed");
    }


    /**
     * Performs undo-remove all actions
     */
    public void clickUndoRemove() {
        LOGGER.info("clickUndoRemove started");
        driver.waitForElementClickable(undoRemove);
        driver.jsClick(undoRemove);
        //TODO: IE change - may make this a conditional
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        LOGGER.info("clickUndoRemove completed");
    }

    /**
     * Verifies the expected attributes for a given section of the comparison page are displayed
     *
     * @param compareSection The section of the compare page containing the attributes to be validated
     */
    public void verifyExpectedAttributesForCompareSection(String compareSection) {
        LOGGER.info("verifyExpectedAttributesForCompareSection started for compare page section: '"
                + compareSection + "'");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(removeAll);
        List<String> expectedAttributesList = null;

        switch (compareSection) {
            case SIZE_SPECIFICATIONS:
                expectedAttributesList = sizeSectionAttributes;
                break;
            case TREAD_TRACTION_SPECIFICATIONS:
                expectedAttributesList = treadSectionAttributes;
                break;
            case SAFETY_PERFORMANCE_SPECIFICATIONS:
                expectedAttributesList = safetyPerformanceSectionAttributes;
                break;
            default:
                Assert.fail("FAIL - Did not recognize the compare section to validate! " +
                        "Please verify section arg matches the displayed case and/or wording.");
        }

        commonActions.verifyPageSectionContainsAttributes(compareSection, compareSectionHeaderBy,
                expectedAttributesList);
        LOGGER.info("verifyExpectedAttributesForCompareSection completed for compare page section: '"
                + compareSection + "'");
    }

    /**
     * Verifies the 'Add to cart' buttons on the Compare Products page are all enabled
     */
    public void verifyAddToCartEnabledForProducts() {
        LOGGER.info("verifyAddToCartEnabledForProducts started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(removeAll);

        List<WebElement> addToCartButtonsList = webDriver.findElements(addToCartBy);
        for (WebElement addToCartButton : addToCartButtonsList) {
            Assert.assertTrue("FAIL: One of the 'Add to cart' buttons was NOT enabled!",
                    addToCartButton.isEnabled());
        }
        LOGGER.info("verifyAddToCartEnabledForProducts completed");
    }

    /**
     * Verifies that all the products on the compare products page contain the '...cannot ship ____ items...' messaging
     * and listed locations. IF displayStatus == Display, the method allows for up to one cell to contain empty string,
     * accounting for an instance where only two products are compared. ELSE method validates the
     * 'Shipping Restrictions' row is not displayed on the Compare Products page.
     *
     * @param displayStatus Determines if the 'Shipping Restrictions' row (and messaging) should or should not be
     *                      displayed for the currently compared products
     */
    public void verifyCannotShipMessageForAllProducts(String displayStatus) {
        LOGGER.info("verifyCannotShipMessageForAllProducts started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(removeAll);
        String locationValidationMessaging = ConstantsDtc.CANNOT_SHIP_LOCATIONS;

        WebElement shippingRestrictionRowEle = driver.getElementWithText(compareDescriptionListTagBy,
                SHIPPING_RESTRICTIONS);

        if (displayStatus.equalsIgnoreCase(Constants.DISPLAYED)) {
            List<WebElement> shippingRestrictionCellList = shippingRestrictionRowEle.findElements
                    (compareDescriptionDataTagBy);

            int cellsMissingWarningMessage = 0;
            for (WebElement cell : shippingRestrictionCellList) {
                String cellText = cell.getText();

                if (cellText.equalsIgnoreCase(EMPTY_STRING)) {
                    cellsMissingWarningMessage++;
                } else {
                    Assert.assertTrue("FAIL: Shipping Restrictions cell text did not contain expected warning"
                                    + " message! \n\t\tExpected string: '" + ConstantsDtc.CANNOT_SHIP_ITEMS_WARNING
                                    + "' \n\t\tActual string: '" + cellText + "'",
                            cellText.contains(ConstantsDtc.CANNOT_SHIP_ITEMS_WARNING));

                    if (Config.isSafari()) {
                        locationValidationMessaging = ConstantsDtc.CANNOT_SHIP_LOCATIONS_SAFARI;
                        cellText = cellText.replaceAll("[\\r\\n\\t]+", "");
                    }

                    Assert.assertTrue("FAIL: Shipping Restrictions cell text did not contain expected "
                                    + "locations! \n\t\tExpected string: '" + locationValidationMessaging
                                    + "' \n\t\tActual string: '" + cellText + "'",
                            cellText.contains(locationValidationMessaging));
                }

                Assert.assertTrue("FAIL: More than one of the compared products is missing the"
                        + " '...cannot ship items...' messaging! Please verify items used are "
                        + "shipping restricted!", cellsMissingWarningMessage <= 1);
            }
        } else {
            Assert.assertTrue("FAIL: The 'Shipping Restrictions' row was displayed for compared products!",
                    shippingRestrictionRowEle == null);
        }
        LOGGER.info("verifyCannotShipMessageForAllProducts completed");
    }

    /**
     * Selects the item / product title by the specified item number
     *
     * @param itemNumber Item number of the product to select in order to view its product details
     */
    public void viewItemDetailsForProductByItemNumber(String itemNumber) {
        LOGGER.info("viewItemDetailsForProductByItemNumber started with item number: '" + itemNumber + "'");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(removeAll);

        webDriver.findElement(By.xpath
                (String.format(productTitleByItemNumber, itemNumber))).click();
        LOGGER.info("viewItemDetailsForProductByItemNumber completed with item number: '" + itemNumber + "'");
    }

    /**
     * Selects "Add an item" to compare products
     */
    public void selectAddAnItemToCompare() {
        LOGGER.info("selectAddAnItemToCompare started");
        driver.waitForElementClickable(compareAddItemBy);
        webDriver.findElement(compareAddItemBy).click();
        driver.waitForPageToLoad();
        LOGGER.info("selectAddAnItemToCompare completed");
    }
}