package dtc.pages;

import common.Constants;
import dtc.data.ConstantsDtc;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Channing Luden on 10/14/2016.
 */
public class ProductBrandsPage {

    private final Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(FitmentPopupPage.class.getName());

    public ProductBrandsPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static final By imageBy = By.cssSelector(".category-list__image-link > img");

    private static final By brandContainersBy = By.className("category-list__member");

    @FindBy(css = ".category-list__image-link > img")
    public static WebElement image;

    /**
     * Returns auto-classname for option
     *
     * @param option Type of option to shop
     * @return autoClassname    auto-classname for option
     */
    private String getAutoClassName(String option) {
        String autoOption = option.substring(option.lastIndexOf("for ") + 4)
                .replaceAll(" / ", "-").replaceAll(" ", "-").toLowerCase();
        return "auto-shop-btn-" + autoOption;
    }

    /**
     * Selects an option from the Brands page - Brand name image
     *
     * @param option Brand name to shop
     */
    public void selectBrandOption(String option) {
        LOGGER.info("selectOption started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(image);

        try {
            List<WebElement> brandImageList = webDriver.findElements(imageBy);
            for (WebElement brandImage : brandImageList) {
                if (brandImage.getAttribute(Constants.ATTRIBUTE_ALT).toLowerCase().contains(option.toLowerCase())) {
                    driver.jsScrollToElement(brandImage);
                    brandImage.click();
                    break;
                }
            }
        } catch (NoSuchElementException nse) {
            Assert.fail("FAIL: Option \"" + option + "\" NOT found on the brand page! (Full Stack Trace: " +
                    nse.toString() + ")");
        }

        LOGGER.info("selectOption completed");
    }

    /**
     * Selects a specific option from the specific brand page - Wheel / tire type
     *
     * @param option Wheel / tire type for which to shop
     */
    public void selectTireWheelBrandOption(String option) {
        LOGGER.info("selectTireWheelBrandOption started");
        driver.waitForPageToLoad();
        String autoClassName = getAutoClassName(option);

        try {
            driver.waitForClassPresent(autoClassName, Constants.DEFAULT_SEC_WAIT);
            WebElement optionElement = webDriver.findElement(By.className(autoClassName));
            driver.jsScrollToElement(optionElement);
            optionElement.click();

        } catch (NoSuchElementException nse) {
            Assert.fail("FAIL: Option \"" + option + "\" NOT found on the brand page! (Full Stack Trace: " +
                    nse.toString() + ")");
        }

        LOGGER.info("selectTireWheelBrandOption completed");
    }

    /**
     * Method will click the View All button at bottom of the page
     */
    public void selectViewAllOptionButton() {
        LOGGER.info("selectViewAllOptionButton started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(CommonActions.btnDefault);
        List<WebElement> btnDefaultElements = webDriver.findElements(CommonActions.btnDefaultBy);
        for (WebElement btnDefaultElement : btnDefaultElements) {
            if (btnDefaultElement.getAttribute(Constants.TITLE).equalsIgnoreCase(ConstantsDtc.TITLE_VIEW_ALL)) {
                driver.jsScrollToElement(btnDefaultElement);
                btnDefaultElement.click();
                break;
            }
        }
        LOGGER.info("selectViewAllOptionButton completed");
    }

    /**
     * Verifies that all the listed Brand containers have images (and are active links) on the Product Brands page
     */
    public void verifyAllListBrandsHaveImages() {
        LOGGER.info("verifyAllListBrandsHaveImages started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(image);
        int brandCounter = 1;
        ArrayList<Integer> emptyBrandLinkHrefList = new ArrayList<>();
        ArrayList<Integer> emptyBrandImageSrcList = new ArrayList<>();
        ArrayList<Integer> emptyBrandImageAltList = new ArrayList<>();

        List<WebElement> brandContainersList = webDriver.findElements(brandContainersBy);

        for (WebElement brandContainer : brandContainersList) {
            WebElement brandLinkEle = brandContainer.findElement(CommonActions.anchorTagBy);
            String brandLinkHref = brandLinkEle.getAttribute(Constants.ATTRIBUTE_HREF);

            WebElement brandImageEle = brandContainer.findElement(CommonActions.imgTagBy);
            String brandImageSrc = brandImageEle.getAttribute(Constants.ATTRIBUTE_SRC);
            String brandImageAlt = brandImageEle.getAttribute(Constants.ATTRIBUTE_ALT);

            if (CommonActions.isStringEmpty(brandLinkHref)) {
                LOGGER.info("Brand #'" + brandCounter + "' had an EMPTY HREF attribute!");
                emptyBrandLinkHrefList.add(brandCounter);
            }

            if (CommonActions.isStringEmpty(brandImageSrc)) {
                LOGGER.info("Brand #'" + brandCounter + "' had an EMPTY IMG SRC attribute!");
                emptyBrandImageSrcList.add(brandCounter);
            }

            if (CommonActions.isStringEmpty(brandImageAlt)) {
                LOGGER.info("Brand #'" + brandCounter + "' had an EMPTY IMG ALT attribute!");
                emptyBrandImageAltList.add(brandCounter);
            }
            brandCounter++;
        }

        Assert.assertEquals("FAIL: The following brand #s had empty HREF attributes!\n\t Brand numbers: '"
                + emptyBrandLinkHrefList + "'", 0, emptyBrandLinkHrefList.size());

        Assert.assertEquals("FAIL: The following brand #s had empty IMG SRC attributes!\n\t Brand numbers: '"
                + emptyBrandLinkHrefList + "'", 0, emptyBrandImageSrcList.size());

        Assert.assertEquals("FAIL: The following brand #s had empty IMG ALT attributes!\n\t Brand numbers: '"
                + emptyBrandLinkHrefList + "'", 0, emptyBrandImageAltList.size());
        LOGGER.info("verifyAllListBrandsHaveImages completed");
    }
}