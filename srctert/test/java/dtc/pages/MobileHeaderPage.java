package dtc.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 1/20/17.
 */
public class MobileHeaderPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(MobileHeaderPage.class.getName());

    public MobileHeaderPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "js-mobile-menu")
    public static WebElement mobileMenu;

    @FindBy(linkText = "ADD VEHICLE")
    public static WebElement addVehicleLink;

    @FindBy(className = "header__logo--mobile")
    public static WebElement dtLogo;

    @FindBy(className = "fa-shopping-cart")
    public static WebElement miniCartIcon;

    @FindBy(className = "header__cart-item-count")
    public static WebElement miniCartCount;

    @FindBy(className = "auto-header-appointment")
    public static WebElement services;

    @FindBy(className = "auto-header-find-store")
    public static WebElement findAStore;

    @FindBy(className = "auto-header-tips-guides")
    public static WebElement tipsAndGuides;

    @FindBy(className = "header__nav-button--search-mobile")
    public static WebElement searchForItemsIcon;

    @FindBy(className = "search__box")
    public static WebElement searchForItemsInput;

    public static final String addVehicleString = "ADD VEHICLE";

    public static final String findAStoreHeaderString = "FIND A STORE";

    public static final String scheduleAppointmentMenuString = "Schedule appointment";


    /**
     * Expands the mobile menu
     */
    public void openMobileMenu() {
        LOGGER.info("openMobileMenu started");
        driver.waitForElementVisible(mobileMenu);
        mobileMenu.click();
        driver.waitForMilliseconds();
        driver.waitForElementVisible(addVehicleLink);
        LOGGER.info("openMobileMenu completed");
    }


    /**
     * Expands the mobile menu and clicks the link by text
     *
     * @param linkText Menu link to click
     */
    public void clickMenuLink(String linkText) {
        LOGGER.info("clickMenuLink " + linkText + " started");
        driver.waitForElementVisible(By.linkText(linkText));
        driver.clickElementWithLinkText(linkText);
        driver.waitForMilliseconds();
        LOGGER.info("clickMenuLink " + linkText + " completed");
    }


    /**
     * Clicks the mobile navigation link by text
     *
     * @param linkText The header link to click
     */
    public void clickHeaderLink(String linkText) {
        LOGGER.info("clickHeaderLink " + linkText + " started");
        driver.waitForElementVisible(By.linkText(linkText));
        driver.clickElementWithLinkText(linkText);
        driver.waitForMilliseconds();
        LOGGER.info("clickHeaderLink " + linkText + " completed");
    }


    /**
     * Clicks the site logo at the top of page
     */
    public void clickSiteLogo() {
        LOGGER.info("clickStoreLogo started");
        driver.waitForMilliseconds();
        dtLogo.click();
        LOGGER.info("clickStoreLogo completed");
    }


    /**
     * Searches for a product via the search box at top right of home page
     *
     * @param item Specific item or product to search for as well as View All Results link
     */
    public void searchItem(String item) {
        LOGGER.info("searchItem started");
        driver.waitForElementClickable(searchForItemsIcon);
        driver.jsScrollToElement(searchForItemsIcon);
        searchForItemsIcon.click();
        driver.waitForElementVisible(searchForItemsInput);
        searchForItemsInput.sendKeys(item + Keys.RETURN);
        LOGGER.info("searchItem completed");
    }


    /**
     * Verifies visibility of all mobile header elements
     */
    public void verifyMobileHeaderElements() {
        LOGGER.info("verifyMobileHeaderElements started");
        driver.waitForElementVisible(mobileMenu);
        LOGGER.info("Verified mobile menu visible.");
        driver.waitForElementVisible(dtLogo);
        LOGGER.info("Verified Discount Tire Logo visible.");
        driver.waitForElementVisible(miniCartIcon);
        LOGGER.info("Verified cart icon visible.");
        driver.waitForElementVisible(miniCartCount);
        LOGGER.info("Verified cart count visible.");
        driver.waitForElementVisible(services);
        LOGGER.info("Verified SERVICES link visible.");
        driver.waitForElementVisible(findAStore);
        LOGGER.info("Verified FIND A STORE link visible.");
        driver.waitForElementVisible(tipsAndGuides);
        LOGGER.info("Verified TIPS AND GUIDES link visible.");
        LOGGER.info("verifyMobileHeaderElements completed");
    }

    /**
     * Verifies the H1 header text at top of the mobile page
     *
     * @param pageTitle Title on top of the page
     */
    public void assertPageH1Header(String pageTitle) {
        LOGGER.info("assertPageH1Header started");
        driver.waitForElementVisible(CommonActions.headerBy);
        WebElement header = webDriver.findElement(CommonActions.headerBy);
        Assert.assertTrue("ERROR: Mobile page header was not " + pageTitle + ". But instead was " + header.getText(),
                header.getText().equals(pageTitle));
        LOGGER.info("assertPageH1Header completed");
    }
}