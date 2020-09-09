package dtc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.data.Customer;
import dtc.pages.AppointmentPage;
import dtc.pages.OrderPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class AppointmentPageSteps {

    private AppointmentPage appointmentPage;
    private OrderPage orderPage;
    private Customer customer;
    private String appointmentDate;
    private String appointmentTime;
    private GondorSteps gondorSteps;


    public AppointmentPageSteps(Driver driver) {
        appointmentPage = new AppointmentPage(driver);
        orderPage = new OrderPage(driver);
        customer = new Customer();
        gondorSteps = new GondorSteps(driver);
    }

    @And("^I schedule an appointment for \"([^\"]*)\"$")
    public void i_schedule_an_appointment(String customerType) throws Throwable {
        Customer apptcCust = customer.getCustomer(customerType);
        appointmentPage.makeAppointment(apptcCust);
    }

    @And("^I reserve items for \"([^\"]*)\"$")
    public void i_reserve_items(String customerType) throws Throwable {
        Customer apptcCust = customer.getCustomer(customerType);
        appointmentPage.clickNextStepForCustomerInformation();
        appointmentPage.enterCustomerInformation(apptcCust);
        appointmentPage.clickSubmitButton(apptcCust);
    }

    @And("^I click next step for customer information$")
    public void i_click_next_for_customer_information() throws Throwable {
        appointmentPage.clickNextStepForCustomerInformation();
    }

    @And("^I click continue for appointment customer details page$")
    public void i_click_continue_for_appointment_customer_details_page() throws Throwable {
        appointmentPage.clickContinueForAppointmentCustomerDetailsPage();
    }

    @When("^I select edit and select change store$")
    public void i_select_edit_and_change_store() throws Throwable {
        appointmentPage.clickAppointmentEditLink();
        appointmentPage.clickChangeStore();
    }

    @When("^I select service option\\(s\\): \"(.*?)\"$")
    public void i_select_service_options(String options) throws Throwable {
        appointmentPage.selectService(options);
    }

    @When("^I select default date and time$")
    public void i_select_default_date_and_time() throws Throwable {
        appointmentPage.clickSetAppointmentDetailsForDateAndTime();
        appointmentPage.selectDate();
        appointmentPage.selectTime();
    }

    @And("^I select default date$")
    public void i_select_default_date() throws Throwable {
        appointmentPage.clickSetAppointmentDetailsForDateAndTime();
        appointmentPage.selectDate();
    }

    @Then("^I verify the appointment time range is between \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_verify_appointment_time_range(String range1, String range2) throws Throwable {
        appointmentPage.verifyAppointmentTimeRange(range1, range2);
    }

    @And("^I extract date and time for validation$")
    public void i_extract_date_and_time_for_validation() throws Throwable {
        appointmentDate = appointmentPage.extractDate();
        appointmentTime = appointmentPage.extractTime();
    }

    @Then("^I verify date and time on the customer details appointment page$")
    public void i_verify_date_and_time_on_the_customer_details_page() throws Throwable {
        appointmentPage.verifyDateAndTime(appointmentDate, appointmentTime);
    }

    @Then("^I verify date and time on the order confirmation page$")
    public void i_verify_date_and_time_on_the_order_confirmation_page() throws Throwable {
        orderPage.verifyDateAndTime(appointmentDate, appointmentTime);
    }

    @And("^I verify \"([^\"]*)\" store on the customer information appointment page$")
    public void i_verify_store_on_the_customer_information_page(String store) throws Throwable {
        appointmentPage.verifyStore(store);
    }
    
    @And("^I verify default store on the customer details appointment page$")
    public void i_verify_default_store_on_the_customer_details_page() throws Throwable {
        appointmentPage.verifyStore(Config.getDefaultStoreAddress().split(",")[0]);
    }

    @When("^I select \"(?:Schedule Appointment|Place Order|Continue To Shipping Method|Continue To Payment)\" after entering customer information for \"(.*?)\"$")
    public void i_continue_after_entering_customer_information(String customerType) throws Throwable {
        Customer apptcCust = customer.getCustomer(customerType);
        appointmentPage.enterCustomerInformation(apptcCust);
        appointmentPage.clickSubmitButton(apptcCust);
    }

    @When("^I enter information for other recipient \"([^\"]*)\" requesting delivery by \"(phone|email)\" and \"(text updates|no text updates)\"$")
    public void i_enter_other_recipient_information(String otherCustomer, String deliveryType, String textUpdates) {
        Customer someoneElse = customer.getCustomer(otherCustomer);
        boolean receiveTextUpdates = false;
        if (textUpdates.equalsIgnoreCase(Constants.TEXT_UPDATES)) {
            receiveTextUpdates = true;
        }
        appointmentPage.enterSomeoneElseInformation(someoneElse, deliveryType, receiveTextUpdates);
    }

    @And("^I validate the Special Order header message$")
    public void i_validate_the_special_order_header_message() throws Throwable {
        appointmentPage.verifySpecialOrderMessage();
    }

    @And("^I validate the Call Us message$")
    public void i_validate_the_call_us_message() throws Throwable {
        appointmentPage.verifyCallUsMessage();
    }
    
    @When("^I select 'Set Appointment Details' for Date and Time$")
    public void i_select_set_appointment_details_for_date_and_time() throws Throwable {
        appointmentPage.clickSetAppointmentDetailsForDateAndTime();
    }

    @When("^I select the \"(First|Last)\" appointment to verify total available appointment days are \"([^\"]*)\"$")
    public void i_select_the_appointment_to_verify_total_available_appointment_days_are(String day, String availDays) throws Throwable {
        appointmentPage.selectDateAndVerifyAvailableDays(day, availDays);
        appointmentPage.selectTime();
    }

    @Then("^I confirm \"([^\"]*)\" receives an email for the appointment$")
    public void i_confirm_email_received_for_appointment(String customerType) throws Throwable {
        //TODO: Currently "Check Email" constantly failing since several months in QA environment
        //TODO: Due to Integration Env issue there is a delay in delivery the email on Gondor server
        if (Config.isChrome() && Config.getDataSet().equalsIgnoreCase(Constants.STG)) {
            Customer apptEmailCustomer = customer.getCustomer(customerType);
            gondorSteps.i_open_gondor_inbox(apptEmailCustomer.email, apptEmailCustomer.emailPassword);
            gondorSteps.i_verify_most_recent_email_in_gondor_inbox_with_orderid();
        }
    }

    @And("^I click reserve after entering customer information, including the address, for \"([^\"]*)\"$")
    public void i_enter_customer_information_with_address(String customerType) throws Throwable {
        Customer apptcCust = customer.getCustomer(customerType);
        appointmentPage.enterCustomerInformation(apptcCust);
        appointmentPage.enterCustomerAddressInformation(apptcCust);
        appointmentPage.clickSubmitButton(apptcCust);
    }

    @And("^I click Edit Appointment link$")
    public void i_click_edit_appointment_link() throws Throwable {
        appointmentPage.clickEditAppointmentLink();
    }

    @When("^I click Select \"([^\"]*)\"$")
    public void i_click_select_datetime(String selection) throws Throwable {
        appointmentPage.clickSelectDateOrTime(selection);
    }

    @Then("^I verify the datepicker message is correct$")
    public void i_verify_the_datepicker_message_is_correct() throws Throwable {
        appointmentPage.verifyDatePickerMessage();
    }

    @When("^I close the Appointment Selected message bar$")
    public void i_close_the_appointment_selected_message_bar() throws Throwable {
        appointmentPage.closeAppointmentSelectedMessageBar();
    }

    @Then("^I verify the Appointment Selected message is not displayed$")
    public void i_verify_the_appointment_selected_message_not_displayed() throws Throwable {
        appointmentPage.assertAppointmentSelectedMessageIsNotDisplayed();
    }

    @Then("^I verify peak hours appointment is indicated in appointment details$")
    public void i_verify_peak_hours_appointment_is_indicated_in_appointment_details() throws Throwable {
        appointmentPage.verifyPeakHoursInAppointmentDetails();
    }

    @Then ("^I verify selected service option\\(s\\): \"(.*?)\" is displayed on Service Appointment page$")
    public void i_verify_selected_service_option_text_on_service_appointment_page(String serviceOption) throws Throwable {
        appointmentPage.assertSelectedServiceOptionText(serviceOption);
    }

    @Then("^I verify \"(SERVICES|APPOINTMENT DETAILS|CUSTOMER DETAILS)\" section is displayed and active$")
    public void i_verify_section_title_is_displayed_and_active(String sectionTitle) throws Throwable {
        appointmentPage.assertActiveSectionTitleMessageIsDisplayed(sectionTitle);
    }
}