package dtc.steps;

import cucumber.api.java.en.Then;
import dtc.pages.ItemAddedToYourCartPopupPage;
import dtc.pages.CommonActions;
import utilities.Driver;

/**
 * Created by collinreed on 7/19/17.
 */
public class ItemAddedToYourCartPageSteps {

    private ItemAddedToYourCartPopupPage itemAddedPopupPage;
    private final CommonActions commonActions;

    public ItemAddedToYourCartPageSteps(Driver driver) {
    	itemAddedPopupPage = new ItemAddedToYourCartPopupPage(driver);
        commonActions = new CommonActions(driver);
    }

    @Then("^I verify the item added to your cart popup displays$")
    public void i_verify_the_item_added_to_your_cart_popup_displays() throws Throwable {
    	itemAddedPopupPage.assertItemAddedToYourCartPopUp();
    }

    @Then("^I click view shopping cart button$")
    public void i_click_view_shopping_cart_button() throws Throwable {
        commonActions.clickViewShoppingCart();
    }
 }