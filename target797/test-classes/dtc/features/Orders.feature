@orders
Feature: Orders

  Background:
    Given I change to the default store

  @at
  @web
  @8426
  @reservationVehicleMultiProductPromotion
  Scenario Outline: HYBRIS_ORDER_SALES_AT Hard Reservation_ Vehicle_multi-product_promotion_7 (ALM #8426)
  """first data table TODO: For validation of logic in STG (e.g. not the data requested to be used in ALM testcase)
    second data table TODO: Data Defect 6599
    | 2012 | Honda | Civic | Coupe DX | none     | All tires     | Results for Tires | 34299          | Defender A/S   | 20464          | DR-69          | default_customer_az |"""
    When I search for store within "25" miles of "92324"
    And  I select "Make this my store" for store #"1" in the store location results
    And  I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I do a free text search for "<Product A Code>"
    Then I should see product list page with "<Product A Name>"
    When I select "<Product A Name>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<Product A Name>"
    When I add item to my cart and "Continue Shopping"
    And  I do a free text search for "<Product B Code>"
    Then I should see product list page with "<Product B Name>"
    When I select "<Product B Name>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<Product B Name>"
    When I add item to my cart and "View shopping cart"
    Then I see "<Product A Code>" on the cart page
    And  I see "<Product B Code>" on the cart page
    When I select the checkout option "with appointment"
    And  I schedule an appointment for "<Customer>"
    And  I reserve items for "<Customer>"
    Then I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Header       | Product A Code | Product A Name | Product B Code | Product B Name | Customer            |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | tire results | 34302          | Defender A/S   | 23500          | KNIGHT         | default_customer_az |

  @at
  @8426
  @mobile
  @reservationVehicleMultiProductPromotion
  Scenario Outline: Mobile - HYBRIS_ORDER_SALES_AT Hard Reservation_ Vehicle_multi-product_promotion_7 (ALM #8426)
  """TODO: For validation of logic in STG (e.g. not the data requested to be used in ALM testcase)
    TODO: Data Defect 6599
    | 2012 | Honda | Civic | Coupe DX | none     | All tires     | Results for Tires | 34299          | Defender A/S   | 20464          | DR-69          | default_customer_az |"""
    When I search for store within "25" miles of "92324"
    And  I select "Make this my store" for store #"1" in the store location results
    And  I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I do a free text search for "<Product A Code>"
    Then I should see "<Product A Name>" in the search results
    When I add item "<Product A Code>" of type "none" to my cart and "Continue Shopping"
    And  I do a free text search for "<Product B Code>"
    Then I should see "<Product B Name>" in the search results
    When I add item "<Product B Code>" of type "none" to my cart and "View shopping cart"
    Then I see "<Product A Code>" on the cart page
    And  I see "<Product B Code>" on the cart page
    When I select the checkout option "with appointment"
    And  I schedule an appointment for "<Customer>"
    And  I reserve items for "<Customer>"
    When I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Header       | Product A Code | Product A Name | Product B Code | Product B Name | Customer            |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | tire results | 34302          | Defender A/S   | 23500          | KNIGHT         | default_customer_az |

  @dtd
  @9633
  @web
  Scenario Outline: Checkout More Shipping Options Canadian Shipping Address_2 (ALM # 9633)
  """TODO: After 'I enter shipping info' Check for 3 shipping options
    then I confirm the shipping options are: 'UPS Standard, UPS Worldwide ExpeditedSM, UPS Saver - 1 Delivery day'"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    Then I confirm the shipping options are: "UPS Standard"
    And  I verify "UPS Ground - Free" does not appear in the Delivery Method section
    When I select shipping option: "UPS Standard" as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    Then I confirm that taxes are listed on the "checkout" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout | Customer             |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 18012    | default  | default_customer_can |

  @dtd
  @9635
  @web
  Scenario Outline: Checkout More Shipping Options FPO Shipping Address_4 (ALM # 9635)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    Then I verify "UPS Ground - Free" does not appear in the Delivery Method section
    When I select shipping option: "Priority Mail Military" as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout | Customer     |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 18012    | default  | fpo_customer |

  @dtd
  @6619
  @web
  Scenario Outline: Checkout with Paypal verifying non-editable customer fields (ALM #6619)
  """TODO: Fails in dtdqa1 due to the first 2 pages of results having no prices and pushing real products to 3rd or 4th page of results"""
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select the default shipping option as "<Customer>"
    And  I select the "<Checkout>" payment option
    And  I continue on to PayPal checkout
    And  I log into paypal as "<Customer>"
    And  I continue with the paypal payment
    Then I confirm the Checkout sections "Shipping Address", "Delivery Method" and "Payment & Billing Address" cannot be edited
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout | Customer           |
      | 2012 | Honda | Civic | Coupe DX | none     | All Wheels    | 49240    | paypal   | paypal_customer_az |

  @dtd
  @web
  @8226
  Scenario Outline: HYBRIS_ORDERS_CHECKOUT_DTD_VantivPaymentChanges_CarCareOne (ALM #8226)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I "accept" credit card discloure consent
    And  I "accept" credit card terms of agreement
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer              | ShippingOption | Credit Card |
      | 34302    | Defender A/S | default  | car_care_one_customer | UPS Ground     | carcareone  |

  @mobile
  @8226
  Scenario Outline: HYBRIS_ORDERS_CHECKOUT_DTD_VantivPaymentChanges_CarCareOne (ALM #8226)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I "accept" credit card discloure consent
    And  I "accept" credit card terms of agreement
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer              | ShippingOption | Credit Card |
      | 34302    | Defender A/S | default  | car_care_one_customer | UPS Ground     | carcareone  |

  @dtd
  @6180
  @envFeeValidation
  @web
  Scenario Outline: Verify the environmental fee for an item appears in the shopping cart (ALM 6180)
  """TODO: Only works in QA (AZ), STG (Portland) stores don't have state requirement for env fee on tires."""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    And  I verify the "Environmental Fee" label present on the shopping cart page
    And  I verify the environment fee amount "<Env Fee>" on the shopping cart page

    Examples:
      | ItemCode | ProductName        | Env Fee |
      | 26895    | Extreme Contact DW | 8.00    |

  @dtd
  @6178
  @tiresCertificatesValidation
  @web
  Scenario Outline: Verify the tires certificates fee of an item appears in the shopping cart (ALM 6178)
  """TODO - Reactivate the line below 'I confirm shipping options' when the additional shipping options are available/
    ready in all environments
    | 26895    | R265        | 117.00   |
    Originally = |26895    |EXTREME CONTACT DW|156.00   |"""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    And  I verify the certificate service fee amount "<Cert Fee>" on the shopping cart page

    Examples:
      | ItemCode | ProductName        | Cert Fee |
      | 26895    | Extreme Contact DW | 165.00   |

  @dtd
  @9307
  @9634
  @web
  Scenario Outline: Checkout with More Shipping Options US, APO (ALM #9307, 9634)
  """TODO - Reactivate the line below when the additional shipping options are available/ready in all environments
    then I confirm the shipping options are: 'UPS Ground, UPS Second Day Air, UPS Next Day Air"
    Verified this data in examples table valid in dtdqa1:"""
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    Then I confirm the shipping options are: "<AvailableOptions>"
    When I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout | Customer            | AvailableOptions       | ShippingOption         |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 28704    | default  | default_customer_az | UPS Ground             | UPS Ground             |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 28704    | default  | apo_customer        | Priority Mail Military | Priority Mail Military |

  @dtd
  @12490
  @12531
  @12532
  @web
  @regression
  @ordersRegression
  Scenario Outline: Validate Order Details in Confirmation Page After Order is Placed Successfully_DTD (ALM # 12490,12531,12532)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select show fee details option
    Then I verify the environment fee amount on the shopping cart page
    When I extract environment fee details
    And  I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    Then I confirm the shipping options are: "<ShippingOption>"
    When I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    And  I expand the fee details for the item listed in the cart summary on the Checkout page
    And  I extract the "sales tax" on the checkout page
    Then I verify the "order total" on the checkout page
    When I extract the "order total" on the checkout page
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I verify customer "<Customer>" details are listed on the order confirmation page
    And  I verify the order total on order confirmation page matches with "checkout" order total
    And  I verify the sales tax on order confirmation page matches with "checkout" sales tax
    When I expand the fee details for the item listed on the order confirmation page
    Then I verify the Environment Fee details on the order confirmation page
    When I select survey feedback on order confirmation page
    And  I navigate to newly opened next tab
    Then I verify the current URL contains "<SurveyLink>"
    When I navigate to previous browser tab
    And  I close open tabs
    Then I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ProductName  | ItemCode | Checkout | Customer            | ShippingOption | SurveyLink                     |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | Defender A/S | 34302    | default  | default_customer_az | UPS Ground     | s3/3641012/Website-UX-DTD-Epic |

  @dt
  @13872
  @web
  @regression
  @ordersRegression
  Scenario Outline: Validate Order Details in Confirmation Page After Order is Placed Successfully_DT (ALM # 13872)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
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
    Then I am brought to the order confirmation page
    And  I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I verify date and time on the order confirmation page
    And  I verify customer "<Customer>" email confirmation message on the order confirmation page
    And  I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ProductName  | ItemCode | Checkout | Customer                    | Credit Card |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | Defender A/S | 34302    | default  | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis  |