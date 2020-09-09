package dtc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.data.BrowsePageData;
import dtc.data.ConstantsDtc;
import dtc.pages.CartPage;
import dtc.pages.CommonActions;
import dtc.pages.FooterPage;
import dtc.pages.ProductDetailPage;
import dtc.pages.ProductListPage;
import dtc.pages.CheckoutPage;
import utilities.DomObjectsExtractor;
import utilities.Driver;
import dtc.pages.SolicitedReviewPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


/**
 * Created by aaronbriel on 10/24/16.
 */
public class CommonActionsSteps {

    private Driver driver;
    private CheckoutPageSteps checkoutPageSteps;
    private CommonActions commonActions;
    private HomePageSteps homePageSteps;
    private OrderPageSteps orderPageSteps;
    private ProductDetailPageSteps productDetailPageSteps;
    private ProductListPage productListPage;
    private CartPage cartPage;
    private SearchAutocompleteDropDownPageSteps searchAutocompleteDropDownPageSteps;
    private DomObjectsExtractor domObjectsExtractor;
    private SolicitedReviewPage solicitedReviewPage;
    private final Logger LOGGER = Logger.getLogger(CommonActionsSteps.class.getName());
    public static double cartSubtotal;
    public static double checkoutSubtotal;
    public static double orderTotal;
    public static double salesTax;
    public static double salesTaxCheckout;
    public static double orderTotalCheckout;
    public static double pdpPrice;
    public static double miniCartQuickTotal;
    public static String storeName;

    public static HashMap<String, String> plpProductPrice = new HashMap<String, String>();
    public static HashMap<String, String> cartProductPrice = new HashMap<String, String>();
    public static ArrayList<String> cartInstantPromotionPrice = new ArrayList<>();
    public static ArrayList<String> cartMailInPromotion = new ArrayList<>();

    public CommonActionsSteps(Driver driver) throws IOException {
        checkoutPageSteps = new CheckoutPageSteps(driver);
        commonActions = new CommonActions(driver);
        homePageSteps = new HomePageSteps(driver);
        orderPageSteps = new OrderPageSteps(driver);
        productDetailPageSteps = new ProductDetailPageSteps(driver);
        productListPage = new ProductListPage(driver);
        searchAutocompleteDropDownPageSteps = new SearchAutocompleteDropDownPageSteps(driver);
        domObjectsExtractor = new DomObjectsExtractor(driver);
        cartPage = new CartPage(driver);
        solicitedReviewPage = new SolicitedReviewPage(driver);
    }

    @Then("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_button(String btnText) throws Throwable {
        commonActions.clickButtonByText(btnText);
    }

    @And("^I verify the \"([^\"]*)\" line item is present on the shopping cart page$")
    public void i_verify_the_line_item_is_present_on_the_shopping_cart_page(String tipContainerText) throws Throwable {
        commonActions.assertElementWithTextIsVisible(CartPage.optionName, tipContainerText);
    }

    @Then("^I confirm that taxes are listed on the \"(order|checkout)\" page$")
    public void i_confirm_that_taxes_are_listed_on_the_page(String page) throws Throwable {
        commonActions.confirmTaxes(page, CommonActions.ZERO_DOLLARS, true);
    }

    @And("^I confirm that fees are listed on the \"(order|checkout)\" page$")
    public void i_confirm_that_fees_are_listed_on_the_page(String page) throws Throwable {
        if (page.equalsIgnoreCase(ConstantsDtc.ORDER)) {
            orderPageSteps.i_expand_fee_details_for_item_listed_on_order_confirmation_page();
        } else {
            checkoutPageSteps.i_expand_cart_item_details_section_of_cart_summary_on_checkout_page();
            checkoutPageSteps.i_expand_fee_details_for_item_listed_in_cart_summary_on_checkout_page();
        }
        //TODO CCL - could set last arg to true if we knew what amount was to be expected instead of CommonActions.ZERO_DOLLARS ($0)
        commonActions.confirmFees(page, CommonActions.ZERO_DOLLARS, false);
    }

    @Then("^I should see store locator page$")
    public void i_should_see_store_locator_page() throws Throwable {
        commonActions.waitForUrl(CommonActions.STORE_LOCATOR_URL, Constants.DEFAULT_SEC_WAIT);
        commonActions.assertPageHeader(CommonActions.STORE_LOCATOR_HEADER);
    }

    @Then("^I am brought to the page with path \"(.*?)\"$")
    public void i_am_brought_to_the_page_with_path(String path) throws Throwable {
        commonActions.waitForUrl(path, Constants.DEFAULT_SEC_WAIT);
    }

