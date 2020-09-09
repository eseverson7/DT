package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import dtc.data.Customer;
import dtc.steps.CommonActionsSteps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/22/16.
 */
public class CartPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private Customer customer;
    private final Logger LOGGER = Logger.getLogger(CartPage.class.getName());

    public CartPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        customer = new Customer();
        PageFactory.initElements(webDriver, this);
    }

    public static HashMap<String, String> productPriceOnCart = new HashMap<>();
    public static HashMap<String, WebElement> productParent = new HashMap<>();
    public static HashMap<String, List<WebElement>> feeParents = new HashMap<>();
    public static ArrayList<String> instantPromotionsOnCart = new ArrayList<String>();
    public static ArrayList<String> mailInPromotionsOnCart = new ArrayList<String>();

    private static final String SUBTOTAL = "Cart Subtotal";
    private static final String ITEM_REMOVED_MESSAGE = "Your Cart is Empty";
    private static final String QUANTITY_UPDATED_MESSAGE = "Product quantity has been updated.";
    private static final String CERTIFICATE_REPAIR_REFUND_REPLACEMENT = "Certificates for Repair, Refund, or Replacement";
    private static final String CERTIFICATE_REPAIR_REFUND_REPLACEMENT_MESSAGE = "Cover your tires up to 3 years, regardless of road hazard or wear, down to legal tread depth (3/32\").";
    private static final String INSTALLATION_SPIN_BALANCING = "Installation & LifeTime Spin Balancing";
    private static final String REMOVED = "Removed";
    private static final String UPDATED = "Updated";
    private static final String SHOW_FEE_DETAILS = "show fee details";
    public static final String TIRE_DISPOSAL_FEE = "Tire Disposal Fee";
    public static final String TPMS_REBUILD_KIT = "TPMS Rebuild Kits";
    private static final String TPMS_SENSOR = "TPMS Sensor";
    private static final String ATTRIBUTE_TITLE = "Title";
    private static final String CLEAR_CART = "Clear my cart and Continue";
    public static final String VIEW_CART = "View cart";
    private static final String CONTINUE_SHOPPING = "Continue Shopping";
    private static final String PRODUCT_NAME = "ProductName";
    private static final String ITEM_CODE = "ItemCode";
    public static String environmentFee;
    public List<Double> optionalFeesServicesList = Collections.emptyList();

    private static By appointmentButton = By.className("dt-checkout");

    private static By cartItemQuantityBy = By.className("cart-item__item-quantity");

    private static final By childTip = By.cssSelector("span.display-inline-block-sm");

    public static final By optionName = By.className("cart-item__option-label");

    public static final By orderSummaryRows = By.className("cart-item__row");

    public static final By productName = By.xpath("//div[@class='order-list__productname']/a");

    private static final By orderListPartsInfo = By.className("order-list__parts-info");

    private static final By orderListItemDesc = By.className("order-list__item-desc");

    private static final By orderListTotal = By.className("order-list__total");

    private static final By orderListPrice = By.className("order-list__price");

    private static final By orderListQty = By.className("order-list__qty");

    private static final By cartProductBy = By.className("cart-item__product-name");

    private static final By cartItemCodeBy = By.className("cart-item__product-code");

    private static final By productSpec = By.className("cart-item__product-specs");

    private static final By switchOptions = By.className("modal-switch-cart__button-holder");

    private static final By orderFeeServiceItemLabelBy = By.className("cart-item__option-label");

    private static final By feeServiceItemLabelBy = By.className("cart-item__option-name");

    private static final By brandName = By.className("order-list__brandname");

    private static final By cartItems = By.className("cart-item");

    private static final By buttonBy = By.tagName("button");

    private static final By certForRRPopUpBy = By.className("checkout-form__second-effort-action");

    private static final By feeDetailsItemsRowParentBy = By.className("cart-item__row");

    public static final By feeDetailsItemsRowLabelBy = By.className("cart-item__column--info");

    public static final By feeDetailsItemsRowPriceBy = By.className("cart-item__column--price");

    private static final By serviceFeeItemRowPriceBy = By.className("cart-item__column--each");

    private static final By itemRowQuantityBy = By.className("cart-item__column--qty");

    private static final By cartItemDetailsParentBy = By.className("cart-item__details");

    private static final By showFeeDetailsBy = By.cssSelector(".dt-link[type='button']");

    public static final By cartSummaryBreakDownNameBy = By.className("cart-summary__breakdown-name");

    public static final By cartSummaryBreakDownPrice = By.className("cart-summary__breakdown-price");

    private static final By itemPriceEachBy = By.className("cart-item__item-each");

    private static final By itemPriceBy = By.className("cart-item__item-price");

    private static final By cartItemProductBy = By.className("cart-item__product");

    private static final By removeItemBy = By.className("cart-item__remove-item");

    public static final By qualifyPromotionBy = By.className("cart-item__rebate-amount--instant");

    private static final By cartSummaryHeadingBy = By.className("cart-summary__heading");

    private static final By cartItemTotalPriceBy = By.className("cart-item__total-price");

    private static final By cartItemProductSize = By.className("cart-item__product-size");

    private static final By cartItemSubtotal = By.className("cart-item__subtotal-amount");

    private static final By viewCartButtonBy = By.className("auto-popover-view-cart-button");

    private static final By continueShoppingButtonBy = By.className("auto-popover-continue-shopping-button");

    private static final By miniCartItemNameBy = By.className("mini-cart__item-name");

    private static final By miniCartItemQuantityBy = By.className("mini-cart__item-quantity");

    private static final By miniCartItemTotalBy = By.className("mini-cart__item-total");

    private static final By miniCartItemInfoBy = By.className("mini-cart__item-info");

    private static final By miniCartTotalBy = By.className("mini-cart__total");

    public static final By modalSwitchCartBy = By.cssSelector(".modal-switch-cart>h3");

    private static final By cartItemProductImageBy = By.className("cart-item__product-image");

    public static final By cartItemRebateNameBy = By.className("cart-item__rebate-name");

    private static final By cartSummaryInstantSavingBy = By.className("cart-summary__instant-savings-amount");

    public static final By cartItemRowBy = By.className("cart-item__row--space-between");

    private static final By cartItemsPriceBy = By.cssSelector(".cart-item__product .cart-item__item-price");

    private static final By cartItemFitBy = By.className("cart-item__fit");

    private static final By miniCartItemPriceBy = By.className("mini-cart__item-price");

    @FindBy(id = "quantity_0")
    public static WebElement quantityInfoBox;

    @FindBy(id = "quantity_1")
    public static WebElement quantityInfoBoxNext;

    @FindBy(className = "cart-item__item-price")
    public static WebElement priceBox;

    @FindBy(className = "cart-item__store-address-city-state-zip")
    public static WebElement cartPageStoreState;

    @FindBy(className = "cart-item__remove-item")
    public static WebElement removeItem;

    @FindBy(className = "visible-xs")
    public static WebElement removeItemMobile;

    @FindBy(className = "order-list__total")
    public static WebElement preTotal;

    @FindBy(css = ".order-list__top-info .order-list__total")
    public static WebElement itemTotal;

    //TODO Refactor Needed For Xpath(s) When Auto-Class Attribute Available

    @FindBy(css = ".tip-container>strong")
    public static WebElement certificateForRRRLabel;

    @FindBy(css = ".order-list__item-desc-more")
    public static WebElement CertificateForRRRMsg;

    @FindBy(xpath = "//div[@class='order-summary__tax']/span")
    public static WebElement salesTax;

    @FindBy(xpath = "//div[@class='order-list__warranty']/strong")
    public static WebElement totalMilesWarranty;

    @FindBy(xpath = "//div[@class='order-list__pressure']/strong")
    public static WebElement tireMaxPressure;

    @FindBy(className = "order-summary__price")
    public static WebElement totalPriceInclTax;

    @FindBy(xpath = "//a[contains(@href, 'alltires')]")
    public static WebElement continueShoppingForTires;

    @FindBy(xpath = "//a[contains(@href, 'allwheels')]")
    public static WebElement continueShoppingForWheels;

    @FindBy(xpath = "//a[contains(@class, 'staticAccessoryMoreOptions')]")
    public static WebElement moreOptionsLink;

    @FindBy(className = "shopping-cart")
    public static WebElement shoppingCart;

    @FindBy(className = "auto-results-row-cartbutton")
    public static WebElement addToCartButton;

    @FindBy(className = "results-row__compare")
    public static WebElement resultRowCompare;

    @FindBy(className = "auto-header-cart-button-display-lg")
    public static WebElement miniCart;

    @FindBy(css = ".header__cart-quick-total.display-lg")
    public static WebElement miniCartPrice;

    @FindBy(xpath = "//span[contains(text(), 'Certificate')]/following-sibling::span[@class='mini-cart__item-quantity']")
    public static WebElement miniCartRRACertItemQuantity;

    @FindBy(xpath = "//span[contains(text(), 'Certificate')]/parent::div/following-sibling::div[@class='mini-cart__item-price']")
    public static WebElement miniCartRRACertBasePrice;

    @FindBy(xpath = "//span[contains(text(), 'Certificate')]/parent::div/following-sibling::div[@class='mini-cart__item-total']")
    public static WebElement miniCartRRACertTotalPrice;

    @FindBy(xpath = "//button[contains(text(), '" + CLEAR_CART + "')]")
    public static WebElement clearCart;

    @FindBy(xpath = "//button[contains(text(), '" + VIEW_CART + "')]")
    public static WebElement viewCart;

    @FindBy(css = ".modal-switch-cart>a")
    public static WebElement modalSwitchCancel;

    @FindBy(className = "cart-item__subtotal-amount")
    public static WebElement subTotal;

    @FindBy(className = "order-summary__tax")
    public static WebElement tax;

    @FindBy(xpath = "//div[contains(@class, 'cart-item__column--summary-price')]/span[@class='cart-item__total-price']")
    public static WebElement orderTotal;

    @FindBy(className = "order-summary__savings")
    public static WebElement savings;

    @FindBy(id = "zip-code")
    public static WebElement orderSummaryZipCode;

    @FindBy(id = "looseMoreOptions")
    public static WebElement tpmsMoreOpts;

    @FindBy(xpath = "//strong[contains(.,'TPMS Sensor')]")
    public static WebElement tpmsSensor;

    @FindBy(css = "input[data-accessorytype='SENSORS']~span")
    public static WebElement tpmsSensorRadioBtn;

    @FindBy(className = "cart-summary__breakdown-price")
    public static WebElement cartSubtotal;

    @FindBy(xpath = "//div[@class='tip-container' and contains(.,'TPMS Sensor')]")
    public static WebElement tpmsSensorLabel;

    @FindBy(className = "cart-summary__checkout")
    public static WebElement checkoutButton;

    @FindBy(className = "cart-wrapper")
    public static WebElement orderList;

    /**
     * Calculate Certificate Fee for DT
     *
     * @param item product code
     */
    public double getCalculatedCertFeeDt(String item) {
        LOGGER.info("getCalculatedCertFeeDt started");
        double itemPrice;
        if (item.contains(Constants.NONE)) {
            itemPrice = commonActions.cleanMonetaryString(webDriver.findElement(itemPriceEachBy).getText());
        } else {
            itemPrice = commonActions.cleanMonetaryString(CommonActionsSteps.cartProductPrice.get(item).split("[a-z]")[0].trim());
        }
        double certFee = 0.00;
        if (itemPrice < 0.00) {
            Assert.fail("FAIL: Item Price can't be negative, ItemPrice :" + itemPrice);
        } else if (itemPrice <= 15.99) {
            certFee = 4.50;
        } else if (itemPrice <= 30.99) {
            certFee = 6.00;
        } else if (itemPrice <= 35.99) {
            certFee = 7.50;
        } else if (itemPrice <= 40.99) {
            certFee = 8.00;
        } else if (itemPrice <= 50.99) {
            certFee = 10.50;
        } else if (itemPrice <= 65.99) {
            certFee = 11.00;
        } else if (itemPrice <= 75.99) {
            certFee = 13.50;
        } else if (itemPrice <= 90.99) {
            certFee = 14.00;
        } else if (itemPrice <= 100.99) {
            certFee = 15.50;
        } else if (itemPrice <= 115.99) {
            certFee = 16.00;
        } else if (itemPrice <= 125.99) {
            certFee = 17.50;
        } else if (itemPrice <= 140.99) {
            certFee = 18.00;
        } else if (itemPrice <= 165.99) {
            certFee = 23.00;
        } else if (itemPrice >= 166) {
            certFee = Double.parseDouble(new DecimalFormat("#.##").format(itemPrice * 0.18));
            int certFeeInteger = (int) certFee;
            double diff = certFee - certFeeInteger;
            if (diff == 0.00) {
                return certFee;
            } else if (diff <= 0.25) {
                return certFeeInteger + 0.25;
            } else if (diff <= 0.50) {
                return certFeeInteger + 0.50;
            } else if (diff <= 0.75) {
                return certFeeInteger + 0.75;
            } else {
                return certFeeInteger + 1;
            }
        }
        LOGGER.info("getCalculatedCertFeeDt completed");
        return certFee;
    }

    /**
     * Calculate Certificate Fee for DTD
     *
     * @param item product code
     */
    public double getCalculatedCertFeeDtd(String item) {
        LOGGER.info("getCalculatedCertFeeDtd started");
        double itemPrice;
        if (item.contains(Constants.NONE)) {
            itemPrice = commonActions.cleanMonetaryString(webDriver.findElement(itemPriceEachBy).getText());
        } else {
            itemPrice = commonActions.cleanMonetaryString(CommonActionsSteps.cartProductPrice.get(item).split("[a-z]")[0].trim());
        }
        double certFee = Double.parseDouble(new DecimalFormat("#.##").format(itemPrice * 0.12));
        if (itemPrice < 0.00) {
            Assert.fail("FAIL: Item Price can't be negative, ItemPrice :" + itemPrice);
        } else if (itemPrice <= 0.01) {
            certFee = 0.01;
        } else {
            int certFeeInteger = (int) certFee;
            double diff = certFee - certFeeInteger;
            if (diff == 0.00) {
                return certFee;
            } else if (diff <= 0.25) {
                return certFeeInteger + 0.25;
            } else if (diff <= 0.50) {
                return certFeeInteger + 0.50;
            } else if (diff <= 0.75) {
                return certFeeInteger + 0.75;
            } else {
                return certFeeInteger + 1;
            }
        }
        LOGGER.info("getCalculatedCertFeeDtd completed");
        return certFee;
    }

    /**
     * Calculate Certificate Fee for AT
     *
     * @param item product code
     */
    public double getCalculatedCertFeeAt(String item) {
        LOGGER.info("getCalculatedCertFeeAt started");
        double itemPrice;
        if (item.contains(Constants.NONE)) {
            itemPrice = commonActions.cleanMonetaryString(webDriver.findElement(itemPriceEachBy).getText());
        } else {
            itemPrice = commonActions.cleanMonetaryString(CommonActionsSteps.cartProductPrice.get(item).split("[a-z]")[0].trim());
        }
        double certFee = Constants.ZERO;
        if (itemPrice < Constants.ZERO) {
            Assert.fail("FAIL: Item Price can't be negative, ItemPrice :" + itemPrice);
        } else if (itemPrice <= 35.00) {
            certFee = 4.50;
        } else if (itemPrice <= 50.00) {
            certFee = 6.50;
        } else if (itemPrice <= 75.00) {
            certFee = 9.50;
        } else if (itemPrice <= 90.00) {
            certFee = 13.00;
        } else if (itemPrice <= 100.00) {
            certFee = 13.50;
        } else if (itemPrice <= 115.00) {
            certFee = 15.50;
        } else if (itemPrice >= 115.01) {
            certFee = Double.parseDouble(new DecimalFormat("#.##").format(itemPrice * 0.14));
            int certFeeInteger = (int) certFee;
            double diff = certFee - certFeeInteger;
            if (diff == 0.00) {
                return certFee;
            } else if (diff <= 0.25) {
                return certFeeInteger + 0.25;
            } else if (diff <= 0.50) {
                return certFeeInteger + 0.50;
            } else if (diff <= 0.75) {
                return certFeeInteger + 0.75;
            } else {
                return certFeeInteger + 1;
            }
        }
        LOGGER.info("getCalculatedCertFeeAt completed");
        return certFee;
    }

    /**
     * Calculate FET Fee
     *
     * @param itemCode The int item to check
     */
    public double getCalculatedFETFee(int itemCode) {
        LOGGER.info("getCalculatedFETFee started");
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double fetFee = Constants.ZERO;
        if (itemCode == 10001) {
            fetFee = 8.00 * itemQuantity;
        } else if (itemCode == 10082 || itemCode == 10083 || itemCode == 10090 || itemCode == 10097) {
            fetFee = 1.00 * itemQuantity;
        } else if (itemCode == 10204) {
            fetFee = 10.01 * itemQuantity;
        } else if (itemCode == 47359) {
            fetFee = 5.48 * itemQuantity;
        } else if (itemCode == 47360) {
            fetFee = 0.19 * itemQuantity;
        }
        LOGGER.info("getCalculatedFETFee completed");
        return fetFee;
    }

    /**
     * Calculate Disposal Fee for DT & AT
     *
     * @param item product code
     */
    public double getCalculatedDisposalFee(String item) {
        LOGGER.info("getCalculatedDisposalFee started");
        double itemPrice;
        double disposalFee = 0.00;
        if (item.contains(Constants.NONE)) {
            itemPrice = commonActions.cleanMonetaryString(webDriver.findElement(itemPriceEachBy).getText());
        } else {
            itemPrice = commonActions.cleanMonetaryString(CommonActionsSteps.cartProductPrice.get(item).split("[a-z]")[0].trim());
        }
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) && itemPrice <= 9999.99) {
            disposalFee = 2.50;
        } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT) && itemPrice <= 9999.99) {
            disposalFee = 2.00;
        } else if (itemPrice < Constants.ZERO) {
            Assert.fail("FAIL: Item Price can't be negative, ItemPrice :" + itemPrice);
        }
        LOGGER.info("getCalculatedDisposalFee completed");
        return disposalFee;
    }

    /**
     * Calculate Environment Fee for DT, AT and DTD
     *
     * @param item product code
     */
    public double getCalculatedEnvironmentFee(String item) {
        LOGGER.info("getCalculatedEnvironmentFee started");
        double itemPrice;
        double environmentFee = 0.00;
        if (item.contains(Constants.NONE)) {
            itemPrice = commonActions.cleanMonetaryString(webDriver.findElement(itemPriceEachBy).getText());
        } else {
            itemPrice = commonActions.cleanMonetaryString(CommonActionsSteps.cartProductPrice.get(item).split("[a-z]")[0].trim());
        }
        if (itemPrice < 100.00) {
            environmentFee = Double.parseDouble(new DecimalFormat("#.##").format(itemPrice * 0.02));
        } else if ((Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) || Config.getSiteRegion().equalsIgnoreCase
                (ConstantsDtc.DTD)) && itemPrice <= 9999.99) {
            environmentFee = 2.00;
        } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT) && itemPrice <= 9999.99) {
            environmentFee = 1.75;
        } else if (itemPrice < Constants.ZERO) {
            Assert.fail("FAIL: Item Price can't be negative, ItemPrice :" + itemPrice);
        }
        LOGGER.info("getCalculatedEnvironmentFee completed");
        return environmentFee;
    }

    /**
     * Asserts item passed in is either present or not present on the shopping cart page
     *
     * @param item          selected item/product to verify appears on page
     * @param displayStatus sets the verification expectation as to whether the item should or should NOT be displayed
     *                      on the Cart page
     */
    public void assertItemOnCartPage(String item, String displayStatus) {
        LOGGER.info("assertItemOnCartPage started");

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isMobile() || Config.isFirefox()) {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        } else {
            driver.waitForPageToLoad();
        }
        driver.waitForElementVisible(orderList);

        if (displayStatus.equalsIgnoreCase(Constants.DISPLAYED)) {
            Assert.assertTrue("FAIL: Item \"" + item + "\" was NOT present on the cart page!",
                    driver.checkIfElementContainsTextLowerCase(cartItems, item));
            LOGGER.info("Confirmed \"" + item + "\" was visible on cart page.");
        } else {
            Assert.assertTrue("FAIL: Item \"" + item + "\" was present on the cart page when it was expected"
                            + " NOT to be displayed!",
                    !driver.checkIfElementContainsTextLowerCase(cartItems, item));
            LOGGER.info("Confirmed \"" + item + "\" was NOT visible on cart page.");
        }
        LOGGER.info("assertItemOnCartPage completed");
    }

    /**
     * Asserts item passed in is present on the shopping cart page. This overload method defaults to expecting the
     * item to be present / displayed on the cart page
     *
     * @param item selected item/product to verify appears on page
     */
    public void assertItemOnCartPage(String item) {
        assertItemOnCartPage(item, Constants.DISPLAYED);
    }

    /**
     * Asserts that the current product quantity matches desired/default quantity in Product Page
     *
     * @param value The quantity value to validate
     */
    public void assertProductQuantityOnCartPage(String value) {
        LOGGER.info("assertProductQuantityOnCartPage started");
        driver.waitForElementVisible(quantityInfoBox);
        Assert.assertTrue("FAIL: The actual product quantity: \"" + quantityInfoBox.getAttribute(Constants.VALUE)
                        + " does NOT match expected: \"" + value + "\"!",
                quantityInfoBox.getAttribute(Constants.VALUE).equalsIgnoreCase(value));
        LOGGER.info("Confirmed that default product quantity \"" + value +
                "\" matches with rendered quantity ==>" + quantityInfoBox.getAttribute(Constants.VALUE));
        LOGGER.info("assertProductQuantityOnCartPage completed");
    }

    /**
     * Clicks 'remove' on shopping cart page
     */
    public void removeItem() {
        LOGGER.info("removeItem started");

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        List<WebElement> removeItems = webDriver.findElements(removeItemBy);

        if (Config.isMobilePhone()) {
            driver.waitForElementVisible(removeItems.get(1));
            removeItems.get(1).click();
        } else {
            driver.waitForElementVisible(removeItems.get(0));
            removeItems.get(0).click();
        }
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        driver.clickElementWithText(buttonBy, ConstantsDtc.REMOVE_ITEM_FROM_CART);

        LOGGER.info("removeItem completed");
    }

    /**
     * Remove items from cart based on stock status
     *
     * @param stockStatus - 'In Stock' or 'On Order'.  If 'In Stock', remove
     *                    the items that do not have delayed availability.
     *                    if 'On Order', remove the items with delayed availability.
     */
    public void removeItemsByStockStatus(String stockStatus) {
        LOGGER.info("removeItemsByStockStatus started");
        List<WebElement> itemElements = webDriver.findElements(cartItemProductBy);

        if (!stockStatus.equalsIgnoreCase(ConstantsDtc.IN_STOCK) &&
                !stockStatus.equalsIgnoreCase(ConstantsDtc.ON_ORDER)) {
            Assert.fail("Invalid parameter for stock status ('" + stockStatus + "') in "
                    + "CartPage.removeItemsByStockStatus.  Valid values are 'In Stock' or 'On Order'");
        }

        // Iterate the rows from bottom to top keeping item position indices intact.
        // Remove the target items based on specified stock status and current availability.
        for (int itemIndex = itemElements.size() - 1; itemIndex >= 0; itemIndex--) {
            WebElement itemElement = itemElements.get(itemIndex);

            boolean availabilityDelayed = itemElement.getText().trim().contains(
                    ConstantsDtc.AVAILABILITY_3_5_DAYS_CALLTOCONFIRM);

            if ((stockStatus.equalsIgnoreCase(ConstantsDtc.IN_STOCK) && !availabilityDelayed)
                    || (stockStatus.equalsIgnoreCase(ConstantsDtc.ON_ORDER) && availabilityDelayed)) {
                driver.jsScrollToElementClick(itemElement.findElement(removeItemBy));
                LOGGER.info("Removed " + itemElement.getText().split("Item")[0] + " from cart.");
                driver.clickElementWithText(buttonBy, ConstantsDtc.REMOVE_ITEM_FROM_CART);
            }
        }
        LOGGER.info("removeItemsByStockStatus completed");
    }

    /**
     * Asserts the different types of messages that appear on page based on different actions
     *
     * @param messageType used to indicate what type of message should appear on the page
     */
    public void assertHeaderMessage(String messageType) {
        LOGGER.info("assertHeaderMessage started");

        if (Config.isMobile())
            driver.waitForMilliseconds();
        driver.waitForPageToLoad();
        if (messageType.equalsIgnoreCase(REMOVED)) {
            Assert.assertEquals("FAIL: Header element did NOT display \"Item Removed\" message!",
                    webDriver.findElement(CommonActions.headerBy).getText(), ITEM_REMOVED_MESSAGE);
        } else if (messageType.equalsIgnoreCase(UPDATED)) {
            Assert.assertEquals("FAIL: Header element did not display \"Quantity Updated\" message!",
                    CommonActions.globalMessage.getText(), QUANTITY_UPDATED_MESSAGE);
        }
        LOGGER.info("Confirmed that product removed/updated message was displayed.");
        LOGGER.info("assertHeaderMessage completed");
    }

    /**
     * Clears and resets the quantity input box with a new value
     *
     * @param quantity Quantity to update to
     */
    public void updateQuantity(String quantity) {
        LOGGER.info("updateQuantity started");
        driver.waitForElementVisible(quantityInfoBox);
        quantityInfoBox.click();
        quantityInfoBox.clear();
        quantityInfoBox.sendKeys(quantity);

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari()) {
            quantityInfoBox.sendKeys(Keys.ENTER);
            driver.waitForMilliseconds();
        } else if (Config.isIe()) {
            priceBox.click();
        } else if (Config.isIphone() || Config.isIpad()) {
            //Clicking parent element to trigger event
            shoppingCart.click();

        } else {
            quantityInfoBox.sendKeys(Keys.RETURN);
        }

        driver.waitForMilliseconds();

        LOGGER.info("Entered updated quantity: " + quantity);
        LOGGER.info("updateQuantity completed");
    }

    /**
     * Asserts that the quantity input box is the same as the quantity passed in
     *
     * @param quantity The quantity that the input box should abe showing
     */
    public void assertUpdatedProductQty(String quantity) {
        LOGGER.info("assertUpdatedProductQty started");
        String updatedQuantity = quantityInfoBox.getAttribute(Constants.VALUE);
        driver.waitForElementVisible(quantityInfoBox);
        Assert.assertEquals("FAIL: The expected quantity: \"" + quantity
                        + "\" does NOT match the actual updated quantity: \"" + updatedQuantity + "\"!", quantity,
                updatedQuantity);
        LOGGER.info("Confirmed that updated quantity'" + quantity + "' matches with rendered quantity ==>"
                + updatedQuantity);
        LOGGER.info("assertUpdatedProductQty completed");
    }

    /**
     * Verifies that the tax or fee is a specific percent of the subtotal
     *
     * @param orderSummaryItem The item in the order summary to validate
     * @param percentage       The percentage to validate
     * @throws Exception Exception thrown by method
     */
    public void verifyPercentage(String orderSummaryItem, String percentage) throws Exception {
        LOGGER.info("verifyPercentage started");
        driver.waitForElementVisible(orderSummaryRows);
        WebElement orderSummaryItemRow = driver.getElementWithText(orderSummaryRows, orderSummaryItem);
        WebElement orderSummarySubtotalRow = driver.getElementWithText(orderSummaryRows, SUBTOTAL);
        String itemAmount = driver.getLastSubstring(orderSummaryItemRow, "$");
        String subtotalAmount = driver.getLastSubstring(orderSummarySubtotalRow, "$");
        float percentageActualFloat = Float.parseFloat(itemAmount) / Float.parseFloat(subtotalAmount);
        int percentageActual = (int) (percentageActualFloat * 100);
        Assert.assertEquals("FAIL: Percentage expected: \"" + percentage + "\", Percentage actual: \""
                + percentageActual + "\"!", 1, percentageActual);
        LOGGER.info("Confirmed that the percentage was %" + percentage + " of the total.");
        LOGGER.info("verifyPercentage completed");
    }

    /**
     * Asserts that the current product quantity matches desired/default quantity in Product Page
     *
     * @param checkoutType The quantity value to validate
     */
    public void verifyCheckoutButtonNotDisplayed(String checkoutType) {
        LOGGER.info("verifyCheckoutButtonNotDisplayed started");
        WebElement elementCheckout = driver.getElementWithText(appointmentButton, checkoutType);
        Assert.assertNull("FAIL: \"" + checkoutType + "\" button should NOT have been displayed!",
                elementCheckout);
        LOGGER.info("verifyCheckoutButtonNotDisplayed ended");
    }

    /**
     * Asserts the passed in name appears in the title
     *
     * @param name Name to verify in the title
     */
    public void assertItemTitle(String name) {
        LOGGER.info("assertItemTitle started");
        driver.assertElementAttributeString(productName, ATTRIBUTE_TITLE, name);
        LOGGER.info("assertItemTitle completed");
    }

    /**
     * Extracts the Total Miles Manufacturer Warranty
     *
     * @return String
     */
    private String getTotalMilesWarrantyText() {
        LOGGER.info("getTotalMilesWarrantyText started");
        driver.waitForElementVisible(totalMilesWarranty);
        LOGGER.info("getTotalMilesWarrantyText completed");
        return totalMilesWarranty.getText();
    }

    /**
     * Assert provided "Tire Miles Warranty" matches with the rendered warranty
     * value on cart's page
     *
     * @param miles The string fee to check
     */
    public void assertMilesWarranty(String miles) {
        LOGGER.info("assertMilesWarranty started");
        String milesWarranty = getTotalMilesWarrantyText();
        Assert.assertEquals(
                "FAIL: Miles didn't match! (displayed miles:-> " + milesWarranty + " expected:->  "
                        + miles + ")", miles, milesWarranty);
        LOGGER.info("Miles matched: (displayed miles:-> " + milesWarranty + " with expected:->  " + miles + ")");
        LOGGER.info("assertMilesWarranty completed");
    }

    /**
     * Extracts the Tire PSI Max Air Pressure
     *
     * @return String
     */
    private String getTireMaxPressureText() {
        LOGGER.info("getTireMaxPressureText started");
        driver.waitForElementVisible(tireMaxPressure);
        LOGGER.info("getTireMaxPressureText completed");
        return tireMaxPressure.getText();
    }

    /**
     * Assert provided "Tire Max PSI Pressure" matches with the rendered psi on
     * cart's page
     *
     * @param psi The string psi to check
     */

    public void assertTireMaxPressure(String psi) {
        LOGGER.info("assertTireMaxPressure started");
        String tireMaxPressure = getTireMaxPressureText();
        Assert.assertEquals("FAIL: Tire Max PSI Pressure didn't match! (displayed:-> " + tireMaxPressure
                + " expected:->  " + psi + ")!", psi, tireMaxPressure);
        LOGGER.info("Tire Max PSI Pressure matched: (displayed:-> " + tireMaxPressure + " with expected:->  "
                + psi + ")");
        LOGGER.info("assertTireMaxPressure completed");
    }

    /**
     * Extracts the Environmental Fee (State Required)
     *
     * @return String
     */
    private String getEnvironmentalFee() {
        return this.getSpecialPricingOnCartPage(feeDetailsItemsRowParentBy, feeDetailsItemsRowLabelBy, feeDetailsItemsRowPriceBy,
                ConstantsDtc.ENVIRONMENTAL_FEE);
    }

    /**
     * Extracts the Valve Stem Fee (DTD)
     *
     * @return String
     */
    private String getvalveStemFee() {
        return this.getSpecialPricingOnCartPage(orderListPartsInfo, orderListItemDesc, orderListTotal,
                ConstantsDtc.VALVE_STEM);
    }

    /**
     * Extracts the Federal Excise Tax (F.E.T.)
     *
     * @return String
     */
    private String getFETFee() {
        return this.getSpecialPricingOnCartPage(orderFeeServiceItemLabelBy, orderListItemDesc, orderListTotal,
                ConstantsDtc.FETFEE_LABEL);
    }

    /**
     * Assert provided "Environmental Fee" price matches with the rendered fee
     * on cart's page
     *
     * @param fee The string fee to check
     */
    public void assertEnvironmentalFeeAmount(String fee) {
        LOGGER.info("assertEnvironmentalFeeAmount started");
        String environmentalFee = String.valueOf(getEnvironmentalFee());
        Assert.assertEquals("FAIL: Environmental Fee didn't match! (displayed:-> " + environmentalFee
                + " expected:->  " + fee + ")", fee, environmentalFee);
        LOGGER.info(
                "Environmental Fee matched: (displayed:-> " + environmentalFee + " with expected:->  "
                        + fee + ")");
        LOGGER.info("assertEnvironmentalFeeAmount completed");
    }

    /**
     * Assert provided "Environmental Fee" price matches with the rendered fee (Fixed when Product Price > $100)
     * on cart's page
     */
    public void assertEnvironmentalFeeAmt() {
        LOGGER.info("assertEnvironmentalFeeAmt started");
        driver.waitForPageToLoad();
        double estimatedEnvPrice = getCalculatedEnvironmentFee(Constants.NONE);
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double expectedEnvFeeTotal = (estimatedEnvPrice * itemQuantity);
        double actualEnvFee = commonActions.cleanMonetaryString(getEnvironmentalFee());
        Assert.assertTrue("FAIL: Environmental Fee didn't match! (displayed:-> " + actualEnvFee + "expected:-> "
                + expectedEnvFeeTotal + ")", actualEnvFee == expectedEnvFeeTotal);
        LOGGER.info("assertEnvironmentalFeeAmt completed");
    }

    /**
     * Assert FET fee on cart's page
     */
    public void assertFETFee(int item) {
        LOGGER.info("assertFETFee started");
        WebElement elementLabel = driver.getElementWithText(childTip, ConstantsDtc.FETFEE_LABEL);
        if (elementLabel != null && driver.isTextPresentInPageSource(ConstantsDtc.FETFEE_LABEL)) {
            double expectedFetFee = getCalculatedFETFee(item);
            double fetFee = commonActions.cleanMonetaryString(getFETFee());
            Assert.assertTrue("FAIL: fet Fee didn't match! (displayed:-> "
                            + fetFee + "expected:->  " + expectedFetFee + ")",
                    expectedFetFee == fetFee);
        }
        LOGGER.info("assertFETFee completed");
    }

    /**
     * Extracts the Tire Disposal Fee
     *
     * @return String
     */
    private String getTireDisposalFee() {
        return this.getSpecialPricingOnCartPage(feeDetailsItemsRowParentBy, feeDetailsItemsRowLabelBy, feeDetailsItemsRowPriceBy,
                ConstantsDtc.DISPOSAL_FEE);
    }

    /**
     * Assert provided "Tire Disposal Fee" price matches with the rendered fee
     * on cart's page
     *
     * @param fee The string fee to check
     */
    public void assertTireDisposalFeeAmount(String fee) {
        LOGGER.info("assertTireDisposalFeeAmount started");
        String tireDisposalFee = String.valueOf(getTireDisposalFee());
        Assert.assertEquals("FAIL: Tire Disposal Fee didn't match! (displayed:-> " + tireDisposalFee
                + " expected:->  " + fee + ")", fee, tireDisposalFee);
        LOGGER.info(
                "Tire Disposal Fee matched: (displayed:-> " + tireDisposalFee + " with expected:->  " + fee + ")");
        LOGGER.info("assertTireDisposalFeeAmount completed");
    }

    /**
     * Assert provided "Tire Disposal Fee" price matches with the expected disposal fee
     * on cart's page
     */
    public void assertTireDisposalFeeAmt() {
        LOGGER.info("assertTireDisposalFeeAmt started");
        double actualDisposalFee = commonActions.cleanMonetaryString(getTireDisposalFee());
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double estimatedDisposalFee = getCalculatedDisposalFee(Constants.NONE);
        double expectedDisposalFee = (estimatedDisposalFee * itemQuantity);
        Assert.assertTrue("FAIL: Tire Disposal Fee didn't match! (displayed:-> "
                        + actualDisposalFee + "expected:->  " + expectedDisposalFee + ")",
                expectedDisposalFee == actualDisposalFee);
        LOGGER.info("assertTireDisposalFeeAmt completed");
    }

    /**
     * Extracts the Certificate for Repair, Refund or Replacement Service Fee
     *
     * @return String
     */
    private String getCertificateForRRRServiceFee() {
        return this.getSpecialPricingOnCartPage(orderFeeServiceItemLabelBy, orderListItemDesc, orderListPrice, CERTIFICATE_REPAIR_REFUND_REPLACEMENT);
    }

    /**
     * Assert provided "Certificate for Repair, Refund or Replacement Service
     * Fee" price matches with the rendered fee on cart's page
     *
     * @param fee The string fee to check
     */
    public void assertCertificateRRRFeeAmount(String fee) {
        LOGGER.info("assertCertificateRRRFeeAmount started");
        String certificateRRRFee = String.valueOf(getCertificateForRRRServiceFee());
        Assert.assertEquals("FAIL: Certificate for Repair, Refund or Replacement Service Fee did NOT match! "
                + "(displayed:-> " + certificateRRRFee + " expected:->  " + fee + ")", fee, certificateRRRFee);
        LOGGER.info("Certificate for Repair, Refund or Replacement Service Fee matched: (displayed:-> "
                + certificateRRRFee + " with expected:->  " + fee + ")");
        LOGGER.info("assertCertificateRRRFeeAmount completed");
    }

    /**
     * Extracts the Installation and Spin Balancing Price
     *
     * @return String
     */
    private String getInstallationPrice() {
        return this.getSpecialPricingOnCartPage(feeDetailsItemsRowParentBy, feeDetailsItemsRowLabelBy, feeDetailsItemsRowPriceBy,
                ConstantsDtc.INSTALLATION);
    }

    /**
     * Extract the web element to get Installation Price and quantity
     *
     * @param text fee type
     * @return parent element for the targeted fee and quantity
     */
    public WebElement extractInstallationElement(String text) {
        LOGGER.info("extractInstallationElement started");
        driver.waitForElementVisible(feeServiceItemLabelBy);
        WebElement serviceEle = driver.getElementWithText(feeServiceItemLabelBy, text);
        WebElement greatGrandParent = driver.getParentElement(driver.getParentElement(driver.getParentElement(serviceEle)));
        LOGGER.info("extractInstallationElement completed");
        return greatGrandParent;
    }

    /**
     * Assert provided "Installation and Spin Balancing" price matches with the
     * rendered price on cart's page
     *
     * @param fee The string fee to check
     */
    public void assertInstallationBalancingPrice(String fee) {
        LOGGER.info("assertInstallationBalancingPrice started");
        String installationBalancingPrice = getInstallationPrice();
        Assert.assertEquals("FAIL: Installation & Spin Balancing Price didn't match! (displayed:-> "
                + installationBalancingPrice + " expected:->  " + fee + ")", fee, installationBalancingPrice);
        LOGGER.info("Installation & Spin Balancing Price matched: (displayed:-> " + installationBalancingPrice
                + " with expected:->  " + fee + ")");
        LOGGER.info("assertInstallationBalancingPrice completed");
    }

    /**
     * Extracts the TPMS Rebuild Kits Price
     *
     * @return String
     */
    private String getTPMSRebuildPrice() {
        return this.getSpecialPricingOnCartPage(orderListPartsInfo, orderListItemDesc, orderListTotal,
                TPMS_REBUILD_KIT);
    }

    /**
     * Extracts the TPMS Price irrespective of TPMS Rebuild and TPMS Sensor
     *
     * @return String
     */
    private String getTPMSPrice() {
        return this.getSpecialPricingOnCartPage(orderListPartsInfo, orderListItemDesc, orderListTotal,
                ConstantsDtc.TPMS);
    }

    /**
     * Assert provided "TPMS Rebuild Kits" price matches with the rendered price
     * on cart's page
     *
     * @param fee The string fee to check
     */
    public void assertTPMSRebuildPrice(String fee) {
        LOGGER.info("assertTPMSRebuildPrice started");
        String tPMSRebuildPrice = getTPMSRebuildPrice();
        Assert.assertEquals("FAIL: TPMS Rebuild Kits Price didn't match! (displayed:-> " + tPMSRebuildPrice
                + " with expected:->  " + fee + ")", fee, tPMSRebuildPrice);
        LOGGER.info("TPMS Rebuild Kits Price matched: (displayed:-> " + tPMSRebuildPrice + " with expected:->  "
                + fee + ")");
        LOGGER.info("assertTPMSRebuildPrice completed");
    }

    /**
     * Extracts the Sales Tax
     *
     * @return String
     */
    private String getSalesTax() {
        LOGGER.info("getSalesTax started");
        driver.waitForElementVisible(salesTax);
        LOGGER.info("getSalesTax completed");
        return driver.getLastSubstring(salesTax, "$");
    }

    /**
     * Assert provided "Sales Tax" matches with the rendered tax on cart's page
     *
     * @param tax The string tax to check
     */
    public void assertSalesTax(String tax) {
        LOGGER.info("assertSalesTax started");
        String salesTax = getSalesTax();
        Assert.assertEquals(
                "FAIL: Sales Tax didn't match! (rendered tax: -> " + salesTax + " expected:->  " + tax + ")",
                tax, salesTax);
        LOGGER.info("Sales Tax matched: (displayed:-> " + salesTax + " with expected:->  " + tax + ")");
        LOGGER.info("assertSalesTax completed");
    }

    /**
     * Extracts the Total Price Including Tax
     *
     * @return String
     */
    private String getTotalPriceInclTax() {
        LOGGER.info("getTotalPriceInclTax started");
        driver.waitForElementVisible(totalPriceInclTax);
        LOGGER.info("getTotalPriceInclTax completed");
        return driver.getLastSubstring(totalPriceInclTax, "$");
    }

    /**
     * Assert provided "Total Price Incl Tax" price matches with the rendered
     * price on cart's page
     *
     * @param fee The string price to check
     */
    public void assertTotalPriceInclTax(String fee) {
        LOGGER.info("assertTotalPriceInclTax started");
        String totalPriceInclTax = getTotalPriceInclTax();
        Assert.assertEquals("FAIL: Total Price Incl Tax did NOT match! (displayed:-> " + totalPriceInclTax
                + " expected:->  " + fee + ")", fee, totalPriceInclTax);
        LOGGER.info("Total Price Incl Tax matched: (displayed:-> " + totalPriceInclTax + " with expected:->  "
                + fee + ")");
        LOGGER.info("assertTotalPriceInclTax completed");
    }

    /**
     * Click Continue Shopping For Tires Button
     */
    public void clickContinueShoppingForTires() {
        LOGGER.info("clickContinueShoppingForTires started");
        // TODO: retest when new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForElementClickable(continueShoppingForTires);
        continueShoppingForTires.click();
        LOGGER.info("clickContinueShoppingForTires completed");
    }

    /**
     * Click Continue Shopping For Wheels Button
     */
    public void clickContinueShoppingForWheels() {
        LOGGER.info("clickContinueShoppingForWheels started");
        // TODO: retest when new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForElementClickable(continueShoppingForWheels);
        continueShoppingForWheels.click();
        LOGGER.info("clickContinueShoppingForWheels completed");
    }

    /**
     * Click More Options link
     */
    public void clickMoreOptionsLink() {
        LOGGER.info("clickMoreOptionsLink started");
        // TODO: retest when new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        moreOptionsLink.click();
        LOGGER.info("clickMoreOptionsLink completed");
    }

    /**
     * Checks if "Environmental Fee (State Required)" text is displayed
     */
    public void assertEnvironmentalFeeLabelDisplayed() {
        LOGGER.info("assertEnvironmentalFeeLabelDisplayed started");
        String textLookUp = ConstantsDtc.ENVIRONMENTAL_FEE;
        WebElement elementLabel = driver.getElementWithText(childTip, textLookUp);
        if (elementLabel != null && driver.isTextPresentInPageSource(textLookUp)) {
            Assert.assertTrue("FAIL: Environmental Fee expected value: \"" + textLookUp
                            + "\", actual value: \"" + elementLabel.getText() + "\"!",
                    elementLabel.getText().contains(textLookUp));
        } else
            Assert.fail("FAIL: Environmental Fee Text : \"" + textLookUp + "\" - NOT present in page source!");
        LOGGER.info("assertEnvironmentalFeeLabelDisplayed completed");
    }

    /**
     * Checks if "Tire Disposal Fee" text is displayed
     */
    public void assertTireDisposalFeeLabelDisplayed() {
        LOGGER.info("assertTireDisposalFeeLabelDisplayed started");
        String textLookUp = ConstantsDtc.DISPOSAL_FEE;
        WebElement elementLabel = driver.getElementWithText(childTip, textLookUp);
        if (elementLabel != null && driver.isTextPresentInPageSource(textLookUp)) {
            Assert.assertTrue("FAIL: Text NOT matched; Found this \"" + elementLabel.getText()
                            + "\" but expected this \"" + textLookUp + "\"!",
                    elementLabel.getText().matches(textLookUp));
        } else
            Assert.fail("FAIL: Tire Disposal Fee Text: \"" + textLookUp + "\" - NOT present in page source!");
        LOGGER.info("assertTireDisposalFeeLabelDisplayed completed");
    }

    /**
     * Checks if "Certificate for Repair Refund Replacement" text is displayed
     */
    public void assertCertificateRepairRefundReplacementLabelDisplayed() {
        LOGGER.info("assertCertificateRepairRefundReplacementLabelDisplayed started");
        String textLookUp = CERTIFICATE_REPAIR_REFUND_REPLACEMENT;
        String textLookUp1 = CERTIFICATE_REPAIR_REFUND_REPLACEMENT_MESSAGE;

        if (certificateForRRRLabel != null && driver.isTextPresentInPageSource(textLookUp)) {
            Assert.assertTrue("FAIL: Text NOT matched; Found this \"" + certificateForRRRLabel.getText()
                            + "\" but expected this \"" + textLookUp + "\"!",
                    certificateForRRRLabel.getText().matches(textLookUp));
        } else
            Assert.fail("FAIL: Certification Label Text: \"" + textLookUp + "\" - NOT present in page source!");

        if (CertificateForRRRMsg != null && driver.isTextPresentInPageSource(textLookUp1)) {
            Assert.assertTrue("FAIL: Text NOT matched; Found this \"" + CertificateForRRRMsg.getText()
                    + "\" but expected this \"" + textLookUp1 + "\"!", CertificateForRRRMsg.getText().contentEquals(textLookUp1));
        } else
            Assert.fail("FAIL: Certification Message Text: \"" + textLookUp1 + "\" - NOT present in page source!");
        LOGGER.info("assertCertificateRepairRefundReplacementLabelDisplayed completed");
    }

    /**
     * Checks if "INSTALLATION and LIFETIME SPIN_BALANCING" text is displayed
     */
    public void assertInstallationAndSpinBalancingLabelDisplayed() {
        LOGGER.info("assertInstallationAndSpinBalancingLabelDisplayed started");
        String textLookUp = INSTALLATION_SPIN_BALANCING;
        WebElement elementLabel = driver.getElementWithText(childTip, textLookUp);
        if (elementLabel != null && driver.isTextPresentInPageSource(textLookUp)) {
            Assert.assertTrue("FAIL: Text NOT matched; Found this \"" + elementLabel.getText()
                            + "\" but expected this \"" + textLookUp + "\"!",
                    elementLabel.getText().matches(textLookUp));
        } else
            Assert.fail("FAIL: INSTALLATION_SPIN_BALANCING Text: \"" + textLookUp + "\" - NOT present in page source!");
        LOGGER.info("assertInstallationAndSpinBalancingLabelDisplayed completed");
    }

    /**
     * Checks if "TPMS Rebuild Kits" text is displayed
     */
    public void assertTPMSRebuildKitsLabelDisplayed() {
        LOGGER.info("assertTPMSRebuildKitsLabelDisplayed started");
        String textLookUp = TPMS_REBUILD_KIT;
        WebElement elementLabel = driver.getElementWithText(optionName, textLookUp);
        if (elementLabel != null) {
            Assert.assertTrue("FAIL: Text NOT matched; Found this \"" + elementLabel.getText()
                    + "\" but expected this \"" + textLookUp + "\"!", elementLabel.getText().contains(textLookUp));
        } else
            Assert.fail("FAIL: TPMS Rebuild Kits Text: \"" + textLookUp + "\" - NOT present in page source!");
        LOGGER.info("assertTPMSRebuildKitsLabelDisplayed completed");
    }

    /**
     * Extracts the Pricing among multiple that contains text substring
     *
     * @param parentElement The By element to build list with
     * @param refElement    The By element to check the existence of specific element
     * @param targetElement The By element to interact with
     * @param text          The string to lookup the specific element
     * @return String
     */
    private String getSpecialPricingOnCartPage(By parentElement, By refElement, By targetElement, String text) {
        LOGGER.info("getSpecialPricingOnCartPage started");
        boolean StringFound = false;
        List<WebElement> objects = webDriver.findElements(parentElement);
        String pricing = "";
        for (WebElement object : objects) {
            if (driver.isElementDisplayed(object.findElement(refElement))) {
                WebElement refObject = object.findElement(refElement);
                if (refObject.getText().trim().contains(text)) {
                    StringFound = true;
                    pricing = object.findElement(targetElement).getText().trim();
                    break;
                }
            }
        }
        if (!StringFound) {
            Assert.fail("FAIL: String \"" + text + "\" NOT found!");
        }
        LOGGER.info("getSpecialPricingOnCartPage completed");
        return pricing.substring(pricing.lastIndexOf("$") + 1);
    }

    /**
     * Asserts that the Add to Cart button is disabled
     *
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void assertAddToCartButtonIsDisabled() throws Exception {
        LOGGER.info("assertAddToCartButtonIsDisabled started");
        driver.waitForElementVisible(resultRowCompare);
        if (addToCartButton.isEnabled()) {
            Assert.fail("Add to Cart Button is not disabled.");
        }
        LOGGER.info("assertAddToCartButtonIsDisabled completed");
    }

    /**
     * Verifies the Switch Vehicle options (Clear my cart and Continue | View cart)
     *
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void assertSwitchVehicleOptions() throws Exception {
        LOGGER.info("assertSwitchVehicleOptions started");
        String failure = "";
        List<String> optionValues = new ArrayList<String>();

        driver.waitForPageToLoad();
        driver.waitForElementVisible(switchOptions);
        List<WebElement> options = webDriver.findElements(switchOptions);

        for (WebElement option : options) {
            optionValues.add(option.getText());
        }

        if (!optionValues.toString().contains(CLEAR_CART))
            failure = "\"" + CLEAR_CART + "\" option not present. ";

        if (!optionValues.toString().contains(VIEW_CART))
            failure = failure + "\"" + VIEW_CART + "\" option not present.";

        if (failure.length() > 0)
            Assert.fail("FAIL: " + failure);

        LOGGER.info("assertSwitchVehicleOptions completed");
    }

    /**
     * Selects the Switch Vehicle options (Clear my cart and Continue | View cart)
     *
     * @param option The switch vehicle option to select.
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void selectSwitchVehicleOption(String option) throws Exception {
        LOGGER.info("selectSwitchVehicleOption started");

        if (option.equalsIgnoreCase(CLEAR_CART)) {
            driver.waitForElementClickable(clearCart);
            clearCart.click();
        } else if (option.equalsIgnoreCase(VIEW_CART)) {
            driver.waitForElementClickable(viewCart);
            viewCart.click();
        } else if (option.equalsIgnoreCase(ConstantsDtc.CANCEL)) {
            driver.waitForElementVisible(modalSwitchCancel);
            modalSwitchCancel.click();
        } else {
            Assert.fail("FAIL: Option \"" + option + "\" not available.");
        }

        LOGGER.info("selectSwitchVehicleOption completed");
    }

    /**
     * Verifies the product price
     *
     * @param price The price to verify
     */
    public void assertCartPrice(String price) {
        LOGGER.info("assertCartPrice started");
        driver.waitForElementVisible(priceBox);
        Assert.assertTrue("FAIL: The actual price: \"" + priceBox.getText()
                        + "\" did not contain the expected price: \"" + price + "\"!",
                priceBox.getText().contains(price));
        LOGGER.info("Confirmed that \"" + price + "\" was listed for the product.");
        LOGGER.info("assertCartPrice completed");
    }

    /**
     * Verifies the itemTotal = product price * quantity
     */
    public void assertItemTotal() {
        LOGGER.info("assertItemTotal started");
        driver.waitForElementVisible(itemTotal);
        double itemPriceBox = commonActions.cleanMonetaryString(priceBox.getText());
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double actualItemTotal = commonActions.cleanMonetaryString(itemTotal.getText());
        double expectedItemTotal = (itemPriceBox * itemQuantity);
        Assert.assertTrue("FAIL: Item-total not displayed", driver.isElementDisplayed(itemTotal));
        Assert.assertTrue("FAIL: The actual item-total (" + actualItemTotal + ")not equal to Expected item-Total Price " +
                "(" + expectedItemTotal + ")", itemPriceBox * itemQuantity == actualItemTotal);
        LOGGER.info("assertItemTotal completed");
    }

    /**
     * Verifies the pre total
     *
     * @param preTotalValue The pre total to verify
     */
    public void assertCartPreTotal(String preTotalValue) {
        LOGGER.info("assertCartPreTotal started");
        driver.waitForElementVisible(preTotal);
        Assert.assertTrue("FAIL: The actual pre-total: \"" + preTotal.getText()
                        + "\" did not contain the expected pre-total: \"" + preTotalValue + "\"!",
                preTotal.getText().contains(preTotalValue));
        LOGGER.info("Confirmed that \"" + preTotalValue + "\" was listed as the pre-total for the product.");
        LOGGER.info("assertCartPreTotal completed");
    }

    /**
     * Verifies the subtotal
     *
     * @param subTotalValue The subtotal to verify
     */
    public void assertCartSubtotal(String subTotalValue) {
        LOGGER.info("assertCartSubtotal started");
        driver.waitForElementVisible(subTotal);
        Assert.assertTrue("FAIL: The actual  subtotal: \"" + subTotal.getText()
                        + "\" did not contain the expected total: \"" + subTotalValue + "\"!",
                subTotal.getText().contains(subTotalValue));
        LOGGER.info("Confirmed that \"" + subTotalValue + "\" was listed as the subtotal for the product.");
        LOGGER.info("assertCartSubtotal completed");
    }

    /**
     * Verifies the tax
     *
     * @param taxValue The tax value to verify
     */
    public void assertCartTax(String taxValue) {
        LOGGER.info("assertCartTax started");
        driver.waitForElementVisible(tax);
        Assert.assertTrue("FAIL: The actual tax: \"" + tax.getText()
                        + "\" did not contain the expected tax: \"" + taxValue + "\"!",
                tax.getText().contains(taxValue));
        LOGGER.info("Confirmed that \"" + taxValue + "\" was listed as the tax for the product.");
        LOGGER.info("assertCartTax completed");
    }

    /**
     * Assert tax calculation is specific % of sub-total with consideration of savings on product base price
     */
    public double getTaxOnCartPage() {
        LOGGER.info("getTaxOnCartPage started");
        String tirePrice = priceBox.getText();
        String stateOnCart;
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            stateOnCart = ConstantsDtc.DTD;
        } else {
            stateOnCart = (cartPageStoreState.getText().split(",")[1].split("\\s+")[1]).trim();
        }
        String customerType = "default_customer_".concat(stateOnCart.toLowerCase());
        CheckoutPage.setRegionalTaxesFactor(customer.getCustomer(customerType));

        //Calculate Tax based on the fees and promotions applicable
        driver.waitForPageToLoad();
        String envFee = getEnvironmentalFee();
        double estimatedTaxAmount = commonActions.getCalculatedSalesPriceForDTCRegion(tirePrice)
                + commonActions.getCalculatedSalesPriceForDTCRegion(envFee);
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) || Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            String dispFee = getTireDisposalFee();
            estimatedTaxAmount = estimatedTaxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(dispFee);
        } else {
            String optionalFee = String.valueOf(extractPriceForOptionalFee(Constants.NONE, ConstantsDtc.VALVE_STEM));
            estimatedTaxAmount = estimatedTaxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(optionalFee);
        }
        String certFee = String.valueOf(extractPriceForOptionalFee(Constants.NONE, ConstantsDtc.CERTIFICATE));
        if (driver.isTextPresentInPageSource(ConstantsDtc.CERTIFICATE)) {
            estimatedTaxAmount = estimatedTaxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(certFee);
        }
        if (driver.isElementDisplayed(cartSummaryInstantSavingBy)) {
            String promotion = webDriver.findElement(cartSummaryInstantSavingBy).getText();
            estimatedTaxAmount = estimatedTaxAmount
                    - commonActions.getCalculatedSalesPriceForDTCRegion(promotion);
        }
        //TODO - FET fee needs to be verified as part of Cart Redesign
        WebElement fetLabel = driver.getElementWithText(childTip, ConstantsDtc.FETFEE_LABEL);
        if (fetLabel != null && driver.isTextPresentInPageSource(ConstantsDtc.FETFEE_LABEL)) {
            String fetFEE = getFETFee();
            estimatedTaxAmount = estimatedTaxAmount
                    + commonActions.getCalculatedSalesPriceForDTCRegion(fetFEE);
        }
        LOGGER.info("getTaxOnCartPage completed");
        return commonActions.twoDForm(estimatedTaxAmount, 2);
    }

    /**
     * Calculate tax based on item price, fees and promotions applied for all items on Cart Page
     */
    public double calculateTaxForItems() {
        LOGGER.info("calculateTaxForItems started");
        double taxAmount;
        double totalTaxAmount = 0.00;
        Set<String> items = CommonActionsSteps.cartProductPrice.keySet();
        for (String item : items) {
            String stateOnCart = (cartPageStoreState.getText().split(",")[1].split("\\s+")[1]).trim();
            String customerType = "default_customer_".concat(stateOnCart.toLowerCase());
            CheckoutPage.setRegionalTaxesFactor(customer.getCustomer(customerType));

            String tirePrice = String.valueOf(extractItemsPriceTotal(item));

            String envFee = String.valueOf(extractFeesForItemsOnCart(ConstantsDtc.ENVIRONMENTAL_FEE, item));
            taxAmount = commonActions.getCalculatedSalesPriceForDTCRegion(tirePrice)
                    + commonActions.getCalculatedSalesPriceForDTCRegion(envFee);
            if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) || Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
                String dispFee = String.valueOf(extractFeesForItemsOnCart(ConstantsDtc.DISPOSAL_FEE, item));
                taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(dispFee);
            } else {
                String optionalFee = String.valueOf(extractPriceForOptionalFee(item, ConstantsDtc.VALVE_STEM));
                taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(optionalFee);
            }
            String certFee = String.valueOf(extractPriceForOptionalFee(item, ConstantsDtc.CERTIFICATE));
            if (!certFee.isEmpty()) {
                taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(certFee);
            }
            if (driver.isElementDisplayed(cartSummaryInstantSavingBy)) {
                String promotion = driver.getParentElement(getParent(item)).findElement(cartSummaryInstantSavingBy).getText();
                taxAmount = taxAmount - commonActions.getCalculatedSalesPriceForDTCRegion(promotion);
            }
            //TODO - Tax calculation needs to be further extended for FET, TPMS, etc Fees and Certificate needs to be tested
            totalTaxAmount = totalTaxAmount + taxAmount;
        }
        LOGGER.info("calculateTaxForItems completed");
        return commonActions.twoDForm(totalTaxAmount, 2);
    }

    /**
     * Extract tax calculation on Cart Page
     */
    public double extractTaxOnCart() {
        LOGGER.info("extractTax started");
        WebElement tax = driver.getElementWithText(cartSummaryBreakDownNameBy, ConstantsDtc.ESTIMATED_TAX);
        WebElement taxParent = driver.getParentElement(driver.getParentElement(tax));
        LOGGER.info("extractTax completed");
        return commonActions.cleanMonetaryString(taxParent.findElement(cartSummaryBreakDownPrice).getText());
    }

    /**
     * Assert calculated tax matches with the actual extracted tax on cart page
     */
    public void assertTax() {
        LOGGER.info("assertTax started");
        double estimatedTaxAmount;
        if (webDriver.findElements(itemPriceBy).size() > Constants.ITEM_CLASS_COUNT) {
            estimatedTaxAmount = calculateTaxForItems();
        } else {
            estimatedTaxAmount = getTaxOnCartPage();
        }
        double actualTaxAmount = extractTaxOnCart();
        Assert.assertTrue("FAIL: The actual  tax amount: {" + actualTaxAmount
                + ") did not match to the expected tax amount: ("
                + estimatedTaxAmount + ")", estimatedTaxAmount == actualTaxAmount);
        LOGGER.info("assertTax completed");
    }

    /**
     * Verifies the order total
     *
     * @param orderTotalValue The order total to verify
     */
    public void assertCartOrderTotal(String orderTotalValue) {
        LOGGER.info("assertCartSubtotal started");
        driver.waitForElementVisible(orderTotal);
        Assert.assertTrue("FAIL: The actual order total: \"" + orderTotal.getText()
                        + "\" did not contain the expected order total: \"" + orderTotalValue + "\"!",
                orderTotal.getText().contains(orderTotalValue));
        LOGGER.info("Confirmed that \"" + orderTotalValue + "\" was listed as the order total for the product.");
        LOGGER.info("assertCartSubtotal completed");
    }

    /**
     * Verifies the dollar discount amount has actually been applied to the total price
     *
     * @param fixedDiscount Amount in Dollars ($) of the item discount
     */
    public String assertFixedDollarDiscountApplied(double fixedDiscount) {
        LOGGER.info("assertFixedDollarDiscountApplied started");
        driver.waitForElementVisible(preTotal);

        // Order summary subtotal
        WebElement subTotal = webDriver.findElement(orderSummaryRows);
        subTotal = subTotal.findElement(By.cssSelector(Constants.SPAN));
        double orderSubTotal = commonActions.cleanMonetaryString(subTotal.getText());

        // Order summary tax
        tax = tax.findElement(By.cssSelector(Constants.SPAN));
        double taxesInt = commonActions.cleanMonetaryString(tax.getText());

        // Order summary discount
        String savingsString = savings.findElement(By.cssSelector(Constants.SPAN)).getText();
        double savings = commonActions.cleanMonetaryString(savingsString);

        // Order summary total
        double totalInt = commonActions.cleanMonetaryString(totalPriceInclTax.getText());
        String orderTotal = totalPriceInclTax.getText();

        Assert.assertTrue("FAIL: Price on PLP page (" + fixedDiscount + ") did not match 'Savings:' price ("
                + savings + ") in order summary.", savings == fixedDiscount);
        Assert.assertTrue("FAIL: Promotional discount (" + savings + ")was not applied correctly.",
                (orderSubTotal + taxesInt - savings) == totalInt);

        LOGGER.info("assertFixedDollarDiscountApplied completed");
        return orderTotal;
    }

    /**
     * Verifies the fixed discount percentage has actually been applied to the total price
     *
     * @param fixedPercentage Amount in Percentage (%) of the item discount
     */
    public String assertFixedPercentageDiscountApplied(int fixedPercentage) {
        LOGGER.info("assertFixedPercentageDiscountApplied started");
        driver.waitForElementVisible(preTotal);
        double taxes = Constants.ZERO;
        if (fixedPercentage == Constants.ZERO) {
            Assert.fail("Fixed Percentage value set to 0, cannot assert the discount applied on the page");
        }

        // Pre-Total Amount
        double preTotalAmount = commonActions.cleanMonetaryString(preTotal.getText());
        // Calculated Savings Based On fixedPercentage

        double calculatedSavings = (preTotalAmount * fixedPercentage) / Constants.ONE_HUNDRED;
        // Order summary subtotal

        WebElement subTotal = webDriver.findElement(orderSummaryRows);
        subTotal = subTotal.findElement(By.cssSelector(Constants.SPAN));
        double orderSubTotal = commonActions.cleanMonetaryString(subTotal.getText());

        // Order summary tax
        // No Tax listed for Eugene Store
        //TODO CCL - Fails in QA due to defect #7191 (No tax on cart page)
        if (!Config.getDefaultStoreCity().contains(ConstantsDtc.DT_STG_DEFAULT_STORE_CITY)) {
            tax = tax.findElement(By.cssSelector(Constants.SPAN));
            taxes = commonActions.cleanMonetaryString(tax.getText());
        }

        // Order summary discount
        //TODO CCL - Fails in STG due to defect #7185 (No savings on cart page)
        String savingsString = savings.findElement(By.cssSelector(Constants.SPAN)).getText();
        double savings = commonActions.cleanMonetaryString(savingsString);

        // Order summary total
        double total = commonActions.cleanMonetaryString(totalPriceInclTax.getText());
        String orderTotal = totalPriceInclTax.getText();

        Assert.assertTrue("FAIL: Price on PLP page (" + calculatedSavings + ") did not match 'Savings:' price ("
                + savings + ") in order summary.", savings == calculatedSavings);
        Assert.assertTrue("FAIL: Promotional discount (" + savings + ")was not applied correctly.",
                (orderSubTotal + taxes - savings) == total);

        LOGGER.info("assertFixedPercentageDiscountApplied completed");
        return orderTotal;
    }

    /**
     * Calculates the taxes and fees for a customer by selecting the country, entering the zip code and clicking the
     * calculate the button / icon.
     *
     * @param cartCustomer Type of customer to pull from dtc.data and use for country and zip code fields
     */
    public void calculateTaxesFeesForCustomer(Customer cartCustomer) {
        LOGGER.info("calculateTaxesFeesForCustomer started");
        driver.waitForPageToLoad();
        //TODO: For DTD QA env, zipode is pre-populated with AZ zipcode
        //TODOL No input textbox to enter or update zipcode
        if (driver.isElementDisplayed(orderSummaryZipCode)) {
            orderSummaryZipCode.sendKeys(cartCustomer.zip);
            orderSummaryZipCode.sendKeys(Keys.RETURN);
        }
        LOGGER.info("calculateTaxesFeesForCustomer completed");
    }

    /**
     * Verifies the specified label (fee or service) is present on the cart page
     *
     * @param labelToVerify String representing the text of the fee or service to verify
     */
    public void assertFeeServiceLabelPresentOnCartPage(String labelToVerify) {
        LOGGER.info("assertFeeServiceLabelPresentOnCartPage started");
        driver.waitForElementVisible(orderFeeServiceItemLabelBy);
        Assert.assertTrue("FAIL: Expected label - \"" + labelToVerify + "\" was not found on the cart page!",
                driver.checkIfElementContainsText(orderFeeServiceItemLabelBy, labelToVerify));
        LOGGER.info("assertFeeServiceLabelPresentOnCartPage completed");
    }

    /**
     * Extracts the amount of the Optional Service specified
     *
     * @param serviceName String of the optional service whose value is to be extracted
     * @return Double type representing the optional service amount
     */
    public Double extractOptionalServiceAmount(String serviceName) {
        LOGGER.info("extractOptionalServiceAmount started");
        driver.waitForElementVisible(orderFeeServiceItemLabelBy);
        WebElement serviceEle = driver.getElementWithText(orderFeeServiceItemLabelBy, serviceName);
        WebElement greatGrandParent = driver.getParentElement(driver.getParentElement(driver.getParentElement(serviceEle)));
        return commonActions.cleanMonetaryString(greatGrandParent.findElement(feeDetailsItemsRowPriceBy).getText());
    }

    /**
     * Verifies the selected tires are displayed on the cart page
     *
     * @param map Hashmap containing brand and product information of the selected tires
     */
    public void assertSelectedTires(HashMap map) {
        LOGGER.info("assertSelectedTires started");

        HashMap<String, ArrayList<String>> values = map;
        driver.waitForPageToLoad();
        String expectedBrand = values.get(ConstantsDtc.BRAND).get(0);
        String expectedProduct = values.get(ConstantsDtc.PRODUCT).get(0);

        String displayBrand = webDriver.findElement(brandName).getText();
        String displayProduct = webDriver.findElement(productName).getText();


        Assert.assertTrue("FAIL: Brand incorrect on Cart page.  Expected:  " + expectedBrand + ".  Actual:  " + displayBrand, displayBrand.equals(expectedBrand));
        Assert.assertTrue("FAIL: Product incorrect on Cart page.  Expected:  " + expectedProduct + ".  Actual:  " + displayProduct, displayProduct.equals(expectedProduct));

        LOGGER.info("Verified the selected tire on the Cart Page is '" + displayBrand + " | " + displayProduct);
        LOGGER.info("assertSelectedTires completed");
    }

    /**
     * Calculate the subtotal on cart page by sum of product, fees and certificate
     */
    public double getCartSubtotal() {
        LOGGER.info("getCartSubtotal started");
        driver.waitForPageToLoad();
        double subtotal = commonActions.twoDForm(commonActions.cleanMonetaryString(priceBox.getText()), 2);
        if (driver.isElementDisplayed(cartSummaryInstantSavingBy)) {
            double savings = commonActions.cleanMonetaryString(webDriver.findElement(cartSummaryInstantSavingBy).getText());
            subtotal = commonActions.twoDForm(subtotal - savings, 2);
        }
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) || Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            subtotal = commonActions.twoDForm(subtotal + commonActions.cleanMonetaryString(getEnvironmentalFee())
                    + commonActions.cleanMonetaryString(getTireDisposalFee())
                    + commonActions.cleanMonetaryString(getInstallationPrice()), 2);
        }
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            subtotal = commonActions.twoDForm(subtotal + commonActions.cleanMonetaryString(getvalveStemFee()), 2);
        }
        double certificateTotal = extractPriceForOptionalFee(Constants.NONE, ConstantsDtc.CERTIFICATE);
        if (certificateTotal != Constants.ZERO) {
            subtotal = commonActions.twoDForm(subtotal
                    + certificateTotal, 2);
        }
        if (driver.isTextPresentInPageSource(ConstantsDtc.FETFEE_LABEL)) {
            subtotal = commonActions.twoDForm(subtotal + commonActions.cleanMonetaryString(getFETFee()), 2);
        }
        WebElement elementLabel = driver.getElementWithText(optionName, ConstantsDtc.TPMS);
        if (elementLabel != null && driver.isTextPresentInPageSource(ConstantsDtc.TPMS)) {
            subtotal = commonActions.twoDForm(subtotal + commonActions.cleanMonetaryString(getTPMSPrice()), 2);
        }
        LOGGER.info("getCartSubtotal completed");
        return subtotal;
    }

    /**
     * Calculate the subtotal on cart page by sum of product, fees and certificate for multiple items
     */
    public double calculateCartSubtotalForItems() {
        LOGGER.info("calculateCartSubtotalForItems started");
        driver.waitForPageToLoad();
        double subtotal = 0.00;
        double finalSubtotal = 0.00;
        Set<String> items = CommonActionsSteps.cartProductPrice.keySet();
        for (String item : items) {
            subtotal = extractItemsPriceTotal(item);
            if (driver.isElementDisplayed(cartSummaryInstantSavingBy)) {
                double savings = commonActions.cleanMonetaryString(webDriver.findElement(cartSummaryInstantSavingBy).getText());
                subtotal = commonActions.twoDForm(subtotal - savings, 2);
            }
            if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) || Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
                subtotal = subtotal
                        + extractFeesForItemsOnCart(ConstantsDtc.ENVIRONMENTAL_FEE, item)
                        + extractFeesForItemsOnCart(ConstantsDtc.DISPOSAL_FEE, item);
                if (!driver.isElementDisplayed(extractInstallationElement(ConstantsDtc.INSTALLATION))) {
                    subtotal = subtotal
                            + commonActions.cleanMonetaryString(getInstallationPrice());
                }
            }
            //TODO - This needs to be extended for FET
            finalSubtotal = finalSubtotal + subtotal;
        }
        if (driver.isElementDisplayed(extractInstallationElement(ConstantsDtc.INSTALLATION))) {
            finalSubtotal = finalSubtotal + commonActions.cleanMonetaryString(
                    extractInstallationElement(ConstantsDtc.INSTALLATION).findElement(feeDetailsItemsRowPriceBy).getText());
        }
        LOGGER.info("calculateCartSubtotalForItems completed");
        return finalSubtotal;
    }

    /**
     * Extract the subtotal on cart page
     */
    public double extractCartSubtotal() {
        LOGGER.info("extractCartSubtotal started");
        driver.waitForPageToLoad();
        WebElement cartSubTotal = driver.getElementWithText(cartSummaryBreakDownNameBy, ConstantsDtc.CART_SUBTOTAL);
        WebElement subTotalParent = driver.getParentElement(driver.getParentElement(cartSubTotal));
        LOGGER.info("extractCartSubtotal completed");
        return commonActions.cleanMonetaryString(subTotalParent.findElement(cartSummaryBreakDownPrice).getText());
    }

    /**
     * Verifies the subtotal on cart page with calculated subtotal
     */
    public void assertExtractedCartSubtotal() {
        LOGGER.info("assertExtractedCartSubtotal started");
        driver.waitForPageToLoad();
        double expectedSubtotal;
        if (webDriver.findElements(itemPriceBy).size() > Constants.ITEM_CLASS_COUNT) {
            expectedSubtotal = calculateCartSubtotalForItems();
        } else {
            expectedSubtotal = getCartSubtotal();
        }
        double actualSubTotal = extractCartSubtotal();
        Assert.assertTrue("FAIL: The actual  subtotal: \"" + actualSubTotal
                + "\" did not contain the expected total: \""
                + expectedSubtotal + "\"!", actualSubTotal == expectedSubtotal);
        LOGGER.info("assertExtractedCartSubtotal completed");
    }

    /**
     * Verify added Certificate TotalPrice in MiniCart with Shopping Cart
     */
    public void assertCertificateTotalPriceMiniCart() {
        LOGGER.info("assertCertificateTotalPriceMiniCart started");
        double miniCartCertificateTotal = commonActions.cleanMonetaryString(miniCartRRACertTotalPrice.getText());
        double cartCertificateTotal = commonActions.cleanMonetaryString(
                driver.getParentElement(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.CERTIFICATE))
                        .findElement(feeDetailsItemsRowPriceBy).getText());
        Assert.assertTrue("FAIL: Added RRR Certificate TotalPrice (" + cartCertificateTotal
                + ")not matching to displayed MiniCart TotalPrice (" + miniCartCertificateTotal
                + ")", miniCartCertificateTotal == cartCertificateTotal);
        LOGGER.info("assertCertificateTotalPriceMiniCart completed");
    }

    /**
     * Verify added Certificate BasePrice in MiniCart with Shopping Cart
     */
    public void assertCertificateBasePriceMiniCart() {
        LOGGER.info("assertCertificateBasePriceMiniCart started");
        double certificateBasePriceMiniCart = commonActions.cleanMonetaryString(miniCartRRACertBasePrice.getText());
        double cartCertificateBasePrice = commonActions.cleanMonetaryString(
                driver.getParentElement(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.CERTIFICATE))
                        .findElement(serviceFeeItemRowPriceBy).getText());
        Assert.assertTrue("FAIL: Added RRR Certificate BasePrice (" + cartCertificateBasePrice
                + ")not matching to displayed MiniCart BasePrice (" + certificateBasePriceMiniCart
                + ")", certificateBasePriceMiniCart == cartCertificateBasePrice);
        LOGGER.info("assertCertificateBasePriceMiniCart completed");
    }

    /**
     * Verify added Certificate Quantity in MiniCart with Shopping Cart
     */
    public void assertCertificateQuantityMiniCart() {
        LOGGER.info("assertCertificateQuantityMiniCart started");
        int miniCartCertificateQuantity = (int) (commonActions.cleanMonetaryString(miniCartRRACertItemQuantity.getText()));
        int cartCertificateQuantity = (int) commonActions.cleanMonetaryString(
                driver.getParentElement(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.CERTIFICATE))
                        .findElement(itemRowQuantityBy).getText());
        Assert.assertTrue("FAIL: Added RRR Certificate itemQuantity expected(" + cartCertificateQuantity
                + ")not matching to displayed MiniCart itemQuantity (" + miniCartCertificateQuantity
                + ")", cartCertificateQuantity == miniCartCertificateQuantity);
        LOGGER.info("assertCertificateQuantityMiniCart completed");
    }

    /**
     * Assert RRR Certificate BasePrice on Shopping Cart Page
     */
    public void assertRRRCertBasePrice() {
        LOGGER.info("assertRRRCertBasePrice started");
        double certBasePrice = extractOptionalServiceAmount(ConstantsDtc.CERTIFICATE);
        driver.waitForPageToLoad();
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            double estimatedCertBasePriceDt = getCalculatedCertFeeDt(Constants.NONE);
            Assert.assertTrue("FAIL: RRR Certificate Base price not matching in DT, Actual : (" + certBasePrice
                    + ") with Estimated : (" + estimatedCertBasePriceDt + ")", certBasePrice == estimatedCertBasePriceDt);
        } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            double estimatedCertBasePriceAt = getCalculatedCertFeeAt(Constants.NONE);
            Assert.assertTrue("FAIL: RRR Certificate Base price not matching in AT, Actual : (" + certBasePrice
                    + ") with Estimated : (" + estimatedCertBasePriceAt + ")", certBasePrice == estimatedCertBasePriceAt);
        } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            double estimatedCertBasePriceDtd = getCalculatedCertFeeDtd(Constants.NONE);
            Assert.assertTrue("FAIL: RRR Certificate Base price not matching in DTD, Actual : (" + certBasePrice
                    + ") with Estimated : (" + estimatedCertBasePriceDtd + ")", certBasePrice == estimatedCertBasePriceDtd);
        }
        LOGGER.info("assertRRRCertBasePrice Completed");
    }

    /**
     * Assert RRR Certificate Quantity on Shopping Cart Page
     */
    public void assertRRRCertQty() {
        LOGGER.info("assertRRRCertQty started");
        int actualCertQty = Integer.parseInt(this.getSpecialPricingOnCartPage(orderFeeServiceItemLabelBy, orderListItemDesc, orderListQty, CERTIFICATE_REPAIR_REFUND_REPLACEMENT));
        int estimatedCertQty = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        Assert.assertTrue("FAIL: RRR Certificate Base price not matching, Actual : (" + actualCertQty
                + ") with Estimated : (" + estimatedCertQty + ")", actualCertQty == estimatedCertQty);
        LOGGER.info("assertRRRCertQty Completed");
    }

    /**
     * Assert RRR Certificate Total on Shopping Cart Page
     */
    public void assertCertificateTotal() {
        LOGGER.info("assertCertificateTotal started");
        driver.waitForPageToLoad();
        double estimatedCertificatePrice = 0;
        Set<String> itemCodes = CommonActionsSteps.cartProductPrice.keySet();
        for (String itemCode : itemCodes) {
            if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
                estimatedCertificatePrice = getCalculatedCertFeeDt(itemCode);
            } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
                estimatedCertificatePrice = getCalculatedCertFeeAt(itemCode);
            } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
                estimatedCertificatePrice = getCalculatedCertFeeDtd(itemCode);
            }
            int itemQuantity = Integer.parseInt(productParent.get(itemCode).findElement(cartItemQuantityBy).getAttribute(Constants.VALUE));
            double expectedCertificateTotal = (estimatedCertificatePrice * itemQuantity);
            double actualCertificateTotal = extractPriceForOptionalFee(itemCode, ConstantsDtc.CERTIFICATE);
            Assert.assertTrue("FAIL: Certificate Fee didn't match! (displayed:-> " + actualCertificateTotal + "expected:-> "
                    + expectedCertificateTotal + ")", actualCertificateTotal == expectedCertificateTotal);
        }
        LOGGER.info("assertCertificateTotal Completed");
    }

    /**
     * Assert provided "TPMS Rebuild Kits" price calculated based on  item quantity and the rendered price
     * on cart's page
     *
     * @param tpmsRebldFee The string fee to check
     */
    public void assertTPMSRebldPrice(String tpmsRebldFee) {
        LOGGER.info("assertTPMSRebldPrice started");
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double fee = Double.parseDouble(tpmsRebldFee);
        double expectedTPMSRebldFee = (fee * itemQuantity);
        double actualTPMSRebuildPrice = commonActions.cleanMonetaryString(getSpecialPricingOnCartPage(orderListPartsInfo,
                orderListItemDesc, orderListTotal, TPMS_REBUILD_KIT));
        Assert.assertTrue("FAIL: TPMS Rebuild Kits Price didn't match! (displayed:-> " + actualTPMSRebuildPrice
                + " with expected:->  " + expectedTPMSRebldFee + ")", expectedTPMSRebldFee == actualTPMSRebuildPrice);
        LOGGER.info("TPMS Rebuild Kits Price matched: (displayed:-> " + actualTPMSRebuildPrice + " with expected:->  "
                + expectedTPMSRebldFee + ")");
        LOGGER.info("assertTPMSRebldPrice completed");
    }

    /**
     * Add TPMS Sensor
     */
    public void addTPMSSensor() {
        LOGGER.info("addTPMSSensor started");
        driver.jsScrollToElement(tpmsMoreOpts);
        tpmsMoreOpts.click();
        driver.waitForElementVisible(tpmsSensor);
        String tpmsWindowHandler = webDriver.getWindowHandle();
        webDriver.switchTo().window(tpmsWindowHandler);
        tpmsSensorRadioBtn.click();
        LOGGER.info("addTPMSSensor completed");
    }

    /**
     * Checks if "TPMS Sensor" text is displayed
     */
    public void assertTPMSSensorLabelDisplayed() {
        LOGGER.info("assertTPMSSensorLabelDisplayed started");
        String textLookUp = TPMS_SENSOR;
        driver.waitForElementNotVisible(By.linkText(TPMS_REBUILD_KIT));
        driver.waitForElementVisible(tpmsSensorLabel);
        Assert.assertTrue("FAIL: Text NOT matched; Found this \"" + tpmsSensorLabel.getText()
                + "\" but expected this \"" + textLookUp + "\"!", tpmsSensorLabel.getText().matches(textLookUp));
        LOGGER.info("assertTPMSSensorLabelDisplayed completed");
    }

    /**
     * Extracts the TPMS Sensor Price
     *
     * @return String
     */
    private String getTPMSSensorPrice() {
        return this.getSpecialPricingOnCartPage(orderListPartsInfo, orderListItemDesc, orderListTotal,
                TPMS_SENSOR);
    }

    /**
     * Assert price on mini cart button
     */
    public void assertMiniCartPrice() {
        LOGGER.info("assertMiniCartPrice started");
        driver.waitForElementVisible(miniCartPrice);
        double miniCartPrc = commonActions.cleanMonetaryString(miniCartPrice.getText());
        double subtotalPrc = commonActions.cleanMonetaryString(subTotal.getText());
        Assert.assertTrue("FAIL: Minicart price : (" + miniCartPrc + ") not matching to subtotal : ("
                        + subtotalPrc + ")",
                miniCartPrc == subtotalPrc);
        LOGGER.info("assertMiniCartPrice completed");
    }

    /**
     * Verifies the order summary total on cart page
     */
    public void assertCartOrderPriceTotal() {
        LOGGER.info("assertCartOrderPriceTotal started");
        driver.waitForElementVisible(orderTotal);
        double expectedOrderTotal = extractCartSubtotal() + extractTaxOnCart();
        double actualOrderTotal = commonActions.cleanMonetaryString(orderTotal.getText());
        Assert.assertTrue("FAIL: The actual order total: \"" + actualOrderTotal
                        + "\" did not match to expected order total: \"" + expectedOrderTotal + "\"!",
                actualOrderTotal == expectedOrderTotal);
        LOGGER.info("assertCartOrderPriceTotal completed");
    }

    /**
     * Assert provided "Installation and Spin Balancing" price calculated based on item quantity and
     * expected Install price on cart's page
     */
    public void assertInstallationPrice() {
        LOGGER.info("assertInstallationPrice started");
        double actualInstallFee = commonActions.cleanMonetaryString(getInstallationPrice());
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double fee = ConstantsDtc.INSTALLATION_FEE_AZ;
        double expectedInstallFee = (fee * itemQuantity);
        Assert.assertTrue("FAIL: Installation & Spin Balancing Price didn't match! (displayed:-> "
                + actualInstallFee + " expected:->  " + expectedInstallFee + ")", expectedInstallFee == actualInstallFee);
        LOGGER.info("assertInstallationPrice completed");
    }

    /**
     * Assert provided "TPMS Sensor" price matches with the rendered price
     * on cart's page
     *
     * @param tpmsSensorFee The string fee to check
     */
    public void assertTPMSSensorPrice(String tpmsSensorFee) {
        LOGGER.info("assertTPMSSensorPrice started");
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double fee = Double.parseDouble(tpmsSensorFee);
        double expectedTPMSSensorFee = (fee * itemQuantity);
        double actualTPMSRebuildPrice = Double.parseDouble(getTPMSSensorPrice());
        Assert.assertTrue("FAIL: TPMS Rebuild Kits Price didn't match! (displayed:-> " + actualTPMSRebuildPrice
                + " with expected:->  " + expectedTPMSSensorFee + ")", expectedTPMSSensorFee == actualTPMSRebuildPrice);
        LOGGER.info("assertTPMSSensorPrice completed");
    }

    public void verifyElementInCart(String element, String text) {
        LOGGER.info("verifyElementInCart started");

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isMobile() || Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (!Config.isSafari()) {
            driver.waitForPageToLoad();
        } else {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        }

        driver.waitForElementVisible(productSpec);
        boolean textPresent;
        if (element.equalsIgnoreCase(PRODUCT_NAME)) {
            textPresent = driver.waitForTextPresentIgnoreCase(cartProductBy, text, Constants.THREE_SEC_WAIT);
        } else if (element.equalsIgnoreCase(ITEM_CODE)) {
            textPresent = driver.waitForTextPresent(cartItemCodeBy, text, Constants.THREE_SEC_WAIT);
        } else {
            textPresent = driver.waitForTextPresent(cartItemCodeBy, element, Constants.THREE_SEC_WAIT);
        }
        Assert.assertTrue("'" + text + "' was not present on the cart page", textPresent);
        LOGGER.info("verifyElementInCart completed");
    }

    /**
     * Selects the 'Checkout Now' button on the Cart page
     */
    public void selectCheckoutNow() {
        LOGGER.info("selectCheckoutNow started");
        //NOTE: Firefox flashes the page, then displays blank page for several seconds
        //This caused waitForPageToLoad to only work intermittently. Adding a second call resolves it
        if (Config.isFirefox() || Config.isSafari())
            driver.waitForPageToLoad();

        driver.waitForPageToLoad();
        driver.waitForElementVisible(checkoutButton);
        driver.jsScrollToElement(checkoutButton);
        checkoutButton.click();
        LOGGER.info("selectCheckoutNow completed");
    }

    /**
     * Takes the desired action on the Certificates for Repair and Replacement popup
     *
     * @param action Continue to Checkout OR Add the certificates for product
     */
    public void selectActionOnPopUpCertsForRandR(String action) {
        LOGGER.info("selectActionOnPopUpCertsForRandR started");
        driver.waitForPageToLoad();

        if (driver.isElementDisplayed(certForRRPopUpBy)) {
            String buttonText;
            if (action.toLowerCase().contains(ConstantsDtc.CONTINUE_TO_CHECKOUT.toLowerCase())) {
                buttonText = ConstantsDtc.CONTINUE_TO_CHECKOUT;
            } else {
                buttonText = ConstantsDtc.ADD;
            }
            driver.clickElementWithText(buttonBy, buttonText);
        }
        LOGGER.info("selectActionOnPopUpCertsForRandR completed");
    }

    /**
     * Clicks 'Show Fee Details' on shopping cart page
     *
     * @param itemCode product item to reference.
     */
    public void clickShowFeeDetails(String itemCode) {
        LOGGER.info("clickShowFeeDetails started");
        driver.waitForPageToLoad();
        // TODO: retest when new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (itemCode.contains(Constants.NONE)) {
            driver.clickElementWithText(buttonBy, SHOW_FEE_DETAILS);
        } else {
            WebElement itemGrandParent = driver.getParentElement(
                    driver.getParentElement(
                            driver.getParentElement(
                                    driver.getParentElement(
                                            driver.getElementWithText(cartItemCodeBy, itemCode)
                                    )
                            )
                    )
            );
            driver.jsScrollToElement(itemGrandParent.findElement(showFeeDetailsBy));
            itemGrandParent.findElement(showFeeDetailsBy).click();
        }
        LOGGER.info("clickShowFeeDetails completed");
    }

    /**
     * Extracts the parent section for selected item
     *
     * @param itemCode product code
     * @return itemParents Web elements list Grand Parent for item Code
     */
    public List<WebElement> getRowParents(String itemCode) {
        LOGGER.info("getRowParents started");
        driver.waitForPageToLoad();
        WebElement productCode = driver.getElementWithText(cartItemCodeBy, itemCode);
        WebElement itemGrandParent = driver.getParentElement(driver.getParentElement(driver.getParentElement(driver.getParentElement(productCode))));
        List<WebElement> itemParents = itemGrandParent.findElements(orderSummaryRows);
        LOGGER.info("getRowParents completed");
        return itemParents;
    }

    /**
     * Extracts the Pricing among multiple Web elements that contains text substring
     *
     * @param itemParents The Web elements parent list with
     * @param text        The string to lookup the specific element
     * @return pricing
     */
    public double getPrice(List<WebElement> itemParents, String text) {
        LOGGER.info("getPrice started for:" + text);
        boolean StringFound = false;
        double pricing = Constants.ZERO;
        for (WebElement itemParent : itemParents) {
            if (itemParent.findElement(feeDetailsItemsRowLabelBy).getText().contains(text)) {
                StringFound = true;
                Assert.assertTrue("FAIL: Each item Price not displayed for "
                        + text, driver.isElementDisplayed(itemParent.findElement(serviceFeeItemRowPriceBy)));
                Assert.assertTrue("FAIL: Each item Quantity not displayed for "
                        + text, driver.isElementDisplayed(itemParent.findElement(itemRowQuantityBy)));
                Assert.assertTrue("FAIL: Total item Price not displayed for "
                        + text, driver.isElementDisplayed(itemParent.findElement(feeDetailsItemsRowPriceBy)));
                pricing = commonActions.cleanMonetaryString(itemParent.findElement(feeDetailsItemsRowPriceBy).getText());
                break;
            }
        }
        if (!StringFound) {
            Assert.fail("FAIL: String \"" + text + "\" NOT found!");
        }
        LOGGER.info("getPrice completed for:" + text);
        return pricing;
    }

    /**
     * Get Product/quantity parent on cart page
     *
     * @param itemCode The quantity value to validate
     * @return Webelement Grand Parent for item Quantity
     */
    public WebElement getParent(String itemCode) {
        LOGGER.info("getParent started");
        driver.waitForPageToLoad();
        WebElement productCode = driver.getElementWithText(cartItemCodeBy, itemCode);
        WebElement grandParent = driver.getParentElement(
                driver.getParentElement(
                        driver.getParentElement(productCode)
                ));
        LOGGER.info("getParent completed");
        return grandParent;
    }

    /**
     * Assert provided "Installation and Spin Balancing" price calculated based on item quantity and
     * rendered price on cart's page
     *
     * @param quantity Webelement quantity Parent
     * @param price    The double fee to compare with the calculated fee
     */
    public void assertInstallationPrice(double price, WebElement quantity) {
        LOGGER.info("assertInstallationPrice started");
        int itemQuantity = ConstantsDtc.DEFAULT_QUANTITY;
        List<WebElement> items = driver.getParentElement(quantity).findElements(orderSummaryRows);
        for (WebElement item : items) {
            if (item.findElement(feeDetailsItemsRowLabelBy).getText().contains(ConstantsDtc.INSTALLATION)) {
                itemQuantity = Integer.parseInt(item.findElement(itemRowQuantityBy).getText());
                break;
            }
        }
        double fee = ConstantsDtc.INSTALLATION_FEE_AZ;
        double InstallationFee = (fee * itemQuantity);
        Assert.assertTrue("FAIL: Installation Price didn't match! (displayed:-> "
                + price + " expected:->  " + InstallationFee + ")", price == InstallationFee);
        LOGGER.info("assertInstallationPrice completed");
    }

    /**
     * Assert provided Item price on pdp page matches with the item price on cart page
     * Assert itemTotal = PDP item price * quantity
     */
    public void assertCartProductPriceTotal() {
        LOGGER.info("assertCartProductPriceTotal started");
        driver.waitForElementVisible(priceBox);
        int itemQuantity = Integer.parseInt(quantityInfoBox.getAttribute(Constants.VALUE));
        double actualItemTotal = commonActions.cleanMonetaryString(priceBox.getText());
        double actualItemPrice = commonActions.cleanMonetaryString(webDriver.findElement(itemPriceEachBy).getText());
        double expectedItemTotal = (CommonActionsSteps.pdpPrice * itemQuantity);
        Assert.assertTrue("FAIL: Item price PDP Page (" + CommonActionsSteps.pdpPrice + ")not Equal to item price on cart page" +
                "(" + actualItemPrice, CommonActionsSteps.pdpPrice == actualItemPrice);
        Assert.assertTrue("FAIL: The actual item-total (" + actualItemTotal + ")not equal to Expected item-Total Price " +
                "(" + expectedItemTotal + ")", expectedItemTotal == actualItemTotal);
        LOGGER.info("assertCartProductPriceTotal completed");
    }

    /**
     * Extract product details on Cart page having product code and product price
     *
     * @return product details map
     */
    public HashMap<String, String> getProductPriceOnCart() {
        LOGGER.info("getProductPriceOnCart started");
        List<WebElement> itemElements = webDriver.findElements(cartItemProductBy);
        for (WebElement itemElement : itemElements) {
            productPriceOnCart.put(itemElement.findElement(cartItemCodeBy).getText().split("#")[1].trim()
                    , itemElement.findElement(orderSummaryRows).findElement(serviceFeeItemRowPriceBy).getText().replaceAll("[^0-9.]", ""));
        }
        LOGGER.info("getProductPriceOnCart completed");
        return productPriceOnCart;
    }

    /**
     * Get product parent mapping with product code
     *
     * @return productParent map
     */
    public HashMap<String, WebElement> getProductParent() {
        LOGGER.info("getProductParent started");
        Set<String> items = CommonActionsSteps.cartProductPrice.keySet();
        for (String item : items) {
            productParent.put(
                    item, getParent(item)
            );
        }
        LOGGER.info("getProductParent completed");
        return productParent;
    }

    /**
     * Set product parent mapping
     *
     * @param productParent map
     */
    public static void setProductParent(HashMap<String, WebElement> productParent) {
        CartPage.productParent = productParent;
    }

    /**
     * Get fee parents mapping with product code
     *
     * @return fee parents map
     */
    public HashMap<String, List<WebElement>> getFeeParents() {
        LOGGER.info("getFeeParents started");
        Set<String> items = CommonActionsSteps.cartProductPrice.keySet();
        for (String item : items) {
            feeParents.put(
                    item, getRowParents(item)
            );
        }
        LOGGER.info("getFeeParents completed");
        return feeParents;
    }

    /**
     * Set fee parents mapping
     *
     * @param feeParents map
     */
    public static void setFeeParents(HashMap<String, List<WebElement>> feeParents) {
        CartPage.feeParents = feeParents;
    }

    /**
     * Assert provided for items on Cart page with PLP page item codes and prices
     */
    public void assertProductsAndPricesOnCartPageFromPlp() {
        LOGGER.info("assertProductsAndPricesOnCartPageFromPlp started");
        driver.waitForPageToLoad();
        Set<String> itemCodes = CommonActionsSteps.plpProductPrice.keySet();
        setProductParent(getProductParent());
        setFeeParents(getFeeParents());
        for (String itemCode : itemCodes) {
            if (driver.isElementDisplayed(driver.getElementWithText(cartItemCodeBy, itemCode))) {
                String price = productParent.get(itemCode).findElement(itemPriceEachBy).getText();
                String expectedPrice = CommonActionsSteps.plpProductPrice.get(itemCode);
                Assert.assertTrue("FAIL: Item Price didn't match! (displayed:-> "
                        + price + " expected:->  " + expectedPrice + ")", expectedPrice.contains(price));
            } else {
                Assert.fail("FAIL: Added Item " + itemCode + "not found on Cart Page");
            }
        }
        LOGGER.info("assertProductsAndPricesOnCartPageFromPlp completed");
    }

    /**
     * Calculate items total on cart page with extracted item price from PLP page multiplied with quantity displayed
     */
    public double calculateItemsPriceTotal(String itemCode) {
        LOGGER.info("calculateItemsPriceTotal started");
        double price = commonActions.cleanMonetaryString(CommonActionsSteps.plpProductPrice.get(itemCode).split("[a-z]")[0].trim());
        int itemQuantity = Integer.parseInt(productParent.get(itemCode).findElement(cartItemQuantityBy).getAttribute(Constants.VALUE));
        double calculatedTotal = (price * itemQuantity);
        LOGGER.info("calculateItemsPriceTotal completed");
        return calculatedTotal;
    }

    /**
     * Extract items Total On cart page
     *
     * @param itemCode product code
     * @return itemsTotal
     */
    public double extractItemsPriceTotal(String itemCode) {
        LOGGER.info("extractItemsPriceTotal started");
        double itemTotal = commonActions.cleanMonetaryString(productParent.get(itemCode).findElement(itemPriceBy).getText());
        LOGGER.info("extractItemsPriceTotal completed");
        return itemTotal;
    }

    /**
     * Assert items total On Cart page with item price on Plp page multiplied with quantity
     */
    public void assertItemsPriceTotal() {
        LOGGER.info("assertItemsPriceTotal started");
        Set<String> itemCodes = CommonActionsSteps.cartProductPrice.keySet();
        for (String itemCode : itemCodes) {
            double iItemTotal = extractItemsPriceTotal(itemCode);
            double expectedTotal = calculateItemsPriceTotal(itemCode);
            Assert.assertTrue("FAIL: Item total Price didn't match! (displayed:-> "
                    + itemTotal + " expected:->  " + expectedTotal + ")", iItemTotal == expectedTotal);
        }
        LOGGER.info("assertItemsPriceTotal completed");
    }

    /**
     * Calculate total fee on cart page by getting the calculated fee amount multiplied with quantity
     *
     * @param text     fee type
     * @param itemCode product code
     * @return calculated fee
     */
    public double calculateFeesOnCart(String text, String itemCode) {
        LOGGER.info("calculateFeesOnCart started for " + text);
        double fee = 0.00;
        double calculatedFee;
        int itemQuantity;
        setProductParent(getProductParent());
        itemQuantity = Integer.parseInt(productParent.get(itemCode).findElement(cartItemQuantityBy).getAttribute(Constants.VALUE));
        if (text.equalsIgnoreCase(ConstantsDtc.INSTALLATION)) {
            fee = ConstantsDtc.INSTALLATION_FEE_AZ;
            itemQuantity = Integer.parseInt(extractInstallationElement(text).findElement(itemRowQuantityBy).getText());
        }
        if (text.equalsIgnoreCase(ConstantsDtc.ENVIRONMENTAL_FEE)) {
            fee = getCalculatedEnvironmentFee(itemCode);
        }
        if (text.equalsIgnoreCase(ConstantsDtc.DISPOSAL_FEE)) {
            fee = getCalculatedDisposalFee(itemCode);
        }
        calculatedFee = (fee * itemQuantity);
        LOGGER.info("calculateFeesOnCart completed for " + text);
        return calculatedFee;
    }

    /**
     * Extract the fee on Cart page based on fee type and product code
     *
     * @param text     fee type
     * @param itemCode product code
     * @return extracted fee
     */
    public double extractFeesForItemsOnCart(String text, String itemCode) {
        LOGGER.info("extractFeesForItemsOnCart started for " + text);
        double extractedFee;
        if (text.contains(ConstantsDtc.INSTALLATION)) {
            extractedFee = commonActions.cleanMonetaryString(extractInstallationElement(text).findElement(feeDetailsItemsRowPriceBy).getText());
        } else {
            extractedFee = getPrice(feeParents.get(itemCode), text);
        }
        LOGGER.info("extractFeesForItemsOnCart completed for " + text);
        return extractedFee;
    }

    /**
     * Verify displayed fee for product on cart page with calculated fee based on fee type
     *
     * @param text     fee type
     * @param itemCode product code
     */
    public void assertFeesForItemsOnCart(String text, String itemCode) {
        LOGGER.info("assertFeesForItemsOnCart started for " + text);
        double calculatedFee = calculateFeesOnCart(text, itemCode);
        double extractedFee = extractFeesForItemsOnCart(text, itemCode);
        Assert.assertTrue("FAIL: Installation Price didn't match! (displayed:-> "
                + extractedFee + " expected:->  " + calculatedFee + ")", extractedFee == calculatedFee);
        LOGGER.info("assertFeesForItemsOnCart completed for " + text);
    }

    /**
     * Verifies the specified item appears on the Cart page with the expected quantity
     *
     * @param itemNumber       The item number expected to be on the Cart page
     * @param expectedQuantity The expected quantity of the specified item number
     */
    public void assertItemQuantityOnCartPage(String itemNumber, String expectedQuantity) {
        LOGGER.info("assertItemQuantityOnCartPage started with item '" + itemNumber
                + "' and expected quantity of '" + expectedQuantity + "'");
        By itemQuantityBy = By.xpath
                ("ancestor::div[@class='cart-item__product']//input[@class='cart-item__item-quantity']");
        WebElement itemQuantityField = null;
        String itemQuantity = "";
        driver.waitForElementVisible(orderList);

        List<WebElement> itemNumbersList = webDriver.findElements(cartItemCodeBy);

        for (WebElement item : itemNumbersList) {
            if (item.getText().contains(itemNumber)) {
                itemQuantityField = item.findElement(itemQuantityBy);
                itemQuantity = itemQuantityField.getAttribute(Constants.VALUE);
                break;
            }
        }

        if (itemQuantityField != null) {
            Assert.assertTrue("FAIL: For item '" + itemNumber + "' the expected quantity was '"
                            + expectedQuantity + "' but was actually '" + itemQuantity + "'!",
                    itemQuantity.equalsIgnoreCase(expectedQuantity));
        } else {
            Assert.fail("FAIL: Unable to find the item quantity field for item number '" + itemNumber + "'!");
        }
        LOGGER.info("assertItemQuantityOnCartPage completed with item '" + itemNumber
                + "' and expected quantity of '" + expectedQuantity + "'");
    }

    /**
     * Extracts the Pricing for optional fee
     *
     * @param itemCode The product code
     * @param feeType  The string to lookup the specific optional fee
     * @return pricing
     */
    public double extractPriceForOptionalFee(String itemCode, String feeType) {
        LOGGER.info("getPriceForOptionalFee started for:" + feeType);
        boolean StringFound = false;
        double pricing = Constants.ZERO;
        driver.waitForElementVisible(feeDetailsItemsRowPriceBy);
        WebElement itemGrandParent;
        if (itemCode.equalsIgnoreCase(Constants.NONE)) {
            itemGrandParent = webDriver.findElement(cartItemDetailsParentBy);
        } else {
            itemGrandParent = driver.getParentElement(getParent(itemCode));
        }
        List<WebElement> feeForSelectionElements = itemGrandParent.findElements(optionName);
        for (WebElement element : feeForSelectionElements) {
            if (element.getText().contains(feeType)) {
                StringFound = true;
                WebElement pricingElement = driver.getParentElement(
                        driver.getParentElement(
                                driver.getParentElement(
                                        driver.getParentElement(element))
                        )
                );
                pricing = commonActions.cleanMonetaryString(pricingElement.findElement(feeDetailsItemsRowPriceBy).getText());
                break;
            }
        }
        if (!StringFound) {
            Assert.fail("FAIL: String \"" + feeType + "\" NOT found!");
        }
        LOGGER.info("getPriceForOptionalFee completed for:" + feeType);
        return pricing;
    }

    /**
     * Select the optional fee
     *
     * @param itemCode The product code
     * @param feeToAdd The string to lookup the specific optional fee
     */
    public void addOptionalFeeOnCartPage(String itemCode, String feeToAdd) {
        LOGGER.info("addOptionalFeeOnCartPage started");
        driver.waitForElementVisible(orderFeeServiceItemLabelBy);
        if (itemCode.contains(Constants.NONE)) {
            WebElement serviceOptionEle = driver.getElementWithText(feeServiceItemLabelBy, feeToAdd);
            serviceOptionEle.click();
        } else {
            WebElement itemGrandParent = driver.getParentElement(getParent(itemCode));
            List<WebElement> feeForSelectionElements = itemGrandParent.findElements(optionName);
            driver.waitForElementVisible(optionName);
            for (WebElement element : feeForSelectionElements) {
                if (element.getText().contains(feeToAdd)) {
                    driver.waitForElementClickable(element);
                    element.click();
                    driver.waitForPageToLoad();
                    break;
                }
            }
        }
        LOGGER.info("addOptionalFeeOnCartPage completed");
    }

    /**
     * Calculate optional fee on cart page by getting the calculated fee amount multiplied with quantity
     *
     * @param feeType  Optional fee type
     * @param itemCode product code
     * @return calculated fee
     */
    public double calculateOptionalFeesOnCart(String itemCode, String feeType) {
        LOGGER.info("calculateOptionalFeesOnCart started for " + feeType);
        double fee = 0.00;
        double calculatedFee;
        setProductParent(getProductParent());
        int itemQuantity = Integer.parseInt(productParent.get(itemCode).findElement(cartItemQuantityBy).getAttribute(Constants.VALUE));
        List<WebElement> feeForSelectionElements = driver.getParentElement(getParent(itemCode)).findElements(optionName);
        driver.waitForElementVisible(optionName);
        for (WebElement element : feeForSelectionElements) {
            if (element.getText().contains(feeType)) {
                fee = commonActions.cleanMonetaryString(element.getText());
            }
        }
        calculatedFee = (fee * itemQuantity);
        LOGGER.info("calculateOptionalFeesOnCart completed for " + feeType);
        return calculatedFee;
    }

    /**
     * Verify the optional fee price on cart page with the expected fee price
     *
     * @param text Optional fee type
     */
    public void assertOptionalFeePriceOnCart(String text) {
        LOGGER.info("assertOptionalFeePriceOnCart started for " + text);
        Set<String> itemCodes = CommonActionsSteps.cartProductPrice.keySet();
        for (String itemCode : itemCodes) {
            double calculatedFee = calculateOptionalFeesOnCart(itemCode, text);
            double extractedFee = extractPriceForOptionalFee(itemCode, text);
            Assert.assertTrue("FAIL: Installation Price didn't match! (displayed:-> "
                    + extractedFee + " expected:->  " + calculatedFee + ")", extractedFee == calculatedFee);
        }
        LOGGER.info("assertOptionalFeePriceOnCart completed for " + text);
    }

    /**
     * Update quantity for the product on cart page
     *
     * @param quantity Quantity to update
     * @param item     product code
     */
    public void updateQuantityForItem(String item, String quantity) {
        LOGGER.info("updateQuantityForItem started");
        driver.waitForPageToLoad();
        int itemQuantityByIndex = 0;
        if (Config.isMobile()) {
            itemQuantityByIndex = 1;
        }
        WebElement itemQuantity = driver.getParentElement(getParent(item)).findElements(cartItemQuantityBy).get(itemQuantityByIndex);
        driver.jsScrollToElementClick(itemQuantity);
        itemQuantity.clear();
        itemQuantity.sendKeys(quantity);

        if (Config.isSafari()) {
            itemQuantity.sendKeys(Keys.ENTER);
            driver.waitForMilliseconds();
        } else if (Config.isIe()) {
            priceBox.click();
        } else {
            itemQuantity.sendKeys(Keys.RETURN);
        }
        driver.waitForPageToLoad();
        LOGGER.info("updateQuantityForItem completed");
    }

    /**
     * Remove item from the cart page
     *
     * @param itemCode product code
     */
    public void removeItem(String itemCode) {
        LOGGER.info("removeItem started");
        WebElement remove = getParent(itemCode).findElement(removeItemBy);
        if (Config.isSafari()) {
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        }
        driver.waitForElementVisible(remove);
        driver.jsScrollToElementClick(remove);
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        driver.clickElementWithText(buttonBy, ConstantsDtc.REMOVE_ITEM_FROM_CART);
        driver.waitForPageToLoad();
        LOGGER.info("removeItem completed");
    }

    /**
     * Verify removed item not displayed on cart page
     *
     * @param itemCode product code
     */
    public void assertRemovedItemNotDisplayed(String itemCode) {
        LOGGER.info("assertRemovedItemNotDisplayed started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(cartItemCodeBy);
        Assert.assertTrue("FAIL: Product not removed from the cart page",
                driver.getElementsWithText(cartItemCodeBy, itemCode).size() < 1);
        LOGGER.info("assertRemovedItemNotDisplayed completed");
    }

    /**
     * Verify fee label is displayed for item
     *
     * @param text     fee type
     * @param itemCode product code
     */
    public void assertFeeLabelDisplayed(String text, String itemCode) {
        LOGGER.info("assertFeeLabelDisplayed started for " + text);
        boolean StringFound = false;

        if (itemCode.equalsIgnoreCase(Constants.NONE)) {
            WebElement elementLabel = driver.getElementWithText(childTip, text);
            if (elementLabel != null && driver.isTextPresentInPageSource(text)) {
                StringFound = true;
                Assert.assertTrue("FAIL: Fee label NOT matched; Found this \"" + elementLabel.getText()
                        + "\" but expected this \"" + text + "\"!", elementLabel.getText().matches(text));
            }
        } else {
            List<WebElement> labels = driver.getParentElement(getParent(itemCode)).findElements(feeDetailsItemsRowLabelBy);
            for (WebElement label : labels) {
                if (label.getText().contains(text)) {
                    StringFound = true;
                    Assert.assertTrue("FAIL:Fee label" + text + "not displayed for " + itemCode, label.getText().contains(text));
                    break;
                }
            }
        }
        if (!StringFound) {
            Assert.fail("FAIL: Fee label \"" + text + "\" NOT displayed !");
        }
        LOGGER.info("assertFeeLabelDisplayed completed for " + text);
    }

    /**
     * Verify the fee quantity displayed for respective product code
     *
     * @param feeType  fee type displayed for product
     * @param itemCode product code
     * @param quantity fee quantity
     */
    public void assertFeeQuantity(String feeType, String itemCode, String quantity) {
        LOGGER.info("assertFeeQuantity started for " + feeType);
        List<WebElement> items = driver.getParentElement(getParent(itemCode)).findElements(orderSummaryRows);
        for (WebElement item : items) {
            if (item.findElement(feeDetailsItemsRowLabelBy).getText().contains(feeType)) {
                String feeQuantity = item.findElement(itemRowQuantityBy).getText();
                Assert.assertTrue("FAIL: Fee quantity didn't match for" + feeType + "(displayed:-> "
                        + feeQuantity + " expected:->  " + quantity + ")", feeQuantity.equals(quantity));
                break;
            }
        }
        LOGGER.info("assertFeeQuantity completed for " + feeType);
    }

    /**
     * Verify Order Summary section verbiage on cart page
     */
    public void assertCartSummaryVerbiagesDisplay() {
        LOGGER.info("assertCartSummaryVerbiagesDisplay started");
        driver.waitForElementVisible(cartSummaryHeadingBy);
        WebElement orderSummaryVerbiage = driver.getElementWithText(cartSummaryHeadingBy, ConstantsDtc.ORDER_SUMMARY);
        WebElement cartSubtotalVerbiage = driver.getElementWithText(cartSummaryBreakDownNameBy, ConstantsDtc.CART_SUBTOTAL);
        WebElement estimatedTaxVerbiage = driver.getElementWithText(cartSummaryBreakDownNameBy, ConstantsDtc.ESTIMATED_TAX);
        WebElement totalVerbiage = driver.getElementWithText(cartItemTotalPriceBy, ConstantsDtc.TOTAL);

        Assert.assertTrue("FAIL: Order Summary Verbiage NOT displayed!", driver.isElementDisplayed(orderSummaryVerbiage));
        Assert.assertTrue("FAIL: Cart Total Verbiage NOT displayed!", driver.isElementDisplayed(totalVerbiage));
        Assert.assertTrue("FAIL: Cart Subtotal Verbiage NOT displayed!", driver.isElementDisplayed(cartSubtotalVerbiage));
        Assert.assertTrue("FAIL: Estimated Tax Verbiage not displayed!", driver.isElementDisplayed(estimatedTaxVerbiage));
        LOGGER.info("assertCartSummaryVerbiagesDisplay completed");
    }

    /**
     * Calculates & Set Environment Fee to a class variable for later use across pages
     */
    public void setEnvironmentFee() {
        LOGGER.info("setEnvironmentFee started");
        if (Config.isSafari()) {
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        }
        environmentFee = String.valueOf(commonActions.cleanMonetaryString(getEnvironmentalFee()));
        LOGGER.info("setEnvironmentFee completed");
    }

    /**
     * Calculate item subtotal on cart page by sum of product price and fees applied for item
     *
     * @param itemCode product code
     */
    public double calculateItemSubtotal(String itemCode) {
        LOGGER.info("calculateItemSubtotal started");
        driver.waitForPageToLoad();
        setProductParent(getProductParent());
        setFeeParents(getFeeParents());
        double itemSubtotal = extractItemsPriceTotal(itemCode);
        if (driver.isElementDisplayed(cartSummaryInstantSavingBy)) {
            double savings = commonActions.cleanMonetaryString(webDriver.findElement(cartSummaryInstantSavingBy).getText());
            itemSubtotal = commonActions.twoDForm(itemSubtotal - savings, 2);
        }
        String productElement = driver.getParentElement(getParent(itemCode)).findElement(cartItemProductSize).getText();
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) && productElement.contains(ConstantsDtc.WHEEL_ELEMENT)
                || Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT) && productElement.contains(ConstantsDtc.WHEEL_ELEMENT)) {
            if (driver.isElementDisplayed(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.HUB_CENTRIC_RING))) {
                itemSubtotal = itemSubtotal + extractFeesForItemsOnCart(ConstantsDtc.HUB_CENTRIC_RING, itemCode);
            }
            if (driver.isElementDisplayed(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.WHEEL_INSTALL_KIT))) {
                itemSubtotal = itemSubtotal + extractFeesForItemsOnCart(ConstantsDtc.WHEEL_INSTALL_KIT, itemCode);
            }
        } else {
            itemSubtotal = itemSubtotal + extractFeesForItemsOnCart(ConstantsDtc.ENVIRONMENTAL_FEE, itemCode)
                    + extractFeesForItemsOnCart(ConstantsDtc.DISPOSAL_FEE, itemCode);
            if (driver.isElementDisplayed(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.INSTALLATION))) {
                itemSubtotal = itemSubtotal + getPrice(getRowParents(itemCode), ConstantsDtc.INSTALLATION);
            }
        }
        LOGGER.info("calculateItemSubtotal completed");
        return itemSubtotal;
    }

    /**
     * Extract item subtotal on cart page for item
     *
     * @param itemCode product code
     */
    public double extractItemSubtotal(String itemCode) {
        LOGGER.info("extractItemSubtotal started");
        WebElement productParent = driver.getParentElement(driver.getParentElement(getParent(itemCode)));
        double itemSubtotal = commonActions.cleanMonetaryString(productParent.findElement(cartItemSubtotal).getText());
        LOGGER.info("extractItemSubtotal completed");
        return itemSubtotal;
    }

    /**
     * Verify item subtotal on cart page with calculated subtotal for item code
     *
     * @param itemCode product code
     */
    public void assertItemSubtotal(String itemCode) {
        LOGGER.info("assertItemSubtotal started");
        double calculatedItemTotal = calculateItemSubtotal(itemCode);
        double extractedItemTotal = extractItemSubtotal(itemCode);
        Assert.assertTrue("FAIL: Item Subtotal Price didn't match! (displayed:-> "
                + extractedItemTotal + " expected:->  " + calculatedItemTotal + ")", extractedItemTotal == calculatedItemTotal);
        LOGGER.info("assertItemSubtotal completed");
    }

    /**
     * Verify the actual fee with calculated fee amount for wheels
     *
     * @param extractedFee The double fee to compare with the calculated fee
     * @param quantity     Web element quantity
     * @param text         fee type for wheel
     */
    public void assertFeePriceWheel(double extractedFee, WebElement quantity, String text) {
        LOGGER.info("assertFeePriceWheel started for " + text);
        int itemQuantity = 0;
        double fee = 0.00;
        double calculatedFee;
        List<WebElement> items = driver.getParentElement(quantity).findElements(orderSummaryRows);
        for (WebElement item : items) {
            if (item.findElement(feeDetailsItemsRowLabelBy).getText().contains(text)) {
                itemQuantity = Integer.parseInt(item.findElement(itemRowQuantityBy).getText());
                break;
            }
        }
        if (text.equalsIgnoreCase(ConstantsDtc.WHEEL_INSTALL_KIT)) {
            fee = ConstantsDtc.WHEEL_INSTALL_KIT_FEE;
        } else if (text.equalsIgnoreCase(ConstantsDtc.HUB_CENTRIC_RING)) {
            fee = ConstantsDtc.HUB_CENTRIC_RING_FEE;
        }
        calculatedFee = (fee * itemQuantity);
        Assert.assertTrue("FAIL: Fee Price for" + text + "didn't match! (displayed:-> "
                + extractedFee + " expected:->  " + calculatedFee + ")", extractedFee == calculatedFee);
        LOGGER.info("assertFeePriceWheel completed for " + text);
    }

    /**
     * Calculate the subtotal on cart page for wheels and tires by adding the item subtotal
     */
    public double calculateCartSubtotalForWheelsAndTires() {
        LOGGER.info("calculateCartSubtotalForWheelsAndTires started");
        double CartSubtotal;
        double finalSubtotal = 0.00;
        Set<String> items = CommonActionsSteps.cartProductPrice.keySet();
        for (String item : items) {
            CartSubtotal = calculateItemSubtotal(item);
            finalSubtotal = finalSubtotal + CartSubtotal;
        }
        LOGGER.info("calculateCartSubtotalForWheelsAndTires completed");
        return finalSubtotal;
    }

    /**
     * Verify the subtotal on cart page with calculated subtotal for Wheels and Tires
     */
    public void assertCartSubtotalForWheelsAndTires() {
        LOGGER.info("assertCartSubtotalForWheelsAndTires started");
        driver.waitForPageToLoad();
        double expectedSubtotal = calculateCartSubtotalForWheelsAndTires();
        double actualSubTotal = extractCartSubtotal();
        Assert.assertTrue("FAIL: The actual  subtotal for Wheels and Tires on cart page: \"" + actualSubTotal
                + "\" did not contain the expected total: \""
                + expectedSubtotal + "\"!", actualSubTotal == expectedSubtotal);
        LOGGER.info("assertCartSubtotalForWheelsAndTires completed");
    }

    /**
     * Calculate tax for wheels and Tires on Cart Page
     */
    public double calculateTaxForWheelsAndTires() {
        LOGGER.info("calculateTaxForWheelsAndTires started");
        double taxAmount;
        double totalTaxAmount = 0.00;
        Set<String> items = CommonActionsSteps.cartProductPrice.keySet();
        for (String item : items) {
            String stateOnCart = (cartPageStoreState.getText().split(",")[1].split("\\s+")[1]).trim();
            String customerType = "default_customer_".concat(stateOnCart.toLowerCase());
            CheckoutPage.setRegionalTaxesFactor(customer.getCustomer(customerType));

            String productElement = driver.getParentElement(getParent(item)).findElement(cartItemProductSize).getText();
            taxAmount = commonActions.getCalculatedSalesPriceForDTCRegion(String.valueOf(extractItemsPriceTotal(item)));

            if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) && productElement.contains(ConstantsDtc.WHEEL_ELEMENT)
                    || Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT) && productElement.contains(ConstantsDtc.WHEEL_ELEMENT)) {
                if (driver.isElementDisplayed(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.HUB_CENTRIC_RING))) {
                    String hubCentricRingFee = String.valueOf(extractFeesForItemsOnCart(ConstantsDtc.HUB_CENTRIC_RING, item));
                    taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(hubCentricRingFee);
                }
                if (driver.isElementDisplayed(driver.getElementWithText(feeDetailsItemsRowLabelBy, ConstantsDtc.WHEEL_INSTALL_KIT))) {
                    String wheelInstallKit = String.valueOf((extractFeesForItemsOnCart(ConstantsDtc.WHEEL_INSTALL_KIT, item)));
                    taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(wheelInstallKit);
                }
            } else {
                String envFee = String.valueOf(extractFeesForItemsOnCart(ConstantsDtc.ENVIRONMENTAL_FEE, item));
                taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(envFee);
                if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) || Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
                    String dispFee = String.valueOf(extractFeesForItemsOnCart(ConstantsDtc.DISPOSAL_FEE, item));
                    taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(dispFee);
                } else {
                    String optionalFee = String.valueOf(extractPriceForOptionalFee(item, ConstantsDtc.VALVE_STEM));
                    taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(optionalFee);
                }
                String certFee = String.valueOf(extractPriceForOptionalFee(item, ConstantsDtc.CERTIFICATE));
                if (!certFee.isEmpty()) {
                    taxAmount = taxAmount + commonActions.getCalculatedSalesPriceForDTCRegion(certFee);
                }
                if (driver.isElementDisplayed(cartSummaryInstantSavingBy)) {
                    String promotion = driver.getParentElement(getParent(item)).findElement(cartSummaryInstantSavingBy).getText();
                    taxAmount = taxAmount - commonActions.getCalculatedSalesPriceForDTCRegion(promotion);
                }
            }
            //TODO - Tax calculation needs to be further extended for FET, TPMS, etc Fees and Certificate needs to be tested
            totalTaxAmount = totalTaxAmount + taxAmount;
        }
        LOGGER.info("calculateTaxForWheelsAndTires completed");
        return commonActions.twoDForm(totalTaxAmount, 2);
    }

    /**
     * Verify calculated tax matches with the extracted tax for Tires and Wheels on cart page
     */
    public void assertTaxForWheelsAndTires() {
        LOGGER.info("assertTaxForWheelsAndTires started");
        double estimatedTaxAmount;
        estimatedTaxAmount = calculateTaxForWheelsAndTires();
        double actualTaxAmount = extractTaxOnCart();
        Assert.assertTrue("FAIL: The actual  tax amount: {" + actualTaxAmount
                + ") did not match to the expected tax amount: ("
                + estimatedTaxAmount + ") for wheels and tires displayed on cart page)", estimatedTaxAmount == actualTaxAmount);
        LOGGER.info("assertTaxForWheelsAndTires completed");
    }

    /**
     * Verify the optional fee is displayed
     *
     * @param itemCode The product code
     * @param text     The string to lookup the specific optional fee
     */
    public void assertOptionalFeeDisplay(String itemCode, String text) {
        LOGGER.info("assertOptionalFeeDisplay started for " + text);
        driver.waitForElementVisible(orderFeeServiceItemLabelBy);
        if (itemCode.contains(Constants.NONE)) {
            Assert.assertTrue("FAIL: Optional fee not displayed for "
                    + text, driver.isElementDisplayed(driver.getElementWithText(optionName, text)));
        } else {
            WebElement itemGrandParent = driver.getParentElement(getParent(itemCode));
            List<WebElement> feeForSelectionElements = itemGrandParent.findElements(optionName);
            driver.waitForElementVisible(optionName);
            for (WebElement element : feeForSelectionElements) {
                if (element.getText().contains(text)) {
                    Assert.assertTrue("FAIL: Optional fee not displayed for "
                            + text, element.getText().contains(text));
                    break;
                }
            }
        }
        LOGGER.info("assertOptionalFeeDisplay completed for " + text);
    }

    /**
     * Verify mini cart quick total before product added
     */
    public void assertMiniCartQuickTotalBeforeAddingProduct() {
        LOGGER.info("assertMiniCartQuickTotalBeforeAddingProduct started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(miniCartPrice);
        double miniCartQuickTotal = commonActions.cleanMonetaryString(miniCartPrice.getText());
        Assert.assertTrue("FAIL: Minicart price displayed: (" + miniCartQuickTotal + ") not matching to "
                        + Constants.ZERO + "before adding product)",
                miniCartQuickTotal == Constants.ZERO);
        LOGGER.info("assertMiniCartQuickTotalBeforeAddingProduct completed");
    }

    /**
     * Verify mini cart quick total after product added
     */
    public void assertMiniCartQuickTotalAfterAddingProduct() {
        LOGGER.info("assertMiniCartQuickTotalAfterAddingProduct started");
        driver.waitForElementVisible(miniCartPrice);
        double miniCartQuickTotal = commonActions.cleanMonetaryString(miniCartPrice.getText());
        Assert.assertTrue("FAIL: Mini Cart price changed to "
                        + miniCartQuickTotal + ") since product added, expected : " + CommonActionsSteps.miniCartQuickTotal,
                miniCartQuickTotal == CommonActionsSteps.miniCartQuickTotal);
        LOGGER.info("assertMiniCartQuickTotalAfterAddingProduct completed");
    }

    /**
     * Verify the View Cart option is displayed in Mini Cart
     */
    public void assertMiniCartDisplayViewCart() {
        LOGGER.info("assertMiniCartDisplayViewCart started");
        WebElement miniCartViewCart = driver.getElementWithText(viewCartButtonBy, VIEW_CART);
        Assert.assertTrue("FAIL: View Cart button not displayed in Mini Cart!",
                driver.isElementDisplayed(miniCartViewCart));
        LOGGER.info("assertMiniCartDisplayViewCart completed");
    }

    /**
     * Verify the Continue Shopping option is displayed in Mini Cart
     */
    public void assertMiniCartDisplayContinueShopping() {
        LOGGER.info("assertMiniCartDisplayContinueShopping started");
        WebElement miniCartContinueShopping = driver.getElementWithText(continueShoppingButtonBy, CONTINUE_SHOPPING);
        Assert.assertTrue("FAIL: Continue Shopping button not displayed in Mini Cart!",
                driver.isElementDisplayed(miniCartContinueShopping));
        LOGGER.info("assertMiniCartDisplayContinueShopping completed");
    }

    /**
     * Verify added product is displayed in Mini Cart
     *
     * @param productName added product
     */
    public void assertAddedProductInMiniCart(String productName) {
        LOGGER.info("assertAddedProductInMiniCart started");
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        Assert.assertTrue("FAIL: Product \"" + productName + "\" NOT present on Mini Cart!",
                webDriver.findElement(miniCartItemNameBy).getText().toLowerCase().contains(productName.toLowerCase()));
        LOGGER.info("assertAddedProductInMiniCart completed");
    }

    /**
     * Click View Cart on Mini Cart
     */
    public void clickViewCartMiniCart() {
        LOGGER.info("clickViewCartMiniCart started");
        WebElement miniCartViewCart = driver.getElementWithText(viewCartButtonBy, VIEW_CART);
        driver.waitForElementVisible(miniCartViewCart);
        driver.jsScrollToElementClick(miniCartViewCart);
        driver.waitForPageToLoad();
        LOGGER.info("clickViewCartMiniCart completed");
    }

    /**
     * Calculate Mini Cart fee price total by multiplying base price with quantity
     *
     * @param text     fee type
     * @param itemCode product code
     */
    public double calculateMiniCartPrice(String text, String itemCode) {
        LOGGER.info("calculateMiniCartPrice started for " + text);
        double expectedBasePrice = 0.00;
        double itemQuantity = 0.00;
        if (text.equalsIgnoreCase(ConstantsDtc.INSTALLATION)) {
            expectedBasePrice = ConstantsDtc.INSTALLATION_FEE_AZ;
            itemQuantity = commonActions.cleanMonetaryString(driver.getParentElement(driver.getElementWithText(miniCartItemNameBy, ConstantsDtc.INSTALLATION))
                    .findElement(miniCartItemQuantityBy).getText());
        } else if (text.equalsIgnoreCase(ConstantsDtc.ENVIRONMENTAL_FEE)) {
            expectedBasePrice = getCalculatedEnvironmentFee(itemCode);
            itemQuantity = commonActions.cleanMonetaryString(driver.getParentElement(driver.getElementWithText(miniCartItemNameBy, ConstantsDtc.ENVIRONMENTAL_FEE))
                    .findElement(miniCartItemQuantityBy).getText());
        } else if (text.equalsIgnoreCase(ConstantsDtc.DISPOSAL_FEE)) {
            expectedBasePrice = getCalculatedDisposalFee(itemCode);
            itemQuantity = commonActions.cleanMonetaryString(driver.getParentElement(driver.getElementWithText(miniCartItemNameBy, ConstantsDtc.DISPOSAL_FEE))
                    .findElement(miniCartItemQuantityBy).getText());
        } else if (webDriver.findElement(miniCartItemNameBy).getText().toLowerCase().contains(text.toLowerCase())) {
            expectedBasePrice = Double.parseDouble(driver.getParentElement(getParent(itemCode)).
                    findElement(itemPriceEachBy).getText().split("\\$")[1]);
            itemQuantity = Double.parseDouble(driver.getParentElement(getParent(itemCode)).
                    findElement(cartItemQuantityBy).getAttribute(Constants.VALUE));
        }
        double calculatedTotalPrice = (expectedBasePrice * itemQuantity);
        LOGGER.info("calculateMiniCartPrice completed for " + text);
        return calculatedTotalPrice;
    }

    /**
     * Extract Mini Cart fee price total
     *
     * @param text fee type
     */
    public double extractMiniCartPrice(String text) {
        LOGGER.info("extractMiniCartPrice started for " + text);
        double extractedPriceTotal = 0.00;
        if (text.equalsIgnoreCase(ConstantsDtc.INSTALLATION)) {
            extractedPriceTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, ConstantsDtc.INSTALLATION)).findElement(miniCartItemTotalBy).getText());
        } else if (text.equalsIgnoreCase(ConstantsDtc.ENVIRONMENTAL_FEE)) {
            extractedPriceTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, ConstantsDtc.ENVIRONMENTAL_FEE)).findElement(miniCartItemTotalBy).getText());
        } else if (text.equalsIgnoreCase(ConstantsDtc.DISPOSAL_FEE)) {
            extractedPriceTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, ConstantsDtc.DISPOSAL_FEE)).findElement(miniCartItemTotalBy).getText());
        } else if (webDriver.findElement(miniCartItemNameBy).getText().toLowerCase().contains(text.toLowerCase())) {
            extractedPriceTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, text)).findElement(miniCartItemTotalBy).getText());
        }
        LOGGER.info("extractMiniCartPrice completed for " + text);
        return extractedPriceTotal;
    }

    /**
     * Verify Mini Cart fees total with calculated total
     *
     * @param text     fee type
     * @param itemCode product code
     */
    public void assertMiniCartPrice(String text, String itemCode) {
        LOGGER.info("assertMiniCartPrice started for " + text);
        double expectedTotal = calculateMiniCartPrice(text, itemCode);
        double extractedTotal = extractMiniCartPrice(text);
        Assert.assertTrue("FAIL: Mini Cart price total for (" + text + ") didn't match! (displayed:-> "
                + extractedTotal + " expected:->  " + expectedTotal + ")", extractedTotal == expectedTotal);
        // Verify the product base price in mini cart
        if (webDriver.findElement(miniCartItemNameBy).getText().toLowerCase().contains(text.toLowerCase())) {
            double miniCartItemBasePrice = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, text)).findElement(miniCartItemPriceBy).getText());
            double CartItemBasePrice = Double.parseDouble(driver.getParentElement(getParent(itemCode)).
                    findElement(itemPriceEachBy).getText().split("\\$")[1]);
            Assert.assertTrue("FAIL: Mini Cart item base price for (" + text + ") didn't match! (displayed:-> "
                            + miniCartItemBasePrice + " expected:->  " + CartItemBasePrice + ")",
                    miniCartItemBasePrice == CartItemBasePrice);
        }
        LOGGER.info("assertMiniCartPrice completed for " + text);
    }

    /**
     * Calculate Mini Cart price total by adding product price and fees
     *
     * @param text product
     */
    public double calculateMiniCartTotal(String text) {
        LOGGER.info("calculateMiniCartTotal started");
        double miniCartTotal = 0.00;
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            double itemTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, text)).findElement(miniCartItemTotalBy).getText());
            double installFee = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, ConstantsDtc.INSTALLATION)).findElement(miniCartItemTotalBy).getText());
            double disposalTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, ConstantsDtc.DISPOSAL_FEE)).findElement(miniCartItemTotalBy).getText());
            double environmentalFee = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, ConstantsDtc.ENVIRONMENTAL_FEE)).findElement(miniCartItemTotalBy).getText());
            miniCartTotal = itemTotal + installFee + disposalTotal + environmentalFee;
        } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            double itemTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, text)).findElement(miniCartItemTotalBy).getText());
            double environmentalFee = commonActions.cleanMonetaryString(driver.getParentElement(
                    driver.getElementWithText(miniCartItemInfoBy, ConstantsDtc.ENVIRONMENTAL_FEE)).findElement(miniCartItemTotalBy).getText());
            miniCartTotal = itemTotal + environmentalFee;
        }
        LOGGER.info("calculateMiniCartTotal completed");
        return miniCartTotal;
    }

    /**
     * Verify Mini Cart price total with calculated price total
     *
     * @param text product name
     */
    public void assertMiniCartTotal(String text) {
        LOGGER.info("assertMiniCartTotal started");
        double expectedTotal = 0.00;
        if (driver.isTextPresentInPageSource(ConstantsDtc.MAP)) {
            expectedTotal = extractCartSubtotal();
        } else {
            expectedTotal = calculateMiniCartTotal(text);
        }
        double extractedTotal = commonActions.cleanMonetaryString(webDriver.findElement(miniCartTotalBy).getText());
        Assert.assertTrue("FAIL: Mini Cart total didn't match! (displayed:-> "
                + extractedTotal + " expected:->  " + expectedTotal + ")", extractedTotal == expectedTotal);
        LOGGER.info("assertMiniCartTotal completed");
    }

    /**
     * Verify Mini Cart item total
     *
     * @param text     product name
     * @param itemCode product code
     */
    public void assertMiniCartItemTotal(String text, String itemCode) {
        LOGGER.info("assertMiniCartItemTotal started");
        double expectedBasePrice = commonActions.cleanMonetaryString(CommonActionsSteps.cartProductPrice.get(itemCode).split("[a-z]")[0].trim());
        double itemQuantity = commonActions.cleanMonetaryString(webDriver.findElement(miniCartItemQuantityBy).getText().split("[:]")[1].trim());
        double savings = 0.00;
        if (driver.isElementDisplayed(qualifyPromotionBy)) {
            savings = commonActions.cleanMonetaryString(webDriver.findElement(qualifyPromotionBy).getText());
        }
        double expectedTotal = (expectedBasePrice * itemQuantity) - savings;
        double extractedTotal = commonActions.cleanMonetaryString(driver.getParentElement(
                driver.getElementWithText(miniCartItemInfoBy, text)).findElement(miniCartItemTotalBy).getText());
        Assert.assertTrue("FAIL: Mini Cart total didn't match! (displayed:-> "
                + extractedTotal + " expected:->  " + expectedTotal + ")", extractedTotal == expectedTotal);
        LOGGER.info("assertMiniCartItemTotal completed");
    }

    /**
     * Verify the 'Checkout Now' button is enabled on the Cart page
     */
    public void assertCheckoutNowEnabled() {
        LOGGER.info("assertCheckoutNowEnabled started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(checkoutButton);
        Assert.assertTrue("FAIL: Checkout Now was not enabled on shopping cart page!", checkoutButton.isEnabled());
        LOGGER.info("assertCheckoutNowEnabled completed");
    }

    /**
     * Verify the popup message display (Switching to another store will clear your cart) when Switch Store on Cart Page
     */
    public void assertSwitchStoreOnCartPopupMessage() {
        LOGGER.info("assertSwitchStoreOnCartPopupMessage started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(modalSwitchCartBy);
        Assert.assertTrue("FAIL: Switching store on Cart Page, popup not displaying message! : " + ConstantsDtc.SWITCH_STORE_MESSAGE_ON_CART
                , webDriver.findElement(modalSwitchCartBy).getText().contains(ConstantsDtc.SWITCH_STORE_MESSAGE_ON_CART));
        LOGGER.info("assertSwitchStoreOnCartPopupMessage completed");
    }

    /**
     * Verify the Switch Store On cart page display options (Clear my cart and Continue | View cart)
     */
    public void assertSwitchStoreOptions() {
        LOGGER.info("assertSwitchStoreOptions started");
        String failure = "";
        List<String> optionValues = new ArrayList<String>();

        driver.waitForElementVisible(switchOptions);
        List<WebElement> options = webDriver.findElements(switchOptions);

        for (WebElement option : options) {
            optionValues.add(option.getText());
        }

        if (!optionValues.toString().contains(CLEAR_CART))
            failure = "\"" + CLEAR_CART + "\" option not present. ";

        if (!optionValues.toString().contains(VIEW_CART))
            failure = failure + "\"" + VIEW_CART + "\" option not present.";

        if (failure.length() > 0)
            Assert.fail("FAIL: " + failure);

        LOGGER.info("assertSwitchStoreOptions completed");
    }

    /**
     * Verify the popup message display (Switching to another vehicle will clear your cart) when Switch vehicle on Cart Page
     */
    public void assertSwitchVehicleOnCartPopupMessage() {
        LOGGER.info("assertSwitchVehicleOnCartPopupMessage started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(modalSwitchCartBy);
        Assert.assertTrue("FAIL: Switching vehicle on Cart Page, popup not displaying message! : " + ConstantsDtc.SWITCH_VEHICLE_POPUP_MESSAGE,
                webDriver.findElement(modalSwitchCartBy).getText().contains(ConstantsDtc.SWITCH_VEHICLE_POPUP_MESSAGE));
        LOGGER.info("assertSwitchVehicleOnCartPopupMessage completed");
    }

    /**
     * Verify applicable fee display on Cart page with vehicle
     */
    public void assertFeeDisplayWithVehicle(String itemCode) {
        LOGGER.info("assertFeeDisplayWithVehicle started for " + itemCode);
        if (!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            if (getParent(itemCode).findElement(cartItemProductImageBy).getAttribute(ConstantsDtc.ATTR_STYLE).
                    contains(Constants.WHEEL.toLowerCase())) {
                assertFeeLabelDisplayed(ConstantsDtc.WHEEL_INSTALL_KIT, itemCode);
                assertFeeLabelDisplayed(ConstantsDtc.HUB_CENTRIC_RING, itemCode);
            } else {
                assertFeeLabelDisplayed(ConstantsDtc.INSTALLATION, itemCode);
                assertFeeLabelDisplayed(ConstantsDtc.ENVIRONMENTAL_FEE, itemCode);
                assertFeeLabelDisplayed(ConstantsDtc.DISPOSAL_FEE, itemCode);
            }
        } else {
            if (getParent(itemCode).findElement(cartItemProductImageBy).getAttribute(ConstantsDtc.ATTR_STYLE).
                    contains(Constants.WHEEL.toLowerCase())) {
                assertFeeLabelDisplayed(ConstantsDtc.WHEEL_INSTALL_KIT, itemCode);
            } else {
                assertFeeLabelDisplayed(ConstantsDtc.ENVIRONMENTAL_FEE, itemCode);
            }
        }
        LOGGER.info("assertFeeDisplayWithVehicle completed for " + itemCode);
    }

    /**
     * Verify quantity for the product on cart page
     *
     * @param quantity Quantity to update
     * @param item     product code
     */
    public void assertQuantityForItem(String item, String quantity) {
        LOGGER.info("assertQuantityForItem started for " + item);
        driver.waitForPageToLoad();
        String itemQuantity = driver.getParentElement(getParent(item)).
                findElement(cartItemQuantityBy).getAttribute(Constants.VALUE);
        Assert.assertTrue("FAIL: Item quantity didn't match (displayed:-> "
                + itemQuantity + " expected:->  " + quantity + ")", itemQuantity.equals(quantity));
        LOGGER.info("assertQuantityForItem completed for " + item);
    }

    /**
     * Verify the Order Summary Instant Savings box on the cart page display applied instant discounts
     *
     * @param page Web Page Header
     */
    public void assertInstantSavingOrderSummary(String page) {
        LOGGER.info("assertInstantSavingOrderSummary started for " + page);
        driver.waitForPageToLoad();
        double expectedDiscount = 0.00;
        double instantDiscount;
        List<WebElement> instantSavings = webDriver.findElements(qualifyPromotionBy);
        for (WebElement instantSaving : instantSavings) {
            instantDiscount = Double.parseDouble(instantSaving.getText().split("\\$")[1].trim());
            expectedDiscount = expectedDiscount + instantDiscount;
        }
        double appliedInstantSaving = Double.parseDouble(webDriver.findElement(cartSummaryInstantSavingBy).getText().split("\\$")[1].trim());
        Assert.assertTrue("FAIL: 'Instant online Saving applied to Cart' is not accurate ! expected : "
                        + expectedDiscount + "actual : " + appliedInstantSaving,
                appliedInstantSaving == expectedDiscount);
        LOGGER.info("assertInstantSavingOrderSummary completed for " + page);
    }

    /**
     * Verify Instant Savings
     *
     * @param item product code
     * @param page Web Page Header
     */
    public void assertInstantSavingsDisplayedForItem(String page, String item) {
        LOGGER.info("assertInstantSavingsDisplayedForItem started for " + page);
        driver.waitForPageToLoad();
        int itemQuantityByIndex = 0;
        if (Config.isMobile()) {
            itemQuantityByIndex = 1;
        }
        List<WebElement> promotions = driver.getParentElement(getParent(item)).findElements(cartItemRebateNameBy);
        if (Config.isMobile()) {
            Assert.assertTrue("FAIL: Instant Savings not Displayed ! ",
                    !(driver.getElementsWithText(cartItemRebateNameBy, ConstantsDtc.INSTANT_SAVINGS).size() == 0));
        } else {
            Assert.assertTrue("FAIL: Instant Savings not Displayed ! ",
                    !(driver.getElementsWithText(cartItemRebateNameBy, ConstantsDtc.INSTANT_SAVINGS,
                            Constants.VALID).size() == 0));
        }
        for (WebElement promotion : promotions) {
            driver.jsScrollToElement(promotion);
            if (promotion.getText().contains(ConstantsDtc.INSTANT_SAVINGS)) {
                int quantity;
                double expectedPromotion = 0.00;
                double displayedPromotion = Double.parseDouble(
                        driver.getParentElement(promotion).findElement(qualifyPromotionBy).getText().split("\\$")[1].trim());
                if (promotion.getText().contains(ConstantsDtc.PERCENTAGE)) {
                    double fixedPercentage = Double.parseDouble(promotion.getText().split("\\%")[0].trim());
                    double price = commonActions.cleanMonetaryString(driver.getParentElement(getParent(item)).
                            findElement(itemPriceBy).getText());
                    expectedPromotion = (price * fixedPercentage) / 100;
                } else {
                    if (page.contains(ConstantsDtc.ORDER_CONFIRMATION)) {
                        quantity = Integer.parseInt(driver.getParentElement(getParent(item)).findElements(cartItemQuantityBy).get(itemQuantityByIndex).
                                getText());
                    } else {
                        quantity = Integer.parseInt(driver.getParentElement(getParent(item)).findElements(cartItemQuantityBy).get(itemQuantityByIndex).
                                getAttribute(Constants.VALUE));
                    }
                    double fixedPromotion = Double.parseDouble(promotion.getText().split("\\$")[1].split(" ")[0].trim());
                    expectedPromotion = (fixedPromotion / ConstantsDtc.DEFAULT_QUANTITY) * quantity;
                }
                Assert.assertTrue("FAIL: Instant Savings: " + promotion.getText()
                                + "for item " + item + " not calculated correctly. Displayed : " + displayedPromotion
                                + ". Expected Promotion : " + expectedPromotion,
                        expectedPromotion == displayedPromotion);
            }
        }
        LOGGER.info("assertInstantSavingsDisplayedForItem completed for " + page);
    }

    /**
     * Verify Mail In Rebate displayed
     *
     * @param mailInRebate text on Mail-In-Rebate
     * @param page         Web Page Header
     * @param item         Product code
     */
    public void assertMailInRebateDisplayed(String mailInRebate, String page, String item) {
        LOGGER.info("assertMailInRebateDisplayed started for " + page);
        List<WebElement> promotions = driver.getParentElement(getParent(item)).findElements(cartItemRebateNameBy);
        for (WebElement promotion : promotions) {
            if (promotion.getText().contains(ConstantsDtc.MAIL_IN_REBATE)) {
                Assert.assertTrue("FAIL: Expected Mail-In-Rebate: " + mailInRebate + " not Displayed!, Actual: "
                        + promotion.getText(), promotion.getText().contains(mailInRebate));
                if (!page.equalsIgnoreCase(ConstantsDtc.CHECKOUT)) {
                    Assert.assertTrue("FAIL: Mail In Rebate not displayed with 'view details' Link ! ",
                            driver.isElementDisplayed(driver.getParentElement(promotion).findElement(CommonActions.dtLinkBy)));
                }
            }
        }
        if (Config.isMobile()) {
            Assert.assertTrue("FAIL: Mail-in rebates not Displayed ! ",
                    !(driver.getElementsWithText(cartItemRebateNameBy, ConstantsDtc.MAIL_IN_REBATE).size() == 0));
        } else {
            Assert.assertTrue("FAIL: Mail-in rebates not Displayed ! ",
                    !(driver.getElementsWithText(cartItemRebateNameBy, ConstantsDtc.MAIL_IN_REBATE, Constants.VALID).size() == 0));
        }
        LOGGER.info("assertMailInRebateDisplayed completed for " + page);
    }

    /**
     * Extract Instant Savings on Cart page
     *
     * @return applied promotions on cart
     */
    public ArrayList<String> getInstantSavingsOnCart() {
        LOGGER.info("getInstantSavingsOnCart started");
        List<WebElement> promotionElements = driver.getElementsWithText(cartItemRowBy, ConstantsDtc.INSTANT_SAVINGS);
        for (WebElement promotion : promotionElements) {
            instantPromotionsOnCart.add(promotion.getText().split("-")[0].trim().concat("   ").
                    concat(promotion.getText().split("\\-\\$")[1].trim()));
        }
        LOGGER.info("getInstantSavingsOnCart completed");
        return instantPromotionsOnCart;
    }

    /**
     * Verify provided vehicle name is present on cart page
     *
     * @param vehicle Vehicle title
     */
    public void assertVehicleOnCartPage(String vehicle) {
        LOGGER.info("assertVehicleOnCartPage started");
        driver.waitForPageToLoad();
        WebElement cartFit = webDriver.findElement(cartItemFitBy);
        Assert.assertEquals("FAILED: vehicle fit not displayed on shopping Cart Page!",
                vehicle.toLowerCase(), cartFit.getText().toLowerCase());
        LOGGER.info("assertVehicleOnCartPage completed");
    }

    /**
     * Extract Mail In Rebate on Cart page
     *
     * @return applied promotions on cart
     */
    public ArrayList<String> getMailInRebateOnCart() {
        LOGGER.info("getMailInRebateOnCart started");
        List<WebElement> promotionElements = driver.getElementsWithText(cartItemRowBy, ConstantsDtc.MAIL_IN_REBATE);
        for (WebElement promotion : promotionElements) {
            mailInPromotionsOnCart.add(promotion.getText().split("-")[0].trim());
        }
        LOGGER.info("getMailInRebateOnCart completed");
        return mailInPromotionsOnCart;
    }
}