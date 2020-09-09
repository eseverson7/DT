@ovc
@layaway

Feature: Layaway tests

  Background:
    When I go to the ovc homepage
    And  I login with Server, Username, and Password

  @ovcRegression
  @tLogRegression
  @ovcBBA
  @12924
  Scenario Outline: Oneview_Orders_Sales_Layaway_create (ALM #12924)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "Layaway" link on the page
    And  I select the "Create Layaway" link on the page
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the first "1" product(s) from the Product Results table
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    Then I verify "VRItem1" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify "VRItem2" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify "VRItem3" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals expected value of: "$10.00"
    When I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    And  I select the "OK" button on the "Popup" page
    When I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature |
      | Layaway |

  @tLogRegression
  @ovcBBA
  @12925
  @12927
  Scenario Outline: Oneview_Orders_Sales_Layaway_lookup_payment (ALM #12925, 12927)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "Layaway" link on the page
    And  I select the "Create Layaway" link on the page
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the first "1" product(s) from the Product Results table
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify "LAYAWAY SALE" is displayed on the order receipt
    And  I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Layaway" link on the page
    And  I select the "Lookup Layaway" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Resume Layaway" button on the "Transaction View" page
    Then I verify "VRItem1" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify "VRItem3" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Make Payment" link from the Checkout menu
    And  I select the "Cash" link from the Checkout menu
    Then I verify the dialog contains "Layaway Total Amount Due"
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature |
      | Layaway |

  @ovcBBA
  @12926
  Scenario Outline: Oneview_Orders_Sales_Layaway_lookup_add_appintment (ALM #12926)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "Layaway" link on the page
    And  I select the "Create Layaway" link on the page
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the first "1" product(s) from the Product Results table
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    When I select "Close" option from the right pane nav bar
    And  I select the "Layaway" link on the page
    And  I select the "Lookup Layaway" link on the page
    And  I search transaction details for the customer from feature "<Feature>"
    And  I select the first transaction on the page
    And  I select the "Appointment" button on the "Transaction View" page
    When I create an appointment for customer in feature "<Feature>" with reason "<ApptReason>"
    And  I select the "OK" button on the "Popup" page
    Then I verify the right nav pane title is "Layaway"

    Examples:
      | Feature | ApptReason      |
      | Layaway | Tire Inspection |

  @ovcBBA
  @12928
  Scenario: Oneview_Orders_Sales_Layaway_lookup_close_mail_check (ALM# 12928)
    When I select the "Layaway" link on the page
    And  I select the "Lookup Layaway" link on the page
    Then I am brought to a Transaction Search page
    When I select the "Search" button on the "Transaction View" page
    And  I select the first transaction on the page
    And  I select the "Resume Layaway" button on the "Transaction View" page
    And  I select the "Refund Layaway" link on the page
    And  I select the "No" button on the "Popup" page
    Then I verify the dialog contains "Refund Layaway - Mail Check"
    And  I verify the dialog contains "This layaway order has been closed."
    When I select the "OK" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

  @12797
  @E2E
  Scenario Outline: OVC_SMOKE_LAYAWAY_CASH (ALM# 12797)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "Layaway" link on the page
    Then I verify the right nav pane title is "Layaway"
    When I select the "Create Layaway" link on the page
    And  I select the "Fitment" icon from the Global Header
    Then I verify the page currently displayed is the "Fitment" page
    When I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select selections from feature "<Feature>" from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    Then I verify the refined fitment product results display on page
    When I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I close out the Fitment Qualifiers dialog
    And  I select the "Close" button on the "Lookup" page
    Then I verify "ItemNumber2" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals expected value of: "$10.00"
    When I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I verify "LAYAWAY SALE" is displayed on the order receipt
    And  I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "E2E" excel
    When I select the "OK" button on the "popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "LOGOFF" button on the "Home" page
    And  I select the "Yes" button on the "popup" page
    Then I verify I am back on the login page

    Examples:
      | Feature    | Mileage | Condition |
      | Layaway    | 46000   | Excellent |
      
  @tLogRegression
  @layawayAddToCSL
  Scenario Outline: Oneview_Orders_Sales_Layaway_Partial_Finalize (ALM #NONE)
    """TODO: Workaround has been added as the right nav issue doesn't happen manually."""
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "Layaway" link on the page
    And  I select the "Create Layaway" link on the page
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the first "1" product(s) from the Product Results table
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Complete Deposit" button on the "Home" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify "LAYAWAY SALE" is displayed on the order receipt
    And  I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Layaway" link on the page
    And  I select the "Lookup Layaway" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Resume Layaway" button on the "Transaction View" page
    Then I verify "VRItem1" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify "VRItem3" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Make Payment" link from the Checkout menu
    And  I select the "Cash" link from the Checkout menu
    Then I verify the dialog contains "Layaway Total Amount Due"
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "OK" button on the "Printer" page
    And  I go to the ovc homepage
    And  I enter Server, Username and Password and hit Login on the second login page
    Then I verify the right nav pane title is "Main"
    When I select the "Layaway" link on the page
    And  I select the "Lookup Layaway" link on the page
    And  I search transaction details for the customer from feature "<Feature>"
    And  I select the first transaction on the page
    And  I select the "Add to CSL" button on the "Transaction View" page
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "popup" page 
    Then I verify the right nav pane title is "Main"
    
    Examples:
      | Feature |
      | Layaway |