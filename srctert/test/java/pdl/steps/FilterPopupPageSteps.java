package pdl.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pdl.pages.FilterPopupPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 6/7/17.
 */
public class FilterPopupPageSteps {

    private FilterPopupPage filterPopupPage;

    public FilterPopupPageSteps(Driver driver) {
        filterPopupPage = new FilterPopupPage(driver);
    }

    @Then("^I verify that the filter window \"(displays|closes)\"$")
    public void i_verify_filter_popup_open_or_closed(String status) throws Throwable {
        filterPopupPage.assertFilterPopupOpenOrClosed(status);
    }

    @When("^I select filter brand \"(.*?)\"$")
    public void i_select_filter_brand(String brand) throws Throwable {
        filterPopupPage.selectBrand(brand);
    }

    @And("^I select Apply Filter$")
    public void i_select_apply_filter() throws Throwable {
        filterPopupPage.clickApplyFilterButton();
    }

    @And("^I select Reset All")
    public void i_select_reset_All() throws Throwable {
        filterPopupPage.clickResetAllButton();
    }

    @When("^I select \"([^\"]*)\" from the Stock dropdown$")
    public void i_select_quantity_from_the_stock_dropdown(String quantity) throws Throwable {
        filterPopupPage.selectStockQuantity(quantity);
    }

    @And("^I select the Other checkbox with \"([^\"]*)\"$")
    public void i_select_the_other_checkbox_with_text(String text) throws Throwable {
        filterPopupPage.selectOtherCheckbox(text);
    }

    @Then("^I verify the No Products Match error on the Filter popup$")
    public void i_verify_the_no_products_match_error_on_the_filter_popup() throws Throwable {
        filterPopupPage.assertFilterErrorMessage(filterPopupPage.NO_PRODUCTS_MATCH);
    }
}
