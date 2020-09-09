package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import ovc.data.OvcData;
import ovc.pages.AccountReceivablePage;
import ovc.pages.CommonActions;
import utilities.Driver;

/**
 * Created by eseverson on 6/29/2017.
 */
public class AccountReceivablePageSteps {

    private OvcData featureData;
    private AccountReceivablePage accountReceivablePage;
    private OvcData ovcData;
    private CommonActions commonActions;

    public AccountReceivablePageSteps(Driver driver) {
        accountReceivablePage = new AccountReceivablePage(driver);
        ovcData = new OvcData();
        commonActions = new CommonActions(driver);
    }

    @When("^I enter \"([^\"]*)\" for \"([^\"]*)\" into the \"([^\"]*)\" input on the AR Payment page$")
    public void i_enter_text_into_the_textbox_for_ar_payment_page(String dataField, String feature, String textbox) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        accountReceivablePage.enterTextIntoARInputField(text, textbox);
    }

    @And("^I verify that I can enter 300 characters in the account requirements field$")
    public void i_verify_that_i_can_enter_300_characters_in_the_account_requirements_field() throws Throwable {
        accountReceivablePage.assertCharacterLimitAccountRequirement();
    }
    
    @When("^I enter vehicle data for feature \"([^\"]*)\" on AR Payment page$")
    public void i_enter_vehicle_data_for_feature_on_ar_payment_page(String feature) throws Throwable {
    	   featureData = ovcData.getOvcData(feature);
           String year = featureData.Year;
           String make = featureData.Make;
           String model = featureData.Model;

           commonActions.vehicleSearchYMM(year, make, model);
    }
    
    @When("^I enter state for feature \"([^\"]*)\" on AR Payment page$")
    public void i_enter_state_for_feature_on_ar_payment_page(String feature) throws Throwable {
    		featureData = ovcData.getOvcData(feature);
    		String state = featureData.state;
    		
    		commonActions.licenseStateSearch(state);
    }
}
