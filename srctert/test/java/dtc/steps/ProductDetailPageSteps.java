package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import dtc.pages.CommonActions;
import dtc.pages.ProductDetailPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class ProductDetailPageSteps {

    private ProductDetailPage productDetailPage;
    private CommonActions commonActions;

    public ProductDetailPageSteps(Driver driver) {
        productDetailPage = new ProductDetailPage(driver);
        commonActions = new CommonActions(driver);
    }

    @Then("^I should see product detail page with \"(.*?)\"$")
    public void i_should_see_the_product_detail_page_with(String productName) throws Throwable {
        productDetailPage.assertProductNameOnProductDetailPage(productName);
    }

    @Then("^I add item to my cart and \"(View shopping cart|Continue Shopping|Close Added To Cart popup)\"$")
    public void i_add_items_to_my_cart_and_take_action(String action) throws Throwable {
        //TODO: This doesn't work for checkoutDefaultViaHomepageKeywordSearchSmoke
        commonActions.addToCart();
        if (action.equalsIgnoreCase(ProductDetailPage.VIEW_SHOPPING_CART)){
            commonActions.clickViewShoppingCart();
        } else if (action.equalsIgnoreCase(ProductDetailPage.CONTINUE_SHOPPING)){
            commonActions.clickContinueShopping();
        } else {
            commonActions.closeAddedToCartPopupWindow();
        }
    }

    @Then("^I should see product specification \"(.*?)\" value to be \"(.*?)\"$")
    public void i_should_see_product_specification_value_to_be(String specLabel, String specValue) throws Throwable {
        productDetailPage.assertProductSpecValue(specLabel, specValue);
    }
    
    @Then("^I should see product specification label \"(.*?)\" present on the page$")
    public void i_should_see_product_specification_label_present(String specLabel) throws Throwable {
        productDetailPage.assertProductSpecLabelVisible(specLabel);
    }
    
    @Then("^I should see product inventory stock message displayed on the page$")
    public void i_should_see_the_product_inventory_stock_details() throws Throwable {
        productDetailPage.assertProductInventoryMessage();
    }

    @Then("^I verify that the price for the product on the product details page is \"(.*?)\"$")
    public void i_verify_product_price_pdp(String price) throws Throwable {
        productDetailPage.assertProductPrice(price);
    }

    @And("^I verify expected \"(Tire|Wheel)\" attributes are displayed for product detail page section \"(.*?)\"$")
    public void i_verify_expected_type_attributes_for_product_detail_page_section
            (String productType, String pageSection) throws Throwable {
        productDetailPage.verifyExpectedTypeAttributesForProductDetailSection(productType, pageSection);
    }

    @And("^I verify the \"We cannot ship items to locations\" message is \"(displayed|not displayed)\" on the product detail page$")
    public void i_verify_cannot_ship_message_displayed_on_product_detail_page(String displayStatus) throws Throwable {
        productDetailPage.verifyCannotShipMessageForProductDetailPage(displayStatus);
    }

    @Then("^I verify PDP page is displayed$")
    public void i_verify_pdp_page_is_displayed() throws Throwable {
        productDetailPage.verifyProductDetailsPage();
    }

    @Then("^I add item to my cart from \"(PLP|PDP|Check Availability)\"$")
    public void i_add_item_to_my_cart(String page) throws Throwable {
        commonActions.addToCart(page);
    }

    @Then("^I verify the stock count message contains \"(.*?)\" on PDP$")
    public void i_verify_the_stock_count_message_on_pdp_text(String text) throws Throwable {
        productDetailPage.verifyStockCountTextPdp(text);
    }

    @Then("^I should see \"([^\"]*)\" on product details page$")
    public void i_should_see_product_on_detail_page(String product) throws Throwable {
        productDetailPage.assertItemIdOnProductDetailPage(product);
    }
}