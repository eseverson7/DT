package dtc.pages;

/**
 * Created by aaronbriel on 9/16/16.
 */

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class FitmentPopupPage {

    private Driver driver;
    private WebDriver webDriver;
    private final ProductListPage productListPage;
    private final CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(FitmentPopupPage.class.getName());

    public FitmentPopupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        productListPage = new ProductListPage(driver);
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String[] NON_STAGGERED_FITMENT_LINKS = {
            "All tires", "Best selling", "On promotion", "Optional Tire Sizes", "All wheels", "Optional Wheel Sizes", "Highest rated"
    };
    private static final String[] STAGGERED_FITMENT_LINKS = {
            "All tire sets", "Optional Tire Sizes", "All Front Tires", "Best selling", "All Rear Tires", "All Front Wheels", "All Rear Wheels", "Optional Wheel Sizes", "Highest rated"
    };
    private static final String[] BASIC_NON_STAGGERED_FITMENT_LINKS = {
            "All tires", "Best selling", "On promotion", "Optional Tire Sizes", "All wheels",
            "Optional Wheel Sizes"
    };

    private static final String STAGGERED_VEHICLE_MESSAGE = "Ok, now that we know your vehicle uses different front and rear tire sizes we can help you choose from options that fit.";
    private static final String NON_STAGGERED_VEHICLE_MESSAGE = "Great, now that we know about your vehicle, we can help you stay safe on the road. We'll narrow the product options to those that will fit.";
    private static final String STAGGERED = "Staggered";
    private static final String NONSTAGGERED = "Non Staggered";
    private static final String RED_COLOR = "rgba(237, 28, 36, 1)";
    private static final String RED_COLOR_RGB = "rgb(237, 28, 36)";
    private static final String BASICNONSTAGGERED = "Basic Non Staggered";
    private static final String BEST_SELLING = "Best selling";
    private static final String TIRES_ON_PROMOTION = "On promotion";
    private static final String SCHEDULE_SERVICE = "Schedule a Service";
    private static final String OPTIONAL_TIRE_SIZES = "Optional Tire Sizes";
    private static final String OPTIONAL_WHEEL_SIZES = "Optional Wheel Sizes";
    private static final String[] EXP_FITMENT_OPTS = {"TIRES", "WHEELS"};
    private static final String TIRES_HEADER = "Results for Tires";
    private static final String WHEELS_HEADER = "Results for Wheels";
    private static final String BEST_SELLER = "Best Seller";

    private static final By closeButtonBy = By.className("fitment-box__close");

    private static final By fitmentVehicleYearMakeBy = By.className("fitment-vehicle-display__year-make");

    private static final By vehicleMessageBy = By.className("fitment-box__result-desc");

    private static final By fitmentBoxTitleBy = By.className("dt-tabs__tab");

    private static final By fitmentRangeBy = By.className("fitment-box__optional-size-list-option");

    private static final By staggeredMenuOptionBy = By.className("dt-sub-menu__button");

    private static final By menuButtonBy = By.className("dt-tabs__tab");

    @FindBy(className = "fitment-box__set-flex")
    public static WebElement fitmentBoxFlex;

    @FindBy(className = "fitment-box__optional-size-list-option")
    public static WebElement fitmentRange;

    @FindBy(css = ".fitment-box__optional-size-list-option--selected")
    public static WebElement fitmentRangeSelected;

    @FindBy(className = "fitment-navigation__close")
    public static WebElement closeButton;

    @FindBy(className = "fitment-vehicle-display__change")
    public static WebElement changeVehicle;

    @FindBy(className = "fitment-box__pdl-cta-button")
    public static WebElement pdlxDrivingDetails;

    /**
     * Finds an element on the Fitment popup screen and selects it
     *
     * @param fitmentText Fitment option to be selected
     */
    public void selectFitment(String fitmentText) {
        LOGGER.info("selectFitment started");
        WebElement fitmentElement;

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isMobile())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (driver.isElementDisplayed(getFitmentElement(fitmentText))) {
            fitmentElement = driver.getElementWithText(getFitmentElement(fitmentText), fitmentText);
            driver.waitForElementVisible(fitmentElement);
        } else {
            fitmentElement = driver.getElementWithText(CommonActions.dtLinkBy, fitmentText);
        }

        //TODO:  may fail to click on element in Safari
        try {
            driver.jsScrollToElement(fitmentElement);
            fitmentElement.click();
        } catch (NoSuchElementException e) {
            Assert.fail("FAIL: Fitment link \"" + fitmentText + "\" NOT found. (Full Stack Trace: " + e.toString() + ")");
        } catch (WebDriverException we) {
            //Deals with intermittent issue where other element receives click due to popup animation
            driver.waitForMilliseconds();
            fitmentElement.click();
        }
        driver.waitForPageToLoad();
        LOGGER.info("selectFitment completed");
    }

    /**
     * Finds an element on the Fitment popup screen and selects it
     *
     * @param vehicle Fitment vehicle to be selected
     */
    public void selectFitmentVehicle(String vehicle) {
        LOGGER.info("selectFitmentVehicle started");

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isMobile())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        WebElement vehicleButton = driver.getElementWithText(CommonActions.dtButtonBy, vehicle);
        driver.waitForElementVisible(vehicleButton);

        try {
            driver.jsScrollToElement(vehicleButton);
            vehicleButton.click();
        } catch (NoSuchElementException e) {
            Assert.fail("FAIL: Vehicle button \"" + vehicle + "\" NOT found. (Full Stack Trace: " + e.toString() + ")");
        } catch (WebDriverException we) {
            //Deals with intermittent issue where other element receives click due to popup animation
            driver.waitForMilliseconds();
            vehicleButton.click();
        }

        driver.waitForPageToLoad();

        LOGGER.info("selectFitmentVehicle completed");
    }

    /**
     * Clicks Change Vehicle link
     */
    public void clickChangeVehicle() {
        driver.waitForElementClickable(changeVehicle);
        changeVehicle.click();
        driver.waitForMilliseconds();
    }

    /**
     * Returns partial link text of specified fitment
     *
     * @param fitment Fitment to get link for
     * @return By       Partial link text By element
     */
    private By getFitmentElement(String fitment) {
        return By.partialLinkText(fitment);
    }

    /**
     * Verifies passed in vehicle name is present and visible
     *
     * @param vehicle Vehicle title to assert is present on the screen
     */
    public void assertVehicleInPanel(String vehicle) {
        LOGGER.info("assertVehicleInPanel started");
        driver.waitForMilliseconds();
        driver.waitForElementVisible(fitmentVehicleYearMakeBy);
        WebElement fitmentElement = webDriver.findElement(fitmentVehicleYearMakeBy);
        Assert.assertEquals("FAILED: vehicle was not displayed", vehicle.toLowerCase(), fitmentElement.getText().toLowerCase());
        LOGGER.info("Confirmed that \"" + vehicle + "\" was listed in the fitment panel.");
        LOGGER.info("assertVehicleInPanel completed");
    }

    /**
     * Verifies the detail message on screen based on the passed in option type
     *
     * @param optionType Staggered or Non-staggered vehicle
     */
    public void assertVehicleDetailsMessage(String optionType) {
        LOGGER.info("assertVehicleDetailsMessage started");
        if (optionType.equalsIgnoreCase("non staggered")) {
            Assert.assertTrue("FAIL: The option type: \"" + optionType + "\" was NOT shown on vehicle details page!",
                    driver.waitForTextPresent(vehicleMessageBy, STAGGERED_VEHICLE_MESSAGE,
                            Constants.DEFAULT_SEC_WAIT));
            LOGGER.info("Confirmed that '" + STAGGERED_VEHICLE_MESSAGE + "' shown on vehicle details page.");
        } else if (optionType.equalsIgnoreCase("staggered")) {
            Assert.assertTrue("FAIL: The option type: \"" + optionType + "\" was NOT shown on vehicle details page!",
                    driver.waitForTextPresent(vehicleMessageBy, NON_STAGGERED_VEHICLE_MESSAGE, Constants.DEFAULT_SEC_WAIT));
            LOGGER.info("Confirmed that '" + NON_STAGGERED_VEHICLE_MESSAGE + "' shown on vehicle details page.");
        }
        LOGGER.info("assertVehicleDetailsMessage completed");
    }

    /**
     * Verifies all the Fitment popup links common to the option type
     *
     * @param optionType String staggered or non-staggered
     */
    public void verifyAllFitmentLinksWork(String optionType) {
        LOGGER.info("verifyAllFitmentLinksWork started");
        //TODO: Recheck once new safaridriver is stable. Navigation does NOT work with Version 2.48:
        //org.openqa.selenium.WebDriverException: Yikes! Safari history navigation does not work.
        // We can go forward or back, but once we do, we can no longer communicate with the page...

        //NOTE: Some of the header validations are not visible in mobile with changes as of 3/9/17,
        //so this validation is being skipped for mobile altogether

        List<String> failedLinkList = new ArrayList<>();

        if (!Config.isSafari() && !Config.isMobile()) {
            String[] links = new String[0];
            //TODO: Text values for Best Selling Font + Rear still work as expected but may need to be refactored in the future.
            if (optionType.equalsIgnoreCase(NONSTAGGERED)) {
                links = STAGGERED_FITMENT_LINKS;
            } else if (optionType.equalsIgnoreCase(STAGGERED)) {
                links = NON_STAGGERED_FITMENT_LINKS;
            } else if (optionType.equalsIgnoreCase(BASICNONSTAGGERED)) {
                links = BASIC_NON_STAGGERED_FITMENT_LINKS;
            }
            for (String link : links) {
                boolean linkVerified;
                WebElement element = null;
                if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)
                        && link.equalsIgnoreCase(SCHEDULE_SERVICE)) {
                    //DTD does not display the "Schedule a Service" link
                    driver.waitForPageToLoad();
                    linkVerified = !driver.isTextPresentInPageSource(link);
                } else {
                    element = webDriver.findElement(By.partialLinkText(link));
                    driver.waitForElementClickable(element);
                    element.click();
                    linkVerified = assertFitmenLinkHeaderResults(link);
                }

                if (!linkVerified) {
                    failedLinkList.add("\"" + link + "\" FAILED Expected Page Header Not Found!\n\t");
                }

                webDriver.navigate().back();
                driver.waitForPageToLoad();
                LOGGER.info(element + " element visible on screen");
            }

            Assert.assertTrue("FAIL: Issues found for one or more \"" + optionType + "\" link(s): "
                    + failedLinkList + "\"!", failedLinkList.isEmpty());
        } else {
            LOGGER.info("SKIPPING TEST FOR SAFARIDRIVER, NAVIGATION NOT SUPPORTED IN VERSION 2.48.");
        }
        LOGGER.info("verifyAllFitmentLinksWork completed");
    }

    /**
     * Verifies the selected class value of the box via provided text value
     *
     * @param value Text value of the fitment box that is selected
     */
    public void assertFitmentBoxIsSelected(String value) {
        LOGGER.info("assertFitmentBoxIsSelected started");
        driver.waitForElementVisible(fitmentRange);
        Assert.assertTrue("Value of the selected element was not " + value, fitmentRangeSelected.getText().contains(value));
        LOGGER.info("assertFitmentBoxIsSelected completed");
    }

    /**
     * Verifies the fitment box contains the provided text value
     *
     * @param value Text value of the fitment box
     */
    public void assertFitmentBoxValue(String value) {
        LOGGER.info("assertFitmentBoxIsSelected started");
        driver.waitForElementVisible(fitmentRange);
        WebElement getFitmentRangeElement = driver.getElementWithText(fitmentRangeBy, value);
        Assert.assertTrue("Value of the selected element was not " + value, getFitmentRangeElement.getText().contains(value));
        LOGGER.info("assertFitmentBoxIsSelected completed");
    }

    /**
     * Verifies the color of the selected fitment box
     */
    public void assertSelectedFitmentBoxColorIsRed() {
        LOGGER.info("assertGoodBetterBestBackgroundColor started");
        driver.waitForElementVisible(fitmentRange);
        String redColor = RED_COLOR;
        String color = fitmentRangeSelected.getCssValue(Constants.BACKGROUND_COLOR);

        if (Config.isFirefox() || (Config.isSafari()))
            redColor = RED_COLOR_RGB;

        Assert.assertTrue("FAIL: Expected color: " + redColor + " but the actual color was: "
                + color + "!", color.equalsIgnoreCase(redColor));
        LOGGER.info("assertSelectedFitmentBoxColor completed");
    }

    /**
     * Clicks on the fitment selection range box via provided text value
     *
     * @param value Text value of the box to select
     */
    public void selectFitmentBoxOption(String value) {
        LOGGER.info("selectFitmentBoxOption started");
        driver.waitForElementVisible(fitmentRange);
        WebElement tireSizeBox = driver.getElementWithText(fitmentRangeBy, value);
        tireSizeBox.click();
        LOGGER.info("selectFitmentBoxOption completed");
    }

    /**
     * Closes the Fitment popup
     **/
    public void closeFitmentPopUp() {
        LOGGER.info("closeFitmentPopUp started");
        driver.waitForElementVisible(closeButton);
        closeButton.click();
        driver.waitForElementNotVisible(closeButtonBy);
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        LOGGER.info("closeFitmentPopUp completed");
    }

    /***
     * Returns a boolean based on the success or failure of the header verification for the link coming from the
     * Fitment popup.
     * @param link Link of the fitment option on Fitment results page
     * @return True for successful verification OR False for a failure
     */
    public boolean assertFitmenLinkHeaderResults(String link) {
        LOGGER.info("assertFitmenLinkHeaderResults started");
        if (link.equals(OPTIONAL_TIRE_SIZES) || link.equals(OPTIONAL_WHEEL_SIZES)) {
            return FitmentPopupPage.fitmentBoxFlex.isDisplayed();
        } else if (link.contains(Constants.WHEEL)) {
            try {
                commonActions.assertPageHeader(WHEELS_HEADER);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if (link.contains(Constants.TIRE)) {
            try {
                commonActions.assertPageHeader(TIRES_HEADER);
                if (link.equals(TIRES_ON_PROMOTION)) {
                    commonActions.assertPageHeader(TIRES_HEADER);
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if (link.equals(BEST_SELLING)) {
            try {
                productListPage.verifySortByValue(BEST_SELLER);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if (link.equals(SCHEDULE_SERVICE)) {
            try {
                commonActions.assertPageHeader(AppointmentPage.SCHEDULE_SERVICE_HEADER);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            LOGGER.info("assertFitmenLinkHeaderResults completed - \"" + link + "\" not recognized by method!");
            return false;
        }
    }

    /**
     * Verifies all fitment options on fitment result window
     */
    public void assertAllFitmentOpts() {
        LOGGER.info("assertAllFitmentOpts started");
        driver.waitForPageToLoad();
        List<WebElement> fitmentOpts = webDriver.findElements(fitmentBoxTitleBy);

        //verify if all three fitment options found
        if (EXP_FITMENT_OPTS.length != fitmentOpts.size()) {
            Assert.fail("FAIL: All three fitment options not displayed");
        }
        //verify that the value of every fitment option equal to the expected value
        for (int i = Constants.ZERO; i < EXP_FITMENT_OPTS.length; i++) {
            String fitmentValue = fitmentOpts.get(i).getText().toUpperCase();
            if (Arrays.asList(EXP_FITMENT_OPTS).contains(fitmentValue)) {
                LOGGER.info("PASS: Fitment option found : " + fitmentValue);
            } else {
                Assert.fail("FAIL: actual fitment option: '" + fitmentValue + "' expected: '"
                        + EXP_FITMENT_OPTS[i] + "'!");
            }
        }
        LOGGER.info("assertAllFitmentOpts completed");
    }

    /**
     * Clicks PDLX Driving Details Recommended Tires Link
     */
    public void selectPdlxDrivingDetailsRecommendedTiresLink() {
        LOGGER.info("selectPdlxDrivingDetailsRecommendedTiresLink started");
        driver.waitForElementClickable(pdlxDrivingDetails);
        pdlxDrivingDetails.click();
        driver.waitForMilliseconds();
        LOGGER.info("selectPdlxDrivingDetailsRecommendedTiresLink completed");
    }

    /**
     * Clicks the menu buttons on the Fitment Modal popup
     *
     * @param value Text value of the box to select
     */
    public void clickMenuOption(String value, String menuOptionType) {
        LOGGER.info("clickMenuOption started with menuOptionType = '" + menuOptionType + "' and value = '"
                + value + "'");
        By fitmentMenuBy;

        if (menuOptionType.contains(STAGGERED.toLowerCase())) {
            fitmentMenuBy = staggeredMenuOptionBy;
        } else {
            fitmentMenuBy = menuButtonBy;
        }

        try {
            List<WebElement> menuOptions = driver.getElementsWithText(fitmentMenuBy, value);
            for (WebElement menuOption : menuOptions) {
                if (menuOption.getText().contains(value) || menuOption.getText().trim().toLowerCase().contains
                        (value.toLowerCase())) {
                    driver.waitForElementClickable(fitmentMenuBy);
                    driver.waitForMilliseconds();
                    menuOption.click();
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("FAIL: did not click '" + value + "' menu option.");
        }
        LOGGER.info("clickMenuOption completed with menuOptionType = '" + menuOptionType + "' and value = '"
                + value + "'");
    }
}