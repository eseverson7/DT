@article
Feature: Article

  Background:
    Given I change to the default store

  @dt
  @at
  @dtd
  @web
  @6796
  @regression
  @articleRegression
  Scenario Outline: HYBRIS_PRODUCT DETAIL PAGE_WHEELS AND TIRES_FacetValue_PLP_tire (ALM #6796)
    When I do a free text search for "<Search Term>" and hit enter
    Then I verify the PLP header message contains "Results for "<Search Term>""
    And  I verify the "Review Ratings, Speed Rating, Good Better Best" filter section(s) is/are displayed

    Examples:
      | Search Term |
      | tir         |
      | ti          |

  @dt
  @at
  @dtd
  @web
  @6789
  @regression
  @articleRegression
  Scenario: HYBRIS_PRODUCT DETAIL PAGE_WHEELS AND TIRES_Tire_Compare_Upto 3 (ALM #6789)
  """TODO CCL fails in DTD & AT due to defect #9285 - Missing attributes from compare product sections"""
    When I do a free text search for "tir" and hit enter
    Then I verify the PLP header message contains "Results for "tir""
    When I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I am brought to the page with header "Compare products"
    And  I verify expected attributes are displayed for compare page section "Size specifications"
    And  I verify expected attributes are displayed for compare page section "Tread and traction specifications"
    And  I verify expected attributes are displayed for compare page section "Safety and performance specifications"

  @dt
  @at
  @dtd
  @web
  @6788
  @6791
  @regression
  @articleRegression
  Scenario Outline: HYBRIS_PRODUCT DETAIL PAGE_ TIRES_Tire_PDP (ALM #6788, 6791)
    When I do a free text search for "<Item Code>" and hit enter
    And  I select from the "Quick Filters" filter section, "single" option(s): "<Quick Filter>"
    And  I select the first product result image
    Then I verify expected "<Attribute Type>" attributes are displayed for product detail page section "SIZE"
    Then I verify expected "<Attribute Type>" attributes are displayed for product detail page section "<Section>"
    Then I verify expected "<Attribute Type>" attributes are displayed for product detail page section "SAFETY & PERFORMANCE"

    Examples:
      | Item Code | Quick Filter | Attribute Type | Section              |
      | 34302     | Tires        | Tire           | TREAD & TRACTION     |
      | 49240     | Wheels       | Wheel          | STYLE & CONSTRUCTION |

  @dt
  @at
  @dtd
  @web
  @6794
  @regression
  @articleRegression
  Scenario Outline: HYBRIS_PRODUCT DETAIL PAGE_WHEELS AND TIRES_TireSet_PDP_verify all field (ALM #6794)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "All tire sets"
    And  I select the first product result image
    Then I verify the message on the "Tires" "PDP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify expected "Tire" attributes are displayed for product detail page section "SIZE"
    And  I verify expected "Tire" attributes are displayed for product detail page section "TREAD & TRACTION"
    And  I verify expected "Tire" attributes are displayed for product detail page section "SAFETY & PERFORMANCE"

    Examples:
      | Year | Make   | Model | Trim       | Assembly |
      | 2014 | Nissan | 370Z  | Coupe Base | none     |

  @dtd
  @web
  @6856
  @6855
  @regression
  @articleRegression
  Scenario Outline: HYBRIS_PRODUCT_DISPLAY RULES Shipping Block Rule Compare Page With Restricted Products Checkout (ALM #6856, 6855)
  """TODO CCL - data commented out as the products called for in ALM #6855 are unable to be added to cart Using Michelin
    products for time being. Entered data defect #9379
    | YOKOHAMA    | 43727, 44339, 43576 | 43727            | not displayed  |"""
    When I do a free text search for "<Search Term>" and hit enter
    And  I select "View All" from the Product Brand page
    And  I select item number(s): "<Item Numbers>" from the results list to compare
    And  I click the compare products Compare button
    Then I am brought to the page with header "Compare products"
    And  I verify the "Home, Compare Page" link in the breadcrumb container
    And  I verify "Add to cart" is enabled for each product being compared
    And  I verify the "We cannot ship items to locations" message is "<Display Status>" for all compared products
    When I select item number: "<Item To Purchase>" from compare products to view its product details
    Then I verify the "We cannot ship items to locations" message is "<Display Status>" on the product detail page
    When I add item to my cart and "View shopping cart"
    And  I select the checkout option "none"
    And  I enter shipping info as "default_customer_can"
    Then I verify the "Shipping Restriction" modal messaging as well as controls are "<Display Status>"

    Examples:
      | Search Term | Item Numbers        | Item To Purchase | Display Status |
      | NITTO       | 17832, 19345, 19350 | 19345            | displayed      |
      | Michelin    | 24231, 17077, 34302 | 34302            | not displayed  |

  @dtd
  @web
  @6846
  @regression
  @articleRegression
  Scenario Outline: HYBRIS_PRODUCT_DISPLAY RULES Shipping Block Rule And Change Address (ALM #6846)
    When I do a free text search for "<Item Code>" and hit enter
    Then I should see "<Item Name>" in the search results
    And  I verify the Add To Cart button is clickable and Red on "PLP" page
    When I add item to my cart and "View shopping cart"
    Then I should see product "<Item Name>" on the cart page
    When I select the checkout option "none"
    And  I enter shipping info as "<Canadian Customer>"
    Then I verify the "Shipping Restriction" modal messaging as well as controls are "displayed"
    When I select "Change Address" from the "Shipping Restriction" modal
    And  I enter shipping info as "<US Customer>"
    Then I verify the "Shipping Restriction" modal messaging as well as controls are "not displayed"
    And  I confirm the shipping options are: "UPS Ground - Free"

    Examples:
      | Item Code | Item Name | Canadian Customer    | US Customer         |
      | 19345     | Invo      | default_customer_can | default_customer_az |

  @dtd
  @web
  @6859
  @regression
  @articleRegression
  Scenario Outline: HYBRIS_PRODUCT_DISPLAY RULES Cart Page With Restricted and Unrestricted Products Remove Restricted (ALM #6859)
    When I do a free text search for "<Restricted Item Code 1>" and hit enter
    Then I should see "<Restricted Item Name 1>" in the search results
    And  I verify the Add To Cart button is clickable and Red on "PLP" page
    When I add item to my cart and "Continue Shopping"
    And  I do a free text search for "<Restricted Item Code 2>" and hit enter
    Then I should see "<Restricted Item Name 2>" in the search results
    And  I verify the Add To Cart button is clickable and Red on "PLP" page
    When I add item to my cart and "Continue Shopping"
    And  I do a free text search for "<Unrestricted Item Code>" and hit enter
    Then I should see "<Unrestricted Item Name>" in the search results
    And  I verify the Add To Cart button is clickable and Red on "PLP" page
    When I add item to my cart and "View shopping cart"
    Then I verify product "<Restricted Item Name 1>" is "displayed" on the cart page
    And  I verify product "<Restricted Item Name 2>" is "displayed" on the cart page
    And  I verify product "<Unrestricted Item Name>" is "displayed" on the cart page
    When I select the checkout option "none"
    And  I enter shipping info as "<Canadian Customer>"
    Then I verify the "Shipping Restriction" modal messaging as well as controls are "displayed"
    When I select "Close" from the "Shipping Restriction" modal
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify product "<Restricted Item Name 1>" is "displayed" in the "Order Summary" of the Checkout page
    And  I verify product "<Restricted Item Name 2>" is "displayed" in the "Order Summary" of the Checkout page
    And  I verify product "<Unrestricted Item Name>" is "displayed" in the "Order Summary" of the Checkout page
    When I resubmit the current "Shipping Details" for Checkout
    And  I select "Remove Items" from the "Shipping Restriction" modal
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify product "<Restricted Item Name 1>" is "not displayed" in the "Order Summary" of the Checkout page
    And  I verify product "<Restricted Item Name 2>" is "not displayed" in the "Order Summary" of the Checkout page
    And  I verify product "<Unrestricted Item Name>" is "displayed" in the "Order Summary" of the Checkout page

    Examples:
      | Restricted Item Code 1 | Restricted Item Name 1 | Restricted Item Code 2 | Restricted Item Name 2 | Unrestricted Item Code | Unrestricted Item Name | Canadian Customer    |
      | 19345                  | Invo                   | 17832                  | NT421Q                 | 34302                  | Defender A/S           | default_customer_can |