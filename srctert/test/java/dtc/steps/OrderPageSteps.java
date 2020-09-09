package dtc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import dtc.data.Customer;
import dtc.pages.OrderPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class OrderPageSteps {

    private OrderPage orderPage;
    private CartPageSteps cartPageSteps;
    private Customer customer;

    public OrderPageSteps(Driver driver) {
        orderPage = new OrderPage(driver);
        cartPageSteps = new CartPageSteps(driver);
        customer = new Customer();
    }

    @Then("^I am brought to the order confirmation page$")
    public void i_am_brought_to_the_order_confirmation_page() throws Throwable {
        orderPage.confirmOrder();
    }

    @Then("^I should see reservation confirmation message with details \"(.*?)\" and \"(.*?)\"$")
    public void i_should_see_reservation_confirmation_message(String product, String item) throws Throwable {
        orderPage.confirmOrder();
        orderPage.assertItemOnOrderConfirmationPage(product, item);
    }

    @And("^I store the order number$")
    public void i_store_the_order_number() throws Throwable {
        orderPage.storeOrderNumber();
    }

    @Then("^I verify the Total price on the order confirmation page$")
    public void i_verify_the_total_price_on_the_order_confirmation_page() throws Throwable {
        orderPage.assertOrderTotal(cartPageSteps.orderTotalPrice);
    }

    @And("^I verify \"(.*?)\" is listed on the order confirmation page$")
    public void i_verify_text_on_the_order_confirmation_page(String text) throws Throwable {
        orderPage.assertTextOnOrderConfirmationPage(text);
    }

    @And("^I expand the fee details for the item listed on the order confirmation page$")
    public void i_expand_fee_details_for_item_listed_on_order_confirmation_page() throws Throwable {
        orderPage.expandFeeDetailsForItem();
    }

    @And("^I verify the order total on order confirmation page matches with \"(cart|checkout)\" order total$")
    public void i_verify_the_order_total_on_order_confirmation_page(String text) throws Throwable {
        if (text.equalsIgnoreCase("cart")) {
            orderPage.assertOrderConfirmationAndCartOrderTotal();
        } else {
            orderPage.assertOrderConfirmationAndCheckoutOrderTotal();
        }
    }

    @And("^I verify the sales tax on order confirmation page matches with \"(cart|checkout)\" sales tax$")
    public void i_verify_the_sales_tax_on_order_confirmation_page(String text) throws Throwable {
        if (text.equalsIgnoreCase("cart")) {
            orderPage.assertOrderConfirmationAndCartSalesTax();
        } else {
            orderPage.assertOrderConfirmationAndCheckoutSalesTax();
        }
    }
    
    @And("^I verify customer \"(.*?)\" details are listed on the order confirmation page$")
    public void i_verify_customer_details_are_listed_on_the_order_confirmation_page(String customerType) throws Throwable {
    	Customer customerObject = customer.getCustomer(customerType);
    	orderPage.verifyCustomerDetailsOnOrderConfirmation(customerObject);
    }
    
    @And("^I verify customer \"(.*?)\" email confirmation message on the order confirmation page$")
    public void i_verify_customer_email_confirmation_message_on_the_order_confirmation_page(String customerType) throws Throwable {
    	Customer customerObject = customer.getCustomer(customerType);
    	orderPage.verifyCustomerEmailMessageOnOrderConfirmation(customerObject);
    }

    @And("^I select survey feedback on order confirmation page$")
    public void i_select_survey_feedback_link_on_the_order_confirmation_page() throws Throwable {
        orderPage.clickSurveyLink();
    }
    
    @Then("^I verify the Environment Fee details on the order confirmation page$")
    public void i_verify_the_environment_fee_details_on_the_order_confirmation_page() throws Throwable {
        orderPage.verifyEnvironmentFeeDetailsOnOrderConfirmation();
    }
}
