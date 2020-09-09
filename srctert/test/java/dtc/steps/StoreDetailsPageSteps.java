package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import dtc.pages.StoreDetailsPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class StoreDetailsPageSteps {

    private StoreDetailsPage storeDetailsPage;

    public StoreDetailsPageSteps(Driver driver) {
        storeDetailsPage = new StoreDetailsPage(driver);
    }

    @And("^I verify the \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" of the current store$")
    public void i_verify_details_of_the_current_store(String title, String address, String days) throws Throwable {
        storeDetailsPage.verifyMyStoreDetails(title, address, days);
    }

    @Then("^I verify the \"STORE HOURS\" for the current store$")
    public void i_verify_store_hours_current_store() throws Throwable {
        storeDetailsPage.verifyStoreHoursForCurrentStore();
    }

    @And("^I click \"Schedule appointment\" on Store Details page$")
    public void i_click_schedule_appointment_on_store_details() throws Throwable {
        storeDetailsPage.clickScheduleAppointmentStoreDetails();
    }
}