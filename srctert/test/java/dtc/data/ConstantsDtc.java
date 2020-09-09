package dtc.data;

import common.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aaronbriel on 3/28/17.
 */
public class ConstantsDtc {

    public static final String DTD = "dtd";
    public static final String DT = "dt";
    public static final String AT = "at";

    public static final String DT_QA_DEFAULT_STORE = "AZF 01";
    public static final String DT_QA_DEFAULT_STORE_CITY = "flagstaff";
    public static final String DT_QA_DEFAULT_STORE_ADDRESS = "1230 S MILTON RD, FLAGSTAFF, AZ 86001-6306";
    public static final String DT_QA_DEFAULT_STORE_CODE_PATH = "/store/az/flagstaff/s/1002";
    public static final String DT_QA_DEFAULT_STORE_PHONE_NUMBER = "928-774-5273";
    public static final String DT_QA_DEFAULT_STORE_CODE = "1002";
    public static final String DT_QA_DEFAULT_STORE_TEXT_LABEL = "S MILTON";

    public static final String DT_STG_DEFAULT_STORE = "ORP 02";
    public static final String DT_STG_DEFAULT_STORE_CITY = "eugene";
    public static final String DT_STG_DEFAULT_STORE_ADDRESS = "4026 W 11TH AVE, EUGENE, OR 97402-5625";
    public static final String DT_STG_DEFAULT_STORE_CODE_PATH = "/store/or/eugene/s/1174";
    public static final String DT_STG_DEFAULT_STORE_PHONE_NUMBER = "541-345-7961";
    public static final String DT_STG_DEFAULT_STORE_CODE = "1174";
    //TODO: Add text label for DT staging when it is being used there

    public static final String AT_QA_DEFAULT_STORE = "CAL 01";
    public static final String AT_QA_DEFAULT_STORE_CITY = "Chino";
    public static final String AT_QA_DEFAULT_STORE_ADDRESS = "11925 CENTRAL AVE, CHINO, CA 91710-1906";
    public static final String AT_QA_DEFAULT_STORE_CODE_PATH = "/store/ca/chino/s/1038";
    public static final String AT_QA_DEFAULT_STORE_PHONE_NUMBER = "909-591-4501";
    public static final String AT_QA_DEFAULT_STORE_CODE = "1038";
    public static final String AT_QA_DEFAULT_STORE_TEXT_LABEL = "CHINO";

    public static final String AT_STG_DEFAULT_STORE = "CAN 19";
    public static final String AT_STG_DEFAULT_STORE_CITY = "redding";
    public static final String AT_STG_DEFAULT_STORE_ADDRESS = "16 HARTNELL AVE, REDDING, CA 96002-1840";
    public static final String AT_STG_DEFAULT_STORE_CODE_PATH = "/store/ca/redding/s/1064";
    public static final String AT_STG_DEFAULT_STORE_PHONE_NUMBER = "530-221-0667";
    public static final String AT_STG_DEFAULT_STORE_CODE = "1064";
    //TODO: Add text label for AT staging when it is being used there

    public static final String DTD_DEFAULT_PHONE_NUMBER = "800-589-6789";

    public static final String PAYPAL = "paypal";
    public static final String DEFAULT = "default";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "Password";
    public static final String WITH_APPOINTMENT = "with appointment";
    public static final String INSTALL_WITHOUT_APPOINTMENT = "install without appointment";
    public static final String INSTALL_WITH_APPOINTMENT = "install with appointment";
    public static final String ENVIRONMENTAL_FEE = "Environmental Fee";
    public static final String CERTIFICATE = "Certificates";
    public static final String VALVE_STEM = "Valve Stem";
    public static final String TAX = "Tax";

    public static final String IN_STOCK = "In Stock";

    public static final String ON_ORDER = "On Order";

    public static final String ZERO_IN_STOCK_AT_MY_STORE = "0 in stock at My Store";

    public static final String AVAILABILITY_3_5_DAYS_CALLTOCONFIRM =
            "Availability: Usually 3-5 days, call to confirm.";

