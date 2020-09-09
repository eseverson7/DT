package ovc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by eseverson on 6/27/2017.
 */
public class FitmentPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(FitmentPage.class.getName());

    public FitmentPage(Driver driver){
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }
    public static final String ON = "On";

    public static final String OFF = "Off";

    public static final String TIRES_AND_WHEELS = "Tires & Wheels";

    public static final String SPARE = "Spare";

    public static final String ACCESSORIES = "Accessories";

    public static final String ATV = "ATV";

    public static final String TRAILER = "Trailer";

    @FindBy(id = "on")
    public static WebElement onRadio;

    @FindBy(id = "off")
    public static WebElement offRadio;

    @FindBy(id = "roadtire")
    public static WebElement tiresWheelsRadio;

    @FindBy(id = "sparetire")
    public static WebElement spareRadio;

    @FindBy(id = "accessory")
    public static WebElement accessoryRadio;

    @FindBy(id = "atv")
    public static WebElement atvRadio;

    @FindBy(id = "trailer")
    public static WebElement trailerRadio;

    @FindBy(className = "fitmentQualifierDialog")
    public static WebElement fitmentQualifierBox;

    @FindBy(className = "customerVehicleData")
    public static WebElement customerVehicleData;

    public static final By fitmentSizeTableBy = By.className("wheel_tyre_size");

    public static final By fitmentColumnBy = By.className("col-sm-1");

    public static final By sizeValueBy = By.className("fa-square-o");

    public static final By resultsRowBy = By.className("productWrapper");

    public static final By custVehItemBtnBy = By.className("custVehItemBtn");

    /**
     * Selects first selection of entries from size table
     *
     * @param selection Integer (1 or 2)
     */
    public void selectFirstResultsFromSizeTable(int selection) {
        LOGGER.info("selectResultsFromSizeTable started");
        int i = 0;
        int col = 0;
        driver.waitForElementClickable(fitmentSizeTableBy);
        driver.waitForPageToLoad();

        i = selectColumnValue(selection, col, i);
        if (i < selection) {
            col++;
            selectColumnValue(selection, col, i);
        }

        LOGGER.info("selectResultsFromSizeTable completed");
    }

    /**
     * Helper method to click Tire/Wheel column options
     *
     * @param selection Integer (1 or 2)
     * @param col       Column to select from
     * @param i         Iterator
     * @return          Integer i
     */
    private int selectColumnValue(int selection, int col, int i) {
        LOGGER.info("selectColumnValue started");
        WebElement column = null;

        try {
            column = webDriver.findElements(fitmentSizeTableBy).get(2).findElements(fitmentColumnBy).get(col);
        } catch (Exception e) {
            Assert.fail("FAIL: Fitment results table not found.");
        }

        List<WebElement> options = column.findElements(sizeValueBy);
        for (WebElement option : options) {
            if (i < selection) {
                option.click();
                i++;
            }
        }

        LOGGER.info("selectColumnValue completed");
        return i;
    }

    /**
     * Selects specific selection of entries from size table
     *
     * @param selections String of tire sizes to select
     */
    public void selectSpecificResultsFromSizeTable(String selections) {
        LOGGER.info("selectSpecificResultsFromSizeTable started");
        driver.waitForElementClickable(fitmentSizeTableBy);
        driver.waitForPageToLoad();
        boolean found = false;

        List <WebElement> options =
                webDriver.findElements(fitmentSizeTableBy).get(2).findElements(CommonActions.tableRowBy);

        if (selections.contains(",")) {
            List<String> selection = Arrays.asList(selections.split(","));
            for (String select : selection) {
                for (WebElement option : options) {
                    if (option.getText().contains(select.trim())) {
                        option.click();
                        found = true;
                        break;
                    }
                }
            }
        } else {
            for (WebElement option : options) {
                if (option.getText().equalsIgnoreCase(selections)) {
                    option.click();
                    found = true;
                    break;
                }
            }
        }

        if (!found)
            Assert.fail("FAIL: Fitment selection page did not contain option(s): " + selections);
        LOGGER.info("Values selected from the table: " + selections);
        LOGGER.info("selectSpecificResultsFromSizeTable completed");
    }

    /**
     * Verifies search results exist on fitment page
     */
    public void assertFitmentResults() {
        LOGGER.info("assertFitmentResults started");
        driver.waitForElementClickable(CommonActions.homeIcon);
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        //TODO: THIS METHOD NEEDS REFACTORING TO BECOME MORE ROBUST
        List<WebElement> rows = CommonActions.resultsTable.findElements(resultsRowBy);
        Assert.assertTrue("FAIL: Fitment page results was empty.", !rows.isEmpty());
        for (WebElement row : rows) {
            Assert.assertTrue("FAIL: No Fitment page results were displayed.", driver.isElementDisplayed(row));
        }
        LOGGER.info("assertFitmentResults completed");
    }

    /**
     * Selects a set number of Product Results from the results table
     *
     * @param selection  Number of results to select
     */
    public void selectFitmentProductResults(int selection) {
        LOGGER.info("selectFitmentProductResults started");
        int i = 0;
        driver.waitForElementClickable(CommonActions.resultsTable);
        driver.waitForPageToLoad();
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        List<WebElement> rows = CommonActions.resultsTable.findElements(resultsRowBy);
        Assert.assertTrue("FAIL: Fitment page results was empty.", !rows.isEmpty());
        for (WebElement row : rows) {
            if (i < selection) {
                row.click();
                i++;
            }
        }
        LOGGER.info("selectFitmentProductResults completed");
    }

    /**
     * Asserts passed in text appears in the Fitment Qualifiers popup
     *
     * @param values Values to verify
     */
    public void assertFitmentQualifierText(String values) {
        LOGGER.info("assertFitmentQualifierText started");
        driver.waitForElementVisible(fitmentQualifierBox);
        Assert.assertTrue("FAIL: Fitment Qualifier textbox did not contain " + values,
                    fitmentQualifierBox.getText().contains(values));
        LOGGER.info("assertFitmentQualifierText completed");
    }

    /**
     * Clicks a radio button based on text passed
     *
     * @param button Text of the button to click
     */
    public void selectRadioButton(String button) {
        LOGGER.info("selectRadioButton started");
        driver.waitForElementClickable(onRadio);
        WebElement btn = returnRadioBtn(button);
        driver.waitForMilliseconds();
        btn.click();
        LOGGER.info("selectRadioButton started");
    }

    /**
     * Returns clickable radio button element
     *
     * @icon Text of the radio button on page to return
     * returns WebElement Radio button
     */
    private WebElement returnRadioBtn(String button) {
        LOGGER.info("returnRadioBtn started");
        WebElement radioToClick = null;

        if (button.equalsIgnoreCase(ON)) {
            radioToClick = onRadio;
        } else if (button.equalsIgnoreCase(OFF)) {
            radioToClick = offRadio;
        } else if (button.equalsIgnoreCase(TIRES_AND_WHEELS)) {
            radioToClick = tiresWheelsRadio;
        } else if (button.equalsIgnoreCase(SPARE)) {
            radioToClick = spareRadio;
        } else if (button.equalsIgnoreCase(ACCESSORIES)) {
            radioToClick = accessoryRadio;
        } else if (button.equalsIgnoreCase(ATV)) {
            radioToClick = atvRadio;
        } else if (button.equalsIgnoreCase(TRAILER)) {
            radioToClick = trailerRadio;
        }

        LOGGER.info("returnRadioBtn completed");
        return radioToClick;
    }

    /**
     * Selects the div element with the provided vehicle text
     *
     * @param vehicleDescription Vehicle description to click on
     */
    public void selectVehicleElement(String vehicleDescription) {
        LOGGER.info("selectVehicleElement started");
        driver.waitForElementClickable(CommonActions.homeIcon);
        driver.waitForMilliseconds();

        boolean found = false;
        List <WebElement> vehicles = customerVehicleData.findElements(custVehItemBtnBy);
        for (WebElement vehicle : vehicles) {
            if (vehicle.getText().contains(vehicleDescription)) {
                vehicle.click();
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: Vehicle '" + vehicleDescription + "' was NOT found on Fitment page!");

        driver.waitForPageToLoad();
        LOGGER.info("selectVehicleElement completed");
    }
}
