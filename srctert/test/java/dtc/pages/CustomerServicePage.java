package dtc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/22/16.
 */
public class CustomerServicePage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CustomerServicePage.class.getName());

    public CustomerServicePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "dt-list")
    private static WebElement cardList;

    /**
     * Clicks customer care links based on the input text
     *
     * @param linkText Text of link to click
     */
    public void clickCustomerCareLinks(String linkText) {
        LOGGER.info("clickCustomerCareLinks started with linkText = \"" + linkText + "\"");
        //TODO: These links need to be updated with autoClass names
        driver.clickElementWithLinkText(linkText);
        LOGGER.info("clickCustomerCareLinks completed with linkText = \"" + linkText + "\"");
    }

    /**
     * Verifies all credit card options from Constants class appears on the page
     */
    public void verifyMajorCreditCardsDisplay() {
        LOGGER.info("verifyMajorCreditCardsDisplay started");
        for (String card : Constants.CREDIT_CARDS) {
            Assert.assertTrue("FAIL: Page did not contain element " + card, cardList.getText().contains(card));
        }
        LOGGER.info("verifyMajorCreditCardsDisplay completed");
    }

}