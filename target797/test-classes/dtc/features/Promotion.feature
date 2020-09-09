@promotion
Feature: Promotions

  Background:
    Given I change to the default store

  @at
  @dt
  @8722
  @web
  @reserveFixedDiscount
  Scenario Outline: HYBRIS_260_PROMOTIONS&DISCOUNT_Reserve Product with Fixed Discount_Date Restrictions (ALM# 8722)
  """TODO: Item 34302 does not have a fixed discount applied
    | 34302    | without appointment | default_customer_az |
    NOTE: Original test calls for Item 25551 which does not exist in STG or QA for DT (Defect_ID 6936)
    | 25551    | without appointment | default_customer_az |"""
    When I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "View All" from the Product Brand page
    And  I select the "On Promotion" checkbox
    And  I extract the fixed dollar promotion discount of "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I validate the fixed dollar discount has been applied
    When I select the checkout option "<Checkout>"
    And  I click next step for customer information
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I verify the Total price on the order confirmation page

    Examples:
      | ItemCode | Checkout            | Customer            |
      | 34362    | without appointment | default_customer_az |

  @at
  @dt
  @8723
  @web
  @reserveFixedDiscountPercentage
  Scenario Outline: HYBRIS_261_PROMOTIONS&DISCOUNT_Appointmentt with Fixed Percentage with_Qty Restrictions (ALM # 8723)
  """TODO: On Promotion quick filter is available for brand Michelin not for Yokohama.
    and  I select the "On Promotion" quick filter checkbox"""
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    And  I select the "Yokohama Tires" tire brand image
    And  I select "View All" from the Product Brand page
    And  I extract the fixed percentage promotion discount of "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I validate the fixed percentage discount has been applied
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page
    When I create an appointment with defaults from Checkout
    And  I reserve items for "<Customer>"
    Then I verify the Total price on the order confirmation page

    Examples:
      | ItemCode | Checkout         | Customer            |
      | 43870    | with appointment | default_customer_az |

  @dt
  @8719
  @web
  @reserveFixedDiscountStoreDT
  Scenario Outline: HYBRIS_258_PROMOTIONS&DISCOUNT_Checkout with Fixed Discount_Store Restrictions (ALM #8719)
  """TODO: Fails in STG due to defect #7185 (No savings on cart page)
    Fails in QA due to defect #7191 (No tax on cart page)
    Fails in QA due to defect #7193 (Incorrect discount being applied)
    TODO: Original test calls for Item 12063 which does not exist in QA (DT/AT), wrong product in STG (Defect #7135)
    | 12063    | with appointment | default_customer_az"""
    When I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "View All" from the Product Brand page
    And  I select the "On Promotion" checkbox
    And  I extract the fixed percentage promotion discount of "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I validate the fixed percentage discount has been applied
    When I select the checkout option "<Checkout>"
    And  I create an appointment with defaults from Checkout
    And  I click next step for customer information
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I verify the Total price on the order confirmation page

    Examples:
      | ItemCode | Checkout         | Customer            |
      | 34302    | with appointment | default_customer_az |

  @at
  @8719
  @web
  @reserveFixedDiscountStoreAT
  Scenario Outline: HYBRIS_258_PROMOTIONS&DISCOUNT_Checkout with Fixed Discount_Store Restrictions (ALM #8719)
  """TODO: Fails in STG due to defect #7185 (No savings on cart page)
    Fails in QA due to defect #7191 (No tax on cart page)
    Fails in QA due to defect #7193 (Incorrect discount being applied)
    TODO: Original test calls for Item 12063 which does not exist in QA (DT/AT), wrong product in STG (Defect #7135)
    | 12063    | with appointment | default_customer_az"""
    When I open the "TIRES" navigation link
    And  I click the "Falken Tires" menu option
    And  I select "View All" from the Product Brand page
    And  I select the "On Promotion" checkbox
    And  I extract the fixed percentage promotion discount of "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I validate the fixed percentage discount has been applied
    When I select the checkout option "<Checkout>"
    And  I create an appointment with defaults from Checkout
    And  I click next step for customer information
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I verify the Total price on the order confirmation page

    Examples:
      | ItemCode | Checkout         | Customer            |
      | 18012    | with appointment | default_customer_ca |

  @dt
  @8720
  @web
  @reserveFixedDiscount
  Scenario Outline: HYBRIS_257_PROMOTIONS&DISCOUNT_Checkout with Fixed Discount_Product Type Restrictions (ALM #8720)
  """TODO: Fails in STG due to defect #7185 (No savings on cart page)
    Fails in QA due to defect #7191 (No tax on cart page)
    Fails in QA due to defect #7193 (Incorrect discount being applied)
    TODO: Item 34362 does not have a percentage fixed discount applied
    | 34362    | with appointment | default_customer_az |"""
    When I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "View All" from the Product Brand page
    And  I select the "On Promotion" checkbox
    And  I extract the fixed percentage promotion discount of "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I validate the fixed percentage discount has been applied
    When I select the checkout option "<Checkout>"
    And  I create an appointment with defaults from Checkout
    And  I click next step for customer information
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I verify the Total price on the order confirmation page

    Examples:
      | ItemCode | Checkout         | Customer            |
      | 34302    | with appointment | default_customer_az |

  @at
  @8720
  @web
  @reserveFixedDiscount
  Scenario Outline: HYBRIS_257_PROMOTIONS&DISCOUNT_Checkout with Fixed Discount_Product Type Restrictions (ALM #8720)
  """TODO: Fails in STG due to defect #7185 (No savings on cart page)
    Fails in QA due to defect #7191 (No tax on cart page)
    Fails in QA due to defect #7193 (Incorrect discount being applied)
    TODO: Item 34362 does not have a percentage fixed discount applied
    | 34362    | with appointment | default_customer_az |"""
    When I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "View All" from the Product Brand page
    And  I select the "On Promotion" checkbox
    And  I extract the fixed percentage promotion discount of "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I validate the fixed percentage discount has been applied
    When I select the checkout option "<Checkout>"
    And  I click next step for customer information
    And  I select "Place Order" after entering customer information for "<Customer>"
    Then I verify the Total price on the order confirmation page

    Examples:
      | ItemCode | Checkout            | Customer            |
      | 34302    | without appointment | default_customer_az |

  @at
  @dt
  @web
  @mobile
  @15380
  @promotion
  Scenario Outline: HYBRIS_PROMOTIONS_PROMOTIONS_Verify Instant Rebates with update quantity for staggered tires (ALM #15380)
    When I go to the homepage
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "All tire sets"
    And  I add item "<ItemCodeA>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I update quantity for item "<ItemCodeA>" to "<QtyA>"
    And  I update quantity for item "<ItemCodeB>" to "<QtyB>"
    Then I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I extract the "Instant Savings" from cart page
    And  I select the checkout option "default"
    And  I select the checkout without install reason "Make an appointment at a later time"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I click next step for customer information
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Order Confirmation" page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | ItemCodeA | ItemCodeB | QtyA | QtyB | Customer                    | Credit Card |
      | 2010 | Chevrolet | Corvette | Base | none     | 28653     | 36347     | 1    | 3    | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis  |


  @at
  @dt
  @web
  @mobile
  @15385
  @promotion
  Scenario Outline: HYBRIS_PROMOTIONS_PROMOTIONS_Verify Mail-In Rebates with update quantity for staggered tires (ALM #15385)
    When I go to the homepage
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "All tire sets"
    And  I add item "<ItemCodeA>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    And  I verify "<Mail In Rebate>" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "<Mail In Rebate>" displayed on the "cart page" for item "<ItemCodeB>"
    When I extract the "Mail In Rebate" from cart page
    And  I select the checkout option "default"
    And  I select the checkout without install reason "Make an appointment at a later time"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    When I click next step for customer information
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    When I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify "<Mail In Rebate>" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "<Mail In Rebate>" displayed on the "Order Confirmation" for item "<ItemCodeB>"

    Examples:
      | Year | Make      | Model    | Trim | Assembly | ItemCodeA | ItemCodeB | Mail In Rebate                | Customer                    | Credit Card |
      | 2010 | Chevrolet | Corvette | Base | none     | 36241     | 36259     | 30 Mail In Rebate for 4 tires | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis  |

  @at
  @dt
  @web
  @mobile
  @15413
  @promotion
  Scenario Outline: HYBRIS_PROMOTIONS_PROMOTIONS_DT_Verify Instant AND Mail-In Rebates for staggered tires (ALM #15413)
    When I go to the homepage
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "All tire sets"
    And  I add item "<ItemCodeA>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    And  I verify "<Mail In Rebate>" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "<Mail In Rebate>" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I extract the "Instant Savings" from cart page
    And  I extract the "Mail In Rebate" from cart page
    And  I select the checkout option "default"
    And  I select the checkout without install reason "Make an appointment at a later time"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    And  I verify the "Instant Savings" for set displayed on "Checkout" page
    When I click next step for customer information
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    And  I verify the "Instant Savings" for set displayed on "Checkout" page
    When I select "Place Order" after entering customer information for "<Customer>"
    And  I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    And  I verify the "Instant Savings" for set displayed on "Checkout" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify "<Mail In Rebate>" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "<Mail In Rebate>" displayed on the "Order Confirmation" for item "<ItemCodeB>"
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Order Confirmation" page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | ItemCodeA | ItemCodeB | Mail In Rebate     | Customer                    | Credit Card |
      | 2010 | Chevrolet | Corvette | Base | none     | 14175     | 14181     | 100 Mail In Rebate | DEFAULT_CUSTOMER_BOPIS_VISA | Visa Bopis  |

  @dtd
  @web
  @mobile
  @15495
  @promotion
  Scenario Outline: HYBRIS_PROMOTIONS_PROMOTIONS_DTD_Verify Instant AND Mail-In Rebates for staggered tires (ALM #15495)
    When I go to the homepage
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "All tire sets"
    And  I add item "<ItemCodeA>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    And  I verify "<Mail In Rebate>" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "<Mail In Rebate>" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I extract the "Instant Savings" from cart page
    And  I extract the "Mail In Rebate" from cart page
    And  I select the checkout option "default"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    And  I verify the "Instant Savings" for set displayed on "Checkout" page
    When I enter shipping info as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    And  I verify the "Instant Savings" for set displayed on "Checkout" page
    When I select the default shipping option as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Mail In Rebate" for set displayed on "Checkout" page
    And  I verify the "Instant Savings" for set displayed on "Checkout" page
    When I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify "<Mail In Rebate>" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "<Mail In Rebate>" displayed on the "Order Confirmation" for item "<ItemCodeB>"
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Order Confirmation" page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | ItemCodeA | ItemCodeB | Mail In Rebate     | Customer            | Credit Card |
      | 2010 | Chevrolet | Corvette | Base | none     | 14175     | 14181     | 100 Mail In Rebate | default_customer_az | Visa        |

  @dtd
  @web
  @mobile
  @15503
  @promotion
  Scenario Outline: HYBRIS_PROMOTIONS_PROMOTIONS_DTD_Verify Instant Rebates with update quantity for staggered tires (ALM #15503)
    When I go to the homepage
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "All tire sets"
    And  I add item "<ItemCodeA>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I update quantity for item "<ItemCodeA>" to "<QtyA>"
    And  I update quantity for item "<ItemCodeB>" to "<QtyB>"
    Then I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I extract the "Instant Savings" from cart page
    And  I select the checkout option "default"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I enter shipping info as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I select the default shipping option as "DEFAULT_CUSTOMER_AZ"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    And  I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Order Confirmation" page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | ItemCodeA | ItemCodeB | QtyA | QtyB | Customer            | Credit Card |
      | 2010 | Chevrolet | Corvette | Base | none     | 14175     | 14181     | 1    | 3    | default_customer_az | Visa        |

  @dtd
  @web
  @15546
  @promotion
  Scenario Outline: HYBRIS_PROMOTIONS_PROMOTIONS_DTD_Verify Percentage Rebates with update quantity for staggered (ALM #15546)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "All tire sets"
    And  I add item "<ItemCodeA>" of type "sets" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I update quantity for item "<ItemCodeA>" to "<QtyA>"
    And  I update quantity for item "<ItemCodeB>" to "<QtyB>"
    Then I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "cart page" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Cart" page
    When I extract the "Instant Savings" from cart page
    And  I select the checkout option "paypal"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I enter shipping info as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I select the default shipping option as "<Customer>"
    And  I expand the cart item details section of the cart summary on the Checkout page
    Then I verify the "Instant Savings" for set displayed on "Checkout" page
    When I select the "paypal" payment option
    And  I continue on to PayPal checkout
    And  I log into paypal as "<Customer>"
    And  I continue with the paypal payment
    Then I am brought to the order confirmation page
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeA>"
    And  I verify "Instant Savings" displayed on the "Order Confirmation" for item "<ItemCodeB>"
    And  I verify the Order Summary Instant Savings box on the "Order Confirmation" page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | ItemCodeA | ItemCodeB | QtyA | QtyB | Customer           |
      | 2010 | Chevrolet | Corvette | Base | none     | 35454     | 35455     | 1    | 3    | paypal_customer_oh |