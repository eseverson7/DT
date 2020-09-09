package pdl.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import pdl.pages.OptionalTireSizePage;
import utilities.Driver;

/**
 * Created by aarora on 5/18/17.
 */
public class OptionalTireSizePageSteps {

	private OptionalTireSizePage optionalTireSizePage;

    public OptionalTireSizePageSteps(Driver driver) {
    	optionalTireSizePage = new OptionalTireSizePage(driver);
    }

    @When("^I select staggered set \"(.*?)\"$")
    public void i_select_staggered_set(String staggeredSetValue) throws Throwable {
    	optionalTireSizePage.selectStaggeredSet(staggeredSetValue);
    }

    @When("^I select optional tire sizes for front \"(.*?)\" and for rear \"(.*?)\"$")
    public void i_select_optional_tire_size_for_front_and_rear(String frontTireSize, String rearTireSize) throws Throwable {
    	optionalTireSizePage.selectFrontRearStaggeredOptionalFitment(frontTireSize, rearTireSize);
    }
    
    @When("^I select optional tire size fitment \"(.*?)\"$")
    public void i_select_optional_tire_size(String tireSize) throws Throwable {
    	optionalTireSizePage.selectStandardOptionalFitment(tireSize);
    }
    
    @When("^I click on optional tire size modal close icon$")
    public void i_click_on_optional_tire_size_modal_close_icon() throws Throwable {
    	optionalTireSizePage.clickModalCloseIcon();
    }

}
