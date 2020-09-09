package ovc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.CashManagementPage;
import ovc.pages.CheckoutMenuPage;
import ovc.pages.HomePage;
import utilities.Driver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aaronbriel on 8/7/17.
 */
public class CashManagementPageSteps {

    private CashManagementPage cashManagementPage;
    private CheckoutMenuPage checkoutMenuPage;
    private OvcData ovcData, featureData;

    public CashManagementPageSteps(Driver driver) {
        cashManagementPage = new CashManagementPage(driver);
        checkoutMenuPage = new CheckoutMenuPage(driver);
        ovcData = new OvcData();
    }

    @And("^I select \"(Cash|Personal Check|Card Payment|Money Order|Travelers Check)\" Tender and enter amount " +
            "\"(.*?)\" with Serial Number \"(.*?)\"$")
    public void i_enter_reimbursement_amount(String paymentType, String amount, String serialNumber)
            throws Throwable {
        if (amount.contains(HomePage.SUBTOTAL))
            amount = HomePage.orderSubTotal + amount.split("\\+")[1];
        cashManagementPage.enterPayment(paymentType, amount, serialNumber);
    }

    @And("^I enter \"(.*?)\" into the comments field$")
    public void i_enter_comments(String comments) throws Throwable {
        cashManagementPage.enterDialogComments(comments);
    }

    @And("^I enter \"(.*?)\" into the \"(.*?)\" \"(Select Tender|Default|Form Group|Scope)\" input field$")
    public void i_enter_input_text(String inputValue, String inputField, String dialogType) throws Throwable {
        cashManagementPage.enterInput(dialogType, inputField, inputValue);
    }

    @When("^I enter \"(.*?)\" into the \"(.*?)\" \"(Select Tender|Default|Form Group|Scope)\" input field for feature \"(.*?)\"$")
    public void i_enter_input_text_for_feature(String dataField, String inputField, String dialogType, String feature)
            throws Throwable {
        String inputValue = ovcData.getFieldValue(feature, dataField);
        cashManagementPage.enterInput(dialogType, inputField, inputValue);
    }

    @And("^I select reason \"(.*?)\"$")
    public void i_select_reason(String reason) throws Throwable {
        cashManagementPage.selectDropdown(CashManagementPage.REASON_LABEL, reason);
    }

    @And("^I select equipment type \"(.*?)\"$")
    public void i_select_equipment_type(String equipmentType) throws Throwable {
        cashManagementPage.selectDropdown(CashManagementPage.EQUIPMENT_TYPE_LABEL, equipmentType);
    }

    @And("^I select approximate year aquired \"(.*?)\"$")
    public void i_select_approx_year_aquired(String approxYearAquired) throws Throwable {
        cashManagementPage.selectDropdown(CashManagementPage.APPROX_YEAR_ACQUIRED_LABEL, approxYearAquired);
    }

    @And("^I verify \"(.*?)\" values for customer in feature \"(.*?)\" are pre-populated in the dialog")
    public void i_verify_customer_values_are_prepopulated_dialog(String customerValueTypes, String feature)
            throws Throwable {
        cashManagementPage.verifyReadOnlyCustomerValuesInDialog(feature, customerValueTypes);
    }

    @And("^I verify City, State and ZIP Code values for \"(AR|non AR)\" customer in feature \"(.*?)\" are pre-populated in the dialog")
    public void i_verify_customer_city_state_zip_values_are_prepopulated_in_dialog
            (String customerType, String feature) throws Throwable {
        cashManagementPage.verifyCityStateZipReadOnlyCustomerValuesInDialog(customerType, feature);
    }

    @And("^I verify the \"(.*?)\" \"(.*?)\" input field is pre-populated with \"(.*?)\" in the dialog")
    public void i_verify_value_is_prepopulated_in_dialog(String inputLabel, String inputType, String inputValue)
            throws Throwable {
        //NOTE: ALM requirement is simply to check that it is pre-populated with a value. Since date appears to be last
        //transaction date it would need to be extracted in order to validate actual value (like other fields). This
        //is outside of the scope of the test requirements.
        if (inputValue.equalsIgnoreCase(Constants.DATE)) {
            cashManagementPage.verifyCustomerValueInDialogIsPopulated(inputType, inputLabel);
        } else {
            cashManagementPage.verifyCustomerValueInDialog(inputType, inputLabel, inputValue);
        }
    }

    @And("^I verify the \"(.*?)\" \"(.*?)\" input field is pre-populated with the extracted subtotal")
    public void i_verify_cash_is_prepopulated_with_extracted_subtotal_in_dialog(String inputLabel, String inputType)
            throws Throwable {
        cashManagementPage.verifyCustomerValueInDialog(inputType, inputLabel, HomePage.orderSubTotal);
    }

