@ovc
@airCheck
Feature: AirCheck Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcRegression
  @ovcBBA
  @12087
  Scenario Outline: Oneview_Analytics_Tagging_Aircheck_With_Customer (ALM# 12087)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "Air Check" icon from the Global Header
    Then I verify the page currently displayed is the "Air Check" page
    When I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "OE Pressure" button on the "Air Check" page
    And  I select the "End" button on the "Air Check" page
    And  I select the "Save" button on the "Air Check" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature  |
      | AirCheck |

  @ovcBBA
  @12086
  Scenario Outline: Oneview_Analytics_Tagging_Aircheck_Without_Customer (ALM# 12086)
    When I select the "Air Check" icon from the Global Header
    Then I verify the page currently displayed is the "Air Check" page
    When I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "OE Pressure" button on the "Air Check" page
    And  I select the "End" button on the "Air Check" page
    And  I select the "Save" button on the "Air Check" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature  |
      | AirCheck |