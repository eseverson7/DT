@ovc
@customer
Feature: Customer Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcRegression
  @ovcBBA
  @11833
  Scenario Outline: Oneview_customer_vehicle_add (ALM# 11833)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Vehicles List" button on the "Customer" page
    And  I select the "Add Vehicle" button on the "Vehicle View" page
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Save" button on the "Vehicle View" page
    And  I select the vehicle from the vehicle pill on the home page
    Then I verify the "added" vehicle for feature "<Feature>" appears in the customer vehicle list
    When I select customer for feature "<Feature>" on home page
    And  I select the "Add" div tag on the on the "Client Details" page
    And  I select "Add Placeholder Vehicle" on the "Customer" page
    And  I perform a free text vehicle search with values "2018", "New", "Test", "Model" and "Automation"
    And  I select the "Save" button on the "Vehicle View" page
    Then I verify the right nav pane title is "Main"
    And  I verify that the vehicle description for vehicle "NewVehicle" and feature "<Feature>" appears on the home page

    Examples:
      | Feature  |
      | Customer |

  @ovcBBA
  @11553
  Scenario Outline: Oneview_customer_add_customer (ALM# 11553)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    And  I verify "Last Name" field is displayed on customer page
    And  I verify "Phone Number" field is displayed on customer page
    And  I verify "Email" field is displayed on customer page
    And  I verify "Search" button is displayed on customer page
    And  I verify "Clear" button is displayed on customer page
    And  I verify "New" button is displayed on customer page
    And  I verify "Close" button is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt

    Examples:
      | Feature  |
      | Customer |

  @ovcBBA
  @12175
  Scenario Outline: Oneview_customer_Lookup_top_menu (ALM# 12175)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    And  I verify customer vehicle for feature "<Feature>" is present in vehicle list section

    Examples:
      | Feature  |
      | Customer |

  @ovcBBA
  @12179
  Scenario Outline: Oneview_customer_modify_top_menu (ALM# 12179)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    And  I verify customer vehicle for feature "<Feature>" is present in vehicle list section
    When I select "Modify" customer profile
    Then I verify the customer profile field for customer in feature "<Feature>" is displayed
    When I select customer type "Retail" on customer profile
    And  I set the customer profile field "Mobile" value for feature "<Feature>" to "Mobile" on customer page
    And  I set the customer profile field "Home Phone" value for feature "<Feature>" to "HomePhone" on customer page
    And  I set the customer profile field "Work Phone" value for feature "<Feature>" to "WorkPhone" on customer page
    And  I select "Save" customer profile
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    When I select "Modify" customer profile
    Then I verify the customer profile field "Mobile" value for feature "<Feature>" is set to "Mobile" on customer page
    And  I verify the customer profile field "Home Phone" value for feature "<Feature>" is set to "HomePhone" on customer page
    And  I verify the customer profile field "Work Phone" value for feature "<Feature>" is set to "WorkPhone" on customer page

    Examples:
      | Feature  |
      | Customer |

  @ovcBBA
  @12177
  Scenario Outline: Oneview_customer_add_top_menu (ALM# 12177)
  """Additional verification Steps added after 'I verify new customer for feature' to avoid false positive results"""
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I select the "New" button on the "Customer" page
    Then I verify regular customer label is present on customer profile
    When I select customer type "Retail" on customer profile
    And  I set the customer profile field "First Name" value for feature "<Feature>" to "NewFirstName" on customer page
    And  I set the customer profile field "Last Name" value for feature "<Feature>" to "NewLastName" on customer page
    And  I set the customer profile field "Email" value for feature "<Feature>" to "Email" on customer page
    And  I set the customer profile field "Mobile" value for feature "<Feature>" to "Mobile" on customer page
    And  I set the customer profile field "Home Phone" value for feature "<Feature>" to "HomePhone" on customer page
    And  I set the customer profile field "Work Phone" value for feature "<Feature>" to "WorkPhone" on customer page
    And  I select " State/Province" value for feature "<Feature>" for "state"
    And  I select "Save" customer profile
    Then I verify new customer for feature "<Feature>" added to the virtual receipt
    When I select the "Customer" icon from the Global Header
    When I enter "NewFirstName" for feature "<Feature>" into the "First Name"
    When I enter "NewLastName" for feature "<Feature>" into the "Last Name"
    And  I select the "Search" button on the "Customer" page
    And  I select the new customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify new customer for feature "<Feature>" added to the virtual receipt

    Examples:
      | Feature  |
      | Customer |

  @ovcRegression
  @ovcBBA
  @11834
  Scenario Outline: Oneview_customer_vehicle_modify (ALM# 11834)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Vehicles List" button on the "Customer" page
    And  I select the "Modify" button on the "Vehicle View" page
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Save" button on the "Vehicle View" page
    And  I select the vehicle from the vehicle pill on the home page
    Then I verify customer vehicle for feature "<Feature>" is present on vehicle customer popup

    Examples:
      | Feature  |
      | Customer |

  @ovcRegression
  @ovcBBA
  @11835
  Scenario Outline: Oneview_customer_vehicle_remove (ALM# 11835)
  """TODO: New Confirmation popup added recently before removing vehicle"""
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    Then I verify "Vehicles List" button is displayed on customer page
    When I select the "Vehicles List" button on the "Customer" page
    And  I select the "Remove" button on the "Vehicle View" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select customer for feature "<Feature>" on home page
    Then I verify customer vehicle for feature "<Feature>" is not present in vehicle list section

    Examples:
      | Feature  |
      | Customer |

  @ovcBBA
  @12814
  Scenario Outline: Oneview_Customer_Customer_Account_Dasboard_Widgets_Appointment (ALM# 12814)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    When I select customer order transaction that contains matching "<TransactionType>" from the table
    Then I verify "Appointment" button is displayed on the page
    When I select the "Appointment" button on the "Client Details" page
    Then I am brought to the page with header "Schedule Appointment"

    Examples:
      | Feature  | TransactionType |
      | Customer | regular Sale    |

  @ovcBBA
  @12810
  Scenario Outline: Oneview_Customer_Customer_Requirements (ALM# 12810)
    When I select the "VTV" icon from the Global Header
    And  I select the replace "RF" option
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    And  I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    Then I verify the dialog contains "Checkout is not possible without Customer and Vehicle."

    Examples:
      | Feature  |
      | Customer |

  @ovcRegression
  @ovcBBA
  @12811
  Scenario Outline: Oneview_Customer_Customer_Account_Dasboard_Widgets_RFS (ALM# 12811)
  """Preconditions for test case end after saving order receipt"""
    When I select the "VTV" icon from the Global Header
    And  I select the "Replace Tire" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    And  I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "CSL" button on the "Popup" page
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Customer" icon from the Global Header
    And  I enter "FirstName" for feature "<Feature>" into the "First Name"
    And  I enter "LastName" for feature "<Feature>" into the "Last Name"
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify the page currently displayed is the "Client Details" page
    When I select the customer page transaction with the previous receipt number
    And  I select the "Return for Service" button on the "Client Details" page
    And  I select the "No Return" button on the "Order Details" page
    Then I verify the page currently displayed is the "Home" page
    When I enter "ItemNumber2" for feature "<Feature>" in the article search box
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify "ItemNumber2" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "TPMS Status N/A" radio button on the VTV page
    And  I select the "Replace Tire" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    And  I select the first available appointment date
    And  I select "Tire Inspection" as the reason for the appointment
    And  I select the first available appointment time
    Then I verify the selected appointment time is highlighted red
    When I select the "Schedule" button on the "Appointment" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "Ok" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature  |
      | Customer |

  @ovcBBA
  @12809
  Scenario Outline:  Oneview_customer-customer_Transaction_Search_POS_Reprint (ALM# 12809)
  """Preconditions for test case end after saving order receipt
    TODO test will fail here due to Defect 8740
    Test will fail here due to printer configuration not being delivered yet
    TODO: We will have a receipt printed message in future but not the transaction number"""
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Customer" icon from the Global Header
    And  I enter "FirstName" for feature "<Feature>" into the "First Name"
    And  I enter "LastName" for feature "<Feature>" into the "Last Name"
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Vehicles List" button on the "Customer" page
    And  I select the "Select" button on the "Vehicle View" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify the page currently displayed is the "Client Details" page
    When I select the customer page transaction with the previous receipt number
    And  I select the "Reprint Receipt" button on the "Client Details" page
    And  I select the "OK" button on the "Popup" page
    Then I verify that the popup with a "OK" button is closed

    Examples:
      | Feature  |
      | Customer |

  @ovcRegression
  @15352
  Scenario Outline: OVC_VEHICLE_Vehicle_must_display_YMMTA (ALM# 15352)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for vehicle "FullCustomerVehicleDescription" and feature "<Feature>" appears on the home page

    Examples:
      | Feature  |
      | Customer |
      
  @tLogRegression
  @oneTimeARCustomer
  Scenario Outline:  Oneview_onetime_AR_customer (ALM# NONE)
    When I select the "VTV" icon from the Global Header
    And  I select the "Replace Tire" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    And  I select the "Customer" icon from the Global Header
    And  I enter "ArAccountNumber" for feature "<Feature>" into the "AR Account Number"
    And  I select the "Search" button on the "Customer" page
    And  I select the "ArCustomer" for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I enter One Time AR Customer Details for feature "<Feature>"
    And  I select " State/Province" value for feature "<Feature>" for "state"
    And  I set the customer profile field "Mobile" value for feature "<Feature>" to "Mobile" on customer page
    And  I set the customer profile field "Home Phone" value for feature "<Feature>" to "HomePhone" on customer page
    And  I set the customer profile field "Work Phone" value for feature "<Feature>" to "WorkPhone" on customer page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the Add New vehicle button
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Save" button on the "Vehicle View" page
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the first "1" product(s) from the Product Results table
    And  I select the "Add" button on the "Lookup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Accounts Receivable" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    Then I am brought to the page with header "Account Receivable"
    When I enter "ArCompanyName" for "<Feature>" into the "Company Name *" input on the AR Payment page
    And  I enter "ArFirstName" for "<Feature>" into the "Driver's First Name *" input on the AR Payment page
    And  I enter "ArLastName" for "<Feature>" into the "Driver's Last Name *" input on the AR Payment page
    And  I enter "ArAddress" for "<Feature>" into the "Street Address *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "Purchase Order *" input on the AR Payment page
    And  I enter "state" for "<Feature>" into the "State *" input on the AR Payment page
    And  I enter "ArZip" for "<Feature>" into the "Zip Code *" input on the AR Payment page
    And  I enter "Mileage" for "<Feature>" into the "Mileage *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "VIN # *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "License Plate *" input on the AR Payment page
    And  I enter vehicle data for feature "<Feature>" on AR Payment page
    And  I enter state for feature "<Feature>" on AR Payment page
    And  I select the "Save" button on the "AR Payment" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
        
    Examples:
      | Feature  | 
      | Customer |    

  @ovcRegression
  @11307
  Scenario Outline:  OVC_CUSTOMER_CUSTOMER_AR_Customer_search (ALM# 11307)
    When I select the "Customer" icon from the Global Header
    And  I select the "More Filters" drop down to expose all filters
    Then I verify "First Name" field is displayed on customer page
    And  I verify "Last Name" field is displayed on customer page
    And  I verify "Phone Number" field is displayed on customer page
    And  I verify "Address" field is displayed on customer page
    And  I verify "Town/City" field is displayed on customer page
    And  I verify "State/Province" field is displayed on customer page
    And  I verify "AR Account Number" field is displayed on customer page
    And  I verify "Company Name" field is displayed on customer page
    When I select the "More Filters" drop down to expose all filters
    And  I enter "ArCompanyName" for feature "<Feature>" into the "Company Name"
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Details" button on the "Customer" page
    Then I verify inputs on the customer details page are disabled

    Examples:
      | Feature  |
      | Customer |
