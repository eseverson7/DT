package ovc.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import ovc.data.OvcData;
import ovc.pages.CheckoutMenuPage;
import ovc.pages.CommonActions;
import utilities.Driver;

/**
 * Created by cluden on 7/12/2017.
 */
public class CheckoutMenuPageSteps {

    private CheckoutMenuPage checkoutMenuPage;
    private CommonActions commonActions;
    private OvcData ovcData, featureData;

    public CheckoutMenuPageSteps(Driver driver) {
        checkoutMenuPage = new CheckoutMenuPage(driver);
        commonActions = new CommonActions(driver);
        ovcData = new OvcData();
    }

    @And("^I select the \"(.*?)\" link from the Checkout menu$")
    public void i_select_the_link_from_the_checkout_menu(String linkText) throws Throwable {
        commonActions.selectLinkWithText(linkText);
    }

    @And("^I select \"([^\"]*)\" as the Special Payment option from the Checkout menu$")
    public void i_select_specified_special_payment_option_from_the_checkout_menu(String paymentOption) throws Throwable {
        checkoutMenuPage.selectSpecialPaymentOption(paymentOption);
    }

    @When("^I change tender amount to \"([^\"]*)\"$")
    public void i_change_tender_amount_to(String amount) throws Throwable {
        checkoutMenuPage.changeTenderAmount(amount);
    }

    @And("^I enter a tire inspection price$")
    public void i_enter_a_tire_inspection_price() throws Throwable {
        checkoutMenuPage.enterTireInspectionPrice();
    }
    
    @When("^I enter the data about the tender in travelers check popup for feature \"([^\"]*)\"$")
    public void i_enter_the_data_about_the_tender_in_travelers_check_popup_for_feature(String feature) throws Throwable {
    	featureData = ovcData.getOvcData(feature);
        String phoneNumber = featureData.Phone;
        String licenseNumber = featureData.LicenseNumber;
        String licenseState = featureData.LicenseState;
        String licenseExpiration = featureData.LicenseExpiration;
        String routingNumber = featureData.RoutingNumber;
        String accountNumber = featureData.AccountNumber;
        String checkNumber = featureData.CheckNumberInteger;
        
        checkoutMenuPage.enterTenderDetailsForTavelersCheck(phoneNumber, licenseNumber, licenseState, licenseExpiration, routingNumber, accountNumber, checkNumber);
    }

    @And("^I enter a partial total of \"([^\"]*)\" for the order$")
    public void i_enter_a_partial_total_of_amount_for_the_order(String amount) throws Throwable {
        checkoutMenuPage.changeTenderAmount(amount);
    }
}