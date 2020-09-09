package dtc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.data.ConstantsDtc;
import dtc.pages.CommonActions;
import dtc.pages.ProductListPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 9/17/16.
 */
public class ProductListPageSteps {

    private ProductListPage productListPage;
    private final CommonActions commonActions;

    private String currentProductPageUrl;
    public String productOnPLP;

    public ProductListPageSteps(Driver driver) {
        productListPage = new ProductListPage(driver);
        commonActions = new CommonActions(driver);
    }

    @And("^I add item \"(.*?)\" of type \"(sets|front|rear|none)\" to my cart and \"(View shopping cart|Continue Shopping)\"$")
    public void i_add_items_to_my_cart_and(String itemId, String option, String action) throws Throwable {
        if (Config.isMobilePhone()) {
            productListPage.addToCartMobile(option, itemId, action);
        } else
            productListPage.addToCart(option, itemId, action);
    }

    @And("^I set quantity to \"(.*?)\" and add item \"(.*?)\" of type \"(sets|front|rear|none)\" to my cart and \"(View shopping cart|Continue Shopping)\"$")
    public void i_set_quantity_and_add_items_to_my_cart_and(String quantity, String itemId, String option, String action) throws Throwable {
        CommonActions.productQuantity = quantity;
        productListPage.addToCart(option, itemId, action);
    }

    @Then("^The search results show tires/wheels with the specified measurements \"(.*?)\"$")
    public void the_search_results_show_tires_wheels_with_the_specified_measurements(String measurements)
            throws Throwable {
        productListPage.assertSearchResults(measurements);
    }

    @And("^I note the current product list results url$")
    public void i_note_url_of_current_product_list_page() throws Throwable {
        currentProductPageUrl = productListPage.getCurrentProductPageUrl();
    }

    //NOTE: It's necessary to keep this step within this class due to the url dependency
    @Then("^I am taken back to the previous product list results url$")
    public void i_am_back_at_previous_product_list_results_url() throws Throwable {
        commonActions.waitForUrl(currentProductPageUrl, Constants.DEFAULT_SEC_WAIT);
    }

    @Then("^I verify that each page displays 10 products and total number of pages is equal to total count / 10$")
    public void i_verify_that_each_page_displays_products_and_total_number_of_pages_is_equal_to_total_count_div_10()
            throws Throwable {
        productListPage.validatePagination();
    }

    @And("^I verify that the sort by dropdown value is set to \"(.*?)\"$")
    public void i_verify_that_the_sortby_dropdown_value_is_set_to(String value) throws Throwable {
        productListPage.verifySortByValue(value);
    }

    @And("^I verify that the number of search refinement filters is \"(.*?)\"$")
    public void i_verify_search_refinement_filters_number(String number) throws Throwable {
        productListPage.verifyNumberOfSearchRefinementFilters(number);
    }

    @And("^I verify that the search refinement filters contain the \"(single|multiple)\" value\\(s\\): \"(.*?)\"$")
    public void i_verify_that_search_refinement_filters_contain_values(String multiple, String values) throws Throwable {
        productListPage.verifySearchRefinementFilterValues(multiple, values);
    }

    @And("^I select the \"([^\"]*)\" from the Sort By dropdown box$")
    public void i_select_the_from_the_sort_by_dropdown_box(String value) throws Throwable {
        productListPage.setSortByValue(value);
    }

    @And("^I verify the results list is sorted in \"(.*?)\" order by \"(price|name)\"$")
    public void i_verify_the_results_list_is_sorted_in_order(String order, String orderType) throws Throwable {
        if (orderType.equalsIgnoreCase(Constants.PRICE)) {
            productListPage.verifyPricesInOrder(order);
        } else {
            productListPage.verifyNamesInOrder(order);
        }
    }

    @And("^I expand the \"(.*?)\" filter section$")
    public void i_expand_filter_section(String filterSection) throws Throwable {
        productListPage.expandFilterSection(filterSection);
    }

