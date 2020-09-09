package dtc.steps;

import cucumber.api.java.en.Then;
import dtc.pages.CommonActions;
import dtc.pages.CustomerServicePage;
import utilities.Driver;

/**
 * Created by Eric on 11/28/2016.
 */
public class CustomerServicePageSteps {

    private CommonActions commonActions;
    private CustomerServicePage customerServicePage;

    public CustomerServicePageSteps(Driver driver) {
        commonActions = new CommonActions(driver);
        customerServicePage = new CustomerServicePage(driver);
    }

    @Then("^I click on the \"([^\"]*)\" additional Customer Care link$")
    public void i_click_on_the_additional_customer_care_link(String link) throws Throwable {
        customerServicePage.clickCustomerCareLinks(link);
    }

    @Then("^I verify all major credit cards appear on the page$")
    public void i_verify_all_major_credit_cards_appear_on_the_page() throws Throwable {
        customerServicePage.verifyMajorCreditCardsDisplay();
    }
}
