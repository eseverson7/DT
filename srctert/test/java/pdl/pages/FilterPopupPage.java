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
 * Created by aaronbriel on 6/7/17.
 */
public class FilterPopupPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(FilterPopupPage.class.getName());

    public FilterPopupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "pdl-product-filter")
    public static WebElement filterPopup;

    @FindBy(id = "auto-product-filter-apply-filter")
    public static WebElement applyFilterButton;

    @FindBy(id = "auto- product-filter-actions-reset")
    public static WebElement resetAllButton;

    @FindBy(className = "pdl-product-filter__qty")
    public static WebElement stockDropdown;

    @FindBy(className = "pdl-product-filter__other-options")
    public static WebElement othersSectionBy;

    private static final By brandBy = By.id("auto-porduct-filter-brand");

    private static final By span = By.tagName("span");

    private static final String DISPLAYS = "displays";
    private static final String RESET_ALL = "Reset All";
    private static final String APPLY_FILTER = "Apply Filter";
    public static final String NO_PRODUCTS_MATCH = "No products match.";

    /**
     * Selects filter checkbox for brandParent
     *
     * @param brandValue The brandParent checkbox to click
     */
    public void selectBrand(String brandValue) {
        LOGGER.info("selectBrand started");
        driver.waitForElementVisible(brandBy);
        List<WebElement> brands = webDriver.findElements(brandBy);

        for (WebElement brand: brands) {
            String actualBrand = brand.findElement(span).getText().toLowerCase();
            if (actualBrand.contains(brandValue.toLowerCase())) {
                brand.findElement(span).click();
                break;
            }
        }

        LOGGER.info("selectBrand completed");
    }

    /**
     * Verifies that the filter popup loaded
     * @param status Whether to check if it is displayed or closed
     */
    public void assertFilterPopupOpenOrClosed(String status) {
        LOGGER.info("assertFilterPopupLoaded started");
        if (status.equalsIgnoreCase(DISPLAYS)) {
            driver.waitForElementVisible(filterPopup);
            LOGGER.info("Confirmed that the filter popup was displayed.");
        } else {
            if (driver.isElementDisplayed(filterPopup, Constants.ONE_SEC_WAIT)) {
                Assert.fail("FAIL: Filter popup was displayed when it should have been closed");
            } else {
                LOGGER.info("Confirmed that the filter popup was closed");
            }
        }
        LOGGER.info("assertFilterPopupLoaded completed");
    }

    /**
     * Clicks Apply Filter Button
     */
    public void clickApplyFilterButton() throws Exception {
        LOGGER.info("clickApplyFilterButton started");
        driver.waitForElementVisible(applyFilterButton);
        applyFilterButton.click();
        LOGGER.info("clickApplyFilterButton completed");
    }

    /**
     * Clicks Reset All Button
     */
    public void clickResetAllButton() throws Exception {
        LOGGER.info("clickResetAllButton started");
        driver.waitForElementVisible(resetAllButton);
        resetAllButton.click();
        LOGGER.info("clickResetAllButton completed");
    }

    /**
     * Selects Stock dropdown quantity
     *
     * @param quantity The brandParent checkbox to click
     */
    public void selectStockQuantity(String quantity) {
        LOGGER.info("selectStockQuantity started");
        driver.waitForElementVisible(stockDropdown);
        driver.selectFromDropdownByVisibleText(stockDropdown, quantity);
        LOGGER.info("selectStockQuantity completed");
    }

    /**
     * Selects Other dropdown checkbox
     *
     * @param checkboxText The text of checkbox to click
     */
    public void selectOtherCheckbox(String checkboxText) {
        LOGGER.info("selectOtherCheckbox started");
        driver.waitForElementVisible(brandBy);
        List<WebElement> lists = othersSectionBy.findElements(CommonActions.label);
        for (WebElement list: lists) {
            if (list.getText().equalsIgnoreCase(checkboxText)) {
                list.click();
                break;
            }
        }
        LOGGER.info("selectOtherCheckbox completed");
    }

    /**
     * Verifies error message on Filter popup
     *
     * @param message Text of the error message to verify
     */
    public void assertFilterErrorMessage(String message) {
        LOGGER.info("assertErrorMessage started");
        driver.waitForElementVisible(brandBy);
        WebElement errorMessage = filterPopup.findElement(CommonActions.errorMessageBy);
        Assert.assertTrue("FAIL: Filter popup error message did not contain " + message,
                errorMessage.getText().contains(message));
        LOGGER.info("assertErrorMessage completed");
    }
}
