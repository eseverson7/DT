package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.ItemAddedToYourCartPopupPage;
import dtc.pages.CompareTireReviewsPage;
import dtc.pages.ProductListPage;
import dtc.pages.CartPage;
import utilities.Driver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by collinreed on 7/14/2017.
 */
public class CompareTireReviewsPageSteps {

    private final Driver driver;
    private final ProductListPage productListPage;
    private final CompareTireReviewsPage compareTireReviewsPage;
    private final ItemAddedToYourCartPopupPage itemAddedPopupPage;
    private final CartPage cartPage;
    HashMap<String, ArrayList<String>> values;

    public CompareTireReviewsPageSteps(Driver driver) {
        this.driver = driver;
        productListPage = new ProductListPage(driver);
        compareTireReviewsPage = new CompareTireReviewsPage(driver);
        itemAddedPopupPage = new ItemAddedToYourCartPopupPage(driver);
        cartPage = new CartPage(driver);
    }

    @Then("^I click compare tire reviews link$")
    public void i_click_compare_tire_reviews_link() throws Throwable {
        values = productListPage.clickCompareTireReviewsLink();
    }

    @And("^I verify the compare tire reviews page displays$")
    public void i_verify_the_compare_tire_reviews_page_displays() throws Throwable {
        compareTireReviewsPage.assertCompareTiresPage();
    }


    @When("^I click add to cart for selected tire on compare tire reviews page$")
    public void i_click_add_to_cart_for_selected_tire_on_compare_tire_reviews_page() throws Throwable {
        compareTireReviewsPage.clickAddToCart(values);
    }

    @When("^I click add to cart for the first tire on the compare tire reviews page$")
    public void i_click_add_to_cart_for_first_tire_on_compare_tire_reviews_page() throws Throwable {
        compareTireReviewsPage.clickAddToCartForFirstItem();
    }

    @And("^I verify item added to your cart popup contains selected tires$")
    public void i_verify_item_added_to_your_cart_popup_contains_selected_tire() throws Throwable {
        itemAddedPopupPage.assertItemAddedToYourCartSelectedTires(values);
    }

    @Then("I see selected tires on the cart page$")
    public void i_see_selected_tires_on_the_cart_page() throws Throwable {
        cartPage.assertSelectedTires(values);
    }

    @And("^I verify \"([^\"]*)\" sort option is displayed$")
    public void i_verify_sort_option_is_displayed(String value) throws Throwable {
        compareTireReviewsPage.assertSortOptionIsDisplayed(value);
    }

    @When("^I select the arrow on the sort option$")
    public void i_select_the_arrow_on_the_sort_option() throws Throwable {
        compareTireReviewsPage.clickArrowOnTheSortOption();
    }

    @And("^I verify the overall rating for the selected tire on the compare tire reviews page$")
    public void i_verify_the_overall_rating_for_the_selected_tire_on_the_compare_tire_reviews_page() throws Throwable {
        compareTireReviewsPage.assertOverallRating();
    }
}