package dtc.pages;

import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ericseverson on 10/10/16.
 */
public class StoreDetailsPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(StoreDetailsPage.class.getName());

    public StoreDetailsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(name = "isAppointment")
    public static WebElement scheduleAppointmentButton;

    @FindBy(className = "store-details-title")
    public static WebElement storeTitle;

    @FindBy(css = ".store-details-option__items > ul")
    public static WebElement storeAddress;

    @FindBy(css = ".store-openings.weekday_openings")
    public static WebElement storeHours;

    @FindBy(className = "store-details-title")
    public static WebElement storeDetailTitle;

    /**
     * Verifies the text of a given element
     *
     * @param element Element that contains the text to verify
     * @param text    String to validate against element text
     */
    public void verifyElementText(WebElement element, String text) {
        LOGGER.info("verifyElementText started");
        driver.waitForElementClickable(storeDetailTitle);
        Assert.assertTrue("FAIL: Element \"" + element + "\" was NOT found on page!",
                element.getText().trim().contains(text));
        LOGGER.info("verifyElementText completed");
    }

    /**
     * Verifies the current store details in the header
     *
     * @param title   Store title
     * @param address Store address
     * @param days    Days of operation
     */
    public void verifyMyStoreDetails(String title, String address, String days) {
        LOGGER.info("verifyMyStoreDetails started");
        driver.waitForElementClickable(scheduleAppointmentButton);
        Assert.assertTrue("FAIL: Store in header: \"" + storeTitle.getText() + "\" did NOT contain title \""
                        + title + "\"!",
                storeTitle.getText().trim().contains(title));
        Assert.assertTrue("FAIL: In the header, the store's address: \"" + storeAddress.getText()
                        + "\" did NOT match expected address: \"" + address + "\"!",
                storeAddress.getText().trim().contains(address.toUpperCase()));
        List<String> hour = Arrays.asList(days.split("\\s*,\\s*"));
        for (String day : hour) {
            Assert.assertTrue("FAIL: The store's open hours did not contain days: \"" + days + "\"!",
                    storeHours.getText().trim().contains(day));
        }
        LOGGER.info("verifyMyStoreDetails completed");
    }

    /***
     * Verifies the current store's days of operation and store hours
     */
    public void verifyStoreHoursForCurrentStore() {
        LOGGER.info("verifyStoreHoursForCurrentStore started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(scheduleAppointmentButton);
        for (String daysHours : ConstantsDtc.STORE_DETAILS_STORE_HOURS) {
            Assert.assertTrue("FAIL: The store hours of: " + storeHours.getText()
                    + " for the current store did NOT contain expected day and hour combination of: \""
                    + daysHours + "\"!" , storeHours.getText().contains(daysHours));
        }
        LOGGER.info("verifyStoreHoursForCurrentStore completed");
    }

    /**
     * Clicks the "Schedule appointment" button on the Store Details page
     */
    public void clickScheduleAppointmentStoreDetails() {
        LOGGER.info("clickScheduleAppointmentStoreDetails started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(scheduleAppointmentButton);
        driver.jsScrollToElement(scheduleAppointmentButton);
        scheduleAppointmentButton.click();
        LOGGER.info("clickScheduleAppointmentStoreDetails completed");
    }
}