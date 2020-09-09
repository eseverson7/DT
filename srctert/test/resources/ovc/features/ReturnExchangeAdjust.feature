@ovc
@returnExchangeAdjust
Feature: Return/Exchange/Adjust tests

  Background:
    When I go to the ovc homepage
    And  I login with Server, Username, and Password

  @ovcBBA
  @12127
  Scenario Outline: Oneview_Payment_Payment_and_Tenders_Special_Payments_Equal_to_transaction_amount (ALM# 12127)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I enter "ItemNumber2" for feature "<Feature>" in the article search box
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify "ItemNumber2" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select the "Search" button on the "Lookup" page
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    And  I extract the "Balance" total from the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Special Payment" link from the Checkout menu
    And  I select "Managers tires/snow tires" as the Special Payment option from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature              |
      | ReturnExchangeAdjust |

  @ovcBBA
  @12073
  Scenario Outline:  Oneview_Orders_Returns_Return_with_reciept_overide (ALM# 12073)
  """TODO manager approval currently disabled
    and  I select the "OK" button on the "Popup" page
    and  I enter Manager Approval credentials in to the window and click "Approve"
    and  I select the "Continue" button on the "Popup" page"""
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the page currently displayed is the "Home" page
    When I perform a "ReturnVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "With Receipt" link on the page
    And  I enter transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return" button on the "Transaction View" page
    And  I select "Return" from the Return page dropdown
    And  I select the checkbox to return "ItemNumber" for feature "<Feature>" with a quantity of "2"
    And  I select the "Return" option on the Return page
    Then I verify the page currently displayed is the "Lookup" page
    When I select the "Close" button on the "Lookup" page
    Then I verify the page currently displayed is the "Home" page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Continue" button on the "Popup" page
    Then I verify the dialog contains extracted balance total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature              | QuantityAdjust |
      | ReturnExchangeAdjust | 4              |

  @ovcBBA
  @12074
  Scenario Outline:  Oneview_Orders_Returns_Return_with_reciept_overide_other (ALM# 12074)
  """TODO manager approval currently disabled
    and  I select the "OK" button on the "Popup" page
    and  I enter Manager Approval credentials in to the window and click "Approve"
    and  I select the "Continue" button on the "Popup" page"""
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the page currently displayed is the "Home" page
    When I perform a "ReturnVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    And  I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "With Receipt" link on the page
    Then I verify the right nav pane title is "Return"
    When I enter transaction details with the previous receipt number
    And  I select the "Search" button on the "Transaction View" page
    And  I select the transaction with the previous receipt number
    And  I select the "Return" button on the "Transaction View" page
    And  I select "Return" from the Return page dropdown
    And  I select the checkbox to return "ItemNumber" for feature "<Feature>" with a quantity of "2"
    And  I select the "Return" option on the Return page
    Then I verify the page currently displayed is the "Lookup" page
    When I select the "Close" button on the "Lookup" page
    Then I verify the page currently displayed is the "Home" page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Continue" button on the "Popup" page
    Then I verify the dialog contains extracted balance total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature              | QuantityAdjust |
      | ReturnExchangeAdjust | 4              |

  @ovcBBA
  @12128
  Scenario Outline: Oneview_Payment_Payment_and_Tenders_Special_Payments_Special_Payment_is_for_less_than_transaction_amount (ALM# 12128)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I enter "ItemNumber2" for feature "<Feature>" in the article search box
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify "ItemNumber2" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select the "Search" button on the "Lookup" page
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Special Payment" link from the Checkout menu
    And  I select "Staff Vehicle" as the Special Payment option from the Checkout menu
    Then I verify the dialog contains "<Text>"
    And  I verify tender amount equals the order price total
    When I change tender amount to "<Amount>"
    And  I select the "Next" button on the "Popup" page
    Then I verify the right nav pane title is "Checkout"
    When I select the "Cash" link from the Checkout menu
    Then I verify the dialog contains "<Text>"
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature              | Text                         | Amount |
      | ReturnExchangeAdjust | Please provide tender amount | 2500   |

  @ovcBBA
  @11611
  Scenario Outline:  Oneview_Orders_Returns_Return_with_reciept (ALM# 11611)
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I enter "ItemNumber2" for feature "<Feature>" in the article search box
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    And  I adjust the item quantity of "ItemNumber2" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "ItemNumber2" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "With Receipt" link on the page
    Then I verify the right nav pane title is "Return"
    When I enter transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return" button on the "Transaction View" page
    And  I select "Return" from the Return page dropdown
    And  I select the checkbox to return "ItemNumber2" for feature "<Feature>" with a quantity of "QuantityAdjust"
    And  I select the "Return" option on the Return page
    Then I verify the page currently displayed is the "Home" page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Continue" button on the "Popup" page
    Then I verify the dialog contains extracted balance total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature              | QuantityAdjust |
      | ReturnExchangeAdjust | 4              |

  @ovcRegression
  @ovcBBA
  @11613
  @11314
  Scenario Outline:  Oneview_Orders_Returns_without reciept (ALM# 11613)
    When I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "Without Receipt" link on the page
    And  I select the "Return/Exchange" link on the page
    And  I select the "Article Search" link on the page
    And  I select "ACCESSORY" from the Lookup page dropdown
    And  I select "AccessoryNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "AccessoryNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    Then I verify the dialog contains extracted balance total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature              |
      | ReturnExchangeAdjust |

  @ovcBBA
  @12892
  Scenario Outline: Oneview_Orders_Voids_transactions_article (ALM# 12892)
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the red "x" by the total dollar amount
    Then I verify the dialog contains "<CancelConfirm>"
    When I select the "Cancel" button on the "Popup" page
    And  I select the red "x" for the line of "ItemNumber" for feature "<Feature>"
    Then I verify the dialog contains "<CancelConfirm>"
    When I select the "OK" button on the "Popup" page
    Then I verify that the popup with a "OK" button is closed
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify the page currently displayed is the "Lookup" page
    When I select the "Close" button on the "Lookup" page
    And  I select the red "x" by the total dollar amount
    Then I verify the dialog contains "<CancelConfirm>"
    When I select the "OK" button on the "Popup" page
    Then I verify that the popup with a "OK" button is closed
    And  I verify that item "ItemNumber" for feature "<Feature>" is no longer on the VR

    Examples:
      | Feature              | CancelConfirm |
      | ReturnExchangeAdjust | void          |

  @12886
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Card_Payment_Integration_Refund_Voids_Refund_without_Receipt_Return_to_credit_card_Service_LaterComplete (ALM# 12886)
  """TODO - per Nathan K. will be introduced back to the suite once cc processing is delivered"""
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "Without Receipt" link on the page
    And  I select the "Return/Exchange" link on the page
    Then I verify the right nav pane title is "Scan"
    When I select the "Article Search" link on the page
    And  I select "ACCESSORY" from the Lookup page dropdown
    And  I select "AccessoryNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "AccessoryNumber" of the item for feature "<Feature>" appears on the home page
    And  I verify the right nav pane title is "Return"
    When I select the "Close" button on the "Home" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Card Payment" link from the Checkout menu
    Then I verify the dialog contains extracted balance total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature              |
      | ReturnExchangeAdjust |

  @ovcRegression
  @ovcBBA
  @10525
  Scenario Outline: OVC_CUSTOMER_CUSTOMER_Customer Invoice Requirements - Adjustments with (ALM# 10525)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I extract the subtotal from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "With Receipt" link on the page
    And  I enter transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return" button on the "Transaction View" page
    And  I select "Adjust" from the Return page dropdown
    And  I select the checkbox to return "ItemNumber" for feature "<Feature>" with a quantity of "2"
    And  I select the "Return" option on the Return page
    And  I select the "In Store Damage" radio button on the Order Adjustment page
    And  I select the "Same Tire" radio button on the Order Adjustment page
    And  I select the "Next" button on the "Order Adjustment" page
    Then I verify the page currently displayed is the "Order Adjustment" page
    When I select the "Finish" button on the "Order Adjustment" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature              |
      | ReturnExchangeAdjust |

  @ovcBBA
  @11364
  Scenario Outline: OVC_CUSTOMER_CUSTOMER_Customer Invoice Requirements - Adjustments without (ALM# 11364)
    When I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "Without Receipt" link on the page
    Then I verify the right nav pane title is "Return"
    When I select the "Adjustment" link on the page
    And  I select the "Article Search" link on the page
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Close" button on the "Home" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature              |
      | ReturnExchangeAdjust |

  @ovcRegression
  @tLogRegression
  @vendorAdjustment
  Scenario Outline:  Oneview_Orders_Returns_Adjust_with_reciept_vendor (ALM# None)
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the page currently displayed is the "Home" page
    And  I verify the VTV icon color is "Green"
    When I perform a "ReturnVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    And  I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "With Receipt" link on the page
    Then I verify the right nav pane title is "Return"
    When I enter transaction details with the previous receipt number
    And  I select the "Search" button on the "Transaction View" page
    And  I select the transaction with the previous receipt number
    And  I select the "Return" button on the "Transaction View" page
    And  I select "Adjust" from the Return page dropdown
    And  I select the checkbox to return "ItemNumber" for feature "<Feature>" with a quantity of "1"
    And  I select the "Return" option on the Return page
    Then I verify the page currently displayed is the "Order Adjustment" page
    When I select the "Mileage" radio button on the "Order Adjustment" page
    And  I enter "<WarrantyMiles>" into the "Warranty" input box on the order adjustment page
    And  I enter "<MilesOn>" into the "Miles On" input box on the order adjustment page
    And  I enter "<MilesOff>" into the "Miles Off" input box on the order adjustment page
    And  I enter "<CurrentDepth>" into the "Current Depth" input box on the order adjustment page
    And  I enter "<OriginalDepth>" into the "Original Depth" input box on the order adjustment page
    And  I select the "Same Tire" radio button on the "Order Adjustment" page
    And  I select the "Next" button on the "Order Adjustment" page
    And  I select the "Finish" button on the "Order Adjustment" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    Then I verify the right nav pane title is "Main"
    When I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature              | QuantityAdjust | WarrantyMiles | MilesOn | MilesOff | CurrentDepth | OriginalDepth |
      | ReturnExchangeAdjust | 4              | 50000         | 10000   | 25000    | 12           | 30            |

  @tLogRegression
  @nonVendorAdjustment
  Scenario Outline:  Oneview_Orders_Returns_Adjust_with_reciept_non_vendor (ALM# None)
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the page currently displayed is the "Home" page
    And  I verify the VTV icon color is "Green"
    When I perform a "ReturnVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    And  I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Adjust/Return/Exchange" link on the page
    Then I verify the right nav pane title is "Adjust/Return/Exchange"
    When I select the "With Receipt" link on the page
    Then I verify the right nav pane title is "Return"
    When I enter transaction details with the previous receipt number
    And  I select the "Search" button on the "Transaction View" page
    And  I select the transaction with the previous receipt number
    And  I select the "Return" button on the "Transaction View" page
    And  I select "Adjust" from the Return page dropdown
    And  I select the checkbox to return "ItemNumber" for feature "<Feature>" with a quantity of "1"
    And  I select the "Return" option on the Return page
    Then I verify the page currently displayed is the "Order Adjustment" page
    When I select the "Customer Satisfaction" radio button on the "Order Adjustment" page
    And  I enter "<WarrantyMiles>" into the "Warranty" input box on the order adjustment page
    And  I enter "<MilesOn>" into the "Miles On" input box on the order adjustment page
    And  I enter "<MilesOff>" into the "Miles Off" input box on the order adjustment page
    And  I enter "<CurrentDepth>" into the "Current Depth" input box on the order adjustment page
    And  I enter "<OriginalDepth>" into the "Original Depth" input box on the order adjustment page
    And  I select the "Same Tire" radio button on the "Order Adjustment" page
    And  I select the "Next" button on the "Order Adjustment" page
    And  I select the "Finish" button on the "Order Adjustment" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    Then I verify the right nav pane title is "Main"
    When I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "OK" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature              | QuantityAdjust | WarrantyMiles | MilesOn | MilesOff | CurrentDepth | OriginalDepth |
      | ReturnExchangeAdjust | 4              | 50000         | 10000   | 25000    | 12           | 30            |