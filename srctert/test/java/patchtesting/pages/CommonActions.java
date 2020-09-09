package patchtesting.pages;

import common.Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by eseverson on 5/21/18.
 */
public class CommonActions {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public CommonActions(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "mwEmbedKalturaIframe")
    public static WebElement commoniFrame1;

    @FindBy(className = "accessibilityLabel")
    public WebElement currentTime;

    @FindBy(className = "playlistAPI")
    public WebElement videoContainer;

    @FindBy(className = "time-disp")
    public static WebElement timeDisplay;

    @FindBy(className = "play-btn-large")
    public static WebElement largePlayBtn;

    public static final By li = By.tagName("li");

    public static final By imgTagBy = By.tagName("img");

    private static final String A = "a";
    private static final String P = "P";
    private static final String H2 = "h2";
    private static final String TEXTCONTENT = "textContent";

    /**
     * Selects a sub-menu option via provided menu and sub-menu options
     *
     * @param menuOption    Menu header to hover over
     * @param subMenuOption Sub-menu option to click
     */
    public void selectSubMenuOption(String menuOption, String subMenuOption) {
        LOGGER.info("selectSubMenuOption started");
        driver.waitForMilliseconds();
        By topMenuLink = By.linkText(menuOption);
        driver.mouseHoverOverElement(topMenuLink);
        driver.waitForMilliseconds();
        By subMenuLink = By.linkText(subMenuOption);
        webDriver.findElement(subMenuLink).click();
        LOGGER.info("selectSubMenuOption completed");
    }

    /**
     * Switches frame back to the default content
     */
    public void switchToDefaultContentWindow() {
        LOGGER.info("switchToDefaultContentWindow started");
        webDriver.switchTo().defaultContent();
        LOGGER.info("switchToDefaultContentWindow completed");
    }

    /**
     * Selects a link from the passed in link text
     *
     * @linkText Text of the link to click in the section
     */
    public void selectLinkFromPage(String linkText) {
        LOGGER.info("selectLinkFromPage started");
        try {
            webDriver.findElement(By.linkText(linkText)).click();
        } catch (Exception e) {
            driver.getElementWithText(By.tagName(Constants.BUTTON), linkText).click();
        }
        driver.waitForMilliseconds(Constants.FIVE_MILLISEC_WAIT);
        LOGGER.info("selectLinkFromPage completed");
    }

    /**
     * Verifies page URL. Switches based on window text passed in.
     *
     * @window New window or Same window
     * @url Text of the link to click in the section
     */
    public void verifyPageURL(String url) {
        LOGGER.info("verifyPageURL started");
        driver.waitForPageToLoad();
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertTrue("FAIL: Current page URL (" + currentUrl + ") did not contain: " + url,
                currentUrl.contains(url));
        LOGGER.info("verifyPageURL completed");
    }

    /**
     * Switches to a newly opened window handle
     */
    public void switchToNewWindowHandle() {
        LOGGER.info("verifyPageURL completed");
        String mainHandle = webDriver.getWindowHandle();
        Set allHandles = webDriver.getWindowHandles();
        Iterator iter = allHandles.iterator();
        while (iter.hasNext()) {
            String popupHandle = iter.next().toString();
            if (!popupHandle.contains(mainHandle)) {
                webDriver.switchTo().window(popupHandle);
            }
        }
        LOGGER.info("verifyPageURL completed");
    }

    /**
     * Verifies video is playing on the page
     */
    public void selectFirstVideoAndVerifyVideoPlaying() {
        LOGGER.info("selectFirstVideoAndVerifyVideoPlaying started");
        driver.waitForPageToLoad();
        driver.waitForMilliseconds();

        // Method needs actual testing once videos are actually on page
        webDriver.switchTo().frame(commoniFrame1);
        videoContainer.findElement(li).click();

        driver.waitForMilliseconds();
        String timeString = currentTime.getAttribute(TEXTCONTENT);
        int timeInt = Integer.parseInt(timeString.substring(2, 4));
        Assert.assertTrue("FAIL: Video time display was not greater than zero.", timeInt > 0);
        webDriver.switchTo().defaultContent();

        LOGGER.info("selectFirstVideoAndVerifyVideoPlaying completed");
    }

