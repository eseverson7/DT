package dtc.steps;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.HomePage;
import dtc.pages.MobileHeaderPage;
import dtc.pages.StoreLocatorPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/2016.
 */
public class StoreLocatorPageSteps {

    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private MobileHeaderPage mobileHeaderPage;

    public StoreLocatorPageSteps(Driver driver) {
        homePage = new HomePage(driver);
        mobileHeaderPage = new MobileHeaderPage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @When("^I search for store within \"(10|25|50|75)\" miles of \"(.*?)\"$")
    public void i_search_for_store_within_miles_of(String searchRange, String cityStateOrZip) throws Throwable {
        String siteRegion = Config.getSiteRegion().toLowerCase();
        if (siteRegion.equalsIgnoreCase(ConstantsDtc.DT) || siteRegion.equalsIgnoreCase(ConstantsDtc.AT)) {
            if (Config.isMobile()) {
                // TODO: Need to re-test on physical android device. potential
                // issue on found on emulators which
                // TODO: (cont) were unstable and were giving suspect results.
                if (Config.isAndroidPhone() || Config.isAndroidTablet()) {
                    mobileHeaderPage.openMobileMenu();
                    mobileHeaderPage.clickMenuLink("Find a Store");
                } else {
                    mobileHeaderPage.clickHeaderLink(MobileHeaderPage.findAStoreHeaderString);
                }
            } else {
                homePage.openChangeStore();
            }
        } else if (siteRegion.contains(ConstantsDtc.DTD)) {
            homePage.clickInstallersMenu();
        }
        storeLocatorPage.searchForStore(searchRange, cityStateOrZip);
    }

    @Then("^I should be redirected to the site for \"(America's Tire|Discount Tire)\"$")
    public void i_should_be_redirected_to_the_site_for(String siteToValidate) throws Throwable {
        storeLocatorPage.confirmPopUpForSite(siteToValidate);
        storeLocatorPage.closeWelcomePopUp(ConstantsDtc.CONTINUE);
        storeLocatorPage.confirmStoreFoundForSite(siteToValidate);
    }

    @And("^I select make this \"(.*?)\" my store$")
    public void i_select_make_this_my_store(String store) throws Throwable {
        if (!storeLocatorPage.currentStoreSelected && Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            storeLocatorPage.currentStoreSelected = true;
        } else {
            //TODO: Temporary fix to deal with Safari caching issue for iOS and local Safari
            if ((Config.isSafari() || Config.isIphone() || Config.isIpad()) &&
                    !storeLocatorPage.isMyStoreSelected()) {
                storeLocatorPage.clickMakeThisMyStoreButton(store);
            } else if (!Config.isSafari() && !Config.isIphone() && !Config.isIpad()) {
                storeLocatorPage.clickMakeThisMyStoreButton(store);
            }
        }
    }

    @Then("^I should see my store as current store$")
    public void i_see_my_store_as_current_store() throws Throwable {
        storeLocatorPage.confirmCurrentStoreTextPresent();
    }

    @When("^I change to the default store through url$")
    public void i_navigate_and_change_my_store_through_url() throws Throwable {
        storeLocatorPage.navigateToMyStoreThroughUrl();
        storeLocatorPage.clickMakeThisMyStoreButton();
    }

    @When("^I change to the Scottsdale store through url$")
    public void i_change_to_scottsdale_store_through_url() throws Throwable {
        storeLocatorPage.navigateToScottsdaleStoreThroughUrl();
        storeLocatorPage.clickMakeThisMyStoreButton();
    }

    @When("^I change to the store with url \"(.*?)\"$")
    public void i_navigate_to_store_path_and_change_my_store(String path) throws Throwable {
        storeLocatorPage.navigateToMyStoreThroughUrlPath(path);
        storeLocatorPage.clickMakeThisMyStoreButton();
    }

    @When("^I select \"(Send to phone|Schedule appointment|Make this my store|Directions|Read reviews)\" for store #\"([1-9][0-9]*)\" in the store location results$")
    public void i_select_action_for_store_on_store_locator_results(String action, String storeResultNumber) throws Throwable {
        storeLocatorPage.selectActionForStoreLocationResultItem(action, storeResultNumber);
    }

    @Then("^I should see a prompt for \"(America's Tire|Discount Tire|Discount Tire Direct)\"$")
    public void i_should_see_a_prompt_for(String siteToValidate) throws Throwable {
        storeLocatorPage.confirmPopUpForSite(siteToValidate);
    }

    @When("^I select \"(Continue|Search in another area)\"$")
    public void i_select(String action) throws Throwable {
        storeLocatorPage.interactWithStorePopUp(action);
    }

    @Then("^I verify the Current Store text color is Blue$")
    public void i_verify_the_current_store_text_color_is_blue() throws Throwable {
        storeLocatorPage.assertCurrentStoreTextColor();
    }

    @Then("^I should see this partner installer \"(.*?)\" is present in the displayed stores list$")
    public void i_should_see_partner_installer_is_displayed(String siteToValidate) throws Throwable {
        storeLocatorPage.confirmStoreFoundForSite(siteToValidate);
    }

    @Then("^I should confirm schedule appointment option is not available to users on DTD store locator page$")
    public void i_should_confirm_schedule_appointment_option_is_not_available() throws Throwable {
        storeLocatorPage.assertScheduleAppointmentOptionNotAvailableOnDTDStoreLocatorPage();
    }

    @Then("^I verify the \"HOURS OF OPERATION\" in the store location results$")
    public void i_verify_hours_of_operation_for_store_search_results() throws Throwable {
        storeLocatorPage.verifyHoursOfOperationForStoreSearchResults();
    }

    @When("^I select \"(.*?)\" for store details$")
    public void i_select_store_result_item_for_store_details(String storeLinkText) throws Throwable {
        storeLocatorPage.selectStoreForStoreDetails(storeLinkText);
    }

    @When("^I search for stores within \"(10|25|50|75)\" miles of non default zip code$")
    public void i_search_for_stores_within_miles_of_non_default_zip_code(String searchRange) throws Throwable {
        homePage.openChangeStore();
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            storeLocatorPage.searchForStore(searchRange, ConstantsDtc.ZIP_CODE_DT);
        } else {
            storeLocatorPage.searchForStore(searchRange, ConstantsDtc.ZIP_CODE_AT);
        }
    }

    @And("^I select non default store as my store$")
    public void i_select_non_default_store_as_my_store() throws Throwable {
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            i_select_make_this_my_store(ConstantsDtc.CHANGE_STORE_DT);
        } else {
            storeLocatorPage.selectActionForStoreLocationResultItem(ConstantsDtc.MAKE_THIS_MY_STORE, ConstantsDtc.STORE_DEFAULT_RESULT);
        }
    }
}