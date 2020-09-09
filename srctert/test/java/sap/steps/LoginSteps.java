package sap.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import sap.pages.Login;
import utilities.Driver;


/**
 * Created by mnabizadeh on 5/11/18.
 */
public class LoginSteps {
	
    private Login login;

    public LoginSteps(Driver driver) {
        login = new Login(driver);
    }

    @Given("^I go to the sap homepage$")
    public void i_go_to_the_sap_homepage() throws Throwable {
        login.goToSAPHome();
    }
    
    @When("^I login with System, Client, Username, and Password$")
    public void i_login_with_system_client_username_and_password() throws Throwable {
    	login.login();
    }
}
