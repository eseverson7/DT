package dtc.pages;

import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import common.Constants;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by collinreed on 7/14/17.
 */
public class CompareTireReviewsPage {

    private final Driver driver;
    private final WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CompareTireReviewsPage.class.getName());
    private final CommonActions commonActions;

    public CompareTireReviewsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String SELECTED_TIRE = "SELECTED TIRE";

    private static final By tireBrand = By.className("tire__brand");

    private static final By tireName = By.className("tire__name");

    private static final By tirePrice = By.className("price-block__amount");

    private static final By compareTireReviewsHeader = By.className("review-comparison__title");

    private static final By resultsRow = By.className("comparison-row");

    private static final By addToCartBy = By.className("price-block__add-to-cart");

    private static final String sortOptionXpath = "//span[text()='%s']";

    private static final By comparisonRowSelectedBy = By.cssSelector(".comparison-row--selected td");

    @FindBy(className = "sorted-desc")
    public static WebElement sortedDesc;

    /**
     * Verify current page is Compare Tires page
     */
    public void assertCompareTiresPage() {
        LOGGER.info("assertCompareTiresPage started");
        String header = "Compare tire reviews";
        driver.waitForPageToLoad();

        Assert.assertTrue("FAIL: Page header " + header + "\"!", webDriver.findElement(compareTireReviewsHeader).getText().equals(header));

        LOGGER.info("assertCompareTiresPage completed");
    }

    /**
     * Click Add to Cart button for specified product
     *
     * @param map containing brand, product, and price values for the product for which Add to Cart will be clicked
     */
    public void clickAddToCart(HashMap map) {
        LOGGER.info("clickAddToCart started");

        List<WebElement> rows = webDriver.findElements(resultsRow);
        HashMap<String, ArrayList<String>> values = map;
        String expectedBrand = values.get(ConstantsDtc.BRAND).get(0);
        String expectedProduct = values.get(ConstantsDtc.PRODUCT).get(0);
        String expectedPrice = values.get(ConstantsDtc.PRICE).get(0).replace("ea.", "").trim();
        String displayBrand = "";
        String displayProduct = "";
        String displayPrice = "";

        for (WebElement row : rows) {
            if (row.getText().contains(SELECTED_TIRE)) {
                displayBrand = row.findElement(tireBrand).getText();
                displayProduct = row.findElement(tireName).getText();
                displayPrice = row.findElement(tirePrice).getText().replace("ea.", "").trim();

                Assert.assertTrue("FAIL: Selected Brand incorrect.  Expected:  " + expectedBrand +
                        ".  Actual:  " + displayBrand, displayBrand.equals(expectedBrand));

                Assert.assertTrue("FAIL: Selected Product incorrect.  Expected:  " + expectedProduct +
                        ".  Actual:  " + displayProduct, displayProduct.equals(expectedProduct));

                Assert.assertTrue("FAIL: Selected Price incorrect.  Expected:  " + expectedPrice +
                        ".  Actual:  " + displayPrice, displayPrice.equals(expectedPrice));

                row.findElement(addToCartBy).click();
                break;
            }
        }


        LOGGER.info("clicked on Add To Cart button for " + displayBrand + "|" + displayProduct);
        LOGGER.info("clickAddToCart completed");
    }

    /**
     * Clicks Add To Cart for first item listed
     */
    public void clickAddToCartForFirstItem() {
        LOGGER.info("clickAddToCartForFirstItem started");
        driver.waitForElementClickable(CommonActions.addToCart);
        driver.jsScrollToElement(CommonActions.addToCart);
        CommonActions.addToCart.click();
        LOGGER.info("clickAddToCartForFirstItem completed");
    }


    /**
     * Extracts Overall Rating
     *
     * @return double The rating of the Selected Tire
     */
    public double extractOverallRatingForSelectedTire() {
        LOGGER.info("extractOverallRatingForSelectedTire started");
        WebElement row = webDriver.findElement(comparisonRowSelectedBy);
        driver.waitForElementVisible(row.findElement(CommonActions.overallRatingBy));
        double itemOverallRating = Double.parseDouble(row.findElement(CommonActions.overallRatingBy).getText());
        LOGGER.info("extractOverallRatingForSelectedTire completed");
        return itemOverallRating;
    }

    /**
     * Verify overall rating is average of Ride Comfort, Cornering / Steering, Ride Noise, Tread life
     * Dry Traction, Wet Traction
     * Winter Traction and Buy Tire Again is not part of the calculation
     */
    public void assertOverallRating() {
        LOGGER.info("assertOverallRating started");
        double reviewListTotalValue = 0.0;
        int reviewCount = 0;

        List<WebElement> elements = webDriver.findElements(comparisonRowSelectedBy);
        for (int i = 3; i < elements.size() - 2; i++) {
            if (elements.get(i).getText() != ConstantsDtc.NOT_APPLICABLE) {
                reviewListTotalValue = reviewListTotalValue + Double.valueOf(elements.get(i).getText());
                reviewCount++;
            }
        }
        Assert.assertTrue("FAIL: Overall Rating " + extractOverallRatingForSelectedTire() + " did NOT match " +
                        commonActions.twoDForm((reviewListTotalValue / reviewCount), 1),
                extractOverallRatingForSelectedTire() == commonActions.twoDForm((reviewListTotalValue / reviewCount), 1));

        LOGGER.info("assertOverallRating completed");
    }

    /**
     * Verifies the sort option displays on the page
     *
     * @param sortOption sort option on the page
     */
    public void assertSortOptionIsDisplayed(String sortOption) {
        LOGGER.info("assertSortOptionIsDisplayed started");
        driver.waitForPageToLoad();
        String sortOptionDisplay;
        if (sortOption.contains("Cornering /")) {
            sortOptionDisplay = sortOption.split("/ ")[1];
        } else if (sortOption.contains("Buy Tire")) {
            sortOptionDisplay = sortOption.substring(9);
        } else {
            sortOptionDisplay = sortOption.split(" ")[0].trim();
        }

        WebElement sortOptionDisplayEle = webDriver.findElement(By.xpath(String.format(sortOptionXpath, sortOptionDisplay)));
        Assert.assertTrue("FAIL:  Unable to locate the sort option!",
                driver.isElementDisplayed(sortOptionDisplayEle));
        LOGGER.info("assertSortOptionIsDisplayed completed");
    }

    /**
     * Clicks Arrow on the Sort Option
     */
    public void clickArrowOnTheSortOption() {
        LOGGER.info("clickArrowOnTheSortOption started");
        driver.waitForElementClickable(sortedDesc);
        sortedDesc.click();
        driver.waitForPageToLoad();
        LOGGER.info("clickArrowOnTheSortOption completed");
    }
}