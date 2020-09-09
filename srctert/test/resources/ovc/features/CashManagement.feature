@ovc
@cashManagement
Feature: Cash Management tests

  Background:
    When I go to the ovc homepage
    And  I login with Server, Username, and Password

  @ovcBBA
  @11604
  Scenario: Payment_Cash_Management_Store Spot_Check_Visual_Only (ALM# 11604)
    When I select the "Cash Management" link on the page
    And  I select the "Till Spot Check" link on the page
    Then I verify "default values" appears on the Till Spot Check popup
    When I select the "Done" button on the "Popup" page
    And  I select "Back" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

  @ovcBBA
  @12093
  Scenario: Oneview_Payment_Cash_Management_Store Spot_Check_Print_Report (ALM# 12093)
  """TODO: Remove ok on popup page step once printer is properly configured"""
    When I select the "Cash Management" link on the page
    And  I select the "Till Spot Check" link on the page
    Then I verify "default values" appears on the Till Spot Check popup
    When I select the "Print" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    And  I select "Back" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

  @ovcRegression
  @tLogRegression
  @ovcBBA
  @11801
  Scenario Outline: Oneview_Orders_Voucher_Pay in_employee (ALM# 11801)
    When I select the "Cash Management" link on the page
    And  I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Employee Reimbursement" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay In - Employee Reimbursement"
    When I enter "FirstName" into the "First Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "PayrollId" into the "Payroll ID" "Select Tender" input field for feature "<Feature>"
    And  I enter "<Reimbursement>" into the "Reimbursement" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select "Cash" Tender and enter amount "<Amount>" with Serial Number ""
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Feature        | Reimbursement      | Comments      | Amount |
      | CashManagement | Auto Reimbursement | Auto Comments | 50.00  |

  @ovcBBA
  @11805
  Scenario Outline: Oneview_Orders_Voucher_Pay in_other (ALM# 11805)
  """TODO currently fails as the personal check authorization steps fails - will need to add validation later"""
    When I select the "Cash Management" link on the page
    And  I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Other" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the popup alert contains "Pay In - Other"
    When I select the "Next" button on the "Popup" page
    And  I select reason "<Reason>"
    And  I enter "<Comments>" into the comments field
    And  I select "Personal Check" Tender and enter amount "<Amount>" with Serial Number "<SerialNumber>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Personal Check" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    And  I fill out the check payment details for feature "<Feature>"
    And  I select the "OK" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page

    Examples:
      | Feature        | Reason          | Comments      | Amount | SerialNumber |
      | CashManagement | Building Damage | Auto Comments | 50.00  | 12345        |

  @ovcBBA
  @12102
  Scenario Outline: Oneview_Payment_Cash_Management_Count_with_a_discrepancy (ALM# 12102)
    When I select the "Cash Management" link on the page
    And  I select the "Nightly Deposit" link on the page
    And  I enter "<Amount>" into the input field of the popup
    And  I select the "Next" button on the "Popup" page
    And  I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    Then I verify the input value field of the popup contains "<FinalNum>"
    When I select the "Proceed" button on the "Popup" page
    And  I enter "<Dollars1>" into the "<DollarAmt1>" Manual Denomination Count text field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    And  I verify the popup alert contains "END OF DAY DISCREPANCY"
    And  I verify the popup alert contains "$1550.00"
    When I select the "Recount" button on the "Popup" page
    And  I enter "<Dollars2>" into the "<DollarAmt2>" Manual Denomination Count text field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    And  I verify the popup alert contains "END OF DAY DISCREPANCY"
    And  I verify the popup alert contains "$2050.00"
    When I select the "Complete" button on the "Popup" page
    And  I select the "Print" button on the "Popup" page
    Then I verify I am back on the login page

    Examples:
      | Amount | BagNum1 | BagNum2     | FinalNum   | Dollars1 | DollarAmt1 | Dollars2 | DollarAmt2 |
      | 5000   | 12      | 12345678901 | 1234567890 | 15       | 100.00     | 10       | 50.00      |

  @tLogRegression
  @ovcBBA
  @11803
  Scenario Outline: Oneview_Orders_Voucher_Pay in_sales_of (ALM# 11803)
    When I select the "Cash Management" link on the page
    And  I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Sales of Equipment" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the popup alert contains "Pay In - Sales of Equipment"
    When I select the "Next" button on the "Popup" page
    And  I select equipment type "<EquipmentType>"
    And  I enter "<SerialNumber>" into the "Asset/Serial" "Select Tender" input field
    And  I select approximate year aquired "<ApproxYear>"
    And  I select "Money Order" Tender and enter amount "<Amount>" with Serial Number "<SerialNumber>"
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Money Order" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "Sales of Equipment"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Feature        | Company  | EquipmentType | SerialNumber | ApproxYear | Comments      | Amount |
      | CashManagement | Acme Inc | Tire Changer  | 12345        | 2009       | Auto Comments | 50.00  |

  @ovcRegression
  @tLogRegression
  @ovcBBA
  @11812
  Scenario Outline: Oneview_Orders_Vouchers_pay_out_customer_repair (ALM# 11812)
    When I select the "Cash Management" link on the page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Customer Repair" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the popup alert contains "Pay Out - Customer Repair"
    And  I verify "First Name, Last Name, Address" values for customer in feature "<Feature>" are pre-populated in the dialog
    And  I verify City, State and ZIP Code values for "non AR" customer in feature "<Feature>" are pre-populated in the dialog
    When I select the "Next" button on the "Popup" page
    And  I enter "<Company>" into the "Company" "Select Tender" input field
    And  I enter "<DamagedItems>" into the "Damaged Items" "Select Tender" input field
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "Customer Repair"
    And  I verify the popup alert contains "<Company>"
    And  I verify the popup alert contains "<DamagedItems>"
    And  I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Feature        | Company       | DamagedItems       | Amount | Comments      |
      | CashManagement | Discount Tire | Auto Damaged Items | 50.00  | Auto Comments |

  @tLogRegression
  @ovcBBA
  @11804
  Scenario Outline: Oneview_Orders_Voucher_Pay in_ROA (ALM# 11804)
    When I select the "Cash Management" link on the page
    And  I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "ROA" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter the Company Name for the AR customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the popup alert contains "Pay In - ROA"
    And  I verify "Ar Address, AR Account #, Ar Phone" values for customer in feature "<Feature>" are pre-populated in the dialog
    And  I verify City, State and ZIP Code values for "AR" customer in feature "<Feature>" are pre-populated in the dialog
    And  I enter "ArFirstName" into the "First Name" "Form Group" input field for feature "<Feature>"
    And  I enter "ArLastName" into the "Last Name" "Form Group" input field for feature "<Feature>"
    And  I select the "Next" button on the "Popup" page
    And  I enter invoice "<Invoice>" and click the add button
    And  I enter "<Amount>" into the "Amount" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals expected value of: "<Amount>"
    When I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Invoice>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Feature        | Invoice | Amount | Comments      |
      | CashManagement | 54321   | 50.00  | Auto Comments |

  @ovcBBA
  @11806
  Scenario Outline: Oneview_Orders_Vouchers_pay_out_courier (ALM# 11806)
    When I select the "Cash Management" link on the page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Freight/Courier" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Freight/Courier"
    When I enter "<CompanyName>" into the "Freight/Courier Company Name" "Select Tender" input field
    And  I enter "<From>" into the "From" "Select Tender" input field
    And  I enter "<To>" into the "To" "Select Tender" input field
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "<CompanyName>"
    And  I verify the popup alert contains "<To>"
    And  I verify the popup alert contains "<From>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | CompanyName | To      | From      | Amount | Comments      |
      | AutoCompany | Auto To | Auto From | 50.00  | Auto Comments |

  @ovcBBA
  @11802
  Scenario Outline: Oneview_Orders_Voucher_Pay in_NSF (ALM# 11802)
  """test prerequisites Required to obtain a Transaction ID"""
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I am brought to the page with header "Main"
    And  I extract the subtotal from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    And  I select the "Cash Management" link on the page
    And  I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Insufficient Funds" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify the popup alert contains "Pay In - Insufficient Funds"
    And  I verify "First Name, Last Name" values for customer in feature "<Feature>" are pre-populated in the dialog
    And  I verify the NSF letter options are displayed
    When I select the "NO" NSF letter option
    And  I select the "Next" button on the "Popup" page
    Then I am brought to a Transaction Search page
    When I search for transaction details with the previous receipt number
    And  I select the "Search" button on the "Transaction View" page
    And  I select the transaction with the previous receipt number
    Then I verify the popup alert contains "Pay In - Insufficient Funds"
    And  I verify the "Check Date" "Select Tender" input field is pre-populated with "date" in the dialog
    And  I verify the Store Location input field is pre-populated with store from feature "<Feature>" in the dialog
    And  I verify the "Check Amount" "Select Tender" input field is pre-populated with the extracted subtotal
    And  I enter "<Fee>" into the "Fee" "Select Tender" input field
    When I select "Cash" Tender and enter amount "subtotal+<Fee>" with Serial Number ""
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains extracted subtotal plus "<Fee>"
    And  I verify the popup alert contains store location for feature "<Feature>"
    And  I verify the popup alert contains "<Comments>"
    And  I verify the right nav pane title is "Main"

    Examples:
      | Feature        | Fee   | Comments      |
      | CashManagement | 10.00 | Auto Comments |

  @ovcBBA
  @12283
  Scenario Outline: OVC_Orders_Vouchers_pay_out_gas (ALM# 12283)
    When I select the "Cash Management" link on the page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Gasoline/Mileage" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Gasoline/Mileage"
    When I enter "FirstName" into the "First Name" "Form Group" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Form Group" input field for feature "<Feature>"
    And  I enter "<PayrollID>" into the "Payroll ID" "Form Group" input field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Gasoline/Mileage"
    When I enter "Add Trip" with "<From City>" and "<To City>" and click the add button
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Comments>"
    And  I verify the popup alert contains "<From City>"
    And  I verify the popup alert contains "<To City>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Feature        | PayrollID | From City   | To City   | Comments      | Amount |
      | CashManagement | 12345     | Minneapolis | Flagstaff | Auto Comments | 50.00  |

  @ovcBBA
  @12908
  Scenario Outline: Oneview_Orders_Vouchers_Pay_Out_Meals (ALM# 12908)
    When I select the "Cash Management" link on the page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Meals" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Meals"
    When I enter "<Company>" into the "Company" "Select Tender" input field
    And  I enter "<Items Purchased>" into the "Items Purchased" "Select Tender" input field
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Comments>"
    And  I verify the popup alert contains "<Company>"
    And  I verify the popup alert contains "<Items Purchased>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Company       | Items Purchased    | Amount | Comments         |
      | Discount Tire | Honda Civic Wheels | 50.00  | Pay Out Comments |

  @ovcBBA
  @12857
  Scenario Outline: Payment_CashManagement_No_Count_Discrepancy (ALM# 12857)
    When I select the "Cash Management" link on the page
    And  I select the "Nightly Deposit" link on the page
    Then I verify the popup alert contains "Nightly Deposit"
    When I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "TRACER BAG NUMBER"
    When I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    And  I enter "<Dollars1>" into the "<DollarAmt1>" Manual Denomination Count text field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    And  I verify the popup alert contains "END OF DAY DISCREPANCY"
    When I select the "Complete" button on the "Popup" page
    Then I verify "Float left in Till" appears on the Nightly Deposit receipt Details
    When I select the "Done" button on the "Popup" page

    Examples:
      | BagNum1 | BagNum2 | Dollars1 | DollarAmt1 |
      | 10      | 1001    | 1        | 1          |

  @ovcBBA
  @12287
  Scenario Outline: OVC_Orders_Vouchers_pay_out_office (ALM# 12287)
    When I select the "Cash Management" link on the page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Office Supplies" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter "<Company>" into the "Company" "Select Tender" input field
    And  I enter "Add Items" with "<SKU>" and "<Description>" and click the add button
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "Office Supplies"
    And  I verify the popup alert contains "<Company>"
    And  I verify the popup alert contains "<SKU>"
    And  I verify the popup alert contains "<Description>"
    And  I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Company  | SKU   | Description      | Amount | Comments      |
      | Acme Inc | 12345 | Auto_Description | 50.00  | Auto Comments |

  @ovcBBA
  @12855
  Scenario Outline:Payment_CashManagement_Start_Of_Day_Manual_Run_If_End_Of_Day_Was_Not_Run (ALM# 12855)
  """TODO: Pre-Conditions - End of Day not run/did not complete. Unable to Meet Condition at this time
    TODO: Popup with "Start of Day before performing sale or return transactions not implemented yet."""
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Store is now open"
    When I select the "Complete" button on the "Popup" page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day has already been processed"
    When I select the "Complete" button on the "Popup" page
    And  I select the "End of Day" link on the page
    Then I verify the dialog window contains "End of Day cannot be run until all tills are closed"
    When I select the "OK" button on the "Popup" page
    And  I select the "Nightly Deposit" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    When I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    And  I enter "<Dollars1>" into the "<DollarAmt1>" Manual Denomination Count text field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    And  I verify the popup alert contains "END OF DAY DISCREPANCY"
    When I select the "Complete" button on the "Popup" page
    Then I verify "Float left in Till" appears on the Nightly Deposit receipt Details
    When I select the "Done" button on the "popup" page
    And  I go to the ovc homepage
    And  I enter Server, Username and Password and hit Login on the second login page
    Then I verify the right nav pane title is "Main"
    When I select the "Cash Management" link on the page
    And  I select the "End of Day" link on the page
    Then I verify the popup alert contains "Store is now closed"
    When I select the "Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Store is now open"
    When I select the "Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"

    Examples:
      | BagNum1 | BagNum2 | Dollars1 | DollarAmt1 |
      | 5       | 1001    | 1        | 100.00     |

  @ovcBBA
  @12853
  Scenario Outline: Payment_CashManagement_End_Of_Day_Manual_Run_Accurate_Count (ALM# 12853)
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select the "End of Day" link on the page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Nightly Deposit" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    When I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    And  I enter "<Dollars1>" into the "<DollarAmt1>" Manual Denomination Count text field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    And  I verify the popup alert contains "END OF DAY DISCREPANCY"
    When I select the "Complete" button on the "Popup" page
    Then I verify "Float left in Till" appears on the Nightly Deposit receipt Details
    When I select the "Done" button on the "Popup" page
    And  I go to the ovc homepage
    And  I enter Server, Username and Password and hit Login on the second login page
    Then I verify the right nav pane title is "Main"
    When I select the "Cash Management" link on the page
    And  I select the "End of Day" link on the page
    Then I verify the popup alert contains "Store is now closed"
    When I select the "Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page

    Examples:
      | BagNum1 | BagNum2 | Dollars1 | DollarAmt1 |
      | 5       | 1001    | 1        | 100.00     |

  @ovcBBA
  @12904
  Scenario Outline: Oneview_Orders_Vouchers_pay_out_Freightcourier (ALM# 12904)
    When I select the "Cash Management" link on the page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Freight/Courier" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Freight/Courier"
    When I enter "<Company>" into the "Freight/Courier Company Name" "Select Tender" input field
    And  I enter "<FromState>" into the "From" "Select Tender" input field
    And  I enter "<ToState>" into the "To" "Select Tender" input field
    And  I enter "<Cash>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    Then I verify the dialog contains "$<Cash>"
    When I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Freight/Courier"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Company       | Comments         | Cash  | FromState | ToState   |
      | Discount Tire | Pay out Comments | 50.00 | Arizona   | Minnesota |

  @ovcBBA
  @12905
  Scenario Outline: Orders_Vouchers_Pay_Out_Gas (ALM# 12905)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Gasoline/Mileage" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Gasoline/Mileage"
    When I enter "<FirstName>" into the "First Name" "Form Group" input field
    And  I enter "<LastName>" into the "Last Name" "Form Group" input field
    And  I enter "<PayrollID>" into the "Payroll ID" "Form Group" input field
    And  I select the "Next" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Gasoline/Mileage"
    When I enter "Add Trip" with "<From>" and "<To>" and click the add button
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "popup" page
    And  I select the "Yes" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Gasoline/Mileage"
    And  I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | FirstName | LastName      | PayrollID | From  | To  | Amount | Comments |
      | OVC       | AUTO-LASTNAME | 12345     | Start | End | 10.00  | COMMENT  |

  @ovcBBA
  @12907
  Scenario Outline: Orders_Vouchers_pay_out_repairs (ALM# 12907)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    When I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Repairs/Maintenance" radio button on the "Cash Management" page
    And  I select the "OK" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Repairs/Maintenance"
    When I enter "<Company>" into the "Company" "Select Tender" input field
    And  I select reason "<Reason>"
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "popup" page
    And  I select the "Yes" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Repairs/Maintenance"
    And  I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Company>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Company  | Reason       | Amount | Comments |
      | Acme Inc | Pest Control | 10.00  | COMMENTS |

  @ovcBBA
  @12909
  Scenario Outline: Orders_Vouchers_Pay_Out_Misc (ALM# 12909)
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Miscellaneous" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay Out - Miscellaneous"
    When I enter "<Company>" into the "Company" "Select Tender" input field
    And  I enter "SKU" with "<SKU>" and "<Description>" and click the add button
    And  I enter "<Cash>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    Then I verify the dialog contains "$<Cash>"
    When I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "<SKU>"
    And  I verify the popup alert contains "<Cash>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Company  | SKU   | Description | Cash  | Comments |
      | Acme Inc | 12345 | Tire        | 10.00 | COMMENTS |

  @ovcBBA
  @12910
  Scenario Outline: Orders_Vouchers_pay_out_tools (ALM# 12910)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Tools/Supplies" radio button on the "Cash Management" page
    And  I select the "OK" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Tools/Supplies"
    When I enter "<Company>" into the "Company" "Select Tender" input field
    And  I enter "SKU" with "<SKU>" and "<Description>" and click the add button
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "popup" page
    And  I select the "Yes" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Tools/Supplies"
    And  I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Company>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Company  | SKU   | Description | Amount | Comments |
      | Acme Inc | 12345 | Tire        | 10.00  | COMMENTS |

  @ovcBBA
  @12911
  Scenario Outline: Orders_Vouchers_Pay_Out_Office (ALM# 12911)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    And  I select the "Complete" button on the "Popup" page
    When I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Office Supplies" radio button on the "Cash Management" page
    And  I select the "OK" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Office Supplies"
    When I enter "<Company>" into the "Company" "Select Tender" input field
    And  I enter "SKU" with "<SKU>" and "<Description>" and click the add button
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "popup" page
    And  I select the "Yes" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Office Supplies"
    And  I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Company>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Company  | SKU   | Description | Amount | Comments |
      | Acme Inc | 12345 | Tire        | 10.00  | COMMENTS |

  @ovcBBA
  @12284
  Scenario Outline: OVC_Orders_Vouchers_pay_out_inventoried (ALM# 12284)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Pay Out" link on the page
    Then I verify the dialog contains "Pay Out"
    When I select the "Inventory Items" radio button on the "Cash Management" page
    And  I select the "OK" button on the "popup" page
    And  I enter "Vendor" for feature "<Feature>" into the "Vendor Name"
    And  I select the "Search" button on the "Vendor" page
    And  I select the "Vendor" for feature "<Feature>" from the table on the "Vendor" page
    And  I select the "Select" button on the "Vendor" page
    Then I verify the popup alert contains "Pay Out - Inventory Items"
    And  I verify "Company" values for vendor in feature "<Feature>" are pre-populated in the dialog
    When I enter "<FirstName>" into the "First Name" "Select Tender" input field
    And  I enter "<LastName>" into the "Last Name" "Select Tender" input field
    And  I enter "<Contact>" into the "Contact" "Select Tender" input field
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I click the add button
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "Wheel" name and sku for feature "<Feature>" on the Lookup Page
    And  I select the value from feature "<Feature>" for the "rimDiameter" dropdown on the lookup page
    And  I select "WheelSku" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Next" button on the "popup" page
    And  I select the "Yes" button on the "popup" page
    Then I verify the popup alert contains "Pay Out - Inventory Items"
    And  I verify the popup alert contains "Vendor" for feature "<Feature>"
    And  I verify the popup alert contains "WheelName" for feature "<Feature>"
    And  I verify the popup alert contains "WheelSku" for feature "<Feature>"
    And  I verify the popup alert contains "<Amount>"
    And  I verify the popup alert contains "<Comments>"
    And  I verify the popup alert contains "<FirstName>"
    And  I verify the popup alert contains "<LastName>"
    And  I verify the popup alert contains "<Contact>"
    When I select the "Done" button on the "popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature        | FirstName | LastName   | Contact      | Amount | Comments      |
      | CashManagement | OVC       | Automation | Auto Contact | 10.00  | Auto Comments |

  @tLogRegression
  @ovcBBA
  @12903
  Scenario Outline: Oneview_Orders_Voucher_Pay in_INF (ALM# 12903)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Insufficient Funds" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Popup" page
    Then I verify the popup alert contains "Pay In - Insufficient Funds"
    When I select the "Yes" button on the "Popup" page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Pay In - Insufficient Funds"
    When I enter "Today's Date" into the Check Date input field
    And  I enter "StoreLocation" into the "Store Location" "Select Tender" input field for feature "<Feature>"
    And  I enter "<Amount>" into the "Check Amount" "Select Tender" input field
    And  I enter "<Fee>" into the "Fee" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I enter "<Amount>" into the "Amount" "Select Tender" input field
    Then I verify the "Check Date" "Select Tender" input field is pre-populated with "date" in the dialog
    When I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Cash" link on the page
    Then I verify the dialog contains "<Amount>"
    And  I verify the dialog contains "Pay In amount"
    When I select the "Next" button on the "Popup" page
    And  I verify the popup alert contains "Pay In - Insufficient Funds"
    And  I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Feature        | Fee | Amount | Comments |
      | CashManagement | 54  | 10.00  | COMMENTS |

  @ovcBBA
  @12854
  Scenario Outline: Payment_CashManagement_End_Of_Day_Manual_Run_With_Count_Discrepancy (ALM# 12854)
  """TODO: Pre-Conditions - End of Day not run/did not complete. Unable to Meet Condition at the time"""
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    And  I select the "Complete" button on the "Popup" page
    And  I select the "End of Day" link on the page
    Then I verify the dialog contains "Warning" with loader wait
    When I select the "OK" button on the "Popup" page
    And  I select the "Nightly Deposit" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "TRACER BAG NUMBER"
    When I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    And  I enter "<Dollars1>" into the "<DollarRow1>" Manual Denomination Count text field
    And  I enter "<Dollars2>" into the "<DollarRow2>" Manual Denomination Count text field
    And  I enter "<Dollars3>" into the "<DollarRow3>" Manual Denomination Count text field
    And  I enter "<Dollars4>" into the "<DollarRow4>" Manual Denomination Count text field
    And  I enter "<Dollars5>" into the "<DollarRow5>" Manual Denomination Count text field
    And  I enter "<Dollars12>" into the "<DollarRow12>" Manual Denomination Count text field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    And  I verify the popup alert contains "END OF DAY DISCREPANCY"
    When I select the "recount" button on the "Popup" page
    Then I verify the denomination dialog contains "MANUAL DENOMINATION COUNT"
    When I enter "<Dollars6>" into the "<DollarRow6>" Manual Denomination Count text field
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    And  I verify the popup alert contains "END OF DAY DISCREPANCY"
    When I select the "complete" button on the "Popup" page
    Then I verify "Float left in Till" appears on the Nightly Deposit receipt Details
    When I select the "Done" button on the "Popup" page
    And  I go to the ovc homepage
    And  I enter Server, Username and Password and hit Login on the second login page
    And  I select the "Cash Management" link on the page
    And  I select the "End of Day" link on the page
    Then I verify the popup alert contains "Store is now closed"
    When I select the "Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Store is now open"
    When I select the "Complete" button on the "Popup" page

    Examples:
      | BagNum1 | BagNum2 | Dollars1 | DollarRow1 | Dollars2 | DollarRow2 | Dollars3 | DollarRow3 | Dollars4 | DollarRow4 | Dollars5 | DollarRow5 | Dollars12 | DollarRow12 | Dollars6 | DollarRow6 |
      | 5       | 1001    | 1        | 100.00     | 1        | 50.00      | 1        | 20.00      | 1        | 10.00      | 1        | 5.00       | 1         | 0.01        | 1        | 2.00       |

  @ovcBBA
  @12097
  Scenario Outline: Oneview_Payment_Cash_Management_Start_of_Day_Manual_Run_if_End_of_day_was_run (ALM# 12097)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Nightly Deposit" link on the page
    Then I verify the popup alert contains "FLOAT AMOUNT"
    When I select the "Next" button on the "Popup" page
    And  I enter "<Tracer>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "Nightly Deposit"
    When I select the "Complete" button on the "Popup" page
    And  I select the "Done" button on the "Popup" page
    And  I go to the ovc homepage
    And  I enter Server, Username and Password and hit Login on the second login page
    Then I verify the right nav pane title is "Main"
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    And  I verify the popup alert contains "Store is now open"
    When I select the "Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Main"

    Examples:
      | Tracer   |
      | 11111111 |

  @tLogRegression
  @payInReversal
  Scenario Outline: Oneview_Orders_Voucher_Pay in_employee (ALM# NONE)
    When I select the "Cash Management" link on the page
    And  I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Employee Reimbursement" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay In - Employee Reimbursement"
    When I enter "FirstName" into the "First Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "PayrollId" into the "Payroll ID" "Select Tender" input field for feature "<Feature>"
    And  I enter "<Reimbursement>" into the "Reimbursement" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select "Cash" Tender and enter amount "<Amount>" with Serial Number ""
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    And  I save the barcode number from pay in popup page
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed
    When I select the "Cash Management" link on the page
    And  I select the "Reverse PV" link on the page
    And  I search for the previously saved voucher# in the "Voucher #" input field
    And  I select the first available voucher date
    And  I select the "OK" button on the "Popup" page
    And  I select the "Freight/Courier" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter "<CompanyName>" into the "Freight/Courier Company Name" "Select Tender" input field
    And  I enter "<From>" into the "From" "Select Tender" input field
    And  I enter "<To>" into the "To" "Select Tender" input field
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "<CompanyName>"
    And  I verify the popup alert contains "<To>"
    And  I verify the popup alert contains "<From>"
    And  I verify the popup alert contains "<Comments>"
    When I select the "Done" button on the "Popup" page

    Examples:
      | Feature        | Reimbursement      | Comments      | Amount | CompanyName | To      | From      |
      | CashManagement | Auto Reimbursement | Auto Comments | 50.00  | AutoCompany | Auto To | Auto From |
      
      
  @ovcRegression
  @13376
  Scenario Outline: Oneview_Orders_Voucher_Pay in_employee (ALM# 13376)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    And  I verify the dialog contains "Employee Reimbursement"
    And  I verify the dialog contains "Insufficient Funds"
    And  I verify the dialog contains "Sales of Equipment"
    And  I verify the dialog contains "ROA"
    And  I verify the dialog contains "Other"
    When I select the "Employee Reimbursement" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay In - Employee Reimbursement"
    When I enter "FirstName" into the "First Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "PayrollId" into the "Payroll ID" "Select Tender" input field for feature "<Feature>"
    And  I enter "<Reimbursement>" into the "Reimbursement" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select "Cash" Tender and enter amount "<Amount>" with Serial Number ""
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    And  I save the barcode number from pay in popup page
    When I select the "Done" button on the "Popup" page
    Then I verify the right nav pane title is "Main"
    
    Examples:
      | Feature        | Reimbursement      | Comments      | Amount |
      | CashManagement | Auto Reimbursement | Auto Comments | 50.00  |
      
  @ovcRegression
  @14130
  Scenario Outline: Oneview_Orders_Voucher_Pay in_employee (ALM# 14130)
    When I select the "Cash Management" link on the page
    And  I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Employee Reimbursement" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay In - Employee Reimbursement"
    When I enter "FirstName" into the "First Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "PayrollId" into the "Payroll ID" "Select Tender" input field for feature "<Feature>"
    And  I enter "<Reimbursement>" into the "Reimbursement" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select "Cash" Tender and enter amount "<Amount>" with Serial Number ""
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    And  I save the barcode number from pay in popup page
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed
    When I select the "Cash Management" link on the page
    And  I select the "Reverse RV" link on the page
    And  I search for the previously saved voucher# in the "Voucher #" input field
    And  I select the first available voucher date
    And  I select the "OK" button on the "Popup" page
    And  I select the "Employee Reimbursement" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    And  I enter "FirstName" into the "First Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "PayrollId" into the "Payroll ID" "Select Tender" input field for feature "<Feature>"
    And  I enter "<Reimbursement>" into the "Reimbursement" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I enter "<Amount>" into the "Cash" "Select Tender" input field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed

    Examples:
      | Feature        | Reimbursement      | Comments      | Amount |
      | CashManagement | Auto Reimbursement | Auto Comments | 50.00  |