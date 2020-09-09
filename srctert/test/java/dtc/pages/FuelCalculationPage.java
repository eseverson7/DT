package dtc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mukul Garg on 10/30/2017.
 */
public class FuelCalculationPage {

    private WebDriver webDriver;
    private Driver driver;
    private final Logger LOGGER = Logger.getLogger(FuelCalculationPage.class.getName());
    private CommonActions commonActions;

    public FuelCalculationPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
    }

    @FindBy(id = "avg-miles-driven")
    public static WebElement avgMilesDriven;

    @FindBy(id = "avg-mpg")
    public static WebElement averageMPG;

    @FindBy(id = "gas-price")
    public static WebElement gasPrice;

    @FindBy(className = "calculator__output__results")
    public static WebElement calculatorResults;

    public static By li = By.tagName("li");

    public static final String MILESPERDAY = "Miles Per Day";
    public static final String GASPRICE = "Gas Price";
    public static final String AVERAGEGMPG = "Average MPG";

    /**
     * Fills the specified field with the specified value for fuel calculations
     *
     * @param field Field of Fuel Calculations element to fill
     * @param value String value to fill field with
     */
    public void enterFuelCalculationTextBoxValue(String field, String value) {
        LOGGER.info("enterFuelCalculationTextBoxValue started");
        WebElement fieldEle = null;

        if (field.equalsIgnoreCase(MILESPERDAY)) {
            fieldEle = avgMilesDriven;
        } else if (field.equalsIgnoreCase(GASPRICE)) {
            fieldEle = gasPrice;
        } else if (field.equalsIgnoreCase(AVERAGEGMPG)) {
            fieldEle = averageMPG;
        }

        fieldEle.click();
        fieldEle.clear();
        fieldEle.sendKeys(value);
        driver.waitForPageToLoad();

        LOGGER.info("enterFuelCalculationTextBoxValue completed");
    }

    /**
     * Assert fuel calculation is specific % of 5 PSI low, 10 PSI low, 15 PSI low and 20 PSI low
     */
    public void assertFuelCalculationPSIValues() {
        LOGGER.info("assertFuelCalculationPSIValues started");
        float psiValue;
        List<WebElement> allPSIElements = calculatorResults.findElements(li);


        for (WebElement psiElement : allPSIElements) {
            psiValue = Float.parseFloat(driver.getLastSubstring(psiElement, "$"));
            Assert.assertTrue("FAIL: The PSI value is invalid: " + psiValue, psiValue > Constants.ZERO);
        }
        LOGGER.info("assertFuelCalculationPSIValues completed");
    }
}