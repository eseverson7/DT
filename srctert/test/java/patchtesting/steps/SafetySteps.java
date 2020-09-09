package patchtesting.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import patchtesting.pages.Safety;
import utilities.Driver;

/**
 * Created by eseverson on 5/21/18.
 */
public class SafetySteps {

    private Safety safety;

    public SafetySteps(Driver driver) { safety = new Safety(driver); }

    @And("^I select the video display box under the word SAFETY$")
    public void i_select_the_video_display_box_under_the_word_safety() throws Throwable {
        safety.selectVideoFromImageSlider();
    }

    @Then("^I verify the video appears on the center of screen$")
    public void i_verify_the_video_appears_on_the_center_of_screen() throws Throwable {
        safety.verifyVideoInCenterOfScreen();
    }

    @When("^I close the currently playing video$")
    public void i_close_the_currently_playing_video() throws Throwable {
        safety.closeCurrentVideo();
    }
}