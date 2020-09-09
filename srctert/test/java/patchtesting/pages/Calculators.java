package patchtesting.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

public class Calculators {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public Calculators(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "#ss_outside > a")
    public static WebElement specialOrdersImg;

    @FindBy(css = "#ss_education > a")
    public static WebElement educationImg;

    public static final String SPECIAL_ORDERS = "Special Orders";
    public static final String EDUCATION = "Education";

    /**
     * Selects a sub-menu option via provided menu and sub-menu options
     *
     * @param menuOption    Menu header to click
     * @param subMenuOption Sub-menu option to click as well
     */
    public void selectSubMenuOptionWithClick(String menuOption, String subMenuOption) {
        LOGGER.info("selectSubMenuOptionWithClick started");
        driver.waitForMilliseconds();

        WebElement option = returnCalculatorHeaderElement(menuOption);
        option.click();

        driver.waitForMilliseconds();
        By subMenuLink = By.linkText(subMenuOption);
        webDriver.findElement(subMenuLink).click();
        LOGGER.info("selectSubMenuOptionWithClick completed");
    }

    /**
     * Helper method to return webelement for click
     *
     * @param menuOption Text value of header to be clicked
     * @return element   Generated WebElement based on menuOption passed in
     */
    public WebElement returnCalculatorHeaderElement(String menuOption) {
        LOGGER.info("returnCalculatorHeaderElement started");
        WebElement element = null;

        switch (menuOption) {
            case SPECIAL_ORDERS:
                element = specialOrdersImg;
                break;
            case EDUCATION:
                element = educationImg;
                break;
            default:
                Assert.fail("Passed in menuOption (" + menuOption + ") does not match header on Calculator page.");
        }

        LOGGER.info("returnCalculatorHeaderElement completed");
        return element;
    }
}
