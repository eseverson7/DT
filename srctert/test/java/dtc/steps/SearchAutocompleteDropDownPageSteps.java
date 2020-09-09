package dtc.steps;

import common.Config;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.CommonActions;
import dtc.pages.SearchAutocompleteDropDownPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class SearchAutocompleteDropDownPageSteps {

    private SearchAutocompleteDropDownPage searchAutocompleteDropDownPage;
    private final CommonActions commonActions;

    public SearchAutocompleteDropDownPageSteps(Driver driver) {
        searchAutocompleteDropDownPage = new SearchAutocompleteDropDownPage(driver);
        commonActions = new CommonActions(driver);
    }

    @Then("^I should see product list page with \"(.*?)\"$")
    public void i_should_see_the_product_list_page_with_result(String productName) throws Throwable {
        if (!Config.isMobile()) {
            searchAutocompleteDropDownPage.assertFirstProductNameInResultPanel(productName);
        }
    }

    @When("^I select \"(.*?)\" from the autocomplete dropdown of the search box$")
    public void i_select_text_from_the_search_dropdown(String text) throws Throwable {
        if (Config.isMobile()) {
            commonActions.clickElementByText(text);
        } else {
            searchAutocompleteDropDownPage.selectProductByName(text);
        }
    }
}