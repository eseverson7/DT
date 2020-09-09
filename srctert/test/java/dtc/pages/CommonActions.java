package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.Set;

/**
 * Created by aaronbriel on 10/5/16.
 */
public class CommonActions {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public CommonActions(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static final String TIRES_FOR_YOUR = "Tires for your:";
    private static final String WHEELS_FOR_YOUR = "Wheels for your:";
    private static final String ITEMS_FIT_YOUR = "These items fit your:";
    private static final String ORDER_PAGE = "order";
    private static final String GREEN_BANNER = "rgba(82, 162, 64, 1)";
    private static final String GREEN_BANNER_ALT_RGBA = "rgba(70, 186, 43, 1)";
    private static final String YELLOW_BANNER = "rgba(255, 161, 13, 1)";
    private static final String YELLOW_BANNER_ALT_RGB = "rgb(255, 161, 13)";
    private static final String GREEN_BANNER_RGB = "rgb(82, 162, 64)";
    private static final String GREEN_BANNER_ALT_RGB = "rgb(70, 186, 43)";
    private static final String RED_BANNER_ALT_RGB = "rgb(237, 28, 36)";
    private static final String RED_BANNER = "rgba(237, 28, 36, 1)";
    public static final String STORE_LOCATOR_HEADER = "Store Locator";
    public static final String STORE_LOCATOR_URL = "/store-locator";
    public static final String ZERO_DOLLARS = "$0";
    // Initializing product quantity to blank so it can be populated during a test run only if needed
    public static String productQuantity = "";

    private static final String welcomePopupClosed =
            "return document.getElementsByClassName('store-pop').length === 0;";

    private static final String chatNowPopupClosed =
            "return document.getElementsByClassName('LPMcontainer').length === 0;";

    //TODO: Add these as candidates for autoclass ID's
    public static final By chosenSingleBy = By.cssSelector("#sortForm1 .chosen-single");

    public static final By chosenSingleDropBy = By.cssSelector("#sortForm1 .chosen-drop");

    public static final By chosenSingleMobileBy = By.cssSelector("#sortOptions2");

    private static final By breadCrumbLinksBy = By.cssSelector(".breadcrumb li");

    private static final By noThanksBtnBy = By.className("LPMcloseButton");

    private static final By nextBtnBy = By.className("pagination__link--next");

    private static final By previousBtnBy = By.className("pagination__link--prev");

    private static final By plpResultMessageBy = By.className("result_message");

    private static final By plpNotSureResultMessageBy = By.className("message__not-sure");

    private static final By pdpResultMessageBy = By.className("message__bar");

    public static final By btnDefaultBy = By.className("btn-default");

    public static final By dtButtonBy = By.className("dt-button");

    public static final By closeButtonXBy = By.className("fa-times");

    public static final By dropdownBy =
            By.className("react-selectize-search-field-and-selected-values");

    public static final By dropdownOptionBy = By.className("simple-option");

    private static final By submitButtonBy = By.className("dt-form__submit");

    private static final By viewShoppingCartBy = By.className("View shopping cart");

    private static final By closeButtonBy = By.className("icon-close");

    private static final By miniCartBy = By.className("fa-shopping-cart");

    private static final By firstBtnBy = By.xpath("//i[@class='fa fa-angle-double-left']//parent::a");

    private static final By lastBtnBy = By.xpath("//i[@class='fa fa-angle-double-right']//parent::a");

    public static final By overallRatingBy = By.xpath("//span[@class='overall-rating']");

    private static final By storeReviewScoreBy = By.cssSelector(".review-score>span:not(.review-score__scale)");

    public static final By storeReadReviewsBy = By.className("review-rating__read");

    //region TagName By objects
    public static final By headerBy = By.tagName("h1");

    public static final By ptagNameBy = By.tagName("p");

    public static final By spanTagNameBy = By.tagName("span");

    public static final By headerSecondBy = By.tagName("h2");

    public static final By headerFourthBy = By.tagName("h4");

    public static final By headerThirdBy = By.tagName("h3");

    public static final By headerFifthBy = By.tagName("h5");

    public static final By buttonBy = By.tagName("button");

    public static final By optionTagBy = By.tagName("option");

    public static final By anchorTagBy = By.tagName("a");

    public static final By imgTagBy = By.tagName("img");
    //endregion

    public static final By modalClose = By.className("dt-modal__close");

    public static final By dtLinkBy = By.className("dt-link");

    public static final By radioButtonBy = By.xpath("//span[contains(@class,'dt-radio__display')]");

    public static final By radioLabelBy = By.className("form-group__radio-label");

    public static final By collapsibleToggleBy = By.className("collapsible-section__toggle");

    public static By formGroupBy = By.className("form-group");

    public static By inputBy = By.cssSelector("input");

    public static final By inventoryMessageBy = By.className("inventory-message");

    public static final By inventoryMessageMyStoreBy = By.className("inventory-message--my-store");

    public static final By itemIdBy = By.className("results-row__itemid");

    public static final By checkInventoryBy = By.cssSelector(".checkinventory-link.results-row__link");

    public static final By pdpInfoContainerBy = By.className("pdp-info__container");

    @FindBy(className = "collapsible-section__content--open")
    public static WebElement openCollapsibleSection;

    @FindBy(className = "btn-default")
    public static WebElement btnDefault;

    @FindBy(className = "global-message-content")
    public static WebElement globalMessage;

    @FindBy(className = "pdp-cartbutton")
    public static WebElement addToCart;

    @FindBy(className = "results-row__cartbutton")
    public static WebElement addToCartPLP;

    @FindBy(linkText = "View shopping cart")
    public static WebElement viewShoppingCart;

    @FindBy(id = "cartContinueBut")
    public static WebElement continueShopping;

    @FindBy(id = "cartCloseBut")
    public static WebElement closeCart;

    @FindBy(id = "chooseStore")
    public static WebElement continueButton;

    @FindBy(className = "result_message")
    public static WebElement plpResultMessage;

    @FindBy(className = "message__bar")
    public static WebElement pdpResultMessage;

    @FindBy(className = "auto-header-my-store-address")
    public static WebElement cartMyStore;

    @FindBy(className = "my-account__sign-out")
    public static WebElement myProfileSignOut;

    @FindBy(xpath = "//td[text()='There are no products to compare']")
    public static WebElement noProductsToCompareMessage;

    @FindBy(css = ".product-availability__right button.product-availability__product-add-cart")
    public static WebElement addToCartProductAvailability;

    /**
     * Clicks button containing certain text
     *
     * @param text Value of the button to be clicked
     */
    public void clickButtonByText(String text) {
        By button = By.tagName("button");
        LOGGER.info("clickButtonByText started with text = \"" + text + "\"");
        driver.clickElementWithText(button, text);
        LOGGER.info("clickButtonByText completed with text = \"" + text + "\"");
    }

    /**
     * Selects option from bootstrap dropdown
     *
     * @param elementId The id of option list
     * @param value     The value to select
     */
    public void selectDropDownValue(String elementId, String value) {
        LOGGER.info("selectDropDownValue started");

        String js = "$('#" + elementId + "').trigger('mousedown');";
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        try {
            driver.waitForElementClickable(webDriver.findElement(By.id(elementId)));
            WebElement webEle = webDriver.findElement(By.id(elementId));
            driver.jsScrollToElement(webEle);
            jse.executeScript(js);

            driver.waitForMilliseconds();

            //If no underlying "li" tags are found, trying finding "option" tags
            List<WebElement> options = webEle.findElements(By.tagName("li"));
            if (options.size() == 0) {
                options = webEle.findElements(By.tagName("option"));
            }

            for (WebElement option : options) {
                if (option.getText().toLowerCase().contains(value.toLowerCase())) {
                    driver.jsScrollToElement(option);
                    option.click();
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Selecting value \"" + value + "\" from dropdown with ID \"" +
                    elementId + "\" FAILED with error: " + e);
        }
        LOGGER.info("selectDropDownValue completed");
    }

    /**
     * Asserts that taxes are listed on the page and contain/do not contain amount
     *
     * @param page     The page to verify
     * @param amount   The amount to check (Note that it expects $. For example $0.)
     * @param contains Check if element contains or does not contain amount
     */
    //TODO: change to assertEquals once we get unique auto classnames
    public void confirmTaxes(String page, String amount, boolean contains) {
        LOGGER.info("confirmTaxes started");
        driver.waitForPageToLoad();
        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        try {
            WebElement taxEl = getTaxElement(page);
            String taxRowString = taxEl.getText().trim();
            Assert.assertTrue("FAIL: \"" + ConstantsDtc.TAX + "\" was NOT displayed!",
                    taxRowString.contains(ConstantsDtc.TAX));
            LOGGER.info("Confirmed that " + ConstantsDtc.TAX + " was displayed.");
            Assert.assertTrue("FAIL: \"$\" was NOT displayed!", taxRowString.contains("$"));
            LOGGER.info("Confirmed that " + taxRowString + " contains $.");

            String itemsPrice = getItemsPriceElement(page).getText().trim();
            if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
                String salesTax = String.valueOf(Math.floor(getCalculatedSalesPriceForDTCRegion(itemsPrice) * 100) / 100);
                Assert.assertTrue("FAIL: The tax did NOT contain amount: \"" + salesTax + "\"!",
                        taxRowString.contains(salesTax));
            } else {
                if (contains) {
                    Assert.assertTrue("FAIL: The tax did NOT contain amount: \"" + amount + "\"!",
                            taxRowString.contains(amount));
                    LOGGER.info("Confirmed that tax contains amount " + amount + ".");
                } else {
                    Assert.assertTrue("FAIL: The tax DID contain amount: \"" + amount + "\"!",
                            !taxRowString.contains(amount));
                    LOGGER.info("Confirmed that tax did not contain amount " + amount + ".");
                }
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Confirming taxes FAILED with error: " + e);
        }
        LOGGER.info("confirmTaxes completed");
    }

    /**
     * Gets the tax element from the page
     *
     * @param page The page to verify
     * @return WebElement that contains either Summary Subtotal or Summary Row
     */
    private WebElement getTaxElement(String page) {
        LOGGER.info("getTaxElement started");
        if (page.equalsIgnoreCase(ORDER_PAGE)) {
            driver.waitForElementClickable(OrderPage.orderCartSummaryBy);
            LOGGER.info("getTaxElement completed");
            return driver.getElementWithText(OrderPage.orderCartSummaryBy, ConstantsDtc.TAX);
        } else {
            driver.waitForElementClickable(CheckoutPage.salesTaxRowBy);
            LOGGER.info("getTaxElement completed");
            return driver.getElementWithText(CheckoutPage.salesTaxRowBy, ConstantsDtc.TAX);
        }
    }

    /**
     * Gets the Cart Summary Items Price element from the page
     *
     * @param page The page to verify
     * @return WebElement that contains Cart Summary Items Price
     */
    private WebElement getItemsPriceElement(String page) {
        LOGGER.info("getTaxElement started");
        if (page.equalsIgnoreCase(ORDER_PAGE)) {
            driver.waitForElementClickable(OrderPage.orderPageCartSummaryItemsPrice);
            LOGGER.info("getItemsPriceElement completed");
            return OrderPage.orderPageCartSummaryItemsPrice;
        } else {
            driver.waitForElementClickable(CheckoutPage.checkoutPageCartSummaryItemsPrice);
            LOGGER.info("getItemsPriceElement completed");
            return CheckoutPage.checkoutPageCartSummaryItemsPrice;
        }
    }

    /**
     * Gets the calculated sales tax based 3 tax factor
     *
     * @param price cart summary items price
     * @return Double that contains calculated sales price
     */
    public double getCalculatedSalesPriceForDTCRegion(String price) {
        LOGGER.info("getCalculatedSalesPriceForDTCRegion started");
        double stateTax = Double.valueOf(CheckoutPage.stateTaxString);
        double cityTax = Double.valueOf(CheckoutPage.cityTaxString);
        double otherTax = Double.valueOf(CheckoutPage.otherTaxString);
        double calculatedSalesTax = 0.0;

        stateTax = twoDForm(stateTax * cleanMonetaryString(price), 2);
        cityTax = twoDForm(cityTax * cleanMonetaryString(price), 2);
        otherTax = twoDForm(otherTax * cleanMonetaryString(price), 2);
        calculatedSalesTax = twoDForm((stateTax + cityTax + otherTax), 2);

        LOGGER.info("getCalculatedSalesPriceForDTCRegion completed");
        return calculatedSalesTax;
    }

    /**
     * Asserts that fees are listed on the page and contain/do not contain amount
     *
     * @param page     The page to pull the fee from
     * @param amount   The amount to check (Note that it expects $. For example $0.)
     * @param contains Check if element contains or does not contain amount
     */
    //TODO: change to assertEquals once we get unique auto classnames
    public void confirmFees(String page, String amount, boolean contains) {
        LOGGER.info("confirmFees started");
        try {
            WebElement feeEl = getFeeElement(page);
            String expectedText;
            String feeRowString = feeEl.getText().trim();

            if (page.equalsIgnoreCase(ORDER_PAGE)) {
                expectedText = ConstantsDtc.ENVIRONMENTAL_FEE;
            } else {
                expectedText = ConstantsDtc.ABBREV_ENVIRONMENTAL_FEE;
            }

            Assert.assertTrue("FAIL: \"" + expectedText + "\" was NOT displayed!",
                    feeRowString.contains(expectedText));
            LOGGER.info("Confirmed that " + expectedText + " was displayed.");
            Assert.assertTrue("FAIL: Fee did NOT contain symbol: \"$\"!", feeRowString.contains("$"));
            LOGGER.info("Confirmed that " + feeRowString + " contains $.");
            if (contains) {
                Assert.assertTrue("FAIL: Actual fee: \"" + feeRowString
                                + "\" did NOT contain or match the expected: \"" + amount + "\"!",
                        feeRowString.contains(amount));
                LOGGER.info("Confirmed that fee contains amount " + amount + ".");
            } else {
                Assert.assertTrue("FAIL: Actual fee: \"" + feeRowString
                        + "\" DID contain or match the expected: \"" + amount + "\"!", !feeRowString.contains(amount));
                LOGGER.info("Confirmed that fee did not contain amount " + amount + ".");
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Confirming fees FAILED with error: " + e);
        }
        LOGGER.info("confirmFees completed");
    }

    /**
     * Gets fee element from page
     *
     * @param page String used to determine which element to search page for
     */
    private WebElement getFeeElement(String page) {
        LOGGER.info("getFeeElement started");
        if (page.equalsIgnoreCase(ORDER_PAGE)) {
            LOGGER.info("getFeeElement completed");
            return driver.getElementWithText(OrderPage.feeInfoBy, ConstantsDtc.ENVIRONMENTAL_FEE);
        } else {
            LOGGER.info("getFeeElement completed");
            return driver.getElementWithText(CheckoutPage.feeDetailsBy, ConstantsDtc.ABBREV_ENVIRONMENTAL_FEE);
        }
    }

    /**
     * Asserts that the current url matches base url with path or contains path
     *
     * @param path     The path of the url to verify
     * @param contains Boolean for URL vs partial URL
     */
    public void confirmCurrentUrl(String path, boolean contains) {
        LOGGER.info("confirmCurrentUrl started");

        try {
            String expectedUrl;
            String currentUrl = webDriver.getCurrentUrl();
            boolean assertion;
            if (contains) {
                expectedUrl = Config.getBaseUrl() + path;
                assertion = currentUrl.contains(path);
            } else {
                expectedUrl = path;
                assertion = currentUrl.equalsIgnoreCase(expectedUrl);
            }
            Assert.assertTrue("FAIL: Expected url: \"" + expectedUrl + "\" did NOT match the actual url: \""
                    + currentUrl + "\"!", assertion);
            LOGGER.info("Confirmed that current URL is \"" + expectedUrl + "\".");
        } catch (Exception e) {
            Assert.fail("FAIL: Confirming current URL FAILED with error: " + e);
        }
        LOGGER.info("confirmCurrentUrl completed");
    }

    /**
     * Waits for url to contain path
     *
     * @param path    The path of the url to wait for
     * @param seconds Integer of seconds for driver wait
     */
    public void waitForUrl(final String path, int seconds) {
        LOGGER.info("waitForUrl started");
        WebDriverWait wait = new WebDriverWait(webDriver, seconds);
        ExpectedCondition e = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (d.getCurrentUrl().contains(path));
            }
        };

        try {
            wait.until(e);
        } catch (Exception exception) {
            Assert.fail("FAIL: Url after \"" + seconds + "\" seconds remained \"" + webDriver.getCurrentUrl() +
                    "\" and never ended up containing \"" + path + "\"!");
        }
        LOGGER.info("Confirmed that current URL now contains '" + path + "'.");
        LOGGER.info("waitForUrl completed");
    }

    /**
     * Asserts that the expected header is displayed on the page
     *
     * @param header The header (h1 element text) of the page
     */
    public void assertPageHeader(String header) {
        LOGGER.info("assertPageHeader started");

        //TODO CCL - need to address issue w/ Safari; Safari appears to bring in extra chars and spacing that .trim()
        //TODO CCL (cont) and regex removal patterns seem to be ineffective in removing
        if (!Config.isSafari()) {
            driver.waitForPageToLoad();
            driver.waitForElementVisible(headerBy);
            String actualHeader = webDriver.findElement(headerBy).getText();

            Assert.assertTrue("FAIL: The expected page header '" + header + "' was NOT contained in the"
                    + " actual header '" + actualHeader + "'!", actualHeader.trim().toLowerCase().contains(
                    header.trim().toLowerCase()));
            LOGGER.info("Confirmed that the page header is \"" + header + "\".");
        }
        LOGGER.info("assertPageHeader completed");
    }

    /**
     * Asserts that the expected 2nd lvl header is displayed on the page
     *
     * @param text The header (h2 element text) of the page
     */
    public void assertPageSecondLevelHeader(String text) {
        LOGGER.info("assertPageSecondLevelHeader started");
        driver.waitForTextPresent(headerSecondBy, text, Constants.FIVE_SEC_WAIT);

        Set allHandles = webDriver.getWindowHandles();
        String mainHandle = webDriver.getWindowHandle();
        int count = allHandles.size();

        if (count == 1) {
            Assert.assertTrue("FAIL: \"" + text + "\" was NOT displayed on the page!",
                    driver.checkIfElementContainsText(headerSecondBy, text));
        } else {
            Iterator iter = allHandles.iterator();
            while (iter.hasNext()) {
                String popupHandle = iter.next().toString();
                if (!popupHandle.contains(mainHandle)) {
                    webDriver.switchTo().window(popupHandle);
                    Assert.assertTrue("FAIL: \"" + text + "\" was NOT displayed on the page!",
                            driver.checkIfElementContainsText(headerSecondBy, text));
                    webDriver.switchTo().window(mainHandle);
                }
            }
        }
        LOGGER.info("Confirmed that the element contains \"" + text + "\".");
        LOGGER.info("assertPageSecondLevelHeader completed");
    }

    /**
     * Asserts that the expected text is displayed on the page
     *
     * @param by           The By element of the page
     * @param textToSearch The header (subtext) of the page
     */
    public void assertPageElementTextByElement(By by, String textToSearch) {
        LOGGER.info("assertPageElementTextByElement started");
        Set allHandles = webDriver.getWindowHandles();
        String mainHandle = webDriver.getWindowHandle();
        int count = allHandles.size();
        if (count == 1) {
            Assert.assertTrue("FAIL: \"" + textToSearch + "\" was NOT displayed on the page!",
                    driver.checkIfElementContainsText(by, textToSearch));
        } else {
            Iterator iter = allHandles.iterator();
            while (iter.hasNext()) {
                String popupHandle = iter.next().toString();
                if (!popupHandle.contains(mainHandle)) {
                    webDriver.switchTo().window(popupHandle);
                    Assert.assertTrue("FAIL: \"" + textToSearch + "\" was NOT displayed on the page!",
                            driver.checkIfElementContainsTextLowerCase(by, textToSearch));
                    webDriver.switchTo().window(mainHandle);
                }
            }
        }
        LOGGER.info("Confirmed that the element contains \"" + textToSearch + "\".");
        LOGGER.info("assertPageElementTextByElement completed");
    }

    /**
     * Asserts whether the current page title matches or not
     *
     * @param title The Title (title element text) of the page
     */
    public void assertPageTitle(String title) {
        LOGGER.info("assertPageTitle started");
        int time = Constants.DEFAULT_SEC_WAIT;
        String script = "return document.getElementsByTagName('title')[0].textContent.indexOf('" + title + "')>-1;";
        boolean foundTitle = driver.pollUntil(script, time);
        Assert.assertTrue("FAIL: Title \"" + title + "\" was NOT displayed in \"" + time + "\" seconds!",
                foundTitle);
        LOGGER.info("Confirmed that the page title is \"" + title + "\".");
        LOGGER.info("assertPageTitle completed");
    }

    /**
     * Performs add to cart operation
     */
    public void addToCart() {
        LOGGER.info("addToCart started");
        if (driver.isElementDisplayed(addToCart)) {
            driver.jsScrollToElement(addToCart);
            addToCart.click();
        } else {
            driver.jsScrollToElement(addToCartPLP);
            addToCartPLP.click();
        }
        LOGGER.info("addToCart completed");
    }

    /**
     * Performs add to cart operation for the specified page
     *
     * @param page The page in test (PLP, PDP, Check Availability)
     */
    public void addToCart(String page) {
        LOGGER.info("addToCart for page:'" + page + "' started");
        WebElement addToCartElement = null;
        try {
            switch (page) {
                case "PDP":
                    addToCartElement = addToCart;
                    break;
                case "PLP":
                    addToCartElement = addToCartPLP;
                    break;
                case "Check Availability":
                    addToCartElement = addToCartProductAvailability;
                    break;
                default:
                    Assert.fail("FAIL: Could not find add to cart button");
            }
            driver.waitForElementVisible(addToCartElement);
            driver.jsScrollToElement(addToCartElement);
            addToCartElement.click();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        LOGGER.info("addToCart for page:'" + page + "' completed");
    }

    /**
     * Performs view shopping cart operation
     */
    public void clickViewShoppingCart() {
        LOGGER.info("clickViewShoppingCart started");
        driver.waitForElementClickable(viewShoppingCart);
        driver.jsScrollToElement(viewShoppingCart);
        viewShoppingCart.click();
        driver.waitForPageToLoad();
        LOGGER.info("clickViewShoppingCart completed");
    }

    /**
     * Performs continue shopping operation
     */
    public void clickContinueShopping() {
        LOGGER.info("clickContinueShopping started");
        driver.waitForElementClickable(continueShopping);
        driver.jsScrollToElement(continueShopping);
        continueShopping.click();
        driver.waitForPageToLoad();
        LOGGER.info("clickContinueShopping completed");
    }

    /**
     * Returns element among multiple that contains text substring, if substring not found
     * returns first
     *
     * @param by   The element to check
     * @param text The text to check
     */
    public void assertElementWithTextIsVisible(By by, String text) {
        LOGGER.info("assertTextPresentInElement started");
        if (driver.getElementWithText(by, text) == null) {
            Assert.fail("FAIL: Element \"" + by.toString() + "\" with text \"" + text + "\" NOT present!");
        } else {
            Assert.assertTrue("FAIL: Unable to find a displayed element with By = \"" + by
                            + "\" and text = \"" + text + "\"!",
                    driver.getElementWithText(by, text).isDisplayed());
        }
        LOGGER.info("assertTextPresentInElement ended - Line item \"" + text + "\" is present");
    }

    /**
     * Asserts whether an element of a particular By type is displayed
     *
     * @param byType  The by characteristic of the element (ie classname, css)
     * @param element The text of the element to search for
     */
    public void assertElementIsDisplayed(String byType, String element) {
        LOGGER.info("assertElementIsDisplayed started");
        boolean elementFound;
        try {
            By by = driver.getBy(byType, element);
            WebElement webElement = webDriver.findElement(by);
            elementFound = driver.isElementDisplayed(webElement, Constants.DEFAULT_SEC_WAIT);
        } catch (NoSuchElementException e) {
            elementFound = false;
        }
        Assert.assertTrue("FAIL: Element '" + element + " was either not found or not displayed.", elementFound);
        LOGGER.info("assertElementIsDisplayed ended - Element '" + element + " was displayed.");
    }


    /**
     * Returns the h1 header of the page
     *
     * @return String
     */
    public String getPageHeader() {
        return webDriver.findElement(headerBy).getText();
    }

    /**
     * Clicks next or previous in results pages. Since mweb and web share classnames for pagination,
     * getDisplayedElement must be leveraged in order to click the correct buttons
     *
     * @param direction The direction to paginate, "next", "previous", "first", "last"
     * @return boolean  Whether the button was found
     */
    public boolean navToDifferentPageOfResults(String direction) {
        LOGGER.info("navToDifferentPageOfResults started with direction: '" + direction + "'");
        WebElement button = null;
        boolean foundButton = true;

        if (Config.isFirefox() || Config.isMobile())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (direction.equalsIgnoreCase(Constants.NEXT)) {
            button = driver.getDisplayedElement(nextBtnBy, Constants.ZERO);
        } else if (direction.equalsIgnoreCase(Constants.PREVIOUS)) {
            button = driver.getDisplayedElement(previousBtnBy, Constants.ZERO);
        } else if (direction.equalsIgnoreCase(Constants.FIRST)) {
            button = driver.getDisplayedElement(firstBtnBy, Constants.ZERO);
        } else if (direction.equalsIgnoreCase(Constants.LAST)) {
            button = driver.getDisplayedElement(lastBtnBy, Constants.ZERO);
        } else {
            Assert.fail("FAIL: The results navigation direction of '" + direction + "' was NOT recognized!");
        }

        if (button != null) {
            driver.jsScrollToElement(button);
            try {
                button.click();
            } catch (Exception e) {
                driver.jsClick(button);
            }
        } else {
            foundButton = false;
        }
        LOGGER.info("navToDifferentPageOfResults completed with direction: '" + direction + "'");
        return foundButton;
    }

    /**
     * Checks for 'Welcome' popup modal and performs appropriate actions
     */
    public void checkForWelcomePopup() {
        LOGGER.info("checkForWelcomePopup started");
        if (driver.isElementDisplayed(continueButton, Constants.TWO_SEC_WAIT)) {
            CommonActions.continueButton.click();
            driver.pollUntil(welcomePopupClosed, Constants.TEN_SEC_WAIT);
        } else {
            LOGGER.info("checkForWelcomePopup not shown on screen");
        }
        LOGGER.info("checkForWelcomePopup completed");
    }

    /**
     * Sends back navigation command to browser
     */
    public void navigateToPreviousBrowserPage() {
        driver.browserNavigateBackAction();
    }

    /**
     * Finds and clicks element with given link text
     *
     * @param linkText Text in the link to click
     */
    public void clickElementByText(String linkText) {
        //Note: This is necessary as Driver methods cannot
        // be accessed from step definition methods

        if (Config.isSafari())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.clickElementWithLinkText(linkText);
    }

    /**
     * Checks if text is displayed
     *
     * @param text The string text to check
     */
    public void assertTextPresentInPageSource(String text) {
        LOGGER.info("assertTextPresentInPageSource started with text \"" + text + "\"");
        Assert.assertTrue("FAIL: Text: \"" + text + "\" - was NOT present!",
                driver.isTextPresentInPageSource(text));
        LOGGER.info("Text: \"" + text + "\" - was present");
        LOGGER.info("assertTextPresentInPageSource completed with text \"" + text + "\"");
    }

    /**
     * Checks for text inside the Breadcrumb container on the page
     *
     * @param linkName String representing the names/titles of the breadcrumb links being checked
     */
    public void verifyLinksInsideBreadcrumbContainer(String linkName) {
        LOGGER.info("verifyLinksInsideBreadcrumbContainer started");

        int count = 0;
        String links[] = linkName.split(",");

        if (Config.isMobile() || Config.isSafari() || Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (!Config.isFirefox() && !Config.isSafari())
            driver.waitForElementVisible(By.linkText(links[0]));

        List<WebElement> crumbLinks = webDriver.findElements(breadCrumbLinksBy);
        for (int i = 0; i < links.length; i++) {
            Assert.assertEquals("FAIL: Link \"" + links[count] + "\" was NOT found on page in the " +
                            "breadcrumb container!", links[count].trim().toLowerCase(),
                    crumbLinks.get(count).getText().trim().toLowerCase());
            count++;
        }

        LOGGER.info("verifyLinksInsideBreadcrumbContainer completed");
    }

    /**
     * Navigates to page with url + path
     *
     * @param path The path to append to base url
     */
    public void navigateToPage(String path) {
        LOGGER.info("navigateToPage started with path: \"" + path + "\"");

        if (Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        try {
            String url = Config.getBaseUrl() + path;
            driver.getUrl(url);
            driver.waitForPageToLoad();
        } catch (Exception e) {
            Assert.fail("FAIL: Navigating to page with url and path \"" + path + "\" FAILED with error: " + e);
        }
        LOGGER.info("navigateToPage completed with path: \"" + path + "\"");
    }

    /**
     * Verifies appropriate messages are present in top message banner
     *
     * @param item Tires or Wheels
     * @param page PLP or PDP page for banner selection
     * @param text Text inside the banner to verify
     */
    public void assertStringInTopBanner(String item, String page, String text) {
        LOGGER.info("assertStringInTopBanner started");
        if (page.equalsIgnoreCase(ConstantsDtc.PLP)) {
            //prevents null elements from being returned, as getDisplayedElement has no way to wait for visible
            driver.waitForClassPresent(driver.getByValue(plpResultMessageBy), Constants.DEFAULT_SEC_WAIT);
            if (Config.isMobilePhone()) {
                WebElement plpResultMessage = driver.getDisplayedElement(plpResultMessageBy, Constants.ZERO);
                Assert.assertTrue(text + " was not found in PLP banner message.",
                        plpResultMessage.getText().contains(text));
                Assert.assertTrue(ITEMS_FIT_YOUR + " was not found in PLP banner message.",
                        plpResultMessage.getText().contains(ITEMS_FIT_YOUR));
            } else if (item.equalsIgnoreCase(ConstantsDtc.TIRES) && !Config.isMobilePhone()) {
                WebElement plpResultMessage = driver.getDisplayedElement(plpResultMessageBy, Constants.ZERO);
                Assert.assertTrue(text + " was not found in PLP banner message.",
                        plpResultMessage.getText().contains(text));
                Assert.assertTrue(TIRES_FOR_YOUR + " was not found in PLP banner message.",
                        plpResultMessage.getText().contains(TIRES_FOR_YOUR));
            } else if (item.equalsIgnoreCase(ConstantsDtc.WHEELS) && !Config.isMobilePhone()) {
                WebElement plpResultMessage = driver.getDisplayedElement(plpResultMessageBy, Constants.ZERO);
                Assert.assertTrue(text + " was not found in PLP banner message.",
                        plpResultMessage.getText().contains(text));
                Assert.assertTrue(WHEELS_FOR_YOUR + " was not found in PLP banner message.",
                        plpResultMessage.getText().contains(WHEELS_FOR_YOUR));
            }
        } else {
            driver.waitForClassPresent(driver.getByValue(pdpResultMessageBy), Constants.DEFAULT_SEC_WAIT);
            WebElement pdpResultMessage = driver.getDisplayedElement(pdpResultMessageBy, Constants.ZERO);
            Assert.assertTrue(text + " was not found in PDP banner message.",
                    pdpResultMessage.getText().contains(text));
        }
        LOGGER.info("assertStringInTopBanner completed");
    }

    /**
     * Verifies the message banner color
     *
     * @param page  The page to assert against (PLP or PDP)
     * @param color The color to assert (Green,Yellow, Red)
     */
    public void assertBannerColor(String page, String color) {
        LOGGER.info("assertBannerColor started");
        String expectedBannerColor = GREEN_BANNER;
        String expectedBannerAltColor = GREEN_BANNER_ALT_RGBA;
        String backgroundColor;
        if (color.equalsIgnoreCase(Constants.GREEN) && Config.isFirefox() || Config.isSafari()) {
            expectedBannerColor = GREEN_BANNER_RGB;
            expectedBannerAltColor = GREEN_BANNER_ALT_RGB;
        } else if (color.equalsIgnoreCase(Constants.YELLOW)) {
            expectedBannerColor = YELLOW_BANNER;
            if (Config.isFirefox())
                expectedBannerColor = YELLOW_BANNER_ALT_RGB;
        } else if (color.equalsIgnoreCase(Constants.RED)){
            expectedBannerColor = RED_BANNER;
            if (Config.isFirefox())
                expectedBannerColor = RED_BANNER_ALT_RGB;
    }

        if (page.equalsIgnoreCase(ConstantsDtc.PLP)) {
            if (color.equalsIgnoreCase(Constants.GREEN)) {
                driver.waitForClassPresent(driver.getByValue(plpResultMessageBy), Constants.DEFAULT_SEC_WAIT);
                WebElement plpResultMessage = driver.getDisplayedElement(plpResultMessageBy, Constants.ZERO);
                backgroundColor = plpResultMessage.getCssValue(Constants.BACKGROUND_COLOR);
            } else {
                driver.waitForClassPresent(driver.getByValue(plpNotSureResultMessageBy), Constants.DEFAULT_SEC_WAIT);
                WebElement plpResultMessage = driver.getDisplayedElement(plpNotSureResultMessageBy, Constants.ZERO);
                backgroundColor = plpResultMessage.getCssValue(Constants.BACKGROUND_COLOR);
            }
            Assert.assertTrue("FAIL: Expected PLP color: " + expectedBannerColor + " but the actual color was: "
                    + backgroundColor + "!", backgroundColor.equalsIgnoreCase(expectedBannerColor));
        } else if (page.equalsIgnoreCase(ConstantsDtc.PDP)) {
            driver.waitForClassPresent(driver.getByValue(pdpResultMessageBy), Constants.DEFAULT_SEC_WAIT);
            WebElement pdpResultMessage = driver.getDisplayedElement(pdpResultMessageBy, Constants.ZERO);
            backgroundColor = pdpResultMessage.getCssValue(Constants.BACKGROUND_COLOR);
            Assert.assertTrue("FAIL: Expected PDP color: " + expectedBannerColor + " but the actual color was: "
                    + backgroundColor + "!", backgroundColor.equalsIgnoreCase(expectedBannerColor)
                    || backgroundColor.equalsIgnoreCase(expectedBannerAltColor));
        }
        LOGGER.info("assertBannerColor completed");
    }

    /**
     * Verifies provided text is contained inside the global results banner
     *
     * @param page The page to assert against (PLP or PDP)
     * @param text Text to verify appears
     */
    public void assertResultsMessageContains(String page, String text) {
        LOGGER.info("assertResultsMessageContains started");
        if (page.toLowerCase().equals(ConstantsDtc.PLP)) {
            driver.waitForElementVisible(CommonActions.plpResultMessage);
            Assert.assertTrue("PLP result message banner did not contain text: " + text,
                    CommonActions.plpResultMessage.getText().contains(text));
        } else if (page.toLowerCase().equals(ConstantsDtc.PDP)) {
            driver.waitForElementVisible(CommonActions.pdpResultMessage);
            Assert.assertTrue("PDP result message banner did not contain text: " + text,
                    pdpResultMessage.getText().contains(text));
        }
        LOGGER.info("assertResultsMessageContains completed");
    }

    /***
     * Checks for and closes the 'Chat Now' popup modal on DTD site
     */
    public void checkForChatNowPopup() {
        LOGGER.info("checkForChatNowPopup started");
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            if (driver.isElementDisplayed(noThanksBtnBy, Constants.TWO_SEC_WAIT)) {
                WebElement noThanksBtnEle = webDriver.findElement(noThanksBtnBy);
                driver.jsScrollToElement(noThanksBtnEle);
                noThanksBtnEle.click();
                driver.pollUntil(chatNowPopupClosed, Constants.FIVE_SEC_WAIT);
                LOGGER.info("Chat Now Popup was closed");
            } else {
                LOGGER.info("Chat Now Popup not shown on screen");
            }
        }
        LOGGER.info("checkForChatNowPopup completed");
    }

    /**
     * Switch to Already Opened Next Browser Tab
     */
    public void switchToNextOpenedTab() {
        LOGGER.info("switchToNextOpenedTab started");
        String mainHandle = webDriver.getWindowHandle();
        //TODO: This seems to wait for 5 seconds regardless
        new WebDriverWait(webDriver, Constants.FIVE_SEC_WAIT).until(ExpectedConditions.numberOfWindowsToBe(2));
        Set allHandles = webDriver.getWindowHandles();
        Iterator iter = allHandles.iterator();
        while (iter.hasNext()) {
            String popupHandle = iter.next().toString();
            if (!popupHandle.contains(mainHandle)) {
                webDriver.switchTo().window(popupHandle);
            }
        }
        LOGGER.info("switchToNextOpenedTab completed");
    }

    /**
     * Switch to Previous Opened Browser Tab
     */
    public void switchToPreviousOpenedTab() {
        LOGGER.info("switchToPreviousOpenedTab started");
        new WebDriverWait(webDriver, Constants.FIVE_SEC_WAIT).until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tab = new ArrayList<String>(webDriver.getWindowHandles());
        if (tab.size() > 1) {
            webDriver.switchTo().window(tab.get(1));
            webDriver.close();
            webDriver.switchTo().window(tab.get(0));
        } else {
            LOGGER.info("Previous Browser Tab Not Found");
        }
        LOGGER.info("switchToPreviousOpenedTab completed");
    }

    /**
     * Verifies element text is not empty
     *
     * @param element The element to check
     */
    public void assertElementTextPopulated(WebElement element) {
        LOGGER.info("assertElementTextPopulated started");

        driver.waitForElementVisible(element);

        Assert.assertTrue("FAIL: Element text was empty.",
                !element.getText().isEmpty());

        LOGGER.info("Element text was populated.");
        LOGGER.info("assertElementTextPopulated completed");
    }

    /**
     * Method takes in a string, removes all non-numeric characters and returns an Int
     *
     * @param phrase String containing numbers you'd like to return
     */
    public int removeNonNumericsFromString(String phrase) {
        LOGGER.info("removeNonNumericsFromString started");
        int returnInt = 0;
        try {
            phrase = phrase.replaceAll("\\D", "");
            returnInt = Integer.parseInt(phrase);
            LOGGER.info("Returning integer value of " + returnInt);
        } catch (Exception e) {
            Assert.fail("FAIL: Provided phrase did not contain a numeric value");
        }

        LOGGER.info("removeNonNumericsFromString completed");
        return returnInt;
    }

    /**
     * Closes all tabs or windows except the main handle
     */
    public void closeTabs() {
        LOGGER.info("closeTabs started");

        Set allHandles = webDriver.getWindowHandles();
        String mainHandle = webDriver.getWindowHandle();

        Iterator iter = allHandles.iterator();
        while (iter.hasNext()) {
            String popupHandle = iter.next().toString();
            if (!popupHandle.contains(mainHandle)) {
                webDriver.switchTo().window(popupHandle);
                webDriver.close();
                webDriver.switchTo().window(mainHandle);
            }
        }
        LOGGER.info("closeTabs completed");
    }

    /**
     * Cleans a string of any non-digit or non-decimal point chars and returns a double containing the newly parsed
     * value
     *
     * @param currencyString String containing a currency / monetary value needing to be cleaned & parsed
     * @return Monetary value in a Double type
     */
    public double cleanMonetaryString(String currencyString) {
        return Double.parseDouble(currencyString.split(Constants.EA)[0].replaceAll("[^0-9.]+", ""));
    }

    /**
     * Convert decimal to exactly two decimal place
     *
     * @return any calculated value in a Double type
     */
    public double twoDForm(double input, int digits) {
        double digitFactor = Math.pow(10.0D, digits);
        return Math.round(input * digitFactor) / digitFactor;
    }

    /**
     * Extracts the subtotal value from either the Cart or Checkout page
     *
     * @return Subtotal amount / value from the specified page, cleaned and typed as a Double
     */
    public double extractSubtotalAmount(String pageWithSubtotal) {
        LOGGER.info("extractSubtotalAmount started");
        WebElement subtotalEle;

        if (pageWithSubtotal.equalsIgnoreCase("cart")) {
            subtotalEle = CartPage.cartSubtotal;
        } else {
            subtotalEle = CheckoutPage.checkoutSubtotal;
        }

        driver.waitForElementVisible(subtotalEle);
        LOGGER.info("extractSubtotalAmount completed");
        return cleanMonetaryString(subtotalEle.getText());
    }

    /**
     * Closes a popup
     */
    public void closePopup() {
        LOGGER.info("closePopup started");
        webDriver.findElement(closeButtonBy).click();
        driver.waitForElementNotVisible(closeButtonBy);
        LOGGER.info("closePopup completed");
    }

    /**
     * Converts a date in the format of mm/dd/yyyy to EEEE, MMM dd format (e.g. 12/04/2017 to Monday, Dec 04)
     *
     * @param date String in the format of mm/dd/yyyy to be converted
     * @return Date string in the format of EEEE, MMM dd (Monday, Dec 04)
     */
    public String convertDateToDayMonthDateFormat(String date) {
        LOGGER.info("convertDateToDayMonthDateFormat started with date:  " + date);
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            Date t = fmt.parse(date);
            fmt.applyPattern("EEEE, MMM d");
            date = fmt.format(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LOGGER.info("convertDateToDayMonthDateFormat completed with date:  " + date);
        return date;
    }

    /**
     * Extracts the sales tax from Cart page
     *
     * @return salesTax typed as a Double
     */
    public double extractSalesTaxOnCart() {
        LOGGER.info("extractSalesTaxOnCart started");
        WebElement tax = driver.getElementWithText(CartPage.cartSummaryBreakDownNameBy, ConstantsDtc.ESTIMATED_TAX);
        WebElement taxParent = driver.getParentElement(driver.getParentElement(tax));
        LOGGER.info("extractSalesTaxOnCart completed");
        return cleanMonetaryString(taxParent.findElement(CartPage.cartSummaryBreakDownPrice).getText());
    }

    /**
     * Selects the desired phone type from the phone type dropdown based on the flow path specified
     *
     * @param dropdownFlow The flow by which the dropdown is encountered (Checkout with shipping address,
     *                     Schedule Service, etc)
     * @param phoneType    Phone type to select from the dropdown
     */
    public void selectPhoneTypeFromDropdown(String dropdownFlow, String phoneType) {
        LOGGER.info("selectPhoneTypeFromDropdown started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(dropdownBy, Constants.FIVE_SEC_WAIT);

        int index = 0;
        if (dropdownFlow.equalsIgnoreCase(ConstantsDtc.CHECKOUT_WITH_SHIPPING)) {
            index = 1;
        }

        WebElement phoneDropdownEle = webDriver.findElements(dropdownBy).get(index);
        driver.jsScrollToElement(phoneDropdownEle);
        phoneDropdownEle.click();
        driver.getElementsWithText(dropdownOptionBy, phoneType).get(0).click();
        LOGGER.info("selectPhoneTypeFromDropdown completed");
    }

    /**
     * Verify whether the specified button is enabled or disabled
     *
     * @param enabledOrDisabled - expected state of 'enabled' or 'disabled'
     */

    /**
     * Verify whether the specified button is enabled or disabled
     *
     * @param buttonText        - the text on the button to be validated
     * @param enabledOrDisabled - expected state of 'enabled' or 'disabled'
     */
    public void assertButtonEnabledDisabled(String buttonText, String enabledOrDisabled) {
        LOGGER.info("assertButtonEnabledDisabled started");
        boolean found = false;
        List<WebElement> buttons = webDriver.findElements(submitButtonBy);

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText().equals(buttonText)) {
                if (enabledOrDisabled.equalsIgnoreCase(Constants.ENABLED)) {
                    Assert.assertTrue("The submit button was disabled when it was expected to be enabled", buttons.get(i).isEnabled());
                } else {
                    Assert.assertTrue("The submit button was enabled when it was expected to be disabled", !buttons.get(i).isEnabled());
                }
                found = true;
                break;
            }
        }

        Assert.assertTrue(buttonText + " button not found", found);
        LOGGER.info("assertButtonEnabledDisabled completed");
    }

    /**
     * Close item added to your cart pop up window
     */
    public void closeAddedToCartPopupWindow() {
        LOGGER.info("closeAddedToCartPopupWindow started");
        driver.waitForElementClickable(viewShoppingCart);
        driver.jsScrollToElement(viewShoppingCart);
        closeCart.click();
        driver.waitForPageToLoad();
        driver.waitForElementNotVisible(viewShoppingCartBy);
        LOGGER.info("closeAddedToCartPopupWindow completed");
    }

    /**
     * Verifies the expected attributes (strings) are contained within a specified section (By object)
     *
     * @param pageSection            Name of the page section
     * @param sectionHeaderBy        By object for the section of the page containing the attributes to be verified
     * @param expectedAttributesList List of attribute values expected to be contained with the specified page section
     */
    public void verifyPageSectionContainsAttributes(String pageSection, By sectionHeaderBy,
                                                    List<String> expectedAttributesList) {
        LOGGER.info("verifyPageSectionContainsAttributes started for section: '" + pageSection
                + "'\n\t\twith expected attributes: '" + expectedAttributesList + "'");
        String compareSectionContent = driver.getElementWithText(sectionHeaderBy, pageSection).getText();
        for (String attribute : expectedAttributesList) {
            Assert.assertTrue("FAIL - The page section '" + pageSection
                            + "' did NOT contain the expected attribute of: '" + attribute + "'!",
                    compareSectionContent.contains(attribute));
        }
        LOGGER.info("verifyPageSectionContainsAttributes completed successfully for section: '"
                + pageSection + "'");
    }

    /**
     * Verify Mini Cart not displayed on shipping details, shipping method, payment and order confirmation page
     *
     * @param page web page type
     */
    public void assertMiniCartNotDisplayed(String page) {
        LOGGER.info("assertMiniCartNotDisplayed started for " + page);
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: MiniCart is displayed", driver.isElementNotVisible(miniCartBy,
                Constants.TWO_SEC_WAIT));
        LOGGER.info("assertMiniCartNotDisplayed completed for " + page);
    }

    /**
     * Verify Signout link is displayed
     */
    public void assertSignOutLinkDisplayed() {
        LOGGER.info("assertSignOutLinkDisplayed started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: Sign out link did NOT display", driver.isElementDisplayed(myProfileSignOut));
        LOGGER.info("assertSignOutLinkDisplayed completed");
    }

    /**
     * Closes a modal window
     */
    public void closeModalWindow() {
        LOGGER.info("closeModalWindow started");
        driver.waitForElementClickable(modalClose);
        webDriver.findElement(modalClose).click();
        driver.waitForPageToLoad();
        driver.waitForElementNotVisible(modalClose);
        LOGGER.info("closeModalWindow completed");
    }

    /**
     * Get the X coordinate value of an element
     *
     * @param element The element in reference
     * @return int     X coordinate value of an element
     */
    public int getXCoordinateValueOfAnElement(WebElement element) {
        LOGGER.info("getXCoordinateValueOfAnElement started");
        driver.waitForElementVisible(element);

        int xCoordinateValue = Integer.valueOf(element.getLocation().toString().split(",")[0].split("\\(")[1]);
        LOGGER.info("getXCoordinateValueOfAnElement completed");
        return xCoordinateValue;
    }

    /**
     * Asserts that the expected 3rd lvl header is displayed on the page
     *
     * @param text The header (h3 element text) of the page
     */
    public void assertPageThirdLevelHeader(String text) {
        LOGGER.info("assertPageThirdLevelHeader started");
        driver.waitForTextPresent(headerThirdBy, text, Constants.FIVE_SEC_WAIT);

        Set allHandles = webDriver.getWindowHandles();
        String mainHandle = webDriver.getWindowHandle();
        int count = allHandles.size();

        if (count == 1) {
            Assert.assertTrue("FAIL: \"" + text + "\" was NOT displayed on the page!",
                    driver.checkIfElementContainsText(headerThirdBy, text));
        } else {
            Iterator iter = allHandles.iterator();
            while (iter.hasNext()) {
                String popupHandle = iter.next().toString();
                if (!popupHandle.contains(mainHandle)) {
                    webDriver.switchTo().window(popupHandle);
                    Assert.assertTrue("FAIL: \"" + text + "\" was NOT displayed on the page!",
                            driver.checkIfElementContainsText(headerThirdBy, text));
                    webDriver.switchTo().window(mainHandle);
                }
            }
        }
        LOGGER.info("Confirmed that the element contains \"" + text + "\".");
        LOGGER.info("assertPageThirdLevelHeader completed");
    }

    /**
     * Evaluates a given String to determine if it is null or empty ("")
     *
     * @param str String of text to evaluate
     * @return True if string == empty OR False if string != empty
     */
    public static boolean isStringEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Verifies the checkbox with specified label has been selected
     *
     * @param element       WebElement for the checkbox
     * @param checkboxLabel String of the checkbox label
     */
    public void verifyCheckboxSelected(WebElement element, String checkboxLabel) {
        LOGGER.info("verifyCheckboxSelected started for checkbox w/ label: \"" + checkboxLabel + "\"");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: checkbox with label: \"" + checkboxLabel + "\" was NOT selected!",
                element.isSelected());
        LOGGER.info("verifyCheckboxSelected completed - checkbox w/ label: \"" + checkboxLabel
                + "\" was selected!");
    }

