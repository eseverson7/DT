@posInactive
Feature: POS left inactive tests

  Background:
    When I go to the ovc homepage
    And  I login with Server, Username, and Password

  @12850
  Scenario Outline: Oneview_Enterprise_Dashboard_Enterprise_Dashboard_Auto_Log_Off_Pending_Transaction (ALM# 12850)
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
    And  I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When I leave the POS inactive for over a minute
    Then I verify the page currently displayed is the "Login" page

    Examples:
      | Feature     | Mileage | Location   | Condition | QuantityAdjust |
      | POSInactive | 50000   | Scottsdale | Excellent | 4              |

  @12851
  Scenario: Oneview_Enterprise_Dashboard_Enterprise_Dashboard_Auto_Log_Off (ALM# 12851)
    When I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I leave the POS inactive for over a minute
    Then I verify the page currently displayed is the "Login" page


  @12852
  Scenario Outline: Oneview_Enterprise_Dashboard_Enterprise_Dashboard_Auto_Log_Off_Transaction_Void (ALM# 12852)
    When I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    And  I verify the page currently displayed is the "Home" page
    When I leave the POS inactive for over a minute
    Then I verify the page currently displayed is the "Login" page

    Examples:
      | Mileage | Location   | Condition |
      | 50000   | Scottsdale | Excellent |