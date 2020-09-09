@ovc
@appointment
Feature: Appointment Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @tLogRegression
  @ovcRegression
  @ovcBBA
  @11829
  @10669
  Scenario Outline: Oneview_Schedule_Appointment_Store_POS_Cancel_Appointment_Time (ALM# 11829)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the page currently displayed is the "Home" page
    When I select the "Alerts" icon from the Global Header
    And  I select the refresh icon on the alerts page
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Cancel" button from the alert page
    Then I verify the "Appointment cancelled successfully" message was displayed

    Examples:
      | Feature     | AppointmentReason |
      | Appointment | Tire Inspection   |

  @ovcBBA
  @11831
  Scenario Outline: Oneview_Schedule_Appointment_Store_POS_Add_Product_To_Appointment_Time (ALM# 11831)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Appointment" button on the "Transaction View" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I verify the dialog contains "<Appointment Confirmation>"
    When I select the "Ok" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Alerts" icon from the Global Header
    And  I select the refresh icon on the alerts page
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Go to transaction search" button from the alert page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return For Service" button on the "Order Search" page
    And  I select the "No Return" option on the Return page
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I enter "ItemNumber3" for feature "<Feature>" in the article search box
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     | AppointmentReason | Appointment Confirmation         |
      | Appointment | Tire Inspection   | Appointment created successfully |

  @ovcRegression
  @ovcSmokeTest
  @tLogRegression
  @ovcBBA
  @11827
  @10612
  Scenario Outline: Oneview_Schedule_Appointment_Store_POS_Appointment_Creation_Service_Later_Transaction (ALM #11827)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Appointment" button on the "Transaction View" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I verify the dialog contains "<Appointment Confirmation>"
    When I select the "Ok" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature     | AppointmentReason | Appointment Confirmation         |
      | Appointment | Tire Inspection   | Appointment created successfully |

  @ovcBBA
  @11825
  Scenario Outline: Oneview_Schedule_Appointment_Store_POS_Sale_Transaction (ALM# 11825)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Appointment" button on the "Popup" page
    Then I am brought to the page with header "Schedule Appointment"
    When I select the first available appointment date
    And  I select "Tire Inspection" as the reason for the appointment
    Then I see a listing of available appointment times for either the Morning, Afternoon, and/or Evening (if available)
    When I select the first available appointment time
    And  I select "Schedule" after entering customer data for feature "<Feature>" into the customer information fields
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     |
      | Appointment |

  @ovcBBA
  @11826
  Scenario Outline: Oneview_Schedule_Appointment_Store_POS_Appointment_Creation (ALM# 11826)
    When I select the "Appointment" icon from the Global Header
    Then I am brought to the page with header "Schedule Appointment"
    When I select the first available appointment date
    And  I select "Tire Inspection" as the reason for the appointment
    Then I see a listing of available appointment times for either the Morning, Afternoon, and/or Evening (if available)
    When I select the first available appointment time
    Then I verify the selected appointment time is highlighted red
    And  I select "Schedule" after entering customer data for feature "<Feature>" into the customer information fields
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     |
      | Appointment |

  @ovcBBA
  @12138
  Scenario Outline: Oneview_Store_Experience_Alert_Center_Add_Product_To_Appointment_Time (ALM# 12138)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Appointment" button on the "Transaction View" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I verify the dialog contains "<Appointment Confirmation>"
    When I select the "Ok" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Alerts" icon from the Global Header
    And  I select the refresh icon on the alerts page
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Go to transaction search" button from the alert page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return For Service" button on the "Order Search" page
    And  I select the "No Return" option on the Return page
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    Then I verify the page currently displayed is the "Home" page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     | AppointmentReason | Appointment Confirmation         |
      | Appointment | Tire Inspection   | Appointment created successfully |

  @ovcRegression
  @ovcBBA
  @10668
  @12139
  Scenario Outline: Oneview_Store_Experience_Alert_Center_Adding_Notes (ALM# 12139)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the page currently displayed is the "Home" page
    When I select the "Alerts" icon from the Global Header
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Notes" button from the alert page
    And  I enter a default note into the alert notes
    And  I select the "Save" button on the "Popup" page
    Then I verify the "Notes added successfully" message was displayed

    Examples:
      | Feature     | AppointmentReason |
      | Appointment | Tire Inspection   |

  @ovcBBA
  @12085
  Scenario Outline: Oneview_Store_Experience_Alert_Center_Cancel_Appointment_Time (ALM# 12085)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the page currently displayed is the "Home" page
    When I select the "Alerts" icon from the Global Header
    And  I select the refresh icon on the alerts page
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Cancel" button from the alert page
    Then I verify the "Appointment cancelled successfully" message was displayed

    Examples:
      | Feature     | AppointmentReason |
      | Appointment | Tire Inspection   |

  @ovcBBA
  @11832
  Scenario Outline: Oneview_Schedule_Appointment_Store_POS_Return_For_Service_Appointment_Creation (ALM# 11832)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
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
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I enter "ItemNumber3" for feature "<Feature>" in the article search box
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    Then I verify the page currently displayed is the "Appointment" page
    When I select the first available appointment date
    And  I select "Tire Inspection" as the reason for the appointment
    Then I see a listing of available appointment times for either the Morning, Afternoon, and/or Evening (if available)
    When I select the first available appointment time
    And  I enter the phone number for customer in feature "<Feature>"
    And  I select the "Schedule" button on the "Appointment" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     |
      | Appointment |

  @ovcBBA
  @12133
  Scenario Outline: Oneview_Scheduling_Workflow_CSL_Complete_CSL_invoice (ALM# 12133)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     |
      | Appointment |

  @tLogRegression
  @ovcRegression
  @ovcBBA
  @12084
  @15013
  Scenario Outline: Oneview_Store_Experience_ Alert_Center_Modify_Appointment (ALM# 12084)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Appointment" button on the "Transaction View" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I verify the dialog contains "<Appointment Confirmation>"
    When I select the "Ok" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Alerts" icon from the Global Header
    And  I select the refresh icon on the alerts page
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Modify" button from the alert page
    And  I select a different date and time for the appointment
    And  I select the "Save" button on the modify appointment popup
    Then I verify the "Appointment modified successfully" message was displayed
    And  I verify the appointment has been modified for customer in feature "<Feature>"

    Examples:
      | Feature     | AppointmentReason | Appointment Confirmation         |
      | Appointment | Tire Inspection   | Appointment created successfully |

  @ovcBBA
  @12083
  Scenario Outline: Oneview_Store_Experience_ Alert_Center_Check_In_Appointment_Time (ALM# 12083)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Appointment" button on the "Transaction View" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I verify the dialog contains "<Appointment Confirmation>"
    When I select the "Ok" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    And  I select the "Alerts" icon from the Global Header
    And  I select the refresh icon on the alerts page
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Go to transaction search" button from the alert page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Return For Service" button on the "Order Search" page
    And  I select the "No Return" option on the Return page
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I enter "ItemNumber3" for feature "<Feature>" in the article search box
    And  I enter a tire inspection price
    And  I select the "OK" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     | AppointmentReason | Appointment Confirmation         |
      | Appointment | Tire Inspection   | Appointment created successfully |

  @ovcBBA
  @11609
  Scenario Outline: Oneview_Inventory_Stock Management_Suspend-Add to CSL (ALM# 11609)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    And  I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Close" button on the "Lookup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature     |
      | Appointment |

  @tLogRegression
  @suspendAppointment
  Scenario Outline: Suspend_Transaction_with_appointment (ALM# NONE)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    And  I select the first available appointment date
    And  I select "Tire Inspection" as the reason for the appointment
    And  I select the first available appointment time
    Then I verify the selected appointment time is highlighted red
    When I select the "Schedule" button on the "Appointment" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature     |
      | Appointment |

  @tLogRegression
  @quoteAppointment
  Scenario Outline: Create_Quote_with_appointment (ALM# NONE)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I select the "Create Quote" link on the page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Appointment" button on the "Transaction View" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I verify the dialog contains "<Appointment Confirmation>"
    When I select the "Ok" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page

    Examples:
      | Feature     | AppointmentReason | Appointment Confirmation         |
      | Appointment | Tire Inspection   | Appointment created successfully |

  @ovcRegression
  @10670
  Scenario Outline: OVC_STORE EXPERIENCE_ALERT_CENTER_COMPLETE (ALM# 10670)
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    Then I verify the page currently displayed is the "Home" page
    When I fill out Visit The Vehicle page information for feature "<Feature>"
    And  I check out using the cash Payment Method
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Transaction Search" link on the page
    And  I search for transaction details with the previous receipt number
    And  I select the transaction with the previous receipt number
    And  I select the "Appointment" button on the "Transaction View" page
    And  I create an appointment for customer in feature "<Feature>" with reason "<AppointmentReason>"
    Then I verify the dialog contains "<Appointment Confirmation>"
    When I select the "Ok" button on the "Popup" page
    Then I verify the page currently displayed is the "Home" page
    When I select the "Alerts" icon from the Global Header
    And  I select the refresh icon on the alerts page
    And  I select the previously created appointment for customer in feature "<Feature>"
    And  I select the "Complete" button from the alert page
    Then I verify the "Appointment completed successfully" message was displayed

    Examples:
      | Feature     | AppointmentReason | Appointment Confirmation         |
      | Appointment | Tire Inspection   | Appointment created successfully |