    public static final String SEARCH = "Search";

    public static final String CONTINUE = "Continue";
    public static final String CONTINUE_SHOPPING = "Continue Shopping";
    public static final String SIGN_OUT = "Sign out";

    public static final String BRAND = "brand";
    public static final String PRICE = "price";
    public static final String PRODUCT = "product";
    public static final String INVENTORY_MESSAGE = "inventoryMessage";

    public static final String PRICE_NOT_DISPLAYED = "Price not displayed.";

    public static final String CONTINUE_TO_CHECKOUT = "Continue to Checkout";
    public static final String REMOVE_ITEM_FROM_CART = "Remove item from cart";
    public static final String PLACE_ORDER = "Place Order";
    public static final String SAVE_CARD_DETAILS = "SAVE CARD DETAILS";

    public static final String INSTALLATION = "Installation";
    public static final String ESTIMATED_TAX = "Estimated Tax";
    public static final String SALES_TAX = "Sales Tax";
    public static final String CART_SUBTOTAL = "Cart Subtotal";
    public static final String TOTAL = "Total";
    public static final String DISPOSAL_FEE = "Disposal Fee";
    public static final String TIRES = "TIRES";
    public static final String WHEELS = "WHEELS";

    public static final String ORDERS_FILE = "orders.txt";

    public static final String AMERICAS_TIRE_STORE = "America's Tire Store";
    public static final String DISCOUNT_TIRE_STORE = "Discount Tire Store";
    public static final String DISCOUNT_TIRE_DIRECT = "Discount Tire Direct";

    public static final String COMPARE_ITEM_MESSAGE = "SELECT 1 OR 2 MORE";

    public static final String ORDER = "order";
    public static final String SPECIAL_ORDER = "Special Order";

    public static final String UPPERCASE = "UPPERCASE";
    public static final String LOWERCASE = "LOWERCASE";

    public static final String DIRECTIONS = "Directions";

    public static final String stateDropDownString = "addressProvince_chosen";
    public static final String countryDropDownString = "country_chosen";

    public static final String AMERICAS_TIRE = "americastire";
    public static final String DISCOUNT_TIRE = "discounttire";

    public static final List<String> MY_STORE_POPUP_STORE_HOURS =
            Arrays.asList("Mon - Fri", "8AM - 6PM", "Saturday", "8AM - 5PM", "Sunday", "Closed");

    public static final List<String> STORE_RESULTS_HOURS_OPERATION =
            Arrays.asList("Mon-Fri : 8:00 AM - 6:00 PM", "Sat : 8:00 AM - 5:00 PM", "Sun : CLOSED");

    public static final List<String> STORE_DETAILS_STORE_HOURS =
            Arrays.asList("Mon-Fri: 8:00 AM - 6:00 PM", "Sat: 8:00 AM - 5:00 PM", "Sun: CLOSED");

    public static final String HOMEPAGE = "Homepage";
    public static final String PLP = "PLP";
    public static final String PDP = "PDP";
    public static final String SHOPPING_CART = "Shopping Cart";
    public static final String CHECKOUT = "Checkout";
    public static final String APPOINTMENT = "Appointment";
    public static final String ORDER_CONFIRMATION = "Order Confirmation";
    public static final String FIXED = "FIXED";
    public static final String VIEW_ALL = "View All";
    public static final String TITLE_VIEW_ALL = "View+All";
    public static final String PERCENT_DISCOUNT = "% DISCOUNT";
    public static final String PERCENT_PERCENTAGE_DISCOUNT = "% PERCENTAGE DISCOUNT";
    public static final String PERCENT_FIXED_DISCOUNT = "% FIXED DISCOUNT";
    public static final String TPMS = "TPMS";
    public static final String FETFEE_LABEL = "Federal Excise Tax (F.E.T.)";

    public static final double INSTALLATION_FEE_AZ = 16.00;
    public static final double HUB_CENTRIC_RING_FEE = 5.00;
    public static final double WHEEL_INSTALL_KIT_FEE = 45.00;
    public static final int DEFAULT_QUANTITY = 4;

