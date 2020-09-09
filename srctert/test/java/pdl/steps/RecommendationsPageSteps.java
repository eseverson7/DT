package pdl.steps;

import cucumber.api.java.en.Then;
import org.junit.Assert;

import cucumber.api.java.en.And;
import pdl.pages.CommonActions;
import pdl.pages.RecommendationsPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 4/25/17.
 */
public class RecommendationsPageSteps {

    private RecommendationsPage recommendationsPage;
    private CommonActions commonActions;

    public RecommendationsPageSteps(Driver driver) {
        recommendationsPage = new RecommendationsPage(driver);
        commonActions = new CommonActions(driver);
    }

	@And("^I verify the vehicle year is set to \"(.*?)\" at upper left header$")
	public void i_verify_vehicle_year_at_upper_left_header(String year) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.leftHeaderVehicleYear, year);
	}

	@And("^I verify the vehicle make is set to \"(.*?)\" at upper left header$")
	public void i_verify_vehicle_make_at_upper_left_header(String make) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.leftHeaderVehicleMake, make);
	}

	@And("^I verify the vehicle model is set to \"(.*?)\" at upper left header$")
	public void i_verify_vehicle_model_at_upper_left_header(String model) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.leftHeaderVehicleModel, model);
	}

	@And("^I verify the vehicle trim is set to \"(.*?)\" at upper left header$")
	public void i_verify_vehicle_trim_at_upper_left_header(String trim) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.leftHeaderVehicleTrim, trim);
	}

	@And("^I verify the summary zip code is set to \"(.*?)\"$")
	public void i_verify_summary_zip(String zipcode) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.summaryZipcode, zipcode);
	}

	@And("^I verify the summary car is set to \"(.*?)\"$")
	public void i_verify_summary_car(String car) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.summaryCar, car);
	}

	@And("^I verify the summary Miles Driven is set to \"(.*?)\"$")
	public void i_verify_summary_miles(String milesDriven) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.summaryMilesDriven, milesDriven);
	}

	@And("^I verify the summary Tire Size is set to \"(.*?)\"$")
	public void i_verify_summary_tire_size(String tireSize) throws Throwable {
		commonActions.verifyElementText(RecommendationsPage.summaryTireSize, tireSize);
	}

	@And("^I verify the tire brand \"(.*?)\" is listed on results page$")
	public void i_verify_tire_brand(String tireBrand) throws Throwable {
		commonActions.verifyElementTextInTheList(RecommendationsPage.productBrandBy, tireBrand);
	}

	@And("^I verify the tire name \"(.*?)\" is listed on results page$")
	public void i_verify_tire_name(String tireName) throws Throwable {
		commonActions.verifyElementTextInTheList(RecommendationsPage.productNameBy, tireName);
	}

	@And("^I verify the front tire size \"(.*?)\" is listed on results page$")
	public void i_verify_front_tire_size(String tireSizeFront) throws Throwable {
		commonActions.verifyElementTextInTheList(RecommendationsPage.productFrontTireSizeBy, tireSizeFront);
	}

	@And("^I verify the rear tire size \"(.*?)\" is listed on results page$")
	public void i_verify_rear_tire_size(String tireSizeRear) throws Throwable {
		commonActions.verifyElementTextInTheList(RecommendationsPage.productRearTireSizeBy, tireSizeRear);
	}

	@And("^I verify the \"(wet climate stop|expected tire life)\" label is listed only once on results page$")
	public void i_verify_ratings_element(String ratings) throws Throwable {
		if (RecommendationsPage.WET_CLIMATE_STOP.toLowerCase().contains(ratings)) {
			recommendationsPage.verifyRatingsElementText(RecommendationsPage.wetClimateLabelTextEle,
					RecommendationsPage.WET_CLIMATE_STOP);
			recommendationsPage
					.verifyRatingsElementOccurrenceOnRecommendationPage(RecommendationsPage.wetClimateLabelTextBy, 1);
		} else if (RecommendationsPage.EXPECTED_TIRE_LIFE.toLowerCase().contains(ratings)) {
			recommendationsPage.verifyRatingsElementText(RecommendationsPage.expectedLifeLabelTextEle,
					RecommendationsPage.EXPECTED_TIRE_LIFE);
			recommendationsPage.verifyRatingsElementOccurrenceOnRecommendationPage(
					RecommendationsPage.expectedLifeLabelTextBy, 1);
		}
	}

	@And("^I verify all View Details buttons are enabled on results page$")
	public void i_verify_all_view_details_buttons() throws Throwable {
		recommendationsPage.verifyViewDetailsButtonIsEnabledForAllProductItems();
	}

	@And("^I verify the item stock status label is present for all the product items on results page$")
	public void i_verify_item_stock_label_is_present_for_all_items() throws Throwable {
		recommendationsPage.verifyItemStockStatusLabelIsPresentForAllProductItems();
	}

	@And("^I verify the item qty stock status label is present for all the product items on results page$")
	public void i_verify_item_qty_stock_label_is_present_for_all_items() throws Throwable {
		recommendationsPage.verifyItemQtyStatusLabelIsPresentForAllProductItems();
	}

	@And("^I verify the \"(Typical|performance)\" summary Driving Priorities$")
	public void i_verify_summary_driving_priorities(String drivingPriority) throws Throwable {
		commonActions.verifyDrivingPriorities(drivingPriority, true);
	}

    @And("^I verify there are \"(.*?)\" results listed$")
    public void i_verify_result_count(String count) throws Throwable {
        recommendationsPage.verifyResultsCount(Integer.parseInt(count));
    }

    @And("^I verify that the Top Recommendation ribbon is displayed only for the first tire in the list$")
    public void i_verify_top_recommendation_ribbon() throws Throwable {
        recommendationsPage.verifyTopRecommendationRibbon();
    }

    @And("^I select the product checkbox at position \"(.*?)\" from the products list results$")
    public void i_select_product_checkbox_at(String position) throws Throwable {
        recommendationsPage.selectProductCheckboxAtPosition(Integer.parseInt(position));
    }
    
    @And("^I select the product checkbox for item \"(.*?)\" from the products list results$")
    public void i_select_product_checkbox_for_item(String itemId) throws Throwable {
        recommendationsPage.selectProductCheckbox(itemId);
    }

    @And("^I select view details for the tire \"(.*?)\" from the results page$")
    public void i_select_view_details_button_of_item(String tireName) throws Throwable {
        recommendationsPage.clickViewDetailsButton(tireName);
    }
    
    @And("^I select the first available view details from the results page$")
    public void i_select_the_first_available_view_details_button() throws Throwable {
        recommendationsPage.clickFirstViewDetailsButton();
    }
    
    @And("^I select the compare tires button$")
    public void i_select_compare_tires_button() throws Throwable {
        recommendationsPage.clickCompareTiresButton();
    }

	@And("^I select the filter results button$")
	public void i_select_filter_results_button() throws Throwable {
		recommendationsPage.clickFilterResultsButton();
	}

    @And("^I verify that compare tires button is \"(enabled|disabled)\" on results page$")
    public void i_verify_compare_tires_button_status_is(String status) throws Throwable {
		recommendationsPage.verifyCompareTiresButtonStatus(status);
    }
    
    @And("^I verify the status of product checkbox at position \"(.*?)\" is \"(enabled|disabled)\"$")
    public void i_verify_product_checkbox_status_at(String position, String status) throws Throwable {
        recommendationsPage.verifyProductCheckboxStatusAtPosition(Integer.parseInt(position), status);
    }
    
    @And("^I verify the status of product checkbox for item \"(.*?)\" is \"(enabled|disabled)\"$")
    public void i_verify_product_checkbox_status_for_item(String itemId, String status) throws Throwable {
        recommendationsPage.VerifyProductCheckboxStatusForItem(itemId, status);
    }
    
    @And("^I verify all tire images are on results page$")
    public void i_verify_all_tire_images_are_on_results_page() throws Throwable {
    	recommendationsPage.verifyProductImagePresentForAllItems();
    }
    
    @And("^I verify the Driving Details is displayed on the page$")
    public void i_verify_the_driving_details_is_displayed_on_the_page() throws Throwable {
    	recommendationsPage.assertDrivingDetailsElementIsPresent();
    	commonActions.verifyElementText(RecommendationsPage.drivingDetails, RecommendationsPage.DRIVING_DETAILS_LABEL);
    }
    
	@And("^I verify the product article number label is present for all the product items on results page$")
	public void i_verify_item_number_label_is_present_for_all_items() throws Throwable {
		recommendationsPage.verifyProductArticleNumberLabelIsPresentForAllProductItems();
	}

	@Then("^I verify that only tires of brand \"(.*?)\" are listed in results$")
	public void i_verify_brand_in_results(String brand) throws Throwable {
		recommendationsPage.verifyBrandInResults(brand);
	}

	@And("^I verify that only item codes \"(.*?)\" are listed in results$")
	public void i_verify_item_codes_in_results(String itemCodes) throws Throwable {
		recommendationsPage.verifyItemCodesInResults(itemCodes);
	}

	@And("^I verify the item code \"(.*?)\" is listed on results page$")
	public void i_verify_item_code(String itemCode) throws Throwable {
		commonActions.verifyElementTextInTheList(RecommendationsPage.productArticleNumberBy, itemCode);
	}
}
