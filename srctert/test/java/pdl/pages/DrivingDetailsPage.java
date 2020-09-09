package pdl.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pdl.data.ConstantsPdl;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 4/24/17.
 */
public class DrivingDetailsPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(DrivingDetailsPage.class.getName());

    public DrivingDetailsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "auto-zipcode")
    public static WebElement zipcode;

    @FindBy(className = "pdl-driving-location__city-state")
    public static WebElement cityState;

    @FindBy(id = "auto-year")
    public static WebElement year;

    @FindBy(id = "auto-make")
    public static WebElement make;

    @FindBy(id = "auto-model")
    public static WebElement model;

    @FindBy(id = "auto-trim")
    public static WebElement trim;

    @FindBy(id = "auto-assembly")
    public static WebElement assembly;

    @FindBy(id = "auto-miles-drive")
    public static WebElement milesDriven;

    @FindBy(className = "pdl-tire-size__info")
    public static WebElement tireSize;
    
    @FindBy(id = "auto-driving-priorities-typical")
    public static WebElement drivingPriorityTypical;

    @FindBy(id = "auto-driving-priorities-performance")
    public static WebElement drivingPriorityPerformance;

    @FindBy(id = "auto-view-recommendations")
    public static WebElement viewRecommendationsButton;
    
    @FindBy(id = "auto-optionalsizes-size")
    public static WebElement notYourTireLink;
    
    @FindBy(id = "auto-tiresize-oe")
    public static WebElement tireSizeOE;
    
    @FindBy(id = "auto-tiresize-optional")
    public static WebElement tireSizeOptional;
    
    @FindBy(id = "auto-miles-driven-gauge")
    public static WebElement milesDrivenGauge;
    
    @FindBy(className = "pdl-page__right-col")
    public static WebElement rightColumn;

    @FindBy(className = "pdl-page__left-col")
    public static WebElement leftColumn;
    
    public static By genericSelectBy = By.className("pdl-car-select");

    public static By drivingPriorityBy = By.id("auto-priorities-list-name");

    public static final String VIEW_TIRE_RECOMMENDATIONS = "View Tire Recommendations";
    private static final String YEAR = "Select Year";
    private static final String MAKE = "Select Make";
    private static final String MODEL = "Select Model";
    private static final String TRIM = "Select Trim";
    private static final String NOT_YOUR_TIRE = "not your tire";
    private static final String TYPICAL = "typical";
    private static final String PERFORMANCE = "performance";
    private static final String OE = "oe";
    private static final String OPTIONAL = "optional";
    public static final String miles5K = "5";
    public static final String miles10K = "10";
    public static final String miles15K = "15";
    public static final String miles20K = "20";
    public static final String miles30K = "30";
    private static final String NO_MATCH_ERROR = "Unable to find tires that match the current size";
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";
    public static final String DEFAULT_TIRE_SIZE = "XXX/XX XX";

    /**
     * Verifies the default car dropdown values
     *
     */
    public void verifyDefaultCarDropdownValues() {
        LOGGER.info("verifyDefaultCarDropdownValues started");
        String valuesDropdowns = "";

        driver.waitForElementVisible(year);

        if (!year.getText().substring(0, YEAR.length()).equalsIgnoreCase(YEAR)) {
            valuesDropdowns = valuesDropdowns + "Expected value:" + YEAR + ", Actual Value:" + year.getText() + "\n";
        }
        if (!make.getText().substring(0, MAKE.length()).equalsIgnoreCase(MAKE)) {
            valuesDropdowns = valuesDropdowns + "Expected value:" + MAKE + ", Actual Value:" + make.getText() + "\n";
        }
        if (!model.getText().substring(0, MODEL.length()).equalsIgnoreCase(MODEL)) {
            valuesDropdowns = valuesDropdowns + "Expected value:" + MODEL + ", Actual Value:" + model.getText() + "\n";
        }
        if (!trim.getText().substring(0, TRIM.length()).equalsIgnoreCase(TRIM)) {
            valuesDropdowns = valuesDropdowns + "Expected value:" + TRIM + ", Actual Value:" + trim.getText() + "\n";
        }

        if (!valuesDropdowns.equalsIgnoreCase("")) {
            Assert.fail("FAIL: The following default values checks failed: \n" + valuesDropdowns);
        } else {
            LOGGER.info("Confirmed all default car dropdown values");
        }
        LOGGER.info("verifyDefaultCarDropdownValues completed");
    }


    /**
     * Verifies the miles driven value
     *
     * @param expectedMiles The miles value to check
     */
    public void verifyMilesDriven(String expectedMiles) {
        LOGGER.info("verifyMilesDriven started");

        driver.waitForElementVisible(milesDriven);
        String actualMiles = milesDriven.getText();
        Assert.assertTrue("FAIL: Expected miles driven: \"" + expectedMiles + "\", Actual miles driven: \"" +
                actualMiles + "\"", expectedMiles.equalsIgnoreCase(actualMiles));
        LOGGER.info("Confirmed that the Miles Driven value was \"" + expectedMiles + "\".");
        LOGGER.info("verifyMilesDriven completed");
    }


    /**
     * Verifies the given driving priority is selected
     *
     * @param drivingPriorityType   The type of priority to verify whether selected
     */
    public void verifySelectedDrivingPriority(String drivingPriorityType) {
        LOGGER.info("verifySelectedDrivingPriority started");
        int index = 0;
        WebElement drivingPriorityEl;

        if (drivingPriorityType.equalsIgnoreCase(ConstantsPdl.TYPICAL)) {
            drivingPriorityEl = drivingPriorityTypical;
        } else {
            drivingPriorityEl = drivingPriorityPerformance;
        }

        driver.waitForElementVisible(drivingPriorityEl);


        LOGGER.info("verifySelectedDrivingPriority completed");
    }


    /**
     * Selects a vehicle by selecting specified values from dropdown controls
     *
     * @param yearValue       The year value to select
     * @param makeValue       The make value to select
     * @param modelValue      The model value to select
     * @param trimValue       The trim value to select
     * @param assemblyValue   The assembly value to select
     * @throws Exception Exception
     */
    public void selectVehicle(String yearValue, String makeValue, String modelValue,
                              String trimValue, String assemblyValue) throws Exception {
        LOGGER.info("selectVehicle started");
        try {
            driver.waitForElementVisible(year);
            driver.selectFromDropdownByVisibleText(year, yearValue);
            driver.selectFromDropdownByVisibleText(make, makeValue);
            driver.selectFromDropdownByVisibleText(model, modelValue);
            driver.selectFromDropdownByVisibleText(trim, trimValue);
            if (!assemblyValue.equalsIgnoreCase(Constants.NONE)) {
                driver.selectFromDropdownByVisibleText(assembly, assemblyValue);
            }
        } catch (Exception e) {
            Assert.fail("FAIL: Selecting vehicle FAILED with error: " + e);
        }
        LOGGER.info("selectVehicle completed");
    }


    /**
     * Clicks View Tire Recommendations button
     * @throws Exception Exception
     */
    public void viewRecommendations() throws Exception {
        LOGGER.info("viewRecommendations started");
        try {
            driver.waitForElementClickable(viewRecommendationsButton);
            driver.jsScrollToElement(viewRecommendationsButton);
            viewRecommendationsButton.click();
        } catch (Exception e) {
            Assert.fail("FAIL: Clicking View Tire Recommendations button FAILED with error: " + e);
        }
        LOGGER.info("viewRecommendations completed");
    }
    
    public void clickingDrivingPriorityPerformance() throws Exception {
    	 LOGGER.info("clickingDrivingPriorityPerformance started");
         driver.waitForElementClickable(drivingPriorityPerformance);
    	 drivingPriorityPerformance.click();
         LOGGER.info("clickingDrivingPriorityPerformance completed");
    }
    
    
    /**
     * Clicks Not Your Tire? Make a Change link
     * @throws Exception Exception
     */
    public void clickNotYourTireLink() throws Exception {
        LOGGER.info("clickNotYourTireLink started");
        try {
            driver.waitForElementClickable(notYourTireLink);
            notYourTireLink.click();
        } catch (Exception e) {
            Assert.fail("FAIL: Clicking 'Not your Tire?' link FAILED with error: " + e);
        }
        LOGGER.info("clickNotYourTireLink completed");
    }
    
    /**
     * Verifies that selected page element is displayed on driving details page
     * 
     * @param ele       The element string value (text) 
     * @throws Exception Exception
     */
    public void verifyPageElementIsDisplayedOnDrivingDetails(String ele) {
        LOGGER.info("verifyPageElementIsDisplayedOnDrivingDetails started");
        WebElement webEle = null;
        if (ele.contains(TYPICAL)){
        	webEle = drivingPriorityTypical;
        }else if (ele.contains(PERFORMANCE)){
        	webEle = drivingPriorityPerformance;
        }else if (ele.contains(NOT_YOUR_TIRE)){
        	webEle = notYourTireLink;
        }else{
        	 LOGGER.info(ele + " element doesn't match with any rendered element on driving detail page");
        }
        try {
            driver.isElementDisplayed(webEle);
            LOGGER.info(ele + " element was displayed on driving details page");
        } catch (Exception e) {
            Assert.fail("FAIL: "+ ele + " element was not Displayed and FAILED with error: " + e);
        }

        LOGGER.info("verifyPageElementIsDisplayedOnDrivingDetails completed");
    }

    /**
     * Verifies the Tire Size Label Category
     *
     * @param expectedCategoryLabel The tire size category label to check
     */
    public void verifyTireSizeCategoryLabel(String expectedCategoryLabel) {
		LOGGER.info("verifyTireSizeCategoryLabel started");
		String actualCategoryLabelText = null;
		expectedCategoryLabel = expectedCategoryLabel.toLowerCase();
		if (expectedCategoryLabel.contains(OE)) {
			actualCategoryLabelText = tireSizeOE.getText().toLowerCase();
		} else if (expectedCategoryLabel.contains(OPTIONAL)) {
			actualCategoryLabelText = tireSizeOptional.getText().toLowerCase();
		}
		driver.waitForElementVisible(tireSize);
		Assert.assertTrue("FAIL: Expected tire size category label : \"" + expectedCategoryLabel + "\", Actual: \""
				+ actualCategoryLabelText + "\"", actualCategoryLabelText.contains(expectedCategoryLabel));
		LOGGER.info("Confirmed that the tire size category label value was \"" + expectedCategoryLabel + "\".");
		LOGGER.info("verifyTireSizeCategoryLabel completed");
	}
    
    /**
     * Update ZipCode
     *
     * @param   zipcodeValue      Zipcode
     */
    public void updateZipcode(String zipcodeValue) {
        LOGGER.info("updateZipcode started");
        driver.waitForElementVisible(zipcode);
        zipcode.clear();
        zipcode.sendKeys(zipcodeValue);
        driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
        zipcode.sendKeys(Keys.ENTER);
        driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
    	commonActions.waitForElementToContainText(zipcode, zipcodeValue, Constants.DEFAULT_SEC_WAIT);
        LOGGER.info("updateZipcode completed");
    }
    
    /**
     * Set/Update Miles Driven Per Year Value Using Slider
     *
     * @param   miles      Driven Miles
     */
    public void setMilesDrivenUsingSlider(String miles) {
        LOGGER.info("setMilesDrivenTo started");
        driver.waitForElementVisible(milesDrivenGauge);
		Actions slide = new Actions(webDriver);
		// Set slider position to extreme left to set the baseline position /
		// as point-of-reference
		Action action = (Action) slide.dragAndDropBy(milesDrivenGauge, ConstantsPdl.SLIDER_RANGE_EXTREME_LEFT, 0).build();
		action.perform();
		action = (Action) slide.dragAndDropBy(milesDrivenGauge, getMilesDrivenSliderRange(miles), 0).build();
		action.perform();
		driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
		LOGGER.info("setMilesDrivenTo completed");
    }
    
    
    /**
     * Set/Update Miles Driven Per Year Value 
     *
     * @param   miles      Driven Miles
     */
    public void setMilesDrivenPerYearTo(String miles) {
        LOGGER.info("setMilesDrivenTo started");
        miles = miles.toUpperCase().split("K")[0];
        driver.waitForElementVisible(milesDrivenGauge);   
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;        
        jse.executeScript("document.querySelector('.pdl-miles-driven__miles-number > span').setAttribute('value', '"+miles+"')");
        driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
        LOGGER.info("setMilesDrivenTo completed");
    }
    
    
    /**
     * Returns X coordinates for Miles Driven Slider based on miles passed
     *
     * @param milesDriven   Miles value 
     * @return int   Returns X coordinate of the slider to match the miles value passed in
     */
    public int getMilesDrivenSliderRange(String milesDriven) {
        int slider = 0;
        milesDriven = milesDriven.toUpperCase().split("K")[0];
        if (milesDriven.equalsIgnoreCase(miles5K)) {
        	slider = ConstantsPdl.SLIDER_RANGE_5K;
        } else if (milesDriven.equalsIgnoreCase(miles10K)) {
        	slider = ConstantsPdl.SLIDER_RANGE_10K;
        } else if (milesDriven.equalsIgnoreCase(miles15K)) {
        	slider = ConstantsPdl.SLIDER_RANGE_15K;
        } else if (milesDriven.equalsIgnoreCase(miles20K)) {
        	slider = ConstantsPdl.SLIDER_RANGE_20K;
        } else if (milesDriven.equalsIgnoreCase(miles30K)) {
        	slider = ConstantsPdl.SLIDER_RANGE_30K;
        }
		return slider; 
       }

    /**
     * Verifies the 'Unable to find tires that match..." error message appears
     */
    public void verifyTireSizeErrorMessage(String side) {
        LOGGER.info("verifyTireSizeErrorMessage started");
        driver.waitForElementVisible(zipcode);

        WebElement error = null;
        if (side.equalsIgnoreCase(ConstantsPdl.RIGHT)){
            error = rightColumn.findElement(CommonActions.errorMessageBy);
        } else if (side.equalsIgnoreCase(ConstantsPdl.LEFT)){
            error = leftColumn.findElement(CommonActions.errorMessageBy);
        }

        Assert.assertTrue("FAIL: Tire size error message was not visible on page.", error.isDisplayed());
        Assert.assertTrue("FAIL: Tire size error message did not contain " + NO_MATCH_ERROR + ".",
                error.getText().contains(NO_MATCH_ERROR));
        LOGGER.info("verifyTireSizeErrorMessage completed");
    }

}
