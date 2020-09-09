package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import dtc.data.Customer;
import dtc.pages.PaypalPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 9/17/16.
 */
public class PaypalPageSteps {

    private PaypalPage paypalPage;
    private Customer customer;

    public PaypalPageSteps(Driver driver) {
        paypalPage = new PaypalPage(driver);
        customer = new Customer();
    }

    @When("^I log into paypal as \"(.*?)\"$")
    public void i_enter_paypal_info(String customerType) throws Throwable {
        Customer paypalCust = customer.getCustomer(customerType);
        paypalPage.login(paypalCust.paypalUser, paypalCust.paypalPass);
    }

    @And("^I select a paypal address for \"(.*?)\"$")
    public void i_select_a_paypal_address(String customerType) throws Throwable {
        Customer paypalCust = customer.getCustomer(customerType);
        paypalPage.selectAddressAndContinue(paypalCust);
    }

    @And("^I continue with the paypal payment$")
    public void i_continue_with_paypal_payment() throws Throwable {
        paypalPage.clickContinue();
    }

}
