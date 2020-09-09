package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.HeaderPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/26/16.
 */
public class HeaderPageSteps {

    private HeaderPage headerNavigationPage;
    private HomePageSteps homePageSteps;

    public HeaderPageSteps(Driver driver) {
        headerNavigationPage = new HeaderPage(driver);
        homePageSteps = new HomePageSteps(driver);
    }

    @When("^I open the \"(TIRES|WHEELS|APPOINTMENTS|SERVICES|TIPS & GUIDES|FINANCING|PROMOTIONS)\" navigation link")
    public void i_open_the_navigation_link(String navigationLink)
            throws Throwable {
        headerNavigationPage.clickNavigationOption(navigationLink);
    }

    @And("^I click the \"([^\"]*)\" menu option")
    public void i_click_the_menu_option(String menuOption)
            throws Throwable {
        headerNavigationPage.clickNavigationMenuOption(menuOption);
    }

    @When("^I click the discount tire logo")
    public void i_click_the_discount_tire_logo()
            throws Throwable {
        headerNavigationPage.clickSiteLogo();
    }

    @Then("^I click the \"([^\"]*)\" View All link in the header$")
    public void i_click_the_view_all_link_in_the_header(String section) throws Throwable {
        headerNavigationPage.clickViewAllNavigationOption(section);
    }
    
    @When("^I verify the \"(TIRES|WHEELS|APPOINTMENTS|SERVICES|TIPS & GUIDES|FINANCING|PROMOTIONS)\" navigation link is displayed")
    public void i_verify_the_navigation_link_is_displayed(String navigationLink) throws Throwable {
        headerNavigationPage.assertNavigationOptionIsDisplayed(navigationLink);
    }
    
    //TODO: Will consolidate this step definition with previous one in customer data refactoring 
    @Then("^I verify Join/Sign-in is displayed on global header$")
    public void i_verify_the_join_sign_in_is_displayed() throws Throwable {
    	homePageSteps.i_verify_the_join_sign_in_is_displayed();
    }
}