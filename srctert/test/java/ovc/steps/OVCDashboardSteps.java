package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.pages.CommonActions;
import ovc.pages.OVCDashboard;
import utilities.Driver;

/**
 * Created by eseverson on 4/16/2018.
 */
public class OVCDashboardSteps {

    private OVCDashboard ovcDashboard;
    private CommonActions commonActions;

    public OVCDashboardSteps(Driver driver) {
        ovcDashboard = new OVCDashboard(driver);
        commonActions = new CommonActions(driver);
    }

    @When("^I navigate to the OVC Dashboard \"([^\"]*)\"$")
    public void i_navigate_to_ovc_dashboard(String URL) throws Throwable {
        commonActions.navigateToUrlViaPath(URL);
    }

    @And("^I select the \'Addition/Subtraction\' symbol for \"([^\"]*)\" on the OVC Dashboard page$")
    public void i_select_the_addition_subtraction_symbol_on_ovc_dashboard_page(String labelText) throws Throwable {
        ovcDashboard.selectOVCDashboardAdditionSubtractionSymbol(labelText);
    }

    @Then("^I verify all the correct labels are listed under the \"([^\"]*)\" label$")
    public void i_verify_all_the_correct_labels_are_listed_under_the_label(String label) throws Throwable {
        ovcDashboard.verifyDescedantLabelsAreVisible(label);
    }
}
