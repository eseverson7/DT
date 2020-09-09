package pdl.pages;

/**
 * Created by aarora on 05/24/17.
 */

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class TireComparisonPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(TireComparisonPage.class.getName());

    public TireComparisonPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "pdl-product-comparison__header-title")
    public static WebElement tireComparisonPageHeader;

	@FindBy(className = "pdl-product-comparison__header-back")
	public WebElement resultsLink;

	@FindBy(className = "pdl-product-comparison__stopping-distance")
	public static WebElement distanceToStopSection;

	@FindBy(className = "pdl-product-comparison__tire-life")
	public static WebElement tirelifeSection;

	@FindBy(className = "pdl-product-comparison__cost")
	public static WebElement costSection;

	@FindBy(className = "pdl-product-comparison__handling")
	public static WebElement rideSection;

	@FindBy(className = "pdl-product-comparison__header-back")
	public static WebElement resultsButton;

	private static final By wetClimateGraphBy = By.className("pdl-product-rating-stopping__line-measure");

	private static final By dryClimateGraphBy = By.className("pdl-product-rating-stopping__line-measure");

	private static final By winterRatingGraphBy = By.className("pdl-product-rating-winter__line-measure");

	private static final By milesGraphBy = By.className("pdl-product-rating-mileage__line");

	private static final By yearsMonthsGraphBy = By.className("pdl-product-rating-tire-life__line-measure");

	private static final By milesPerDollarGraphBy = By.className("pdl-product-rating-cost__line-measure");

	private static final By handlingGraphBy = By.className("pdl-product-rating-ride__line-measure");

	private static final By quietRideGraphBy = By.className("pdl-product-rating-ride__line-measure");

	private static final By comfortGraphBy = By.className("pdl-product-rating-ride__line-measure");

	private static final By productBy = By.className("pdl-product-comparison__info");

	private static final By productCompareImageBy = By.className("pdl-product-comparison__image");

	private static final By greenRibbonImage = By.className("pdl-product-comparison__ratings-best-image");

	private static final By comparisonRowBy = By.className("pdl-product-comparison__ratings-line");

	private static final By comparisonRowModelBy = By.className("pdl-product-name-model");

	public static final By customerStarsBy = By.className("pdl-product-summary__review-star");

	public static final By customerRecommendedBy = By.className("pdl-product-summary__review-recommended");

	private static final By comparedItemInfoBy = By.className("pdl-product-comparison__info");

	private static final By viewDetailsButtonBy = By.className("pdl-product-comparison__view-details");

	private static final String WET_CLIMATE = "Wet Climate";
	private static final String DRY_CLIMATE = "Dry Climate";
	private static final String WINTER_RATING = "Winter Rating";
	private static final String YEARS_MONTHS = "Years/Months";
	private static final String MILES_PER_DOLLAR = "Miles Per Dollar";
	private static final String HANDLING = "Handling";
	private static final String QUIET_RIDE = "Quiet Ride";
	private static final String COMFORT = "Comfort";


	/**
	 * Return graph element for specified graph type
	 *
	 * @param comparisonRow The row to graph the graph element from
	 * @return By	The By element of the graph type
	 */
	private By getGraphBy(WebElement comparisonRow) {
		LOGGER.info("getGraphBy started");

		String comparisonRowText = comparisonRow.getText();
		By graphElement = null;

		if (comparisonRowText.contains(WET_CLIMATE)) {
			graphElement = wetClimateGraphBy;
		} else if (comparisonRowText.contains(DRY_CLIMATE)) {
			graphElement = dryClimateGraphBy;
		} else if (comparisonRowText.contains(WINTER_RATING)) {
			graphElement = winterRatingGraphBy;
		} else if (comparisonRowText.contains(Constants.MILES) &&
				!comparisonRowText.contains(MILES_PER_DOLLAR)) {
			graphElement =  milesGraphBy;
		} else if (comparisonRowText.contains(YEARS_MONTHS)) {
			graphElement =  yearsMonthsGraphBy;
		} else if (comparisonRowText.contains(MILES_PER_DOLLAR)) {
			graphElement =  milesPerDollarGraphBy;
		} else if (comparisonRowText.contains(HANDLING)) {
			graphElement =  handlingGraphBy;
		} else if (comparisonRowText.contains(QUIET_RIDE)) {
			graphElement =  quietRideGraphBy;
		} else if (comparisonRowText.contains(COMFORT)) {
			graphElement =  comfortGraphBy;
		}

		LOGGER.info("getGraphBy completed");
		return graphElement;
	}

	/**
	 * Verify the Tire Comparison Header Title
	 *
	 * @param expectedHeader	The expected header to match
	 * @throws Exception		Exception
	 */
	public void verifyTireComparisonPageHeader(String expectedHeader) throws Exception {
		LOGGER.info("verifyTireComparisonPageHeader started");
		try {
			driver.waitForElementVisible(tireComparisonPageHeader);
			String actualPageHeader = tireComparisonPageHeader.getText();
			Assert.assertTrue(
					"FAIL: Tire Comparison page header didn't match, Actual: " + actualPageHeader
							+ " with Expected: " + expectedHeader,
							actualPageHeader.contains(expectedHeader));
			LOGGER.info("Tire Comparison header " + actualPageHeader + "matched with expected header "
					+ expectedHeader);
		} catch (Exception e) {
			Assert.fail("FAIL: Tire Comparison verify header FAILED with error: " + e);
		}
		LOGGER.info("verifyTireComparisonPageHeader completed");
	}


	/**
	 * Verifies all product images are displayed on comparison page
	 *
	 */
	public void verifyProductImagesPresentForAllTires(){
		LOGGER.info("verifyProductImagesPresentForAllTires started");
		int index = 1;
		driver.waitForElementVisible(productCompareImageBy);
		List<WebElement> items = webDriver.findElements(productCompareImageBy);

		for (WebElement item : items) {
			if (!item.isDisplayed()){
				Assert.fail("FAIL: \" Item \" product image # " + index
						+ " doesn't exist : " );
			}
			index++;
		}
		LOGGER.info("Confirmed: All " + items.size() + " product images are present. ");
		LOGGER.info("verifyProductImagesPresentForAllTires completed");
	}


	/**
	 * Verifies that the specified tire contains the text/detail passed in
	 *
	 * @param text The text to verify
	 */
	public void verifyTireDetail(String itemCode, String text){
		LOGGER.info("verifyTireDetail started");
		driver.waitForElementVisible(productBy);
		List<WebElement> products = webDriver.findElements(productBy);

		for (WebElement product : products) {
			String productText = product.getText().replaceAll("\\s","");
			if (productText.contains(itemCode)) {
				Assert.assertTrue("FAIL: Tire with item code \"" + itemCode +
								"\" did not contain expected text \"" + text + "\".",
						productText.contains(text.replaceAll("\\s","")));
			}
		}
		LOGGER.info("Confirmed tire with item code " + itemCode + "\" contained text \"" + text + "\".");
		LOGGER.info("verifyTireDetail completed");
	}

	/**
	 * Verifies that the Tire Comparison sections (Distance to Stop, Tire Life, Cost, and Ride) each contain at least one
	 * green / best ribbon
	 */
	public void verifyTireComparisonSectionsContainGreenRibbon() {
		LOGGER.info("verifyTireComparisonSectionsContainGreenRibbon started");

		driver.waitForElementVisible(tireComparisonPageHeader);

		List<WebElement> comparisonSections = new ArrayList<WebElement>(Arrays.asList(distanceToStopSection,
				tirelifeSection, costSection, rideSection));

		for (WebElement comparisonSection : comparisonSections){
			List<WebElement> ribbonsFoundList = comparisonSection.findElements(greenRibbonImage);
			Assert.assertTrue("FAIL: One of the tire comparison sections did NOT contain at least one " +
					"green/best ribbon!", ribbonsFoundList.size() >= 1);
		}
		
		LOGGER.info("verifyTireComparisonSectionsContainGreenRibbon completed");
	}

	/**
	 * Verifies that every Tire in each Tire Comparison section contains a graph
	 */
	public void verifyTiresInComparisonSectionsContainGraphs() {
		LOGGER.info("verifyTiresInComparisonSectionsContainGraphs started");

		driver.waitForElementVisible(comparisonRowBy);
		List<WebElement> comparisonRows = webDriver.findElements(comparisonRowBy);

		for (WebElement comparisonRow : comparisonRows){
			String tire = comparisonRow.findElement(comparisonRowModelBy).getText();

			try {
				comparisonRow.findElement(getGraphBy(comparisonRow));
			} catch (Exception e) {
				Assert.fail("FAIL: A graph was not displayed for tire \"" + tire + "\"! (Exception: " + e);
			}

			LOGGER.info("A graph was displayed for tire \"" + tire + "\".");
		}

		LOGGER.info("verifyTiresInComparisonSectionsContainGraphs completed");
	}

	/**
	 * Clicks Results button
	 */
	public void clickResultsButton() {
		LOGGER.info("clickResultsButton started");
		driver.waitForElementVisible(resultsButton);
		resultsButton.click();
		LOGGER.info("clickResultsButton completed");
	}

	/**
	 * Verifies that the specified tire contains the By element passed in
	 *
	 * @param elementBy The By element to find within each comparison section
	 */
	public void verifyElementPresentInEachTireComparisonInfoSection(By elementBy){
		LOGGER.info("verifyElementPresentInEachTireComparisonInfoSection started");
		driver.waitForElementVisible(productBy);

		String byValue = driver.getByValue(elementBy);
		List<WebElement> products = webDriver.findElements(productBy);

		for (WebElement product : products) {
			try {
				product.findElement(elementBy);
			} catch (Exception e) {
				Assert.fail("FAIL: Tire did not contain element \"" + byValue + "\".!");
			}
		}
		LOGGER.info("Confirmed each tire contained by element \"" + byValue  + "\".");
		LOGGER.info("verifyElementPresentInEachTireComparisonInfoSection completed");
	}

	/**
	 * Clicks the "View Details" button for an item that contains a matching itemCode on the Tire Comparison page
	 * @param itemCode Code / number of compared item containing the "View Details" button to click
	 */
	public void selectViewDetailsByItemCode(String itemCode) {
		LOGGER.info("selectViewDetailsByItemCode started");

		driver.waitForElementVisible(comparisonRowBy);
		List<WebElement> comparedItemsList = webDriver.findElements(comparedItemInfoBy);

		for (WebElement item : comparedItemsList){
			if (item.getText().contains(itemCode)) {
				item.findElement(viewDetailsButtonBy).click();
				break;
			}
		}
		LOGGER.info("selectViewDetailsByItemCode completed");
	}
	
	/**
	 * Counts the number of Checkboxes checked on the page and if more than two selected will fail the test
	 * 
	 */
    public void productCheckboxSelected(int number) throws Exception {
		LOGGER.info("productCheckboxSelected started");
		
		List<WebElement> checkboxes;

		driver.waitForElementVisible(RecommendationsPage.recommendationResultBy);
		checkboxes = webDriver.findElements(RecommendationsPage.compareCheckboxBy);
		int checkedboxCnt = 0;
			
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				checkedboxCnt++;			
			} else {
				LOGGER.info("There are no checkboxes selected on the page.");
			}
		}
			
		if(checkedboxCnt != number){
		Assert.fail("FAIL: should not be able to select more than two checkboxes. Current number of boxes checked are "
					+ checkedboxCnt);
		}else{
			LOGGER.info("Current number of boxes checked are " + checkedboxCnt);
		}
			LOGGER.info("productCheckboxSelected completed");
	}
}