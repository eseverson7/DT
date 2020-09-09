package ovc.steps;

import cucumber.api.java.en.And;
import ovc.data.OvcData;
import ovc.pages.MainMenuPage;
import utilities.Driver;

/**
 * Created by mginevan on 8/22/2017.
 */
public class MainMenuPageSteps {

    private MainMenuPage mainMenuPage;
    private OvcData ovcData;

    public MainMenuPageSteps(Driver driver) {
        mainMenuPage = new MainMenuPage(driver);
        ovcData = new OvcData();
    }

    @And("^I select the checkbox to return \"([^\"]*)\" for feature \"([^\"]*)\" with a quantity of \"([^\"]*)\"$")
    public void i_select_the_checkbox_to_return_item_with_quantity_of_num(String dataField, String feature, String quantity)
            throws Throwable {
        String item = ovcData.getFieldValue(feature, dataField);
        mainMenuPage.selectItemReturnCheckBox(item, quantity);
    }
}