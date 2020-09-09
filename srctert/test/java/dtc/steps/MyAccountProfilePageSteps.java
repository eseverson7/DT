package dtc.steps;

import cucumber.api.java.en.When;
import dtc.data.ConstantsDtc;
import dtc.pages.MyAccountProfilePage;
import utilities.Driver;

/**
 * Created by ankitarora on 02/19/18.
 */
public class MyAccountProfilePageSteps {

    private MyAccountProfilePage myAccountProfilePage;

    public MyAccountProfilePageSteps(Driver driver) {
    	myAccountProfilePage = new MyAccountProfilePage(driver);
    }

    @When("^I select edit for \"(Email Address|Password)\"$")
    public void i_select_edit_for_for(String type) throws Throwable {
    	if (type.equalsIgnoreCase(ConstantsDtc.PASSWORD)) {
    		myAccountProfilePage.clickEditLink(ConstantsDtc.PASSWORD);
    	} else
    		myAccountProfilePage.clickEditLink(ConstantsDtc.EMAIL_ADDRESS_LABEL);
    }

}
