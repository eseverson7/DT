package dtc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mukul Garg on 8/29/2017
 */
public class GondorPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(GondorPage.class.getName());
    private String currentEmailInbox;

    public GondorPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public static final String GONDOR_URL = "http://gondor.trtc.com/";
    public static final String GONDOR_EMAIL_SUFFIX = "@discounttire.com";
    private static final String MAIN_EMAIL_FRAME = "s_MainFrame";
    private static final int MAX_TRIALS = 6;
    public static final By boldedEmailFields = By.tagName("b");
    public static final By emailCloseIcon = By.cssSelector("[id$=-close-graphic]");

    @FindBy(name = "Username")
    public static WebElement inboxUsernameField;

    @FindBy(name = "Password")
    public static WebElement inboxPasswordField;

    @FindBy(css = "input[type='submit']")
    public static WebElement signInInboxBtn;

    @FindBy(id = "e-tab-mail-body-cell")
    public static WebElement inboxEmailsBtn;

    @FindBy(className = "s-toolbaricon-notext")
    public static WebElement refreshEmailBtn;

    @FindBy(id = "e-listview-container-mail-row-1")
    public static WebElement firstEmail;

    /**
     * Setting currentEmailInbox object value
     * @param currentEmailInbox
     */
    public void setCurrentEmailInbox(String currentEmailInbox) {
        this.currentEmailInbox = currentEmailInbox;
    }

    /***
     * Goes to gondor.trtc.com and opens the specified inbox. Note: emailInbox arg can either be the full email address
     * (dtc.test@discounttire.com) OR the portion before the @ symbol (dtc.test)
     * @param emailUsername GONDOR email username 
     * @param emailPassword GONDOR email password 
     */
    public void openGondorInbox(String emailUsername, String emailPassword) {
        LOGGER.info("openGondorInbox started for inbox: \"" + emailUsername + "\\" + emailPassword + "");
        driver.getUrl(GONDOR_URL);
        driver.waitForElementClickable(inboxUsernameField);

        if (!emailUsername.contains(GONDOR_EMAIL_SUFFIX) && !emailUsername.contains(GONDOR_EMAIL_SUFFIX.toUpperCase())) {
            emailUsername = emailUsername + GONDOR_EMAIL_SUFFIX;
        }

        inboxUsernameField.sendKeys(emailUsername);
        inboxPasswordField.sendKeys(emailPassword);
        signInInboxBtn.click();
        setCurrentEmailInbox(emailUsername);
        LOGGER.info("openGondorInbox completed for inbox: \"" + emailUsername + "\"");
    }

    /***
     * Verifies the most recent email in the GONDOR inbox contains the email
     * contains the previously stored order / appointment number.
     */
    public void verifyMostRecentDtOrderConfirmationInEmailInbox() {
        LOGGER.info("verifyMostRecentDtOrderConfirmationInEmailInbox started for inbox: \"" + currentEmailInbox
                + "\"");

        String emailBodyFrame;
        int currrentTrial = 0;
        String expectedOrderNumber = driver.scenarioData.getCurrentOrderNumber();
        boolean emailNotFound = true;
        long emailCheckStartTime = System.currentTimeMillis();

        while (emailNotFound && currrentTrial < MAX_TRIALS) {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(MAIN_EMAIL_FRAME);
            driver.waitForElementClickable(refreshEmailBtn);
            refreshEmailBtn.click();

            Actions action = new Actions(webDriver);
            action.doubleClick(firstEmail).perform();

            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);

            emailBodyFrame = getEmailBodyFrame();
            webDriver.switchTo().frame(emailBodyFrame);

            List<WebElement> list = webDriver.findElements(boldedEmailFields);
            for (WebElement strongElement : list) {
                if (strongElement.getText().contains(expectedOrderNumber)) {
                    Assert.assertTrue("Success: The order number given by DT: \"" + expectedOrderNumber +
                            " was found in the confirmation email!", strongElement.getText().contains(expectedOrderNumber));
                    LOGGER.info("Email found in inbox at : " + (System.currentTimeMillis() - emailCheckStartTime)
                            + " seconds after : " + currrentTrial + " trails");
                    emailNotFound = false;
                    break;
                }
            }

            webDriver.switchTo().parentFrame();

            /// closing last opened email window
            List<WebElement> listClose = webDriver.findElements(emailCloseIcon);
            listClose.get(listClose.size() - 1).click();

            inboxEmailsBtn.click();
            driver.waitForMilliseconds(Constants.TWO_MILLISEC_WAIT);
            currrentTrial++;
        }

        if (currrentTrial == MAX_TRIALS && emailNotFound) {
            Assert.fail("FAIL: The order number given by DT: \"" + expectedOrderNumber + " was NOT found in the confirmation email! ");
        }

        LOGGER.info("verifyMostRecentDtOrderConfirmationInEmailInbox completed for inbox: \"" +
                currentEmailInbox + "\"");
    }

    /***
     * Gets the dynamically generated frame ID of the email body
     * @return String
     */
    private String getEmailBodyFrame() {
        LOGGER.info("getEmailBodyFrame started");
        String javascript = "frame = document.getElementsByClassName('s-form-bodyiframe s-basicpanel s-panel-border')[0]; " +
                "return frame.getAttribute('id');";
        String frameId;

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        frameId = (String) jse.executeScript(javascript);

        LOGGER.info("getEmailBodyFrame completed, got frame ID \"" + frameId + "\"");
        return frameId;
    }
}