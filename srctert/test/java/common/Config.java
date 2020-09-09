package common;

import dtc.data.ConstantsDtc;
import pdl.data.ConstantsPdl;

/**
 * Created by aaronbriel on 9/17/16.
 */

public class Config {

    private static final String BROWSER = System.getProperty("browser", Constants.CHROME_BROWSER);
    private static final String IS_REMOTING = System.getProperty("isRemoting", "false");
    private static final String IS_SAUCELABS = System.getProperty("isSaucelabs", "false");
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static final String SITE_REGION = System.getProperty("siteRegion", null);
    private static String BASE_URL = System.getProperty("baseUrl", "localhost");
    private static final String DATA_SET = System.getProperty("dataSet", Constants.QA);
    //NOTE: Android version 5.1 and before use "Browser", for above use "Chrome"
    private static final String ANDROID_BROWSER = System.getProperty("androidBrowser", "Chrome");
    //NOTE: Device name for droid can be found by running ../android_sdk/platform_tools/abd devices
    private static final String DEVICE_NAME = System.getProperty("deviceName", "emulator-5554");
    private static final String UDID = System.getProperty("udid", "PLEASE SPECIFIY -Dudid");
    private static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    private static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    private static final String HMC_PATH = "/hmc/hybris";
    private static final String SAUCELABS_SAFARI_PLATFORM = "OS X 10.11";
    private static final String SAUCELABS_SAFARI_VERSION = "10.0";
    private static final String SAUCELABS_IE_PLATFORM = "Windows 7";
    private static final String SAUCELABS_IE_VERSION = "11.0";
    private static final String SAUCELABS_CHROME_PLATFORM = "Windows 7";
    private static final String SAUCELABS_CHROME_VERSION = "54.0";
    private static final String SAUCELABS_FIREFOX_PLATFORM = "Windows 10";
    private static final String SAUCELABS_FIREFOX_VERSION = "45.0";
    private static final String APPLITOOLS_API_KEY = System.getenv("APPLITOOLS_API_KEY");
    private static String STORED_BASE_URL;

    public static String getBaseUrl() {
        if (BASE_URL.toLowerCase().contains(HTTPS)) {
            return BASE_URL;
        }
        return HTTP + BASE_URL;
    }

    public static void setBaseUrl(String url) {
        BASE_URL = url;
    }

    /**
     * Gets baseUrl with protocol prepended if valid one passed in, otherwise baseUrl returned
     *
     * @param protocol The protocol (http/https) to prepend to baseUrl
     * @return String
     */
    public static String getBaseUrl(String protocol) {
        if (protocol.equalsIgnoreCase(HTTP)) {
            return HTTP + BASE_URL;
        } else if (protocol.equalsIgnoreCase(HTTPS)) {
            return HTTPS + BASE_URL;
        } else {
            return BASE_URL;
        }
    }

    public static String getSiteRegion() {
        return SITE_REGION;
    }

    public static String getBrowser() {
        return BROWSER;
    }

    public static String getDataSet() {
        return DATA_SET;
    }

    public static Boolean getIsRemoting() {
        return Boolean.parseBoolean(IS_REMOTING);
    }

    public static Boolean getIsSaucelabs() {
        return Boolean.parseBoolean(IS_SAUCELABS);
    }

    public static String getSaucelabsBrowser() {
        return System.getenv("SELENIUM_BROWSER");
    }

    public static String getSaucelabsBrowserVersion() {
        return System.getenv("SELENIUM_VERSION");
    }

    public static String getSaucelabsPlatform() {
        return System.getenv("SELENIUM_PLATFORM");
    }

    public static String getSaucelabsDevice() {
        return System.getenv("SELENIUM_DEVICE");
    }

    public static String getSauceUsername() {
        return SAUCE_USERNAME;
    }

    public static String getSauceAccessKey() {
        return SAUCE_ACCESS_KEY;
    }

    public static String getDeviceName() {
        return DEVICE_NAME;
    }

    public static String getUdid() {
        return UDID;
    }

    public static String getAndroidBrowser() {
        return ANDROID_BROWSER;
    }

    public static String getHmcUserName() {
        return System.getenv("HMC_USER");
    }

    public static String getHmcUserPswd() {
        return System.getenv("HMC_PSWD");
    }

    public static String getSeleniumVersion() {
        return System.getenv("SELENIUM_POM_VERSION");
    }

