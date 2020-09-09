package common;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

/**
 * Created by aaronbriel on 11/10/16.
 */
public class ScenarioData {

    private String sessionId;
    private String currentOrderNumber;
    private String currentPurchaseOrderNumber;
    private static final String SESSION_COOKIE_NAME = "JSESSIONID";


    public ScenarioData() {
    }

    /**
     * Gets the id of the current session
     *
     * @return sessionId String of current session id
     */
    public String getDtSessionId() {
        return this.sessionId;
    }

    /**
     * Gets the default store for current tests and passes it back as a string
     *
     * @param driver WebDriver instance
     */
    public void setDtSessionId(WebDriver driver) {
        //TODO: retest when new safaridriver is stable
        if (Config.getBrowser().equalsIgnoreCase(Constants.SAFARI_BROWSER) ||
                Config.isMobile()) {
            this.sessionId = "SKIPPED_FOR_SAFARI_AND_MOBILE";
        } else {
            Cookie cookie = driver.manage().getCookieNamed(SESSION_COOKIE_NAME);
            try {
            	this.sessionId = cookie.getValue();
            }
            catch(NullPointerException ex) {
            }
            
        }
    }

    /***
     * Gets the current order number
     * @return String of current order number
     */
    public String getCurrentOrderNumber() {
        return currentOrderNumber;
    }

    /***
     * Sets currentOrderNumber to passed in value
     * @param orderNumber value to set as currentOrderNumber's value
     */
    public void setCurrentOrderNumber(String orderNumber) {
        currentOrderNumber = orderNumber;
    }

    public String getCurrentPurchaseOrderNumber(){return currentPurchaseOrderNumber;}

    public void setCurrentPurchaseOrderNumber(String purchaseOrderNumber) {
        currentPurchaseOrderNumber = purchaseOrderNumber;
    }
}