    @When("^I select from the \"(.*?)\" filter section, \"(single|multiple)\" option\\(s\\): \"(.*?)\"$")
    public void i_select_from_filter_section(String multiple, String filterSection, String options) throws Throwable {
        if (Config.isMobile()) {
            productListPage.selectFromFilterSectionMobile(filterSection, multiple, options);
        } else {
            productListPage.selectFromFilterSection(filterSection, multiple, options);
        }
    }

    @Then("^I verify no search refinement filters are being applied$")
    public void i_verify_no_search_refinement_filters_applied() throws Throwable {
        productListPage.verifyNoSearchRefinementsAreApplied();
    }

    @And("^I add \"(in stock|on order)\" item of type \"(.*?)\" to my cart and \"(View shopping cart|Continue Shopping)\"$")
    public void i_add_item_to_my_cart_and(String itemStockStatus, String option, String action) throws Throwable {
        if (itemStockStatus.equalsIgnoreCase(ConstantsDtc.IN_STOCK)) {
            productListPage.addToCart(option, true, action);
        } else {
            productListPage.addToCart(option, false, action);
        }
    }

    @And("^I add the first item to my cart and \"(View shopping cart|Continue Shopping)\"$")
    public void i_add_the_first_item_to_my_cart_and(String action) throws Throwable {
        productListPage.addFirstItemToCart(action);
    }

    @Then("^I verify the initial page displays products that match my tire size\\(s\\): \"(.*?)\"$")
    public void i_verify_initial_page_displays_products_with_matching_tire_sizes(String tireSizes) throws Throwable {
        productListPage.verifyProductsMatchTireSize(tireSizes);
    }

    @And("^I select the first product result image$")
    public void i_select_the_first_product_result_image() throws Throwable {
        productListPage.selectFirstProductByImage();
    }

    @Then("^I should see \"([^\"]*)\" in the search results$")
    public void i_should_see_product_in_search_results(String productName) throws Throwable {
        productListPage.assertSearchResults(productName);
    }

    @And("^I select the \"Check Inventory\" link for item \"(.*?)\"$")
    public void i_click_the_check_inventory_link(String itemCode) throws Throwable {
        productListPage.clickCheckInventory(itemCode);
    }

    @When("^I select the Check Inventory for the first item available$")
    public void i_click_the_check_inventory_button_for_first_item_available() throws Throwable {
        productListPage.clickCheckInventoryForFirstAvailableItem();
    }

    @And("^I verify PLP UI \"(basic controls|banner without vehicle|filter / sorting|pagination)\"$")
    public void i_verify_plp_ui_aspects(String aspectToVerify) throws Throwable {
        productListPage.verifyPlpUiSection(aspectToVerify);
    }

    @Then("^I should see \"(.*?)\" on the product list page$")
    public void i_should_see_specified_info_on_the_product_list_page(String text) throws Throwable {
        productListPage.assertTextInProductListDetails(text);
    }

    @And("^I verify the Add To Cart button is clickable and Red on \"(.*?)\" page$")
    public void i_verify_the_add_to_cart_button_is_clickable_and_red(String page) throws Throwable {
        productListPage.verifyAddToCartButtonProperties(page);
    }

    @When("^I enter \"([^\"]*)\" into the first item quantity text box$")
    public void i_enter_into_the_first_item_quantity_text_box(int quantity) throws Throwable {
        productListPage.updateFirstRowItemQuantityMobile(quantity);
    }

    @Then("^I see the Please Enter a Number error message$")
    public void i_see_the_please_enter_a_number_error_message() throws Throwable {
        productListPage.verifyProductQuantityErrorMessage();
    }

    @And("^I extract the fixed dollar promotion discount of \"([^\"]*)\"$")
    public void i_extract_the_fixed_dollar_promotion_discount_of_item(String itemCode) throws Throwable {
        CartPageSteps.fixedDiscountAmount = productListPage.extractFixedDiscountFromItem(itemCode);
    }

