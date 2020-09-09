package ovc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.CommonActions;
import ovc.pages.CustomerPage;
import ovc.pages.HomePage;
import ovc.pages.ParentElementsPage;
import ovc.pages.VTVPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 5/18/17.
 */
public class CommonActionsSteps {

    private CommonActions commonActions;
    private CustomerPageSteps customerPageSteps;
    private LookupPageSteps lookupPageSteps;
    private VTVPageSteps vtvPageSteps;
    private OvcData ovcData, featureData;

    public CommonActionsSteps(Driver driver) {
        commonActions = new CommonActions(driver);
        customerPageSteps = new CustomerPageSteps(driver);
        lookupPageSteps = new LookupPageSteps(driver);
        vtvPageSteps = new VTVPageSteps(driver);
        ovcData = new OvcData();
    }

    @Then("^I am brought to the page with title \"(.*?)\"$")
    public void i_am_brought_to_the_page_with_title(String title) throws Throwable {
        commonActions.assertPageTitleOvc(title);
    }

    @When("^I select the \"(Home|CSL|Alerts|Appointment|Lookup|Customer|Air Check|Endless Aisle|Fitment|VTV)\" " +
            "icon from the Global Header$")
    public void i_select_the_icon_from_the_global_header(String icon) throws Throwable {
        commonActions.selectGlobalHeader(icon);
    }

    @Then("^I select the \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" from the fitment page$")
    public void i_select_the_vehicle_details_from_the_fitment_page(String year, String make, String model, String trim,
                                                                   String assembly) throws Throwable {
        commonActions.vehicleSearch(year, make, model, trim, assembly);
    }

    @And("^I perform a free text vehicle search with values \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_perform_a_free_text_vehicle_search_with_values(String year, String make, String model, String trim,
                                                                 String assembly) throws Throwable {
        commonActions.enterVehicleSearchCriteria(year, make, model, trim, assembly);
    }

    @And("^I select the \"([^\"]*)\" button on the \"([^\"]*)\" page$")
    public void i_select_the_button_on_the_page(String text, String page) throws Throwable {
        commonActions.selectButtonWithText(page, text);
    }

    @And("^I select the \"([^\"]*)\" button for feature \"([^\"]*)\" on the \"([^\"]*)\" page$")
    public void i_select_the_button_on_the_page_alm(String dataField, String feature, String page) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        commonActions.selectButtonWithText(page, text);
    }

    @Then("^I verify \"([^\"]*)\" button is displayed on the page$")
    public void i_verify_the_button_on_the_page(String text) throws Throwable {
        commonActions.verifyButtonWithText(ParentElementsPage.POPUP, text);
    }

    @And("^I select the \"([^\"]*)\" link on the page$")
    public void i_select_the_link_on_the_page(String text) throws Throwable {
        commonActions.selectLinkWithText(text);
    }

    @When("^I select \"([^\"]*)\" option from the right pane nav bar$")
    public void i_select_option_from_right_pane_nav_bar(String option) throws Throwable {
        commonActions.selectOptionFromRightPaneNavBar(option);
    }

    @Then("^I select vehicle data for feature \"([^\"]*)\" and complete a vehicle search$")
    public void i_select_the_excel_data_vehicle_details_and_search(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String year = featureData.Year;
        String make = featureData.Make;
        String model = featureData.Model;
        String trim = featureData.Trim;
        String assembly = featureData.Assembly;
        commonActions.vehicleSearch(year, make, model, trim, assembly);
    }

    @And("^I enter \"([^\"]*)\" into the \"([^\"]*)\"$")
    public void i_enter_text_into_the_textbox(String text, String textbox) throws Throwable {
        commonActions.enterTextIntoInputBox(text, textbox);
    }

