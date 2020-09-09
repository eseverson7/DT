package dtc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.FitmentPopupPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class FitmentPopupPageSteps {

    private FitmentPopupPage fitmentPopupPage;

    public FitmentPopupPageSteps(Driver driver) {
        fitmentPopupPage = new FitmentPopupPage(driver);
    }

    @Then("^I should see the fitment panel page with vehicle \"(.*?)\"$")
    public void i_should_see_the_fitment_panel_page_with_vehicle(String vehicle) throws Throwable {
        fitmentPopupPage.assertVehicleInPanel(vehicle);
    }

    @When("^I select a fitment option \"(.*?)\"$")
    public void i_select_a_fitment_option(String fitment) throws Throwable {
        fitmentPopupPage.selectFitment(fitment);
    }
    
    @When("^I select enter driving details for recommended tires$")
    public void i_select_pdlx_driving_details_recommended_tires() throws Throwable {
        fitmentPopupPage.selectPdlxDrivingDetailsRecommendedTiresLink();
    }

    @When("^I select the fitment vehicle \"(.*?)\"$")
    public void i_select_a_fitment_vehicle(String vehicle) throws Throwable {
        fitmentPopupPage.selectFitmentVehicle(vehicle);
    }

    @Then("^I am brought to the fitment page with the \"(.*?)\" vehicle message$")
    public void i_am_brought_to_the_details_page_with_message(String optionType) throws Throwable {
        fitmentPopupPage.assertVehicleDetailsMessage(optionType);
    }

    @And("^I verify all the \"([^\"]*)\" fitment option links by clicking on them$")
    public void i_verify_all_the_fitment_option_links_by_clicking_on_them(String optionType) throws Throwable {
        fitmentPopupPage.verifyAllFitmentLinksWork(optionType);
    }

    @Then("^I verify the selected fitment box option has a value of \"([^\"]*)\"$")
    public void i_verify_selected_fitment_box_option_has_a_value_of(String value) throws Throwable {
        fitmentPopupPage.assertFitmentBoxIsSelected(value);
    }
    
    @Then("^I verify the fitment box option has a value of \"([^\"]*)\"$")
    public void i_verify_fitment_box_option_has_a_value_of(String value) throws Throwable {
        fitmentPopupPage.assertFitmentBoxValue(value);
    }

    @And ("^I select the \"([^\"]*)\" fitment box option$")
    public void i_select_the_fitment_box_option (String value) throws Throwable {
        fitmentPopupPage.selectFitmentBoxOption(value);
    }

    @When("^I close the fitment popup$")
    public void i_close_fitment_popup() throws Throwable {
        fitmentPopupPage.closeFitmentPopUp();
    }

    @And("^I select Change Vehicle$")
    public void i_select_change_vehicle() throws Throwable {
        fitmentPopupPage.clickChangeVehicle();
    }

    @Then("^I should see the fitment panel page with fitment options$")
    public void i_should_see_the_fitment_panel_page_with_fitment_options() throws Throwable {
        fitmentPopupPage.assertAllFitmentOpts();
    }

    @And ("^I select the \"([^\"]*)\" (menu|staggered menu) option$")
    public void i_select_the_menu_option (String value, String menuOptionType) throws Throwable {
        fitmentPopupPage.clickMenuOption(value, menuOptionType);
    }
}