    /**
     * Extracts the ratings and verifies they are in ascending/descending order
     *
     * @param sortOrder String (Ascending or Descending)
     * @param value     String (Lowest Rated,Overall Rating)
     */
    public void verifyRatingOrder(String value, String sortOrder) {
        LOGGER.info("verifyRatingOrder started");
        List<WebElement> reviews = null;
        if (value.equalsIgnoreCase(ConstantsDtc.OVERALL_RATING)) {
            Assert.assertFalse("Fail - The \"There are no products to compare\" message was displayed!",
                    driver.isElementDisplayed(noProductsToCompareMessage, Constants.ONE_SEC_WAIT));
            driver.waitForElementVisible(overallRatingBy);
            reviews = webDriver.findElements(overallRatingBy);
        } else if (value.equalsIgnoreCase(ConstantsDtc.LOWEST_RATED)) {
            reviews = webDriver.findElements(storeReviewScoreBy);
        }
        ArrayList<Float> reviewList = new ArrayList<>();
        if (null != reviews) {
            for (WebElement review : reviews) {
                reviewList.add(Float.parseFloat(review.getText()));
            }
            if (sortOrder.equalsIgnoreCase(Constants.ASCENDING)) {
                if (!verifyAscendingOrder(reviewList)) {
                    Assert.fail("FAIL : Rating did NOT display in ascending order");
                }
            } else if (sortOrder.equalsIgnoreCase(Constants.DESCENDING)) {
                if (!verifyDescendingOrder(reviewList)) {
                    Assert.fail("FAIL : Rating did NOT display in descending order");
                }
            } else {
                Assert.fail("FAIL: Input value should either be Ascending or Descending but was :\""
                        + sortOrder + "\"!");
            }
        } else {
            Assert.fail("FAIL: There are no reviews ");
        }
        LOGGER.info("verifyRatingOrder completed");
    }

