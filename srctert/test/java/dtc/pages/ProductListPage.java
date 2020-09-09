package dtc.pages;

import com.google.common.collect.Ordering;
import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aaronbriel on 9/22/16.
 */
public class ProductListPage {

    private Driver driver;
    private WebDriver webDriver;
    private final CommonActions commonActions;
    private final SiteMapPage siteMapPage;
    private static final Logger LOGGER = Logger.getLogger(ProductListPage.class.getName());
    public static String itemID;
    public static String plpBrandName;

    public ProductListPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
        siteMapPage = new SiteMapPage(driver);
    }

    private boolean showFilterBtnClicked = false;

    private int totalSelectedItems = 0;
    private static final Integer ZERO = 0;
    private static final Integer ONE_HUNDRED_THOUSAND = 100000;
    private int minValueApproximate = 0;
    private int maxValueApproximate = 0;

    private static final String PRICE_LOW_TO_HIGH = "Price (Low To High)";
    private static final String PRICE_HIGH_TO_LOW = "Price (High To Low)";
    private static final String RELEVANCE = "Relevance";
    private static final String HIGHEST_RATED = "Highest Rated";
    private static final String NAME_ASC = "Name (ascending)";
    private static final String NAME_DESC = "Name (descending)";

    private static final String TOTAL_RESULTS = "Total Results";
    private static final String SPECIAL_ORDER_AVAILABILITY = "Usually available in 7 - 10 days.";
    private static final String SPECIAL_ORDER = "Special Order";
    private static final String E = "e";
    private static final String ASCENDING = "Ascending";
    private static final String DESCENDING = "Descending";
    private static final String FRONT = "FRONT";
    private static final String REAR = "REAR";
    private static final String QUANTITY_ERROR_MOBILE = "Please enter a number";
    private static final String CUSTOMER_RATING = "Customer Rating";
    private static final String THERE_ARE_NO_REVIEWS = "There are no reviews yet.";
    private static final String CHECK_INVENTORY = "Check Inventory";
    private static final String MULTIPLE = "multiple";
    private static final String THOUSAND_MILES_SUFFIX = ",000";
    private static String filterFontSize = " ";
    private static final String NO_VEHICLE_SELECTED = "No vehicle selected";

    public static final String[] PDL_EVERYDAY_PRIORITY_ORDER = {
            "Tire Life", "Stopping", "Handling", "Comfort"};

    public static final String[] PDL_PERFORMANCE_PRIORITY_ORDER = {
            "Handling", "Stopping", "Comfort & Noise", "Life of Tire"};
    private static final String STAGGERED_FRONT = "Front";
    private static final String STAGGERED_REAR = "Rear";
    private static final String[] STAGGERED_PLP_TAB = {"SETS", "FRONT", "REAR"};
    private static HashMap<String, String> productPriceOnPlp = new HashMap<>();
    private static final String PRODUCT_IMG_MAX_HEIGHT_SIZE = "190px";
    private static final String PRODUCT_IMG_MAX_WIDTH_SIZE = "100%";
    private static final String IMG_MAX_HEIGHT_ATTR = "max-height";
    private static final String IMG_MAX_WIDTH_ATTR = "max-width";

    private static final ArrayList<String> brandList = new ArrayList<>();
    private static final ArrayList<String> productList = new ArrayList<>();
    private static final ArrayList<String> priceList = new ArrayList<>();
    private static final ArrayList<String> inventoryMessageList = new ArrayList<>();

    private String showLessLinkForSectionXpathMobile = "//button[contains(@class, \"dt-link\")]" +
            "[normalize-space()=\"Show Less\"]";

    private String unsureItemWillFitMsg = "We're not sure if these items will fit.";
    private String tellUsAboutVehicleMsg = "Tell us about your vehicle to get the correct items.";
    private ArrayList<String> sortOptionsList =
            new ArrayList<>(Arrays.asList(RELEVANCE, PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW,
                    HIGHEST_RATED, NAME_ASC, NAME_DESC));

    private static final String SETS = "SETS";
    private static final String compareUpTo3 = "Compare (up to 3)";
    private static final String compare = "Compare";
    private static final String BLUE_COMPARE_COLOR = "rgba(52, 131, 222, 1)";
    private static final String iconOpenClass = "--open";
    private static final String ITEM_NUMBER_PREFIX = "ITEM# ";
    private static final String ORIGINAL_EQUIPMENT_TIRE_MESSAGE = "Original Equipment Tire";

    private static final String QUICK_FILTERS = "Quick Filters";
    private static final String BRANDS = "Brands";
    private static final String PRICE_RANGE = "Price Range";
    private static final String REVIEW_RATINGS = "Review Ratings";
    private static final String MILEAGE_WARRANTY = "Mileage Warranty";
    private static final String TIRE_CATEGORY = "Tire Category";
    private static final String GOOD_BETTER_BEST = "Good Better Best";
    private static final String SIDEWALL_DESCRIPTION = "Sidewall Description";
    private static final String SPEED_RATING = "Speed Rating";
    private static final String LOAD_RANGE = "Load Range";
    private static final String SECTION_WIDTH = "Section Width";
    private static final String ASPECT_RATIO = "Aspect Ratio";
    private static final String DIAMETER = "Diameter";
    private static final String WHEEL_CATEGORY = "Wheel Category";
    private static final String WHEEL_COLOR = "Wheel Color";
    private static final String WHEEL_WIDTH = "Wheel Width";

    private static final List<String> tireFilterCategoriesList = new ArrayList<>(Arrays.asList(QUICK_FILTERS, BRANDS,
            PRICE_RANGE, REVIEW_RATINGS, MILEAGE_WARRANTY, TIRE_CATEGORY, GOOD_BETTER_BEST, SIDEWALL_DESCRIPTION,
            SPEED_RATING, LOAD_RANGE, SECTION_WIDTH, ASPECT_RATIO, DIAMETER));

    private static final List<String> wheelFilterCategoriesList = new ArrayList<>(Arrays.asList(QUICK_FILTERS, BRANDS,
            PRICE_RANGE, WHEEL_CATEGORY, WHEEL_COLOR, DIAMETER, WHEEL_WIDTH));

    private static final String SHOW_MORE = "Show More";

    private static final String MIN = "min";

    private static final String MAX = "max";

    private static final By resultsOptionLinkBy = By.className("results__optionlink");

    public static final By plpResultsRowBy = By.className("results-row");

    private static final By checkInventoryBy = By.cssSelector(".results-row__link");

    private static final By compareCheckboxBy = By.className("chooseCompare");

    private static final By compareButtonTagBy = By.cssSelector("a[class^='btn-small']");

    private static final By compareButtonValueBy = By.cssSelector(".results-row__compare button");

    private static final By compareButtonActiveMobileBy = By.className("product-list__compare-button--active");

    private static final By brandNameBy = By.cssSelector(".results-row__brandname > a");

    private static final By brandNameMobileBy = By.cssSelector(".product-list__item-brand");

    private static final By productNameBy = By.cssSelector(".results-row__productname > a");

    private static final By productNameMobileBy = By.cssSelector(".product-list__item-name");

    private static final By productPriceBy = By.cssSelector(".results-row__price");

    private static final By productMapPriceBy = By.cssSelector(".pdp-info__map-amount");

    private static final By productPriceMobileBy = By.cssSelector(".product-list__item-price");

    private static final By searchRefinementFilterBy = By.className("product-list-filter__applied-filter");

    private static final By dropDownOptionBy = By.className("active-result");

    private static final By specialOrderAvailabilityMobileBy = By.className("product-list__item-availability");

    private static final By searchFilterSectionBy = By.className("product-list-filter__filter-category");

    private static final By searchFilterSectionToggleBy = By.className("product-list-filter__toggle");

    private static final By openFilterSectionIconBy = By.className("product-list-filter__toggle");

    private static final By filterOptionBy = By.className("product-list-filter__option-label");

    private static final By addToCartBy = By.className("results-row__cartbutton");

    private static final By addToCartMobileBy = By.className("js-add-to-cart-button");

    private static final By resultsOptionLinkMobileBy = By.className("dt-tabs__label");

    public static final By resultsRowMobileBy = By.className("product-list__item");

    private static final By resultsRowMobileAnchorBy = By.className("auto-btn-compare-finalurl");

    private static final By productPostCodeBy = By.name("productCodePost");

    private static final By resultsMessageBy = By.className("product-list__total-results");

    private static final By productImageBy = By.className("js-product-list-image");

    private static final By productCompareMessageBy = By.className("js-compare-message");

    private static final By productQuantityErrorMobileBy = By.className("js-add-to-cart-error");

    private static final By promotionDiscountBy = By.className("promotion-message__title");

    private static final By compareTireReviewsBy = By.className("link-secondary");

    private static final By resultsRowRatingBy = By.className("results-row__rating");

    private static final By productListingBy = By.className("product-listing");

    private static final By zipLocationBy = By.className("product-list__pdl-details-value");

    private static final By milesPerYearBy = By.className("product-list__pdl-details-value--green");

    private static final By ourRecommendationBannerBy = By.className("results-row__recommended");

    private static final By pdlDrivingPrioritiesBy = By.className("product-list__pdl-priority");

    private static final By pdlDrivingPriorityOptionNameBy = By.className("display-inline-block-md");

    private static final By pdlEditDrivingDetailsBy = By.className("product-list__pdl-edit");

    private static final By tireSizeBy = By.className("results-row__tiresize");

    private static final By clearfixBy = By.className("clearfix");

    private static final By itemCodeBy = By.cssSelector("input[type='hidden']");

    private static final By sortByCurrentlySelectedMobileBy =
            By.xpath("//select[@id='sortOptions2']//option[@selected='selected']");

    private static final By sortByCurrentlySelectedWebBy = By.xpath("//div[@id='sortOptions1_chosen']/a");

    private static final By reviewRatingBy = By.className("review-rating");

    private static final By staggeredOptionTabBy = By.className("auto-results-option-link");

    private static final By staggeredSizeBy = By.className("number");

    private static final By resultRowBrandNameBy = By.className("auto-results-row-brand-name");

    private static final By resultRowAvailabilityBy = By.className("results-row__availability");

    private static final By resultRowAvailabilityMessageBy = By.cssSelector(".results-row__availability > p");

    private static final By originalEquipmentTireMessageBy = By.cssSelector("div.results-row__datacolumn>p.product-list__original-equipment");

    private static final By quantityInfoBoxBy = By.cssSelector(".results-row__quantity.auto-results-row-quantity");

    private static final By productItemBy = By.className("product-item");

    private static final By productListOEBy = By.className("product-list__original-equipment");

    private static final By sortByCurrentSelectedValueBy = By.id("sortOptions2_chosen");

    private static final By filterOptionLabelBy = By.className("product-list-filter__option-label");

    private static final By searchFilterSectionTitleBy = By.className("product-list-filter__category-name");

    private static final By staggeredMyStoreMessageBy = By.cssSelector(".auto-stock-mystore");

    private static final By compareBy = By.xpath("//a[@class='btn-small inverse inline-block finalUrl auto-btn-compare-finalurl'][not(contains(@class,'hidden'))]");

    private static final By compareUpTo3By = By.xpath("//button[@class='btn-small inverse inline-block finalText auto-btn-compare-finaltext']");

    private static final By compareInputBy = By.cssSelector(".results-row__compare input");

    private static final By resultRowImageColumnBy = By.cssSelector(".results-row__imagecolumn input");

    private static final By compareUpTo3DisabledBy = By.cssSelector(".inverse.auto-btn-compare-finaltext[disabled='disabled']");

    private static final By inputTagBy = By.tagName("input");

    private static final By currentPriceRangeBy = By.className("price-range-filter__price-range");

    private static final By priceRangeMinimumPointBy = By.className("rc-slider-handle-1");

    private static final By priceRangeMaximumPointBy = By.className("rc-slider-handle-2");

    private static final By priceRangeApplyBtnBy = By.className("price-range-filter__apply");

    private static final By filterOptionCheckboxBy = By.className("dt-checkbox__display");

    private static final By productListImageBy = By.cssSelector(".results-row__image > .thumb >.js-product-list-image");

    private static final By top3TilesBy = By.cssSelector(".top-three-tile__inner-tile > h5");

    private static final By top3TilesBrandBy = By.className("top-three-tile__brand");

    public static final By productBy = By.className("auto-results-row-product-name");

    @FindBy(className = "results__right")
    public static WebElement plpResultSection;

    @FindBy(className = "results-row__quantity")
    public static WebElement productQuantityBox;

    @FindBy(className = "js-add-to-cart-qty")
    public static WebElement productQuantityBoxMobile;

    @FindBy(css = ".js-checkinventory-in-store-link.results-row__link")
    public static WebElement checkInventory;

    @FindBy(className = "results-row__cartbutton")
    public static WebElement addToCart;

    @FindBy(className = "js-add-to-cart-button")
    public static WebElement addToCartMobile;

    @FindBy(linkText = "View shopping cart")
    public static WebElement viewCart;

    @FindBy(linkText = "Continue Shopping")
    public static WebElement continueShopping;

    @FindBy(id = "cartCloseBut")
    public static WebElement closeButton;

    @FindBy(className = "dt-modal__close")
    public static WebElement mobileFilterCloseBtn;

    @FindBy(className = "refinements__clear")
    public static WebElement clearAllFiltersLink;

    @FindBy(className = "dt-button-lg--secondary")
    public static WebElement clearAllFiltersLinkMobile;

    @FindBy(className = "no-results__description")
    public static WebElement noResultsInfoMessage;

    @FindBy(className = "dt-button--ghost")
    public static WebElement filterBtn;

    @FindBy(className = "product-list__total-results")
    public static WebElement totalResults;

    @FindBy(className = "dt-link")
    public static WebElement showMoreLink;

    @FindBy(className = "product-list__details")
    public static WebElement productListDetails;

    @FindBy(className = "results__showfilter")
    public static WebElement showFilterBtn;

    @FindBy(xpath = "//div[@class='my-vehicles__selected-vehicle-description']/span")
    public static WebElement vehicleSelectedDescription;

    @FindBy(xpath = "(//label[contains(text(),'Bolt Pattern')])[1]")
    public static WebElement boltPatternFacet;

    @FindBy(className = "chosen-container-left")
    public static WebElement plpResultsHeader;

    @FindBy(className = "results__optionlink--selected")
    public static WebElement plpCurrentResultsTab;

    @FindBy(css = ".results__options.clearfix > div > .top-three-component")
    public static WebElement top3Component;

    @FindBy(css = ".product-list__item-review > div > a.review-rating__read")
    public static WebElement readReviewsPLP;

    @FindBy(className = "product-list__list")
    public static WebElement productListingMobile;

    /**
     * Returns the total number of selected items
     *
     * @return int      the total number of selected items
     */
    public int getTotalSelectedItems() {
        return totalSelectedItems;
    }

    /**
     * Sets the total number of selected items
     *
     * @param totItems the total number of selected items
     */
    public void setTotalSelectedItems(int totItems) {
        totalSelectedItems = totItems;
    }

    //TODO: This does not work with geckodriver

    /**
     * Selects options from fitment search results to add to cart
     *
     * @param option The option in results to pick from (Ex. SETS, FRONT, REAR)
     * @param itemId The itemid to identify which addToCart button to click
     * @param action The action to take in the item added popup
     */
    public void addToCart(String option, String itemId, String action) {
        LOGGER.info("addToCart started");

        boolean itemFound = false;
        driver.waitForElementVisible(addToCart);

        if (!option.equalsIgnoreCase(Constants.NONE)) {
            selectProductListTab(option);
        }

        // Condition check - While item not found on current page, navigate to next page
        // till item is not found & next pagination link is displayed
        while (!itemFound) {
            //TODO: Retest once latest safaridriver or geckodriver is updated & stabilized
            if (!Config.isSafari()) {
                if (Config.isFirefox() || Config.isIe()) {
                    driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
                }
                driver.waitForPageToLoad();
            } else {
                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
            }

            driver.waitForElementVisible(plpResultsRowBy);

            // selecting add to cart item if itemId is contained in text of row
            List<WebElement> rows = webDriver.findElements(plpResultsRowBy);
            List<WebElement> addToCartButtons = webDriver.findElements(addToCartBy);

            int i = 0;
            for (WebElement row : rows) {

                if (Config.isIe() || Config.isSafari())
                    driver.waitForMilliseconds();

                if (row.getText().contains(itemId)) {
                    driver.jsScrollToElement(addToCartButtons.get(i));
                    addToCartButtons.get(i).click();
                    selectAction(action);
                    itemFound = true;
                    break;
                } else {
                    i++;
                }
            }

            //navToDifferentPageOfResults now returns a boolean, indicating whether the next/prev button was found
            if (!itemFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                LOGGER.info("Navigating to next page.");

                if (Config.isFirefox())
                    driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

            } else if (!itemFound) {
                Assert.fail("FAIL: Item - \"" + itemId + "\" NOT found! Unable to add to cart.");
                break;
            }
        }

        LOGGER.info("addToCart completed");
    }

    /**
     * Selects options from fitment search results to add to cart
     *
     * @param option The option in results to pick from (Ex. SETS, FRONT, REAR)
     * @param itemId The itemid to identify which addToCart button to click
     * @param action The action to take in the item added popup
     */
    public void addToCartMobile(String option, String itemId, String action) {
        LOGGER.info("addToCartMobile started");
        boolean itemFound = false;
        By productCodeBy = productPostCodeBy;
        String setsProductCodeXpath = "//form[@action='/cart/addSets']/input[contains(@name, 'productCodePost')]";

        driver.waitForElementVisible(addToCartMobile);

        if (!option.equalsIgnoreCase(Constants.NONE)) {
            selectProductListTab(option);
            if (option.equalsIgnoreCase(SETS)) {
                productCodeBy = By.xpath(setsProductCodeXpath);
            }
        }

        // Condition check - While item not found on current page, navigate to next page
        // till item is not found & next pagination link is displayed
        while (!itemFound) {
            driver.waitForMilliseconds();

            // selecting add to cart item if itemId is contained in text of row
            List<WebElement> rows = webDriver.findElements(productCodeBy);

            for (WebElement row : rows) {
                if (row.getAttribute(Constants.VALUE).contains(itemId)) {
                    WebElement addToCartButton = driver.getParentElement(row).findElement(addToCartMobileBy);
                    driver.jsScrollToElement(addToCartButton);
                    addToCartButton.click();
                    itemFound = true;
                    selectAction(action);
                    break;
                }
            }

            if (!itemFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                LOGGER.info("Navigating to next page.");
            } else if (!itemFound) {
                Assert.fail("FAIL: Item - \"" + itemId + "\" NOT found! Unable to add to cart.");
                break;
            }

        }
        LOGGER.info("addToCartMobile completed");
    }

    //TODO: This does not work with geckodriver

    /**
     * Selects options from fitment search results to add to cart
     *
     * @param option  The option in results to pick from (Ex. SETS, FRONT, REAR)
     * @param inStock Criteria to select an item based on inStock availability set to (True or False)
     * @param action  The action to take in the item added popup
     */
    public void addToCart(String option, Boolean inStock, String action) {
        LOGGER.info("addToCart started");
        boolean itemFound = false;
        WebElement myStoreAvailability;
        List<WebElement> availabilitySections;
        driver.waitForElementVisible(addToCart);

        if (!option.equalsIgnoreCase(Constants.NONE)) {
            selectProductListTab(option);
        }

        // Condition check - While item not found on current page, navigate to next page
        // till item is not found & next pagination link is displayed
        while (!itemFound) {
            //TODO: Retest once latest safaridriver or geckodriver is updated & stabilized
            if (!Config.isSafari()) {
                if (Config.isFirefox() || Config.isIe()) {
                    driver.waitForMilliseconds();
                }
                driver.waitForPageToLoad();
            } else {
                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
            }
            driver.waitForElementVisible(plpResultsRowBy);

            // selecting add to cart item if itemId is contained in text of row

            if (driver.isElementDisplayed(staggeredOptionTabBy)) {
                availabilitySections = webDriver.findElements(staggeredMyStoreMessageBy);
            } else {
                availabilitySections = webDriver.findElements(resultRowAvailabilityBy);
            }
            List<WebElement> addToCartButtons = webDriver.findElements(addToCartBy);

            //TODO: Retest after stable IE runs
            if (Config.isIe())
                driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);

            int i = 0;
            for (WebElement availability : availabilitySections) {

                if (Config.isIe())
                    driver.waitForMilliseconds();

                if (driver.isElementDisplayed(staggeredOptionTabBy)) {
                    myStoreAvailability = availability;
                } else {
                    myStoreAvailability = availability.findElements(resultRowAvailabilityMessageBy).get(0);
                }
                boolean zeroInStock = myStoreAvailability.getText().equals(ConstantsDtc.ZERO_IN_STOCK_AT_MY_STORE);

                // TODO: Needed to include cart button isEnabled due to data issues.  Some cart buttons were disabled.
                // TODO: (cont'd) When the data issues are resolved, we can remove this condition.
                if ((inStock && !zeroInStock || !inStock && zeroInStock) && (addToCartButtons.get(i).isEnabled())) {
                    addToCartButtons.get(i).click();
                    itemFound = true;
                    selectAction(action);
                    break;
                } else {
                    i++;
                }
            }

            //navToDifferentPageOfResults now returns a boolean, indicating whether the next/prev button was found
            if (!itemFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                LOGGER.info("Navigating to next page.");
            } else if (!itemFound) {
                Assert.fail("FAIL: Item with in-stock criteria " + inStock + " NOT found! Unable to add to cart.");
                break;
            }
        }
        LOGGER.info("addToCart completed");
    }

    /**
     * Selects either View Cart or Continue Shopping
     *
     * @param action text for button selection
     */
    public void selectAction(String action) {
        LOGGER.info("selectAction started");
        //Selecting popup action
        driver.waitForElementClickable(viewCart);
        if (action.equals(viewCart.getText())) {
            viewCart.click();
        } else if (action.equals(continueShopping.getText())) {
            continueShopping.click();
        } else {
            closeButton.click();
        }
        LOGGER.info("selectAction completed");
    }

    /**
     * Clicks the Check Availability button for a specific item
     *
     * @param itemCode   Item Code for item to click button for
     */
    public void clickCheckInventory(String itemCode) {
        LOGGER.info("clickCheckInventory started");
        driver.waitForPageToLoad();
        if (driver.isElementDisplayed(checkInventoryBy)) {

            if (Config.isIe() || Config.isFirefox() || Config.isMobile()) {
                driver.waitForMilliseconds();
            }
            WebElement grandParent = driver.getParentElement(
                    driver.getParentElement(
                            driver.getElementWithText(CommonActions.itemIdBy, itemCode)));
            driver.jsScrollToElement(grandParent);
            grandParent.findElement(checkInventoryBy).click();
        } else {
            LOGGER.info("'Check Inventory at nearby stores' link was not present for item # " + itemCode);
        }
        LOGGER.info("clickCheckInventory completed");
    }

    /**
     * Clicks the Check Availability button for the first item that has the link and
     * fails if no items are found
     */
    public void clickCheckInventoryForFirstAvailableItem() {
        LOGGER.info("clickCheckInventoryForFirstAvailableItem started");

        boolean itemFound = false;
        By rowBy = addToCartBy;

        if (Config.isMobile())
            rowBy = addToCartMobileBy;

        // Condition check - While item not found on current page, navigate to next page
        // till item is not found & next pagination link is displayed
        while (!itemFound) {

            driver.waitForPageToLoad();

            List<WebElement> rows = webDriver.findElements(rowBy);

            for (WebElement row : rows) {

                if (Config.isIe())
                    driver.waitForMilliseconds();

                if (row.getText().contains(CHECK_INVENTORY)) {
                    checkInventory.click();
                    itemFound = true;
                    break;
                }
            }

            //navToDifferentPageOfResults returns a boolean, indicating whether the next/prev button was found
            if (!itemFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                LOGGER.info("Navigating to next page.");
            } else if (!itemFound) {
                Assert.fail("FAIL: Item with Check Inventory link NOT found! Unable to open Check Inventory popup.");
                break;
            }
        }
        LOGGER.info("clickCheckInventoryForFirstAvailableItem completed");
    }

    /**
     * Verifies the value is contained within a result row on search results
     *
     * @param results String to verify
     */
    public void assertSearchResults(String results) {
        LOGGER.info("assertSearchResults started");
        if (Config.isMobilePhone()) {
            Assert.assertTrue("FAIL: The current page of search results did NOT contain a single instance " +
                    "of: \"" + results + "\"!", driver.waitForTextPresent(resultsRowMobileBy, results,
                    Constants.DEFAULT_SEC_WAIT));
        } else {
            Assert.assertTrue("FAIL: The current page of search results did NOT contain a single instance " +
                    "of: \"" + results + "\"!", driver.waitForTextPresent(plpResultsRowBy, results,
                    Constants.DEFAULT_SEC_WAIT));
        }
        LOGGER.info("Confirmed that '" + results + "' was listed in the fitment search results.");
        LOGGER.info("assertSearchResults completed");
    }

    /**
     * Cycles through results for unchecked items
     * Checks the checkboxes of each item as well as adds their values to a list
     *
     * @param quantity Number of checkboxes to be selected on page
     * @return HashMap containing values of each selected item
     */
    public HashMap<String, ArrayList<String>> clickCompareCheckboxes(int quantity) {
        LOGGER.info("clickCompareCheckboxes started");
        int i;
        boolean itemsFound = false;
        List<WebElement> rows = new ArrayList<>();

        //TODO: retest when new safaridriver or geckodriver is updated and stabilized
        if (Config.isSafari() || Config.isFirefox() || Config.isIe())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForPageToLoad();
        checkForNoResultsMessage();
        driver.waitForElementVisible(addToCart);

        // check this condition just in case there are already enough selected items when entering the function
        if (totalSelectedItems >= quantity) {
            itemsFound = true;
        }

        // Traverse the pages (clicking next) if necessary to find the specified quantity of valid items for comparison.
        while (!itemsFound) {
            //TODO: Retest once latest safaridriver or geckodriver is updated & stabilized
            if (Config.isSafari() || Config.isFirefox() || Config.isIe()) {
                driver.waitForPageToLoad();
            }

            rows.clear();
            rows = webDriver.findElements(plpResultsRowBy);

            i = 0;
            for (WebElement row : rows) {
                //TODO CCL - needed in cases where we have wheels and tires on PLP; currently wheels cannot be compared
                //TODO CCL (cont) which was breaking test #6789

                webDriver.manage().timeouts().implicitlyWait(Constants.TWO_SEC_WAIT, TimeUnit.SECONDS);
                List<WebElement> compareButtonValueList = row.findElements(compareButtonValueBy);
                driver.resetImplicitWaitToDefault();
                if (compareButtonValueList.size() > 0) {
                    if (row.findElement(compareButtonValueBy).getText().trim().equalsIgnoreCase(compareUpTo3)) {
                        if (!row.findElement(compareCheckboxBy).isSelected()) {

                            //TODO: retest when new safaridriver or geckodriver is updated & stabilized
                            if (Config.isSafari() || Config.isFirefox() || Config.isIe())
                                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

                            //TODO: retest when new safaridriver or geckodriver is updated & stabilized
                            //StaleElementReferenceException thrown with safaridriver for
                            //(cont) CompareProducts > compareProductsFromDifferentResultPages
                            try {
                                //TODO: retest when geckodriver is updated & stabilized
                                if (Config.isFirefox()) {
                                    driver.jsMoveToElementClick(row.findElement(compareCheckboxBy));
                                } else {
                                    driver.waitForPageToLoad();
                                    driver.jsScrollToElement(row.findElement(compareCheckboxBy));
                                    row.findElement(compareCheckboxBy).click();
                                }
                            } catch (StaleElementReferenceException e) {
                                LOGGER.info(Config.getBrowser() + " threw an error: " + e);
                                webDriver.findElements(compareCheckboxBy).get(i).click();
                            } catch (WebDriverException e) {
                                LOGGER.info(Config.getBrowser() + " threw an error: " + e);
                                ((JavascriptExecutor) webDriver).executeScript("arguments[0].checked = true;",
                                        webDriver.findElements(compareCheckboxBy).get(i));
                                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
                            }

                            appendToBrandProductPriceLists(row, i, false);

                            //TODO: retest when new safaridriver or geckodriver is updated & stabilized
                            // These checkbox clicks fail in safaridriver without a waiter
                            if (Config.isSafari() || Config.isFirefox())
                                driver.waitForMilliseconds();

                            totalSelectedItems++;
                            verifyCompareButtonUpdated(row, i);
                        }

                        i++;
                        if (quantity == totalSelectedItems) {
                            LOGGER.info(quantity + " products selected for comparison");
                            itemsFound = true;
                            String productDetails = row.getText().split("#")[1].trim();
                            itemID = productDetails.split("\\r?\\n")[0].trim();
                            LOGGER.info("Storing last compare ItemID is " + itemID);
                            break;
                        }
                    }
                } else {
                    i++;
                }
            }

	        /*
               Click Next Page if less than specified quantity of valid items have been found.
	           navToDifferentPageOfResults returns boolean for Next button exists.  False indicates final page.
	        */
            if (!itemsFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                LOGGER.info("Navigating to next page.");
            } else if (!itemsFound) {
                Assert.fail("FAIL: There were not enough non-special-order items to do a comparison");
                break;
            }

        }

        HashMap<String, ArrayList<String>> values = new HashMap<>();
        values.put(ConstantsDtc.BRAND, brandList);
        values.put(ConstantsDtc.PRICE, priceList);
        values.put(ConstantsDtc.PRODUCT, productList);
        values.put(ConstantsDtc.INVENTORY_MESSAGE, inventoryMessageList);

        LOGGER.info("clickCompareCheckboxes completed");
        return values;
    }

    /**
     * Cycles through results for unchecked items - Mobile
     * Checks the checkboxes of each item as well as adds their values to a list
     *
     * @param quantity Number of checkboxes to be selected on page
     * @return HashMap containing values of each selected item
     */
    public HashMap<String, ArrayList<String>> clickCompareCheckboxesMobile(int quantity) {
        LOGGER.info("clickCompareCheckboxes started");

        //TODO: retest when new safaridriver or geckodriver is updated and stabilized
        if (Config.isSafari() || Config.isFirefox() || Config.isIe())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForPageToLoad();
        checkForNoResultsMessage();
        driver.waitForElementVisible(addToCartMobile);

        int currentPageSelectedItemCount = 0;

        List<WebElement> rows = webDriver.findElements(resultsRowMobileBy);

        for (WebElement row : rows) {
            if (row.findElement(compareCheckboxBy).isSelected()) {
                currentPageSelectedItemCount++;
            }
        }

        if (currentPageSelectedItemCount != 0) {
            totalSelectedItems = currentPageSelectedItemCount;
        }

        int i = 0;
        for (WebElement row : rows) {
            if (!row.findElement(specialOrderAvailabilityMobileBy).getText().contains(SPECIAL_ORDER)) {

                if (!row.findElement(compareCheckboxBy).isSelected()) {
                    try {
                        WebElement checkBox = row.findElement(compareCheckboxBy);
                        driver.jsClick(checkBox);
                    } catch (StaleElementReferenceException e) {
                        LOGGER.info(Config.getBrowser() + " threw an error: " + e);
                        webDriver.findElements(compareCheckboxBy).get(i).click();
                    }

                    appendToBrandProductPriceLists(row, i, true);
                    totalSelectedItems++;
                }
                i++;

                if (i == quantity && totalSelectedItems > 1) {
                    break;
                }

                if (quantity == totalSelectedItems) {
                    break;
                }
            }
        }

        HashMap<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();
        values.put(ConstantsDtc.BRAND, brandList);
        values.put(ConstantsDtc.PRICE, priceList);
        values.put(ConstantsDtc.PRODUCT, productList);

        LOGGER.info("clickCompareCheckboxes completed");
        return values;
    }

    /**
     * Clicks the Compare button on the last checked product
     */
    public void clickCompareProductsButton() {
        LOGGER.info("clickCompareProductsButton started");
        driver.waitForMilliseconds();
        driver.waitForElementVisible(webDriver.findElement(plpResultsRowBy));
        List<WebElement> rows = webDriver.findElements(plpResultsRowBy);

        for (WebElement row : rows) {
            //TODO CCL - needed in cases where we have wheels and tires on PLP; because wheels cannot be compared
            //TODO CCL (cont) they do not have a compare button which breaks our previous assumptions for PLP
            webDriver.manage().timeouts().implicitlyWait(Constants.ZERO, TimeUnit.SECONDS);
            List<WebElement> compareButtonTagList = row.findElements(compareButtonTagBy);
            driver.resetImplicitWaitToDefault();
            if (compareButtonTagList.size() > 0) {
                if (row.findElement(compareButtonTagBy).getText().equalsIgnoreCase(compare)) {
                    WebElement compareBtnEle = row.findElement(compareButtonTagBy);
                    driver.jsScrollToElement(compareBtnEle);
                    driver.jsClick(compareBtnEle);
                    LOGGER.info("Compare Products Button clicked");
                    break;
                }
            }
        }
        LOGGER.info("clickCompareProductsButton completed");
    }

    /**
     * Clicks the Compare button on the last checked product
     */
    public void clickCompareProductsButtonMobile() {
        LOGGER.info("clickCompareProductsButton started");
        driver.waitForMilliseconds();
        driver.waitForElementVisible(webDriver.findElement(resultsRowMobileBy));

        List<WebElement> rows = webDriver.findElements(resultsRowMobileBy);
        for (WebElement row : rows) {
            if (!row.findElement(specialOrderAvailabilityMobileBy).getText().contains(SPECIAL_ORDER_AVAILABILITY)) {
                WebElement compareBtn = row.findElement(compareButtonActiveMobileBy);
                driver.waitForElementClickable(compareBtn);
                driver.jsClick(compareBtn);
                LOGGER.info("Compare Products Button clicked");
                break;
            }
        }
        LOGGER.info("clickCompareProductsButton completed");
    }

    /**
     * @return String of current page URL
     */
    public String getCurrentProductPageUrl() {
        LOGGER.info("getCurrentProductPageUrl returning url = " + webDriver.getCurrentUrl());
        return webDriver.getCurrentUrl();
    }


    /**
     * Extracts total result count from the product list page, then verifies that each page
     * displays 10 results per page within that total, also checking that last page contains the remainder count
     */
    public void validatePagination() {
        LOGGER.info("validatePagination started");
        int total = getTotalCount();
        int totalPages = total / 10;
        int remainder = total % 10;
        int displayCountExpected = 10;
        int displayCount;
        //NOTE: This will work for web or mweb, as all elements exist even if not displayed
        String paginationActiveScript =
                "return document.getElementsByClassName('pagination__link active')[0].textContent == ";

        if ((totalPages == 0) || (totalPages == 1)) {
            LOGGER.info("Either there were no results or only one page, skipping pagination validation.");
        } else {
            //if there is an uneven number, you will end up with an extra page
            if (remainder != 0)
                totalPages++;

            for (int page = 1; page <= totalPages; page++) {

                //waits for next page to load (for slower browsers / mobile)
                driver.pollUntil(paginationActiveScript + Integer.toString(page), Constants.DEFAULT_SEC_WAIT);

                if (Config.isMobile()) {
                    driver.waitForElementVisible(resultsRowMobileBy);
                } else {
                    //Dealing with Intermittent gecko NoSuchElementExceptions
                    try {
                        driver.waitForElementVisible(plpResultsRowBy);
                    } catch (NoSuchElementException e) {
                        driver.waitForMilliseconds();
                        driver.waitForElementVisible(plpResultsRowBy);
                    }
                }

                //last page should include the remainder
                if ((page == totalPages) && (remainder != 0)) {
                    displayCountExpected = remainder;
                }

                if (Config.isMobile()) {
                    displayCount = webDriver.findElements(resultsRowMobileBy).size();
                } else {
                    displayCount = webDriver.findElements(plpResultsRowBy).size();
                }

                if (displayCount == displayCountExpected) {
                    LOGGER.info("Confirmed that display count for page " + page + " was " + displayCountExpected);
                } else {
                    Assert.fail("FAIL: Display count expected was \"" + displayCountExpected
                            + "\" but actual count was \"" + displayCount + "\"!");
                }

                //nextPage button is not present on the last page
                if (page < totalPages) {
                    commonActions.navToDifferentPageOfResults(Constants.NEXT);
                }
            }
        }
        LOGGER.info("validatePagination completed");
    }

    /**
     * @return total : integer count of pages of results displayed
     */
    private int getTotalCount() {
        LOGGER.info("getTotalCount started");
        String totalCountString;

        if (Config.isMobilePhone()) {
            driver.waitForElementVisible(totalResults);
            totalCountString = totalResults.getText();
        } else {
            totalCountString = commonActions.getPageHeader();
        }

        int total = -1;
        try {
            int iEnd = totalCountString.indexOf(" ");
            total = Integer.parseInt(totalCountString.substring(0, iEnd));
        } catch (Exception e) {
            Assert.fail("FAIL: Unable to extract total count!");
        }
        LOGGER.info("getTotalCount ended");
        return total;
    }

    /**
     * Sets the sort dropdown box displays passed in value
     *
     * @param value Value to set in the dropbox value
     */
    public void setSortByValue(String value) {
        LOGGER.info("setSortByValue started");
        WebElement dropDown;
        checkForNoResultsMessage();
        if (Config.isMobile()) {
            dropDown = webDriver.findElement(CommonActions.chosenSingleMobileBy);
            dropDown.click();
            driver.clickElementWithText(CommonActions.optionTagBy, value, true);
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            driver.waitForPageToLoad();
        } else {
            dropDown = webDriver.findElement(CommonActions.chosenSingleBy);
            dropDown.click();

            //If no underlying "class" tags are found, try finding "option" tags
            List<WebElement> options = webDriver.findElements(dropDownOptionBy);
            if (options.size() == 0) {
                options = dropDown.findElements(CommonActions.optionTagBy);
            }
            for (WebElement option : options) {
                if (option.getText().toLowerCase().contains(value.toLowerCase())) {
                    driver.jsScrollToElement(option);
                    option.click();
                    break;
                }
            }
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            driver.waitForPageToLoad();
        }
        LOGGER.info("setSortByValue completed");
    }

    /**
     * Verifies the sort dropdown box displays passed in value
     *
     * @param value Value to verify in sortBy
     */
    public void verifySortByValue(String value) {
        LOGGER.info("verifySortByValue started");
        driver.waitForPageToLoad();
        checkForNoResultsMessage();
        if (Config.isMobile()) {
            WebElement sortValue = driver.getElementWithText(CommonActions.optionTagBy, value);
            if (!sortValue.isDisplayed()) {
                Assert.fail("FAIL: Product page sort by dropdown was NOT set to \"" + value + "\"!");
            }
        } else {
            String spanText;
            if (!Config.isSafari()) {
                String script = "return document.getElementById('sortOptions2_chosen').innerHTML;";
                JavascriptExecutor jse = (JavascriptExecutor) webDriver;
                spanText = (String) jse.executeScript(script);
            } else {
                spanText = webDriver.findElement(sortByCurrentSelectedValueBy).getText();
            }
            Assert.assertTrue("FAIL: Product page sort by dropdown was NOT set to \"" + value
                            + "\"! Actual value was \"" + spanText.trim() + "\"!",
                    spanText.trim().toLowerCase().contains(value.toLowerCase()));
        }
        LOGGER.info("Confirmed that the product page sort by dropdown was set to \"" + value + "\".");
        LOGGER.info("verifySortByValue completed");
    }

    /**
     * Verifies how many filters are checked on the product results page
     *
     * @param number Expected number of filters to be checked
     */
    public void verifyNumberOfSearchRefinementFilters(String number) {
        LOGGER.info("verifyNumberOfSearchRefinementFilters started");
        int numberOfFilters = webDriver.findElements(searchRefinementFilterBy).size();
        Assert.assertEquals("FAIL: The number of search refinement filters was NOT \"" + number + "\"!",
                numberOfFilters, Integer.parseInt(number));
        LOGGER.info("Confirmed that the number of search refinement filters was set to \"" + number + "\".");
        LOGGER.info("verifyNumberOfSearchRefinementFilters completed");
    }

    /***
     * Verifies the Refinements section contains specified value(s)
     *
     * @param multiple "single" or "multiple" values to verify
     * @param values String representing the value(s) to be verified in the refinement section. Multiple values
     *                need to be separated by a comma
     */
    public void verifySearchRefinementFilterValues(String multiple, String values) {
        LOGGER.info("verifySearchRefinementFilterValues started & is looking for value(s):\n\t\t" + values);
        List<String> valuesToVerifyList = new LinkedList<>(Arrays.asList(values.split(",")));
        int valuesToFind = valuesToVerifyList.size();
        int foundValuesCount = 0;
        boolean allValuesFound = false;
        List<String> valuesFound = new ArrayList<>();
        List<WebElement> searchRefinementFilterElements;

        driver.waitForPageToLoad();

        if (Config.isMobile()) {
            driver.jsScrollToElement(filterBtn);
            filterBtn.click();
        }

        if (multiple.equalsIgnoreCase(MULTIPLE))
            values = values.replaceAll(",\\s+", ",");

        if (Config.isFirefox())
            driver.waitForMilliseconds();

        driver.waitForElementVisible(searchFilterSectionBy);
        searchRefinementFilterElements = driver.getDisplayedElementsList(searchRefinementFilterBy);

        for (String value : valuesToVerifyList) {
            for (WebElement searchRefinementFilterElement : searchRefinementFilterElements) {
                if (searchRefinementFilterElement.getText().toLowerCase().contains(value.toLowerCase())) {
                    foundValuesCount++;
                    valuesFound.add(value);
                    break;
                }

                if (searchRefinementFilterElement.getText().toLowerCase().contains(PRICE_RANGE.toLowerCase())) {
                    String approxPriceRange = "$" + minValueApproximate + " - $" + maxValueApproximate;
                    if (searchRefinementFilterElement.getText().toLowerCase().contains(approxPriceRange)) {
                        foundValuesCount++;
                        valuesFound.add(approxPriceRange);
                        LOGGER.info("An approximate price range (instead of the exact: \"" + value + "\") was"
                                + " used to validate the \"Price Range\" refinement filter was set!");
                        break;
                    }
                }

                //TODO CCL - remove when "Price Range" filter works as intended for Safari
                if (Config.isSafari() && value.contains("$")) {
                    foundValuesCount++;
                    valuesFound.add("Skipped \"Price Range\" filter validation for Safari!");
                    break;
                }
            }
        }

        if (foundValuesCount == valuesToFind) {
            allValuesFound = true;
        } else {
            valuesToVerifyList.removeAll(valuesFound);
        }

        Assert.assertTrue("FAIL: The following value(s) was/were NOT located as search refinement "
                + "filter(s): \"" + valuesToVerifyList + "\"!", allValuesFound);

        if (Config.isMobile()) {
            driver.jsScrollToElement(mobileFilterCloseBtn);
            mobileFilterCloseBtn.click();
        }
        LOGGER.info("Confirmed the expected value(s): \n\t\t" + values
                + "\n\t was/were displayed as search refinement filter(s).");
    }

    /**
     * Removes the first selected item from Brand, Product and Price lists
     * Used when removing the first item from a compare products results page
     */
    public void removeFirstSelectedItem() {
        LOGGER.info("removeFirstSelectedItem started");
        brandList.remove(brandList.size() - 1);
        productList.remove(productList.size() - 1);
        priceList.remove(priceList.size() - 1);
        // decrement the total selected items
        totalSelectedItems--;
        LOGGER.info("removeFirstSelectedItem completed");
    }

    /**
     * Extracts all prices on page and verifies they are in ascending/descending order
     *
     * @param order String (Ascending or Descending)
     */
    public void verifyPricesInOrder(String order) {
        LOGGER.info("verifyPricesInOrder started");
        double low = ZERO;
        double high = ONE_HUNDRED_THOUSAND;

        driver.waitForMilliseconds();
        if (Config.isFirefox())
            driver.waitForPageToLoad();

        for (int i = 0; i < 5; i++) {
            if (!driver.isElementDisplayed(productPriceBy)) {
                if (commonActions.navToDifferentPageOfResults(Constants.NEXT))
                    LOGGER.info("Navigating to next page of results as the current page is not displaying prices "
                            + "for products");
            } else {
                break;
            }

            if (i == 4)
                Assert.fail("FAIL: No prices were displayed for products on the first 5 pages of results!");
        }

        checkForNoResultsMessage();
        List<WebElement> prices = webDriver.findElements(productPriceBy);
        for (WebElement price : prices) {
            String cost = price.getText().substring(1);
            cost = cost.split(E)[0];
            cost = cost.replace(",", "");
            double value = Double.valueOf(cost);
            if (order.equalsIgnoreCase(ASCENDING)) {
                if (value < low)
                    Assert.fail("FAIL: Price list NOT sorted in ascending order at \"" + price + "\"!");
                low = value;
            } else if (order.equalsIgnoreCase(DESCENDING)) {
                if (value > high)
                    Assert.fail("FAIL: Price list NOT sorted in descending order at \"" + price + "\"!");
                high = value;
            } else {
                Assert.fail("FAIL: Order parameter should either be Ascending or Descending but was :\""
                        + order + "\"!");
            }
        }
        LOGGER.info("verifyPricesInOrder successful");
    }

    /***
     * Expands specified filter section.
     * @param filterSection String representing the name of the filter section to expand
     */
    public void expandFilterSection(String filterSection) {
        LOGGER.info("expandFilterSection started for section: \"" + filterSection + "\"");

        if (Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForElementVisible(searchFilterSectionTitleBy);

        List<WebElement> searchFilterSectionsList = webDriver.findElements(searchFilterSectionTitleBy);

        for (WebElement searchFilterSection : searchFilterSectionsList) {
            if (searchFilterSection.getText().equalsIgnoreCase(filterSection)) {
                driver.jsScrollToElement(searchFilterSection);
                searchFilterSection.click();
                break;
            }
        }
        LOGGER.info("expandFilterSection completed for section: \"" + filterSection + "\"");
    }

    /***
     * Selects refinement filter options from a specified filter section.
     * NOTE: If the section contains a "Show More" link, it will be clicked before every filter option is selected
     * to avoid issue if an option to be selected is being hidden.
     * @param multiple "single" or "multiple" values to verify
     * @param filterSection String representing the name of the filter section from which to select options
     * @param options String representing the option(s) to be selected from the filter section. Multiple values
     *                need to be separated by a comma
     */
    public void selectFromFilterSection(String multiple, String filterSection, String options) {
        LOGGER.info("selectFromFilterSection started for section: \n\t\t" + filterSection
                + "\n With option(s): \n\t\t" + options);
        driver.waitForPageToLoad();

        if (Config.isFirefox() || Config.isSafari() || Config.isIe())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        checkForNoResultsMessage();

        List<String> optionsToSelectList = Arrays.asList(options);

        if (multiple.equalsIgnoreCase(MULTIPLE))
            optionsToSelectList = Arrays.asList(options.split(","));

        if (Config.isSafari()) {
            if (!showFilterBtnClicked) {
                if (driver.isElementDisplayed(showFilterBtn)) {
                    showFilterBtn.click();
                    showFilterBtnClicked = true;
                }
            }
        }

        driver.waitForElementVisible(searchFilterSectionBy);

        for (String optionToSelect : optionsToSelectList) {
            List<WebElement> possibleFilterSectionEleList = driver.getElementsWithText(searchFilterSectionBy,
                    filterSection);
            WebElement filterSectionEle = driver.getDisplayedElement(possibleFilterSectionEleList, Constants.ZERO);
            WebElement filterSectionOpenIconEle = filterSectionEle.findElement(openFilterSectionIconBy);

            if (!filterSectionOpenIconEle.getAttribute(Constants.CLASSNAME).contains(iconOpenClass))
                driver.jsMoveToElementClick(filterSectionOpenIconEle);

            selectShowMoreForFilterSection(filterSectionEle);

            List<WebElement> possibleFilterOptionEleList = driver.getElementsWithText(filterOptionLabelBy,
                    optionToSelect.trim());
            WebElement filterOptionEle = driver.getDisplayedElement(possibleFilterOptionEleList, Constants.ZERO);

            if (Config.isSafari())
                filterOptionEle = filterOptionEle.findElement(filterOptionCheckboxBy);

            driver.jsScrollToElement(filterOptionEle);
            filterOptionEle.click();
            driver.waitForPageToLoad();
        }
        LOGGER.info("selectFromFilterSection completed for section: \n\t\t" + filterSection
                + "\n With option(s): \n\t\t" + options);
    }

    /**
     * Selects / clicks the "Show More" link for a filter section if the link is present
     *
     * @param filterSectionEle WebElement of the filter section to be checked for a "Show More" link
     */
    private void selectShowMoreForFilterSection(WebElement filterSectionEle) {
        LOGGER.info("selectShowMoreForFilterSection started");
        webDriver.manage().timeouts().implicitlyWait(Constants.ZERO, TimeUnit.SECONDS);
        List<WebElement> sectionLinkList = filterSectionEle.findElements(CommonActions.dtLinkBy);
        driver.resetImplicitWaitToDefault();

        if (sectionLinkList.size() > 0) {
            for (WebElement sectionLink : sectionLinkList) {
                if (sectionLink.getText().equalsIgnoreCase(SHOW_MORE)) {
                    driver.jsScrollToElement(sectionLink);
                    sectionLink.click();
                    driver.waitForMilliseconds();
                }
            }
        }
        LOGGER.info("selectShowMoreForFilterSection completed");
    }

    /***
     * Selects refinement filter options from a specified mobile filter section.
     * NOTE: If the section contains a "Show More" link, it will be clicked before every filter option is selected
     * to avoid issue if an option to be selected is being hidden.
     * @param multiple "single" or "multiple" values to verify
     * @param filterSection String representing the name of the filter section from which to select options
     * @param options String representing the option(s) to be selected from the filter section. Multiple values
     *                need to be separated by a comma
     */
    public void selectFromFilterSectionMobile(String multiple, String filterSection, String options) {
        LOGGER.info("selectFromFilterSectionMobile started for section: \n\t\t" + filterSection
                + "\n With option(s): \n\t\t" + options);

        List<String> optionsToSelectList = Collections.singletonList(options);

        if (multiple.equalsIgnoreCase(MULTIPLE))
            optionsToSelectList = Arrays.asList(options.split(","));

        driver.waitForElementClickable(filterBtn);
        filterBtn.click();
        driver.waitForElementClickable(clearAllFiltersLinkMobile);

        for (String optionToSelect : optionsToSelectList) {

            driver.waitForElementVisible(searchFilterSectionBy);
            List<WebElement> searchFilterSectionsList = webDriver.findElements(searchFilterSectionBy);
            By byShowLessLinkForSection = By.xpath(String.format(showLessLinkForSectionXpathMobile, filterSection));

            for (WebElement searchFilterSection : searchFilterSectionsList) {
                driver.waitForElementVisible(searchFilterSection);
                if (searchFilterSection.getText().toLowerCase().contains(filterSection.toLowerCase())) {
                    searchFilterSection.findElement(searchFilterSectionToggleBy).click();

                    if (driver.isElementDisplayed(showMoreLink)) {
                        showMoreLink.click();
                        driver.waitForElementClickable(byShowLessLinkForSection);
                    }
                    driver.clickElementWithText(filterOptionBy, optionToSelect.trim());
                    break;
                }
            }

        }

        driver.jsScrollToElement(mobileFilterCloseBtn);
        mobileFilterCloseBtn.click();

        LOGGER.info("selectFromFilterSectionMobile completed for section: \n\t\t" + filterSection
                + "\n With option(s): \n\t\t" + options);
    }

    /***
     * Clicks the "Clear All" link - removes all currently active search refinement filters
     */
    public void clearAllSearchFilters() {
        LOGGER.info("clearAllSearchFilters started");
        if (Config.isMobile()) {
            driver.jsScrollToElement(filterBtn);
            filterBtn.click();

            driver.waitForElementClickable(clearAllFiltersLinkMobile);
            clearAllFiltersLinkMobile.click();
        } else {
            driver.waitForElementVisible(searchFilterSectionBy);
            int appliedFiltersStartCount;
            List<WebElement> currentAppliedFiltersList = driver.getDisplayedElementsList(searchRefinementFilterBy);
            appliedFiltersStartCount = currentAppliedFiltersList.size();

            for (int i = 0; i < appliedFiltersStartCount; i++) {
                currentAppliedFiltersList.clear();
                currentAppliedFiltersList = driver.getDisplayedElementsList(searchRefinementFilterBy);
                currentAppliedFiltersList.get(0).click();
                driver.waitForPageToLoad();
                driver.waitForElementVisible(searchFilterSectionBy);
            }
        }
        LOGGER.info("clearAllSearchFilters completed");
    }

    /***
     * Verifies no search refinement filters are currently active/displayed on the product results
     */
    public void verifyNoSearchRefinementsAreApplied() {
        LOGGER.info("verifyNoSearchRefinementsAreApplied started");
        Assert.assertTrue("FAIL: A search refinement filter was still being applied to the product results!",
                !driver.isElementDisplayed(searchRefinementFilterBy, Constants.TWO_SEC_WAIT));
        LOGGER.info("verifyNoSearchRefinementsAreApplied completed - " +
                "No refinement filters were active on the product results");
    }

    /***
     * Verifies the tire sizes appearing on the Product List Page match the expected value(s). Work with either one or
     * two tire sizes. For two tire sizes, method will validate that the correct tire sizes appear for each of the
     * potential tabs: SET, FRONT, REAR.
     * @param tireSizes String representing the tire size(s) to validate. Can handle up to 2 tire sizes, specified by a
     *                  comma between the sizes
     */
    public void verifyProductsMatchTireSize(String tireSizes) {
        LOGGER.info("verifyProductsMatchTireSize started with tire size(s): " + tireSizes);
        List<String> tireSizesList = new ArrayList<String>();

        Matcher m = Pattern.compile("[0-9]{3}\\s[\\\\/][0-9]{2}\\sR[0-9]{2}").matcher(tireSizes);

        while (m.find()) {
            tireSizesList.add(m.group());
        }

        checkForNoResultsMessage();

        if (tireSizesList.size() == 2) {
            LOGGER.info("Verifying tire sizes match the values on the SET, FRONT, and REAR tabs");

            //Validate SETS tab
            List<WebElement> setTabResultRowsList = webDriver.findElements(plpResultsRowBy);
            for (WebElement setTabResultRow : setTabResultRowsList) {
                Assert.assertTrue("A row on the SET tab does not contain a tire size matching either "
                                + tireSizesList.get(0) + " or " + tireSizesList.get(1),
                        setTabResultRow.getText().toLowerCase().contains(
                                tireSizesList.get(0).toLowerCase())
                                || setTabResultRow.getText().toLowerCase().contains(
                                tireSizesList.get(1).toLowerCase()));
            }

            //Validate FRONT tab
            driver.clickElementByPartialText(FRONT);
            checkForNoResultsMessage();
            verifyFirstPageOfResultsContainExpected(tireSizesList.get(0));
            LOGGER.info("Front tire size: " + tireSizesList.get(0)
                    + " matched the values displayed on the FRONT tab");

            //Validate REAR tab
            driver.clickElementByPartialText(REAR);
            checkForNoResultsMessage();
            verifyFirstPageOfResultsContainExpected(tireSizesList.get(1));
            LOGGER.info("Rear tire size: " + tireSizesList.get(1)
                    + " matched the values displayed on the REAR tab");

        } else if (tireSizesList.size() == 1) {
            verifyFirstPageOfResultsContainExpected(tireSizesList.get(0));
            LOGGER.info("Tire size: " + tireSizesList.get(0)
                    + " matched the values on the Product List Page search results");
        } else {
            Assert.fail("Fail - either NO tire sizes OR more than 2 tire sizes were passed into this method!");
        }
        LOGGER.info("verifyProductsMatchTireSize completed successfully with tire size(s): " + tireSizes);
    }

    /***
     * Checks for the "No Results found for ..." message on the Product List Page.
     */
    private void checkForNoResultsMessage() {
        LOGGER.info("checkForNoResultsMessage started");
        Assert.assertFalse("Fail - The \"No product results found...\" message was displayed!",
                driver.isElementDisplayed(noResultsInfoMessage, Constants.ONE_SEC_WAIT));
        LOGGER.info
                ("checkForNoResultsMessage completed - \"No product results found\" message was NOT displayed");
    }

    /**
     * Verifies each result row on the current search expectedText page contains the expected value
     *
     * @param expectedText String to verify
     */
    private void verifyFirstPageOfResultsContainExpected(String expectedText) {
        LOGGER.info("verifyFirstPageOfResultsContainExpected started");
        checkForNoResultsMessage();

        List<WebElement> searchResultRowsList = webDriver.findElements(plpResultsRowBy);
        List<Integer> rowsWithoutDataList = new ArrayList<>();

        for (int i = 0; i < searchResultRowsList.size(); i++) {
            String searchResultRowText = searchResultRowsList.get(i).getText();

            if (Config.isSafari())
                searchResultRowText = searchResultRowText.trim().replaceAll(" +", " ");

            if (!searchResultRowText.toLowerCase().contains(expectedText.toLowerCase()))
                rowsWithoutDataList.add(i + 1);
        }

        Assert.assertTrue("Search result row #'s" + rowsWithoutDataList
                + " did not contain: \"" + expectedText + "\"", rowsWithoutDataList.size() == 0);
        LOGGER.info("verifyFirstPageOfResultsContainExpected completed - all result rows on the current page"
                + " contained " + expectedText);
    }

    /**
     * Currently selects the first image on PLP
     * <p>
     * Wanted to keep this agnostic to avoid dtc.data dependency issues
     */
    public void selectFirstProductByImage() {
        LOGGER.info("selectProductByImage started");
        if (Config.isSafari() || Config.isIphone())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        List<WebElement> imageList = webDriver.findElements(productImageBy);
        WebElement productImage = driver.getDisplayedElement(imageList, Constants.ZERO);
        driver.waitForElementVisible(productImage);
        productImage.click();
        LOGGER.info("selectProductByImage completed");
    }

    /**
     * Verifies the Quick Filter Checkbox is not selected by default
     *
     * @param checkBoxLabel String to verify
     */
    public void assertCheckboxDeselected(String checkBoxLabel) {
        LOGGER.info("assertCheckboxDeselected started");
        driver.waitForPageToLoad();

        if (Config.isMobile() && driver.isElementDisplayed(filterBtn) && !driver.isElementDisplayed(CommonActions.modalClose)) {
            filterBtn.click();
        }
        boolean isFilterChecked = driver.getElementWithText(filterOptionBy, checkBoxLabel)
                .getAttribute(Constants.CLASS).contains(Constants.CHECKED);
        Assert.assertFalse("FAIL : " + checkBoxLabel + "filter checkbox is selected by default:",
                isFilterChecked);

        if (Config.isMobile() && driver.isElementDisplayed(CommonActions.modalClose)) {
            commonActions.closeModalWindow();
        }
        LOGGER.info("assertCheckboxDeselected completed");
    }

    /**
     * Clicks the specified checkbox by its label text e.g. "Wheels"
     *
     * @param checkboxLabelText String of the checkbox label
     */
    public void clickCheckboxByLabel(String checkboxLabelText) {
        LOGGER.info("clickCheckboxByLabel started for label text: '" + checkboxLabelText + "'");

        if (Config.isSafari()) {
            if (driver.isElementDisplayed(showFilterBtn)) {
                showFilterBtn.click();
            }
        }

        WebElement checkboxEle = driver.getElementWithText(filterOptionBy, checkboxLabelText);
        driver.waitForElementVisible(checkboxEle);
        checkboxEle.click();
        if (Config.isFirefox())
            driver.waitForMilliseconds();
        LOGGER.info("clickCheckboxByLabel completed for label text: '" + checkboxLabelText + "'");
    }

    /**
     * Clicks the specified filter checkbox to select or deselect it. IF method is called to deselect a filter
     * it checks to make sure the checkbox has already been selected
     *
     * @param checkboxLabel String of checkbox label
     * @param deselect      True to deselect a filter, False to select a filter
     */
    public void clickPlpCheckboxToSelectDeselectFilter(String checkboxLabel, boolean deselect) {
        LOGGER.info("clickPlpCheckboxToSelectDeselectFilter started");
        driver.waitForPageToLoad();

        if (Config.isMobile() && driver.isElementDisplayed(filterBtn) && !driver.isElementDisplayed(CommonActions.modalClose)) {
            filterBtn.click();
        }
        WebElement filterEle = driver.getElementWithText(filterOptionBy, checkboxLabel);
        driver.jsScrollToElement(filterEle);

        if (deselect)
            verifyPlpCheckboxFilterSelected(checkboxLabel);

        filterEle.click();

        if (Config.isMobile() && driver.isElementDisplayed(CommonActions.modalClose)) {
            commonActions.closeModalWindow();
        }
        LOGGER.info("clickPlpCheckboxToSelectDeselectFilter completed");
    }

    /**
     * Verifies the checkbox with specified label has been selected
     *
     * @param checkboxLabel String of the checkbox label
     */
    public void verifyPlpCheckboxFilterSelected(String checkboxLabel) {
        LOGGER.info("verifyPlpCheckboxFilterSelected started");
        driver.waitForPageToLoad();
        WebElement filterLabelEle = driver.getElementWithText(filterOptionBy, checkboxLabel);
        WebElement filterCheckboxEle = filterLabelEle.findElement(inputTagBy);
        commonActions.verifyCheckboxSelected(filterCheckboxEle, checkboxLabel);
        LOGGER.info("verifyPlpCheckboxFilterSelected completed");
    }

    /**
     * Asserts total results message for mobile validation, as normal "Results for Wheels/Tires"
     * message does not display in search results for mobile
     */
    public void assertResultsMessage() {
        LOGGER.info("assertResultsMessage started");
        driver.waitForPageToLoad();
        int time = Constants.DEFAULT_SEC_WAIT;
        boolean foundHeader = driver.waitForTextPresent(resultsMessageBy, TOTAL_RESULTS, time);
        Assert.assertTrue("FAIL: Results message \"" + TOTAL_RESULTS + "\" was NOT displayed in \"" + time
                + "\" seconds!", foundHeader);
        LOGGER.info("Confirmed that the results message was displayed.");
        LOGGER.info("assertResultsMessage completed");
    }

    /***
     * Appends the text values of brand, product and price (index used for Safari issues w/ Stale Element Exceptions)
     * by objects to the respective lists.
     * @param row - WebElement; row element containing the values to retrieve and append to the brand, product, and
     *            price lists
     * @param index - Int; used in Safari to by pass Stale Element Exception if thrown.
     * @param isMobile - Boolean; decides which by objects (web OR mobile) are used
     */
    private void appendToBrandProductPriceLists(WebElement row, int index, Boolean isMobile) {
        By brandBy;
        By productBy;
        By priceBy;
        By inventoryMessageStoreBy;

        if (isMobile) {
            brandBy = brandNameMobileBy;
            productBy = productNameMobileBy;
            priceBy = productPriceMobileBy;
            inventoryMessageStoreBy = CommonActions.inventoryMessageMyStoreBy;
        } else {
            brandBy = brandNameBy;
            productBy = productNameBy;
            priceBy = productPriceBy;
            inventoryMessageStoreBy = CommonActions.inventoryMessageBy;
        }

        //TODO: retest when new safaridriver is stable
        //StaleElementReferenceException thrown with safaridriver for
        // CompareProducts > compareProductsFromDifferentResultPages
        try {
            brandList.add(row.findElement(brandBy).getText());
            productList.add(row.findElement(productBy).getText());
            inventoryMessageList.add(row.findElement(inventoryMessageStoreBy).getText());
            try {
                priceList.add(row.findElement(priceBy).getText());
            } catch (Exception e) {
                try {
                    priceList.add(row.findElement(productMapPriceBy).getText());
                } catch (Exception ex) {
                    priceList.add(ConstantsDtc.PRICE_NOT_DISPLAYED);
                    LOGGER.info("Price is not logged in product list page.");
                }
            }
        } catch (StaleElementReferenceException e) {
            LOGGER.info(Config.getBrowser() + " threw an error: " + e);
            brandList.add(webDriver.findElements(brandBy).get(index).getText());
            productList.add(webDriver.findElements(productBy).get(index).getText());
            inventoryMessageList.add(webDriver.findElements(inventoryMessageStoreBy).get(index).getText());
            try {
                priceList.add(webDriver.findElements(priceBy).get(index).getText());
            } catch (Exception er) {
                try {
                    priceList.add(row.findElements(productMapPriceBy).get(index).getText());
                } catch (Exception er1) {
                    priceList.add(ConstantsDtc.PRICE_NOT_DISPLAYED);
                    LOGGER.info("Price is not logged in product list page.");
                }
            }
        }
    }

    /***
     * Verifies the "Compare" button updates appropriately when the containing row / item is selected for comparison.
     * @param row - WebElement; row that should have been selected for comparison and contains the "Compare" button
     * @param index - Int; used in Safari to by pass Stale Element Exception if thrown.
     */
    private void verifyCompareButtonUpdated(WebElement row, int index) {
        LOGGER.info("verifyCompareButtonUpdated started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: Attempting to verify an update to the compare button of a row that has " +
                "NOT been selected", row.findElement(compareCheckboxBy).isSelected());
        String buttonText;

        driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);

        if (totalSelectedItems == 1) {
            try {
                buttonText = row.findElement(compareButtonValueBy).getText().trim();
                Assert.assertTrue("FAIL: \"Compare\" button for this row (and the only selected item) " +
                                "should read: \"" + compareUpTo3 + "\" but was actually: \"" + buttonText + "\"",
                        buttonText.equalsIgnoreCase(compareUpTo3));
            } catch (StaleElementReferenceException stale) {
                LOGGER.info(Config.getBrowser() + " threw an error: " + stale);
                buttonText = webDriver.findElements(compareButtonValueBy).get(index).getText().trim();
                Assert.assertTrue("FAIL: \"Compare\" button for this row (and the only selected item) " +
                                "should read: \"" + compareUpTo3 + "\" but was actually: \"" + buttonText + "\"",
                        buttonText.equalsIgnoreCase(compareUpTo3));
            }
        } else if (totalSelectedItems == 2 || totalSelectedItems == 3) {
            try {
                buttonText = row.findElement(compareButtonTagBy).getText().trim();
                Assert.assertFalse("FAIL: \"Compare\" button text still displays: \"" + compareUpTo3
                                + "\" with multiple items selected. Expected text: \"" + compare + "\"",
                        row.findElement(compareButtonValueBy).isDisplayed());
                Assert.assertTrue("FAIL: \"Compare\" button for this row (one of multiple selected items)"
                                + "should read: \"" + compare + "\" but was actually: \"" + buttonText + "\"",
                        buttonText.equalsIgnoreCase(compare));
            } catch (StaleElementReferenceException stale) {
                LOGGER.info(Config.getBrowser() + " threw an error: " + stale);
                buttonText = webDriver.findElements(compareButtonTagBy).get(index).getText().trim();
                Assert.assertFalse("FAIL: \"Compare\" button text still displays: \"" + compareUpTo3
                                + "\" with multiple items selected. Expected text: \"" + compare + "\"",
                        webDriver.findElements(compareButtonValueBy).get(index).isDisplayed());
                Assert.assertTrue("FAIL: \"Compare\" button for this row (one of multiple selected items)"
                                + "should read: \"" + compare + "\" but was actually: \"" + buttonText + "\"",
                        buttonText.equalsIgnoreCase(compare));
            }
        } else {
            Assert.fail("FAIL: Between 1 and 3 items can be compared. The total selected item count of \""
                    + totalSelectedItems + "\" is outside of those bounds");
        }
        LOGGER.info("verifyCompareButtonUpdated completed successfully");
    }

    /**
     * Verifies that the expected options are contained in the Sort By dropdown
     */
    public void verifySortByOptions() {
        LOGGER.info("verifySortByOptions started");
        WebElement dropDown;
        if (Config.isMobile()) {
            dropDown = webDriver.findElement(CommonActions.chosenSingleMobileBy);
            dropDown.click();
            for (String option : sortOptionsList) {
                Assert.assertTrue("FAIL: Excepted option: \""
                        + option + "\" was not in the \"Sort By\" dropdown", dropDown.getText().contains(option));
            }
            dropDown.sendKeys(Keys.TAB);
        } else {
            dropDown = webDriver.findElement(CommonActions.chosenSingleBy);
            dropDown.click();
            dropDown = webDriver.findElement(CommonActions.chosenSingleDropBy);
            for (String option : sortOptionsList) {
                Assert.assertTrue("FAIL: Excepted option: \""
                        + option + "\" was not in the \"Sort By\" dropdown", dropDown.getText().contains(option));
            }
            dropDown.click();
        }
        LOGGER.info("verifySortByOptions completed");
    }

    /***
     * Verifies the specified section of the PLP UI is displayed to the user
     * @param sectionToVerify Section of the UI on PLP to verify e.g. basic controls, banner without a vehicle,
     *                       filter / sorting, OR pagination
     */
    public void verifyPlpUiSection(String sectionToVerify) {
        LOGGER.info("verifyPlpUiSection started for section: \"" + sectionToVerify + "\"");
        String BASIC_CONTROLS = "basic controls";
        String BANNER_WITHOUT_VEHICLE = "banner without vehicle";
        String FILTER_SORTING = "filter / sorting";
        String PAGINATION = "pagination";

        driver.waitForPageToLoad();
        checkForNoResultsMessage();

        if (sectionToVerify.equalsIgnoreCase(BASIC_CONTROLS)) {
            List<By> controlsByList = new ArrayList<>();

            if (Config.isMobile()) {
                controlsByList.add(addToCartMobileBy);
            } else {
                controlsByList.add(addToCartBy);
            }
            controlsByList.add(compareButtonTagBy);
            controlsByList.add(compareCheckboxBy);

            for (By control : controlsByList) {
                List<WebElement> controlsDisplayList;
                controlsDisplayList = webDriver.findElements(control);
                Assert.assertTrue("FAIL: At least one of the basic PLP controls "
                                + "(Add to Cart, Compare checkbox, Compare button) was NOT displayed!",
                        controlsDisplayList.size() > 0);
            }
        } else if (sectionToVerify.equalsIgnoreCase(BANNER_WITHOUT_VEHICLE)) {
            commonActions.assertBannerColor(ConstantsDtc.PLP, Constants.YELLOW);
            commonActions.assertResultsMessageContains(ConstantsDtc.PLP, unsureItemWillFitMsg);
            commonActions.assertResultsMessageContains(ConstantsDtc.PLP, tellUsAboutVehicleMsg);
        } else if (sectionToVerify.equalsIgnoreCase(FILTER_SORTING)) {
            verifySortByCurrentValue(RELEVANCE);
            verifySortByOptions();
        } else if (sectionToVerify.equalsIgnoreCase(PAGINATION)) {
            validatePagination();
        } else {
            Assert.fail("FAIL: unrecognized PLP aspect to verify - \"" + sectionToVerify
                    + "\"! Please check for typos, or update verification method");
        }
    }

    /**
     * Asserts the text passed in appears in the product list details
     *
     * @param text Text to verify in product list details
     */
    public void assertTextInProductListDetails(String text) {
        LOGGER.info("assertTextInProductListDetails started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(productListDetails);
        Assert.assertTrue("FAIL: The expected text: \"" + text
                        + "\" does NOT match the actual text: \"" + productListDetails.getText() + "\"!",
                productListDetails.getText().contains(text));
        LOGGER.info("Confirmed that \"" + text + "\" was listed in the Product List Page.");
        LOGGER.info("assertTextInProductListDetails completed");
    }

    /***
     * Verifies the current product list items are sorted properly by name
     * @param order - String representing the sort order expected (ASCENDING or DESCENDING)
     */
    public void verifyNamesInOrder(String order) {
        LOGGER.info("verifyNamesInOrder started");
        List<WebElement> nameEleList;
        List<String> nameStringList = new ArrayList<>();
        By productNameBy;

        driver.waitForPageToLoad();
        checkForNoResultsMessage();

        if (Config.isMobile()) {
            productNameBy = productNameMobileBy;
        } else {
            productNameBy = ProductListPage.productNameBy;
        }

        driver.waitForElementVisible(productNameBy);
        nameEleList = webDriver.findElements(productNameBy);

        for (WebElement nameEle : nameEleList) {
            nameStringList.add(nameEle.getText());
        }

        if (order.equalsIgnoreCase(ASCENDING)) {
            Assert.assertTrue("FAIL: The product list is NOT sorted by NAME in ASCENDING order. "
                            + "Results in order: \"" + nameStringList + "\"",
                    Ordering.from(String.CASE_INSENSITIVE_ORDER).isOrdered(nameStringList));
        } else {
            List<String> reversedNameList = nameStringList;
            Collections.sort(reversedNameList, Ordering.natural().reverse());
            Assert.assertTrue("FAIL: The product list is NOT sorted by NAME in DESCENDING order. "
                    + "Results in order: \"" + nameStringList + "\"", nameStringList.equals(reversedNameList));
        }
        LOGGER.info("verifyNamesInOrder completed");
    }

    /***
     * Verifies the "Compare" button updates appropriately when the containing row / item is selected for comparison.
     * @param row - WebElement; row that should have been selected for comparison and contains the "Compare" button
     * @param index - Int; used in Safari to by pass Stale Element Exception if thrown.
     */
    private void verifyCompareButtonUpdatedMobile(WebElement row, int index) {
        LOGGER.info("verifyCompareButtonUpdatedMobile started");
        Assert.assertTrue("FAIL: Attempting to verify an update to the compare button of a row that has " +
                "NOT been selected", row.findElement(compareCheckboxBy).isSelected());
        String compareText;
        String buttonColor;
        WebElement anchor;

        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        driver.jsScrollToElement(row);

        if (totalSelectedItems == 1) {
            try {
                compareText = row.findElement(productCompareMessageBy).getText();
                Assert.assertTrue("FAIL: \"SELECT 1 0R 2 MORE\" message failed to display.",
                        compareText.equals(ConstantsDtc.COMPARE_ITEM_MESSAGE));
            } catch (StaleElementReferenceException stale) {
                LOGGER.info(Config.getBrowser() + " threw an error: " + stale);
                compareText = webDriver.findElements(productCompareMessageBy).get(index).getText();
                Assert.assertTrue("FAIL: \"SELECT 1 0R 2 MORE\" message failed to display.",
                        compareText.equals(ConstantsDtc.COMPARE_ITEM_MESSAGE));
            }
        } else if (totalSelectedItems == 2 || totalSelectedItems == 3) {
            try {
                anchor = row.findElement(resultsRowMobileAnchorBy);
                buttonColor = anchor.getCssValue(Constants.BACKGROUND_COLOR);
                Assert.assertTrue("FAIL: Compare button color was: " + buttonColor + "; not BLUE.",
                        buttonColor.equals(BLUE_COMPARE_COLOR));
            } catch (StaleElementReferenceException stale) {
                LOGGER.info(Config.getBrowser() + " threw an error: " + stale);
                anchor = row.findElement(resultsRowMobileAnchorBy);
                buttonColor = anchor.getCssValue(Constants.BACKGROUND_COLOR);
                Assert.assertTrue("FAIL: Compare button color was not BLUE.",
                        buttonColor.equals(BLUE_COMPARE_COLOR));
            }
        } else {
            Assert.fail("FAIL: Between 1 and 3 items can be compared. The total selected item count of \""
                    + totalSelectedItems + "\" is outside of those bounds");
        }
        LOGGER.info("verifyCompareButtonUpdatedMobile completed successfully");
    }

    /***
     * Updates the row quantity box with provided integer
     *
     * @param quantity Number that
     */
    public void updateFirstRowItemQuantityMobile(int quantity) {
        LOGGER.info("updateRowItemQuantity started");
        try {
            String quantityString = Integer.toString(quantity);
            if (quantity > Constants.NEGATIVE_ONE && quantity < Constants.ONE_HUNDRED) {
                driver.jsScrollToElement(productQuantityBoxMobile);
                productQuantityBoxMobile.click();
                productQuantityBoxMobile.clear();
                productQuantityBoxMobile.sendKeys(quantityString);
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Entered quantity could not be converted to an integer!");
        }

        LOGGER.info("updateRowItemQuantity completed successfully");
    }

    /***
     * Clears the value from the quantity box
     *
     */
    public void clearFirstItemQuantityInput() {
        LOGGER.info("clearItemQuantityCheckbox started");
        if (Config.isMobile()) {
            productQuantityBox.clear();
        } else {
            productQuantityBox.clear();
        }
        LOGGER.info("clearItemQuantityCheckbox completed successfully");
    }

    /***
     * Verifies the first row Add to Cart button is clickable and Red colored
     *
     */
    public void verifyAddToCartButtonProperties(String page) {
        LOGGER.info("verifyAddToCartButtonProperties started");
        String color;
        if (page.equalsIgnoreCase(ConstantsDtc.PLP)) {
            if (Config.isMobile()) {
                driver.waitForElementClickable(addToCartMobile);
                color = addToCartMobile.getCssValue(Constants.BACKGROUND_COLOR);
                Assert.assertTrue("FAIL: 'Add To Cart' button color was not Red --- actually was " + color + ".",
                        color.equals(Constants.RED_COLOR_MOBILE));
            } else {
                driver.waitForElementClickable(addToCart);
                color = addToCart.getCssValue(Constants.BACKGROUND_COLOR);
                Assert.assertTrue("FAIL: 'Add To Cart' button color was not Red (rgb:'"
                        + Constants.RED_COLOR_THREE_DIGIT + "' OR rgba:'" + Constants.RED_COLOR_FOUR_DIGIT
                        + "') --- actually was " + color + ".", color.equals(Constants.RED_COLOR_THREE_DIGIT) ||
                        color.equals(Constants.RED_COLOR_FOUR_DIGIT));
            }
        } else {
            driver.waitForElementClickable(CommonActions.addToCartProductAvailability);
            color = CommonActions.addToCartProductAvailability.getCssValue(Constants.BACKGROUND_COLOR);
            Assert.assertTrue("FAIL: 'Add To Cart' button color was not Red (rgb:'"
                    + Constants.RED_COLOR_THREE_DIGIT + "' OR rgba:'" + Constants.RED_COLOR_FOUR_DIGIT
                    + "') --- actually was " + color + ".", color.equals(Constants.RED_COLOR_THREE_DIGIT) ||
                    color.equals(Constants.RED_COLOR_FOUR_DIGIT));
        }
        LOGGER.info("verifyAddToCartButtonProperties completed successfully");
    }

    /***
     *  Method to verify the error message on the quantity box on the PLP page
     *
     */
    public void verifyProductQuantityErrorMessage() {
        LOGGER.info("verifyProductQuantityErrorMessage started");
        driver.waitForElementVisible(productQuantityErrorMobileBy);
        WebElement errorBox = webDriver.findElement(productQuantityErrorMobileBy);
        if (errorBox.getAttribute(Constants.CLASS).contains("hidden")) {
            Assert.fail("FAIL: \"Please enter a number\" error message was not shown on the screen!");
        }
        Assert.assertTrue("FAIL: Error message text was not " + QUANTITY_ERROR_MOBILE + ". But was: " +
                errorBox.getText(), errorBox.getText().contains(QUANTITY_ERROR_MOBILE));
        LOGGER.info("verifyProductQuantityErrorMessage completed successfully");
    }


    /**
     * Verifies the currently selected / default value of the "Sort By" dropdown matches the specified value
     *
     * @param expectedValue - String containing the expected / default value of the PLP "Sort By" dropdown
     */
    private void verifySortByCurrentValue(String expectedValue) {
        LOGGER.info("verifySortByCurrentValue started with value: \"" + expectedValue + "\"");
        By sortDropdownBy;
        if (Config.isMobile()) {
            sortDropdownBy = sortByCurrentlySelectedMobileBy;
        } else {
            sortDropdownBy = sortByCurrentlySelectedWebBy;
        }
        Assert.assertTrue("FAIL: Default value of PLP \"Sort By\" dropdown was: \""
                + webDriver.findElement(sortDropdownBy).getText() + "\" but expected: \"" + expectedValue
                + "\"!", driver.checkIfElementContainsText(sortDropdownBy, expectedValue));
        LOGGER.info("verifySortByCurrentValue completed with value: \"" + expectedValue + "\"");
    }

    /**
     * Extracts the Fixed dollar discount of a certain item
     *
     * @param itemCode Code number of item to extract discount
     */
    public double extractFixedDiscountFromItem(String itemCode) {
        LOGGER.info("extractFixedDiscountFromItem started");
        driver.waitForElementVisible(addToCart);
        double amount = 0;
        List<WebElement> results = webDriver.findElements(plpResultsRowBy);
        for (WebElement result : results) {

            if (result.getText().contains(itemCode)) {
                WebElement discount = result.findElement(promotionDiscountBy);
                if (discount.getText().contains(ConstantsDtc.FIXED)) {
                    String sub = discount.getText().substring(1);
                    String[] split = sub.split(" ");
                    amount = Integer.parseInt(split[0]);
                    break;
                } else {
                    Assert.fail("FAIL: Item number " + itemCode + " did not have a fixed promotion discount!");
                }
            }
        }
        LOGGER.info("Returning integer value of " + amount);
        LOGGER.info("extractFixedDiscountFromItem completed");
        return amount;
    }

    /**
     * Extracts the Percentage instant savings discount of a certain item
     *
     * @param itemCode Code number of item to extract discount
     */
    public int extractFixedDiscountPercentageFromItem(String itemCode) {
        LOGGER.info("extractFixedDiscountPercentageFromItem started");
        driver.waitForElementVisible(addToCart);
        int percentage = 0;
        boolean percentageFound = false;
        List<WebElement> results = webDriver.findElements(plpResultsRowBy);
        if (Config.isSafari())
            driver.waitForMilliseconds();
        for (WebElement result : results) {
            if (result.getText().contains(itemCode)) {
                WebElement discount = result.findElement(promotionDiscountBy);
                if (discount.getText().contains(ConstantsDtc.PERCENT_FIXED_DISCOUNT)
                        || discount.getText().contains(ConstantsDtc.PERCENT_DISCOUNT)
                        || discount.getText().contains(ConstantsDtc.PERCENT_PERCENTAGE_DISCOUNT)) {
                    percentage = Integer.parseInt(discount.getText().split("%")[0].trim());
                    percentageFound = true;
                    break;
                }
            }
            LOGGER.info("Returning integer value of " + percentage);
        }

        Assert.assertTrue("FAIL: Item number " + itemCode + " did not have a fixed discount!", percentageFound);
        LOGGER.info("extractFixedDiscountPercentageFromItem completed");
        return percentage;
    }


    /**
     * Clicks on the first available 'Compare tire reviews' link on the PLP page
     *
     * @return HashMap containing values for the product for which link was clicked
     */
    public HashMap<String, ArrayList<String>> clickCompareTireReviewsLink() {
        LOGGER.info("clickCompareTireReviewsLink started");

        boolean itemFound = false;
        List<WebElement> rows = new ArrayList<WebElement>();

        // Navigate to next page until link is found
        while (!itemFound) {
            driver.waitForPageToLoad();

            //TODO: Retest after stable IE runs
            if (Config.isIe())
                driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);

            rows = webDriver.findElements(plpResultsRowBy);
            int i = 0;

            // Click on first available 'Compare tire reviews' link on the current page
            for (WebElement row : rows) {
                if (!row.findElement(resultsRowRatingBy).getText().contains(THERE_ARE_NO_REVIEWS)) {
                    appendToBrandProductPriceLists(row, i, false);
                    WebElement element = row.findElement(compareTireReviewsBy);
                    try {
                        element.click();
                    } catch (Exception e) {
                        driver.jsClick(element);
                    }
                    itemFound = true;
                    break;
                }

                i++;
            }

            //navToDifferentPageOfResults now returns a boolean, indicating whether the next/prev button was found
            if (!itemFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                LOGGER.info("Navigating to next page.");
            } else if (!itemFound) {
                Assert.fail("FAIL: There are no available 'Compare tire reviews' links.");
                break;
            }
        }

        HashMap<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();
        values.put(ConstantsDtc.BRAND, brandList);
        values.put(ConstantsDtc.PRICE, priceList);
        values.put(ConstantsDtc.PRODUCT, productList);

        LOGGER.info("clickCompareTireReviewsLink completed");
        return values;
    }

    /**
     * Verifies passed in text does not appear in the Tire set tab titles
     *
     * @param text Text to verify does not appear in tabs
     */
    public void assertTextNotInTireTabTitles(String text) {
        LOGGER.info("assertTextNotInTireTabTitles started");
        driver.waitForElementVisible(addToCart);
        List<WebElement> results = webDriver.findElements(resultsOptionLinkBy);

        for (WebElement result : results) {
            Assert.assertTrue("FAIL: Tire set tabs do not contain the word " + text,
                    !result.getText().contains(text));
        }
        LOGGER.info("assertTextNotInTireTabTitles completed");
    }

    /**
     * Asserts item passed in is present on the product list page
     *
     * @param item selected item/product to verify appears on page
     */
    public void assertItemOnProductListPage(String item) {
        LOGGER.info("assertItemOnProductListPage started");
        boolean itemFound = false;

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isMobile() || Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForPageToLoad();
        driver.waitForElementVisible(productListingBy);

        while (!itemFound) {
            //TODO: Retest once latest safaridriver or geckodriver is updated & stabilized
            if (!Config.isSafari()) {
                if (Config.isFirefox() || Config.isIe()) {
                    driver.waitForMilliseconds(Constants.ONE_SEC_WAIT);
                }
                driver.waitForPageToLoad();
            } else {
                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
            }

            driver.waitForElementVisible(plpResultsRowBy);

            // selecting add to cart item if itemId is contained in text of row
            List<WebElement> rows = webDriver.findElements(plpResultsRowBy);
            List<WebElement> addToCartButtons = webDriver.findElements(addToCartBy);

            int i = 0;
            for (WebElement row : rows) {

                if (Config.isIe() || Config.isSafari())
                    driver.waitForMilliseconds();

                if (row.getText().contains(item)) {
                    driver.jsScrollToElement(addToCartButtons.get(i));
                    itemFound = true;
                    break;
                } else {
                    i++;
                }
            }

            //navToDifferentPageOfResults now returns a boolean, indicating whether the next/prev button was found
            if (!itemFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                LOGGER.info("Navigate to next page.");

                if (Config.isFirefox())
                    driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

            } else if (!itemFound) {
                Assert.fail("FAIL: Item - \"" + item + "\" NOT found! Unable to add to cart.");
                break;
            }
        }
        LOGGER.info("Confirmed \"" + item + "\" was visible on the product list page.");
        LOGGER.info("assertItemOnProductListPage completed");
    }

    /**
     * Verifies the value is contained within a driving details result section on search results
     *
     * @param zipCode String to verify
     */
    public void assertZipcodeValueOnPdlDrivingDetails(String zipCode) {
        LOGGER.info("assertZipcodeValueOnPdlDrivingDetails started");
        WebElement locationEle = driver.getElementWithText(zipLocationBy, zipCode);
        Assert.assertTrue("FAIL: Pdl Driving Details results section did NOT contain a zipcode value of: \""
                + zipCode + "\"!", locationEle.getText().contains(zipCode));
        LOGGER.info("Confirmed that '" + zipCode + "' zipcode was listed on Pdl Driving Details results section.");
        LOGGER.info("assertZipcodeValueOnPdlDrivingDetails completed");
    }

    /**
     * Verifies the value is contained within a driving details result section on search results
     *
     * @param milesPerYear String to verify
     */
    public void assertMilesPerYearValueOnPdlDrivingDetails(String milesPerYear) {
        LOGGER.info("assertMilesPerYearValueOnPdlDrivingDetails started");
        milesPerYear += THOUSAND_MILES_SUFFIX;
        WebElement MilesEle = driver.getElementWithText(milesPerYearBy, milesPerYear);
        Assert.assertTrue("FAIL: Pdl Driving Details results section did NOT contain a miles per year value "
                + "of: \"" + milesPerYear + "\"!", MilesEle.getText().contains(milesPerYear));
        LOGGER.info("Confirmed that '" + milesPerYear + "' as miles per year was listed on Pdl Driving Details "
                + "results section.");
        LOGGER.info("assertMilesPerYearValueOnPdlDrivingDetails completed");
    }

    /**
     * Verifies the value is contained within a driving details result section on search results
     */
    public void assertOurRecommendationBannerIsDisplayed() {
        LOGGER.info("assertOurRecommendationBannerIsDisplayed started");
        WebElement recommendationBanner = driver.getElementWithText(ourRecommendationBannerBy,
                ConstantsDtc.OUR_RECOMMENDATION);
        Assert.assertTrue("FAIL: Pdl search results did NOT contain any item with : \""
                + ConstantsDtc.OUR_RECOMMENDATION + " banner\"!", driver.isElementDisplayed(recommendationBanner));
        LOGGER.info("Confirmed that '" + ConstantsDtc.OUR_RECOMMENDATION + "' banner was listed on Pdl Driving "
                + "Details search results.");
        LOGGER.info("assertOurRecommendationBannerIsDisplayed completed");
    }

    /**
     * Verify the order sequence of PDL Driving priorities
     *
     * @param drivingPrioritiesOrder Driven Priorities
     */
    public void assertDrivingPrioritiesOrder(String[] drivingPrioritiesOrder) {
        LOGGER.info("assertDrivingPrioritiesOrder started");
        int i = 0;
        List<WebElement> options = plpResultSection.findElements(pdlDrivingPrioritiesBy);
        for (WebElement option : options) {
            // TODO: Success rate of this method is intermittent, needs a proper fix for IE
            WebElement optionElement = option.findElement(pdlDrivingPriorityOptionNameBy);
            if (!(optionElement.getText().contains(drivingPrioritiesOrder[i]))) {
                Assert.fail("FAIL: PDL driving priority sequence didn't match, At position " + (i + 1)
                        + " Expected " + drivingPrioritiesOrder[i] + " but Actual : " + optionElement.getText());
                break;
            }
            LOGGER.info("At position " + (i + 1) + " Driving Priority " + drivingPrioritiesOrder[i] + " matched");
            i++;
        }
        LOGGER.info("assertDrivingPrioritiesOrder completed");
    }

    /**
     * Select Edit PDL Driving Details
     */
    public void selectEditPdlDrivingDetails() {
        LOGGER.info("selectEditPdlDrivingDetails started");
        WebElement pdlEdit = plpResultSection.findElement(pdlEditDrivingDetailsBy);
        driver.waitForElementClickable(pdlEdit);
        pdlEdit.click();
        LOGGER.info("selectEditPdlDrivingDetails completed");
    }

    /**
     * Verify Product List page is display the Sets for staggered vehicle
     */
    public void verifyPLPPageDisplayStaggered() {
        LOGGER.info("verifyPLPPageDisplayStaggered started");
        driver.waitForPageToLoad();
        Assert.assertTrue("PLP Page not displaying the staggered set",
                driver.isElementDisplayed(driver.getElementWithText(tireSizeBy, STAGGERED_FRONT)) &&
                        driver.isElementDisplayed(driver.getElementWithText(tireSizeBy, STAGGERED_REAR)));
        LOGGER.info("verifyPLPPageDisplayStaggered completed");
    }

    /**
     * Extract product details on Plp page having product code and product price
     *
     * @return product details map
     */
    public HashMap<String, String> getProductPriceOnPlp() {
        LOGGER.info("getProductPriceOnPlp started");
        driver.waitForPageToLoad();
        WebElement parent = driver.getParentElement(webDriver.findElement(addToCartBy));
        List<WebElement> items = parent.findElements(clearfixBy);
        for (WebElement item : items) {
            driver.jsScrollToElement(item.findElement(productPriceBy));
            productPriceOnPlp.put(
                    item.findElement(itemCodeBy).getAttribute(Constants.VALUE),
                    item.findElement(productPriceBy).getText()
            );
        }
        LOGGER.info("getProductPriceOnPlp completed");
        return productPriceOnPlp;
    }

    /**
     * Handles the selection of the desired product list tab when sets are available (SETS, FRONT, REAR)
     *
     * @param tab The tab in results to pick from (Ex. SETS, FRONT, REAR)
     */
    private void selectProductListTab(String tab) {
        LOGGER.info("selectProductListTab started for tab: " + tab);
        try {
            By tabBy = resultsOptionLinkBy;

            if (Config.isMobile()) {
                tabBy = resultsOptionLinkMobileBy;
            }

            List<WebElement> optionLinks = webDriver.findElements(tabBy);
            for (WebElement optionLink : optionLinks) {
                if (optionLink.getText().toUpperCase().contains(tab.toUpperCase())) {
                    driver.jsScrollToElement(optionLink);
                    optionLink.click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            Assert.fail("FAIL: The desired product list tab: \"" + tab + "\" was NOT found and nothing was " +
                    "added to the cart!");
        }
        LOGGER.info("selectProductListTab completed for tab: " + tab);
    }

    /**
     * Add the first item listed to the cart
     *
     * @param action - 'View Shopping Cart' or 'Continue Shopping'
     */
    public void addFirstItemToCart(String action) {
        LOGGER.info("addFirstItemToCart started");
        if (!Config.isSafari()) {
            if (Config.isFirefox() || Config.isIe()) {
                driver.waitForMilliseconds();
            }
        } else {
            driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        }
        driver.waitForPageToLoad();
        try {
            webDriver.findElements(addToCartBy).get(0).click();
        } catch (Exception e) {
            Assert.fail("Unable to click the first item on the "
                    + "Product List Page!\n" + e.getMessage());
        }
        selectAction(action);
        LOGGER.info("addFirstItemToCart completed");
    }

    /**
     * Verify Product List page display tabs for staggered vehicle
     */
    public void verifyPLPTabDisplayForStaggered() {
        LOGGER.info("verifyPLPTabDisplayForStaggered started");
        driver.waitForPageToLoad();
        List<WebElement> plpTabs = webDriver.findElements(resultsOptionLinkBy);
        if (STAGGERED_PLP_TAB.length != plpTabs.size()) {
            Assert.fail("FAIL: All three plp display tabs not showing for staggered vehicle");
        }
        for (int i = 0; i < STAGGERED_PLP_TAB.length; i++) {
            String tabValue = plpTabs.get(i).getText().split("\\s+")[0].trim();
            if (Arrays.asList(STAGGERED_PLP_TAB).contains(tabValue)) {
                LOGGER.info("PASS: Staggered vehicle plp display tab found : " + tabValue);
            } else {
                Assert.fail("FAIL: For Staggered vehicle plp display tab : (" + tabValue + ") expected : "
                        + STAGGERED_PLP_TAB[i]);
            }
        }
        LOGGER.info("verifyPLPTabDisplayForStaggered completed");
    }

    /**
     * Verifies the display of a specified list of filter sections on the product list page
     *
     * @param filterSections - String of comma seperated values containing the name(s) of the refinement filter
     *                       section(s) as should appear on the PLP
     */
    public void verifyFilterSectionsDisplay(String filterSections) {
        LOGGER.info("verifyFilterSectionsDisplay started w/ filter section(s): '" + filterSections + "'");
        driver.waitForPageToLoad();

        if (Config.isSafari()) {
            if (driver.isElementDisplayed(showFilterBtn)) {
                showFilterBtn.click();
            }
        }

        driver.waitForElementVisible(searchFilterSectionBy);
        List<String> sectionsToVerifyList = Arrays.asList(filterSections.split(","));
        List<WebElement> displayedFilterSectionsList = webDriver.findElements(searchFilterSectionBy);

        for (String sectionToVerify : sectionsToVerifyList) {
            boolean filterSectionFound = false;
            for (WebElement displayedSection : displayedFilterSectionsList) {
                if (displayedSection.getText().toLowerCase().contains(sectionToVerify.trim().toLowerCase())) {
                    filterSectionFound = true;
                    break;
                }
            }
            Assert.assertTrue("FAIL: Unable to find '" + sectionToVerify.trim() + "' filter section!",
                    filterSectionFound);
        }
        LOGGER.info("verifyFilterSectionsDisplay completed w/ filter section(s): '" + filterSections + "'");
    }

    /**
     * Verifies that a staggered option tab with the specified value set is visible
     *
     * @param tabValue The value of the tab
     */
    public void verifyStaggeredOptionTabIsDisplayed(String tabValue) {
        LOGGER.info("verifyStaggeredOptionTabIsDisplayed started");
        driver.waitForPageToLoad();

        WebElement optionElement = driver.getElementWithText(staggeredOptionTabBy, tabValue);
        if (!driver.isElementDisplayed(optionElement)) {
            Assert.fail("FAIL: Staggered option tab : " + tabValue + "didn't display on PLP result page");
        }
        LOGGER.info("verifyStaggeredOptionTabIsDisplayed completed");
    }

    /**
     * Extracts the Staggered size from fitment tab
     *
     * @param fitmentType The value of the staggered tab
     * @return String       Diameter of the selected fitment type
     */
    public String getStaggeredFitmentSizeFromTab(String fitmentType) {
        LOGGER.info("getStaggeredFitmentSizeFromTab started");
        driver.waitForPageToLoad();

        WebElement optionElement = driver.getElementWithText(staggeredOptionTabBy, fitmentType);
        LOGGER.info("getStaggeredFitmentSizeFromTab completed");
        return optionElement.findElement(staggeredSizeBy).getText().split("\"")[0];
    }

    /**
     * Verifies Front Or Rear wheel diameter matches with the size of each product on the results page
     *
     * @param fitmentType The value of the staggered tab - Front Or Rear
     */
    public void verifyListedProductsSizeMatchesWithSelectedStaggeredDiameter(String fitmentType) {
        LOGGER.info("verifyListedProductsSizeMatchesWithSelectedStaggeredDiameter started");
        driver.waitForPageToLoad();

        boolean sizeMatched = true;
        String diameter = getStaggeredFitmentSizeFromTab(fitmentType).trim();
        List<WebElement> displayedStaggeredSizes = webDriver.findElements(tireSizeBy);
        List<WebElement> items = webDriver.findElements(commonActions.itemIdBy);
        int row = 0;

        for (WebElement displayedStaggeredSize : displayedStaggeredSizes) {
            if (!displayedStaggeredSize.getText().split("X")[0].trim().equalsIgnoreCase(diameter)) {
                sizeMatched = false;
                row = displayedStaggeredSizes.indexOf(displayedStaggeredSize);
                break;
            }
        }

        Assert.assertTrue("FAIL: Staggered size " + displayedStaggeredSizes.get(row).getText() + " for '"
                + items.get(row).getText() + "' did NOT match with selected " + fitmentType + " diameter : "
                + diameter, sizeMatched);

        LOGGER.info("verifyListedProductsSizeMatchesWithSelectedStaggeredDiameter completed");
    }

    /**
     * Click staggered option tab
     *
     * @param tabValue The value of the tab
     */
    public void selectStaggeredTab(String tabValue) {
        LOGGER.info("selectStaggeredTab started");
        driver.waitForPageToLoad();

        WebElement optionElement = driver.getElementWithText(staggeredOptionTabBy, tabValue);
        optionElement.click();
        LOGGER.info("selectStaggeredTab completed");
    }

    /**
     * Verify that Customer Rating is displayed and either a link for 'Read reviews'
     * or a message stating 'There are no reviews yet.' is displayed for each product.
     */
    public void verifyCustomerRatingAndReviewsDisplayedForListedProducts() {
        LOGGER.info("verifyCustomerRatingAndReviewsDisplayedForListedProducts started");
        List<WebElement> productRows;
        driver.waitForPageToLoad();
        if (Config.isMobile()){
            driver.waitForElementVisible(productListingMobile);
            productRows = productListingMobile.findElements(plpResultsRowBy);
        } else {
            driver.waitForElementVisible(productListingBy);
            productRows = webDriver.findElement(productListingBy).findElements(plpResultsRowBy);
        }
        for (WebElement productRow : productRows) {
            String productRowText = productRow.getText();
            if (!productRowText.contains(CUSTOMER_RATING)) {
                Assert.fail("FAIL:  Customer Ratings were not displayed for product '"
                        + productRow.findElement(productNameBy).getText() + "'!");
            }
            if (!productRowText.contains(THERE_ARE_NO_REVIEWS) &&
                    !driver.isElementDisplayed(productRow.findElement(reviewRatingBy))) {
                Assert.fail("FAIL:  Neither customer reviews or the 'There are no reviews yet.' "
                        + "message were displayed for product '"
                        + productRow.findElement(productNameBy).getText() + "'!");
            }
        }
        LOGGER.info("verifyCustomerRatingAndReviewsDisplayedForListedProducts completed");
    }

    /**
     * Verify the 'Compare tire reviews' link appears for products that have reviews
     */
    public void verifyCompareTireReviewsLinksDisplayed() {
        LOGGER.info("verifyCompareTireReviewsLinksDisplayed started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(productListingBy);
        List<WebElement> productRows = webDriver.findElement(productListingBy).findElements(plpResultsRowBy);
        for (WebElement productRow : productRows) {
            boolean found = true;
            if (!productRow.findElement(resultsRowRatingBy).getText().contains(THERE_ARE_NO_REVIEWS)) {
                found = driver.isElementDisplayed(productRow.findElement(compareTireReviewsBy));
            }
            if (!found) {
                Assert.fail("FAIL:  Compare tire reviews not displayed for product '"
                        + productRow.findElement(productNameBy).getText() + "'!");
            }
        }
        LOGGER.info("verifyCompareTireReviewsLinksDisplayed completed");
    }

    /**
     * Verify that selected filter is deselected for specific brands
     *
     * @param filterLabel Label of the filter option
     * @param type        Tire or Wheel
     */
    public void verifyFilterIsDeselectedForSpecificBrands(String filterLabel, String type) {
        LOGGER.info("verifyFilterIsDeselectedForSpecificBrands started");
        driver.waitForPageToLoad();
        String[] brandURLs;

        if (type.equalsIgnoreCase(Constants.TIRE)) {
            brandURLs = ConstantsDtc.TIRE_BRANDS_URLS;
        } else {
            brandURLs = ConstantsDtc.WHEEL_BRANDS_URLS;
        }
        for (String brandURL : brandURLs) {
            commonActions.navigateToPage(brandURL);
            commonActions.waitForUrl(brandURL, Constants.DEFAULT_SEC_WAIT);
            assertCheckboxDeselected(filterLabel);
            LOGGER.info("Confirmed: " + filterLabel + " filter was deselected for " + type + " Brand with URL "
                    + brandURL);
        }
        LOGGER.info("verifyFilterIsDeselectedForSpecificBrands completed");
    }

    /**
     * Verifies the Quick Filter Checkbox is not selected by default
     *
     * @param checkBoxLabel String to verify
     */
    public void assertFilterFontSizeBeforeAndAfterFilterSelection(String checkBoxLabel) {
        LOGGER.info("assertFilterFontSizeBeforeAndAfterFilterSelection started");
        String newFontSize = driver.getElementWithText(filterOptionBy, checkBoxLabel).getCssValue(Constants.FONT_SIZE);
        Assert.assertTrue("FAIL: " + checkBoxLabel + " filter before and after selection font size value did"
                + " Not match", filterFontSize.equalsIgnoreCase(newFontSize));
        LOGGER.info("Confirmed " + checkBoxLabel + " filter font size value matched:" + newFontSize + " : "
                + filterFontSize);
        LOGGER.info("assertFilterFontSizeBeforeAndAfterFilterSelection completed");
    }

    /**
     * Extract the Filter font size value
     *
     * @param checkBoxLabel Filter label
     */
    public void getFilterFontSize(String checkBoxLabel) {
        LOGGER.info("getFilterFontSize started");

        filterFontSize = driver.getElementWithText(filterOptionBy, checkBoxLabel).getCssValue(Constants.FONT_SIZE);
        LOGGER.info("getFilterFontSize completed");
    }

    /**
     * Verifies the Bolt Pattern Facet on the PLP
     *
     * @param isPresent True, if the Bolt Pattern displays and there is no Fitment
     *                  False, if the Bolt Pattern does not display and there is Fitment
     */
    public void verifyBoltPatternFacetIsDisplayed(boolean isPresent) {
        LOGGER.info("verifyBoltPatternFacetIsDisplayed started");
        if (isPresent && (!driver.isAttributePresent(vehicleSelectedDescription, NO_VEHICLE_SELECTED))) {
            Assert.assertTrue("FAIL: Bolt Pattern facet did not display on PLP Page ",
                    driver.isElementDisplayed(boltPatternFacet));
            LOGGER.info("As expected, the Bolt Pattern facet displayed on the PLP without Fitment");
        } else {
            Assert.assertFalse("FAIL: Bolt Pattern facet displayed on PLP",
                    driver.isElementDisplayed(boltPatternFacet));
            LOGGER.info("As expected , the Bolt Pattern facet should not display on PLP page with Fitment");
        }
        LOGGER.info("verifyBoltPatternFacetIsDisplayed completed");
    }

    /**
     * Selects the specified items in a comma seperated list for comparison from the product list page results. Method
     * paginates through the entire result list looking for the requested item number, and if found, restarts from
     * the first page of the results list to begin looking for the subsequent item numbers. If the method iterates
     * through the entire list and does not find a specified item number, an error is thrown.
     *
     * @param itemNumbers Comma separated list of product / item numbers to select for comparison
     */
    public void selectItemNumbersForComparison(String itemNumbers) {
        LOGGER.info("selectItemNumbersForComparison started with number(s):'" + itemNumbers + "'");
        driver.waitForPageToLoad();
        checkForNoResultsMessage();
        driver.waitForElementVisible(addToCart);

        List<WebElement> resultRowsList = new ArrayList<>();
        List<String> itemNumbersToSelectList = Arrays.asList(itemNumbers.split(","));

        for (String itemNumber : itemNumbersToSelectList) {
            boolean itemFound = false;

            if (commonActions.navToDifferentPageOfResults(Constants.FIRST))
                LOGGER.info("Navigating back to first page of results");

            while (!itemFound) {
                //TODO: Retest once latest safaridriver or geckodriver is updated & stabilized
                if (Config.isSafari() || Config.isFirefox() || Config.isIe()) {
                    driver.waitForPageToLoad();
                }

                resultRowsList.clear();
                resultRowsList = webDriver.findElements(plpResultsRowBy);

                for (WebElement resultRow : resultRowsList) {
                    if (resultRow.getText().contains(ITEM_NUMBER_PREFIX + itemNumber.trim())) {
                        itemFound = true;
                        driver.jsScrollToElement(resultRow.findElement(compareCheckboxBy));
                        resultRow.findElement(compareCheckboxBy).click();
                        break;
                    }
                }

                if (!itemFound && commonActions.navToDifferentPageOfResults(Constants.NEXT)) {
                    LOGGER.info("Navigating to next page.");

                    if (Config.isFirefox())
                        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

                } else if (!itemFound) {
                    Assert.fail("FAIL: Item - \"" + itemNumber.trim() + "\" NOT found! "
                            + "Unable to select for comparison");
                    break;
                }
            }
        }
        LOGGER.info("selectItemNumbersForComparison completed with number(s):'" + itemNumbers + "'");
    }

    /**
     * Verify the only expected brand products are displayed on PLP
     *
     * @param brand expected brand products
     */
    public void assertProductBrandOnPLP(String brand) {
        LOGGER.info("assertProductBrandOnPLP started for " + brand);
        List<WebElement> productsBrand = webDriver.findElements(resultRowBrandNameBy);
        for (WebElement productBrand : productsBrand) {
            Assert.assertTrue("FAIL: Product brand : " + productBrand.getText()
                    + " displayed different from expected brand : " + brand, productBrand.getText()
                    .equalsIgnoreCase(brand));
        }
        LOGGER.info("assertProductBrandOnPLP completed for " + brand);
    }

    /**
     * Asserts that the current product quantity matches desired/default
     * quantity in Product List Page
     *
     * @param value The value to verify
     */
    public void assertProductQuantityInProductListPage(String value) {
        LOGGER.info("assertProductQuantityInProductListPage started");
        driver.waitForElementVisible(quantityInfoBoxBy);

        List<WebElement> itemQuantity = webDriver.findElements(quantityInfoBoxBy);
        for (WebElement quantity : itemQuantity) {
            Assert.assertTrue("FAIL : Staggered default quantity : " + value
                            + " didn't match the display quantity" + quantity.getAttribute(Constants.VALUE),
                    quantity.getAttribute(Constants.VALUE).equals(value));
        }
        LOGGER.info("Confirmed that default product quantity '" + value + "' matches with rendered quantity ==>"
                + productQuantityBox.getAttribute(Constants.VALUE));
        LOGGER.info("assertProductQuantityInProductListPage completed");
    }

    /**
     * Verify Original Equipment Tire is displayed on PLP page
     */
    public void assertOETireDisplayedOnPLP() {
        LOGGER.info("assertOETireDisplayedOnPLP started");
        driver.waitForPageToLoad();
        WebElement oeProduct;
        if (Config.isMobile()) {
            oeProduct = webDriver.findElement(productListOEBy);
        } else {
            oeProduct = webDriver.findElement(productItemBy).findElement(productListOEBy);
        }
        Assert.assertTrue("FAIL: original Equipment tire not displayed with expected text bar : "
                        + ConstantsDtc.OE_TIRE + " Actual text bar : " + oeProduct.getText(),
                oeProduct.getText().equals(ConstantsDtc.OE_TIRE));
        LOGGER.info("assertOETireDisplayedOnPLP completed");
    }

    /**
     * Verifies the OE Tire Message
     */
    public void assertOETireMessage() {
        LOGGER.info("assertOETireMessage started");
        driver.waitForElementVisible(originalEquipmentTireMessageBy);

        Assert.assertTrue("FAIL: Original Equipment Tire message was not displayed ",
                driver.getElementWithText(originalEquipmentTireMessageBy, ORIGINAL_EQUIPMENT_TIRE_MESSAGE)
                        .isDisplayed());

        LOGGER.info("assertOETireMessage completed");
    }

    /**
     * Verifies the order of the filter categories matches the expectation, dependent on the product type
     * (Wheels or Tires)
     *
     * @param productType Product type (Wheels Or Tires) which determines the categories used in validation
     */
    public void verifyOrderOfFilterCategoriesForProductType(String productType) {
        LOGGER.info("verifyOrderOfFilterCategoriesForProductType started with productType: '" + productType + "'");
        List<String> filterCategoriesToVerifyList = new ArrayList<>();
        List<String> displayedFilterCategoriesList = new ArrayList<>();

        if (productType.equalsIgnoreCase(ConstantsDtc.TIRES)) {
            filterCategoriesToVerifyList = tireFilterCategoriesList;
        } else if (productType.equalsIgnoreCase(ConstantsDtc.WHEELS)) {
            filterCategoriesToVerifyList = wheelFilterCategoriesList;
        } else {
            Assert.fail("FAIL: The product type of : '" + productType + "' is NOT currently a valid selection!");
        }

        if (Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (Config.isSafari()) {
            if (driver.isElementDisplayed(showFilterBtn)) {
                showFilterBtn.click();
            }
        }
        driver.waitForElementVisible(searchFilterSectionTitleBy);

        List<WebElement> searchFilterSectionsList = driver.getDisplayedElementsList(searchFilterSectionTitleBy);
        for (WebElement searchFilterSection : searchFilterSectionsList) {
            displayedFilterCategoriesList.add(searchFilterSection.getText().trim());
        }

        Assert.assertEquals("FAIL: The displayed filter categories:\n\t" + displayedFilterCategoriesList
                + "\n\n\t did NOT match the expected order of categories:\n\t" + filterCategoriesToVerifyList
                + "!", filterCategoriesToVerifyList, displayedFilterCategoriesList);
        LOGGER.info("verifyOrderOfFilterCategoriesForProductType completed with productType: '" + productType
                + "'");
    }

    /**
     * Verifies that the currently displayed products on PLP are "On Promotion" i.e. have promotional messaging
     * displayed as part of their product row
     */
    public void verifyDisplayedProductsAreOnPromotion() {
        LOGGER.info("verifyDisplayedProductsAreOnPromotion started");
        driver.waitForPageToLoad();
        checkForNoResultsMessage();

        List<WebElement> resultItemsList = webDriver.findElement(productListingBy).findElements(plpResultsRowBy);
        for (WebElement resultItem : resultItemsList) {
            Assert.assertTrue("FAIL: A product currently displayed does NOT appear to be on a promotion!",
                    driver.isElementDisplayed(resultItem.findElement(promotionDiscountBy)));
        }
        LOGGER.info("verifyDisplayedProductsAreOnPromotion completed");
    }

    /**
     * Verifies the quantity of products selected
     *
     * @param quantity Number of checkboxes to be verified
     */
    public void assertProductQuantitySelected(Integer quantity) {
        LOGGER.info("assertProductQuantitySelected started");
        driver.waitForPageToLoad();
        List<WebElement> checkboxes = webDriver.findElements(compareBy);
        Assert.assertTrue("FAIL: The Expected " + quantity + " did not match the actual quantity " + checkboxes.size(),
                checkboxes.size() == quantity);
        LOGGER.info("assertProductQuantitySelected completed");
    }

    /**
     * Verifies the text on the compare button
     *
     * @param expectedValue The text to be verified
     */
    public void assertCompareButtonText(String expectedValue) {
        LOGGER.info("assertCompareButtonText started");
        driver.waitForPageToLoad();
        List<WebElement> checkboxes = webDriver.findElements(compareBy);
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue("FAIL: The Expected Text " + expectedValue + " did not match the actual text " + checkbox.getText(),
                    checkbox.getText().equalsIgnoreCase(ConstantsDtc.COMPARE));
        }
        LOGGER.info("assertCompareButtonText completed");
    }

    /**
     * Verifies the text on Checkbox based on the position
     *
     * @param position The specific value of rendered checkbox's position
     * @param text     The text to be verified
     */
    public void assertCompareButtonTextAtPosition(String text, int position) {
        LOGGER.info("assertCompareButtonTextAtPosition started");
        driver.waitForPageToLoad();
        List<WebElement> compareCheckboxes = webDriver.findElements(compareBy);
        List<WebElement> compareUpTo3Checkboxes = webDriver.findElements(compareButtonValueBy);

        if (text.equals(ConstantsDtc.COMPARE)) {
            Assert.assertTrue("FAIL: The Expected Text " + text + " did not match the actual text " + compareCheckboxes.get(position - 1).getText(),
                    compareCheckboxes.get(position - 1).getText().equalsIgnoreCase(ConstantsDtc.COMPARE));
        } else if (text.equals(ConstantsDtc.COMPARE_UP_TO_3)) {
            Assert.assertTrue("FAIL: The Expected Text " + text + " did not match the actual text " + compareCheckboxes.get(position - 1).getText(),
                    compareUpTo3Checkboxes.get(position - 1).getText().trim().equalsIgnoreCase(ConstantsDtc.COMPARE_UP_TO_3));
        }
        LOGGER.info("assertCompareButtonTextAtPosition completed");
    }

    /**
     * Selects a Product Checkbox based on the position
     *
     * @param position The specific value of rendered checkbox's position
     */
    public void selectCompareCheckboxAtPosition(int position) throws Exception {
        LOGGER.info("selectCompareCheckboxAtPosition started");
        driver.waitForPageToLoad();
        List<WebElement> inputs = webDriver.findElements(resultRowImageColumnBy);
        if (webDriver.findElement(compareUpTo3By).getText().trim().equalsIgnoreCase(compareUpTo3)) {
            if (!inputs.get(position - 1).isSelected()) {
                inputs.get(position - 1).click();
                driver.waitForPageToLoad();
                WebElement row = webDriver.findElements(plpResultsRowBy).get(position-1);
                appendToBrandProductPriceLists(row, position-1, false);

                HashMap<String, ArrayList<String>> values = new HashMap<>();
                values.put(ConstantsDtc.BRAND, brandList);
                values.put(ConstantsDtc.PRICE, priceList);
                values.put(ConstantsDtc.PRODUCT, productList);
                values.put(ConstantsDtc.INVENTORY_MESSAGE, inventoryMessageList);
            } else {
                LOGGER.info("Product list checkbox # " + position + " was already selected");
            }
        } else
            LOGGER.info("Compare Product checkbox to select at position " + position + " has " + webDriver.findElement(compareUpTo3By).getText());
        LOGGER.info("selectCompareCheckboxAtPosition completed");
    }

    /**
     * De-selects the Compare Checkbox at a given position
     *
     * @param position The specific value of rendered checkbox's position
     */
    public void deSelectCompareCheckboxAtPosition(int position) {
        LOGGER.info("deSelectCompareCheckboxAtPosition started");
        driver.waitForPageToLoad();

        List<WebElement> checkboxes = webDriver.findElements(compareBy);
        List<WebElement> inputs = webDriver.findElements(compareInputBy);

        if ((checkboxes.get(position - 1).getText().equalsIgnoreCase(ConstantsDtc.COMPARE))) {
            inputs.get(position - 1).click();
            driver.waitForPageToLoad();
        } else {
            LOGGER.info("Product list checkbox # " + position + " was already deselected");
        }
        LOGGER.info("deSelectCompareCheckboxAtPosition completed");
    }

    /**
     * Verify whether the specified button is enabled or disabled
     *
     * @param enabledOrDisabled - expected state of 'enabled' or 'disabled'
     */
    public void assertCompareButtonEnabledDisabled(String enabledOrDisabled) {
        LOGGER.info("assertCompareButtonEnabledDisabled started");
        driver.waitForPageToLoad();
        List<WebElement> compareCheckboxes = webDriver.findElements(compareBy);
        List<WebElement> compareUpTo3Checkboxes = webDriver.findElements(compareUpTo3DisabledBy);

        if (enabledOrDisabled.equalsIgnoreCase(Constants.DISABLED)) {
            Assert.assertTrue("FAIL: Button should have been disabled but it was enabled.",
                    compareCheckboxes.size() == 3 && compareUpTo3Checkboxes.size() > 0);
        } else {
            Assert.assertTrue("FAIL: Button should have been enabled but it was disabled.",
                    (compareCheckboxes.size() < 3) && compareUpTo3Checkboxes.size() == 0);
        }
        LOGGER.info("assertCompareButtonEnabledDisabled completed");
    }

    /**
     * Verifies that the specified text is present in the PLP results header message e.g. "28 results for wheels"
     *
     * @param textToValidate Text string that is expected to appear in the PLP results header
     */
    public void verifyPlpHeaderMessageContainsText(String textToValidate) {
        LOGGER.info("verifyPlpHeaderMessageContainsText started with text: '" + textToValidate + "'");
        driver.waitForPageToLoad();
        String actualHeader = null;
        checkForNoResultsMessage();

        if (!Config.isMobile()) {
            if (driver.isElementDisplayed(plpCurrentResultsTab, Constants.ZERO)
                    && plpCurrentResultsTab.getText().contains(SETS)) {
                LOGGER.info("Skipping PLP header text validation as the \"SETS\" tab does not display header text!");
            } else {
                driver.waitForElementVisible(plpResultsHeader);
                actualHeader = plpResultsHeader.getText();
            }

            Assert.assertTrue("FAIL: The PLP header text '" + actualHeader.trim() + "' did NOT contain the"
                    + " expected text '" + textToValidate + "'!", actualHeader.trim().toLowerCase().contains(
                    textToValidate.trim().toLowerCase()));
            LOGGER.info("verifyPlpHeaderMessageContainsText completed with text: '" + textToValidate + "'");
        } else {
            LOGGER.info("Skipping PLP header text validation as the mobile site does not display header text based on search terms!");
        }
    }

    /**
     * Sets the "Price Range" slider filter to the specified range
     *
     * @param priceMin String for the minimum point value
     * @param priceMax String for the maximum point value
     */
    public void setPriceRangeSliderFilterToRange(String priceMin, String priceMax) {
        LOGGER.info("setPriceRangeSliderFilterToRange started with min: \"" + priceMin + "\" and max: \""
                + priceMax + "\"");
        //TODO CCL - retest in the future with an updated version of Safari driver
        if (!Config.isSafari()) {
            driver.waitForPageToLoad();
            checkForNoResultsMessage();
            driver.waitForElementVisible(searchFilterSectionBy);

            minValueApproximate = Integer.parseInt(priceMin);
            maxValueApproximate = Integer.parseInt(priceMax);
            setPriceRangeFilterValue(MIN, priceMin);
            setPriceRangeFilterValue(MAX, priceMax);

            WebElement priceRangeApplyBtn = driver.getDisplayedElement(priceRangeApplyBtnBy, Constants.ZERO);
            driver.jsScrollToElement(priceRangeApplyBtn);
            priceRangeApplyBtn.click();
        } else {
            LOGGER.info("Skipping setPriceRangeSliderFilterToRange() for Safari browser");
        }
        LOGGER.info("setPriceRangeSliderFilterToRange completed with min: \"" + priceMin + "\" and max: \""
                + priceMax + "\"");
    }

    /**
     * Sets the specified range point (Min or Max) for the "Price Range" slider filter to the specified value
     *
     * @param rangePoint         String for the range point (Min or Max)
     * @param desiredFilterValue String for the filter value
     */
    private void setPriceRangeFilterValue(String rangePoint, String desiredFilterValue) {
        LOGGER.info("setPriceRangeFilterValue started for point: \"" + rangePoint + "\" with value to set of: \""
                + desiredFilterValue + "\"");
        By sliderPointBy = null;
        int expectedFilterValue = Integer.parseInt(desiredFilterValue);
        Actions moveSlider = new Actions(webDriver);

        switch (rangePoint.toLowerCase()) {
            case MIN:
                sliderPointBy = priceRangeMinimumPointBy;
                break;
            case MAX:
                sliderPointBy = priceRangeMaximumPointBy;
                break;
            default:
                Assert.fail("FAIL: Did NOT recognize \"Price Range\" point: \"" + rangePoint + "\"!");
        }

        WebElement sliderPoint = driver.getDisplayedElement(sliderPointBy, Constants.ZERO);
        driver.jsScrollToElement(sliderPoint);
        driver.jsClick(sliderPoint);

        while (!getCurrentPriceRangePoint(rangePoint).equalsIgnoreCase(desiredFilterValue)) {
            if (Config.isSafari())
                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

            String lastPriceRangePointValue = getCurrentPriceRangePoint(rangePoint);
            Action action = moveSlider.dragAndDropBy(sliderPoint,
                    setXoffsetValue(rangePoint, 9, expectedFilterValue), Constants.ZERO).build();
            action.perform();

            if (lastPriceRangePointValue.equalsIgnoreCase(getCurrentPriceRangePoint(rangePoint))) {
                Assert.fail("FAIL: The last attempt to modify filter point: \"" + rangePoint
                        + "\" was unsuccessful! The value of the point is the same after attempted update!");
            }

            if (checkForValueInApproximateRange(rangePoint, desiredFilterValue, 5))
                break;
        }
        LOGGER.info("setPriceRangeFilterValue completed for point: \"" + rangePoint
                + "\" with value to set of: \"" + desiredFilterValue + "\"");
    }

    /**
     * Checks to see if the specified slider range point is within a specified threshold in cases where the drag & drop
     * action cannot exactly select the desired value, and the current value has gone beyond the expected value
     *
     * @param rangePoint         Min or Max ("Price Range" slider), left or right, low or high, etc.
     * @param desiredFilterValue Desired value for the range point
     * @param rangeThreshold     Acceptable threshold for the range point value. For the left slider point, the threshold
     *                           will always be a value higher than desired to allow for exact matching. Same for the right
     *                           slider point except the value will always be slightly lower than desired
     * @return True if a value was found within the specified threshold, else False
     */
    private boolean checkForValueInApproximateRange(String rangePoint, String desiredFilterValue, int rangeThreshold) {
        LOGGER.info("checkForValueInApproximateRange started for range point: \"" + rangePoint
                + "\", desired value of: \"" + desiredFilterValue + "\" and price threshold of: \""
                + rangeThreshold + "\"");
        int currentFilterValue = Integer.parseInt(getCurrentPriceRangePoint(rangePoint));
        int expectedFilterValue = Integer.parseInt(desiredFilterValue);

        if (rangePoint.equalsIgnoreCase(MIN) && currentFilterValue > expectedFilterValue) {
            if (currentFilterValue - expectedFilterValue <= rangeThreshold) {
                minValueApproximate = currentFilterValue;
                LOGGER.info("Could not select exact amount of \"" + desiredFilterValue
                        + "\" for MIN price range point, instead selected approximate amount of \""
                        + String.valueOf(minValueApproximate) + "\"");
                return true;
            } else {
                Assert.fail("FAIL: Could NOT select the exact amount of \"" + desiredFilterValue
                        + "\" nor an amount within $\"" + rangeThreshold + "\" over, for the MIN price range point!");
            }
        }

        if (rangePoint.equalsIgnoreCase(MAX) && currentFilterValue < expectedFilterValue) {
            if (expectedFilterValue - currentFilterValue <= rangeThreshold) {
                maxValueApproximate = currentFilterValue;
                LOGGER.info("Could not select exact amount of \"" + desiredFilterValue
                        + "\" for MAX price range point, instead selected approximate amount of \""
                        + String.valueOf(maxValueApproximate) + "\"");
                return true;
            } else {
                Assert.fail("FAIL: Could NOT select the exact amount of \"" + desiredFilterValue
                        + "\" nor an amount within $\"" + rangeThreshold + "\" under, for the MAX price range point!");
            }
        }
        return false;
    }

    /**
     * Gets the current value for the specified range point (Min or Max) of the "Price Range" slider filter. Method
     * cleans the string such that just the numeric portion is returned
     *
     * @param rangePoint String for the range point (Min or Max)
     * @return String containing current value of the specified range point
     */
    private String getCurrentPriceRangePoint(String rangePoint) {
        LOGGER.info("getCurrentPriceRangePoint started for range point '" + rangePoint + "'");
        String currentPriceRange = driver.getDisplayedElement(currentPriceRangeBy, Constants.ZERO).getText();

        switch (rangePoint.toLowerCase()) {
            case MIN:
                LOGGER.info("getCurrentPriceRangePoint returning current minimum 'Price Range' point");
                currentPriceRange = StringUtils.substringBetween(currentPriceRange, "$", " -");
                break;
            case MAX:
                LOGGER.info("getCurrentPriceRangePoint returning current maximum 'Price Range' point");
                currentPriceRange = StringUtils.substringAfterLast(currentPriceRange, "$");
                break;
            default:
                Assert.fail("FAIL: cannot get current value of \"Price Range\" point: \"" + rangePoint + "\"!");
        }
        return currentPriceRange;
    }

    /**
     * Sets and adjusts the x axis offset value dependent upon the difference between the current slider point's value
     * and the expected / desired value. Since currently the value of 1 unit of offset appears to be equal to 3 dollars,
     * the default value and ranges are based on multiples of 3.
     *
     * @param rangePoint          String for the range point (Min or Max)
     * @param xOffsetValue        Int for the current xOffsetValue
     * @param expectedFilterValue Int for the desired / expected filter value
     * @return Int defaulting to the initially passed in value; 2 if between the range of 90 and 18; 1 if below 18
     */
    private int setXoffsetValue(String rangePoint, int xOffsetValue, int expectedFilterValue) {
        LOGGER.info("setXoffsetValue started with range point: \"" + rangePoint
                + "\", default offset value for x: \"" + xOffsetValue + "\" and expected filter value: \""
                + expectedFilterValue + "\"");
        int filterValueDifference;
        int currentFilterValue = Integer.parseInt(getCurrentPriceRangePoint(rangePoint));

        if (rangePoint.equalsIgnoreCase(MIN)) {
            filterValueDifference = expectedFilterValue - currentFilterValue;
        } else {
            filterValueDifference = currentFilterValue - expectedFilterValue;
        }

        if (filterValueDifference <= 90 && filterValueDifference >= 18) {
            xOffsetValue = 2;
            LOGGER.info("Using xOffsetValue of: \"" + xOffsetValue + "\"");
        }

        if (filterValueDifference <= 18) {
            xOffsetValue = 1;
            LOGGER.info("Using xOffsetValue of: \"" + xOffsetValue + "\"");
        }

        if (rangePoint.equalsIgnoreCase(MAX)) {
            xOffsetValue = -xOffsetValue;
        }

        LOGGER.info("setXoffsetValue completed with range point: \"" + rangePoint
                + "\", default offset value for x: \"" + xOffsetValue + "\" and expected filter value: \""
                + expectedFilterValue + "\"");
        return xOffsetValue;
    }

    /**
     * Verify all products on PLP display full image size
     */
    public void assertAllProductsDisplayFullImageSize() {
        LOGGER.info("assertAllProductsDisplayFullImageSize started");
        int i = 0;
        boolean fullSizeImage = true;
        String imgHeightSize = null;
        String imgWidthSize = null;
        List<WebElement> productsImageSpecs = webDriver.findElements(productListImageBy);

        for (WebElement productsImageSpec : productsImageSpecs) {
            imgHeightSize = productsImageSpec.getCssValue(IMG_MAX_HEIGHT_ATTR);
            imgWidthSize = productsImageSpec.getCssValue(IMG_MAX_WIDTH_ATTR);

            if (!imgHeightSize.equalsIgnoreCase(PRODUCT_IMG_MAX_HEIGHT_SIZE) &&
                    !imgWidthSize.equalsIgnoreCase(PRODUCT_IMG_MAX_WIDTH_SIZE)) {
                fullSizeImage = false;
                break;
            }
            i++;
        }
        if (!fullSizeImage)
            Assert.fail("FAIL: Product " + productsImageSpecs.get(i).getAttribute(Constants.ATTRIBUTE_ALT) +
                    " did NOT display full image specification on PLP!");

        LOGGER.info("assertAllProductsDisplayFullImageSize completed");
    }

    /**
     * Verifies the selected category appears in the URL path
     *
     * @param category selected category to appear on the page/URL path
     * @param brand whether or not you're verifying a brand catalog
     */
    public void verifyCatalogPage(String category, boolean brand) {
        LOGGER.info("verifyCatalogPage started");
        String brandCategoryUrlPath = "";
        verifyPLPdisplayedWithResults();
        if (webDriver.getCurrentUrl().contains(Constants.WHEEL.toLowerCase())) {
            if (category.equalsIgnoreCase(Constants.MB_WHEELS) || (category.equalsIgnoreCase(Constants.AMERICAN_OUTLAW)))
                brandCategoryUrlPath = getCategoryWheelUrlPath(category.replace(" ", "-").toLowerCase(), brand);
            else
                brandCategoryUrlPath = getCategoryWheelUrlPath(category.split(" ")[0].toLowerCase(), brand);
        } else
            brandCategoryUrlPath = getCategoryTireUrlPath(category.split(" ")[0].toLowerCase(), brand);
        commonActions.waitForUrl(brandCategoryUrlPath, Constants.TEN_SEC_WAIT);

        LOGGER.info("verifyCatalogPage completed");
    }

    /**
     * Returns partial URL path for tire catalog containing category
     *
     * @param category selected category to appear in URL
     * @param brand    whether or not you're verifying a brand catalog
     */
    private String getCategoryTireUrlPath(String category, boolean brand) {
        LOGGER.info("getCategoryTireUrlPath started with category = " + category + ", and brand = " + brand);
        if (brand) {
            String path = "/tires/brands/" + category + "-catalog";
            LOGGER.info("getCategoryTireUrlPath returning brand path = " + path);
            return path;
        } else {
            String path = "/tires/" + category + "-catalog";
            LOGGER.info("getCategoryTireUrlPath returning path = " + path);
            return path;
        }
    }

    /**
     * Returns partial URL path for wheel catalog containing category
     *
     * @param category selected category to appear in URL
     * @param brand    whether or not you're verifying a brand catalog
     */
    private String getCategoryWheelUrlPath(String category, boolean brand) {
        LOGGER.info("getCategoryWheelUrlPath started with category = " + category + ", and brand = " + brand);
        if (brand) {
            String path = "/wheels/brands/" + category + "-catalog";
            LOGGER.info("getCategoryWheelUrlPath returning brand path = " + path);
            return path;
        } else {
            String path = "/wheels/" + category + "-catalog";
            LOGGER.info("getCategoryWheelUrlPath returning path = " + path);
            return path;
        }
    }

    /**
     * Verify the PLP page is displayed and products are listed
     */
    public void verifyPLPdisplayedWithResults() {
        LOGGER.info("verifyPLPdisplayedWithResults started");
        if (!Config.isMobile()) {
            Assert.assertTrue("FAIL: The PLP page is not displayed",
                    plpResultsHeader.getText().toLowerCase().contains(Constants.RESULTS));
        }
        Assert.assertTrue("FAIL: There are no products listed on the PLP page",
                webDriver.findElements(plpResultsRowBy).size() > 0);
        LOGGER.info("verifyPLPdisplayedWithResults completed");
    }

    /**
     * Assert provided for validating the Top 3 Tiles section is displayed
     */
    public void assertTop3TilesComponentIsDisplayed() {
        LOGGER.info("assertTop3TilesComponentIsDisplayed started");
        Assert.assertTrue("FAIL: Top 3 Tiles component not found on the product results page!",
                driver.isElementDisplayed(top3Component, Constants.TWO_SEC_WAIT));
        LOGGER.info("assertTop3TilesComponentIsDisplayed completed");
    }

    /**
     * Assert provided for validating the values in the Top 3 Tiles section
     *
     * @param value1 The first expected value in the Top 3 Tiles section
     * @param value2 The second expected value in the Top 3 Tiles section
     * @param value3 The third expected value in the Top 3 Tiles section
     */
    public void assertTop3TileValues(String value1, String value2, String value3) {
        LOGGER.info("assertTop3TileValues started");
        List<String> expectedValues = Arrays.asList(value1, value2, value3);
        List<WebElement> top3Tiles = webDriver.findElements(top3TilesBy);
        //verify if all three top tiles options found
        if (top3Tiles.size() != expectedValues.size()) {
            Assert.fail("FAIL: All three top tiles options not displayed");
        }
        int i = 0;
        for (WebElement top3Tile : top3Tiles) {
            String className = top3Tile.getAttribute(Constants.CLASS);
            String actualTile = className.split(" --")[1].replace("-", " ");
            Assert.assertTrue("FAIL: Expected Tile Value : " + expectedValues.get(i) + ", but actual Value was: "
                    + actualTile + " ! ", actualTile.equalsIgnoreCase(expectedValues.get(i)));
            i++;
        }
        LOGGER.info("assertTop3TileValues started");
    }

    /**
     * Extracts a Product on the position
     *
     * @param position The specific value of rendered product position
     */
    public String extractProductOnPLP(int position) throws Exception {
        LOGGER.info("extractProductOnPLP started");
        driver.waitForPageToLoad();
        List<WebElement> items = webDriver.findElements(brandNameBy);
        if (position <= items.size()) {
            plpBrandName = items.get(position - 1).getText();
        } else
            LOGGER.info("The position is outside the Result list ");
        LOGGER.info(" The Extracted item code is " + plpBrandName);
        LOGGER.info("extractProductOnPLP completed");
        return plpBrandName;
    }

    /**
     * Assert provided for item in Top 3 Tiles with brand in the result list
     *
     * @param expectedBrandName The brand to be verified
     * @param position          The position of the expected brand
     */
    public void assertProductInTop3Tiles(String expectedBrandName, int position) {
        LOGGER.info("assertProductsOnTop3Tiles started");
        driver.waitForPageToLoad();
        List<WebElement> top3TilesBrands = webDriver.findElements(top3TilesBrandBy);
        Assert.assertTrue("FAIL: Item Code didn't match! (displayed:-> "
                        + top3TilesBrands.get(position - 1).getText() + " expected:->  " + expectedBrandName + ")"
                , top3TilesBrands.get(position - 1).getText().toLowerCase().contains(expectedBrandName.toLowerCase()));
        LOGGER.info("assertProductsOnTop3Tiles completed");
    }

    /**
     * Assert provided to check for a filter not part of the facet
     *
     * @param checkboxLabel The filter to be validated
     */
    public void assertFilterNotDisplayed(String checkboxLabel) {
        LOGGER.info("assertFilterNotDisplayed started");
        driver.waitForPageToLoad();
        List<WebElement> filterOptions = webDriver.findElements(filterOptionBy);
        for (WebElement filterOption : filterOptions) {
            Assert.assertTrue("FAIL: The facet filter was displayed , when it was not expected to be displayed "
                    , (!(filterOption.getText().equalsIgnoreCase(checkboxLabel))));
        }
        LOGGER.info("assertFilterNotDisplayed started");
    }

    /**
     * Click Read Reviews link on PLP
     */
    public void selectReadReviewsLink() {
        LOGGER.info("selectReadReviewsLink started");
        driver.waitForPageToLoad();
        driver.jsClick(readReviewsPLP);
        LOGGER.info("selectReadReviewsLink completed");
    }

    /**
     * Assert provided to check for Check Inventory Link on the PLP page
     */
    public void assertCheckInventoryLink(String itemCode) {
        LOGGER.info("assertCheckInventoryLink started");
        driver.waitForPageToLoad();
        WebElement grandParent = driver.getParentElement(
                driver.getParentElement(
                        driver.getElementWithText(CommonActions.itemIdBy, itemCode)));
        Assert.assertTrue("The Check Inventory link is missing for the itemCode" + itemCode,
                driver.isElementDisplayed(grandParent.findElement(checkInventoryBy)));
        LOGGER.info("assertCheckInventoryLink completed");
    }

    /**
     * Click on product
     *
     * @param itemCode product code
     */
    public void clickOnProduct(String itemCode) {
        LOGGER.info("clickOnProduct started");
        driver.waitForPageToLoad();
        WebElement product = driver.getParentElement(
                driver.getElementWithText(CommonActions.itemIdBy, itemCode)).findElement(productBy);
        driver.jsScrollToElementClick(product);
        driver.waitForPageToLoad();
        LOGGER.info("clickOnProduct completed");
    }
}