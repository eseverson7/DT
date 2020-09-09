package dtc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.SolicitedReviewPage;
import utilities.Driver;

/**
 * Created by Collin Reed on 3/26/18.
 */
public class SolicitedReviewPageSteps {

    private SolicitedReviewPage solicitedReviewPage;

    public SolicitedReviewPageSteps(Driver driver) {
        solicitedReviewPage = new SolicitedReviewPage(driver);
    }

    @When("^I navigate to Store Review page")
    public void i_navigate_to_store_review_page() throws Throwable {
        solicitedReviewPage.navigateToStoreReviewPage();
    }

    @When("^I navigate to Product Review page with vehicle details for \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\"$")
    public void i_navigate_to_product_review_page_with_vehicle_details(String year, String make,
                                                                       String model, String productId) throws Throwable {
        solicitedReviewPage.navigateToProductReviewPage(year, make, model, productId);
    }

    @When("^I navigate to Product Review page with vehicle Id for \"(.*?)\" \"(.*?)\"$")
    public void i_navigate_to_product_review_page_with_vehicle_id(String vehicleId, String productId) throws Throwable {
        solicitedReviewPage.navigateToProductReviewPage(vehicleId, productId);
    }

    @Then("^I verify store logo is displayed in store info section$")
    public void i_verify_store_logo_is_displayed_in_store_info_section() throws Throwable {
        solicitedReviewPage.verifyStoreLogoInStoreInfoSection();
    }

    @Then("^I verify store name, address, and phone number are displayed in store info section$")
    public void i_verify_store_details_are_displayed_in_store_info_section() throws Throwable {
        solicitedReviewPage.verifyStoreDetailsInStoreInfoSection();
    }

    @Then("^I verify \"Store Hours\" are displayed in store info section$")
    public void i_verify_hours_are_displayed_in_store_info_section() throws Throwable {
        solicitedReviewPage.verifyStoreHoursInStoreInfoSection();
    }

    @Then("^I verify store review page header text$")
    public void i_verify_store_review_page_header_text() throws Throwable {
        solicitedReviewPage.verifyStoreReviewPageHeaderText();
    }

    @Then("^I verify product review page header text with product name \"(.*?)\"$")
    public void i_verify_product_review_page_header_text_with_product_name(String productName) throws Throwable {
        solicitedReviewPage.verifyProductReviewPageHeaderText(productName);
    }

    @Then("^I verify \"All fields required unless marked as optional\" displayed$")
    public void i_verify_all_fields_required_unless_marked_as_optional_displayed() throws Throwable {
        solicitedReviewPage.verifyAllFieldsRequiredUnlessOptionalMessage();
    }

    @Then("^I verify store ratings headings$")
    public void i_verify_store_ratings_headings() throws Throwable {
        solicitedReviewPage.verifyStoreRatingHeadings();
    }

    @Then("^I verify \"Would You Recommend This Store\" displayed with YES NO buttons$")
    public void i_verify_would_you_recommend_this_store_with_yes_no_buttons() throws Throwable {
        solicitedReviewPage.verifyWouldYouRecommendYesNo(Constants.STORE);
    }

    @Then("^I verify \"Would you recommend this product to family and friends\" question displayed with YES NO buttons$")
    public void i_verify_would_you_recommend_this_product_to_family_and_friends_with_yes_no_buttons() throws Throwable {
        solicitedReviewPage.verifyWouldYouRecommendYesNo(Constants.PRODUCT);
    }

    @Then("^I verify \"Comments About Your Experience\" displayed$")
    public void i_verify_comments_about_your_experience_displayed() throws Throwable {
        solicitedReviewPage.verifyCommentsAboutYourExperienceLabelDisplayed();
    }

    @Then("^I verify \"(Pros|Cons|Comments)\" text area displayed with \"([^\"]*)\" character limit$")
    public void i_verify_text_area_displayed_with_character_limit(String textBoxName, int characterLimit) throws Throwable {
        solicitedReviewPage.verifyTextAreaDisplayedWithTextLimit(textBoxName, characterLimit);
    }

    @Then("^I verify \"Yes I agree to Terms and Conditions\" text displayed with checkbox and Terms and Conditions link$")
    public void i_verify_terms_and_conditions_text_checkbox_and_link() throws Throwable {
        solicitedReviewPage.verifyTermsAndConditions();
    }

    @When("^I click \"Terms and Conditions\" link$")
    public void i_click_terms_and_conditions_link() throws Throwable {
        solicitedReviewPage.clickTermsAndConditionsLink();
    }

    @Then("^I verify \"CUSTOMER RATINGS AND REVIEW TERMS OF USE\" pop-up is displayed$")
    public void i_verify_customer_ratings_and_review_terms_of_use_popup_is_displayed() throws Throwable {
        solicitedReviewPage.verifyCustomerRatingsAndReviewTermsOfUseModalPopupDisplayed();
    }

