package dtc.pages;

import common.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtils;
import utilities.Driver;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import common.Config;
import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Collin Reed on 3/26/18.
 */
public class SolicitedReviewPage {

    private Driver driver;
    private WebDriver webDriver;
    private final CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(SolicitedReviewPage.class.getName());
    // create Collections from the arrays that can be modified by remove and add methods
    private static Collection<String> productErrorListItems = new ArrayList<>();
    private static Collection<String> productPageErrorHeadingTitles = new ArrayList<>();
    private static Collection<String> storeErrorListItems = new ArrayList<>();
    private static Collection<String> storePageErrorHeadingTitles = new ArrayList<>();

    public SolicitedReviewPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
        initializeErrorArrayLists();
    }

    private static final String STORE_REVIEW_HEADER_MESSAGE_START = "Tell us what you think about our";
    private static final String PRODUCT_REVIEW_HEADER_MESSAGE_START = "Tell us what you think about the";
    private static final String HEADER_MESSAGE_END = "store";
    private static final String HI = "Hi";
    private static final String ALL_FIELDS_REQUIRED_UNLESS_OPTIONAL = "All fields required unless marked as optional.";
    private static final String WOULD_YOU_RECOMMEND_STORE = "Would You Recommend This Store?";
    private static final String WOULD_YOU_RECOMMEND_PRODUCT = "Would you recommend this product to friends and family?";
    private static final String COMMENTS_ABOUT_EXPERIENCE = "Comments About Your Experience";
    private static final String CHARACTERS_REMAINING = "characters remaining";
    private static final String YES_I_AGREE_TO = "Yes, I agree to";
    private static final String TERMS_AND_CONDITIONS = "Terms & Conditions";
    private static final String CUSTOMER_RATINGS_AND_REVIEWS_TERMS_OF_USE = "CUSTOMER RATINGS AND REVIEWS TERMS OF USE";
    private static final String PLEASE_PROVIDE_FEEDBACK_FOR_THE_FOLLOWING = "Please provide feedback for the following:";
    private static final String[] STORE_RATING_HEADERS =
            {"Employee Knowledge/Friendliness", "Store Cleanliness", "Overall Rating"};
    private static final String[] PRODUCT_RATING_HEADINGS =
            {"RIDE COMFORT", "CORNERING & STEERING", "RIDE NOISE", "TREAD LIFE", "DRY TRACTION", "WET TRACTION",
                    "WINTER WEATHER TRACTION (SNOW & ICE)"};
    private static final String[] STORE_RATING_HEADINGS =
            {"Employee Knowledge/Friendliness", "Store Cleanliness", "Overall Rating"};
    private static final String PROPER_ZIP_CODE_OR_POSTAL_CODE = "Proper zip code or postal code";
    private static final String MILES_DRIVEN_SHOULD_BE = "Miles driven should be between 1 mile and 150000 miles";
    private static final String FIVE_DIGIT_ZIP_CODE_REQUIRED = "5 digit zip code required";
    private static final String PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER = "Please type a number between 1 and 150000";
    private static final String GENERIC_STORE_CODE = "9999";
    private static final String STORE_ADDRESS = Config.getDefaultStoreAddress();
    private static final String STORE_PHONE_NUMBER = Config.getDefaultStorePhoneNumber();
    private static final String STORE = Config.getDefaultStore();
    private static final String STORE_TYPE = Config.getStoreType();
    private static final String FIFTY_CHARACTERS = "01234567890123456789012345678901234567890123456789";
    private static final String ONE_STAR = "one";
    private static final String TWO_STAR = "two";
    private static final String THREE_STAR = "three";
    private static final String FOUR_STAR = "four";
    private static final String FIVE_STAR = "five";
    private static final String STAR_RATINGS = "%s-star-ratings";
    private static final String ATTRIBUTE_FOR = "for";

    private static final String[] DRIVING_CONDITIONS_ARRAY = {ConstantsDtc.MOSTLY_DRY, ConstantsDtc.MIXED_DRY_WET,
            ConstantsDtc.MOSTLY_WET, ConstantsDtc.SNOW};

    private static final String[] TYPE_OF_DRIVING_ARRAY = {ConstantsDtc.MOSTLY_HIGHWAY, ConstantsDtc.MIXED_HIGHWAY_CITY,
            ConstantsDtc.MOSTLY_CITY, ConstantsDtc.OFF_ROAD, ConstantsDtc.RACE};

    private static final String[] PRODUCT_REVIEW_REQUIRED_FIELD_ERRORTEXT_ARRAY = {ConstantsDtc.RIDE_COMFORT,
            ConstantsDtc.CORNERING_AND_STEERING, ConstantsDtc.RIDE_NOISE, ConstantsDtc.TREAD_LIFE,
            ConstantsDtc.DRY_TRACTION, ConstantsDtc.PRODUCT_RECOMMENDATION, ConstantsDtc.ZIP_CODE,
            ConstantsDtc.MILES_DRIVEN, ConstantsDtc.DRIVING_CONDITIONS, ConstantsDtc.TYPE_OF_DRIVING,
            ConstantsDtc.TERMS_AND_CONDITIONS};

    private static final String[] STORE_REVIEW_REQUIRED_FIELD_ERRORTEXT_ARRAY = {
            ConstantsDtc.EMPLOYEE_KNOWLEDGE_FRIENDLINESS,
            ConstantsDtc.STORE_CLEANLINESS, ConstantsDtc.OVERALL_RATING, ConstantsDtc.STORE_RECOMMENDATION,
            ConstantsDtc.GENERAL_COMMENTS, ConstantsDtc.TERMS_AND_CONDITIONS};

    private List<String> starRatingsList = new ArrayList<>(Arrays.asList(
            ONE_STAR, TWO_STAR, THREE_STAR, FOUR_STAR, FIVE_STAR));

    @FindBy(css = ".review-title > h2")
    public static WebElement header;

    @FindBy(css = ".review-title > h2 > span")
    public static WebElement hiCustomer;

    @FindBy(css = ".review-title > p")
    public static WebElement allFieldsRequiredOrOptionalBy;

    @FindBy(className = "submit-review__rate--store")
    public static WebElement storeRatingSection;

    @FindBy(className = "submit-review__rate")
    public static WebElement ratingSection;

    @FindBy(className = "termsAndConditions")
    public static WebElement modalRatingsAndReviewsTermsOfUse;

    @FindBy(className = "errorList")
    public static WebElement modalErrorList;

    @FindBy(className = "submit-review__review__comment")
    public static WebElement commentsTextArea;

    @FindBy(className = "submit-review__review__terms")
    public static WebElement termsAndConditions;

    @FindBy(css = ".submit-review__review__terms > .reviewQuestion > .agreeTermsLabel > span")
    public static WebElement termsAndConditionsCheckBox;

    @FindBy(className = "showTC")
    public static WebElement getTermsAndConditionsLink;

    @FindBy(id = "answerDatazipcode")
    public static WebElement zipCodeField;

    @FindBy(id = "answerDatamilesdriven")
    public static WebElement milesDrivenField;

    @FindBy(className = "submit-review__recommend__yes")
    public static WebElement yesButton;

    @FindBy(className = "submit-review__recommend__no")
    public static WebElement noButton;

    @FindBy(className = "results")
    public static WebElement storeSuccessfulSubmissionMessage;

    @FindBy(className = "submit-review")
    public static WebElement productSuccessfulSubmissionMessage;

    @FindBy(className = "react-selectize-toggle-button-container")
    public static WebElement sortByDropdown;

    @FindBy(css = ".simple-value>span")
    public static WebElement storeReviewSimpleValue;

    @FindBy(css = ".detail__reviews__sort  span")
    public static WebElement productReviewSimpleValue;

    @FindBy(className = "detail__reviews__filter__ratings")
    public static WebElement filterReviewsByRatingContainer;

    @FindBy(className = "react-component__container")
    public static WebElement reviewsReactContainer;

    private static final By ratingQuestionsBy = By.className("reviewQuestion");

    private static final By storeLogoBy = By.cssSelector(".submit-review__info__display > img");

    private static final By storeNameBy = By.cssSelector(".submit-review__info__details > H3");

    private static final By storeDetailsBy = By.cssSelector(".submit-review__info__details > p");

    private static final By storeHoursBy = By.className("submit-review__info__tips");

    private static final By storeInfoSectionBy = By.className("submit-review__info");

    private static final By characterLimitBy = By.className("submit-review__character-limit");

    private static final By modalCloseButtonBy = By.cssSelector(".modal-review-box > a.closeModal");

    private static final By errorFixListItemBy = By.cssSelector(".errorFixList > li");

    private static final By reviewQuestionsAndCommentsBy = By.className("submit-review__review");

    private static final By submitReviewButtonBy = By.className("submit-review__button");

    private static final By fiveStarsBy = By.className("fivestar-rating");

    private static final By starBy = By.className("fivestar-rating__star");

    private static final By errorMessageBy = By.className("error-msg");

    private static final By listBoxBy = By.cssSelector(".submit-review__review > .reviewQuestion > .chosen-container");

    private static final By getTermsAndConditionsLinkBy = By.className("showTC");

    private static final By listItemBy = By.className("active-result");

    private static final By prosConsTextAreaBy = By.className("submit-review__pros-cons");

    private static final By prosTextAreaBy = By.id("answerDatapros");

    private static final By consTextAreaBy = By.id("answerDatacons");

    private static final By storeReviewDateBy = By.cssSelector(".detail__reviews__review__info__reviewer > [class='detail__reviews__review__info__reviewer__details']:not(:empty)");

    private static final By detailReviewsSortBy = By.cssSelector(".simple-option>span");

    private static final By storeDetailsNoReviewsMessageBy = By.cssSelector(".detail__reviews__overall>p");

    private static final By myStoreNoReviewsMessageBy = By.cssSelector(".my-store__rating>p");

    private static final By productReviewDateBy = By.cssSelector(".detail__reviews__review__info__reviewer :nth-child(2)");

    private static final By reviewScoresBy = By.className("review-score");

    private static final By facetInputLabelBy = By.className("facetLabelForInput");

    private static final By inputCheckboxBy = By.className("input__checkbox");

    public void initializeErrorArrayLists() {
        productErrorListItems = new ArrayList(Arrays.asList(PRODUCT_REVIEW_REQUIRED_FIELD_ERRORTEXT_ARRAY));
        productPageErrorHeadingTitles = new ArrayList(Arrays.asList(PRODUCT_REVIEW_REQUIRED_FIELD_ERRORTEXT_ARRAY));
        storeErrorListItems = new ArrayList(Arrays.asList(STORE_REVIEW_REQUIRED_FIELD_ERRORTEXT_ARRAY));
        storePageErrorHeadingTitles = new ArrayList(Arrays.asList(STORE_REVIEW_REQUIRED_FIELD_ERRORTEXT_ARRAY));
    }

    /**
     * Navigate to the Store Review page
     */
    public void navigateToStoreReviewPage() {
        LOGGER.info("navigateToStoreReviewPage started");
        if (!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            commonActions.navigateToPage("/en" + Config.getDefaultStoreCodeURL() +
                    ConstantsDtc.solicitedReviewPartialURL.get(Constants.STORE));
        } else {
            Assert.fail("FAIL: DTD does not have store reviews");
        }
        LOGGER.info("navigateToStoreReviewPage completed");
    }

    /**
     * Navigate to the Product Review page using actual vehicle information
     *
     * @param year      - Vehicle year
     * @param make      - Vehicle make
     * @param model     - Vehicle model
     * @param productId - Product ID
     */
    public void navigateToProductReviewPage(String year, String make, String model, String productId) {
        LOGGER.info("navigateToProductReviewPage started");
        String storeCode = GENERIC_STORE_CODE;
        if (!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            storeCode = Config.getDefaultStoreCode();
        }
        commonActions.navigateToPage("/review/product/solicited?year=" + year + "&make=" + make + "&model=" + model +
                ConstantsDtc.solicitedReviewPartialURL.get(Constants.PRODUCT) + productId + "&storeName=" + storeCode);
        LOGGER.info("navigateToProductReviewPage completed");
    }

    /**
     * Navigate to the Product Review page using vehicle ID
     *
     * @param vehicleId - Vehicle ID
     * @param productId - Product ID
     */
    public void navigateToProductReviewPage(String vehicleId, String productId) {
        LOGGER.info("navigateToProductReviewPage started");
        String storeCode = GENERIC_STORE_CODE;
        if (!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            storeCode = Config.getDefaultStoreCode();
        }
        commonActions.navigateToPage("/review/product/solicited?vid=" + vehicleId +
                ConstantsDtc.solicitedReviewPartialURL.get(Constants.PRODUCT) + productId + "&storeName=" + storeCode);
        LOGGER.info("navigateToProductReviewPage completed");
    }

    /**
     * Verify the store logo is in the store info section
     */
    public void verifyStoreLogoInStoreInfoSection() {
        LOGGER.info("verifyStoreLogoInStoreInfoSection started");
        WebElement image = webDriver.findElement(storeInfoSectionBy).findElement(storeLogoBy);
        String expectedImagePath = Config.getSolicitedReviewStoreImageUrl();
        Assert.assertTrue("FAIL:  The expected image (" + image.getAttribute(Constants.ATTRIBUTE_SRC)
                        + ") was not displayed!",
                image.getAttribute(Constants.ATTRIBUTE_SRC).equals(expectedImagePath));
        LOGGER.info("verifyStoreLogoInStoreInfoSection completed");
    }

    /**
     * Verify the store name, address, and phone number are in the store info section
     */
    public void verifyStoreDetailsInStoreInfoSection() {
        LOGGER.info("verifyStoreDetailsInStoreInfoSection started");
        WebElement storeInfo = webDriver.findElement(storeInfoSectionBy);
        String displayedStoreName = storeInfo.findElement(storeNameBy).getText();
        String displayedStoreDetails = storeInfo.findElement(storeDetailsBy).getText();
        String[] details = displayedStoreDetails.split("\n");
        String displayedPhoneNumber = details[details.length - 1];
        String expectedStoreName = Config.getStoreName();
        String displayedStoreAddress = "";
        for (int i = 0; i < details.length - 1; i++) {
            displayedStoreAddress = displayedStoreAddress + details[i] + " ";
        }
        Assert.assertTrue("The expected store, " + expectedStoreName + ", was not displayed",
                displayedStoreName.equals(expectedStoreName));
        Assert.assertTrue("FAIL:  The expected store address (" + STORE_ADDRESS + ") was not displayed!",
                displayedStoreAddress.replace(",", "").replaceAll("\\s+", "").trim().
                        equals(STORE_ADDRESS.replace(",", "").replaceAll("\\s+", "").trim()));
        Assert.assertTrue("FAIL:  The expected phone number (" + STORE_PHONE_NUMBER + ") was not displayed!",
                displayedPhoneNumber.equals(STORE_PHONE_NUMBER));
        LOGGER.info("verifyStoreDetailsInStoreInfoSection completed");
    }

    /**
     * Verify the store hours are in the store info section
     */
    public void verifyStoreHoursInStoreInfoSection() {
        LOGGER.info("verifyStoreHoursInStoreInfoSection started");
        WebElement hours = webDriver.findElement(storeInfoSectionBy).findElement(storeHoursBy);
        String displayedHours = hours.getText();
        displayedHours = displayedHours.replace(Constants.STORE_HOURS + "\n", "").trim();
        String[] hoursText = displayedHours.split("\n");
        for (int i = 0; i < ConstantsDtc.STORE_DETAILS_STORE_HOURS.size(); i++) {
            Assert.assertTrue("FAIL:  The expected store hours (" + ConstantsDtc.STORE_DETAILS_STORE_HOURS.get(i)
                            + ") were not displayed!",
                    hoursText[i].equals(ConstantsDtc.STORE_DETAILS_STORE_HOURS.get(i).replace(" - ", "-")));
        }
        LOGGER.info("verifyStoreHoursInStoreInfoSection completed");
    }

    /**
     * Verify expected Store Review page header text
     */
    public void verifyStoreReviewPageHeaderText() {
        LOGGER.info("verifyStoreReviewPageHeaderText started");
        String expectedStoreName = Config.getStoreName();
        String expectedHeaderMessage = STORE_REVIEW_HEADER_MESSAGE_START + " " +
                expectedStoreName + " " + HEADER_MESSAGE_END;
        String currentUrl = webDriver.getCurrentUrl();
        String hiMessage = hiCustomer.getText();
        String headerMessage = header.getText().replace(hiMessage, "").trim();
        Assert.assertTrue("FAIL:  The expected header message:  '" + expectedHeaderMessage + "' was not displayed!",
                headerMessage.equals(expectedHeaderMessage));
        String[] urlText = currentUrl.split("firstName=");
        String expectedHiMessage = HI + " " + urlText[urlText.length - 1].split("&")[0] + ",";
        Assert.assertTrue("FAIL:  The expected hi customer message:  '" + expectedHiMessage + "' was not displayed!",
                hiMessage.equals(expectedHiMessage));
        LOGGER.info("verifyStoreReviewPageHeaderText completed");
    }

    /**
     * Verify expected Product Review page header text
     */
    public void verifyProductReviewPageHeaderText(String productName) {
        LOGGER.info("verifyProductReviewPageHeaderText started");
        String expectedHeaderMessage = PRODUCT_REVIEW_HEADER_MESSAGE_START + " " + productName;
        String headerMessage = header.getText().trim();
        Assert.assertTrue("FAIL:  The expected header message:  '" + expectedHeaderMessage + "' was not displayed!",
                headerMessage.equals(expectedHeaderMessage));
        LOGGER.info("verifyProductReviewPageHeaderText completed");
    }

    /**
     * Verify the 'All fields required unless marked optional' message displayed
     */
    public void verifyAllFieldsRequiredUnlessOptionalMessage() {
        LOGGER.info("verifyAllFieldsRequiredUnlessOptionalMessage started");
        Assert.assertTrue("FAIL:  The expected message '"
                        + ALL_FIELDS_REQUIRED_UNLESS_OPTIONAL + "' was not displayed!",
                allFieldsRequiredOrOptionalBy.getText().equals(ALL_FIELDS_REQUIRED_UNLESS_OPTIONAL));
        LOGGER.info("verifyAllFieldsRequiredUnlessOptionalMessage completed");
    }

    /**
     * Verify the store rating headings
     */
    public void verifyStoreRatingHeadings() {
        LOGGER.info("verifyStoreRatingHeadings started");
        List<WebElement> storeRatingCategories = storeRatingSection.findElements(ratingQuestionsBy);
        for (int i = 0; i < storeRatingCategories.size(); i++) {
            String displayedRatingsHeader = storeRatingCategories.get(i).getText();
            Assert.assertTrue("FAIL:  The expected store rating header, '"
                            + STORE_RATING_HEADERS[i] + "' was not displayed!",
                    STORE_RATING_HEADERS[i].equalsIgnoreCase(displayedRatingsHeader));
        }
        LOGGER.info("verifyStoreRatingHeadings completed");
    }

    /**
     * Verify 'Would you recommend...' question is displayed with YES and NO buttons
     */
    public void verifyWouldYouRecommendYesNo(String reviewType) {
        LOGGER.info("verifyWouldYouRecommendYesNo started");
        String expectedQuestion;
        boolean found = false;

        if (reviewType.equalsIgnoreCase(Constants.STORE)) {
            expectedQuestion = WOULD_YOU_RECOMMEND_STORE;
        } else {
            expectedQuestion = WOULD_YOU_RECOMMEND_PRODUCT;
        }

        List<WebElement> ratingQuestions = webDriver.findElement(reviewQuestionsAndCommentsBy).
                findElements(ratingQuestionsBy);

        for (WebElement ratingQuestion : ratingQuestions) {
            String text = ratingQuestion.getText();
            if (text.contains(expectedQuestion)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("FAIL:  The expected review question, '" + expectedQuestion + "' was not displayed!", found);
        Assert.assertTrue("FAIL:  The '" + Constants.YES + "' button was not displayed!",
                driver.isElementDisplayed(yesButton));
        Assert.assertTrue("FAIL:  The '" + Constants.NO + "' button was not displayed!",
                driver.isElementDisplayed(noButton));
        LOGGER.info("verifyWouldYouRecommendYesNo completed");
    }

    /**
     * Verify 'Comments About Your Experience' is displayed
     */
    public void verifyCommentsAboutYourExperienceLabelDisplayed() {
        LOGGER.info("verifyCommentsAboutYourExperience started");
        WebElement commentsSection = webDriver.findElement(reviewQuestionsAndCommentsBy).
                findElements(ratingQuestionsBy).get(1);
        String commentsAboutExperience = commentsSection.getText().split("\n")[0];
        Assert.assertTrue("FAIL:  The heading '" + COMMENTS_ABOUT_EXPERIENCE + "' was not displayed!",
                commentsAboutExperience.equals(COMMENTS_ABOUT_EXPERIENCE));
        LOGGER.info("verifyCommentsAboutYourExperience completed");
    }

    /**
     * Verify specified text area is displayed with text limit
     *
     * @param textBoxName    - The name of the text box: 'Pros', 'Cons', or 'Comments'
     * @param characterLimit - The expected character limit for the text box
     */
    public void verifyTextAreaDisplayedWithTextLimit(String textBoxName, int characterLimit) {
        LOGGER.info("verifyTextAreaDisplayedWithTextLimit started with '" + textBoxName + "' text box");
        List<WebElement> characterLimitMessages = webDriver.findElements(characterLimitBy);
        WebElement characterLimitMessage = null;
        WebElement textBoxArea = null;
        List<WebElement> prosConsTextAreas = webDriver.findElements(prosConsTextAreaBy);
        switch (textBoxName.toUpperCase()) {
            case "PROS":
                characterLimitMessage = characterLimitMessages.get(0);
                textBoxArea = prosConsTextAreas.get(0).findElement(prosTextAreaBy);
                break;
            case "CONS":
                characterLimitMessage = characterLimitMessages.get(1);
                textBoxArea = prosConsTextAreas.get(1).findElement(consTextAreaBy);
                break;
            case "COMMENTS":
                characterLimitMessage = characterLimitMessages.get(characterLimitMessages.size() - 1);
                textBoxArea = commentsTextArea;
                break;
        }

        Assert.assertTrue("FAIL:  The '" + textBoxName + "' text box was not displayed!",
                driver.isElementDisplayed(textBoxArea));
        Assert.assertTrue("FAIL:  " + String.valueOf(characterLimit) +
                        " " + CHARACTERS_REMAINING + " was not displayed!",
                driver.isElementDisplayed(characterLimitMessage) && characterLimitMessage.getText().
                        equals(String.valueOf(characterLimit) + " " + CHARACTERS_REMAINING));

        textBoxArea.clear();
        int loopIterationLimit = characterLimit / FIFTY_CHARACTERS.length();
        for (int i = 0; i < loopIterationLimit; i++) {
            textBoxArea.sendKeys(FIFTY_CHARACTERS);
        }

        Assert.assertTrue("FAIL:  " + Constants.ZERO + " was not displayed after filling the '" + textBoxName +
                        "' text box with the maximum number of characters!",
                driver.isElementDisplayed(characterLimitMessage) && characterLimitMessage.getText().
                        equals(String.valueOf(Constants.ZERO) + " " + CHARACTERS_REMAINING));
        LOGGER.info("verifyTextAreaDisplayedWithTextLimit completed with '" + textBoxName + "' text box'");
    }

    /**
     * Verify checkbox with 'Yes, I agree to Discount Tire's/America's Tire's Terms and Conditions' displayed
     * and that the "Terms & Conditions" portion of the text is a link.
     */
    public void verifyTermsAndConditions() {
        LOGGER.info("verifyTermsAndConditions started");
        // TODO:  This will fail for America's Tire stores due to defect 9508.  The text says
        // TODO (cont'd):  'Yes, I agree to Discount Tire's Terms & Conditions' even when it is an America's Tire store.
        String storeText = STORE_TYPE.replace(" Store", "'s");
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            storeText = "the " + ConstantsDtc.DISCOUNT_TIRE_DIRECT;
        }
        String expectedText = YES_I_AGREE_TO + " " + storeText + " " + TERMS_AND_CONDITIONS;
        Assert.assertTrue("FAIL:  The agree to Terms & Conditions message was not displayed!",
                termsAndConditions.getText().contains(expectedText));
        Assert.assertTrue("FAIL:  The agree to Terms & Conditions checkbox was not displayed!",
                driver.isElementDisplayed(termsAndConditionsCheckBox));
        if (!driver.isElementDisplayed(termsAndConditions.findElement(getTermsAndConditionsLinkBy))) {
            Assert.fail("FAIL:  The Terms & Conditions link was not displayed!");
        }
        LOGGER.info("verifyTermsAndConditions completed");
    }

    /**
     * Click the Terms and Conditions link
     */
    public void clickTermsAndConditionsLink() {
        LOGGER.info("clickTermsAndConditionsLink started");
        getTermsAndConditionsLink.click();
        LOGGER.info("clickTermsAndConditionsLink completed");
    }

    /**
     * Verify the 'Customer Ratings and Review Terms of Use' modal pop-up window displayed
     */
    public void verifyCustomerRatingsAndReviewTermsOfUseModalPopupDisplayed() {
        LOGGER.info("verifyCustomerRatingsAndReviewTermsOfUseModalPopupDisplayed started");
        Assert.assertTrue("FAIL:  The " + CUSTOMER_RATINGS_AND_REVIEWS_TERMS_OF_USE
                        + " modal popup window did not display!",
                driver.isElementDisplayed(modalRatingsAndReviewsTermsOfUse) &&
                        modalRatingsAndReviewsTermsOfUse.getText().contains(CUSTOMER_RATINGS_AND_REVIEWS_TERMS_OF_USE));
        LOGGER.info("verifyCustomerRatingsAndReviewTermsOfUseModalPopupDisplayed completed");
    }

    /**
     * Close the 'Customer Ratings and Review Terms of Use' modal pop-up window
     */
    public void closeCustomerRatingsAndReviewTermsOfUseModalPopup() {
        LOGGER.info("closeCustomerRatingsAndReviewTermsOfUseModalPopup started");
        modalRatingsAndReviewsTermsOfUse.findElement(modalCloseButtonBy).click();
        Assert.assertTrue("FAIL:  The " + CUSTOMER_RATINGS_AND_REVIEWS_TERMS_OF_USE
                        + " modal popup window remained displayed!",
                !driver.isElementDisplayed(modalRatingsAndReviewsTermsOfUse));
        LOGGER.info("closeCustomerRatingsAndReviewTermsOfUseModalPopup completed");
    }

    /**
     * Verify the Submit Review Button displayed
     */
    public void verifySubmitReviewButtonDisplayed() {
        LOGGER.info("verifySubmitReviewButtonDisplayed started");
        Assert.assertTrue("FAIL:  The Submit Review button was not displayed!",
                driver.isElementDisplayed(webDriver.findElement(reviewQuestionsAndCommentsBy).
                        findElement(submitReviewButtonBy)));
        LOGGER.info("verifySubmitReviewButtonDisplayed completed");
    }

    /**
     * Click the Submit Review Button
     */
    public void clickSubmitReviewButton() {
        LOGGER.info("clickSubmitReviewButton started");
        webDriver.findElement(reviewQuestionsAndCommentsBy).findElement(submitReviewButtonBy).click();
        LOGGER.info("clickSubmitReviewButton completed");
    }

    /**
     * Verify the Error List modal pop-up displayed
     */
    public void verifyErrorListModalPopupDisplayed() {
        LOGGER.info("verifyErrorListModalPopupDisplayed started");
        Assert.assertTrue("FAIL:  The Error List modal popup window did not display!",
                driver.isElementDisplayed(modalErrorList) &&
                        modalErrorList.getText().contains(PLEASE_PROVIDE_FEEDBACK_FOR_THE_FOLLOWING));
        LOGGER.info("verifyErrorListModalPopupDisplayed completed");
    }

    /**
     * Close the Error List modal pop-up window
     */
    public void closeErrorListModalPopup() {
        LOGGER.info("closeErrorListModalPopup started");
        modalErrorList.findElement(modalCloseButtonBy).click();
        Assert.assertTrue("FAIL:  The Error List modal popup window remained displayed!",
                !driver.isElementDisplayed(modalErrorList));
        LOGGER.info("closeErrorListModalPopup completed");
    }

    /**
     * Verify the product rating category headers and that there are five stars next to each one
     */
    public void verifyProductRatingCategoriesDisplayedWithFiveStars() {
        LOGGER.info("verifyProductRatingCategoriesDisplayedWithFiveStars started");
        List<WebElement> productRatingCategories = ratingSection.findElements(ratingQuestionsBy);
        for (int i = 0; i < productRatingCategories.size(); i++) {
            String displayedRatingsHeader = productRatingCategories.get(i).findElement(CommonActions.headerFourthBy).getText();
            Assert.assertTrue("FAIL:  The expected store rating header, '"
                            + PRODUCT_RATING_HEADINGS[i] + "' was not displayed!",
                    PRODUCT_RATING_HEADINGS[i].equalsIgnoreCase(displayedRatingsHeader));
            WebElement starRating = productRatingCategories.get(i).findElement(fiveStarsBy);
            List<WebElement> ratingStars = starRating.findElements(SolicitedReviewPage.starBy);
            Assert.assertTrue("FAIL:  There were not five stars displayed for , '"
                    + PRODUCT_RATING_HEADINGS[i] + "' product rating!", ratingStars.size() == 5);
        }
        LOGGER.info("verifyProductRatingCategoriesDisplayedWithFiveStars completed");
    }

    /**
     * Verify the Error popup dialog contains a specified item.
     *
     * @param targetItem - The item being verified is displayed in the pop-up dialog
     * @param exclusive  - whether or not the target item is the only item expected to be displayed
     */
    public void verifyErrorPopUpListItem(String targetItem, boolean exclusive) {
        LOGGER.info("verifyErrorPopUpListItem started");
        boolean found = false;
        if (productErrorListItems.contains(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER)) {
            productErrorListItems.remove(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER);
            productErrorListItems.add(MILES_DRIVEN_SHOULD_BE);
        }
        if (productErrorListItems.contains(FIVE_DIGIT_ZIP_CODE_REQUIRED)) {
            productErrorListItems.remove(FIVE_DIGIT_ZIP_CODE_REQUIRED);
            productErrorListItems.add(PROPER_ZIP_CODE_OR_POSTAL_CODE);
        }
        List<WebElement> ratingCategories = webDriver.findElements(errorFixListItemBy);
        for (WebElement ratingCategory : ratingCategories) {
            if (ratingCategory.getText().trim().equals(targetItem)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("FAIL: The expected item, '" + targetItem +
                "' was not displayed in the Error List popup!", found);
        if (exclusive) {
            Assert.assertTrue("FAIL: '" + targetItem + "' was not the only item displayed in the Error List popup!",
                    ratingCategories.size() == 1);
        }
        LOGGER.info("verifyErrorPopUpListItem completed");
    }

    /**
     * Verify the Error popup dialog contains all expected items.
     *
     * @param reviewType - Type of review "store" or "product"
     */
    public void verifyErrorPopUpListItems(String reviewType) {
        LOGGER.info("verifyErrorPopUpListItems started");
        ArrayList<String> errorListItems;

        if (reviewType.equalsIgnoreCase(Constants.STORE)) {
            errorListItems = new ArrayList(Arrays.asList(storeErrorListItems.toArray()));
        } else {
            if (productErrorListItems.contains(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER)) {
                productErrorListItems.remove(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER);
                productErrorListItems.add(MILES_DRIVEN_SHOULD_BE);
            }
            if (productErrorListItems.contains(FIVE_DIGIT_ZIP_CODE_REQUIRED)) {
                productErrorListItems.remove(FIVE_DIGIT_ZIP_CODE_REQUIRED);
                productErrorListItems.add(PROPER_ZIP_CODE_OR_POSTAL_CODE);
            }
            errorListItems = new ArrayList(Arrays.asList(productErrorListItems.toArray()));
        }

        driver.waitForElementVisible(errorFixListItemBy);
        List<WebElement> ratingCategories = webDriver.findElements(errorFixListItemBy);
        for (String errorListItem : errorListItems) {
            boolean found = false;
            for (WebElement ratingCategory : ratingCategories) {
                if (ratingCategory.getText().trim().equals(errorListItem)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue("FAIL:  The expected item, '" + errorListItem +
                    "' was not displayed in the Error List popup!", found);
        }

        for (WebElement ratingCategory : ratingCategories) {
            String displayedItem = ratingCategory.getText().trim();
            Assert.assertTrue("FAIL:  Unexpected item, '" + displayedItem +
                    "' was displayed in the Error List popup!", errorListItems.contains(displayedItem));
        }
        LOGGER.info("verifyErrorPopUpListItems completed");
    }

    /**
     * Rate a category with a specified number of stars
     *
     * @param category     - Category name
     * @param selectedStar - Number of stars to give
     */
    public void selectProductCategoryRating(String category, String selectedStar) {
        LOGGER.info("selectProductCategoryRating started");
        List<WebElement> ratingCategories = ratingSection.findElements(ratingQuestionsBy);
        for (WebElement ratingCategory : ratingCategories) {
            String displayedRatingsHeader = ratingCategory.getText();
            if (CommonUtils.containsIgnoreCase(displayedRatingsHeader, category)) {
                clickStarRating(category, ratingCategory, selectedStar);
                break;
            }
        }
        LOGGER.info("selectProductCategoryRating completed");
    }

    /**
     * Click the rating star for a specified product rating category
     *
     * @param targetCategory - The name of the category to be rated
     * @param ratingCategory - The review question WebElement that contains the star set to be clicked on
     * @param selectedStar   - The star rating to be selected between 1 and 5.  If "random", then a random star is selected.
     */
    private void clickStarRating(String targetCategory, WebElement ratingCategory, String selectedStar) {
        LOGGER.info("clickStarRating started");
        int starSelection;
        boolean found = false;
        if (selectedStar.equalsIgnoreCase(Constants.RANDOM)) {
            starSelection = CommonUtils.getRandomNumber(1, 5);
        } else {
            starSelection = Integer.parseInt(selectedStar);
        }

        WebElement starSet = ratingCategory.findElement(fiveStarsBy);
        List<WebElement> stars = starSet.findElements(starBy);
        for (WebElement singleStar : stars) {
            if (Integer.parseInt(singleStar.getAttribute(Constants.VALUE)) == starSelection) {
                singleStar.click();
                for (String fieldHeading : PRODUCT_RATING_HEADINGS) {
                    if (fieldHeading.equalsIgnoreCase(targetCategory)) {
                        cleanUpErrorLists(fieldHeading);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
                for (String fieldHeading : STORE_RATING_HEADINGS) {
                    if (fieldHeading.equalsIgnoreCase(targetCategory)) {
                        cleanUpErrorLists(fieldHeading);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        LOGGER.info("clickStarRating completed");
    }

    /**
     * Remove the items array lists corresponding to the specified product rating heading
     *
     * @param ratingHeading - The rating heading text
     */
    private void cleanUpErrorLists(String ratingHeading) {
        LOGGER.info("cleanUpErrorLists started");
        boolean found = false;
        // Check product rating headers. If found, modify the product arrays.
        String[][] requiredProductRatingHeadings = {{ConstantsDtc.RIDE_COMFORT.toUpperCase(),
                ConstantsDtc.CORNERING_APPERSAND_STEERING.toUpperCase(), ConstantsDtc.RIDE_NOISE.toUpperCase(),
                ConstantsDtc.TREAD_LIFE.toUpperCase(), ConstantsDtc.DRY_TRACTION.toUpperCase()},
                {ConstantsDtc.RIDE_COMFORT, ConstantsDtc.CORNERING_AND_STEERING,
                        ConstantsDtc.RIDE_NOISE, ConstantsDtc.TREAD_LIFE, ConstantsDtc.DRY_TRACTION}};

        for (int i = 0; i < requiredProductRatingHeadings[0].length; i++) {
            if (requiredProductRatingHeadings[0][i].equalsIgnoreCase(ratingHeading)) {
                productErrorListItems.remove(requiredProductRatingHeadings[1][i]);
                productPageErrorHeadingTitles.remove(requiredProductRatingHeadings[1][i]);
                found = true;
                break;
            }
        }

        if (!found) {
            // Check store rating headers. If found, modify the store arrays.
            String[] requiredStoreRatingHeadings = {ConstantsDtc.EMPLOYEE_KNOWLEDGE_FRIENDLINESS,
                    ConstantsDtc.STORE_CLEANLINESS, ConstantsDtc.OVERALL_RATING};

            for (String storeHeading : requiredStoreRatingHeadings) {
                if (storeHeading.equalsIgnoreCase(ratingHeading)) {
                    storeErrorListItems.remove(storeHeading);
                    storePageErrorHeadingTitles.remove(storeHeading);
                    break;
                }
            }
        }

        LOGGER.info("cleanUpErrorLists completed");
    }

    /**
     * Verify required fields and other error messages are displayed on the product review page
     */
    public void verifyProductReviewErrorMessagesOnPage() {
        LOGGER.info("verifyProductReviewErrorMessagesOnPage started");
        List<WebElement> errorMessages = webDriver.findElements(errorMessageBy);

        for (WebElement errorMessage : errorMessages) {
            String errorMessageText = errorMessage.getText().trim();
            if (errorMessageText.equals(Constants.REQUIRED) ||
                    errorMessageText.equals(FIVE_DIGIT_ZIP_CODE_REQUIRED) ||
                    errorMessageText.equals(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER)) {
                String title = errorMessage.getAttribute(Constants.DATA_Q_TITLE);

                if (!productPageErrorHeadingTitles.contains(title)) {
                    Assert.fail("FAIL: Unexpected error message " + errorMessageText +
                            " found for category '" + title + "'!");
                }
            }
        }

        if (productErrorListItems.contains(MILES_DRIVEN_SHOULD_BE)) {
            productErrorListItems.remove(MILES_DRIVEN_SHOULD_BE);
            productErrorListItems.add(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER);
        }

        if (productErrorListItems.contains(PROPER_ZIP_CODE_OR_POSTAL_CODE)) {
            productErrorListItems.remove(PROPER_ZIP_CODE_OR_POSTAL_CODE);
            productErrorListItems.add(FIVE_DIGIT_ZIP_CODE_REQUIRED);
        }

        for (String expectedRequired : productPageErrorHeadingTitles) {
            boolean found = false;
            boolean requiredField = false;
            for (WebElement errorMessage : errorMessages) {
                found = false;
                requiredField = false;
                String errorMessageText = errorMessage.getText().trim();
                if (errorMessageText.equals(Constants.REQUIRED) ||
                        errorMessageText.equals(FIVE_DIGIT_ZIP_CODE_REQUIRED) ||
                        errorMessageText.equals(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER)) {
                    requiredField = true;
                    String title = errorMessage.getAttribute(Constants.DATA_Q_TITLE);
                    if (title.equals(expectedRequired)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found && requiredField) {
                Assert.fail("FAIL: Expected error message not found for category '" + expectedRequired + "'!");
            }
        }
        LOGGER.info("verifyProductReviewErrorMessagesOnPage completed");
    }

    /**
     * Verify required fields and other error messages are displayed on the store review page
     */
    public void verifyStoreReviewErrorMessagesOnPage() {
        LOGGER.info("verifyStoreReviewErrorMessagesOnPage started");
        List<WebElement> errorMessages = webDriver.findElements(errorMessageBy);

        for (WebElement errorMessage : errorMessages) {
            String errorMessageText = errorMessage.getText().trim();
            if (errorMessageText.equals(Constants.REQUIRED)) {
                String title = errorMessage.getAttribute(Constants.DATA_Q_TITLE);

                if (!storePageErrorHeadingTitles.contains(title)) {
                    Assert.fail("FAIL: Unexpected error message " + errorMessageText +
                            " found for category '" + title + "'!");
                }
            }
        }

        for (String expectedRequired : storePageErrorHeadingTitles) {
            boolean found = false;
            boolean requiredField = false;
            for (WebElement errorMessage : errorMessages) {
                found = false;
                requiredField = false;
                String errorMessageText = errorMessage.getText().trim();
                if (errorMessageText.equals(Constants.REQUIRED)) {
                    requiredField = true;
                    String title = errorMessage.getAttribute(Constants.DATA_Q_TITLE);
                    if (title.equals(expectedRequired)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found && requiredField) {
                Assert.fail("FAIL: Expected error message not found for category '" + expectedRequired + "'!");
            }
        }
        LOGGER.info("verifyStoreReviewErrorMessagesOnPage completed");
    }

    /**
     * Verify the 'Driving zip code' field displayed
     */
    public void verifyDrivingZipCodeFieldDisplayed() {
        LOGGER.info("verifyDrivingZipCodeFieldDisplayed started");
        Assert.assertTrue("FAIL: The 'Driving zip code' field was not displayed!",
                driver.isElementDisplayed(zipCodeField));
        LOGGER.info("verifyDrivingZipCodeFieldDisplayed completed");
    }

    /**
     * Verify the 'Miles driven on tires' field displayed
     */
    public void verifyMilesDrivenOnTiresFieldDisplayed() {
        LOGGER.info("verifyMilesDrivenOnTiresFieldDisplayed started");
        Assert.assertTrue("FAIL: The 'Miles driven on tires' field was not displayed!",
                driver.isElementDisplayed(milesDrivenField));
        LOGGER.info("verifyMilesDrivenOnTiresFieldDisplayed completed");
    }

    /**
     * Verify the specified list box displayed
     *
     * @param listBoxName - The name of the list box
     */
    public void verifyListBoxDisplayed(String listBoxName) {
        LOGGER.info("verifyListBoxDisplayed started for '" + listBoxName + "'");
        Assert.assertTrue("FAIL: The '" + listBoxName + "' list box was not displayed!",
                driver.isElementDisplayed(driver.getElementWithText(listBoxBy, listBoxName)));
        LOGGER.info("verifyListBoxDisplayed completed for '" + listBoxName + "'");
    }

    /**
     * Enter a value into the 'Driving zip code' field
     *
     * @param zipCode - value being entered
     * @param isValid - true/false whether or not the value is valid
     */
    public void enterZipCode(String zipCode, boolean isValid) {
        LOGGER.info("enterZipCode started with zip code = '" + zipCode + "'");
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
        productErrorListItems.remove(ConstantsDtc.ZIP_CODE);
        productErrorListItems.remove(PROPER_ZIP_CODE_OR_POSTAL_CODE);
        productErrorListItems.remove(FIVE_DIGIT_ZIP_CODE_REQUIRED);
        productPageErrorHeadingTitles.remove(ConstantsDtc.ZIP_CODE);
        if (!isValid) {
            if (!zipCode.equals("")) {
                productErrorListItems.add(PROPER_ZIP_CODE_OR_POSTAL_CODE);
            } else {
                productErrorListItems.add(ConstantsDtc.ZIP_CODE);
            }
            productPageErrorHeadingTitles.add(ConstantsDtc.ZIP_CODE);
        }
        LOGGER.info("enterZipCode completed with zip code = '" + zipCode + "'");
    }

    /**
     * Enter a value into the 'Miles driven on tires' field
     *
     * @param miles - value being entered
     */
    public void enterMilesDriven(String miles) {
        LOGGER.info("enterMilesDriven started with miles = '" + miles + "'");
        milesDrivenField.clear();
        milesDrivenField.sendKeys(miles);
        productErrorListItems.remove(ConstantsDtc.MILES_DRIVEN);
        productErrorListItems.remove(MILES_DRIVEN_SHOULD_BE);
        productErrorListItems.remove(PLEASE_TYPE_A_VALID_MILES_DRIVEN_NUMBER);
        productPageErrorHeadingTitles.remove(ConstantsDtc.MILES_DRIVEN);
        if (miles.equals("")) {
            productErrorListItems.add(ConstantsDtc.MILES_DRIVEN);
            productPageErrorHeadingTitles.add(ConstantsDtc.MILES_DRIVEN);
        } else {
            if (Integer.parseInt(miles) < 1 || Integer.parseInt(miles) > 150000) {
                productErrorListItems.add(MILES_DRIVEN_SHOULD_BE);
                productPageErrorHeadingTitles.add(ConstantsDtc.MILES_DRIVEN);
            }
        }
        LOGGER.info("enterMilesDriven completed with miles = '" + miles + "'");
    }

    /**
     * Verify the specified error message displayed
     *
     * @param errorMsg - The error message to verify
     */
    public void verifyErrorMessageDisplayed(String errorMsg) {
        LOGGER.info("verifyErrorMessageDisplayed started with error message " + errorMsg);
        List<WebElement> errorMessages = webDriver.findElements(errorMessageBy);
        boolean found = false;
        for (WebElement errorMessage : errorMessages) {
            if (errorMessage.getText().trim().equals(errorMsg)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("FAIL: The expected error message '" + errorMsg +
                "' was not displayed!", found);
        LOGGER.info("verifyErrorMessageDisplayed completed with error message " + errorMsg);
    }

    /**
     * Select or deselect 'Yes, I agree to Discount Tire's Terms & Conditions' checkbox
     *
     * @param selection - desired action: 'select' or 'deselect'
     */
    public void clickTermsAndConditionsCheckBox(String selection) {
        LOGGER.info("clickTermsAndConditionsCheckBox started");
        boolean selected = termsAndConditionsCheckBox.getAttribute(Constants.CLASS).contains(Constants.CHECKED);

        if ((selection.equalsIgnoreCase(Constants.SELECT) && !selected ||
                (selection.equalsIgnoreCase(Constants.DESELECT) && selected))) {
            termsAndConditionsCheckBox.click();
        }

        selected = termsAndConditionsCheckBox.getAttribute(Constants.CLASS).contains(Constants.CHECKED);
        if ((selection.equalsIgnoreCase(Constants.SELECT) && !selected ||
                (selection.equalsIgnoreCase(Constants.DESELECT) && selected))) {
            Assert.fail("FAIL: Unable to " + selection +
                    " the 'Yes, I agree to Discount Tire's Terms & Conditions' checkbox");
        } else {
            if (selection.equalsIgnoreCase(Constants.SELECT)) {
                storeErrorListItems.remove(ConstantsDtc.TERMS_AND_CONDITIONS);
                storePageErrorHeadingTitles.remove(ConstantsDtc.TERMS_AND_CONDITIONS);
                productErrorListItems.remove(ConstantsDtc.TERMS_AND_CONDITIONS);
                productPageErrorHeadingTitles.remove(ConstantsDtc.TERMS_AND_CONDITIONS);
            } else {
                storeErrorListItems.add(ConstantsDtc.TERMS_AND_CONDITIONS);
                storePageErrorHeadingTitles.add(ConstantsDtc.TERMS_AND_CONDITIONS);
                productErrorListItems.add(ConstantsDtc.TERMS_AND_CONDITIONS);
                productPageErrorHeadingTitles.add(ConstantsDtc.TERMS_AND_CONDITIONS);
            }
        }
        LOGGER.info("clickTermsAndConditionsCheckBox completed");
    }

    /**
     * Select specified answer to "Would You Recommend" question for store or product
     *
     * @param answer - The button to click: "YES" or "NO"
     */
    public void selectWouldYouRecommendAnswer(String answer) {
        LOGGER.info("selectWouldYouRecommendAnswer started");
        if (answer.equalsIgnoreCase(Constants.RANDOM)) {
            if (CommonUtils.getRandomNumber(0, 2) == 0) {
                answer = Constants.YES;
            } else {
                answer = Constants.NO;
            }
        }
        if (answer.equalsIgnoreCase(Constants.YES)) {
            yesButton.click();
        } else {
            noButton.click();
        }
        storeErrorListItems.remove(ConstantsDtc.STORE_RECOMMENDATION);
        storePageErrorHeadingTitles.remove(ConstantsDtc.STORE_RECOMMENDATION);
        productErrorListItems.remove(ConstantsDtc.PRODUCT_RECOMMENDATION);
        productPageErrorHeadingTitles.remove(ConstantsDtc.PRODUCT_RECOMMENDATION);
        LOGGER.info("selectWouldYouRecommendAnswer completed");
    }

    /**
     * Verify the completed review message header and message below the header displayed for store review
     *
     * @param reviewType - Type of review: 'store' or 'product'
     */
    public void verifyReviewSuccessfulSubmission(String reviewType) {
        LOGGER.info("verifyReviewSuccessfulSubmission started for " + reviewType + " review");
        String successfulSubmissionMessage = null;

        if (reviewType.equals(Constants.STORE)) {
            successfulSubmissionMessage = storeSuccessfulSubmissionMessage.getText();
        } else {
            successfulSubmissionMessage = productSuccessfulSubmissionMessage.getText();
        }

        Assert.assertTrue("FAIL: The '" + ConstantsDtc.completedSolicitedReviewHeader.get(reviewType) +
                        "' header did not display after submitting a completed review.",
                successfulSubmissionMessage.contains(ConstantsDtc.completedSolicitedReviewHeader.get(reviewType)));
        Assert.assertTrue("FAIL: The message '" + ConstantsDtc.completedSolicitedReviewMessage.get(reviewType) +
                        "' did not display after submitting a completed review",
                successfulSubmissionMessage.contains(ConstantsDtc.completedSolicitedReviewMessage.get(reviewType)));
        LOGGER.info("verifyReviewSuccessfulSubmission completed for " + reviewType + " review");
    }

    /**
     * Select the specified item from the "Driving condition" dropdown list
     *
     * @param selection - The item to select
     */
    public void selectDrivingConditions(String selection) {
        LOGGER.info("selectDrivingConditions started");
        if (selection.equalsIgnoreCase(Constants.RANDOM)) {
            int itemIndex = CommonUtils.getRandomNumber(0, 4);
            selection = DRIVING_CONDITIONS_ARRAY[itemIndex];
        }
        driver.getElementWithText(listBoxBy, ConstantsDtc.DRIVING_CONDITIONS).click();
        driver.getElementWithText(listItemBy, selection).click();
        productErrorListItems.remove(ConstantsDtc.DRIVING_CONDITIONS);
        productPageErrorHeadingTitles.remove(ConstantsDtc.DRIVING_CONDITIONS);
        LOGGER.info("selectDrivingConditions completed");
    }

    /**
     * Select the specified item from the "Type of driving" dropdown list
     *
     * @param selection - The item to select
     */
    public void selectTypeOfDriving(String selection) {
        LOGGER.info("selectTypeOfDriving started");
        if (selection.equalsIgnoreCase(Constants.RANDOM)) {
            int itemIndex = CommonUtils.getRandomNumber(0, 4);
            selection = TYPE_OF_DRIVING_ARRAY[itemIndex];
        }
        driver.getElementWithText(listBoxBy, ConstantsDtc.TYPE_OF_DRIVING).click();
        driver.getElementWithText(listItemBy, selection).click();
        productErrorListItems.remove(ConstantsDtc.TYPE_OF_DRIVING);
        productPageErrorHeadingTitles.remove(ConstantsDtc.TYPE_OF_DRIVING);
        LOGGER.info("selectTypeOfDriving completed");
    }

    /**
     * Enter text into the Comments text area
     *
     * @param text - The text to be entered
     */
    public void enterGeneralComments(String text) {
        LOGGER.info("enterGeneralComments started");
        commentsTextArea.clear();
        commentsTextArea.sendKeys(text);
        storeErrorListItems.remove(ConstantsDtc.GENERAL_COMMENTS);
        storePageErrorHeadingTitles.remove(ConstantsDtc.GENERAL_COMMENTS);
        LOGGER.info("enterGeneralComments completed");
    }

    /**
     * Click the Read Reviews Link for a specified store
     */
    public void clickReadReviewsLink() {
        LOGGER.info("clickReadReviewsLink started");
        if (driver.isElementDisplayed(myStoreNoReviewsMessageBy)) {
            Assert.fail("FAIL: There were no reviews for the store");
        }
        webDriver.findElement(CommonActions.storeReadReviewsBy).click();
        LOGGER.info("clickReadReviewsLink completed");
    }

    /**
     * Validates the sorting value selected is displayed
     *
     * @param reviewType Type of Review ( Store Reviews , Product Reviews )
     * @param sortValue  Sort By Value that is being verified
     */
    public void assertSortByDropdownValue(String reviewType, String sortValue) {
        LOGGER.info("assertSortByDropdownValue started");
        if (reviewType.equalsIgnoreCase(ConstantsDtc.STORE_REVIEWS)) {
            driver.waitForPageToLoad();
            driver.waitForMilliseconds(Constants.FIVE_HUNDRED_MILLISEC_WAIT);
            Assert.assertTrue("FAIL: The sort value displayed for Store Reviews is not " + sortValue,
                    storeReviewSimpleValue.getText().equalsIgnoreCase(sortValue)
                            || driver.isElementDisplayed(storeReviewSimpleValue));
        } else {
            driver.waitForPageToLoad();
            driver.waitForMilliseconds(Constants.FIVE_HUNDRED_MILLISEC_WAIT);
            Assert.assertTrue("FAIL: The sort value displayed for Product Reviews is not " + sortValue,
                    productReviewSimpleValue.getText().equalsIgnoreCase(sortValue)
                            || driver.isElementDisplayed(storeReviewSimpleValue));
        }
        LOGGER.info("assertSortByDropdownValue completed");
    }

    /**
     * Verifies the list of reviews are sorted in descending order
     * by Most Recent sorting option
     */
    public void assertReviewMostRecentSortOrder(String reviewType) {
        LOGGER.info("assertReviewMostRecentSortOrder started");
        List<WebElement> reviewDateList;
        if (reviewType.equalsIgnoreCase(ConstantsDtc.STORE_REVIEWS)) {
            reviewDateList = webDriver.findElements(storeReviewDateBy);
        } else {
            reviewDateList = webDriver.findElements(productReviewDateBy);
        }

        if ((reviewDateList.size() == 0) && (driver.isElementDisplayed(storeDetailsNoReviewsMessageBy))) {
            Assert.fail("FAIL: There were no reviews and dates listed on the page");
        }

        String recentDate = null;
        for (WebElement reviewDate : reviewDateList) {
            String previousDate = reviewDate.getText();
            if (recentDate != null) {
                Assert.assertTrue("The Date " + recentDate + " is less than the previous date " + previousDate,
                        recentDate.compareTo(previousDate) >= 0);
            }
            recentDate = previousDate;
        }
        LOGGER.info("assertReviewMostRecentSortOrder completed");
    }

    /**
     * Selects the Sort By value specified for sorting
     *
     * @param sortValue Value being selected for sorting
     */
    public void clickStoreReviewsSortByValue(String sortValue) {
        LOGGER.info("clickStoreReviewsSortByValue started");
        driver.waitForElementClickable(detailReviewsSortBy);
        driver.getElementWithText(detailReviewsSortBy, sortValue).click();
        LOGGER.info("clickStoreReviewsSortByValue completed");
    }

    /**
     * Selects the Sort By dropdown
     */
    public void clickSortByDropdown() {
        LOGGER.info("clickSortByDropdown started");
        webDriver.navigate().refresh();
        driver.waitForElementClickable(sortByDropdown);
        sortByDropdown.click();
        driver.waitForPageToLoad();
        LOGGER.info("clickSortByDropdown completed");
    }

    /**
     * Verifies the container for the "Filter Reviews By Rating" section is displayed to the user
     */
    public void verifyFilterReviewsByRatingSectionIsDisplayed() {
        LOGGER.info("verifyFilterReviewsByRatingSectionIsDisplayed started");
        driver.waitForPageToLoad();
        Assert.assertTrue("FAIL: The \"Filter Reviews By Rating\" section was NOT displayed!",
                driver.isElementDisplayed(filterReviewsByRatingContainer, Constants.FIVE_SEC_WAIT));
        LOGGER.info("verifyFilterReviewsByRatingSectionIsDisplayed completed");
    }

    /**
     * Selects the specified (star) rating filter for Store Reviews
     *
     * @param ratingToSelect The star rating filter to select (one - five)
     */
    public void selectStarRatingCheckboxForStoreReviews(String ratingToSelect) {
        LOGGER.info("selectStarRatingCheckboxForStoreReviews started and will select the \"" + ratingToSelect
                + "\" rating");
        WebElement ratingCheckbox = driver.getElementWithAttribute(facetInputLabelBy, ATTRIBUTE_FOR,
                String.format(STAR_RATINGS, ratingToSelect));
        driver.waitForElementClickable(ratingCheckbox, Constants.FIVE_SEC_WAIT);
        ratingCheckbox.click();
        LOGGER.info("selectStarRatingCheckboxForStoreReviews completed and selected the \"" + ratingToSelect
                + "\" rating");
    }

    /**
     * Verifies the specified (star) rating filter is the only selected rating filter. Method loops through all the
     * filters, removing the one that should be active and validates it has been checked / selected. A second loop
     * through the remaining filters validates that they are either un-checked / un-selected OR disabled (due to there
     * not being any reviews for that particular filter)
     *
     * @param selectedFilter The filter to validate is active AND checked / selected
     */
    public void verifyStarRatingCheckboxIsTheOnlySelectedFilter(String selectedFilter) {
        LOGGER.info("verifyStarRatingCheckboxIsTheOnlySelectedFilter started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(filterReviewsByRatingContainer, Constants.FIVE_SEC_WAIT);

        WebElement selectedFilterLabel = driver.getElementWithAttribute(facetInputLabelBy, ATTRIBUTE_FOR,
                String.format(STAR_RATINGS, selectedFilter));
        WebElement selectedFilterSpan = selectedFilterLabel.findElement(inputCheckboxBy);
        String selectedSpanClassText = selectedFilterSpan.getAttribute(Constants.CLASS);

        Assert.assertTrue("FAIL: The star rating filter: \"" + selectedFilter
                        + "\" was NOT selected OR was disabled!",
                !selectedSpanClassText.contains(Constants.DISABLED)
                        && selectedSpanClassText.contains(Constants.CHECKED));

        List<String> unSelectedFilterList = starRatingsList;
        unSelectedFilterList.removeIf(selectedFilter::equalsIgnoreCase);

        for (String filter : unSelectedFilterList){
            WebElement unSelectedFilterLabel = driver.getElementWithAttribute(facetInputLabelBy, ATTRIBUTE_FOR,
                    String.format(STAR_RATINGS, filter));
            WebElement unSelectedFilterSpan = unSelectedFilterLabel.findElement(inputCheckboxBy);
            String unSelectedSpanClassText = unSelectedFilterSpan.getAttribute(Constants.CLASS);

            Assert.assertTrue("FAIL: The star rating filter: \"" + filter
                            + "\" was expected to be unselected OR disabled!",
                    !unSelectedSpanClassText.contains(Constants.CHECKED)
                            || unSelectedSpanClassText.contains(Constants.DISABLED));
        }
        LOGGER.info("verifyStarRatingCheckboxIsTheOnlySelectedFilter completed");
    }

    /**
     * Verifies the displayed store reviews match what should be the user specified (star) rating filter
     *
     * @param selectedFilter (star) Rating filter that should be active and match with the displayed store reviews
     */
    public void verifyStoreReviewsFilteredByStarRating(String selectedFilter) {
        LOGGER.info("verifyStoreReviewsFilteredByStarRating started");
        driver.waitForPageToLoad();
        driver.waitForElementVisible(reviewScoresBy);
        String expectedFilterRating = null;
        List<WebElement> storeReviewScoresList;

        switch (selectedFilter){
            case ONE_STAR:
                expectedFilterRating = "1";
                break;
            case TWO_STAR:
                expectedFilterRating = "2";
                break;
            case THREE_STAR:
                expectedFilterRating = "3";
                break;
            case FOUR_STAR:
                expectedFilterRating = "4";
                break;
            case FIVE_STAR:
                expectedFilterRating = "5";
                break;
            default:
                Assert.fail("FAIL: The selected filter does NOT match an expected digit or value! Cannot verify"
                        + " ratings have been filtered!");
                break;
        }

        storeReviewScoresList = reviewsReactContainer.findElements(reviewScoresBy);

        for (WebElement storeReviewScore : storeReviewScoresList){
            String actualReviewRating = storeReviewScore.getText().split("\\.")[0];
            Assert.assertTrue("FAIL: A review score of: '" + actualReviewRating
                    + "' did NOT match the expected filter rating of: '" + expectedFilterRating + "'!",
                    actualReviewRating.equalsIgnoreCase(expectedFilterRating));
        }
        LOGGER.info("verifyStoreReviewsFilteredByStarRating completed");
    }
}