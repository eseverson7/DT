package ovc.data;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class OvcData {

    private final Logger LOGGER = Logger.getLogger(OvcData.class.getName());

    public String feature;
    public String CustomerVehicle;
    public String ReturnVehicle;
    public String NewVehicle;
    public String ItemNumber;
    public String ItemNumber2;
    public String ItemNumber3;
    public String ItemNumber4;
    public String ItemNumber5;
    public String CustomerId;
    public String CustomerName;
    public String NewCustomerName;
    public String FirstName;
    public String LastName;
    public String NewFirstName;
    public String NewLastName;
    public String BusinessName;
    public String CompanyName;
    public String Phone;
    public String HomePhone;
    public String WorkPhone;
    public String Mobile;
    public String Email;
    public String Address;
    public String City;
    public String State;
    public String Zip;
    public String ArFirstName;
    public String ArLastName;
    public String ArCompanyName;
    public String ArCustomerVehicle;
    public String ArPhone;
    public String ArEmail;
    public String ArAddress;
    public String ArCity;
    public String ArState;
    public String ArZip;
    public String ArRoa;
    public String ArPurchaseOrder;
    public String ArTransactionId;
    public String ArAccountNumber;
    public String ArCustomer;
    public String ArTown;
    public String CustomerNotes;
    public String TransactionId;
    public String StoreLocation;
    public String Year;
    public String Make;
    public String Model;
    public String Trim;
    public String Assembly;
    public String Selections;
    public String AddedVehicle;
    public String TireName;
    public String TireSku;
    public String WheelName;
    public String WheelSku;
    public String rimDiameter;
    public String AccessoryTPMSName;
    public String AccessoryTPMSSku;
    public String AccessoryValveStemName;
    public String AccessoryValveStemSku;
    public String brandCategory;
    public String loadRange;
    public String aspectRatio;
    public String CertificationNumber;
    public String Price1;
    public String Price2;
    public String Price3;
    public String Price4;
    public String PurchaseOrder;
    public String TotalPrice;
    public String AccessoryNumber;
    public String wheelColor;
    public String MFGAuth;
    public String DOTNumber;
    public String WarrantyItem;
    public String VRItem1;
    public String VRItem2;
    public String VRItem3;
    public String VRItem4;
    public String PayrollId;
    public String CancelItem;
    public String CouponCode;
    public String Vendor;
    public String ManagedInventorySite;
    public String NearbyStoresSite;
    public String Certificate;
    public String FitmentValue;
    public String LaborSku;
    public String LaborName;
    public String VendorNumber;
    public String GiftCertificateNumber;
    public String LicenseNumber;
    public String LicenseState;
    public String LicenseExpiration;
    public String RoutingNumber;
    public String AccountNumber;
    public String CheckNumberInteger;
    public String IssuingInstitution;
    public String StaggeredYear;
    public String StaggeredMake;
    public String StaggeredModel;
    public String StaggeredTrim;
    public String StaggeredAssembly;
    public String StaggeredItemNumber;
    public String StaggeredSelections;
    public String PulledQuantity;
    public String TireSkuLinkedProduct;
    public String TireCertificateOfRepair;
    public String TireDisposalFee;
    public String Mileage;
    public String Condition;
    public String MileageWarranty;
    public String StoreDescription;
    public String StoreSize;
    public String LoadRange;
    public String GBB;
    public String state;
    public String MiscWheelGtin;
    public String MiscWheelVpn;
    public String MiscTireGtin;
    public String MiscTireVpn;
    public String MiscWheelArticle;
    public String MiscTireArticle;
    public String storePath;
    public String productName;
    public String checkoutOption;
    public String reason;
    public String customerType;
    public String FullCustomerVehicleDescription;
    public String Misc_Wheel_Width;
    public String Misc_Rim_Diameter;
    public String Misc_Brand;
    public String Misc_Number_Of_Bolts;
    public String Misc_Bolt_Circle_1;
    public String Misc_Bolt_Circle_2;
    public String Misc_Offset;
    public String Misc_Wheel_Color;
    public String Misc_Finish;
    public String Misc_Wheel_Accent;
    public String Misc_Hub_Bore;
    public String Misc_Line;
    public String Misc_Rim_Size;

    public String Misc_Service_Type;
    public String Misc_Cross_Section;
    public String Misc_Aspect_Ratio;
    public String Misc_Tire_Construction;
    public String Misc_Load_Index_Primary;
    public String Misc_Speed_Rating;
    public String Misc_Load_Range;
    public String Misc_Side_wall_Description;
    public String Misc_Family;
    public String Misc_Runflat;

    public String FETArticle;

    private enum OvcDataType {
        AirCheck,
        Appointment,
        CashManagement,
        CrossApplication,
        CSL,
        CustomerAr,
        Customer,
        Fitment,
        Inventory,
        Layaway,
        Lookup,
        OrderAdjustment,
        Payment,
        POSInactive,
        Promotions,
        ReturnExchangeAdjust,
        SmokeTests,
        TrainingMode,
        TransactionSearch,
        VTV;

        public static OvcDataType ovcDataForName(String feature) throws IllegalArgumentException {
            for (OvcDataType c : values()) {
                if (c.toString().equalsIgnoreCase(feature)) {
                    return c;
                }
            }
            throw ovcDataNotFound(feature);
        }

        private static IllegalArgumentException ovcDataNotFound(String outcome) {
            return new IllegalArgumentException(("Invalid feature [" + outcome + "]"));
        }
    }

    public OvcData() {
    }

    /**
     * Stores and returns specific feature data based on the feature passed in
     *
     * @param feature Name of feature
     * @return OvcData  Data values for feature
     */
    public OvcData getOvcData(String feature) {
        OvcDataType ovcDataType = OvcDataType.ovcDataForName(feature);
        OvcData ovcData = new OvcData();

        switch (ovcDataType) {
            case AirCheck:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.rimDiameter = "16.0";
                ovcData.aspectRatio = "75";
                ovcData.Year = "2011";
                ovcData.Make = "Honda";
                ovcData.Model = "Civic";
                ovcData.Trim = "Coupe EX";
                ovcData.Assembly = "205 /55 R16 SL";
                ovcData.Selections = "195/65-R15";
                ovcData.aspectRatio = "75";
                break;
            case Appointment:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "45963";
                ovcData.ItemNumber3 = "90079";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.rimDiameter = "16.0";
                ovcData.aspectRatio = "75";
                ovcData.Mileage = "20000";
                ovcData.Condition = "Good";
                break;
            case CashManagement:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.Address = "4217 Bannock Ave";
                ovcData.City = "San Diego";
                ovcData.State = "CA";
                ovcData.Zip = "92117-2808";
                ovcData.TransactionId = "10351057813672";
                ovcData.StoreLocation = "1682";
                ovcData.WheelName = "V378";
                ovcData.WheelSku = "10958";
                ovcData.rimDiameter = "17.0";
                ovcData.aspectRatio = "75";
                ovcData.PayrollId = "12345";
                ovcData.Vendor = "AMERICAN EAGLE WHEEL-AZ";
                ovcData.ArFirstName = "REDEKER EXCAVATING INC";
                ovcData.ArLastName = "AutoLastName";
                ovcData.ArPhone = "3036812218";
                ovcData.ArEmail = "Automation_Team@discounttire.com";
                ovcData.ArAddress = "2931 N US HIGHWAY 85";
                ovcData.ArCity = "CASTLE ROCK";
                ovcData.ArState = "CO";
                ovcData.ArZip = "80109-9403";
                ovcData.ArCompanyName = "REDEKER EXCAVATING INC";
                ovcData.ArRoa = "0000026647";
                ovcData.ArTransactionId = "10351057813672";
                ovcData.ArAccountNumber = "8675309";
                ovcData.LicenseNumber = "A123456789";
                ovcData.LicenseState = "AZ";
                ovcData.LicenseExpiration = "15301990";
                ovcData.RoutingNumber = "067006432";
                ovcData.AccountNumber = "1010101010";
                ovcData.CheckNumberInteger = "305";
                ovcData.IssuingInstitution = "123";
                break;
            case CSL:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "10958";
                ovcData.ItemNumber3 = "80219";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.Year = "2011";
                ovcData.Make = "Honda";
                ovcData.Model = "Civic";
                ovcData.Trim = "Coupe EX";
                ovcData.Assembly = "205 /55 R16 SL";
                ovcData.DOTNumber = "LMRL5107";
                ovcData.PulledQuantity = "1";
                ovcData.RoutingNumber = "067006432";
                ovcData.AccountNumber = "1010101010";
                ovcData.CheckNumberInteger = "305";
                ovcData.Mileage = "20000";
                ovcData.Condition = "Mad Decent";
                break;
            case Customer:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2014 BMW 435i xDrive";
                ovcData.NewVehicle = "2018 New Test";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "80225";
                ovcData.CustomerId = "c000009135";
                ovcData.CustomerName = "DAVID JAVADI";
                ovcData.NewCustomerName = "Tim Donahue";
                ovcData.FirstName = "DAVID";
                ovcData.LastName = "JAVADI";
                ovcData.NewFirstName = "Tim";
                ovcData.NewLastName = "Donahue";
                ovcData.Mobile = "8585681111";
                ovcData.HomePhone = "8585681112";
                ovcData.WorkPhone = "8585681113";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.Year = "2014";
                ovcData.Make = "BMW";
                ovcData.Model = "435i xDrive";
                ovcData.Trim = "Coupe";
                ovcData.Assembly = "225 /45 R18 SL";
                ovcData.AddedVehicle = "2014 BMW 435i xDrive";
                ovcData.aspectRatio = "75";
                ovcData.state = "Arizona";
                ovcData.FullCustomerVehicleDescription = "2014 BMW 435i xDrive Coupe 225 /45 R18 SL";
                ovcData.ArAccountNumber = "0000300000";
                ovcData.ArCustomer= "MISCELLANEOUS ACCOUNT";
                ovcData.ArCompanyName = "Automation Inc.";
                ovcData.ArFirstName = "Automated";
                ovcData.ArLastName = "Testing";
                ovcData.ArAddress = "Automation Ln.";
                ovcData.ArTown = "Scottsdale";
                ovcData.ArZip = "85255";
                ovcData.ArPurchaseOrder = "12312387";
                ovcData.Mileage = "20000";
                ovcData.ArCompanyName = "REDEKER EXCAVATING INC";
                break;
            case Fitment:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.NewVehicle = "2012 Honda Civic";
                ovcData.ItemNumber = "19454";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Year = "2011";
                ovcData.Make = "Honda";
                ovcData.Model = "Civic";
                ovcData.Trim = "Coupe EX";
                ovcData.Assembly = "205 /55 R16 SL";
                ovcData.Selections = "205/55-R16";
                ovcData.StaggeredSelections = "285/30-R20";
                ovcData.aspectRatio = "75";
                ovcData.VRItem1 = "37031";
                ovcData.FitmentValue = "37031 - 205/55R16 91W B BFG GFRCE SPCM2";
                ovcData.StaggeredYear = "2014";
                ovcData.StaggeredMake = "Chevrolet";
                ovcData.StaggeredModel = "Corvette";
                ovcData.StaggeredTrim = "Z51";
                ovcData.StaggeredAssembly = "F 245 /35 R19 SL - R 285 /30 R20 SL";
                ovcData.StaggeredItemNumber = "11643";
                break;
            case Inventory:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10958";
                ovcData.ItemNumber2 = "10001";
                ovcData.ItemNumber3 = "90079";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.aspectRatio = "75";
                ovcData.ManagedInventorySite = "9202";
                ovcData.NearbyStoresSite = "1249";
                ovcData.Vendor = "MICHELIN";
                ovcData.VendorNumber = "10016";
                break;
            case Layaway:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10002";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.aspectRatio = "75";
                ovcData.VRItem1 = "Layaway Requirements:";
                ovcData.VRItem2 = "Initial deposit of $10.00 required";
                ovcData.VRItem3 = "Your layaway balance is";
                break;
            case Lookup:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.TireName = "MOTIVO";
                ovcData.ItemNumber = "80225";
                ovcData.TireSku = "10003";
                ovcData.WheelName = "V378";
                ovcData.WheelSku = "10958";
                ovcData.rimDiameter = "17.0";
                ovcData.aspectRatio = "75";
                ovcData.AccessoryTPMSName = "TPMS SENSOR";
                ovcData.AccessoryTPMSSku = "45963";
                ovcData.AccessoryValveStemName = "VALVE STEM";
                ovcData.AccessoryValveStemSku = "80145";
                ovcData.brandCategory = "004";
                ovcData.loadRange = "E1";
                ovcData.wheelColor = "CS";
                ovcData.LaborName = "";
                ovcData.LaborSku = "80085";
                ovcData.CouponCode = "80222";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.TireSkuLinkedProduct = "10034";
                ovcData.TireCertificateOfRepair = "80017";
                ovcData.TireDisposalFee = "80224";
                ovcData.ItemNumber2 = "10001";
                ovcData.MileageWarranty = "60000";
                ovcData.StoreDescription = "CON CONTINENTAL MOTIVO";
                ovcData.StoreSize = "245 /55 R18 103W SL BSW SR";
                ovcData.LoadRange = "SL";
                ovcData.GBB = "BETTR";
                ovcData.MiscWheelGtin = "0000";
                ovcData.MiscWheelVpn = "";
                ovcData.MiscTireGtin = "0000";
                ovcData.MiscTireVpn = "";
                ovcData.MiscWheelArticle = "55555";
                ovcData.MiscTireArticle = "33333";
                ovcData.Misc_Wheel_Width = "15";
                ovcData.Misc_Rim_Diameter = "15";
                ovcData.Misc_Brand = "AKITA";
                ovcData.Misc_Number_Of_Bolts = "4";
                ovcData.Misc_Bolt_Circle_1 = "200.00";
                ovcData.Misc_Bolt_Circle_2 = "100.00";
                ovcData.Misc_Offset = "1";
                ovcData.Misc_Wheel_Color = "SILVER";
                ovcData.Misc_Finish = "METALLIC";
                ovcData.Misc_Wheel_Accent = "BLACK ACCENT";
                ovcData.Misc_Hub_Bore = "7";
                ovcData.Misc_Line = "10";
                ovcData.Misc_Rim_Size = "17";
                ovcData.Misc_Service_Type = "ATV";
                ovcData.Misc_Cross_Section = "14";
                ovcData.Misc_Aspect_Ratio = "15.00";
                ovcData.Misc_Tire_Construction = "RADIAL";
                ovcData.Misc_Speed_Rating = "R";
                ovcData.Misc_Load_Range = "4P";
                ovcData.Misc_Side_wall_Description = "GOLD AND WHITE";
                ovcData.Misc_Family = "BLIZZAK";
                ovcData.Misc_Runflat = "RUN FLAT";
                ovcData.FETArticle = "FET_PRODUCT";
                break;
            case OrderAdjustment:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "10958";
                ovcData.ItemNumber3 = "45963";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.TransactionId = "11795838808372";
                ovcData.aspectRatio = "75";
                ovcData.MFGAuth = "8675309";
                ovcData.DOTNumber = "LMRL5107";
                ovcData.WarrantyItem = "10204";
                break;
            case Payment:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "10958";
                ovcData.ItemNumber3 = "45963";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.BusinessName = "Lepisto Inc.";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.Year = "2011";
                ovcData.Make = "Honda";
                ovcData.Model = "Civic";
                ovcData.Trim = "Coupe EX";
                ovcData.Assembly = "205 /55 R16 SL";
                ovcData.aspectRatio = "75";
                ovcData.GiftCertificateNumber = "9130000244";
                ovcData.VRItem1 = "Gift Certificate (GC) $50.00";
                ovcData.LicenseNumber = "A123456789";
                ovcData.LicenseState = "AZ";
                ovcData.LicenseExpiration = "15301990";
                ovcData.RoutingNumber = "067006432";
                ovcData.AccountNumber = "1010101010";
                ovcData.CheckNumberInteger = "305";
                ovcData.IssuingInstitution = "123";
                ovcData.ArCustomerVehicle = "2016 Arctic Cat 700";
                ovcData.ArFirstName = "REDEKER EXCAVATING INC";
                ovcData.ArLastName = "AutoLastName";
                ovcData.ArPhone = "3036812218";
                ovcData.ArEmail = "Automation_Team@discounttire.com";
                ovcData.ArAddress = "2931 N US HIGHWAY 85";
                ovcData.ArCity = "CASTLE ROCK";
                ovcData.ArState = "CO";
                ovcData.ArZip = "80109-9403";
                ovcData.ArCompanyName = "REDEKER EXCAVATING INC";
                ovcData.ArRoa = "0000026647";
                ovcData.ArTransactionId = "10351057813672";
                ovcData.ArAccountNumber = "8675309";
                ovcData.ArPurchaseOrder = "12312387";
                break;
            case POSInactive:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "10958";
                ovcData.ItemNumber3 = "45963";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                break;
            case Promotions:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "10958";
                ovcData.ItemNumber3 = "45963";
                ovcData.ItemNumber4 = "73570";
                ovcData.ItemNumber5 = "80219";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.aspectRatio = "75";
                ovcData.CancelItem = "Customer Satisfaction";
                ovcData.CouponCode = "87997";
                break;
            case ReturnExchangeAdjust:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ReturnVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "90079";
                ovcData.ItemNumber3 = "45963";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.aspectRatio = "75";
                ovcData.Price1 = "-$178.00";
                ovcData.Price2 = "-$30.00";
                ovcData.Price3 = "-$38.42";
                ovcData.Price4 = "-$38.00";
                ovcData.TotalPrice = "$106.00";
                ovcData.AccessoryNumber = "10007";
                ovcData.Mileage = "20000";
                ovcData.Condition = "Mad Decent";
                break;
            case SmokeTests:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "26899";
                ovcData.ItemNumber3 = "45963";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.CustomerNotes = "Dummy Notes for Schedule Appointment";
                ovcData.Year = "2011";
                ovcData.Make = "Honda";
                ovcData.Model = "Civic";
                ovcData.Trim = "Coupe EX";
                ovcData.Assembly = "205 /55 R16 SL";
                ovcData.Selections = "195/65-R15";
                ovcData.aspectRatio = "75";
                ovcData.Mileage = "20000";
                ovcData.Condition = "Poor";
                ovcData.WheelName = "Vector";
                ovcData.WheelSku = "13258";
                break;
            case TransactionSearch:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "90079";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.TransactionId = "12087668413672";
                ovcData.TireSku = "10003";
                ovcData.aspectRatio = "75";
                ovcData.Price1 = "-$106.00";
                ovcData.Vendor = "MICHELIN";
                ovcData.VendorNumber = "10016";
                break;
            case TrainingMode:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.ItemNumber2 = "45963";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
                ovcData.Phone = "8585681111";
                ovcData.Email = "Automation_Team@discounttire.com";
                ovcData.Year = "2011";
                ovcData.Make = "Honda";
                ovcData.Model = "Civic";
                ovcData.Trim = "Coupe EX";
                ovcData.Assembly = "205 /55 R16 SL";
                ovcData.Selections = "195/65-R15";
                ovcData.aspectRatio = "75";
                break;
            case VTV:
                ovcData.feature = feature;
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.ItemNumber = "10001";
                ovcData.CustomerId = "c000009024";
                ovcData.CustomerName = "ALEX LEPISTO";
                ovcData.FirstName = "ALEX";
                ovcData.LastName = "LEPISTO";
            case CrossApplication:
                //Store ZIP: 78223, Address: 3118 Goliad Rd. San Antonio
                ovcData.storePath = "/store/tx/san-antonio/s/1682";
                ovcData.ItemNumber = "34302";
                ovcData.productName = "Defender A/S";
                ovcData.checkoutOption = "default";
                ovcData.reason = "Make an appointment at a later time";
                ovcData.customerType = "DEFAULT_CUSTOMER_TX";
                ovcData.Year = "2011";
                ovcData.Make = "Honda";
                ovcData.Model = "Civic";
                ovcData.Trim = "Coupe EX";
                ovcData.Assembly = "205 /55 R16 SL";
                ovcData.CustomerVehicle = "2011 Honda Civic";
                ovcData.Mileage = "20000";
                ovcData.Condition = "Good";
        }
        return ovcData;
    }

    /**
     * Returns field value for feature through reflection
     *
     * @param feature   Feature to extract data field value from
     * @param dataField The data field value to extract
     * @return String    The data field value
     * @throws Exception Access exception
     */
    public String getFieldValue(String feature, String dataField) throws Exception {
        OvcData ovcData = new OvcData();
        OvcData featureData = getOvcData(feature);
        Field featureField = ovcData.getClass().getField(dataField);
        return featureField.get(featureData).toString();
    }

    /**
     * Returns string concatenation of feature data
     *
     * @param ovcData OvcData instance
     * @return String of feature data
     */
    public String getOvcDataString(OvcData ovcData) {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        Field[] fields = ovcData.getClass().getDeclaredFields();
        result.append(newLine);

        //print field names paired with their values if not null
        for (Field field : fields) {
            try {
                if (field.get(ovcData) != null) {
                    result.append("  ");
                    result.append(field.getName());
                    result.append(": ");
                    result.append(field.get(ovcData));
                    result.append(newLine);
                }
            } catch (IllegalAccessException ex) {
                LOGGER.info(ex.toString());
            }
        }

        return result.toString();
    }
}