    /**
     * Checks the ascending order for the list of products on the compare tire reviews page excluding the first one
     * because the first one in the list is the Selected Tire
     *
     * @param value The array to check the ascending order of reviews
     * @return whether or not the list is sorted in ascending order
     */
    private Boolean verifyAscendingOrder(ArrayList<Float> value) {
        LOGGER.info("verifyAscendingOrder started");
        driver.waitForPageToLoad();
        for (int i = 1; i < value.size() - 1; i++) {
            if (value.get(i) > value.get(i + 1)) {
                return false;
            }
        }
        LOGGER.info("verifyAscendingOrder completed");
        return true;
    }

    /**
     * Checks the descending order for the list of products on the compare tire reviews page excluding the first one
     * because the first one in the list is the Selected Tire
     *
     * @param value The array to check the descending order of reviews
     * @return whether or not the list is sorted in descending order
     */
    private Boolean verifyDescendingOrder(ArrayList<Float> value) {
        LOGGER.info("verifyDescendingOrder started");
        driver.waitForPageToLoad();
        for (int i = 1; i < value.size() - 1; i++) {
            if (value.get(i) < value.get(i + 1)) {
                return false;
            }
        }
        LOGGER.info("verifyDescendingOrder completed");
        return true;
    }

    /**
     * Verify Inventory Message for item code
     *
     * @param page    web page
     * @param message the expected Inventory Message
     * @param item    Product Code
     */
    public void assertInventoryMessage(String message, String item, String page) {
        LOGGER.info("assertInventoryMessage started on " + page);
        driver.waitForPageToLoad();
        String inventoryMessageText;
        if (page.equalsIgnoreCase(ConstantsDtc.PDP)) {
            inventoryMessageText = webDriver.findElement(inventoryMessageBy).getText();
        } else {
            inventoryMessageText = driver.getParentElement(
                    driver.getParentElement(driver.getElementWithText(itemIdBy, item)))
                    .findElement(inventoryMessageBy).getText();
        }
        Assert.assertTrue("FAIL: The inventory message on" + page + " didn't match for product :"
                        + item + " ! (displayed:-> '" + inventoryMessageText + "'  expected:->  " + message + "'!",
                inventoryMessageText.equals(message));
        LOGGER.info("assertInventoryMessage completed on " + page);
    }

