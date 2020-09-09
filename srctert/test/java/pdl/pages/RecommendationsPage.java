package pdl.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pdl.data.ConstantsPdl;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 4/25/17.
 */
public class RecommendationsPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(RecommendationsPage.class.getName());
	private CommonActions commonActions;
	
    public RecommendationsPage(Driver driver) {
        this.driver = driver;
        commonActions = new CommonActions(driver);
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "auto-driving-summary-zipcode")
    public static WebElement summaryZipcode;

    @FindBy(id = "auto-driving-summary-yourcar")
    public static WebElement summaryCar;

    @FindBy(id = "auto-driving-summary-miles-driven-per-year")
    public static WebElement summaryMilesDriven;

    @FindBy(id = "auto-driving-summary-tiresize")
    public static WebElement summaryTireSize;

    @FindBy(id = "auto-compare-tires")
    public static WebElement compareTiresButton;

    @FindBy(id = "auto- recommendation-filter-results")
    public static WebElement filterResultsButton;
    
    @FindBy(id = "auto-product-list-compare-checkbox")
    public static WebElement compareCheckbox;
    
    @FindBy(id = "auto-vehicle-year")
    public static WebElement leftHeaderVehicleYear;
    
    @FindBy(id = "auto-vehicle-make")
    public static WebElement leftHeaderVehicleMake;
    
    @FindBy(id = "auto-vehicle-model")
    public static WebElement leftHeaderVehicleModel;
    
    @FindBy(id = "auto-vehicle-trim")
    public static WebElement leftHeaderVehicleTrim;
    
    @FindBy(id = "auto-product-list-view-details")
    public static WebElement viewDetailsButton;
    
    @FindBy(className = "pdl-product-list__wet-climate")
    public static WebElement wetClimateLabelTextEle;
    
    @FindBy(className = "pdl-product-list__tire-life")
    public static WebElement expectedLifeLabelTextEle;
    
    @FindBy(className = "pdl-recommendations__header-button")
    public static WebElement drivingDetails;
      
    public static final By summaryDrivingPriorityBy = By.className("pdl-driving-summary__list-item");

    public static final By productBrandBy = By.className("pdl-product-list__overview-brand");

	public static final By productItemCodeBy = By.className("pdl-product-list__overview-article-number");

    public static final By viewDetailsButtonBy = By.id("auto-product-list-view-details");
    
    public static final By productNameBy = By.className("pdl-product-list__overview-name");
    
    public static final By productFrontTireSizeBy = By.xpath("//div[@class='pdl-product-list__overview-front']//span[@class='pdl-product-list__overview-size']");
    
    public static final By productRearTireSizeBy = By.xpath("//div[@class='pdl-product-list__rear-info']//span[@class='pdl-product-list__overview-size']");
    
    public static final By wetClimateLabelTextBy = By.className("pdl-product-list__wet-climate");
    
    public static final By expectedLifeLabelTextBy = By.className("pdl-product-list__tire-life");
    
    public static final By itemStockStatusBy = By.className("pdl-product-list__stock-status");
    
    public static final By productArticleNumberBy = By.className("pdl-product-list__overview-article-number");
    
    public static final By itemStockQtyBy = By.className("pdl-product-list__stock-quantity");
        
    public static final By recommendationResultBy = By.id("auto-compare-tires-list-item");

    public static final By recommendationRibbonBy = By.className("pdl-product-list__overview-top-recommendation");
    
    public static final By compareCheckboxBy = By.id("auto-product-list-compare-checkbox");

	public static final By productListImage = By.className("pdl-product-list__image");

    public static final String COMPARE_TIRES = "Compare Tires";
    public static final String FILTER_RESULTS = "Filter Results";
    public static final String WET_CLIMATE_STOP = "Wet Climate Stop";
    public static final String EXPECTED_TIRE_LIFE = "Expected Tire Life";
    public static final String DRIVING_DETAILS_LABEL = "Driving Details";
    
    public static final String PRODUCT_ARTICLE_NUMBER_LABEL = "Item #";

    /**
     * Verifies the number of results on the recommendation page
     *
     * @param expectedCount The expected number of results
     */
    public void verifyResultsCount(int expectedCount) {
        LOGGER.info("verifyResultsCount started");

        driver.waitForElementVisible(recommendationResultBy);
        int actualCount = driver.getElementCount(recommendationResultBy);

        Assert.assertTrue("FAIL: Expected result count: \"" + expectedCount + "\", Actual miles driven: \"" +
                actualCount + "\"", expectedCount == actualCount);
        LOGGER.info("Confirmed that the result count was \"" + expectedCount + "\".");
        LOGGER.info("verifyResultsCount completed");
    }


    /**
     * Verifies that the Top Recommendation ribbon only appears for the first tire in the list
     *
     */
    public void verifyTopRecommendationRibbon() {
        LOGGER.info("verifyTopRecommendationRibbon started");
        boolean ribbon = false;
        int index = 0;

        driver.waitForElementVisible(recommendationResultBy);
        List <WebElement> resultTires = webDriver.findElements(recommendationResultBy);

        webDriver.manage().timeouts().implicitlyWait(Constants.ZERO, TimeUnit.SECONDS);

        for (WebElement resultTire: resultTires) {
            try {
                resultTire.findElement(recommendationRibbonBy);
                ribbon = true;
                if (index > 0)
                    ribbon = false;
            } catch (NoSuchElementException e) {
                if (index == 0) {
                    ribbon = false;
                }
            }
            index++;
        }

        driver.resetImplicitWaitToDefault();

        Assert.assertTrue("FAIL: Top Recommendation ribbon was not present in the first tire result, " +
                "or was present in other results.", ribbon);
        LOGGER.info("Confirmed that the Top Recommendation ribbon was only in the first result.");
        LOGGER.info("verifyTopRecommendationRibbon completed");
    }
	
    /**
     * Selects a Product Checkbox based on the position
     *
     * @param position       The specific value of rendered checkbox's position
     * @throws Exception Exception
     */
    public void selectProductCheckboxAtPosition(int position) throws Exception {
		LOGGER.info("selectProductCheckboxAtPosition started");
		List<WebElement> checkboxes;
		try {
			driver.waitForElementVisible(recommendationResultBy);
			checkboxes = webDriver.findElements(compareCheckboxBy);
			if(checkboxes.size() >= position){
				if(!checkboxes.get(position).isSelected()){
					checkboxes.get(position).click();
				}else{
					LOGGER.info("Product list checkbox # " + position + " was already selected");
				}
			}else
				LOGGER.info("Product list checkbox to select at position " + position + "exceeded the total available products checkboxes ("+checkboxes.size()+") on the page.  ");
		} catch (Exception e) {
			Assert.fail("FAIL: FAILED with error: " + e);
		}
		LOGGER.info("selectProductCheckboxAtPosition completed");
    }
    
    /**
     * Verifies Product Checkbox status based on the displayed position 
     *
     * @param position       The specific value of rendered checkbox's position
     * @param status         The state of the checkbox to be (enabled or disabled)
     * @throws Exception Exception
     */
	public void verifyProductCheckboxStatusAtPosition(int position, String status) throws Exception {
		LOGGER.info("verifyProductCheckboxStatusAtPosition started");
		List<WebElement> checkboxes;
		try {
			driver.waitForElementVisible(recommendationResultBy);
			checkboxes = webDriver.findElements(compareCheckboxBy);
			if (checkboxes.size() >= position) {
				if (status.equalsIgnoreCase(Constants.ENABLED)) {
					if (!checkboxes.get(position).isEnabled()) {
						Assert.fail("FAIL: Expected Result: Checkbox at positon " + position
								+ "should be enabled, Actual: It was disabled");
					}
				} else if (status.equalsIgnoreCase(Constants.DISABLED)) {
					if (checkboxes.get(position).isEnabled()) {
						Assert.fail("FAIL: Expected Result: Checkbox at positon " + position
								+ "should be disabled, Actual: It was enabled");
					}
				}
			} else
				LOGGER.info("Product list checkbox to select at position " + position
						+ "exceeded the total available products checkboxes (" + checkboxes.size()
						+ ") on the page.  ");
		} catch (Exception e) {
			Assert.fail("FAIL: FAILED with error: " + e);
		}
		LOGGER.info("verifyProductCheckboxStatusAtPosition completed");
	}

    /**
     * Verifies Compare Tires button status state 
     *
     * @param status         The state of the button to be (enabled or disabled)
     * @throws Exception Exception
     */
	public void verifyCompareTiresButtonStatus(String status) throws Exception {
		LOGGER.info("verifyCompareTiresButtonStatus started");
		try {
			driver.waitForElementVisible(compareTiresButton);
			if (status.equalsIgnoreCase(Constants.ENABLED)) {
				commonActions.verifyButtonEnabled(compareTiresButton);
				LOGGER.info("Confirmed that COMPARE TIRES button was enabled.");
			} else if (status.equalsIgnoreCase(Constants.DISABLED)) {
				commonActions.verifyButtonDisabled(compareTiresButton);
				LOGGER.info("Confirmed that COMPARE TIRES button was disabled.");
			}
		} catch (Exception e) {
			Assert.fail("FAIL: FAILED with error: " + e);
		}
		LOGGER.info("verifyCompareTiresButtonStatus completed");
	}


	/**
     * Click Compare Tires Button
     * @throws Exception Exception
     */
    public void clickCompareTiresButton() throws Exception {
        LOGGER.info("clickCompareTiresButton started");
		try {
			driver.waitForElementVisible(compareTiresButton);
			if (compareTiresButton.isEnabled()){
				compareTiresButton.click();
			    LOGGER.info("Successfully clicked on Compare Tires button");
			} else
				Assert.fail("Compare Tires button wasn't enabled");
		} catch (Exception e) {
			Assert.fail("FAIL: Interaction with Compare Tires button FAILED with error: " + e);
		}
		LOGGER.info("clickCompareTiresButton completed");
	}

	/**
	 * Click Filter Results Button
	 */
	public void clickFilterResultsButton() throws Exception {
		LOGGER.info("clickFilterResultsButton started");
		driver.waitForElementVisible(filterResultsButton);
		filterResultsButton.click();
		LOGGER.info("clickFilterResultsButton completed");
	}

	/**
	 * Verifies the occurrence of ratings element
	 *
	 * @param element
	 *            The WebElement to check
	 * @param expectedCount
	 *            The occurrence of that element to verify
	 */
	public void verifyRatingsElementOccurrenceOnRecommendationPage(By element, int expectedCount) {
		LOGGER.info("verifyRatingsElementOccurrenceOnRecommendationPage started");
		driver.waitForElementVisible(element);
		int actualElementOccurrence = webDriver.findElements(element).size();
		if (expectedCount!=actualElementOccurrence) {
			Assert.fail("FAIL: Expected occurence: " + expectedCount + ", but Actual was:" + actualElementOccurrence);
		}
		LOGGER.info("Confirmed that Ratings element occurence was: " + expectedCount);
		LOGGER.info("verifyRatingsElementOccurrenceOnRecommendationPage completed");
	}
    
	/**
     * Verifies the text of the ratings element
     *
     * @param element       The WebElement to check
     * @param expectedText  The text of the element to verify
     */
    public void verifyRatingsElementText(WebElement element, String expectedText) {
        LOGGER.info("verifyRatingsElementText started");
        driver.waitForElementVisible(element);
        String actualText = element.getText().split(":")[0];
        Assert.assertTrue("FAIL: Text expected: \"" + expectedText + "\", actual text: \"" + actualText + "\".",
                actualText.equalsIgnoreCase(expectedText));
        LOGGER.info("Confirmed that \"" + expectedText + "\" was displayed .");
        LOGGER.info("verifyRatingsElementText completed");
    }

	/**
	 * Verifies the status of all View Details buttons status as enabled
	 *
	 */
	public void verifyViewDetailsButtonIsEnabledForAllProductItems() throws Exception {
		LOGGER.info("verifyViewDetailsButtonsAreEnabledForAllProductItems started");
		int index = 1;
		driver.waitForElementVisible(viewDetailsButton);
		List<WebElement> viewDetailsElements = webDriver.findElements(viewDetailsButtonBy);
		for (WebElement viewDetailsElement : viewDetailsElements) {
			if (!viewDetailsElement.isEnabled()) {
				Assert.fail("FAIL: \"View Details\" button at row (" + index + ") was disabled");
			}
			index++;
		}
		LOGGER.info("Confirmed: All " + viewDetailsElements.size() + " \" View Details \" buttons were enabled");
		LOGGER.info("verifyViewDetailsButtonsAreEnabledForAllProductItems completed");
	}

	/**
	 * Verifies the presence of stock status label(In Stock/ Out of Stock) for
	 * all items
	 *
	 */
	public void verifyItemStockStatusLabelIsPresentForAllProductItems() throws Exception {
		LOGGER.info("verifyItemStockStatusLabelIsPresentForAllProductItems started");
		int index = 1;
		driver.waitForElementVisible(itemStockStatusBy);
		List<WebElement> items = webDriver.findElements(itemStockStatusBy);
		for (WebElement item : items) {
			if (!(item.getText().contains(ConstantsPdl.INSTOCK)
					|| item.getText().contains(ConstantsPdl.OUT_OF_STOCK))) {
				Assert.fail("FAIL: \" Item \" stock status label at row (" + index
						+ ") didn't match, Actual item stock label was : " + item.getText());
			}
			index++;
		}
		LOGGER.info("Confirmed: All " + items.size() + " Items had the matching stock status label present");
		LOGGER.info("verifyItemStockStatusLabelIsPresentForAllProductItems completed");
	}

	/**
	 * Verifies the presence of quantity(Qty) status label for all items
	 *
	 */
	public void verifyItemQtyStatusLabelIsPresentForAllProductItems() throws Exception {
		LOGGER.info("verifyItemQtyStatusLabelIsPresentForAllProductItems started");
		int index = 1;
		driver.waitForElementVisible(itemStockQtyBy);
		List<WebElement> items = webDriver.findElements(itemStockQtyBy);
		for (WebElement item : items) {
			if (!item.getText().contains(ConstantsPdl.QTY_LABEL)) {
				Assert.fail("FAIL: \" Item \" quantity status label at row (" + index
						+ ") didn't match, Actual item quantity label was : " + item.getText());
			}
			index++;
		}
		LOGGER.info("Confirmed: All " + items.size() + " Items had the matching quantity status label present");
		LOGGER.info("verifyItemQtyStatusLabelIsPresentForAllProductItems completed");
	}

	/**
	 * Verifies that only the specified brand of tires is listed in results
	 *
	 * @param brand The brand to verify
	 */
	public void verifyBrandInResults(String brand) throws Exception {
		LOGGER.info("verifyBrandInResults started");

		driver.waitForElementVisible(productBrandBy);
		List<WebElement> productBrands = webDriver.findElements(productBrandBy);

		for (WebElement productBrand: productBrands) {
			if (!productBrand.getText().contains(brand)) {
				Assert.fail("FAIL: Expected brand \"" + brand + "\", Actual brand: \"" + productBrand.getText());
			} else {
				LOGGER.info("Confirmed that product listed was brand \"" + brand + "\"");
			}
		}

		LOGGER.info("verifyBrandInResults completed");
	}

	/**
	 * Verifies that only the specified item codes are listed in results
	 *
	 * @param itemCodes The array of item codes to verify
	 */
	public void verifyItemCodesInResults(String itemCodes) throws Exception {
		LOGGER.info("verifyItemCodesInResults started");

		String expectedItemCodes[] = itemCodes.split(",");
		List<String> actualItemCodesList = new ArrayList<String>();

		driver.waitForElementVisible(productItemCodeBy);
		List<WebElement> productItemCodes = webDriver.findElements(productItemCodeBy);

		//pulling actual item codes from results
		for (WebElement productItemCode: productItemCodes) {
			actualItemCodesList.add(productItemCode.getText().split("#")[1]);
		}

		//converting list to array for sort and validation
		String[] actualItemCodes = actualItemCodesList.toArray(new String[actualItemCodesList.size()]);

		//sorting expected and actual arrays to prevent dependence on results order in pdl
		Arrays.sort(expectedItemCodes);
		Arrays.sort(actualItemCodes);

		Assert.assertArrayEquals("FAIL: Expected item codes:" + expectedItemCodes +
						", actual item codes: " + actualItemCodes, expectedItemCodes, actualItemCodes);

		LOGGER.info("Confirmed that expected item codes were " + expectedItemCodes);
		LOGGER.info("verifyItemCodesInResults completed");
	}
	
	/**
	 * Clicks the first available View Details button
	 *
	 */
	public void clickFirstViewDetailsButton() {
		LOGGER.info("clickViewDetailsButton started");
		driver.waitForElementVisible(viewDetailsButton);
		 if(viewDetailsButton.isEnabled()) {
			 viewDetailsButton.click();
		}else
			Assert.fail("FAIL: No \"View Details\" button found on the page");
		LOGGER.info("clickViewDetailsButton completed");
	}
	
	/**
	 * Clicks on View Details button of specified article
	 *
	 * @param productName
	 *            The name of the product to select
	 * 
	 */
	public void clickViewDetailsButton(String productName) {
		LOGGER.info("clickViewDetailsButton started");
        int index = 0;
        boolean isFound = false;
		driver.waitForElementVisible(viewDetailsButtonBy);
		List<WebElement> resultElements = webDriver.findElements(productNameBy);
		List<WebElement> viewDetailsButtons = webDriver.findElements(viewDetailsButtonBy);
		for (WebElement resultEle : resultElements) {
			if (resultEle.getText().contains(productName)) {
				viewDetailsButtons.get(index).click();
				isFound = true;
				break;
			}
			index++;
		}
        if(!isFound){
        	Assert.fail("FAIL: No \"View Details\" button found for Product \"" + productName + "\" on the page");
        }
        LOGGER.info("clickViewDetailsButton completed");
    }
	
	
	/**
	 * Verifies all product images on recommendations/results page
	 * 
	 */
	public void verifyProductImagePresentForAllItems(){
		LOGGER.info("verifyProductImagePresentForAllItems started");
		int index = 1;
		driver.waitForElementVisible(productListImage);
		List<WebElement> items = webDriver.findElements(productListImage);
		
		for (WebElement item : items) {
			if (!item.isDisplayed()){
				Assert.fail("FAIL: \" Item \" product image at row (" + index
						+ ") doesn't exist : " );
			}
			index++;
		}
		LOGGER.info("Confirmed: All " + items.size() + " product image are present. ");
		LOGGER.info("verifyProductImagePresentForAllItems completed");
		
	}
	
	 /**
     * Verifies Driving Details Element is present
     * 
     */
    public void assertDrivingDetailsElementIsPresent(){
		LOGGER.info("assertDrivingDetailsElementIsPresent started");
		driver.waitForElementVisible(drivingDetails);
		
		if (!(driver.isElementDisplayed(drivingDetails))){
			Assert.fail("FAIL: Driving Details Element not present.");
		}
		
		LOGGER.info("assertDrivingDetailsElementIsPresent completed");
    }
	
	/**
	 * Verifies the presence of item number label (Item #) for all items
	 *
	 */
	public void verifyProductArticleNumberLabelIsPresentForAllProductItems() throws Exception {
		LOGGER.info("verifyProductArticleNumberLabelIsPresentForAllProductItems started");
		int index = 1;
		driver.waitForElementVisible(productArticleNumberBy);
		List<WebElement> items = webDriver.findElements(productArticleNumberBy);
		for (WebElement item : items) {
			if (!(item.getText().contains(PRODUCT_ARTICLE_NUMBER_LABEL))) {
				Assert.fail("FAIL: \" Product \" article number label at row (" + index
						+ ") didn't match, Actual article number label was : " + item.getText());
			}
			index++;
		}
		LOGGER.info("Confirmed: All " + items.size() + " Items had the matching product article number 'Item #' label present");
		LOGGER.info("verifyProductArticleNumberLabelIsPresentForAllProductItems completed");
	}
    /**
     * Selects a Product Checkbox based on the item Id
     *
     * @param itemId       The article number (item id) of product to select
     * @throws Exception Exception
     */
    public void selectProductCheckbox(String itemId) throws Exception {
		LOGGER.info("selectProductCheckbox started");
		List<WebElement> checkboxes;
		try {
			driver.waitForElementVisible(recommendationResultBy);
			checkboxes = webDriver.findElements(compareCheckboxBy);

			for (WebElement checkbox : checkboxes) {
				if (checkbox.getAttribute(Constants.VALUE).contains(itemId)) {
					if (!checkbox.isSelected()) {
						checkbox.click();
					} else {
						LOGGER.info("Product list checkbox for item# " + itemId + " was already selected");
					}
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("FAIL: Selecting the product checkbox for item " + itemId + " failed with error: " + e );
		}
		LOGGER.info("selectProductCheckbox completed");
	}

    /**
     * Verifies the Product Checkbox status (enabled or disabled) based on the item Id
     *
     * @param itemId      String   The article number (item id) of product to select
     * @param status      String   The Status value as Enabled or Disabled
     * @throws Exception Exception
     */
    public void VerifyProductCheckboxStatusForItem(String itemId, String status) throws Exception {
		LOGGER.info("VerifyProductCheckboxStatusForItem started");
		List<WebElement> checkboxes;
		try {
			driver.waitForElementVisible(recommendationResultBy);
			checkboxes = webDriver.findElements(compareCheckboxBy);

			for (WebElement checkbox : checkboxes) {
				if (checkbox.getAttribute(Constants.VALUE).contains(itemId)) {
					if (status.equalsIgnoreCase(Constants.ENABLED)) {
						if (!checkbox.isEnabled()) {
							Assert.fail("FAIL: Expected Result: Checkbox for item id " + itemId
									+ "should be enabled, Actual: It was disabled");
						}
					} else if (status.equalsIgnoreCase(Constants.DISABLED)) {
						if (checkbox.isEnabled()) {
							Assert.fail("FAIL: Expected Result: Checkbox for item id " + itemId
									+ "should be disabled, Actual: It was enabled");
						}
					}
					break;
				} 
			}
		} catch (Exception e) {
			Assert.fail("FAIL: Selecting the product checkbox for item " + itemId + " failed with error: " + e );
		}
		LOGGER.info("VerifyProductCheckboxStatusForItem completed");
	}

}