    @Then("^I am brought to the page with header \"(.*?)\"$")
    public void i_am_brought_to_the_page_with_header(String text) throws Throwable {
        commonActions.assertPageHeader(text);
    }

    @Then("^I am brought to the \"(America's Tire|Discount Tire|Discount Tire Direct)\" site$")
    public void i_am_brought_to_the_site_for(String siteToValidate) throws Throwable {
        commonActions.assertPageTitle(siteToValidate);
    }

    @And("^I go to the \"(next|previous)\" page of product list results$")
    public void i_go_to_next_prev_page_of_product_results(String direction) throws Throwable {
        commonActions.navToDifferentPageOfResults(direction);
    }

    @When("^I close the Welcome Popup$")
    public void i_close_welcome_popup() throws Throwable {
        commonActions.checkForWelcomePopup();
    }

    @And("^I navigate back to previous page$")
    public void i_navigate_to_previous_page() throws Throwable {
        commonActions.navigateToPreviousBrowserPage();
    }

    @And("^I navigate to newly opened next tab$")
    public void i_navigate_to_already_opened_next_tab() throws Throwable {
        commonActions.switchToNextOpenedTab();
    }

    @And("^I navigate to previous browser tab$")
    public void i_navigate_to_previous_browser_tab() throws Throwable {
        commonActions.switchToPreviousOpenedTab();
    }

    @Then("^I verify the second level header \"([^\"]*)\" on the page$")
    public void i_verify_the_second_level_header_on_the_page(String text) throws Throwable {
        commonActions.assertPageSecondLevelHeader(text);
    }

    @Then("^I verify the Third level header \"([^\"]*)\" on the page$")
    public void i_verify_the_third_level_header_on_the_page(String text) throws Throwable {
        commonActions.assertPageThirdLevelHeader(text);
    }

    @Then("^I verify the header subtext \"([^\"]*)\" on the page$")
    public void i_verify_the_header_subtext_on_the_page(String text) throws Throwable {
        commonActions.assertPageElementTextByElement(FooterPage.payYourBillHeaderSubtextBy, text);
    }

    @Then("^I should see text \"(.*?)\" present in the page source$")
    public void i_should_see_text_present_on_the_page(String text) throws Throwable {
        commonActions.assertTextPresentInPageSource(text);
    }

    @And("^I verify the \"([^\"]*)\" link in the breadcrumb container$")
    public void i_verify_the_link_in_the_breadcrumb_container(String linkText) throws Throwable {
        commonActions.verifyLinksInsideBreadcrumbContainer(linkText);
    }

    @And("^I navigate to the \"([^\"]*)\" url$")
    public void i_navigate_to_the_url(String urlLink) throws Throwable {
        commonActions.navigateToPage(urlLink);
    }

    @And("^the element \"([^\"]*)\" of type \"([^\"]*)\" is visible on the page$")
    public void the_element_is_visible_on_the_page(String element, String byType) throws Throwable {
        commonActions.assertElementIsDisplayed(byType, element);
    }

    @And("^I click on the \"([^\"]*)\" Link$")
    public void i_click_on_the_link(String link) throws Throwable {
        commonActions.clickElementByText(link);
    }

    @And("^I verify the \"(PLP|PDP)\" banner color is \"(Green|Yellow|Red)\"$")
    public void i_verify_the_banner_color_is(String page, String color) throws Throwable {
        commonActions.assertBannerColor(page, color);
    }

    @Then("^I verify the message on the \"(Tires|Wheels)\" \"(PLP|PDP)\" banner contains " +
            "\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_verify_the_message_on_top_banner_contains(String item, String page, String itemSize, String make,
                                                            String model, String year) throws Throwable {
        String text = make + " " + model + " " + year;
        if (page.equalsIgnoreCase(ConstantsDtc.PLP)) {
            commonActions.assertStringInTopBanner(item, page, itemSize);
            commonActions.assertStringInTopBanner(item, page, text);
        } else if (page.equalsIgnoreCase(ConstantsDtc.PDP)) {
            commonActions.assertStringInTopBanner(item, page, text);
        }
    }

    @Then("^I verify the \"(PDP|PLP)\" results banner message contains \"(.*?)\"$")
    public void i_verify_the_results_banner_message_contains(String page, String text) throws Throwable {
        commonActions.assertResultsMessageContains(page, text);
    }

