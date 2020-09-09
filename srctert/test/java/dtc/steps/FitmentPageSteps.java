package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.FitmentPage;
import org.openqa.selenium.WebElement;
import utilities.Driver;

/**
 * Created by aaronbriel on 1/30/17.
 */
public class FitmentPageSteps {

    private FitmentPage fitmentPage;

    public FitmentPageSteps(Driver driver) {
        fitmentPage = new FitmentPage(driver);
    }

    @When("^I click the \"([^\"]*)\" dropdown$")
    public void i_click_the_dropdown(String dropDownType)
            throws Throwable {
        fitmentPage.expandFitmentDropdown(dropDownType);
    }

    @When("^I click the disabled dropdown with value \"([^\"]*)\"$")
    public void i_click_the_disabled_dropdown(String dropDownType)
            throws Throwable {
        fitmentPage.clickDisabledFitmentDropdown(dropDownType);
    }

    @Then("^I verify that a dropdown has the value \"([^\"]*)\"$")
    public void i_verify_the_dropdown_has_value(String value)
            throws Throwable {
        fitmentPage.verifyDropdownWithValueIsDisplayed(value);
    }

    @When("^I select \"([^\"]*)\" from the expanded dropdown$")
    public void i_select_a_dropdown_value_from_an_expanded_menu(String value)
            throws Throwable {
        fitmentPage.selectDropDownValueFromExpandedMenu(value);
    }

    @And("^I verify that the \"([^\"]*)\" value in the dropdown is selected")
    public void i_verify_the_dropdown_value_is_selected(String value)
            throws Throwable {
        fitmentPage.verifyDropdownValueSelected(value);
    }

    @When("^I verify that the \"(Tires|Wheels|Pay now, pick up in-store|Reserve now, pick up in-store)\" radio " +
            "button is \"(enabled|disabled)\"$")
    public void i_verify_the_radio_button_is_enabled_or_disabled(String buttonType, String enabled)
            throws Throwable {
        fitmentPage.verifyRadioButtonEnabledOrDisabled(buttonType, Boolean.parseBoolean(enabled));
    }

    @When("^I type \"(.*?)\" in the \"(.*?)\" dropdown$")
    public void i_type_the_value_in_the_dropdown(String value, String dropDown)
            throws Throwable {
        fitmentPage.typeValueInDropdown(dropDown, value);
    }

    @Then("^I verify that the items in the drop down display in alphabetically order$")
    public void i_verify_the_dropdown_values_are_sorted()
            throws Throwable {
        fitmentPage.verifyDropdownValuesSorted();
    }

    @Then("^I verify that the dropdown is limited to values that start with \"(.*?)\"$")
    public void i_verify_the_dropdown_values_are_limited_to_specific_values(String character)
            throws Throwable {
        fitmentPage.verifyDropdownValuesLimited(character);
    }

    @And("^I click the fitment \"(Wheels|Tires)\" radio button$")
    public void i_click_the_fitment_radio_button(String buttonType) throws Throwable {
        fitmentPage.clickFitmentRadioButton(buttonType);
    }

    @And("^I verify that the dropdown menu \"(has|has not)\" expanded")
    public void i_verify_the_dropdown_menu_has_expanded(String expanded)
            throws Throwable {
        if (expanded.equalsIgnoreCase("has")) {
            fitmentPage.verifyDropdownMenuExpanded(true);
        } else {
            fitmentPage.verifyDropdownMenuExpanded(false);
        }
    }

    @And("^I verify that the Search button is disabled")
    public void i_verify_the_search_button_is_disabled()
            throws Throwable {
        fitmentPage.assertSearchButtonIsDisabled();
    }

    @And("^I verify that the Search button is enabled")
    public void i_verify_the_search_button_is_enabled()
            throws Throwable {
        fitmentPage.assertSearchButtonIsEnabled();
    }

    @And("^I verify that the Search button color is \"([^\"]*)\"$")
    public void i_verify_the_search_button_color(String color)
            throws Throwable {
        fitmentPage.assertSearchButtonColor(color);
    }

    @When("^I select \"(.*?)\" from the \"([^\"]*)\" dropdown$")
    public void i_select_a_dropdown_value(String value, String dropDownType)
            throws Throwable {
        WebElement dropDown = fitmentPage.getDropDown(dropDownType);
        fitmentPage.selectDropDownValue(dropDown, value);
    }

