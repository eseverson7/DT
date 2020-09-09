@cartPage
Feature: Shopping Cart

  Background:
    Given I change to the default store

  @dt
  @at
  @9619
  @clearCart
  @web
  Scenario Outline: Unable to clear the cart while trying to change the Recent Vehicle to the Current Vehicle (ALM #9619)
  """Step 4 has validation of Cart is empty and displays as "$0.00" but this is not true. item is added and cart
    updated After clicking clear, cart does not clear right away. only after closing the select fitment popup or
    selecting a fitment. This differs from the expected result in Step 8 of the testcase"""
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "Continue Shopping"
    And  I open the My Vehicles popup
    Then I verify that My Vehicles displays "<Year> <Make> <Model>" as the current vehicle
    When I select Add Vehicle
    And  I do a vehicle search with details "2015" "Honda" "Accord" "Coupe EX" "none"
    Then I verify the options in the switch vehicle popup
    When I select "Clear my cart and Continue" in the Switching vehicle popup
    And  I select a fitment option "All tires"
    Then I verify My Vehicle in the header displays as "Honda Accord"
    And  I verify the header cart total is "$0.00"

    Examples:
      | Year | Make   | Model | Trim       | Assembly                            | FitmentOption | ItemCode |
      | 2010 | Nissan | 370Z  | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL | All tire sets | 25991    |

  @dt
  @at
  @9619
  @clearCart
  @mobile
  Scenario Outline: Unable to clear the cart while trying to change the Recent Vehicle to the Current Vehicle (ALM #9619)
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "Continue Shopping"
    And  I open the My Vehicles popup
    Then I verify that My Vehicles displays "<Year> <Make> <Model>" as the current vehicle
    When I select Add Vehicle
    And  I do a vehicle search with details "2015" "Honda" "Accord" "Coupe EX" "none"
    Then I verify the options in the switch vehicle popup
    When I select "Clear my cart and Continue" in the Switching vehicle popup
    And  I select a fitment option "All tires"
    And  I verify the header cart item count is "0"
    When I open the My Vehicles popup
    Then I verify that My Vehicles displays "2015 Honda Accord" as the current vehicle

    Examples:
      | Year | Make   | Model | Trim       | Assembly                            | FitmentOption | ItemCode |
      | 2010 | Nissan | 370Z  | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL | All tire sets | 25991    |

  @11010
  @web
  @dt
  @verifyPricesTaxesFees
  Scenario Outline: HYBRIS_SHOPPINGCART_Validate Prices and Taxes of the product_DT (ALM #11010)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I do a free text search for "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    And  I see a purchase quantity of "<Quantity>"
    And  I verify the item total displayed on cart page
    And  I verify total price on mini cart
    And  I verify the "Environmental Fee" label present on the shopping cart page
    And  I verify the environment fee amount on the shopping cart page
    And  I verify the "Tire Disposal Fee" label present on the shopping cart page
    And  I verify the tire disposal fee amount on the shopping cart page
    And  I verify the FET fee amount on the shopping cart if applicable to item "<ItemCode>"
    And  I verify the RRA Certificate message
    And  I verify the RRA Certificate "BasePrice" on MiniCart page
    And  I verify the RRA Certificate "Quantity" on MiniCart page
    And  I verify the RRA Certificate "TotalPrice" on MiniCart page
    And  I verify the installation fee amount on the shopping cart page
    And  I verify the TPMS Rebuild Kits amount calculated based on "<TPMSFee>" on cart page
    And  I verify the subtotal price on the cart page
    And  I verify the tax on the cart page
    When I click on More Options and select TPMS Sensor
    Then I verify the TPMS Sensor label present on the shopping cart page
    And  I verify the TPMS Sensor amount calculated based on "<TPMSSensor>" price on cart page
    And  I verify the subtotal price on the cart page
    And  I verify the tax on the cart page
    And  I verify the Total price on the cart summary page

    Examples:
      | Year | Make  | Model | Trim         | Assembly       | FitmentOption | ProductName       | ItemCode | Quantity | TPMSFee | TPMSSensor |
      | 2016 | Ram   | 2500  | MEGA CAB 4WD | 275 /70 R18 E1 | All tires     | Terra Grappler G2 | 10204    | 4        | 7.50    | 55.00      |
      | 2012 | Honda | Civic | Coupe DX     | none           | All tires     | Defender A/S      | 34302    | 4        | 0.00    | 60.00      |

  @dt
  @at
  @bba
  @8892
  @tpmsCartValidation
  @web
  @cartBBA
  Scenario Outline: HYBRIS_202_ShoppingCart_Add_Product_to_Shopping_Cart_from_Compare_Page (ALM #8892)
    When I do a free text search for "Tire" and hit enter
    Then I am brought to the page with header "Tires"
    When I click on the "<ProductType>" Link
    And  I select item number(s): "<Items>" from the results list to compare
    And  I click the compare products Compare button
    And  I click add to cart for the first tire on the compare tire reviews page
    And  I add item to my cart and "View shopping cart"
    Then I verify product "<Item>" is "displayed" on the cart page

    Examples:
      | ProductType               | Items        | Item  |
      | Shop for All-Season Tires | 18003, 31098 | 31098 |

  @11056
  @web
  @dt
  @verifyPricesTaxesFees
  Scenario Outline: HYBRIS_SHOPPINGCART_Validate Prices and Taxes for quantity updated in shopping cart page for Regular Vehicle_DT (ALM #11056)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I do a free text search for "<ItemCode>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    And  I see a purchase quantity of "<Quantity>"
    And  I verify the item total displayed on cart page
    And  I verify total price on mini cart
    When I update the quantity to "<New Quantity>"
    Then I should see quantity is set to "<New Quantity>" in the cart
    And  I verify the item total displayed on cart page
    And  I verify total price on mini cart
    And  I verify the "Environmental Fee" label present on the shopping cart page
    And  I verify the environment fee amount on the shopping cart page
    And  I verify the "Tire Disposal Fee" label present on the shopping cart page
    And  I verify the tire disposal fee amount on the shopping cart page
    And  I verify the FET fee amount on the shopping cart if applicable to item "<ItemCode>"
    And  I verify the RRA Certificate message
    And  I verify the RRA Certificate "BasePrice" on MiniCart page
    And  I verify the RRA Certificate "Quantity" on MiniCart page
    And  I verify the RRA Certificate "TotalPrice" on MiniCart page
    And  I verify the installation fee amount on the shopping cart page
    And  I verify the TPMS Rebuild Kits amount calculated based on "<TPMSFee>" on cart page
    And  I verify the subtotal price on the cart page
    And  I verify the tax on the cart page
    And  I verify the Total price on the cart summary page

    Examples:
      | Year | Make  | Model | Trim         | Assembly       | FitmentOption | ProductName       | ItemCode | Quantity | New Quantity | TPMSFee |
      | 2016 | Ram   | 2500  | MEGA CAB 4WD | 275 /70 R18 E1 | All tires     | Terra Grappler G2 | 10204    | 4        | 2            | 7.50    |
      | 2012 | Honda | Civic | Coupe DX     | none           | All tires     | Defender A/S      | 34302    | 4        | 2            | 7.50    |

  @dt
  @at
  @dtd
  @bba
  @8886
  @8887
  @web
  @cartBBA
  Scenario Outline: ShoppingCart _Remove Product in Shopping Cart from PLP (ALM #8886, 8887)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ProductName" "<ProductName>" is in the cart
    When I remove the item from the cart
    Then I should see product has been "removed" in cart message

    Examples:
      | ProductName  | ItemCode |
      | Defender A/S | 34302    |

  @dt
  @at
  @dtd
  @8886
  @bba
  @mobile
  @cartBBA
  Scenario Outline: Mobile - ShoppingCart _Remove Product in Shopping Cart from PLP (ALM #8886)
    When I do a free text search for "<ItemCode>"
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I remove the item from the cart
    Then I should see product has been "removed" in cart message

    Examples:
      | ProductName  | ItemCode |
      | Defender A/S | 34302    |

  @dt
  @at
  @dtd
  @bba
  @8880
  @8881
  @web
  @cartBBA
  Scenario Outline: ShoppingCart _Update Product in Shopping Cart from PLP (ALM #8880, 8881)
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    And  I update the quantity to "<UpdatedQuantity>"
    And  I should see quantity is set to "<UpdatedQuantity>" in the cart

    Examples:
      | ProductName  | ItemCode | UpdatedQuantity |
      | Defender A/S | 34302    | 8               |

  @dt
  @at
  @dtd
  @8880
  @8881
  @bba
  @mobile
  @cartBBA
  Scenario Outline: Mobile - ShoppingCart _Update Product in Shopping Cart from PLP (ALM #8880, 8881)
    When I do a free text search for "<ItemCode>"
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    And  I update quantity for item "<ItemCode>" to "<UpdatedQuantity>"
    Then I verify quantity for "<ItemCode>" is set to "<UpdatedQuantity>" in the cart

    Examples:
      | ProductName  | ItemCode | UpdatedQuantity |
      | Defender A/S | 34302    | 8               |

  @dt
  @at
  @web
  @mobile
  @12265
  @12297
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Verify Installation fee when the quantity of two or more tire products updated regular vehicle (ALM #12265, 12297)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "Continue Shopping"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeB>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    When I select show fee details option for item "<ItemCodeB>"
    Then I verify the Installation price for item "<ItemCodeB>"
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the Installation price for item "<ItemCodeA>"
    When I update quantity for item "<ItemCodeA>" to "<QtyItemCodeA>"
    Then I verify the Installation price for item "<ItemCodeA>"
    When I update quantity for item "<ItemCodeB>" to "<QtyItemCodeB>"
    Then I verify the Installation price for item "<ItemCodeB>"
    When I remove the item "<ItemCodeB>" from the cart
    Then I verify the item "<ItemCodeB>" is removed from the cart page
    And  I see "<ItemCodeA>" on the cart page
    And  I see a purchase quantity of "<QtyItemCodeA>"
    And  I verify the Installation price for item "<ItemCodeA>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCodeA | ItemCodeB | QtyItemCodeA | QtyItemCodeB |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 18012     | 34302     | 2            | 6            |

  @dt
  @at
  @web
  @12299
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Verify Installation fee is displayed as a line item for a tire products added to cart for a staggered vehicle (ALM #12299)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    And  I verify the plp display tabs for staggered vehicle
    And  I verify the product list page is displayed with sets
    When I add item "<ItemCodeA>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    And  I verify the "Installation" price for items

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption | ItemCodeA | ItemCodeB |
      | 2010 | Chevrolet | Corvette | Base | none     | All tire sets | 35524     | 15387     |

  @dt
  @at
  @web
  @12337
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Verify Installation fee when a vehicle is added with matching tires and wheels (ALM #12337)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "Continue Shopping"
    Then I verify the PLP header message contains "tire results"
    When I open the My Vehicles popup
    Then I verify My Vehicles popup displays add vehicle
    And  I verify that My Vehicles displays "<Year> <Make> <Model>" as the current vehicle
    When I select current Vehicle
    Then I should see the fitment panel page with fitment options
    When I select the "WHEELS" menu option
    And  I select a fitment option "All wheels this size"
    Then I verify the PLP header message contains "wheel results"
    When I add item "<ItemCodeB>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the Installation price for item "<ItemCodeA>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCodeA | ItemCodeB |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 18012     | 75909     |

  @dt
  @at
  @web
  @13511
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Verify Installation fee when tires and wheels are added with unmatching sizes for a regular vehicle (ALM #13511)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "Continue Shopping"
    Then I verify the PLP header message contains "tire results"
    When I open the My Vehicles popup
    Then I verify My Vehicles popup displays add vehicle
    And  I verify that My Vehicles displays "<Year> <Make> <Model>" as the current vehicle
    When I select current Vehicle
    Then I should see the fitment panel page with fitment options
    When I select the "WHEELS" menu option
    And  I select a fitment option "<SizeOption>"
    Then I verify the PLP header message contains "wheel results"
    When I add item "<ItemCodeB>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the Installation price for item "<ItemCodeA>"
    When I select show fee details option for item "<ItemCodeB>"
    Then I verify the Installation price for item "<ItemCodeB>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCodeA | ItemCodeB | SizeOption |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 18012     | 69173     | 16         |

  @dt
  @at
  @web
  @12307
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Verify Installation fee when tires and wheels are added without vehicle (ALM #12307)
    When I do a free text search for "Tire" and hit enter
    Then I am brought to the page with header "Tires"
    When I click on the "<ProductType>" Link
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "Continue Shopping"
    Then I verify the PLP header message contains "tire results"
    When I do a free text search for "Wheels" and hit enter
    Then I verify the PLP header message contains "Results for "wheels""
    When I add item "<ItemCodeB>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the "Installation" label displayed for item "<ItemCodeA>"
    And  I verify the "Environmental Fee" label displayed for item "<ItemCodeA>"
    And  I verify the "Disposal Fee" label displayed for item "<ItemCodeA>"
    And  I verify the Installation price for item "<ItemCodeA>"
    When I select show fee details option for item "<ItemCodeB>"
    Then I verify the "Installation" label displayed for item "<ItemCodeB>"
    And  I verify the Installation price for item "<ItemCodeB>"

    Examples:
      | ProductType               | ItemCodeA | ItemCodeB |
      | Shop for All-Season Tires | 18012     | 50940     |

  @dt
  @at
  @web
  @12290
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Verify Installation Fee when a vehicle is added with tires and wheels of different quantity Regular Vehicle (ALM #12290)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "Continue Shopping"
    Then I verify the PLP header message contains "tire results"
    When I open the My Vehicles popup
    And  I select current Vehicle
    Then I should see the fitment panel page with fitment options
    When I select the "WHEELS" menu option
    And  I select a fitment option "All wheels this size"
    Then I verify the PLP header message contains "wheel results"
    When I add item "<ItemCodeB>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    When I update quantity for item "<ItemCodeB>" to "<QtyItemCodeB>"
    And  I select show fee details option for item "<ItemCodeB>"
    Then I verify the "Installation" quantity for item "<ItemCodeB>" display "<InstallQuantityB>"
    And  I verify the Installation price for item "<ItemCodeB>"
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the "Installation" quantity for item "<ItemCodeA>" display "<InstallQuantityA>"
    And  I verify the Installation price for item "<ItemCodeA>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCodeA | ItemCodeB | QtyItemCodeB | InstallQuantityA | InstallQuantityB |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 18012     | 75909     | 6            | 4                | 2                |

  @dtd
  @web
  @12410
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Validate Verbiage of calculating taxes and fees in Order summary Section_DTD (ALM #12410)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    And  I verify the cart summary verbiages are displayed

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption | ItemCode |
      | 2012 | Honda     | CIVIC    | COUPE DX | none     | All tires     | 34302    |
      | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets | 30018    |

  @dt
  @at
  @dtd
  @web
  @11772
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Validate Qty for  Certificates in Minicart (ALM #11772)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCodeA>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    When I select the optional "Certificates" fee for item
    Then I verify the Certificate fee amount on the shopping cart page
    When I select mini cart
    Then I verify the RRA Certificate "Quantity" on MiniCart page

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption | ItemCodeA |
      | 2012 | Honda     | CIVIC    | COUPE DX | none     | All tires     | 34302     |
      | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets | 30018     |

  @dt
  @web
  @11251
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Validate Prices and Taxes for Tire and Wheel Products for Regular Vehicle_DT (ALM #11251)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "Continue Shopping"
    Then I verify the PLP header message contains "tire results"
    When I open the My Vehicles popup
    And  I select current Vehicle
    Then I should see the fitment panel page with fitment options
    When I select the "WHEELS" menu option
    And  I select a fitment option "All wheels this size"
    Then I verify the PLP header message contains "wheel results"
    When I add item "<ItemCodeB>" of type "none" to my cart and "View shopping cart"
    Then I verify the added products and prices displayed on cart page
    And  I verify the items total price on cart page
    When I extract the product price from "Cart" page
    And  I select show fee details option for item "<ItemCodeB>"
    Then I verify the "Wheel Install Kit" price for wheel item "<ItemCodeB>"
    And  I verify the "Hub Centric Ring" price for wheel item "<ItemCodeB>"
    And  I verify the item subtotal for item "<ItemCodeB>"
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the Installation price for item "<ItemCodeA>"
    And  I verify the "Environmental Fee" price for item "<ItemCodeA>"
    And  I verify the "Disposal Fee" price for item "<ItemCodeA>"
    And  I verify the item subtotal for item "<ItemCodeA>"
    And  I verify the optional "Certificates" fee is displayed
    And  I verify the cart subtotal for tire and wheel items
    And  I verify the total tax for tire and wheel items
    And  I verify the Total price on the cart summary page
    When I select the checkout option "<Checkout>"
    Then I verify "install with appointment" option is enabled on the Checkout page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCodeA | ItemCodeB | Checkout |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 18012     | 75908     | default  |

  @dt
  @web
  @11158
  @regression
  @cartRegression
  Scenario Outline: SHOPPINGCART_Validate Prices in the Mini Cart Modal in different pages without adding a Vehicle_DT (ALM #11158)
    When I do a free text search for "<ItemCode>" and hit enter
    Then I should see product detail page with "<ProductName>"
    And  I verify Mini Cart quick total "before" adding product
    When I add item to my cart and "Close Added To Cart popup"
    And  I extract the mini cart quick total
    Then I should see product detail page with "<ProductName>"
    And  I verify Mini Cart quick total "after" adding product
    When I select mini cart
    Then I verify the added product "<ProductName>" is displayed in Mini Cart
    And  I verify the "View cart" is displayed in Mini Cart
    And  I verify the "Continue Shopping" is displayed in Mini Cart
    When I select View Cart on Mini Cart
    Then I see "<ItemCode>" on the cart page
    And  I verify Mini Cart quick total "after" adding product
    When I extract the product price from "Cart" page
    And  I select mini cart
    Then I verify the "Installation" price in Mini Cart for product "<ItemCode>"
    And  I verify the "Environmental Fee" price in Mini Cart for product "<ItemCode>"
    And  I verify the "Disposal Fee" price in Mini Cart for product "<ItemCode>"
    And  I verify Mini Cart item total for product "<ProductName>" with item code "<ItemCode>"
    And  I verify Mini Cart total for product "<ProductName>"

    Examples:
      | ProductName  | ItemCode |
      | Defender A/S | 34302    |

  @dtd
  @web
  @11127
  @regression
  @cartRegression
  Scenario Outline: SHOPPING CART_Validate Prices in the Mini Cart Modal in different pages without adding a Vehicle_DTD (ALM #11127)
  """TODO Failing due to defect #9312"""
    When I do a free text search for "<ItemCode>" and hit enter
    Then I should see product detail page with "<ProductName>"
    And  I verify Mini Cart quick total "before" adding product
    When I add item to my cart and "Close Added To Cart popup"
    Then I should see product detail page with "<ProductName>"
    When I extract the mini cart quick total
    Then I verify Mini Cart quick total "after" adding product
    When I select mini cart
    Then I verify the added product "<ProductName>" is displayed in Mini Cart
    And  I verify the "View cart" is displayed in Mini Cart
    And  I verify the "Continue Shopping" is displayed in Mini Cart
    When I select View Cart on Mini Cart
    Then I see "<ItemCode>" on the cart page
    And  I verify Mini Cart quick total "after" adding product
    When I extract the product price from "Cart" page
    And  I select mini cart
    Then I verify the "Environmental Fee" price in Mini Cart for product "<ItemCode>"
    And  I verify Mini Cart item total for product "<ProductName>" with item code "<ItemCode>"
    And  I verify Mini Cart total for product "<ProductName>"
    When I select the checkout option "<Checkout>"
    Then I verify that Mini Cart is not displayed on "Shipping Details" page
    When I enter shipping info as "<Customer>"
    And  I select the default shipping option as "<Customer>"
    Then I verify that Mini Cart is not displayed on "Shipping Method" page
    When I enter payment info with "<Credit Card>" and confirm Checkout Summary as "<Customer>"
    Then I verify that Mini Cart is not displayed on "Payment" page
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page
    And  I verify that Mini Cart is not displayed on "Order Confirmation" page

    Examples:
      | ProductName  | ItemCode | Checkout | Customer            | Credit Card |
      | Defender A/S | 34302    | default  | default_customer_az | Visa        |

  @dt
  @at
  @web
  @9408
  @regression
  @cartRegression
  Scenario Outline: SHOPPING CART_PERSISTENT CART MODAL_When store is changed with product in the Cart (ALM #9408)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I verify checkout now is enabled
    When I search for stores within "25" miles of non default zip code
    And  I select non default store as my store
    Then I verify switch store popup message is displayed
    And  I verify the switch store options are displayed

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCodeA |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 34302     |

  @dt
  @at
  @dtd
  @web
  @5874
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart_Validate Mini Cart Quick Total on PLP, Cart and Home Page (ALM #5874)
  """TODO Failing due to defect #9346"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I do a free text search for "<ItemCode>" and hit enter
    Then I should see product detail page with "<ProductName>"
    And  I verify Mini Cart quick total "before" adding product
    When I add item to my cart and "View shopping cart"
    And  I extract the mini cart quick total
    Then I see "<ItemCode>" on the cart page
    When I navigate back to previous page
    Then I should see product detail page with "<ProductName>"
    And  I verify Mini Cart quick total "after" adding product
    When I select mini cart
    And  I select View Cart on Mini Cart
    Then I see "<ItemCode>" on the cart page
    And  I verify Mini Cart quick total "after" adding product
    When I click the discount tire logo
    Then I am brought to the homepage
    And  I verify Mini Cart quick total "after" adding product

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | ProductName  |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 34302    | Defender A/S |

  @dt
  @at
  @dtd
  @web
  @6839
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Change Vehicle Fitment and select clear my cart and continue (ALM #6839)
    When I do a vehicle search with details "<Year1>" "<Make1>" "<Model1>" "<Trim1>" "<Assembly1>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I open the My Vehicles popup
    And  I select Add Vehicle
    And  I do a vehicle search with details "<Year2>" "<Make2>" "<Model2>" "<Trim2>" "<Assembly2>"
    Then I verify switch vehicle popup message is displayed
    And  I verify the options in the switch vehicle popup
    When I select "Clear my cart and Continue" in the Switching vehicle popup
    Then I should see the fitment panel page with fitment options
    When I close the fitment popup
    Then I verify that My Vehicles displays "<Make2> <Model2>" in the header
    And  I should see product has been "removed" in cart message
    When I open the My Vehicles popup
    Then I verify recent vehicle display "<Year1> <Make1> <Model1>"

    Examples:
      | Year1 | Make1 | Model1 | Trim1    | Assembly1 | FitmentOption | ItemCode | Year2 | Make2     | Model2   | Trim2 | Assembly2 |
      | 2012  | Honda | CIVIC  | COUPE DX | none      | All tires     | 34302    | 2010  | Chevrolet | Corvette | Base  | none      |

  @dt
  @at
  @dtd
  @web
  @8611
  @regression
  @cartRegression
  Scenario Outline: ShoppingCart _Verify Installation fee when a vehicle is added with matching tires and wheels (ALM #8611)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCodeA>" of type "none" to my cart and "Continue Shopping"
    Then I verify the PLP header message contains "tire results"
    When I open the My Vehicles popup
    And  I select current Vehicle
    Then I should see the fitment panel page with fitment options
    When I select the "WHEELS" menu option
    And  I select a fitment option "All wheels this size"
    Then I verify the PLP header message contains "wheel results"
    When I add item "<ItemCodeB>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the applicable fees are displayed on cart page for item "<ItemCodeA>"
    When I select show fee details option for item "<ItemCodeB>"
    Then I verify the applicable fees are displayed on cart page for item "<ItemCodeB>"
    When I open the My Vehicles popup
    And  I select Add Vehicle
    Then I navigate to Shop by Size
    When I select "185" from the "Tire Width" dropdown
    And  I select "70" from the "Aspect Ratio" dropdown
    And  I select "14" from the "Tire Diameter" dropdown
    And  I click on the "Find Tires" button
    Then I verify the PLP header message contains "Shop 185/70R14 Tires"
    And  I verify the "PLP" results banner message contains "185/70-14"
    And  I should see no vehicle
    When I select mini cart
    And  I select View Cart on Mini Cart
    Then I see "<ItemCodeA>" on the cart page
    And  I see "<ItemCodeB>" on the cart page
    When I select show fee details option for item "<ItemCodeA>"
    Then I verify the applicable fees are displayed on cart page for item "<ItemCodeA>"
    When I select show fee details option for item "<ItemCodeB>"
    Then I verify the applicable fees are displayed on cart page for item "<ItemCodeB>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCodeA | ItemCodeB |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 18012     | 75909     |

  @dt
  @at
  @dtd
  @web
  @15615
  @regression
  @cartRegression
  Scenario Outline: HYBRIS-PRICING-MAP-Verify MAP price of product appears in mini cart (ALM #15615)
    When I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "View All" from the Product Brand page
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select mini cart
    Then I verify the "<Product>" price in Mini Cart for product "<ItemCode>"
    And  I verify Mini Cart total for product "<Product>"

    Examples:
      | Product          | ItemCode |
      | Energy Saver A/S | 34152    |