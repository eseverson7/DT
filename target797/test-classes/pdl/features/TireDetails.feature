@pdl
@regression_pdl
@tireDetails
Feature: PDL Tire Details Tests

  Background:
    Given I go to the pdl homepage

  @regression_pdl
  @10995
  @tireDetailsTypicalOrder2
  Scenario Outline: PDL_TD1-01_Tire Details_Typical order 2 (ALM #10995)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    And  I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    And  I select view details for the tire "<TireName>" from the results page
    Then I verify tire details page header is "TIRE DETAILS"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the tire image and alt text is displayed on tire details page
    And  I verify the tire brand "<Brand>" is listed on tire details page
    And  I verify the tire name "<TireName>" is listed on tire details page
    And  I verify the tire size "<DetailedTireSize>" is listed on tire details page
    And  I verify the tire item id "<ItemId>" is listed on the tire details page
    And  I verify the tire quantity is "<Quantity>" on tire details page
    And  I verify Rating label is present on tire details page
    And  I verify Distance To Stop section header is present on tire details page
    And  I verify Expected Tire Life Range section header is present on tire details page
    And  I verify Cost section header is present on tire details page
    And  I verify Ride section header is present on tire details page
    And  I verify the stopping distance ratings labels are listed on tire details page
    And  I verify the ride ratings labels are listed on tire details page
    And  I verify the expected tire life range labels are listed on tire details page
    And  I verify the cost rating miles per dollar label is listed on tire details page

    Examples:
      | StoreID | PayrollID | Year | Make  | Model | Trim     | Assembly | TireSize   | Brand       | TireName                    | DetailedTireSize       | ItemId | Quantity |
      | MNM29   | 919919    | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | Continental | CONTROL CONTACT TOURING A/S | 195 /65 R15 91T SL BSW | 19661  | 4        |