    /**
     * Verify Check nearby stores link display for item code
     *
     * @param page web page
     * @param text displayed or not displayed
     * @param item Product Code
     */
    public void assertCheckNearbyStoresLinkDisplay(String text, String item, String page) {
        LOGGER.info("assertCheckNearbyStoresLinkDisplay started on " + page);
        driver.waitForPageToLoad();
        WebElement parentElement;
        if (page.equalsIgnoreCase(ConstantsDtc.PDP)) {
            parentElement = webDriver.findElement(pdpInfoContainerBy);
        } else {
            parentElement = driver.getParentElement(
                    driver.getParentElement(driver.getElementWithText(CommonActions.itemIdBy, item)));
        }
        if (text.equalsIgnoreCase(Constants.DISPLAYED)) {
            Assert.assertTrue("FAIL: The Check nearby stores link on" + page
                            + "for product" + item + "is not displayed!",
                    driver.isElementDisplayed(parentElement.findElement(checkInventoryBy)));
            Assert.assertTrue("FAIL: The Check nearby stores link text on" + page + " didn't match for product:"
                            + item + " ! (displayed:-> '" + parentElement.findElement(checkInventoryBy).getText()
                            + "'  expected:->  " + ConstantsDtc.CHECK_NEARBY_STORES + "'!",
                    parentElement.findElement(checkInventoryBy).getText().equals(ConstantsDtc.CHECK_NEARBY_STORES));
        } else {
            if (webDriver.findElements(CommonActions.itemIdBy).size() == 1) {
                Assert.assertTrue("FAIL: The Check nearby stores link on" + page
                                + "for product" + item + "is displayed!",
                        !(driver.isElementDisplayed(checkInventoryBy)));
            } else {
                List<WebElement> links = webDriver.findElements(checkInventoryBy);
                for (WebElement link : links) {
                    String productCode = driver.getParentElement(
                            driver.getParentElement(link)).findElement(CommonActions.itemIdBy).getText();
                    Assert.assertTrue("FAIL: The Check nearby stores link on" + page
                                    + "for product" + item + "is displayed!",
                            !productCode.contains(item));
                }
            }
        }
        LOGGER.info("assertCheckNearbyStoresLinkDisplay completed on " + page);
    }

