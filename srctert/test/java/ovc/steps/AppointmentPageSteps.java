package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.AppointmentPage;
import ovc.pages.CommonActions;
import ovc.pages.ParentElementsPage;
import utilities.Driver;

/**
 * Created by eseverson on 6/29/2017.
 */
public class AppointmentPageSteps {

    private AppointmentPage appointmentPage;
    private CommonActions commonActions;
    private CommonActionsSteps commonActionsSteps;
    private OvcData ovcData, featureData;

    public AppointmentPageSteps(Driver driver) {
        appointmentPage = new AppointmentPage(driver);
        commonActions = new CommonActions(driver);
        commonActionsSteps = new CommonActionsSteps(driver);
        ovcData = new OvcData();
    }

    @When("^I select the first available appointment date$")
    public void i_select_first_available_appointment_date() throws Throwable {
        appointmentPage.selectDefaultAppointmentDate();
    }

    @And("^I select \"([^\"]*)\" as the reason for the appointment$")
    public void i_select_reason_for_appointment(String apptReason) throws Throwable {
        appointmentPage.selectAppointmentReason(apptReason);
    }

    @Then("^I see a listing of available appointment times for either the Morning, Afternoon, and/or Evening \\(if available\\)$")
    public void i_see_available_appointment_times_for_morning_afternoon_evening() throws Throwable {
        appointmentPage.verifyAvailableTimesForCreateApptDaySegments();
    }

    @When("^I select the first available appointment time$")
    public void i_select_first_available_appointment_time() throws Throwable {
        appointmentPage.selectFirstAvailableAppointmentTime();
    }
    
    @When("^I select the first evening available appointment time$")
    public void i_select_first_evening_available_appointment_time() throws Throwable {
        appointmentPage.selectFirstEveningAvailableAppointmentTime();
    }

    @Then("^I verify the selected appointment time is highlighted red$")
    public void i_verify_selected_appointment_time_is_red() throws Throwable {
        appointmentPage.verifySelectedAppointmentTimeIsRed();
    }

    @When("^I select \"Schedule\" after entering customer data for feature \"(.*?)\" into the customer information fields$")
    public void i_select_schedule_after_entering_cust_data_into_cust_info_fields(String feature) throws Throwable {
        appointmentPage.enterCustomerInformation(feature);
        appointmentPage.selectScheduleButtonForCustomer(feature);
    }
    
    @And("^I enter customer for feature \"(.*?)\" notes towards schedule appointment$")
    public void i_enter_the_customers_notes(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String customerNotes = featureData.CustomerNotes;
        appointmentPage.enterCustomerNotes(customerNotes);
    }

    @And("^I enter the phone number for customer in feature \"(.*?)\"$")
    public void i_enter_the_customers_phone_number(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String phoneNumber = featureData.Phone;
        appointmentPage.enterPhoneNumber(phoneNumber);
    }

    @When("^I create an appointment for customer in feature \"(.*?)\" with reason \"(.*?)\"$")
    public void i_create_appointment_for_customer_with_reason(String feature, String reasonOption) throws Throwable {
        i_select_first_available_appointment_date();
        i_select_reason_for_appointment(reasonOption);
        i_select_first_available_appointment_time();
        i_select_schedule_after_entering_cust_data_into_cust_info_fields(feature);
    }

    @When("^I create an appointment for the AR customer in feature \"(.*?)\" with reason \"(.*?)\"$")
    public void i_create_appointment_for_ar_customer_with_reason(String feature, String reasonOption) throws Throwable {
        i_select_first_available_appointment_date();
        i_select_reason_for_appointment(reasonOption);
        i_select_first_available_appointment_time();
        commonActionsSteps.i_select_the_button_on_the_page(ConstantsOvc.SCHEDULE, ConstantsOvc.APPOINTMENT);
    }

    @And("^I select the \"([^\"]*)\" button on the modify appointment popup$")
    public void i_select_button_on_modify_appointment_popup(String buttonText) throws Throwable {
        commonActions.selectButtonWithText(ParentElementsPage.POPUP, buttonText);
    }

    @And("^I verify reason error$")
    public void i_verify_reason_error() throws Throwable {
        appointmentPage.verifyReasonError();
    }

    @And("^I select \"(.*?)\" reason and verify Available Time Error$")
    public void i_verify_available_time_error(String apptReason) throws Throwable {
        i_select_reason_for_appointment(apptReason);
        appointmentPage.verifyAvailableTimeError();
    }

    @And("^I create an appointment \"([^\"]*)\" and validate Customer Error$")
    public void i_create_an_appointment_and_validate_customer_error(String apptReason) throws Throwable {
        i_select_reason_for_appointment(apptReason);
        i_select_first_available_appointment_time();
        appointmentPage.validateCustomerError();
    }

    @Then("^I verify required fields error for \"([^\"]*)\", Available Time and Customer Contact$")
    public void i_verify_required_fields_error_for_available_time_and_customer_contact(String apptReason) throws Throwable {
        i_verify_reason_error();
        i_verify_available_time_error(apptReason);
        i_create_an_appointment_and_validate_customer_error(apptReason);
    }

    @When("^I expand the Appointment Reason dropdown list$")
    public void i_expand_the_appointment_reason_dropdown_list() throws Throwable {
        appointmentPage.expandAppointmentReasonDropdown();
    }
}