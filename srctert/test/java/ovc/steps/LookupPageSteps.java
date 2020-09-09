package ovc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ovc.data.ConstantsOvc;
import ovc.data.OvcData;
import ovc.pages.CommonActions;
import ovc.pages.LookupPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 5/18/17.
 */
public class LookupPageSteps {

    private LookupPage lookupPage;
    private CommonActions commonActions;
    private OvcData ovcData, featureData;

    public LookupPageSteps(Driver driver) {
        lookupPage = new LookupPage(driver);
        commonActions = new CommonActions(driver);
        ovcData = new OvcData();
    }

    @And("^I select \"([^\"]*)\" from the Lookup page dropdown$")
    public void i_select_from_the_lookup_page_dropdown(String value) throws Throwable {
        lookupPage.selectOvcDropdownValue(value);
    }

    @Then("^I select item \"([^\"]*)\" from the Lookup results screen$")
    public void i_select_item_from_the_lookup_results_screen(String item) throws Throwable {
        if (item.equalsIgnoreCase(lookupPage.GTIN)) {
            item = lookupPage.GTIN_NUMBER;
        }
        lookupPage.selectTableRowResult(item);
    }

    @Then("^I select \"([^\"]*)\" for feature \"([^\"]*)\" from the Lookup results screen$")
    public void i_select_item_in_feature_from_the_lookup_results_screen(String dataField, String feature) throws Throwable {
        String item = ovcData.getFieldValue(feature, dataField);
        lookupPage.selectTableRowResult(item);
    }

    @When("^I enter \"(Tire|Wheel|AccessoryTPMS|Labor|AccessoryValveStem)\" name and sku for feature \"([^\"]*)\" on the Lookup Page$")
    public void i_enter_name_and_sku_on_Lookup_Page(String type, String feature) throws Throwable {
        String name = "";
        String sku = "";
        featureData = ovcData.getOvcData(feature);
        if (type.equalsIgnoreCase(Constants.WHEEL)) {
            name = featureData.WheelName;
            sku = featureData.WheelSku;
        } else if (type.equalsIgnoreCase(Constants.TIRE)) {
            name = featureData.TireName;
            sku = featureData.TireSku;
        } else if (type.equalsIgnoreCase(Constants.ACCESSORYTPMS)) {
            name = featureData.AccessoryTPMSName;
            sku = featureData.AccessoryTPMSSku;
        } else if (type.equalsIgnoreCase(Constants.LABOR)) {
            name = featureData.LaborName;
            sku = featureData.LaborSku;
        } else if (type.equalsIgnoreCase(Constants.ACCESSORYVALVESTEM)) {
            name = featureData.AccessoryValveStemName;
            sku = featureData.AccessoryValveStemSku;
        }
        lookupPage.enterTireNameAndSku(name, sku);
    }

    @When("^I enter sku for feature \"([^\"]*)\" on the Lookup Page$")
    public void i_enter_sku_on_Lookup_Page(String feature) throws Throwable {
        featureData = ovcData.getOvcData(feature);
        String sku = featureData.TireSku;
        lookupPage.enterTireNameAndSku("", sku);
    }

    @Then("^I verify the text displayed on the Lookup Page matches the \"([^\"]*)\" value for feature \"([^\"]*)\"$")
    public void i_verify_type_matches_text_displayed_on_Lookup_Page(String dataField, String feature)
            throws Throwable {
        String text = ovcData.getFieldValue(feature, dataField);
        commonActions.verifyElementText(LookupPage.productWrapperBy, text);
    }

    @And("^I select the value from feature \"(.*?)\" for the \"(.*?)\" dropdown on the lookup page$")
    public void i_select_option_from_dropdown_on_lookup_page(String feature, String dataField) throws Throwable {
        String option = ovcData.getFieldValue(feature, dataField);
        lookupPage.selectOptionFromLookupPageDropdown(option, dataField);
    }

