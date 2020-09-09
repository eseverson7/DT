package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtils;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Channing Luden on 10/6/2016.
 */
public class StoreLocatorPage {

    private HomePage homePage;
    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;

    private final Logger LOGGER = Logger.getLogger(StoreLocatorPage.class.getName());

    public StoreLocatorPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        homePage = new HomePage(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String AMERICA = "America";
    private static final String DISCOUNT = "Discount";
    private static final String CURRENT_STORE_LABEL = "current store";
    private static final String CURRENT_STORE_BLUE = "rgba(1, 177, 227, 1)";
    private static final String SCHEDULE_APPOINTMENT_LABEL = "Schedule appointment";

    @FindBy(className = "store-locator__select-column")
    public static WebElement selectedRange;

    @FindBy(className = "store-locator__select")
    public static WebElement dropdownList;

    @FindBy(xpath = "//a[@class='chosen-single']//span")
    public static WebElement storeSearchSelectedRange;

    public static String searchRangeOption = "//ul[@class='chosen-results']//li[text()='%s Miles']";

    @FindBy(id = "storelocator-query")
    public static WebElement cityStateZipSearchField;

    @FindBy(xpath = "//button[text()='Search']")
    public static WebElement storeLocatorSearchButton;

    @FindBy(xpath = "//div[@class='store-pop']//h2")
    public static WebElement welcomePopUpHeader;

    @FindBy(xpath = "//div[@class='store-pop']//p")
    public static WebElement welcomePopUpMessageText;

    @FindBy(id = "chooseStore")
    public static WebElement welcomePopUpContinueButton;

    @FindBy(id = "opposite")
    public static WebElement welcomePopUpSearchLink;

    @FindBy(className = "activate-message")
    public static WebElement currentStoreActive;

    @FindBy(className = "track-make-my-store")
    public static WebElement makeThisMyStoreButton;

    public static final By storeNameInStoreLocatorResults = By.className("link-quaternary");

    public static final By currentSelectedStoreInStoreLocatorResults =
            By.cssSelector("[class=\"activate-message store-locator__current\"]");

    public static final By directionsLinks = By.linkText(ConstantsDtc.DIRECTIONS);

    public static final By sendToPhoneResultLinks = By.className("sendstoretophone");

    public static final By scheduleApptResultButtons = By.xpath("//button[normalize-space()='Schedule appointment']");

    public static final By makeMyStoreResultButtons = By.xpath("//button[normalize-space()='Make this my store']");

    public static final By makeThisMyStoreBy = By.className("track-make-my-store");

    public static final By storeInfoBox = By.className("store-locator__store-info");

    public static final By welcomePopUpWindow = By.className("store-pop");

    public static final By hoursOpText = By.className("store-locator__operation-column");

    public static final By storeRowBy = By.className("store-locator__list-item");

    public boolean currentStoreSelected = false;


    /**
     * Searches for a store within a specified range, using city and state or zip code.
     *
     * @param searchRange    range or distance with which to limit the store locator search. Current valid selections
     *                       are 10, 25, 50, and 75 miles.
     * @param cityStateOrZip city and state or zip code for store locator search.
     */
    public void searchForStore(String searchRange, String cityStateOrZip) {
        LOGGER.info("searchForStore started");
        driver.waitForPageToLoad();

        //TODO: retest when new safaridriver is stable
        if (Config.isIe() || Config.isSafari() || Config.isFirefox())
            driver.waitForMilliseconds();

        driver.waitForElementVisible(storeLocatorSearchButton);
        if (Config.getSiteRegion().contains(ConstantsDtc.AT)) {
            commonActions.checkForWelcomePopup();
        }

        if (Config.isMobilePhone()) {
            searchRange = searchRange + " Miles";
            selectedRange.click();
            driver.selectFromDropdownByVisibleText(dropdownList, searchRange);
        } else {
            if (!storeSearchSelectedRange.getText().contains(searchRange)) {

                driver.waitForMilliseconds();
                storeSearchSelectedRange.click();

                //TODO: retest when new safaridriver is stable
                if (Config.isSafari())
                    driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

                webDriver.findElement(By.xpath
                        (String.format(searchRangeOption, searchRange))).click();
            }
        }

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds();

        cityStateZipSearchField.click();
        cityStateZipSearchField.clear();
        cityStateZipSearchField.sendKeys(cityStateOrZip);

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        //Error handing for intermittent 'cannot determine loading status' failures
        try {
            storeLocatorSearchButton.click();
            commonActions.checkForWelcomePopup();
        } catch (Exception e) {
            driver.waitForElementClickable(storeLocatorSearchButton);
            storeLocatorSearchButton.click();
            commonActions.checkForWelcomePopup();
        }

        if (Config.isFirefox()) {
            driver.waitForPageToLoad();
        }

        if (Config.getSiteRegion().contains(ConstantsDtc.AT)) {
            commonActions.checkForWelcomePopup();
        }
        LOGGER.info("searchForStore completed");
    }

    /**
     * Asserts that Pop-Up displayed is for the correct site (Discount Tire OR America's Tire).
     *
     * @param siteToValidate name of the site, whose appearance in the h2 and p tags is the basis for validation
     */
    public void confirmPopUpForSite(String siteToValidate) {
        LOGGER.info("confirmPopUpForSite started");
        //TODO: retest when new safaridriver is stable
        if (Config.isSafari())
            driver.waitForMilliseconds();

        driver.waitForElementVisible(welcomePopUpHeader);
        Assert.assertTrue("FAIL: The Welcome Pop-Up header text of: \"" + welcomePopUpHeader.getText()
                        + "\" did not contain expected : \"" + siteToValidate + "\"!",
                welcomePopUpHeader.getText().contains(siteToValidate));
        Assert.assertTrue("FAIL: The Welcome Pop-Up message text did NOT contain any mention of expected " +
                "site: \"" + siteToValidate + "\"!", welcomePopUpMessageText.getText().contains(siteToValidate));
        LOGGER.info("Confirmed Welcome Pop-Up for '" + siteToValidate + "'.");
        LOGGER.info("confirmPopUpForSite completed");
    }

    /**
     * Closes the Welcome Pop-Up by either clicking Continue or Search for another store link.
     *
     * @param action Search OR Close
     */
    public void closeWelcomePopUp(String action) {
        LOGGER.info("closeWelcomePopUp started with action: " + action);
        if (action.equalsIgnoreCase(ConstantsDtc.SEARCH)) {
            welcomePopUpSearchLink.click();
        } else {
            welcomePopUpContinueButton.click();
        }
        driver.waitForElementNotVisible(welcomePopUpWindow, Constants.TWO_SEC_WAIT);
        LOGGER.info("closeWelcomePopUp completed with action: " + action);
    }

    /**
     * Asserts store location search results on first page all contain the expected site name.
     *
     * @param siteToValidate name of the site, used in validating the store location names match as expected
     */
    public void confirmStoreFoundForSite(String siteToValidate) {
        LOGGER.info("confirmStoreFoundForSite started");
        List<WebElement> searchResultStoreNames = webDriver.findElements(storeNameInStoreLocatorResults);

        for (WebElement searchResult : searchResultStoreNames) {
            Assert.assertTrue("FAIL: Store location search results \"" + searchResult.getText()
                            + "\" did not contain \"" + siteToValidate + "\"!",
                    searchResult.getText().contains(siteToValidate));
            LOGGER.info("Confirmed search results store name matched \"" + siteToValidate + "\".");
        }
        LOGGER.info("confirmStoreFoundForSite completed");
    }

    /**
     * Clicks Make This My Store button
     */
    public void clickMakeThisMyStoreButton() {
        LOGGER.info("clickMakeThisMyStoreButton started");
        if (Config.isMobile())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        driver.waitForPageToLoad();
        if (!driver.isElementDisplayed(currentStoreActive)) {
            try {
                driver.waitForElementClickable(makeThisMyStoreButton);
                driver.jsScrollToElement(makeThisMyStoreButton);
                makeThisMyStoreButton.click();
            } catch (Exception e) {
                homePage.goToHome();
            }
        }
        LOGGER.info("clickMakeThisMyStoreButton completed");
    }

    /**
     * Clicks Make This My Store button in row where store info box contains storeName
     *
     * @param storeName The store name of the row to click Make This My Store
     */
    public void clickMakeThisMyStoreButton(String storeName) {
        LOGGER.info("clickMakeThisMyStoreButton started");

        // return if the target store is already selected
        List<WebElement> storeRows = webDriver.findElements(storeRowBy);
        boolean found = false;
        for (WebElement storeRow : storeRows) {
            String rowText = storeRow.getText();
            if (CommonUtils.containsIgnoreCase(rowText, storeName) &&
                    CommonUtils.containsIgnoreCase(rowText, CURRENT_STORE_LABEL)) {
                found = true;
                break;
            }
        }

        if (found) {
            return;
        }

        driver.waitForPageToLoad();
        driver.waitForElementClickable(makeThisMyStoreButton);

        //TODO: retest when new safaridriver or geckodriver is updated & stabilized
        if (Config.isSafari() || Config.isFirefox())
            driver.waitForMilliseconds();

        //clicking indexed Make This My Store button if storeName is contained in storeInfoBox
        List<WebElement> storeBoxes = webDriver.findElements(storeInfoBox);
        List<WebElement> makeThisMyStoreButtons = webDriver.findElements(makeThisMyStoreBy);

        int i = 0;

        driver.jsScrollToElement(makeThisMyStoreButton);

        //TODO: retest when new safaridriver is stable
        //safaridriver fails in the loop if size is 1, so this conditional was added.
        if (makeThisMyStoreButtons.size() == 1) {
            driver.jsScrollToElement(makeThisMyStoreButton);
            makeThisMyStoreButton.click();
        } else {
            for (WebElement storeBox : storeBoxes) {
                if (storeBox.getText().contains(storeName)) {
                    if (Config.isMobile()) {
                        driver.jsClick(makeThisMyStoreButtons.get(i));
                    } else {
                        //Handing intermittent 'cannot determine loading status' failures
                        try {
                            makeThisMyStoreButtons.get(i).click();
                        } catch (Exception e) {
                            driver.waitForElementClickable(makeThisMyStoreButton);
                            makeThisMyStoreButtons = webDriver.findElements(makeThisMyStoreBy);
                            makeThisMyStoreButtons.get(i).click();
                        }
                    }
                    break;
                } else {
                    i++;
                }
            }
        }
        driver.waitForPageToLoad();
        if (driver.isElementNotVisible(CartPage.modalSwitchCartBy, Constants.TWO_SEC_WAIT)) {
            driver.waitForElementVisible(currentStoreActive);
        }
        LOGGER.info("clickMakeThisMyStoreButton completed");
    }

    /**
     * Uses site region and environment to determine default store city and asserts against store displayed
     *
     * @param siteRegion Store region currently AT or DT
     * @param dataSet    Test environment, currently QA or STG
     */
    public void verifyDefaultStoreCity(String siteRegion, String dataSet) {
        LOGGER.info("verifyDefaultStoreCity started with siteRegion: " + siteRegion + " and dataSet: " + dataSet);
        String city = "";

        if (siteRegion.equalsIgnoreCase(ConstantsDtc.AT) && dataSet.equalsIgnoreCase(Constants.QA)) {
            city = ConstantsDtc.AT_QA_DEFAULT_STORE_CITY;
        } else if (siteRegion.equalsIgnoreCase(ConstantsDtc.AT) && dataSet.equalsIgnoreCase(Constants.STG)) {
            city = ConstantsDtc.AT_STG_DEFAULT_STORE_CITY;
        } else if (siteRegion.equalsIgnoreCase(ConstantsDtc.DT) && dataSet.equalsIgnoreCase(Constants.QA)) {
            city = ConstantsDtc.DT_QA_DEFAULT_STORE_CITY;
        } else if (siteRegion.equalsIgnoreCase(ConstantsDtc.DT) && dataSet.equalsIgnoreCase(Constants.STG)) {
            city = ConstantsDtc.DT_STG_DEFAULT_STORE_CITY;
        }
        Assert.assertTrue("FAIL: The homepage \"My Store\" address: " + HomePage.myStoreAddress.getText()
                        + " did NOT contain the expected city of: \"" + city + "\"!",
                HomePage.myStoreAddress.getText().toLowerCase().contains(city));
        LOGGER.info("verifyDefaultStoreCity completed successfully with siteRegion: " + siteRegion
                + " and dataSet: " + dataSet);
    }

    //TODO: This method is NOT working

    /**
     * Confirm "current store" text marked / present on store-locator page.
     */
    public void confirmCurrentStoreTextPresent() {
        if (driver.waitForTextPresent(currentSelectedStoreInStoreLocatorResults,
                CURRENT_STORE_LABEL, Constants.DEFAULT_SEC_WAIT))
            LOGGER.info("Confirmed 'Current Store' label is present.");
        else
            LOGGER.info("Confirmed 'Current Store' label is not present.");
    }

    /**
     * Navigate to default stored details page through URL.
     */
    public void navigateToMyStoreThroughUrl() {
        LOGGER.info("navigateToMyStoreThroughUrl Started");
        driver.getUrl(Config.getBaseUrl().concat(Config.getDefaultStoreCodeURL()));
        LOGGER.info("navigateToMyStoreThroughUrl Completed");
    }

    /**
     * Navigate to store details page through specified URL path.
     *
     * @param path Path to store
     */
    public void navigateToMyStoreThroughUrlPath(String path) {
        LOGGER.info("navigateToMyStoreThroughUrlPath Started");
        driver.getUrl(Config.getBaseUrl().concat(path));
        LOGGER.info("navigateToMyStoreThroughUrlPath Completed");
    }

    /**
     * Navigate to default stored details page through URL.
     */
    public void navigateToScottsdaleStoreThroughUrl() {
        LOGGER.info("navigateToScottsdaleStoreThroughUrl Started");
        driver.getUrl(Config.getBaseUrl().concat(ConstantsDtc.SCOTTSDALE_STORE_CODE_PATH));
        LOGGER.info("navigateToScottsdaleStoreThroughUrl Completed");
    }

    /**
     * Executes an action for a store location result, specified by its number in the result list. If there is no match
     * on action, the default is to Schedule appointment.
     *
     * @param action            The action to take (Schedule appointment, Make this my store, Send to phone)
     * @param storeResultNumber number representing position of the store location result item in the result list
     */
    public void selectActionForStoreLocationResultItem(String action, String storeResultNumber) {
        //TODO - Refactor this method as the index needed for many of the actions differ
        LOGGER.info("selectionActionForResultItem started action: " + action + " for store #" + storeResultNumber);
        List<WebElement> elements;

        driver.waitForPageToLoad();
        commonActions.checkForWelcomePopup();

        //TODO: retest when new safaridriver is stable
        if (Config.isSafari() || Config.isMobile() || Config.isFirefox())
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

        if (action.equalsIgnoreCase("Schedule appointment")) {
            driver.waitForElementVisible(scheduleApptResultButtons);
            elements = webDriver.findElements(scheduleApptResultButtons);
        } else if (action.equalsIgnoreCase("Make this my store")) {
            driver.waitForElementVisible(makeMyStoreResultButtons);
            elements = webDriver.findElements(makeMyStoreResultButtons);
        } else if (action.equalsIgnoreCase("Read reviews")) {
            driver.waitForClassPresent(driver.getByValue(CommonActions.storeReadReviewsBy), Constants.DEFAULT_SEC_WAIT);
            elements = webDriver.findElements(CommonActions.storeReadReviewsBy);
        } else if (action.equalsIgnoreCase("Directions")) {
            driver.waitForElementClickable(directionsLinks);
            elements = webDriver.findElements(directionsLinks);
        } else if (action.equalsIgnoreCase("Send to phone")) {
            driver.waitForClassPresent(driver.getByValue(sendToPhoneResultLinks), Constants.DEFAULT_SEC_WAIT);
            elements = webDriver.findElements(sendToPhoneResultLinks);
        } else {
            driver.waitForElementVisible(scheduleApptResultButtons);
            elements = webDriver.findElements(scheduleApptResultButtons);
        }

        if (elements.size() > 0) {
            if (action.equalsIgnoreCase("Make this my store")) {
                //TODO: Note - if current store appears in the results, the selection instead of being by store position
                //(con't) in results will be by offset by one (due to only looking for result items containing
                //(con't) "Make My Store" button). Work-around tries to click "Make this my store" button for desired
                //(con't) result item position, if that fails, it will instead click the first available
                //(con't) "Make this my store" button
                if (driver.isElementDisplayed(currentStoreActive)) {
                    try {
                        driver.jsScrollToElement(elements.get(Integer.parseInt(storeResultNumber)));
                        elements.get(Integer.parseInt(storeResultNumber)).click();
                    } catch (Exception e) {
                        driver.jsScrollToElement(elements.get((0)));
                        elements.get(0).click();
                    }
                }
            } else if (action.equalsIgnoreCase("Schedule appointment")) {
                driver.jsScrollToElement(elements.get(Integer.parseInt(storeResultNumber) - 1));
                elements.get(Integer.parseInt(storeResultNumber) - 1).click();
            } else {
                driver.jsScrollToElement(elements.get(Integer.parseInt(storeResultNumber)));
                elements.get(Integer.parseInt(storeResultNumber)).click();
            }

            //TODO: retest when new safaridriver is stable
            if (Config.isSafari() || Config.isFirefox())
                driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

            commonActions.checkForWelcomePopup();
        } else {
            Assert.fail("FAIL: No store location results were found!");
        }
        LOGGER.info("selectionActionForResultItem completed action: " + action + " for store #" + storeResultNumber);
    }

    /**
     * Closes the Welcome Pop-Up by either clicking Continue or Search for another store link.
     *
     * @param action Search OR Close
     */
    public void interactWithStorePopUp(String action) {
        LOGGER.info("interactWithStorePopUp started with action: " + action);
        if (action.equalsIgnoreCase(ConstantsDtc.CONTINUE)) {
            welcomePopUpSearchLink.click();
        } else {
            welcomePopUpContinueButton.click();
        }
        LOGGER.info("interactWithStorePopUp completed with action: " + action);
    }

    /**
     * Verifies the Current Store text color is blue
     */
    public void assertCurrentStoreTextColor() {
        LOGGER.info("assertCurrentStoreTextColor started");
        String color = currentStoreActive.getCssValue(Constants.COLOR);
        Assert.assertTrue("FAIL: Expected text property to be CURRENT STORE! Actual: " +
                currentStoreActive.getText(), currentStoreActive.getText().equalsIgnoreCase(CURRENT_STORE_LABEL));
        Assert.assertTrue("FAIL: Expected Current Store text color: " + CURRENT_STORE_BLUE +
                        " but the actual color was: " + color + "!",
                color.equalsIgnoreCase(CURRENT_STORE_BLUE));
        LOGGER.info("assertCurrentStoreTextColor completed");
    }

    /**
     * Takes input of a store name and adds region data to a string
     *
     * @return boolean Whether My Store is already selected
     */
    public boolean isMyStoreSelected() {
        LOGGER.info("isMyStoreSelected started");
        boolean myStoreSelected = false;
        if (driver.isElementDisplayed(makeThisMyStoreBy, Constants.ONE_SEC_WAIT))
            myStoreSelected = true;
        LOGGER.info("isMyStoreSelected completed");
        return myStoreSelected;
    }

    /**
     * Assert Schedule Appointment Option is not available on on store locator page DTD site
     */
    public void assertScheduleAppointmentOptionNotAvailableOnDTDStoreLocatorPage() {
        LOGGER.info("assertScheduleAppointmentOptionNotAvailableOnDTDStoreLocatorPage started");
        driver.waitForElementVisible(storeLocatorSearchButton);
        if (driver.isTextPresentInPageSource(SCHEDULE_APPOINTMENT_LABEL)
                || driver.isElementDisplayed(scheduleApptResultButtons)) {
            Assert.fail("Schedule Appointment option is available to user on store locator page DTD site ");
        } else {
            LOGGER.info("Confirmed: Schedule Appointment option was not displayed on store locator page");
        }
        LOGGER.info("assertScheduleAppointmentOptionNotAvailableOnDTDStoreLocatorPage completed");
    }

    /***
     * Verifies the listed "HOURS OF OPERATION" for each store location result item matches the expected Discount Tire
     * standard
     */
    public void verifyHoursOfOperationForStoreSearchResults() {
        LOGGER.info("verifyHoursOfOperationForStoreSearchResults started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(scheduleApptResultButtons);
        List<WebElement> resultsHoursOpList = webDriver.findElements(hoursOpText);
        for (WebElement result : resultsHoursOpList) {
            for (String hoursOpCombination : ConstantsDtc.STORE_RESULTS_HOURS_OPERATION) {
                Assert.assertTrue("FAIL: The current result item's day and hours combo: \""
                                + result.getText() + "\" did NOT contain \"" + hoursOpCombination + "\"!",
                        result.getText().contains(hoursOpCombination));
            }
        }
        LOGGER.info("verifyHoursOfOperationForStoreSearchResults completed");
    }

    /***
     * Clicks the store link in store search results that contains passed text
     * @param storeText The text of the store link (e.g. AZF 01 or CAN 19) to click in order to load the store details
     */
    public void selectStoreForStoreDetails(String storeText) {
        LOGGER.info("selectStoreForStoreDetails started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(scheduleApptResultButtons);
        driver.clickElementWithText(storeNameInStoreLocatorResults, storeText);
        LOGGER.info("selectStoreForStoreDetails completed");
    }
}