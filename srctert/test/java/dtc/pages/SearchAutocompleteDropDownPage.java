package dtc.pages;

/**
 * Created by aarora on 10/10/16.
 */

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Constants;
import utilities.Driver;

import java.util.logging.Logger;

public class SearchAutocompleteDropDownPage {

	private Driver driver;
	private WebDriver webDriver;
	private final Logger LOGGER = Logger.getLogger(SearchAutocompleteDropDownPage.class.getName());

	@FindBy(className = "autocomplete-row__itemid")
	public static WebElement autoCompleteTireNameInfo;

	@FindBy(className = "autocomplete-row__productname")
	public static WebElement autoCompleteProductNameInfo;

	public SearchAutocompleteDropDownPage(Driver driver) {
		this.driver = driver;
		webDriver = driver.getDriver();
		PageFactory.initElements(webDriver, this);
	}

	/**
	 * Verifies passed in product name is shown in the results panel
	 *
	 * @param productName Expected name of product
	 */
	public void assertFirstProductNameInResultPanel(String productName) {
		LOGGER.info("assertFirstProductNameInResultPanel started");
		driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
		Assert.assertEquals(
				"FAIL: The expected product name: \"" + productName + "\" did NOT match the actual: \""
						+ autoCompleteProductNameInfo.getText() + "\"!",
				productName, autoCompleteProductNameInfo.getText());
		LOGGER.info("Confirmed that \"" + productName + "\" was listed in the Search Autocomplete DropDown.");
		LOGGER.info("assertFirstProductNameInResultPanel completed");
	}

	/**
	 * Verifies passed in item ID is shown in the results panel
	 *
	 * @param itemId Expected item ID
	 */
	public void assertFirstItemIdInResultPanel(String itemId) {
		LOGGER.info("assertFirstItemIdInResultPanel started");
		Assert.assertEquals("FAIL: \"" + itemId + "\" was NOT found in dropdown!", itemId,
				autoCompleteTireNameInfo.getText());
		LOGGER.info("Confirmed that \"" + itemId + "\" was listed in the Search Autocomplete DropDown.");
		LOGGER.info("assertFirstItemIdInResultPanel completed");
	}

	/**
	 * Finds product on page by name and clicks on it
	 *
	 * @param productName Name of product to select
	 */
	public void selectProductByName(String productName) {
		LOGGER.info("selectProductByName started");
		driver.waitForElementClickable(autoCompleteProductNameInfo);
		try {
			driver.clickElementWithLinkText(productName);
		} catch (Exception e) {
			HomePage.searchBoxInput.sendKeys(Keys.RETURN);
		}
		LOGGER.info("selectProductByName completed");
	}
}