    public static final String APPT_NOT_SURE_OF_AVAILABILITY = "Not sure of my availability";
    public static final String APPT_MAKE_AN_APPOINTMENT_AT_A_LATER_TIME = "Make an appointment at a later time";
    public static final String APPT_THESE_ITEMS_ARE_FOR_MULTIPLE_VEHICLES = "These items are for multiple vehicles";
    public static final String DEFAULT_REASON = APPT_NOT_SURE_OF_AVAILABILITY;
    public static final String RESERVE_WITHOUT_APPOINTMENT_MESSAGE =
            "Upon product arrival, you will have 10 days to install the product on your vehicle.";
    public static final String RESERVE_WITHOUT_APPOINTMENT_MULTIPLE_VEHICLES_MESSAGE =
            "We may be able to accommodate you for an appointment with multiple vehicles. Contact your selected store.";
    public static final String COUNTRY = "Country";
    public static final String PHONE_TYPE = "Phone Type";
    public static final String CONTINUE_TO_SHIPPING = "Continue To Shipping Method";
    public static final String CONTINUE_TO_PAYMENT = "Continue To Payment";
    public static final String CONTINUE_TO_CUSTOMER_DETAILS = "CONTINUE TO CUSTOMER DETAILS";
    public static final String CC_EXPIRE_MONTH = "Exp. Month";
    public static final String CC_EXPIRE_YEAR = "Exp. Year";
    public static final String ADD = "Add";
    public static final String CONTINUE_TO_PAYPAL = "Continue to Paypal";
    public static final String ABBREV_ENVIRONMENTAL_FEE = "Enviro. Fee";

    public static final String CHECKOUT_CUSTOMER_INFO_URL = "/checkout/customer-info";
    public static final String SCOTTSDALE_STORE_CODE_PATH = "/store/az/scottsdale/s/1344";
    public static final String USE_USPS_CORRECTED_ADDRESS = "Use USPS Corrected Address";

    public static final String DRIVING_PRIORITY_EVERYDAY = "EVERYDAY";
    public static final String OUR_RECOMMENDATION = "Our Recommendation";
    public static final String CHECKOUT_WITH_SHIPPING = "Checkout with shipping";
    public static final String DATEPICKER_MESSAGE = "To schedule an installation for new tires "
            + "or wheels that you are purchasing today, pick a date up to 10 business days from now. "
            + "If you just need to schedule a routine service, pick a date up to 35 business days "
            + "from today, but walk-ins are always welcome.";
    public static final String ORDER_SUMMARY = "Order Summary";
    public static final String BASE_PRICE = "BasePrice";
    public static final String QUANTITY = "Quantity";

    public static final String HUB_CENTRIC_RING = "Hub Centric Ring";
    public static final String WHEEL_INSTALL_KIT = "Wheel Install Kit";
    public static final String WHEEL_ELEMENT = " X";

    public static final String BEFORE = "before";
    public static final String AFTER = "after";
    public static final String BRAND_SEARCH_FIELD_PLACEHOLDER_TEXT = "Search for a partial or full brand name...";
    public static final String SWITCH_STORE_MESSAGE_ON_CART = "Switching to another store will clear your cart";
    public static final String CHANGE_STORE_DT = "AZP 20";
    public static final String ZIP_CODE_DT = "85250";
    public static final String ZIP_CODE_AT = "91709";
    public static final String MAKE_THIS_MY_STORE = "Make this my store";
    public static final String STORE_DEFAULT_RESULT = "1";

    public static final String SWITCH_VEHICLE_POPUP_MESSAGE = "Switching to another vehicle will clear your cart";
    public static final String CANCEL = "cancel";
    public static final String[] TIRE_BRANDS_URLS = {"/fitmentresult/tires/optionalPlusSize/225/40-18",
            "/tires/brands/michelin-catalog?cat=all-season-tires",
            "/tires/brands/nitto-catalog?cat=all-terrain-tires",
            "/tires/brands/nitto-catalog?cat=competition-tires",
            "/tires/brands/goodyear-catalog?cat=mud-terrain-tires",
            "/tires/brands/goodyear-catalog?cat=passenger-tires",
            "/tires/brands/goodyear-catalog?cat=performance-tires",
            "/tires/brands/goodyear-catalog?cat=summer-tires",
            "/tires/brands/goodyear-catalog?cat=touring-tires",
            "/tires/trailer-catalog?brand=carlisle-tires",
            "/tires/brands/goodyear-catalog?cat=truck-tires",
            "/tires/brands/goodyear-catalog?cat=winter-tires"};

