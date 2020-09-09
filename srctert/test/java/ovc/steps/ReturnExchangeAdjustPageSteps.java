package ovc.steps;

import cucumber.api.java.en.And;
import ovc.pages.ReturnExchangeAdjustPage;
import utilities.Driver;


/**
 * Created by mginevan on 8/15/2017.
 */
public class ReturnExchangeAdjustPageSteps {

    private ReturnExchangeAdjustPage returnExchangeAdjustPage;

    public ReturnExchangeAdjustPageSteps(Driver driver) {

        returnExchangeAdjustPage = new ReturnExchangeAdjustPage(driver);
    }

    @And("^I enter transaction details with the previous receipt number$")
    public void i_enter_transaction_details_with_the_previous_receipt_number() throws Throwable {
        returnExchangeAdjustPage.enterPreviousReceipt();
    }

    @And("^I select the \"Select All\" checkbox on the page$")
    public void i_select_the_select_all_checkbox_on_the_page() throws Throwable {
        returnExchangeAdjustPage.clickSelectAllCheckBox();
    }

    @And("^I select \"([^\"]*)\" from the Return page dropdown$")
    public void i_select_from_the_page_dropdown(String value) throws Throwable {
        returnExchangeAdjustPage.selectReturnPageDropdownValue(value);
    }

    @And("^I select the \"([^\"]*)\" option on the Return page$")
    public void i_select_the_button_option_on_the_return_page(String text) throws Throwable {
        returnExchangeAdjustPage.selectButtonWithExactTextOnReturnPage(text);
    }
}