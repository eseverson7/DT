package dtc.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.MobileHeaderPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 1/20/17.
 */
public class MobileHeaderPageSteps {

    private MobileHeaderPage mobileHeaderPage;

    public MobileHeaderPageSteps(Driver driver) {
        mobileHeaderPage = new MobileHeaderPage(driver);
    }

    @When("^I click on \"([^\"]*)\" menu link$")
    public void i_click_on_link(String link) throws Throwable {
        mobileHeaderPage.clickMenuLink(link);
    }

    @When("^I click on \"([^\"]*)\" header link$")
    public void i_click_on_header_link(String link) throws Throwable {
        mobileHeaderPage.clickHeaderLink(link);
    }

    @Then("^I verify all of the mobile header elements are visible")
    public void i_verify_mobile_header_elements()
            throws Throwable {
        mobileHeaderPage.verifyMobileHeaderElements();
    }

    @When("^I click the mobile homepage menu$")
    public void i_click_the_mobile_homepage_menu() throws Throwable {
        mobileHeaderPage.openMobileMenu();
    }

    @Given("^I click the discount tire logo in the mobile header$")
    public void i_click_the_dt_logo_in_mobile_header() throws Throwable {
        mobileHeaderPage.clickSiteLogo();
    }

    @Then("^I am brought to the mobile page with header \"([^\"]*)\"$")
    public void i_am_brought_to_the_mobile_page_with_header(String pageTitle) throws Throwable {
        mobileHeaderPage.assertPageH1Header(pageTitle);
    }
}
