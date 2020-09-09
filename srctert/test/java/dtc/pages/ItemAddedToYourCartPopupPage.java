package dtc.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import dtc.data.ConstantsDtc;
import utilities.Driver;

/**
 * Created by collinreed on 7/14/17.
 */
public class ItemAddedToYourCartPopupPage {

    private final Driver driver;
    private final WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(ItemAddedToYourCartPopupPage.class.getName());
    
    public ItemAddedToYourCartPopupPage(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }
    
    private static final By itemAddedToYourCartHeader = By.className("order");
    private static final By tireBrand = By.className("order-list__brandname");
    private static final By tireName = By.className("order-list__productname");
   
    /**
	 * Verify the 'Item added to your cart!" popup displayed 
	 */
    public void assertItemAddedToYourCartPopUp() {
        LOGGER.info("assertItemAddedToYourCartPopUp started");
        String header = "Item added to your cart!";
        
        Assert.assertTrue("FAIL: Page header " + header + "\"!", 
        		webDriver.findElement(itemAddedToYourCartHeader).getText().contains(header));
        
        LOGGER.info("assertItemAddedToYourCartPopUp completed");
    }

    /**
     * Verify the 'Item added to your cart!' popup contains the selected tires
     * 
     * @param map Hashmap containing brand and price information of the selected tires
     */
    public void assertItemAddedToYourCartSelectedTires(HashMap map) {
        LOGGER.info("assertItemAddedToYourCartSelectedTires started");
        HashMap<String, ArrayList<String>> values = map;
        int listCount = values.get(ConstantsDtc.BRAND).size();
        driver.waitForPageToLoad();
        String expectedBrand = values.get(ConstantsDtc.BRAND).get(listCount-1);
        String expectedProduct = values.get(ConstantsDtc.PRODUCT).get(listCount-1);

		String displayBrand = webDriver.findElement(tireBrand).getText();
		String displayProduct = webDriver.findElement(tireName).getText();
                
		Assert.assertTrue("FAIL: Popup Brand incorrect.  Expected:  " + expectedBrand + 
						".  Actual:  " + displayBrand, displayBrand.equals(expectedBrand));
		Assert.assertTrue("FAIL: Popup Product incorrect.  Expected:  " + expectedProduct + 
						".  Actual:  " + displayProduct, displayProduct.equals(expectedProduct));
        
        LOGGER.info("Verified the selected tire on the Added To Your Cart Popup page is '" + displayBrand + " | " + displayProduct);
        LOGGER.info("assertItemAddedToYourCartSelectedTires completed");
    }
   	
}
