@pdl
@errorConditions
Feature: PDL Error Tests

  Background:
    Given I go to the pdl homepage

  @11363
  @tireSizeError
  Scenario Outline: PDL_DD5-04_Vehicle-OE TIRE SIZE - StandAlone_Vehicle OE Tire_Tire Size Error (ALM# 11363)
  """TODO: This doesn't need to be run as part of regression. The wrong assembly was a bug that was fixed in the application."""
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify the Tire Size error message is visible on the "Right" side of the page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  |
      | MNM 29  | 919919    | 2015 | BMW  | M3    | Sedan | F 255/40-18 - R 275/40-18 |