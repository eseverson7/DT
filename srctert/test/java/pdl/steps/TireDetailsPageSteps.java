package pdl.steps;

import common.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pdl.data.ConstantsPdl;
import pdl.pages.CommonActions;
import pdl.pages.TireDetailsPage;
import utilities.Driver;

/**
 * Created by aarora on 6/2/17.
 */
public class TireDetailsPageSteps {

    private TireDetailsPage tireDetailsPage;
    private CommonActions commonActions;

    public TireDetailsPageSteps(Driver driver) {
    	tireDetailsPage = new TireDetailsPage(driver);
        commonActions = new CommonActions(driver);
    }

    @Then("^I verify tire details page header is \"(.*?)\"$")
    public void i_verify_tire_details_page_header_is(String headerText) throws Throwable {
    	tireDetailsPage.verifyTireDetailsPageHeader(headerText);
    }
    
	@And("^I verify the tire image and alt text is displayed on tire details page$")
	public void i_verify_tire_tmage_src_and_alt_image_text() throws Throwable {
    	tireDetailsPage.verifyTireImageSrcLinkAndAltImageTextExist();
	}
	
	@And("^I verify the tire brand \"(.*?)\" is listed on tire details page$")
	public void i_verify_tire_brand(String tireBrand) throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.productBrand, tireBrand);
	}

	@And("^I verify the tire name \"(.*?)\" is listed on tire details page$")
	public void i_verify_tire_name(String tireName) throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.productName, tireName);
	}

	@And("^I verify the front tire size \"(.*?)\" is listed on tire details page$")
	public void i_verify_front_tire_size(String frontTireSize) throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.FRONT, TireDetailsPage.tireSizeBy, frontTireSize, TireDetailsPage.TIRE_SIZE);
	}

	@And("^I verify the rear tire size \"(.*?)\" is listed on tire details page$")
	public void i_verify_rear_tire_size(String rearTireSize) throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.REAR, TireDetailsPage.tireSizeBy, rearTireSize, TireDetailsPage.TIRE_SIZE);
	}
	
	@And("^I verify the front tire item id \"(.*?)\" is listed on tire details page$")
	public void i_verify_front_tire_item_id(String frontTireItemId) throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.FRONT, TireDetailsPage.tireItemIdBy, frontTireItemId, TireDetailsPage.TIRE_ITEM_ID);
	}

	@And("^I verify the rear tire item id \"(.*?)\" is listed on tire details page$")
	public void i_verify_rear_tire_item_id(String rearTireItemId) throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.REAR, TireDetailsPage.tireItemIdBy, rearTireItemId, TireDetailsPage.TIRE_ITEM_ID);
	}
	
	@And("^I verify the front tire quantity is \"(.*?)\" on tire details page$")
	public void i_verify_front_tire_qty(String frontTireQty) throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.FRONT, TireDetailsPage.tireQtyBy, frontTireQty, TireDetailsPage.TIRE_QUANTITY);
	}

	@And("^I verify the rear tire quantity is \"(.*?)\" on tire details page$")
	public void i_verify_rear_tire_qty(String rearTireQty) throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.REAR, TireDetailsPage.tireQtyBy, rearTireQty, TireDetailsPage.TIRE_QUANTITY);
	}
	
	@And("^I verify that Add To Cart is enabled for front tires on tire details page$")
	public void i_verify_front_tire_add_to_cart_button() throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.FRONT, TireDetailsPage.tireAddToCartButtonBy, null, TireDetailsPage.ADD_TO_CART);
	}

	@And("^I verify  that Add To Cart is enabled for rear tires on tire details page$")
	public void i_verify_rear_tire_add_to_cart_button() throws Throwable {
		tireDetailsPage.verifyTireDisplayedInfo(ConstantsPdl.REAR, TireDetailsPage.tireAddToCartButtonBy, null, TireDetailsPage.TIRE_QUANTITY);
	}
	
	@And("^I verify the stopping distance ratings labels are listed on tire details page$")
	public void i_verify_stopping_distance_labels() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.wetClimateLabel, TireDetailsPage.WET_CLIMATE);
		commonActions.verifyElementText(TireDetailsPage.dryClimateLabel, TireDetailsPage.DRY_CLIMATE);
		commonActions.verifyElementText(TireDetailsPage.winterRatingLabel, TireDetailsPage.WINTER_RATING);
	}
	
	@And("^I verify the expected tire life range labels are listed on tire details page$")
	public void i_verify_expected_tire_life_range_labels() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.milesLabel, Constants.MILES);
		commonActions.verifyElementText(TireDetailsPage.yearsMonthsLabel, TireDetailsPage.YEARS_MONTHS);
	}

	@And("^I verify the ride ratings labels are listed on tire details page$")
	public void i_verify_ride_ratings_labels() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.rideHandlingLabel, TireDetailsPage.HANDLING);
		commonActions.verifyElementText(TireDetailsPage.quietRideLabel, TireDetailsPage.QUIET_RIDE);
		commonActions.verifyElementText(TireDetailsPage.comfortRideLabel, TireDetailsPage.COMFORT);
	}
	
	@And("^I verify the cost rating miles per dollar label is listed on tire details page$")
	public void i_verify_cost_miles_per_dollar_label() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.milesPerDollarLabel, TireDetailsPage.MILES_PER_DOLLAR);
	}
	
	@And("^I verify distance to stop ratings are in 0 to \"(.*?)\" range on tire details page$")
	public void i_verify_distance_to_stop_ratings_range(String maxRange) throws Throwable {
		tireDetailsPage.verifyProductDistanceToStopRatingsRange(maxRange);
	}
	
	@And("^I verify ride ratings are in 0 to \"(.*?)\" range on tire details page$")
	public void i_verify_ride_ratings_range(String maxRange) throws Throwable {
		tireDetailsPage.verifyProductRideRatingsRange(maxRange);
	}
	
	@And("^I verify Distance To Stop section header is present on tire details page$")
	public void i_verify_distance_to_stop_section_header_is_present() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.distanceToStopHeader, TireDetailsPage.HEADER_DISTANCE_TO_STOP);
	}
	
	@And("^I verify Expected Tire Life Range section header is present on tire details page$")
	public void i_verify_expected_tire_life_section_header_is_present() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.expectedTireLifeRangeHeader, TireDetailsPage.HEADER_EXPECTED_TIRE_LIFE_RANGE);
	}
	
	@And("^I verify Cost section header is present on tire details page$")
	public void i_verify_cost_section_header_is_present() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.costHeader, TireDetailsPage.HEADER_COST);
	}
	
	@And("^I verify Ride section header is present on tire details page$")
	public void i_verify_ride_section_header_is_present() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.rideHeader, TireDetailsPage.HEADER_RIDE);
	}
	
	@And("^I verify Rating label is present on tire details page$")
	public void i_verify_rating_label_is_present() throws Throwable {
		commonActions.verifyElementText(TireDetailsPage.ratingLabel, TireDetailsPage.RATING_LABEL);
	}

	@And("^I verify the tire size \"(.*?)\" is listed on tire details page$")
	public void i_verify_tire_size(String tireSize) throws Throwable {
		tireDetailsPage.verifyNonStaggeredTireDisplayInfo(TireDetailsPage.nonStagTireSizeBy, tireSize,
				TireDetailsPage.TIRE_SIZE);
	}

	@And("^I verify the tire item id \"(.*?)\" is listed on the tire details page$")
	public void i_verify_tire_item_id(String tireItemId) throws Throwable {
		tireDetailsPage.verifyNonStaggeredTireDisplayInfo(TireDetailsPage.tireItemIdBy, tireItemId,
				TireDetailsPage.TIRE_ITEM_ID);
	}

	@And("^I verify the tire quantity is \"(.*?)\" on tire details page$")
	public void i_verify_tire_quantity(String tireQuantity) throws Throwable {
		tireDetailsPage.verifyNonStaggeredTireDisplayInfo(TireDetailsPage.nonStagTireQtyBy, tireQuantity,
				TireDetailsPage.TIRE_QUANTITY);
	}
}
