package ovc.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by eseverson on 4/16/2018.
 */
public class OVCDashboard {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public OVCDashboard(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public static final String REINALT_THOMAS = "Reinalt-Thomas";

    private static String[] REINALT_THOMAS_VALUES = { "IN", "AZ", "KY", "IA", "SC", "ID", "WY", "NV", "VA", "TN", "MS",
            "MT", "OH", "GA", "FL", "OK", "MO", "MN", "AL", "CO", "WA", "IL", "AR", "UT", "CA", "KS", "WI", "NM", "TX",
            "OR", "LA", "MI", "NE", "NC" };

    /**
     * Clicks an addition (+) or subtraction (-) element on the page
     *
     * @param text Text of the label next to the (+)/(-) symbol
     */
    public void selectOVCDashboardAdditionSubtractionSymbol(String text) {
        LOGGER.info("selectOVCDashboardAdditionSubtractionSymbol started");
        driver.waitForPageToLoad();
        By byElement = By.xpath("//p[text()='" + text + "']/parent::span/parent::span/preceding-sibling::span/span");
        WebElement element = webDriver.findElement(byElement);
        element.click();
        LOGGER.info("selectOVCDashboardAdditionSubtractionSymbol completed");
    }

    /**
     * Asserts that certain values are
     *
     * @parame label Text of the label to validate its descendants
     */
    public void verifyDescedantLabelsAreVisible(String text) {
        LOGGER.info("verifyDescedantLabelsAreVisible started");
        if (text.equalsIgnoreCase(REINALT_THOMAS)) {
            for (String value : REINALT_THOMAS_VALUES) {
                By label = By.xpath("//p[text()='" + value + "']");
                Assert.assertTrue("FAIL: Value - '" + value + "' was NOT visible on OVC Dashboard screen.",
                        driver.isElementDisplayed(label));
            }
        }
        LOGGER.info("verifyDescedantLabelsAreVisible completed");
    }
}
