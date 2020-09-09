@ovc
@orderAdjustment
Feature: Order adjustment tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcBBA
  @12893
  Scenario Outline: Oneview_Orders_Sales_adjustment_tire_mileage (ALM# 12893)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select the "Search" button on the "Lookup" page
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Adjustment" button on the "Lookup" page
    Then I verify the page currently displayed is the "Order Adjustment" page
    When I select the "Mileage" radio button on the "Order Adjustment" page
    And  I enter "<WarrantyMiles>" into the "Warranty" input box on the order adjustment page
    And  I enter "<MilesOn>" into the "Miles On" input box on the order adjustment page
    And  I enter "<MilesOff>" into the "Miles Off" input box on the order adjustment page
    And  I enter "MFGAuth" into the "Manufacture Authorization" input box for feature "<Feature>" on the order adjustment page
    And  I enter "DOTNumber" into the "Dot Number" input box for feature "<Feature>" on the order adjustment page
    And  I enter "<CurrentDepth>" into the "Current Depth" input box on the order adjustment page
    And  I enter "<OriginalDepth>" into the "Original Depth" input box on the order adjustment page
    And  I select the "Same Tire" radio button on the "Order Adjustment" page
    And  I select the "Next" button on the "Order Adjustment" page
    And  I select the "Finish" button on the "Order Adjustment" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify the page currently displayed is the "Lookup" page
    When I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature         | WarrantyMiles | MilesOn | MilesOff | CurrentDepth | OriginalDepth |
      | OrderAdjustment | 50000         | 10000   | 25000    | 8            | 11            |

  @ovcBBA
  @12894
  Scenario Outline: Oneview_Orders_Sales_adjustment_tire_mfg_condition (ALM# 12894)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select the "Search" button on the "Lookup" page
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Adjustment" button on the "Lookup" page
    Then I verify the page currently displayed is the "Order Adjustment" page
    When I select the "Manufacturer Condition" radio button on the "Order Adjustment" page
    And  I select the "Liner Cracks" from the order adjustment page drop down
    And  I enter "<WarrantyMiles>" into the "Warranty" input box on the order adjustment page
    And  I enter "<MilesOn>" into the "Miles On" input box on the order adjustment page
    And  I enter "<MilesOff>" into the "Miles Off" input box on the order adjustment page
    And  I enter "MFGAuth" into the "Manufacture Authorization" input box for feature "<Feature>" on the order adjustment page
    And  I enter "DOTNumber" into the "Dot Number" input box for feature "<Feature>" on the order adjustment page
    And  I enter "<CurrentDepth>" into the "Current Depth" input box on the order adjustment page
    And  I enter "<OriginalDepth>" into the "Original Depth" input box on the order adjustment page
    And  I select the "Same Tire" radio button on the "Order Adjustment" page
    And  I select the "Next" button on the "Order Adjustment" page
    And  I select the "Finish" button on the "Order Adjustment" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature         | WarrantyMiles | MilesOn | MilesOff | CurrentDepth | OriginalDepth |
      | OrderAdjustment | 50000         | 10000   | 25000    | 8            | 11            |

  @ovcBBA
  @12895
  Scenario Outline: Oneview_Orders_Sales_adjustment_tire_prorate_different_tire (ALM# 12895)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select the "Search" button on the "Lookup" page
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Adjustment" button on the "Lookup" page
    Then I verify the page currently displayed is the "Order Adjustment" page
    When I select the "Road hazard" radio button on the "Order Adjustment" page
    And  I enter "<WarrantyMiles>" into the "Warranty" input box on the order adjustment page
    And  I enter "<MilesOn>" into the "Miles On" input box on the order adjustment page
    And  I enter "<MilesOff>" into the "Miles Off" input box on the order adjustment page
    And  I enter "MFGAuth" into the "Manufacture Authorization" input box for feature "<Feature>" on the order adjustment page
    And  I enter "DOTNumber" into the "Dot Number" input box for feature "<Feature>" on the order adjustment page
    And  I enter "<CurrentDepth>" into the "Current Depth" input box on the order adjustment page
    And  I enter "<OriginalDepth>" into the "Original Depth" input box on the order adjustment page
    And  I select the "Different Tire" radio button on the "Order Adjustment" page
    Then I verify the page currently displayed is the "Lookup" page
    When I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Continue Adjustments" button on the "Lookup" page
    And  I select the "Next" button on the "Order Adjustment" page
    And  I select the "Finish" button on the "Order Adjustment" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify the page currently displayed is the "Lookup" page
    When I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature         | WarrantyMiles | MilesOn | MilesOff | CurrentDepth | OriginalDepth |
      | OrderAdjustment | 50000         | 10000   | 25000    | 8            | 11            |

  @ovcBBA
  @12896
  Scenario Outline: Oneview_Orders_Sales_adjustment_wheel (ALM# 12896)
    When I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "ItemNumber2" for feature "<Feature>" into "SKU" textbox and send Enter key
    And  I select the "Search" button on the "Lookup" page
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Adjustment" button on the "Lookup" page
    Then I verify the page currently displayed is the "Order Adjustment" page
    When I select the "Customer Satisfaction" radio button on the "Order Adjustment" page
    And  I select the "Yes" radio button on the "Order Adjustment" page
    And  I select the "Next" button on the "Order Adjustment" page
    And  I select the "Finish" button on the "Order Adjustment" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature         |
      | OrderAdjustment |

  @ovcBBA
  @12916
  Scenario Outline: Oneview_Orders_Sales_Sell_multiple_items (ALM# 12916)
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"

    Examples:
      | Feature         | QuantityAdjust |
      | OrderAdjustment | 4              |

  @ovcBBA
  @12919
  Scenario Outline: Oneview_Orders_Sales_suspend_resume (ALM# 12919)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I wait for the OVC preloader to be no longer visible
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Resume Suspended Transaction" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Resume Suspended Transaction" button on the "Order Search" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify customer for feature "<Feature>" added to the virtual receipt

    Examples:
      | Feature         |
      | OrderAdjustment |

  @ovcBBA
  @12921
  Scenario Outline: Oneview_Orders_Sales_suspend_resume_add_to_csl (ALM# 12921)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Resume Suspended Transaction" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Resume Suspended Transaction" button on the "Order Search" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify customer for feature "<Feature>" added to the virtual receipt

    Examples:
      | Feature         | Mileage | Location   | Condition |
      | OrderAdjustment | 175000  | Scottsdale | poor      |

  @ovcBBA
  @12918
  Scenario Outline: Oneview_Orders_Sales_warranty (ALM# 12918)
    When I enter "WarrantyItem" for feature "<Feature>" in the article search box
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the page currently displayed is the "Lookup" page
    When I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature         |
      | OrderAdjustment |

  @ovcBBA
  @12922
  Scenario Outline: Oneview_Orders_Sales_quote_create (ALM# 12922)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    Then I verify the page currently displayed is the "Lookup" page
    When I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Create Quote" link on the page
    Then I verify the page currently displayed is the "Home" page
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Retrieve Quote" link on the page
    And  I search transaction details for the customer from feature "<Feature>"
    And  I select the first transaction on the page
    And  I select the "Retrieve Quote" button on the "Order Search" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify customer for feature "<Feature>" added to the virtual receipt

    Examples:
      | Feature         | Mileage | Location   | Condition |
      | OrderAdjustment | 175000  | Scottsdale | poor      |

  @ovcBBA
  @12923
  Scenario Outline: Oneview_Orders_Sales_quote_retrive (ALM# 12923)
    When I select the "Retrieve Quote" link on the page
    And  I select the "Search" button on the "Order Search" page
    And  I select the first transaction on the page
    And  I select the "Retrieve Quote" button on the "Order Search" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt

    Examples:
      | Feature         |
      | OrderAdjustment |

  @tLogRegression
  @ovcBBA
  @12915
  @12917
  Scenario Outline: Oneview_Orders_Sales_Price_overide (ALM #12915 (_high), 12917 (_low))
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I modify the price of "ItemNumber" for feature "<Feature>" on the Virtual Receipt to "<NewPrice>"
    And  I select the "Damaged Product" option on the Price Override popup
    And  I extract the subtotal of item "ItemNumber" for feature "<Feature>"
    Then I verify subtotal for extracted item equals "<NewPrice>"

    Examples:
      | Feature         | NewPrice |
      | OrderAdjustment | 1000.00  |
      | OrderAdjustment | 0.00     |