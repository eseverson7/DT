package patchtesting.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.List;
import java.util.logging.Logger;

public class Safety {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public Safety(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy (className = "sss")
    public static WebElement imageSliderClass;

    @FindBy(css = ".lBox-container iframe")
    public static WebElement iframe1;

    @FindBy(className = "lightbox-close")
    public static WebElement videoCloseBtn;

    public static final By imgBy = By.tagName("img");

    public static final String src = "src";
    public static final String IAM = "iAm";

    /**
     * Grabs elements from image slider and selects the correct index
     */
    public void selectVideoFromImageSlider() {
        LOGGER.info("selectVideoFromImageSlider started");
        List<WebElement> images = imageSliderClass.findElements(imgBy);
        for (WebElement image : images) {
            if (image.getAttribute(src).contains(IAM)) {
                driver.waitForElementClickable(image);
                image.click();
                break;
            }
        }
        LOGGER.info("selectVideoFromImageSlider completed");
    }

    /**
     *  Gets coordinates from iframe surrounding the video player.
     *  Verifies that
     */
    public void verifyVideoInCenterOfScreen() {
        LOGGER.info("verifyVideoInCenterOfScreen started");
        driver.waitForPageToLoad();

        // Video coordiniates
        driver.waitForElementVisible(iframe1);
        Point iframeXCoord = iframe1.getLocation();
        double xCoordLeft = iframeXCoord.x;
        Assert.assertTrue("FAIL: Safety video does not appear to be in center of screen.",xCoordLeft > 350);

        // Page coordinates
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double pixelsFromRight = screenWidth - (iframe1.getSize().width + iframeXCoord.x);
        Assert.assertTrue("FAIL: Safety video does not appear to be in center of screen.",pixelsFromRight > 350);

        LOGGER.info("verifyVideoInCenterOfScreen completed");
    }

    /**
     * Verifies video is playing on the screen
     */
    public void closeCurrentVideo() {
        LOGGER.info("closeCurrentVideo started");
        videoCloseBtn.click();
        LOGGER.info("closeCurrentVideo completed");
    }
}