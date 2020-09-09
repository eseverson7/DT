package patchtesting.steps;

import cucumber.api.java.en.Then;
import patchtesting.pages.CommonActions;
import patchtesting.pages.Epic;
import utilities.Driver;

public class EpicSteps {

    private Epic epic;
    private CommonActions commonActions;

    public EpicSteps(Driver driver) {
        epic = new Epic(driver);
        commonActions = new CommonActions(driver);
    }

    @Then("^I verify EPIC video appears on the center of screen$")
    public void i_verify_epic_video_appears_on_center_of_screen() throws Throwable {
        epic.verifyEpicVideoInCenter();
    }
}
