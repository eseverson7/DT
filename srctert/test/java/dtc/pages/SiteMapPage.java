package dtc.pages;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Channing Luden on 12/9/2016.
 */
public class SiteMapPage {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(SiteMapPage.class.getName());

    public SiteMapPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    private static final String TIRES_MIXED_CASE = "Tires";
    private static final String WHEELS_MIXED_CASE = "Wheels";
    private static final String SITE_MAP_PATH = "/sitemap";

    @FindBy(className = "breadcrumb-container")
    public static WebElement breadCrumbContainer;

    private static final By linkedPageTitle = By.tagName("h1");

    private static final By sectionLinksByTitle = By.className("contentpage-topic__container");

    /***
     * Given a section, method clicks all the links in the section on the Site Map page, and does basic validation
     * of the new page.  Validation includes checking the url of the new page against the href contained within the
     * link on the Site Map page, display of breadcrumb links, and that a page title is present.
     * @param sectionToVerify String representing the name of the section containing links to be verified
     */
    public void clickSectionLinksWithVerification(String sectionToVerify) {
        LOGGER.info("clickSectionLinksWithVerification started for section: " + sectionToVerify);
        List<WebElement> sectionsList = webDriver.findElements(sectionLinksByTitle);
        List<WebElement> linkElementsList = new ArrayList<WebElement>();
        List<String> linkTitleList = new ArrayList<String>();
        List<String> linksWithIssuesList = new ArrayList<String>();

        findAllLinksUnderSection(sectionToVerify, sectionsList, linkElementsList);

        storeTextValueOfLinks(linkElementsList, linkTitleList);

        for (String title : linkTitleList) {

            WebElement linkEle = createTextBasedWebElementForLink(title);

            String linkName;
            String linkUrl;
            linkName = linkEle.getText().trim();
            linkUrl = linkEle.getAttribute("href");

            LOGGER.info("\t - Attempting to verify section link: " + linkName);

            driver.waitForElementVisible(linkEle);

            //TODO: remove once geckodriver/marionette is updated & stabilized
            if (Config.isFirefox()) {
                driver.jsMoveToElementClick(linkEle);
            } else {
                linkEle.click();
            }

            if (checkUrlOnLinkedPage(linksWithIssuesList, linkName, linkUrl)) continue;

            checkBreadcrumbOnLinkedPage(linksWithIssuesList, linkName);

            if (checkForTitleOnLinkedPage(linksWithIssuesList, linkName)) continue;

            LOGGER.info("Site Map link: '" + linkName + "' works as expected. Validated - Url, Breadcrumb, " +
                    "and display of a page title");

            navBackToSiteMapPage();
        }

        Assert.assertTrue("FAIL: Issues found for one or more links under \""
                + sectionToVerify + "\" section: " + linksWithIssuesList + "\"!", linksWithIssuesList.isEmpty());

        LOGGER.info("clickSectionLinksWithVerification completed successfully for section: " + sectionToVerify);
    }

    //region Private methods
    //region Helper methods
    /***
     * Finds all the links under a specified section of the Site Map page. Given the name of the
     * section and a list of all the sections on the Site Map page, finds all the anchor tags under the correct
     * section and stores them in a list used by clickSectionLinksWithVerification.
     * @param sectionToVerify String representing the name of the section containing links to be verified
     * @param sectionsList List containing all the WebElements for every section on the Site Map page
     * @param linkElementsList List used to store all WebElement links (anchor tags) for the specified and found section
     */
    private void findAllLinksUnderSection(String sectionToVerify, List<WebElement> sectionsList,
                                          List<WebElement> linkElementsList) {
        LOGGER.info("findAllLinksUnderSection started");
        for (WebElement section : sectionsList) {
            LOGGER.info("Found '" + sectionToVerify + "' section on Site Map page!");
            if (section.getText().toLowerCase().contains(sectionToVerify.toLowerCase())) {

                if (sectionToVerify.equalsIgnoreCase("services")
                        && section.getText().toLowerCase().contains("customer services")) continue;
                if (sectionToVerify.equals(ConstantsDtc.TIRES)
                        && !section.getText().contains(ConstantsDtc.TIRES)) continue;
                if (sectionToVerify.equals(ConstantsDtc.WHEELS)
                        && !section.getText().contains(ConstantsDtc.WHEELS)) continue;

                linkElementsList.addAll(section.findElements(By.tagName("a")));
                break;
            }
        }
        LOGGER.info("findAllLinksUnderSection completed");
    }

    /***
     * Stores the text value from the list of WebElements (in this case the name of the links) and adds them to a
     * separate list used by clickSectionLinksWithVerification.
     * @param linkElementsList List used to store all WebElement links (anchor tags) for the specified and found section
     * @param linkTitleList List composed of all the text (titles) for the links in the section of interest
     */
    private void storeTextValueOfLinks(List<WebElement> linkElementsList, List<String> linkTitleList) {
        LOGGER.info("storeTextValueOfLinks started");
        for (WebElement link : linkElementsList) {
            linkTitleList.add(link.getText().trim());
        }
        LOGGER.info("storeTextValueOfLinks completed");
    }

