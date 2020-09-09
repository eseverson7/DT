package dtc.data;

import common.Config;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 9/22/16.
 */
public class Customer {

    private final Logger LOGGER = Logger.getLogger(Customer.class.getName());

    public String customerType;
    public String firstName;
    public String lastName;
    public String address1;
    public String address2;
    public String country;
    public String state;
    public String city;
    public String zip;
    public String phone;
    public String phoneType;
    public String email = "DTC_IT_QA_Auto@discounttire.com";
    public String emailDtd = "ima@discounttire.com";
    public String emailLower = "qatest@discounttire.com";
    public String emailUpper = "QATEST@DISCOUNTTIRE.COM";
    public String emailAt = "DTC_IT_QA_Auto@americastire.com";
    public String paypalUser = "Robin_Test@discounttireco.com";
    public String paypalPass = "dtctest1";
    public String paypalAddress;
    private String emailTimeStamp;
    public String emailPassword;
    public String stateTaxQA;
    public String cityTaxQA;
    public String otherTaxQA;
    public String stateTaxSTG;
    public String cityTaxSTG;
    public String otherTaxSTG;
    //region Credit Card Info
    public String ccNumAmEx = "378282246310005";
    public String ccNumDiscover = "6011111111111117";
    public String ccNumMaster = "5555555555554444";
    public String ccNumVisa = "4111111111111111";
    public String ccNumVisa2 = "4445222299990007";
    public String ccNumCCO = "6501590705828755";
    public String ccNumAmEx_bopis = "341111597242000";
    public String ccNumDiscover_bopis = "6011799900001240";
    public String ccNumMaster_bopis = "5444009999222205";
    public String ccNumCCO_bopis = "6501590054801155";
    public String expDateMM;
    public String expDateYY;

    public String ccNum = ccNumVisa;
    public String cvn = "1234";
    public String cvn2 = "382";
    public String cvn3 = "111";
    public String cvv = "906";
    public String cvv2 = "079";
    public String zeroTax = "0.0";
    //endregion

    private void setEmailTimeStamp(String scenarioTimeStamp) {
        this.emailTimeStamp = scenarioTimeStamp;
    }

    private enum CustomerType {
        DEFAULT_CUSTOMER_AZ,
        DEFAULT_CUSTOMER_FL,
        DEFAULT_CUSTOMER_GA,
        DEFAULT_CUSTOMER_LA,
        DEFAULT_CUSTOMER_OH,
        DEFAULT_CUSTOMER_OH_2,
        DEFAULT_CUSTOMER_TX,
        DEFAULT_CUSTOMER_CAN,
        PAYPAL_CUSTOMER_AZ,
        PAYPAL_CUSTOMER_OH,
        APO_CUSTOMER,
        FPO_CUSTOMER,
        EMAIL_VALIDATION_CUSTOMER,
        DEFAULT_CUSTOMER_HI,
        DEFAULT_CUSTOMER_IT,
        DEFAULT_CUSTOMER_MX,
        UPPERCASE_CUSTOMER_CAN,
        LOWERCASE_CUSTOMER_CAN,
    	DEFAULT_CUSTOMER_CA,
    	CAR_CARE_ONE_CUSTOMER,
        DEFAULT_CUSTOMER_DTD,
        DEFAULT_CUSTOMER_CA_2,
    	DEFAULT_MYACCOUNT_CUSTOMER,
        DEFAULT_CUSTOMER_BOPIS_VISA,
        DEFAULT_CUSTOMER_BOPIS_MASTERCARD,
        DEFAULT_CUSTOMER_BOPIS_DISCOVER,
        DEFAULT_CUSTOMER_BOPIS_AMEX,
        DEFAULT_CUSTOMER_BOPIS_CC1;
    	
        public static CustomerType customerForName(String customer) throws IllegalArgumentException {
            for (CustomerType c : values()) {
                if (c.toString().equalsIgnoreCase(customer)) {
                    return c;
                }
            }
            throw customerNotFound(customer);
        }

