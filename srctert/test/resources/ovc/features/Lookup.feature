@ovc
@lookup
Feature: Lookup tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcRegression
  @ovcSmokeTest
  @ovcBBA
  @11636
  Scenario Outline: Oneview_Product_Lookup_Tires (ALM#11636)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I enter "Tire" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text displayed on the Lookup Page matches the "TireName" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "TireSku" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "MileageWarranty" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "StoreDescription" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "StoreSize" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "LoadRange" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "GBB" value for feature "<Feature>"

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11637
  Scenario Outline: Oneview_Product_Lookup_Wheels (ALM#11637)
    When I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "Wheel" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the value from feature "<Feature>" for the "rimDiameter" dropdown on the lookup page
    And  I select the "Search" button on the "Lookup" page
    And  I verify the text displayed on the Lookup Page matches the "WheelName" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "WheelSku" value for feature "<Feature>"

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11639
  Scenario Outline: Oneview_Product_Lookup_TPMS (ALM#11639)
  """TODO following step is not delivered yet
    and I select 'All TPMS Sensors' from the Lookup page dropdown
    TODO verify ALL TPMS Sensors"""
    When I select the "Lookup" icon from the Global Header
    And  I select "ACCESSORY" from the Lookup page dropdown
    And  I enter "AccessoryTPMS" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I verify the text displayed on the Lookup Page matches the "AccessoryTPMSName" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "AccessoryTPMSSku" value for feature "<Feature>"

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11640
  Scenario Outline: Oneview_Product_Lookup_TPMS (ALM#11640)
    When I select the "Lookup" icon from the Global Header
    And  I select "SERVICE" from the Lookup page dropdown
    And  I enter "Labor" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text displayed on the Lookup Page matches the "LaborName" value for feature "<Feature>"

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11642
  @11837
  Scenario Outline: Oneview_Product_Lookup_ValveStem (ALM#11642)
  """TODO select value from feature for brand category step need to be modified once the drop downs are finalized"""
    When I select the "Lookup" icon from the Global Header
    And  I select "ACCESSORY" from the Lookup page dropdown
    And  I enter "AccessoryValveStem" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the value from feature "<Feature>" for the "brandCategory" dropdown on the lookup page
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text displayed on the Lookup Page matches the "AccessoryValveStemName" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "AccessoryValveStemSku" value for feature "<Feature>"
    When I select the "Close" button on the "Lookup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11841
  @11842
  Scenario Outline: Oneview_Product_Lookup_Tires_Additonal_Option (ALM#11841)
  """TODO - partial functionality delivered, needs to revisit once all drop downs are present"""
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select the value from feature "<Feature>" for the "loadRange" dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "aspectRatio" dropdown on the lookup page
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text displayed on the Lookup Page matches the "loadRange" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "aspectRatio" value for feature "<Feature>"
    When I select the "Close" button on the "Lookup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11843
  Scenario Outline: Oneview_Product_Lookup_Tires_Clear_Search_Results (ALM#11843)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    Then I verify "Tire" dropdown fields are displayed on Lookup Page
    And  I verify the text displayed on the Lookup Page matches the "aspectRatio" value for feature "<Feature>"
    And  I select the "Clear" button on the "Lookup" page
    Then I verify "Tire" dropdown fields are not present on the Lookup page

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11844
  Scenario Outline: Oneview_Product_Lookup_Tires_Clear_Search_Fields (ALM#11844)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select the value from feature "<Feature>" for the "aspectRatio" dropdown on the lookup page
    And  I verify the text displayed on the Lookup Page matches the "aspectRatio" value for feature "<Feature>"
    When I select the "Clear" button on the "Lookup" page
    Then I verify "Tire" dropdown fields are not present on the Lookup page

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11848
  @11849
  Scenario Outline: Oneview_Product_Lookup_Wheels_Additional_Options (ALM#11848)
    When I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And I enter "Wheel" name and sku for feature "Lookup" on the Lookup Page
    And  I select the value from feature "<Feature>" for the "rimDiameter" dropdown on the lookup page
    Then I verify "Wheel" dropdown fields are displayed on Lookup Page
    And  I select the value from feature "<Feature>" for the "wheelColor" dropdown on the lookup page
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text displayed on the Lookup Page matches the "WheelName" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "WheelSku" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "brandCategory" value for feature "<Feature>"
    And  I verify the text displayed on the Lookup Page matches the "wheelColor" value for feature "<Feature>"
    When I select the "Close" button on the "Lookup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11850
  Scenario Outline: Oneview_Product_Lookup_Wheels_Clear_Search_Results (ALM#11850)
    When I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "Wheel" name and sku for feature "Lookup" on the Lookup Page
    And  I select the value from feature "<Feature>" for the "wheelColor" dropdown on the lookup page
    Then I verify "Wheel" dropdown fields are displayed on Lookup Page
    When I select the "Search" button on the "Lookup" page
    Then I verify the text displayed on the Lookup Page matches the "wheelColor" value for feature "<Feature>"
    When I select the "Clear" button on the "Lookup" page
    Then I verify "Tire" dropdown fields are not present on the Lookup page

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @11624
  Scenario Outline: Oneview_Fitment_Associated_Product_Flat_Repair (ALM# 11624)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I enter "CouponCode" for feature "<Feature>" in the article search box
    And  I enter "<Amount>" into the Flat Repair dialog input
    And  I select the "OK" button on the "Popup" page
    And  I select "AccessoryTPMSSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "AccessoryTPMSName" for feature "<Feature>" appears on the Virtual Receipt
    Then I verify "AccessoryTPMSSku" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify the "CouponCode" of the item for feature "<Feature>" appears on the home page

    Examples:
      | Feature | Amount |
      | Lookup  | 10.00  |

  @ovcBBA
  @11623
  Scenario Outline: Oneview_Fitment_Associated_Product (ALM# 11623)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I enter "Tire" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "TireSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Lookup" icon from the Global Header
    And  I select "ACCESSORY" from the Lookup page dropdown
    And  I enter "AccessoryTPMS" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "AccessoryTPMSSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "AccessoryTPMSName" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify "AccessoryTPMSSku" for feature "<Feature>" appears on the Virtual Receipt

    Examples:
      | Feature |
      | Lookup  |

  @ovcBBA
  @12914
  Scenario Outline: Oneview_Orders_sales_linked_products (ALM# 12914)
    When I enter "TireSkuLinkedProduct" for feature "<Feature>" in the article search box
    Then I verify the quantity of "TireSkuLinkedProduct" for feature "<Feature>" was updated to "<DefaultQuantity>"
    When I adjust the item quantity of "TireCertificateOfRepair" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "TireCertificateOfRepair" for feature "<Feature>" was updated to "<QuantityAdjust>"
    And  I verify the quantity of "TireSkuLinkedProduct" for feature "<Feature>" was updated to "<DefaultQuantity>"
    And  I verify the quantity of "TireDisposalFee" for feature "<Feature>" was updated to "<DefaultQuantity>"

    Examples:
      | Feature | QuantityAdjust | DefaultQuantity |
      | Lookup  | 4              | 1               |

  @ovcBBA
  @11820
  Scenario: Oneview_Product_Lookup_Labor_Clear_Search_Results (ALM# 11820)
    When I select the "Lookup" icon from the Global Header
    And  I select "SERVICE" from the Lookup page dropdown
    And  I enter "Balancing" into the "Name"
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text "Balancing" is displayed for each product on the Lookup page results
    When I enter "Repair" into the "Name"
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text "Repair" is displayed for each product on the Lookup page results
    When I select the "Clear" button on the "Lookup" page
    Then I verify that no products are listed in the Lookup results page

  @ovcBBA
  @11821
  Scenario Outline: Oneview_Product_Lookup_Labor_Closing_Session (ALM# 11821)
    When I select the "Lookup" icon from the Global Header
    And  I select "SERVICE" from the Lookup page dropdown
    And  I enter "Balancing" into the "Name"
    And  I select the "Search" button on the "Lookup" page
    Then I verify the text "Balancing" is displayed for each product on the Lookup page results
    When I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I change tender amount to "<Price>"
    And  I select the "OK" button on the "Popup" page
    Then I verify the Virtual Receipt balance total is "<Price>"
    When I select the red "x" by the total dollar amount
    And  I select the "OK" button on the "Popup" page
    Then I verify that no products are listed in the Lookup results page

    Examples:
      | Feature | Price  |
      | Lookup  | 200.00 |

  @ovcSmokeTest
  @endlessAisle
  Scenario Outline: Endless_Aisle (ALM# None)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Site" button on the "Lookup" page
    And  I switch "To" "Pdp iframe" window
    Then I verify PDP page is displayed
    When I select the "Add to Receipt" button
    And  I switch "Back From" "Pdp iframe" window
    And  I wait for the OVC preloader to be no longer visible
    And  I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select "AccessoryTPMSSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "E2E" excel

    Examples:
      | Feature |
      | Lookup  |

  @ovcRegression
  @miscWheel
  Scenario Outline: Miscellaneous Wheel (ALM# None)
    When I select the "Lookup" icon from the Global Header
    And  I select "MISC WHEEL" from the Lookup page dropdown
    And  I enter "MiscWheel" gtin and vpn for feature "Lookup" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "MiscWheelGtin" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify "MiscWheelArticle" for feature "<Feature>" appears on the Virtual Receipt
    And  I verify the page currently displayed is the "Home" page
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature |
      | Lookup  |

  @ovcRegression
  @miscTire
  Scenario Outline: Miscellaneous Tire (ALM# None)
    When I select the "Lookup" icon from the Global Header
    And  I select "MISC TIRE" from the Lookup page dropdown
    And  I enter "MiscTire" gtin and vpn for feature "Lookup" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "MiscTireGtin" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    And  I verify "MiscTireArticle" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature |
      | Lookup  |

  @ovcRegression
  @miscWheelAdd
  @13238
  Scenario Outline: OVC_ARTICLE_PRODUCT_Misc Batch - Create - Wheel (Attributes) (ALM# 13238)
    When I select the "Lookup" icon from the Global Header
    And  I select "MISC WHEEL" from the Lookup page dropdown
    And  I select the "Add Misc Wheel" button on the "Lookup" page
    And  I select the value from feature "<Feature>" for the "Misc_Wheel_Width" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Rim_Diameter" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Brand" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Number_Of_Bolts" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Bolt_Circle_1" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Bolt_Circle_2" 'Add Misc' dropdown on the lookup page
    And  I enter "2" into the "offset"
    And  I select the value from feature "<Feature>" for the "Misc_Wheel_Color" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Finish" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Wheel_Accent" 'Add Misc' dropdown on the lookup page
    And  I enter "4" into the "hubBore"
    And  I select the value from feature "<Feature>" for the "Misc_Line" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Rim_Size" 'Add Misc' dropdown on the lookup page
    And  I enter "Random" gtin into the Add Misc GTIN field on the lookup page
    And  I select the "Save" button on the "Misc Product" page
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the text "GTIN" is displayed for each product on the Lookup page results
    When I select item "GTIN" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    And  I verify "MiscWheelArticle" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature |
      | Lookup  |

  @ovcRegression
  @miscTireAdd
  @13236
  Scenario Outline: OVC_ARTICLE_PRODUCT_Misc Batch - Create - Tire (Attributes) (ALM# 13236)
    When I select the "Lookup" icon from the Global Header
    And  I select "MISC TIRE" from the Lookup page dropdown
    And  I select the "Add Misc Tire" button on the "Lookup" page
    And  I select the value from feature "<Feature>" for the "Misc_Service_Type" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Cross_Section" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Aspect_Ratio" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Tire_Construction" 'Add Misc' dropdown on the lookup page
    And  I enter "4" into the "vnLoadIndp"
    And  I select the value from feature "<Feature>" for the "Misc_Speed_Rating" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Load_Range" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Side_wall_Description" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Brand" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Family" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Runflat" 'Add Misc' dropdown on the lookup page
    And  I select the value from feature "<Feature>" for the "Misc_Rim_Size" 'Add Misc' dropdown on the lookup page
    And  I enter "Random" gtin into the Add Misc GTIN field on the lookup page
    And  I select the "Save" button on the "Misc Product" page
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the text "GTIN" is displayed for each product on the Lookup page results
    When I select item "GTIN" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    And  I verify "MiscTireArticle" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature |
      | Lookup  |

  @ovcRegression
  @15384
  Scenario Outline: OVC_PRICING_PRICING_Linked Product_FET (ALM# 15384)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I enter "TireSku" for feature "<Feature>" in the article search box
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify "FETArticle" for feature "<Feature>" appears on the Virtual Receipt

    Examples:
      | Feature |
      | Lookup  |