    /**
     * Returns row containing specified item code
     *
     * @param itemCode The item code to get the row for
     * @return int      The row containing the item code
     */
    public int getRowOfItem(String itemCode) {
        LOGGER.info("getRowOfItem started with itemCode:'" + itemCode + "'");
        int rowCount = 0;
        List<WebElement> rows;

        webDriver.manage().timeouts().implicitlyWait(Constants.ZERO, TimeUnit.SECONDS);

        if (Config.isMobilePhone()) {
            rows = webDriver.findElements(ProductListPage.resultsRowMobileBy);
        } else {
            rows = webDriver.findElements(ProductListPage.plpResultsRowBy);
        }

        for (WebElement row : rows) {
            if (row.getText().contains(itemCode)) {
                break;
            } else {
                rowCount++;
            }
        }

        driver.resetImplicitWaitToDefault();
        LOGGER.info("getRowOfItem completed for itemCode:'" + itemCode + "'");
        return rowCount;
    }

    /**
     * Verify Inventory Message for set product
     *
     * @param page    web page
     * @param message the expected Inventory Message
     * @param item    set Product Code
     */
    public void assertInventoryMessageForSet(String message, String item, String page) {
        LOGGER.info("assertInventoryMessageForSet started on " + page);
        driver.waitForPageToLoad();
        int productIndex = 0;
        WebElement parent;
        List<WebElement> products;
        if (page.equalsIgnoreCase(ConstantsDtc.PDP)) {
            parent = webDriver.findElement(CommonActions.pdpInfoContainerBy);
            products = webDriver.findElements(ProductDetailPage.tireItemInfoBy);
        } else {
            parent = driver.getParentElement(
                    driver.getParentElement(driver.getElementWithText(itemIdBy, item)));
            products = parent.findElements(itemIdBy);
        }
        for (WebElement product : products) {
            if (product.getText().contains(item)) {
                Assert.assertTrue("FAIL: The inventory message on" + page + " didn't match for product :"
                                + item + " ! (displayed:-> '" + parent.findElements(inventoryMessageBy).get(productIndex).getText()
                                + "'  expected:->  " + message + "'!",
                        parent.findElements(inventoryMessageBy).get(productIndex).getText().equals(message));
                break;
            }
            productIndex++;
        }
        LOGGER.info("assertInventoryMessageForSet completed on " + page);
    }

    /**
     * Verify Check nearby stores link display for set product
     *
     * @param page web page
     * @param text displayed or not displayed
     * @param item set Product Code
     */
    public void assertCheckNearbyStoresLinkDisplayForSetItem(String text, String item, String page) {
        LOGGER.info("assertCheckNearbyStoresLinkDisplayForSetItem started on " + page);
        driver.waitForPageToLoad();
        List<WebElement> checkNearByStores = webDriver.findElements(checkInventoryBy);
        for (WebElement link : checkNearByStores) {
            if (link.getAttribute(Constants.ACTIONURL).contains(item)) {
                Assert.assertTrue("FAIL: The Check nearby stores link on" + page
                                + "for product" + item + "is not displayed!",
                        text.equalsIgnoreCase(Constants.DISPLAYED));
                break;
            }
            Assert.assertTrue("FAIL: The Check nearby stores link on" + page
                            + "for product" + item + "is expected to be " + text,
                    text.equalsIgnoreCase(Constants.NOT_DISPLAYED));
        }
        LOGGER.info("assertCheckNearbyStoresLinkDisplayForSetItem completed on " + page);
    }

}