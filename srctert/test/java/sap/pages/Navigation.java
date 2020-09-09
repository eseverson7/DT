package sap.pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

public class Navigation {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(Navigation.class.getName());

    public Navigation(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "M0:D:10::okcd")
    private static WebElement commandField;

    @FindBy(id = "RCua2OldToolbar-itms")
    private static WebElement navBar;

    @FindBy(id = "M0:D:10::btn[15]")
    private static WebElement logOffIcon;

    @FindBy(id = "M0:D:10::btn[11]")
    private static WebElement saveIcon;

    @FindBy(id = "M0:D:10::btn[86]")
    private static WebElement printIcon;

    @FindBy(id = "M0:D:10::btn[71]")
    private static WebElement findIcon;

    @FindBy(id = "M0:D:10::btn[84]")
    private static WebElement findNextIcon;

    @FindBy(id = "M0:D:10::btn[80]")
    private static WebElement firstPageIcon;

    @FindBy(id = "M0:D:10::btn[81]")
    private static WebElement previousPageIcon;

    @FindBy(id = "M0:D:10::btn[82]")
    private static WebElement nextPageIcon;

    @FindBy(id = "M0:D:10::btn[83]")
    private static WebElement lastPageIcon;

    @FindBy(id = "M0:D:10::btn[34]")
    private static WebElement userMenuIcon;

    @FindBy(id = "M0:D:10::btn[35]")
    private static WebElement sapMenuIcon;

    @FindBy(id = "M0:D:10::btn[36]")
    private static WebElement sapBusinessWorkspaceIcon;

    @FindBy(id = "M0:D:10::btn[42]")
    private static WebElement addToFavoritesIcon;

    @FindBy(id = "M0:D:13::btn[14]")
    private static WebElement removeFavoriteIcon;

    @FindBy(id = "M0:D:13::btn[39]")
    private static WebElement changeFavorites;

    @FindBy(id = "M0:D:13::btn[38]")
    private static WebElement moveFavoritesUpIcon;

    @FindBy(id = "M0:D:13::btn[37]")
    private static WebElement moveFavoritesDownIcon;

    @FindBy(id = "M0:D:13::btn[3]")
    private static WebElement backIcon;

    @FindBy(id = "M0:D:13::btn[12]")
    private static WebElement cancelIcon;

    @FindBy(id = "M0:D:13::btn[5]")
    private static WebElement overviewIcon;

    @FindBy(id = "M0:D:13::btn[6]")
    private static WebElement headerDetailIcon;

    @FindBy(id = "M0:D:13::btn[26]")
    private static WebElement copyDocumentIcon;

    private static final String LOGOFF = "Log Off";
    private static final String SAVE = "Save";
    private static final String PRINT = "Print";
    private static final String FIND = "Find";
    private static final String FIND_NEXT = "Find Next";
    private static final String FIRST_PAGE = "First Page";
    private static final String PREVIOUS_PAGE = "Previous Page";
    private static final String NEXT_PAGE = "Next Page";
    private static final String LAST_PAGE = "Last Page";
    private static final String USER_MENU = "User Menu";
    private static final String SAP_MENU = "SAP Menu";
    private static final String SAP_BUSINESS_WORKSPACE = "SAP Business Workspace";
    private static final String ADD_TO_FAVORITES = "Add To Favorites";
    private static final String REMOVE_FAVORITE = "Remove Favorite";
    private static final String CHANGE_FAVORITES = "Change Favorites";
    private static final String MOVE_FAVORITES_UP = "Move Favorites Up";
    private static final String MOVE_FAVORITES_DOWN = "Move Favorites Down";
    private static final String BACK = "Back";
    private static final String CANCEL = "Cancel";
    private static final String OVERVIEW = "Overview";
    private static final String HEADER_DETAILS = "Header Details";
    private static final String COPY_DOCUMENT = "Copy Document";

    /**
     * Calls returnIconToClick with string to find icon in header, then clicks on the returned result
     *
     * @param icon String passed in a step level of Icon to click. Valid options listed as strings variables at top
     */
    public void clickNavIcon(String icon) {
        LOGGER.info("clickNavIcon started");
        driver.waitForElementClickable(navBar);
        returnIconToClick(icon).click();
        LOGGER.info("clickNavIcon completed");
    }

    /**
     * Compares passed string to cases of WebElements based on strings listed at top of class. Hits an Assert Fail if
     * no matching case can be found to stop the test.
     *
     * @param icon string comparator passed from step level of what is to be clicked
     * @return WebElement matching the case for string comparator.
     */
    private WebElement returnIconToClick(String icon) {
        LOGGER.info("returnIconToClick started");
        switch (icon) {
            case LOGOFF:
                return logOffIcon;
            case PRINT:
                return printIcon;
            case FIND:
                return findIcon;
            case FIND_NEXT:
                return findNextIcon;
            case FIRST_PAGE:
                return firstPageIcon;
            case PREVIOUS_PAGE:
                return previousPageIcon;
            case NEXT_PAGE:
                return nextPageIcon;
            case LAST_PAGE:
                return lastPageIcon;
            case USER_MENU:
                return userMenuIcon;
            case SAP_MENU:
                return sapMenuIcon;
            case SAP_BUSINESS_WORKSPACE:
                return sapBusinessWorkspaceIcon;
            case ADD_TO_FAVORITES:
                return addToFavoritesIcon;
            case REMOVE_FAVORITE:
                return removeFavoriteIcon;
            case CHANGE_FAVORITES:
                return changeFavorites;
            case MOVE_FAVORITES_UP:
                return moveFavoritesUpIcon;
            case MOVE_FAVORITES_DOWN:
                return moveFavoritesDownIcon;
            case BACK:
                return backIcon;
            case SAVE:
                return saveIcon;
            case CANCEL:
                return cancelIcon;
            case OVERVIEW:
                return overviewIcon;
            case HEADER_DETAILS:
                return headerDetailIcon;
            case COPY_DOCUMENT:
                return copyDocumentIcon;
            default:
                Assert.fail("FAIL: Could not find icon that matched string passed from step");
                return null;
        }
    }

    /**
     * Enters t-code into the Command Field
     *
     * @param tCode Transaction Code to search for
     */
    public void enterTCode(String tCode) {
        LOGGER.info("enterTCode started");
        driver.waitForElementClickable(commandField);
        commandField.clear();
        commandField.sendKeys(tCode);
        commandField.sendKeys(Keys.ENTER);
        LOGGER.info("enterTCode completed");
    }
}
