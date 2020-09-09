package dtc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.HybrisManagementConsolePage;
import utilities.Driver;

/**
 * Created by Channing Luden on 2/13/2017.
 */
public class HybrisManagementConsoleSteps {

    private HybrisManagementConsolePage hybrisManagementConsolePage;
    private final Driver driver;

    public HybrisManagementConsoleSteps(Driver driver) {
        hybrisManagementConsolePage = new HybrisManagementConsolePage(driver);
        this.driver = driver;
    }

    @And("^I search for the order number in HMC$")
    public void i_search_for_order_number_in_hmc() throws Throwable {
        if (Config.isChrome()) {
            //Check needed for Jenkins runs where failures are caused by SauceLabs trying to connect to HMC. Access
            //(con't) to HMC was given to the VMs only.
            if (!Config.getIsSaucelabs()) {
                //TODO - investigate possibility of reading in order # from orders.txt
                String orderNumber = driver.scenarioData.getCurrentOrderNumber();
                hybrisManagementConsolePage.loginToHmc();
                hybrisManagementConsolePage.searchHmcByOrderNumber(orderNumber);
                hybrisManagementConsolePage.selectSearchResultByOrderNumber(orderNumber);
            }
        }
    }

    @Then("^I confirm the \"(order|appointment)\" has been processed successfully in HMC$")
    public void i_confirm_order_processed_successfully_in_hmc(String serviceType) throws Throwable {
        if (Config.isChrome()) {
            //Check needed for Jenkins runs where failures are caused by SauceLabs trying to connect to HMC. Access
            //(con't) to HMC was giving to the VMs only.
            if (!Config.getIsSaucelabs()) {
                hybrisManagementConsolePage.selectTabForCurrentOrder(HybrisManagementConsolePage.ADMINISTRATION_TAB_HMC);
                if (serviceType.equals(Constants.ORDER.toLowerCase())) {
                    hybrisManagementConsolePage.validateCurrentOrderResultStatus();
                } else {
                    hybrisManagementConsolePage.validateCurrentAppointmentResultStatus();
                }
                hybrisManagementConsolePage.closeCurrentHmcSession();
                hybrisManagementConsolePage.verifyReturnToHmcLoginPage();
            }
        }
    }

    //<editor-fold desc="Inactive HMC step bindings">
    @And("^I select the \"([^\"]*)\" directory in HMC$")
    public void i_select_a_directory_in_hmc(String directory) throws Throwable {
        hybrisManagementConsolePage.selectHmcDirectory(directory);
    }

    @And("^I select the \"([^\"]*)\" directory option in HMC$")
    public void i_select_directory_option_in_hmc(String directoryOption) throws Throwable {
        hybrisManagementConsolePage.selectHmcDirectory(directoryOption);
    }

    @And("^I search for Order Number: \"([^\"]*)\" in HMC$")
    public void i_search_for_order_number_in_hmc(String orderNumber) throws Throwable {
        hybrisManagementConsolePage.searchHmcByOrderNumber(orderNumber);
    }

    @And("^I select Order Number: \"([^\"]*)\" from HMC search results$")
    public void i_select_order_number_from_hmc_search_results(String orderNumber) throws Throwable {
        hybrisManagementConsolePage.selectSearchResultByOrderNumber(orderNumber);
    }

    @And("^I select the \"([^\"]*)\" tab for the current order$")
    public void i_select_tab_for_current_order(String tabName) throws Throwable {
        hybrisManagementConsolePage.selectTabForCurrentOrder(tabName);
    }

    @Then("^I should see a successful status for the current order$")
    public void i_see_successful_status_for_current_order() throws Throwable {
        hybrisManagementConsolePage.validateCurrentOrderResultStatus();
    }

    @When("^I close the current HMC session$")
    public void i_close_current_hmc_session() throws Throwable {
        hybrisManagementConsolePage.closeCurrentHmcSession();
    }

    @Then("^I should be returned to the HMC login page$")
    public void i_am_returned_to_hmc_login_page() throws Throwable {
        hybrisManagementConsolePage.verifyReturnToHmcLoginPage();
    }

    @When("^I login to HMC$")
    public void i_login_to_hmc() throws Throwable {
        hybrisManagementConsolePage.loginToHmc();
    }
    //</editor-fold>
}