package dtc.steps;

import common.Config;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.CompareProductsPage;
import dtc.pages.ProductListPage;
import utilities.Driver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eric on 10/19/2016.
 */
public class CompareProductsPageSteps {

    private final Driver driver;
    private final CompareProductsPage compareProductsPage;
    private final ProductListPage productListPage;

    HashMap<String, ArrayList<String>> values;

    public CompareProductsPageSteps(Driver driver) {
        this.driver = driver;
        compareProductsPage = new CompareProductsPage(driver);
        productListPage = new ProductListPage(driver);
    }

    @And("^I select the first \"([2|3])\" results to compare$")
    public void i_select_the_first_results_to_compare(Integer quantity) throws Throwable {
        if (Config.isMobile()) {
            values = productListPage.clickCompareCheckboxesMobile(quantity);
        } else {
            values = productListPage.clickCompareCheckboxes(quantity);
        }
    }

    @Then("^I verify all categories are present for the \"([2|3])\" products$")
    public void i_verify_all_categories_are_present_for_each_product(Integer quantity) throws Throwable {
        if (!Config.isMobilePhone()) {
            compareProductsPage.assertGoodBetterBestBackgroundColor();
        }
        compareProductsPage.verifyTopProductDetails(values, quantity);
        compareProductsPage.verifyCompareProductsPageElements();
    }

    @And("^I select a single product to compare$")
    public void i_select_single_product_to_compare() throws Throwable {
        if (Config.isMobile()) {
            values = productListPage.clickCompareCheckboxesMobile(1);
        } else {
            values = productListPage.clickCompareCheckboxes(1);
        }
    }

    @And("^I click the compare products Compare button$")
    public void i_click_the_compare_products_compare_button() throws Throwable {
        if (Config.isMobile()) {
            productListPage.clickCompareProductsButtonMobile();
        } else {
            productListPage.clickCompareProductsButton();
        }
    }

    @When("^I click Remove All$")
    public void i_click_remove_all() throws Throwable {
        compareProductsPage.clickRemoveAll();
        productListPage.setTotalSelectedItems(0);
    }

    @When("^I add the first item to my cart and click \"Close\" on the Compare Products page$")
    public void i_add_first_item_to_my_cart_and_click_on_the_compare_product_page() throws Throwable {
        compareProductsPage.addFirstItemToCartAndClose();
    }

    @And("^I click the X next to the first product on the compare product page$")
    public void i_click_the_x_next_to_the_first_product_on_the_compare_product_page() throws Throwable {
        compareProductsPage.clickXNextToFirstComparedItem();
        int totSelected = productListPage.getTotalSelectedItems() - 1;
        productListPage.setTotalSelectedItems(totSelected);
    }

    @And("^I click the undo remove product button$")
    public void i_click_the_undo_remove_product_button() throws Throwable {
        compareProductsPage.clickUndoRemove();
        int totSelected = productListPage.getTotalSelectedItems() + 1;
        productListPage.setTotalSelectedItems(totSelected);
    }

    @When("^I remove the first item on the compare product page$")
    public void i_remove_first_item_on_compare_product_page() throws Throwable {
        compareProductsPage.clickXNextToFirstComparedItem();
        productListPage.removeFirstSelectedItem();
    }

    @And("^I verify expected attributes are displayed for compare page section \"(.*?)\"$")
    public void i_verify_expected_attributes_for_compare_page_section(String pageSection) throws Throwable {
        compareProductsPage.verifyExpectedAttributesForCompareSection(pageSection);
    }

    @And("^I select item number\\(s\\): \"(.*?)\" from the results list to compare$")
    public void i_select_item_numbers_from_results_list_to_compare(String itemNumbers) throws Throwable {
        productListPage.selectItemNumbersForComparison(itemNumbers);
    }

    @And("^I verify \"Add to cart\" is enabled for each product being compared$")
    public void i_verify_add_to_cart_enabled_for_each_compared_product() throws Throwable {
        compareProductsPage.verifyAddToCartEnabledForProducts();
    }

    @And("^I verify the \"We cannot ship items to locations\" message is \"(displayed|not displayed)\" for all compared products$")
    public void i_verify_cannot_ship_message_display_for_all_compared_products(String displayStatus) throws Throwable {
        compareProductsPage.verifyCannotShipMessageForAllProducts(displayStatus);
    }

    @When("^I select item number: \"(.*?)\" from compare products to view its product details$")
    public void i_select_item_number_from_compare_products_to_view_product_details(String itemNumber) throws Throwable {
        compareProductsPage.viewItemDetailsForProductByItemNumber(itemNumber);
    }

    @When("^I select 'Add an item' to compare$")
    public void i_select_add_an_item_to_compare() throws Throwable {
        compareProductsPage.selectAddAnItemToCompare();
    }
}