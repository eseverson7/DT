package dtc.pages;

import common.Config;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 10/26/16.
 */
public class HeaderPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(FitmentPopupPage.class.getName());

    private static final String VIEW_ALL = "View all";

    @FindBy(css = ".auto-header-logo")
    public static WebElement dtLogo;

    private static By headerLinkBy = By.className("header__link");

    private static By buttonLinkPrimaryBy = By.className("dt-button--primary");

    private static By headerLinkSecondaryBy = By.className("header__link--secondary");

    private static By buttonLinkSecondaryBy = By.className("dt-button--secondary");

    private static By headerDropdownSection = By.className("header__drop-down-section");


    public HeaderPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Clicks navigation option link based on the input text
     *
     * @param navigationLink TIRES|WHEELS|APPOINTMENTS|TIPS and GUIDES
     */
    public void clickNavigationOption(String navigationLink) {
        LOGGER.info("selectNavigationOption started");

        driver.waitForMilliseconds();

        if (Config.isSafari() || Config.isIe() || Config.isFirefox())
            driver.waitForPageToLoad();

        WebElement navLink = driver.getElementWithText(headerLinkBy, navigationLink);
        navLink.click();
        LOGGER.info("selectNavigationOption completed");
    }

    /**
     * Clicks sublink on menu under navigation option button
     *
     * @param menuOption Text of sublink to select
     */
    public void clickNavigationMenuOption(String menuOption) {
        LOGGER.info("selectNavigationOption started");

        driver.waitForMilliseconds();

        if (Config.isFirefox() || Config.isSafari()) {
            driver.waitForMilliseconds();

            //pulling button element if menuOption isn't a header element, then secondary button if it isn't primary
            WebElement menuLink = driver.getElementWithText(headerLinkSecondaryBy, menuOption);
            if (menuLink == null) {
                menuLink = driver.getElementWithText(buttonLinkPrimaryBy, menuOption);
            }
            if (menuLink == null) {
                menuLink = driver.getElementWithText(buttonLinkSecondaryBy, menuOption);
            }
            menuLink.click();
        } else {
            driver.clickElementByPartialText(menuOption);
        }
        if (Config.isFirefox())
            driver.waitForMilliseconds();

        LOGGER.info("selectNavigationOption completed");
    }


    /**
     * Clicks the site logo at the top of page
     */
    public void clickSiteLogo() {
        LOGGER.info("clickStoreLogo started");
        if (Config.isMobilePhone()) {
            driver.waitForElementClickable(MobileHeaderPage.dtLogo);
            MobileHeaderPage.dtLogo.click();
        } else {
            driver.waitForPageToLoad();
            driver.waitForElementClickable(dtLogo);
            try {
                dtLogo.click();
            } catch (TimeoutException e) {
                LOGGER.info("Page load timed out, attempting refresh...");
                webDriver.navigate().refresh();
                driver.waitForPageToLoad();
                driver.waitForElementClickable(dtLogo);
                dtLogo.click();
            }

        }
        LOGGER.info("clickStoreLogo completed");
    }

    /**
     * Clicks View All navigation option link based on the input text
     *
     * @param navigationLink TIRES|WHEELS|APPOINTMENTS|TIPS and GUIDES
     */
    public void clickViewAllNavigationOption(String navigationLink) {
        LOGGER.info("clickViewAllNavigationOption started");
        driver.waitForMilliseconds();

        WebElement navLink = driver.getElementWithText(headerDropdownSection, navigationLink);
        navLink.findElement(By.linkText(VIEW_ALL)).click();

        LOGGER.info("clickViewAllNavigationOption completed");
    }
    
    /**
     * Verify navigation option link is displayed based on the input text
     *
     * @param navigationLink TIRES|WHEELS|APPOINTMENTS|TIPS and GUIDES
     */
    public void assertNavigationOptionIsDisplayed(String navigationLink) {
        LOGGER.info("assertNavigationOptionIsDisplayed started");
        driver.waitForMilliseconds();

        if (Config.isSafari() || Config.isIe() || Config.isFirefox())
            driver.waitForPageToLoad();
        
        WebElement navLink = driver.getElementWithText(headerLinkBy, navigationLink);
        if(!driver.isElementDisplayed(navLink))
        	Assert.fail("FAIL: Navigation link " + navLink + " did NOT display on Global Header");
        LOGGER.info("assertNavigationOptionIsDisplayed completed");
    }
}