package dtc.pages;


import common.Config;
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
 * Created by mnabizadeh on 10/20/16.
 */

public class BrandsPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(BrandsPage.class.getName());

    public BrandsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static String brandName = "page-BRANDtire-";

    private static final By brandRefinementBy = By.className("refinements__selected");

    private static final By brandImageBy = By.cssSelector(".category-list__image-link img");

    private static final By fitmentComponentBy = By.className("fitment-component__toggle-button");

    @FindBy(className = "auto-shop-btn-all-season-tires")
    public static WebElement shopAllSeasonButton;

    @FindBy(className = "auto-shop-btn-all-terrain-tires")
    public static WebElement shopAllTerrainButton;

    @FindBy(className = "auto-shop-btn-performance-tires")
    public static WebElement shopPerformanceButton;

    @FindBy(className = "auto-shop-btn-summer-tires")
    public static WebElement shopSummerButton;

    @FindBy(className = "auto-shop-btn-touring-tires")
    public static WebElement shopTouringButton;

    @FindBy(className = "auto-shop-btn-truck-suv-tires")
    public static WebElement shopTruckSuvButton;

    /**
     * Selects category of tire option to select based on input
     *
     * @param subCategory category of tire to select
     */
    public void clickShopBrandCategoryTires(String subCategory) {
        LOGGER.info("clickShopBrandCategoryTires started");
        driver.jsScrollToElement(shopAllSeasonButton);
        driver.waitForElementClickable(shopAllSeasonButton);

        if (subCategory.equals(shopAllSeasonButton.getText()))
            shopAllSeasonButton.click();
        else if (subCategory.equals(shopAllTerrainButton.getText()))
            shopAllTerrainButton.click();
        else if (subCategory.equals(shopPerformanceButton.getText()))
            shopPerformanceButton.click();
        else if (subCategory.equals(shopSummerButton.getText()))
            shopSummerButton.click();
        else if (subCategory.equals(shopTouringButton.getText()))
            shopTouringButton.click();
        else if (subCategory.equals(shopTruckSuvButton.getText()))
            shopTruckSuvButton.click();

        LOGGER.info("clickShopBrandCategoryTires completed");
    }

    /**
     * Verifies the brand that was selected appears on the page
     *
     * @param brand the brand of tire that has been selected
     */
    public void verifyBrandSelected(String brand) {
        LOGGER.info("verifyBrandSelected started");
        driver.jsScrollToElement(shopAllSeasonButton);
        driver.waitForElementVisible(shopAllSeasonButton);
        final By brandElement = By.className(brandName.concat(brand.split(" ")[0].toLowerCase()));
        Assert.assertTrue("FAIL: The brand: \"" + brand + "\" was NOT found on the page!",
                webDriver.findElement(brandElement).getText().toUpperCase().trim()
                        .contains(brand.toUpperCase()));
        LOGGER.info("verifyBrandSelected completed");
    }

    /**
     * Verifies the selected brand category appears in the refinements area
     *
     * @param refinement selected category / refinement to appear under refinements
     *                   section
     */
    public void verifyBrandRefinementSelected(String refinement) {
        if (Config.isMobilePhone()) {

            LOGGER.info("Skipped the refinement validation on Mobile");
        } else {

            LOGGER.info("verifyBrandRefinementSelected started");
            boolean refinmentFound = false;
            // selecting brand refinement if refinement is contained in text of
            // row
            List<WebElement> rows = webDriver.findElements(brandRefinementBy);

            if (Config.isIe())
                driver.waitForMilliseconds(Constants.TWO_SEC_WAIT);
            //TODO: added a counter for future reference,  Not required at this moment
            //but for later if we have a specific req w.r.t positioning and/or ordering
            int i = 0;
            for (WebElement row : rows) {

                if (Config.isIe())
                    driver.waitForMilliseconds();

                if (row.getText().trim().contains(refinement)) {
                    refinmentFound = true;
                    LOGGER.info(
                            "Confirmed that Refinement '" + refinement + "' was listed in the fitment search results.");
                    break;
                } else {
                    i++;
                }

                if (!refinmentFound) {
                    Assert.fail("FAIL: Refinement '" + refinement + "' was not listed in the fitment search results.");
                }
            }
            LOGGER.info("verifyBrandRefinementSelected completed");
        }
    }

    /**
     * Selects the given brand based on image 'alt' tag text on the Brands page
     *
     * @param brand selected company brand to appear on the 'alt' tag text
     */
    public void selectBrandImageViaText(String brand) {
        LOGGER.info("selectBrandImageViaText started");
        driver.waitForElementVisible(brandImageBy);
        WebElement brandImage = driver.getElementWithAttribute(brandImageBy, Constants.ATTRIBUTE_ALT, brand);
        driver.jsScrollToElement(brandImage);
        brandImage.click();
        LOGGER.info("selectBrandImageViaText completed");
    }

    /**
     * Click wheel category option to select based on input
     *
     * @param subCategory Type of wheels to select
     */
    public void clickShopBrandCategoryWheels(String subCategory) {
        LOGGER.info("clickShopBrandCategoryWheels started for " + subCategory);
        driver.clickElementWithLinkText(subCategory);
        LOGGER.info("clickShopBrandCategoryWheels completed for " + subCategory);
    }

    /**
     * Select category for Brand via fitment
     *
     * @param category Wheels or Tires
     */
    public void selectBrand(String category) {
        LOGGER.info("selectBrand started for " + category);
        driver.jsScrollToElementClick(driver.getElementWithText(fitmentComponentBy, category));
        LOGGER.info("selectBrand completed for " + category);
    }
}