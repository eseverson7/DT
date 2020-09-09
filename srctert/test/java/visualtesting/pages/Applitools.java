package visualtesting.pages;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import common.Config;
import common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by aaronbriel on 5/3/17.
 */
public class Applitools {

    private Driver driver;
    private Eyes eyes;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(Applitools.class.getName());

    public Applitools(Driver driver) {
        this.driver = driver;
        this.eyes = new Eyes();
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    private static final String CSS_STITCHMODE = "CSS_STITCHMODE";
    private static final String STRICT_MATCHLEVEL = "STRICT_MATCHLEVEL";
    private static final String LAYOUT_MATCHLEVEL = "LAYOUT_MATCHLEVEL";
    private static final String LAYOUT2_MATCHLEVEL = "LAYOUT2_MATCHLEVEL";
    private static final String PAGE_VIEWPORT = "PAGE";
    private static final String IPHONE_VIEWPORT = Constants.IPHONE;
    private static final String IPAD_VIEWPORT = Constants.IPAD;

    /**
     * Verifies a Window with  Applitools
     *
     * @param window    The String value of the window to check
     * @param batch The Applitools Batch Name
     * @param app   The Applitools App Name
     * @param test  The Applitools Test Name
     */
    public void verifyWindowApplitools(String window, String batch, String app, String test) {
        LOGGER.info("verifyWindowApplitools started");

        eyes.setApiKey(Config.getApplitoolsApiKey());
        eyes.setMatchLevel(getMatchLevel());
        eyes.setBatch(new BatchInfo(batch));

        try {
            openEyes(app, test);
            eyes.checkWindow(window);
            LOGGER.info("verifyWindowApplitools completed");
            eyes.close(false);
        } finally {
            eyes.abortIfNotClosed();
        }
    }


    /**
     * Verifies a Region with Applitools
     *
     * @param selector  The region (selector) to check against
     * @param batch     The Applitools Batch Name
     * @param app       The Applitools App Name
     * @param test      The Applitools Test Name
     */
    public void verifyRegionApplitools(String selector, String batch, String app, String test) {
        LOGGER.info("verifyRegionApplitools started");

        eyes.setApiKey(Config.getApplitoolsApiKey());
        eyes.setForceFullPageScreenshot(true);
        eyes.setStitchMode(getStitchMode());
        eyes.setBatch(new BatchInfo(batch));

        try {
            openEyes(app, test);
            //TODO: May need to refine to allow for other locator types
            eyes.checkRegion(By.className(selector), test, true);
            LOGGER.info("verifyRegionApplitools completed");
            eyes.close(false);
        } finally {
            eyes.abortIfNotClosed();
        }
    }

    /**
     * Sets webDriver to eyes.open, dependent upon mobile vs web
     *
     * @param app   The Applitools App Name
     * @param test  The Applitools Test Name
     */
    private void openEyes(String app, String test) {
        LOGGER.info("openEyes started");
        webDriver = eyes.open(webDriver, app, test);
        LOGGER.info("openEyes completed");
    }

    /**
     * Returns View Port size depending on browser/device/panel
     *
     * @return RectangleSize The View Port size
     */
    private RectangleSize getViewportSize() {

        String viewType = PAGE_VIEWPORT;

        if (Config.isIphone()) {
            viewType = IPHONE_VIEWPORT;
        } else if (Config.isIpad()) {
            viewType = IPAD_VIEWPORT;
        }

        switch(viewType) {
            case IPHONE_VIEWPORT:
                return new RectangleSize(1820,890);
            case IPAD_VIEWPORT:
                return new RectangleSize(1024,704);
            default:
                return new RectangleSize(1820,890);
        }

    }

    /**
     * Returns Match Level, defaulting to STRICT_MATCHLEVEL
     *
     * @return RectangleSize The Match Level
     */
    private MatchLevel getMatchLevel() {
        String matchLevel = System.getProperty("applitoolsMatchLevel", STRICT_MATCHLEVEL);

        switch(matchLevel) {
            case STRICT_MATCHLEVEL:
                return MatchLevel.STRICT;
            case LAYOUT_MATCHLEVEL:
                return MatchLevel.LAYOUT;
            case LAYOUT2_MATCHLEVEL:
                return MatchLevel.LAYOUT2;
            default:
                return MatchLevel.EXACT;
        }

    }

    /**
     * Returns Stitch Mode, defaulting to CSS_STITCHMODE
     *
     * @return StitchMode The Stitch Mode to use
     */
    private StitchMode getStitchMode() {
        String stitchMode = System.getProperty("applitoolsStitchMode", CSS_STITCHMODE);

        switch(stitchMode) {
            case CSS_STITCHMODE:
                return StitchMode.CSS;
            default:
                return StitchMode.CSS;
        }
    }
   
    /**
     * Verifies a Window with  Applitools
     *
     * @param page  The String value of the window/page to check
     * @param batch The Applitools Batch Name
     * @param app   The Applitools App Name
     * @param test  The Applitools Test Name
     */
    public void verifyOvcPageApplitools(String page, String batch, String app, String test) {
        LOGGER.info("verifyOvcPageApplitools started");

        eyes.setApiKey(Config.getApplitoolsApiKey());
        eyes.setBatch(new BatchInfo(batch));

        try {
            openEyes(app, test);
            eyes.checkWindow(page);
            LOGGER.info("verifyOvcPageApplitools completed");
            eyes.close(false);
        } finally {
            eyes.abortIfNotClosed();
        }
    }
    
    /**
     * Specifies Applitools Batch ID 
     *
     * @param batch The Applitools Batch Name
     */
    public void specifyApplitoolsBatchId(String batch, String testName) {
        LOGGER.info("specifyApplitoolsBatchId started");
        
        eyes = new Eyes();
        eyes.setApiKey(Config.getApplitoolsApiKey());
        eyes.setMatchLevel(MatchLevel.STRICT);
        eyes.setBatch(new BatchInfo(batch));
        webDriver = eyes.open(webDriver, "OVC", testName);

   	    LOGGER.info("specifyApplitoolsBatchId completed");
    }
    
    /**
     * Verifies a page of application with Applitools
     *
     * @param page  The String value of the window/page to check
     */
    public void verifyOvcPageApplitools(String page) {
        LOGGER.info("verifyOvcPageApplitools started");
        eyes.checkWindow(page);
        LOGGER.info("verifyOvcPageApplitools completed");
    }
    
    /**
     * Close Applitools connections
     */
    public void closeApplitoolsConnection() {
        LOGGER.info("closeApplitoolsConnection started");
        try {
            eyes.close(false);
        } finally {
            eyes.abortIfNotClosed();
        }
        LOGGER.info("closeApplitoolsConnection completed");
    }
}