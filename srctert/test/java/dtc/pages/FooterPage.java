package dtc.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/22/16.
 */
public class FooterPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(FooterPage.class.getName());

    public FooterPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "business-locator")
    public static WebElement pleaseRead;

    @FindBy(className = "body-container")
    public static WebElement welcomeMessage;
    
    @FindBy(css = "h3.footer__quote-headline-header")
    public static WebElement footerTextElement;

    public static final By payYourBillHeaderSubtextBy = By.className("synchrony-hero-heading-subtext");
    public static final By footerCopyrightTextBy = By.className("footer__copyright");
    public static final By footerSectionLinkBy = By.className("footer__section-list-item");
    public static final String COPYRIGHT_TEXT = "© 2017 The Reinalt-Thomas Corporation. All rights reserved.";
    public static final String FOOTER_HEADLINE_TEXT = "Fast & Friendly service";

    /**
     * Helper method for constructing and clicking an autoClass named element
     * as opposed to straight linkText
     *
     * @param type     Type of link: header, footer...etc
     * @param linkText Text of the link to be clicked
     */
    // TODO: Remove after page validation completion
    public void clickAutoClassLink(String type, String linkText) {
        LOGGER.info("clickAutoClassLink started");
        driver.waitForPageToLoad();
        driver.clickElementByAutoClassName(type, linkText);
        LOGGER.info("clickAutoClassLink completed");
    }

    /**
     * Clicks customer care links based on the input text
     *
     * @param text Text to assert on page
     */
    public void assertApplyNowPageText(String text) {
        LOGGER.info("assertApplyNowPageText started");
        driver.waitForElementVisible(pleaseRead);
        Assert.assertTrue("FAIL: terms_heading header on \"Apply Now\" page did NOT display!",
                pleaseRead.isDisplayed());
        Assert.assertTrue("FAIL: terms_heading header did NOT contain \"" + text + "\"!",
                pleaseRead.getText().contains(text));
        LOGGER.info("assertApplyNowPageText completed");
    }

    /**
     * Verifies the 'text' header is displayed on teh page
     *
     * @param text String text of the displayed header
     */
    public void assertCommercialPaymentsText(String text) {
        LOGGER.info("assertCommercialPaymentsText started");
        Assert.assertTrue("FAIL: welcome-message header on \"Commercial Payment\" page did NOT display!",
                welcomeMessage.isDisplayed());
        Assert.assertTrue("FAIL: welcome-message header did NOT contain \"" + text + "\"!",
                welcomeMessage.getText().contains(text));
        LOGGER.info("assertCommercialPaymentsText completed");
    }

    /**
     * Helper method to click a hyperlink via linkText
     *
     * @param linkText text of the element to click
     */
    public void clickHyperLink(String linkText) {
        LOGGER.info("clickHyperLink started");
        //TODO: These links need to be updated with autoClass names
        driver.clickElementWithLinkText(linkText);
        LOGGER.info("clickHyperLink completed");
    }

    /**
     * Verifies the URL of the social media site clicked on in the footer
     *
     * @param urlText Partial text to be found inside the page URL
     */
    public void verifySocialMediaUrl(String urlText) {
        LOGGER.info("verifySocialMediaUrl started");
        if (urlText.contains(Constants.INSTAGRAM) ||
                urlText.contains(Constants.CAREERS_PARTIAL_URL) ||
                urlText.contains(Constants.STORE_LOCATOR_PARTIAL_URL) ||
                urlText.contains(Constants.RETURN_POLICY_PARTIAL_URL)) {
            String mainHandle = webDriver.getWindowHandle();
            Set allHandles = webDriver.getWindowHandles();
            Iterator iter = allHandles.iterator();
            while (iter.hasNext()) {
                String popupHandle = iter.next().toString();
                if (!popupHandle.contains(mainHandle)) {
                    webDriver.switchTo().window(popupHandle);
                    commonActions.waitForUrl(urlText, Constants.TEN_SEC_WAIT);
                    webDriver.switchTo().window(mainHandle);
                }
            }
        } else {
            commonActions.waitForUrl(urlText, Constants.TEN_SEC_WAIT);
        }
        LOGGER.info("verifySocialMediaUrl completed");
    }

    /**
     * Verifies the Footer has a Copyright Text
     */
    public void verifyCopyrightText() {
        LOGGER.info("verifyCopyright Text started");
        driver.waitForElementVisible(footerCopyrightTextBy);
        String copyrightText = webDriver.findElement(footerCopyrightTextBy).getText();
        Assert.assertTrue("FAIL: \"" + copyrightText + "\" does NOT contain " + "\"" + COPYRIGHT_TEXT + "\"!", copyrightText.contains(COPYRIGHT_TEXT));
        LOGGER.info("verifyCopyright Text completed");
    }
    
    /**
     * Verify that footer link is displayed
     *
     * @param linkText Text in the link to validate
     */
    public void assertFooterLinkIsDisplayed(String linkText) {
    	LOGGER.info("assertFooterLinkIsDisplayed started with linkText \"" + linkText + "\"");
        
    	WebElement linkElement = driver.getElementWithText(footerSectionLinkBy, linkText);
    	Assert.assertTrue("FAIL: Link "+ linkText + " did NOT display ", driver.isElementDisplayed(linkElement));
    	LOGGER.info("assertFooterLinkIsDisplayed completed with linkText \"" + linkText + "\"");
    }
    
    /**
     * Verify the Footer Headline Text
     */
    public void verifyFooterHeadlineText() {
        LOGGER.info("verifyFooterHeadlineText Text started");
        driver.waitForElementVisible(footerTextElement);
        String footerHeadlineText = footerTextElement.getText();
        Assert.assertTrue("FAIL: \"" + footerHeadlineText + "\" does NOT contain " + "\"" + FOOTER_HEADLINE_TEXT 
        		+ "\"!", footerHeadlineText.contains(FOOTER_HEADLINE_TEXT));
        LOGGER.info("verifyFooterHeadlineText Text completed");
    }
}