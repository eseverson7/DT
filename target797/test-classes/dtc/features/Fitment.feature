@dt
@at
@fitment
Feature: Fitment

  Background:
    Given I change to the default store

  @9622
  @9743
  @wheelDiameterNoDecimal
  Scenario Outline: Verify the Wheel Diameter dropdown displays with no decimal places (ALM #9743, 9622)
    When I open the fitment popup
    Then I verify the My Vehicle "Wheel Size Search" "Diameter" dropdown has no decimals
    When I close the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "All tire sets"
    Then I verify the word "All" does not appear in the tire set tab titles

    Examples:
      | Year | Make      | Model    | Trim | Assembly |
      | 2010 | Chevrolet | Corvette | Base | none     |

  @9630
  @at
  @dt
  @dtd
  @web
  @mobile
  @regression
  @fitmentRegression
  Scenario Outline: Verify Green Banner on PDP page for Wheels (ALM #9630)
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select the "WHEELS" menu option
    And  I select a fitment option "<WheelAttribute>"
    Then I verify the message on the "Wheels" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PLP" banner color is "Green"
    When I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    And  I select the first product result image
    Then I verify the message on the "Wheels" "PDP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PDP" banner color is "Green"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | WheelAttribute |
      | 2012 | Honda | Civic | Coupe EX | none     | 17             |

  @9628
  @5362
  @web
  @dtd
  @dt
  @regression
  @matchRegression
  @fitmentRegression
  Scenario Outline: Verify Tire fitment search for plus size (ALM #9628)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify the fitment box option has a value of "<SizeOption>"
    When I select the "<SizeOption>" fitment box option
    Then I verify the selected fitment box option has a value of "<SizeOption>"
    When I select a fitment option "<TireSize>"
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PLP" banner color is "Green"
    When I select the first product result image
    Then I verify the message on the "Tires" "PDP" banner contains "<TireSize>" "<Year>" "<Make>" and "<Model>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | SizeOption | TireSize  |
      | 2012 | Honda | Civic | Coupe EX | none     | 15         | 215/60-15 |
      | 2012 | Honda | Civic | Coupe EX | none     | 17         | 215/45-17 |

  @9628
  @mobile
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Mobile - HYBRIS_FITMENT_FITMENT_Tire Error Bar_When Plus Size On Product Detail (ALM #9628)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the fitment box option has a value of "<SizeOption>"
    When I select the "<SizeOption>" fitment box option
    Then I verify the selected fitment box option has a value of "<SizeOption>"
    When I select a fitment option "<TireSize>"
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PLP" banner color is "Green"
    When I select the first product result image
    Then I verify the message on the "Tires" "PDP" banner contains "<TireSize>" "<Year>" "<Make>" and "<Model>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | SizeOption | TireSize  |
      | 2012 | Honda | Civic | Coupe EX | none     | Optional tire sizes | 15         | 205/65-15 |
      | 2012 | Honda | Civic | Coupe DX | none     | Optional tire sizes | 16         | 205/55-16 |

  @5358
  @myVehicleTireSizeMatchesProductResults
  @web
  Scenario Outline: My Vehicle "All tires" option shows results matching vehicle size (ALM #5358)
    When I search for store within "25" miles of "Scottsdale"
    And  I select make this "AZP 29" my store
    And  I go to the homepage
    And  I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the initial page displays products that match my tire size(s): "<Assembly>"

    Examples:
      | Year | Make   | Model   | Trim       | Assembly                            | FitmentOption |
      | 2012 | Ford   | Mustang | GT         | 235 /50 R18 SL                      | All tires     |
      | 2010 | Nissan | 370Z    | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL | All tire sets |

  @9882
  @searchWheelsFitmentShopyByBrandList
  @web
  Scenario Outline: Search Wheels Fitment Shop By Brand List (ALM #9882)
    When I open the "WHEELS" navigation link
    And  I click the "WHEELS BY BRAND" menu option
    Then I am brought to the page with header "<Header>"
    When I navigate to Shop by Brand
    And  I click the fitment "Wheels" radio button
    Then I verify that the "Wheels" radio button is "enabled"
    When I type "DEAN" in the "brand" dropdown
    Then I verify that the Search button is disabled

    Examples:
      | Header     |
      | All Brands |

  @9607
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: PLP Banner should display Wheel Size when searching for Wheels by Size (ALM #9607)
    When I do a "homepage" wheel size search with details "<Diameter>" "<WheelWidth>" "<BoltPattern>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify the "PLP" banner color is "Yellow"
    And  I verify the "PLP" results banner message contains "<WheelSize>"

    Examples:
      | Diameter | WheelWidth | BoltPattern       | Header        | WheelSize               |
      | 15       | 6.5        | 5-114.3 MM/5-4.5" | wheel results | 15X6.5 5-114.3 mm/5-4.5 |

  @9695
  @9877
  @dtd
  @web
  @mobile
  @bba
  @fitmentBBA
  Scenario Outline: Search by Vehicle using the Homepage menu (ALM #8864, 8867, 8868)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I navigate back to previous page
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I go to the homepage
    And  I do a vehicle search with details "<NewYear>" "<NewMake>" "<NewModel>" "<NewTrim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<NewYear> <NewMake>"
    When I select a fitment option "<NewFitmentOption>"
    Then I verify the product list page is displayed with sets
    When I navigate back to previous page
    Then I should see the fitment panel page with vehicle "<NewYear> <NewMake>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | NewYear | NewMake   | NewModel | NewTrim | NewFitmentOption |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 2010    | Chevrolet | Corvette | Base    | All tire sets    |

  @9398
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Clearing Fitment while on PLP (ALM #9398)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I remove my current vehicle
    Then I should see no vehicle

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Header       |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires this size | tire results |

  @9326
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: PLP displays current vehicle in the My Vehicle section in the header (ALM #9326)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify that My Vehicles displays "<Make> <Model>" in the header
    When I open the My Vehicles popup
    Then I verify that My Vehicles displays "<Year> <Make> <Model>" as the current vehicle
    And  I verify My Vehicles popup displays add vehicle
    When I select Add Vehicle
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model1>" "<Trim1>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I close the fitment popup
    Then I verify that My Vehicles displays "<Make> <Model1>" in the header

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Model1 | Trim1    | FitmentOption       | Header       |
      | 2012 | Honda | Civic | Coupe EX | none     | Accord | EX Coupe | All tires this size | tire results |

  @11327
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: No Results Page Message (ALM #11327)
    When I browse to the "<Page>" page with defaults
    Then I verify the default selection in the fitment component
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify no results page displays

    Examples:
      | Year | Make   | Model  | Trim     | Assembly | FitmentOption       | Page     |
      | 2007 | Toyota | Tacoma | 4CYL 2WD | none     | All tires this size | Homepage |

  @10691
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Validate Wheels Bolt Pattern facet displays without Fitment (ALM #10691)
    When I do a free text search for "<Text>" and hit enter
    Then I verify the PLP header message contains "<Text>"
    And  I verify the "Bolt Pattern" facet is displayed when no vehicle is selected
    When I expand the "<Facet>" filter section
    And  I select the "<Refinement>" checkbox
    And  I expand the "<Facet>" filter section
    Then I verify the "<Refinement>" checkbox has been checked
    When I select the "<Refinement>" filter to uncheck the checkbox
    And  I expand the "<Facet>" filter section
    Then I verify the "<Refinement>" filter checkbox to be deselected
    When I click the discount tire logo
    Then I am brought to the homepage
    When I navigate to Shop by Brand
    And  I click the fitment "Wheels" radio button
    And  I select "<Brand>" and find products
    And  I select "View All" from the Product Brand page
    Then I can see "<Brand>" Brand PLP page
    And  I verify the "Bolt Pattern" facet is displayed when no vehicle is selected
    When I click the discount tire logo
    Then I am brought to the homepage
    When I open the "WHEELS" navigation link
    And  I click the "Wheel Style" View All link in the header
    Then I am brought to the page with header "Wheels"
    When I select "Shop for Chrome" from the Product Brand page
    Then I am brought to the page with path "<Path>"
    And  I verify the "Bolt Pattern" facet is displayed when no vehicle is selected
    When I click the discount tire logo
    Then I am brought to the homepage
    When I do a free text search for "<ProductCode>" and hit enter
    Then I verify the PLP header message contains "<Header> for "<ProductCode>""
    And  I verify the "Bolt Pattern" facet is displayed when no vehicle is selected

    Examples:
      | Text   | Facet        | Refinement | Brand       | Path                   | Header  | ProductCode |
      | Wheels | Bolt Pattern | 4-100.0    | DRAG WHEELS | /wheels/chrome-catalog | Results | 76607       |

  @10684
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Validate Wheels Bolt Pattern facet does not display with Fitment (ALM #10684)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify that My Vehicles displays "<Make> <Model>" in the header
    And  I verify bolt pattern facet is not displayed when vehicle is selected
    When I click the discount tire logo
    Then I am brought to the homepage
    When I navigate to Shop by Size
    And  I click the fitment "Wheels" radio button
    And  I do a "my vehicles" wheel size search with details "<Diameter>" "<WheelWidth>" "<BoltPattern>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify bolt pattern facet is not displayed when vehicle is selected

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption        | Header        | Diameter | WheelWidth | BoltPattern |
      | 2012 | Honda | Civic | Coupe EX | none     | All wheels this size | wheel results | 15       | 6.5        | 5-100.0 MM  |

  @10117
  @web
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Brand Search Displays No Results Page And Incorrect Error Message DT (ALM #10117)
    When I navigate to Shop By "Brand"
    And  I select fitment by brand for "Wheels"
    And  I select "<Brand>" and find products
    Then I am brought to the page with header "<Brand>"
    When I select "<SubCategory1>" to shop for wheels
    Then I can see "<Brand>" Brand PLP page
    When I navigate back to previous page
    Then I am brought to the page with header "<Brand>"
    When I open the "WHEELS" navigation link
    And  I click the "Wheel Brand" View All link in the header
    Then I am brought to the page with header "All Brands"
    When I select the "<Brand Image>" tire brand image
    Then I am brought to the page with header "<Brand Image>"
    When I select "<SubCategory2>" to shop for wheels
    Then I can see "<Brand Image>" Brand PLP page

    Examples:
      | Brand     | SubCategory1    | Brand Image     | SubCategory2     |
      | MB WHEELS | Shop for Chrome | AMERICAN OUTLAW | Shop for Painted |

  @11572
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Validate Tire Brand Category On PLP (ALM #11572)
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    Then I am brought to the page with header "All Brands"
    When I select the "<Brand Image>" tire brand image
    Then I am brought to the page with header "<Brand Image>"
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify the product list page is displayed with sets
    And  I verify expected brand "<Brand>" products displayed on PLP

    Examples:
      | Brand Image    | Year | Make      | Model    | Trim | Assembly | Brand    |
      | MICHELIN TIRES | 2010 | Chevrolet | Corvette | Base | none     | Michelin |

  @12000
  @web
  @dt
  @at
  @regression
  @fitmentRegression
  Scenario Outline: Validate Check Availability Link for Staggered Vehicle Optional Tire Sizes (ALM #12000)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<SizeOption>"
    Then I verify the PLP header message contains "<Header>"
    When I select the "Check Inventory" link for item "<ItemCode>"
    Then I should verify that the Check Availability popup loaded
    And  I should verify that default store MY STORE label is at top and visible
    And  I should verify that make my store button is displayed

    Examples:
      | Year | Make      | Model    | Trim | Assembly | Header | SizeOption | ItemCode |
      | 2010 | Chevrolet | Corvette | Base | none     | none   | 17         | 43437    |

  @9489
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario: The brand name should appear in alphabetical order in the dropdown (ALM #9489)
  """TODO Failing from below step due to defect # 9439"""
    When I navigate to Shop By "Brand"
    Then I verify that the "Tires" radio button is "enabled"
    When I click the "Brand" dropdown
    Then I verify that the items in the drop down display in alphabetically order
    When I select fitment by brand for "Wheels"
    And  I click the "Brand" dropdown
    Then I verify that the items in the drop down display in alphabetically order

  @11578
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Validate OE PLP Message (ALM #11578)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I select the "<QuickFilter>" checkbox
    Then I verify the "<QuickFilter>" checkbox has been checked
    And  I verify that OE Tire displays the Original Equipment Tire message

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Header       | QuickFilter        |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires this size | tire results | Original Equipment |

  @12335
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Validate the default quantity for Staggered Tires on PLP (ALM #12335)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify tire quantity is "<Quantity>" on product list page
    When I navigate back to previous page
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "<SizeOption>" fitment box option
    Then I verify the product list page is displayed with sets
    And  I verify tire quantity is "<Quantity>" on product list page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption | Quantity | SizeOption |
      | 2010 | Chevrolet | Corvette | Base | none     | All tire sets | 2        | 19         |

  @12436
  @web
  @mobile
  @dtd
  @dt
  @at
  @regression
  @fitmentRegression
  Scenario Outline: OE Tires Quick Filter Facet On PLP_Standard Vehicle (ALM #12436)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    And  I verify the "<OE Filter>" checkbox to be deselected by default
    When I select the "<OE Filter>" checkbox
    Then I verify Original Equipment tire is displayed on plp page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | OE Filter          |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | Original Equipment |

  @15327
  @web
  @dtd
  @dt
  @at
  @regression
  @fitmentRegression
  Scenario Outline: OE Tires Quick Filter Facet On PLP_Staggered Vehicle (ALM #15327)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    When I select "FRONT" staggered tab on PLP result page
    Then I verify the "<OE Filter>" checkbox to be deselected by default
    When I select the "<OE Filter>" checkbox
    Then I verify Original Equipment tire is displayed on plp page
    When I select "REAR" staggered tab on PLP result page
    Then I verify the "<OE Filter>" checkbox to be deselected by default
    When I select the "<OE Filter>" checkbox
    Then I verify Original Equipment tire is displayed on plp page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption | OE Filter          |
      | 2010 | Chevrolet | Corvette | Base | none     | All tire sets | Original Equipment |

  @12336
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Validate the default quantity for Staggered Wheel Sizes and Check Inventory PLP (ALM #12336)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select the "<SizeOption>" fitment box option
    Then I verify the PLP header message contains "<Header>"
    And  I verify tire quantity is "<Quantity>" on product list page
    And  I verify the "<BreadCrumbs>" link in the breadcrumb container
    When I select the "Check Inventory" link for item "<ItemCode>"
    Then I should verify that the Check Availability popup loaded
    And  I should verify that default store MY STORE label is at top and visible
    And  I should verify that make my store button is displayed

    Examples:
      | Year | Make      | Model    | Trim | Assembly | Header        | Quantity | SizeOption | BreadCrumbs               | ItemCode |
      | 2010 | Chevrolet | Corvette | Base | none     | wheel results | 2        | 17         | Home, Search Results Page | 73617    |

  @9949
  @web
  @dt
  @at
  @dtd
  @regression
  @fitmentRegression
  Scenario Outline: Compare Products (ALM #9949)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    When I select the first "3" results to compare
    And  I click the compare products Compare button
    Then I verify all categories are present for the "3" products
    When I remove the first item on the compare product page
    And  I click the X next to the first product on the compare product page
    Then I verify the PLP header message contains "<Header>"
    When I select the first "3" results to compare
    And  I click the compare products Compare button
    And  I click Remove All
    Then I verify the PLP header message contains "<Header>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Header       |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires this size | tire results |

  @dt
  @at
  @dtd
  @web
  @15556
  @regression
  @fitmentRegression
  Scenario Outline: HYBRIS_FITMENT_FITMENT_Vehicle with staggered and standard options show correctly on PLP (ALM #15556)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly1>"
    And  I select a fitment option "All tires"
    Then I verify the PLP header message contains "tire results"
    When I open the My Vehicles popup
    And  I select Add Vehicle
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly2>"
    And  I select a fitment option "All tire sets"
    Then I verify the product list page is displayed with sets

    Examples:
      | Year | Make | Model | Trim            | Assembly1      | Assembly2                           |
      | 2013 | BMW  | 335i  | Convertible 2WD | 225 /45 R17 SL | F 225 /35 R19 XL - R 255 /30 R19 XL |

  @dt
  @at
  @dtd
  @web
  @mobile
  @13120
  Scenario Outline: HYBRIS_FITMENT_FTMENT_Validate Vehicle Description is displaying above the product in the cart with an already selected vehicle (ALM #13120)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    And  I verify the vehicle "<Year> <Make> <Model> <Trim>" is displayed on shopping cart page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | ItemCode | ProductName  |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires this size | 34302    | Defender A/S |

  @dt
  @at
  @web
  @15692
  Scenario Outline: Check Availability Modal_Add to cart button (ALM #15692)
    When I search for store within "10" miles of "85255"
    Then I select make this "<DisplayName>" my store
    When I go to the homepage
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify Check Inventory link for "<ItemCodeA>"
    When I select the "Check Inventory" link for item "<ItemCodeA>"
    Then I should verify that the Check Availability popup loaded
    And  I verify the Add To Cart button is clickable and Red on "Check Availability" page
    When I add item to my cart from "Check Availability"
    And  I click view shopping cart button
    Then I should see product "<ItemCodeA>" on the cart page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | DisplayName | ItemCodeA |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires this size | AZP 29      | 29935     |