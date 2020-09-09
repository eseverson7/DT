@inventory
@dtd
Feature: Checkout - Inventory

  Background:
    Given I go to the homepage

  @8730
  @web
  Scenario Outline: Inventory Display Rules Out Of Stock (ALM #8730)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    And  I select "<LinkText>" from the autocomplete dropdown of the search box
    Then I verify add To cart option is disabled

    Examples:
      | ItemCode | ProductName | LinkText                |
      | 11248    | UHP         | View all search results |

  @8730
  @mobile
  Scenario Outline: Inventory Display Rules Out Of Stock (ALM #8730)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    Then I verify add To cart option is disabled

    Examples:
      | ItemCode | ProductName |
      | 11248    | UHP         |

  @8648
  @web
  Scenario Outline: HYBRIS_280_INVENTORY DISPLAY RULES_Complete Checkout with Relative Inventory Status as IN STOCK _DTD
  (ALM #8648)
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    And  I select the "Falken Tires" tire brand image
    And  I select "Shop for All-Season Tires" from the Product Brand page
    Then I verify the "In Stock" checkbox to be deselected by default
    When I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I select the first product result image
    And  I add item to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Shipping Customer>"
    And  I select shipping option: "UPS Ground - Free" as "<Payment Customer>"
    And  I enter payment info with a different billing address and confirm Checkout Summary as "<Payment Customer>"
    And  I place the order for "<Payment Customer>"
    Then I confirm that taxes are listed on the "order" page
    When I store the order number

    Examples:
      | Checkout | Shipping Customer     | Payment Customer      |
      | default  | default_customer_ga   | default_customer_ga   |
      | default  | default_customer_oh_2 | default_customer_oh_2 |

  @8648
  @mobile
  Scenario Outline: Mobile - HYBRIS_280_INVENTORY DISPLAY RULES_Complete Checkout with Relative Inventory Status as
  IN STOCK _DTD (ALM #8648)
    When I click the mobile homepage menu
    And  I click on "Tire" menu link
    And  I click on "Tire Brand" menu link
    And  I click on "Falken Tires" menu link
    And  I click the "All-Season Tires" menu option
    And  I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I select the first product result image
    When I add item to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    And  I enter shipping info as "<Shipping Customer>"
    And  I select shipping option: "UPS Ground - Free" as "<Payment Customer>"
    And  I enter payment info with a different billing address and confirm Checkout Summary as "<Payment Customer>"
    And  I place the order for "<Payment Customer>"
    Then I confirm that taxes are listed on the "order" page
    When I store the order number

    Examples:
      | Checkout | Shipping Customer     | Payment Customer      |
      | default  | default_customer_ga   | default_customer_ga   |
      | default  | default_customer_oh_2 | default_customer_oh_2 |

  @dt
  @8676
  @web
  Scenario Outline: HYBRIS_155_INVENTORY_Without Checkout with Relative Inventory Status as Special Order (ALM #8676)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I select the "Check Inventory" link for item "<ItemCode>"
    Then I should verify that the Check Availability popup loaded
    And  I should verify that Special Order Label is displayed on Check Inventory popup
    And  I should verify that default store MY STORE label is at top and visible
    When I close the Check Inventory popup
    Then I should see product detail page with "<ProductName>"

    Examples:
      | ProductName   | ItemCode |
      | Sincera SN828 | 18012    |

  @dt
  @at
  @8675
  @globallyMarkettedProductDisplay
  @web
  Scenario Outline: Globally Marketted Product Inventory Display Check (ALM #8675)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    And  I should see product inventory stock message displayed on the page

    Examples:
      | ProductName             | ItemCode |
      | ROCKY MOUNTAIN ATS      | 10001    |
      | ASSURANCE TRIPLETRED AS | 30671    |


  @dt
  @at
  @8651
  @completeAppointmentSpecialOrder
  @web
  Scenario Outline: Complete Appointment with Relative Inventory Status as Special Order (ALM #8651)
  """TODO: first table of data is for Staging
    This data for QA DOES NOT WORK
    |85250   |AZP 20 |34299    |PBX A/T HARDCORE  |without appointment | default_customer_az |"""
    When I go to the homepage
    And  I search for store within "25" miles of "<Zipcode>"
    And  I close the Welcome Popup
    And  I select make this "<Store>" my store
    And  I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "Shop for Passenger Tires" from the Product Brand page
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    And  I validate the Special Order header message
    And  I validate the Call Us message
    When I reserve items for "<Customer>"
    Then I should see reservation confirmation message with details "<ProductName>" and "<ItemCode>"
    And  I store the order number

    Examples:      
      | Zipcode | Store  | ItemCode | ProductName | Checkout            | Customer            |
      | 85250   | AZP 20 | 17077    | X-Ice Xi3   | without appointment | default_customer_az |