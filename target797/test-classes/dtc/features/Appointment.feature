@appointment
Feature: Appointments

  Background:
    Given I change to the default store

  @at
  @dt
  @web
  @8657
  @mobile
  @smoketest
  @appointmentSmoke
  @sendStoreLocationToPhone
  Scenario Outline: HYBRIS_APPOINTMENTS_APPOINTMENTS SmokeTest DT or AT Schedule Service 3  (ALM #8657)
    When I search for store within "25" miles of "<ZipCode>"
    And  I close the Welcome Popup
    And  I select "Send to phone" for store #"1" in the store location results
    And  I enter phone number: "<PhoneNumber>" in the Send to Phone dialog
    Then I confirm the Send to Phone results popup
    When I close the popup
    And  I schedule an appointment for my current store
    And  I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select first available appointment date
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    Then I should see an appointment confirmation message for "<Customer>" with service options: "<ServiceOptions>"
    And  I store the order number

    Examples:
      | ZipCode | PhoneNumber  | ServiceOptions                | Customer            |
      | 32003   | 555-555-5555 | New Tires/Wheels Consultation | DEFAULT_CUSTOMER_AZ |

  @at
  @dt
  @7001
  @serviceUpAptwithProductsShoppingCart
  @mobile
  @web
  Scenario Outline: ServiceUp Appointment with Products Shopping Cart (ALM #7001)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I add item "<ItemCode>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    And  I see a purchase quantity of "<Quantity>"
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I create an appointment with defaults from Checkout
    Then I extract date and time for validation
    When I click next step for customer information
    Then I verify date and time on the customer details appointment page
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | FitmentOption | Header       | ProductName  | ItemCode | Quantity | Checkout         | Customer            |
      | 2015 | Honda | Accord | Coupe EX | none     | All tires     | tire results | Defender A/S | 34384    | 4        | with appointment | default_customer_az |

  @at
  @dt
  @9987
  @appointmentRange
  @mobile
  @web
  Scenario Outline: HYBRIS_APPOINTMENTS_User_Set_Appointment_Range_8AM_And_545PM_DT_TC01 (ALM # 9987)
    When I schedule an appointment for my current store
    And  I select service option(s): "<ServiceOptions>"
    And  I select default date
    Then I verify the appointment time range is between "8:30 AM" and "5:30 PM"

    Examples:
      | ServiceOptions                |
      | New Tires/Wheels Consultation |
      | Tire Rotation and Balance     |
      | Tire Balancing                |
      | Tire Inspection               |
      | Flat Repair                   |
      | TPMS Service                  |
      | Winter Tire Change            |

  @dt
  @at
  @bba
  @9384
  @scheduleAppt35DaysInAdvance
  @web
  @mobile
  @appointmentBBA
  Scenario Outline: WebServiceAppointment_35BusinessDaysInAdvance (ALM #9384)
    When I open the "SERVICES" navigation link
    And  I click on the "Schedule an appointment" Link
    And  I select service option(s): "<ServiceOption>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select the "<available>" appointment to verify total available appointment days are "35"
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    Then I should see an appointment confirmation message for "<Customer>" with service options: "<ServiceOption>"
    And  I should see my previously selected store, date and time, in the appointment details section
    And  I store the order number

    Examples:
      | ServiceOption             | Customer            | available |
      | Tire Rotation and Balance | DEFAULT_CUSTOMER_AZ | Last      |

  @dt
  @at
  @bba
  @8153
  @scheduleAppt10DaysInAdvance
  @web
  @appointmentBBA
  Scenario Outline: ScheduleAppointment_10BusinessDaysInAdvance (ALM #8153)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    And  I verify an appointment for a checkout with item(s) can be created for 10 business days later
    When I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | ProductName  | ItemCode | Checkout         | Customer            |
      | Defender A/S | 34302    | with appointment | default_customer_az |

  @dt
  @at
  @8153
  @bba
  @scheduleAppt10DaysInAdvance
  @mobile
  @appointmentBBA
  Scenario Outline: Mobile - ScheduleAppointment_10BusinessDaysInAdvance (ALM #8153)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    And  I verify an appointment for a checkout with item(s) can be created for 10 business days later
    When I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | ProductName  | ItemCode | Checkout         | Customer            |
      | Defender A/S | 34302    | with appointment | default_customer_az |

  @at
  @dt
  @8656
  @smoketest
  @reserveWithoutAppointmentViaVehicleSearch
  @mobile
  @web
  @appointmentSmoke
  Scenario Outline: Reserve product without Appointment using Vehicle Search via My Vehicles (ALM #8656)
  """TODO: ItemCode 25991 is not available on STG.  Added additional Product for test on STG.
    TODO: Re-visit this scenario for mobile
    | 2015 | Honda | Accord | Coupe EX | none     | All Wheels    | KONIG       | 16120    | 4        | without appointment | default_customer_az |"""
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    And  I see for item "<ItemCode>" a purchase quantity of "<Quantity>"
    When I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    When I select the checkout without install reason "<Reason>"
    And  I click next step for customer information
    And  I select "ROPIS" option
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | Year | Make   | Model | Trim       | Assembly                            | FitmentOption | ProductName | ItemCode | Quantity | Checkout            | Customer            | Reason                              |
      | 2010 | Nissan | 370Z  | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL | All tire sets | Potenza     | 25192    | 2        | without appointment | default_customer_az | Make an appointment at a later time |
      | 2010 | Nissan | 370Z  | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL | All tire sets | Potenza     | 36245    | 2        | without appointment | default_customer_az | Make an appointment at a later time |

  @dt
  @at
  @bba
  @8528
  @reserveWithAppointmentWithoutChangingStoreViaHomepageVehicleSearch
  @mobile
  @web
  @appointmentBBA
  Scenario Outline: Reserve Product with Appointment via Search by Vehicle using the Homepage (ALM #8528)
  """| 2015 | Honda | Accord | Coupe EX | none     | All Wheels    | Results for Wheels | 56178    | with appointment | default_customer_az |"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I add item "<ItemCode>" of type "sets" to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I create an appointment with defaults from Checkout
    And  I reserve items and complete checkout for "<Customer>"
    Then I store the order number

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | FitmentOption        | Header        | ItemCode | ProductName | Checkout         | Customer            |
      | 2015 | Honda | Accord | Coupe EX | none     | All wheels this size | wheel results | 20744    | Cross       | with appointment | default_customer_az |

  @dt
  @at
  @bba
  @8531
  @reserveWithoutAppointmentWithoutChangingStore
  @web
  @appointmentBBA
  Scenario Outline: Reserve Product without Appointment (ALM #8531)
  """| PBX A/T HARDCORE | 34302    | without appointment | default_customer_az |"""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    When I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | ProductName  | ItemCode | Checkout | Customer            |
      | Defender A/S | 34302    | default  | default_customer_az |
      | Illusion     | 61661    | default  | default_customer_az |

  @dt
  @at
  @8531
  @bba
  @reserveWithoutAppointmentWithoutChangingStore
  @mobile
  @appointmentBBA
  Scenario Outline: Mobile - Reserve Product without Appointment (ALM #8531)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I reserve items for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | ProductName  | ItemCode | Checkout            | Customer            |
      | Defender A/S | 34302    | without appointment | default_customer_az |

  @at
  @dt
  @8655
  @8724
  @smoketest
  @reserveWithAppointmentViaFreeTextSearch
  @web
  @appointmentSmoke
  Scenario Outline: Reserve Product with Appointment (ALM #8655,8724)
  """| PBX A/T HARDCORE | 34302    | with appointment | default_customer_az |
    TODO: Staging data for 8724. dtqa1 does not appear to contain this product/itemCode; Item needs to be rebate eligible
    | PROVIDER ENTRADA HT | 12168    | with appointment | default_customer_az |"""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I create an appointment with defaults from Checkout
    And  I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:      
      | ProductName  | ItemCode | Checkout         | Customer            |
      | Defender A/S | 34302    | with appointment | default_customer_az |

  @dt
  @at
  @8655
  @8724
  @smoketest
  @reserveWithAppointmentViaFreeTextSearch
  @mobile
  @appointmentSmoke
  Scenario Outline: Mobile - Reserve Product with Appointment (ALM #8655,8724)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I create an appointment with defaults from Checkout
    And  I reserve items for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:      
      | ProductName  | ItemCode | Checkout         | Customer            |
      | Defender A/S | 34302    | with appointment | default_customer_az |

  @dt
  @at
  @bba
  @25450
  @checkoutWithoutApptWithSpecialOrderArticle
  @web
  @appointmentBBA
  Scenario Outline: Checkout without appointment with special order article (ALM #25450)
  """|85250   |AZP 20 |34299    |PBX A/T HARDCORE  |without appointment | default_customer_az |"""
    When I go to the homepage
    And  I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    Then I verify checkout option "with appointment" is not available
    When I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    And  I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:      
      | ZipCode | Store  | ProductName  | ItemCode | Checkout            | Customer            | Reason                              |
      | 48911   | MIL 15 | Defender A/S | 34302    | without appointment | default_customer_az | Make an appointment at a later time |

  @dt
  @at
  @25450
  @bba
  @checkoutWithoutApptWithSpecialOrderArticle
  @mobile
  @appointmentBBA
  Scenario Outline: Mobile - Checkout without appointment with special order article (ALM #25450)
    When I go to the homepage
    And  I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    Then I verify checkout option "with appointment" is not available
    When I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    And  I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:      
      | ZipCode | Store  | ProductName  | ItemCode | Checkout            | Reason                              |
      | 48911   | MIL 15 | Defender A/S | 34302    | without appointment | Make an appointment at a later time |

  @dt
  @web
  @8530
  @scheduleApptCheckout10DaysInAdvanceDT
  Scenario Outline: HYBRIS_209_APPOINTMENT_Checkout_Schedule Appointment Not Changing Store_10 Business days (ALM #8530)
  """TODO - AT / DT PROD, STG, QA1 will fail when run with Internet Explorer; see defect 7018"""
    When I search for store within "25" miles of "Scottsdale"
    And  I select make this "AZP 29" my store
    And  I do a free text search for "<SearchTerm>" and hit enter
    And  I select "Shop for All-Season Tires" from the Product Brand page
    And  I select the "In Stock" checkbox
    And  I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I select from the "Brands" filter section, "single" option(s): "<BrandOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    And  I verify an appointment for a checkout with item(s) can be created for 10 business days later
    When I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | SearchTerm | BrandOption | ItemCode | ProductName        | Checkout         | Customer            |
      | tires      | ARIZONIAN   | 29942    | Silver Edition III | with appointment | default_customer_az |

  @at
  @web
  @8530
  @scheduleApptCheckout10DaysInAdvanceAT
  Scenario Outline: HYBRIS_209_APPOINTMENT_Checkout_Schedule Appointment Not Changing Store_10 Business days (ALM #8530)
  """TODO - AT / DT PROD, STG, QA1 will fail when run with Internet Explorer; see defect 7018"""
    When I do a free text search for "<SearchTerm>" and hit enter
    And  I select "Shop for All-Season Tires" from the Product Brand page
    And  I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I select from the "Brands" filter section, "single" option(s): "<BrandOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    And  I verify an appointment for a checkout with item(s) can be created for 10 business days later
    When I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | SearchTerm | BrandOption | ItemCode | ProductName | Checkout         | Customer            |
      | tires      | VEENTO TIRE | 17901    | G-3         | with appointment | default_customer_az |

  @at
  @dt
  @8616
  @scheduleServiceFromServiceHeaderLink
  @web
  Scenario Outline: Schedule Appointment for Tire Rotation/Rebalance (ALM #8616)
    When I open the "SERVICES" navigation link
    And  I click the "Schedule an appointment" menu option
    And  I select service option(s): "<ServiceOption>"
    And  I select default date and time
    Then I verify default store on the customer details appointment page
    When I click continue for appointment customer details page
    Then I verify date and time on the customer details appointment page
    When I select "Schedule Appointment" after entering customer information for "<Customer>"
    Then I should see an appointment confirmation message for "<Customer>" with service options: "<ServiceOption>"
    And  I should see my previously selected store, date and time, in the appointment details section
    And  I store the order number

    Examples:
      | ServiceOption             | Customer            |
      | Tire Rotation and Balance | DEFAULT_CUSTOMER_AZ |

  @dt
  @at
  @web
  @12315
  @12196
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Cart Checkout_Validating redirection to Appointment Details (ALM #12315, #12196)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    And  I verify "install with appointment" option is enabled on the Checkout page
    And  I should see install without appointment tooltip
    And  I verify install without appointment tooltip message
    When I select install with appointment
    Then I verify list of available appointment dates
    And  I verify unavailable time slots are disabled
    And  I verify time slot hours increase from top to bottom
    And  I verify time slot list is scrollable
    And  I verify message bar with appointment day date and time
    When I close the Appointment Selected message bar
    Then I verify the Appointment Selected message is not displayed
    When I select first available appointment date
    And  I extract date and time for validation
    And  I click next step for customer information
    Then I verify date and time on the customer details appointment page
    When I click Edit Appointment link
    And  I select last available appointment date
    And  I extract date and time for validation
    And  I click next step for customer information
    Then I verify date and time on the customer details appointment page
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | ProductName  | ItemCode | Checkout | Customer            |
      | Defender A/S | 34302    | default  | DEFAULT_CUSTOMER_AZ |

  @dt
  @at
  @web
  @12197
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Cart Checkout_Validating redirection to Appointment Details (ALM #12197)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add "in stock" item of type "none" to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    And  I verify "install with appointment" option is enabled on the Checkout page
    And  I should see install without appointment tooltip
    And  I verify install without appointment tooltip message
    When I select install with appointment
    Then I verify list of available appointment dates
    And  I verify unavailable time slots are disabled
    And  I verify time slot hours increase from top to bottom
    And  I verify time slot list is scrollable
    And  I verify message bar with appointment day date and time
    When I close the Appointment Selected message bar
    Then I verify the Appointment Selected message is not displayed
    When I select first available appointment date
    And  I extract date and time for validation
    And  I click next step for customer information
    Then I verify date and time on the customer details appointment page
    When I click Edit Appointment link
    And  I select last available appointment date
    And  I extract date and time for validation
    And  I click next step for customer information
    Then I verify date and time on the customer details appointment page
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Checkout | Customer            |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires this size | default  | DEFAULT_CUSTOMER_AZ |

  @dt
  @at
  @web
  @12210
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Cart Checkout_Validating available hours for appointment on the basis of stock level_58 (ALM #12210)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add "on order" item of type "none" to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    And  I select install with appointment
    Then I verify appointment dates and appointment times sections are displayed
    And  I verify list of available appointment dates
    And  I verify appointment dates start on expected date "on order"
    When I click the discount tire logo
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add "in stock" item of type "none" to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    And  I select install with appointment
    Then I verify list of available appointment dates
    And  I verify appointment dates start on expected date "on order"
    When I click the discount tire logo
    And  I select mini cart and "View Cart"
    And  I remove the "on order" items from the cart
    And  I select the checkout option "<Checkout>"
    And  I select install with appointment
    Then I verify list of available appointment dates
    And  I verify appointment dates start on expected date "in stock"

    Examples:

      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Checkout |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | default  |

  @dt
  @at
  @web
  @12192
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Validating appointmentsection on the checkout_52 (ALM #12192)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add "in stock" item of type "none" to my cart and "View shopping cart"
    And  I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    And  I verify "install with appointment" option is enabled on the Checkout page
    When I select install with appointment
    Then I verify appointment dates and appointment times sections are displayed
    And  I verify list of available appointment dates
    When I select first available appointment date
    Then I verify appointment timeslots are displayed
    And  I verify appointment dates start on expected date "in stock"
    And  I verify the correct month and year are displayed in the Appointment Date header
    And  I verify the dates falling in different months are divided by a tab displaying month and year
    And  I verify unavailable time slots are disabled and available time slots are enabled
    And  I verify the time list header date is displayed and correct
    And  I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Checkout |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | default  |

  @dt
  @at
  @web
  @9407
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_APPOINTMENTS_REGRESSION_Wording for the Appoinments on the Date Calendar page (ALM #9407)
    When I open the "SERVICES" navigation link
    And  I click the "SCHEDULE APPOINTMENT" menu option
    And  I select service option(s): "<ServiceOption>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I click Select "date"
    Then I verify the datepicker message is correct

    Examples:
      | ServiceOption                 |
      | New Tires/Wheels Consultation |
      | Tire Rotation and Balance     |
      | Tire Balancing                |
      | Tire Inspection               |
      | Flat Repair                   |
      | TPMS Service                  |
      | Winter Tire Change            |

  @dt
  @at
  @web
  @12216
  @12221
  @12223
  @12225
  @12229
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Verify the appointment details in checkout page (ALM #12216, #12221, #12223, #12225, #12229)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add the first item to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    And  I verify "install with appointment" option is enabled on the Checkout page
    And  I verify the "install with appointment" option is selected
    And  I should see install without appointment tooltip
    When I select install without appointment
    Then I verify "install with appointment" option is enabled on the Checkout page
    And  I verify the Continue to Customer Details button is "disabled"
    And  I verify default install without appointment reason selected
    And  I verify the reasons listed in the reason dropdown list
    When I select the checkout without install reason "Not sure of my availability"
    Then I verify the reserve appointment message displayed for "Not sure of my availability"
    When I select the checkout without install reason "Make an appointment at a later time"
    Then I verify the reserve appointment message displayed for "Make an appointment at a later time"
    When I select the checkout without install reason "These items are for multiple vehicles"
    Then I verify the reserve appointment message displayed for "These items are for multiple vehicles"
    When I click next step for customer information
    And  I select Make an Appointment
    Then I verify the "install with appointment" option is selected
    And  I verify "install without appointment" option is enabled on the Checkout page
    When I select install with appointment
    Then I verify appointment dates and appointment times sections are displayed
    And  I verify the "CONTINUE TO CUSTOMER DETAILS" button is "disabled"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Checkout |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires this size | none     |

  @dt
  @at
  @web
  @14243
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Verify the appointment details in checkout page (ALM #14243)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add the first item to my cart and "View shopping cart"
    And  I schedule an appointment for my current store
    And  I select checkout with appointment
    And  I select first available appointment date
    And  I extract date and time for validation
    And  I click next step for customer information
    Then I verify date and time on the customer details appointment page
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I am brought to the order confirmation page
    And  I should see my previously selected store, date and time, in the appointment details section
    When I store the order number

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption       | Customer            |
      | 2012 | Honda     | Civic    | Coupe DX | none     | All tires this size | DEFAULT_CUSTOMER_AZ |
      | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets       | DEFAULT_CUSTOMER_AZ |

  @dt
  @web
  @12212
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_APPOINTMENTS_APPOINTMENTS_Cart Checkout Validating when appointments are not available (ALM #12212)
    When I search for store within "25" miles of "<ZipCode>"
    Then I select make this "<Store>" my store
    When I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I select a fitment option "<FitmentOption>"
    When I add "in stock" item of type "none" to my cart and "View shopping cart"
    When I select the checkout option "<Checkout>"
    And  I verify WalkIns Welcome message displays
    And  I verify View Times did not display on any unavailable appointment dates
    And  I verify store is closed on Sunday

    Examples:
      | ZipCode | Store  | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Checkout |
      | 97223   | ORP 01 | 2012 | Honda | Civic | Coupe DX | none     | All tires this size | none     |

  @dt
  @at
  @web
  @12206
  @regression
  @appointmentRegression
  Scenario Outline: HYBRIS_APPOINTMENTS_APPOINTMENTS_Cart Checkout Validating peak hours when checkout with appointment is selected (ALM #12206)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add "in stock" item of type "none" to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    And  I verify "install with appointment" option is enabled on the Checkout page
    And  I verify list of available appointment dates
    When I select "Saturday" appointment date
    Then I verify peak times message and peak hours for "Saturday"
    When I select "Monday" appointment date
    Then I verify peak times message and peak hours for "Monday"
    When I select "Tuesday" appointment date
    Then I verify peak times message and peak hours for "Tuesday"
    When I select "Wednesday" appointment date
    Then I verify peak times message and peak hours for "Wednesday"
    When I select "Thursday" appointment date
    Then I verify peak times message and peak hours for "Thursday"
    When I select "Friday" appointment date
    Then I verify peak times message and peak hours for "Friday"
    When I select first available appointment time within peak hours for selected date
    Then I verify service time will be longer than usual
    When I click next step for customer information
    Then I verify peak hours appointment is indicated in appointment details

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption       | Checkout |
      | 2012 | Honda     | Civic    | Coupe DX | none     | All tires this size | none     |
      | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets       | none     |

  @at
  @dt
  @15458
  @web
  Scenario Outline: HYBRIS_APPOINTMENTS_APPOINTMENTS_ScheduleAppointmentWithServicesMenuOption (ALM # 15458)
    When I open the "SERVICES" navigation link
    And  I click the "SCHEDULE APPOINTMENT" menu option
    Then I verify default store on the customer details appointment page
    When I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    Then I verify selected service option(s): "<ServiceOptions>" is displayed on Service Appointment page
    And  I verify "APPOINTMENT DETAILS" section is displayed and active
    When I select first available appointment date
    And  I extract date and time for validation
    Then I verify message bar with appointment day date and time
    When I click continue for appointment customer details page
    Then I verify date and time on the customer details appointment page
    When I select "Schedule Appointment" after entering customer information for "DEFAULT_CUSTOMER_AZ"
    Then I should see an appointment confirmation message for "DEFAULT_CUSTOMER_AZ" with service options: "<ServiceOptions>"
    And  I should see my previously selected store, date and time, in the appointment details section

    Examples:
      | ServiceOptions                |
      | New Tires/Wheels Consultation |
      | Tire Rotation and Balance     |
      | Tire Balancing                |
      | Tire Inspection               |
      | Flat Repair                   |
      | TPMS Service                  |
      | Winter Tire Change            |

  @at
  @dt
  @15544
  @web
  Scenario Outline: HYBRIS_APPOINTMENTS_APPOINTMENTS_ScheduleAppointmentWithServicesfromFooter (ALM # 15544)
    When I select "footer" "Services"
    Then I am brought to the page with header "Wheel and Tire Services"
    When I click on the "SCHEDULE AN APPOINTMENT" Link
    Then I verify default store on the customer details appointment page
    When I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    Then I verify selected service option(s): "<ServiceOptions>" is displayed on Service Appointment page
    And  I verify "APPOINTMENT DETAILS" section is displayed and active
    When I select first available appointment date
    And  I extract date and time for validation
    Then I verify message bar with appointment day date and time
    When I click continue for appointment customer details page
    Then I verify date and time on the customer details appointment page
    When I select "Schedule Appointment" after entering customer information for "DEFAULT_CUSTOMER_AZ"
    Then I should see an appointment confirmation message for "DEFAULT_CUSTOMER_AZ" with service options: "<ServiceOptions>"
    And  I should see my previously selected store, date and time, in the appointment details section

    Examples:
      | ServiceOptions                |
      | New Tires/Wheels Consultation |
      | Tire Rotation and Balance     |
      | Tire Balancing                |
      | Tire Inspection               |
      | Flat Repair                   |
      | TPMS Service                  |
      | Winter Tire Change            |

  @at
  @dt
  @15543
  @web
  Scenario Outline: HYBRIS_APPOINTMENTS_APPOINTMENTS_ScheduleAppointmentWithServicesfromMyStore (ALM # 15543)
    When I click on "My Store" title
    And  I click on the "SCHEDULE APPOINTMENT" Link
    Then I verify default store on the customer details appointment page
    When I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    Then I verify selected service option(s): "<ServiceOptions>" is displayed on Service Appointment page
    And  I verify "APPOINTMENT DETAILS" section is displayed and active
    When I select first available appointment date
    And  I extract date and time for validation
    Then I verify message bar with appointment day date and time
    When I click continue for appointment customer details page
    Then I verify date and time on the customer details appointment page
    When I select "Schedule Appointment" after entering customer information for "DEFAULT_CUSTOMER_AZ"
    Then I should see an appointment confirmation message for "DEFAULT_CUSTOMER_AZ" with service options: "<ServiceOptions>"
    And  I should see my previously selected store, date and time, in the appointment details section

    Examples:
      | ServiceOptions                |
      | New Tires/Wheels Consultation |
      | Tire Rotation and Balance     |
      | Tire Balancing                |
      | Tire Inspection               |
      | Flat Repair                   |
      | TPMS Service                  |
      | Winter Tire Change            |

  @at
  @dt
  @web
  @15490
  Scenario Outline: HYBRIS_APPOINTMENTS_Validating dynamic peak time and 1st available appointment time for Holiday Hours (ALM #15490)
  """Test will only work in the QA environments which has Full and Partial Holidays set for stores """
    When I do a free text search for "<Item Code>"
    Then I should see product list page with "<Product Name>"
    When I select "<Product Name>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<Product Name>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<Product Name>" is in the cart
    When I select the checkout option "with appointment"
    Then I verify "install with appointment" option is enabled on the Checkout page
    And  I verify an appointment for a checkout with item(s) can be created for 10 business days later
    And  I verify time slot list is scrollable
    When I scroll time slot list to reach "<Holiday Type>" Holiday
    Then I verify graph showing store schedule is "<Display Expectation>"
    And  I verify the 'First Available Appointment Time' message is "<Display Expectation>"

    Examples:
      | Item Code | Product Name | Holiday Type | Display Expectation |
      | 34302     | Defender A/S | Fullday      | not displayed       |
      | 34302     | Defender A/S | Partial      | displayed           |