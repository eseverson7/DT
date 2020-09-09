package dtc.pages;

/**
 * Created by aarora on 10/10/16.
 */

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ProductDetailPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(ProductDetailPage.class.getName());

    public ProductDetailPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String ON_ORDER_AVAILABILITY = "Usually available in 1-2 days.";
    public static final String VIEW_SHOPPING_CART = "View Shopping Cart";
    public static final String CONTINUE_SHOPPING = "Continue Shopping";
    private static final String productSpecsSize = "pdp-specs__size--front";
    private static final String ZERO_STOCK = "0 in stock in the ";
    private static final String SIZE = "SIZE";
    private static final String TREAD_AND_TRACTION = "TREAD & TRACTION";
    private static final String SAFETY_AND_PERFORMANCE = "SAFETY & PERFORMANCE";
    private static final String STYLE_AND_CONSTRUCTION = "STYLE & CONSTRUCTION";

    private static List<String> tireSizeSectionAttributes = new ArrayList<>(Arrays.asList("Wheel Diameter",
            "Aspect Ratio", "Section Width (Cross Section)", "Overall Diameter", "Rim Width Range", "Rim Width",
            "Weight"));

    private static List<String> tireTreadSectionAttributes = new ArrayList<>(Arrays.asList("Tread Depth",
            "Tread Grade", "Traction Grade", "Tread Width"));

    private static List<String> tireSafetyPerformanceSectionAttributes = new ArrayList<>(Arrays.asList("Load Index",
            "Max PSI", "Speed Rating (up to X mph)", "Temperature Grade", "Vendor Product Number / MPN", "Warranty"));

    private static List<String> wheelSizeSectionAttributes = new ArrayList<>(Arrays.asList("Wheel Size", "Rim Diameter",
            "Wheel Width", "Bolt Pattern", "Offset", "Hub Bore Size", "Weight"));

    private static List<String> wheelStyleConstructionSectionAttributes = new ArrayList<>(Arrays.asList("Color",
            "Finish", "Accent", "Construction Material Description", "Wheel Pieces Quantity", "Number of Bolts"));

    private static List<String> wheelSafetyPerformanceSectionAttributes = new ArrayList<>(Arrays.asList(
            "Tire Load Capacity", "Load Rating", "Vendor Product Number / MPN"));

    private static final By productSpecsBy = By.className("pdp-specs__infoname");
    private static final By productDetailSectionHeaderBy = By.className("pdp-specs__list");
    private static final By warningMessageBy = By.className("pdp-info__unsupported");
    private static final By pdpPageBy = By.className("page-productDetails");

    public static final By tireItemInfoBy = By.className("pdp-info__tire__item");

    @FindBy(className = "pdp-info__quantity")
    public static WebElement quantityInfoBox;

    @FindBy(className = "pdp-info__productname")
    public static WebElement productNameInfo;

    @FindBy(className = "message-stock")
    public static WebElement inStockInfo;

    @FindBy(css = ".pdp-info__price > .pdp-info__newprice")
    public static WebElement productPrice;

    @FindBy(className = "auto-pdp-info-map-amount")
    public static WebElement mapProductPrice;

    @FindBy(className = "inventory-message-stock-count")
    public static WebElement inventoryMessageStockCount;

    /**
     * Asserts the product name on screen is the same as the product name passed in
     *
     * @param productName Product name that should appear on page
     */
    public void assertProductNameOnProductDetailPage(String productName) {
        LOGGER.info("assertProductNameOnProductDetailPage started");
        if (Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForPageToLoad();

        Assert.assertEquals("FAIL: The expected product name: \"" + productName
                        + "\" does NOT match the actual product name: \"" + productNameInfo.getText() + "\"!",
                productName, productNameInfo.getText());
        LOGGER.info("Confirmed that \"" + productName + "\" was listed in the Product Detail Page.");
        LOGGER.info("assertProductNameOnProductDetailPage completed");
    }

    /**
     * Verifies the itemID parameter equals the page item id
     *
     * @param itemId Validate actually item Id against this
     */
    public void assertItemIdOnProductDetailPage(String itemId) {
        LOGGER.info("assertItemIdOnProductDetailPage started");
        List<WebElement> products = webDriver.findElements(tireItemInfoBy);
        for (WebElement product : products) {
            if (product.getText().contains(itemId)) {
                Assert.assertTrue("FAIL: The expected itemID: \"" + itemId
                        + "\" does NOT contain in actual displayed itemID: \"" + product.getText()
                        + "\"!", product.getText().contains(itemId));
                LOGGER.info("Confirmed that \"" + itemId + "\" was listed in the Product Detail Page.");
                break;
            }
        }
        LOGGER.info("assertItemIdOnProductDetailPage completed");
    }

    /**
     * Logs message based on if product is in stock or not
     *
     * @return Boolean
     */
    private boolean isItemInStockOnProductDetailPage() {
        LOGGER.info("isItemInStockOnProductDetailPage started");
        boolean isInStock = inStockInfo.getText().equals(ConstantsDtc.IN_STOCK);
        if (isInStock)
            LOGGER.info("Confirmed that selected Item was listed in stock on the Product Detail Page.");
        else
            LOGGER.info("Confirmed that selected Item is currently not in stock on the Product Detail Page.");
        LOGGER.info("isItemInStockOnProductDetailPage completed");
        return isInStock;
    }

    /**
     * Asserts that the current product quantity matches desired/default
     * quantity in Product Page
     *
     * @param value The value to verify
     */
    public void assertProductQuantityInProductDetailPage(String value) {
        LOGGER.info("assertProductQuantityInProductDetailPage started");
        driver.waitForElementVisible(quantityInfoBox);
        Assert.assertEquals("FAIL: The expected product quantity of: \"" + value
                + "\" does NOT match the displayed quantity of: \"" + quantityInfoBox.getAttribute("Value")
                + "\"!", value, quantityInfoBox.getAttribute("Value"));
        LOGGER.info("Confirmed that default product quantity'" + value + "' matches with rendered quantity ==>"
                + quantityInfoBox.getAttribute("Value"));
        LOGGER.info("assertProductQuantityInProductDetailPage completed");
    }

    /**
     * Extracts the Product Specification
     *
     * @param specLabel - Specification label's value to extract
     * @return String
     */
    public String getPrdSpecificationValueText(String specLabel) {
        LOGGER.info("getPrdSpecificationValueText started");
        boolean prodSpecFound = false;

        List<WebElement> specs = webDriver.findElements(productSpecsBy);
        List<WebElement> specsValues = webDriver.findElements(By.className(productSpecsSize));
        int i = 0;
        for (WebElement spec : specs) {
            if (spec.getText().contains(specLabel)) {
                prodSpecFound = true;
                break;
            } else {
                i++;
            }
        }
        if (!prodSpecFound) {
            Assert.fail("FAIL: Product Specification \"" + specLabel + "\" NOT found on PDP!");
        }
        LOGGER.info("getPrdSpecificationValueText completed");
        return specsValues.get(i).getText();
    }

    /**
     * Assert provided "Product's Specific Specification Value" matches with the actual
     * value on product detail page
     *
     * @param specLabel The string product specification label
     * @param specValue The specValue to verify
     */
    public void assertProductSpecValue(String specLabel, String specValue) {
        LOGGER.info("assertProductSpecValue started");
        String getSpecValue = getPrdSpecificationValueText(specLabel);
        Assert.assertEquals(
                "FAIL: Spec didn't match: (" + specLabel + " :-> " + getSpecValue + " but expected:->  "
                        + specValue + ")!", specValue, getSpecValue);
        LOGGER.info("Spec matched: (" + specLabel + " :-> " + getSpecValue + " &  expected:->  " + specValue
                + ")");
        LOGGER.info("assertProductSpecValue completed");
    }

    /**
     * Checks if "Certificate for Repair Refund Replacement" text is displayed
     *
     * @param text The string text to check
     */
    public void assertProductSpecLabelVisible(String text) {
        LOGGER.info("assertProductSpecLabelVisible started");
        commonActions.assertElementWithTextIsVisible(productSpecsBy, text);
        LOGGER.info("assertProductSpecLabelVisible completed");
    }

    /**
     * Asserts "selected product's inventory stock details"
     */
    public void assertProductInventoryMessage() {
        LOGGER.info("assertProductInventoryMessage started");
        boolean inStockStatus = this.isItemInStockOnProductDetailPage();
        if (inStockStatus) {
            Assert.assertTrue("In Stock related inventory message not present",
                    inStockInfo.getText().contains(ConstantsDtc.IN_STOCK));
        } else {
            Assert.assertTrue(
                    "On Order inventory stock message (1) - " + ZERO_STOCK + " was not present",
                    driver.isTextPresentInPageSource(ZERO_STOCK));
            Assert.assertTrue(
                    "On Order inventory stock message(2) - " + ON_ORDER_AVAILABILITY + " was not present",
                    driver.isTextPresentInPageSource(ON_ORDER_AVAILABILITY));
        }
        LOGGER.info("assertProductInventoryMessage completed");
    }

    /**
     * Verifies the produce price for the first product listed
     *
     * @param price The price to verify
     */
    public void assertProductPrice(String price) {
        LOGGER.info("assertProductContainsPrice started");
        driver.waitForElementVisible(productPrice);
        Assert.assertTrue("FAIL: The expected price: \"" + price
                        + "\" does NOT match the actual displayed price: \"" + productPrice.getText() + "\"!",
                productPrice.getText().contains(price));
        LOGGER.info("Confirmed that \"" + price + "\" was listed for the product.");
        LOGGER.info("assertProductContainsPrice completed");
    }

    /**
     * Verifies the expected attributes for a given section of the product details page are displayed
     *
     * @param productType          The type of product e.g. Tire or Wheel
     * @param productDetailSection The section of the product details page containing the attributes to be validated
     */
    public void verifyExpectedTypeAttributesForProductDetailSection(String productType, String productDetailSection) {
        LOGGER.info("verifyExpectedTypeAttributesForProductDetailSection started for product type: '"
                + productType + "' product detail page section: '" + productDetailSection + "'");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(quantityInfoBox);
        List<String> expectedAttributesList = null;

        switch (productDetailSection) {
            case SIZE:
                if (productType.equalsIgnoreCase(Constants.TIRE)) {
                    expectedAttributesList = tireSizeSectionAttributes;
                } else {
                    expectedAttributesList = wheelSizeSectionAttributes;
                }
                break;
            case TREAD_AND_TRACTION:
                expectedAttributesList = tireTreadSectionAttributes;
                break;
            case SAFETY_AND_PERFORMANCE:
                if (productType.equalsIgnoreCase(Constants.TIRE)) {
                    expectedAttributesList = tireSafetyPerformanceSectionAttributes;
                } else {
                    expectedAttributesList = wheelSafetyPerformanceSectionAttributes;
                }
                break;
            case STYLE_AND_CONSTRUCTION:
                expectedAttributesList = wheelStyleConstructionSectionAttributes;
                break;
            default:
                Assert.fail("FAIL - Did not recognize the product detail section '" + productDetailSection +
                        "'! Please verify section arg matches the displayed case and/or wording");
        }

        commonActions.verifyPageSectionContainsAttributes(productDetailSection, productDetailSectionHeaderBy,
                expectedAttributesList);
        LOGGER.info("verifyExpectedTypeAttributesForProductDetailSection completed for product type: '"
                + productType + "' product detail page section: '" + productDetailSection + "'");
    }

    /**
     * Verifies the '...cannot ship...' warning message text and locations on PDP. IF displayStatus == Display,
     * verifies the warning container is displayed along with the messaging. ELSE verifies the warning container is
     * not displayed for the current product on the PDP
     *
     * @param displayStatus Determines if the Shipping Warning message message should or should not be displayed for
     *                      the current product on the PDP
     */
    public void verifyCannotShipMessageForProductDetailPage(String displayStatus) {
        LOGGER.info("verifyCannotShipMessageForProductDetailPage started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(quantityInfoBox);
        String locationValidationMessaging = ConstantsDtc.CANNOT_SHIP_LOCATIONS;

        if (displayStatus.equalsIgnoreCase(Constants.DISPLAYED)) {
            if (driver.isElementDisplayed(warningMessageBy)) {
                String warningMessageText = webDriver.findElement(warningMessageBy).getText();
                Assert.assertTrue("FAIL: PDP warning message text did not contain expected warning"
                                + " message! \n\t\tExpected string: '" + ConstantsDtc.CANNOT_SHIP_ITEMS_WARNING
                                + "' \n\t\tActual string: '" + warningMessageText + "'",
                        warningMessageText.contains(ConstantsDtc.CANNOT_SHIP_ITEMS_WARNING));

                if (Config.isSafari()) {
                    locationValidationMessaging = ConstantsDtc.CANNOT_SHIP_LOCATIONS_SAFARI;
                    warningMessageText = warningMessageText.replaceAll("[\\r\\n\\t]+", "");
                }

                Assert.assertTrue("FAIL: PDP warning message text did not contain expected locations!"
                        + " \n\t\tExpected string: '" + locationValidationMessaging + "' \n\t\tActual string: '"
                        + warningMessageText + "'", warningMessageText.contains(locationValidationMessaging));
            } else {
                Assert.fail("FAIL: Current product details page is NOT displaying the "
                        + "'...cannot ship __ items...' message!");
            }
        } else {
            Assert.assertTrue("FAIL: The '...cannot ship...' warning message was displayed when it was "
                    + "expected NOT to be!", !driver.isElementDisplayed(warningMessageBy, Constants.TWO_SEC_WAIT));
        }
        LOGGER.info("verifyCannotShipMessageForProductDetailPage completed");
    }

    /**
     * Verifies PDP page is present
     */
    public void verifyProductDetailsPage() {
        LOGGER.info("verifyProductDetailsPage started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();
        Assert.assertTrue("FAIL: PDP page didn't display",
                driver.isElementDisplayed(pdpPageBy));
        LOGGER.info("verifyProductDetailsPage completed");
    }

    /**
     * Verify the stock count in product details page
     *
     * @param stockMessage Text string that is expected to appear in the product details page
     */
    public void verifyStockCountTextPdp(String stockMessage) {
        LOGGER.info("verifyStockCountTextPdp started");
        driver.waitForPageToLoad();
        String myStoreStockMessage = inventoryMessageStockCount.getText();
        Assert.assertTrue("FAIL: The PDP inventory message stock count displayed: '" + myStoreStockMessage
                        + "' did NOT contain the" + " expected text '" + stockMessage + "'!",
                myStoreStockMessage.equals(stockMessage));
        LOGGER.info("verifyStockCountTextPdp completed");
    }
}