    @When("^I browse to the \"(Homepage|PLP|PDP|Shopping Cart|Checkout|Appointment|Order Confirmation)\" page with defaults$")
    public void i_browse_to_page_with_defaults(String validationPage) throws Throwable {

        BrowsePageData browsePageData = new BrowsePageData();

        homePageSteps.i_go_to_the_homepage();
        //Homepage ends here

        if (validationPage.equalsIgnoreCase(ConstantsDtc.CHECKOUT) && Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) ||
                validationPage.equalsIgnoreCase(ConstantsDtc.ORDER_CONFIRMATION) && Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT) ||
                validationPage.equalsIgnoreCase(ConstantsDtc.APPOINTMENT) && Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            LOGGER.info("\n \n SKIPPING: " + validationPage + " page test for " + Config.getSiteRegion() + " environment.");
        } else {
            if (!validationPage.equalsIgnoreCase(ConstantsDtc.HOMEPAGE)) {
                homePageSteps.i_do_a_free_text_search_for(browsePageData.defaultItemCode);

                if (validationPage.equalsIgnoreCase(ConstantsDtc.PLP)) {
                    searchAutocompleteDropDownPageSteps.i_select_text_from_the_search_dropdown(browsePageData.viewAllSeasonLink);
                    //PLP ends here
                } else {
                    searchAutocompleteDropDownPageSteps.i_select_text_from_the_search_dropdown(browsePageData.defaultItemName);

                    //PDP ends here
                    if (!validationPage.equalsIgnoreCase(ConstantsDtc.PDP)) {
                        productDetailPageSteps.i_add_items_to_my_cart_and_take_action(browsePageData.viewShoppingCartLink);

                        //Shopping Cart ends here
                        if (!validationPage.equalsIgnoreCase(ConstantsDtc.SHOPPING_CART)) {

                            if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {

                                checkoutPageSteps.i_select_the_checkout_option(browsePageData.defaultCheckout);
                                //Checkout ends here

                                if (validationPage.equalsIgnoreCase(ConstantsDtc.ORDER_CONFIRMATION)) {
                                    checkoutPageSteps.i_enter_shipping_info(browsePageData.defaultCustomer);
                                    checkoutPageSteps.i_select_shipping_option(browsePageData.defaultShippingOption,
                                            browsePageData.defaultCustomer);
                                    checkoutPageSteps.i_enter_payment_info_and_confirm_checkout_summary(browsePageData.defaultCustomer);
                                    checkoutPageSteps.i_place_the_order(browsePageData.defaultCustomer);
                                    orderPageSteps.i_am_brought_to_the_order_confirmation_page();
                                    //Order Confirmation ends here
                                }
                            } else {
                                checkoutPageSteps.i_select_the_checkout_option(browsePageData.checkoutWithAppointment);
                                //Appointment ends here
                            }
                        }
                    }
                }
            }
        }
    }

    @Then("^I close open tabs$")
    public void i_close_tabs() throws Throwable {
        commonActions.closeTabs();
    }

    @And("^I extract the subtotal amount on the \"(cart|checkout)\" page$")
    public void i_extract_subtotal_amount_on_page(String page) throws Throwable {
        if (page.equalsIgnoreCase("cart")) {
            cartSubtotal = commonActions.extractSubtotalAmount(page);
        } else {
            checkoutSubtotal = commonActions.extractSubtotalAmount(page);
        }
    }

    @Then("^I extract the DOM elements from \"(.*?)\" page and store in \"(.*?)\" excel spreadsheet$")
    public void i_parse_dom_creates_new_excel(String page, String excelName) throws Throwable {
        domObjectsExtractor.parseDomCreateExcel(page, excelName);
    }

    @When("^I close the popup$")
    public void i_close_the_popup() throws Throwable {
        commonActions.closePopup();
    }

    @And("^I extract the \"(order total|sales tax)\" on the cart page$")
    public void i_extract_amount_on_the_cart_page(String text) throws Throwable {
        if (text.equalsIgnoreCase("order total"))
            orderTotal = commonActions.cleanMonetaryString(CartPage.orderTotal.getText());
        else {
            salesTax = commonActions.extractSalesTaxOnCart();
        }
    }

    @And("^I extract the product price from \"(PDP|PLP|Cart)\" page$")
    public void i_extract_the_product_price_from(String text) throws Throwable {
        String itemPrice = null;

        if (text.equalsIgnoreCase("PDP")) {
            if (ProductDetailPage.productPrice.getText() != null) {
                itemPrice = ProductDetailPage.productPrice.getText();
            } else {
                itemPrice = ProductDetailPage.mapProductPrice.getText();
            }
            pdpPrice = commonActions.cleanMonetaryString(itemPrice);
        } else if (text.equalsIgnoreCase("PLP")) {
            plpProductPrice = productListPage.getProductPriceOnPlp();
        } else {
            cartProductPrice = cartPage.getProductPriceOnCart();
        }
    }

    @When("^I extract my store name on the cart page")
    public void i_extract_my_store_name_on_the_cart_page() throws Throwable {
        storeName = CommonActions.cartMyStore.getText().replace("(...)", "");
    }

    @And("^I extract the \"(order total|sales tax)\" on the checkout page$")
    public void i_extract_amount_on_the_checkout_page(String text) throws Throwable {
        if (text.equalsIgnoreCase("order total"))
            orderTotalCheckout = commonActions.cleanMonetaryString(CheckoutPage.checkoutOrdTotal.getText());
        else {
            salesTaxCheckout = commonActions.cleanMonetaryString(CheckoutPage.checkoutAndOrderCartSummarySalesTax.getText());
        }
    }

    @Then("^I verify the \"([^\"]*)\" button is \"(enabled|disabled)\"$")
    public void i_verify_the_button_is_enabled_or_disabled(String buttonText, String enabledOrDisabled) throws Throwable {
        commonActions.assertButtonEnabledDisabled(buttonText, enabledOrDisabled);
    }

    @And("^I extract the mini cart quick total$")
    public void i_extract_the_mini_cart_quick_total() throws Throwable {
        miniCartQuickTotal = commonActions.cleanMonetaryString(CartPage.miniCartPrice.getText());
    }

    @And("^I verify that Mini Cart is not displayed on \"(Shipping Details|Shipping Method|Payment|Order Confirmation)\" page$")
    public void i_verify_that_mini_cart_is_not_displayed_on_page(String page) throws Throwable {
        commonActions.assertMiniCartNotDisplayed(page);
    }

    @Then("^I verify sign out option is displayed$")
    public void i_verify_sign_out_is_displayed() throws Throwable {
        commonActions.assertSignOutLinkDisplayed();
    }

    @And("^I extract the \"(Instant Savings|Mail In Rebate)\" from cart page$")
    public void i_extract_the_promotion_price_from_cart_page(String promotion) throws Throwable {
        if (promotion.equalsIgnoreCase(ConstantsDtc.MAIL_IN_REBATE)) {
            cartMailInPromotion.clear();
            cartMailInPromotion = cartPage.getMailInRebateOnCart();
        } else {
            cartInstantPromotionPrice.clear();
            cartInstantPromotionPrice = cartPage.getInstantSavingsOnCart();
        }
    }

    @And("^I verify the result list for \"(Most Recent|Lowest Rated|Overall Rating)\" is sorted in \"(Descending|Ascending)\" order$")
    public void i_verify_result_list_is_sorted_in_order(String value, String sortOrder) throws Throwable {
            commonActions.verifyRatingOrder(value, sortOrder);
    }

    @And("^I verify the \"(Store Reviews|Product Reviews)\" result list for \"(Most Recent|Lowest Rated)\" is sorted in \"(Descending|Ascending)\" order$")
    public void i_verify_reviews_result_list_is_sorted_in_order(String reviewType, String value, String sortOrder) throws Throwable {
        if (reviewType.equalsIgnoreCase(ConstantsDtc.STORE_REVIEWS)) {
            if (value.equalsIgnoreCase(ConstantsDtc.MOST_RECENT)) {
                solicitedReviewPage.assertReviewMostRecentSortOrder(reviewType);
            } else if (value.equalsIgnoreCase(ConstantsDtc.LOWEST_RATED)) {
                commonActions.verifyRatingOrder(value, sortOrder);
            }
        } else {
            solicitedReviewPage.assertReviewMostRecentSortOrder(reviewType);
        }
    }

    @Then("^I verify the \"(.*?)\" displayed for \"(.*?)\" on \"(PLP|PDP)\" page$")
    public void i_verify_inventory_message_displayed_for_item_on_page(String text, String item, String page) throws Throwable {
        commonActions.assertInventoryMessage(text, item, page);
    }

    @Then("^I verify check nearby stores link is \"(not displayed|displayed)\" for \"(.*?)\" on \"(PLP|PDP)\" page$")
    public void i_verify_check_nearby_stores_display_for_item_on_page(String text, String item, String page) throws Throwable {
        commonActions.assertCheckNearbyStoresLinkDisplay(text, item, page);
    }

    @Then("^I verify the \"(.*?)\" for set displayed for \"(.*?)\" on \"(PLP|PDP)\" page$")
    public void i_verify_inventory_message_for_set_displayed_for_item_on_page(String text, String item, String page) throws Throwable {
        commonActions.assertInventoryMessageForSet(text, item, page);
    }

    @Then("^I verify check nearby stores link is \"(not displayed|displayed)\" having set \"(.*?)\" on \"(PLP|PDP)\" page$")
    public void i_verify_check_nearby_stores_display_having_set_item_on_page(String text, String item, String page) throws Throwable {
        commonActions.assertCheckNearbyStoresLinkDisplayForSetItem(text, item, page);
    }
}