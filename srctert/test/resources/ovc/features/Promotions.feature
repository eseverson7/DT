@ovc
@promotions
Feature: Promotion tests

  Background:
    When I go to the ovc homepage
    And  I login with Server, Username, and Password

  @ovcRegression
  @ovcSmokeTest
  @tLogRegression
  @ovcBBA
  @12840
  Scenario Outline: Oneview_Promotions_ Discounts_Applying_Money_Discount_Total_Invoice (ALM# 12840)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I extract the price of the "ItemNumber" for feature "<Feature>"
    And  I select the word "Total" on the Virtual Receipt
    And  I select the price toggle to set it to "Dollar"
    Then I verify the discount type is toggled to "Dollar" off
    When I select the "Customer Satisfaction" radio button on the "Popup" page
    And  I enter a discount value of "ItemPrice" into the order discount textbox
    And  I select the "OK" button on the "Popup" page
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature    | Mileage | Location   | Condition | QuantityAdjust |
      | Promotions | 175000  | Scottsdale | Poor      | 4              |

  @ovcBBA
  @12841
  Scenario Outline: Oneview_Promotions_Discounts_Applying_Percent_Discount_Total_Invoice (ALM# 12841)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the word "Total" on the Virtual Receipt
    And  I select the price toggle to set it to "Percent"
    Then I verify the discount type is toggled to "Percent" off
    When I select the "Customer Satisfaction" radio button on the "Popup" page
    And  I enter a discount value of "100" into the order discount textbox
    And  I select the "OK" button on the "Popup" page
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    | Mileage | Location   | Condition | QuantityAdjust |
      | Promotions | 175000  | Scottsdale | Poor      | 4              |

  @ovcBBA
  @12842
  Scenario Outline: Oneview_Promotions_Discounts_Applying_Money_Discount_Error_Message_Total_Invoice (ALM# 12842)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the word "Total" on the Virtual Receipt
    And  I select the price toggle to set it to "Dollar"
    Then I verify the discount type is toggled to "Dollar" off
    When I select the "Customer Satisfaction" radio button on the "Popup" page
    And  I enter a discount value of "$5000.00" into the order discount textbox
    And  I select the "OK" button on the "Popup" page
    Then I verify the dialog contains "You have exceeded your discount limit."
    When I select the "No" button on the "Popup" page
    Then I verify that the popup with a "No" button is closed

    Examples:
      | Feature    | Mileage | Location   | Condition | QuantityAdjust |
      | Promotions | 175000  | Scottsdale | Poor      | 4              |

  @ovcBBA
  @12843
  Scenario Outline: Oneview_Promotions_Discounts_Applying_Money_Discount_Manager_Override_Total_Invoice (ALM# 12843)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the word "Total" on the Virtual Receipt
    And  I select the price toggle to set it to "Dollar"
    Then I verify the discount type is toggled to "Dollar" off
    When I select the "Customer Satisfaction" radio button on the "Popup" page
    And  I enter a discount value of "$5000.00" into the order discount textbox
    And  I select the "OK" button on the "Popup" page
    Then I verify the dialog contains "You have exceeded your discount limit."
    When I select the "Yes" button on the "Popup" page
    And  I enter Manager Approval credentials in to the window and click "Approve"
    And  I select the "OK" button on the "Popup" page
    And  I select the "Cancel" button on the "Popup" page
    And  I select the "No" button on the "Popup" page
    Then I verify that the popup with a "No" button is closed

    Examples:
      | Feature    | Mileage | Location   | Condition | QuantityAdjust |
      | Promotions | 175000  | Scottsdale | Poor      | 4              |

  @ovcBBA
  @12844
  Scenario Outline: Oneview_Promotions_ Discounts_Applying_Money_Discount_On_Product (ALM# 12844)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the "Close" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I extract the "Balance" total from the home page
    And  I select the word "Total" on the Virtual Receipt
    And  I select the price toggle to set it to "Dollar"
    Then I verify the discount type is toggled to "Dollar" off
    When I select the "Customer Satisfaction" radio button on the "Popup" page
    And  I enter a discount value of "25.00" into the order discount textbox
    And  I select the "OK" button on the "Popup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    | Mileage | Location   | Condition | QuantityAdjust |
      | Promotions | 175000  | Scottsdale | Poor      | 4              |

  @ovcBBA
  @12845
  Scenario Outline: Oneview_Promotions_Discounts_Cancel_Money_Discount_Total_Invoice (ALM# 12845)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the word "Total" on the Virtual Receipt
    And  I select the price toggle to set it to "Dollar"
    Then I verify the discount type is toggled to "Dollar" off
    When I select the "Customer Satisfaction" radio button on the "Popup" page
    And  I enter a discount value of "$100.00" into the order discount textbox
    And  I select the "OK" button on the "Popup" page
    And  I select the red "x" for the line of "CancelItem" for feature "<Feature>"
    Then I verify the dialog contains "<CancelConfirm>"
    When I select the "OK" button on the "Popup" page
    Then I verify that the popup with a "OK" button is closed

    Examples:
      | Feature    | Mileage | Location   | Condition | CancelConfirm      | QuantityAdjust |
      | Promotions | 175000  | Scottsdale | Poor      | void this discount | 4              |

  @ovcBBA
  @12846
  Scenario Outline: Oneview_Promotions_Discounts_Cancel_Percentage_Discount_Total_Invoice (ALM# 12846)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I select the word "Total" on the Virtual Receipt
    And  I select the price toggle to set it to "Percent"
    Then I verify the discount type is toggled to "Percent" off
    When I select the "Customer Satisfaction" radio button on the "Popup" page
    And  I enter a discount value of "50" into the order discount textbox
    And  I select the "OK" button on the "Popup" page
    And  I select the red "x" for the line of "CancelItem" for feature "<Feature>"
    Then I verify the dialog contains "<CancelConfirm>"
    When I select the "OK" button on the "Popup" page
    Then I verify that the popup with a "OK" button is closed

    Examples:
      | Feature    | Mileage | Location   | Condition | CancelConfirm      | QuantityAdjust |
      | Promotions | 175000  | Scottsdale | Poor      | void this discount | 4              |

  @ovcSmokeTest
  @ovcBBA
  @12959
  Scenario Outline: OVC_Promotions_Coupons_With_Validation_Applying_Coupon_Before_Adding_Product_ (ALM# 12959)
    When I enter "CouponCode" for feature "<Feature>" in the article search box
    And  I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    Then I verify "Tire" dropdown fields are displayed on Lookup Page
    When I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    And  I verify "CouponCode" for feature "<Feature>" appears on the Virtual Receipt
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    | QuantityAdjust |
      | Promotions | 4              |

  @ovcBBA
  @12958
  Scenario Outline: OVC_Promotions_Coupons_With_Validation_Applying_Coupon_After_Adding_Product_ (ALM# 12958)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    Then I verify "Tire" dropdown fields are displayed on Lookup Page
    When I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I enter "CouponCode" for feature "<Feature>" in the article search box
    And  I wait for the OVC preloader to be no longer visible
    Then I verify "CouponCode" for feature "<Feature>" appears on the Virtual Receipt
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    | QuantityAdjust |
      | Promotions | 4              |

  @ovcBBA
  @12839
  Scenario Outline:  Oneview_Promotions_Coupons_With_Validation_User_Can_Not_Remove_Coupon (ALM# 12839)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I enter "CouponCode" for feature "<Feature>" in the article search box
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the red "x" not visible for the line of "CouponCode" for feature "<Feature>"

    Examples:
      | Feature    | QuantityAdjust |
      | Promotions | 4              |

  @ovcBBA
  @12836
  Scenario Outline:  Oneview_Promotions_Coupons_With_Validation_Applying_Coupon_After_Adding_Product (ALM# 12836)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I enter "CouponCode" for feature "<Feature>" in the article search box
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    | QuantityAdjust |
      | Promotions | 4              |

  @ovcBBA
  @12848
  Scenario Outline:  Oneview_Promotions_Promotion_Applying_Multiple_Promotions (ALM# 12848)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Close" button on the "Lookup" page
    Then I verify the page currently displayed is the "Home" page
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I enter "ItemNumber2" for feature "<Feature>" in the article search box
    Then I verify the page currently displayed is the "Home" page
    When I adjust the item quantity of "ItemNumber2" for feature "<Feature>" to "<QuantityAdjust>"
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    | QuantityAdjust |
      | Promotions | 4              |

  @ovcBBA
  @12847
  Scenario Outline: Oneview_Promotions_Promotion_Applying_Promotion (ALM# 12847)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I enter "12" into the "RF" Tire Stats
    And  I enter "1490" into the "LR" Tire Stats
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber4" for feature "<Feature>" in the article search box
    Then I verify "ItemNumber4" for feature "<Feature>" appears on the Virtual Receipt
    When I adjust the item quantity of "ItemNumber4" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "ItemNumber4" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I extract the subtotal of item "ItemNumber4" for feature "<Feature>"
    And  I extract the subtotal of item "ItemNumber5" for feature "<Feature>"
    Then I verify actual subtotal is "<PromotionAmount>" less than the extracted subtotal
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    Then I verify the popup alert contains "CSL OPTIONS"

    Examples:
      | Feature    | Mileage | Location   | Condition | QuantityAdjust | PromotionAmount |
      | Promotions | 175000  | Scottsdale | Poor      | 4              | 50              |