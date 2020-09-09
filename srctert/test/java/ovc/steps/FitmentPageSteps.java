package ovc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ovc.data.OvcData;
import ovc.pages.FitmentPage;
import ovc.pages.CommonActions;
import utilities.Driver;

/**
 * Created by eseverson on 6/29/2017.
 */
public class FitmentPageSteps {

    private FitmentPage fitmentPage;
    private CommonActions commonActions;
    private OvcData ovcData, featureData;
    private static final String FITMENT = "Fitment";

    public FitmentPageSteps(Driver driver) {
        fitmentPage = new FitmentPage(driver);
        commonActions = new CommonActions(driver);
        ovcData = new OvcData();
    }

    @And("^I select \"([^\"]*)\" sizes from the Tires and Wheels results table$")
    public void i_select_sizes_from_the_tires_and_wheels_results_table(int selection) throws Throwable {
        fitmentPage.selectFirstResultsFromSizeTable(selection);
    }

    @And("^I select selections from feature \"(.*?)\" from the Tires and Wheels results table$")
    public void i_select_specific_sizes_from_the_tires_and_wheels_results_table(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String selections = featureData.Selections;
        fitmentPage.selectSpecificResultsFromSizeTable(selections);
    }

    @And("^I select Staggered selections from feature \"(.*?)\" from the Tires and Wheels results table$")
    public void i_select_staggered_selections_from_the_tires_and_wheels_results_table(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String selections = featureData.StaggeredSelections;
        fitmentPage.selectSpecificResultsFromSizeTable(selections);
    }

    @Then("^I verify the refined fitment product results display on page$")
    public void i_verify_the_refined_fitment_product_results_display_on_page() throws Throwable {
        fitmentPage.assertFitmentResults();
    }

    @And("^I select the \"([^\"]*)\" Fitment page radio button$")
    public void i_select_the_fitment_page_radio_button(String button) throws Throwable {
        fitmentPage.selectRadioButton(button);
    }

    @And("^I select the first \"([1-9])\" product\\(s\\) from the Product Results table$")
    public void i_select_the_first_products_the_product_results_table(int selection) throws Throwable {
        fitmentPage.selectFitmentProductResults(selection);
    }

    @Then("^I verify the \"([^\"]*)\" appears in the feature \"(.*?)\" Fitment Qualifiers popup$")
    public void i_verify_the_value_appears_in_the_fitment_qualifier_popup(String dataField, String feature) throws Throwable {
        String fitmentQualifier = ovcData.getFieldValue(feature, dataField);
        fitmentPage.assertFitmentQualifierText(fitmentQualifier);
    }

    @And("^I close the Fitment Qualifier popup window$")
    public void i_close_the_fitment_qualifier_popup() throws Throwable {
        commonActions.selectCloseElement();
    }

    @And("^I select the \"([^\"]*)\" vehicle element from the Fitment page$")
    public void i_select_the_text_element_from_the_fitment_feature_on_fitment_page(String dataField) throws Throwable {
        String vehicleDescription = ovcData.getFieldValue(FITMENT, dataField);
        fitmentPage.selectVehicleElement(vehicleDescription);
    }
}
