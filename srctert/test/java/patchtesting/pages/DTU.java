package patchtesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.Logger;

public class DTU {

    private Driver driver;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public DTU(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "leader-out")
    public WebElement dtuMovie;

    /**
     * Selects the movie element on the DTU page
     */
    public void selectDTUMovieElement() {
        LOGGER.info("selectDTUMovieElement started");
        dtuMovie.click();
        LOGGER.info("selectDTUMovieElement completed");
    }
}
