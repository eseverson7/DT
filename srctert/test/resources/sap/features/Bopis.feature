@sap
@bopis
Feature: BOPIS Tests

  Background:
    Given I go to the sap homepage
    When  I login with System, Client, Username, and Password
    And   I wait for the RTC SAP home page to load

  @posdtBopis
  Scenario Outline: POSDT Bopis Order Create (ALM# None)
    When I enter t-code "<TCode>" in the command field
    And  I enter Store and Posting Date
    And  I select the "Goods Movements" option from the right side of the page
    And  I select the first transaction from left side table
    Then I save the transaction number and type to the scenario data
    And  I verify the customer information is correctly displayed after selecting "Customer information"

    Examples:
      | TCode         |
      | /N/POSDW/MON0 |