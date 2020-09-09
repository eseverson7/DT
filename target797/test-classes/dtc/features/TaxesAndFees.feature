@taxesAndFees
Feature: Taxes & Fees for Checkout

  Background:
    Given I go to the homepage

  @dtd
  @bba
  @8629
  @8640
  @8643
  @web
  @taxesAndFeesBBA
  Scenario Outline: Checkout with Credit card using Homepage Keyword Search (ALM #8629,8640,8643)
  """TODO: AB 5/7/17, I believe this validation is no longer needed. Also, LA doesn't list taxes
    and  I confirm that fees are listed on the "checkout" page
    and  I confirm that fees are listed on the "order" page
    TODO: LA fails to list taxes. Test does not pass. Data defect # 6985"""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping and payment info as "<Customer>"
    Then I confirm that taxes are listed on the "checkout" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer            |
      | 34302    | Defender A/S | default  | default_customer_oh |
      | 34302    | Defender A/S | default  | default_customer_la |
      | 34302    | Defender A/S | default  | default_customer_tx |

  @dtd
  @8629
  @8640
  @8643
  @bba
  @mobile
  @taxesAndFeesBBA
  Scenario Outline: Mobile - Checkout with Credit card using Homepage Keyword Search (ALM #8629,8640,8643)
  """TODO: AB 5/7/17, I believe this validation is no longer needed. Also, LA doesn't list taxes
    and  I confirm that fees are listed on the "checkout" page
    and  I confirm that fees are listed on the "order" page
    TODO: LA fails to list taxes. Test does not pass. Data defect # 6985"""
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping and payment info as "<Customer>"
    Then I confirm that taxes are listed on the "checkout" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer            |
      | 34302    | Defender A/S | default  | default_customer_oh |
      | 34302    | DEFENDER A/S | default  | default_customer_la |
      | 34302    | DEFENDER A/S | default  | default_customer_tx |

  @dtd
  @bba
  @8627
  @web
  @taxesAndFeesBBA
  Scenario Outline: Checkout with Paypal using Homepage Keyword Search (ALM #8627)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select the default shipping option as "<Customer>"
    And  I select the "<Checkout>" payment option
    And  I continue on to PayPal checkout
    And  I log into paypal as "<Customer>"
    And  I continue with the paypal payment
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I confirm that fees are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer           |
      | 34302    | Defender A/S | paypal   | paypal_customer_az |

  @dtd
  @8627
  @bba
  @mobile
  @taxesAndFeesBBA
  Scenario Outline: Mobile - Checkout with Paypal using Homepage Keyword Search (ALM #8627)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select the default shipping option as "<Customer>"
    And  I select the "<Checkout>" payment option
    And  I continue on to PayPal checkout
    And  I log into paypal as "<Customer>"
    And  I continue with the paypal payment
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I confirm that fees are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer           |
      | 34302    | Defender A/S | paypal   | paypal_customer_az |

  @dtd
  @web
  @8628
  Scenario Outline: HYBRIS_232_CHECKOUT_TAXES & FEES_Shipping Method_Payment_Taxes and Fees_California Zipcode (ALM #8628)
  """TODO - DTD PROD, STG and QA1 will fail when run with Internet Explorer; see defect 7018
    TODO: Following Step needs an update, will be done in DTD Core Scenario 5
    then I verify "fee": "<Fee>" is present in the cart summary section
    and  I verify "service": "<Service>" is present in the cart summary section"""
    When I browse to the "Shopping Cart" page with defaults
    And  I calculate the taxes and fees for "<Customer>"
    And  I select show fee details option
    Then I verify the "Environmental Fee" label present on the shopping cart page
    And  I verify the environment fee amount on the shopping cart page
    When I click to add the optional service: "<Service>" on the cart page
    And  I extract the optional service amount for: "<Service>" on the cart page
    And  I extract the subtotal amount on the "cart" page
    And  I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I extract the subtotal amount on the "checkout" page
    Then I verify the Checkout and Shopping Cart order summary subtotal amounts match
    When I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"

    Examples:
      | Checkout | Customer              | ShippingOption | Credit Card | Service      |
      | default  | CAR_CARE_ONE_CUSTOMER | UPS Ground     | MasterCard  | Certificates |

  @dt
  @at
  @bba
  @8634
  @reserveWithAppointmentViaFreeTextSearchFlorida
  @web
  @taxesAndFeesBBA
  Scenario Outline: Reserve Product with Appointment in Florida store using Free Text Search (ALM #8634)
  """TODO: Fails w/ null pointer exception. Is this step still valid? -> verify the order summary item
    TODO: Fails @Then because it's trying to verify store info not against selected store but the Default
    TODO: This data contains no appointments in QA. 32808 with store FLO 06 doesn't contain fee we need to validate"""
    When I search for store within "25" miles of "<Zipcode>"
    And  I close the Welcome Popup
    And  I select make this "<Store>" my store
    And  I go to the homepage
    And  I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    And  I verify the "<Order Summary Item>" is "<Fee Percentage>" percent of the total in the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I create an appointment with defaults from Checkout
    And  I extract date and time for validation
    Then I verify "<Store Info>" store on the customer information appointment page
    When I click next step for customer information
    Then I verify date and time on the customer details appointment page
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | Zipcode | Store  | ItemCode | ProductName  | Order Summary Item | Fee Percentage | Checkout         | Customer            | Store Info     |
      | 32955   | FLM 01 | 34302    | Defender A/S | PIF/PUF-FLM01      | 1              | with appointment | default_customer_fl | West Melbourne |

  @dt
  @at
  @8634
  @bba
  @reserveWithAppointmentViaFreeTextSearchFlorida
  @mobile
  @taxesAndFeesBBA
  Scenario Outline: Mobile - Reserve Product with Appointment in Florida store using Free Text Search (ALM #8634)
  """TODO: Fails w/ null pointer exception. Is this step still valid? -> verify the order summary item
    TODO: Fails @Then because it's trying to verify store info not against selected store but the Default
    TODO: This data contains no appointments in QA. 32808 with store FLO 06 doesn't contain fee we need to validate
    | 32955   | FLM 01 | 34302    | PBX A/T HARDCORE | PUF/PUF-FLM01      | 1              | with appointment | default_customer_fl |"""
    When I search for store within "25" miles of "<Zipcode>"
    And  I close the Welcome Popup
    And  I select make this "<Store>" my store
    And  I go to the homepage
    And  I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I verify the "<Order Summary Item>" is "<Fee Percentage>" percent of the total in the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I create an appointment with defaults from Checkout
    And  I extract date and time for validation
    Then I verify "<Store>" store on the customer information appointment page
    When I click next step for customer information
    Then I verify date and time on the customer details appointment page
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:      
      | Zipcode | Store  | ItemCode | ProductName  | Order Summary Item | Fee Percentage | Checkout         | Customer            |
      | 32955   | FLM 01 | 34302    | Defender A/S | PIF/PUF-FLM01      | 1              | with appointment | default_customer_fl |