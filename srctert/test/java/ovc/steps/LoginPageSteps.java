package ovc.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.pages.CommonActions;
import ovc.pages.HomePage;
import ovc.pages.LoginPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 5/18/17.
 */
public class LoginPageSteps {

    private LoginPage loginPage;
    private CommonActions commonActions;
    private HomePage homePage;

    public LoginPageSteps(Driver driver) {
        loginPage = new LoginPage(driver);
        commonActions = new CommonActions(driver);
        homePage = new HomePage(driver);
    }

    @Given("^I go to the ovc homepage$")
    public void i_go_to_the_ovc_homepage() throws Throwable {
        loginPage.goToHome();
    }

    @When("^I login with Server, Username, and Password$")
    public void i_login() throws Throwable {
        loginPage.login(ConstantsOvc.OVC);
    }

    @When("^I login to \"Training Mode\" with Server, Username, and Password$")
    public void i_login_to_training_mode() throws Throwable {
        loginPage.login(ConstantsOvc.TRAINING_LOGIN);
    }

    @When("^I attempt to login \"(.*?)\" times$")
    public void i_attempt_login(String times) throws Throwable {
        loginPage.verifyFirstLogin(times);
    }
    
    @When("^I login with Server, Username, and Invalid Password$")
    public void i_login_with_Server_Username_and_Invalid_Password() throws Throwable {
    	loginPage.InvalidLogin();
    }

    @Then("^I verify I am back on the login page$")
    public void i_verify_I_am_back_on_the_login_page() throws Throwable {
    	loginPage.verifyLoginPage();
    }
    
    @When("^I login to dashboard with Username, and Password$")
    public void i_login_to_dashboard_with_Username_and_Password() throws Throwable {
    	loginPage.dashboardLogin();
    }

    @When("^I click on system, scheduled jobs on the left pane nav bar$")
    public void i_click_on_system_scheduled_jobs_on_the_left_pane_nav_bar() throws Throwable {
    	homePage.clickSystemOnDashHome();
    	homePage.clickScheduledJobOnDashHome();
    }
    
    @Then("^I verify the desired job \"([^\"]*)\" is displayed$")
    public void i_verify_the_desired_job_is_displayed(String text) throws Throwable {
    	homePage.assertJobDashContainsPhrase(text);
    }
    
    @When("^I enter Server, Username and Password and hit Login on the initial login page$")
    public void i_enter_server_username_and_password_and_hit_login_on_the_initial_login_page() throws Throwable {
    	loginPage.loginOnFirstLoginPage();
    }

    @Then("^I verify till is not present in the second login page$")
    public void i_verify_till_is_not_present_in_the_second_login_page() throws Throwable {
    	loginPage.verifyTillNotPresentOnLoginPage();
    }

    @And("^I enter Server, Username and Password and hit Login on the second login page$")
    public void i_enter_server_username_and_password_and_hit_login_on_the_second_login_page() throws Throwable {
        loginPage.verifySecondLogin(1);
    }
}
