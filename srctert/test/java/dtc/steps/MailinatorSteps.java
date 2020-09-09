package dtc.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.MailinatorPage;
import utilities.Driver;

/**
 * Created by Channing Luden on 2/27/2017.
 */
public class MailinatorSteps {
    private MailinatorPage mailinatorPage;
    private final Driver driver;

    public MailinatorSteps(Driver driver){
        mailinatorPage = new MailinatorPage(driver);
        this.driver = driver;
    }

    @When("^I open Mailinator inbox: \"(.*?)\"$")
    public void i_open_mailinator_inbox(String emailInbox) throws Throwable {
        mailinatorPage.openMailinatorInbox(emailInbox);
    }

    @Then("^I verify most recent email in the current mailinator inbox is from sender: \"(.*?)\" with title: \"(.*?)\" $")
    public void i_verify_most_recent_email_in_mailinator_inbox_from_sender_with_title(
            String expectedSender, String expectedTitle) throws Throwable {
        mailinatorPage.verifyMostRecentDtOrderConfirmationInEmailInbox(expectedSender,
                expectedTitle);
    }

    @When("^I delete all emails in the current mailinator inbox$")
    public void i_delete_all_emails_in_current_mailinator_inbox() throws Throwable {
        mailinatorPage.deleteEmailsFromInbox();
    }
}