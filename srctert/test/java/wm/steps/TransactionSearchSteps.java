package wm.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.Driver;
import wm.pages.TransactionSearch;

/**
 * Created by mnabizadeh on 5/18/18.
 */
public class TransactionSearchSteps {
	
    private TransactionSearch transactionSearch;

    public TransactionSearchSteps(Driver driver) {
    	transactionSearch = new TransactionSearch(driver);
    }
    
    @Given("^I go to the wm homepage$")
    public void i_go_to_the_wm_homepage() throws Throwable {
    	transactionSearch.goWMHome();
    }
    
    @When("^I login with Username and Password$")
    public void i_login_with_username_and_password() throws Throwable {
    	transactionSearch.login();
    }
    
    @When("^I read the transaction number from \"([^\"]*)\" excel$")
    public void i_read_the_transaction_number_from_excel(String file) throws Throwable {
    	transactionSearch.hybrisOrderNumberExcel(file);
    }

    @When("^I search for transaction number on interface monitor page$")
    public void i_search_for_transaction_number_on_interface_monitor_page() throws Throwable {
    	transactionSearch.wmFunctionIdSearch();
    }

    @When("^I select the date range \"([^\"]*)\"$")
    public void i_select_the_date_range(String date) throws Throwable {
    	transactionSearch.selectDateRange(date);
    }

    @When("^I complete the search$")
    public void i_complete_the_search() throws Throwable {
    	transactionSearch.searchButton();
    }

    @Then("^I verify transaction number appears with the status of completed$")
    public void i_verify_transaction_number_appears_with_the_status_of_completed() throws Throwable {
    	transactionSearch.assertCompletedStatus();
    }
}