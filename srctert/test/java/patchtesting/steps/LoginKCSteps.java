package patchtesting.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import patchtesting.pages.LoginKC;
import patchtesting.pages.Safety;
import utilities.Driver;

/**
 * Created by eseverson on 5/17/18.
 */
public class LoginKCSteps {

    private LoginKC loginKC;
    private Safety safety;

    public LoginKCSteps(Driver driver) {
        loginKC = new LoginKC(driver);
        safety = new Safety(driver);
    }

    @Given("^I go to the 'Knowledge Center' home page$")
    public void i_go_to_the_knowledge_center_home_page() throws Throwable {
        loginKC.goToKCHome();
    }

    @And("^I log in to the 'Knowledge Center' site$")
    public void i_log_in_to_the_knowledge_center_site() throws Throwable {
        loginKC.loginKC();
    }
}