    @And("^I verify the Store Location input field is pre-populated with store from feature \"(.*?)\" in the dialog")
    public void i_verify_store_location_is_prepopulated_in_dialog(String feature)
            throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String storeLocation = featureData.StoreLocation;
        cashManagementPage.verifyCustomerValueInDialog(ConstantsOvc.SELECT_TENDER, ConstantsOvc.STORE_LOCATION, storeLocation);
    }

    @And("^I enter invoice \"(.*?)\" and click the add button$")
    public void i_enter_invoice_and_click_add_button(String inputValue) throws Throwable {
        cashManagementPage.enterInput(CashManagementPage.ADD_CURRENCY_INPUT_TYPE,
                CashManagementPage.INVOICE_LABEL, inputValue);
        cashManagementPage.clickAddButton();
    }

    @And("^I enter a trip from \"(.*?)\" to \"(.*?)\" and click the add button$")
    public void i_enter_trip_and_click_add(String from, String to) throws Throwable {
        cashManagementPage.enterTrip(from, to);
        cashManagementPage.clickAddButton();
    }

    @And("^I enter \"([^\"]*)\" into the \"([^\"]*)\" Manual Denomination Count text field$")
    public void i_enter_into_the_manual_denomination_count_text_field(String dollars, String row) throws Throwable {
        cashManagementPage.enterTextCashDenominationTable(dollars, row);
    }

    @And("^I verify the NSF letter options are displayed$")
    public void i_verify_nsf_letter_options_displayed() throws Throwable {
        cashManagementPage.assertNsfOptionsDisplayed();
    }

    @When("^I select the \"(YES|NO)\" NSF letter option$")
    public void i_select_nsf_option(String option) throws Throwable {
        cashManagementPage.selectNsfOption(option);
    }

    @And("^I enter \"(.*?)\" with \"(.*?)\" and \"(.*?)\" and click the add button$")
    public void i_enter_add_item_data_and_click_add(String label, String firstItem, String secondItem)
            throws Throwable {
        if (label.equalsIgnoreCase(CashManagementPage.ADD_TRIP)) {
            cashManagementPage.enterTrip(firstItem, secondItem);
        } else {
            cashManagementPage.enterItem(firstItem, secondItem);
        }
        cashManagementPage.clickAddButton();
    }

    @And("^I input amount into the \"(MONEY ORDER|CASH)\" Quick Deposit popup$")
    public void i_input_amount_into_the_quick_deposit_popup(String field) throws Throwable {
        cashManagementPage.quickDepositAmounts(field);
    }

    @Then("^I verify \"([^\"]*)\" appears on the Quick Deposit receipt Details$")
    public void i_verify_appears_on_the_quick_deposit_receipt_details(String countTenders) throws Throwable {
        cashManagementPage.quickDepositReceipt(countTenders);
    }

    @Then("^I verify \"Float left in Till\" appears on the Nightly Deposit receipt Details$")
    public void i_verify_float_left_in_till_appears_on_the_nightly_deposit_receipt_details() throws Throwable {
        cashManagementPage.verifyNightlyDepositReceipt();
    }

    @And("^I verify \"(.*?)\" values for vendor in feature \"(.*?)\" are pre-populated in the dialog")
    public void i_verify_vendor_values_are_prepopulated_dialog(String vendorValueTypes, String feature)
            throws Throwable {
        cashManagementPage.verifyReadOnlyVendorValuesInDialog(feature, vendorValueTypes);
    }

    @Then("^I verify the denomination dialog contains \"([^\"]*)\"$")
    public void i_verify_the_denomination_dialog_contains_text(String text) throws Throwable {
        cashManagementPage.assertDenominationDialogTextContains(text);
    }

    @And("^I click the add button$")
    public void i_click_add_button() throws Throwable {
        cashManagementPage.clickAddButton();
    }

    @When("^I enter \"(Today's Date)\" into the Check Date input field$")
    public void i_enter_date_into_the_check_date_input_field(String date) throws Throwable {
        String timeStamp = new SimpleDateFormat("MM.dd.yyyy").format(new Date());
        cashManagementPage.enterCheckDateInput(timeStamp);
    }

    @And("^I enter tender amount of \"(.*?)\" for the Cash Management Pay In$")
    public void i_enter_tender_amount_for_the_cash_management_pay_in(String amount) throws Throwable {
        cashManagementPage.enterPayInInput(amount);
    }
    
    @When("^I enter the data about the tender in money order popup for feature \"([^\"]*)\"$")
    public void i_enter_the_data_about_the_tender_in_money_order_popup_for_feature(String feature) throws Throwable {
    	featureData = ovcData.getOvcData(feature);
        String routingNumber = featureData.RoutingNumber;
        String accountNumber = featureData.AccountNumber;
        String checkNumber = featureData.CheckNumberInteger;
        String issuingInstitution = featureData.IssuingInstitution;
        
        checkoutMenuPage.enterTenderDetailsForMoneyOrder(routingNumber, accountNumber, checkNumber, issuingInstitution);
    }
    
    @When("^I select the first available voucher date$")
    public void i_select_the_first_available_voucher_date() throws Throwable {
    	cashManagementPage.selectVoucherDate();
    }
    
    @Then("^I save the barcode number from pay in popup page$")
    public void i_save_the_barcode_number_from_pay_in_popup_page() throws Throwable {
    	cashManagementPage.extractBarCodePayIn();
    	cashManagementPage.storeOvcBarCode();
    }
    
    @When("^I search for the previously saved voucher# in the \"([^\"]*)\" input field$")
    public void i_search_for_the_previously_saved_voucher_in_the_input_field(String text) throws Throwable {
    	cashManagementPage.searchForVoucherByBarCodeNumber(text);
    }
}