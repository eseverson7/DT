package pdl.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pdl.data.ConstantsPdl;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 4/24/17.
 */
public class CommonActions {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(pdl.pages.CommonActions.class.getName());

    public CommonActions(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "pdl-error-line")
    public static WebElement errorMessage;
    
    public static By errorMessageBy = By.className("pdl-error-line");

    private static By headerCn = By.tagName("h1");

	public static By label = By.tagName("label");

    /**
     * Asserts that the expected header is displayed on the page
     *
     * @param header The header (h1 element text) of the page
     */

    public void assertPageHeaderPdl(String header) {
        LOGGER.info("assertPageHeaderPdl started");
        driver.waitForElementVisible(headerCn);
        WebElement foundHeader = webDriver.findElement(headerCn);
        Assert.assertTrue("ERROR: assertPageHeaderPd1 page header was not " + header + ". But instead was " + foundHeader.getText(),
                foundHeader.getText().contains(header));
//        int time = Constants.DEFAULT_SEC_WAIT;
//        boolean foundHeader = driver.waitForTextPresent(headerCn, header, time);
//        Assert.assertTrue("FAIL: Header \"" + header + "\" was NOT displayed in \"" + time + "\" seconds!",
//                foundHeader);
//        LOGGER.info("Confirmed that the page header is \"" + header + "\".");
        LOGGER.info("assertPageHeaderPdl completed");
    }


    /**
     * Verifies the text of the element
     *
     * @param element       The WebElement to check
     * @param expectedText  The text of the element to verify
     */
    public void verifyElementText(WebElement element, String expectedText) {
        LOGGER.info("verifyElementText started");
        driver.waitForMilliseconds();
        driver.waitForElementVisible(element);
        String actualText = element.getText();
        Assert.assertTrue("FAIL: Text expected: \"" + expectedText + "\", actual text: \"" + actualText + "\".",
                actualText.contains(expectedText));
        LOGGER.info("Confirmed that \"" + expectedText + "\" was displayed .");
        LOGGER.info("verifyElementText completed");
    }
    
    /**
     * Verifies the value of the element
     *
     * @param element       The WebElement to check
     * @param expectedValue  The Value of the element to verify
     */
    public void verifyElementValue(WebElement element, String expectedValue) {
        LOGGER.info("verifyElementValue started");
        driver.waitForElementAttribute(element, Constants.VALUE, expectedValue);
        LOGGER.info("verifyElementValue completed");
    }
    
    
    /**
     * Verifies the text of the element from the list of elements
     *
     * @param element       The WebElement to check
     * @param expectedText  The text of the element to verify
     */
    public void verifyElementTextInTheList(By element, String expectedText) {
        LOGGER.info("verifyElementTextInTheList started");
        int index = 1;
        boolean isFound = false;
        driver.waitForElementVisible(element);
		List<WebElement> resultElements = webDriver.findElements(element);
		for (WebElement resultEle : resultElements) {
			if (resultEle.getText().trim().contains(expectedText)) {
				LOGGER.info("Confirmed: \"" + expectedText + "\" text match found and was at row# ("
						+ index +") out of "+ resultElements.size() +" matches found.");
				isFound = true;
				break;
			}
			index++;
		}
        if(!isFound){
        	Assert.fail("FAIL: No match found for Expected text \"" + expectedText + "\" on the page");
        }
        LOGGER.info("verifyElementTextInTheList completed");
    }

    /**
     * Verifies the number of car dropdowns
     * @param dropDown          Dropdown element to create list from
     * @param expectedNumber    Number of dropdowns
     */
    public void verifyNumberOfDropdowns(By dropDown, String expectedNumber) {
        LOGGER.info("verifyNumberOfDropdowns started");
        driver.waitForElementVisible(dropDown);
        List<WebElement> dropDowns = webDriver.findElements(dropDown);
        String actualNumber = Integer.toString(dropDowns.size());
        Assert.assertEquals("FAIL: Expected number of dropdowns: \"" + expectedNumber + "\", actual number: \"" +
                actualNumber + "\".", expectedNumber, actualNumber);
        LOGGER.info("verifyNumberOfDropdowns completed");
    }


