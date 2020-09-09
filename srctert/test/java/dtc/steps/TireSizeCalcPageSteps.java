package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.TireSizeCalcPage;
import utilities.Driver;

/**
 * Created by Channing Luden on 11/30/2016.
 */
public class TireSizeCalcPageSteps {

    private TireSizeCalcPage tireSizeCalcPage;

    public TireSizeCalcPageSteps(Driver driver) {
        tireSizeCalcPage = new TireSizeCalcPage(driver);
    }

    @Then("^I verify Tire Size calculator controls and expected sections are displayed$")
    public void i_verify_tire_size_calc_controls_plus_sections_display() throws Throwable {
        tireSizeCalcPage.verifyBasicDisplayOfPage();
    }

    @When("^I enter \"(current|new)\" tire size of \"(\\d{3})\" / \"(\\d{2})\" R \"(\\d{2})\" into " +
            "the Tire Size Calculator$")
    public void i_enter_tire_size_into_tire_size_calc(String tireSizeType, String tireDiameter, String tireWidth,
                                                      String wheelDiameter) throws Throwable {
        tireSizeCalcPage.enterTireSizeIntoCalc(tireSizeType, tireDiameter, tireWidth, wheelDiameter);
    }

    @Then("^I verify the Tire Size Calculator results table is updated$")
    public void i_verify_tire_size_calc_results_table_updated() throws Throwable {
        tireSizeCalcPage.verifyTireSizeCalcResultsTableUpdated();
    }

    @And("^I verify the Side by Side Comparison section is updated with a size of" +
            " \"(\\d{3})\" / \"(\\d{2})\" R \"(\\d{2})\"$")
    public void i_verify_side_by_side_comparison_section_updated(String tireDiameter, String tireWidth,
                                                                 String wheelDiameter) throws Throwable {
        tireSizeCalcPage.verifySideBySideSectionContainsTireSize(tireDiameter, tireWidth, wheelDiameter);
    }

    @And("^I enter a speed of \"(\\d{1,3})\" as the speedometer reading$")
    public void i_enter_speed_into_speedometer_reading(String speedToEnter) throws Throwable {
        tireSizeCalcPage.enterNewSpeedIntoSpeedometer(speedToEnter);
    }

    @Then("^I verify the traveling speed is updated to \"(\\d{1,3})\"$")
    public void i_verify_traveling_speed_updated(String expectedTravelingSpeed) throws Throwable {
        tireSizeCalcPage.verifyUpdatedTravelingSpeed(expectedTravelingSpeed);
    }
}
