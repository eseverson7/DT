@checkout
@dtd
Feature: Checkout

  Background:
    Given I go to the homepage

  @web
  @8775
  Scenario Outline: Checkout with major credit cards using Homepage Keyword Search US, APO, FPO (ALM #8775)
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
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer            | ShippingOption         | Credit Card      |
      | 34302    | Defender A/S | default  | default_customer_az | UPS Ground             | MasterCard       |
      | 34302    | Defender A/S | default  | default_customer_hi | UPS Ground             | Visa             |
      | 34302    | Defender A/S | default  | apo_customer        | Priority Mail Military | American Express |
      | 34302    | Defender A/S | default  | fpo_customer        | Priority Mail Military | Discover         |

  @8775
  @mobile
  Scenario Outline: Mobile - Checkout with major credit cards using Homepage Keyword Search US, APO, FPO (ALM #8775)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    When I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer            | ShippingOption         | Credit Card      |
      | 34302    | Defender A/S | default  | default_customer_az | UPS Ground             | MasterCard       |
      | 34302    | Defender A/S | default  | default_customer_hi | UPS Ground             | Visa             |
      | 34302    | Defender A/S | default  | apo_customer        | Priority Mail Military | American Express |
      | 34302    | Defender A/S | default  | fpo_customer        | Priority Mail Military | Discover         |

  @web
  @8775
  Scenario Outline: Checkout with major credit cards using Homepage Keyword Search CAN (ALM #8775)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "UPS Standard" as "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer             | Credit Card      |
      | 34302    | Defender A/S | default  | default_customer_can | American Express |
      | 34302    | Defender A/S | default  | default_customer_can | Discover         |
      | 34302    | Defender A/S | default  | default_customer_can | MasterCard       |
      | 34302    | Defender A/S | default  | default_customer_can | Visa             |

  @8775
  @mobile
  Scenario Outline: Mobile - Checkout with major credit cards using Homepage Keyword Search CAN (ALM #8775)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "UPS Standard" as "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    When I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer             | Credit Card      |
      | 34302    | Defender A/S | default  | default_customer_can | American Express |
      | 34302    | Defender A/S | default  | default_customer_can | Discover         |
      | 34302    | Defender A/S | default  | default_customer_can | MasterCard       |
      | 34302    | Defender A/S | default  | default_customer_can | Visa             |

  @web
  @8775
  Scenario Outline: Checkout with different shipping and billing address using Homepage Keyword Search (ALM #8775)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Shipping Customer>"
    And  I select shipping option: "UPS Ground" as "<Payment Customer>"
    And  I enter payment info with a different billing address and confirm Checkout Summary as "<Payment Customer>"
    And  I place the order for "<Payment Customer>"
    Then I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Shipping Customer   | Payment Customer     |
      | 34302    | Defender A/S | default  | default_customer_az | default_customer_can |

  @8775
  @mobile
  Scenario Outline: Mobile - Checkout with different shipping and billing address using Homepage Keyword Search (ALM #8775)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Shipping Customer>"
    And  I select shipping option: "UPS Ground" as "<Payment Customer>"
    And  I enter payment info with a different billing address and confirm Checkout Summary as "<Payment Customer>"
    And  I place the order for "<Payment Customer>"
    Then I confirm that taxes are listed on the "order" page
    When I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Shipping Customer   | Payment Customer     |
      | 34302    | Defender A/S | default  | default_customer_az | default_customer_can |

  @web
  @8775
  Scenario Outline: Checkout with Canadian customer with upper or lower case zip code and no spaces using Homepage Keyword Search (ALM #8775)
  """TODO: In DTDQA1 affected by defect 6442 - address line 1 not being populated after selecting suggested address"""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "UPS Standard" as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer               |
      | 34302    | Defender A/S | default  | UPPERCASE_CUSTOMER_CAN |
      | 34302    | Defender A/S | default  | lowercase_customer_can |

  @8775
  @mobile
  Scenario Outline: Mobile - Checkout with Canadian customer with upper or lower case zip code and no spaces using Homepage Keyword Search (ALM #8775)
  """TODO: In DTDQA1 affected by defect 6442 - address line 1 not being populated after selecting suggested address"""
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "UPS Standard" as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    When I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer               |
      | 34302    | Defender A/S | default  | UPPERCASE_CUSTOMER_CAN |
      | 34302    | Defender A/S | default  | lowercase_customer_can |

  @mailinator
  @tireOrderEmailValidation
  @web
  Scenario Outline: Tire size order email validation with Mailinator
  """TODO: ALM number pending"""
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    When I store the order number
    Then I confirm "<Customer>" receives an email for the order

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout | Customer                  | ShippingOption |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 28704    | default  | email_validation_customer | UPS Ground     |

  @8579
  @checkoutFromReviewComparisonPageShippingUPS
  @web
  Scenario Outline: Checkout from Review Comparison Page by Shipping UPS (ALM # 8579)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I click compare tire reviews link
    Then I verify the compare tire reviews page displays
    When I click add to cart for selected tire on compare tire reviews page
    Then I verify the item added to your cart popup displays
    And  I verify item added to your cart popup contains selected tires
    When I click view shopping cart button
    Then I see selected tires on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption | Checkout | Customer              | ShippingOption |
      | 2012 | Honda     | Civic    | Coupe DX | none     | All tires     | default  | default_customer_can  | UPS Standard   |
      | 2012 | Honda     | Civic    | Coupe DX | none     | All tires     | default  | default_customer_ca_2 | UPS Ground     |
      | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets | default  | default_customer_can  | UPS Standard   |
      | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets | default  | default_customer_ca_2 | UPS Ground     |

  @8575
  @web
  Scenario Outline: HYBRIS_213_CHECKOUT_Checkout from Product Comparison Page by Paypal Payment (ALM #8575)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I close the fitment popup
    And  I open the "TIRES" navigation link
    And  I click the "Cooper Tires" menu option
    And  I select "View All" from the Product Brand page
    And  I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I select the first "2" results to compare
    And  I click the compare products Compare button
    And  I add item to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select the default shipping option as "<Customer>"
    And  I select the "<Shipping>" payment option
    And  I continue on to PayPal checkout
    And  I log into paypal as "<Customer>"
    And  I continue with the paypal payment
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Checkout | Customer           | Shipping |
      | 2012 | Honda | Civic | Coupe DX | none     | default  | paypal_customer_az | paypal   |


  @8577
  @web
  Scenario Outline: HYBRIS_215_CHECKOUT_Checkout from Review Comparison Page with Shipping Adress Change (ALM # 8577)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I click compare tire reviews link
    Then I verify the compare tire reviews page displays
    When I click add to cart for the first tire on the compare tire reviews page
    And  I click view shopping cart button
    And  I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I select to edit the shipping address
    And  I set "Address Line 1" to "<NewAddressLine1>"
    And  I set "Zip / Postal Code" to "<NewZip>"
    And  I submit the updated address information
    And  I select the default shipping option as "<Customer>"
    And  I enter payment info and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify "<NewAddressLine1>" is listed on the order confirmation page
    And  I verify "<NewZip>" is listed on the order confirmation page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Checkout | Customer            | ShippingOption | NewAddressLine1     | NewZip     |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | default  | default_customer_az | UPS Ground     | 2240 W Camelback Rd | 85015-3445 |

  @8227
  @web
  @regression
  @checkoutRegression
  Scenario Outline: HYBRIS_215_CHECKOUT_Checkout from Review Comparison Page with Shipping Adress Change (ALM # 8227)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I go to the homepage
    And  I select mini cart and "View Cart"
    And  I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select shipping option: "<ShippingOption>" as "<Customer>"
    And  I select the "<PaymentType>" payment option
    And  I continue on to PayPal checkout
    And  I log into paypal as "<Customer>"
    And  I continue with the paypal payment
    Then I am brought to the order confirmation page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer           | PaymentType | ShippingOption |
      | 34302    | Defender A/S | default  | PAYPAL_CUSTOMER_OH | paypal      | UPS Ground     |

  @dtd
  @8653
  @smoketest
  @web
  @ordersSmoke
  @regression
  @checkoutRegression
  Scenario Outline: Checkout with Credit card using Homepage Keyword Search (ALM #8653)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping and payment info as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    And  I expand the fee details for the item listed in the cart summary on the Checkout page
    Then I verify the "tax" on the checkout page
    When I extract the "sales tax" on the checkout page
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify the sales tax on order confirmation page matches with "checkout" sales tax
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer            |
      | 34302    | Defender A/S | default  | default_customer_az |

  @8653
  @smoketest
  @mobile
  @ordersSmoke
  @regression
  @checkoutRegression
  Scenario Outline: Mobile - Checkout with Credit card using Homepage Keyword Search (ALM #8653)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping and payment info as "<Customer>"
    Then I confirm that taxes are listed on the "checkout" page
    And  I confirm that fees are listed on the "checkout" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I confirm that taxes are listed on the "order" page
    And  I store the order number

    Examples:
      | ItemCode | ProductName  | Checkout | Customer            |
      | 34302    | Defender A/S | default  | default_customer_az |

  @dtd
  @8654
  @smoketest
  @mobile
  @web
  @ordersSmoke
  @regression
  @checkoutRegression
  Scenario Outline: Checkout with Paypal using Vehicle Search via My Vehicles (ALM #8654)
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Customer>"
    And  I select the default shipping option as "<Customer>"
    And  I extract the "sales tax" on the checkout page
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
      | Year | Make  | Model | Trim     | Assembly | FitmentOption        | ItemCode | Checkout | Customer           |
      | 2012 | Honda | Civic | Coupe DX | none     | All wheels this size | 49240    | paypal   | paypal_customer_az |