    @And("^I enter \"([^\"]*)\" for feature \"([^\"]*)\" into the \"([^\"]*)\"$")
    public void i_enter_text_into_the_textbox(String dataField, String feature, String textbox) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        if ((textbox.equalsIgnoreCase(CustomerPage.COMPANY_NAME))||(textbox.equalsIgnoreCase(CustomerPage.AR_ACCOUNT_NUMBER))) {
            CustomerPage.moreFilters.click();
        }
        commonActions.enterTextIntoInputBox(text, textbox);
    }

    @And("^I enter the Full Name for the customer in feature \"([^\"]*)\" into the textbox$")
    public void i_enter_full_name_into_the_textbox(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String firstName = featureData.FirstName;
        String lastName = featureData.LastName;
        commonActions.enterTextIntoInputBox(firstName, ConstantsOvc.CUSTOMER_FIRST_NAME_TEXTBOX);
        commonActions.enterTextIntoInputBox(lastName, ConstantsOvc.CUSTOMER_LAST_NAME_TEXTBOX);
    }

    @And("^I enter the Company Name for the AR customer in feature \"([^\"]*)\" into the textbox$")
    public void i_enter_company_ar_name__into_the_textbox(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String companyNameAr = featureData.ArCompanyName;
        CustomerPage.moreFilters.click();
        commonActions.enterTextIntoInputBox(companyNameAr, ConstantsOvc.COMPANY_NAME);
    }

    @And("^I enter Transaction ID for the transaction in feature \"([^\"]*)\" into the textbox$")
    public void i_enter_transaction_id_into_the_textbox(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String transactionId = featureData.TransactionId;
        commonActions.enterTextIntoInputBox(transactionId, ConstantsOvc.TRANSACTIONID_TEXTBOX);
    }

    @And("^I enter the stored order number into the Transaction ID textbox$")
    public void i_enter_stored_order_number_into_transaction_id_textbox() throws Throwable {
        commonActions.enterTextIntoInputBox(HomePage.orderNumber, ConstantsOvc.TRANSACTIONID_TEXTBOX);
    }

    @Then("^I am brought to the page with header \"([^\"]*)\"$")
    public void i_am_brought_to_ovc_page_with_header(String pageHeader) throws Throwable {
        commonActions.verifyPageHeaderOvc(pageHeader);
    }

    @Then("^I verify the popup alert contains \"([^\"]*)\"$")
    public void i_verify_the_popup_contains_text(String text) throws Throwable {
        commonActions.assertPopupTextContains(text);
    }

    @Then("^I verify the popup alert contains extracted subtotal plus \"([^\"]*)\"$")
    public void i_verify_the_popup_contains_subtotal_plus_amount(String amount) throws Throwable {
        double subTotal = Double.parseDouble(HomePage.orderSubTotal);
        double amountInt = Double.parseDouble(amount);
        commonActions.assertPopupTextContains(Double.toString(subTotal + amountInt));
    }

    //TODO: This step is deprecated, and steps using it should be updated to use the one below it
    @Then("^I verify the popup alert contains store location for feature \"([^\"]*)\"$")
    public void i_verify_the_popup_contains_store_location(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String storeLocation = featureData.StoreLocation;
        commonActions.assertPopupTextContains(storeLocation);
    }

    @Then("^I verify the popup alert contains \"([^\"]*)\" for feature \"([^\"]*)\"$")
    public void i_verify_the_popup_contains_text_for_feature(String dataField, String feature) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        commonActions.assertPopupTextContains(text);
    }

    @Then("^I verify the right nav pane title is \"([^\"]*)\"$")
    public void i_verify_the_right_nav_pane_title_is(String title) throws Throwable {
        commonActions.assertRightPaneNavTitle(title);
    }

    @Then("^I verify the dialog contains \"([^\"]*)\"$")
    public void i_verify_the_dialog_contains_text(String text) throws Throwable {
        commonActions.assertDialogTextContains(text);
    }

    @Then("^I verify the dialog contains \"([^\"]*)\" for feature \"([^\"]*)\"$")
    public void i_verify_the_dialog_contains_text_for_feature(String dataField, String feature) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        commonActions.assertDialogTextContains(text);
    }

    @Then("^I select the \"([^\"]*)\" radio button$")
    public void i_select_the_radio_button(String label) throws Throwable {
        commonActions.selectRadioButton(ParentElementsPage.POPUP, label);
    }

    @And("^I select the \"([^\"]*)\" radio button on the \"([^\"]*)\" page$")
    public void i_select_the_radio_button(String label, String page) throws Throwable {
        commonActions.selectRadioButton(label, page);
    }

    @Then("^I verify that the popup with a \"([^\"]*)\" button is closed$")
    public void i_verify_popup_is_closed(String text) {
        commonActions.assertPopupClosed(ParentElementsPage.POPUP, text);
    }

    @And("^I enter \"([^\"]*)\" into the input field of the popup$")
    public void i_enter_into_the_input_field_of_the_popup(String text) throws Throwable {
        commonActions.enterTextSingleInputField(text);
    }

    @Then("^I verify the input value field of the popup contains \"([^\"]*)\"$")
    public void i_verify_the_input_value_field_of_the_popup_contains(String text) throws Throwable {
        commonActions.assertTextSingleInputFieldContains(text);
    }

    @When("^I perform a \"(CustomerVehicle|ReturnVehicle)\" and \"(tire|wheel)\" lookup using customer, vehicle and item number for feature \"(.*?)\"$")
    public void i_perform_vehicle_and_item_lookup_for_customer_with_vehicle_for_item
            (String dataField, String itemType, String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String customerFirstName = featureData.FirstName;
        String customerLastName = featureData.LastName;
        String customerVehicle = ovcData.getFieldValue(feature, dataField);

        i_select_the_icon_from_the_global_header(ConstantsOvc.CUSTOMER);
        i_enter_text_into_the_textbox(customerFirstName, ConstantsOvc.CUSTOMER_FIRST_NAME_TEXTBOX);
        i_enter_text_into_the_textbox(customerLastName, ConstantsOvc.CUSTOMER_LAST_NAME_TEXTBOX);
        commonActions.selectButtonWithText(ConstantsOvc.CUSTOMER, ConstantsOvc.SEARCH);
        customerPageSteps.i_select_a_customer_from_the_customer_table(feature);

        i_select_the_button_on_the_page(Constants.SELECT, ConstantsOvc.CUSTOMER);
        i_select_the_button_on_the_page(ConstantsOvc.VEHICLES, ConstantsOvc.HOME);
        i_select_the_button_on_the_page(customerVehicle, ParentElementsPage.OVC);
        i_select_the_icon_from_the_global_header(ConstantsOvc.LOOKUP);

        if (itemType.equalsIgnoreCase(Constants.TIRE)) {
            lookupPageSteps.i_select_from_the_lookup_page_dropdown(ConstantsOvc.UPPER_TIRE);
        } else {
            lookupPageSteps.i_select_from_the_lookup_page_dropdown(ConstantsOvc.UPPER_WHEEL);
        }

        i_select_the_button_on_the_page(ConstantsOvc.SEARCH, ConstantsOvc.LOOKUP);
        lookupPageSteps.i_select_item_in_feature_from_the_lookup_results_screen(ConstantsOvc.ITEM_NUMBER_DATA_FIELD, feature);
        i_select_the_button_on_the_page(ConstantsOvc.ADD, ConstantsOvc.LOOKUP);
    }

    @Then("^I should see text \"(.*?)\" present in the page$")
    public void i_should_see_text_present_on_the_page(String text) throws Throwable {
        commonActions.assertTextPresentInPage(text);
    }

    @Then("^I verify the page currently displayed is the \"(.*?)\" page$")
    public void i_verify_the_page_currently_displayed_is_the_specified_page(String page) throws Throwable {
        commonActions.assertPageDisplayed(page);
    }

    @When("^I perform a vehicle and customer lookup for customer and vehicle in feature \"(.*?)\"$")
    public void i_perform_vehicle_and_customer_lookup_for_customer_and_vehicle(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String customerFirstName = featureData.FirstName;
        String customerLastName = featureData.LastName;
        String vehicle = featureData.CustomerVehicle;

        i_select_the_icon_from_the_global_header(ConstantsOvc.CUSTOMER);
        i_enter_text_into_the_textbox(customerFirstName, ConstantsOvc.CUSTOMER_FIRST_NAME_TEXTBOX);
        i_enter_text_into_the_textbox(customerLastName, ConstantsOvc.CUSTOMER_LAST_NAME_TEXTBOX);
        commonActions.selectButtonWithText(ConstantsOvc.CUSTOMER, ConstantsOvc.SEARCH);
        customerPageSteps.i_select_a_customer_from_the_customer_table(feature);
        i_select_the_button_on_the_page(Constants.SELECT, ConstantsOvc.CUSTOMER);
        i_select_the_button_on_the_page(ConstantsOvc.VEHICLES, ConstantsOvc.HOME);
        i_select_the_button_on_the_page(vehicle, ParentElementsPage.OVC);
    }

    @And("^I enter \"(.*?)\" into the \"(.*?)\" textbox and send Enter key$")
    public void i_enter_into_the_textbox_and_send_enter_key(String text, String textbox) throws Throwable {
        commonActions.enterTextIntoInputBoxWithEnter(text, textbox);
    }

    @And("^I enter \"(.*?)\" for feature \"(.*?)\" into \"(.*?)\" textbox and send Enter key$")
    public void i_enter_item_number_for_feature_into_the_search_and_send_enter_key(String article, String feature, String textbox) throws Throwable {
        String sku = ovcData.getFieldValue(feature, article);
        commonActions.enterTextIntoInputBoxWithEnter(sku, textbox);
    }

    @Then("^I verify \"(.*?)\" field is displayed on the current page$")
    public void i_verify_text_box_input_element_is_displayed_on_current_page(String text) throws Throwable {
        commonActions.verifyInputBoxElementDisplayed(text);
    }

    @And("^I select the \"(.*?)\" div tag on the on the \"(.*?)\" page$")
    public void i_select_the_div_tag_on_the_page(String text, String page) throws Throwable {
        commonActions.selectDivTagElement(page, text);
    }

    @And("^I select the \"([^\"]*)\" for feature \"([^\"]*)\" from the table on the \"(Customer|Vendor)\" page$")
    public void i_select_an_item_from_the_table(String dataField, String feature, String page) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        String knownText = ConstantsOvc.CUSTOMER_ID;
        if (page.equalsIgnoreCase(ConstantsOvc.VENDOR))
            knownText = ConstantsOvc.VENDOR_ID;
        commonActions.selectTableRow(page, knownText, text);
    }

    @Then("^I verify the dialog contains \"([^\"]*)\" with loader wait$")
    public void i_verify_the_dialog_contains_with_loader_wait(String text) throws Throwable {
        commonActions.assertDialogTextContainsWithLoaderWait(text);
    }

    @Then("^I verify the dialog window contains \"([^\"]*)\"$")
    public void i_verify_the_dialog_window_contains(String text) throws Throwable {
        commonActions.assertDialogWindowContains(text);
    }