    @And("^I extract the fixed percentage promotion discount of \"([^\"]*)\"$")
    public void i_extract_the_fixed_percentage_promotion_discount_of_item(String itemCode) throws Throwable {
        CartPageSteps.fixedDiscountPercentage = productListPage.extractFixedDiscountPercentageFromItem(itemCode);
    }

    @Then("^I verify the word \"([^\"]*)\" does not appear in the tire set tab titles$")
    public void i_verify_the_word_does_not_appear_in_the_tire_set_tab_titles(String word) throws Throwable {
        productListPage.assertTextNotInTireTabTitles(word);
    }

    @Then("^I see \"([^\"]*)\" on the product list page$")
    public void i_see_item_on_the_product_list_page(String item) throws Throwable {
        productListPage.assertItemOnProductListPage(item);
    }

    @And("^I select the \"(.*?)\" checkbox$")
    public void i_select_the_quick_filter_checkbox(String checkBox) throws Throwable {
        productListPage.clickPlpCheckboxToSelectDeselectFilter(checkBox, false);
    }

    @And("^I verify the \"(.*?)\" checkbox has been checked$")
    public void i_verify_the_checkbox_has_been_checked(String checkBox) throws Throwable {
        productListPage.verifyPlpCheckboxFilterSelected(checkBox);
    }

    @And("^I select the \"([^\"]*)\" filter to uncheck the checkbox$")
    public void i_select_the_filter_to_uncheck_the_checkbox(String checkBox) throws Throwable {
        productListPage.clickPlpCheckboxToSelectDeselectFilter(checkBox, true);
    }

    @And("^I verify the \"(.*?)\" filter checkbox to be deselected$")
    public void i_verify_the_filter_checkbox_is_deselected(String checkBoxLabel) throws Throwable {
        productListPage.assertCheckboxDeselected(checkBoxLabel);
    }

    @And("^I verify the \"(.*?)\" checkbox to be deselected by default$")
    public void i_verify_filter_checkbox_is_deselected(String checkBoxLabel) throws Throwable {
        productListPage.assertCheckboxDeselected(checkBoxLabel);
    }

    @Then("^I should see \"(.*?)\" as my current zipcode on pdl driving details section$")
    public void i_verify_pdl_zipcode_is_present(String zipcode) throws Throwable {
        productListPage.assertZipcodeValueOnPdlDrivingDetails(zipcode);
    }

    @Then("^I should see \"(.*?)\" as my selected miles per year value on pdl driving details section$")
    public void i_verify_pdl_miles_per_year_is_present(String milesPerYear) throws Throwable {
        productListPage.assertMilesPerYearValueOnPdlDrivingDetails(milesPerYear);
    }

    @Then("^I should see Our Recommendation banner appears on pdl search result page$")
    public void i_verify_pdl_recommendation_banner_is_present() throws Throwable {
        productListPage.assertOurRecommendationBannerIsDisplayed();
    }

    @Then("^I verify \"(Everyday|Performance)\" default pdl driving priority order on search result page$")
    public void i_verify_pdl_default_driving_priorities_order(String drivingPriority) throws Throwable {
        if (ConstantsDtc.DRIVING_PRIORITY_EVERYDAY.equalsIgnoreCase(drivingPriority)) {
            productListPage.assertDrivingPrioritiesOrder(ProductListPage.PDL_EVERYDAY_PRIORITY_ORDER);
        } else {
            productListPage.assertDrivingPrioritiesOrder(ProductListPage.PDL_PERFORMANCE_PRIORITY_ORDER);
        }
    }

    @Then("^I should see \"(.*?)\" \"(.*?)\" \"(.*?)\" and \"(.*?)\" are in order on search result page$")
    public void i_verify_pdl_driving_priorities_order(String pdlA, String pdlB, String pdlC, String pdlD) throws Throwable {
        String[] PDL_PRIORITY_ORDER = {pdlA, pdlB, pdlC, pdlD};
        productListPage.assertDrivingPrioritiesOrder(PDL_PRIORITY_ORDER);
    }

