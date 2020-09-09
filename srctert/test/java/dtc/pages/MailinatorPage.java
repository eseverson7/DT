package dtc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Channing Luden on 2/27/2017.
 */
public class MailinatorPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(MailinatorPage.class.getName());
    private String currentEmailInbox;

    public MailinatorPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public static final String MAILINATOR_URL = "https://www.mailinator.com/";
    public static final String MAILINATOR_EMAIL_SUFFIX = "@mailinator.com";
    public static final String MAILINATOR_RECEIVED_MOMENTS_AGO = "moments ago";

    @FindBy(id = "inboxfield")
    public static WebElement inboxSearchField;

    @FindBy(className = "input-group-btn")
    public static WebElement searchForInboxBtn;

    @FindBy(id = "msg_body")
    public static WebElement emailBodyIframe;

    @FindBy(id = "publicInboxCtrl")
    public static WebElement emailInbox;

    @FindBy(id = "public_delete_button")
    public static WebElement deleteEmailBtn;

    public static final By inboxEmails = By.className("all_message-min");

    public static final By boldedEmailFields = By.tagName("STRONG");

    public static final By emailCheckboxes = By.tagName("LABEL");

    private static final String jsInboxPopulatedString =
            "return document.getElementsByClassName('all_message-min').length!==0";
    private static final String jsMomentsAgoString =
            "return document.getElementsByClassName('row ng-scope')[0].textContent.indexOf('"
                    + MAILINATOR_RECEIVED_MOMENTS_AGO + "')>-1";

    public void setCurrentEmailInbox(String currentEmailInbox) {
        this.currentEmailInbox = currentEmailInbox;
    }

    /***
     * Goes to mailinator.com and opens the specified inbox. Note: emailInbox arg can either be the full email address
     * (dtc.test@mailinator.com) OR the portion before the @ symbol (dtc.test)
     * @param emailInbox Mailinator inbox to open and display
     */
    public void openMailinatorInbox(String emailInbox) {
        LOGGER.info("openMailinatorInbox started for inbox: \"" + emailInbox + "\"");
        driver.getUrl(MAILINATOR_URL);
        driver.waitForElementClickable(inboxSearchField);

        if (!emailInbox.contains(MAILINATOR_EMAIL_SUFFIX)) {
            emailInbox = emailInbox + MAILINATOR_EMAIL_SUFFIX;
        }

        inboxSearchField.sendKeys(emailInbox);
        searchForInboxBtn.click();
        setCurrentEmailInbox(emailInbox);
        LOGGER.info("openMailinatorInbox completed for inbox: \"" + emailInbox + "\"");
    }

    /***
     * Verifies the most recent email in the Mailinator inbox contains the correct sender, email subject/title, and
     * that the email itself contains the previously stored order / appointment number.
     *
     * @param expectedSender        Expected email sender to verify
     * @param expectedEmailTitle    Expeted email title to verify
     */
    public void verifyMostRecentDtOrderConfirmationInEmailInbox(String expectedSender, String expectedEmailTitle) {
        LOGGER.info("verifyMostRecentDtOrderConfirmationInEmailInbox started for inbox: \"" + currentEmailInbox
                + "\"");
        WebElement mostRecentEmailEle = getMostRecentEmailElement();
        String expectedOrderNumber = driver.scenarioData.getCurrentOrderNumber();

        Assert.assertTrue("FAIL: The most recent email did NOT match the expected sender: \"" + expectedSender
                        + "\" AND email title: \"" + expectedEmailTitle
                        + "\"! Actual title & sender: \"" + mostRecentEmailEle.getText().replace("\n", "; ")
                        + "\"",
                mostRecentEmailEle.getText().toLowerCase().contains(expectedSender.toLowerCase())
                        && mostRecentEmailEle.getText().toLowerCase().contains(expectedEmailTitle.toLowerCase()));

        driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
        mostRecentEmailEle.click();
        driver.waitForMilliseconds();
        webDriver.switchTo().frame(emailBodyIframe);
        Assert.assertTrue("FAIL: The order number given by DT: \"" + expectedOrderNumber
                        + " was NOT found in the confirmation email! ",
                driver.getElementWithText(boldedEmailFields, expectedOrderNumber).isDisplayed());
        webDriver.switchTo().defaultContent();
        LOGGER.info("verifyMostRecentDtOrderConfirmationInEmailInbox completed for inbox: \"" + currentEmailInbox
                + "\"");
    }

    /***
     * Retrieves an element representing the most recent email from the current Mailinator inbox
     * @return WebElement
     */
    private WebElement getMostRecentEmailElement() {
        LOGGER.info("getMostRecentEmailElement started");
        waitForInboxUpdateWithMostRecentEmail();

        WebElement emailEle = null;
        List<WebElement> emailList = webDriver.findElements(inboxEmails);

        //Check inbox for at least one email. If more than one, return the most recent @ index 0
        if (emailList.size() >= 1) {
            emailEle = emailList.get(0);
        } else {
            Assert.fail("FAIL: No emails are present in the current inbox!");
        }
        LOGGER.info("getMostRecentEmailElement completed");
        return emailEle;
    }

    /***
     * Waits for current Mailinator inbox to contain an email, and then to contain an email with a received status of
     * "moments ago". Uses the pollUntil method to check the inbox for emails with a max wait time of 3 minutes. After
     * 3 minutes without receiving an email, an assert failure is thrown. If the inbox contains an email, a second
     * pollUntil is used to check for the received status "moments ago" with a max wait time of 2 minutes. However, no
     * failure for this second pollUntil.
     */
    private void waitForInboxUpdateWithMostRecentEmail(){
        LOGGER.info("waitForEmailWithStatus started");

        if (!driver.pollUntil(jsInboxPopulatedString, Constants.THREE_MIN_WAIT)) {
            Assert.fail("FAIL: Mailinator Inbox is empty, and did not receive a new email within 3 minutes!");
        }

        driver.pollUntil(jsMomentsAgoString, Constants.TWO_MIN_WAIT);
        LOGGER.info("waitForEmailWithStatus completed");
    }

    /***
     * Deletes all emails from the current Mailinator inbox. *Method assumes browser is open and currently displaying
     * a Mailinator inbox.
     */
    public void deleteEmailsFromInbox() {
        driver.waitForElementVisible(emailInbox);
        List<WebElement> emailCheckboxesList = emailInbox.findElements(emailCheckboxes);

        for (WebElement emailCheckbox : emailCheckboxesList){
            emailCheckbox.click();
        }
        driver.jsScrollToElement(deleteEmailBtn);
        deleteEmailBtn.click();
    }
}