    /**
     * Verifies video is playing on the screen
     */
    public void verifyPopupVideoIsPlaying() {
        LOGGER.info("verifySafetyVideoIsPlaying started");
        driver.waitForMilliseconds();
        // Need video (and for it to be playing) for this to pass
        String timeString = timeDisplay.getText();
        int timeInt = Integer.parseInt(timeString.substring(2, 4));
        Assert.assertTrue("FAIL: Video does not appear to be playing.", timeInt > 0);
        LOGGER.info("verifySafetyVideoIsPlaying completed");
    }

    /**
     * Switches frame context to the specified iFrame
     *
     * @param position Position within the DOM of the iframe
     */
    public void switchToVideoContentFrame(int position) {
        LOGGER.info("switchToVideoContent started");
        driver.waitForPageToLoad();
        webDriver.switchTo().frame(position);
        driver.waitForElementClickable(commoniFrame1);
        webDriver.switchTo().frame(commoniFrame1);
        LOGGER.info("switchToVideoContent completed");
    }

    /**
     * Verifies the file downloaded to the correct path with the correct extension
     *
     * @param fileNameWithExtension Name of the downloaded file + extension
     */
    public void isFileDownloaded(String fileNameWithExtension) {
        LOGGER.info("isFileDownloaded started");
        boolean flag = false;
        String filePath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\";

        File dir = new File(filePath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileNameWithExtension)) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue("FAIL: File not downloaded to the Downloads folder.", flag);
        LOGGER.info("isFileDownloaded completed");
    }

    /**
     * Deletes downloaded file passed in via name
     *
     * @param name File + extension name
     */
    public void deleteDownloadedFile(String name) {
        LOGGER.info("switchToVideoContent completed");
        try {
            String filePath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\" + name;
            Files.deleteIfExists(Paths.get(filePath));
        } catch (NoSuchFileException e) {
            LOGGER.info("No such file/directory exists");
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("switchToVideoContent completed");
    }

    /**
     * Verifies the passed in text of a header element is displayed on the page
     *
     * @param text Text value to assert on the page
     */
    public void verifyElementDisplayedOnPage(String text) {
        LOGGER.info("verifyElementDisplayedOnPage started");
        driver.waitForPageToLoad();

        List<String> commonTags = new ArrayList<String>(Arrays.asList(Constants.SPAN, P, A, H2));
        for (String tag : commonTags) {
            try {
                WebElement textEle = driver.getElementWithText(By.tagName(tag), text);
                if (textEle != null) {
                    Assert.assertTrue("FAIL: Element '" + text + "' was not found on page.",
                            driver.isElementDisplayed(textEle));
                    break;
                }
            } catch (Exception e) {
                LOGGER.info("Could not find an element on page by tag type '" + tag
                        + "' with text value '" + text + "'");
            }
        }
        LOGGER.info("verifyElementDisplayedOnPage completed");
    }

    /**
     * Navigates to page with url
     *
     * @param url The url to navigate to
     */
    public void navigateToPage(String url) {
        LOGGER.info("navigateToPage started with: " + url);
        try {
            driver.getUrl(url);
            driver.waitForPageToLoad();
        } catch (Exception e) {
            Assert.fail("FAIL: Navigating to page with url: " + url + ". FAILED with error: " + e);
        }
        LOGGER.info("navigateToPage completed with: " + url);
    }

    /**
     * Selects image with the passed in 'alt' attribute
     *
     * @param altText Text contained in the 'alt' attribute
     */
    public void selectImageWithAltText(String altText) {
        LOGGER.info("selectImageWithAltText started");
        WebElement image = driver.getElementWithAttribute(imgTagBy, Constants.ATTRIBUTE_ALT, altText);
        image.click();
        LOGGER.info("selectImageWithAltText completed");
    }
}