        private static IllegalArgumentException customerNotFound(String outcome) {
            return new IllegalArgumentException(("Invalid customer [" + outcome + "]"));
        }
    }

    public Customer() {
    }

    /**
     * Stores and returns specific customer dtc.data based on the customer type passed in
     *
     * @param custType Customer type by state
     * @return Customer values
     */
    public Customer getCustomer(String custType) {
        LOGGER.info("getCustomer started with custType: " + custType);
        CustomerType customerType = CustomerType.customerForName(custType);
        Customer customer = new Customer();

        if (Config.getSiteRegion().equalsIgnoreCase(ConstantsDtc.AT))
            email = email.replaceAll(ConstantsDtc.DISCOUNT_TIRE, ConstantsDtc.AMERICAS_TIRE);

        switch (customerType) {
            case DEFAULT_CUSTOMER_AZ:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "AZ-TEST";
                customer.address1 = "E 67 Elwood St";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "85040";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = "0.056";
                customer.cityTaxQA = "0.02051";
                customer.otherTaxQA = "0.013";
                customer.stateTaxSTG = "0.056";
                customer.cityTaxSTG = "0.02051";
                customer.otherTaxSTG = "0.013";
                break;
            case DEFAULT_CUSTOMER_FL:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "FL-TEST";
                customer.address1 = "3700 E Fiske Blvd";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Florida";
                customer.city = "Rockledge";
                customer.zip = "32955";
                customer.phone = "777-888-8888";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = "0.06";
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = "0.005";
                customer.stateTaxSTG = "0.06";
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = "0.01";                
                break;
            case DEFAULT_CUSTOMER_GA:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "GA-TEST";
                customer.address1 = "800 Spring St NW";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Georgia";
                customer.city = "Atlanta";
                customer.zip = "30308";
                customer.phone = "888-888-9999";
                customer.phoneType = "Home";
                customer.email = emailLower;
                customer.emailPassword = "Password4QA!";
                customer.ccNum = ccNumVisa2;
                customer.cvn = cvn2;
                customer.stateTaxQA = "0.04";
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = "0.03";
                customer.stateTaxSTG = "0.04";
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = "0.03";                
                break;
            case DEFAULT_CUSTOMER_LA:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "LA-TEST";
                customer.address1 = "2018 Ambassador Caffery Pkwy";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Louisiana";
                customer.city = "Lafayette";
                customer.zip = "70506";
                customer.phone = "888-888-8888";
                customer.phoneType = "Home";
                customer.email = emailUpper;
                customer.emailPassword = "Password4QA!";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = zeroTax;
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = zeroTax;
                customer.stateTaxSTG = "0.05";
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = "0.0475";                
                break;
            case DEFAULT_CUSTOMER_OH:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "OH-TEST";
                customer.address1 = "1421 Saint Clair Ave NE";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Ohio";
                customer.city = "Cleveland";
                customer.zip = "44114";
                customer.phone = "555-555-5555";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn2;
                customer.stateTaxQA = "0.0575";
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = "0.0175";
                customer.stateTaxSTG = "0.0575";
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = "0.015";                
                break;
            case DEFAULT_CUSTOMER_OH_2:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "OH-TEST-2";
                customer.address1 = "590 Taylor Rd";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Ohio";
                customer.city = "Gahanna";
                customer.zip = "43230";
                customer.phone = "555-555-5555";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNumVisa2;
                customer.cvn = cvn;
                break;
            case DEFAULT_CUSTOMER_TX:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "TX-TEST";
                customer.address1 = "3301 Palmer Hwy";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Texas";
                customer.city = "Texas City";
                customer.zip = "77590";
                customer.phone = "777-777-7777";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = "0.0625";
                customer.cityTaxQA = "0.0175";
                customer.otherTaxQA = "0.0025";
                customer.stateTaxSTG = "0.0625";
                customer.cityTaxSTG = "0.01";
                customer.otherTaxSTG = "0.01";                
                break;
            case DEFAULT_CUSTOMER_CAN:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "CAN-TEST";
                customer.address1 = "372 Rundleview Drive NE";
                customer.address2 = "";
                customer.country = "Canada";
                customer.state = "Alberta";
                customer.city = "Calgary";
                customer.zip = "T1Y 1H3";
                customer.phone = "4806066000";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = zeroTax;
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = zeroTax;
                customer.stateTaxSTG = zeroTax;
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = zeroTax;                
                break;
            case PAYPAL_CUSTOMER_AZ:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "AZ-TEST-PAYPAL";
                customer.address1 = "E 67 Elwood St";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "85040";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = email;
                customer.paypalUser = paypalUser;
                customer.paypalPass = paypalPass;
                customer.paypalAddress = "20225 N. Scottsdale, Scottsdale, AZ 85255";
                customer.stateTaxQA = zeroTax;
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = zeroTax;
                customer.stateTaxSTG = zeroTax;
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = zeroTax;                
                break;
            case PAYPAL_CUSTOMER_OH:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "OH-TEST-PAYPAL";
                customer.address1 = "1421 Saint Clair Ave NE";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Ohio";
                customer.city = "Cleveland";
                customer.zip = "44114";
                customer.phone = "555-555-5555";
                customer.phoneType = "Home";
                customer.email = email;
                customer.paypalUser = paypalUser;
                customer.paypalPass = paypalPass;
                customer.paypalAddress = "10813 Bellaire Rd, Cleveland, OH 44111";
                break;
            case APO_CUSTOMER:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "APO-TEST";
                customer.address1 = "CMR 489 Box 696";
                customer.address2 = "";
                customer.state = "Armed Forces Europe";
                customer.country = "United States";
                customer.city = "APO";
                customer.zip = "09751";
                customer.email = email;
                customer.phone = "1112223333";
                customer.phoneType = "Mobile";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = zeroTax;
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = zeroTax;
                customer.stateTaxSTG = zeroTax;
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = zeroTax;                
                break;
            case FPO_CUSTOMER:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "FPO-TEST";
                customer.address1 = "PSC 812 Box 3238";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Armed Forces Europe";
                customer.city = "FPO";
                customer.zip = "09627";
                customer.email = email;
                customer.phone = "4806066000";
                customer.phoneType = "Mobile";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = zeroTax;
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = zeroTax;
                customer.stateTaxSTG = zeroTax;
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = zeroTax;                
                break;
            case EMAIL_VALIDATION_CUSTOMER:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "EMAIL-TEST";
                customer.address1 = "21555 N. 56th Street";
                customer.address2 = "Apr #2098";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "85054";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email ="qatest@discounttire.com";
                customer.emailPassword = "Password4QA!";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                break;
            case DEFAULT_CUSTOMER_HI:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "HI-TEST";
                customer.address1 = "2040 Kinoole St";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Hawaii";
                customer.city = "Hilo";
                customer.zip = "96720";
                customer.phone = "666-666-6666";
                customer.phoneType = "Mobile";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = zeroTax;
                customer.cityTaxQA = zeroTax;
                customer.otherTaxQA = zeroTax;
                customer.stateTaxSTG = zeroTax;
                customer.cityTaxSTG = zeroTax;
                customer.otherTaxSTG = zeroTax;                
                break;
            case UPPERCASE_CUSTOMER_CAN:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "CAN-TEST";
                customer.address1 = "1121 AVENUE D N";
                customer.address2 = "";
                customer.country = "CANADA";
                customer.state = "SASKATCHEWAN";
                customer.city = "SASKATOON";
                customer.zip = "S7L1N7";
                customer.phone = "777-888-9999";
                customer.phoneType = "HOME";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                break;
            case LOWERCASE_CUSTOMER_CAN:
                customer.customerType = custType;
                customer.firstName = "ima";
                customer.lastName = "can-test";
                customer.address1 = "445 rue lavigueur";
                customer.address2 = "";
                customer.country = "canada";
                customer.state = "quebec";
                customer.city = "quebec city";
                customer.zip = "g1r1b6";
                customer.phone = "666-666-6666";
                customer.phoneType = "Mobile";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                break;
            case DEFAULT_CUSTOMER_CA:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "CA-TEST";
                customer.address1 = "36 Leila Ave";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "California";
                customer.city = "Redding";
                customer.zip = "96002";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = emailAt;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = "0.0725";
                customer.cityTaxQA = "0.00";
                customer.otherTaxQA = "0.005";
                customer.stateTaxSTG = "0.0725";
                customer.cityTaxSTG = "0.00";
                customer.otherTaxSTG = "0.005";
                break;
            case CAR_CARE_ONE_CUSTOMER:
                customer.customerType = custType;
                customer.firstName = "QA Test";
                customer.lastName = "QA Test";
                customer.address1 = "20225 N Scottsdale Rd";
                customer.address2 = "";
                customer.state = "Arizona";
                customer.country = "United States";
                customer.city = "Scottsdale";
                customer.zip = "85255";
                customer.email = "puja.patel@discounttire.com";
                customer.phone = "4806066000";
                customer.phoneType = "Mobile";
                customer.ccNum = ccNumCCO;
                customer.cvn = cvv;
                break;
            case DEFAULT_CUSTOMER_CA_2:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "CA-TEST";
                customer.address1 = "12018 Central Ave";
                customer.address2 = "";
                customer.state = "California";
                customer.country = "United States";
                customer.city = "Chino";
                customer.zip = "91710";
                customer.email = "puja.patel@discounttire.com";
                customer.phone = "1112223333";
                customer.phoneType = "Mobile";
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.stateTaxQA = "0.0725";
                customer.cityTaxQA = "0.00";
                customer.otherTaxQA = "0.005";
                customer.stateTaxSTG = "0.0725";
                customer.cityTaxSTG = "0.00";
                customer.otherTaxSTG = "0.005";
                break;
            case DEFAULT_MYACCOUNT_CUSTOMER:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "TEST-USER";
                customer.address1 = "20225 N Scottsdale Rd";
                customer.address2 = "";
                customer.state = "Arizona";
                customer.country = "United States";
                customer.city = "Scottsdale";
                customer.zip = "85255";
                customer.email ="qatest@discounttire.com";
                customer.emailPassword = "Password4QA!";
                customer.phone = "4806066000";
                customer.phoneType = "Mobile";
                customer.ccNum = ccNumCCO;
                customer.cvn = cvv;
                break;
            case DEFAULT_CUSTOMER_DTD:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "AZ-TEST";
                customer.address1 = "E 67 Elwood St";
                customer.address2 = "Scottsdale";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "85040";
                customer.phone = "4806666666";
                customer.phoneType = "Home";
                customer.email = emailDtd;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.phoneType = "Mobile";
                customer.stateTaxQA = "0.056";
                customer.cityTaxQA = "0.023";
                customer.otherTaxQA = "0.007";
                customer.stateTaxSTG = "0.056";
                customer.cityTaxSTG = "0.023";
                customer.otherTaxSTG = "0.007";
                break;
            case DEFAULT_CUSTOMER_BOPIS_VISA:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "BOPIS-VISA-TEST";
                customer.address1 = "100";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "33606";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.expDateMM = "12";
                customer.expDateYY = "20";
                customer.stateTaxQA = "0.056";
                customer.cityTaxQA = "0.02051";
                customer.otherTaxQA = "0.013";
                customer.stateTaxSTG = "0.056";
                customer.cityTaxSTG = "0.02051";
                customer.otherTaxSTG = "0.013";
                break;
            case DEFAULT_CUSTOMER_BOPIS_MASTERCARD:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "BOPIS-MASTERCARD-TEST";
                customer.address1 = "100";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "33606";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.expDateMM = "12";
                customer.expDateYY = "20";
                customer.stateTaxQA = "0.056";
                customer.cityTaxQA = "0.02051";
                customer.otherTaxQA = "0.013";
                customer.stateTaxSTG = "0.056";
                customer.cityTaxSTG = "0.02051";
                customer.otherTaxSTG = "0.013";
                break;
            case DEFAULT_CUSTOMER_BOPIS_DISCOVER:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "BOPIS-DISCOVER-CARD-TEST";
                customer.address1 = "100";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "33606";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.expDateMM = "12";
                customer.expDateYY = "20";
                customer.stateTaxQA = "0.056";
                customer.cityTaxQA = "0.02051";
                customer.otherTaxQA = "0.013";
                customer.stateTaxSTG = "0.056";
                customer.cityTaxSTG = "0.02051";
                customer.otherTaxSTG = "0.013";
                break;
            case DEFAULT_CUSTOMER_BOPIS_AMEX:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "BOPIS-AMEX-CARD-TEST";
                customer.address1 = "100";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "33606";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.expDateMM = "12";
                customer.expDateYY = "20";
                customer.stateTaxQA = "0.056";
                customer.cityTaxQA = "0.02051";
                customer.otherTaxQA = "0.013";
                customer.stateTaxSTG = "0.056";
                customer.cityTaxSTG = "0.02051";
                customer.otherTaxSTG = "0.013";
                break;
            case DEFAULT_CUSTOMER_BOPIS_CC1:
                customer.customerType = custType;
                customer.firstName = "IMA";
                customer.lastName = "BOPIS-CC1-TEST";
                customer.address1 = "100";
                customer.address2 = "";
                customer.country = "United States";
                customer.state = "Arizona";
                customer.city = "Phoenix";
                customer.zip = "33606";
                customer.phone = "666-666-6666";
                customer.phoneType = "Home";
                customer.email = email;
                customer.ccNum = ccNum;
                customer.cvn = cvn;
                customer.expDateMM = "9";
                customer.expDateYY = "22";
                customer.stateTaxQA = "0.056";
                customer.cityTaxQA = "0.02051";
                customer.otherTaxQA = "0.013";
                customer.stateTaxSTG = "0.056";
                customer.cityTaxSTG = "0.02051";
                customer.otherTaxSTG = "0.013";
                break;
        }
        LOGGER.info("getCustomer completed");
        return customer;
    }

