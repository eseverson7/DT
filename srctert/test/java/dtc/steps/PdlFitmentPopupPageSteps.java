package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.FitmentPopupPage;
import dtc.pages.PdlxFitmentPopupPage;
import utilities.Driver;

/**
 * Created by aarora on 01/17/18.
 */
public class PdlFitmentPopupPageSteps {

    private PdlxFitmentPopupPage pdlFitmentPopupPage;
    private FitmentPopupPage fitmentPopupPage;

    public PdlFitmentPopupPageSteps(Driver driver) {
    	pdlFitmentPopupPage = new PdlxFitmentPopupPage(driver);
    	fitmentPopupPage = new FitmentPopupPage(driver);
    }

    @Then("^I should see the pdlx fitment panel page with vehicle \"(.*?)\"$")
    public void i_should_see_pdlx_fitment_panel_page_with_vehicle(String vehicle) throws Throwable {
    	fitmentPopupPage.assertVehicleInPanel(vehicle);
    }

    @When("^I select \"(Everyday|Performance)\" as my driving priority$")
    public void i_select_a_driving_priority(String priority) throws Throwable {
    	pdlFitmentPopupPage.selectDrivingPriority(priority.toUpperCase());
    }
    
    @When("^I select view recommended tires$")
    public void i_select_view_recommended_tires() throws Throwable {
    	pdlFitmentPopupPage.selectViewRecommendedTiresButton();;
    }

    @When("^I set the driving location zipcode to \"(.*?)\"$")
    public void i_set_the_driving_location_zipcode_to(String zipcode) throws Throwable {
    	pdlFitmentPopupPage.setZipcode(zipcode);
    }
    
    @When("^I set the miles per year to \"(.*?)\"$")
    public void i_set_the_miles_driven_per_year_to(String miles) throws Throwable {
    	pdlFitmentPopupPage.setMiles(miles);
    }

    @Then("^I am brought to the pdl fitment page with title \"(.*?)\"$")
    public void i_am_brought_to_the_pdl_fitment_page_with_title(String title) throws Throwable {
    	pdlFitmentPopupPage.assertPdlFitmentPopupTitle(title);
    }

    @Then("^I verify the zipcode has a value of \"([^\"]*)\" on pdl fitment page$")
    public void i_verify_pdl_fitment_zipcode_has_a_value_of(String value) throws Throwable {
    	pdlFitmentPopupPage.assertZipCodeValue(value);
    }
    
    @Then("^I verify the selected pdl fitment tire size value \"([^\"]*)\" is displayed$")
    public void i_verify_pdl_fitment_tireSize_has_a_value_of(String value) throws Throwable {
    	pdlFitmentPopupPage.assertSelectedPdlFitmentTireSizeValue(value);
    }
    
    @Then("^I verify the miles driven per year has a value of \"([^\"]*)\" on pdl fitment page$")
    public void i_verify_miles_driven_per_year_has_a_value_of(String value) throws Throwable {
    	pdlFitmentPopupPage.assertMilesDrivenPerYearValue(value);
    }

    @And("^I select Change Vehicle on pdl fitment page$")
    public void i_select_change_vehicle() throws Throwable {
    	fitmentPopupPage.clickChangeVehicle();
    }
    
    @When("^I move \"(Handling|Stopping Distance|Comfort & Noise|Life of Tire)\" driving priority to position \"(1|2|3|4)\"$")
    public void i_move_driving_priority(String priorityOption, String position) throws Throwable {
    	pdlFitmentPopupPage.moveDrivingPriorityOptionTo(priorityOption, position);
    }

}
