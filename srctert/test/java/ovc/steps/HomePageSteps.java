package ovc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.CheckoutMenuPage;
import ovc.pages.CommonActions;
import ovc.pages.HomePage;
import ovc.pages.VTVPage;
import ovc.utilities.OvcExcel;
import utilities.Driver;

/**
 * Created by eseverson on 6/29/2017.
 */
public class HomePageSteps {


    private HomePage homePage;
    private Driver driver;
    private CommonActions commonActions;
    private VTVPage vtvPage;
    private OvcExcel ovcExcel;
    private OvcData ovcData, featureData;
    private float subtotal = 0;
    public String currentBalanceTotal;
    public String currentSubTotal;
    private CheckoutMenuPage checkoutMenuPage;

    public HomePageSteps(Driver driver) {
        this.driver = driver;
        homePage = new HomePage(driver);
        commonActions = new CommonActions(driver);
        vtvPage = new VTVPage(driver);
        ovcData = new OvcData();
        ovcExcel = new OvcExcel();
        checkoutMenuPage = new CheckoutMenuPage(driver);
    }

    @Then("^I verify the \"([^\"]*)\" of the item for feature \"([^\"]*)\" appears on the home page$")
    public void i_verify_the_item_appears_on_the_home_page(String dataField, String feature) throws Throwable {
        String value = ovcData.getFieldValue(feature, dataField);
        homePage.verifyItemNumberAppearsOnVR(value);
    }

    @And("^I select the list item \"([^\"]*)\"$")
    public void i_select_the_list_item(String text) throws Throwable {
        commonActions.selectListElementWithText(HomePage.listGroupBy, text);
    }

    @And("^I extract the subtotal from the home page$")
    public void i_extract_the_subtotal_on_the_home_page() throws Throwable {
        currentSubTotal = homePage.extractCurrentSubtotal();
    }

    @And("^I extract the \"Balance\" total from the home page$")
    public void i_extract_the_balance_total_on_the_home_page() throws Throwable {
        currentBalanceTotal = homePage.extractCurrentBalanceTotal();
    }

    @And("^I extract the \"Subtotal\" balance from the home page$")
    public void i_extract_the_subtotal_balance_on_the_home_page() throws Throwable {
        currentBalanceTotal = homePage.extractCurrentSubtotal();
    }

    @And("^I save the order receipt after verifying that the order number is visible$")
    public void i_save_the_order_receipt_after_verifying_that_the_order_number_is_visible() throws Throwable {
        homePage.verifyReceiptDisplaysWithBarcode();
        homePage.pullOrderReceiptText();
        homePage.storeOvcOrderNumber();
    }

    @Then("^I verify tender amount equals the order price total$")
    public void i_verify_tender_amount_equals_order_price_total() throws Throwable {
        homePage.assertTenderAmount(currentBalanceTotal);
    }

    @Then("^I verify that the vehicle description for feature \"([^\"]*)\" appears on the home page$")
    public void i_verify_that_the_vehicle_description_appears_on_the_home_page(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String vehicleDescription = featureData.CustomerVehicle;
        homePage.assertVehicleDescription(vehicleDescription);
    }

    @Then("^I verify that the AR Customer vehicle description for feature \"([^\"]*)\" appears on the home page$")
    public void i_verify_that_the_ar_customer_vehicle_description_appears_on_the_home_page(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String vehicleDescription = featureData.ArCustomerVehicle;
        homePage.assertVehicleDescription(vehicleDescription);
    }

    @Then("^I verify that the \"(ReturnVehicle|NewVehicle)\" description for feature \"([^\"]*)\" appears on the home page$")
    public void i_verify_that_the_new_vehicle_description_appears_on_the_home_page(String dataField, String feature) throws Throwable {
        String vehicleDescription = ovcData.getFieldValue(feature, dataField);
        homePage.assertVehicleDescription(vehicleDescription);
    }

    @And("^I verify that the vehicle description for vehicle \"(.*?)\" and feature \"(.*?)\" appears on the home page$")
    public void i_verify_that_the_vehicle_description_for_vehicle_and_feature_appears_on_the_home_page(String dataField, String feature) throws Throwable {
        String vehicleDescription = ovcData.getFieldValue(feature, dataField);
        homePage.assertVehicleDescription(vehicleDescription);
    }

    @And("^I search for transaction details with the previous receipt number$")
    public void i_search_for_transaction_with_prev_receipt_number() throws Throwable {
        homePage.searchForTransactionByReceiptNumber();
    }

