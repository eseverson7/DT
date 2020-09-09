package ovc.steps;

import cucumber.api.java.en.And;
import ovc.data.OvcData;
import ovc.pages.OrderAdjustmentPage;
import ovc.pages.ParentElementsPage;
import utilities.Driver;

/**
 * Created by mginevan on 9/13/2017.
 */
public class OrderAdjustmentPageSteps {
    private OrderAdjustmentPage orderAdjustmentPage;
    private OvcData ovcData;

    public OrderAdjustmentPageSteps(Driver driver) {
        orderAdjustmentPage = new OrderAdjustmentPage(driver);
        ovcData = new OvcData();
    }

    @And("^I enter \"(.*?)\" into the \"([^\"]*)\" input box on the order adjustment page$")
    public void i_enter_value_into_the_specified_input_box_on_the_order_adjustment_page(String value, String inputBox)
            throws Throwable {
        orderAdjustmentPage.enterValueIntoOrderAdjustmentInputBox(value, inputBox);
    }

    @And("^I enter \"(.*?)\" into the \"([^\"]*)\" input box for feature \"(.*?)\" on the order adjustment page$")
    public void i_enter_value_for_feature_into_the_specified_input_box_on_the_order_adjustment_page(String dataField, String inputBox, String feature)
            throws Throwable {
        String value = ovcData.getFieldValue(feature, dataField);
        orderAdjustmentPage.enterValueIntoOrderAdjustmentInputBox(value, inputBox);
    }

    @And("^I select the \"(.*?)\" from the order adjustment page drop down$")
    public void i_select_the_specified_from_the_order_adjustment_page_drop_down(String selection) throws Throwable {
        orderAdjustmentPage.selectFromOrderAdjustmentDropDown(selection);
    }

    @And("^I select the \"([^\"]*)\" radio button on the Order Adjustment page$")
    public void i_select_the_radio_button_on_the_order_adjustment_page(String buttonText) throws Throwable {
        orderAdjustmentPage.selectOrderAdjustmentRadioButton(buttonText);
    }
}