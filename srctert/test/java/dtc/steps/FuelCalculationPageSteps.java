package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import dtc.pages.FuelCalculationPage;
import utilities.Driver;

/**
 * Created by Mukul Garg on 10/31/2017.
 */
public class FuelCalculationPageSteps {

    private FuelCalculationPage fuelCalculationPage;

    public FuelCalculationPageSteps(Driver driver) {
        fuelCalculationPage = new FuelCalculationPage(driver);
    }

    @And("^I fill the \"(Miles Per Day|Gas Price|Average MPG)\" text box with the value \"([^\"]*)\" on the Fuel Calculation page$")
    public void i_fill_the_text_box_with_the_value_on_the_fuel_calculation_page(String textBox, String value) throws Throwable {
        fuelCalculationPage.enterFuelCalculationTextBoxValue(textBox, value);
    }

    @Then("^I verify the Fuel Calculator PSI result table is updated$")
    public void i_verify_fuel_calculation_psi_results_table_updated() throws Throwable {
        fuelCalculationPage.assertFuelCalculationPSIValues();
    }
}
