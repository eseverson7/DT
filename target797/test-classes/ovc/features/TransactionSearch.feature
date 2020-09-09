@ovc
@transactionSearch
Feature: Transaction Search Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcBBA
  @12185
  @11836
  Scenario Outline: Oneview_customer_transaction_search_order (ALM# 12185)
    When I select the "Transaction Search" link on the page
    And  I enter Transaction ID for the transaction in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Transaction View" page
    Then I should see "TransactionId" from feature "<Feature>" present on transaction search page
    And  I should see "LastName" from feature "<Feature>" present on transaction search page
    And  I should see "Email" from feature "<Feature>" present on transaction search page

    Examples:
      | Feature           |
      | TransactionSearch |

  @ovcBBA
  @12182
  Scenario Outline: Oneview_transaction_search_pos_add_to_csl (ALM# 12182)
  """TODO: Needs more updates. Consensus from B. Randall on test steps."""
    When I select the "Transaction Search" link on the page
    And  I enter "Layaway" into the "TransactionType" textbox and send Enter key
    And  I enter "LastName" for feature "<Feature>" into "Last Name" textbox and send Enter key
    And  I select transaction order that contains matching "layaway" and click the "Add to CSL" button
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I verify the dialog contains "Successfully Added to CSL"
    When I select the "Ok" button on the "Popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature           |
      | TransactionSearch |

  @ovcBBA
  @12183
  Scenario Outline: Oneview_transaction_search_pos_add_to_appointment (ALM# 12183)
    When I select the "Transaction Search" link on the page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    When I select customer order transaction that contains matching "Email" for feature "<Feature>" from the table
    Then I verify "Appointment" button is displayed on the page
    When I select the "Appointment" button on the "Client History" page
    Then I am brought to the page with header "Schedule Appointment"
    When I select the first available appointment date
    And  I select "Tire Inspection" as the reason for the appointment
    Then I see a listing of available appointment times for either the Morning, Afternoon, and/or Evening (if available)
    When I select the first available appointment time
    Then I verify the selected appointment time is highlighted red
    When I enter the phone number for customer in feature "<Feature>"
    And  I select the "Schedule" button on the "Appointment" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature           |
      | TransactionSearch |

  @ovcBBA
  @12181
  Scenario Outline: Oneview_transaction_search_pos_reprint (ALM# 12181)
  """TODO: Print Functionality is not implemented yet. Not sure on next verification step and/or successful message
    Needs a re-visit when this functionality is available
    then I verify the popup alert contains '**Successfully Printed**'"""
    When I select the "Transaction Search" link on the page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    When I select customer order transaction that contains matching "<TransactionType>" from the table
    Then I verify "Reprint Receipt" button is displayed on the page
    When I select the "Reprint Receipt" button on the "Client History" page

    Examples:
      | Feature           | TransactionType |
      | TransactionSearch | regular Sale    |

  @ovcRegression
  @ovcBBA
  @12184
  Scenario Outline: Oneview_transaction_search_pos_return_for_service (ALM# 12184)
    When I select the "Transaction Search" link on the page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicle" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicle" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    When I select customer order that contains matching "<TransactionType>" and click the "Return For Service" button
    And  I select the "VTV" icon from the Global Header
    And  I select the "Replace Sensor" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    And  I select the "Add" button on the "Lookup" page
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature           | TransactionType |
      | TransactionSearch | regular Sale    |

  @ovcBBA
  @12815
  Scenario Outline: Oneview_Customer_Customer_Account_Dasboard_Widgets_Add_to_CSL (ALM# 12815)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select customer for feature "<Feature>" on home page
    Then I verify the Customer Vehicle section contains "CustomerVehicle" for feature "<Feature>"
    And  I verify the Customer Profile section contains "CustomerName" for feature "<Feature>"
    And  I verify the Customer Appointment section is displayed
    When I enter "<TransactionType>" into the "TransactionType" textbox and send Enter key
    And  I select appointment from the Customer Details Appointment table with value of "<TransactionType>"
    And  I select the "Add To CSL" button on the "Client Details" page
    And  I select the "Replace Wheel" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    And  I verify the right nav pane title is "Main"

    Examples:
      | Feature           | TransactionType |
      | TransactionSearch | regular Sale    |

  @ovcBBA
  @12812
  Scenario Outline: Oneview_Customer_Customer_Account_Dasboard_Widgets_Return (ALM# 12812)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicle" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicle" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select customer for feature "<Feature>" on home page
    Then I verify customer name for feature "<Feature>" is present at profile section
    When I select customer order that contains matching "<TransactionType>" and click the "Return For Service" button
    And  I wait for the OVC preloader to be no longer visible
    And  I select "Return" from the Return page dropdown
    And  I select the checkbox to return "ItemNumber" for feature "<Feature>" with a quantity of "2"
    And  I select the "Return" option on the Return page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the right nav pane title is "Main"
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Continue" button on the "Popup" page
    Then I verify the dialog contains extracted balance total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature           | TransactionType |
      | TransactionSearch | regular Sale    |

  @ovcBBA
  @12920
  Scenario Outline: Oneview_Orders_Sales_suspend_resume_pill (ALM# 12920)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicle" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the first "1" product(s) from the Product Results table
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Suspend Transaction" link from the Checkout menu
    And  I select the "OK" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"
    When I select the "<Transaction>" previous transaction on the bottom of the Home page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    And  I verify the Virtual Receipt "previous balance total" price for feature "<Feature>"

    Examples:
      | Feature           | Transaction |
      | TransactionSearch | 0           |

  @ovcBBA
  @12874
  Scenario Outline: Oneview_Inventory_Stock_Management_Special_Orders_Search_Special_Order_and_cancel (ALM# 12874)
  """Pre-requisite: Create Special Order for Cancel"""
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I enter sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "TireSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Territory Stock" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the "Order" button on the "Find It" page
    And  I enter "Vendor" for feature "<Feature>" into the "Vendor Name"
    And  I select the "Search" button on the "Vendor" page
    And  I select the "Vendor" for feature "<Feature>" from the table on the "Vendor" page
    And  I select the "Select" button on the "Vendor" page
    Then I verify the "Vendor Number" text box is "VendorNumber" for the feature "<Feature>"
    When I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "<Comments>" on the Find It page
    And  I fill the "Contact Number" text box with the value "<Contact>" on the Find It page
    Then I verify the "Quantity" text box is "<Quantity>" on the Find It page
    When I select the "Complete Purchase Order" button on the "Find It" page
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Home" icon from the Global Header
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I enter "Special Order" into the "TransactionType" textbox and send Enter key
    And  I enter "LastName" for feature "<Feature>" into the "Last Name"
    And  I select the "Search" button on the "Transaction View" page
    Then I verify "First Name" field is displayed on the current page
    And  I verify "Associate" field is displayed on the current page
    And  I verify "From Amount" field is displayed on the current page
    And  I verify "To Amount" field is displayed on the current page
    And  I verify "Transaction ID" field is displayed on the current page
    When I select the "LastName" with "Cancel" Special Order from the table on the "TransactionSearch" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the right nav pane title is "Checkout"
    When I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the dialog contains "<Message>"
    When I select the "OK" button on the "Printer" page
    And  I select the "OK" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    Then I verify the transaction table entry does not show "Cancel Special Order" button

    Examples:
      | Feature           | Message                       | Quantity | Header          | Comments | Contact    | Tender |
      | TransactionSearch | remember to update the PO/STO | 10       | Territory Stock | Comments | 1234567890 | 50.00  |
      
  @specialOrderReturn
  @tLogRegression
  Scenario Outline: SpecialOrderReturn (ALM# NONE)
    """Prerequisite: Create Special Order for Return""" 
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I enter sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "TireSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Territory Stock" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the "Order" button on the "Find It" page
    And  I enter "Vendor" for feature "<Feature>" into the "Vendor Name"
    And  I select the "Search" button on the "Vendor" page
    And  I select the "Vendor" for feature "<Feature>" from the table on the "Vendor" page
    And  I select the "Select" button on the "Vendor" page
    Then I verify the "Vendor Number" text box is "VendorNumber" for the feature "<Feature>"
    When I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "<Comments>" on the Find It page
    And  I fill the "Contact Number" text box with the value "<Contact>" on the Find It page
    Then I verify the "Quantity" text box is "<Quantity>" on the Find It page
    When I select the "Complete Purchase Order" button on the "Find It" page
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page 
    And  I select the "Close" button on the "Lookup" page
    Then I extract the "Balance" total from the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I change the tender amount to the extracted balance total
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Add to CSL" button on the "TransactionSearch" page
    And  I select the "Replace Tire" selection for the "RF" Tire Service section 
    And  I select the "Save" button on the "VTV" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the first transaction on the page
    And  I select the "Return" button on the "TransactionSearch" page
    And  I select the "Yes" button on the "Popup" page
    And  I select "Return" from the Return page dropdown
    And  I select the checkbox to return "TireSku" for feature "<Feature>" with a quantity of "20"
    And  I select the "Return" option on the Return page
    And  I select the "Close" button on the "Lookup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    
    Examples:
      | Feature           |  Quantity | Header          | Comments | Contact    | Tender |
      | TransactionSearch |  10       | Territory Stock | Comments | 1234567890 | 50.00  |

  @specialOrderFinalizeFullPayCSL
  @tLogRegression
  Scenario Outline: SpecialOrderFinalizeFullPayCSL (ALM# NONE)
    """Prerequisite: Create Special Order for Refund"""
    When I select the "VTV" icon from the Global Header
    And  I select the "Replace Tire" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    And  I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I enter sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "TireSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Territory Stock" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the "Order" button on the "Find It" page
    And  I enter "Vendor" for feature "<Feature>" into the "Vendor Name"
    And  I select the "Search" button on the "Vendor" page
    And  I select the "Vendor" for feature "<Feature>" from the table on the "Vendor" page
    And  I select the "Select" button on the "Vendor" page
    Then I verify the "Vendor Number" text box is "VendorNumber" for the feature "<Feature>"
    When I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "<Comments>" on the Find It page
    And  I fill the "Contact Number" text box with the value "<Contact>" on the Find It page
    Then I verify the "Quantity" text box is "<Quantity>" on the Find It page
    When I select the "Complete Purchase Order" button on the "Find It" page
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Close" button on the "Lookup" page
    Then I extract the "Balance" total from the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I change the tender amount to the extracted balance total
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature           |  Quantity | Header          | Comments | Contact    | Tender |
      | TransactionSearch |  10       | Territory Stock | Comments | 1234567890 | 50.00  |

  @specialOrderFinalizePartialPayCSL
  @tLogRegression
  Scenario Outline: SpecialOrderFinalizePartialPayCSL (ALM# NONE)
    """Prerequisite: Create Special Order for Refund"""
    When I select the "VTV" icon from the Global Header
    And  I select the "Replace Tire" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    And  I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I enter sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "TireSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Territory Stock" button on the "Lookup" page
    Then I verify the "<Header>" text appears at the top of the Find It page
    When I select the "Order" button on the "Find It" page
    And  I enter "Vendor" for feature "<Feature>" into the "Vendor Name"
    And  I select the "Search" button on the "Vendor" page
    And  I select the "Vendor" for feature "<Feature>" from the table on the "Vendor" page
    And  I select the "Select" button on the "Vendor" page
    Then I verify the "Vendor Number" text box is "VendorNumber" for the feature "<Feature>"
    When I fill the "Quantity" text box with the value "<Quantity>" on the Find It page
    And  I fill the "Comments" text box with the value "<Comments>" on the Find It page
    And  I fill the "Contact Number" text box with the value "<Contact>" on the Find It page
    Then I verify the "Quantity" text box is "<Quantity>" on the Find It page
    When I select the "Complete Purchase Order" button on the "Find It" page
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Close" button on the "Lookup" page
    Then I extract the "Balance" total from the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I change tender amount to "<Tender>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    When I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I enter "Special Order" into the "TransactionType" textbox and send Enter key
    And  I enter "LastName" for feature "<Feature>" into the "Last Name"
    And  I select the "Search" button on the "Transaction View" page
    Then I verify "First Name" field is displayed on the current page
    And  I verify "Associate" field is displayed on the current page
    And  I verify "From Amount" field is displayed on the current page
    And  I verify "To Amount" field is displayed on the current page
    And  I verify "Transaction ID" field is displayed on the current page
    When I select the "LastName" with "Resume" Special Order from the table on the "TransactionSearch" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature           |  Quantity | Header          | Comments | Contact    | Tender |
      | TransactionSearch |  10       | Territory Stock | Comments | 1234567890 | 50.00  |