package dtc.pages;

import common.Config;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 4/27/17.
 */
public class MyVehiclesPopupPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(MyVehiclesPopupPage.class.getName());

    public MyVehiclesPopupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "popover--my-vehicles")
    public static WebElement myVehiclePopup;

    @FindBy(className = "mobile-menu__vehicles")
    public static WebElement myVehiclePopupMobile;

    @FindBy(className = "my-vehicles__controls-message")
    public static WebElement vehicleControlMessage;

    @FindBy(css = ".dt-button.dt-button--secondary")
    public static WebElement currentVehicle;

    private static final By recentVehicleBy = By.className("my-vehicles__recent-vehicles-button");

    private static final By currentVehicleBy = By.className("dt-button");

    private static final By myVehicleHeaderLinkBy = By.cssSelector(".dt-button.track-header-link");

    private static final By myVehiclesHeaderBy = By.className("header__my-vehicles-label");

    private static final String VEHICLE_LIMIT_MESSAGE = "Vehicle limit reached (4)Remove vehicles before adding more";

    private static final String ADD_VEHICLE = "Add Vehicle";

    /**
     * Clicks specified recent vehicle
     *
     * @param vehicle The vehicle to click
     */
    public void clickRecentVehicle(String vehicle) {
        LOGGER.info("clickRecentVehicle started");
        driver.waitForElementVisible(recentVehicleBy);
        driver.getElementWithText(recentVehicleBy, vehicle).click();
        LOGGER.info("clickRecentVehicle completed");
    }

    /**
     * Verifies the current vehicle
     *
     * @param vehicle   The vehicle to check
     */
    public void assertCurrentVehicle(String vehicle) {
        LOGGER.info("assertCurrentVehicle started");
        WebElement element;

        if (Config.isMobile()) {
            element = myVehiclePopupMobile;
        } else {
            element = myVehiclePopup;
        }

        driver.waitForElementVisible(element);
        Assert.assertTrue("FAIL: \"" + vehicle + "\" was not listed as current vehicle.",
                !driver.getElementWithText(currentVehicleBy, vehicle).equals(null));

        LOGGER.info("Confirmed that \"" + vehicle + "\" was listed as current vehicle.");
        LOGGER.info("assertCurrentVehicle completed");
    }

    /**
     * Verifies the current vehicle
     */
    public void assertVehicleLimitMessage() {
        LOGGER.info("assertVehicleLimitMessage started");
        driver.waitForElementVisible(vehicleControlMessage);
        String vehicleMessage = vehicleControlMessage.getText().replaceAll("\n", "");

        Assert.assertTrue("FAIL: Vehicle limit message was not displayed, but instead contained \"" +
                        vehicleMessage + "\".", vehicleMessage.contains(VEHICLE_LIMIT_MESSAGE));

        LOGGER.info("Confirmed that the vehicle limit message was displayed.");
        LOGGER.info("assertVehicleLimitMessage completed");
    }

    /**
     * Verify Add vehicle link displayed for my vehicle popup
     */
    public void assertAddVehicle(){
        LOGGER.info("assertAddVehicle started");
        driver.waitForPageToLoad();
        WebElement addVehicle = driver.getElementWithText(myVehicleHeaderLinkBy, ADD_VEHICLE);
        Assert.assertTrue("FAIL: Add Vehicle not displayed in My Vehicle Popup" , driver.isElementDisplayed(addVehicle));
        LOGGER.info("assertAddVehicle completed");
    }

    /**
     * Clicks current vehicle on My Vehicle popup
     */
    public void clickCurrentVehicle() {
        LOGGER.info("clickCurrentVehicle started");
        driver.waitForPageToLoad();
        driver.jsClick(currentVehicle);
        LOGGER.info("clickCurrentVehicle completed");
    }

    /**
     * Verifies the display of current vehicle in My Vehicles section
     */
    public void assertMyVehicles(String vehicle) {
        LOGGER.info("assertMyVehicles started");
        driver.waitForPageToLoad();
        WebElement myVehicles = driver.getElementWithText(myVehiclesHeaderBy,vehicle);
        Assert.assertTrue("FAIL: Vehicle not displayed in My Vehicle header" , driver.isElementDisplayed(myVehicles));
        LOGGER.info("assertMyVehicles completed");
    }

    /**
     * Verify that specified vehicle listed in recent vehicle
     *
     * @param vehicle The vehicle to verify in recent vehicles
     */
    public void assertRecentVehicleDisplay(String vehicle) {
        LOGGER.info("assertRecentVehicleDisplay started for " + vehicle);
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: Vehicle not displayed in recent vehicles" ,
                driver.isElementDisplayed(driver.getElementWithText(recentVehicleBy, vehicle)));
        LOGGER.info("assertRecentVehicleDisplay completed for " + vehicle);
    }
}