    @When("^I close \"CUSTOMER RATINGS AND REVIEW TERMS OF USE\" pop-up$")
    public void i_close_customer_ratings_and_review_terms_of_use_popup() throws Throwable {
        solicitedReviewPage.closeCustomerRatingsAndReviewTermsOfUseModalPopup();
    }

    @Then("^I verify the \"Submit Review\" button is displayed$")
    public void i_verify_the_submit_review_button_is_displayed() throws Throwable {
        solicitedReviewPage.verifySubmitReviewButtonDisplayed();
    }

    @When("^I click \"Submit Review\" button$")
    public void i_click_submit_review_button() throws Throwable {
        solicitedReviewPage.clickSubmitReviewButton();
    }

    @Then("^I verify the \"Please provide feedback for the following\" pop-up is displayed$")
    public void i_verify_the_please_provide_feedback_for_the_following_popup_is_displayed() throws Throwable {
        solicitedReviewPage.verifyErrorListModalPopupDisplayed();
    }

    @When("^I close the \"Please provide feedback for the following\" pop-up$")
    public void i_close_the_please_provide_feedback_for_the_following_popup() throws Throwable {
        solicitedReviewPage.closeErrorListModalPopup();
    }

    @Then("^I verify product rating headings displayed with five stars$")
    public void i_verify_product_rating_headings_displayed_with_five_stars() throws Throwable {
        solicitedReviewPage.verifyProductRatingCategoriesDisplayedWithFiveStars();
    }

    @Then("^I verify items listed in \"Please provide feedback for the following\" pop-up for \"(store|product)\"$")
    public void i_verify_items_listed_in_please_provide_feedback_for_the_following_popup(String reviewType) throws Throwable {
        solicitedReviewPage.verifyErrorPopUpListItems(reviewType);
    }

    @When("^I select \"(1|2|3|4|5|random)\" stars for " +
            "\"(RIDE COMFORT|CORNERING & STEERING|RIDE NOISE|TREAD LIFE|DRY TRACTION|WET TRACTION|WINTER WEATHER TRACTION)\" " +
            "product rating category$")
    public void i_select_number_of_stars_for_product_rating_category(String stars, String category) throws Throwable {
        solicitedReviewPage.selectProductCategoryRating(category, stars);
    }

    @When("^I select \"(1|2|3|4|5|random)\" stars for " +
            "\"(Employee Knowledge/Friendliness|Store Cleanliness|Overall Rating)\" store rating category$")
    public void i_select_number_of_stars_for_store_rating_category(String stars, String category) throws Throwable {
        solicitedReviewPage.selectProductCategoryRating(category, stars);
    }

    @Then("^I verify error messages on page for \"(store|product)\"$")
    public void i_verify_error_messages_on_page(String reviewType) throws Throwable {
        if (reviewType.equalsIgnoreCase(Constants.STORE))
            solicitedReviewPage.verifyStoreReviewErrorMessagesOnPage();
        else
            solicitedReviewPage.verifyProductReviewErrorMessagesOnPage();
    }

    @Then("^I verify \"Driving zip code\" field displayed$")
    public void i_verify_driving_zip_code_field_displayed() throws Throwable {
        solicitedReviewPage.verifyDrivingZipCodeFieldDisplayed();
    }

    @Then("^I verify \"Miles driven on tires\" field displayed$")
    public void i_verify_miles_driven_on_tires_field_displayed() throws Throwable {
        solicitedReviewPage.verifyMilesDrivenOnTiresFieldDisplayed();
    }

    @Then("^I verify \"([^\"]*)\" list box displayed$")
    public void i_verify_driving_conditions_field_displayed(String listBoxName) throws Throwable {
        solicitedReviewPage.verifyListBoxDisplayed(listBoxName);
    }

    @When("^I enter \"(valid|invalid)\" Driving zip code \"([^\"]*)\"$")
    public void i_enter_valid_or_invalid_driving_zip_code(String validNotValid, String zipCode) throws Throwable {
        boolean isValid = true;
        if (!validNotValid.equals(Constants.VALID)) {
            isValid = false;
        }
        solicitedReviewPage.enterZipCode(zipCode, isValid);
    }

    @When("^I enter \"([^\"]*)\" Miles driven on tires$")
    public void i_enter_miles_driven_on_tires(String milesDriven) throws Throwable {
        solicitedReviewPage.enterMilesDriven(milesDriven);
    }

    @Then("^I verify \"(5 digit zip code required|Please type a number between 1 and 150000)\" error message displayed$")
    public void i_verify_error_message_displayed(String errorMessage) throws Throwable {
        solicitedReviewPage.verifyErrorMessageDisplayed(errorMessage);
    }

