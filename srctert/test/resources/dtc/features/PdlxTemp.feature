@dt
@at
@pdlx
Feature: Tests related to pdlx fitment options

  Background:
    Given I go to the homepage

  @web
  @fitmentPLDX
  @Pdlx
  Scenario Outline: Verify PDLX Fitment UI
  """TODO: Following verify step fails occasionally due to DOM Element issue UI Developer has already been notified
    and looking into it.
    and  I verify "Performance" default pdl driving priority order on search result page"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select enter driving details for recommended tires
    Then I am brought to the pdl fitment page with title "<Title>"
    And  I verify the selected pdl fitment tire size value "<Tire Size>" is displayed
    And  I verify the zipcode has a value of "<ZipCode>" on pdl fitment page
    When I set the driving location zipcode to "<Location>"
    And  I set the miles per year to "<Miles>"
    Then I verify the zipcode has a value of "<Location>" on pdl fitment page
    And  I verify the miles driven per year has a value of "<Miles>" on pdl fitment page
    When I select "Performance" as my driving priority
    And  I select view recommended tires
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I should see "<Location>" as my current zipcode on pdl driving details section
    And  I should see "<Miles>" as my selected miles per year value on pdl driving details section
    And  I should see Our Recommendation banner appears on pdl search result page
    When I select edit pdl driving details on plp page
    Then I am brought to the pdl fitment page with title "<Title>"
    And  I verify the zipcode has a value of "<Location>" on pdl fitment page
    And  I verify the miles driven per year has a value of "<Miles>" on pdl fitment page
    When I set the driving location zipcode to "<ZipCode>"
    And  I set the miles per year to "<New Miles>"
    And  I move "Handling" driving priority to position "3"
    And  I select view recommended tires
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I should see "<ZipCode>" as my current zipcode on pdl driving details section
    And  I should see "<New Miles>" as my selected miles per year value on pdl driving details section

    Examples:
      | ZipCode | Year | Make  | Model | Trim     | Assembly | Tire Size  | Title                | Location | Miles | New Miles |
      | 85260   | 2012 | Honda | Civic | Coupe DX | none     | 195/65-R15 | YOUR DRIVING DETAILS | 85255    | 25    | 45        |