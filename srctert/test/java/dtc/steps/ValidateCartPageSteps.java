package dtc.steps;

import java.util.logging.Logger;

import common.Config;
import dtc.data.ConstantsDtc;
import common.Constants;
import cucumber.api.java.en.And;
import dtc.pages.CartPage;
import utilities.Driver;

/**
 * Created by aaronbriel on 10/24/16.
 */
public class ValidateCartPageSteps {

    private CartPage cartPage;
    private final Logger LOGGER = Logger.getLogger(ValidateCartPageSteps.class.getName());
    public ValidateCartPageSteps(Driver driver) {
        cartPage = new CartPage(driver);
    }
    
    @And("^I verify the product title \"(.*?)\" of an item on the shopping cart page$")
    public void i_verify_the_of_an_item_on_the_cart_page(String title) throws Throwable {
        cartPage.assertItemTitle(title);
    }
    
    @And("^I verify the miles manufacturer warranty \"(.*?)\" of selected item on the shopping cart page$")
    public void i_verify_miles_warranty_text_on_the_cart_page(String miles) throws Throwable {
        cartPage.assertMilesWarranty(miles);
    }
    
    @And("^I verify the max air pressure \"(.*?)\" of selected item on the shopping cart page$")
    public void i_verify_max_air_pressure_on_the_cart_page(String psi) throws Throwable {
        cartPage.assertTireMaxPressure(psi);
    }
    
    @And("^I verify the \"(Environmental Fee|Tire Disposal Fee|Certificates|TPMS Rebuild Kits)\" label present on the shopping cart page$")
	public void i_verify_text_labels_on_the_cart_page(String label) throws Throwable {
		if (ConstantsDtc.ENVIRONMENTAL_FEE.contains(label)) {
			//TODO Implement the tag based scenario / feature lookup instead of this 'if Conditional' low level check 
			if (Config.getDataSet().equalsIgnoreCase(Constants.QA)) {
				cartPage.assertEnvironmentalFeeLabelDisplayed();
			} else
				LOGGER.info("There is no environmental fee requirement for selected env store- " + Config.getDefaultStoreCity());
		} else if (ConstantsDtc.DISPOSAL_FEE.contains(label)) {
			cartPage.assertTireDisposalFeeLabelDisplayed();
		} else if (CartPage.TPMS_REBUILD_KIT.contains(label)) {
			cartPage.assertTPMSRebuildKitsLabelDisplayed();
		} else
			cartPage.assertCertificateRepairRefundReplacementLabelDisplayed();
	}
    
    @And("^I verify the environment fee amount \"(.*?)\" on the shopping cart page$")
    public void i_verify_environment_fee_amount_on_the_cart_page(String fee) throws Throwable {
		//TODO Implement the tag based scenario / feature lookup instead of this 'if Conditional' low level check 
//    	if (Config.getDefaultStore().equalsIgnoreCase(ConstantsDtc.DT_QA_DEFAULT_STORE)) {
//			cartPage.assertEnvironmentalFeeAmount(fee);
//		} else
//			LOGGER.info("There is no environmental fee requirement for selected env store - " + Config.getDefaultStoreCity());
        cartPage.assertEnvironmentalFeeAmount(fee);
    }
    
    @And("^I verify the tire disposal fee amount \"(.*?)\" on the shopping cart page$")
    public void i_verify_tire_disposal_fee_amount_on_the_cart_page(String fee) throws Throwable {
        cartPage.assertTireDisposalFeeAmount(fee);
    }
    
    @And("^I verify the certificate service fee amount \"(.*?)\" on the shopping cart page$")
    public void i_verify_certificates_service_fee_amount_on_the_cart_page(String fee) throws Throwable {
        cartPage.assertCertificateRRRFeeAmount(fee);
    }
    
    @And("^I verify the installation and spin balancing fee amount \"(.*?)\" on the shopping cart page$")
    public void i_verify_installation_spin_balancing_amount_on_the_cart_page(String fee) throws Throwable {
        cartPage.assertInstallationBalancingPrice(fee);
    }
    
    @And("^I verify the installation fee amount on the shopping cart page$")
    public void i_verify_the_installation_fee_amount_on_the_shopping_cart_page() throws Throwable {
        cartPage.assertInstallationPrice();
    }

    @And("^I verify the total price including tax amount \"(.*?)\" on the shopping cart page$")
    public void i_verify_total_price_incl_tax_on_the_cart_page(String total) throws Throwable {
        cartPage.assertTotalPriceInclTax(total);
    }
    
    @And("^I select \"(Continue shopping for tires|Continue shopping for wheels)\" option$")
    public void i_select_continue_shopping_action_on_the_cart_page(String action) throws Throwable {
		if (action.contains(ConstantsDtc.TIRES.toLowerCase())) {
			cartPage.clickContinueShoppingForTires();
		} else
			cartPage.clickContinueShoppingForWheels();
	}
}
