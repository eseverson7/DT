package dtc.pages;

import dtc.data.ConstantsDtc;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Constants;
import utilities.Driver;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by @ankitarora on 02/14/18.
 */
public class MyAccountPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(MyAccountPage.class.getName());

    public MyAccountPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "dt-modal__title")
    public static WebElement dtModalTitle;
    
    @FindBy(id = "e-mail-address")
    public static WebElement myAccountEmailAddress;
    
    @FindBy(id = "reset-password-email")
    public static WebElement myAccountResetPasswordEmail;
    
    @FindBy(id = "password")
    public static WebElement myAccountPassword;

    @FindBy(id = "first-name")
    public static WebElement myAccountFirstName;
    
    @FindBy(id = "last-name")
    public static WebElement myAccountLastName;
    
    @FindBy(className = "form-group__input-toggle")
    public static WebElement myAccountForgotPassword;

    @FindBy(css = "#keep-signed-in > span")
    public static WebElement keepSignedInCheckbox;
    
    @FindBy(linkText = "Sign-up Now")
    public static WebElement myAccountSignUpNow;
    
    @FindBy(className = "header__nav-button-label")
    public static WebElement myAccountLoggedInLabel;
    
    @FindBy(className = "form-group__message--error")
    public static WebElement emailErrorMessageElement;

    @FindBy(className = "login-container__forgot-password-text")
    public static WebElement forgotPasswordLabelElement;
    
    @FindBy(className = "login-container__informational-message")
    public static WebElement resetPasswordInstructionElement;
    
    private static By formSubmitContainerBy = By.className("dt-form__submit");
    private static By passedProgressStepBy = By.className("dt-form__progress-step--passed");
    private static By myAccountModalTitleBy = By.className("dt-modal__title");
    public static By registerCompleteInfoBy = By.className("dt-form__success-message-header");
    public static By successEmailMessageBy = By.className("dt-form__success-message-body");
    public static By emailMessageErrorElementBy = By.className("form-group__message--error");
    public static By myAccountErrorValidationBy = By.className("dt-error-message");
    private static By myAccountModalHeaderBy = By.className("dt-modal__header");
    public static By myAccountModalCloseBy = By.className("dt-modal__close");
    
    private static final String MY_ACCOUNT_MODAL_TITLE = "Sign into your Discount Tire Account";
    private static final String RESET_PASSWORD = "Reset Password";
    private static final String SIGN_IN = "Sign-in";
    private static final String CREATE_AN_ACCOUNT = "CREATE AN ACCOUNT";
    private static final String[] INVALID_EMAIL_ADDRESSES = {"testdiscounttire.com","testAtTheRatediscounttire.com","test@discounttirecom"};
    private static final String EMAIL_VALIDATION_MESSAGE = "A valid e-mail address is required";
    private static final String[] PASSWORD_PROGRESS_STEPS_LABELS = {"7 - 40 characters","AaBbCc","123"};
    private static final String[] PASSWORDS_FOR_VALIDATION = {"testing","Testing","123"};
    private static final String FAKE_EMAIL = "@DTATDTD.com";
    public static String generatedRandomEmail = "";
    private static final String FORGOT_PASSWORD_TEXT = "If you have an account, we'll send you instructions on how to reset your password:";
    private static final String RESET_PASSWORD_INSTRUCTIONS = "An e-mail was sent to the address you've provided. Please follow the instructions to reset your password.";
 
    /**
     * Verify field is displayed on MyAccount modal
     *
     *@param element The web element to check 
     */
    public void assertMyAccountFieldIsDisplayed(WebElement element) {
        LOGGER.info("assertMyAccountFieldIsDisplayed started");
        driver.waitForPageToLoad();
        if(!driver.isElementDisplayed(element)){
        	Assert.fail("FAIL : Input field did NOT displayed on My Account modal");
        }
        LOGGER.info("assertMyAccountFieldIsDisplayed completed");
    }

    /**
     * Input text value in MyAccount Sign-in field
     *
     * @param element The web element to select 
     * @param text    String contains text
     */
    public void inputTextToMyAccountField(WebElement element, String text) {
        LOGGER.info("inputTextToMyAccountField started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(element);
        element.clear();
        element.sendKeys(text);
        LOGGER.info("inputTextToMyAccountField completed");
    }   

    /**
     * Click forgot password link
     */
    public void clickForgotPasswordLink() {
        LOGGER.info("clickForgotPasswordLink started");
        driver.waitForPageToLoad();
        driver.waitForElementClickable(myAccountForgotPassword);
        myAccountForgotPassword.click();
        LOGGER.info("clickForgotPasswordLink completed");
    }

    /**
     * Click Reset Password button
     */
    public void clickResetPasswordButton() {
        LOGGER.info("clickResetPasswordButton started");
        driver.waitForMilliseconds();
        driver.getElementWithText(formSubmitContainerBy, RESET_PASSWORD).click();
        driver.waitForPageToLoad();
        LOGGER.info("clickResetPasswordButton completed");
    }
    
    /**
     * Click Sign In button
     */
    public void clickSignInButton() {
        LOGGER.info("clickSignInButton started");
        driver.getElementWithText(formSubmitContainerBy, SIGN_IN).click();
        driver.waitForPageToLoad();
        LOGGER.info("clickSignInButton completed");
    }
    
    /**
     * Click Sign-up Now link
     */
    public void clickSignUpNowLink() {
        LOGGER.info("clickSignUpNowLink started");
        driver.waitForElementClickable(myAccountSignUpNow);
        myAccountSignUpNow.click();
        driver.waitForPageToLoad();
        LOGGER.info("clickSignUpNowLink completed");
    }
    
    /**
     * Set Keep me signed in checkbox to true
     */
    public void setKeepMeSignedInCheckbox() {
        LOGGER.info("setKeepMeSignedInCheckbox started");
        driver.waitForElementClickable(keepSignedInCheckbox);
        if (!keepSignedInCheckbox.isSelected());
        	keepSignedInCheckbox.click();
        LOGGER.info("setKeepMeSignedInCheckbox completed");
    }
    
    /**
     * UnSet / Deselect Keep me signed in checkbox
     */
    public void unSetKeepMeSignedInCheckbox() {
        LOGGER.info("unSetKeepMeSignedInCheckbox started");
        driver.waitForElementClickable(keepSignedInCheckbox);
        if (keepSignedInCheckbox.isSelected());
        	keepSignedInCheckbox.click();
        LOGGER.info("unSetKeepMeSignedInCheckbox completed");
    }
    
    /**
     * Verify My Account modal input field value with provided text value 
     * 
     * @param InputFieldName Element name that contains the text to verify
     * @param text           String to validate against element text
     */
    public void verifyMyAccountInputFieldValue(String InputFieldName, String text) {
        LOGGER.info("verifyMyAccountInputFieldValue started");
        String actualText = null;
        driver.waitForElementClickable(myAccountEmailAddress);

        if(InputFieldName.equalsIgnoreCase(ConstantsDtc.EMAIL)){
        	actualText = myAccountEmailAddress.getAttribute(Constants.VALUE);
        } else if(InputFieldName.equalsIgnoreCase(ConstantsDtc.PASSWORD)){
        	actualText = myAccountPassword.getAttribute(Constants.VALUE);
        } else
        	actualText = myAccountResetPasswordEmail.getAttribute(Constants.VALUE);        
		Assert.assertTrue("FAIL: " + InputFieldName + " field,  Actual value :" + actualText
				+ " did NOT match with expected text: \"" + text + "\"!", actualText.contains(text));
		
        LOGGER.info("verifyMyAccountInputFieldValue completed");
    }
    
    /**
     * Verify My Account modal input field value with provided text value 
     * 
     * @param buttonName   Name of the button to verify
     * @param status       Desired status of the button
     */
    public void verifyMyAccountButtonState(String buttonName, String status) {
        LOGGER.info("verifyMyAccountButtonState started");
        boolean conditionFailed = false;
        String buttonStatus = null;
        driver.waitForMilliseconds();
        WebElement submitButton = driver.getElementWithText(formSubmitContainerBy, buttonName);
        if(status.equalsIgnoreCase(Constants.ENABLED) && !submitButton.isEnabled()){
        	conditionFailed = true;
        	buttonStatus = Constants.DISABLED;
        }else if(status.equalsIgnoreCase(Constants.DISABLED) && submitButton.isEnabled()){
        	conditionFailed = true;
        	buttonStatus = Constants.ENABLED;
        }
        	
        if(conditionFailed){
        	Assert.fail("FAIL : "+ buttonName + " button status didn't match, Actual status " + buttonStatus+ " did NOT match with expected status " + status);
        }
		
        LOGGER.info("verifyMyAccountButtonState completed");
    }
    
    /**
     * Verify keep me signed in option is displayed on MyAccount modal
     */
    public void assertKeepMeSignedInIsDisplayed() {
        LOGGER.info("assertKeepMeSignedInIsDisplayed started");
        driver.waitForPageToLoad();
        if(!driver.isElementDisplayed(keepSignedInCheckbox)){
        	Assert.fail("FAIL : Keep me signed in option did NOT displayed on My Account modal");
        }
        LOGGER.info("assertKeepMeSignedInIsDisplayed completed");
    }
    
    /**
     * Verify Signed in user first name is displayed on home-page header 
     * 
     * @param firstName String to validate after user signed in
     */
    public void assertSignedInUserFirstNameIsDisplayed(String firstName) {
        LOGGER.info("assertSignedInUserFirstNameIsDisplayed started");
        String actualText = null;
        driver.waitForElementClickable(myAccountLoggedInLabel);

    	actualText = myAccountLoggedInLabel.getText();        
	Assert.assertTrue("FAIL: " + firstName + " name was NOT present,  Actual value :" + actualText
			+ " did NOT match with expected name: \"" + firstName + "\"!", actualText.contains(firstName));
		
        LOGGER.info("assertSignedInUserFirstNameIsDisplayed completed");
    }
       
    /**
     * Verify the error message for invalid email addresses
     */
    public void assertEmailFieldErrorMessageValidation() {
        LOGGER.info("assertEmailFieldErrorMessageValidation started");
        boolean validationPassed = true;
        driver.waitForElementClickable(myAccountEmailAddress);
        for (String invalidEmailAddress : INVALID_EMAIL_ADDRESSES) {
        	inputTextToMyAccountField(myAccountEmailAddress, invalidEmailAddress);
        	if (!emailErrorMessageElement.getText().contains(EMAIL_VALIDATION_MESSAGE)) {
        		validationPassed = false;
        		LOGGER.info("FAIL : Email error message validation failed for case :" + INVALID_EMAIL_ADDRESSES);
        	}
        }
        if (!validationPassed) {
        	Assert.fail("FAIL : Email field error message validations failed");
        }
        LOGGER.info("assertEmailFieldErrorMessageValidation completed");
    }
    
    /**
     * Generate random email and set email to create new account 
     *
     */
    public void generateAndSetFakeRandomEmailForUser(String user) {
        LOGGER.info("generateAndSetFakeRandomEmailForUser started");
        Random rand = new Random();

        user += rand.nextInt(9999) + rand.nextInt(99) + FAKE_EMAIL;
        generatedRandomEmail = user;
        inputTextToMyAccountField(myAccountEmailAddress, user);
        LOGGER.info("Generated fake email address : " + INVALID_EMAIL_ADDRESSES);
        
        LOGGER.info("generateAndSetFakeRandomEmailForUser completed");
    }
    
    /**
     * Click Create an Account button
     */
    public void clickCreateAnAccountButton() {
        LOGGER.info("clickCreateAnAccountButton started");
        driver.getElementWithText(formSubmitContainerBy, CREATE_AN_ACCOUNT).click();
        driver.waitForPageToLoad();
        LOGGER.info("clickCreateAnAccountButton completed");
    }
    
    /**
     * Click Continue Shopping button
     */
    public void clickContinueShoppingButton() {
        LOGGER.info("clickContinueShoppingButton started");
        driver.getElementWithText(formSubmitContainerBy, ConstantsDtc.CONTINUE_SHOPPING).click();
        driver.waitForPageToLoad();
        LOGGER.info("clickContinueShoppingButton completed");
    }
    
    /**
     * Verify My Account confirmation page has provided text message present 
     * 
     * @param element        Web Element that contains the text to verify
     * @param text           String to validate against element text
     */
    public void assertAccountModalHasMessagePresent(By element, String text) {
        LOGGER.info("assertAccountModalHasMessagePresent started");
        driver.waitForElementVisible(myAccountModalTitleBy);
        
        Assert.assertTrue("FAIL: Message :" + text + " did NOT present on My Account modal",
				driver.checkIfElementContainsText(element, text));

        LOGGER.info("assertAccountModalHasMessagePresent completed");
    }
    
    /**
     * Verify email authentication link message present on Account confirmation modal 
     */
    public void assertAuthenticationEmailLinkMessagePresent() {
        LOGGER.info("assertAuthenticationEmailLinkMessagePresent started");
        driver.waitForElementVisible(successEmailMessageBy);

        WebElement messageElement = driver.getElementWithText(successEmailMessageBy, ConstantsDtc.EMAIL_AUTHENTICATION_LINK_MESSAGE);
        Assert.assertTrue("FAIL: Message :" + ConstantsDtc.EMAIL_AUTHENTICATION_LINK_MESSAGE + " did NOT present on My Account modal", 
        		driver.checkIfElementContainsText(messageElement, ConstantsDtc.EMAIL_AUTHENTICATION_LINK_MESSAGE));
		
        LOGGER.info("assertAuthenticationEmailLinkMessagePresent completed");
    }

    /**
     * Verify the requirements for passed progress steps validation for password field
     */
    public void assertPasswordProgressStepsValidation() {
        LOGGER.info("assertPasswordProgressStepsValidation started");
        boolean validationPassed = true;
        int i = 0;
        driver.waitForElementClickable(myAccountPassword);
        for (String password : PASSWORDS_FOR_VALIDATION) {
        	inputTextToMyAccountField(myAccountPassword, password);
        	if (!driver.isElementDisplayed(driver.getElementWithText(passedProgressStepBy, PASSWORD_PROGRESS_STEPS_LABELS[i]))) {
        		validationPassed = false;
        		LOGGER.info("FAIL : Password requirement validation for passed progress steps failed : " + password);
        		}
        	i++;
         }
        if (!validationPassed) {
        	Assert.fail("FAIL : Password requirement for passed progress steps failed");
          }
        LOGGER.info("assertPasswordProgressStepsValidation completed");
    }
    
    /**
     * Verify reset your password instruction displayed
     */
    public void assertResetYourPasswordInstructionDisplayed() {
        LOGGER.info("assertResetYourPasswordInstructionDisplayed started");
        driver.waitForElementVisible(myAccountResetPasswordEmail);
        
        Assert.assertTrue("FAIL: Reset your password instruction :" + FORGOT_PASSWORD_TEXT
				+ " did NOT present on My Account forgot password modal", driver.checkIfElementContainsText(forgotPasswordLabelElement, FORGOT_PASSWORD_TEXT));		
        LOGGER.info("assertResetYourPasswordInstructionDisplayed completed");
    }
    
    /**
     * Verify the error messages for invalid email addresses for reset your password email field
     */
    public void assertResetYourPasswordEmailFieldErrorMessageValidation() {
        LOGGER.info("assertResetYourPasswordEmailFieldErrorMessageValidation started");
        boolean validationPassed = true;
        driver.waitForElementClickable(myAccountResetPasswordEmail);
        for (String invalidEmailAddress : INVALID_EMAIL_ADDRESSES) {
        	inputTextToMyAccountField(myAccountResetPasswordEmail,invalidEmailAddress);
        	if (!emailErrorMessageElement.getText().contains(EMAIL_VALIDATION_MESSAGE)) {
        		validationPassed = false;
        		LOGGER.info("FAIL : Email error message validation failed for case :" + INVALID_EMAIL_ADDRESSES);
            }
        }
        if(!validationPassed){
        	Assert.fail("FAIL : Email field error message validations failed");
        }
        LOGGER.info("assertResetYourPasswordEmailFieldErrorMessageValidation completed");
    }
    
    /**
     * Verify password instruction displayed after reset password
     */
    public void assertPasswordInstructionDisplayedAfterResetPassword() {
        LOGGER.info("assertPasswordInstructionDisplayedAfterResetPassword started");
        driver.waitForElementVisible(myAccountEmailAddress);

		Assert.assertTrue("FAIL: Reset your password instruction :" + RESET_PASSWORD_INSTRUCTIONS
				+ " did NOT present on My Account forgot password modal", driver.checkIfElementContainsText(resetPasswordInstructionElement, RESET_PASSWORD_INSTRUCTIONS));		
        LOGGER.info("assertPasswordInstructionDisplayedAfterResetPassword completed");
    }
    
    /**
     * Click My Account popup
     */
    public void clickMyAccountPopup() {
        LOGGER.info("clickMyAccountPopup started");
        driver.waitForMilliseconds();
        WebElement element = driver.getParentElement(driver.getElementWithText(CommonActions.headerSecondBy, ConstantsDtc.EDIT_SIGNIN_EMAIL_LABEL));
        element.findElement(myAccountModalCloseBy).click();
        driver.waitForPageToLoad();
        LOGGER.info("clickMyAccountPopup completed");
    }
}