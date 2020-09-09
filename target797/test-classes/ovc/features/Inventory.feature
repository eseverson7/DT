@ovc
@inventory
Feature: Inventory Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcBBA
  @11948
  Scenario Outline: Inventory_Stock_Management_Hold_Inventory_Add_Customer_Add_Product_Hold_Inventory_Service_Later (ALM# 11948)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "ItemNumber" for feature "<Feature>" into "SKU" textbox and send Enter key
    And  I select item "<ItemNumber>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Hold Inventory" link on the page
    Then I verify the dialog contains "Hold Inventory"
    When I select the "Yes" button on the "Popup" page
    Then I verify the dialog contains "Checkout Options"
    When I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature   | ItemNumber |
      | Inventory | 10958      |

  @ovcBBA
  @12873
  Scenario Outline: Oneview_Inventory_Stock_Management_Special_Orders_Create_Special_Order_View_in_Alert_Center (ALM# 12873)
  """TODO: Verify order appears in alerts. Waiting for Defect_ID from Nathan Knott"""
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Quantity>" into VTV Vehicle section
    And  I select the "Replace Tire" selection for the "FR" Tire Service section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "ItemNumber" for feature "<Feature>" into "SKU" textbox and send Enter key
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Order" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the displayed "Order" button for site "Managed Inventory" on the Find It page for the feature "<Feature>"
    And  I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "<Comments>" on the Find It page
    And  I fill the "Contact Number" text box with the value "<Contact>" on the Find It page
    Then I verify the "Quantity" text box is "<Quantity>" on the Find It page
    When I select the "Complete Purchase Order" button on the "Find It" page
    And  I wait for the OVC preloader to be no longer visible
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the dialog contains "Appointment"
    And  I verify the dialog contains "Add to CSL"
    And  I verify the dialog contains "Service Later/Complete"
    When I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature   | Quantity | Header | Comments | Contact    | Tender  |
      | Inventory | 2        | Order  | Comments | 9997778888 | 6670.85 |

  @tLogRegression
  @ovcBBA
  @12870
  Scenario Outline: Oneview_Inventory_Stock_Management_Special_Orders_Order_directly_from_manufacturer (ALM# 12870)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Find It" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the "Vendor Order" button on the "Find It" page
    And  I enter "VendorNumber" for feature "<Feature>" into the "Vendor Id"
    And  I select the "Search" button on the "Vendor" page
    And  I select the "Vendor" for feature "<Feature>" from the table on the "Vendor" page
    And  I select the "Select" button on the "Vendor" page
    And  I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "Comments" on the Find It page
    And  I select the "Complete Purchase Order" button on the "Vendor" page
    And  I change tender amount to "3240"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Home" icon from the Global Header
    Then I verify the "ItemNumber2" of the item for feature "<Feature>" appears on the home page
    And  I verify the phrase "Special Order Requirements:" appears on the Virtual Receipt in the color "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    Then I verify the dialog contains "Service Later/Complete"
    When I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature   | Header | Quantity | Tender |
      | Inventory | Order  | 30       | 50000  |

  @12871
  Scenario Outline: Oneview_Inventory_Stock_Management_Special_Orders_Order_from_available_managed_warehouse (ALM# 12871)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Find It" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the displayed "Order" button for site "Managed Inventory" on the Find It page for the feature "<Feature>"
    And  I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "Comments" on the Find It page
    And  I select the "Complete Purchase Order" button on the "Vendor" page
    And  I select the "Home" icon from the Global Header
    Then I verify the "ItemNumber2" of the item for feature "<Feature>" appears on the home page
    And  I verify the phrase "Special Order Requirements:" appears on the Virtual Receipt in the color "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    Then I verify the dialog contains "Service Later/Complete"
    When I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the dialog contains "Printer Configuration Unavailable"
    When I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature   | Header | Quantity | Tender |
      | Inventory | Order  | 30       | 50000  |

  @12872
  Scenario Outline: Oneview_Inventory_Stock_Management_Special_Orders_Order_from_another_store (ALM# 12872)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Order" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the "NEARBY STORES" link on the page
    And  I select the displayed "Order" button for site "Nearby Stores" on the Find It page for the feature "<Feature>"
    And  I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "Comments" on the Find It page
    And  I select the "Complete Purchase Order" button on the "Vendor" page
    And  I select the "Home" icon from the Global Header
    Then I verify the "ItemNumber2" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    Then I verify the dialog contains "Service Later/Complete"
    When I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the dialog contains "Printer Configuration Unavailable"
    When I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature   | Header | Quantity | Tender |
      | Inventory | Order  | 30       | 50000  |