package patchtesting.steps;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import patchtesting.pages.CommonActions;
import patchtesting.pages.DTU;
import utilities.Driver;

import java.util.logging.Logger;

public class DTUSteps {

    private DTU dtu;
    private WebDriver webDriver;
    private CommonActions commonActions;
    private final Logger LOGGER = Logger.getLogger(CommonActions.class.getName());

    public DTUSteps(Driver driver) {
        dtu = new DTU(driver);
        commonActions = new CommonActions(driver);
        PageFactory.initElements(webDriver, this);
    }


    @And("^I select the video element in the center of the DTU screen$")
    public void i_select_the_video_element_in_center_of_dtu_screen() throws Throwable {
        dtu.selectDTUMovieElement();
    }

}
