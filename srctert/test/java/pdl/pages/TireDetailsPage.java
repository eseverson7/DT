package pdl.pages;

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
 * Created by aarora on 6/2/17.
 */
public class TireDetailsPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(TireDetailsPage.class.getName());

    public TireDetailsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "pdl-product-details__header-title")
    public static WebElement tireDetailsHeader;

    @FindBy(className = "pdl-product-details__info-left-container__image")
    public static WebElement tireImageContainer;

    @FindBy(className = "pdl-product-summary__overview-brand")
    public static WebElement productBrand;

    @FindBy(className = "pdl-product-summary__overview-name")
    public static WebElement productName;

    @FindBy(className = "pdl-product-rating-stopping__rating")
    public static WebElement ratingLabel;

    @FindBy(id = "auto-product-rating-stopping-name-wet-climate")
    public static WebElement wetClimateLabel;

    @FindBy(id = "auto-product-rating-stopping-name-dry-climate")
    public static WebElement dryClimateLabel;

    @FindBy(id = "auto-product-rating-winter-handling")
    public static WebElement winterRatingLabel;

    @FindBy(className = "pdl-product-rating-mileage__name")
    public static WebElement milesLabel;

    @FindBy(className = "pdl-product-rating-tire-life__name")
    public static WebElement yearsMonthsLabel;

    @FindBy(id = "auto-product-rating-mileage-number")
    public static WebElement milesMedianRange;

    @FindBy(id = "auto-rating-tire-life-number")
    public static WebElement tireLifeNumber;

    @FindBy(id = "auto-product-rating-cost-name")
    public static WebElement milesPerDollarLabel;

    @FindBy(id = "auto-product-rating-ride-handling")
    public static WebElement rideHandlingLabel;

    @FindBy(id = "auto-product-rating-ride-quiet")
    public static WebElement quietRideLabel;

    @FindBy(id = "auto-product-rating-ride-comfort")
    public static WebElement comfortRideLabel;

    @FindBy(id = "auto-product-rating-winter-rating")
    public static WebElement winterRatingNumber;

    @FindBy(id = "auto-product-rating-cost-rating-number")
    public static WebElement costRatingNumber;

    @FindBy(css = "div.pdl-product-details__stopping-distance > h2")
    public static WebElement distanceToStopHeader;

    @FindBy(css = "div.pdl-product-details__tire-life > h2")
    public static WebElement expectedTireLifeRangeHeader;

    @FindBy(css = "div.pdl-product-details__cost > h2")
    public static WebElement costHeader;

    @FindBy(css = "div.pdl-product-details__handling > h2")
    public static WebElement rideHeader;

    public static final By stoppingRatingNumberBy = By.id("auto-product-stopping-rating");

    public static final By rideRatingNumberBy = By.id("auto-product-rating-ride-rating");

    public static final By tireImageContainerBy = By.className("pdl-product-details__info-left-container__image");

    public static final By productTireInfoBy = By.className("pdl-product-details__info-cart");

    public static final By tireLabelBy = By.className("pdl-product-details__info-cart-label");

    public static final By tireSizeBy = By.className("pdl-product-details__info-cart-tire-size");

    public static final By tirePriceBy = By.id("auto-product-price-dollars-amount");

    public static final By tireItemIdBy = By.className("pdl-product-details__info-cart-item-id");

    public static final By tireQtyBy = By.className("pdl-product-details__info-cart-qty");

    public static final By tireAddToCartButtonBy = By.className("pdl-product-details__info-cart-button");

    public static final By nonStagTireLabelBy = By.className("pdl-product-details__info-cart-details");

    public static final By nonStagTireSizeBy = By.className("pdl-product-summary__overview-size");

    public static final By nonStagTireQtyBy = By.cssSelector(".pdl-product-details__info-cart-qty");

    public static final By nonStagProductTireInfoBy = By.className("pdl-product-details__info-tire-details");

    public static final String ALT_TIRE_IMAGE = "Tire Image";
    public static final String TIRE_SIZE = "Tire Size";
    public static final String TIRE_ITEM_ID = "Tire Item Id";
    public static final String TIRE_PRICE = "Tire Prize";
    public static final String TIRE_QUANTITY = "Tire Quantity";
    public static final String ADD_TO_CART = "ADD TO CART";
    public static final String WET_CLIMATE = "Wet Climate";
    public static final String DRY_CLIMATE = "Dry Climate";
    public static final String WINTER_RATING = "Winter Rating";
    public static final String YEARS_MONTHS = "Years/Months";
    public static final String MILES_PER_DOLLAR = "Miles Per Dollar";
    public static final String HANDLING = "Handling";
    public static final String QUIET_RIDE = "Quiet Ride";
    public static final String COMFORT = "Comfort";
    public static final String HEADER_DISTANCE_TO_STOP = "DISTANCE TO STOP";
    public static final String HEADER_EXPECTED_TIRE_LIFE_RANGE = "EXPECTED TIRE LIFE RANGE";
    public static final String HEADER_COST = "COST";
    public static final String HEADER_RIDE = "RIDE";
    public static final String RATING_LABEL = "RATING";

	/**
	 * Verify the Tire Details Header Title
	 *
	 * @param expectedHeader The expected header to match
	 */
	public void verifyTireDetailsPageHeader(String expectedHeader) throws Exception {
		LOGGER.info("verifyTireDetailsPageHeader started");

			driver.waitForElementVisible(tireDetailsHeader);
			String actualPageHeader = tireDetailsHeader.getText();
			Assert.assertTrue(
					"FAIL: Tire Details page header didn't match, Actual: " + actualPageHeader
							+ " with Expected: " + expectedHeader,
							actualPageHeader.contains(expectedHeader));
			LOGGER.info("Tire Details header " + actualPageHeader + "matched with expected header "
					+ expectedHeader);
		LOGGER.info("verifyTireDetailsPageHeader completed");
	}

	/**
	 * Verify the Tire Image Src Link and Alt Image Text Exist on Tire Details Page
	 */
	public void verifyTireImageSrcLinkAndAltImageTextExist() throws Exception {
		LOGGER.info("verifyTireImageSrcLinkAndAltImageTextExist started");
		driver.waitForElementVisible(tireImageContainer);

		String imageLink = tireImageContainer.getAttribute(Constants.ATTRIBUTE_SRC);
		String alt_image_text = tireImageContainer.getAttribute(Constants.ATTRIBUTE_ALT);

		if (!(imageLink.contains(Constants.IMG_EXT_JPG) || imageLink.contains(Constants.NO_IMG_EXT_PNG))) {
			Assert.fail("FAIL: No image link was present");
		}
		if (!alt_image_text.equalsIgnoreCase(ALT_TIRE_IMAGE)) {
			Assert.fail("FAIL: Expected Alt text :  \"" + ALT_TIRE_IMAGE
					+ "\" was either empty or didn't match, Actual: \"" + alt_image_text + "\"");
		}
		LOGGER.info("Confirmed: Tire image link was present with src value: " + imageLink);
		LOGGER.info("Confirmed: Expected Alt text : \"" + ALT_TIRE_IMAGE + "\" did match with actual: \""
				+ alt_image_text + "\"");
		LOGGER.info("verifyTireImageSrcLinkAndAltImageTextExist completed");

	}

	/**
	 * Verifies the tire size by tire type (front or rear)
	 *
	 * @param tireType   The type of tire to reference by (front or rear)
	 * @param element    The WebElement to check
	 * @param tireInfo   The tire info to verify
	 * @param loggerInfo The user defined / custom text for reporting purposes at logger level
	 */
	public void verifyTireDisplayedInfo(String tireType, By element, String tireInfo, String loggerInfo)
			throws Exception {
		LOGGER.info("verifyTireDisplayedInfo started");
		driver.waitForElementVisible(tireLabelBy);
		List<WebElement> rows = webDriver.findElements(productTireInfoBy);
		for (WebElement row : rows) {
			if (row.findElement(tireLabelBy).getText().contains(tireType.toUpperCase())) {
				if (loggerInfo.equalsIgnoreCase(TIRE_QUANTITY)) {
					if (!row.findElement(element).getAttribute(Constants.VALUE).toString().contains(tireInfo)) {
						Assert.fail("FAIL: Expected \"" + tireType + " " + loggerInfo + " \": " + tireInfo
								+ " didn't match with, Actual : "
								+ row.findElement(element).getAttribute(Constants.VALUE));
					}
				} else if (loggerInfo.equalsIgnoreCase(ADD_TO_CART)) {
                    if (!row.findElement(element).isEnabled()) {
                        Assert.fail("FAIL: Expected \"" + tireType + " " + loggerInfo + " \": was not enabled");
                    }
				} else {
                    if (!row.findElement(element).getText().contains(tireInfo)) {
                        Assert.fail("FAIL: Expected \"" + tireType + " " + loggerInfo + " \": " + tireInfo
                                + " didn't match with, Actual : " + row.findElement(element).getText());
                    }
				}
			}
		}
		LOGGER.info("Confirmed: \"" + tireType + " " + loggerInfo + " \": " + tireInfo + " matched on Tire Details Page");
		LOGGER.info("verifyTireDisplayedInfo completed");
	}

	/**
	 * Verifies the Product Ratings falls inside the provided range
	 * 
	 * @param element  The WebElement to check
	 * @param maxRange The max range as ceiling to verify
	 */
	public void verifyProductRatingsRange(By element, String maxRange) throws Exception {
		LOGGER.info("verifyProductRatingsRange started");
		float maxRatingRange = Float.valueOf(maxRange);
		float actualRatingValue;

		driver.waitForElementVisible(element);
		List<WebElement> ratings = webDriver.findElements(element);
		for (WebElement rating : ratings) {
			actualRatingValue = Float.valueOf(rating.getText().trim());
			if (!(actualRatingValue>=0 && actualRatingValue <= maxRatingRange)) {
				Assert.fail("FAIL: \"Actual Ratings\" " + actualRatingValue + " was not inside the range of 0 to "
						+ maxRange);
			}
		}
		LOGGER.info("verifyProductRatingsRange completed");
	}
	
	/**
	 * Verifies the Product Ratings falls inside the provided range
	 * 
	 * @param element  The WebElement to check
	 * @param maxRange The max range as ceiling to verify
	 */
	public void verifyProductRatingsRange(WebElement element, String maxRange) throws Exception {
		LOGGER.info("verifyProductRatingsRange started");
		float maxRatingRange = Float.valueOf(maxRange);
		float actualRatingValue;
		driver.waitForElementVisible(element);
        actualRatingValue = Float.valueOf(element.getText().trim());
        if (!(actualRatingValue>=0 && actualRatingValue <= maxRatingRange)) {
            Assert.fail("FAIL: \"Actual Ratings\" " + actualRatingValue + " was not inside the range of 0 to "
                    + maxRange);
		}
		LOGGER.info("verifyProductRatingsRange completed");
	}

	/**
	 * Verifies the Product Distance To Stop Ratings falls inside the provided range
	 * 
	 * @param maxRange The max range as ceiling to verify
	 */
	public void verifyProductDistanceToStopRatingsRange(String maxRange) throws Exception {
		LOGGER.info("verifyProductDistanceToStopRatingsRange started");
		verifyProductRatingsRange(stoppingRatingNumberBy, maxRange);
		verifyProductRatingsRange(winterRatingNumber, maxRange);
		LOGGER.info("verifyProductDistanceToStopRatingsRange completed");
	}
	
	/**
	 * Verifies the Product Ride Ratings falls inside the provided range
	 * 
	 * @param maxRange The max range as ceiling to verify
	 */
	public void verifyProductRideRatingsRange(String maxRange) throws Exception {
		LOGGER.info("verifyProductRideRatingsRange started");
		verifyProductRatingsRange(rideRatingNumberBy, maxRange);
		LOGGER.info("verifyProductRideRatingsRange completed");
	}

    /**
     * Verifies tire info for non-staggered Tire Details page
     * @param element The WebElement to check
     * @param tireInfo The tire info to verify
     * @param loggerInfo The user defined / custom text for reporting purposes at logger level
     */
    public void verifyNonStaggeredTireDisplayInfo(By element, String tireInfo, String loggerInfo) {
        LOGGER.info("verifyNonStaggeredTireDisplayInfo started");
        driver.waitForElementVisible(nonStagTireLabelBy);
        List<WebElement> tireInfoSectionList = webDriver.findElements(nonStagProductTireInfoBy);
        for (WebElement section : tireInfoSectionList) {
            if (loggerInfo.equalsIgnoreCase(TIRE_QUANTITY)) {
                if (!section.findElement(element).getAttribute(Constants.VALUE).contains(tireInfo)) {
                    Assert.fail("FAIL: Expected \"" + loggerInfo + " \": " + tireInfo
                            + " didn't match with, Actual : "
                            + section.findElement(element).getAttribute(Constants.VALUE));
                }
            } else if (loggerInfo.equalsIgnoreCase(ADD_TO_CART)) {
                if (!section.findElement(element).isEnabled()) {
                    Assert.fail("FAIL: Expected \"" + loggerInfo + " \": was not enabled");
                }
            } else {
                if (!section.findElement(element).getText().contains(tireInfo)) {
                    Assert.fail("FAIL: Expected \"" + loggerInfo + " \": " + tireInfo
                            + " didn't match with, Actual : " + section.findElement(element).getText());
                }
            }
        }
        LOGGER.info(
                "Confirmed: \"" + loggerInfo + " \": " + tireInfo + " matched on Tire Details Page");
        LOGGER.info("verifyNonStaggeredTireDisplayInfo completed");
    }
}
