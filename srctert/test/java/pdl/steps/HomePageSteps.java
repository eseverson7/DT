package pdl.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pdl.pages.HomePage;
import pdl.pages.CommonActions;
import utilities.Driver;

import static dtc.pages.HomePage.homepage;

/**
 * Created by aaronbriel on 4/17/17.
 */
public class HomePageSteps {

    private HomePage homePage;
    private CommonActions commonActions;

    public HomePageSteps(Driver driver) {
        homePage = new HomePage(driver);
        commonActions = new CommonActions(driver);
    }

    @Given("^I go to the pdl homepage$")
    public void i_go_to_the_pdl_homepage() throws Throwable {
        homePage.goToHome();
    }

    @When("^I login with Store ID \"(.*?)\" and Payroll ID \"(.*?)\"$")
    public void i_login(String storeId, String payrollId) throws Throwable {
        homePage.login(storeId, payrollId);
    }
    
    @Then("^I verify Store ID is displayed on the home page$")
    public void i_verify_Store_ID_is_displayed_on_the_home_page() throws Throwable {
    	homePage.assertStoreIDIsPresent();
    	homePage.assertStoreIDPlaceholderValue();
    }

    @Then("^I verify Payroll ID is displayed on the home page$")
    public void i_verify_Payroll_ID_is_displayed_on_the_home_page() throws Throwable {
    	homePage.assertPayrollIDIsPresent();
    	homePage.assertPayrollIDPlaceholderValue();
    }
    
    @Then("^I verify \"(Store ID|Payroll ID)\" message on home page$")
	public void i_verify_error_message_on_home_page(String messageType) throws Throwable {
		String errorMessage;
		if (messageType.contains(HomePage.STORE_ID_PLACEHOLDER_VAL))
			errorMessage = HomePage.STORE_ERROR_MESSAGE;
		else
			errorMessage = HomePage.PAYROLL_ERROR_MESSAGE;
		commonActions.assertPdlErrorMessage(errorMessage);
	}

}
