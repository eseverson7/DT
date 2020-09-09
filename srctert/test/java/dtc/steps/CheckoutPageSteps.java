package dtc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.data.ConstantsDtc;
import dtc.data.Customer;
import dtc.pages.CartPage;
import dtc.pages.CheckoutPage;
import dtc.pages.CommonActions;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class CheckoutPageSteps {

    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CommonActions commonActions;
    private Customer customer;
    private GondorSteps gondorSteps;

    public CheckoutPageSteps(Driver driver) {
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        commonActions = new CommonActions(driver);
        customer = new Customer();
        gondorSteps = new GondorSteps(driver);
    }

    @Then("^I verify \"(install without appointment|install with appointment)\" option is enabled on the Checkout page$")
    public void i_verify_appointment_option_on_the_checkout_page(String appointmentOption) throws Throwable {
        checkoutPage.assertAppointmentOption(appointmentOption);
    }

    @When("^I enter shipping and payment info as \"(.*?)\"$")
    public void i_enter_shipping_and_payment_info(String customerType) throws Throwable {
        i_enter_shipping_and_payment_info_with_credit_card(CheckoutPage.VISA, customerType);
    }

    @When("^I place the order for \"(.*?)\"$")
    public void i_place_the_order(String customerType) throws Throwable {
        Customer cust = customer.getCustomer(customerType);
        checkoutPage.placeOrder(cust);
        checkoutPage.setRegionalTaxesFactor(cust);
    }

    @And("^I enter shipping info as \"(.*?)\"$")
    public void i_enter_shipping_info(String customerType) throws Throwable {
        Customer shippingCustomer = customer.getCustomer(customerType);
        checkoutPage.enterAddressForCustomer(shippingCustomer, true);
        checkoutPage.setRegionalTaxesFactor(shippingCustomer);
    }

    @Then("^I select to edit the shipping address")
    public void i_click_edit() throws Throwable {
        checkoutPage.clickEdit();
    }

    @And("^I set \"(First Name|Last Name|Country|State|Address Line 1|Town / City|Zip / Postal Code)\" to \"(.*?)\"$")
    public void i_edit_address(String field, String fieldValue) throws Throwable {
        checkoutPage.editShippingAddressField(field, fieldValue);
    }

    @Then("^I submit the updated address information")
    public void i_submit_updated_address() throws Throwable {
        checkoutPage.submitUpdatedAddress();
    }

    @Then("^I confirm the shipping options are: \"(.*?)\"$")
    public void i_confirm_shipping_options(String expectedOptions) throws Throwable {
        checkoutPage.confirmAvailableShippingOptions(expectedOptions);
    }

    @When("^I select shipping option: \"(.*?)\" as \"(.*?)\"$")
    public void i_select_shipping_option(String shippingOption, String customerType) throws Throwable {
        Customer shippingCustomer = customer.getCustomer(customerType);
        checkoutPage.selectDeliveryMethod(shippingOption, shippingCustomer);
    }

    @And("^I select the default shipping option as \"(.*?)\"$")
    public void i_select_default_shipping_option(String customerType) throws Throwable {
        Customer shippingCustomer = customer.getCustomer(customerType);
        checkoutPage.selectDefaultDeliveryMethod(shippingCustomer);
    }

    @And("^I enter payment info and confirm Checkout Summary as \"([^\"]*)\"$")
    public void i_enter_payment_info_and_confirm_checkout_summary(String customerType) throws Throwable {
        i_enter_payment_info_with_credit_card_and_confirm_checkout_summary(CheckoutPage.VISA, customerType);
    }

    @Then("^I verify \"([^\"]*)\" does not appear in the Delivery Method section$")
    public void i_verify_option_does_not_appear_in_delivery_method_section(String deliveryType) throws Throwable {
        checkoutPage.assertOptionNotInDeliveryMethod(deliveryType);
    }

    @And("^I select the \"See more options\" link$")
    public void i_select_see_more_options_link() throws Throwable {
        checkoutPage.selectSeeMoreOptionsLink();
    }

    @Then("^I confirm \"([^\"]*)\" receives an email for the order$")
    public void i_confirm_email_received_for_order(String customerType) throws Throwable {
        if (Config.isChrome()) {
            Customer orderEmailCustomer = customer.getCustomer(customerType);
            gondorSteps.i_open_gondor_inbox(orderEmailCustomer.email, orderEmailCustomer.emailPassword);
            gondorSteps.i_verify_most_recent_email_in_gondor_inbox_with_orderid();
        }
    }

    @Then("^I confirm the Checkout sections \"Shipping Address\", \"Delivery Method\" and \"Payment & Billing Address\" cannot be edited$")
    public void i_confirm_checkout_sections_cannot_be_edited() throws Throwable {
        checkoutPage.verifySectionsWithPaypalInfoNotEditable();
    }

    @And("^I confirm the Checkout section \"(Delivery Method|Shipping Address|Payment & Billing Address)\" can be edited for \"(.*?)\"$")
    public void i_confirm_checkout_section_can_be_edited(String section, String customerType) throws Throwable {
        Customer shippingCustomer = customer.getCustomer(customerType);
        checkoutPage.verifySectionIsEditable(section);
        if (!Config.isMobile()) {
            checkoutPage.clickEditLinkForSection(section);
            checkoutPage.selectDeliveryMethod(CheckoutPage.UPS_FREE, shippingCustomer);
        }
    }

    @And("^I enter shipping and payment info with \"(.*?)\" as \"(.*?)\"$")
    public void i_enter_shipping_and_payment_info_with_credit_card(String cardType, String customerType) throws Throwable {
        Customer defaultCust = customer.getCustomer(customerType);
        checkoutPage.enterAddressForCustomer(defaultCust, true);
        checkoutPage.selectDeliveryMethod(CheckoutPage.UPS_FREE, defaultCust);
        checkoutPage.enterPaymentInfo(cardType, defaultCust, true);
        checkoutPage.setRegionalTaxesFactor(defaultCust);
    }

    @And("^I enter payment info with \"(.*?)\" and confirm Checkout Summary as \"(.*?)\"$")
    public void i_enter_payment_info_with_credit_card_and_confirm_checkout_summary(String cardType, String customerType) throws Throwable {
        Customer paymentCustomer = customer.getCustomer(customerType);
        checkoutPage.enterPaymentInfo(cardType, paymentCustomer, true);
        checkoutPage.setRegionalTaxesFactor(paymentCustomer);
    }

    @And("^I enter payment info with a different billing address and confirm Checkout Summary as \"(.*?)\"$")
    public void i_enter_payment_info_different_billing_address_and_confirm_checkout_summary(String customerType) throws Throwable {
        Customer paymentCustomer = customer.getCustomer(customerType);
        checkoutPage.enterPaymentInfo(CheckoutPage.VISA, paymentCustomer, false);
        checkoutPage.setRegionalTaxesFactor(paymentCustomer);
    }

    @And("^I \"(accept|decline)\" credit card discloure consent$")
    public void i_take_cc_disclosure_consent_action(String action) throws Throwable {
        checkoutPage.creditCardDisclosureModalAction(action);
    }

    @And("^I \"(accept|decline)\" credit card terms of agreement$")
    public void i_take_cc_terms_of_agreement_action(String action) throws Throwable {
        checkoutPage.ccTermsOfAgreementAction(action);
    }

    @Then("^I verify the Checkout and Shopping Cart order summary subtotal amounts match$")
    public void i_verify_checkout_and_cart_subtotal_amounts_match() throws Throwable {
        checkoutPage.assertCheckoutAndCartSubtotalMatch();
    }

    @Then("^I verify \"(fee|service)\": \"(.*?)\" is present in the cart summary section$")
    public void i_verify_item_present_in_cart_summary(String itemType, String itemName) throws Throwable {
        checkoutPage.assertItemPresentInCartSummary(itemType, itemName);
    }

    @When("^I select the checkout option \"(default|paypal|with appointment|without appointment|none)\"$")
    public void i_select_the_checkout_option(String checkoutType) throws Throwable {
        cartPage.selectCheckoutNow();
        cartPage.selectActionOnPopUpCertsForRandR(ConstantsDtc.CONTINUE_TO_CHECKOUT);
        if (!Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            if (!checkoutType.equalsIgnoreCase(Constants.NONE)) {
                checkoutPage.selectCheckoutInstallType(checkoutType);
            }
        }
    }

    @When("^I select the checkout without install reason \"(.*?)\"$")
    public void i_select_the_checkout_without_install_reason(String reason) throws Throwable {
        checkoutPage.selectCheckoutWithoutInstallReason(reason);
    }

    @When("^I select the \"(default|paypal)\" payment option$")
    public void i_select_the_payment_option(String paymentType) throws Throwable {
        checkoutPage.clickPaymentType(paymentType);
    }

    @And("^I continue on to PayPal checkout$")
    public void i_continue_on_to_paypal_checkout() throws Throwable {
        checkoutPage.continueToPayPalCheckout();
    }

    @And("^I create an appointment with defaults from Checkout$")
    public void i_create_an_appointment_with_defaults_from_checkout() throws Throwable {
        checkoutPage.selectFirstOrLastAvailableApptDateTime(true);
    }

    @And("^I reserve items and complete checkout for \"([^\"]*)\"$")
    public void i_reserve_items_and_complete_checkout_for_customer(String customerType) throws Throwable {
        Customer checkoutCustomer = customer.getCustomer(customerType);
        if (!Config.isFirefox()) {
            checkoutPage.clickDefaultSubmitButton();
        }
        checkoutPage.enterCustomerInformationForCheckout(checkoutCustomer);
        checkoutPage.setRegionalTaxesFactor(checkoutCustomer);
        i_place_the_order(customerType);
    }

    @And("^I expand the cart item details section of the cart summary on the Checkout page$")
    public void i_expand_cart_item_details_section_of_cart_summary_on_checkout_page() throws Throwable {
        checkoutPage.expandCartSummaryCartItems();
    }

    @And("^I expand the fee details for the item listed in the cart summary on the Checkout page$")
    public void i_expand_fee_details_for_item_listed_in_cart_summary_on_checkout_page() throws Throwable {
        checkoutPage.expandFeeDetailsForCartItem();
    }

    @And("^I verify an appointment for a checkout with item\\(s\\) can be created for 10 business days later$")
    public void i_verify_appointment_for_checkout_with_item_can_be_created_for_x_business_days_later()
            throws Throwable {
        checkoutPage.verifyTotalNumberOfApptDaysForCheckoutWithItem();
        checkoutPage.selectFirstOrLastAvailableApptDateTime(false);
    }

    @Then("^I should see the customer information page for checkout$")
    public void i_should_see_customer_information_page_for_checkout() throws Throwable {
        checkoutPage.assertCustomerInfoPageLoaded();
        commonActions.confirmCurrentUrl(ConstantsDtc.CHECKOUT_CUSTOMER_INFO_URL, true);
    }

    @Then("^I should see install without appointment tooltip$")
    public void i_should_see_install_without_appointment_tooltip() throws Throwable {
        checkoutPage.assertInstallWithoutApptToolTip();
    }

    @And("^I verify install without appointment tooltip message$")
    public void i_verify_install_without_appointment_tooltip_message() throws Throwable {
        checkoutPage.assertInstallWithoutApptToolTipMessage();
    }

    @And("^I verify list of available appointment dates$")
    public void i_verify_list_of_available_appointment_dates() throws Throwable {
        checkoutPage.verifyTotalNumberOfApptDaysForCheckoutWithItem();
    }

    @And("^I verify unavailable time slots are disabled$")
    public void i_verify_unavailable_time_slots_are_not_enabled() throws Throwable {
        checkoutPage.assertUnavailableTimeSlotsAreDisabled();
    }

    @And("^I verify available time slots are enabled$")
    public void i_verify_available_time_slots_are_enabled() throws Throwable {
        checkoutPage.assertAvailableTimeSlotsAreEnabled();
    }

    @And("^I verify unavailable time slots are disabled and available time slots are enabled$")
    public void i_verify_unavailable_time_slots_are_not_enabled_and_available_time_slots_are_enabled() throws Throwable {
        i_verify_unavailable_time_slots_are_not_enabled();
        i_verify_available_time_slots_are_enabled();
    }

    @And("^I verify time slot hours increase from top to bottom$")
    public void i_verify_time_slot_hours_increase_from_top_to_bottom() throws Throwable {
        checkoutPage.assertTimeSlotsAscendingOrderTopToBottom();
    }

    @And("^I verify time slot list is scrollable$")
    public void i_verify_time_slot_list_is_scrollable() throws Throwable {
        checkoutPage.assertScrollableTimeSlotList();
    }

    @And("^I verify message bar with appointment day date and time$")
    public void i_verify_message_bar_with_appointment_day_date_and_time() throws Throwable {
        checkoutPage.assertAppointmentMessageBar();
    }

    @And("^I select first available appointment date$")
    public void i_select_first_available_appointment_date() throws Throwable {
        checkoutPage.selectFirstOrLastAvailableApptDateTime(true);
    }

    @And("^I select last available appointment date$")
    public void i_select_last_available_appointment_date() throws Throwable {
        checkoutPage.selectFirstOrLastAvailableApptDateTime(false);
    }

    @Then("^I select install with appointment$")
    public void i_select_install_with_appointment() throws Throwable {
        checkoutPage.clickReservationRadioButton(ConstantsDtc.INSTALL_WITH_APPOINTMENT);
    }

    @Then("^I select install without appointment$")
    public void i_select_install_without_appointment() throws Throwable {
        checkoutPage.clickReservationRadioButton(ConstantsDtc.INSTALL_WITHOUT_APPOINTMENT);
    }

    @Then("^I verify the checkout order total matches with shopping cart order total$")
    public void i_verify_the_checkout_order_total_matches_with_shopping_cart_order_total() throws Throwable {
        checkoutPage.assertCheckoutAndCartOrderTotal();
    }

    @And("^I verify the checkout sales tax matches with sales tax amount on shopping cart$")
    public void i_verify_the_checkout_sales_tax_matches_with_sales_tax_amount_on_shopping_cart() throws Throwable {
        checkoutPage.assertCheckoutAndCartSalesTax();
    }

    @And("^I verify the checkout store name matches my store on shopping cart$")
    public void i_verify_the_checkout_store_name_matches_my_store_on_shopping_cart() throws Throwable {
        checkoutPage.assertCheckoutAndCartStoreName();
    }

    @Then("^I verify the checkout appointment details reason matches checkout without install reason \"([^\"]*)\"$")
    public void i_verify_the_checkout_appointment_details_reason_matches_checkout_without_install_reason(String reason) throws Throwable {
        checkoutPage.assertCheckoutApptDetailsReason(reason);
    }


    @Then("^I verify WalkIns Welcome message displays$")
    public void i_verify_walkins_welcome_message_displays() throws Throwable {
        checkoutPage.assertWalkInsWelcomeMessage();
    }

    @Then("^I verify appointment dates and appointment times sections are displayed")
    public void i_verify_appointment_dates_and_appointment_times_sections_are_displayed() throws Throwable {
        checkoutPage.assertAppointmentSelectionDisplayed();
    }

    @Then("^I verify appointment dates start on expected date \"(in stock|on order)\"$")
    public void i_verify_appointment_dates_start_on_expected_date(String itemStockStatus) throws Throwable {
        checkoutPage.verifyFirstAppointmentDate(itemStockStatus);
    }

    @Then("^I verify the correct month and year are displayed in the Appointment Date header$")
    public void i_verify_the_correct_month_and_year_are_displayed_in_the_appointment_date_header() throws Throwable {
        checkoutPage.assertMonthYearAppointmentDateHeader();
    }

    @Then("^I verify the dates falling in different months are divided by a tab displaying month and year$")
    public void i_verify_the_dates_falling_in_different_months_are_divided_by_a_tab_displaying_month_and_year() throws Throwable {
        checkoutPage.assertMonthYearAppointmentDateDivider();
    }

    @Then("^I verify appointment timeslots are displayed$")
    public void i_verify_appointment_timeslots_are_displayed() throws Throwable {
        checkoutPage.assertAppointmentTimeListDisplayed();
    }

    @Then("^I verify the time list header date is displayed and correct$")
    public void i_verify_the_time_list_header_date_is_displayed_and_correct() throws Throwable {
        checkoutPage.assertAppointmentTimeListHeaderDate();
    }

    @Then("^I verify the \"(tax|order total)\" on the checkout page$")
    public void i_verify_tax_on_checkout(String text) throws Throwable {
        if (text.equalsIgnoreCase("tax")) {
            checkoutPage.assertTaxOnCheckout();
        } else {
            checkoutPage.assertCheckoutOrderPriceTotal();
        }
    }

    @Then("^I verify the \"(install with appointment|install without appointment)\" option is selected$")
    public void i_verify_the_with_without_appointment_option_is_selected(String appointmentOption) throws Throwable {
        checkoutPage.assertAppointmentOptionSelected(appointmentOption);
    }

    @Then("^I verify default install without appointment reason selected$")
    public void i_verify_default_install_without_appointment_reason_selected() throws Throwable {
        checkoutPage.assertInstallWithoutAppointmentSelectedReason(ConstantsDtc.DEFAULT_REASON);
    }

    @Then("^I verify the reasons listed in the reason dropdown list$")
    public void i_verify_the_reasons_listed_in_the_reason_dropdown_list() throws Throwable {
        checkoutPage.assertInstallWithoutAppointmentReasonOptions();
    }

    @Then("^I verify the reserve appointment message displayed for "
            + "\"(Not sure of my availability|Make an appointment at a later time|These items are for multiple vehicles)\"$")
    public void i_verify_the_reserve_appointment_message_displayed(String installWithoutAppointmentReason) throws Throwable {
        checkoutPage.assertReserveWithoutAppointmentMessage(installWithoutAppointmentReason);
    }

    @When("^I select Make an Appointment$")
    public void i_select_make_an_appointment() throws Throwable {
        checkoutPage.clickMakeAnAppointmentLink();
    }

    @Then("^I verify the Continue to Customer Details button is \"(enabled|disabled)\"$")
    public void i_verify_the_continue_to_customer_details_button_is_enabled_or_disabled(String enabledDisabled) throws Throwable {
        checkoutPage.verifyContinueToCustomerDetailsButtonStatus(enabledDisabled);
    }

    @Then("^I verify View Times did not display on any unavailable appointment dates$")
    public void i_verify_view_times_did_not_display_on_any_unavailable_appointment_dates() throws Throwable {
        checkoutPage.verifyViewTimesNotDisplayedForUnavailableAppointmentDate();
    }

    @Then("^I verify store is closed on Sunday$")
    public void i_verify_store_is_closed_on_Sunday() throws Throwable {
        checkoutPage.verifyStoreClosedDisplayedOnSundayAppointmentDate();
    }

    @When("^I select \"(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday)\" appointment date$")
    public void i_select_appointment_date_for_day_of_week(String dayOfWeek) throws Throwable {
        checkoutPage.selectAppointmentDate(dayOfWeek);
    }

    @Then("^I verify peak times message and peak hours for \"(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday)\"$")
    public void i_verify_peak_times_message_and_peak_hours(String dayOfWeek) {
        i_verify_peak_times_message(dayOfWeek);
        i_verify_peak_hours(dayOfWeek);
    }

    @Then("^I verify peak times message for \"(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday)\"$")
    public void i_verify_peak_times_message(String dayOfWeek) {
        checkoutPage.verifyPeakTimesMessage(dayOfWeek);
    }

    @Then("^I verify peak hours for \"(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday)\"$")
    public void i_verify_peak_hours(String dayOfWeek) {
        checkoutPage.verifyPeakHours(dayOfWeek);
    }

    @When("^I select first available appointment time within peak hours for selected date$")
    public void i_select_first_available_appointment_time_within_peak_hours_for_selected_date() throws Throwable {
        checkoutPage.selectFirstAvailablePeakHoursAppointmentTimeForSelectedDate();
    }

    @Then("^I verify service time will be longer than usual$")
    public void i_verify_service_time_will_be_longer_than_usual() throws Throwable {
        checkoutPage.verifyPeakTimeSelectedMessage();
    }

    @Then("^I verify the \"Shipping Restriction\" modal messaging as well as controls are \"(displayed|not displayed)\"$")
    public void i_verify_shipping_restriction_modal_messaging_controls_displayed(String displayStatus) throws Throwable {
        checkoutPage.verifyShippingRestrictionModalMessagingControls(displayStatus);
    }

    @When("^I select \"(Change Address|Close|Remove Items)\" from the \"Shipping Restriction\" modal$")
    public void i_select_option_from_shipping_restriction_modal(String option) throws Throwable {
        checkoutPage.selectActionFromShippingRestrictionModal(option);
    }

    @When("^I resubmit the current \"Shipping Details\" for Checkout$")
    public void i_resubmit_current_shipping_details_for_checkout() throws Throwable {
        checkoutPage.clickDefaultSubmitButton();
    }

    @Then("^I verify product \"(.*?)\" is \"(displayed|not displayed)\" in the \"Order Summary\" of the Checkout page$")
    public void i_verify_product_display_in_order_summary_of_checkout_page(String productName, String displayStatus)
            throws Throwable {
        checkoutPage.verifyProductDisplayInOrderSummary(productName, displayStatus);
    }

    @Then("^I verify need help option is displayed$")
    public void i_verify_need_help_is_displayed() throws Throwable {
        checkoutPage.assertNeedHelpLinkDisplayed();
    }

    @Then("^I verify need help popup values email and phone number are displayed$")
    public void i_verify_need_help_popup_values_are_displayed() throws Throwable {
        checkoutPage.assertNeedHelpPopUpValuesDisplayed();
    }

    @And("^I verify 'sign in to skip this step' is \"(displayed|not displayed)\" in Checkout Page$")
    public void i_verify_sign_in_to_skip_this_step(String text) throws Throwable {
        checkoutPage.assertSignInToSkipThisStepDisplay(text);
    }

    @When("^I select 'sign in to skip this step'$")
    public void i_select_Sign_In_to_Skip_this_step() throws Throwable {
        checkoutPage.clickSignInToSkipThisStep();
    }

    @Then("^I verify \"(.*?)\" is displayed on global header$")
    public void i_verify_user_name_is_displayed(String userName) throws Throwable {
        checkoutPage.assertUserNameDisplayedOnGlobalHeader(userName);
    }

    @And("^I verify \"(.*?)\" values for \"(.*?)\" are now pre-populated")
    public void i_verify_customer_values_are_now_prepopulated(String customerDetails, String customer)
            throws Throwable {
        checkoutPage.assertPrePopulatedShippingDetails(customerDetails, customer);
    }

    @When("^I select \"(ROPIS|BOPIS)\" option$")
    public void i_select_ropis_or_bopis_option(String selection) throws Throwable {
        checkoutPage.selectPickUpInStoreOption(selection);
    }

    @Then("^I verify product quantity for \"(.*?)\" on checkout page")
    public void i_verify_product_quantity_on_checkout_page(String productName) throws Throwable {
        checkoutPage.verifyProductQuantity(productName, CommonActions.productQuantity);
    }

    @And("^I verify the \"(Instant Savings|Mail In Rebate)\" for set displayed on \"(.*?)\" page$")
    public void i_verify_instant_savings_for_set_displayed_on_page(String promotion, String page) throws Throwable {
        checkoutPage.assertPromotionDisplayOrder();
        if (promotion.contains(ConstantsDtc.MAIL_IN_REBATE)) {
            checkoutPage.assertMailInRebate(page);
        } else {
            checkoutPage.assertInstantSavings(page);
        }
    }

    @And("^I verify \"(Reserve now, pickup in-store|Pay now, pick up in-store)\" is \"(displayed|not displayed)\"$")
    public void i_verify_the_bopis_ropis_display(String text, String displayStatus) throws Throwable {
        if (text.equalsIgnoreCase(ConstantsDtc.ROPIS_LABEL)) {
            checkoutPage.assertReserveNowPickupInStoreDisplay(displayStatus);
        } else if (text.equalsIgnoreCase(ConstantsDtc.BOPIS_LABEL)) {
            checkoutPage.assertPayNowPickupInStoreDisplay(displayStatus);
        }
    }

    @When("^I expand 'Split credit card payment'$")
    public void i_expand_split_credit_card_payment() throws Throwable {
        checkoutPage.expandSplitCreditCardPayment();
    }

    @When("^I reduce the amount paid by \"(.*?)\"$")
    public void i_reduce_the_amount_paid(String amount) throws Throwable {
        checkoutPage.reducePayment(amount);
    }

    @When("^I save card details$")
    public void i_save_card_details() throws Throwable {
        checkoutPage.clickSaveCardDetails();
    }

    @When("^I expand 'Someone else will pick up my order'$")
    public void i_select_someone_else_will_pick_up_my_order() throws Throwable {
        checkoutPage.expandSomeoneElseWillPickupMyOrder();
    }

    @When("^I scroll time slot list to reach \"(Fullday|Partial)\" Holiday$")
    public void i_scroll_time_slot_list_to_reach_holiday(String holidayType) throws Throwable {
        checkoutPage.scrollTimeSlotToHoliday(holidayType);
    }

    @Then("^I verify graph showing store schedule is \"(displayed|not displayed)\"$")
    public void i_verify_graph_showing_store_schedule_display(String displayExpectation) throws Throwable {
        checkoutPage.verifyGraphShowingStoreScheduleDisplay(displayExpectation);
    }

    @And("^I verify the 'First Available Appointment Time' message is \"(displayed|not displayed)\"$")
    public void i_verify_first_available_appointment_time_message_display(String displayExpectation) throws Throwable {
        checkoutPage.verifyFirstAvailableAppointmentTimeMessageDisplay(displayExpectation);
    }

    @And("^I enter invalid CVN number of \"(\\d{3,4})\"$")
    public void i_enter_invalid_cvn_number(String invalidCvn) throws Throwable {
        checkoutPage.enterInvalidCvnNumber(invalidCvn);
    }

    @Then("^I verify the 'Unable to process the card' messaging$")
    public void i_verify_the_unable_to_process_the_card_messaging() throws Throwable {
        checkoutPage.verifyUnableToProcessCardMessaging();
    }

    @When("^I enter the valid CVN number for card: \"(.*?)\"$")
    public void i_enter_valid_cvn_number_for_card(String cardType) throws Throwable {
        checkoutPage.enterValidCvnNumberForCard(cardType);
    }

    @When("^I 'Edit card details' for the \"(primary|secondary)\" credit card$")
    public void i_edit_card_details_for_credit_card(String cardRole) throws Throwable {
        checkoutPage.editCardDetailsForCreditCard(cardRole);
    }
}