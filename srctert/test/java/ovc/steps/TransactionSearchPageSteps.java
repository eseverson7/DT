package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.OvcData;
import ovc.pages.CommonActions;
import ovc.pages.TransactionsSearchPage;
import ovc.pages.HomePage;
import utilities.Driver;

/**
 * Created by aarora on 8/22/2017.
 */
public class TransactionSearchPageSteps {

    private OvcData ovcData, featureData;
    private CommonActions commonActions;
    private TransactionsSearchPage transactionsSearchPage;

    public TransactionSearchPageSteps(Driver driver) {
        ovcData = new OvcData();
    	commonActions = new CommonActions(driver);
        transactionsSearchPage = new TransactionsSearchPage(driver);
    }

    @Then("^I should see text \"(.*?)\" present on transaction search page$")
    public void i_verify_text_present_on_transaction_page(String expectedText) throws Throwable {
    	commonActions.assertElementTextContains(CommonActions.ngBindingClassBy, expectedText);
    }

    @Then("^I should see \"(.*?)\" from feature \"(.*?)\" present on transaction search page$")
    public void i_verify_text_for_feature_column_present_on_transaction_page(String dataField, String feature) throws Throwable {
        String expectedText = ovcData.getFieldValue(feature, dataField);
        commonActions.assertElementTextContains(CommonActions.ngBindingClassBy, expectedText);
    }

    @Then("^I am brought to a Transaction Search page$")
    public void i_am_brought_to_transaction_search_page() throws Throwable {
        transactionsSearchPage.assertOnTransactionsSearchPage();
    }

    @And("^I select the transaction for feature \"([^\"]*)\" from the table$")
    public void i_select_transaction_for_feature(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String transactionId = featureData.TransactionId;
        commonActions.selectElementWithText(TransactionsSearchPage.firstTransactionBy, transactionId);
    }

    @And("^I select the transaction with the stored order number from the table$")
    public void i_select_transaction_with_stored_order_number() throws Throwable {
        commonActions.selectElementWithText(TransactionsSearchPage.firstTransactionBy, HomePage.orderNumber);
    }

    @When("^I select the \"(.*?)\" with \"(Resume|Cancel)\" Special Order from the table on the \"(.*?)\" page$")
    public void i_select_the_value_with_special_order_from_the_transaction_search_table(String dataField, String selection,
                                                                                        String feature) throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        transactionsSearchPage.selectRecordAndButtonFromSpecialOrderTable(text, selection);
    }

    @Then("^I verify the transaction table entry does not show \"([^\"]*)\" button$")
    public void i_verify_the_transaction_table_entry_does_not_show_special_order_button(String textValue) throws Throwable {
        transactionsSearchPage.assertButtonNotPresentOnScreen(textValue);
    }

    @Then("^I select the first transaction on the page$")
    public void i_select_the_first_transaction_on_the_page() throws Throwable {
        transactionsSearchPage.selectFirstTransaction();
    }

    @And("^^I select transaction order that contains matching \"([^\"]*)\" and click the \"([^\"]*)\" button$")
    public void i_select_transaction_that_contains_transaction_type_and_click_the_x_button
            (String transactionType, String buttonText) throws Throwable {
        transactionsSearchPage.selectTransactionSearchTableRowAndButtonThatAppears(transactionType, buttonText);
    }
}