    /**
     * Returns customer dtc.data based on input customer type
     *
     * @param customer customer type
     * @return String of customer dtc.data
     */
    public String getCustomerDataString(Customer customer) {
        LOGGER.info("getCustomerDataString started");
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        Field[] fields = customer.getClass().getDeclaredFields();

        result.append(newLine);

        //print field names paired with their values if not null
        for (Field field : fields) {
            try {
                if (field.get(customer) != null) {
                    if (isPaypalCustomer(customer) &&
                            field.getName().toLowerCase().contains(ConstantsDtc.EMAIL.toLowerCase())) {
                        //Skipping default customer class-scoped dtc.data for Paypal customer.
                    } else if (!isPaypalCustomer(customer) &&
                            field.getName().toLowerCase().contains(ConstantsDtc.PAYPAL.toLowerCase())) {
                        //Skipping paypal customer class-scoped dtc.data for default checkout customer
                    } else {
                        result.append("  ");
                        result.append(field.getName());
                        result.append(": ");
                        result.append(field.get(customer));
                        result.append(newLine);
                    }
                }
            } catch (IllegalAccessException ex) {
                LOGGER.info(ex.toString());
            }
        }

        LOGGER.info("getCustomerDataString completed");
        return result.toString();
    }

    /**
     * Helper method for constructing and clicking an autoClass named element
     *
     * @param customer Customer type
     */
    private boolean isPaypalCustomer(Customer customer) {
        return customer.customerType.toLowerCase().contains(ConstantsDtc.PAYPAL.toLowerCase());
    }
}