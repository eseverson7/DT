package ovc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import dtc.data.ConstantsDtc;
import dtc.pages.CheckoutPage;
import dtc.pages.HomePage;
import dtc.pages.SearchAutocompleteDropDownPage;
import dtc.pages.CommonActions;
import dtc.pages.OrderPage;
import dtc.pages.AppointmentPage;
import dtc.steps.CheckoutPageSteps;
import dtc.steps.StoreLocatorPageSteps;
import ovc.data.OvcData;
import ovc.pages.AlertsPage;
import utilities.Driver;
import cucumber.api.java.en.When;

public class CrossApplicationSteps {

    private OvcData ovcData, featureData;
    private CheckoutPageSteps checkoutPageSteps;
    private HomePage homePage;
    private SearchAutocompleteDropDownPage searchAutocompleteDropDownPage;
    private CommonActions commonActions;
    private OrderPage orderPage;
    private StoreLocatorPageSteps storeLocatorPageSteps;
    private AlertsPage alertsPage;
    private CheckoutPage checkoutPage;
    private AppointmentPage appointmentPage;

    public CrossApplicationSteps(Driver driver) {
        ovcData = new OvcData();
        homePage = new HomePage(driver);
        searchAutocompleteDropDownPage = new SearchAutocompleteDropDownPage(driver);
        commonActions = new CommonActions(driver);
        checkoutPageSteps = new CheckoutPageSteps(driver);
        orderPage = new OrderPage(driver);
        storeLocatorPageSteps = new StoreLocatorPageSteps(driver);
        alertsPage = new AlertsPage(driver);
        checkoutPage = new CheckoutPage(driver);
        appointmentPage = new AppointmentPage(driver);
    }

    @And("^I set baseUrl to \"(hybris|oneview)\"$")
    public void i_set_base_url(String product) throws Throwable {
        String baseUrl;
        if (product.equalsIgnoreCase(Constants.HYBRIS)) {
            baseUrl = Config.getHybrisUrl();
        } else {
            baseUrl = Config.getOvcUrl();
        }
        Config.setBaseUrl(baseUrl);
    }

    @When("^I checkout \"(with appointment|without appointment)\" in Hybris using data for feature \"(.*?)\" and store the order number$")
    public void i_checkout_in_hybris(String appointment, String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);

        //I change to the store with url "<StoreUrl>"
        storeLocatorPageSteps.i_navigate_to_store_path_and_change_my_store(featureData.storePath);

        //And  I do a free text search for "<ItemCode>"
        homePage.searchItem(featureData.ItemNumber);

        //And  I select "<ProductName>" from the autocomplete dropdown of the search box
        searchAutocompleteDropDownPage.selectProductByName(featureData.productName);

        //When I add item to my cart and "View shopping cart"
        commonActions.addToCart();
        commonActions.clickViewShoppingCart();

        //When I select the checkout option "<Checkout>"
        checkoutPageSteps.i_select_the_checkout_option(featureData.checkoutOption);

        //When I select the checkout without install reason "<Reason>"
        if (appointment.equalsIgnoreCase(ConstantsDtc.WITH_APPOINTMENT)) {
            checkoutPageSteps.i_select_the_checkout_without_install_reason(featureData.reason);
        } else {
            //And  I select install with appointment
            checkoutPage.clickReservationRadioButton(ConstantsDtc.INSTALL_WITH_APPOINTMENT);
            //And  I select first available appointment date
            checkoutPage.selectFirstOrLastAvailableApptDateTime(true);
            //And  I click next step for customer information
            appointmentPage.clickNextStepForCustomerInformation();
        }

        //And  I reserve items and complete checkout for "<Customer>"
        checkoutPageSteps.i_reserve_items_and_complete_checkout_for_customer(featureData.customerType);

        //And  I store the order number
        orderPage.storeOrderNumberInScenarioData();
    }

    @And("^I do an alert search for the stored order number and assert it is visible$")
    public void i_do_an_alert_search_for_stored_order_number() throws Throwable {
        alertsPage.assertWebOrderReceived();
    }
}
