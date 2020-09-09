@applitools
@OvcVisualRegression
Feature: OVC Visual Tests

  Background:
    Given I go to the ovc homepage

  @visualtests
  @homePage
  Scenario: Oneview_HomePage_VisualTest (ALM# NONE)
  """ Test here are missing a few options
    - Manager functions
    - Retrieve quotes
    - Reports """
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Home Page" for test name "Home Page tests"
    Then I use Eyes to verify the Page "Home page" displays
    And  I close the connection for visual test

  @transactionTypeTests
  Scenario: Oneview_TransactionType_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Transaction Search Page" for test name "Transaction Search tests"
    And  I select the "Transaction Search" link on the page
    And  I enter "Special Order" into the "TransactionType" textbox and send Enter key
    Then I use Eyes to verify the Page "Special Order page" displays
    And  I close the connection for visual test

  @visualtests
  @pdlTests
  Scenario: Oneview_PDL_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "PDL Page" for test name "PDL Page tests"
    And  I select the "PDL" link on the page
    Then I use Eyes to verify the Page "PDL dropdown page" displays
    And  I close the connection for visual test

  @cashManagementTests
  Scenario Outline: Oneview_Cash_Management_VisualTest (ALM# NONE)
  """ Many options still incomplete inside of Pay In and Pay Out windows """
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Cash Management" for test name "Cash Management tests"
    And  I select the "Cash Management" link on the page
    Then I use Eyes to verify the Page "Cash Management" displays
    When I select the "Till Spot Check" link on the page
    Then I use Eyes to verify the Page "Till Spot Check" displays
    When I select the "Done" button on the "Popup" page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select the "End of Day" link on the page
    Then I use Eyes to verify the Page "End of Day" displays
    When I select the "OK" button on the "Popup" page
    And  I select the "Pay In" link on the page
    Then I use Eyes to verify the Page "Pay In" displays
    When I select the "Employee Reimbursement" radio button on the "Cash Management" page
    Then I use Eyes to verify the Page "Pay In - Employee Reimbursement" displays
    When I select the "OK" button on the "Popup" page
    And  I enter "FirstName" into the "First Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "PayrollId" into the "Payroll ID" "Select Tender" input field for feature "<Feature>"
    And  I enter "Applitools" into the "Reimbursement" "Select Tender" input field
    And  I enter "Comments" into the comments field
    And  I select "Cash" Tender and enter amount "<Amount>" with Serial Number ""
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I use Eyes to verify the Page "Checkout Menu Options" displays
    When I select the "Close" button on the "Home" page
    And  I select the "Cash Management" link on the page
    And  I select the "Pay Out" link on the page
    Then I use Eyes to verify the Page "Pay Out" displays
    When I select the "Gasoline/Mileage" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I use Eyes to verify the Page "Pay Out - Gasoline/Mileage" displays
    When I select the "Back" button on the "Popup" page
    And  I select the "Reverse RV" link on the page
    Then I use Eyes to verify the Page "Reverse RV" displays
    When I select the "Cancel" button on the "Popup" page
    And  I select the "Reverse PV" link on the page
    Then I use Eyes to verify the Page "Reverse PV" displays
    When I select the "Cancel" button on the "Popup" page
    And  I select "Quick Deposit" option from the right pane nav bar
    Then I use Eyes to verify the Page "Quick Deposit" displays
    When I enter "<Tracer>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    Then I use Eyes to verify the Page "Quick Deposit Total Amount" displays
    When I select the "Next" button on the "Popup" page
    And  I select the "Done" button on the "Popup" page
    And  I select "Nightly Deposit" option from the right pane nav bar
    Then I use Eyes to verify the Page "Nightly Deposit" displays
    When I select the "Next" button on the "Popup" page
    Then I use Eyes to verify the Page "Tracer Bag" displays
    When I enter "<Tracer>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    Then I use Eyes to verify the Page "Manual Denomination Count" displays
    When I select the "Next" button on the "Popup" page
    And  I select the "Complete" button on the "Popup" page
    And  I select the "Done" button on the "Popup" page
    And  I close the connection for visual test

    Examples:
      | Feature        | Amount | Tracer |
      | CashManagement | 5.00   | 1454   |

  @cslTest
  Scenario: Oneview_CSL_VisualTest (ALM# NONE)
  """ CSL in flux. Need to determine scope of the visual tests """

  @alertsTest
  Scenario: Oneview_Alerts_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Alerts Page" for test name "Alerts tests"
    And  I select the "Alerts" icon from the Global Header
    Then I use Eyes to verify the Page "Alerts page" displays
    When I select the "Modify" button from the alert page
    Then I use Eyes to verify the Page "Modify appointment window" displays
    When I select the "Cancel" button on the "Popup" page
    And  I select the "Notes" button from the alert page
    Then I use Eyes to verify the Page "Alert Notes" displays
    When I select the "Close" button on the "Popup" page
    And  I expand the "First" dropdown on the Alerts page
    Then I use Eyes to verify the Page "Alert Dropdown 1" displays
    When I expand the "Second" dropdown on the Alerts page
    Then I use Eyes to verify the Page "Alert Dropdown 2" displays
    When I expand the "Third" dropdown on the Alerts page
    Then I use Eyes to verify the Page "Alert Dropdown 3" displays
    When I expand the "Fourth" dropdown on the Alerts page
    Then I use Eyes to verify the Page "Alert Dropdown 4" displays
    And  I close the connection for visual test

  @createApptTest
  Scenario: Oneview_Create_Appointment_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Appointment Page" for test name "Appointment"
    And  I select the "Appointment" icon from the Global Header
    Then I use Eyes to verify the Page "Appointment page" displays
    When I expand the Appointment Reason dropdown list
    Then I use Eyes to verify the Page "Appointment Reason dropdown page" displays
    When I expand the Appointment Reason dropdown list
    And  I select "Product" as the reason for the appointment
    Then I use Eyes to verify the Page "Appointment populated page" displays
    And  I close the connection for visual test

  @visualtests
  @articleSearch
  Scenario: Oneview_Article_Search_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Lookup page" for test name "Lookup"
    And  I select the "Lookup" icon from the Global Header
    And  I expand the "Lookup" dropdown on the page
    Then I use Eyes to verify the Page "Lookup dropdown" displays
    When I expand the "Lookup" dropdown on the page
    And  I select "TIRE" from the Lookup page dropdown
    Then I use Eyes to verify the Page "Lookup Tire dropdown" displays
    When I select "WHEEL" from the Lookup page dropdown
    Then I use Eyes to verify the Page "Lookup Wheel dropdown" displays
    When I select "MISC WHEEL" from the Lookup page dropdown
    Then I use Eyes to verify the Page "Lookup Misc Wheel dropdown" displays
    When I select "MISC TIRE" from the Lookup page dropdown
    Then I use Eyes to verify the Page "Lookup Misc Tire dropdown" displays
    When I select "ACCESSORY" from the Lookup page dropdown
    Then I use Eyes to verify the Page "Lookup Accessory dropdown" displays
    And  I close the connection for visual test

  @customerTest
  Scenario: Oneview_Customer_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Customer Page" for test name "Customer tests"
    And  I select the "Customer" icon from the Global Header
    And  I select the "Search" button on the "Customer" page
    Then I use Eyes to verify the Page "Customer page" displays

  @airCheckTest
  Scenario: Oneview_Air_Check_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Air Check Page" for test name "Air Check tests"
    And  I select the "Air Check" icon from the Global Header
    Then I use Eyes to verify the Page "Air Check page" displays
    And  I close the connection for visual test

  @fitmentTest
  Scenario: Oneview_Fitment_VisualTest (ALM# NONE)
  """ Take images of the dropdowns here? """
    When I login with Server, Username, and Password
    And  I specify visual test batch id "Fitment Page" for test name "Fitment tests"
    And  I select the "Fitment" icon from the Global Header
    Then I use Eyes to verify the Page "Fitment page" displays
    And  I close the connection for visual test

  @vtvWorkorderTest
  Scenario: Oneview_VTV_Workorder_VisualTest (ALM# NONE)
    When I login with Server, Username, and Password
    And  I specify visual test batch id "VTV Page" for test name "VTV tests"
    And  I select the "VTV" icon from the Global Header
    Then I use Eyes to verify the Page "VTV page" displays
    When I expand the Tire Rotation Pattern dropdown
    Then I use Eyes to verify the Page "VTV expanded page" displays
    And  I close the connection for visual test