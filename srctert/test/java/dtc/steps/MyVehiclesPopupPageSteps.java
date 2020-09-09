package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.MyVehiclesPopupPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 4/27/17.
 */
public class MyVehiclesPopupPageSteps {

    private MyVehiclesPopupPage myVehiclesPopupPage;

    public MyVehiclesPopupPageSteps(Driver driver) {
        myVehiclesPopupPage = new MyVehiclesPopupPage(driver);
    }

    @Then("^I verify that My Vehicles displays \"(.*?)\" as the current vehicle$")
    public void i_verify_current_vehicle(String vehicle) throws Throwable {
        myVehiclesPopupPage.assertCurrentVehicle(vehicle);
    }

    @Then("^I verify that My Vehicles displays the vehicle limit reached message$")
    public void i_verify_vehicle_limit_message() throws Throwable {
        myVehiclesPopupPage.assertVehicleLimitMessage();
    }

    @Then("^I select recent vehicle \"(.*?)\"$")
    public void i_click_recent_vehicle(String vehicle) throws Throwable {
        myVehiclesPopupPage.clickRecentVehicle(vehicle);
    }

    @Then("^I verify My Vehicles popup displays add vehicle$")
    public void i_verify_my_vehicle_popup_displays_add_vehicle() throws Throwable {
        myVehiclesPopupPage.assertAddVehicle();
    }

    @When("^I select current Vehicle$")
    public void i_select_current_vehicle() throws Throwable {
        myVehiclesPopupPage.clickCurrentVehicle();
    }

    @And("^I verify that My Vehicles displays \"([^\"]*)\" in the header$")
    public void i_verify_my_vehicles_display_in_the_header(String vehicle) throws Throwable {
        myVehiclesPopupPage.assertMyVehicles(vehicle);
    }

    @And("^I verify recent vehicle display \"(.*?)\"$")
    public void i_verify_recent_vehicle_display(String vehicle) throws Throwable {
        myVehiclesPopupPage.assertRecentVehicleDisplay(vehicle);
    }
}
