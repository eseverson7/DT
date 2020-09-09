package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.CommonActions;
import ovc.pages.CustomerProfilePage;
import ovc.pages.CustomerPage;
import ovc.pages.ParentElementsPage;
import ovc.pages.HomePage;
import utilities.Driver;

/**
 * Created by eseverson on 9/13/2017.
 */
public class CustomerProfilePageSteps {

    private CustomerProfilePage customerProfilePage;
    private CommonActions commonActions;
    private CustomerPage customerPage;
    private OvcData ovcData, featureData;
    private HomePage homePage;
    public  String timestamp;

    public CustomerProfilePageSteps(Driver driver) {
        customerProfilePage = new CustomerProfilePage(driver);
        commonActions = new CommonActions(driver);
        customerPage = new CustomerPage(driver);
        ovcData = new OvcData();
        homePage = new HomePage(driver);
    }

    @When("^I select \"(Save|Print|Modify|Close)\" customer profile$")
    public void i_select_action_on_customer_profile(String action) throws Throwable {
        commonActions.selectButtonWithText(ParentElementsPage.OVC, action);
    }

    @And("^I set the customer profile field \"(.*?)\" value for feature \"(.*?)\" to \"(.*?)\" on customer page$")
    public void i_set_customer_profile_field_to_value_for_feature(String customerField, String feature, String dataField)
            throws Throwable {
        String value = ovcData.getFieldValue(feature, dataField);
        customerProfilePage.enterTextIntoCustomerProfileInputBox(value, customerField);
    }

    @Then("^I verify the customer profile field \"(.*?)\" value for feature \"(.*?)\" is set to \"(.*?)\" on customer page$")
    public void i_verify_customer_field_value_for_feature_is_set_to(String customerField, String feature, String dataField)
            throws Throwable {
        String expectedText = ovcData.getFieldValue(feature, dataField);
        customerProfilePage.verifyCustomerFieldTextSetTo(customerField, expectedText);
    }

    @Then("^I verify the customer profile field for customer in feature \"([^\"]*)\" is displayed$")
    public void i_verify_customer_field_is_displayed(String feature) throws Throwable {
        customerPage.verifyCustomerPageInputBoxElementDisplayed(ConstantsOvc.CUSTOMER_FIRST_NAME_TEXTBOX);
    }

    @When("^I select customer type \"(Retail|Resale)\" on customer profile$")
    public void i_select_customer_type_on_customer_profile(String type) throws Throwable {
        commonActions.selectButtonWithText(ConstantsOvc.CUSTOMER, type.toUpperCase());
    }

    @When("^I select phone type \"(Home|Mobile)\" on customer profile$")
    public void i_select_phone_type_on_customer_profile(String type) throws Throwable {
        commonActions.selectButtonWithText(ConstantsOvc.CUSTOMER, type.toUpperCase());
    }

    @Then("^I verify regular customer label is present on customer profile$")
    public void i_verify_regular_customer_label_is_present() throws Throwable {
    	commonActions.assertTextPresentInPage(ConstantsOvc.REGULAR_CUSTOMER);
    }

    @Then("^I select customer order transaction that contains matching \"([^\"]*)\" from the table$")
    public void i_select_customer_order_transaction(String matchingText) throws Throwable {
    	commonActions.selectElementWithText(CommonActions.tableRowBy, matchingText);
    }

    @Then("^I select customer order transaction that contains matching \"([^\"]*)\" for feature \"([^\"]*)\" from the table$")
    public void i_select_customer_order_transaction_for_feature(String dataField, String feature) throws Throwable {
        String matchingText = ovcData.getFieldValue(feature, dataField);
        commonActions.selectElementWithText(CustomerPage.customerOrdersTableCellBy, matchingText);
    }

    @And("^I select the customer page transaction with the previous receipt number$")
    public void i_select_the_customer_page_transaction_with_the_previous_receipt_number() throws Throwable {
        customerProfilePage.selectPreviousOrderFromTable();
    }

    @Then("^I verify the Customer Vehicle section contains \"(.*?)\" for feature \"(.*?)\"$")
    public void i_verify_the_customer_vehicle_section_contains(String dataField, String feature) throws Throwable {
        String vehicle = ovcData.getFieldValue(feature, dataField);
        customerProfilePage.assertCustomerVehicle(vehicle);
    }

    @And("^I verify the Customer Profile section contains \"(.*?)\" for feature \"(.*?)\"$")
    public void i_verify_the_customer_profile_section_contains(String dataField, String feature) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        customerProfilePage.assertTextInCustomerProfile(text);
    }

    @And("^I verify the Customer Appointment section is displayed$")
    public void i_verify_the_customer_appointment_section_is_displayed() throws Throwable {
        customerProfilePage.assertAppointmentSectionDisplayed();
    }

    @Then("^I select the first appointment from the Customer Details Appointment table$")
    public void i_select_the_first_appointment_from_the_customer_details_appointments_table() throws Throwable {
        customerProfilePage.selectFirstOrderFromAppoimnmentTable();
    }

    @Then("^I select appointment from the Customer Details Appointment table with value of \"(.*?)\"$")
    public void i_select_appointment_from_the_customer_details_appointments_table_with_value_of
            (String transactionType) throws Throwable {
        customerProfilePage.selectOrderFromAppointmentTableViaTransactionType(transactionType);
    }

    @When("^I select customer order that contains matching \"([^\"]*)\" and click the \"([^\"]*)\" button$")
    public void i_select_order_that_contains_transaction_type_and_click_the_x_button
            (String transactionType, String buttonText) throws Throwable {
        customerProfilePage.selectTableRowAndButtonThatAppears(transactionType, buttonText);
    }

    @And("^I capture the timestamp for Transaction ID lookup$")
    public void i_capture_the_timestamp_for_transaction_id_lookup() throws Throwable {
        timestamp = homePage.captureTimestamp();
    }

    @And("^I enter \"([^\"]*)\" and \"(LastName)\" and search for transaction with particular \"Timestamp\" for feature \"([^\"]*)\"$")
    public void i_enter_and_search_for_transaction_with_timestamp(String salesType, String name, String feature) throws Throwable {
        String lastName = ovcData.getFieldValue(feature, name);
        customerProfilePage.barcode = customerProfilePage.returnLatestCustomerBarcodeViaTimestamp(
                salesType, lastName, timestamp);
    }
    
    @When("^I select \"([^\"]*)\" value for feature \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_select_value_for_feature_for(String stateField, String feature, String dataField) throws Throwable {
    	String desiredState = ovcData.getFieldValue(feature, dataField);
    	customerProfilePage.selectStateDropDownValue(stateField, desiredState);
    }
}