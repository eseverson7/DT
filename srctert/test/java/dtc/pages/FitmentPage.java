package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtils;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 1/30/17.
 */
public class FitmentPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(FitmentPage.class.getName());

    public FitmentPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String YEAR = "Year";
    private static final String MAKE = "Make";
    private static final String MODEL = "Model";
    private static final String TRIM = "Trim";
    private static final String ASSEMBLY = "Assembly";
    private static final String TIRE_WIDTH = "Tire Width";
    private static final String ASPECT_RATIO = "Aspect Ratio";
    private static final String TIRE_DIAMETER = "Tire Diameter";
    private static final String WHEEL_DIAMETER = "Wheel Diameter";
    private static final String WHEEL_WIDTH = "Wheel Width";
    private static final String BOLT_PATTERN = "Bolt Pattern";
    private static final String BRAND = "brand";
    private static final String MYVEHICLES = "my vehicles";
    private static final String SHOP_BY_VEHICLE = "Vehicle";
    private static final String SHOP_BY_SIZE = "Size";
    private static final String SHOP_BY_BRAND = "Brand";
    private static final String SHOP_BY_TIRE_SIZE = "Tire Size";
    private static final String SHOP_BY_WHEEL_SIZE = "Wheel Size";
    private static final String VEHICLE_SEARCH = "Vehicle Search";
    private static final String TIRE_SIZE_SEARCH = "Tire Size Search";
    private static final String WHEEL_SIZE_SEARCH = "Wheel Size Search";
    private static final String PERIOD_DECIMAL = ".";
    private static final String ARROW_UP = "upwards";
    private static final String ARROW_DOWN = "downwards";
    private static final String NO_RESULTS_MESSAGE = " did not match any products that fit your current vehicle.";
    private static final String GREY_COLOR = "rgb(102, 102, 102)";

    private String dropDownArrowUpXpath = "//div[contains(@class, \"%s\")]//div/i[contains(@class,'fa-angle-up')]";

    private String dropDownArrowDownXpath = "//div[contains(@class, \"%s\")]//div/i[contains(@class,'fa-angle-down')]";

    private static final String[] SHOP_BY_VEHICLE_DEFAULT_DROPDOWN_VALUES =
            {"Year", "Make", "Model", "Trim"};
    private static final String[] SHOP_BY_TIRE_SIZE_DEFAULT_DROPDOWN_VALUES =
            {"Width", "Ratio", "Diameter"};
    private static final String[] SHOP_BY_WHEEL_SIZE_DEFAULT_DROPDOWN_VALUES =
            {"Diameter", "Wheel Width", "Bolt Pattern"};
    private static final String[] SHOP_BY_BRAND_DEFAULT_DROPDOWN_VALUES =
            {"Search for a partial or full brand name..."};

    private static final String radioButtonActive = "fitment-component__radio-select--active";

    private static final String searchButtonDisabled = "fitment-component__find-button--disabled";

    private static final String searchButtonClassName = ".fitment-component__find-button";

    private static By dropDownMenuBy = By.className("dropdown-menu");

    private static By radioButtonBy = By.className("fitment-component__toggle-button");

    private static By fitmentComponentOptionBy = By.className("fitment-component__toggle-label");

    private static By reactSelectizeInputBy = By.className("resizable-input");

    private static By selectBy = By.className("react-selectize-control");

    private static By selectOptionBy = By.className("option-wrapper");

    private static By shopTabBy = By.className("fitment-component__shop-by-button");

    private static By noResultsDescriptionBy = By.className("no-results__description");

    private static final By reactSelectizePlaceholderBy = By.className("react-selectize-placeholder");

    @FindBy(className = "fitment-component__find-button")
    public static WebElement searchButton;

    @FindBy(className = "react-selectize-control")
    public static WebElement selectControl;

    @FindBy(className = "fitmentSelectedOptionBox")
    public static WebElement fitmentSelectedBtn;

    @FindBy(className = "auto-fitment-year")
    public static WebElement dropDownYear;

    @FindBy(className = "auto-fitment-make")
    public static WebElement dropDownMake;

    @FindBy(className = "auto-fitment-model")
    public static WebElement dropDownModel;

    @FindBy(className = "auto-fitment-trim")
    public static WebElement dropDownTrim;

    @FindBy(className = "auto-fitment-assembly")
    public static WebElement dropDownAssembly;

    @FindBy(className = "auto-fitment-tire-width")
    public static WebElement dropDownTireWidth;

    @FindBy(className = "auto-fitment-aspect-ratio")
    public static WebElement dropDownAspectRatio;

    @FindBy(className = "auto-fitment-tire-diameter")
    public static WebElement dropDownTireDiameter;

    @FindBy(className = "auto-fitment-wheel-diameter")
    public static WebElement dropDownWheelDiameter;

    @FindBy(className = "auto-fitment-wheel-width")
    public static WebElement dropDownWheelWidth;

    @FindBy(className = "auto-fitment-wheel-bolt-pattern")
    public static WebElement dropDownWheelBoltPattern;

    @FindBy(className = "fitment-component__select-brand")
    public static WebElement dropDownBrand;

    @FindBy(className = "fitment-component__find-button")
    public static WebElement fitmentComponentFindButton;

    @FindBy(className = "auto-fitment-brand-tab")
    public static WebElement shopByBrand;

    @FindBy(className = "auto-fitment-size-tab")
    public static WebElement shopBySize;

    @FindBy(className = "auto-fitment-vehicle-tab")
    public static WebElement shopByVehicle;

    @FindBy(className = "fitment-box__close")
    public static WebElement popupCloseBtn;

    @FindBy(css = ".fitment-component__select-brand > div > div")
    public static WebElement dropDownBrandTextPlaceholderElement;

    /**
     * Selects dropdown value from specified fitment dropdown
     *
     * @param dropDown      The dropDown By (Year, Make, etc)
     * @param dropDownValue The value to select
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void selectDropDownValue(WebElement dropDown, String dropDownValue) throws Exception {
        LOGGER.info("selectDropDownValue started");

        if (Config.isIe()) {
            //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
            driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);
            driver.waitForElementClickable(dropDown);
        } else {
            driver.waitForElementClickable(selectControl);
        }
        driver.jsScrollToElement(dropDown);

        if (Config.isIphone())
            driver.waitForMilliseconds();

        //Expanding dropdown if it is not already expanded
        if (!dropDown.getAttribute(Constants.CLASS).contains(Constants.OPEN)) {
            //Note: Menu only expands in ie/safari/mobile safari when clicking input element
            if (Config.isSafari() || Config.isIphone() || Config.isIpad() || Config.isIe()) {
                dropDown.findElement(reactSelectizeInputBy).click();
            } else if (Config.isFirefox()) {
                dropDown.findElement(reactSelectizePlaceholderBy).click();
            } else {
                dropDown.click();
            }
        }

        driver.waitForElementAttribute(dropDown, Constants.CLASS, Constants.OPEN);

        if (!dropDownValue.equalsIgnoreCase(Constants.NONE)) {
            if (Config.isFirefox()) {
                dropDown.findElement(reactSelectizeInputBy).sendKeys(dropDownValue);
            }

            WebElement dropDownOption = getDropDownOptionElement(dropDownValue);
            driver.waitForElementVisible(dropDownOption);
            driver.jsScrollToElement(dropDownOption);
            dropDownOption.click();
            driver.waitForMilliseconds();
        }
        if (!Config.isIe()) {
            Assert.assertEquals("FAIL: selectDropDownValue not found!", dropDownValue.toLowerCase(),
                    dropDown.getText().toLowerCase());
        } else {
            Assert.assertTrue("FAIL: The dropdown element values: '" + dropDownValue.trim().toLowerCase()
                            + " did NOT contain the expected value: '" + dropDown.getText().trim().toLowerCase() + "'!",
                    dropDown.getText().trim().toLowerCase().contains(dropDownValue.trim().toLowerCase()));
        }
        LOGGER.info("selectDropDownValue completed");
    }

    /**
     * Returns dropDown WebElement based on type passed in
     *
     * @param dropDownName The type of dropdown to return
     * @return WebElement   The dropDown to return
     */
    public WebElement getDropDown(String dropDownName) {
        WebElement dropDown = null;
        if (dropDownName.equalsIgnoreCase(YEAR)) {
            dropDown = dropDownYear;
        } else if (dropDownName.equalsIgnoreCase(MAKE)) {
            dropDown = dropDownMake;
        } else if (dropDownName.equalsIgnoreCase(MODEL)) {
            dropDown = dropDownModel;
        } else if (dropDownName.equalsIgnoreCase(TRIM)) {
            dropDown = dropDownTrim;
        } else if (dropDownName.equalsIgnoreCase(ASSEMBLY)) {
            dropDown = dropDownAssembly;
        } else if (dropDownName.equalsIgnoreCase(TIRE_WIDTH)) {
            dropDown = dropDownTireWidth;
        } else if (dropDownName.equalsIgnoreCase(TIRE_DIAMETER)) {
            dropDown = dropDownTireDiameter;
        } else if (dropDownName.equalsIgnoreCase(ASPECT_RATIO)) {
            dropDown = dropDownAspectRatio;
        } else if (dropDownName.equalsIgnoreCase(WHEEL_WIDTH)) {
            dropDown = dropDownWheelWidth;
        } else if (dropDownName.equalsIgnoreCase(WHEEL_DIAMETER)) {
            dropDown = dropDownWheelDiameter;
        } else if (dropDownName.equalsIgnoreCase(BOLT_PATTERN)) {
            dropDown = dropDownWheelBoltPattern;
        } else if (dropDownName.equalsIgnoreCase(BRAND)) {
            dropDown = dropDownBrand;
        }
        return dropDown;
    }

    /**
     * Returns dropDown WebElement based on type passed in
     *
     * @param dropDownValue The value of the dropdown option
     * @return WebElement   The dropDown option element to return
     */
    private WebElement getDropDownOptionElement(String dropDownValue) {

        LOGGER.info("getDropDownOptionElement started");

        List<WebElement> elements = webDriver.findElements(By.cssSelector(".simple-option"));

        for (WebElement element : elements) {
            LOGGER.info("text:" + element.getText());
            if (element.getText().equalsIgnoreCase(dropDownValue))
                return element;
        }
        return null;
    }

    /**
     * Conducts a vehicle search by selecting specified values from dropdown controls
     *
     * @param year     The year value to select
     * @param make     The make value to select
     * @param model    The model value to select
     * @param trim     The trim value to select
     * @param assembly The assembly value to select
     * @throws Exception Exception
     */
    public void vehicleSearch(String year, String make, String model,
                              String trim, String assembly) throws Exception {
        LOGGER.info("vehicleSearch started");
        driver.waitForPageToLoad();
        selectDropDownValue(dropDownYear, year);
        selectDropDownValue(dropDownMake, make);
        selectDropDownValue(dropDownModel, model);
        selectDropDownValue(dropDownTrim, trim);
        if (!assembly.equalsIgnoreCase(Constants.NONE)) {
            driver.waitForElementVisible(searchButton);
            searchButton.click();
            driver.waitForPageToLoad();
            selectDropDownValue(dropDownAssembly, assembly);
        }
        driver.waitForElementClickable(searchButton);
        driver.jsScrollToElement(searchButton);
        searchButton.click();

        LOGGER.info("vehicleSearch completed");
    }

    /**
     * Determines type of search and selects Section Width, Aspect Ratio and Wheel Diameter as
     * we as click on the search button afterwords
     *
     * @param searchType   Type of search to perform on page
     * @param sectionWidth Section width of the tire to search for
     * @param aspectRatio  Aspect ratio of the tire to search for
     * @param tireDiameter Tire diameter of the tire to search for
     * @throws Exception Exception
     */
    public void tireSizeSearch(String searchType, String sectionWidth,
                               String aspectRatio, String tireDiameter) throws Exception {
        LOGGER.info("tireSizeSearch started");
        try {
            WebElement tab = getSearchTab(searchType, TIRE_SIZE_SEARCH);

            driver.waitForElementClickable(tab);
            driver.jsScrollToElementClick(tab);
            selectDropDownValue(dropDownTireWidth, sectionWidth);
            selectDropDownValue(dropDownAspectRatio, aspectRatio);
            selectDropDownValue(dropDownTireDiameter, tireDiameter);

            driver.waitForElementClickable(searchButton);
            searchButton.click();

        } catch (Exception e) {
            Assert.fail("FAIL: Tire Size search FAILED with error: " + e);
        }
        LOGGER.info("tireSizeSearch completed");
    }

    /**
     * Searches for wheels based on size, width and bolt pattern
     *
     * @param searchType  The type of search in order to select the correct tab
     * @param diameter    Diameter of wheel to search for
     * @param wheelWidth  Wheel width to search for
     * @param boltPattern Bolt pattern of wheel to search for
     * @throws Exception Exception error
     */
    public void wheelSizeSearch(String searchType, String diameter,
                                String wheelWidth, String boltPattern) throws Exception {
        LOGGER.info("wheelSizeSearch started");

        WebElement tab = getSearchTab(searchType, WHEEL_SIZE_SEARCH);

        driver.waitForElementClickable(tab);
        driver.jsScrollToElementClick(tab);
        clickFitmentRadioButton(ConstantsDtc.WHEELS);

        selectDropDownValue(dropDownWheelDiameter, diameter);
        selectDropDownValue(dropDownWheelWidth, wheelWidth);
        selectDropDownValue(dropDownWheelBoltPattern, boltPattern);
        driver.waitForElementClickable(searchButton);
        searchButton.click();

        LOGGER.info("wheelSizeSearch completed");
    }

    /**
     * Gets the correct search tab based on search type
     *
     * @param searchType Type of search to perform on page
     * @return Search type WebElement
     */
    //TODO: This could be refactored to use auto class names, might even look into methods that leverage this
    private WebElement getSearchTab(String searchType, String tab) {
        LOGGER.info("getSearchTab started");
        List<WebElement> tabList;

        if (searchType.equalsIgnoreCase(MYVEHICLES)
                && tab.equalsIgnoreCase(TIRE_SIZE_SEARCH)) {
            tabList = driver.getElementsWithText(shopTabBy, SHOP_BY_SIZE);
            LOGGER.info("getSearchTab completed");
            return driver.getDisplayedElement(tabList, Constants.TWO_SEC_WAIT);
        } else if (searchType.equalsIgnoreCase(MYVEHICLES)
                && tab.equalsIgnoreCase(WHEEL_SIZE_SEARCH)) {
            tabList = driver.getElementsWithText(shopTabBy, SHOP_BY_SIZE);
            LOGGER.info("getSearchTab completed");
            return driver.getDisplayedElement(tabList, Constants.TWO_SEC_WAIT);
        } else if (searchType.equalsIgnoreCase(MYVEHICLES)
                && tab.equalsIgnoreCase(VEHICLE_SEARCH)) {
            tabList = driver.getElementsWithText(shopTabBy, VEHICLE_SEARCH);
            LOGGER.info("getSearchTab completed");
            return driver.getDisplayedElement(tabList, Constants.TWO_SEC_WAIT);
        } else {
            LOGGER.info("getSearchTab completed");
            return driver.getElementWithText(shopTabBy, SHOP_BY_SIZE);
        }
    }

    /**
     * Selects brand from brand drop down
     *
     * @param brandName The brand to select
     * @throws Exception Exception error
     */
    public void enterBrandName(String brandName) throws Exception {
        LOGGER.info("enterBrandName started");
        selectDropDownValue(dropDownBrand, brandName);
        LOGGER.info("enterBrandName completed");
    }

    /**
     * Expands specified fitment dropdown
     *
     * @param dropDownName The value in the dropDown (Year, Make, etc)
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void expandFitmentDropdown(String dropDownName) throws Exception {
        LOGGER.info("expandFitmentDropdown started");

        try {

            //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
            if (Config.isIe())
                driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);

            driver.waitForElementClickable(selectControl);

            WebElement dropDown = getDropDown(dropDownName);
            driver.waitForElementClickable(dropDown);

            driver.jsScrollToElement(dropDown);

            //Note: Menu only expands in ie/safari/mobile safari when clicking input element
            if (Config.isIe() || Config.isSafari() || Config.isIphone() || Config.isIpad()) {
                dropDown.findElement(reactSelectizeInputBy).click();
            } else {
                dropDown.click();
            }

        } catch (Exception e) {
            Assert.fail("FAIL: Expanding fitment dropdown menu \"" + dropDownName
                    + "\" FAILED with error: " + e);
        }
        LOGGER.info("expandFitmentDropdown completed");
    }

    /**
     * Clicks specified disabled fitment dropdown
     *
     * @param dropDownName The value in the dropDown (Year, Make, etc)
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void clickDisabledFitmentDropdown(String dropDownName) throws Exception {
        LOGGER.info("clickDisabledFitmentDropdown started");
        try {
            //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
            if (Config.isIe())
                driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);

            driver.waitForElementClickable(selectControl);

            WebElement dropDown = getDropDown(dropDownName);
            driver.waitForElementClickable(dropDown);

            driver.jsScrollToElement(dropDown);

            //Expanding dropdown if it is not already expanded
            if (!dropDown.getAttribute(Constants.CLASS).contains(Constants.OPEN)) {
                //Note: Menu only expands in ie/safari/mobile safari when clicking input element
                if (Config.isIe() || Config.isSafari() || Config.isIphone() || Config.isIpad()) {
                    dropDown.findElement(reactSelectizeInputBy).click();
                } else {
                    dropDown.click();
                }
            }

        } catch (Exception e) {
            LOGGER.info("Clicking disabled dropdown correctly failed.");
        }
        LOGGER.info("clickDisabledFitmentDropdown completed");
    }

    /**
     * Types the specified value in the given dropdown
     *
     * @param dropDownName The dropdown to type in
     * @param value        The value to type
     */
    public void typeValueInDropdown(String dropDownName, String value) {
        LOGGER.info("typeValueInDropdown started");

        try {
            WebElement dropDown = getDropDown(dropDownName);

            //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
            if (Config.isIe())
                driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);

            driver.waitForElementClickable(selectControl);
            driver.waitForElementClickable(dropDown);

            //TODO: retest when geckodriver is updated & stabilized
            if (Config.isFirefox())
                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

            driver.jsScrollToElement(dropDown);
            //Expanding dropdown if it is not already expanded
            if (!dropDown.getAttribute(Constants.CLASS).contains(Constants.OPEN)) {
                //Note: Menu only expands in ie/safari/mobile safari when clicking input element
                if (Config.isIe() || Config.isSafari() || Config.isIphone() || Config.isIpad()) {
                    dropDown.findElement(reactSelectizeInputBy).click();
                } else {
                    dropDown.click();
                }
            }

            //TODO: This fails on iPhone 10 simulator, possibly due to existing appium bug where
            //TODO (cont) keyboard not expanding. It does however work with iPhone 9.3.
            //TODO (cont) See https://github.com/appium/appium/issues/7868
            new Actions(webDriver).moveToElement(dropDown).sendKeys(value).perform();

            driver.waitForMilliseconds();
        } catch (Exception e) {
            Assert.fail("FAIL: Typing value \"" + value + "\" into dropdown menu \"" + dropDownName
                    + "\" FAILED with error (NOTE: This fails in iOS 10 due to Appium bug 7868, test manually.): " + e);
        }

        LOGGER.info("typeValueInDropdown completed");
    }

    /**
     * Selects a dropdown value from an already expanded menu
     *
     * @param dropDownValue The value in the dropDown to verify
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void selectDropDownValueFromExpandedMenu(String dropDownValue) throws Exception {
        LOGGER.info("selectDropDownValueFromExpandedMenu started");
        try {
            List<WebElement> dropDownOptions = webDriver.findElements(selectOptionBy);

            for (WebElement dropDownOption : dropDownOptions) {
                if (dropDownOption.getText().trim().toLowerCase().contains(dropDownValue.toLowerCase())) {
                    driver.jsScrollToElement(dropDownOption);
                    dropDownOption.click();
                    break;
                }
            }

        } catch (Exception e) {
            Assert.fail("FAIL: Clicking dropdown value \"" + dropDownValue + "\" FAILED with error: " + e);
        }
        LOGGER.info("selectDropDownValueFromExpandedMenu completed");
    }

    /**
     * Clicks the radio button of the fitment window based on button type
     *
     * @param buttonType Wheels or Tires
     */
    public void clickFitmentRadioButton(String buttonType) {
        LOGGER.info("clickFitmentRadioButton started");
        WebElement fitmentRadioButton = driver.getElementWithText(fitmentComponentOptionBy, buttonType);
        driver.waitForElementClickable(fitmentRadioButton);
        driver.jsScrollToElementClick(fitmentRadioButton);
        driver.waitForPageToLoad();
        LOGGER.info("clickFitmentRadioButton completed");
    }

    /**
     * Gets the correct shop by tab based on type
     *
     * @param shopBy Type of tab to get
     * @return Tab type WebElement
     */
    private WebElement getShopByTab(String shopBy) {
        if (shopBy.equalsIgnoreCase(SHOP_BY_TIRE_SIZE) ||
                shopBy.equalsIgnoreCase(SHOP_BY_WHEEL_SIZE)) {
            return shopBySize;
        } else if (shopBy.equalsIgnoreCase(SHOP_BY_VEHICLE)) {
            return shopByVehicle;
        } else if (shopBy.equalsIgnoreCase(SHOP_BY_BRAND)) {
            return shopByBrand;
        } else {
            return null;
        }
    }

    /**
     * Clicks on specified shop by tab, also clicking additional radio for tires or wheels
     *
     * @param shopBy Type of tab to click
     * @throws Exception Exception
     */
    public void clickShopByTab(String shopBy) throws Exception {
        LOGGER.info("clickShopByTab started");
        try {
            WebElement tab = getShopByTab(shopBy);
            driver.waitForElementClickable(tab);
            driver.jsScrollToElementClick(tab);
        } catch (Exception e) {
            Assert.fail("FAIL: Clicking '" + shopBy + "' tab failed with error: " + e);
        }
        LOGGER.info("clickShopByTab completed");
    }

    /**
     * Clicks the Shop By Vehicle element
     */
    public void clickShopByVehicle() {
        LOGGER.info("clickShopByVehicle started");
        driver.jsScrollToElement(shopByVehicle);
        driver.waitForElementClickable(shopByVehicle);
        shopByVehicle.click();
        LOGGER.info("clickShopByVehicle completed");
    }

    /**
     * Clicks the Shop By Size element
     */
    public void clickShopBySize() {
        LOGGER.info("clickShopBySize started");
        driver.waitForPageToLoad();
        driver.jsScrollToElement(shopBySize);
        driver.waitForElementClickable(shopBySize);
        shopBySize.click();
        driver.waitForPageToLoad();
        LOGGER.info("clickShopBySize completed");
    }

    /**
     * Clicks the Shop By Brand element
     */
    public void clickShopByBrand() {
        LOGGER.info("clickShopByBrand started");
        driver.jsScrollToElement(shopByBrand);
        driver.waitForElementClickable(shopByBrand);
        shopByBrand.click();
        driver.waitForPageToLoad();
        LOGGER.info("clickShopByBrand completed");
    }

    /**
     * Clicks the Find Tires or Find Wheels button in fitment search modal
     */
    public void clickFindTiresOrWheels() {
        LOGGER.info("clickFindTires started");
        driver.waitForElementClickable(fitmentComponentFindButton);
        driver.jsScrollToElement(fitmentComponentFindButton);
        fitmentComponentFindButton.click();
        LOGGER.info("clickFindTires completed");
    }

    /**
     * Verifies that all fitment menus (tabs) are displayed
     */
    public void verifyFitmentMenusDisplayed() {
        LOGGER.info("verifyFitmentMenusDisplayed started");
        driver.waitForElementVisible(shopByVehicle);
        LOGGER.info("Verified Shop by Vehicle menu visible.");
        driver.waitForElementVisible(shopBySize);
        LOGGER.info("Verified Shop by Size menu visible.");
        driver.waitForElementVisible(shopByBrand);
        LOGGER.info("Verified Shop by Brand menu visible.");
        LOGGER.info("verifyFitmentMenusDisplayed completed");
    }

    /**
     * Verifies that the specified radio button is enabled or disabled
     *
     * @param buttonType The type of radio button to validate (Tires / Wheels)
     * @param enabled    Whether to check if enabled (true) or disabled (false)
     */
    public void verifyRadioButtonEnabledOrDisabled(String buttonType, boolean enabled) {
        LOGGER.info("verifyRadioButtonEnabledOrDisabled started");

        try {
            WebElement radioButton;
            if (!buttonType.equalsIgnoreCase(ConstantsDtc.BOPIS_LABEL) &&
                    !buttonType.equalsIgnoreCase(ConstantsDtc.ROPIS_LABEL) ) {
                radioButton = driver.getElementWithText(radioButtonBy, buttonType);
            } else {
                radioButton = driver.getElementWithText(CommonActions.radioLabelBy, buttonType);
            }
            if (enabled) {
                Assert.assertTrue("FAIL: Radio button \"" + radioButton + "\" was disabled.",
                        radioButton.getAttribute(Constants.CLASS).contains(radioButtonActive));
            } else {
                Assert.assertTrue("FAIL: Radio button \"" + radioButton + "\" was enabled.",
                        !radioButton.getAttribute(Constants.CLASS).contains(radioButtonActive));
            }


        } catch (Exception e) {
            Assert.fail("FAIL: Verifying that radio button \"" + buttonType
                    + "\" was enabled or disabled FAILED with error: " + e);
        }

        LOGGER.info("verifyRadioButtonEnabledOrDisabled completed");
    }

    /**
     * Verifies that the dropdown values of an expanded fitment menu are sorted
     */
    public void verifyDropdownValuesSorted() {
        LOGGER.info("verifyDropdownValuesSorted started");

        List<WebElement> dropDownOptions = webDriver.findElements(selectOptionBy);
        ArrayList dropDownValues = new ArrayList();

        for (WebElement dropDownOption : dropDownOptions) {
            dropDownValues.add(dropDownOption.getText().trim().toLowerCase());
        }

        Assert.assertTrue("FAIL: Dropdown list was not alphabetically sorted. Values: " +
                dropDownValues, CommonUtils.isListSorted(dropDownValues));

        LOGGER.info("Verified that dropDown values were sorted:" + dropDownValues);
        LOGGER.info("verifyDropdownValuesSorted completed");
    }

    /**
     * Verifies that the dropdown values of an expanded dropdown are limited to those
     * that start with the specified character
     *
     * @param character The character to validate
     */
    public void verifyDropdownValuesLimited(String character) {
        LOGGER.info("verifyDropdownValuesLimited started");

        List<WebElement> dropDownOptions = webDriver.findElements(selectOptionBy);

        for (WebElement dropDownOption : dropDownOptions) {
            String dropDownText = dropDownOption.getText().trim();
            Assert.assertTrue("FAIL: First character of dropdown value did not start with " +
                            "\"" + character + "\" (dropdown value: " + dropDownText,
                    String.valueOf(dropDownText.charAt(0)).equalsIgnoreCase(character));
        }

        LOGGER.info("Verified that dropDown values were limited to those that start with \"" +
                character + "\"");
        LOGGER.info("verifyDropdownValuesLimited completed");
    }

    /**
     * Verifies that a dropdown with the specified value set is visible
     *
     * @param dropDownValue The value in the dropDown
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void verifyDropdownWithValueIsDisplayed(String dropDownValue) throws Exception {
        LOGGER.info("verifyDropdownWithValueIsDisplayed started");
        int size = 0;

        try {

            //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
            if (Config.isIe())
                driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);

            driver.waitForElementClickable(selectControl);

            List<WebElement> dropDownLists = webDriver.findElements(selectBy);

            for (WebElement dropDownList : dropDownLists) {
                if (dropDownList.getText().trim().toLowerCase().contains(dropDownValue.toLowerCase())) {
                    LOGGER.info("Confirmed that the dropdown with value \"" + dropDownValue + "\" exists.");
                    break;
                }
                size++;
            }

            if (size == dropDownLists.size()) {
                Assert.fail("FAIL: Dropdown with value set to \"" + dropDownValue + "\" was not found.");
            }

        } catch (Exception e) {
            Assert.fail("FAIL: Verifying dropdown with value \"" + dropDownValue
                    + "\" FAILED with error: " + e);
        }
        LOGGER.info("verifyDropdownWithValueIsDisplayed completed");
    }

    /**
     * Verifies that a specified drop down value is selected (workaround to validate that it is blue)
     *
     * @param dropDownValue The value in the dropDown to verify
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void verifyDropdownValueSelected(String dropDownValue) throws Exception {
        LOGGER.info("verifyDropdownValueSelected started");
        try {
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            List<WebElement> dropDownOptions = webDriver.findElements(selectOptionBy);

            for (WebElement dropDownOption : dropDownOptions) {
                if (dropDownOption.getText().trim().toLowerCase().contains(dropDownValue.toLowerCase())) {
                    Assert.assertTrue("Dropdown value \"" + dropDownValue + "\" was not selected.",
                            dropDownOption.getAttribute("class").contains("highlight"));
                    break;
                }
            }

        } catch (Exception e) {
            Assert.fail("FAIL: Verifying that dropdown value \"" + dropDownValue
                    + "\" was selected FAILED with error: " + e);
        }
        LOGGER.info("verifyDropdownValueSelected completed");
    }

    /**
     * Verifies that the dropdown menu has expanded (workaround for
     * "the dropdown arrow position should change from downward to upward")
     *
     * @param hasExpanded Whether to verify the menu has expanded or not
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void verifyDropdownMenuExpanded(boolean hasExpanded) throws Exception {
        LOGGER.info("verifyDropdownMenuExpanded started");

        driver.waitForMilliseconds();

        if (hasExpanded) {
            Assert.assertTrue("Dropdown menu was not expanded.",
                    driver.isElementDisplayed(dropDownMenuBy, Constants.TWO_SEC_WAIT));
        } else {
            Assert.assertFalse("Dropdown menu was expanded when it should not have been.",
                    driver.isElementDisplayed(dropDownMenuBy, Constants.TWO_SEC_WAIT));
        }

        LOGGER.info("verifyDropdownMenuExpanded completed");
    }

    /**
     * Asserts that the fitment search button is disabled
     *
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void assertSearchButtonIsDisabled() throws Exception {
        LOGGER.info("assertSearchButtonIsDisabled started");
        Assert.assertTrue("FAIL: The Search button was not disabled.",
                searchButton.getAttribute(Constants.CLASS).contains(searchButtonDisabled));
        LOGGER.info("assertSearchButtonIsDisabled completed");
    }

    /**
     * Asserts that the fitment search button is enabled
     *
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void assertSearchButtonIsEnabled() throws Exception {
        LOGGER.info("assertSearchButtonIsEnabled started");
        Assert.assertTrue("FAIL: The Search button was not enabled.",
                !searchButton.getAttribute(Constants.CLASS).contains(searchButtonDisabled));
        LOGGER.info("assertSearchButtonIsEnabled completed");
    }

    /**
     * Asserts the color of the fitment search button
     *
     * @param expectedColorString The color to verify
     */
    public void assertSearchButtonColor(String expectedColorString) {
        LOGGER.info("assertSearchButtonColor started");

        String expectedColor = getSearchButtonColorHexCodeFromString(expectedColorString);
        String colorScript = "return $('" + searchButtonClassName +
                "').css('background-color') === '" + expectedColor + "'";
        boolean colorChanged = driver.pollUntil(colorScript, Constants.FIVE_SEC_WAIT);
        String actualColor = searchButton.getCssValue(Constants.BACKGROUND_COLOR);

        Assert.assertTrue("FAIL: Search button did not change to color \"" + expectedColor +
                "\", actual color was: \"" + actualColor + "\"!", colorChanged);

        LOGGER.info("assertSearchButtonColor completed");
    }

    /**
     * Gets the specified color hex code
     *
     * @param color The color to check
     * @return String   The hex code of the color
     */
    public static String getColorHexCodeFromString(String color) {
        String colorHexCode = null;
        if (color.equalsIgnoreCase("red")) {
            colorHexCode = Constants.RED_COLOR_FOUR_DIGIT;
        } else if (color.equalsIgnoreCase("grey")) {
            colorHexCode = GREY_COLOR;
        }
        return colorHexCode;
    }

    /**
     * Gets the specified color hex code
     *
     * @param color The color to check
     * @return String   The hex code of the color
     */
    public static String getSearchButtonColorHexCodeFromString(String color) {
        String colorHexCode = null;
        if (color.equalsIgnoreCase("red")) {
            colorHexCode = Constants.RED_COLOR_SEARCH_BUTTON;
        } else if (color.equalsIgnoreCase("grey")) {
            colorHexCode = GREY_COLOR;
        }
        return colorHexCode;
    }

    /**
     * Checks for default values of dropdowns
     *
     * @param dropDownString String representing the default values of the dropdowns being checked
     */
    public void assertDropdownsAreVisible(String dropDownString) {
        LOGGER.info("assertDropdownsAreVisible started");
        driver.waitForElementVisible(selectBy);
        int count = 0;
        String dropDownValues[] = dropDownString.split(",");
        List<WebElement> dropDowns = webDriver.findElements(selectBy);
        for (int i = 0; i < dropDownValues.length; i++) {
            Assert.assertTrue("FAIL: " + dropDownValues[count] + " was not found in any dropdown.",
                    dropDowns.get(count).getText().trim().contains(dropDownValues[count].trim()));
            count++;
        }
        LOGGER.info("assertDropdownsAreVisible completed");
    }

    /**
     * Verifies that the given My Vehicle dropdown is not empty and contains no decimals
     *
     * @param tab          Tab that the dropdown is contained in
     * @param dropDownName Name of the dropdown to check values of
     */
    //TODO: Refactor based on existing dropdown methods
    public void assertNoDecimalInMyVehicleDropdown(String tab, String dropDownName) {
        LOGGER.info("assertNoDecimalInDropdown started");

        WebElement tabToClick = null;

        if (tab.equalsIgnoreCase(VEHICLE_SEARCH)) {
            tabToClick = getSearchTab(MYVEHICLES, VEHICLE_SEARCH);
        } else if (tab.equalsIgnoreCase(TIRE_SIZE_SEARCH)) {
            tabToClick = getSearchTab(MYVEHICLES, TIRE_SIZE_SEARCH);
        } else if (tab.equalsIgnoreCase(WHEEL_SIZE_SEARCH)) {
            tabToClick = getSearchTab(MYVEHICLES, WHEEL_SIZE_SEARCH);
        } else {
            Assert.fail("Search tab " + tab + " was not found on My Vehicles popup page.");
        }

        driver.waitForElementClickable(tabToClick);
        tabToClick.click();

        //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
        if (Config.isIe() || Config.isFirefox())
            driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);

        if (tab.contains(Constants.WHEEL)) {
            HomePage.wheelsRadioLink.click();
        } else if (tab.contains(Constants.TIRE)) {
            HomePage.tiresRadioLink.click();
        }
        driver.waitForElementClickable(selectControl);

        List<WebElement> dropDownLists = webDriver.findElements(selectBy);

        loop:
        for (WebElement dropDownList : dropDownLists) {
            if (dropDownList.getText().trim().toLowerCase().contains(dropDownName.toLowerCase())) {
                driver.waitForElementClickable(dropDownList);
                //TODO: replace with pollUntil call
                driver.waitForMilliseconds();

                //TODO: retest when geckodriver is updated & stabilized
                if (Config.isFirefox()) {
                    driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
                }

                dropDownList.click();
                driver.waitForMilliseconds();

                List<WebElement> dropDownOptions = webDriver.findElements(selectOptionBy);
                Assert.assertTrue(dropDownOptions + " list is empty.", dropDownOptions.size() != 0);
                for (WebElement dropDownOption : dropDownOptions) {
                    Assert.assertFalse(dropDownName + " dropdown DOES contain a decimal: " + dropDownOption.getText(),
                            dropDownOption.getText().contains(PERIOD_DECIMAL));
                }
                break;
            }
        }

        driver.waitForMilliseconds();
        LOGGER.info("assertNoDecimalInDropdown completed");
    }

    /**
     * Verifies the default dropdown values for specific tabs
     *
     * @param shopBy The tab to verify default dropdown values on
     */
    public void verifyDefaultDropdownValues(String shopBy) {
        LOGGER.info("verifyDefaultDropdownValues started");

        List<WebElement> dropDowns = webDriver.findElements(selectBy);
        String[] dropDownValues = getDefaultDropDownValues(shopBy);
        int i = 0;

        for (WebElement dropDown : dropDowns) {
            Assert.assertTrue("FAIL: Expected dropdown value:" + dropDownValues[i] + ", " +
                    "Actual value:" + dropDown.getText(), dropDown.getText().contains(dropDownValues[i]));
            LOGGER.info("Confirmed that drop down contains default value '" + dropDownValues[i] + "'.");
            i++;
        }

        LOGGER.info("verifyDefaultDropdownValues completed");
    }

    /**
     * Returns the default dropdown values for specific tabs
     *
     * @param shopBy The tab to get default dropdown values for
     * @return String[] An array of default dropdown values
     */
    private String[] getDefaultDropDownValues(String shopBy) {
        if (shopBy.equalsIgnoreCase(SHOP_BY_VEHICLE)) {
            return SHOP_BY_VEHICLE_DEFAULT_DROPDOWN_VALUES;
        } else if (shopBy.equalsIgnoreCase(SHOP_BY_BRAND)) {
            return SHOP_BY_BRAND_DEFAULT_DROPDOWN_VALUES;
        } else if (shopBy.equalsIgnoreCase(SHOP_BY_TIRE_SIZE)) {
            return SHOP_BY_TIRE_SIZE_DEFAULT_DROPDOWN_VALUES;
        } else if (shopBy.equalsIgnoreCase(SHOP_BY_WHEEL_SIZE)) {
            return SHOP_BY_WHEEL_SIZE_DEFAULT_DROPDOWN_VALUES;
        } else {
            return null;
        }
    }

    /**
     * Asserts the position of the displayed arrow on a selected drop-down
     */
    public void assertSelectedDropDownArrowPosition(String dropdown, String position) throws Exception {
        LOGGER.info("assertSelectedDropDownArrowPosition started");
        String dropDownFitmentAutoString = "auto-fitment-".concat(dropdown.toLowerCase());
        if (position.toLowerCase().equals(ARROW_DOWN)) {
            Assert.assertTrue("FAIL: Drop Down arrow was not pointing downwards.",
                    driver.isElementDisplayed(By.xpath(String.format(dropDownArrowDownXpath, dropDownFitmentAutoString))));
        } else if (position.toLowerCase().equals(ARROW_UP)) {
            Assert.assertTrue("FAIL: Expanded Drop Down arrow was not pointing upwards.",
                    driver.isElementDisplayed(By.xpath(String.format(dropDownArrowUpXpath, dropDownFitmentAutoString))));
        }
        LOGGER.info("assertSelectedDropDownArrowPosition completed");
    }

    /**
     * Verify that the Shop By Vehicle is the default selection in the Fitment Component on the Homepage
     */
    public void verifyShopByVehicleIsDefault() throws Exception {
        LOGGER.info("assertShopByVehicle started");
        Assert.assertTrue("FAIL: The Shop By Vehicle Tab is not selected by default.",
                driver.isElementDisplayed(dropDownYear));
        LOGGER.info("assertShopByVehicle completed");
    }

    /**
     * Verify no Results Page Message
     */
    public void verifyNoResultsPageMessage() throws Exception {
        LOGGER.info("verifyNoResultsPageMessage started");
        Assert.assertTrue("FAIL: The page did not display the message" + NO_RESULTS_MESSAGE,
                driver.waitForTextPresent(noResultsDescriptionBy, NO_RESULTS_MESSAGE, Constants.FIVE_SEC_WAIT));
        LOGGER.info("verifyNoResultsPageMessage completed");
    }

    /**
     * Verify Shop By Brand default placeholder text
     *
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void assertShopByBrandDefaultPlaceholderText() throws Exception {
        LOGGER.info("assertShopByBrandDefaultPlaceholderText started");
        Assert.assertTrue("FAIL: Shop By Brand placeholder text: " + dropDownBrandTextPlaceholderElement.getText()
                        + " did NOT match with default expected value : " + ConstantsDtc.BRAND_SEARCH_FIELD_PLACEHOLDER_TEXT,
                dropDownBrandTextPlaceholderElement.getText().equalsIgnoreCase(ConstantsDtc.BRAND_SEARCH_FIELD_PLACEHOLDER_TEXT));
        LOGGER.info("assertShopByBrandDefaultPlaceholderText completed");
    }
    
    /**
     * Verify Wheels Shop By Size fields are in specific order left to right
     *
     * @throws Exception General exception caught to allow for graceful failure
     */
    public void assertWheelsShopBySizeFieldsAreInSpecificOrderLeftToRight() throws Exception {
        LOGGER.info("assertWheelsShopBySizeFieldsAreInSpecificOrderLeftToRight started");
        int rimDiameterXCoordinateValue = commonActions.getXCoordinateValueOfAnElement(dropDownWheelDiameter);
        int rimWidthXCoordinateValue = commonActions.getXCoordinateValueOfAnElement(dropDownWheelWidth);
        int boltPatternXCoordinateValue = commonActions.getXCoordinateValueOfAnElement(dropDownWheelBoltPattern);		
        
        if(!((rimDiameterXCoordinateValue < rimWidthXCoordinateValue) && (rimWidthXCoordinateValue < boltPatternXCoordinateValue))){
        	Assert.fail("FAIL: Wheels Shop By Size fields were NOT in specific order left to right");
        }

        LOGGER.info("assertWheelsShopBySizeFieldsAreInSpecificOrderLeftToRight completed");
    }
}