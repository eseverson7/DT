package dtc.steps;

import common.Config;
import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.UrlValidation;
import utilities.Driver;
import utilities.HttpUtils;

/**
 * Created by aaronbriel on 12/20/16.
 */
public class UrlValidationSteps {

    private UrlValidation urlValidation;
    private int responseCode;

    public UrlValidationSteps(Driver driver) {
        urlValidation = new UrlValidation(driver);
    }

    @When("^I request the URL with \"([^\"]*)\"$")
    public void i_request_the_URL_with(String path) throws Throwable {
        String url = Config.getBaseUrl() + path;
        if (path.contains(Constants.HTTP))
            url = path;
        responseCode = HttpUtils.getStatusCode(url);
    }

    @Then("^I should get a \"([^\"]*)\" response$")
    public void i_should_get_a_specified_response(String response) throws Throwable {
        HttpUtils.assertStatusCode(Integer.parseInt(response), responseCode);
    }

    @Then("^I should get a response below 400$")
    public void i_should_get_response_below_400() throws Throwable {
        HttpUtils.assertStatusCodeBelow400(responseCode);
    }

    @And("^an element with text \"([^\"]*)\" exists for path \"([^\"]*)\"$")
    public void the_element_exists_for_path(String elementText, String path) throws Throwable {
        urlValidation.assertElementWithTextExists(path, elementText);
    }

    @And("^the breadcrumbs \"([^\"]*)\" exist for path \"([^\"]*)\"$")
    public void i_should_validate_breadcrumbs_exist(String breadCrumbs, String path) throws Throwable {
        if (!breadCrumbs.equalsIgnoreCase(Constants.NONE))
            urlValidation.assertBreadCrumbsExist(path, breadCrumbs);
    }

    @Given("^All links on the page with path \"([^\"]*)\" should have a status below 400$")
    public void all_links_on_page_with_page_should_have_status_below_400(String path) throws Throwable {
        urlValidation.verifyUrlLinks(path);
    }

}
