package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.ProductAvailabilityPopupPage;
import dtc.pages.CommonActions;
import utilities.Driver;

/**
 * Created by abriel on 1/11/2017.
 */
public class ProductAvailabilityPopupPageSteps {

    private ProductAvailabilityPopupPage productAvailabilityPopupPage;

    public ProductAvailabilityPopupPageSteps(Driver driver) {
        productAvailabilityPopupPage = new ProductAvailabilityPopupPage(driver);
    }

    @Then("^I should verify that the Check Availability popup loaded$")
    public void i_should_verify_that_the_check_availability_popup_loaded() throws Throwable {
        productAvailabilityPopupPage.assertCheckAvailabilityPopupLoaded();
    }
    
    @Then("^I should verify that In Stock Label is not displayed on Check Inventory popup$")
    public void i_should_verify_that_inStock_label_not_present_on_check_availability_popup() throws Throwable {
        productAvailabilityPopupPage.assertInStockLabelNotPresent();
    }
    
    @Then("^I should verify that Special Order Label is displayed on Check Inventory popup$")
    public void i_should_verify_that_special_order_label__present_on_check_availability_popup() throws Throwable {
        productAvailabilityPopupPage.assertSpecialOrderLabelPresent();
    }

    @When("^I close the Check Inventory popup$")
    public void i_close_check_availability_popup() throws Throwable {
        productAvailabilityPopupPage.closeCheckAvailabilityPopupModal();
    }
    
    @Then("^I should verify that default store MY STORE label is at top and visible$")
    public void i_should_verify_that_default_store_myStore_label_is_at_top() throws Throwable {
        productAvailabilityPopupPage.assertDefaultStoreMyStoreLabelIsTopInTheList();
    }

    @And("^I should verify that the Check Inventory headline is \"(.*?)\"$")
    public void i_should_verify_product_availability_headline(String headline) throws Throwable {
        productAvailabilityPopupPage.assertCheckAvailabilityHeadline(headline);
    }

    @And("^I should verify that the Check Inventory product image is displayed$")
    public void i_should_verify_product_image_displayed() throws Throwable {
        productAvailabilityPopupPage.assertProductImageDisplayed();
    }

    @And("^I should verify that the Product Info shows \"(.*?)\", \"(.*?)\", and \"(.*?)\"$")
    public void i_should_verify_product_info(String brand, String productName, String tireSize)
            throws Throwable {
        productAvailabilityPopupPage.assertProductInfo(brand);
        productAvailabilityPopupPage.assertProductInfo(productName);
        productAvailabilityPopupPage.assertProductInfo(tireSize);
    }

    @And("^I should verify price is not blank$")
    public void i_verify_price_not_blank() throws Throwable {
        productAvailabilityPopupPage.assertPricePopulated();
    }

    @When("^I enter a quantity of \"(.*?)\"$")
    public void i_enter_quantity(String quantity) throws Throwable {
        productAvailabilityPopupPage.enterQuantity(quantity);
    }

    @Then("^I should verify that a \"(quantity|validity)\" error message is displayed$")
    public void i_should_verify_quantity_error(String errorType) throws Throwable {
        productAvailabilityPopupPage.assertQuanityErrorMessage(errorType);
    }

    @And("^I should verify that the Add To Cart button is disabled$")
    public void i_should_verify_add_to_cart_disabled() throws Throwable {
        productAvailabilityPopupPage.assertAddToCartDisabled();
    }

    @Then("^I should verify that the show quantity filter is displayed with \"(.*?)\"$")
    public void i_verify_show_quantity_filter(String quantity) throws Throwable {
        productAvailabilityPopupPage.assertShowQuantityFilter(quantity);
    }

    @When("^I enter a zipcode of \"(.*?)\"$")
    public void i_enter_zipcode(String zipcode) throws Throwable {
        //TODO: Zip code will need to be conditionalized based on environment and site (Defect # 6833)
        productAvailabilityPopupPage.enterZipcode(zipcode);
    }

    @Then("^I should verify that a zip code error message is displayed$")
    public void i_should_verify_zipcode_error() throws Throwable {
        productAvailabilityPopupPage.assertZipcodeErrorMessage();
    }

    @When("^I click go and wait for results to load$")
    public void i_click_go() throws Throwable {
        productAvailabilityPopupPage.clickGoAndWaitForResults();
    }

    @Then("^the first store listed should be within \"(.*?)\" miles$")
    public void i_should_verify_first_store(String miles) throws Throwable {
        productAvailabilityPopupPage.assertFirstStoreWithinDistance(miles);
    }

    @When("^I click the \"(In stock|Show more)\" filter$")
    public void i_click_filter(String filter) throws Throwable {
        productAvailabilityPopupPage.clickFilter(filter);
    }

    @Then("^I should verify the first store has stock quantity greater than \"(.*?)\"$")
    public void i_should_verify_first_store_quantity_above_specified_value(String quantity) throws Throwable {
        productAvailabilityPopupPage.assertFirstStoreQuantityAboveSpecifiedValue(quantity);
    }

    @When("^I select store \"(.*?)\"$")
    public void i_select_store(String storeNum) throws Throwable {
        productAvailabilityPopupPage.selectStore(Integer.parseInt(storeNum)-1);
    }

    @Then("^I should verify that store \"(.*?)\" is now the current store$")
    public void i_should_verify_store_is_current_store(String storeNum) throws Throwable {
        productAvailabilityPopupPage.assertStoreIsCurrentStore(Integer.parseInt(storeNum)-1);
    }

    @And("^I should verify that make my store button is displayed$")
    public void i_should_verify_that_make_my_store_button_is_displayed() throws Throwable {
        productAvailabilityPopupPage.assertMakeMyStoreButtonDisplayed();
    }
}

