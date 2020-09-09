package dtc.steps;

import cucumber.api.java.en.And;
import dtc.pages.FooterPage;
import dtc.pages.TipsAndGuidesPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class TipsAndGuidesPageSteps {

    private FooterPage footerPage;
    private TipsAndGuidesPage tipsAndGuidesPage;

    public TipsAndGuidesPageSteps(Driver driver) {
        footerPage = new FooterPage(driver);
        tipsAndGuidesPage = new TipsAndGuidesPage(driver);
    }

    @And("^I click the list element \"([^\"]*)\"$")
    public void i_click_the_list_element(String linkText) throws Throwable {
        tipsAndGuidesPage.clickPageLink(linkText);
    }

    @And("^I verify the brand links on the Torque Chart page$")
    public void i_verify_the_brand_links_on_the_torque_chart_page () throws Throwable {
        tipsAndGuidesPage.verifyBrandLinks();
    }

}
