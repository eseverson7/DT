@bopis
Feature: Bopis

  Background:
    Given I change to the default store

  @dt
  @11666
  @web
  Scenario Outline: HYBRIS_ORDERS_BOPIS _Placing order with 1 credit card and no recipient information_01 (ALM # 11666)
  """ Unable to test CC1 at the time this test was created. Add tests for CC1 when the issues are resolved. """
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with results
    When I set quantity to "<Quantity>" and add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I select first available appointment date
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "displayed"
    And  I verify "Pay now, pick up in-store" is "displayed"
    When I select "<Selection>" option
    And  I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    Then I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total
    When I expand the cart item details section of the cart summary on the Checkout page
    Then I verify product "<ProductName>" is "displayed" in the "Order Summary" of the Checkout page
    And  I verify product quantity for "<ProductName>" on checkout page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption       | ProductName  | ItemCode | Quantity | Checkout         | Selection | Customer                          | Credit Card      |
      | 2012 | Honda     | Civic    | Coupe DX | None     | All tires this size | Defender A/S | 34302    | 4        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_VISA       | Visa Bopis       |
      | 2010 | Chevrolet | Corvette | Base     | None     | All tire sets       | Potenza      | 25788    | 2        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_MASTERCARD | Mastercard Bopis |

  @dt
  @11667
  @web
  Scenario Outline: HYBRIS_ORDERS_BOPIS _Placing order with 2 credit cards and no recipient information_02 (ALM # 11667)
  """ Unable to test CC1 at the time this test was created. Add tests for CC1 when the issues are resolved. """
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with results
    When I set quantity to "<Quantity>" and add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I select first available appointment date
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "displayed"
    And  I verify "Pay now, pick up in-store" is "displayed"
    When I select "<Selection>" option
    And  I select "Place Order" after entering customer information for "<Customer1>"
    And  I enter payment info with "<Credit Card1>" and confirm Checkout Summary as "<Customer1>"
    And  I expand 'Split credit card payment'
    And  I reduce the amount paid by "half"
    And  I enter payment info with "<Credit Card2>" and confirm Checkout Summary as "<Customer2>"
    And  I save card details
    Then I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total
    When I expand the cart item details section of the cart summary on the Checkout page
    Then I verify product "<ProductName>" is "displayed" in the "Order Summary" of the Checkout page
    And  I verify product quantity for "<ProductName>" on checkout page
    When I place the order for "<Customer1>"
    Then I am brought to the order confirmation page

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption       | ProductName  | ItemCode | Quantity | Checkout         | Selection | Customer1                   | Credit Card1           | Customer2                         | Credit Card2     |
      | 2012 | Honda     | Civic    | Coupe DX | None     | All tires this size | Defender A/S | 34302    | 4        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis             | DEFAULT_CUSTOMER_BOPIS_MASTERCARD | Mastercard Bopis |
      | 2010 | Chevrolet | Corvette | Base     | None     | All tire sets       | Potenza      | 25788    | 2        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_AMEX | American Express Bopis | DEFAULT_CUSTOMER_BOPIS_VISA       | Visa Bopis       |

  @dt
  @11668
  @web
  Scenario Outline: HYBRIS_ORDERS_BOPIS _Placing order with 1 credit card with other recipient information_01 (ALM # 11668)
  """ Unable to test CC1 at the time this test was created. Add tests for CC1 when the issues are resolved. """
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with results
    When I set quantity to "<Quantity>" and add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I select first available appointment date
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "displayed"
    And  I verify "Pay now, pick up in-store" is "displayed"
    When I select "<Selection>" option
    And  I expand 'Someone else will pick up my order'
    And  I enter information for other recipient "<Other Customer>" requesting delivery by "phone" and "text updates"
    And  I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    Then I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total
    When I expand the cart item details section of the cart summary on the Checkout page
    Then I verify product "<ProductName>" is "displayed" in the "Order Summary" of the Checkout page
    And  I verify product quantity for "<ProductName>" on checkout page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption       | ProductName  | ItemCode | Quantity | Checkout         | Selection | Customer                        | Credit Card            | Other Customer      |
      | 2012 | Honda     | Civic    | Coupe DX | None     | All tires this size | Defender A/S | 34302    | 4        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_DISCOVER | Discover Bopis         | DEFAULT_CUSTOMER_AZ |
      | 2010 | Chevrolet | Corvette | Base     | None     | All tire sets       | Potenza      | 25788    | 2        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_AMEX     | American Express Bopis | DEFAULT_CUSTOMER_AZ |

  @dt
  @11670
  @web
  Scenario Outline: HYBRIS_ORDERS_BOPIS _Placing order with 2 credit card with other recipient information_04 (ALM # 11670)
  """ Unable to test CC1 at the time this test was created. Add tests for CC1 when the issues are resolved. """
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with results
    When I set quantity to "<Quantity>" and add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I select first available appointment date
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "displayed"
    And  I verify "Pay now, pick up in-store" is "displayed"
    When I select "<Selection>" option
    And  I expand 'Someone else will pick up my order'
    And  I enter information for other recipient "<Other Customer>" requesting delivery by "phone" and "text updates"
    And  I select "Place Order" after entering customer information for "<Customer1>"
    And  I enter payment info with "<Credit Card1>" and confirm Checkout Summary as "<Customer1>"
    And  I expand 'Split credit card payment'
    And  I reduce the amount paid by "half"
    And  I enter payment info with "<Credit Card2>" and confirm Checkout Summary as "<Customer2>"
    And  I save card details
    Then I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total
    When I expand the cart item details section of the cart summary on the Checkout page
    Then I verify product "<ProductName>" is "displayed" in the "Order Summary" of the Checkout page
    And  I verify product quantity for "<ProductName>" on checkout page
    When I place the order for "<Customer1>"
    Then I am brought to the order confirmation page

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption       | ProductName  | ItemCode | Quantity | Checkout         | Selection | Customer1                         | Credit Card1     | Customer2                       | Credit Card2           | Other Customer      |
      | 2012 | Honda     | Civic    | Coupe DX | None     | All tires this size | Defender A/S | 34302    | 4        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_DISCOVER   | Discover Bopis   | DEFAULT_CUSTOMER_BOPIS_AMEX     | American Express Bopis | DEFAULT_CUSTOMER_AZ |
      | 2010 | Chevrolet | Corvette | Base     | None     | All tire sets       | Potenza      | 25788    | 2        | with appointment | BOPIS     | DEFAULT_CUSTOMER_BOPIS_MASTERCARD | Mastercard Bopis | DEFAULT_CUSTOMER_BOPIS_DISCOVER | Discover Bopis         | DEFAULT_CUSTOMER_AZ |

  @dt
  @web
  @mobile
  @15450
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Validate BOPIS not displayed for product added from category without vehicle(ALM #15450)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I do a free text search for "Tire" and hit enter
    Then I am brought to the page with header "Tires"
    When I click on the "<ProductType>" Link
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "with appointment"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I select first available appointment date
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "not displayed"
    And  I verify "Pay now, pick up in-store" is "not displayed"
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"

    Examples:
      | ZipCode | Store  | ProductType               | ItemCode | ProductName   | Customer                    |
      | 86001   | AZF 01 | Shop for All-Season Tires | 18003    | Sincera SN828 | DEFAULT_CUSTOMER_BOPIS_VISA |

  @dt
  @web
  @mobile
  @15451
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Validate BOPIS not displayed for product added from free text search without vehicle(ALM #15451)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "with appointment"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I select first available appointment date
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "not displayed"
    And  I verify "Pay now, pick up in-store" is "not displayed"
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"

    Examples:
      | ZipCode | Store  | ItemCode | ProductName  | Customer                    |
      | 86001   | AZF 01 | 34302    | Defender A/S | DEFAULT_CUSTOMER_BOPIS_VISA |

  @dt
  @web
  @mobile
  @15629
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Validating ROPIS with BOPIS option when a product is added with vehicle (ALM # 15629)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "default"
    And  I select the checkout without install reason "Make an appointment at a later time"
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "displayed"
    And  I verify "Pay now, pick up in-store" is "displayed"
    And  I verify that the "Pay now, pick up in-store" radio button is "enabled"
    When I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"

    Examples:
      | ZipCode | Store  | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | ProductName | Customer                    | Credit Card |
      | 85260   | AZP 29 | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 31098    | FP0612 A/S  | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis  |

  @dt
  @web
  @mobile
  @15630
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Validate BOPIS and ROPIS not-displayed for product added from free text search without vehicle(ALM # 15630)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I select the checkout option "default"
    And  I select the checkout without install reason "Make an appointment at a later time"
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "not displayed"
    And  I verify "Pay now, pick up in-store" is "not displayed"
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"

    Examples:
      | ZipCode | Store  | ItemCode | ProductName  | Customer            |
      | 85260   | AZP 29 | 34302    | Defender A/S | DEFAULT_CUSTOMER_AZ |

  @dt
  @web
  @mobile
  @15631
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Validating ROPIS and BOPIS option not shown when a product is added with vehicle (ALM # 15631)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "default"
    And  I select the checkout without install reason "Make an appointment at a later time"
    And  I click next step for customer information
    Then I verify "Reserve now, pickup in-store" is "not displayed"
    And  I verify "Pay now, pick up in-store" is "not displayed"
    When I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"

    Examples:
      | ZipCode | Store  | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | ProductName | Customer                    |
      | 85255   | AZP 55 | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 31098    | FP0612 A/S  | DEFAULT_CUSTOMER_BOPIS_VISA |

  @dt
  @web
  @11551
  Scenario Outline: HYBRIS_ORDERS_BOPIS_Authorization decline using two credit cards, with 1 successful authorization and 1 decline _03 (ALM # 11551)
  """Test impacted by ALM defect #9897"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "All tires this size"
    Then I verify the product list page is displayed with results
    When I set quantity to "4" and add item "34302" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I extract my store name on the cart page
    And  I extract the "sales tax" on the cart page
    And  I extract the "order total" on the cart page
    And  I select the checkout option "with appointment"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I select first available appointment date
    And  I click next step for customer information
    And  I select "BOPIS" option
    And  I select "Continue To Payment" after entering customer information for "<Primary Customer>"
    And  I enter payment info with "<Primary Card>" and confirm Checkout Summary as "<Primary Customer>"
    And  I expand 'Split credit card payment'
    And  I reduce the amount paid by "half"
    And  I enter payment info with "<Secondary Card>" and confirm Checkout Summary as "<Secondary Customer>"
    And  I enter invalid CVN number of "000"
    And  I save card details
    Then I verify the checkout store name matches my store on shopping cart
    And  I verify the checkout sales tax matches with sales tax amount on shopping cart
    And  I verify the checkout order total matches with shopping cart order total
    When I place the order for "<Primary Customer>"
    Then I verify the 'Unable to process the card' messaging
    When I 'Edit card details' for the "secondary" credit card
    And  I enter the valid CVN number for card: "<Secondary Card>"
    And  I save card details
    And  I place the order for "<Primary Customer>"
    Then I am brought to the order confirmation page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | ProductName  | Primary Customer            | Primary Card | Secondary Customer                | Secondary Card   |
      | 2012 | Honda | Civic | Coupe DX | None     | Defender A/S | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis   | DEFAULT_CUSTOMER_BOPIS_MASTERCARD | MasterCard Bopis |