    @Then("^I verify \"([^\"]*)\" dropdown fields are displayed on Lookup Page$")
    public void i_verify_dropdown_fields_are_displayed_on_Lookup_Page(String productType) throws Throwable {

        String[] lookupDropDownElement = null;

        if (productType.equals("Tire")) {
            lookupDropDownElement = LookupPage.tireDropDownLookup;
        } else if (productType.equals("Wheel")) {
            lookupDropDownElement = LookupPage.wheelDropDownLookup;
        }
        lookupPage.verifyLookupDropDownExists(lookupDropDownElement);
    }

    @Then("^I verify \"([^\"]*)\" dropdown fields are not present on the Lookup page$")
    public void i_verify_dropdown_fields_are_not_present_on_the_Lookup_page(String productType) throws Throwable {

        String[] lookupDropDownElement = null;

        if (productType.contains(Constants.TIRE)) {
            lookupDropDownElement = LookupPage.tireDropDownLookup;
        } else if (productType.equals(Constants.WHEEL)) {
            lookupDropDownElement = LookupPage.wheelDropDownLookup;
        }
        lookupPage.verifyLookupDropDownIsRemoved(lookupDropDownElement);
    }

    @Then("^I verify the text \"([^\"]*)\" is displayed for each product on the Lookup page results$")
    public void i_verify_products_in_lookup_results_contains_text(String text) throws Throwable {
        lookupPage.verifyProductsContainText(text);
    }

    @Then("^I verify that no products are listed in the Lookup results page$")
    public void i_verify_no_products_listed_on_lookup_page() {
        lookupPage.verifyNoProductsListed();
    }
    
    @Then("^I verify PDP page is displayed$")
    public void i_verify_pdp_page_is_displayed() throws Throwable {
    	lookupPage.verifyProductListPage();
    }

    @And("^I expand the \"Lookup\" dropdown on the page$")
    public void i_expand_the_lookup_dropdown_on_the_page() throws Throwable {
        lookupPage.selectOvcDropdownContainer();
    }

    @When("^I select the \"([^\"]*)\" button$")
    public void i_select_the_button(String text) throws Throwable {
        lookupPage.clicksOnButton(text);
    }

    @Then("^I switch \"([^\"]*)\" \"([^\"]*)\" window$")
    public void i_switch_window(String toFrom, String pageIFrame) throws Throwable {
    	if (pageIFrame.contains(ConstantsOvc.PDP)){
    	commonActions.switchFrameContext(CommonActions.pdpIFrame, toFrom);
    	}
    	//TODO - for CSL iFrame switch
    }

    @When("^I enter \"([^\"]*)\" gtin and vpn for feature \"([^\"]*)\" on the Lookup Page$")
    public void i_enter_gtin_and_vpn_for_feature_on_the_lookup_page(String type, String feature) throws Throwable {
        String gtin = "";
        String vpn = "";
        featureData = ovcData.getOvcData(feature);
        if (type.equalsIgnoreCase(Constants.MISCWHEEL)) {
        	gtin = featureData.MiscWheelGtin;
        	vpn = featureData.MiscWheelVpn;
        } else if (type.equalsIgnoreCase(Constants.MISCTIRE)) {
        	gtin = featureData.MiscTireGtin;
        	vpn = featureData.MiscTireVpn;
        }
    	lookupPage.enterMiscGtinVpn(gtin, vpn);
    }

    @And("^I select the value from feature \"([^\"]*)\" for the \"([^\"]*)\" \'Add Misc\' dropdown on the lookup page$")
    public void i_select_value_from_feature_for_the_add_misc_dropdown_on_the_page(String feature, String dataField)
            throws Throwable {
        String option = ovcData.getFieldValue(feature, dataField);
        lookupPage.selectOptionFromAddMiscProductDropdown(option, dataField);
    }

    @And("^I enter \"Random\" gtin into the Add Misc GTIN field on the lookup page$")
    public void i_enter_gtin_into_add_misc_field_on_lookup_page() throws Throwable {
        lookupPage.GTIN_NUMBER = String.valueOf(commonActions.returnRandomlyGenerated5DigitNumber());
        commonActions.enterTextIntoInputBox(lookupPage.GTIN_NUMBER, lookupPage.GTIN);
    }
}