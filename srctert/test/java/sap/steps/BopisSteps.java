package sap.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.Driver;
import sap.pages.Bopis;

public class BopisSteps {

    private Bopis bopis;

    public BopisSteps(Driver driver) { bopis = new Bopis(driver);}

    @When("^I enter Store and Posting Date$")
    public void i_enter_store_and_posting_date() throws Throwable {
        bopis.enterStoreAndPostingDate();
    }

    @And("^I select the \"Goods Movements\" option from the right side of the page$")
    public void select_goods_movement_option_from_right_of_page() throws Throwable {
        bopis.selectGoodsMovementOption();
    }

    @And("^I select the first transaction from left side table$")
    public void select_first_transaction_from_left_table() throws Throwable {
        bopis.selectFirstTransactionFromLeftTable();
    }

    @And("^I save the transaction number and type to the scenario data$")
    public void i_save_the_transaction_number_and_type_to_scenario_data() throws Throwable {
        bopis.saveTransactionNumberToScenarioData();
    }

    @Then("^I verify the customer information is correctly displayed after selecting \"Customer information\"$")
    public void i_verify_customer_information_displayed_after_selecting_customer_information() throws Throwable {
        bopis.selectCustomerInformationButton();
        bopis.verifyCustomerInformationDisplayed();
    }

    @And("^I wait for the RTC SAP home page to load$")
    public void i_wait_for_rtc_sap_home_page_to_load() throws Throwable {
        bopis.waitForRTCLoad();
    }
}