    public static String getApplitoolsApiKey() {
        return APPLITOOLS_API_KEY;
    }

    public static String getOvcUserName() {
        return System.getenv("OVC_USER");
    }
    
    public static String getOvcUserPswd(){
    	return System.getenv("OVC_PSWD"); 
    }

    public static String getOvcServer() {
    	return System.getenv("OVC_SERVER");
    }

    public static String getMngrUser() {
    	return System.getenv("MNGR_USER"); 
    }

    public static String getMngrPswd() {
    	return System.getenv("MNGR_PSWD"); 	
    }
    
    public static String getSapSystem() {
        return System.getenv("SAP_SYSTEM");
    }

    public static String getSapClient() {
        return System.getenv("SAP_CLIENT");
    }
    
    public static String getSapUserName() {
        return System.getenv("SAP_USERNAME");
    }
    
    public static String getSapPassword() {
        return System.getenv("SAP_PASSWORD");
    }

    public static String getKCClientUserName() {
        return System.getenv("KC_USERNAME");
    }

    public static String getKCClientPassword() {
        return System.getenv("KC_PASSWORD");
    }

    public static String getWMUserName() {
        return System.getenv("WM_USERNAME");
    }

    public static String getWMPassword() {
        return System.getenv("WM_PASSWORD");
    }

    /**
     * Checks if product is DTC
     *
     * @return boolean Whether product is DTC
     */
    public static boolean isDtc() {
        boolean isDtc = false;
        if (getSiteRegion() != null)
            isDtc = true;
        return isDtc;
    }

    /**
     * Checks if product is PDL
     *
     * @return boolean Whether product is PDL
     */
    public static boolean isPdl() {
        boolean isPdl = false;
        if (getBaseUrl().contains(ConstantsPdl.TIRE_FINDER))
            isPdl = true;
        return isPdl;
    }

    /**
     * Checks if product is OVC
     *
     * @return boolean Whether product is OVC
     */
    public static boolean isOvc() {
        return Boolean.parseBoolean(System.getProperty("isOvc", "false"));
    }

    /**
     * Checks if browser parameter is a mobile phone or tablet
     *
     * @return boolean Whether browser is a mobile phone or tablet
     */
    public static boolean isMobile() {
        boolean isMobile = false;
        if (isAndroidPhone() ||
                isAndroidTablet() ||
                isIphone() ||
                isIpad())
            isMobile = true;
        return isMobile;
    }

    /**
     * Checks if browser parameter is a mobile phone
     *
     * @return boolean Whether browser is a mobile phone
     */
    public static boolean isMobilePhone() {
        boolean isMobilePhone = false;
        if (getBrowser().equalsIgnoreCase(Constants.ANDROID_PHONE) ||
                isIphone())
            isMobilePhone = true;
        return isMobilePhone;
    }

    /**
     * Checks if current browser is Safari
     *
     * @return Boolean
     */
    public static boolean isSafari() {
        return getBrowser().equalsIgnoreCase(Constants.SAFARI_BROWSER);
    }

    /**
     * Checks if current browser is Chrome
     *
     * @return Boolean
     */
    public static boolean isChrome() {
        return getBrowser().equalsIgnoreCase(Constants.CHROME_BROWSER);
    }

    /**
     * Checks if current browser is Fireofox
     *
     * @return Boolean
     */
    public static boolean isFirefox() {
        return getBrowser().equalsIgnoreCase(Constants.FIREFOX_BROWSER);
    }

    /**
     * Checks if current browser is Internet Explorer
     *
     * @return Boolean
     */
    public static boolean isIe() {
        return getBrowser().equalsIgnoreCase(Constants.IE_BROWSER);
    }

    /**
     * Checks if current browser is iphone
     *
     * @return Boolean
     */
    public static boolean isIphone() {
        return getBrowser().equalsIgnoreCase(Constants.IPHONE);
    }

    /**
     * Checks if current browser is ipad
     *
     * @return Boolean
     */
    public static boolean isIpad() {
        return getBrowser().equalsIgnoreCase(Constants.IPAD);
    }

    /**
     * Checks if current browser is an Android phone
     *
     * @return Boolean
     */
    public static boolean isAndroidPhone() {
        return getBrowser().equalsIgnoreCase(Constants.ANDROID_PHONE);
    }

