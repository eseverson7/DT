package dtc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.data.ConstantsDtc;
import dtc.data.Customer;
import dtc.pages.CartPage;
import dtc.pages.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;

import java.util.Set;

import static dtc.pages.ProductListPage.itemID;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class CartPageSteps {

    private CartPage cartPage;
    private Customer customer;

    public static double fixedDiscountAmount;
    public static int fixedDiscountPercentage;
    public static String orderTotalPrice;

    public CartPageSteps(Driver driver) {
        cartPage = new CartPage(driver);
        customer = new Customer();
    }

    @Then("^I see \"(.*?)\" on the cart page$")
    public void i_see_item_on_the_cart_page(String item) throws Throwable {
        //Could confirm on cart page as well, but since orderList element is only on cart page
        //this is implicit validation of that
        cartPage.assertItemOnCartPage(item);
    }

    @When("^I remove the item from the cart$")
    public void i_remove_the_item_from_the_cart() throws Throwable {
        cartPage.removeItem();
    }

    @When("^I remove the \"(.*?)\" items from the cart$")
    public void i_remove_the_items_from_the_cart(String stockStatus) throws Throwable {
        cartPage.removeItemsByStockStatus(stockStatus);
    }

    @Then("^I should see product \"(.*?)\" on the cart page$")
    public void i_should_see_product_on_the_cart_page(String product) throws Throwable {
        cartPage.assertItemOnCartPage(product);
    }

    @Then("^I verify product \"(.*?)\" is \"(not displayed|displayed)\" on the cart page$")
    public void i_verify_product_display_on_cart_page(String productName, String displayStatus) throws Throwable {
        cartPage.assertItemOnCartPage(productName, displayStatus);
    }

    @Then("^I should see saved product id on the cart page$")
    public void i_should_see_product_on_the_cart_page() throws Throwable {
        cartPage.assertItemOnCartPage(itemID);
    }

    @And("^I update the quantity to \"(.*?)\"$")
    public void i_update_the_quantity_to(String qty) throws Throwable {
        cartPage.updateQuantity(qty);
    }

    @Then("^I should see product has been \"(removed|updated)\" in cart message$")
    public void i_should_see_product_has_been_in_cart_message(String messageType) throws Throwable {
        cartPage.assertHeaderMessage(messageType);
    }

    @Then("^I should see quantity is set to \"(.*?)\" in the cart$")
    public void i_should_see_quantity_is_set_to_in_the_cart(String qty) throws Throwable {
        cartPage.assertUpdatedProductQty(qty);
    }

    @Then("^I see a purchase quantity of \"(.*?)\"$")
    public void i_see_a_purchase_quantity_of(String quantity) throws Throwable {
        cartPage.assertProductQuantityOnCartPage(quantity);
    }

    @And("^I verify the \"(.*?)\" is \"(.*?)\" percent of the total in the cart page$")
    public void i_verify_the_percentage_on_the_cart_page(String orderSummaryItem, String percentage)
            throws Throwable {
        cartPage.verifyPercentage(orderSummaryItem, percentage);
    }

    @Then("^I verify checkout option \"(.*?)\" is not available$")
    public void i_verify_checkout_option_is_not_available(String checkoutType) throws Throwable {
        cartPage.verifyCheckoutButtonNotDisplayed(checkoutType);
    }

    @Then("^I verify add To cart option is disabled$")
    public void i_verify_add_To_cart_option_is_disabled() throws Throwable {
        cartPage.assertAddToCartButtonIsDisabled();
    }

    @Then("^I verify the options in the switch vehicle popup$")
    public void i_verify_switch_vehicle_options() throws Throwable {
        cartPage.assertSwitchVehicleOptions();
    }

    @When("^I select \"(Clear my cart and Continue|View cart|cancel)\" in the Switching vehicle popup$")
    public void i_select_switch_vehicle_options(String option) throws Throwable {
        cartPage.selectSwitchVehicleOption(option);
    }

    @Then("^I verify that the price for the product on the cart page is \"(.*?)\"$")
    public void i_verify_product_price_cart(String price) throws Throwable {
        cartPage.assertCartPrice(price);
    }

    @Then("^I verify that the pre-total on the cart page is \"(.*?)\"$")
    public void i_verify_cart_pretotal(String price) throws Throwable {
        cartPage.assertCartPreTotal(price);
    }

    @Then("^I verify that the subtotal on the cart page is \"(.*?)\"$")
    public void i_verify_cart_subtotal(String subtotal) throws Throwable {
        cartPage.assertCartSubtotal(subtotal);
    }

    @Then("^I verify that the tax on the cart page is \"(.*?)\"$")
    public void i_verify_cart_tax(String tax) throws Throwable {
        cartPage.assertCartTax(tax);
    }

    @Then("^I verify that the order total on the cart page is \"(.*?)\"$")
    public void i_verify_cart_order_total(String total) throws Throwable {
        cartPage.assertCartOrderTotal(total);
    }

    @Then("^I validate the fixed dollar discount has been applied$")
    public void i_validate_the_fixed_dollar_discount_has_been_applied() throws Throwable {
        orderTotalPrice = cartPage.assertFixedDollarDiscountApplied(fixedDiscountAmount);
    }

    @Then("^I validate the fixed percentage discount has been applied$")
    public void i_validate_the_fixed_percentage_discount_has_been_applied() throws Throwable {
        orderTotalPrice = cartPage.assertFixedPercentageDiscountApplied(fixedDiscountPercentage);
    }

    @And("^I calculate the taxes and fees for \"([^\"]*)\"$")
    public void i_calculate_taxes_and_fees_for_customer(String customerType) throws Throwable {
        Customer cartCustomer = customer.getCustomer(customerType);
        cartPage.calculateTaxesFeesForCustomer(cartCustomer);
    }

    @Then("^I verify the \"([^\"]*)\" label present on the cart page$")
    public void i_verify_label_present_on_cart_page(String labelToVerify) throws Throwable {
        cartPage.assertFeeServiceLabelPresentOnCartPage(labelToVerify);
    }

    @When("^I verify the RRA Certificate message$")
    public void i_verify_the_RRA_Certificate_message() throws Throwable {
        cartPage.assertCertificateRepairRefundReplacementLabelDisplayed();
    }

    @When("^I verify the Certificate fee amount on the shopping cart page$")
    public void i_verify_the_Certificate_fee_amount_on_the_shopping_cart() throws Throwable {
        cartPage.setProductParent(cartPage.getProductParent());
        cartPage.assertCertificateTotal();
    }

    @Then("^I verify the subtotal price on the cart page$")
    public void i_verify_cart_subtotal() throws Throwable {
        cartPage.assertExtractedCartSubtotal();
    }

    @Then("^I verify the item total displayed on cart page$")
    public void i_verify_the_item_total_displayed_on_cart_page() throws Throwable {
        cartPage.assertItemTotal();
    }

    @And("^I verify the tire disposal fee amount on the shopping cart page$")
    public void i_verify_tire_disposal_fee_amount_on_the_cart_page() throws Throwable {
        cartPage.assertTireDisposalFeeAmt();
    }


    @Then("^I verify the environment fee amount on the shopping cart page$")
    public void i_verify_the_environment_fee_amount_on_the_shopping_cart_page() throws Throwable {
        cartPage.assertEnvironmentalFeeAmt();
    }

    @Then("^I verify the FET fee amount on the shopping cart if applicable to item \"([^\"]*)\"$")
    public void i_verify_the_FET_fee_amount_on_the_shopping_cart_if_applicable_to_item(int item) throws Throwable {
        cartPage.assertFETFee(item);
    }

    @And("^I verify the TPMS Rebuild Kits amount \"(.*?)\" on the shopping cart page$")
    public void i_verify_TPMS_Rebuild_Kits_amount_on_the_cart_page(String fee) throws Throwable {
        cartPage.assertTPMSRebuildPrice(fee);
    }

    @Then("^I verify the TPMS Rebuild Kits amount calculated based on \"([^\"]*)\" on cart page$")
    public void i_verify_the_TPMS_Rebuild_Kits_amount_calculated_based_on_on_cart_page(String fee) throws Throwable {
        cartPage.assertTPMSRebldPrice(fee);
    }

    @Then("^I click on More Options and select TPMS Sensor$")
    public void i_click_on_More_Options_and_select_TPMS_Sensor() throws Throwable {
        cartPage.addTPMSSensor();
    }

    @Then("^I verify the TPMS Sensor label present on the shopping cart page$")
    public void I_verify_the_TPMS_Sensor_label_present_on_the_shopping_cart_page() throws Throwable {
        cartPage.assertTPMSSensorLabelDisplayed();
    }

    @Then("^I verify the tax on the cart page$")
    public void i_verify_cart_tax() throws Throwable {
        cartPage.assertTax();
    }

    @Then("^I verify total price on mini cart$")
    public void i_verify_total_price_on_mini_cart() throws Throwable {
        cartPage.assertMiniCartPrice();
    }

    @Then("^I verify the RRA Certificate \"(BasePrice|Quantity|TotalPrice)\" on MiniCart page$")
    public void i_verify_the_RRA_Certificate_on_MiniCart_page(String text) throws Throwable {
        if (text.equalsIgnoreCase(ConstantsDtc.BASE_PRICE)) {
            cartPage.assertCertificateBasePriceMiniCart();
        } else if (text.equalsIgnoreCase(ConstantsDtc.QUANTITY)) {
            cartPage.assertCertificateQuantityMiniCart();
        } else {
            cartPage.assertCertificateTotalPriceMiniCart();
        }
    }

    @Then("^I verify the Total price on the cart summary page$")
    public void i_verify_the_Total_price_on_the_cart_summary_page() throws Throwable {
        cartPage.assertCartOrderPriceTotal();
    }

    @Then("^I verify the TPMS Sensor amount calculated based on \"([^\"]*)\" price on cart page$")
    public void i_verify_the_TPMS_Sensor_amount_calculated_based_on_price_on_cart_page(String TPMSSensor) throws Throwable {
        cartPage.assertTPMSSensorPrice(TPMSSensor);
    }

    @Then("^I verify \"([^\"]*)\" \"([^\"]*)\" is in the cart$")
    public void i_verify_is_in_the_cart(String element, String text) throws Throwable {
        cartPage.verifyElementInCart(element, text.toUpperCase());
    }

    @When("^I select show fee details option$")
    public void i_select_show_fee_details() throws Throwable {
        cartPage.clickShowFeeDetails(Constants.NONE);
    }

    @When("^I select show fee details option for item \"([^\"]*)\"$")
    public void i_select_show_fee_details_for_item(String item) throws Throwable {
        cartPage.clickShowFeeDetails(item);
    }

    @When("^I verify the Installation price for item \"([^\"]*)\"$")
    public void i_verify_the_installation_price_for_item(String item) throws Throwable {
        double installPrice = cartPage.getPrice(cartPage.getRowParents(item), ConstantsDtc.INSTALLATION);
        WebElement quantity = cartPage.getParent(item);
        cartPage.assertInstallationPrice(installPrice, quantity);
    }

    @Then("^I verify the item price with PDP price and item total displayed on cart page$")
    public void i_verify_the_item_price_with_PDP_price_and_item_total_displayed_on_cart_page() throws Throwable {
        cartPage.assertCartProductPriceTotal();
    }

    @And("^I verify the added products and prices displayed on cart page$")
    public void i_verify_the_added_products_and_prices_displayed_on_cart_page() throws Throwable {
        cartPage.assertProductsAndPricesOnCartPageFromPlp();
    }

    @Then("^I verify the items total price on cart page$")
    public void i_verify_the_items_total_price_on_cart_page() throws Throwable {
        cartPage.assertItemsPriceTotal();
    }

    @When("^I select show fee details option for items$")
    public void i_select_show_fee_details_for_items() throws Throwable {
        Set<String> itemCodes = CommonActionsSteps.cartProductPrice.keySet();
        for (String itemCode : itemCodes) {
            cartPage.clickShowFeeDetails(itemCode);
        }
    }

    @When("^I verify the \"(Environmental Fee|Installation|Disposal Fee)\" price for items$")
    public void i_verify_the_fee_price_for_items(String text) throws Throwable {
        Set<String> itemCodes = CommonActionsSteps.cartProductPrice.keySet();
        for (String itemCode : itemCodes) {
            cartPage.assertFeesForItemsOnCart(text, itemCode);
        }
    }

    @And("^I see for item \"(.*?)\" a purchase quantity of \"(.*?)\"$")
    public void i_see_item_purchase_quantity(String itemNumber, String expectedQuantity) throws Throwable {
        cartPage.assertItemQuantityOnCartPage(itemNumber, expectedQuantity);
    }

    @When("^I select the optional \"(Certificates|TPMS Rebuild Kit|TPMS Sensor|Valve Stem)\" fee for item$")
    public void i_select_the_optional_fee_for_item(String text) throws Throwable {
        cartPage.addOptionalFeeOnCartPage(Constants.NONE, text);
    }

    @And("^I select the optional \"(Certificates|TPMS Rebuild Kit|TPMS Sensor|Valve Stem)\" fee for items$")
    public void i_select_the_fee_for_items(String text) throws Throwable {
        Set<String> itemCodes = CommonActionsSteps.cartProductPrice.keySet();
        for (String itemCode : itemCodes) {
            cartPage.addOptionalFeeOnCartPage(itemCode, text);
        }
    }

    @And("^I verify the \"(TPMS Rebuild Kit|TPMS Sensor|Valve Stem)\" price$")
    public void i_verify_the_optional_fee_price(String text) throws Throwable {
        cartPage.assertOptionalFeePriceOnCart(text);
    }

    @When("^I update quantity for item \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_update_quantity_for_item_to(String item, String quantity) throws Throwable {
        cartPage.updateQuantityForItem(item, quantity);
    }

    @When("^I remove the item \"([^\"]*)\" from the cart$")
    public void i_remove_the_item_from_the_cart(String item) throws Throwable {
        cartPage.removeItem(item);
    }

    @Then("^I verify the item \"([^\"]*)\" is removed from the cart page$")
    public void i_verify_the_item_is_removed_from_the_cart_page(String item) throws Throwable {
        cartPage.assertRemovedItemNotDisplayed(item);
    }

    @And("^I verify the \"(Installation|Environmental Fee|Disposal Fee)\" label displayed for item \"([^\"]*)\"$")
    public void i_verify_the_fee_label_displayed_for_item(String text, String item) throws Throwable {
        cartPage.assertFeeLabelDisplayed(text, item);
    }

    @Then("^I verify the \"(Installation)\" quantity for item \"([^\"]*)\" display \"(.*?)\"$")
    public void i_verify_the_installation_quantity_for_item_display(String text, String item, String quantity) throws Throwable {
        cartPage.assertFeeQuantity(text, item, quantity);
    }

    @And("^I verify the cart summary verbiages are displayed$")
    public void i_verify_the_cart_summary_verbiages_are_displayed() throws Throwable {
        cartPage.assertCartSummaryVerbiagesDisplay();
    }

    @When("^I extract environment fee details$")
    public void i_extract_environment_fee_details() throws Throwable {
        cartPage.setEnvironmentFee();
    }

    @When("^I verify the \"(Hub Centric Ring|Wheel Install Kit)\" price for wheel item \"([^\"]*)\"$")
    public void i_verify_the_fee_price_for_wheel_item(String text, String item) throws Throwable {
        WebElement quantity = cartPage.getParent(item);
        if (text.equalsIgnoreCase(ConstantsDtc.WHEEL_INSTALL_KIT)) {
            double wheelInstallKit = cartPage.getPrice(cartPage.getRowParents(item), ConstantsDtc.WHEEL_INSTALL_KIT);
            cartPage.assertFeePriceWheel(wheelInstallKit, quantity, ConstantsDtc.WHEEL_INSTALL_KIT);
        } else if (text.equalsIgnoreCase(ConstantsDtc.HUB_CENTRIC_RING)) {
            double hubCentricRing = cartPage.getPrice(cartPage.getRowParents(item), ConstantsDtc.HUB_CENTRIC_RING);
            cartPage.assertFeePriceWheel(hubCentricRing, quantity, ConstantsDtc.HUB_CENTRIC_RING);
        }
    }

    @And("^I verify the \"(Environmental Fee|Disposal Fee)\" price for item \"([^\"]*)\"$")
    public void i_verify_the_fee_price_for_item(String text, String item) throws Throwable {
        cartPage.setFeeParents(cartPage.getFeeParents());
        if (text.equalsIgnoreCase(ConstantsDtc.ENVIRONMENTAL_FEE)) {
            cartPage.assertFeesForItemsOnCart(ConstantsDtc.ENVIRONMENTAL_FEE, item);
        } else {
            cartPage.assertFeesForItemsOnCart(ConstantsDtc.DISPOSAL_FEE, item);
        }
    }

    @And("^I verify the item subtotal for item \"([^\"]*)\"$")
    public void i_verify_the_item_subtotal_for_item(String item) throws Throwable {
        cartPage.assertItemSubtotal(item);
    }

    @And("^I verify the cart subtotal for tire and wheel items$")
    public void i_verify_the_cart_subtotal_for_tire_and_wheel_items() throws Throwable {
        cartPage.assertCartSubtotalForWheelsAndTires();
    }

    @And("^I verify the total tax for tire and wheel items$")
    public void i_verify_the_total_tax_for_tire_and_wheel_items() throws Throwable {
        cartPage.assertTaxForWheelsAndTires();
    }

    @And("^I verify the optional \"(Certificates|TPMS Rebuild Kit|TPMS Sensor|Valve Stem)\" fee is displayed$")
    public void i_verify_the_optional_fee_is_displayed(String text) throws Throwable {
        cartPage.assertOptionalFeeDisplay(Constants.NONE, text);
    }

    @Then("^I verify Mini Cart quick total \"(before|after)\" adding product$")
    public void i_verify_mini_cart_quick_total(String text) throws Throwable {
        if (text.equalsIgnoreCase(ConstantsDtc.BEFORE)) {
            cartPage.assertMiniCartQuickTotalBeforeAddingProduct();
        } else if (text.equalsIgnoreCase(ConstantsDtc.AFTER)) {
            cartPage.assertMiniCartQuickTotalAfterAddingProduct();
        }
    }

    @And("^I verify the \"(View cart|Continue Shopping)\" is displayed in Mini Cart$")
    public void i_verify_the_option_is_displayed_in_mini_cart(String text) throws Throwable {
        if (text.equalsIgnoreCase(CartPage.VIEW_CART)) {
            cartPage.assertMiniCartDisplayViewCart();
        } else {
            cartPage.assertMiniCartDisplayContinueShopping();
        }
    }

    @When("^I verify the added product \"(.*?)\" is displayed in Mini Cart$")
    public void i_verify_the_added_product_is_displayed_in_mini_cart(String productName) throws Throwable {
        cartPage.assertAddedProductInMiniCart(productName);
    }

    @And("^I verify the \"([^\"]*)\" price in Mini Cart for product \"(.*?)\"$")
    public void i_verify_the_price_in_mini_cart(String text, String itemCode) throws Throwable {
        cartPage.assertMiniCartPrice(text, itemCode);
    }

    @When("^I select View Cart on Mini Cart$")
    public void i_select_view_Cart_on_mini_cart() throws Throwable {
        cartPage.clickViewCartMiniCart();
    }

    @When("^I verify Mini Cart total for product \"(.*?)\"$")
    public void i_verify_mini_cart_total(String product) throws Throwable {
        cartPage.assertMiniCartTotal(product);
    }

    @When("^I verify Mini Cart item total for product \"(.*?)\" with item code \"(.*?)\"$")
    public void i_verify_mini_cart_item_total(String product, String itemCode) throws Throwable {
        cartPage.assertMiniCartItemTotal(product, itemCode);
    }

    @Then("^I verify checkout now is enabled")
    public void i_verify_checkout_now_is_enabled() throws Throwable {
        cartPage.assertCheckoutNowEnabled();
    }

    @And("^I verify switch store popup message is displayed")
    public void i_verify_switch_store_popup_message_is_displayed() throws Throwable {
        cartPage.assertSwitchStoreOnCartPopupMessage();
    }

    @And("^I verify the switch store options are displayed")
    public void i_verify_the_switch_store_options_are_displayed() throws Throwable {
        cartPage.assertSwitchStoreOptions();
    }

    @And("^I verify switch vehicle popup message is displayed")
    public void i_verify_switch_vehicle_popup_message_is_displayed() throws Throwable {
        cartPage.assertSwitchVehicleOnCartPopupMessage();
    }

    @And("^I verify the applicable fees are displayed on cart page for item \"(.*?)\"")
    public void i_verify_the_applicable_fees_are_displayed_on_cart_page_for_item(String itemCode) throws Throwable {
        cartPage.assertFeeDisplayWithVehicle(itemCode);
    }

    @Then("^I verify quantity for \"([^\"]*)\" is set to \"(.*?)\" in the cart$")
    public void i_verify_quantity_for_item_display(String item, String quantity) throws Throwable {
        cartPage.assertQuantityForItem(item, quantity);
    }

    @Then("^I verify \"(.*?)\" displayed on the \"(cart page|Order Confirmation)\" for item \"(.*?)\"$")
    public void i_verify_rebate_displayed_on_page(String rebate, String page, String item) throws Throwable {
        if (rebate.contains(ConstantsDtc.INSTANT_SAVINGS)) {
            cartPage.assertInstantSavingsDisplayedForItem(page, item);
        } else {
            cartPage.assertMailInRebateDisplayed(rebate, page, item);
        }
    }

    @Then("^I verify the Order Summary Instant Savings box on the \"([^\"]*)\" page$")
    public void i_verify_the_order_summary_instant_savings_box_on_page(String page) throws Throwable {
        cartPage.assertInstantSavingOrderSummary(page);
    }

    @Then("^I verify the vehicle \"(.*?)\" is displayed on shopping cart page")
    public void i_verify_the_vehicle_is_displayed_on_shopping_cart_page(String vehicle) throws Throwable {
        cartPage.assertVehicleOnCartPage(vehicle);
    }
}