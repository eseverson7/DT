package dtc.pages;

import common.Config;
import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;


public class TipsAndGuidesPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(TipsAndGuidesPage.class.getName());

    public TipsAndGuidesPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static final By table = By.cssSelector(".yCmsComponent  > table");

    private static final By tag = By.tagName("a");

    @FindBy(linkText = "Top of Page")
    public static WebElement topOfPage;

    /**
     * Helper method to access driver method, clicking a page link with text
     *
     * @param linkText Text of link to click
     */
    public void clickPageLink(String linkText) {
        LOGGER.info("clickPageLink " + linkText + " started");
        driver.clickElementWithLinkText(linkText);
        LOGGER.info("clickPageLink " + linkText + " complete");
    }

    /**
     * Clicks each link on Brands page and verifies it moves the screen
     */
    public void verifyBrandLinks() {
        LOGGER.info("verifyBrandLinks started");
        WebElement returnElement = webDriver.findElement(table);
        driver.waitForElementVisible(returnElement);

        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        Long before = (Long) executor.executeScript("return window.pageYOffset;");

        List<WebElement> elements = returnElement.findElements(tag);
        for (WebElement element : elements) {
            //TODO: remove once geckodriver/marionette is updated & stabilized
            if (Config.isFirefox()) {
                driver.jsMoveToElementClick(element);
            } else {
                element.click();
            }
            Long after = getYOffset();
            Assert.assertTrue("FAIL: Page link did not work: " + element.getText(), after > before);
            driver.jsScrollToElementClick(topOfPage);
            before = getYOffset();
            Assert.assertTrue("FAIL: Brand links page did not scroll back to top.",
                    before.toString().equals(Integer.toString(Constants.ZERO)));
        }
        LOGGER.info("verifyBrandLinks complete");
    }

    /**
     * Gets the Y coordinates of the visible screen
     *
     * @return Long type element
     */
    public Long getYOffset() {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        Long after = (Long) executor.executeScript("return window.pageYOffset;");
        return after;
    }
}