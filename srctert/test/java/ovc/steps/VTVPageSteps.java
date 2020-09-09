package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.pages.VTVPage;
import utilities.Driver;

/**
 * Created by mginevan on 8/1/2017.
 */
public class VTVPageSteps {

    private VTVPage vtvPage;

    public VTVPageSteps(Driver driver) {
        vtvPage = new VTVPage(driver);
    }

    @And("^I select the replace tire icon$")
    public void i_select_the_replace_tire_icon() throws Throwable {
        vtvPage.replaceTire();
    }

    @When("^I select the replace \"([^\"]*)\" option$")
    public void i_select_the_replace_option(String option) throws Throwable {
    	vtvPage.replaceDesiredTireOption(option);
    }

    @And("^I enter vehicle \"(mileage|location|condition)\": \"(.*?)\" into VTV Vehicle section$")
    public void i_enter_info_into_vtv_vehicle_section(String field, String fieldInfo) throws Throwable {
        vtvPage.enterInfoIntoVehicleInfoField(field, fieldInfo);
    }

    @And("^I verify the \"(mileage|location|condition)\" text box is set to \"([^\"]*)\"$")
    public void i_verify_the_text_box_is_set_to(String field, String fieldInfo) throws Throwable {
        vtvPage.assertVehicleTextField(field, fieldInfo);
    }

    @And("^I select the \"Add/Remove Dual\" symbol on the VTV page$")
    public void i_select_the_add_remove_dual_symbol_on_the_page() throws Throwable {
        vtvPage.selectAddRemoveDualSymbol();
    }

    @Then("^I verify the \"([^\"]*)\" is visible on the VTV page$")
    public void i_verify_the_element_is_visible_on_the_page(String elementName) throws Throwable {
        vtvPage.assertElementIsDisplayedOnPage(elementName);
    }

    @When("^I enter \"([^\"]*)\" into the \"([^\"]*)\" Tire Stats$")
    public void i_enter_value_into_the_input(String value, String input) throws Throwable {
        vtvPage.enterValueTireStatsInput(input, value);
    }

    @And("^I verify the color of the dot next to the \"([^\"]*)\" tag is \"(Red|Green|Yellow)\"$")
    public void i_verify_the_color_of_the_dot_next_to_the_tag(String tireTag, String color) throws Throwable {
        vtvPage.assertTireStatTreadDepthColor(tireTag, color);
    }

    @And("^I select the \"([^\"]*)\" radio button on the VTV page$")
    public void i_select_the_radio_button_label_on_vtv_page(String labelText) throws Throwable {
        vtvPage.selectVTVRadioButton(labelText);
    }

    @And("^I select the \"([^\"]*)\" selection for the \"([^\"]*)\" Tire Service section$")
    public void i_select_selection_for_the_tire_service_sections(String serviceOption, String tire) throws Throwable {
        vtvPage.selectTireAndServiceOption(serviceOption, tire);
    }

    @When("^I expand the Tire Rotation Pattern dropdown$")
    public void i_expand_the_tire_rotation_pattern_dropdown() throws Throwable {
        vtvPage.expandTireRotationDropdown();
    }
}