    public static final String[] WHEEL_BRANDS_URLS = {"/fitmentresult/wheels",
            "/fitmentresult/wheels/optionalPlusSize/15",
            "/wheels/brands/konig-catalog?cat=painted-wheels",
            "/wheels/brands/konig-catalog?cat=passenger-wheels",
            "/wheels/brands/konig-catalog?cat=multi-spoke-wheels"};

    public static final String ATTR_STYLE = "style";
    public static final String EMAIL_SENT_MESSAGE = "An e-mail was sent to the address you've provided:";
    public static final String EMAIL_AUTHENTICATION_LINK_MESSAGE = "please click the link in the e-mail to authenticate your account";
    public static final String EMAIL_ACCOUNT_NOT_AUTHENTICATED_MESSAGE = "This e-mail address is linked to an account that has not yet been authenticated";
    public static final String MY_ACCOUNT_INVALID_CREDS_MESSAGE = "The e-mail address and/or password you've entered is incorrect, please try again";
    public static final String CANNOT_SHIP_ITEMS_WARNING = "We cannot ship Nitto items to the following locations:";
    public static final String CANNOT_SHIP_LOCATIONS = "Canada : MB ,BC ,ON ,PE ,NT ,QC ,NS ,SK ,AB ,YT ,NB ,NL ,NU";
    public static final String CANNOT_SHIP_LOCATIONS_SAFARI = "Canada: MB ,BC ,ON ,PE ,NT ,QC ,NS ,SK ,AB ,YT ,NB ,NL ,NU";
    public static final String MAKE = "MAKE";
    public static final String EMAIL_ADDRESS_LABEL = "E-mail Address";
    public static final String OE_TIRE = "ORIGINAL EQUIPMENT TIRE";
    public static final String EDIT_SIGNIN_EMAIL_LABEL = "Edit Sign-in E-mail";

    public static final String RIDE_COMFORT = "Ride Comfort";
    public static final String CORNERING_AND_STEERING = "Cornering and Steering";
    public static final String RIDE_NOISE = "Ride Noise";
    public static final String TREAD_LIFE = "Tread Life";
    public static final String DRY_TRACTION = "Dry Traction";
    public static final String PRODUCT_RECOMMENDATION = "Product Recommendation";
    public static final String ZIP_CODE = "Zip Code";
    public static final String MILES_DRIVEN = "Miles Driven";
    public static final String DRIVING_CONDITIONS = "Driving Conditions";
    public static final String TYPE_OF_DRIVING = "Type of Driving";
    public static final String TERMS_AND_CONDITIONS = "Terms and Conditions";
    public static final String CORNERING_APPERSAND_STEERING = "Cornering & Steering";
    public static final String EMPLOYEE_KNOWLEDGE_FRIENDLINESS = "Employee Knowledge/Friendliness";
    public static final String STORE_CLEANLINESS = "Store Cleanliness";
    public static final String OVERALL_RATING = "Overall Rating";
    public static final String STORE_RECOMMENDATION = "Store Recommendation";
    public static final String GENERAL_COMMENTS = "General Comments";

    public static final String MOSTLY_DRY = "Mostly Dry";
    public static final String MIXED_DRY_WET = "Mixed Dry/Wet";
    public static final String MOSTLY_WET = "Mostly Wet";
    public static final String SNOW = "Snow";

    public static final String MOSTLY_HIGHWAY = "Mostly Highway";
    public static final String MIXED_HIGHWAY_CITY = "Mixed Highway/City";
    public static final String MOSTLY_CITY = "Mostly City";
    public static final String OFF_ROAD = "Off Road";
    public static final String RACE = "Race";

