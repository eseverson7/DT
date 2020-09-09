package visualtesting.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import visualtesting.pages.Applitools;
import utilities.Driver;

/**
 * Created by aaronbriel on 5/3/17.
 */
public class ApplitoolsSteps {

    private Applitools applitools;

    public ApplitoolsSteps(Driver driver) {
        applitools = new Applitools(driver);
    }

    @Then("^I verify the Window \"(.*?)\" for Batch \"(.*?)\", App \"(.*?)\" and Test \"(.*?)\"$")
    public void i_verify_the_ui_window_in_applitools(String window, String batch, String app, String test)
            throws Throwable {
        applitools.verifyWindowApplitools(window, batch, app, test);
    }

    @Then("^I verify the Region \"(.*?)\" for Batch \"(.*?)\", App \"(.*?)\" and Test \"(.*?)\"$")
    public void i_verify_the_ui_region_in_applitools(String region, String batch, String app, String test)
            throws Throwable {
        applitools.verifyRegionApplitools(region, batch, app, test);
    }
    
    @When("^I specify visual test batch id \"([^\"]*)\" for test name \"([^\"]*)\"$")
    public void i_specify_visual_test_batch_id(String batch, String testName) throws Throwable {
    	applitools.specifyApplitoolsBatchId(batch, testName);
    }

    @Then("^I close the connection for visual test$")
    public void i_close_the_connection_for_visual_test() throws Throwable {
    	applitools.closeApplitoolsConnection();
    }
    
    @Then("^I use Eyes to verify the Page \"([^\"]*)\" displays$")
    public void i_use_eyes_to_verify_the_page_displays(String page) throws Throwable {
    	applitools.verifyOvcPageApplitools(page);
    }


}