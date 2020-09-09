package patchtesting.steps;

import cucumber.api.java.en.When;
import patchtesting.pages.Calculators;
import utilities.Driver;

public class CalculatorsSteps {

    private Calculators calculators;

    public CalculatorsSteps(Driver driver) {
        calculators = new Calculators(driver);
    }

    @When("^I select \"([^\"]*)\" sub-menu item after selecting the \"(Special Orders|Products|Showroom|Education|Fitment)\" " +
            "menu header option on the Calculators page$")
    public void i_select_sub_menu_item_after_selecting_the_menu_item_header_option_on_calculators_page
            (String subMenuOption, String menuOption) throws Throwable {
        calculators.selectSubMenuOptionWithClick(menuOption, subMenuOption);
    }
}
