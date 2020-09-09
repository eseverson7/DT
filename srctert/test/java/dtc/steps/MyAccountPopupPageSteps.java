package dtc.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.data.ConstantsDtc;
import dtc.pages.MyAccountPage;
import utilities.Driver;

/**
 * Created by ankitarora on 02/19/18.
 */
public class MyAccountPopupPageSteps {

    private MyAccountPage myAccountPage;

    public MyAccountPopupPageSteps(Driver driver) {
        myAccountPage = new MyAccountPage(driver);
    }

    @Then("^I verify my account sign-in modal is displayed$")
    public void i_verify_my_account_sign_in_modal_is_displayed() throws Throwable {
    	i_should_see_email_address_field_is_displayed();
    	i_should_see_password_field_is_displayed();	
    }
    
    @Then("^I should see email address field is displayed$")
    public void i_should_see_email_address_field_is_displayed() throws Throwable {
        myAccountPage.assertMyAccountFieldIsDisplayed(MyAccountPage.myAccountEmailAddress);
    }
    
    @Then("^I should see password field is displayed$")
    public void i_should_see_password_field_is_displayed() throws Throwable {
    	myAccountPage.assertMyAccountFieldIsDisplayed(MyAccountPage.myAccountPassword);
    }

    @Then("^I should see first name field is displayed$")
    public void i_should_see_first_name_field_is_displayed() throws Throwable {
    	myAccountPage.assertMyAccountFieldIsDisplayed(MyAccountPage.myAccountFirstName);
    }
    
    @Then("^I should see last name field is displayed$")
    public void i_should_see_last_name_field_is_displayed() throws Throwable {
    	myAccountPage.assertMyAccountFieldIsDisplayed(MyAccountPage.myAccountLastName);
    }
    
    @And("^I verify that \"(Sign-In|Reset Password|CREATE AN ACCOUNT|DONE EDITING)\" is disabled by default$")
    public void i_verify_my_account_button_state_disabled(String buttonName) throws Throwable {
    	myAccountPage.verifyMyAccountButtonState(buttonName, Constants.DISABLED);
    }

    @And("^I should see \"(Sign-In|Reset Password|CREATE AN ACCOUNT|DONE EDITING)\" is enabled$")
    public void i_should_see_my_account_button_state_enabled(String buttonName) throws Throwable {
    	myAccountPage.verifyMyAccountButtonState(buttonName, Constants.ENABLED);
    }

    @When("^I enter \"(.*?)\" in email address field$")
    public void i_enter_email_in_email_address_field(String value) throws Throwable {
    	myAccountPage.inputTextToMyAccountField(MyAccountPage.myAccountEmailAddress, value);
    }
    
    @When("^I enter \"(.*?)\" in password field$")
    public void i_enter_password_in_password_field(String value) throws Throwable {
    	myAccountPage.inputTextToMyAccountField(MyAccountPage.myAccountPassword, value);
    }

    @When("^I enter \"(.*?)\" in first name field$")
    public void i_enter_firstname_in_firstname_field(String value) throws Throwable {
    	myAccountPage.inputTextToMyAccountField(MyAccountPage.myAccountFirstName, value);
    }
    
    @When("^I enter \"(.*?)\" in last name field$")
    public void i_enter_lastname_in_lastname_field(String value) throws Throwable {
    	myAccountPage.inputTextToMyAccountField(MyAccountPage.myAccountLastName, value);
    }

    @Then("^I verify keep me signed-in option is displayed$")
    public void i_verify_keep_me_signed_in_option_is_displayed() throws Throwable {
    	myAccountPage.assertKeepMeSignedInIsDisplayed();
    }

    @And ("^I select keep me signed-in option$")
    public void i_select_keep_me_signed_in_option() throws Throwable {
    	myAccountPage.setKeepMeSignedInCheckbox();
    }
    
    @And ("^I deselect keep me signed-in option$")
    public void i_deselect_keep_me_signed_in_option() throws Throwable {
    	myAccountPage.unSetKeepMeSignedInCheckbox();
    }

    @And ("^I select sign-in$")
    public void i_select_sign_in_option() throws Throwable {
    	myAccountPage.clickSignInButton();
    }
    
    @And ("^I select forgot password option$")
    public void i_select_forgot_password_option() throws Throwable {
    	myAccountPage.clickForgotPasswordLink();
    }
    
    @And ("^I select reset password option$")
    public void i_select_reset_password_option() throws Throwable {
    	myAccountPage.clickResetPasswordButton();
    }

    @When("^I enter \"(.*?)\" in reset password email address field$")
    public void i_enter_email_in_reset_password_email_address_field(String value) throws Throwable {
    	myAccountPage.inputTextToMyAccountField(MyAccountPage.myAccountResetPasswordEmail, value);
    }

