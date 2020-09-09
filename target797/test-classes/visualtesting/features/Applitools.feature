@at
@dt
@dtd
@applitools
Feature: Test Applitools integration on fitment panel and results page

  Background:
    Given I change to the default store

  @applitoolsFitmentPanelAndResultsPage
  @web
  @mobile
  Scenario Outline: Search by Vehicle using the Homepage menu(ALM #8864, 8867, 8868, 8790, 8789, 8782)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify the Window "fitmentBoxCarName" for Batch "vehiclePanel", App "VehiclePanel" and Test "Fitment Search"
    When I select a fitment option "<FitmentOption>"
    Then I verify the Region "results" for Batch "Page Header", App "Header" and Test "Page Header"

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | FitmentOption |
      | 2015 | Honda | Accord | Coupe EX | none     | All Wheels    |


  @applitoolsPLPPDPPage
  @web
  Scenario Outline: Search by Vehicle using the Homepage menu(ALM #8864, 8867, 8868, 8790, 8789, 8782)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    Then I verify the Window "PDPPage" for Batch "PDP", App "PDP" and Test "Free Product Search"

    Examples:
      | ItemCode | ProductName   |
      | 17928    | Potenza RE-11 |