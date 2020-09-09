package sap.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import sap.pages.Navigation;
import utilities.Driver;

public class NavigationSteps {

    private Navigation navigation;

    public NavigationSteps(Driver driver) {
        navigation = new Navigation(driver);
    }

    @And("^I click on the \"(.*?)\" icon from the SAP navigation bar$")
    public void i_click_on_icon_from_sap_nav_bar(String icon) throws Throwable {
        navigation.clickNavIcon(icon);
    }

    @When("^I enter t-code \"([^\"]*)\" in the command field$")
    public void i_enter_t_code_in_the_command_field(String tCode) throws Throwable {
        navigation.enterTCode(tCode);
    }
}
