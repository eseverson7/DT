package ovc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by eseverson on 6/27/2017.
 */
public class AccountReceivablePage {

    private Driver driver;
    private WebDriver webDriver;
    private ParentElementsPage parentElementsPage;
    private final Logger LOGGER = Logger.getLogger(AccountReceivablePage.class.getName());


    public AccountReceivablePage(Driver driver){
        this.driver = driver;
        webDriver = driver.getDriver();
        parentElementsPage = new ParentElementsPage(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final By accountRequirementsInputBy = By.xpath("//textarea[contains(@ng-model,'form.accountRequirements')]");

    public static final By followingInputBy = By.xpath("following-sibling::div/input");
    
    public static final By followingTextAreaBy = By.xpath("following-sibling::div/textarea");
    
    /**
     * Takes an input String and enters it into the associated text field
     *
     * @param text      Text to be entered into input field
     * @param textbox   Input box to enter the text into
     */
    public void enterTextIntoARInputField(String text, String textbox) {
        LOGGER.info("enterTextIntoARInputField started");
        driver.waitForPageToLoad();
        boolean found = false;
        WebElement input = null;
        WebElement page = parentElementsPage.returnPageObjectElement(ParentElementsPage.AR_PAYMENT);

        List<WebElement> labels = page.findElements(CommonActions.labelTagBy);
        for (WebElement label : labels) {
            if (label.getText().trim().equalsIgnoreCase(textbox)) {
            	if (textbox.equalsIgnoreCase(ConstantsOvc.AR_ADDRESS_LABEL)){
            		 input = label.findElement(followingTextAreaBy);
            	}
            	else {
            		input = label.findElement(followingInputBy);
            	}
                input.click();
                input.clear();
                input.sendKeys(text);
                found = true;
                break;
            }
        }

        if (!found)
            Assert.fail("FAIL: " + text + " was not found on the AR Payment page.");

        LOGGER.info("enterTextIntoARInputField completed");
    }

    /**
     * asserts the maximum character limit of the Account Requirements text input box
     */
    public void assertCharacterLimitAccountRequirement() {
        LOGGER.info("assertCharacterLimitAccountRequirement started");
        driver.waitForPageToLoad();
        WebElement characterLimitContainer = webDriver.findElement(accountRequirementsInputBy);
        String characterLimit = characterLimitContainer.getAttribute(ConstantsOvc.MAX_LENGTH);
        Assert.assertTrue("FAIL: Expected amount:'" + Constants.THREE_HUNDRED_STRING +
                "' DID NOT match the current max characters of:'" + characterLimit + "'!",
                Constants.THREE_HUNDRED_STRING.equals(characterLimit.trim()));
        LOGGER.info("assertCharacterLimitAccountRequirement completed");
    }
}
