package dtc.steps;

import common.Config;
import cucumber.api.java.en.Then;
import dtc.pages.AppointmentConfirmationPage;
import dtc.pages.AppointmentPage;
import dtc.pages.CartPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class AppointmentConfirmationPageSteps {

    private AppointmentConfirmationPage appointmentConfirmationPage;

    public AppointmentConfirmationPageSteps(Driver driver) {
        appointmentConfirmationPage = new AppointmentConfirmationPage(driver);
    }

    @Then("^I should see an appointment confirmation message for \"(.*?)\" with service options: \"(.*?)\"$")
    public void i_see_appointment_confirmation_message_and_selected_services(String customer, String serviceOptions)
            throws Throwable {
        appointmentConfirmationPage.confirmAnAppointmentPlaced(customer);
        appointmentConfirmationPage.assertServiceOnConfirmationPage(serviceOptions);
    }

    @Then("^I should see my previously selected store, date and time, in the appointment details section$")
    public void i_see_selected_store_date_and_time_in_appointment_details() throws Throwable {
        appointmentConfirmationPage.verifyAppointmentDetailsSectionInfo(Config.getDefaultStoreAddress(),
                AppointmentPage.getPassableDate(), AppointmentPage.getPassableTime());
    }

    @Then("^I verify the Appointment Confirmation email address is \"(UPPERCASE|LOWERCASE)\"$")
    public void i_verify_the_appointment_confirmation_email_address(String letterCasing) throws Throwable {
        appointmentConfirmationPage.confirmEmailLetterCasing(letterCasing);
    }
    
    @Then("^I verify the appointment confirmation order placed date is correct$") 
    public void i_verify_the_appointment_confirmation_order_placed_date_is_correct() throws Throwable {
    	appointmentConfirmationPage.assertOrderPlacedDate();
    }
    @Then("^I verify the appointment confirmation store name matches my store on shopping cart$")
    public void i_verify_the_appointment_confirmation_store_name_matches_my_store_on_shopping_cart() throws Throwable {
    	appointmentConfirmationPage.assertConfirmationAndCartStoreName();
    }
    
    @Then("^I verify the appointment confirmation sales tax matches sales tax amount on shopping cart$")
    public void i_verify_the_appointment_confirmation_sales_tax_matches_sales_tax_amount_on_shopping_cart() throws Throwable {
    	appointmentConfirmationPage.assertConfirmationAndCartSalesTax();
    }
    
    @Then("^I verify the appointment confirmation order total matches shopping cart order total$")
    public void i_verify_the_appointment_confirmation_order_total_matches_shopping_cart_order_total() throws Throwable {
    	appointmentConfirmationPage.assertCheckoutAndCartOrderTotal(CartPage.orderTotal.getText());
    }

}
