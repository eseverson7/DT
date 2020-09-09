package dtc.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.SendToPhonePopupPage;
import utilities.Driver;

/**
 * Created by abriel on 10/24/2016.
 */
public class SendToPhonePopupPageSteps {

    private SendToPhonePopupPage sendToPhonePopupPage;

    public SendToPhonePopupPageSteps(Driver driver) {
        sendToPhonePopupPage = new SendToPhonePopupPage(driver);
    }

    @When("^I enter phone number: \"(\\(\\d{3}\\) ?|\\d{3}-?\\d{3}-\\d{4})\" in the Send to Phone dialog$")
    public void i_enter_phone_number_in_send_to_phone_dialog(String phoneNumber) throws Throwable {
        sendToPhonePopupPage.sendLocationToPhone(phoneNumber);
    }

    @Then("^I verify \"([^\"]*)\" appears in the Send to Phone dialog message$")
    public void i_verify_text_appears_in_send_to_phone_dialog_message(String text) throws Throwable {
        sendToPhonePopupPage.verifyTextInPopup(text);
    }

    @Then("^I confirm the Send to Phone results popup$")
    public void i_confirm_the_Send_to_Phone_results_popup() throws Throwable {
        sendToPhonePopupPage.confirmSuccessMessagePopUp();
    }

}
