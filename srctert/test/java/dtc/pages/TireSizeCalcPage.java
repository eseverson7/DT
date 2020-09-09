package dtc.pages;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import static common.Constants.NEW;

/**
 * Created by Channing Luden on 12/1/2016.
 */
public class TireSizeCalcPage {

    private WebDriver webDriver;
    private Driver driver;
    private final Logger LOGGER = Logger.getLogger(TireSizeCalcPage.class.getName());
    private CommonActions commonActions;

    public TireSizeCalcPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
    }

    private static final String CURRENT = "Current";
    private static final String DIFFERENCE = "Difference";
    private static final String SPEEDOMETER_SECTION_MESSAGING = "If your speedometer reads";
    private static final String SIDE_BY_SIDE_CURRENT_SIZE = "Current Size";
    private static final String SIDE_BY_SIDE_NEW_SIZE = "New Size";
    private static final String DISCLAIMER_SECTION_TITLE = "Disclaimers";

    @FindBy(id = "currWidth")
    public static WebElement currentWidth;

    @FindBy(id = "currAspectRatio")
    public static WebElement currentAspectRatio;

    @FindBy(id = "currWheelDiameter")
    public static WebElement currentWheelDiameter;

    @FindBy(id = "newWidth")
    public static WebElement newWidth;

    @FindBy(id = "newAspectRatio")
    public static WebElement newAspectRatio;

    //TODO - ask dev team to add id like the other controls; probably just missed/forgot this one
    @FindBy(xpath = "//label[@for=\"newWheelDiameter\"]/input")
    public static WebElement newWheelDiameter;

    @FindBy(tagName = "table")
    public static WebElement calcResultsTable;

    //TODO - ask for auto classname; update/remove xpath when available
    @FindBy(xpath = "(//p[@class=\"calculator__compare-speed\"]/input)[2]")
    public static WebElement speedometerReading;

    final private By disclaimerSection = By.className("yCmsComponent ");

    final private By speedometerSection = By.className("calculator__compare-speed");

    final private By speedometerActualSpeed = By.className("actual-mph");

    final private By sideBySideComparisonSection = By.className("calculator__comparison");

    //TODO - investigate possibility of auto classname for result table cells
    private String resultTableCellByColumnLoc =
            "//div[@class='calculator__output']//tr//td[text()='%1s:']/../td[%2s]";

    final private static List<String> resultTableRowNamesList =
            Arrays.asList("Sidewall Height", "Section Width", "Tire Height", "Tire Circum", "Rev. / Mile");

    private List<Double> prevCurrentValuesList;
    private List<Double> prevNewValuesList;
    private List<Double> prevDifferenceValuesList;

    /***
     * Verifies the basic display properties of the Tire Size Calculator page.
     * Validates presence of: inputs for tire sizes (current and new), calculator results table, speedometer section,
     * a speedometer reading, side by side comparison section (current and new sizes), and disclaimer section.
     */
    public void verifyBasicDisplayOfPage() {
        LOGGER.info("verifyBasicDisplayOfPage started");
        List<WebElement> calcInputs = Arrays.asList(currentWidth, currentAspectRatio, currentWheelDiameter, newWidth,
                newAspectRatio, newWheelDiameter);

        for (WebElement element : calcInputs) {
            Assert.assertTrue("FAIL: Unable to find Tire Size Calc. element: \"" + element.toString() + "\"!",
                    element.isDisplayed());
        }

        Assert.assertTrue("FAIL: Unable to find the Tire Size Calc. Results table!",
                calcResultsTable.isDisplayed());
        commonActions.assertElementWithTextIsVisible(speedometerSection, SPEEDOMETER_SECTION_MESSAGING);
        Assert.assertTrue("FAIL: Unable to find the speedometer comparison input reading!",
                speedometerReading.isDisplayed());

        commonActions.assertElementWithTextIsVisible(sideBySideComparisonSection, SIDE_BY_SIDE_CURRENT_SIZE);
        commonActions.assertElementWithTextIsVisible(sideBySideComparisonSection, SIDE_BY_SIDE_NEW_SIZE);
        commonActions.assertElementWithTextIsVisible(disclaimerSection, DISCLAIMER_SECTION_TITLE);
        LOGGER.info("verifyBasicDisplayOfPage completed");
    }

    /***
     * Enters the specified dimensions for a tire size into either the current or new input fields. Also calls
     * updateCalcResultValuesLists() so the previous values for 3 columns are saved for update validation.
     * @param tireSizeType specifies group of input fields for the tire dimensions. Supports "current" or "new"
     * @param tireDiameter String representing the value to be entered in Tire Diameter input field
     * @param tireWidth String representing the value to be entered in Tire Width input field
     * @param wheelDiameter String representing the value to be entered in Wheel Diameter input field
     */
    public void enterTireSizeIntoCalc(String tireSizeType, String tireDiameter, String tireWidth,
                                      String wheelDiameter) {
        LOGGER.info("enterTireSizeIntoCalc started - updating '" + tireSizeType + "' calculator values");
        driver.waitForElementVisible(currentWidth);

        List<WebElement> currentMeasurementFields = Arrays.asList(currentWidth, currentAspectRatio,
                currentWheelDiameter);
        List<WebElement> newMeasurementFields = Arrays.asList(newWidth, newAspectRatio,
                newWheelDiameter);

        List<WebElement> fieldsList = new ArrayList<WebElement>();
        List<String> fieldValues = Arrays.asList(tireDiameter, tireWidth, wheelDiameter);

        if (tireSizeType.equalsIgnoreCase(CURRENT)) {
            fieldsList.addAll(currentMeasurementFields);
        } else {
            fieldsList.addAll(newMeasurementFields);
        }

        updateCalcResultValuesLists();

        for (int i = 0; i < fieldsList.size(); i++) {
            fieldsList.get(i).clear();
            fieldsList.get(i).sendKeys(fieldValues.get(i));
        }
        LOGGER.info("enterTireSizeIntoCalc completed  - updated '" + tireSizeType + "' calculator values");
    }

    /***
     * Validates the Tire Size Calculator Results table has been updated by retrieving previous column values from
     * the results table and comparing for differences against the current column values in the table.
     */
    public void verifyTireSizeCalcResultsTableUpdated() {
        LOGGER.info("verifyTireSizeCalcResultsTableUpdated started");
        boolean tableDataUpdated = false;
        Integer updatedColumnCount = 0;

        if (!prevCurrentValuesList.isEmpty() &&
                !Sets.symmetricDifference(new HashSet<Double>(prevCurrentValuesList),
                        new HashSet<Double>(getTireCalcResultsColumnData(CURRENT))).isEmpty()) {
            updatedColumnCount++;
        }

        if (!prevNewValuesList.isEmpty() &&
                !Sets.symmetricDifference(new HashSet<Double>(prevNewValuesList),
                        new HashSet<Double>(getTireCalcResultsColumnData(NEW))).isEmpty()) {
            updatedColumnCount++;
        }

        if (!prevDifferenceValuesList.isEmpty() &&
                !Sets.symmetricDifference(new HashSet<Double>(prevDifferenceValuesList),
                        new HashSet<Double>(getTireCalcResultsColumnData(DIFFERENCE))).isEmpty()) {
            updatedColumnCount++;
        }

        if (updatedColumnCount > 0) {
            tableDataUpdated = true;
        }

        Assert.assertTrue("FAIL: None of the Tire Size Calculator Result Columns were updated with new dtc.data!",
                tableDataUpdated);
        LOGGER.info("verifyTireSizeCalcResultsTableUpdated completed");
    }

    /***
     * Validates the Side By Side Comparision section contains the a tire with specified dimensions
     * @param tireDiameter String representing the value to be entered in Tire Diameter input field
     * @param tireWidth String representing the value to be entered in Tire Width input field
     * @param wheelDiameter String representing the value to be entered in Wheel Diameter input field
     */
    public void verifySideBySideSectionContainsTireSize(String tireDiameter, String tireWidth, String wheelDiameter) {
        LOGGER.info("verifySideBySideSectionContainsTireSize started for tire size of: "
                + tireDiameter + "/" + tireWidth + "R" + wheelDiameter);
        commonActions.assertElementWithTextIsVisible(sideBySideComparisonSection,
                tireDiameter + "/" + tireWidth + "R" + wheelDiameter);
        LOGGER.info("verifySideBySideSectionContainsTireSize completed tire size of: "
                + tireDiameter + "/" + tireWidth + "R" + wheelDiameter + " was found");
    }

    /***
     * Enters specified speed into the input field of the speedometer section (speedometer reads xx)
     * @param speedToEnter String representing the value to be entered in the Speedometer Reads input field
     */
    public void enterNewSpeedIntoSpeedometer(String speedToEnter) {
        LOGGER.info("enterNewSpeedIntoSpeedometer started - entering speed of " + speedToEnter);
        driver.waitForElementVisible(speedometerReading);
        speedometerReading.clear();
        speedometerReading.sendKeys(speedToEnter);
        LOGGER.info("enterNewSpeedIntoSpeedometer completed - entered speed of " + speedToEnter);
    }

    /***
     * Validates the actual traveling speed displayed in the speedometer section matches the expected traveling speed
     * @param expectedTravelingSpeed String representing the expected value of the actual traveling speed label
     */
    public void verifyUpdatedTravelingSpeed(String expectedTravelingSpeed) {
        LOGGER.info("verifyUpdatedTravelingSpeed started - expected actual traveling speed to be "
                + expectedTravelingSpeed);
        commonActions.assertElementWithTextIsVisible(speedometerActualSpeed, expectedTravelingSpeed);
        LOGGER.info("verifyUpdatedTravelingSpeed completed - expected actual traveling speed of "
                + expectedTravelingSpeed + " matches displayed value");
    }

    /***
     * Gets the currently displayed values for a specified column in the Tire Size Calculator results table. Removes "
     * symbol from the cell values.
     * @param columnDataToRetrieve String representing the column for which to return current values
     * @return List of doubles containing the values for the specified column
     */
    private List<Double> getTireCalcResultsColumnData(String columnDataToRetrieve) {
        //TODO based on column name, loop through the rows and store dtc.data in new list to be returned
        LOGGER.info("getTireCalcResultsColumnData started for column: " + columnDataToRetrieve);
        String resultColumnNumber;
        List<Double> columnDataList = new ArrayList<Double>();

        if (columnDataToRetrieve.equalsIgnoreCase(CURRENT)) {
            resultColumnNumber = "2";
        } else if (columnDataToRetrieve.equalsIgnoreCase(NEW)) {
            resultColumnNumber = "3";
        } else if (columnDataToRetrieve.equalsIgnoreCase(DIFFERENCE)) {
            resultColumnNumber = "4";
        } else {
            resultColumnNumber = "2";
        }

        for (String resultTableRowName : resultTableRowNamesList) {
            String cellText = webDriver.findElement(By.xpath(
                    String.format(resultTableCellByColumnLoc, resultTableRowName, resultColumnNumber))).getText();
            cellText = cellText.replace("\"", "");
            columnDataList.add(Double.valueOf(cellText));
        }

        LOGGER.info("getTireCalcResultsColumnData completed for column: " + columnDataToRetrieve);
        return columnDataList;
    }

    /***
     * Updates previous column values lists for the Tire Size Calculator results table. Provides a prior state of the
     * tire size column values to be compared with a later update to the table.
     */
    private void updateCalcResultValuesLists() {
        LOGGER.info("updateCalcResultValuesLists started");
        prevCurrentValuesList = getTireCalcResultsColumnData(CURRENT);
        prevNewValuesList = getTireCalcResultsColumnData(NEW);
        prevDifferenceValuesList = getTireCalcResultsColumnData(DIFFERENCE);
        LOGGER.info("updateCalcResultValuesLists completed");
    }
}