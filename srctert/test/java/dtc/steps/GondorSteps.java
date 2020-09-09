package dtc.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.GondorPage;
import utilities.Driver;

/**
 * Created by Mukul Garg on 8/30/2017.
 */
public class GondorSteps {
    private GondorPage gondorPage;

    public GondorSteps(Driver driver) {
        gondorPage = new GondorPage(driver);
    }

    @When("^I open Gondor inbox username: \"(.*?)\"$  with password: \"(.*?)\" $")
    public void i_open_gondor_inbox(String emailInbox, String password) throws Throwable {
        gondorPage.openGondorInbox(emailInbox, password);
    }

    @Then("^I verify most recent email in the current gondor inbox with orderid")
    public void i_verify_most_recent_email_in_gondor_inbox_with_orderid(
    ) throws Throwable {
        gondorPage.verifyMostRecentDtOrderConfirmationInEmailInbox();
    }
}