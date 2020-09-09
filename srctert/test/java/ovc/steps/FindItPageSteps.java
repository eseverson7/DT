package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.OvcData;
import ovc.pages.FindItPage;
import utilities.Driver;

/**
 * Created by eseverson on 9/15/2017.
 */
public class FindItPageSteps {

    private FindItPage findItPage;
    private OvcData ovcData, featureData;

    public FindItPageSteps(Driver driver) {
        findItPage = new FindItPage(driver);
        ovcData = new OvcData();
    }

    @Then("^I verify the \"([^\"]*)\" text appears at the top of the Find It page$")
    public void i_verify_the_text_appears_at_the_top_of_the_find_it_page(String text) throws Throwable {
        findItPage.assertFindItPageTitle(text);
    }

    @When("^I select the \"([^\"]*)\" field on the Find It page$")
    public void i_select_the_field_on_the_find_it_page(String text) throws Throwable {
        findItPage.selectExpandableField(text);
    }

    @And("^I fill the \"(Quantity|Comments|Contact Number)\" text box with the value \"([^\"]*)\" on the Find It page$")
    public void i_fill_the_text_box_with_the_value_on_the_find_it_page(String textbox, String value) throws Throwable {
        findItPage.enterFindItTextBoxValue(textbox, value);
    }
    @Then("^I verify the \"([^\"]*)\" text box is \"([^\"]*)\" on the Find It page$")
    public void i_verify_the_text_box_is_set_to_on_the_find_it_page_old(String textbox, String value) throws Throwable {
        findItPage.assertFindItTextBoxValue(textbox, value);
    }

    @Then("^I verify the \"([^\"]*)\" text box is \"([^\"]*)\" for the feature \"([^\"]*)\"$")
    public void i_verify_the_text_box_is_set_to_for_the_feature(String textbox, String value, String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String dataValue = featureData.VendorNumber;
        findItPage.assertFindItTextBoxValue(textbox, dataValue);
    }

    @And("^I select the displayed \"Order\" button for site \"(Nearby Stores|Managed Inventory|DTD)\" on the Find It " +
            "page for the feature \"(.*?)\"$")
    public void i_select_the_displayed_order_button_for_site_on_the_page(String tab, String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String site = null;
        if (tab.equalsIgnoreCase(findItPage.NEARBY_STORES)) {
            site = featureData.NearbyStoresSite;
        } else if (tab.equalsIgnoreCase(findItPage.MANAGED_INVENTORY)) {
            site = featureData.ManagedInventorySite;
        }
        findItPage.selectDisplayedOrderButton(site);
    }
}
