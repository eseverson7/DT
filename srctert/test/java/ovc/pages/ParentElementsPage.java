package ovc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ovc.data.ConstantsOvc;
import utilities.Driver;

import java.util.logging.Logger;

/**
 * Created by eseverson on 8/25/2017.
 */
public class ParentElementsPage {

    private Driver driver;
    private WebDriver webDriver;
    private final Logger LOGGER = Logger.getLogger(ParentElementsPage.class.getName());

    public ParentElementsPage(Driver driver){
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public static final String ADJUSTMENT = "Adjustment";
    public static final String VEHICLE_VIEW = "Vehicle View";
    public static final String TRANSACTION_VIEW = "Transaction View";
    public static final String ORDER_DETAILS = "Order Details";
    public static final String CLIENT_DETAILS = "Client Details";
    public static final String AR_PAYMENT = "AR Payment";
    public static final String OVC = "OVC";
    public static final String POPUP = "Popup";
    public static final String ORDER_ADJUSTMENT = "Order Adjustment";
    public static final String LOGIN = "Login";
    public static final String MISC_PRODUCT = "Misc Product";

    @FindBy(className = "mblBackground")
    public static WebElement application;

    @FindBy(id = "posView")
    public static WebElement homePage;

    @FindBy(css = "html[ng-app='cslApp']")
    public static WebElement cslPage;

    @FindBy(id = "alertsView")
    public static WebElement alertsPage;

    @FindBy(id = "ovcAppointmentView")
    public static WebElement appointmentPage;

    @FindBy(id = "ovcEnhancedLookupView")
    public static WebElement lookupPage;

    @FindBy(id = "ovcCustomerEnhancedLookupView")
    public static WebElement customerPage;

    @FindBy(id = "ovcAirCheckView")
    public static WebElement airCheckPage;

    @FindBy(id = "ovcFitmentFormView")
    public static WebElement fitmentPage;

    @FindBy(id = "ovcVisitVehicleView")
    public static WebElement vtvPage;

    @FindBy(id = "ovcVehiclesView")
    public static WebElement vehicleViewPage;

    @FindBy(id = "posView")
    public static WebElement headerPage;

    @FindBy(id = "ovcReports")
    public static WebElement customerVehicleViewPage;

    @FindBy(id = "ovcOrderSearchView")
    public static WebElement transOrderSearchPage;

    @FindBy(id = "ovcReports")
    public static WebElement reportsPage;

    @FindBy(id = "ovcOrderAdjustmentsView")
    public static WebElement orderAdjustmentPage;

    @FindBy(id = "ovcOrderDetailsView")
    public static WebElement orderDetailsPage;

    @FindBy(id = "clientelingView")
    public static WebElement clientDetailsPage;

    @FindBy(id = "ovcFindItView")
    public static WebElement findItPage;

    @FindBy(id = "ovcArPaymentView")
    public static WebElement arPaymentPage;

    @FindBy(id = "loginContent")
    public static WebElement loginPage;

    @FindBy(id = "ovcMiscProductView")
    public static WebElement miscProductPage;

    /**
     * Takes in a page string argument and returns WebElement with ID
     *
     * @param page String value of page to search on
     * @return returnPage Unique page object WebElement by ID
     */
    public WebElement returnPageObjectElement(String page) {
        LOGGER.info("returnPageObjectElement started");

        switch(page) {
            case ConstantsOvc.HOME:
                return homePage;
            case ConstantsOvc.CSL:
                return cslPage;
            case ConstantsOvc.ALERTS:
                return alertsPage;
            case ConstantsOvc.APPOINTMENT:
                return appointmentPage;
            case ConstantsOvc.LOOKUP:
                return lookupPage;
            case ConstantsOvc.CUSTOMER:
                return customerPage;
            case ConstantsOvc.AIR_CHECK:
                return airCheckPage;
            case ConstantsOvc.FITMENT:
                return fitmentPage;
            case ConstantsOvc.VTV:
                return vtvPage;
            case ConstantsOvc.FIND_IT:
                return findItPage;
            case VEHICLE_VIEW:
                return vehicleViewPage;
            case TRANSACTION_VIEW:
                return transOrderSearchPage;
            case ORDER_DETAILS:
                return orderDetailsPage;
            case ORDER_ADJUSTMENT:
                return orderAdjustmentPage;
            case CLIENT_DETAILS:
                return clientDetailsPage;
            case AR_PAYMENT:
                return arPaymentPage;
            case LOGIN:
                return loginPage;
            case MISC_PRODUCT:
                return miscProductPage;
            default:
                return application;
        }
    }
}