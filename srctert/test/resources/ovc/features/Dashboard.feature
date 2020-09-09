@ovc
@dashboard
Feature: Dashboard Tests

  Background:
    Given I go to the ovc homepage

  @ovcDashboardCheckJobStatus
  Scenario Outline: Oneview_check_status_importer_jobs
    When I login to dashboard with Username, and Password
    When I click on system, scheduled jobs on the left pane nav bar
    Then I verify the desired job "<Status>" is displayed

    Examples:
      | Status   |
      | FINISHED |

   @ovcRegression
   @15354
   Scenario: Go to OVC Dashboard and validate tree options (ALM# 15354)
     When I navigate to the OVC Dashboard "https://test-dash.ovcdtc.com/ovcdashboard/login"
     And  I login to dashboard with Username, and Password
     And  I select the "Locations" link on the page
     And  I select the "Location Hierarchy" link on the page
     And  I select the "  Tree View" link on the page
     And  I select the "Collapse All" partial link element
     And  I select the 'Addition/Subtraction' symbol for "dt-all" on the OVC Dashboard page
     And  I select the 'Addition/Subtraction' symbol for "US" on the OVC Dashboard page
     And  I select the 'Addition/Subtraction' symbol for "REINALT-THOMAS" on the OVC Dashboard page
     Then I verify all the correct labels are listed under the "REINALT-THOMAS" label