    @When("^I do a vehicle search with details \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\"$")
    public void i_do_a_vehicle_search_with_details(String year, String make,
                                                   String model, String trim, String assembly)
            throws Throwable {
        fitmentPage.vehicleSearch(year, make, model, trim, assembly);
    }

    @When("^I do a \"(.*?)\" tire size search with details \"(.*?)\" \"(.*?)\" \"(.*?)\"$")
    public void i_do_a_tire_size_search_with_details(String searchType, String sectionWidth,
                                                     String aspectRatio, String wheelDiameter)
            throws Throwable {
        fitmentPage.tireSizeSearch(searchType, sectionWidth, aspectRatio, wheelDiameter);
    }

    @And("^I do a \"([^\"]*)\" wheel size search with details \"([^\"]*)\" \"([^\"]*)\" \"(.*)\"$")
    public void i_do_a_wheel_size_search_with_details(String searchType, String diameter,
                                                      String wheelWidth, String boltPattern) throws Throwable {
        fitmentPage.wheelSizeSearch(searchType, diameter, wheelWidth, boltPattern);
    }

    @And("^the dropdown \"([^\"]*)\" is visible on the page$")
    public void the_dropdown_is_visible_on_the_page(String dropDown) throws Throwable {
        fitmentPage.assertDropdownsAreVisible(dropDown);
    }

    @And("^I verify the My Vehicle \"(Vehicle Search|Tire Size Search|Wheel Size Search)\" \"([^\"]*)\" " +
            "dropdown has no decimals$")
    public void i_verify_the_my_vehicle_dropdown_has_no_decimals(String tab, String dropdownName)
            throws Throwable {
        fitmentPage.assertNoDecimalInMyVehicleDropdown(tab, dropdownName);
    }

    @Then("^I verify the default values of the \"(Vehicle|Brand|Tire Size|Wheel Size)\" dropdowns$")
    public void i_verify_default_values_of_dropdowns(String shopBy) throws Throwable {
        fitmentPage.verifyDefaultDropdownValues(shopBy);
    }

    @When("^I navigate to Shop by Brand$")
    public void i_navigate_to_Shop_by_Brand() throws Throwable {
        fitmentPage.clickShopByBrand();
    }

    @When("^I navigate to Shop by Size")
    public void i_navigate_to_Shop_by_Size() throws Throwable {
        fitmentPage.clickShopBySize();
    }

    @When("^I navigate to Shop by Vehicle$")
    public void i_navigate_to_Shop_by_Vehicle() throws Throwable {
        fitmentPage.clickShopByVehicle();
    }

    @And("^I select \"(.*?)\" and find products$")
    public void i_select_and_find_products(String brand) throws Throwable {
        fitmentPage.enterBrandName(brand);
        fitmentPage.clickFindTiresOrWheels();
    }

    @Then("^I verify all of the fitment menus are displayed$")
    public void i_verify_all_fitment_menus_are_displayed() throws Throwable {
        fitmentPage.verifyFitmentMenusDisplayed();
    }

    @When("^I navigate to Shop By \"(Vehicle|Brand|Tire Size|Wheel Size)\"$")
    public void i_navigate_to_the_shop_by_tab(String shopBy) throws Throwable {
        fitmentPage.clickShopByTab(shopBy);
    }
    
    @Then("^I verify that \"(.*?)\" dropdown arrow is pointing \"(upwards|downwards)\"$")
    public void i_verify_that_selected_dropdown_arrow_position_is(String dropdown, String position)
            throws Throwable {
        fitmentPage.assertSelectedDropDownArrowPosition(dropdown, position);
    }

    @Then("^I verify the default selection in the fitment component$")
    public void i_verify_the_default_selection_in_the_fitment_component() throws Throwable {
        fitmentPage.verifyShopByVehicleIsDefault();
    }

    @Then("^I verify no results page displays$")
    public void i_verify_no_results_page() throws Throwable {
        fitmentPage.verifyNoResultsPageMessage();
    }
    
    @Then("^I verify Shop By Brand default placeholder text$")
    public void i_verify_shop_by_brand_placeholder_text() throws Throwable {
        fitmentPage.assertShopByBrandDefaultPlaceholderText();
    }
    
    @Then("^I verify wheels shop by size fields are in correct order left to right$")
    public void i_verify_wheels_shop_by_size_fields_ordering() throws Throwable {
        fitmentPage.assertWheelsShopBySizeFieldsAreInSpecificOrderLeftToRight();;
    }
}
