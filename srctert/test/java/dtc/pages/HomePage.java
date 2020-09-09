package dtc.pages;

/**
 * Created by aaronbriel on 9/16/16.
 */

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class HomePage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private MobileHeaderPage mobileHeaderPage;
    private final Logger LOGGER = Logger.getLogger(HomePage.class.getName());

    public HomePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        mobileHeaderPage = new MobileHeaderPage(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String ADD_VEHICLE = "Add Vehicle";

    @FindBy(className = "js-open-search")
    public static WebElement searchIcon;

    @FindBy(className = "fitment-component__shop-by")
    public static WebElement fitmentComponent;

    @FindBy(className = "js-nav-myvehicles-button")
    public static WebElement myVehicles;

    @FindBy(linkText = "ADD VEHICLE")
    public static WebElement addVehicleLink;

    @FindBy(className = "header__my-store-label")
    public static WebElement myStore;

    @FindBy(className = "my-store__send-to-phone")
    public static WebElement myStoreSendToPhone;

    @FindBy(className = "auto-popover-change-store")
    public static WebElement changeStoreLink;

    @FindBy(className = "auto-popover-store-details")
    public static WebElement storeDetailsLink;

    @FindBy(className = "auto-popover-schedule-appointment")
    public static WebElement scheduleAppointmentPopOverLink;

    @FindBy(className = "auto-header-appointment")
    public static WebElement scheduleAppointmentLink;

    @FindBy(id = "js-site-search-input")
    public static WebElement searchBoxInput;

    //TODO: Redundant, but in anticipation of auto classnames being added
    @FindBy(className = "auto-remove-current-vehicle-button")
    public static WebElement removeCurrentVehicleButton;

    @FindBy(className = "my-vehicles__remove-button")
    public static WebElement removeRecentVehicleButton;

    @FindBy(className = "js-checkout-w-appointment")
    public static WebElement checkoutWithAppointmentButton;

    private static final String checkVehiclesEmptyScript =
            "return document.getElementsByClassName('my-vehicles--empty').length > 0;";

    //TODO: auto class name only visible with full browser with specific width, so fails in chrome
    //TODO (cont): They need an auto classname for the actual icon (fa-shopping-cart)
    @FindBy(partialLinkText = "Cart")
    public static WebElement miniCartIcon;

    @FindBy(className = "auto-popover-view-cart-button")
    public static WebElement viewCart;

    @FindBy(linkText = "CONTINUE SHOPPING")
    public static WebElement continueShoppingLink;

    @FindBy(className = "header__my-store-address")
    public static WebElement myStoreAddress;

    @FindBy(linkText = ConstantsDtc.TIRES)
    public static WebElement tiresMenu;

    @FindBy(linkText = ConstantsDtc.WHEELS)
    public static WebElement wheelsMenu;

    @FindBy(className = "auto-header-installers")
    public static WebElement installersMenu;

    @FindBy(className = "fa-shopping-cart")
    public static WebElement miniCartIconMobile;

    @FindBy(linkText = "Tires")
    public static WebElement tiresRadioLink;

    @FindBy(linkText = "Wheels")
    public static WebElement wheelsRadioLink;

    @FindBy(className = "mini-cart__item-quantity")
    public static WebElement miniCartItemQty;

    @FindBy(className = "my-store__hours")
    public static WebElement myStoreHours;

    @FindBy(className = "homepage")
    public static WebElement homepage;

    @FindBy(css = ".auto-header-logo > img")
    public static WebElement siteLogo;

    @FindBy(className = "js-myvehicles-dropdown")
    public static WebElement myVehiclesDropDownRef;

    @FindBy(className = "header__drop-down-icon--mobile icon")
    public static WebElement profileIconMobile;

    @FindBy(linkText = "View my account")
    public static WebElement viewMyAccount;

    @FindBy(linkText = "Sign-out")
    public static WebElement signOutLink;

    @FindBy(css = "[title='BROWSE WHEELS']")
    public static WebElement wheelConfiguratorImage;

    @FindBy(className = "fitment-component__imageBlock")
    public static WebElement browseWheels;

    public static By profileIconBy = By.cssSelector(".header__nav-button-label > i.header__drop-down-icon");

    public static By storePopUp = By.className("store-pop");

    private static By addVehicleBy = By.className("auto-add-vehicle-button");

    private static By miniCartIconBy = By.className("fa-shopping-cart");

    private static By cartTotalBy = By.className("header__cart-quick-total");

    private static By cartItemCountBy = By.className("header__cart-item-count");

    private static By myVehicleBy = By.className("auto-header-my-vehicle-label");

    private static By genericButtonBy = By.className("dt-button");

    private static By removeCurrentVehicleBy = By.className("auto-remove-current-vehicle-button");

    private static By strongBy = By.cssSelector("strong");

    public static final String DT_LOGO = "DT_logo.svg";

    public static final String AT_LOGO = "AT_logo.svg";

    public static final String DTD_LOGO = "DTD_logo.png";

    private static final String JOIN_SIGN_IN = "Join/Sign In";

    /**
     * Clicks continue button when prompted while switching to an AT (vs. DT) store
     */
    public void clickATContinueButton() {
        LOGGER.info("clickATContinueButton started");
        driver.waitForElementClickable(CommonActions.continueButton);
        CommonActions.continueButton.click();
        driver.waitForElementNotVisible(storePopUp);
        LOGGER.info("clickATContinueButton completed");
    }

    /**
     * Opens the My Vehicles popup
     */
    public void openMyVehiclesPopup() {
        LOGGER.info("openFitmentPopup started");

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (Config.isMobilePhone()) {
            mobileHeaderPage.openMobileMenu();
        } else {
            driver.waitForElementClickable(myVehicles);
            myVehicles.click();
        }

        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        LOGGER.info("openFitmentPopup completed");
    }

    /**
     * Clicks the Add Vehicle link
     */
    public void clickAddVehicle() {
        LOGGER.info("clickAddVehicle started");

        //TODO: This may be applicable to other browsers if intermittent failures are seen in this area for non FF
        if (Config.isFirefox()) {
            //TODO: ADD VEHICLE auto classname does not exist if a current vehicle is present
            if (driver.isElementVisible(myVehiclesDropDownRef.findElement(removeCurrentVehicleBy), Constants.DEFAULT_SEC_WAIT)) {
                driver.getElementWithText(genericButtonBy, "Add Vehicle").click();
            } else {
                driver.waitForClassPresent(driver.getByValue(addVehicleBy), Constants.DEFAULT_SEC_WAIT);
                WebElement addVehicle = driver.getDisplayedElement(
                        addVehicleBy, Constants.ZERO);
                addVehicle.click();
            }
        } else {
            driver.waitForElementClickable(addVehicleLink);
            addVehicleLink.click();
        }

        driver.waitForMilliseconds();

        //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
        if (Config.isIe())
            driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);
        LOGGER.info("clickAddVehicle completed");
    }


    /**
     * Clicks installer menu link
     */
    public void clickInstallersMenu() {
        LOGGER.info("clickInstallersMenu started");
        driver.waitForElementClickable(installersMenu);
        installersMenu.click();
        LOGGER.info("clickInstallersMenu completed");
    }

    /**
     * Opens the fitment popup page via 'My Vehicles' and 'Add Vehicle' elements
     */
    public void openFitmentPopup() {
        LOGGER.info("openFitmentPopup started");
        if (Config.isMobilePhone()) {
            mobileHeaderPage.openMobileMenu();
            mobileHeaderPage.clickMenuLink(ADD_VEHICLE.toUpperCase());
        } else {
            //TODO: retest when new safaridriver is stable
            if (Config.isSafari() || Config.isFirefox())
                driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);

            driver.waitForElementClickable(myVehicles);
            myVehicles.click();

            //TODO: retest when new safaridriver is stable
            if (Config.isSafari())
                driver.waitForPageToLoad();

            //TODO: This may be applicable to other browsers if intermittent failures are seen in this area for non FF
            if (Config.isFirefox() || Config.isSafari()) {
                driver.getElementWithText(genericButtonBy, ADD_VEHICLE).click();
            } else {
                driver.waitForElementClickable(addVehicleLink);
                addVehicleLink.click();
            }

            driver.waitForMilliseconds();

            //TODO: Needed for IE popup window due to on screen movement that cannot be manually replicated
            if (Config.isIe())
                driver.waitForMilliseconds(Constants.THREE_SEC_WAIT);
        }
        LOGGER.info("openFitmentPopup completed");
    }

    /**
     * Clicks the Store Details link in the My Store menu
     */
    public void openStoreDetails() {
        LOGGER.info("openStoreDetails started");
        if (!driver.isElementVisible(storeDetailsLink, Constants.TWO_SEC_WAIT)) {
            clickGlobalMyStoreHeader();
        }
        driver.waitForElementClickable(storeDetailsLink);
        driver.jsScrollToElement(storeDetailsLink);
        storeDetailsLink.click();
        LOGGER.info("openStoreDetails completed");
    }

    /**
     * Clicks the Change Store link in the My Store menu
     */
    public void openChangeStore() {
        LOGGER.info("openChangeStore started");
        driver.waitForPageToLoad();
        clickGlobalMyStoreHeader();
        driver.waitForElementClickable(changeStoreLink);
        driver.jsScrollToElement(changeStoreLink);

        //Deal with intermittent loading status failures
        try {
            changeStoreLink.click();
        } catch (Exception e) {
            driver.waitForElementClickable(changeStoreLink);
            changeStoreLink.click();
        }
        driver.waitForPageToLoad();
        LOGGER.info("openChangeStore completed");
    }

    /**
     * Clicks the Schedule Appointment link in the My Store menu
     */
    public void openScheduleAppointment() {
        LOGGER.info("openScheduleAppointment started");
        clickGlobalMyStoreHeader();
        driver.waitForElementClickable(scheduleAppointmentPopOverLink);
        driver.jsScrollToElement(scheduleAppointmentPopOverLink);
        scheduleAppointmentPopOverLink.click();
        LOGGER.info("openScheduleAppointment completed");
    }

    /**
     * Clicks 'My Store' global header
     */
    public void clickGlobalMyStoreHeader() {
        LOGGER.info("clickGlobalMyStoreHeader started");

        try {
            driver.waitForElementClickable(myStore);
        } catch (StaleElementReferenceException e) {
            driver.waitForMilliseconds();
            driver.waitForElementClickable(myStore);
        }

        myStore.click();
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();

        LOGGER.info("clickGlobalMyStoreHeader completed");
    }

    /**
     * Navigates the page to the main home page
     * Dynamically generated based on which site and region is being tested
     */
    public void goToHome() {
        LOGGER.info("goToHome started");
        driver.getUrl(Config.getBaseUrl());

        driver.waitForPageToLoad();
        commonActions.checkForWelcomePopup();

        driver.waitForPageToLoad();
        driver.waitForElementClickable(fitmentComponent);
        commonActions.checkForWelcomePopup();
        commonActions.checkForChatNowPopup();
        LOGGER.info("goToHome completed");
    }

    /**
     * Searches for a product via the search box at top right of home page
     *
     * @param item Specific item or product to search for as well as View All Results link
     */
    public void searchItem(String item) {
        LOGGER.info("searchItem started");

        if (Config.isIe())
            driver.waitForMilliseconds(Constants.DEFAULT_MILLISEC_WAIT);

        driver.waitForPageToLoad();

        //The search icon doesn't exist in IE
        if (!Config.isIe()) {
            driver.waitForElementVisible(searchIcon);
            driver.jsScrollToElement(searchIcon);
            searchIcon.click();
        }

        //TODO CCL - fails here for IE depending on the resolution of the VM / machine running the test; If too small
        //TODO CCL (cont) the textbox for search cannot be seen or acted on by the user
        driver.waitForElementClickable(searchBoxInput);
        searchBoxInput.sendKeys(item);

        //Needed for Firefox to keep the focus on the search field and in turn keep the suggested items visible
        if (Config.isFirefox()) {
            searchBoxInput.click();
        }

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isIe())
            driver.waitForMilliseconds();

        LOGGER.info("searchItem completed");
    }

    /**
     * Searches for a product via the search box at top right of home page and hit enter
     *
     * @param item Specific item or product to search for as well as View All Results link
     */
    public void searchItemHitEnter(String item) {
        LOGGER.info("searchItemHitEnter started");
        searchItem(item);
        driver.waitForPageToLoad();
        searchBoxInput.sendKeys(Keys.ENTER);
        LOGGER.info("searchItemHitEnter completed");
    }

    /**
     * Clicks the Mini-cart icon and then View Cart
     */
    public void openMiniCartPopupAndViewCart() {
        LOGGER.info("openMiniCartPopupAndViewCart started");
        if (Config.isMobile()) {
            driver.waitForElementVisible(miniCartIconMobile);
            miniCartIconMobile.click();
        } else {
            driver.waitForClassPresent(driver.getByValue(miniCartIconBy), Constants.DEFAULT_SEC_WAIT);
            WebElement miniCartIconEl = driver.getDisplayedElement(miniCartIconBy, Constants.ZERO);
            miniCartIconEl.click();

            driver.waitForElementClickable(viewCart);
            viewCart.click();
        }
        LOGGER.info("openMiniCartPopupAndViewCart completed");
    }

    /**
     * Clicks the Mini-cart icon
     */
    public void openMiniCart() {
        LOGGER.info("openMiniCart started");
        if (Config.isMobile()) {
            driver.waitForElementVisible(miniCartIconMobile);
            miniCartIconMobile.click();
        } else {
            driver.waitForClassPresent(driver.getByValue(miniCartIconBy), Constants.DEFAULT_SEC_WAIT);
            WebElement miniCartIconEl = driver.getDisplayedElement(miniCartIconBy, Constants.ZERO);
            miniCartIconEl.click();
        }
        LOGGER.info("openMiniCart completed");
    }

    /**
     * Clicks the Mini-cart icon and then Continue Shopping
     */
    public void openMiniCartPopupAndContinueShopping() {
        LOGGER.info("openMiniCartPopupAndContinueShopping started");

        driver.waitForClassPresent(driver.getByValue(miniCartIconBy), Constants.DEFAULT_SEC_WAIT);
        WebElement cartIcon = driver.getDisplayedElement(miniCartIconBy, Constants.ZERO);

        driver.waitForElementClickable(cartIcon);
        cartIcon.click();
        driver.waitForElementClickable(continueShoppingLink);
        continueShoppingLink.click();

        LOGGER.info("openMiniCartPopupAndContinueShopping completed");
    }

    /**
     * Clicks My Vehicle element and removes current vehicle
     */
    public void removeCurrentVehicle() {
        LOGGER.info("removeCurrentVehicle started");
        driver.waitForElementClickable(myVehicles);
        myVehicles.click();
        //driver.waitForElementClickable(removeCurrentVehicleButton);
        driver.jsClick(removeCurrentVehicleButton);
        LOGGER.info("removeCurrentVehicle completed");
    }

    /**
     * Clicks My Vehicle and removes recent vehicle
     */
    public void removeRecentVehicle() {
        LOGGER.info("removeRecentVehicle started");
        driver.waitForElementClickable(myVehicles);
        myVehicles.click();
        driver.jsClick(removeRecentVehicleButton);
        LOGGER.info("removeRecentVehicle completed");
    }

    /**
     * Verifies the My Vehicles list is empty
     */
    public void assertNoVehiclesInMyVehicles() {
        LOGGER.info("assertNoVehiclesInMyVehicles started");
        driver.waitForElementClickable(myVehicles);
        myVehicles.click();
        driver.pollUntil(checkVehiclesEmptyScript, Constants.DEFAULT_SEC_WAIT);
        LOGGER.info("assertNoVehiclesInMyVehicles completed");
    }

    /**
     * Verifies the details listed under the My Store header
     *
     * @param store Store address to verify
     */
    public void verifyMyStoreDetails(String store) {
        LOGGER.info("verifyMyStore started");
        driver.waitForElementClickable(myStore);
        Assert.assertTrue("FAIL: \"My Store\" address: " + myStoreAddress.getText()
                + " did NOT contain expected: \"" + store + "\"!", myStoreAddress.getText().trim().contains(store));
        LOGGER.info("verifyMyStore completed");
    }

    /**
     * Verifies My Vehicle in the header
     *
     * @param vehicle Vehicle to verify
     */
    public void verifyMyVehicle(String vehicle) {
        LOGGER.info("verifyMyVehicle started");
        Assert.assertTrue("FAIL: My Vehicle in the header did not equal \"" + vehicle + "\".",
                driver.waitForTextPresent(myVehicleBy, vehicle, Constants.DEFAULT_SEC_WAIT));
        LOGGER.info("verifyMyVehicle completed");
    }

    /**
     * Verifies item total in the header cart
     *
     * @param total Item total to verify in the header cart
     */
    public void verifyCartTotal(String total) {
        LOGGER.info("verifyCartTotal started");
        driver.waitForClassPresent(driver.getByValue(cartTotalBy), Constants.DEFAULT_SEC_WAIT);
        WebElement cartTotal = driver.getDisplayedElement(cartTotalBy, Constants.ZERO);
        Assert.assertTrue("FAIL: The actual cart total: \"" + cartTotal.getText()
                        + "\" did not contain the expected total: \"" + total + "\"!",
                cartTotal.getText().contains(total));
        LOGGER.info("verifyCartTotal completed");
    }

    /**
     * Verifies item count in the header cart
     *
     * @param count Item count to verify in the header cart
     */
    public void verifyCartItemCount(String count) {
        LOGGER.info("verifyCartItemCount started");
        driver.waitForClassPresent(driver.getByValue(cartItemCountBy), Constants.DEFAULT_SEC_WAIT);
        WebElement cartItemcount = driver.getDisplayedElement(cartItemCountBy, Constants.ZERO);
        Assert.assertTrue("FAIL: Cart item count in the header did not equal \"" + count + "\".",
                cartItemcount.getText().equalsIgnoreCase(count));
        LOGGER.info("verifyCartItemCount completed");
    }


    /**
     * Assert displayed item Qty matches with provided one on mini cart modal
     */
    public void assertItemQtyMiniCartModal(String qty) {
        LOGGER.info("assertItemQtyMiniCartModal started");

        driver.waitForClassPresent(driver.getByValue(miniCartIconBy), Constants.DEFAULT_SEC_WAIT);
        WebElement cartIcon = driver.getDisplayedElement(miniCartIconBy, Constants.ZERO);
        cartIcon.click();

        driver.waitForElementClickable(viewCart);
        Assert.assertTrue("FAIL: Mini Cart item count did not equal \"" + qty + "\".",
                miniCartItemQty.getText().contains(qty));

        //To close the mini-cart modal
        cartIcon.click();

        LOGGER.info("assertItemQtyMiniCartModal completed");
    }

    /***
     * Verifies the "STORE HOURS" information listed in the "My Store" popup
     */
    public void verifyMyStoreHours() {
        LOGGER.info("verifyMyStoreHours started");
        driver.waitForElementClickable(storeDetailsLink);
        for (String daysHours : ConstantsDtc.MY_STORE_POPUP_STORE_HOURS) {
            Assert.assertTrue("FAIL: \"My Store\" hours: " + myStoreHours.getText()
                            + " did NOT contain expected day and hour combination of: \"" + daysHours + "\"!",
                    myStoreHours.getText().contains(daysHours));
        }
        LOGGER.info("verifyMyStoreHours completed");
    }

    /**
     * Verifies user is on the homepage
     */
    public void verifyHomepage() {
        LOGGER.info("verifyHomepage started");

        if (Config.isFirefox())
            driver.waitForMilliseconds();

        driver.waitForPageToLoad();
        driver.waitForElementVisible(homepage);
        LOGGER.info("verifyHomepage completed");
    }

    /**
     * Returns site logo filename based on site region
     *
     * @return String The logo filename
     */
    public String getSiteLogo() {
        LOGGER.info("getSiteLogo started");
        String siteLogo = null;

        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            siteLogo = DT_LOGO;
        } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            siteLogo = AT_LOGO;
        } else if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            siteLogo = DTD_LOGO;
        }
        LOGGER.info("getSiteLogo completed");

        return siteLogo;
    }

    /**
     * Verifies site logo
     */
    public void verifySiteLogo() {
        LOGGER.info("verifySiteLogo started");
        driver.waitForElementAttribute(siteLogo, Constants.ATTRIBUTE_SRC, getSiteLogo());
        LOGGER.info("verifySiteLogo completed");
    }

    /**
     * Clicks installer menu link
     */
    public void clickJoinSignInLink() {
        LOGGER.info("clickJoinSignInLink started");
        driver.waitForMilliseconds();
        driver.getElementWithText(CommonActions.dtLinkBy, JOIN_SIGN_IN).click();
        LOGGER.info("clickJoinSignInLink completed");
    }

    /**
     * Verifies join/sign-in option displayed on homepage
     */
    public void verifyJoinSignInIsDisplayed() {
        LOGGER.info("verifyJoinSignInIsDisplayed started");
        if (Config.isFirefox())
            driver.waitForMilliseconds();

        driver.waitForPageToLoad();
        if (!driver.isElementDisplayed(driver.getElementWithText(CommonActions.dtLinkBy, JOIN_SIGN_IN))) {
            Assert.fail("FAIL : Join / Sign In link did NOT displayed on homepage header");
        }
        LOGGER.info("verifyJoinSignInIsDisplayed completed");
    }

    /**
     * Click 'Checkout with Appointment' button
     */
    public void clickCheckoutWithAppointmentButton() {
        LOGGER.info("selectCheckoutWithAppointment started");
        checkoutWithAppointmentButton.click();
        LOGGER.info("selectCheckoutWithAppointment completed");
    }

    /**
     * Click the Profile popup and then View my account
     */
    public void openProfilePopoverAndViewMyAccount() {
        LOGGER.info("openProfilePopoverAndViewMyAccount started");
        if (Config.isMobile()) {
            driver.waitForElementVisible(profileIconMobile);
            profileIconMobile.click();
        } else {
            WebElement profileIconElement = driver.getDisplayedElement(profileIconBy, Constants.ZERO);
            profileIconElement.click();

            driver.waitForElementClickable(viewMyAccount);
            viewMyAccount.click();
        }
        LOGGER.info("openProfilePopoverAndViewMyAccount completed");
    }

    /**
     * Click the Profile popup and then Sign out
     */
    public void openProfilePopoverAndSignOut() {
        LOGGER.info("openProfilePopoverAndSignOut started");
        if (Config.isMobile()) {
            driver.waitForElementVisible(profileIconMobile);
            profileIconMobile.click();
        } else {
            WebElement profileIconElement = driver.getDisplayedElement(profileIconBy, Constants.ZERO);
            profileIconElement.click();

            driver.waitForElementClickable(signOutLink);
            signOutLink.click();
        }
        LOGGER.info("openProfilePopoverAndSignOut completed");
    }

    /**
     * Verifies the "My Store" popup contains the "CHANGE STORE", "STORE DETAILS", and "SCHEDULE APPOINTMENT" controls
     */
    public void verifyMyStoreContainsControls() {
        LOGGER.info("verifyMyStoreContainsControls started");
        driver.waitForElementClickable(storeDetailsLink);

        ArrayList<WebElement> expectedControlsList = new ArrayList<>(Arrays.asList(changeStoreLink,
                scheduleAppointmentPopOverLink, storeDetailsLink));

        for (WebElement expectedControl : expectedControlsList) {
            Assert.assertTrue("FAIL: The expected control: '" + expectedControl.getText() + "' was NOT displayed!",
                    driver.isElementDisplayed(expectedControl));
        }
        LOGGER.info("verifyMyStoreContainsControls completed");
    }

    /**
     * Verify Wheel Configurator text is displayed on home page
     */
    public void assertWheelConfiguratorTextIsDisplayed(String text) {
        LOGGER.info("assertWheelConfiguratorTextIsDisplayed started");
        driver.waitForPageToLoad();
        WebElement wheelConfiguratorText = webDriver.findElement(strongBy);
        driver.jsScrollToElement(wheelConfiguratorText);
        Assert.assertTrue("FAIL: Wheel Configurator text: " + wheelConfiguratorText.getText()
                        + " not matching to expected text: " + text,
                wheelConfiguratorText.getText().equalsIgnoreCase(text));
        LOGGER.info("assertWheelConfiguratorTextIsDisplayed completed");
    }

    /**
     * Verify Wheel Configurator image is displayed on home page
     */
    public void assertWheelConfiguratorImageIsDisplayed() {
        LOGGER.info("assertWheelConfiguratorImageIsDisplayed started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: Wheel Configurator image not displayed!",
                driver.isElementDisplayed(wheelConfiguratorImage));
        LOGGER.info("assertWheelConfiguratorImageIsDisplayed completed");
    }

    /**
     * Verify BROWSE WHEELS is displayed on home page
     */
    public void assertBrowseWheelsIsDisplayed() {
        LOGGER.info("assertBrowseWheelsIsDisplayed started");
        driver.waitForPageToLoad();
        String browseWheelsBorderColor = browseWheels.getCssValue(Constants.BORDER);
        String browseWheelsColor = browseWheels.getCssValue(Constants.COLOR);
        Assert.assertTrue("FAIL: BROWSE WHEELS displayed on home page with border:"
                        + browseWheelsBorderColor + ", expected red border contains:" + Constants.DTC_RED
                        + ". Displayed font:" + browseWheelsColor + ", expected red font contains:" + Constants.RED_COLOR_FOUR_DIGIT,
                driver.isElementDisplayed(browseWheels) &&
                        browseWheelsBorderColor.contains(Constants.DTC_RED) &&
                        browseWheelsColor.contains(Constants.RED_COLOR_FOUR_DIGIT));
        LOGGER.info("assertBrowseWheelsIsDisplayed completed");
    }

}