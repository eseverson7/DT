package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.FooterPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class FooterPageSteps {

    private FooterPage footerPage;

    public FooterPageSteps(Driver driver) {
        footerPage = new FooterPage(driver);
    }

    @When("^I select \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_select_autoclass_link(String type, String linkText) throws Throwable {
        footerPage.clickAutoClassLink(type, linkText);
    }

    @And("^I verify the current URL contains \"([^\"]*)\"$")
    public void i_verify_the_current_url_contains(String urlText) throws Throwable {
        footerPage.verifySocialMediaUrl(urlText);
    }

    @Then("^I verify \"([^\"]*)\" appears on the Apply Now page$")
    public void i_verify_appears_on_the_apply_now_page(String text) throws Throwable {
        footerPage.assertApplyNowPageText(text);
    }

    @Then("^I verify \"([^\"]*)\" appears on the Commercial Payment page$")
    public void i_verify_appears_on_the_commercial_payment_page(String text) throws Throwable {
        footerPage.assertCommercialPaymentsText(text);
    }

    @Then("^I verify the Copyright Text of footer is Displayed$")
    public void i_verify_the_copyright_text_of_footer_is_Displayed() throws Throwable {
        footerPage.verifyCopyrightText();
    }

    @And("^I verify \"(.*?)\" footer link is displayed$")
    public void i_verify_footer_link_is_displayed(String link) throws Throwable {
    	footerPage.assertFooterLinkIsDisplayed(link);
    }
    
    @Then("^I verify the Headline Text of footer is displayed$")
    public void i_verify_the_footer_headline_text_is_displayed() throws Throwable {
        footerPage.verifyFooterHeadlineText();
    }
}
