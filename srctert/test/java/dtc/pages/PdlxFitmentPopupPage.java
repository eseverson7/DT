package dtc.pages;

/**
 * Created by aarora on 01/15/18.
 */

import common.Config;
import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.util.List;
import java.util.logging.Logger;

public class PdlxFitmentPopupPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(PdlxFitmentPopupPage.class.getName());

    public PdlxFitmentPopupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static final String[] FITMENT_EVERYDAY_PRIORITY_ORDER = {
            "Life of Tire", "Stopping Distance", "Handling", "Comfort & Noise"};
    
    private static final String[] FITMENT_PERFORMANCE_PRIORITY_ORDER = {
            "Handling", "Stopping Distance", "Comfort & Noise", "Life of Tire"};

    private static final String[] EXP_FITMENT_OPTS = {"TIRES", "WHEELS", "SERVICE"};


    @FindBy(className = "fitment-box__close")
    public static WebElement closeButton;

    @FindBy(className = "fitment-vehicle-display__change")
    public static WebElement changeVehicle;
    
    @FindBy(className = "fitment-pdl-entry__oe-size")
    public static WebElement oeSizePdlFitment;
    
    @FindBy(className = "fitment-pdl-entry__title")
    public static WebElement pdlDrivingDetailsTitle;

    @FindBy(id = "primary-driving-location")
    public static WebElement pdlZipcode;
    
    @FindBy(id = "pdl-miles-driven")
    public static WebElement pdlInputMiles;
    
    @FindBy(className = "fitment-pdl-entry__submit")
    public static WebElement viewRecommendationTiresButton;
    
    
    private static final By closeButtonBy = By.className("fitment-box__close");
    
    public static final By drivingPriorityOptionsBy = By.className("fitment-pdl-entry__priority-option");
    
    public static final By drivingPriorityOptionsNameBy = By.className("fitment-pdl-entry__priority-option-name");
    
    public static final By drivingPriorityOptionsOrderBy = By.className("fitment-pdl-entry__priority-option-order");

    private static final int rightPositionXOffsetValue = 188;
    private static final int leftPositionXOffsetValue = -185;

    
    /**
     * Verifies the pdl fitment oe size label matches with provided value
     *
     * @param value Text value of the oe size fitment
     */
    public void assertSelectedPdlFitmentTireSizeValue(String value) {
        LOGGER.info("assertSelectedPdlFitmentTireSizeValue started");
        driver.waitForElementVisible(oeSizePdlFitment);
		Assert.assertTrue("FAIL: The actual fitment pdl oe size value: \"" + oeSizePdlFitment.getText()
						+ "\" did not match with expected: \"" + value + "\"!",
				oeSizePdlFitment.getText().contains(value));
		LOGGER.info("Confirmed that \"" + value + "\" was listed as oe size for selected pdl fitment.");
        LOGGER.info("assertSelectedPdlFitmentTireSizeValue completed");
    }

    /**
     * Verifies the pdl fitment popup title matches with provided value
     *
     * @param value Text value of the pdl fitment popup title
     */
    public void assertPdlFitmentPopupTitle(String value) {
        LOGGER.info("assertPdlFitmentPopupTitle started");
        driver.waitForElementVisible(pdlDrivingDetailsTitle);
		Assert.assertTrue("FAIL: The actual pdl fitment popup title: \"" + pdlDrivingDetailsTitle.getText()
						+ "\" did not match with expected: \"" + value + "\"!",
						pdlDrivingDetailsTitle.getText().contains(value));
		LOGGER.info("Confirmed that \"" + value + "\" was listed as oe size for selected pdl fitment.");
        LOGGER.info("assertPdlFitmentPopupTitle completed");
    }

    
    /**
     * Clicks, clear & sets provided text value to driving location zipcode
     *
     * @param value Text value of zipcode to set
     */
    public void setZipcode(String value) {
        LOGGER.info("setZipcode started");
        driver.waitForElementVisible(pdlZipcode);
        pdlZipcode.click();
        pdlZipcode.clear();
        pdlZipcode.sendKeys(value);
        LOGGER.info("setZipcode completed");
    }

    /**
     * Clicks & set miles to input miles driven per year
     *
     * @param value Text value of miles to set
     */
    public void setMiles(String value) {
        LOGGER.info("setMiles started");
        driver.waitForElementVisible(pdlInputMiles);
        pdlInputMiles.click();
        pdlInputMiles.clear();
        pdlInputMiles.sendKeys(value);
        LOGGER.info("setMiles completed");
    }
    
    /**
     * Verifies the zipcode value matches with provided value
     *
     * @param value Text value of the zipcode
     */
    public void assertZipCodeValue(String value) {
        LOGGER.info("assertZipCodeValue started");
        driver.waitForElementVisible(pdlZipcode);
		String zipcodeValue = pdlZipcode.getAttribute(Constants.VALUE);
        if (zipcodeValue.equalsIgnoreCase(null)) {
			Assert.fail("FAIL: The actual zipcode value was: \"" + null
					+ "\" did not match with expected: \"" + value + "\"!");
		} else {
			Assert.assertTrue("FAIL: The actual zipcode value: \"" + zipcodeValue
					+ "\" did not match with expected: \"" + value + "\"!", zipcodeValue.contains(value));
			LOGGER.info("Confirmed that \"" + value + "\" was listed as zipcode value.");
			LOGGER.info("assertZipCodeValue completed");
		}

    }
    
    /**
     * Verifies the miles driven per year value matches with provided value
     *
     * @param value Text value of the miles
     */
    public void assertMilesDrivenPerYearValue(String value) {
        LOGGER.info("assertMilesDrivenPerYearValue started");
        driver.waitForElementVisible(pdlInputMiles);
		String milesValue = pdlInputMiles.getAttribute(Constants.VALUE);
        if (milesValue.equalsIgnoreCase(null)) {
			Assert.fail("FAIL: The actual miles driven per year value was: \"" + null
					+ "\" did not match with expected: \"" + value + "\"!");
		} else {
			Assert.assertTrue("FAIL: The actual miles driven per year value: \"" + milesValue
					+ "\" did not match with expected: \"" + value + "\"!", milesValue.contains(value));
			LOGGER.info("Confirmed that \"" + value + "\" was listed as miles value.");
			LOGGER.info("assertMilesDrivenPerYearValue completed");
		}

    }
    
    /**
     * Finds an driving priority tab element on the PDL Fitment popup screen and selects it
     *
     * @param priority Driving Priority to be selected
     */
    public void selectDrivingPriority(String priority) {
        LOGGER.info("selectDrivingPriority started");

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isMobile())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        WebElement drivingPriorityTab = driver.getElementWithText(CommonActions.buttonBy, priority);
        try {
            driver.jsScrollToElement(drivingPriorityTab);
            drivingPriorityTab.click();
        } catch (NoSuchElementException e) {
            Assert.fail("FAIL: Driving Priority label \"" + priority + "\" NOT found. (Full Stack Trace: " + e.toString() + ")");
        } catch (WebDriverException we) {
            driver.waitForMilliseconds();
            drivingPriorityTab.click();
        }
        driver.waitForPageToLoad();

        LOGGER.info("selectDrivingPriority completed");
    }

    /**
     * Closes the Fitment popup
     **/
    public void closeFitmentPopUp() {
        LOGGER.info("closeFitmentPopUp started");
        driver.waitForElementVisible(closeButton);
        closeButton.click();
        driver.waitForElementNotVisible(closeButtonBy);
        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        LOGGER.info("closeFitmentPopUp completed");
    }


    
    /**
     * Clicks View Recommended Tires Button
     */
    public void selectViewRecommendedTiresButton() {
    	LOGGER.info("selectViewRecommendedTiresButton started");
        driver.waitForElementClickable(viewRecommendationTiresButton);
        viewRecommendationTiresButton.click();
        driver.waitForMilliseconds();
        LOGGER.info("selectViewRecommendedTiresButton completed");
    }
    
    /**
     * Drag & Drops the Driving Priority to specified position
     *
     * @param   optionName      Driving Priority option name
     * @param   order           Position where option will be set to
     */
	public void moveDrivingPriorityOptionTo(String optionName, String order) {
		LOGGER.info("moveDrivingPriorityOptionTo started");
		
		int xOffset = 0;
		int position = Integer.valueOf(order);
		driver.waitForElementVisible(drivingPriorityOptionsBy);
		Actions slide = new Actions(webDriver);
		List<WebElement> options = webDriver.findElements(drivingPriorityOptionsBy);
		for (WebElement option : options) {
			WebElement optionElement = option.findElement(drivingPriorityOptionsNameBy);
			WebElement currentOrder = option.findElement(drivingPriorityOptionsOrderBy);
			if (optionElement.getText().contains(optionName)) {
				driver.jsScrollToElement(optionElement);
				int elementCurrentPosition = Integer.valueOf(currentOrder.getText());
				if (elementCurrentPosition == position) {
					break;
				} else {
					if (elementCurrentPosition < position) {
						xOffset = rightPositionXOffsetValue;
					} else {
						xOffset = leftPositionXOffsetValue;
						position = 4 - position;
					}
					for (int i = 1; i <= position; i++) {
						try {
							driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
							Action action = (Action) slide.dragAndDropBy(option, xOffset, 0).build();
							action.perform();
						} catch (Exception e) {
							driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
							Action action = (Action) slide.dragAndDropBy(option, xOffset, 0).build();
							action.perform();
						}
					}
				}
				break;
			}
		}
		LOGGER.info("moveDrivingPriorityOptionTo completed");
	}

}