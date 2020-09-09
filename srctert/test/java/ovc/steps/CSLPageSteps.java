package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.CSLPage;
import ovc.pages.CommonActions;
import utilities.Driver;

public class CSLPageSteps {

    private CSLPage cslPage;
    private OvcData ovcData;
    private CommonActions commonActions;

    public CSLPageSteps(Driver driver) {
        cslPage = new CSLPage(driver);
        ovcData = new OvcData();
        commonActions = new CommonActions(driver);
        cslPage = new CSLPage(driver);
        ovcData = new OvcData();
    }

    @When("^I select the \"([^\"]*)\" button from the (Active|Inactive) CSL Service Home list$")
    public void i_select_the_button_from_the_csl_list(String text, String list) throws Throwable {
        cslPage.selectCSLServiceHomeButton(text, list);
    }

    @Then("^I verify header menus exist on CSL page$")
    public void i_select_the_checkbox_to_return_item_with_quantity_of_num() throws Throwable {
        cslPage.verifyCSLHeaderMenusExist();
    }

    @And("^I click on the Work Order with the title \"([^\"]*)\" for the feature \"([^\"]*)\" from the (Active|Inactive) list$")
    public void i_click_on_the_work_order_with_title(String name, String feature, String list) throws Throwable {
        String customerName  = ovcData.getFieldValue(feature, name);
        cslPage.selectWorkOrderTitle(customerName, list);
    }

    @Then("^I verify the \"([^\"]*)\" for feature \"([^\"]*)\" is in the work order title$")
    public void i_verify_the_phrase_is_in_the_work_order(String name, String feature) throws Throwable {
        String customerVehicle = ovcData.getFieldValue(feature, name);
        cslPage.assertTextInWorkOrderTitle(customerVehicle);
    }

    @Then("^I verify there is service text in at least one of the tire boxes$")
    public void i_verify_there_is_service_text_in_at_least_one_tire_box() throws Throwable {
        cslPage.assertServiceInTireBoxes();
    }

    @And("^I enter the DOT number into all of the DOT inputs on the page$")
    public void i_enter_the_dot_number_into_all_the_dot_inputs() throws Throwable {
        cslPage.enterDOTNumbersIntoDOTBoxes();
    }

    @Then("^I verify the DOT number is entered in all of the DOT inputs on the page$")
    public void i_verify_the_dot_number_is_entered_in_all_the_dot_inputs() throws Throwable {
        cslPage.assertDOTNumbersIntoDOTBoxes();
    }

    @And("^I verify the invoice is \"(Paid|Unpaid)\"$")
    public void i_verify_the_invoice_is_paid_unpaid(String paid) throws Throwable {
        cslPage.verifyPaidOrderSignOffStatus(paid);
    }

    @Then("^I verify the Air Pressure values on the CSL page$")
    public void i_verify_the_air_pressure_values_on_the_csl_page() throws Throwable {
        cslPage.verifyValuesInAirPressureTable(cslPage.airPressureWheelValues);
    }

    @And("^I enter \"(.*?)\" into the Service Coordinator field on the CSL Work Order page$")
    public void i_enter_value_into_the_service_coordinator_field_on_the_csl_page(String text) throws Throwable {
        cslPage.enterTextIntoServiceCoordinatorField(text);
    }

    @Then("^I verify that the Service Coordinator field on the CSL Work Order page has value of \"(.*?)\"$")
    public void i_verify_that_the_service_coordinator_field_on_csl_page_has_a_value_of(String text) throws Throwable {
        cslPage.verifyWorkOrderServiceCoordinatorContainsText(text);
    }

    @And("^I click 10 pixels below the CSL popup modal")
    public void i_click_10_pixels_below_the_csl_popup_modal() throws Throwable {
        commonActions.selectElementWithOffset(cslPage.modalPopup);
    }

    @Then("^I verify the modal popup contains \"(.*?)\"$")
    public void i_verify_the_modal_popup_contains_text(String text) throws Throwable {
        cslPage.assertModalPopupTextContains(text);
    }

    @And("^I select the \"([^\"]*)\" option from the delay reason dropdown$")
    public void i_select_the_text_option_from_the_delay_reason_dropdown(String selection) throws Throwable {
        cslPage.selectDelayDropDownReason(selection);
    }

    @And("^I select the \"([^\"]*)\" button from the CSL popup$")
    public void i_select_the_text_button_from_the_csl_popup(String button) throws Throwable {
        cslPage.selectCSLPopupButton(button);
    }

    @And("^I select the \"([^\"]*)\" icon from the CSL header$")
    public void i_select_the_text_icon_from_the_csl_header(String icon) throws Throwable {
        cslPage.selectCSLHeaderIcon(icon);
    }

    @And("^I select the blank checkbox on the Done CSL tab$")
    public void i_select_the_blank_checkbox() throws Throwable {
        cslPage.selectCSLDoneCheckBox();
    }

    @And("^I verify the green dialog with text \"([^\"]*)\" appears$")
    public void i_verify_the_green_dialog_with_text_appears(String dialog) throws Throwable {
        cslPage.assertGreenDialogBanner(dialog);
    }

    @And("^I fill out the \"(DOTNumber|PulledQuantity)\" input from the pull list$")
    public void i_fill_out_the_input_from_the_pull_list(String inputField) throws Throwable {
        String DOTNumber = ovcData.getFieldValue(ConstantsOvc.CSL, inputField);
        cslPage.fillOutPullListInput(inputField, DOTNumber);
    }

    @And("^I select the chevron dropdown to expand the pull list work order$")
    public void i_select_the_chevron_dropdown_to_expand_the_pull_list_work_order() throws Throwable {
        cslPage.selectCSLPullListChevron();
    }

    @And("^I select the \"Back To Sale\" option on the CSL page$")
    public void i_select_the_back_to_sale_option_on_the_csl_page() throws Throwable {
        cslPage.selectBackToSale();
    }

    @And("^I acknowledge the work order sign off requirements popup$")
    public void i_acknowledge_the_work_order_sign_off_requirements_popup() throws Throwable {
        cslPage.closeWorkOrderRequirementsPopup();
    }

    @And("^I verify the Inactive list has at least one order on the Service Home page$")
    public void i_verify_the_inactive_list_has_one_order_on_service_home_page() throws Throwable {
        cslPage.verifyInactiveListWorkOrderQuantity();
    }

    @And("^I remove all active work orders from the Active list on the Service Home page$")
    public void i_remove_all_active_work_orders_from_active_list_on_service_home_page() throws Throwable {
        cslPage.removeActiveWorkOrdersFromActiveList();
    }

    @And("^I remove all the disabled work orders on the Inactive list on the Service Home Page$")
    public void i_remove_all_the_disable_work_orders_on_the_inactive_list_on_the_service_home_page() throws Throwable {
        cslPage.removeDisabledInactiveWorkOrdersFromInactiveList();
    }

    @And("^I select \"(.*?)\" repair reason for the CSL work order$")
    public void i_select_repair_reason_for_the_csl_work_order(String selection) throws Throwable {
        cslPage.selectRepairReasonForCSL(selection);
    }

    @And("^I select the \"(Delay|Bay Out)\" check box on the CSL page$")
    public void i_select_the_check_box_on_the_csl_page(String checkBox) throws Throwable {
        cslPage.selectCheckBoxOnCSLWorkOrderPage(checkBox);
    }
}