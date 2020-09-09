package dtc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by Channing Luden on 10/19/2016.
 */
public class SendToPhonePopupPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(FitmentPopupPage.class.getName());
    private final CommonActions commonActions;

    public SendToPhonePopupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
        commonActions = new CommonActions(driver);
    }

    private static final String SEND_TO_PHONE_CONFIRMATION_MESSAGE = "The information for this location will be sent to your phone";

    @FindBy(id = "sendToPhoneNumber")
    public static WebElement phoneNumberTextBox;

    @FindBy(xpath = "//button[text()='Send']")
    public static WebElement sendButton;

    public static By successPopUpMessage = By.className("success-info");

    private static By sendToPhoneMessageBy = By.className("send-to-phone-message");

    /**
     * Enters the provided phone number into the send to phone text box and clicks the send button for
     * the 'Send to Phone' pop-up
     *
     * @param phoneNumber phone number to use
     */
    public void sendLocationToPhone(String phoneNumber) {
        LOGGER.info("sendLocationToPhone started with phone number: " + phoneNumber);
        driver.waitForElementClickable(phoneNumberTextBox);
        phoneNumberTextBox.sendKeys(phoneNumber);
        driver.waitForMilliseconds();
        sendButton.click();
        driver.waitForMilliseconds();
        LOGGER.info("sendLocationToPhone completed with phone number: " + phoneNumber);
    }

    /**
     * Confirms the display of the Success pop-up message after sending a store location to a phone via the
     * 'Send to Phone' pop-up
     */
    public void confirmSuccessMessagePopUp() {
        LOGGER.info("confirmSuccessMessagePopUp started");
        Assert.assertTrue("FAIL: The \"Send to phone\" pop-up did NOT display a success message after" +
                " attempting to send the location!", driver.waitForTextPresent(successPopUpMessage,
                SEND_TO_PHONE_CONFIRMATION_MESSAGE, Constants.THREE_SEC_WAIT));
        LOGGER.info("confirmSuccessMessagePopUp completed");
    }

    /**
     * Enters the provided phone number into the send to phone text box and clicks the send button for
     * the 'Send to Phone' pop-up
     *
     * @param text The text to verify
     */
    public void verifyTextInPopup(String text) {
        LOGGER.info("verifyTextInPopup started with text: " + text);
        driver.waitForElementVisible(sendToPhoneMessageBy);
        driver.waitForTextPresent(sendToPhoneMessageBy, text, Constants.DEFAULT_SEC_WAIT);
        LOGGER.info("verifyTextInPopup completed with text: " + text);
    }
}