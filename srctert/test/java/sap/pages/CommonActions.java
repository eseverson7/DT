package sap.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import utilities.Driver;

import java.util.logging.Logger;

public class CommonActions {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public CommonActions(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "URLSPW-0")
    public static WebElement selectDocumentiFrame;

    public static final By tBodyBy = By.tagName("tbody");

    public static final By spanTagBy = By.tagName("span");

    public static final By imgTagBy = By.tagName("img");

    /**
     * Switches frame context to the select other doc.
     */
    public void switchToSelectDocPopupFrame() {
        LOGGER.info("switchToSelectDocPopupFrame started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(selectDocumentiFrame);
        webDriver.switchTo().frame(selectDocumentiFrame);
        LOGGER.info("switchToSelectDocPopupFrame completed");
    }
}
