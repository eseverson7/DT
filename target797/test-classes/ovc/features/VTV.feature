@ovc
@vtv
Feature: Visit the Vehicle Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcBBA
  @12897
  Scenario Outline: Oneview_Store_Experience_Visit_the_Vehicle (ALM# 12897)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I enter "3" into the "LF" Tire Stats
    Then I verify the color of the dot next to the "LF" tag is "Red"
    When I enter "4" into the "LF" Tire Stats
    Then I verify the color of the dot next to the "LF" tag is "Yellow"
    When I enter "12" into the "LF" Tire Stats
    Then I verify the color of the dot next to the "LF" tag is "Green"
    And  I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "TPMS Status N/A" radio button on the VTV page
    And  I select the "TPMS Status Solid" radio button on the VTV page
    And  I select the "TPMS Status Flashing" radio button on the VTV page
    And  I select the "Wheel Locks N/A" radio button on the VTV page
    And  I select the "Wheel Locks Yes" radio button on the VTV page
    And  I select the "Carry Out Yes" radio button on the VTV page
    And  I select the "Carry Out N/A" radio button on the VTV page
    And  I select the "Add/Remove Dual" symbol on the VTV page
    And  I select the "Add/Remove Dual" symbol on the VTV page
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "VTV" icon from the Global Header
    And  I select the "Cancel" button on the "VTV" page
    Then I verify the dialog contains "Data Loss Warning"
    When I select the "No" button on the "Popup" page
    And  I select the "Save" button on the "VTV" page
    And  I select the "Home" icon from the Global Header
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the "Home" icon from the Global Header
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition |
      | VTV     | 1200    | Santa Fe | New       |
      
  @tLogRegression
  @vtvFreeRepairNoMoneyNoInventory
  Scenario Outline: VtvFreeRepairNoMoneyNoInventory (ALM# NONE)  
    When I select the "VTV" icon from the Global Header
    And  I select the "Repair" selection for the "RF" Tire Service section
    And  I select the "Save" button on the "VTV" page
    And  I select the "Add" button on the "Popup" page
    And  I change tender amount to "<Tender>"
    And  I select the "Ok" button on the "Popup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Close" button on the "Lookup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    And  I select the "OK" button on the "Printer" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    
    Examples:
      | Feature | Tender |
      | VTV     | 0.00   |
