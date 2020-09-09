package dtc.steps;

import common.Config;
import common.Constants;
import dtc.data.ConstantsDtc;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtc.pages.HeaderPage;
import dtc.pages.HomePage;
import dtc.pages.MobileHeaderPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class HomePageSteps {

    private HomePage homePage;
    private StoreLocatorPageSteps storeLocatorPageSteps;
    private HeaderPage headerNavigationPage;
    private MobileHeaderPage mobileHeaderPage;

    public HomePageSteps(Driver driver) {
        homePage = new HomePage(driver);
        headerNavigationPage = new HeaderPage(driver);
        storeLocatorPageSteps = new StoreLocatorPageSteps(driver);
        headerNavigationPage = new HeaderPage(driver);
        mobileHeaderPage = new MobileHeaderPage(driver);
    }

    //TODO: This should be refactored to be a commonActions method in order to leverage driver methods and it's
    //TODO (cont) become inappropriate for a step definition method
    @Given("^I change to the default store$")
    public void i_change_to_the_default_store() throws Throwable {
        String storeCity = Config.getDefaultStoreCity();
        String storeCode = Config.getDefaultStore();
        String dataSet = Config.getDataSet();
        String region = Config.getSiteRegion();

        if (!region.equals(ConstantsDtc.DTD)) {
            //NOTE:  Added a workaround / hack to change the store without taxing the GEO IP licenses
            storeLocatorPageSteps.i_navigate_and_change_my_store_through_url();

            //TODO: Following steps have been commented out to reduce the consumption of GEO IP licenses
            //TODO: Also, these steps will result in failure when GEO IP API is unavailable
            //storeLocatorPageSteps.i_search_for_store_within_miles_of("25", storeCity);
            //commonActions.checkForWelcomePopup();
            //storeLocatorPageSteps.i_select_make_this_my_store(storeCode);

            if (Config.isMobilePhone()) {
                mobileHeaderPage.clickSiteLogo();
            } else {
                headerNavigationPage.clickSiteLogo();
            }

            //NOTE: clicking site logo doesn't stick with Safari / Mobile Safari
            //TODO: FF here due to intermittent failures with clickSiteLogo but may hang on stg (gecko)
            //TODO: Should change to isElementDisplayed referencing Store Locator page
            if (Config.isSafari() || Config.isIpad() || Config.isIphone() || Config.isFirefox())
                homePage.goToHome();
        } else {
            homePage.goToHome();
        }
    }

    @Given("^I go to the homepage$")
    public void i_go_to_the_homepage() throws Throwable {
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)) {
            homePage.goToHome();
        } else {
            if (Config.getDataSet().equalsIgnoreCase(Constants.QA)) {
                if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
                    storeLocatorPageSteps.i_change_to_scottsdale_store_through_url();
                    homePage.goToHome();
                } else {
                    i_change_to_the_default_store();
                }
            } else {
                homePage.goToHome();
            }
        }
    }

    @When("^I open the fitment popup$")
    public void i_open_the_fitment_popup() throws Throwable {
        if (Config.isMobilePhone()) {
            mobileHeaderPage.openMobileMenu();
            mobileHeaderPage.clickMenuLink(MobileHeaderPage.addVehicleString);
        } else {
            homePage.openFitmentPopup();
        }
    }

    @When("^I open the My Vehicles popup$")
    public void i_open_the_my_vehicles_popup() throws Throwable {
        if (Config.isMobilePhone()) {
            mobileHeaderPage.openMobileMenu();
        } else {
            homePage.openMyVehiclesPopup();
        }
    }

    @When("^I select Add Vehicle$")
    public void i_select_add_vehicle() throws Throwable {
        homePage.clickAddVehicle();
    }

    @When("^I remove my current vehicle$")
    public void i_remove_my_current_vehicle() throws Throwable {
        homePage.removeCurrentVehicle();
    }

    @And("^I remove my recent vehicle$")
    public void i_remove_my_recent_vehicle() throws Throwable {
        homePage.removeRecentVehicle();
    }

    @Then("^I should see no vehicle$")
    public void i_should_see_no_vehicle() throws Throwable {
        homePage.assertNoVehiclesInMyVehicles();
    }

    @And("^I verify the default \"([^\"]*)\" in the popup$")
    public void i_verify_the_default_in_the_popup(String storeAddress) throws Throwable {
        homePage.verifyMyStoreDetails(storeAddress);
        homePage.clickGlobalMyStoreHeader();
    }

    @Then("^I click on Store details button in My Store popup$")
    public void i_click_on_store_details_button_in_my_store_popup_page() throws Throwable {
        homePage.openStoreDetails();
    }

    @When("^I click on \"My Store\" title$")
    public void i_click_on_my_store_title() throws Throwable {
        homePage.clickGlobalMyStoreHeader();
    }

    @When("^I select installers menu option$")
    public void i_click_on_installers_menu_option() throws Throwable {
        homePage.clickInstallersMenu();
    }

    @When("^I select mini cart and \"(Continue Shopping|View Cart)\"$")
    public void i_select_mini_cart_icon_and(String action) throws Throwable {
        if (action.contains(ConstantsDtc.CONTINUE)) {
            homePage.openMiniCartPopupAndContinueShopping();
        } else
            homePage.openMiniCartPopupAndViewCart();
    }

    @When("^I schedule an appointment for my current store$")
    public void i_schedule_appointment_for_current_store() throws Throwable {
        if (Config.isMobilePhone()) {
            mobileHeaderPage.openMobileMenu();
            mobileHeaderPage.clickMenuLink(MobileHeaderPage.scheduleAppointmentMenuString);
        } else {
            homePage.openScheduleAppointment();
        }
    }

    @When("^I open the Store Locator page$")
    public void i_open_the_store_locator_page() throws Throwable {
        if (Config.isMobilePhone()) {
            mobileHeaderPage.clickHeaderLink(MobileHeaderPage.findAStoreHeaderString);
        } else {
            homePage.openChangeStore();
        }
    }

    @When("^I do a free text search for \"(.*?)\"$")
    public void i_do_a_free_text_search_for(String value) throws Throwable {
        if (Config.isMobilePhone()) {
            mobileHeaderPage.searchItem(value);
        } else {
            homePage.searchItem(value);
        }
    }

    @When("^I do a free text search for \"([^\"]*)\" and hit enter")
    public void i_do_a_free_text_search_and_hit_enter(String value) throws Throwable {
        if (Config.isMobile()) {
            mobileHeaderPage.searchItem(value);
        } else {
            homePage.searchItemHitEnter(value);
        }
    }

    @Then("^I verify My Vehicle in the header displays as \"(.*?)\"$")
    public void i_verify_my_vehicle_in_header(String vehicle) throws Throwable {
        homePage.verifyMyVehicle(vehicle);
    }

    @Then("^I verify the header cart total is \"(.*?)\"$")
    public void i_verify_header_cart_item_total(String total) throws Throwable {
        homePage.verifyCartTotal(total);
    }

    @Then("^I verify the header cart item count is \"(.*?)\"$")
    public void i_verify_header_cart_item_count(String count) throws Throwable {
        homePage.verifyCartItemCount(count);
    }

    @Then("^I open mini cart and verify the item qty is \"(.*?)\"$")
    public void i_verify_the_mini_cart_modal_item_qty(String qty) throws Throwable {
        homePage.assertItemQtyMiniCartModal(qty);
    }

    @Then("^I verify the \"STORE HOURS\" in the My Store popup$")
    public void i_verify_store_hours_my_store_popup() throws Throwable {
        homePage.verifyMyStoreHours();
    }

    @Then("^I am brought to the homepage$")
    public void i_verify_i_am_on_homepage() throws Throwable {
        homePage.verifyHomepage();
    }

    @Then("^I verify the site logo$")
    public void i_verify_the_site_logo() throws Throwable {
        homePage.verifySiteLogo();
    }

    @When("^I select mini cart$")
    public void i_select_mini_cart() throws Throwable {
        homePage.openMiniCart();
    }
    
    @Then("^I verify Join/Sign-in is displayed on homepage$")
    public void i_verify_the_join_sign_in_is_displayed() throws Throwable {
        homePage.verifyJoinSignInIsDisplayed();
    }
    
    @When("^I select Join/Sign-in$")
    public void i_select_join_sign_in() throws Throwable {
        homePage.clickJoinSignInLink();
    }
    
    @When("^I select checkout with appointment$")
    public void i_select_checkout_with_appointment() throws Throwable {
    	homePage.clickCheckoutWithAppointmentButton();
    }
    
    @When("^I select my profile and \"(Sign out|View My Account)\"$")
    public void i_select_my_profile_and(String action) throws Throwable {
        if (action.contains(ConstantsDtc.SIGN_OUT)) {
            homePage.openProfilePopoverAndSignOut();
        } else
            homePage.openProfilePopoverAndViewMyAccount();
    }

    @Then("^I verify the \"My Store\" popup contains controls: \"CHANGE STORE, STORE DETAILS, SCHEDULE APPOINTMENT\"$")
    public void i_verify_my_store_popup_contains_controls() throws Throwable {
        homePage.verifyMyStoreContainsControls();
    }

    @And("^I verify the Wheel Configurator \"([^\"]*)\" is displayed$")
    public void i_verify_the_wheel_configurator_text_is_displayed(String text) throws Throwable {
        homePage.assertWheelConfiguratorTextIsDisplayed(text);
    }

    @And("^I verify the Wheel Configurator image is displayed$")
    public void i_verify_the_wheel_configurator_image_is_displayed() throws Throwable {
        homePage.assertWheelConfiguratorImageIsDisplayed();
    }

    @And("^I verify 'BROWSE WHEELS' is displayed$")
    public void i_verify_browse_wheels_is_displayed() throws Throwable {
        homePage.assertBrowseWheelsIsDisplayed();
    }
}