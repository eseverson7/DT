package pdl.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pdl.data.ConstantsPdl;
import pdl.pages.CommonActions;
import pdl.pages.DrivingDetailsPage;
import pdl.pages.HomePage;
import pdl.pages.RecommendationsPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 4/24/17.
 */
public class CommonActionsSteps {

    private CommonActions commonActions;

    public CommonActionsSteps(Driver driver) {
        commonActions = new CommonActions(driver);
    }

    @Then("^I am brought to the page with header \"(.*?)\"$")
    public void i_am_brought_to_the_page_with_header(String text) throws Throwable {
        commonActions.assertPageHeaderPdl(text);
    }

    @And("^I verify the number of \"(car)\" dropdowns is \"(.*?)\"$")
    public void i_verify_the_number_of_dropdowns(String type, String number) throws Throwable {
        if (type.equalsIgnoreCase(ConstantsPdl.CAR)) {
            commonActions.verifyNumberOfDropdowns(DrivingDetailsPage.genericSelectBy, number);
        }
    }

    @And("^I verify that the \"(Enter|View Tire Recommendations|Compare Tires)\" button is disabled$")
    public void i_verify_button_disabled(String button) throws Throwable {
        if (button.equalsIgnoreCase(HomePage.ENTER)) {
            commonActions.verifyButtonDisabled(HomePage.enterButton);
        } else if (button.equalsIgnoreCase(DrivingDetailsPage.VIEW_TIRE_RECOMMENDATIONS)) {
            commonActions.verifyButtonDisabled(DrivingDetailsPage.viewRecommendationsButton);
        } else if (button.equalsIgnoreCase(RecommendationsPage.COMPARE_TIRES)) {
            commonActions.verifyButtonDisabled(RecommendationsPage.compareTiresButton);
        }
    }

    @And("^I verify that the \"(Enter|View Tire Recommendations|Compare Tires|Filter Results)\" button is enabled")
    public void i_verify_button_enabled(String button) throws Throwable {
        if (button.equalsIgnoreCase(HomePage.ENTER)) {
            commonActions.verifyButtonEnabled(HomePage.enterButton);
        } else if (button.equalsIgnoreCase(DrivingDetailsPage.VIEW_TIRE_RECOMMENDATIONS)) {
            commonActions.verifyButtonEnabled(DrivingDetailsPage.viewRecommendationsButton);
        } else if (button.equalsIgnoreCase(RecommendationsPage.COMPARE_TIRES)) {
            commonActions.verifyButtonEnabled(RecommendationsPage.compareTiresButton);
        } else if (button.equalsIgnoreCase(RecommendationsPage.FILTER_RESULTS)) {
            commonActions.verifyButtonEnabled(RecommendationsPage.filterResultsButton);
        }
    }

}