    @And("^I select edit pdl driving details on plp page$")
    public void i_select_edit_pdl_driving_details() throws Throwable {
        productListPage.selectEditPdlDrivingDetails();
    }

    @And("^I select compare tire reviews of the first displayed product$")
    public void i_select_compare_tire_reviews() throws Throwable {
        commonActions.clickElementByText(ConstantsDtc.COMPARE_ITEM_MESSAGE);
    }

    @Then("^I verify the product list page is displayed with sets$")
    public void i_verify_the_product_list_page_is_displayed_with_sets() throws Throwable {
        productListPage.verifyPLPPageDisplayStaggered();
    }

    @Then("^I verify the plp display tabs for staggered vehicle$")
    public void i_verify_the_plp_displayed_tabs_for_staggered() throws Throwable {
        productListPage.verifyPLPTabDisplayForStaggered();
    }

    @Then("^I verify the \"(.*?)\" filter section is displayed$")
    public void i_verify_filter_section_display(String filterSection) throws Throwable {
        productListPage.verifyFilterSectionsDisplay(filterSection);
    }

    @And("^I verify the \"Bolt Pattern\" facet is displayed when no vehicle is selected$")
    public void i_verify_bolt_pattern_facet_is_displayed_when_no_vehicle_is_selected() throws Throwable {
        productListPage.verifyBoltPatternFacetIsDisplayed(true);
    }

    @And("^I verify bolt pattern facet is not displayed when vehicle is selected$")
    public void i_verify_bolt_pattern_facet_is_not_displayed_when_vehicle_is_selected() throws Throwable {
        productListPage.verifyBoltPatternFacetIsDisplayed(false);
    }

    @Then("^I verify the \"(.*?)\" filter section\\(s\\) is/are displayed$")
    public void i_verify_filter_sections_are_displayed(String filterSections) throws Throwable {
        productListPage.verifyFilterSectionsDisplay(filterSections);
    }

    @Then("^I verify \"(FRONT|REAR)\" staggered option tab is displayed on PLP result page$")
    public void i_verify_staggered_option_tab_is_displayed_on_plp(String fitmentTab) throws Throwable {
        productListPage.verifyStaggeredOptionTabIsDisplayed(fitmentTab);
    }

    @When("^I extract \"(FRONT|REAR)\" staggered option size from tab$")
    public void i_extract_staggered_option_size_from_tab(String fitmentTab) throws Throwable {
        productListPage.getStaggeredFitmentSizeFromTab(fitmentTab);
    }

    @Then("^I verify \"(FRONT|REAR)\" wheel diameter matches with the size of each product on the results page$")
    public void i_verify_wheel_diameter_matches_with_each_rendered_product_size_on_plp(String fitmentTab) throws Throwable {
        productListPage.verifyListedProductsSizeMatchesWithSelectedStaggeredDiameter(fitmentTab);
    }

    @Then("^I select \"(FRONT|REAR)\" staggered tab on PLP result page$")
    public void i_select_staggered_tab(String fitmentTab) throws Throwable {
        productListPage.selectStaggeredTab(fitmentTab);
    }

    @Then("^I verify Customer Rating and reviews are displayed for listed products on PLP result page$")
    public void i_verify_customer_rating_and_reviews_are_displayed_for_listed_products_on_plp_result_page() throws Throwable {
        productListPage.verifyCustomerRatingAndReviewsDisplayedForListedProducts();
    }

    @Then("^I verify compare tire reviews link is displayed for products that have reviews$")
    public void i_verify_compare_tire_reviews_link_is_displayed_for_products_that_have_reviews() throws Throwable {
        productListPage.verifyCompareTireReviewsLinksDisplayed();
    }

    @When("^I extract \"(.*?)\" filter font size$")
    public void i_extract_filter_font_size(String filterLabel) throws Throwable {
        productListPage.getFilterFontSize(filterLabel);
    }

