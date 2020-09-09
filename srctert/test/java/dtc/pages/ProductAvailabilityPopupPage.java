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

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 1/11/17.
 */
public class ProductAvailabilityPopupPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(FitmentPopupPage.class.getName());

    public ProductAvailabilityPopupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "product-availability")
    public static WebElement productAvailability;
    
    @FindBy(id = "productAddToCartDiv")
    public static WebElement productAddToCart;
    
    @FindBy(className = "message-stock")
    public static WebElement stockInfo;
    
    @FindBy(className = "icon-close")
    public static WebElement iconClose;

    @FindBy(className =  "product-availability__headline")
    public static WebElement productAvailabilityHeadline;

    @FindBy(id =  "checkinventory_locationForSearch")
    public static WebElement zipWeb;

    @FindBy(id =  "search-zip-input")
    public static WebElement zipMobile;

    @FindBy(className =  "product-availability__search-go")
    public static WebElement zipGoButton;

    @FindBy(className =  "product-availability__store-city")
    public static WebElement storeCity;

    @FindBy(className =  "jsInStock")
    public static WebElement inStockFilter;

    @FindBy(className =  "jsOrMore")
    public static WebElement orMoreFilter;

    public static final By storeAddress = By.className("product-availability__store-address");

    public static final By myStoreEle = By.className("product-availability__store-current");

    public static final By quantityBy = By.className("product-availability__product-qty");

    public static final By quantityErrorBy = By.className("product-availability__error--qty");

    public static final By productInfoBy = By.className("product-availability__product-info");

    public static final By productImageBy = By.className("product-availability__product-image");

    public static final By addToCartBy = By.className("product-availability__product-add-cart");

    public static final By filterQuantityBy = By.className("js-qtyCount");

    public static final By zipcodeErrorBy = By.className("product-availability__error--zip");

    public static final By priceBy = By.className("product-availability__product-price");

    public static final By loadingOverlayBy = By.className("product-availability__loading-overlay");

    public static final By stockQuantityBy = By.className("auto-message-stock");

    public static final By store = By.className("product-availability__store");

    public static final By currentStoreBy = By.className("product-availability__store--selected");

    public static final By makeMyStoreBy = By.xpath("//button[normalize-space()='Make My Store' and contains(@style,'block;')]");

    public static final String DISPLAY_STATUS = "display: block;";
    public static final String MESSAGE_STOCK = "message-stock";
    private static final String QUANTITY = "quantity";
    private static final String VALIDITY = "validity";
    private static final String QUANTITY_ERROR = "Please enter a qty under 100";
    private static final String VALIDITY_ERROR = "Please enter a number";
    private static final String ZIPCODE_ERROR = "Enter a 5 digit postal code";
    private static final String IN_STOCK = "In stock";
    private static final String OR_MORE = "Or more";

    /**
     * Confirms the display of the Success pop-up message after sending a store location to a phone via the
     * 'Send to Phone' pop-up
     */
    public void assertCheckAvailabilityPopupLoaded() {
        LOGGER.info("assertCheckAvailabilityPopupLoaded started");
        Assert.assertTrue("FAIL: Check Availability popup did not load.",
                driver.isElementDisplayed(productAvailability, Constants.DEFAULT_SEC_WAIT));
        LOGGER.info("assertCheckAvailabilityPopupLoaded completed");
    }
    
    /**
     * Confirms the InStock Label is not present on Check Availability page
     */
    public void assertInStockLabelNotPresent() {
        LOGGER.info("assertInStockLabelNotPresent started");
        driver.waitForElementVisible(stockInfo);
        if(stockInfo.getText().equals(ConstantsDtc.IN_STOCK)){
        	 Assert.fail("In Stock label is present on Check Availability popup ");
         }
        LOGGER.info("assertInStockLabelNotPresent completed");
    }
    
    /**
     * Confirms the Special Order Label is present on Check Availability page
     */
    public void assertSpecialOrderLabelPresent() {
        LOGGER.info("assertSpecialOrderLabelPresent started");
        driver.waitForElementVisible(productAddToCart);
        WebElement stockMessage = productAddToCart.findElement(By.className(MESSAGE_STOCK));
        if(!stockMessage.getText().equalsIgnoreCase(ConstantsDtc.SPECIAL_ORDER)){
        	 Assert.fail("Special Order label is not present on Check Availability popup ");
         }
        LOGGER.info("assertSpecialOrderLabelPresent completed");
    }
    
    /**
     * Close Check Availability Page Popup
     */
    public void closeCheckAvailabilityPopupModal() {
        LOGGER.info("closeCheckAvailabilityPopupModal started");
        driver.waitForElementVisible(iconClose);
        iconClose.click();
        LOGGER.info("closeCheckAvailabilityPopupModal completed");
    }
    
    /**
     * Confirms the Default Store My Store label is positioned at the top
     */
    public void assertDefaultStoreMyStoreLabelIsTopInTheList() {
        LOGGER.info("assertDefaultStoreMyStoreLabelIsTopInTheList started");
        driver.waitForElementVisible(myStoreEle);
        
        List<WebElement> myStoreRows = webDriver.findElements(myStoreEle);

        if(webDriver.findElements(storeAddress).get(0).getText().toLowerCase().contains(Config.getDefaultStoreCity())){
        	Assert.assertTrue("FAIL: MY STORE Label is not positioned at the top in listed stores list",
        			myStoreRows.get(0).getAttribute(ConstantsDtc.ATTR_STYLE).equalsIgnoreCase(DISPLAY_STATUS));
        }else
        	Assert.fail("Selected Default Store "+ Config.getDefaultStoreCity()+ " is not the top store in the list ");
        LOGGER.info("assertDefaultStoreMyStoreLabelIsTopInTheList completed");
    }

    /**
     * Confirms the Special Order Label is present on Check Availability page
     *
     * @param expectedHeadline Expected headline
     */
    public void assertCheckAvailabilityHeadline(String expectedHeadline) {
        LOGGER.info("assertCheckAvailabilityHeadline started");
        String actualHeadline;
        driver.waitForElementVisible(productAvailabilityHeadline);
        actualHeadline = productAvailabilityHeadline.getText();
        Assert.assertEquals("FAIL: Expected headline: \"" + expectedHeadline + "\", Actual title: \"" + actualHeadline + "\"",
                expectedHeadline.toLowerCase(), actualHeadline.toLowerCase());
        LOGGER.info("Confirmed that the page title is \"" + expectedHeadline + "\".");
        LOGGER.info("assertCheckAvailabilityHeadline completed");
    }

    /**
     * Enters a quantity value on the Product Availability popup
     *
     * @param quantity The quanitity to enter
     */
    public void enterQuantity(String quantity) {
        LOGGER.info("enterQuantity started");

        driver.waitForClassPresent(driver.getByValue(quantityBy), Constants.DEFAULT_SEC_WAIT);
        WebElement quantityEl = driver.getDisplayedElement(quantityBy, Constants.ZERO);

        driver.jsScrollToElement(quantityEl);
        quantityEl.click();
        quantityEl.clear();
        quantityEl.sendKeys(quantity);

        LOGGER.info("enterQuantity completed");
    }

    /**
     * Verifies that an invalid quantity error message is displayed
     *
     * @param errorType The type of error to expect
     */
    public void assertQuanityErrorMessage(String errorType) {
        LOGGER.info("assertQuanityErrorMessage started");
        String actualError;
        String errorMessage = "";

        if (errorType.equalsIgnoreCase(QUANTITY)) {
            errorMessage = QUANTITY_ERROR;
        } else if (errorType.equalsIgnoreCase(VALIDITY)) {
            errorMessage = VALIDITY_ERROR;
        }

        driver.waitForClassPresent(driver.getByValue(quantityErrorBy), Constants.DEFAULT_SEC_WAIT);
        WebElement quantityErrorEl = driver.getDisplayedElement(quantityErrorBy, Constants.TWO_SEC_WAIT);
        actualError = quantityErrorEl.getText();

        Assert.assertTrue("FAIL: Expected error message: \"" + errorMessage + "\", Actual message: \"" + actualError + "\"",
                actualError.contains(errorMessage));

        LOGGER.info("Confirmed error message \"" + errorMessage + "\" was displayed.");

        LOGGER.info("assertQuanityErrorMessage completed");
    }

    /**
     * Verifies that the Add To Cart button is disabled
     */
    public void assertAddToCartDisabled() {
        LOGGER.info("assertAddToCartDisabled started");
        WebElement addToCartButton = driver.getDisplayedElement(addToCartBy, Constants.ZERO);
        Assert.assertTrue("FAIL: Add to cart button was not disabled.", !addToCartButton.isEnabled());
        LOGGER.info("Confirmed Add To Cart button was disabled");
        LOGGER.info("assertAddToCartDisabled completed");
    }

    /**
     * Verifies that the product image is displayed
     */
    public void assertProductImageDisplayed() {
        LOGGER.info("assertProductImageDisplayed started");

        driver.waitForClassPresent(driver.getByValue(productImageBy), Constants.DEFAULT_SEC_WAIT);

        Assert.assertTrue("FAIL: Product image not displayed.",
                driver.getDisplayedElement(productImageBy, Constants.ZERO) != null);

        LOGGER.info("assertProductImageDisplayed completed");
    }

    /**
     * Verifies product info on the Check Availability popup
     *
     * @param text The text to verify
     */
    public void assertProductInfo(String text) {
        LOGGER.info("assertProductInfo started");

        driver.waitForClassPresent(driver.getByValue(productInfoBy), Constants.DEFAULT_SEC_WAIT);
        WebElement productInfoEl = driver.getDisplayedElement(productInfoBy, Constants.ZERO);

        Assert.assertTrue("FAIL: Product info did not contain expected text,  \"" + text + "\".",
                productInfoEl.getText().toLowerCase().contains(text.toLowerCase()));

        LOGGER.info("Expected text \"" + text + "\" was listed in product info.");

        LOGGER.info("assertProductInfo completed");
    }

    /**
     * Verifies price is populated
     */
    public void assertPricePopulated() {
        LOGGER.info("assertPricePopulated started");

        driver.waitForClassPresent(driver.getByValue(priceBy), Constants.DEFAULT_SEC_WAIT);
        WebElement priceEl = driver.getDisplayedElement(priceBy, Constants.ZERO);

        commonActions.assertElementTextPopulated(priceEl);

        LOGGER.info("assertPricePopulated completed");
    }


    /**
     * Verifies the quantity value in the show quantity filter
     *
     * @param quantity The quantity to verify
     */
    public void assertShowQuantityFilter(String quantity) {
        LOGGER.info("assertShowQuantityFilter started");

        driver.waitForClassPresent(driver.getByValue(filterQuantityBy), Constants.DEFAULT_SEC_WAIT);
        WebElement showQuantityFilterEl = driver.getDisplayedElement(filterQuantityBy, Constants.ZERO);

        Assert.assertTrue("FAIL: The show quantity filter did not show expected quantity,  \"" + quantity + "\".",
                showQuantityFilterEl.getText().contains(quantity));

        LOGGER.info("Expected quantity \"" + quantity + "\" was listed in the show quantity filter.");

        LOGGER.info("assertShowQuantityFilter completed");
    }

    /**
     * Enters a quantity value on the Product Availability popup
     *
     * @param zipcode The zipcode to enter
     */
    public void enterZipcode(String zipcode) {
        LOGGER.info("enterZipcode started");

        WebElement zipCodeEl = null;

        if (Config.isMobile()) {
            zipCodeEl = zipMobile;
        } else {
            zipCodeEl = zipWeb;
        }

        driver.waitForElementVisible(zipCodeEl);
        driver.jsScrollToElement(zipCodeEl);
        zipCodeEl.click();
        zipCodeEl.clear();
        zipCodeEl.sendKeys(zipcode);

        LOGGER.info("enterZipcode completed");
    }

    /**
     * Clicks Go and waits for results to load
     *
     * @throws Exception  Descriptive exception message
     */
    public void clickGoAndWaitForResults() {
        LOGGER.info("clickGoAndWaitForResults started");

        try {
            driver.waitForElementClickable(zipGoButton);
            zipGoButton.click();

            if (driver.isElementDisplayed(loadingOverlayBy, Constants.TWO_SEC_WAIT)) {
                driver.waitForElementNotVisible(loadingOverlayBy);
            }
        } catch (Exception e) {
            Assert.fail("FAIL: There was an issue with clicking Go, most likely a zip with no results.");
        }

        LOGGER.info("clickGoAndWaitForResults completed");
    }

    /**
     * Verifies that an invalid zipcode error message is displayed
     */
    public void assertZipcodeErrorMessage() {
        LOGGER.info("assertZipcodeErrorMessage started");
        String actualError;

        driver.waitForClassPresent(driver.getByValue(zipcodeErrorBy), Constants.DEFAULT_SEC_WAIT);
        WebElement zipcodeErrorEl = driver.getDisplayedElement(zipcodeErrorBy, Constants.TWO_SEC_WAIT);
        actualError = zipcodeErrorEl.getText();

        Assert.assertTrue("FAIL: Expected error message: \"" + ZIPCODE_ERROR + "\", Actual message: \"" + actualError + "\"",
                actualError.contains(ZIPCODE_ERROR));

        LOGGER.info("Confirmed error message \"" + ZIPCODE_ERROR + "\" was displayed.");

        LOGGER.info("assertZipcodeErrorMessage completed");
    }

    /**
     * Verifies that the first store displayed is within a certain distance in miles
     *
     * @param milesString The miles to verify against
     */
    public void assertFirstStoreWithinDistance(String milesString) {
        LOGGER.info("assertFirstStoreWithinDistance started");

        String actualMilesString;
        int actualMiles;
        int miles = Integer.parseInt(milesString);
        driver.waitForElementVisible(storeCity);
        actualMilesString = storeCity.getText().split(" ")[0];

        try {
            actualMiles = Integer.parseInt(actualMilesString);
        } catch (NumberFormatException e) {
            actualMiles = (int) Double.parseDouble(actualMilesString);
        }

        Assert.assertTrue("FAIL: Expected store city to be within \"" + miles + "\" miles.",
                miles - actualMiles > 0);

        LOGGER.info("Confirmed that first store was within \"" + miles + "\".");

        LOGGER.info("assertFirstStoreWithinDistance completed");
    }

    /**
     * Clicks specified filter
     *
     * @param filter The filter to click
     */
    public void clickFilter(String filter) {
        LOGGER.info("clickFilter started");

        WebElement filterEl;

        if (filter.equalsIgnoreCase(IN_STOCK)) {
            filterEl = inStockFilter;
        } else {
            filterEl = orMoreFilter;
        }

        //filter checkboxes cannot be found by webdriver waits
        driver.waitForElementVisible(zipGoButton);
        driver.jsClick(filterEl);

        if (driver.isElementDisplayed(loadingOverlayBy, Constants.TWO_SEC_WAIT)) {
            driver.waitForElementNotVisible(loadingOverlayBy);
        }


        LOGGER.info("clickFilter completed");
    }

    /**
     * Verifies that the first store displayed is within a certain distance in miles
     *
     * @param quantity The quantity to verify the value is above
     */
    public void assertFirstStoreQuantityAboveSpecifiedValue(String quantity) {
        LOGGER.info("assertFirstStoreQuantityAboveSpecifiedValue started");

        String stockQuantityValue;

        driver.waitForClassPresent(driver.getByValue(stockQuantityBy), Constants.DEFAULT_SEC_WAIT);
        WebElement stockQuantityEl = driver.getDisplayedElement(stockQuantityBy, Constants.ZERO);
        stockQuantityValue = stockQuantityEl.getText();

        Assert.assertTrue("FAIL: Stock quantity was not above \"" + quantity + "\", but was \"" +
                        stockQuantityValue + "\".", Integer.parseInt(stockQuantityValue) > 0);

        LOGGER.info("Confirmed that first store quantity was above \"" + quantity + "\".");

        LOGGER.info("assertFirstStoreQuantityAboveSpecifiedValue completed");
    }

    /**
     * Clicks specified store
     *
     * @param storeNum The store to click
     */
    public void selectStore(int storeNum) {
        LOGGER.info("selectStore started");

        driver.waitForElementVisible(store);
        List <WebElement> stores = webDriver.findElements(store);
        stores.get(storeNum).click();

        if (driver.isElementDisplayed(loadingOverlayBy, Constants.TWO_SEC_WAIT)) {
            driver.waitForElementNotVisible(loadingOverlayBy);
        }

        LOGGER.info("selectStore completed");
    }

    /**
     * Verifies that the specified store is the current store
     *
     * @param storeNum The store number to verify
     */
    public void assertStoreIsCurrentStore(int storeNum) {
        LOGGER.info("assertStoreIsCurrentStore started");

        WebElement currentStore;

        driver.waitForElementVisible(store);
        List <WebElement> stores = webDriver.findElements(store);
        currentStore = stores.get(storeNum);

        Assert.assertTrue("FAIL: Store # \"" + storeNum + "\" was not current store.",
                currentStore.getAttribute(Constants.CLASS).contains(driver.getByValue(currentStoreBy)));

        LOGGER.info("Confirmed that store # \"" + storeNum + "\" was the current store.");

        LOGGER.info("assertStoreIsCurrentStore completed");
    }

    /**
     * Verifies that Make My Store button is present on the page
     */
     public void assertMakeMyStoreButtonDisplayed() {
         LOGGER.info("assertMakeMyStoreButtonDisplayed started");
         driver.waitForElementVisible(makeMyStoreBy);

         int storeListEntryForMakeMyStoreCount = 0;

         List<WebElement> rows = webDriver.findElements(makeMyStoreBy);

         for (WebElement row : rows) {
             if (!row.getAttribute(ConstantsDtc.ATTR_STYLE).contains(Constants.NONE)) {
                 Assert.assertTrue("FAIL: MAKE MY STORE button is missing on the page for row number " + storeListEntryForMakeMyStoreCount,
                         row.getText().contains(ConstantsDtc.MAKE));

                 storeListEntryForMakeMyStoreCount++;
             }
         }
         LOGGER.info("assertMakeMyStoreButtonDisplayed completed");
     }
}