    @And("^I search transaction details for the customer from feature \"(.*?)\"$")
    public void i_search_for_transaction_details_for_the_customer_from_feature(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String firstName = featureData.FirstName;
        String lastName = featureData.LastName;
        homePage.searchForTransactionByName(firstName, lastName);
    }

    @Then("^I verify transaction details display for previous receipt number$")
    public void i_verify_transaction_details_display_for_prev_receipt_number() throws Throwable {
        homePage.verifyTransactionDetailsDisplayForReceiptNumber();
    }

    @And("^I verify the \"(added|existing)\" vehicle for feature \"([^\"]*)\" appears in the customer vehicle list$")
    public void i_verify_the_vehicle_appears_in_the_customer_vehicle_list(String type, String feature)
            throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String vehicle = featureData.CustomerVehicle;
        if (type.equalsIgnoreCase(ConstantsOvc.ADDED))
            vehicle = featureData.AddedVehicle;
        homePage.verifyVehicleInVehicleContainer(vehicle);
    }

    @And("^I select the vehicle from the vehicle pill on the home page$")
    public void i_select_the_vehicle_from_the_vehicle_pill_on_the_home_page() throws Throwable {
        homePage.selectVehicleFromCustomerVehiclePill();
    }

    @And("^I select the transaction with the previous receipt number$")
    public void i_select_the_transaction_with_the_previous_receipt_number() throws Throwable {
        homePage.selectTransactionWithPreviousReceiptNumber();
    }

    @And("^I verify \"([^\"]*)\" appears on the Till Spot Check popup$")
    public void i_verify_text_appears_on_the_till_spot_check_popup(String text) throws Throwable {
        if (text.equalsIgnoreCase(ConstantsOvc.DEFAULT_VALUES)) {
            homePage.verifyTextTillSpotCheckWindow(homePage.SPOT_CHECK_VALUES);
        } else if (text.equalsIgnoreCase(ConstantsOvc.TRAINING_DEFAULT_VALUES)) {
            homePage.verifyTextTillSpotCheckWindow(homePage.TRAINING_SPOT_CHECK_VALUES);
        } else {
            homePage.verifyTextTillSpotCheckWindow(text);
        }
    }

    @When("^I select customer for feature \"([^\"]*)\" on home page$")
    public void i_select_customer_on_home_page(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String name = featureData.CustomerName;
        homePage.clickCustomerName(name);
    }

    @Then("^I verify the Virtual Receipt \"(total|previous balance total)\" price for feature \"(.*?)\"$")
    public void i_verify_the_virtual_receipt_total_price_is(String balanceType, String feature) throws Throwable {
        if (balanceType.equalsIgnoreCase(ConstantsOvc.PREVIOUS_BALANCE_TOTAL)) {
            homePage.assertCurrentBalanceTotal(currentBalanceTotal);
        } else {
            featureData = ovcData.getOvcData(feature);
            String totalPrice = featureData.TotalPrice;
            homePage.assertCurrentBalanceTotal(totalPrice);
        }
    }

    @And("^I enter Manager Approval credentials in to the window and click \"(Approve|Cancel)\"$")
    public void i_enter_manager_approval_credentials_in_to_the_window_and_click_button(String selection) throws Throwable {
        homePage.enterManagerApprovalDetails(selection);
    }

    @And("^I modify the price of \"(.*?)\" for feature \"(.*?)\" on the Virtual Receipt to \"(.*?)\"$")
    public void i_modify_price_for_item_number_on_virtual_receipt_to_new_price(String dataField, String feature, String newPrice)
            throws Throwable {
        String itemNumber = ovcData.getFieldValue(feature, dataField);
        homePage.modifyPriceForItemNumberOnVirtualReceipt(itemNumber, newPrice);
    }

    @Then("^I verify the VTV icon color is \"([^\"]*)\"$")
    public void i_verify_the_vtv_icon_color(String color) throws Throwable {
        vtvPage.verifyVTVHeaderIconColor(color);
    }

    @And("^I adjust the item quantity of \"([^\"]*)\" for feature \"([^\"]*)\" to \"([^\"]*)\"")
    public void i_adjust_the_item_quantity(String dataField, String feature, String quantity) throws Throwable {
        String item = ovcData.getFieldValue(feature, dataField);
        homePage.adjustItemQuantity(item, quantity);
    }

    @Then("^I verify the quantity of \"([^\"]*)\" for feature \"([^\"]*)\" was updated to \"([^\"]*)\"$")
    public void i_verify_the_quantity_for_item_was_updated_to_number(String dataField, String feature, String quantity)
            throws Throwable {
        String item = ovcData.getFieldValue(feature, dataField);
        homePage.verifyAdjustedItemQuantity(item, quantity);
    }

    @And("^I select the \"(Competitor Price Match|Employee Discount|Damaged Product|Substitution|Customer Satisfaction)\" option on the Price Override popup$")
    public void i_select_option_on_price_override_popup(String overrideOption) throws Throwable {
        homePage.selectPriceOverrideOption(overrideOption);
    }

    @Then("^I verify the \"Balance\" total matches \"(.*?)\"$")
    public void i_verify_balance_total_matches_amount(String expectedAmount) throws Throwable {
        homePage.verifyBalanceTotalMatchesAmount(currentBalanceTotal, expectedAmount);
    }

    @When("^I enter the Gift Certificate number for feature \"(.*?)\"$")
    public void i_enter_gift_certificate_number(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String certNumber = featureData.GiftCertificateNumber;
        homePage.enterGiftCertificateNumber(certNumber);
    }

    @Then("^I verify \"(.*?)\" for feature \"(.*?)\" appears on the Virtual Receipt$")
    public void i_verify_item_appears_on_virtual_receipt(String dataField, String feature) throws Throwable {
        String item = ovcData.getFieldValue(feature, dataField);
        homePage.verifyItemAppearsOnVirtualReceipt(item);
    }

    @When("^I enter \"(.*?)\" for feature \"(.*?)\" in the article search box$")
    public void i_enter_text_in_the_article_search_box(String dataField, String feature) throws Throwable {
        String textForSearch = ovcData.getFieldValue(feature, dataField);
        homePage.searchForArticle(textForSearch);
    }

    @And("^I select the red \"x\" by the total dollar amount$")
    public void i_select_the_red_x_by_the_total_dollar_amount() throws Throwable {
        homePage.selectTotalDollarX();
    }

    @And("^I select the red \"x\" for the line of \"(.*?)\" for feature \"(.*?)\"$")
    public void i_select_the_red_x_for_line_article_for_feature(String dataField, String feature) throws Throwable {
        String articleNumber = ovcData.getFieldValue(feature, dataField);
        homePage.selectArticleNumberX(articleNumber);
    }

    @Then("^I verify the red \"x\" not visible for the line of \"(.*?)\" for feature \"(.*?)\"$")
    public void i_verify_the_red_x_not_visible_for_the_line_of_article(String dataField, String feature) throws Throwable {
        String articleNumber = ovcData.getFieldValue(feature, dataField);
        homePage.verifyArticleNumberXNotVisible(articleNumber);
    }

    @And("^I verify that item \"(.*?)\" for feature \"(.*?)\" is no longer on the VR$")
    public void i_verify_that_item_is_no_longer_on_the_VR(String dataField, String feature) throws Throwable {
        String articleNumber = ovcData.getFieldValue(feature, dataField);
        homePage.verifyArticleRemovedFromVR(articleNumber);
    }

    @Then("^I verify the appointment date and time appear on the order receipt$")
    public void i_verify_appointment_date_and_time_appear_on_order_receipt() throws Throwable {
        homePage.verifyApptDateTimeAppearOnReceipt();
    }

    @Then("^I verify tender amount equals expected value of: \"(.*?)\"$")
    public void i_verify_tender_amount_equals_expected_value(String expectedAmount) throws Throwable {
        homePage.assertTenderAmount(expectedAmount);
    }

    @Then("^I verify \"([^\"]*)\" is displayed on the order receipt$")
    public void i_verify_text_is_displayed_on_order_receipt(String expectedText) throws Throwable {
        homePage.verifyTextAppearsOnReceipt(expectedText);
    }

    @And("^I select the word \"Total\" on the Virtual Receipt$")
    public void i_select_the_word_total_on_the_virtual_receipt() throws Throwable {
        homePage.selectDiscountLinkOnVR();
    }

    @And("I enter a discount value of \"(.*?)\" into the order discount textbox")
    public void i_enter_a_discount_value_of_number_into_the_order_discount_textbox(String value) throws Throwable {
        if (value.equalsIgnoreCase(ConstantsOvc.ITEM_PRICE)) {
            homePage.enterDiscountValue(HomePage.itemPrice);
        } else if (value.equalsIgnoreCase(ConstantsOvc.SUBTOTAL)) {
            homePage.enterDiscountValue(String.valueOf(currentSubTotal));
        } else {
            homePage.enterDiscountValue(value);
        }
    }

    @And("I select the price toggle to set it to \"(Dollar|Percent)\"")
    public void i_select_price_toggle_to_set_to_selection(String selection) throws Throwable {
        homePage.selectPriceTypeToggle(selection);
    }

    @Then("I verify the discount type is toggled to \"(Dollar|Percent)\" off")
    public void i_verify_the_discount_type_is_toggled_to_value_off(String selection) throws Throwable {
        homePage.verifyPriceTypeToggle(selection);
    }

    @When("^I select the \"([^\"]*)\" previous transaction on the bottom of the Home page$")
    public void i_select_previous_transaction_at_the_bottom_of_the_home_page(int transactionIndex) throws Throwable {
        homePage.selectPreviousTransactionFromBottomBar(transactionIndex);
    }

    @And("^I verify the order receipt contains the words: \"([^\"]*)\"$")
    public void i_verify_the_order_receipt_contains_text(String text) throws Throwable {
        homePage.assertVRTextContainsPhrase(text);
    }

    @And("^I save the order number to the \"(E2E|TLog)\" excel$")
    public void i_save_the_order_number_to_excel(String file) throws Throwable {
        homePage.pullOrderReceiptText();
        String excelTransactionData = ovcExcel.formatExcelWriteData(HomePage.orderNumber);
        homePage.storeOvcOrderNumberExcel(file, excelTransactionData);
    }

    @Then("^I verify the dialog contains extracted balance total$")
    public void i_verify_the_dialog_contains_text() throws Throwable {
        commonActions.assertDialogTextContains(currentBalanceTotal);
    }

    @And("^I close out the Fitment Qualifiers dialog$")
    public void i_close_out_the_fitment_qualifiers_dialog() throws Throwable {
        homePage.closeFitmentQualifiersDialog();
    }

    @And("^I enter a target time for CSL over an hour in the future$")
    public void i_enter_a_target_time_for_csl_over_an_hour_in_the_future() throws Throwable {
        homePage.enterCSLTargetTime();
    }

    @And("^I handle the Credit Card payment popups to get to Checkout options$")
    public void i_handle_credit_card_payment_popups_to_get_to_checkout_options() throws Throwable {
        homePage.handleCreditCardPopupsUntilCheckoutOptions();
    }

    @And("^I verify the Yellow Warning triangle \"(Does|Does Not)\" appear in the row with \"([^\"]*)\" for feature \"([^\"]*)\"$")
    public void i_verify_the_yellow_warning_triangle_appears_in_the_row_with_item(String decision, String dataField,
                                                                                  String feature) throws Throwable {
        String articleNumber = ovcData.getFieldValue(feature, dataField);
        if (decision.equals(ConstantsOvc.DOES)) {
            homePage.assertVRContainsWarning(articleNumber);
        } else if (decision.equals(ConstantsOvc.DOES_NOT)) {
            homePage.assertVRDoesNotContainsWarning(articleNumber);
        }
    }

    @And("^I select the \"(.*?)\" description for the Home page feature")
    public void i_select_the_vehicle_description_on_the_home_page(String dataField) throws Throwable {
        String vehicle = ovcData.getFieldValue(ConstantsOvc.FITMENT, dataField);
        homePage.selectCurrentlySelectedVehicle(vehicle);
    }

    @And("^I fill out the check payment details for feature \"(.*?)\"$")
    public void i_fill_out_the_check_payment_details(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String phoneNumber = featureData.Phone;
        String licenseNumber = featureData.LicenseNumber;
        String licenseState = featureData.LicenseState;
        String licenseExpiration = featureData.LicenseExpiration;
        String routingNumber = featureData.RoutingNumber;
        String accountNumber = featureData.AccountNumber;
        String checkNumber = featureData.CheckNumberInteger;

        homePage.fillCheckPaymentDetails(phoneNumber, licenseNumber, licenseState, licenseExpiration, routingNumber, accountNumber, checkNumber);
    }

    @And("^I fill out the business check payment details for feature \"(.*?)\"$")
    public void i_fill_out_the_business_check_payment_details(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String businessName = featureData.BusinessName;
        String phoneNumber = featureData.Phone;
        String routingNumber = featureData.RoutingNumber;
        String accountNumber = featureData.AccountNumber;
        String checkNumber = featureData.CheckNumberInteger;

        homePage.fillBusinessCheckPaymentDetails(businessName, phoneNumber, routingNumber, accountNumber, checkNumber);
    }

    @And("^I enter \"([^\"]*)\" into the Flat Repair dialog input$")
    public void i_enter_into_the_flat_repair_dialog_input(String amount) throws Throwable {
        commonActions.enterFlatRepairDialogPrice(amount);
    }

    @And("^I leave the POS inactive for over a minute$")
    public void i_leave_the_pos_inactive_for_over_a_minute() throws Throwable {
        homePage.inactivePOSSleep();
    }

    @And("^I toggle the CSL Options popup Send Text Message switch$")
    public void i_toggle_the_csl_options_send_text_message_toggle_switch() throws Throwable {
        commonActions.toggleOnOffSwitchOnCSLOptionsPopup();
    }

    @And("^I select the mobile number on the CSL options popup$")
    public void i_select_the_mobile_number_on_the_csl_options_popup() throws Throwable {
        commonActions.selectPhoneNumberCSLPopup();
    }

    @And("^I verify the phrase \"([^\"]*)\" appears on the Virtual Receipt in the color \"([^\"]*)\"$")
    public void i_verify_the_phrase_appearson_vr_in_color(String text, String color) throws Throwable {
        homePage.verifyTextAndColorOnVR(text, color);
    }

    @And("I extract the subtotal of item \"([^\"]*)\" for feature \"([^\"]*)\"$")
    public void i_extract_subtotal_for_item(String dataField, String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String itemNumber = ovcData.getFieldValue(feature, dataField);
        subtotal += homePage.extractSubtotalForItem(itemNumber);
    }

    @And("^I verify actual subtotal is \"([^\"]*)\" less than the extracted subtotal$")
    public void i_verify_subtotal_contains_discount(String discountAmountString) throws Throwable {
        float listedSubtotal = homePage.extractSubtotal();
        float discountAmount = Float.parseFloat(discountAmountString);
        homePage.assertDiscountInSubtotal(listedSubtotal, subtotal, discountAmount);
    }

    @Then("^I verify subtotal for extracted item equals \"([^\"]*)\"$")
    public void i_verify_subtotal_for_extracted_item_equals(float itemSubtotal) throws Throwable {
        homePage.assertExtractedItemSubtotal(subtotal, itemSubtotal);
    }

    @Then("^I verify the Virtual Receipt balance total is \"([^\"]*)\"$")
    public void i_verify_the_virtual_receipt_balance_is(String price) throws Throwable {
        homePage.assertBalanceTotalContains(price);
    }

    @Then("^I verify the new subtotal reflects the change from \"([^\"]*)\"$")
    public void i_verify_subtotal_change_from_new_price(String newPrice) throws Throwable {
        float newSubtotal = Float.parseFloat(currentSubTotal) - subtotal + Float.parseFloat(newPrice);
        homePage.assertSubtotalNewPrice(newSubtotal);
    }

    @Then("^I verify that the balance has \"([^\"]*)\" less than the extracted balance")
    public void i_verify_balance_has_amount_less_than_extracted(String certificate) throws Throwable {
        homePage.assertCertificateAppliedToBalance(currentBalanceTotal, certificate);
    }

    @When("^I extract the price of the \"([^\"]*)\" for feature \"([^\"]*)\"$")
    public void i_extract_the_price_of_the_for_feature(String dataField, String feature) throws Throwable {
        String itemNumber = ovcData.getFieldValue(feature, dataField);
        HomePage.itemPrice = homePage.extractPriceForItemNumberOnVirtualReceipt(itemNumber);
    }

    @And("^I wait for the OVC preloader to be no longer visible$")
    public void i_wait_for_the_ovc_preloader_to_be_no_longer_visible() throws Throwable {
        driver.waitForPageToLoad();
        driver.waitForElementNotVisible(CommonActions.pagePreLoaderBy);
    }

    @And("^I check out using the cash Payment Method$")
    public void i_check_out_using_the_cash_payment_method() throws Throwable {
        currentBalanceTotal = homePage.extractCurrentBalanceTotal();
        commonActions.selectButtonWithText(ConstantsOvc.HOME, ConstantsOvc.CHECKOUT);
        commonActions.selectLinkWithText(ConstantsOvc.CASH);
        homePage.assertTenderAmount(currentBalanceTotal);
        commonActions.selectButtonWithText(ConstantsOvc.POPUP, Constants.NEXT);
    }

    @Then("^I verify promotion summary alert appears on the Virtual Receipt$")
    public void i_verify_promotion_summary_alert_appears_on_the_virtual_receipt() throws Throwable {
        homePage.verifyPromoSummaryAlert();
    }

    @Then("^I select the Add New vehicle button$")
    public void i_select_add_new_vehicle_button() throws Throwable {
        homePage.clickAddNewVehicle();
    }
    
    @When("^I change the tender amount to the extracted balance total$")
    public void i_change_tender_amount_to_extracted_balance_total() throws  Throwable {
         checkoutMenuPage.changeTenderAmount(currentBalanceTotal);
    }
     
}