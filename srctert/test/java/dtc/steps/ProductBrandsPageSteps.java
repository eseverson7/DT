package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.data.ConstantsDtc;
import dtc.pages.ProductBrandsPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/27/16.
 */
public class ProductBrandsPageSteps {

    private ProductBrandsPage productBrandsPage;

    public ProductBrandsPageSteps(Driver driver) {
        productBrandsPage = new ProductBrandsPage(driver);
    }

    @When("^I select \"([^\"]*)\" from the Product Brands page$")
    public void i_select_from_product_brands_page(String option) throws Throwable {
        productBrandsPage.selectBrandOption(option);
    }

    @And("^I select \"([^\"]*)\" from the Product Brand page$")
    public void i_select_option_from_the_product_brand_page(String option) throws Throwable {
        if (option.equalsIgnoreCase(ConstantsDtc.VIEW_ALL)) {
            productBrandsPage.selectViewAllOptionButton();
        } else {
            productBrandsPage.selectTireWheelBrandOption(option);
        }

    }

    @Then("^I verify all listed brands have corresponding images$")
    public void i_verify_all_listed_brands_have_images() throws Throwable {
        productBrandsPage.verifyAllListBrandsHaveImages();
    }
}