package patchtesting.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import patchtesting.pages.CommonActions;
import patchtesting.pages.Safety;
import utilities.Driver;

/**
 * Created by eseverson on 5/21/18.
 */
public class CommonActionsSteps {

    private CommonActions commonActions;
    private Safety safety;

    public CommonActionsSteps(Driver driver) {
        commonActions = new CommonActions(driver);
        safety = new Safety(driver);
    }

    @When("^I select \"([^\"]*)\" sub-menu item under the \"([^\"]*)\" menu header option$")
    public void i_select_sub_menu_item_under_the_menu_item_header_option(String subMenuOption, String menuOption) throws Throwable {
        commonActions.selectSubMenuOption(menuOption, subMenuOption);
    }

    @And("^I click on video displayed in the middle of the screen and verify it is playing$")
    public void i_click_on_video_displayed_in_middle_of_the_screen_and_verify_it_is_playing() throws Throwable {
        commonActions.selectFirstVideoAndVerifyVideoPlaying();
    }

    @When("^I switch back to the default content window$")
    public void i_switch_back_to_the_default_content_window() throws Throwable {
        commonActions.switchToDefaultContentWindow();
    }

    @And("^I select the \"([^\"]*)\" link on the page$")
    public void i_select_the_link_on_the_page(String text) throws Throwable {
        commonActions.selectLinkFromPage(text);
    }

    @Then("^I verify the \"(?:Same|New)\" URL is displayed as \"([^\"]*)\"$")
    public void i_verify_url_is_displayed(String url) throws Throwable {
        commonActions.verifyPageURL(url);
    }

    @And("^I switch to the newly opened window$")
    public void i_switch_to_the_newly_opened_window() throws Throwable {
        commonActions.switchToNewWindowHandle();
    }

    @And("^I switch to the popup video iframe window in position \"(0|1|2)\"$")
    public void i_switch_to_the_popup_video_iframe_window(int position) throws Throwable {
        commonActions.switchToVideoContentFrame(position);
    }

    @And("^I verify the popup video is playing$")
    public void i_verify_the_popup_video_is_playing() throws Throwable {
        commonActions.verifyPopupVideoIsPlaying();
    }

    @And("^I select the large play button in the middle of the popup video$")
    public void i_select_the_large_play_button_in_middle_of_popup_video() throws Throwable {
           commonActions.largePlayBtn.click();
    }

    @And("^I verify the \"([^\"]*)\" file has downloaded to the Downloads folder$")
    public void i_verify_the_file_has_downloaded_to_the_downloads_folder(String fileNameExtension) throws Throwable {
        commonActions.isFileDownloaded(fileNameExtension);
    }

    @Then("^I remove the \"([^\"]*)\" from the computer$")
    public void i_remove_the_file_name_from_the_computer(String fileName) throws Throwable {
        commonActions.deleteDownloadedFile(fileName);
    }

    @Then("^I verify \"([^\"]*)\" is displayed on the page$")
    public void i_verify_element_is_displayed_on_the_page(String text) throws Throwable {
        commonActions.verifyElementDisplayedOnPage(text);
    }

    @When("^I navigate to the \"([^\"]*)\"$")
    public void i_navigate_to_the_url(String url) throws Throwable {
        commonActions.navigateToPage(url);
    }

    @When("^I select the image with alt text of \"([^\"]*)\"$")
    public void i_select_the_image_with_alt_text(String altText) throws Throwable {
        commonActions.selectImageWithAltText(altText);
    }
}