    public static final String JOIN_SIGN_IN = "Join/Sign In";
    public static final String NOT_APPLICABLE = "N/A";

    public static final String ROPIS = "Ropis";
    public static final String BOPIS = "Bopis";
    public static final String ROPIS_LABEL = "Reserve now, pickup in-store";
    public static final String BOPIS_LABEL = "Pay now, pick up in-store";

    public static final String COMPARE = "Compare";
    public static final String COMPARE_UP_TO_3 = "Compare (up to 3)";
    private static final String STORE_REVIEW_PARTIAL_URL = "/solicitedReview?userId=1111&emailAddress=" +
            "ima.customer@discounttire.com&firstName=ima&lastName=customer&storeName=1002&invoiceNumber=5555";

    private static final String PRODUCT_REVIEW_PARTIAL_URL = "&tdate=2014-01-01 &em=ima.customer@discounttire.com" +
            "&fn=ima%20&ln=customer%20&cid=1003014082&tnum=8216496&fpc=";

    private static final String STORE_THANKS_HEADER = "THANKS FOR TAKING TIME TO LET US KNOW HOW WE ARE DOING.";

    private static final String STORE_SINCERELY_APPRECIATE_MESSAGE = "We sincerely appreciate your time and " +
            "feedback. This information will assist other customers like you, with valuable first-hand experience " +
            "about this store and your visit.";

    private static final String PRODUCT_YOU_ARE_ALL_DONE_HEADER = "You're all done! Your review has been submitted";

    private static final String PRODUCT_WE_APPRECIATE_YOU_TAKING_TIME_MESSAGE = "We appreciate you taking the " +
            "time to help us out. Your feedback is very important!\nWe're going to review your input and post it " +
            "to our website within 48 to 72 hours.\nThanks again for your time!";

    public static HashMap<String, String> solicitedReviewPartialURL = new HashMap<>();
    public static final String MAIL_IN_REBATE = "Mail In Rebate";
    public static final String INSTANT_SAVINGS = "Instant Savings";

    public static HashMap<String, String> completedSolicitedReviewHeader = new HashMap<>();

    public static HashMap<String, String> completedSolicitedReviewMessage = new HashMap<>();

    static {
        solicitedReviewPartialURL.put(Constants.STORE, STORE_REVIEW_PARTIAL_URL);
        solicitedReviewPartialURL.put(Constants.PRODUCT, PRODUCT_REVIEW_PARTIAL_URL);
        completedSolicitedReviewHeader.put(Constants.STORE, STORE_THANKS_HEADER);
        completedSolicitedReviewHeader.put(Constants.PRODUCT, PRODUCT_YOU_ARE_ALL_DONE_HEADER);
        completedSolicitedReviewMessage.put(Constants.STORE, STORE_SINCERELY_APPRECIATE_MESSAGE);
        completedSolicitedReviewMessage.put(Constants.PRODUCT, PRODUCT_WE_APPRECIATE_YOU_TAKING_TIME_MESSAGE);
    }

    public static final String SIGN_IN_TO_SKIP_THIS_STEP = "sign in to skip this step.";
    public static final String MOST_RECENT = "Most Recent";
    public static final String LOWEST_RATED = "Lowest Rated";
    public static final String PERCENTAGE = "%";
    public static final String MAP = "MAP";

    public static final String RESULTS_PAGE = "Results Page";
    public static final String STORE_REVIEWS = "Store Reviews";

    public static final String THANKSGIVING_DAY = "Thanksgiving Day";
    public static final String STORE_CLOSED = "Store Closed";
    public static final String IMAGE_FILEPATH_DT = "/_ui/responsive/theme-dt-common/images/store-img-dt.png";
    public static final String IMAGE_FILEPATH_AT = "/_ui/responsive/theme-dt-common/images/store-img-at.jpg";


    public static final String CHECK_NEARBY_STORES = "Check nearby stores";

    public static final String WARRANTY = "Warranty";
}