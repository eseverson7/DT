package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.pages.AlertsPage;
import ovc.pages.AppointmentPage;
import ovc.pages.CommonActions;
import ovc.pages.ParentElementsPage;
import utilities.Driver;

/**
 * Created by eseverson on 6/29/2017.
 */
public class AlertsPageSteps {

    private AlertsPage alertsPage;
    private AppointmentPage appointmentPage;
    private CommonActions commonActions;

    public AlertsPageSteps(Driver driver) {
        alertsPage = new AlertsPage(driver);
        appointmentPage = new AppointmentPage(driver);
        commonActions = new CommonActions(driver);
    }

    @And("^I select an appointment entry from the alerts list$")
    public void i_select_an_appointment_entry_from_the_alerts_list() throws Throwable {
        alertsPage.selectAppointment();
    }

    @And("^I select the \"(Confirm|Virtual Receipt|Modify|Cancel|Complete|Notes|Undo|Search|Refresh|Go to transaction search)\" button from the alert page$")
    public void i_select_the_button_from_the_alert_page(String icon) throws Throwable {
            alertsPage.selectAlertsPageButton(icon);
    }

    @And("^I select the \"([^\"]*)\" button on the cancel confirmation popup$")
    public void i_select_the_button_on_the_cancel_confirmation_popup(String text) throws Throwable {
        commonActions.selectButtonWithText(ParentElementsPage.POPUP, text);
    }

    @Then("^I verify the \"([^\"]*)\" message was displayed$")
    public void i_verify_the_header_confirmation_message_was_displayed(String text) throws Throwable {
        alertsPage.verifyConfirmationHeader(text);
    }

    @And("^I enter a default note into the alert notes$")
    public void i_enter_a_default_note_into_the_alert_notes() throws Throwable {
        alertsPage.defaultNoteEntry();
    }

    @And("^I select the previously created appointment for customer in feature \"(.*?)\"$")
    public void i_select_previously_created_appointment_for_customer(String feature) throws Throwable {
        alertsPage.selectCreatedAppointmentForCustomer(feature);
    }

    @And("^I verify the appointment has been modified for customer in feature \"(.*?)\"$")
    public void i_verify_appointment_has_been_modified_for_customer(String feature) throws Throwable {
        alertsPage.verifyModifiedAppointmentForCustomerInAlertsTable(feature);
    }

    @And("^I select a different date and time for the appointment$")
    public void i_select_different_date_and_time_for_appointment() throws Throwable {
        appointmentPage.modifyAppointmentDateAndTime();
    }

    @And("^I select the refresh icon on the alerts page$")
    public void i_select_the_refresh_icon_on_the_alerts_page() throws Throwable {
        alertsPage.selectRefreshIcon();
    }

    @When("^I expand the \"(First|Second|Third|Fourth|Fifth)\" dropdown on the Alerts page$")
    public void i_expand_the_dropdown_on_the_alerts_page(String selection) throws Throwable {
        alertsPage.expandPageDropdown(selection);
    }

    @And("^I select all items on the alert page$")
    public void i_select_all_items_on_alert_page_if_present() throws Throwable {
        alertsPage.selectAllItemsIfPresent();
    }
}