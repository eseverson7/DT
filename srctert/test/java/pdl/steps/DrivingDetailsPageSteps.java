package pdl.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pdl.pages.CommonActions;
import pdl.pages.DrivingDetailsPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 4/24/17.
 */
public class DrivingDetailsPageSteps {

    private DrivingDetailsPage drivingDetailsPage;
    
    private CommonActions commonActions;

    public DrivingDetailsPageSteps(Driver driver) {
        drivingDetailsPage = new DrivingDetailsPage(driver);
        commonActions = new CommonActions(driver);
    }

    @And("^I verify the zip code is set to \"(.*?)\"$")
    public void i_verify_zip(String zipcodeValue) throws Throwable {
    	commonActions.verifyElementValue(DrivingDetailsPage.zipcode, zipcodeValue);
    }

    @And("^I update the zip code to \"(.*?)\"$")
    public void i_update_zipcode(String zipcode) throws Throwable {
    	drivingDetailsPage.updateZipcode(zipcode);
    }
    
    @And("^I set miles driven per year to \"(.*?)\"$")
    public void i_set_miles_driven_per_year_to(String miles) throws Throwable {
    	drivingDetailsPage.setMilesDrivenUsingSlider(miles);
    }
    
    @And("^I verify the city and state are set to \"(.*?)\"$")
    public void i_verify_city_and_state(String cityState) throws Throwable {
        commonActions.verifyElementText(DrivingDetailsPage.cityState, cityState);
    }

    @And("^I verify the default values of the car dropdowns$")
    public void i_verify_default_values_of_car_dropdowns() throws Throwable {
        drivingDetailsPage.verifyDefaultCarDropdownValues();
    }

    @And("^I verify that the Miles Driven value is \"(.*?)\"$")
    public void i_verify_miles_driven(String miles) throws Throwable {
        drivingDetailsPage.verifyMilesDriven(miles);
    }

    @And("^I verify that the Tire Size value is \"(.*?)\"$")
    public void i_verify_tire_size(String tireSize) throws Throwable {
    	commonActions.waitForElementToContainText(DrivingDetailsPage.tireSize, tireSize, Constants.DEFAULT_SEC_WAIT);
        commonActions.verifyElementText(DrivingDetailsPage.tireSize, tireSize);
    }

    @And("^I verify that the \"(Typical|performance)\" Driving Priority is selected$")
    public void i_verify_selected_driving_priority(String drivingPriority) throws Throwable {
        drivingDetailsPage.verifySelectedDrivingPriority(drivingPriority);
    }

    @And("^I verify the \"(Typical|performance)\" Driving Priorities$")
    public void i_verify_driving_priorities(String drivingPriority) throws Throwable {
        commonActions.verifyDrivingPriorities(drivingPriority, false);
    }

    @When("^I select a vehicle with details \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\"$")
    public void i_select_a_vehicle_with_details(String year, String make,
                                                String model, String trim, String assembly)
            throws Throwable {
        drivingDetailsPage.selectVehicle(year, make, model, trim, assembly);
    }

    @When("^I select View Tire Recommendations$")
    public void i_select_view_tire_recommendations() throws Throwable {
        drivingDetailsPage.viewRecommendations();
    }
    
    @When("^I switch to performance for driving priorities$")
    public void i_switch_to_performance_for_driving_priorities() throws Throwable {
    	drivingDetailsPage.clickingDrivingPriorityPerformance();
    }
    
    @And("^I verify that the \"(not your tire|Typical|performance)\" is displayed on driving details page$")
    public void i_verify_page_element_is_displayed(String ele) throws Throwable {
        drivingDetailsPage.verifyPageElementIsDisplayedOnDrivingDetails(ele);
    }
    
    @When("^I select Not your tire under Tire Size$")
    public void i_select_not_your_tire_link() throws Throwable {
    	drivingDetailsPage.clickNotYourTireLink();
    }
    
    @And("^I verify that the \"(oe|optional)\" tire size category is displayed$")
    public void i_verify_tire_size_category_is_displayed(String label) throws Throwable {
        drivingDetailsPage.verifyTireSizeCategoryLabel(label);
    }

    @Then("^I verify the Tire Size error message is visible on the \"([^\"]*)\" side of the page$")
    public void i_verify_the_tire_size_error_message_is_visible_on_the_side_of_the_page(String side) throws Throwable {
        drivingDetailsPage.verifyTireSizeErrorMessage(side);
    }
}
