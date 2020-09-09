@pdl
@regression_pdl
@tireComparison
Feature: PDL Tire Comparison Tests

  Background:
    Given I go to the pdl homepage

  @regression_pdl
  @10993
  @tireComparisonStandard
  Scenario Outline: PDL_TC1-01_Tire Comparison_Standard (ALM #10993)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I verify the "Typical" Driving Priorities
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    When I select View Tire Recommendations
    When I select the product checkbox for item "<ItemId1>" from the products list results
    When I select the product checkbox for item "<ItemId2>" from the products list results
    When I select the compare tires button
    Then I verify tire comparison page header is "TIRE COMPARISON"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the back button is labeled "Results"
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId1>"
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId2>"
    And  I verify each tire in all comparison sections contains a graph
    And  I verify each comparison section of the tire comparison page contains at least one green ribbon
    When I select "View Details" for item id "<ItemId2>" from the compare page
    Then I verify tire details page header is "TIRE DETAILS"
    And  I verify the tire brand "<Brand>" is listed on tire details page
    And  I verify the tire name "<TireName>" is listed on tire details page
    And  I verify the stopping distance ratings labels are listed on tire details page
    And  I verify the expected tire life range labels are listed on tire details page
    And  I verify the cost rating miles per dollar label is listed on tire details page

    Examples:
      | StoreID | PayrollID | Year | Make  | Model | Trim     | Assembly | ItemId1 | ItemId2 | Brand    | TireName  |
      | MNM29   | 919919    | 2012 | Honda | Civic | Coupe DX | none     | 19661   | 27334   | Yokohama | YK740 GTX |


  @regression_pdl
  @10990
  Scenario Outline: PDL_Auto_Recommended Tires (ALM @10990)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId1>" from the products list results
    Then I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId2>" from the products list results
    Then I verify that compare tires button is "enabled" on results page
    When I select the product checkbox for item "<ItemId3>" from the products list results
    Then I verify "2" checkboxes were selected

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | Year | Make  | Model | Trim     | Assembly | TireSize   | ItemId1 | ItemId2 | ItemId3 |
      | MNM29   | 919919    | 55125   | SAINT PAUL, MN | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 19661   | 27334   | 28449   |
