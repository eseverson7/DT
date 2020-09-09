package wm.pages;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import common.Constants;
import commonUtils.ExcelUtils;
import common.Config;
import utilities.Driver;
import org.junit.Assert;


/**
 * Created by mnabizadeh on 5/18/18.
 */
public class TransactionSearch {
	
    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(TransactionSearch.class.getName());
    private ExcelUtils excelUtils;
    private String orderNumber = "";
    
    public TransactionSearch(Driver driver) {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }
    
    
    @FindBy(id = "wm_login-username")
    public static WebElement wmUser;
    
    @FindBy(id = "wm_login-password")
    public static WebElement wmPassword;
    
    @FindBy(id = "submit_login")
    public static WebElement loginButton;
    
    @FindBy(id = "jsfwmp19186:searchBarForm:searchBarControl:RFP:htmlInputText4")
    public static WebElement functionId;
    
    @FindBy(id = "jsfwmp19186:searchBarForm:searchBarControl:RFP:dateRangeInput__range")
    public static WebElement dateRangeDropdown;
    
    @FindBy(id = "jsfwmp19187:searchResultsForm:searchResultsTree:__rowc1dad481-b896-43db-96f8"
    		+ "-749923a0ce21u_002c_6585517:htmlGraphicImage6")
    public static WebElement completedStatus;
    
    public static final By searchButtonBy = By.className("caf-button-l");
    
    /**
     *Goes to WM home page
     *
     */
    public void goWMHome() {
        LOGGER.info("goWMHome started");
        driver.getUrl(Config.getBaseUrl());
        driver.waitForPageToLoad();
        LOGGER.info("goWMHome completed");
    }
    
    /**
     * Logs in to WM using UN and PW
     */
    public void login() {
        LOGGER.info("login started");
        driver.waitForElementVisible(wmUser);
        wmUser.sendKeys(Config.getWMUserName());
        wmPassword.sendKeys(Config.getWMPassword());
        driver.waitForElementClickable(loginButton);
        loginButton.click();
        LOGGER.info("login completed");
    }
    
    /**
     * Creates excelUtils object
     *
     * @param file Specifies the excel file to find the location for
     */  
    public ExcelUtils setupExcel (String file){
        LOGGER.info("setupExcel started");
    	String location =  Constants.EXTEND_ASSORT_ORDER_DATA_FILE_IN + file + Constants.EXTEND_ASSORT_EXCEL_FILE_EXTENSION;
   	    excelUtils = new ExcelUtils(location);
        LOGGER.info("setupExcel completed");
        
    	return excelUtils;
    }

    /**
      * Pulls hybris order number from excel and returns for use by the next step
      *
      * @param file Specifies the excel file to read from
      */
    public void hybrisOrderNumberExcel(String file) throws Exception {
    	LOGGER.info("hybrisOrderNumberExcel started");
    	ExcelUtils myOrder = setupExcel(file);
    	orderNumber = myOrder.getCellValue(1,2,0); 
    	LOGGER.info("hybrisOrderNumberExcel completed");
     }
     
     /**
      * Looks up order number/transaction number on Interface Monitor page
      *
      */
     public void wmFunctionIdSearch() throws Exception {
    	 LOGGER.info("wmFunctionIdSearch started");
    	 driver.waitForElementVisible(functionId);
    	 functionId.sendKeys(orderNumber);
    	 LOGGER.info("wmFunctionIdSearch completed");
     }
     
     /**
      * Selects a dropdown value from a Date Range dropdown
      *
      * @param dateRange dropdown value to select
      */
     public void selectDateRange(String dateRange) {
         LOGGER.info("selectDateRange started");
         driver.selectFromDropdownByVisibleText(dateRangeDropdown, "This Day");
         LOGGER.info("selectDateRange completed");
     }
     
     /**
      * Clicks Search button
      * @throws Exception Exception
      */
     public void searchButton() throws Exception {
         LOGGER.info("searchButton started");
         try {
             WebElement searchButton = driver.getElementWithText(searchButtonBy, Constants.SEARCH_BUTTON);
             searchButton.click();
         } catch (Exception e) {
             Assert.fail("FAIL: Clicking search button FAILED with error: " + e);
         }
         LOGGER.info("searchButton completed");
     }
     
     /**
      * Asserts that the completed status icon is present
      */
     public void assertCompletedStatus() {
         LOGGER.info("assertCompletedStatus started");
         driver.waitForElementVisible(completedStatus);
         Assert.assertTrue("FAIL: Completed Icon status not present",completedStatus.isDisplayed());
         LOGGER.info("assertCompletedStatus completed");
     }
}