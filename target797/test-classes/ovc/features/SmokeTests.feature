@ovc
@ovcSmokeTest
Feature: OVC Smoke Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @tLogRegression
  @12794
  Scenario Outline: OVC_SMOKE_Quotes (ALM# 12794)
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    When I enter "FirstName" for feature "<Feature>" into the "First Name"
    And  I enter "LastName" for feature "<Feature>" into the "Last Name"
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    Then I verify "Select" button is displayed on customer page
    When I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Create Quote" link on the page
    And  I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"
    When I select the "LOGOFF" button on the "Home" page
    And  I select the "Yes" button on the "popup" page
    Then I verify I am back on the login page

    Examples:
      | Feature    |
      | SmokeTests |

  @ovcSmokeTest
  @ovcRegression
  @cashTire
  Scenario Outline: OVC_CASH_TIRE (ALM# None)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    And  I verify the page currently displayed is the "Home" page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    |
      | SmokeTests |

  @ovcSmokeTest
  @ovcRegression
  @cashWheel
  Scenario Outline: OVC_CASH_WHEEL (ALM# None)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "Wheel" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the "Search" button on the "Lookup" page
    And  I select "WheelSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "WheelSku" of the item for feature "<Feature>" appears on the home page
    And  I verify the page currently displayed is the "Home" page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature    |
      | SmokeTests |