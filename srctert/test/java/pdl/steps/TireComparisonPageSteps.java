package pdl.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pdl.data.Tire;
import pdl.pages.CommonActions;
import pdl.pages.TireComparisonPage;
import utilities.Driver;

/**
 * Created by aarora on 5/24/17.
 */
public class TireComparisonPageSteps {

	private TireComparisonPage tireComparisonPage;
    private CommonActions commonActions;
    private Tire tire;

    public TireComparisonPageSteps(Driver driver) {
    	tireComparisonPage = new TireComparisonPage(driver);
        commonActions = new CommonActions(driver);
        tire = new Tire();
    }

    @Then("^I verify tire comparison page header is \"(.*?)\"$")
    public void i_verify_tire_comparison_page_header_is(String headerText) throws Throwable {
        tireComparisonPage.verifyTireComparisonPageHeader(headerText);
    }

    @And("^I verify the back button is labeled \"(Results)\"$")
    public void i_verify_back_button_label(String label) throws Throwable {
        commonActions.verifyElementText(tireComparisonPage.resultsLink, label);
    }

    @And("^I verify all tire images are on the compare page$")
    public void i_verify_all_tire_images_are_on_compare_page() throws Throwable {
        tireComparisonPage.verifyProductImagesPresentForAllTires();
    }

    @And("^I verify vendor, model, size, and price of the tire with item id \"(.*?)\"$")
    public void i_verify_tire_details_on_the_compare_page(String itemCode) throws Throwable {
        Tire tireInstance = tire.getTire(Tire.ITEM + itemCode);
        tireComparisonPage.verifyTireDetail(itemCode, tireInstance.vendor);
        tireComparisonPage.verifyTireDetail(itemCode, tireInstance.model);
        tireComparisonPage.verifyTireDetail(itemCode, tireInstance.size);
        tireComparisonPage.verifyTireDetail(itemCode, tireInstance.price);
    }

    @And("^I verify each comparison section of the tire comparison page contains at least one green ribbon$")
    public void i_verify_comparison_sections_of_tire_comparison_page_contains_a_green_ribbon() throws Throwable {
        tireComparisonPage.verifyTireComparisonSectionsContainGreenRibbon();
    }

    @And("^I verify each tire in all comparison sections contains a graph$")
    public void i_verify_each_tire_in_comparison_sections_contains_graph() throws Throwable {
        tireComparisonPage.verifyTiresInComparisonSectionsContainGraphs();
    }

    @When("^I click the Results button to return to the Results screen$")
    public void i_click_results_button() throws Throwable {
        tireComparisonPage.clickResultsButton();
    }

    @And("^I verify each tire contains customer rating stars$")
    public void i_verify_customer_rating_stars_visible_for_each_tire() throws Throwable {
        tireComparisonPage.verifyElementPresentInEachTireComparisonInfoSection(
                TireComparisonPage.customerStarsBy);
    }

    @And("^I verify each tire contains customer recommendation numbers$")
    public void i_verify_customer_recommendation_numbers_visible_for_each_tire() throws Throwable {
        tireComparisonPage.verifyElementPresentInEachTireComparisonInfoSection(
                TireComparisonPage.customerRecommendedBy);

    }

    @When("^I select \"View Details\" for item id \"(.*?)\" from the compare page$")
    public void i_select_view_details_for_item_id_from_compare_page(String itemCode) throws Throwable {
        tireComparisonPage.selectViewDetailsByItemCode(itemCode);
    }
    
    @Then("^I verify \"([^\"]*)\" checkboxes were selected$")
    public void i_verify_checkboxes_were_selected(int number) throws Throwable {
    	tireComparisonPage.productCheckboxSelected(number);
    }
}
