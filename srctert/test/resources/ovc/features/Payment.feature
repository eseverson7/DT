@ovc
@payment
Feature: Payment tests

  Background:
    When I go to the ovc homepage
    And  I login with Server, Username, and Password

  @ovcBBA
  @ovcRegression
  @11600
  @14409
  Scenario Outline: Oneview_Payments_Tenders_Cash_Transaction (ALM# 11600)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Vehicles List" button on the "Customer" page
    And  I select the "Add Vehicle" button on the "Vehicle View" page
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Save" button on the "Vehicle View" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "close" button on the "Lookup" page
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    And  I select the "Home" icon from the Global Header
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the list item "Cash"
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature  |
      | Customer |

  @tLogRegression
  @ovcBBA
  @ovcRegression
  @9930
  @9967
  Scenario Outline: OVC_PAYMENT_PAYMENTS AND TENDER_Special Paymets_Tlog (ALM# 9930, 9967)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "Home" icon from the Global Header
    And  I extract the "Balance" total from the home page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Home" icon from the Global Header
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Special Payment" link from the Checkout menu
    And  I select "Charitable Contributions" as the Special Payment option from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Popup" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature |
      | Payment |

  @ovcBBA
  @11607
  Scenario: Oneview_Payment_Cash_Management_Start_of_Day_Manual_Run_if_End_of_day_was_run (ALM# 11607)
  """TODO: Revisit this test after completing ALM# 12098"""
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    And  I verify the popup alert contains "Store is now open"
    When I select the "Complete" button on the "Popup" page
    And  I select "Back" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

  @tLogRegression
  @ovcBBA
  @12033
  Scenario Outline: Payment_Paymentsandtenders_Cash_Transaction_Make_Partial_Payment_Select_Appointment (ALM#12033)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicle" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Home" icon from the Global Header
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I change tender amount to "<Amount>"
    And  I select the "Next" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature | Mileage | Condition | Amount |
      | Payment | 75000   | Poor      | $50.00 |

  @ovcBBA
  @12032
  Scenario Outline: Payment_PaymentsandTenders_Cash_Transaction_Pay_full_amount_Select_appointment (ALM#12032)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Home" icon from the Global Header
    And  I extract the "Balance" total from the home page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    When I create an appointment for customer in feature "<Feature>" with reason "<ApptReason>"
    Then I save the order receipt after verifying that the order number is visible
    And  I select the "OK" button on the "Printer" page
    When I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature | Mileage | ApptReason      |
      | Payment | 150000  | Tire Inspection |

  @ovcBBA
  @12094
  Scenario: Payment_Cash_Management_Store_Spot_Check_Visual_Only (ALM#12094)
    When I select the "Cash Management" link on the page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Till Spot Check" link on the page
    Then I verify "default values" appears on the Till Spot Check popup
    When I select the "Done" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"

  @tLogRegression
  @ovcBBA
  @12049
  Scenario Outline: Oneview_Payment_Payment_and_Tenders_Special_Payments_Equal_To_Transaction_Amount (ALM# 12049)
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "WHEEL" from the Lookup page dropdown
    And  I enter "ItemNumber2" for feature "<Feature>" into "SKU" textbox and send Enter key
    And  I select "ItemNumber2" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber2" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Special Payment" link from the Checkout menu
    And  I select "Managers Tires/snow tires" as the Special Payment option from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel
    When I select the "OK" button on the "Printer" page
    And  I select "Close" option from the right pane nav bar
    Then I verify the right nav pane title is "Main"

    Examples:
      | Feature | Mileage | Condition |
      | Payment | 53000   | Great     |

  @ovcBBA
  @12035
  Scenario Outline: Payment_PaymentsandTenders_Gift_Certificate_Apply_certificate_Return_change_to_customer (ALM# 12035)
  """TODO: Popup no longer appears at end of test - leaving steps in place in case functionality changes back
    then I verify the dialog contains "Please return $PriceDifference as change"
    when I select the "OK" button on the "Popup" page
    and  I select the "Add to CSL" button on the "Popup" page
    and  I enter a target time for CSL over an hour in the future
    and  I select the "Complete" button on the "Popup" page
    and  I select the "OK" button on the "Popup" page
    then I verify "VRItem1" for feature "Feature" appears on the Virtual Receipt"""
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I extract the subtotal from the home page
    And  I extract the subtotal of item "ItemNumber" for feature "<Feature>"
    And  I modify the price of "ItemNumber" for feature "<Feature>" on the Virtual Receipt to "<NewPrice>"
    And  I select the "Employee Discount" option on the Price Override popup
    Then I verify the new subtotal reflects the change from "<NewPrice>"
    When I extract the "Balance" total from the home page
    And  I select the "Gift Certificate" link from the Checkout menu
    Then I verify the dialog contains "Gift Certificate"
    When I enter the Gift Certificate number for feature "<Feature>"
    Then I verify the dialog contains "Confirm amount: $<CertAmount>"
    When I select the "Next" button on the "Popup" page
    Then I verify that the balance has "<CertAmount>" less than the extracted balance

    Examples:
      | Feature | Mileage | Location | Condition | NewPrice | CertAmount | PriceDifference |
      | Payment | 50000   | Phoenix  | Good      | 45.00    | 50.00      | 5.00            |

  @tLogRegression
  @ovcBBA
  @12099
  Scenario Outline: Payment_Cash_Management_QuickDeposit (ALM#12099)
    When I select the "Cash Management" link on the page
    And  I select the "Start of Day" link on the page
    Then I verify the popup alert contains "Start of Day"
    When I select the "Complete" button on the "Popup" page
    And  I select the "Quick Deposit" link on the page
    Then I verify the popup alert contains "TRACER BAG NUMBER"
    When I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    Then I verify the popup alert contains "QUICK DEPOSIT TOTAL AMOUNT"
    When I input amount into the "CASH" Quick Deposit popup
    And  I select the "Next" button on the "Popup" page
    Then I verify "CASH" appears on the Quick Deposit receipt Details
    When I select the "Done" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"

    Examples:
      | BagNum1 | BagNum2 |
      | 10      | 1001    |

  @ovcBBA
  @12036
  Scenario Outline: Payment_PaymentsandTenders_Gift_Certificate_Apply_certificate_to_an_invoice_of_exact_same_amount (ALM# 12036)
  """TODO: Popup no longer appears at end of test - leaving steps in place in case functionality changes back
    then I verify the dialog contains "Please return $PriceDifference as change"
    when I select the "OK" button on the "Popup" page
    and  I select the "Add to CSL" button on the "Popup" page
    and  I enter a target time for CSL over an hour in the future
    and  I select the "Complete" button on the "Popup" page
    and  I select the "OK" button on the "Popup" page
    then I verify "VRItem1" for feature "Feature" appears on the Virtual Receipt"""
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I extract the subtotal from the home page
    And  I extract the subtotal of item "ItemNumber" for feature "<Feature>"
    And  I modify the price of "ItemNumber" for feature "<Feature>" on the Virtual Receipt to "<NewPrice>"
    And  I select the "Damaged Product" option on the Price Override popup
    Then I verify the new subtotal reflects the change from "<NewPrice>"
    When I extract the "Balance" total from the home page
    And  I select the "Gift Certificate" link from the Checkout menu
    Then I verify the dialog contains "Gift Certificate"
    When I enter the Gift Certificate number for feature "<Feature>"
    Then I verify the dialog contains "Confirm amount: $<CertAmount>"

    Examples:
      | Feature | Mileage | Location | Condition | NewPrice | CertAmount | ApptReason                |
      | Payment | 50000   | Phoenix  | Good      | 50.00    | 50.00      | Tire Rotation and Balance |

  @ovcBBA
  @12856
  Scenario Outline: Payment_Cash_Management_Perform_Multiple_Quick_Deposits_In_Same_Business_Day (ALM# 12856)
  """Added Pay In steps to Add Amount to Additional Tender."""
    When I select the "Cash Management" link on the page
    And  I select the "Quick Deposit" link on the page
    Then I verify the popup alert contains "TRACER BAG NUMBER"
    When I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    Then I verify the popup alert contains "QUICK DEPOSIT TOTAL AMOUNT"
    When I input amount into the "CASH" Quick Deposit popup
    And  I select the "Next" button on the "Popup" page
    Then I verify "CASH" appears on the Quick Deposit receipt Details
    When I select the "Done" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"
    When I select the "Pay In" link on the page
    Then I verify the dialog contains "Pay In"
    When I select the "Employee Reimbursement" radio button on the "Cash Management" page
    And  I select the "OK" button on the "Popup" page
    Then I verify the popup alert contains "Pay In - Employee Reimbursement"
    When I enter "FirstName" into the "First Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "LastName" into the "Last Name" "Select Tender" input field for feature "<Feature>"
    And  I enter "PayrollId" into the "Payroll ID" "Select Tender" input field for feature "<Feature>"
    And  I enter tender amount of "10.00" for the Cash Management Pay In
    And  I enter "<Reimbursement>" into the "Reimbursement" "Select Tender" input field
    And  I enter "<Comments>" into the comments field
    And  I select the "Next" button on the "Popup" page
    And  I select the "Yes" button on the "Popup" page
    And  I select the "Money Order" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "Ok" button on the "Popup" page
    Then I verify the popup alert contains "<Amount>"
    When I select the "Done" button on the "Popup" page
    Then I verify that the popup with a "Done" button is closed
    When I select the "Cash Management" link on the page
    And  I select the "Quick Deposit" link on the page
    Then I verify the popup alert contains "TRACER BAG NUMBER"
    When I enter "<BagNum1>" into the input field of the popup
    Then I verify the popup alert contains "Min: 3 Characters"
    And  I verify the popup alert contains "Max: 10 Characters"
    When I enter "<BagNum2>" into the input field of the popup
    And  I select the "Proceed" button on the "Popup" page
    Then I verify the popup alert contains "QUICK DEPOSIT TOTAL AMOUNT"
    When I input amount into the "MONEY ORDER" Quick Deposit popup
    And  I select the "Next" button on the "Popup" page
    Then I verify "MONEY ORDER" appears on the Quick Deposit receipt Details
    When I select the "Done" button on the "Popup" page
    Then I verify the right nav pane title is "Cash Management"

    Examples:
      | BagNum1 | BagNum2 | Feature        | Reimbursement      | Comments                           | Amount |
      | 10      | 1001    | CashManagement | Auto Reimbursement | Money Order for further validation | 10.00  |

  @ovcBBA
  @12912
  Scenario Outline: Oneview_Orders_Sales_Certificates_one_tire_four_certicates (ALM# 12912)
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I adjust the item quantity of "ItemNumber" for feature "<Feature>" to "<QuantityAdjust>"
    Then I verify the quantity of "ItemNumber" for feature "<Feature>" was updated to "<QuantityAdjust>"
    And  I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicles" button on the "Home" page
    And  I verify the "existing" vehicle for feature "<Feature>" appears in the customer vehicle list
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select "ACCESSORY" from the Lookup page dropdown
    And  I enter "ItemNumber3" for feature "<Feature>" into "SKU" textbox and send Enter key
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | QuantityAdjust |
      | Payment | 4              |

  @ovcBBA
  @12890
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Tender_Check_Personal_Check_Add_to_CSL (ALM# 12890)
  """TODO: Fails due to Defect_ID 8850 (AB, 02/07/2018)"""
    When I perform a "CustomerVehicle" and "tire" lookup using customer, vehicle and item number for feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I wait for the OVC preloader to be no longer visible
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Personal Check" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    And  I fill out the check payment details for feature "<Feature>"
    And  I select the "OK" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    And  I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition |
      | Payment | 50000   | Phoenix  | Good      |

  @ovcBBA
  @12868
  Scenario Outline: Oneview_Payment_Payments_and_Tenders_Gift_Certificate_Apply_certificate_balance_Use_money_order_to_pay_remainder (ALM# 12868)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Gift Certificate" link on the page
    And  I enter the Gift Certificate number for feature "<Feature>"
    Then I verify the dialog contains "Confirm amount: $<CertAmount>"
    When I select the "Next" button on the "Popup" page
    And  I select the "Money Order" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "Ok" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition | CertAmount |
      | Payment | 50000   | Phoenix  | Good      | 50         |

  @ovcBBA
  @12882
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Money_Orders_and_Travelers_Checks_Money_Order_Split_payment_Service_LaterComplete (ALM# 12882)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Money Order" link on the page
    And  I change tender amount to "50.00"
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "OK" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipt"

    Examples:
      | Feature | Mileage | Location | Condition |
      | Payment | 50000   | Phoenix  | Good      |

  @ovcBBA
  @12883
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Money_Orders_and_Travelers_Checks_Travelers_Checks_full_balance_Set_appointment (ALM# 12883)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Travelers Check" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "OK" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "Tire Inspection"
    And  I extract the "Balance" total from the home page
    Then I verify the "Balance" total matches "0.00"
    And  I verify the right nav pane title is "Receipt"

    Examples:
      | Feature | Mileage | Location | Condition |
      | Payment | 50000   | Phoenix  | Good      |

  @ovcBBA
  @12884
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Money_Orders_and_Travelers_Checks_Travelers_Check_Split_payment_Service_LaterComplete (ALM# 12884)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Home" icon from the Global Header
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Checkout" button on the "Home" page
    And  I select the "Travelers Check" link on the page
    And  I enter a partial total of "<Amount>" for the order
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "Ok" button on the "Popup" page
    Then I verify that the popup with a "Ok" button is closed
    When I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition | Amount  |
      | Payment | 50000   | Phoenix  | Good      | $100.00 |

  @ovcBBA
  @12885
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Money_Orders_and_Travelers_Checks_Money_Order_Provide_change_Set_appointment (ALM# 12885)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    And  I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Home" icon from the Global Header
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Travelers Check" link on the page
    And  I enter a partial total of "<Amount>" for the order
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "Ok" button on the "Popup" page
    And  I select the "Ok" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "Tire Inspection"
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition | Amount  |
      | Payment | 50000   | Phoenix  | Good      | $300.00 |

  @ovcBBA
  @12881
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Money_Orders_and_Travelers_Checks_Money_Order_full_balance_Set_appointment (ALM# 12881)
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Checkout" button on the "Home" page
    And  I select the "Money Order" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I enter the data about the tender in money order popup for feature "<Feature>"
    And  I select the "Ok" button on the "Popup" page
    And  I select the "Appointment" button on the "Popup" page
    And  I create an appointment for customer in feature "<Feature>" with reason "Tire Inspection"
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition |
      | Payment | 50000   | Phoenix  | Good      |

  @ovcBBA
  @12891
  Scenario Outline: Oneview_Payment_Payment_and_Tender_Tender_Check_Business_Check_Add_to_CSL (ALM# 12891)
  """TODO: Fails due to Defect_ID 8850 (AB, 02/07/2018)"""
    When I perform a vehicle and customer lookup for customer and vehicle in feature "<Feature>"
    And  I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I enter vehicle "mileage": "<Mileage>" into VTV Vehicle section
    And  I enter vehicle "location": "<Location>" into VTV Vehicle section
    And  I enter vehicle "condition": "<Condition>" into VTV Vehicle section
    And  I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I select "ItemNumber3" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify "ItemNumber" for feature "<Feature>" appears on the Virtual Receipt
    When I select the "Checkout" button on the "Home" page
    And  I select the "Business Check" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Ok" button on the "Popup" page
    And  I fill out the business check payment details for feature "<Feature>"
    And  I select the "OK" button on the "Popup" page
    And  I select the "OK" button on the "Popup" page
    Then I verify that the popup with a "OK" button is closed
    When I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I select the "Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Mileage | Location | Condition |
      | Payment | 50000   | Phoenix  | Good      |

  @ovcRegression
  @tLogRegression
  @ovcBBA
  @12104
  Scenario Outline: Oneview_Payment_Payments_and_ Tenders_Accounts_Receivable_ (AR)_Tender_identify_AR_account_Select_product_make_appointment (ALM# 12104)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    Then I verify "Tire" dropdown fields are displayed on Lookup Page
    When I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Customer" icon from the Global Header
    And  I enter the Company Name for the AR customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "ArCustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the AR Customer vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Accounts Receivable" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I am brought to the page with header "Account Receivable"
    When I enter "ArFirstName" for "<Feature>" into the "Driver's First Name *" input on the AR Payment page
    And  I enter "ArLastName" for "<Feature>" into the "Driver's Last Name *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "Purchase Order *" input on the AR Payment page
    And  I select the "Save" button on the "AR Payment" page
    Then I verify the right nav pane title is "Checkout"
    When I select the "Appointment" button on the "Popup" page
    And  I create an appointment for the AR customer in feature "<Feature>" with reason "Tire Balancing"
    And  I select the "OK" button on the "Printer" page
    Then I verify the right nav pane title is "Receipts"
    And  I verify the appointment date and time appear on the order receipt
    And  I save the order receipt after verifying that the order number is visible
    And  I save the order number to the "TLog" excel

    Examples:
      | Feature |
      | Payment |

  @ovcBBA
  @12106
  Scenario Outline:  Oneview_Payment_Payments _and_Tenders_Accounts_Receivable_(AR)Tender_Select_product_Identify_customer_Take_partial_payment_Service_laterComplete (ALM# 12106)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    Then I verify "Tire" dropdown fields are displayed on Lookup Page
    When I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "ItemNumber" of the item for feature "<Feature>" appears on the home page
    When I select the "Customer" icon from the Global Header
    And  I enter "ArCompanyName" for feature "<Feature>" into the "Company Name"
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "ArCustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the AR Customer vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Accounts Receivable" link on the page
    And  I change tender amount to "<Amount>"
    And  I select the "Next" button on the "Popup" page
    Then I am brought to the page with header "Account Receivable"
    When I enter "ArFirstName" for "<Feature>" into the "Driver's First Name *" input on the AR Payment page
    And  I enter "ArLastName" for "<Feature>" into the "Driver's Last Name *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "Purchase Order *" input on the AR Payment page
    And  I select the "Save" button on the "AR Payment" page
    Then I verify the right nav pane title is "Checkout"
    When I select the "Cash" link on the page
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | Amount |
      | Payment | 10000  |

  @12105
  Scenario Outline: Oneview_Payment_Payments_and_ Tenders_Accounts_Receivable_(AR)_Tender_identify_AR_account_Use_Cash_instead_of_AR_charge_Service_LaterComplete (ALM# 12105)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Company Name for the AR customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "ArCustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the AR Customer vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Accounts Receivable" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I am brought to the page with header "Account Receivable"
    When I enter "ArFirstName" for "<Feature>" into the "Driver's First Name *" input on the AR Payment page
    And  I enter "ArLastName" for "<Feature>" into the "Driver's Last Name *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "Purchase Order *" input on the AR Payment page
    And  I select the "Save" button on the "AR Payment" page
    Then I verify the right nav pane title is "Checkout"
    When I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"

    Examples:
      | Feature |
      | Payment |

  @12103
  Scenario Outline: Oneview_Payment_Payments _and _Tenders_Accounts_Receivable_(AR)_ Tender_Select_product_Identify_AR_customer_Add_to_CSL (ALM# 12103)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Company Name for the AR customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "ArCustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the AR Customer vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Accounts Receivable" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I am brought to the page with header "Account Receivable"
    When I enter "ArFirstName" for "<Feature>" into the "Driver's First Name *" input on the AR Payment page
    And  I enter "ArLastName" for "<Feature>" into the "Driver's Last Name *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "Purchase Order *" input on the AR Payment page
    And  I select the "Save" button on the "AR Payment" page
    Then I verify the right nav pane title is "Checkout"
    When I select the "Add to CSL" button on the "Popup" page
    And  I enter a target time for CSL over an hour in the future
    And  I toggle the CSL Options popup Send Text Message switch
    And  I select the mobile number on the CSL options popup
    And  I select the "Complete" button on the "Popup" page
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the right nav pane title is "Receipts"

    Examples:
      | Feature |
      | Payment |

  @12863
  Scenario Outline: Oneview_Payment_Payments_and_Tenders_Accounts_Receivable_AR_Tender_Identify_AR_customer_Set_amount_equal_to_maximum_allowable_transaction_amount_Service_LaterComplete (ALM# 12863)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Customer" icon from the Global Header
    Then I verify the page currently displayed is the "Customer" page
    When I enter the Company Name for the AR customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "ArCustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the AR Customer vehicle description for feature "<Feature>" appears on the home page
    When I select the "VTV" icon from the Global Header
    Then I verify the page currently displayed is the "VTV" page
    When I select the replace tire icon
    And  I select the "Save" button on the "VTV" page
    Then I verify the VTV icon color is "Green"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Accounts Receivable" link on the page
    And  I select the "Back" button on the "Popup" page
    And  I modify the price of "ItemNumber" for feature "<Feature>" on the Virtual Receipt to "5000.00"
    And  I select the "Employee Discount" option on the Price Override popup
    And  I select the "Accounts Receivable" link on the page
    And  I select the "Next" button on the "Popup" page
    Then I am brought to the page with header "Account Receivable"
    And  I verify that I can enter 300 characters in the account requirements field
    When I enter "ArFirstName" for "<Feature>" into the "Driver's First Name *" input on the AR Payment page
    And  I enter "ArLastName" for "<Feature>" into the "Driver's Last Name *" input on the AR Payment page
    And  I enter "ArPurchaseOrder" for "<Feature>" into the "Purchase Order *" input on the AR Payment page
    And  I select the "Save" button on the "AR Payment" page
    Then I verify the right nav pane title is "Checkout"
    When I select the "Service Later/Complete" button on the "Popup" page
    Then I verify the right nav pane title is "Receipts"

    Examples:
      | Feature |
      | Payment |

  @12107
  Scenario Outline: Oneview_Payment_Payments_and_Tenders_Accounts_Receivable_(AR)_Tender_Identify_AR_customer_Select_product_Suspend_transaction (ALM# 12107)
    When I select the "Lookup" icon from the Global Header
    And  I select "TIRE" from the Lookup page dropdown
    And  I select "ItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    And  I select the "Customer" icon from the Global Header
    And  I enter the Company Name for the AR customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the AR Company name for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "ArCustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the AR Customer vehicle description for feature "<Feature>" appears on the home page
    When I select the "Suspend Transaction" link on the page
    And  I select the "OK" button on the "Popup" page
    Then I verify the right nav pane title is "Main"
    When I select the "Service Later/Complete" button on the "Popup" page
    And  I wait for the OVC preloader to be no longer visible
    Then I verify the right nav pane title is "Receipts"
    And  I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature |
      | Payment |