    /**
     * Checks if current browser is an Android tablet
     *
     * @return Boolean
     */
    public static boolean isAndroidTablet() {
        return getBrowser().equalsIgnoreCase(Constants.ANDROID_TABLET);
    }


    /**
     * Gets the default store for current tests and passes it back as a string
     *
     * @return store String of store number
     */
    public static String getDefaultStore() {
        String store = null;
        if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                store = ConstantsDtc.DT_STG_DEFAULT_STORE;
            } else {
                store = ConstantsDtc.DT_QA_DEFAULT_STORE;
            }
        } else if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                store = ConstantsDtc.AT_STG_DEFAULT_STORE;
            } else {
                store = ConstantsDtc.AT_QA_DEFAULT_STORE;
            }
        }
        return store;
    }

    /**
     * Gets the default city for current store and passes it back as a string
     *
     * @return store String of city name
     */
    public static String getDefaultStoreCity() {
        String store = null;
        if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                store = ConstantsDtc.DT_STG_DEFAULT_STORE_CITY;
            } else {
                store = ConstantsDtc.DT_QA_DEFAULT_STORE_CITY;
            }
        } else if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                store = ConstantsDtc.AT_STG_DEFAULT_STORE_CITY;
            } else {
                store = ConstantsDtc.AT_QA_DEFAULT_STORE_CITY;
            }
        }
        return store;
    }
    
    /**
     * Gets the default store code url path for current tests and passes it back as a string
     *
     * @return store String of store code url path
     */
    public static String getDefaultStoreCodeURL() {
        String storeCode = null;
        if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                storeCode = ConstantsDtc.DT_STG_DEFAULT_STORE_CODE_PATH;
            } else {
                storeCode = ConstantsDtc.DT_QA_DEFAULT_STORE_CODE_PATH;
            }
        } else if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                storeCode = ConstantsDtc.AT_STG_DEFAULT_STORE_CODE_PATH;
            } else {
                storeCode = ConstantsDtc.AT_QA_DEFAULT_STORE_CODE_PATH;
            }
        }
        return storeCode;
    }

    /**
     * Gets the full store address for current store and passes it back as a string
     *
     * @return address String of complete store address
     */
    public static String getDefaultStoreAddress() {
        String store = null;
        if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                store = ConstantsDtc.DT_STG_DEFAULT_STORE_ADDRESS;
            } else {
                store = ConstantsDtc.DT_QA_DEFAULT_STORE_ADDRESS;
            }
        } else if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                store = ConstantsDtc.AT_STG_DEFAULT_STORE_ADDRESS;
            } else {
                store = ConstantsDtc.AT_QA_DEFAULT_STORE_ADDRESS;
            }
        }
        return store;
    }

    /**
     * Gets the phone number for current store and passes it back as a string
     *
     * @return phone number
     */
    public static String getDefaultStorePhoneNumber() {
        String phoneNumber = null;
        if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                phoneNumber = ConstantsDtc.DT_STG_DEFAULT_STORE_PHONE_NUMBER;
            } else {
                phoneNumber = ConstantsDtc.DT_QA_DEFAULT_STORE_PHONE_NUMBER;
            }
        } else if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                phoneNumber = ConstantsDtc.AT_STG_DEFAULT_STORE_PHONE_NUMBER;
            } else {
                phoneNumber = ConstantsDtc.AT_QA_DEFAULT_STORE_PHONE_NUMBER;
            }
        } else if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.DTD)){
            phoneNumber = ConstantsDtc.DTD_DEFAULT_PHONE_NUMBER;
        }
        return phoneNumber;
    }

    /**
     * Gets the store code for current store and passes it back as a string
     *
     * @return store code
     */
    public static String getDefaultStoreCode() {
        String storeCode = null;
        if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                storeCode = ConstantsDtc.DT_STG_DEFAULT_STORE_CODE;
            } else {
                storeCode = ConstantsDtc.DT_QA_DEFAULT_STORE_CODE;
            }
        } else if (getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                storeCode = ConstantsDtc.AT_STG_DEFAULT_STORE_CODE;
            } else {
                storeCode = ConstantsDtc.AT_QA_DEFAULT_STORE_CODE;
            }
        }
        return storeCode;
    }

    /**
     * Returns the saucelabs url
     *
     * @return url      The saucelabs url
     */
    public static String getSaucelabsUrl() {
        return "http://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    }

    /**
     * Returns the default saucelabs platform when running locally
     *
     * @return platform     The default saucelabs platform based on browser
     */
    public static String getDefaultSaucelabsPlatform() {
        String platform = null;
        if (isSafari()) {
            platform = SAUCELABS_SAFARI_PLATFORM;
        } else if (isChrome()) {
            platform = SAUCELABS_CHROME_PLATFORM;
        } else if (isFirefox()) {
            platform = SAUCELABS_FIREFOX_PLATFORM;
        } else if (isIe()) {
            platform = SAUCELABS_IE_PLATFORM;
        }
        return platform;
    }

    /**
     * Returns the default saucelabs browser version when running locally
     *
     * @return version     The default saucelabs browser version based on browser
     */
    public static String getDefaultSaucelabsVersion() {
        String version = null;
        if (isSafari()) {
            version = SAUCELABS_SAFARI_VERSION;
        } else if (isChrome()) {
            version = SAUCELABS_CHROME_VERSION;
        } else if (isFirefox()) {
            version = SAUCELABS_FIREFOX_VERSION;
        } else if (isIe()) {
            version = SAUCELABS_IE_VERSION;
        }
        return version;
    }

    /**
     * Returns the Url for the Hybris Management Console. First checks if Steve notation is in base Url. Steve
     * environments  route dt, dtd, and at to same hybris site. Else HMC Url will be based on -DdataSet.
     * @return Url for Hybris Management Console
     */
    public static String getHmcUrl() {
        if (getBaseUrl().contains("steve")) {
            return HTTP + "steve.discounttire.com" + HMC_PATH;
        } else {
            if (getDataSet().equalsIgnoreCase(Constants.STG)) {
                return HTTP + "cpstg.discounttire.com" + HMC_PATH;
            } else {
                return getBaseUrl() + HMC_PATH;
            }
        }
    }

    /**
     * Returns Hybris URL for integration testing based on -DdataSet.
     * @return Url for Hybris
     */
    public static String getHybrisUrl() {
        //TODO: Currently assumes DT as site Region. Expand if needed.
        if (getDataSet().equalsIgnoreCase(Constants.STG)) {
            return HTTPS + "stg.discounttire.com";
        } else {
            return HTTPS + "dtqa1.epic.discounttire.com";
        }
    }

    /**
     * Returns OVC URL for integration testing.
     * @return Url for OVC
     */
    public static String getOvcUrl() {
        return "localhost:32040/index.html";
    }

    /**
     * Stores current baseUrl to STORED_BASE_URL
     */
    public static void storeCurrentBaseUrl() {
        STORED_BASE_URL = getBaseUrl("none");
    }

    /**
     * Gets stored baseUrl
     *
     * @return String Stored baseUrl
     */
    public static String getStoredBaseUrl() {
        return STORED_BASE_URL;
    }

    /**
     * Get the store type based on the current site region
     *
     * @return storeType 'America's Tire Store' or 'Discount Tire Store'
     */
    public static String getStoreType() {
        String storeType = null;
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
            storeType = ConstantsDtc.AMERICAS_TIRE_STORE;
        } else {
            storeType = ConstantsDtc.DISCOUNT_TIRE_STORE;
        }
        return storeType;
    }

    /**
     * Get the image URL based on the current site region
     *
     * @return imageUrl
     */
    public static String getSolicitedReviewStoreImageUrl() {
        String baseUrl = Config.getBaseUrl();

        String imageUrl = null;
        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.DT)) {
            imageUrl = baseUrl + ConstantsDtc.IMAGE_FILEPATH_DT;
        } else {
            imageUrl = baseUrl + ConstantsDtc.IMAGE_FILEPATH_AT;
        }
        return imageUrl;
    }

    /**
     * Get the store name
     *
     * @return storeName
     */
    public static String getStoreName() {
        String store = Config.getDefaultStore();
        String storeType = Config.getStoreType();

        String storeName = storeType + " (" + store + ")";
        // TODO: Add conditions for staging when text labels are used there.
        if (Config.getDataSet().equals(Constants.QA)) {
            String storeTextLabel = ConstantsDtc.DT_QA_DEFAULT_STORE_TEXT_LABEL;

            if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT)) {
                storeTextLabel = ConstantsDtc.AT_QA_DEFAULT_STORE_TEXT_LABEL;
            }
            storeName = storeTextLabel +  " (" + store + ")";
        }
        return storeName;
    }

}