    @Then("^I verify \"([^\"]*)\" is listed in the \"Please provide feedback for the following\" pop-up$")
    public void i_verify_item_listed_in_please_provide_feedback_for_the_following_popup(String item) throws Throwable {
        solicitedReviewPage.verifyErrorPopUpListItem(item, false);
    }

    @Then("^I verify \"([^\"]*)\" is the only item listed in the \"Please provide feedback for the following\" pop-up$")
    public void i_verify_item_is_the_only_item_listed_in_please_provide_feedback_for_the_following_popup(String item)
            throws Throwable {
        solicitedReviewPage.verifyErrorPopUpListItem(item, true);
    }

    @When("^I \"(select|deselect)\" \"Yes, I agree to Discount Tire's Terms & Conditions\" checkbox$")
    public void i_click_terms_and_conditions_checkbox(String selection) throws Throwable {
        solicitedReviewPage.clickTermsAndConditionsCheckBox(selection);
    }

    @When("^I select \"(YES|NO|random)\" for \"Would You Recommend\" question$")
    public void i_select_answer_for_would_you_recommend_this_store(String answer) throws Throwable {
        solicitedReviewPage.selectWouldYouRecommendAnswer(answer);
    }

    @When("^I enter \"([^\"]*)\" into the Comments text box$")
    public void i_enter_text_into_the_comments_text_box(String text) throws Throwable {
        solicitedReviewPage.enterGeneralComments(text);
    }

    @When("^I select \"(Mostly Dry|Mixed Dry/Wet|Mostly Wet|Snow|random)\" from \"Driving conditions\" dropdown list$")
    public void i_select_item_from_driving_conditions_dropdown_list(String selection) throws Throwable {
        solicitedReviewPage.selectDrivingConditions(selection);
    }

    @When("^I select \"(Mostly Highway|Mixed Highway/City|Mostly City|Off Road|Race|random)\" from" +
            " \"Type of driving\" dropdown list$")
    public void i_select_item_from_type_of_driving_dropdown_list(String selection) throws Throwable {
        solicitedReviewPage.selectTypeOfDriving(selection);
    }

    @Then("^I verify completed review message displayed for \"(store|product)\"$")
    public void i_verify_completed_review_message(String reviewType) throws Throwable {
        solicitedReviewPage.verifyReviewSuccessfulSubmission(reviewType);
    }

    @When("^I select Read Reviews link in the popup$")
    public void i_select_read_reviews_link_in_the_popup() throws Throwable {
        solicitedReviewPage.clickReadReviewsLink();
    }

    @Then("^I verify the \"(Store Reviews|Product Reviews)\" sort by dropdown value is set to \"(Most Recent|Lowest Rated)\"$")
    public void i_verify_the_store_reviews_sort_by_dropdown_value_is_set_to(String reviewType, String sortValue) throws Throwable {
        solicitedReviewPage.assertSortByDropdownValue(reviewType, sortValue);
    }

    @When("^I sort the reviews by \"([^\"]*)\"$")
    public void i_sort_the_reviews_by(String sortValue) throws Throwable {
        solicitedReviewPage.clickStoreReviewsSortByValue(sortValue);
    }

    @When("^I select Sort By dropdown$")
    public void i_select_sort_by_dropdown() throws Throwable {
        solicitedReviewPage.clickSortByDropdown();
    }

    @And("^I verify the 'Filter Reviews By Rating' section is displayed$")
    public void i_verify_filter_reviews_by_rating_section_is_displayed() throws Throwable {
        solicitedReviewPage.verifyFilterReviewsByRatingSectionIsDisplayed();
    }

    @When("^I select the \"(one|two|three|four|five)\" star rating checkbox to filter 'Store Reviews'$")
    public void i_select_star_rating_checkbox_for_store_reviews(String ratingToSelect) throws Throwable {
        solicitedReviewPage.selectStarRatingCheckboxForStoreReviews(ratingToSelect);
    }

    @Then("^I verify the \"(one|two|three|four|five)\" star rating checkbox is the only selected filter for 'Store Reviews'$")
    public void i_verify_star_rating_checkbox_is_the_only_selected_filter_for_store_reviews(String selectedFilter) throws Throwable {
        solicitedReviewPage.verifyStarRatingCheckboxIsTheOnlySelectedFilter(selectedFilter);
    }

    @And("^I verify the 'Store Reviews' are filtered by \"(one|two|three|four|five)\" star rating$")
    public void i_verify_store_reviews_are_filtered_by_star_rating(String selectedFilter) throws Throwable {
        solicitedReviewPage.verifyStoreReviewsFilteredByStarRating(selectedFilter);
    }
}

