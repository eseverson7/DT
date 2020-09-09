package patchtesting.pages;

import org.junit.Assert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Logger;

public class Epic {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public Epic(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy (css = ".layoutthreecolumn iframe")
    public WebElement commonIFrame;

    /**
     * Verifies video is within certain X/Y coordinates
     */
    public void verifyEpicVideoInCenter() {
        LOGGER.info("verifyEpicVideoInCenter started");
        driver.waitForPageToLoad();

        // Video coordiniates
        driver.waitForElementVisible(commonActions.commoniFrame1);
        Point iframeXCoord = commonActions.commoniFrame1.getLocation();
        double xCoordLeft = iframeXCoord.x;
        Assert.assertTrue("FAIL: EPIC video does not appear to be in center of screen.",xCoordLeft > 50);

        // Page coordinates
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double pixelsFromRight = screenWidth - (commonActions.commoniFrame1.getSize().width + iframeXCoord.x);
        Assert.assertTrue("FAIL: EPIC video does not appear to be in center of screen.",pixelsFromRight > 400);

        LOGGER.info("verifyEpicVideoInCenter completed");
    }
}
