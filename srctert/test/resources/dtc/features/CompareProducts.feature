@compareProducts
Feature: Compare Products

  Background:
    Given I change to the default store

  @bba
  @dt
  @at
  @8348
  @compareProductsWithExpectedCategories
  @mobile
  @web
  @9901
  @compareProductsBBA
  Scenario: Compare Tire products with product reviews (ALM # 8348,9901)
  """TC 1 - Compare Products page displays w/ expected categories"""
    When I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "Shop for Passenger Tires" from the Product Brand page
    And  I select from the "Mileage Warranty" filter section, "single" option(s): "60,000-70,000"
    And  I select the first "2" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "2" products
    When I remove the first item on the compare product page
    And  I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products

  @bba
  @dt
  @at
  @8348
  @compareProductsRemoveFromCart
  @mobile
  @web
  @9901
  @compareProductsBBA
  Scenario Outline: Add and remove tires from shopping cart (ALM # 8348,9901)
  """TC 2 - Remove and re-add product from compare page"""
  """TODO: click Add an Item button is missing in the framework"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    When I select a fitment option "<FitmentOption>"
    And  I select the first "<Quantity>" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "<Quantity>" products
    When I add the first item to my cart and click "Close" on the Compare Products page
    And  I click the X next to the first product on the compare product page
    And  I click the undo remove product button
    Then I verify all categories are present for the "<Quantity>" products

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Quantity |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires this size | 3        |

  @bba
  @dt
  @at
  @dtd
  @8348
  @compareProductsModifyComparisonWithBrowserBackButton
  @web
  @9901
  @compareProductsBBA
  Scenario Outline: Modify products to be compared (ALM # 8348,9901)
  """TC 3 - Modify selection updated on Compare Products page (click x on item, click Browser Back Button)"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    And  I select the first "<Quantity>" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "<Quantity>" products
    When I navigate back to previous page
    Then I verify "<Quantity>" products selected
    And  I verify compare button text is displayed as "Compare"
    And  I verify the 'Compare (up to 3)' button is "disabled" on the products list page
    When I "deselect" the compare checkbox at position "1" from the products list page
    Then I verify compare button text is displayed as "Compare (up to 3)" at position "1"
    And  I verify the 'Compare (up to 3)' button is "enabled" on the products list page
    When I "select" the compare checkbox at position "1" from the products list page
    And  I click the compare products Compare button
    Then I verify all categories are present for the "<Quantity>" products
    When I click the X next to the first product on the compare product page
    Then I verify all categories are present for the "2" products
    When I navigate back to previous page
    And  I navigate back to previous page
    Then I verify "2" products selected
    And  I verify compare button text is displayed as "Compare (up to 3)"
    When I "select" the compare checkbox at position "1" from the products list page
    And  I click the compare products Compare button
    Then I verify all categories are present for the "<Quantity>" products
    And  I verify the "Home, Compare Page" link in the breadcrumb container
    And  I verify the current URL contains "/compare/product"
    
    Examples:
  | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Quantity |
  | 2012 | Honda | Civic | Coupe EX | none     | All tires this size | 3        |


  @8348
  @bba
  @dt
  @at
  @compareProductsModifyComparison
  @mobile
  @9901
  @compareProductsBBA
  Scenario: Mobile - Modify products to be compared (ALM # 8348,9901)
  """Mobile TC 3 - Modify selection updated on Compare Products page (click x on item, click Add an Item button)"""
    When I click the mobile homepage menu
    And  I click on "Tire" menu link
    And  I click on "Tire Brand" menu link
    And  I click the "Michelin Tires" menu option
    And  I select "Shop for Passenger Tires" from the Product Brand page
    And  I select from the "Mileage Warranty" filter section, "single" option(s): "60,000-70,000"
    And  I select the first "2" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "2" products
    When I remove the first item on the compare product page
    And  I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products

  @bba
  @dt
  @at
  @8348
  @compareProductsRemoveAll
  @web
  @9901
  @compareProductsBBA
  Scenario: Compare Products and remove all (ALM # 8348,9901)
  """TC 4 TODO: Fail with Defect 8613"""
    When I open the "TIRES" navigation link
    And  I click the "All-Season Tires" menu option
    And  I select "GOODYEAR" from the Product Brands page
    Then I verify that the search refinement filters contain the "single" value(s): "goodyear"
    When I select from the "Mileage Warranty" filter section, "single" option(s): "40,000-50,000"
    And  I note the current product list results url
    And  I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products
    When I click Remove All
    Then I am taken back to the previous product list results url

  @8348
  @bba
  @dt
  @at
  @compareProductsRemoveAll
  @mobile
  @9901
  @compareProductsBBA
  Scenario: Mobile - Compare Products and remove all (ALM # 8348,9901)
  """Mobile TC 4 - Mobile Goodyear tires does not have enough products for Mileage Warranty.More products need to be
    added to filter"""
    When I click the mobile homepage menu
    And  I click on "Tire" menu link
    And  I click on "Tire Type" menu link
    And  I click on "All-Season Tires" menu link
    And  I select "GOODYEAR" from the Product Brands page
    Then I verify that the search refinement filters contain the "single" value(s): "Goodyear"
    And  I note the current product list results url
    And  I select from the "Mileage Warranty" filter section, "single" option(s): "40,000-50,000"
    And  I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products
    When I click Remove All
    Then I am taken back to the previous product list results url

  @bba
  @dt
  @at
  @8348
  @compareProductsFromDifferentResultPages
  @web
  @9901
  @compareProductsBBA
  Scenario: Compare Products from different result pages (ALM # 8348,9901)
  """TC 5"""
    When I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "Shop for All-Season Tires" from the Product Brand page
    And  I select a single product to compare
    And  I go to the "next" page of product list results
    And  I select the first "2" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "2" products

  @8348
  @bba
  @dt
  @at
  @compareProductsFromDifferentResultPages
  @mobile
  @9901
  @compareProductsBBA
  Scenario: Mobile - Compare Products from different result pages (ALM # 8348,9901)
  """Mobile TC 5 - Mobile"""
    When I click the mobile homepage menu
    And  I click on "Tire" menu link
    And  I click on "Tire Brand" menu link
    And  I click on "Michelin Tires" menu link
    And  I select "Shop for All-Season Tires" from the Product Brand page
    And  I select a single product to compare
    And  I go to the "next" page of product list results
    And  I select a single product to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "2" products

  @8856
  @bba
  @dtd
  @web
  @compareProductsBBA
  Scenario Outline: DTD - Compare Tire products with product reviews (ALM # 8856)
    When I navigate to Shop by Brand
    And  I select "<Brand>" and find products
    Then I see the "<Brand>" that I selected
    When I select "<SubCategory>" to shop
    And  I select from the "Mileage Warranty" filter section, "single" option(s): "60,000-70,000"
    And  I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products

    Examples:
      | Brand          | SubCategory               |
      | Michelin Tires | Shop for All-Season Tires |

  @8856
  @bba
  @dtd
  @mobile
  @compareProductsBBA
  Scenario Outline: DTD Mobile - Compare Tire products (ALM # 8856)
    When I click the mobile homepage menu
    And  I click on "Tire" menu link
    And  I click on "Tire Brand" menu link
    And  I click on "View all" menu link
    And  I select the "<Brand>" tire brand image
    And  I select "<SubCategory>" to shop
    And  I select from the "Mileage Warranty" filter section, "single" option(s): "60,000-70,000"
    And  I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products

    Examples:
      | Brand          | SubCategory               |
      | Michelin Tires | Shop for All-Season Tires |

  @at
  @dt
  @web
  @15703
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify the inventory message for the products in Compare Products page (ALM #15703)
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    And  I select the "<Brand>" tire brand image
    And  I select "View All" from the Product Brand page
    And  I select the first "2" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "2" products
    When I select 'Add an item' to compare
    And  I "select" the compare checkbox at position "<Position>" from the products list page
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products

    Examples:
      | Brand          | Position |
      | Michelin Tires | 3        |
      | Continental    | 3        |
