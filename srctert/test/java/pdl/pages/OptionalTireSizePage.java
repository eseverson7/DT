package pdl.pages;

/**
 * Created by aarora on 05/18/17.
 */

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
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class OptionalTireSizePage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(OptionalTireSizePage.class.getName());

    public OptionalTireSizePage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "pdl-optionalsizes__vehicle-label")
    public static WebElement optionalTireSizeHeader;

    @FindBy(id = "auto-tire-staggered-select")
    public static WebElement staggeredSet;

    @FindBy(id = "auto-tire-straggerdsize-front-list")
    public static WebElement frontOptionalSizeList;
    
    @FindBy(id = "auto-tire-straggerdsize-rear-list")
    public static WebElement rearOptionalSizeList;
    
    @FindBy(className = "pdl-optionalsizes__item")
    public static WebElement optionalSizeList;
    
    @FindBy(id = "auto-tire-size-modal-close")
    public static WebElement optionalSizeModalCloseIcon;

    public static By li = By.tagName("li");


    /**
     * Selects a staggered set value from dropdown controls
     *
     * @param staggeredSetValue       The set value to select
     * @throws Exception Exception
     */
    public void selectStaggeredSet(String staggeredSetValue) throws Exception {
        LOGGER.info("selectStaggeredSet started");
        try {
            driver.waitForElementVisible(staggeredSet);
            driver.selectFromDropdownByVisibleText(staggeredSet, staggeredSetValue);
        } catch (Exception e) {
            Assert.fail("FAIL: Selecting Staggered Set FAILED with error: " + e);
        }
        LOGGER.info("selectStaggeredSet completed");
    }

    /**
     * Selects a staggered front and rear size from optional tires controls
     *
     * @param frontSize       The front size value to select
     * @param rearSize		  The rear size value to select
     * @throws Exception Exception
     */
    public void selectFrontRearStaggeredOptionalFitment(String frontSize, String rearSize) throws Exception {
		LOGGER.info("selectFrontRearStaggeredOptionalFitment started");
		int rowCount = 0;
		boolean found = false;
		List<WebElement> frontRows;
		List<WebElement> rearRows;

		webDriver.manage().timeouts().implicitlyWait(Constants.ZERO, TimeUnit.SECONDS);

		try {

			frontRows = frontOptionalSizeList.findElements(li);
			rearRows = rearOptionalSizeList.findElements(li);

			while (rowCount < frontRows.size()) {
				for (WebElement frontRow : frontRows) {
					if (frontRow.getText().contains(frontSize)
							&& (rearRows.get(rowCount).getText().contains(rearSize))) {
						found = true;
						frontRow.click();
						break;
					} else {
						rowCount++;
					}
				}
				if(found)
					break;
			}
		} catch (Exception e) {
			Assert.fail("FAIL: Selecting Staggered Optional Front & Rear Size FAILED with error: " + e);
		}
		LOGGER.info("selectFrontRearStaggeredOptionalFitment completed");
    }
    
    /**
     * Selects a standard tire size from optional tires controls
     *
     * @param tireSize       The tire size value to select
     * @throws Exception Exception
     */
	public void selectStandardOptionalFitment(String tireSize) throws Exception {
		LOGGER.info("selectStandardOptionalFitment started");
		try {
			driver.waitForElementVisible(optionalTireSizeHeader);
			WebElement tireSizeEle = webDriver.findElement(By.id(tireSize));
			if (tireSizeEle.isDisplayed()) {
				tireSizeEle.click();
			}
		} catch (Exception e) {
			Assert.fail("FAIL: Selecting Standard Optional Tire Size FAILED with error: " + e);
		}
		LOGGER.info("selectStandardOptionalFitment completed");
	}
	
	/**
     * Click Modal Close Icon
     * @throws Exception Exception
     */
    public void clickModalCloseIcon() throws Exception {
		LOGGER.info("clickModalCloseIcon started");
		driver.waitForElementVisible(optionalSizeModalCloseIcon);
		optionalSizeModalCloseIcon.click();
		LOGGER.info("Successfully clicked on modal close icon");
		LOGGER.info("clickModalCloseIcon completed");
	}


}