    @When("^I verify \"(.*?)\" filter font size value before and after selection$")
    public void i_verify_filter_font_size_value_before_after_selection(String filterLabel) throws Throwable {
        productListPage.assertFilterFontSizeBeforeAndAfterFilterSelection(filterLabel);
    }

    @And("^I verify the \"(.*?)\" quick filter is deselected by default for specific \"(TIRE|WHEEL)\" brands$")
    public void i_verify_filter_is_deselected_by_default_for_specific_brand(String checkBoxLabel, String type) throws Throwable {
        productListPage.verifyFilterIsDeselectedForSpecificBrands(checkBoxLabel, type);
    }

    @And("^I verify expected brand \"(.*?)\" products displayed on PLP$")
    public void i_verify_expected_brand_products_displayed_on_plp(String brand) throws Throwable {
        productListPage.assertProductBrandOnPLP(brand);
    }

    @Then("^I verify that OE Tire displays the Original Equipment Tire message$")
    public void i_verify_that_oe_tire_displays_the_original_equipment_tire_message() throws Throwable {
        productListPage.assertOETireMessage();
    }

    @And("^I verify tire quantity is \"(.*?)\" on product list page$")
    public void i_verify_tire_quantity_on_product_list_page(String quantity) throws Throwable {
        productListPage.assertProductQuantityInProductListPage(quantity);
    }

    @Then("^I verify Original Equipment tire is displayed on plp page$")
    public void i_verify_original_equipment_tire_is_displayed_on_plp_page() throws Throwable {
        productListPage.assertOETireDisplayedOnPLP();
    }

    @And("^I verify the order of filter categories for \"(Tires|Wheels)\"$")
    public void i_verify_order_of_filter_categories_for_product_type(String productType) throws Throwable {
        productListPage.verifyOrderOfFilterCategoriesForProductType(productType);
    }

    @Then("^I verify the displayed product\\(s\\) is/are on promotion$")
    public void i_verify_displayed_products_are_on_promotion() throws Throwable {
        productListPage.verifyDisplayedProductsAreOnPromotion();
    }

    @Then("^I verify \"([1|2|3])\" products selected$")
    public void i_verify_products_selected(Integer quantity) throws Throwable {
        productListPage.assertProductQuantitySelected(quantity);
    }

    @Then("^I verify compare button text is displayed as \"(Compare|Compare \\(up to 3\\))\"$")
    public void i_verify_compare_button_text_is_displayed_as(String text) throws Throwable {
        productListPage.assertCompareButtonText(text);
    }

    @Then("^I verify compare button text is displayed as \"([^\"]*)\" at position \"([^\"]*)\"$")
    public void i_verify_compare_button_text_is_displayed_as_at_position(String text, String position) throws Throwable {
        if (text.equalsIgnoreCase(ConstantsDtc.COMPARE)) {
            productListPage.assertCompareButtonTextAtPosition(ConstantsDtc.COMPARE, Integer.parseInt(position));
        } else {
            productListPage.assertCompareButtonTextAtPosition(ConstantsDtc.COMPARE_UP_TO_3, Integer.parseInt(position));
        }
    }

    @And("^I \"(select|deselect)\" the compare checkbox at position \"(.*?)\" from the products list page$")
    public void i_select_product_checkbox_at(String selectDeselect, String position) throws Throwable {
        if (selectDeselect.equalsIgnoreCase(Constants.SELECT)) {
            productListPage.selectCompareCheckboxAtPosition(Integer.parseInt(position));
        } else {
            productListPage.deSelectCompareCheckboxAtPosition(Integer.parseInt(position));
        }
    }

    @Then("^I verify the 'Compare \\(up to 3\\)' button is \"(enabled|disabled)\" on the products list page$")
    public void i_verify_the_compare_up_to_3_button_is_enabled_or_disabled_on_the_product_list_page(String enabledOrDisabled) throws Throwable {
        productListPage.assertCompareButtonEnabledDisabled(enabledOrDisabled);
    }

