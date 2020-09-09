package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.BrandsPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/26/16.
 */
public class BrandsPageSteps {

    private BrandsPage brandsPage;

    public BrandsPageSteps(Driver driver) {
        brandsPage = new BrandsPage(driver);
    }

    @Then("^I see the \"(.*?)\" that I selected$")
    public void i_see_the_that_I_selected(String brand) throws Throwable {
        brandsPage.verifyBrandSelected(brand);
    }


    @When("^I select \"(.*?)\" to shop$")
    public void i_select_to_shop(String subCategory) throws Throwable {
        brandsPage.clickShopBrandCategoryTires(subCategory);
    }
    
    @Then("^I can see selected \"(.*?)\" under refinements section on PLP page$")
    public void i_can_see_selected_refinement_under_refinements_section_on_PLP_page(String refinement) throws Throwable {
        brandsPage.verifyBrandRefinementSelected(refinement);
    }

    @And("^I select the \"([^\"]*)\" tire brand image$")
    public void i_select_the_tire_brand_image(String brand) throws Throwable {
        brandsPage.selectBrandImageViaText(brand);
    }

    @When("^I select \"(.*?)\" to shop for wheels$")
    public void i_select_to_shop_for_wheels(String subCategory) throws Throwable {
        brandsPage.clickShopBrandCategoryWheels(subCategory);
    }

    @When("^I select fitment by brand for \"(.*?)\"$")
    public void i_select_fitment_by_brand_for(String category) throws Throwable {
        brandsPage.selectBrand(category);
    }
}
