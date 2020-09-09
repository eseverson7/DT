@integration
@ovcRegression
Feature: Cross Application Scenarios

  @12902
  Scenario Outline: Oneview_Store_Experiance_Cross_Channel_ROPIS (ALM# 12902)
    Given I store the current baseUrl
    When  I set baseUrl to "hybris"
    And   I checkout "<AppointmentType>" in Hybris using data for feature "<Feature>" and store the order number
    And   I set baseUrl to the stored baseUrl
    And   I go to the ovc homepage
    And   I login with Server, Username, and Password
    And   I select the "Alerts" icon from the Global Header
    Then  I do an alert search for the stored order number and assert it is visible
    When  I select all items on the alert page
    And   I select the "Virtual Receipt" button from the alert page
    Then  I verify the page currently displayed is the "Home" page
    When  I wait for the OVC preloader to be no longer visible
    And   I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    And   I wait for the OVC preloader to be no longer visible
    Then  I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    When  I extract the "Balance" total from the home page
    And   I select the "Vehicles" button on the "Home" page
    And   I select the Add New vehicle button
    And   I select vehicle data for feature "<Feature>" and complete a vehicle search
    And   I select the "Save" button on the "Vehicle View" page
    Then  I verify the page currently displayed is the "Lookup" page
    When  I wait for the OVC preloader to be no longer visible
    And   I select the "Home" icon from the Global Header
    Then  I verify the page currently displayed is the "Home" page
    And   I verify that the vehicle description for feature "<Feature>" appears on the home page
    When  I fill out Visit The Vehicle page information for feature "<Feature>"
    Then  I verify the page currently displayed is the "Home" page
    When  I select the "Checkout" button on the "Home" page
    And   I select the "Cash" link from the Checkout menu
    Then  I verify tender amount equals the order price total
    When  I select the "Next" button on the "Popup" page
    And   I select the "Add to CSL" button on the "Popup" page
    And   I enter a target time for CSL over an hour in the future
    And   I select the "Complete" button on the "Popup" page
    Then  I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature          | AppointmentType     | QuantityAdjust |
      | CrossApplication | without appointment | 5              |
      | CrossApplication | with appointment    | 5              |