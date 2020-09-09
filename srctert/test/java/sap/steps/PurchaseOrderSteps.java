package sap.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sap.pages.PurchaseOrder;
import utilities.Driver;

/**
 * Created by mnabizadeh on 5/14/18.
 */
public class PurchaseOrderSteps {

    private PurchaseOrder purchaseOrder;

    public PurchaseOrderSteps(Driver driver) {
        purchaseOrder = new PurchaseOrder(driver);
    }

    @Then("^I am brought to the page with the title \"([^\"]*)\"$")
    public void i_am_brought_to_the_page_with_the_title(String text) throws Throwable {
        purchaseOrder.verifyPageTitle(text);
    }

    @Then("^I verify the dropdown boxes are defaulted to \"(.*?)\" and \"(.*?)\"$")
    public void i_verify_the_drop_down_boxes_are_defaulted_to_defaults(String dropDownDefaultAction,
                                                                       String dropDownDefaultOrderType) throws Throwable {
        purchaseOrder.verifyDefaultDropDowns(dropDownDefaultAction, dropDownDefaultOrderType);
    }

    @When("^I enter the purchase order number in the entry field$")
    public void i_enter_the_purchase_order_number_in_the_entry_field() throws Throwable {
        purchaseOrder.enterPurchaseOrderNumber();
    }

    @Then("^I verify the purchase order number is in the page title$")
    public void i_verify_the_purchase_order_number_in_page_title() throws Throwable {
        String orderNumber = purchaseOrder.getPONumber();
        purchaseOrder.verifyPageTitle(orderNumber);
    }

    @When("^I select the \"Item Okay\" checkbox for each article in the purchase order$")
    public void i_select_the_item_okay_checkbox_for_each_article() throws Throwable {
        purchaseOrder.selectItemOkayCheckBox();
    }

    @Then("^I verify the alert is displayed for the article document being posted$")
    public void i_verify_alert_displayed_for_article_document_posted() throws Throwable {
        purchaseOrder.verifyArticleDocumentPosted();
    }

    @And("^I send the shortcut for the \"Other Purchase Order\" page$")
    public void i_send_shortcut_for_other_purchase_order_page() throws Throwable {
        purchaseOrder.sendOtherPOShortcut();
    }

    @And("^I enter the purchase order number into the popup search field$")
    public void i_enter_purchase_order_number_into_popup_search_field() throws Throwable {
        String orderNumber = purchaseOrder.getPONumber();
        purchaseOrder.enterPurchaseOrderNumberInPopupSearch(orderNumber);
    }

    @And("^I select the \"Other Document\" button on the popup$")
    public void i_select_other_document_button_on_popup() throws Throwable {
        purchaseOrder.selectOtherDocumentButton();
    }

    @Then("^I verify the alert is displayed for the store merch po being changed$")
    public void i_verify_alert_displayed_for_store_merch_po_change() throws Throwable {
        purchaseOrder.verifyStoreMerchPOChanged();
    }

    @When("^I loop through each article and fill out the Confirmation Header and Order Acknowledgement$")
    public void i_loop_each_article_and_fill_confirmation_heard_and_order_acknowledgement() throws Throwable {
        purchaseOrder.fillConfirmationHeaderAndOrderAcknowledgement();
    }

    @When("^I enter the purchase order value for \"(.*?)\" to \"(.*?)\"$")
    public void i_enter_purchase_order_value_for_field_to_value(String field, String value) throws Throwable {
        purchaseOrder.enterValueIntoPOField(field, value);
    }

    @When("^I save the purchase order number to the scenario data$")
    public void i_save_purchase_order_number_to_scenario_data() throws Throwable {
        String PURCHASE_ORDER = purchaseOrder.returnPurchaseOrderNumber();
        purchaseOrder.savePurchaseOrderNumberToScenarioData(PURCHASE_ORDER);
    }

    @When("^I select the PO type \"([^\"]*)\"$")
    public void i_select_the_PO_type(String poType) throws Throwable {
        purchaseOrder.inputPOTypeByText(poType);
    }

    @And("^I search the desired PO number$")
    public void i_search_the_desired_po_number() throws Throwable {
        String orderNumber = purchaseOrder.getPONumber();
        purchaseOrder.searchOtherPurchaseOrderNumber(orderNumber);
    }
}