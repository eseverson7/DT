@TODO
Feature: Credit Card payment type tests
"""Credit card payment tests are not currently delivered functionality, but these test cases will be integrated into
  Payment.feature when it is delivered. Per Mandana we are dropping these in this feature which wont be run for storage"""

  Background:
    When I go to the ovc homepage
    And  I login with Server, Username, and Password

  @12037
  Scenario Outline: Payment_PaymentsandTenders_Payment_Integration_Sales_Credit_card_charge_Service_LaterComplete (ALM# 12037)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Card Payment" link from the Checkout menu
    Then I verify the dialog contains "Please provide tender amount"
    And  I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I verify the dialog contains "Service Later/Complete"
    When I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition |
      | Payment | 25000   | Phoenix  | Fair      |


  @12038
  Scenario Outline: Payment_PaymentsandTenders_Payment_Integration_Sales_Credit_card_charge_for_partial_payment_Use_Cash_to_pay_remaining_balance_Set_appointment (ALM# 12038)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I extract the subtotal from the home page
    And  I extract the subtotal of item "ItemNumber" for feature "<Feature>"
    And  I modify the price of "ItemNumber" for feature "<Feature>" on the Virtual Receipt to "<NewPrice>"
    And  I select the "Employee Discount" option on the Price Override popup
    Then I verify the new subtotal reflects the change from "<NewPrice>"
    When I select the "Cash" link from the Checkout menu
    Then I verify the dialog contains "Please provide tender amount"
    When I change tender amount to "60.00"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Card Payment" link from the Checkout menu
    Then I verify the dialog contains "Please provide tender amount"
    When I select the "Next" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the dialog contains "Appointment"
    When I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "Tire Rotation and Balance"
    And  I extract the "Balance" total from the home page
    Then I verify the "Balance" total matches "0.00"
    And  I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition | NewPrice |
      | Payment | 50000   | Phoenix  | Good      | 100.00   |

  @12039
  Scenario Outline: Payment_PaymentsandTenders_Payment_Integration_Sales_Card_not_present_Add_to_CSL (ALM# 12039)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Card Payment" link on the page
    Then I verify the dialog contains "Please provide tender amount"
    When I select the "Next" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition |
      | Payment | 50000   | Phoenix  | Good      |