package dtc.steps;

import cucumber.api.java.en.Then;
import dtc.pages.SiteMapPage;
import utilities.Driver;

/**
 * Created by Channing Luden on 12/9/2016.
 */
public class SiteMapSteps {

    SiteMapPage siteMapPage;

    public SiteMapSteps(Driver driver) {
        siteMapPage = new SiteMapPage(driver);
    }

    @Then("^I click and verify all the links in the " +
            "\"(ABOUT US|CAREERS|CONTACT US|CUSTOMER SERVICES|SERVICES|LEARN|TIRES|TIRE BRANDS|WHEELS|WHEEL BRANDS)\"" +
            " section of the Site Map page$")
    public void i_click_all_links_in_section_of_site_map_page(String sectionTitle) throws Throwable {
        siteMapPage.clickSectionLinksWithVerification(sectionTitle);
    }
}