//    @And("^I switch \"(To|Back From)\" the iframe window$")
//    public void i_switch_to_the_iframe_window(String toFrom) throws Throwable {
//        commonActions.switchFrameContext(CommonActions.cslIFrame, toFrom);
//    }

    @When("^I select vehicle data for feature \"([^\"]*)\" with Staggered options and complete a vehicle search$")
    public void i_select_vehicle_data_for_feature_with_Staggered_options_and_complete_a_vehicle_search(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String year = featureData.StaggeredYear;
        String make = featureData.StaggeredMake;
        String model = featureData.StaggeredModel;
        String trim = featureData.StaggeredTrim;
        String assembly = featureData.StaggeredAssembly;
        commonActions.vehicleSearch(year, make, model, trim, assembly);
    }

    @And("^I fill out Visit The Vehicle page information for feature \"([^\"]*)\"$")
    public void i_fill_out_visit_the_vehicle_page_information(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String mileage = featureData.Mileage;
        String condition = featureData.Condition;

        i_select_the_icon_from_the_global_header(ConstantsOvc.VTV);
        vtvPageSteps.i_enter_info_into_vtv_vehicle_section(ConstantsOvc.MILEAGE, mileage);
        vtvPageSteps.i_enter_info_into_vtv_vehicle_section(ConstantsOvc.CONDITION, condition);
        vtvPageSteps.i_select_selection_for_the_tire_service_sections(ConstantsOvc.REPLACE_TIRE, VTVPage.LF);
        i_select_the_button_on_the_page(ConstantsOvc.SAVE, ConstantsOvc.VTV);
    }

    @Then("^I store the current baseUrl$")
    public void i_store_current_base_url() throws Throwable {
        Config.storeCurrentBaseUrl();
    }

    @Then("^I set baseUrl to the stored baseUrl$")
    public void i_set_baseurl_to_stored_baseurl() throws Throwable {
        System.out.println("Config.getStoredBaseUrl():" + Config.getStoredBaseUrl());
        Config.setBaseUrl(Config.getStoredBaseUrl());
    }

    @And("^I select the \"([^\"]*)\" partial link element$")
    public void i_select_the_partial_link_element(String linkText) throws Throwable {
        commonActions.selectElementWithPartialLinkText(linkText);
    }
}