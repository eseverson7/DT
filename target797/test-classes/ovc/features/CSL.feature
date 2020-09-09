@ovc
@csl
Feature: CSL Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcBBA
  @12898
  Scenario Outline: Oneview_Store_Experience_Work_Order (ALM# 12898)
  """TODO temporary steps navigating to csl, back to home, then back to csl to force CSL data to populate"""
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "CSL" icon from the Global Header
    And  I select the "Back To Sale" option on the CSL page
    And  I select the "CSL" icon from the Global Header
    And  I switch "To" the iframe window
    Then I verify header menus exist on CSL page
    When I remove all active work orders from the Active list on the Service Home page
    And  I remove all the disabled work orders on the Inactive list on the Service Home Page
    Then I verify the Inactive list has at least one order on the Service Home page
    When I click on the Work Order with the title "CustomerName" for the feature "<Feature>" from the Inactive list
    Then I verify the "CustomerVehicle" for feature "<Feature>" is in the work order title
    And  I verify there is service text in at least one of the tire boxes
    When I enter the DOT number into all of the DOT inputs on the page
    Then I verify the DOT number is entered in all of the DOT inputs on the page
    And  I verify the invoice is "Paid"
    When I select the "Return/Exchange" button on the "CSL" page
    And  I switch "Back From" the iframe window
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return" button on the "Transaction View" page
    And  I select "Return" from the Return page dropdown
    And  I select the "Select All" checkbox on the page
    And  I select the "Return" option on the Return page
    Then I verify the page currently displayed is the "Home" page
    When I wait for the OVC preloader to be no longer visible
    And  I select the "Close" button on the "Lookup" page
    And  I select the "CSL" icon from the Global Header
    And  I select the "Back To Sale" option on the CSL page
    And  I select the "CSL" icon from the Global Header
    And  I switch "To" the iframe window
    And  I click on the Work Order with the title "CustomerName" for the feature "<Feature>" from the Inactive list
    Then I verify the Air Pressure values on the CSL page
    When I select "Puncture" repair reason for the CSL work order
    And  I enter "BR" into the Service Coordinator field on the CSL Work Order page
    Then I verify that the Service Coordinator field on the CSL Work Order page has value of "BR"
    When I select the "Bay Out" check box on the CSL page
    And  I select the "Save" button on the "CSL" page
    And  I select the "Service Home" icon from the CSL header
    And  I switch "Back From" the iframe window
    And  I select the "Back To Sale" option on the CSL page
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature |
      | CSL     |

  @ovcBBA
  @12876
  Scenario Outline: Oneview_Scheduling_Workflow_CSL_Active_Pane (ALM# 12876)
  """TODO temporary steps navigating to csl, back to home, then back to csl to force CSL data to populate"""
    When I select the "CSL" icon from the Global Header
    And  I select the "Back To Sale" option on the CSL page
    And  I select the "CSL" icon from the Global Header
    And  I switch "To" the iframe window
    Then I verify header menus exist on CSL page
    When I remove all active work orders from the Active list on the Service Home page
    And  I remove all the disabled work orders on the Inactive list on the Service Home Page
    Then I verify the Inactive list has at least one order on the Service Home page
    When I select the "Delay" button from the Inactive CSL Service Home list
    And  I select the "No Delay: Service Expected To Exceed 30 Min" option from the delay reason dropdown
    And  I select the "Save" button from the CSL popup
    Then I verify the green dialog with text "Successfully Delayed In" appears
    When I select the "IN" button from the Inactive CSL Service Home list
    Then I verify the green dialog with text "Successfully Bayed In" appears
    When I select the "Service Home" icon from the CSL header
    And  I select the "Back" button from the Active CSL Service Home list
    And  I click on the Work Order with the title "CustomerName" for the feature "<Feature>" from the Active list
    And  I select "Puncture" repair reason for the CSL work order
    And  I enter "BR" into the Service Coordinator field on the CSL Work Order page
    Then I verify that the Service Coordinator field on the CSL Work Order page has value of "BR"
    When I select the "Bay Out" check box on the CSL page
    And  I select the "Save" button on the "CSL" page
    Then I verify the green dialog with text "Successfully Updated" appears
    When I select the "Done" icon from the CSL header
    And  I select the blank checkbox on the Done CSL tab
    And  I select the "Return to Bay" button on the "CSL" page
    Then I verify the green dialog with text "Successfully Updated" appears
    When I select the "Service Home" icon from the CSL header
    And  I select the "OUT" button from the Active CSL Service Home list
    Then I verify the green dialog with text "Successfully Bayed Out" appears

    Examples:
      | Feature |
      | CSL     |

  @ovcBBA
  @12877
  Scenario: Oneview_Scheduling_Workflow_CSL_Pull_List (ALM# 12877)
  """TODO temporary steps navigating to csl, back to home, then back to csl to force CSL data to populate"""
    When I select the "CSL" icon from the Global Header
    And  I select the "Back To Sale" option on the CSL page
    And  I select the "CSL" icon from the Global Header
    And  I switch "To" the iframe window
    Then I verify header menus exist on CSL page
    When I remove all active work orders from the Active list on the Service Home page
    And  I remove all the disabled work orders on the Inactive list on the Service Home Page
    Then I verify the Inactive list has at least one order on the Service Home page
    When I select the "Pull List" icon from the CSL header
    And  I select the chevron dropdown to expand the pull list work order
    And  I fill out the "DOTNumber" input from the pull list
    And  I fill out the "PulledQuantity" input from the pull list
    And  I select the "Pull" button on the "CSL" page
    Then I verify the green dialog with text "Successfully Pulled" appears
    When I select the "Delay" button on the "CSL" page
    And  I select the "No Delay: Service Expected To Exceed 30 Min" option from the delay reason dropdown
    And  I select the "Save" button from the CSL popup
    Then I verify the green dialog with text "Successfully Delayed In" appears
    When I select the "Service Home" icon from the CSL header
    Then I verify the page currently displayed is the "CSL" page

  @ovcSmokeTest
  @ovcBBA
  @12879
  Scenario Outline: Oneview_Scheduling_Workflow_CSL_Return_for_Service (ALM# 12879)
  """TODO temporary steps navigating to csl, back to home, then back to csl to force CSL data to populate"""
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    Then I verify the VTV icon color is "Green"
    When I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "CSL" icon from the Global Header
    And  I select the "Back To Sale" option on the CSL page
    And  I select the "CSL" icon from the Global Header
    And  I switch "To" the iframe window
    Then I verify header menus exist on CSL page
    When I remove all active work orders from the Active list on the Service Home page
    And  I remove all the disabled work orders on the Inactive list on the Service Home Page
    Then I verify the Inactive list has at least one order on the Service Home page
    When I select the "IN" button from the Inactive CSL Service Home list
    Then I verify the green dialog with text "Successfully Bayed In" appears
    When I select the "Service Home" icon from the CSL header
    And  I click on the Work Order with the title "CustomerName" for the feature "<Feature>" from the Active list
    Then I verify the "CustomerVehicle" for feature "<Feature>" is in the work order title
    When I select "Puncture" repair reason for the CSL work order
    And  I enter "BR" into the Service Coordinator field on the CSL Work Order page
    Then I verify that the Service Coordinator field on the CSL Work Order page has value of "BR"
    When I select the "Bay Out" check box on the CSL page
    And  I select the "Save" button on the "CSL" page
    Then I verify the green dialog with text "Successfully Updated" appears
    When I select the "Service Home" icon from the CSL header
    And  I switch "Back From" the iframe window
    And  I select the "Back To Sale" option on the CSL page
    Then I verify the page currently displayed is the "Home" page
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select customer for feature "<Feature>" on home page
    Then I verify the page currently displayed is the "Client Details" page
    When I select the customer page transaction with the previous receipt number
    And  I select the "Return for Service" button on the "Client Details" page
    And  I select the "No Return" option on the Return page
    Then I verify the page currently displayed is the "Home" page
    When I enter "ItemNumber3" for feature "<Feature>" in the article search box
    And  I fill out Visit The Vehicle page information for feature "<Feature>"
    Then I verify the VTV icon color is "Green"
    When I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I select the "OK" button on the "Printer" page
    When I select "Close" option from the right pane nav bar
    And  I select the "CSL" icon from the Global Header
    And  I switch "To" the iframe window
    Then I verify header menus exist on CSL page

    Examples:
      | Feature |
      | CSL     |

  @ovcRegression
  @ovcBBA
  @12878
  Scenario Outline:  Oneview_Scheduling_Workflow_CSL_Appointment_Tab (ALM# 12878)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    Then I verify the page currently displayed is the "Lookup" page
    When I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I check out using the cash Payment Method
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return For Service" button on the "Transaction View" page
    And  I select the "No Return" option on the Return page
    Then I verify the page currently displayed is the "Home" page
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | AppointmentReason |
      | CSL     | Tire Inspection   |

  @ovcBBA
  @12880
  Scenario Outline: Oneview_Scheduling_Workflow_CSL_Inactive_Pane (ALM# 12880)
  """TODO temporary steps navigating to csl, back to home, then back to csl to force CSL data to populate"""
    When I select the "CSL" icon from the Global Header
    And  I select the "Back To Sale" option on the CSL page
    And  I select the "CSL" icon from the Global Header
    And  I switch "To" the iframe window
    Then I verify header menus exist on CSL page
    When I remove all active work orders from the Active list on the Service Home page
    And  I remove all the disabled work orders on the Inactive list on the Service Home Page
    Then I verify the Inactive list has at least one order on the Service Home page
    When I select the "IN" button from the Inactive CSL Service Home list
    Then I verify the green dialog with text "Successfully Bayed In" appears
    When I select the "Service Home" icon from the CSL header
    And  I select the "Delay" button from the Active CSL Service Home list
    And  I select the "No Delay: Service Expected To Exceed 30 Min" option from the delay reason dropdown
    And  I select the "Save" button from the CSL popup
    Then I verify the green dialog with text "Successfully Delayed Out" appears
    When I select the "OUT" button from the Active CSL Service Home list
    And  I acknowledge the work order sign off requirements popup
    And  I click on the Work Order with the title "CustomerName" for the feature "<Feature>" from the Active list
    And  I select "Puncture" repair reason for the CSL work order
    And  I select the "Save" button on the "CSL" page
    And  I select the "Service Home" icon from the CSL header
    And  I select the "OUT" button from the Active CSL Service Home list
    And  I select the "Service Home" icon from the CSL header
    And  I select the "Remove" button from the Inactive CSL Service Home list
    And  I switch "Back From" the iframe window
    And  I select the "Back To Sale" option on the CSL page
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature |
      | CSL     |

  @ovcBBA
  @12875
  Scenario Outline:  Oneview_Inventory_Stock_Management_Suspend-Add_to_CSL_Verify_suspend_invoice_meets_CSL_requirements_to_be_able_to_add_to_CSL (ALM# 12875)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the "Close" button on the "Lookup" page
    And  I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature |
      | CSL     |

  @12796
  @ovcSmokeTest
  Scenario: OVC_SMOKE_CSL (ALM# 12796)
    When I select the "CSL" icon from the Global Header
    Then I verify header menus exist on CSL page