    /**
     * Creates the WebElement for the current link, basing it off of its displayed text value. If and Else If statements
     * were needed to specify the TIRES and WHEELS sections on the Site Map and not the similar links in the header of
     * the page.
     *
     * @param title String representing the displayed text value of the link to click and validate
     * @return WebElement for the desired link based on its specified text value (title)
     */
    private WebElement createTextBasedWebElementForLink(String title) {
        LOGGER.info("createTextBasedWebElementForLink started");
        WebElement linkEle;
        String duplicateTextLinksXpath = "//a[text()='%s']";

        if (title.equals(ConstantsDtc.TIRES)) {
            linkEle = webDriver.findElement(By.xpath(String.format(duplicateTextLinksXpath,
                    TIRES_MIXED_CASE)));
        } else if (title.equals(ConstantsDtc.WHEELS)) {
            linkEle = webDriver.findElement(By.xpath(String.format(duplicateTextLinksXpath,
                    WHEELS_MIXED_CASE)));
        } else {
            linkEle = webDriver.findElement(By.linkText(title));
        }
        LOGGER.info("createTextBasedWebElementForLink completed");
        return linkEle;
    }

    /**
     * Navigates the browser back to the Site Map Page by entering the Url into the address bar.
     */
    private void navBackToSiteMapPage() {
        LOGGER.info("navBackToSiteMapPage started");
        webDriver.get(Config.getBaseUrl() + SITE_MAP_PATH);
        LOGGER.info("navBackToSiteMapPage completed");
    }
    //endregion Helper methods
    //region Checker methods

    /***
     * Checks that the browser Url of the current page contains the expected Url path taken from the anchor tag for the
     * link on the Site Map page.
     * Note: Catch statement is grabbing an assertion error. In this case it is necessary so that the other links under
     * the section being tested can also be evaluated (since an Assertion failure would end the entire scenario at the
     * point of failure).
     * @param linksWithIssuesList List used to store and track the name/title of links (within the section under test)
     *                            that have failed a check/validation step.
     * @param linkName String representing the name/title of the link being checked
     * @param linkUrl String containing the expected Url path for the link being checked
     * @return True or False dependent on whether catch statement (actual Url path didn't match expected) was used
     */
    private boolean checkUrlOnLinkedPage(List<String> linksWithIssuesList, String linkName, String linkUrl) {
        LOGGER.info("checkUrlOnLinkedPage started");
        try {
            commonActions.waitForUrl(linkUrl, Constants.FIVE_SEC_WAIT);
            LOGGER.info("\t - Verified expected url " + linkUrl + " matches url of page loaded");
        } catch (AssertionError ae) {
            linksWithIssuesList.add(linkName + " = incorrect url on page load!");
            LOGGER.info("\t - ISSUE: " + linkUrl + " does NOT match url of page loaded. " +
                    "\n Marking link as a failure and proceeding to next link in section");
            navBackToSiteMapPage();
            LOGGER.info("checkUrlOnLinkedPage completed - URL navigates to correct page");
            return true;
        }
        LOGGER.info("checkUrlOnLinkedPage completed - URL did NOT navigate to correct page");
        return false;
    }

    /***
     * Checks for a Breadcrumb container on the linked out page from the Site Map
     * @param linksWithIssuesList List used to store and track the name/title of links (within the section under test)
     *                            that have failed a check/validation step.
     * @param linkName String representing the name/title of the link being checked
     */
    private void checkBreadcrumbOnLinkedPage(List<String> linksWithIssuesList, String linkName) {
        LOGGER.info("checkBreadcrumbOnLinkedPage started for link/page: " + linkName);
        if (!driver.isElementDisplayed(breadCrumbContainer, Constants.ONE_SEC_WAIT)) {
            linksWithIssuesList.add("\n\t" + linkName + " = No breadcrumb links found on page!");
        }
        LOGGER.info("checkBreadcrumbOnLinkedPage completed for link/page: " + linkName);
    }

    /***
     * Checks for a Title section on the linked out page from the Site Map
     * @param linksWithIssuesList List used to store and track the name/title of links (within the section under test)
     *                            that have failed a check/validation step.
     * @param linkName String representing the name/title of the link being checked
     * @return True or False dependent on whether the linked out page contained at least one title section
     */
    private boolean checkForTitleOnLinkedPage(List<String> linksWithIssuesList, String linkName) {
        LOGGER.info("checkForTitleOnLinkedPage started for link/page: " + linkName);
        List<WebElement> pageTitles;

        webDriver.manage().timeouts().implicitlyWait(Constants.ZERO, TimeUnit.SECONDS);

        pageTitles = webDriver.findElements(linkedPageTitle);
        if (pageTitles.size() <= 0) {
            linksWithIssuesList.add("\n\t" + linkName + " = No title(s) found on page!");
            navBackToSiteMapPage();
            webDriver.manage().timeouts().implicitlyWait(Constants.DEFAULT_SEC_WAIT, TimeUnit.SECONDS);
            LOGGER.info("checkForTitleOnLinkedPage completed for link/page: " + linkName);
            return true;
        }
        webDriver.manage().timeouts().implicitlyWait(Constants.DEFAULT_SEC_WAIT, TimeUnit.SECONDS);
        LOGGER.info("checkForTitleOnLinkedPage completed for link/page: " + linkName);
        return false;
    }

}