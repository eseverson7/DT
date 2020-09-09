@core
Feature: Core Scenarios

  Background:
    Given I go to the homepage

  @dt
  @web
  @coreScenario1
  @coreScenario1_TestFlow1
  Scenario Outline: Verify Search through PDP Free Text
    When I change to the default store
    And  I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"

    Examples:
      | ItemCode | ProductName  |
      | 34302    | Defender A/S |

  @dt
  @web
  @coreScenario1
  @coreScenario1_TestFlow2
  Scenario Outline: Verify the Fitment Match and Search through Shop By Vehicle/Size/Brand for Tires
    When I change to the default store
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "All tires this size"
    Then I verify the PLP header message contains "tire results"
    And  I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    When I open the My Vehicles popup
    And  I select Add Vehicle
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model1>" "<Trim1>" "<Assembly>"
    And  I select a fitment option "<SizeOption>"
    Then I verify the selected fitment box option has a value of "<SizeOption>"
    When I select a fitment option "<TireSize>"
    Then I verify the PLP header message contains "tire results"
    When I click the discount tire logo
    Then I am brought to the homepage
    When I navigate to Shop by Size
    And  I select "205" from the "Tire Width" dropdown
    And  I select "55" from the "Aspect Ratio" dropdown
    And  I select "16" from the "Tire Diameter" dropdown
    And  I click on the "Find Tires" button
    Then I verify the PLP header message contains "Shop 205/55R16 Tires"
    And  I verify the "PLP" results banner message contains "205/55-16"
    When I click the discount tire logo
    Then I am brought to the homepage
    When I navigate to Shop by Brand
    And  I select "<Brand>" and find products
    Then I see the "<Brand>" that I selected
    When I select "Shop for All-Season Tires" to shop
    Then I can see "<Brand>" Brand PLP page
    When I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year2>" "<Make2>" "<Model2>" "<Trim2>" "<Assembly>"
    And  I select a fitment option "All tire sets"
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year2>" "<Make2>" and "<Model2>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Model1 | Trim1    | Year2 | Make2  | Model2 | Trim2      | SizeOption | TireSize  | Brand          |
      | 2012 | Honda | Civic | Coupe EX | none     | Accord | EX Coupe | 2014  | Nissan | 370Z   | Coupe Base | 16         | 205/65-16 | MICHELIN TIRES |

  @dt
  @web
  @coreScenario1
  @coreScenario1_TestFlow3
  Scenario Outline: Verify the Fitment Match and Search through Shop By Vehicle/Size/Brand for Wheels
    When I change to the default store
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select a fitment option "All wheels this size"
    Then I verify the message on the "Wheels" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    When I open the My Vehicles popup
    And  I select Add Vehicle
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model1>" "<Trim1>" "<Assembly>"
    And  I select the "WHEELS" menu option
    And  I select a fitment option "<SizeOption>"
    Then I verify the PLP header message contains "wheel results"
    And  I verify the message on the "Wheels" "PLP" banner contains "" "<Year>" "<Make>" and "<Model1>"
    When I click the discount tire logo
    And  I navigate to Shop by Size
    And  I click the fitment "Wheels" radio button
    And  I do a "my vehicles" wheel size search with details "<Diameter>" "<WheelWidth>" "<BoltPattern>"
    Then I verify the PLP header message contains "wheel results"
    And  I verify the "PLP" results banner message contains "<WheelSize>"
    When I click the discount tire logo
    Then I am brought to the homepage
    When I navigate to Shop by Brand
    And  I click the fitment "Wheels" radio button
    And  I select "<Brand>" and find products
    And  I select "View All" from the Product Brand page
    Then I can see "<Brand>" Brand PLP page
    When I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year2>" "<Make2>" "<Model2>" "<Trim2>" "<Assembly>"
    And  I select the "WHEELS" menu option
    And  I select a fitment option "All front wheels"
    Then I verify the PLP header message contains "wheel results"
    When I navigate back to previous page
    Then I should see the fitment panel page with vehicle "<Year2> <Make2>"
    When I select the "WHEELS" menu option
    And  I select the "Rear wheels" staggered menu option
    And  I select a fitment option "All rear wheels"
    Then I verify the PLP header message contains "wheel results"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Model1 | Trim1    | Year2 | Make2  | Model2 | Trim2      | SizeOption | Diameter | WheelWidth | BoltPattern | WheelSize       | Brand       |
      | 2012 | Honda | Civic | Coupe EX | none     | Accord | EX Coupe | 2014  | Nissan | 370Z   | Coupe Base | 16         | 15       | 6.5        | 5-100.0 MM  | 15X6.0 5-0.0 mm | DRAG WHEELS |

  @at
  @dt
  @web
  @coreScenario2
  @coreScenario2_TestFlow1
  Scenario Outline: Verify prices, Fees and Taxes (ALM#)
    When I change to the default store
    And  I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    And  I extract the product price from "PDP" page
    When I add item to my cart and "View shopping cart"
    Then I verify the item price with PDP price and item total displayed on cart page
    When I select show fee details option
    Then I verify the installation fee amount on the shopping cart page
    And  I verify the environment fee amount on the shopping cart page
    And  I verify the tire disposal fee amount on the shopping cart page
    And  I verify the subtotal price on the cart page
    And  I verify the tax on the cart page
    And  I verify the Total price on the cart summary page
    When I select the optional "Certificates" fee for item
    Then I verify the Certificate fee amount on the shopping cart page
    And  I verify the subtotal price on the cart page
    And  I verify the tax on the cart page
    And  I extract the "sales tax" on the cart page
    And  I verify the Total price on the cart summary page
    And  I extract the "order total" on the cart page
    When I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    Then I verify the checkout order total matches with shopping cart order total
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    When I click next step for customer information
    Then I verify the checkout order total matches with shopping cart order total
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I verify the order total on order confirmation page matches with "cart" order total
    And  I verify the sales tax on order confirmation page matches with "cart" sales tax

    Examples:
      | ProductName  | ItemCode | Checkout | Reason                              | Customer            |
      | Defender A/S | 34302    | default  | Make an appointment at a later time | DEFAULT_CUSTOMER_AZ |

  @dt
  @at
  @web
  @coreScenario2
  @coreScenario2_TestFlow2
  Scenario Outline: Verify prices, Fees and Taxes for Staggered Vehicle(ALM#)
    When I change to the default store
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    And  I extract the product price from "PLP" page
    When I add item to my cart and "View shopping cart"
    And  I extract the product price from "Cart" page
    Then I verify the added products and prices displayed on cart page
    And  I verify the items total price on cart page
    When I select show fee details option for items
    Then I verify the "Environmental Fee" price for items
    And  I verify the "Disposal Fee" price for items
    And  I verify the "Installation" price for items
    And  I verify the subtotal price on the cart page
    And  I verify the tax on the cart page
    And  I extract the "sales tax" on the cart page
    And  I verify the Total price on the cart summary page
    And  I extract the "order total" on the cart page
    When I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    Then I verify the checkout order total matches with shopping cart order total
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    When I click next step for customer information
    Then I verify the checkout order total matches with shopping cart order total
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    When I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I verify the order total on order confirmation page matches with "cart" order total
    And  I verify the sales tax on order confirmation page matches with "cart" sales tax

    Examples:
      | Year | Make   | Model | Trim       | Assembly | FitmentOption | Checkout | Reason                              | Customer                     | Credit Card |
      | 2014 | Nissan | 370Z  | Coupe Base | none     | All tire sets | default  | Make an appointment at a later time | DEFAULT_CUSTOMER_BOPIS_VISA  | Visa Bopis  |

  @dt
  @at
  @web
  @coreScenario3
  @coreScenario3_TestFlow1
  Scenario Outline: Install without Appointments via PDP with Non Staggered Fitment
    When I change to the default store
    And  I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "<Checkout>"
    Then I verify "install without appointment" option is enabled on the Checkout page
    When I select the checkout without install reason "<Reason>"
    And  I select "ROPIS" option
    And  I reserve items and complete checkout for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:
      | ProductName  | ItemCode | Checkout | Customer            | Reason                              |
      | Defender A/S | 34302    | default  | DEFAULT_CUSTOMER_AZ | Make an appointment at a later time |

  @dt
  @at
  @web
  @coreScenario3
  @coreScenario3_TestFlow2
  Scenario Outline:  Install w/ Appointments via PLP with Staggered Data
    When I change to the default store
    And  I open the "TIRES" navigation link
    And  I click the "TIRES BY VEHICLE" menu option
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I add item "<ItemCode>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I select install with appointment
    And  I select first available appointment date
    And  I extract date and time for validation
    And  I click next step for customer information
    Then I verify date and time on the customer details appointment page
    When I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I verify date and time on the customer details appointment page
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I verify date and time on the order confirmation page

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | FitmentOption | Header       | Checkout | ProductName  | ItemCode | Customer                    | Credit Card |
      | 2015 | Honda | Accord | Coupe EX | none     | All tires     | tire results | default  | Defender A/S | 34384    | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis  |

  @dt
  @at
  @web
  @coreScenario3
  @coreScenario3_TestFlow3
  Scenario Outline: Install w/ Appointments with Walk-ins Welcome Validation
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "<Checkout>"
    And  I select install with appointment
    Then I verify WalkIns Welcome message displays
    And  I verify View Times did not display on any unavailable appointment dates
    And  I verify store is closed on Sunday

    Examples:
      | ZipCode | Store  | ProductName  | ItemCode | Checkout |
      | 97223   | ORP 01 | Defender A/S | 34302    | default  |

  @dt
  @at
  @web
  @coreScenario3
  @coreScenario3_TestFlow4
  Scenario Outline:  Schedule Service Appointment
  """TODO: The SCHEDULE APPOINTMENT link not found in Safari for either QA or Stg."""
    When I change to the default store
    And  I open the "SERVICES" navigation link
    And  I click on the "SCHEDULE APPOINTMENT" Link
    And  I select service option(s): "<ServiceOption>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select first available appointment date
    And  I extract date and time for validation
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    Then I should see an appointment confirmation message for "<Customer>" with service options: "<ServiceOption>"
    And  I should see my previously selected store, date and time, in the appointment details section
    And  I store the order number

    Examples:
      | ServiceOption             | Customer            |
      | Tire Rotation and Balance | DEFAULT_CUSTOMER_AZ |

  @dt
  @at
  @web
  @coreScenario4
  @coreScenario4_TestFlow1
  Scenario Outline:  Reserve Product w/o Appointments via PDP with Non Staggered Fitment
  """TODO: Fix Reason selection in Firefox. Using default Reason to accommodate FF since we are only testing validation here."""
    When I change to the default store
    And  I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the subtotal amount on the "cart" page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    And  I click next step for customer information
    Then I verify the checkout appointment details reason matches checkout without install reason "<Reason>"
    And  I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I verify the appointment confirmation order placed date is correct
    And  I verify the appointment confirmation store name matches my store on shopping cart
    And  I verify the appointment confirmation sales tax matches sales tax amount on shopping cart
    And  I verify the appointment confirmation order total matches shopping cart order total
    And  I store the order number

    Examples:
      | ProductName  | ItemCode | Checkout | Customer            | Reason                      | Customer            |
      | Defender A/S | 34302    | default  | DEFAULT_CUSTOMER_AZ | Not sure of my availability | default_customer_az |

  @dt
  @at
  @web
  @coreScenario4
  @coreScenario4_TestFlow2
  Scenario Outline:  Reserve Product w/ Appointments via PDP with Staggered Fitment
    When I change to the default store
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    When I add item to my cart and "View shopping cart"
    And  I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the subtotal amount on the "cart" page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "<Checkout>"
    And  I select install with appointment
    And  I select first available appointment date
    And  I extract date and time for validation
    And  I click next step for customer information
    Then I verify date and time on the customer details appointment page
    And  I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total
    When I select "ROPIS" option
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I should see my previously selected store, date and time, in the appointment details section
    And  I verify the appointment confirmation order placed date is correct
    And  I verify the appointment confirmation store name matches my store on shopping cart
    And  I verify the appointment confirmation sales tax matches sales tax amount on shopping cart
    And  I verify the appointment confirmation order total matches shopping cart order total
    And  I store the order number

    Examples:
      | Year | Make   | Model | Trim       | Assembly | FitmentOption | Checkout | Customer            |
      | 2014 | Nissan | 370Z  | Coupe Base | none     | All tire sets | default  | default_customer_az |

  @web
  @dtd
  @coreScenario5
  @coreScenario5_TestFlow1
  Scenario Outline: Core Scenario5-DTD-Checkout, Payments(CC), Shipping(Ground, NextDay Air) & Customers(US, APO, FPO)
  """TODO change shipping method link fails currently defect #9135
    | 34302    | Defender A/S | default  | default_customer_hi | UPS Next Day Air       | Discover         |
    | 34302    | Defender A/S | default  | fpo_customer        | Priority Mail Military | Discover         |
    | 34302    | Defender A/S | default  | apo_customer        | Priority Mail Military | American Express |"""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    And  I extract the product price from "Cart" page
    Then I should see product "<ProductName>" on the cart page
    When I select show fee details option
    Then I verify the environment fee amount on the shopping cart page
    And  I verify the tax on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    And  I expand the fee details for the item listed in the cart summary on the Checkout page
    Then I verify the "tax" on the checkout page
    When I extract the "sales tax" on the checkout page
    Then I verify the "order total" on the checkout page
    When I extract the "order total" on the checkout page
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify the order total on order confirmation page matches with "checkout" order total
    And  I verify the sales tax on order confirmation page matches with "checkout" sales tax
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer            | ShippingOption | Credit Card |
      | 34302    | Defender A/S | default  | default_customer_oh | UPS Ground     | MasterCard  |
      | 34302    | Defender A/S | default  | default_customer_tx | UPS Ground     | Visa        |

  @dtd
  @web
  @coreScenario5
  @coreScenario5_TestFlow2
  Scenario Outline: Checkout with Paypal using Homepage Keyword Search
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    And  I extract the product price from "Cart" page
    Then I should see product "<ProductName>" on the cart page
    When I select show fee details option
    Then I verify the environment fee amount on the shopping cart page
    And  I verify the tax on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select the default shipping option as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    And  I expand the fee details for the item listed in the cart summary on the Checkout page
    Then I verify the "tax" on the checkout page
    When I extract the "sales tax" on the checkout page
    Then I verify the "order total" on the checkout page
    When I extract the "order total" on the checkout page
    And  I select the "<Checkout>" payment option
    And  I continue on to PayPal checkout
    And  I log into paypal as "<Customer>"
    And  I continue with the paypal payment
    Then I am brought to the order confirmation page
    And  I verify the order total on order confirmation page matches with "checkout" order total
    And  I verify the sales tax on order confirmation page matches with "checkout" sales tax
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer           |
      | 34302    | Defender A/S | paypal   | paypal_customer_oh |

  @web
  @dtd
  @coreScenario5
  @coreScenario5_TestFlow3
  Scenario Outline: Verify prices, Fees and Taxes with fitment on DTD, checkout with Credit Card
  """TODO re-run the test after data issue(product not showing price) is fixed in QA"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item to my cart and "View shopping cart"
    And  I extract the product price from "Cart" page
    And  I select the optional "Certificates" fee for items
    Then I verify the Certificate fee amount on the shopping cart page
    When I select the optional "TPMS Rebuild Kit" fee for items
    Then I verify the "TPMS Rebuild Kit" price
    When I select the optional "TPMS Sensor" fee for items
    Then I verify the "TPMS Sensor" price
    When I select the optional "Valve Stem" fee for items
    Then I verify the "Valve Stem" price
    When I select show fee details option
    Then I verify the environment fee amount on the shopping cart page
    And  I verify the tax on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    And  I expand the fee details for the item listed in the cart summary on the Checkout page
    Then I verify the "tax" on the checkout page
    When I extract the "sales tax" on the checkout page
    Then I verify the "order total" on the checkout page
    When I extract the "order total" on the checkout page
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify the order total on order confirmation page matches with "checkout" order total
    And  I verify the sales tax on order confirmation page matches with "checkout" sales tax

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Checkout | Customer            | ShippingOption | Credit Card |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | default  | default_customer_oh | UPS Ground     | MasterCard  |