    /**
     * Verifies that the button passed in is disabled
     *
     * @param button Button to check
     */
    public void verifyButtonDisabled(WebElement button) {
        LOGGER.info("verifyButtonDisabled started");
        Assert.assertTrue("FAIL: Button should have been disabled but it was not.",
                driver.isAttributePresent(button, Constants.DISABLED));
        LOGGER.info("Verified button was disabled.");
        LOGGER.info("verifyButtonDisabled completed");
    }


    /**
     * Verifies that the button passed in is enabled
     *
     * @param button Button to check
     */
    public void verifyButtonEnabled(WebElement button) {
        LOGGER.info("verifyButtonEnabled started");
        driver.waitForElementVisible(button);
        Assert.assertTrue("FAIL: Button should have been enabled but it was not.",
                !driver.isAttributePresent(button, Constants.DISABLED));
        LOGGER.info("Verified button was enabled.");
        LOGGER.info("verifyButtonEnabled completed");
    }

    /**
     * Verifies the typical or performance Driving Priorities
     *
     * @param drivingPriorityType   The type of priority to verify options for
     * @param summary               Whether it's the summary page or not
     */
    public void verifyDrivingPriorities(String drivingPriorityType, boolean summary) {
        LOGGER.info("verifyDrivingPriorities started");
        int index = 0;
        String[] drivingPriorityArray;
        By drivingPriorityBy;

        if (summary) {
            drivingPriorityBy = RecommendationsPage.summaryDrivingPriorityBy;
        } else {
            drivingPriorityBy = DrivingDetailsPage.drivingPriorityBy;
        }

        if (drivingPriorityType.equalsIgnoreCase(ConstantsPdl.TYPICAL)) {
            drivingPriorityArray = ConstantsPdl.DRIVING_PRIORITIES_TYPICAL;
        } else {
            drivingPriorityArray = ConstantsPdl.DRIVING_PRIORITIES_PERFORMANCE;
        }

        driver.waitForElementVisible(drivingPriorityBy);
        List<WebElement> drivingPriorities = webDriver.findElements(drivingPriorityBy);

        for (WebElement drivingPriority: drivingPriorities) {
            if (drivingPriorityArray[index].equalsIgnoreCase(drivingPriority.getText())) {
                LOGGER.info("Confirmed \"" + drivingPriorityArray[index] + "\" order.");
            } else {
                Assert.fail("FAIL: Expected Driving Priority: \"" + drivingPriorityArray[index] +
                        "\", Actual Driving Priority: \"" + drivingPriority.getText() + "\".");
            }
            index++;
        }
        LOGGER.info("verifyDrivingPriorities completed");
    }

    
    /**
     * Verifying any error message for PDL
     * @param text   actual error message string
     */
    public void assertPdlErrorMessage(String text) {
        LOGGER.info("assertPdlErrorMessage started");
		if (!errorMessage.getText().contains(text))
			Assert.fail("FAIL: pdl error message didn't match!,Expected Message : " + text + " Actual : \""
					+ errorMessage.getText() + "\".");
		LOGGER.info("assertPdlErrorMessage completed");
    }

    /**
     * This method will wait for max 30 secs for element value change To 
     * @param element       The WebElement to check
     * @param value         The value of the element to waitFor
     * @param time          Max time (in seconds) to wait for value to appear 
     */
    public void waitForElementToContainText(WebElement element, String value, int time) {
		LOGGER.info("waitForElementToContainText started");
		int counter = 0;
		driver.waitForMilliseconds();
		driver.waitForElementVisible(element);
		while (!element.getText().contains(value)&& counter <= time) {
			driver.waitForMilliseconds(Constants.DEFAULT_SEC_WAIT);
			LOGGER.info("Waiting for element value change to : " + value);
			counter++;
		}
		if (counter == time) {
			LOGGER.info("Element value : " + value + " didnt appear");
		}
		LOGGER.info("waitForElementToContainText completed");
	}
}