@ovc
@trainingMode
Feature: Training Mode Tests

  Background:
    Given I go to the ovc homepage
    When  I login to "Training Mode" with Server, Username, and Password

  @ovcBBA
  @12828
  Scenario: Oneview_Training_Mode_Login (ALM# 12828)
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    And  I verify the popup alert contains "Store is now open"
    When I select the "Complete" button on the "Popup" page
    Then I verify that the popup with a "Complete" button is closed

  @ovcBBA
  @12830
  Scenario: Oneview_Training_Mode_Logout (ALM# 12830)
    When I select the "LOGOFF" button on the "Home" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify that the popup with a "Yes" button is closed

  @ovcBBA
  @12829
  Scenario Outline: Oneview_Training_Mode_Sales_Transactions (ALM# 12829)
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select "Main" option from the right pane nav bar
    Then I verify that the popup with a "Complete" button is closed
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "CHECKOUT" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature      |
      | TrainingMode |

  @ovcBBA
  @12831
  Scenario: Oneview_Training_Mode_Cash_Management (ALM# 12831)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Till Spot Check" link on the page
    Then I verify "training default values" appears on the Till Spot Check popup
    When I select the "Done" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"

  @ovcBBA
  @12833
  Scenario Outline: Oneview_Training_Mode_Returns (ALM# 12833)
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select "Main" option from the right pane nav bar
    Then I verify that the popup with a "Complete" button is closed
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "CHECKOUT" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
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
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the page currently displayed is the "Lookup" page
    And  I select the "Home" icon from the Global Header
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
      | Feature      |
      | TrainingMode |