    @Then("^I should see \"(.*?)\" displayed on homepage header$")
    public void i_should_see_first_name_is_displayed(String firstName) throws Throwable {
    	myAccountPage.assertSignedInUserFirstNameIsDisplayed(firstName);
    }

    @And ("^I select sign-up now$")
    public void i_select_sign_up_option() throws Throwable {
    	myAccountPage.clickSignUpNowLink();
    }
    
    @Then ("^I verify email validation error message should display for all invalid email addresses$")
    public void i_verify_email_validation_error_message_display_on_invalid_email_addrresses() throws Throwable {
    	myAccountPage.assertEmailFieldErrorMessageValidation();
    }
    
    @And("^I generate and set random email address for user \"(.*?)\"$")
    public void i_generate_set_random_email_for_user(String user) throws Throwable {
    	myAccountPage.generateAndSetFakeRandomEmailForUser(user);
    }
    
    @And ("^I select create an account option$")
    public void i_select_create_an_account_option() throws Throwable {
    	myAccountPage.clickCreateAnAccountButton();
    }
    
    @Then ("^I verify email sent confirmation message displayed on account confirmation modal$")
    public void i_verify_email_sent_confirmation_message_displayed_on_account_confirmation_modal() throws Throwable {
    	myAccountPage.assertAccountModalHasMessagePresent(MyAccountPage.successEmailMessageBy, ConstantsDtc.EMAIL_SENT_MESSAGE);
    }
    
    @Then ("^I verify my email address displayed on account confirmation modal$")
    public void i_verify_email_address_displayed_on_account_confirmation_modal() throws Throwable {
    	myAccountPage.assertAccountModalHasMessagePresent(MyAccountPage.successEmailMessageBy, MyAccountPage.generatedRandomEmail);
    }
    
    @Then ("^I verify my email authentication link message displayed on account confirmation modal$")
    public void i_verify_email_authentication_link_message_displayed_on_account_confirmation_modal() throws Throwable {
    	myAccountPage.assertAuthenticationEmailLinkMessagePresent();
    }
    
    @And ("^I select continue shopping option$")
    public void i_select_continue_shopping_option() throws Throwable {
    	myAccountPage.clickContinueShoppingButton();
    }
    
    @Then ("^I verify the password requirement validation for passed progress steps$")
    public void i_verify_the_password_requirement_validation_for_passed_progress_steps() throws Throwable {
    	myAccountPage.assertPasswordProgressStepsValidation();
    }
    
    @Then ("^I verify Reset Your Password instruction displayed on forgot password modal$")
    public void i_verify_reset_your_password_instruction_displayed_on_forgot_password_modal() throws Throwable {
    	myAccountPage.assertResetYourPasswordInstructionDisplayed();
    }
    
    @Then ("^I verify Reset Your Password email validation error message should display for all invalid email addresses$")
    public void i_verify_reset_password_email_validation_error_message_display_on_invalid_email_addrresses() throws Throwable {
    	myAccountPage.assertResetYourPasswordEmailFieldErrorMessageValidation();
    }

    @Then ("^I verify Reset Your Password instruction displayed after reset password$")
    public void i_verify_reset_your_password_instruction_displayed_after_reset_password() throws Throwable {
    	myAccountPage.assertPasswordInstructionDisplayedAfterResetPassword();
    }
    
    @Then ("^I verify email account not authenticated message displayed$")
    public void i_verify_email_account_not_authenticated_message_displayed() throws Throwable {
    	myAccountPage.assertAccountModalHasMessagePresent(MyAccountPage.emailMessageErrorElementBy, ConstantsDtc.EMAIL_ACCOUNT_NOT_AUTHENTICATED_MESSAGE);
    }
    
    @Then ("^I should see invalid credentials error validation message$")
    public void i_should_see_invalid_credentials_error_validation_message() throws Throwable {
    	myAccountPage.assertAccountModalHasMessagePresent(MyAccountPage.myAccountErrorValidationBy, ConstantsDtc.MY_ACCOUNT_INVALID_CREDS_MESSAGE);
    }

    @Then("^I should see my \"(.*?)\" populated in \"(Email|Password|Reset Password Email)\" field$")
    public void i_should_see_my_value_populated_in_field(String value, String inputField) throws Throwable {
    	myAccountPage.verifyMyAccountInputFieldValue(inputField.toLowerCase(), value);
    }
    
    @And ("^I close My Account modal popup$")
    public void i_close_my_account_modal_popup() throws Throwable {
    	myAccountPage.clickMyAccountPopup();
    }
}
