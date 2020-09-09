package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.CommonActions;
import ovc.pages.CustomerPage;
import ovc.pages.ParentElementsPage;
import utilities.Driver;

/**
 * Created by eseverson on 6/29/2017.
 */
public class CustomerPageSteps {

    private CustomerPage customerPage;
    private CommonActions commonActions;
    private OvcData ovcData, featureData;

    public CustomerPageSteps(Driver driver) {
        customerPage = new CustomerPage(driver);
        commonActions = new CommonActions(driver);
        ovcData = new OvcData();
    }

    @And("^I select the customer for feature \"([^\"]*)\" from the Customer table$")
    public void i_select_a_customer_from_the_customer_table(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String customerFirstName = featureData.FirstName;
        customerPage.selectCustomerTableCell(customerFirstName);
    }

    @And("^I select the new customer for feature \"([^\"]*)\" from the Customer table$")
    public void i_select_the_new_customer_from_the_customer_table(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String customerFirstName = featureData.NewFirstName;
        customerPage.selectCustomerTableCell(customerFirstName);
    }

    @And("^I select the AR Company name for feature \"([^\"]*)\" from the Customer table$")
    public void i_select_company_name_from_the_customer_table(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String customerFirstName = featureData.ArCompanyName;
        customerPage.selectCustomerTableCell(customerFirstName);
    }

    @Then("^I verify \"(First Name|Last Name|Phone Number|Email|Address|Town/City|State/Province|AR Account Number|Company Name)\" field is displayed on customer page$")
    public void i_verify_customer_page_input_element_is_displayed(String text) throws Throwable {
        customerPage.verifyCustomerPageInputBoxElementDisplayed(text);
    }

    @Then("^I verify \"(Search|Clear|Select|Details|Vehicles List|New|Close)\" button is displayed on customer page$")
    public void i_verify_customer_page_button_element_is_displayed(String text) throws Throwable {
        commonActions.verifyButtonElementIsDisplayed(ParentElementsPage.OVC, text);
    }

    @Then("^I verify customer for feature \"([^\"]*)\" added to the virtual receipt$")
    public void i_verify_customer_added_at_bottom_left_of_vr(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String name = featureData.CustomerName;
        commonActions.verifyElementText(CustomerPage.loyaltyCustomerBy, name);
    }

    @Then("^I verify new customer for feature \"([^\"]*)\" added to the virtual receipt$")
    public void i_verify_new_customer_added_at_bottom_left_of_vr(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String name = featureData.NewCustomerName;
        commonActions.verifyElementText(CustomerPage.loyaltyCustomerBy, name);
    }

    @Then("^I verify customer name for feature \"([^\"]*)\" is present at profile section$")
    public void i_verify_customer_name_at_profile_section(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String name = featureData.CustomerName;
        commonActions.verifyElementText(CustomerPage.customerProfileNameBy, name);
    }

    @Then("^I verify customer vehicle for feature \"([^\"]*)\" is present in vehicle list section$")
    public void i_verify_customer_vehicle_information_present_in_vehicle_list_section(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String vehicle = featureData.CustomerVehicle;
        customerPage.verifyCustomerVehicleExistsInVehicleList(vehicle);
    }

    @Then("^I verify customer vehicle for feature \"([^\"]*)\" is not present in vehicle list section$")
    public void i_verify_customer_vehicle_information_is_not_present_in_vehicle_list_section(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String vehicle = featureData.CustomerVehicle;
        customerPage.verifyCustomerVehicleIsNotPresentInVehicleList(vehicle);
    }

    @And("^I select \"([^\"]*)\" on the \"Customer\" page$")
    public void i_select_the_element_on_the_customer_page(String text) throws Throwable {
        commonActions.selectElementWithText(CommonActions.h1TagBy, text);
    }
    
    @Then("^I verify customer vehicle for feature \"([^\"]*)\" is present on vehicle customer popup$")
    public void i_verify_customer_vehicle_for_feature_is_present_on_vehicle_customer_popup(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String vehicle = featureData.CustomerVehicle;
    	customerPage.verifyCustomerVehicleExistsOnCustomerVehiclePopup(vehicle);
    }
    
    @When("^I select the \"([^\"]*)\" for feature \"([^\"]*)\" from the Customer table$")
    public void i_select_the_for_feature_from_the_customer_table(String text, String feature) throws Throwable {
    	String desiredCustomer = ovcData.getFieldValue(feature, text);
    	customerPage.selectDesiredCustomerTableCell(desiredCustomer);
    }
    
    @When("^I enter One Time AR Customer Details for feature \"([^\"]*)\"$")
    public void i_enter_one_time_ar_customer_details_for_feature(String feature) throws Throwable {
    	featureData = ovcData.getOvcData(feature);
        String companyName = featureData.ArCompanyName;
        String address = featureData.ArAddress;
        String town = featureData.ArTown;   
        String zip = featureData.ArZip;
        
        customerPage.enterTextIntoInputBoxWithNgModel(companyName,ConstantsOvc.COMPANY_NAME);
        commonActions.enterTextIntoInputBox(address, ConstantsOvc.ADDRESS_LINE_1);
        customerPage.enterTextIntoInputBoxWithNgModel(town, ConstantsOvc.TOWN_CITY);
        commonActions.enterTextIntoInputBox(zip, ConstantsOvc.POSTAL_ZIP);
    }

    @And("^I select the \"More Filters\" drop down to expose all filters$")
    public void i_select_more_filters_drop_down() throws Throwable {
        CustomerPage.moreFilters.click();
    }

    @Then("^I verify inputs on the customer details page are disabled$")
    public void i_verify_input_on_customer_details_page_are_disabled() throws Throwable {
        customerPage.verifyInputsOnCustomerDetailsDisabled();
    }
}