    @Then("^I verify the PLP header message contains \"(.*?)\"$")
    public void i_verify_plp_header_message_contains_text(String textToValidate) throws Throwable {
        productListPage.verifyPlpHeaderMessageContainsText(textToValidate);
    }

    @And("^I set the \"Price Range\" slider filter to the range: \\$\"(.*?)\" - \"(.*?)\"$")
    public void i_set_price_range_slider_filter_to_price_range(String priceMin, String priceMax) throws Throwable {
        productListPage.setPriceRangeSliderFilterToRange(priceMin, priceMax);
    }

    @When("^I clear all the currently active filters on the PLP page$")
    public void i_clear_all_currently_active_filters_on_plp_page() throws Throwable {
        productListPage.clearAllSearchFilters();
    }

    @Then("^I verify that all products are displaying the full product image$")
    public void i_verify_that_all_products_of_type_are_displaying_the_full_product_image() throws Throwable {
        productListPage.assertAllProductsDisplayFullImageSize();
    }

    @Then("^I verify the product list page is displayed with results$")
    public void i_verify_the_product_list_page_is_displayed_with_results() throws Throwable {
        productListPage.verifyPLPdisplayedWithResults();
    }

    @Then("^I verify the PLP displays 'Top 3 Tiles' below the PLP header$")
    public void i_verify_the_plp_displays_top_tiles_below_the_plp_header() throws Throwable {
        productListPage.assertTop3TilesComponentIsDisplayed();
    }

    @Then("^I verify the PLP displays \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" in the 'Top 3 Tiles'$")
    public void i_verify_the_plp_displays_in_the_top_tiles(String value1, String value2, String value3) throws Throwable {
        productListPage.assertTop3TileValues(value1, value2, value3);
    }

    @Then("^I extract the product at position \"([^\"]*)\" from the products list page$")
    public void i_extract_the_product_at_position_from_the_products_list_page(String position) throws Throwable {
        productOnPLP = productListPage.extractProductOnPLP(Integer.parseInt(position));
    }

    @Then("^I verify the product is displayed at position \"([^\"]*)\" in the 'Top 3 Tiles'$")
    public void i_verify_the_product_is_displayed_at_position_in_the_top_tiles(String position) throws Throwable {
        productListPage.assertProductInTop3Tiles(productOnPLP, Integer.parseInt(position));
    }

    @Then("^I verify \"([^\"]*)\" does not display in the filters$")
    public void i_verify_does_not_display_in_the(String checkBoxLabel) throws Throwable {
        productListPage.assertFilterNotDisplayed(checkBoxLabel);
    }

    @Then("^I verify \"([^\"]*)\" is displayed at position \"([^\"]*)\" in the 'Top 3 Tiles'$")
    public void i_verify_is_displayed_at_position_in_the_top_tiles(String brand, String position) throws Throwable {
        productListPage.assertProductInTop3Tiles(brand, Integer.parseInt(position));
    }

    @And("^I select the 'Read Reviews' link on the PLP$")
    public void i_select_the_read_reviews_link_on_the_plp() throws Throwable {
        productListPage.selectReadReviewsLink();
    }

    @Then("^I can see \"(.*?)\" PLP page$")
    public void i_can_see_plp_page(String category) throws Throwable {
        productListPage.verifyCatalogPage(category, false);
        productListPage.verifyPLPdisplayedWithResults();
    }

    @Then("^I can see \"(.*?)\" Brand PLP page$")
    public void i_can_see_brand_plp_page(String category) throws Throwable {
        productListPage.verifyCatalogPage(category, true);
        productListPage.verifyPLPdisplayedWithResults();
    }

    @Then("^I verify Check Inventory link for \"([^\"]*)\"$")
    public void i_verify_check_inventory_link_for(String itemCode) throws Throwable {
        productListPage.assertCheckInventoryLink(itemCode);
    }

    @When("^I click on the product \"([^\"]*)\"$")
    public void i_click_on_product(String itemCode) throws Throwable {
        productListPage.clickOnProduct(itemCode);
    }
}