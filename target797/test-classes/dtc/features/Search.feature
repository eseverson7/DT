@search
Feature: Search

  Background:
    Given I change to the default store

  @at
  @dt
  @bba
  @dtd
  @8864
  @8867
  @8868
  @8790
  @8789
  @8782
  @web
  @mobile
  @searchBBA
  Scenario Outline: Search by Vehicle using the Homepage menu(ALM #8864, 8867, 8868, 8790, 8789, 8782)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "<Product Type>" menu option
    And  I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"

    Examples:
      | Year | Make  | Model  | Trim         | Assembly       | Product Type | FitmentOption | Header        |
      | 2015 | Honda | Accord | Coupe EX     | none           | WHEELS       | All wheels    | wheel results |
      | 2012 | Honda | Civic  | Coupe DX     | none           | TIRES        | All tires     | tire results  |
      | 2012 | Honda | Civic  | Coupe DX     | none           | WHEELS       | All wheels    | wheel results |
      | 2016 | Ram   | 2500   | Mega Cab 2WD | 275 /70 R18 E1 | WHEELS       | All wheels    | wheel results |
      | 2016 | Ram   | 2500   | Mega Cab 2WD | 275 /70 R18 E1 | TIRES        | All tires     | tire results  |
      | 2016 | Ram   | 3500   | 2WD Crew Cab | 275 /70 R18 E1 | WHEELS       | All wheels    | wheel results |
      | 2016 | Ram   | 3500   | Dually 2WD   | none           | TIRES        | All tires     | tire results  |


  @at
  @dt
  @dtd
  @8491
  @bba
  @8916
  @web
  @mobile
  @searchBBA
  Scenario Outline: Search by Tire Size using the Homepage menu(ALM #8916, 8491)
    When I do a "homepage" tire size search with details "<Width>" "<Ratio>" "<Diameter>"
    Then I verify the PLP header message contains "<Header>"
    And  I am brought to the page with path "/fitmentresult/tires/size/205-55-16"
    And  The search results show tires/wheels with the specified measurements "<Measurements>"

    Examples:
      | Width | Ratio | Diameter | Header               | Measurements |
      | 205   | 55    | 16       | Shop 205/55R16 Tires | 205 /55 R16  |

  @at
  @dt
  @8916
  @searchByTireSizeViaMyVehicles
  @web
  Scenario Outline: Search by Tire Size using My Vehicles search(ALM #8916)
    When I open the fitment popup
    And  I do a "my vehicles" tire size search with details "<Width>" "<Ratio>" "<Diameter>"
    Then I verify the PLP header message contains "<Header>"
    And  The search results show tires/wheels with the specified measurements "<Measurements>"

    Examples:
      | Width | Ratio | Diameter | Header               | Measurements |
      | 205   | 55    | 16       | Shop 205/55R16 Tires | 205 /55 R16  |

  @at
  @dt
  @bba
  @8861
  @searchByBrandViaHomepage
  @web
  @mobile
  @searchBBA
  Scenario Outline: Search by Category Brand (ALM #8861)
    When I navigate to Shop by Brand
    And  I select "<Brand>" and find products
    Then I see the "<Brand>" that I selected
    When I select "<SubCategory>" to shop
    Then I can see "<Brand>" Brand PLP page

    Examples:
      | Brand          | SubCategory               |
      | Michelin Tires | Shop for All-Season Tires |

  @at
  @dt
  @bba
  @web
  @dtd
  @8862
  @searchBBA
  Scenario Outline: DTD - Search by Tire Brand (ALM #8862)
    When I navigate to Shop by Brand
    And  I select "<Brand>" and find products
    Then I see the "<Brand>" that I selected
    When I select "<SubCategory>" to shop
    Then I can see "<Brand>" Brand PLP page
    And  I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Brand          | SubCategory               | Breadcrumbs                                     |
      | Goodyear Tires | Shop for All-Season Tires | Home, Tires, By Brand, Goodyear Tires, Products |

  @at
  @dt
  @bba
  @mobile
  @dtd
  @8862
  @searchBBA
  Scenario Outline: DTD Mobile - Search by Tire Brand (ALM #8862)
    When I navigate to Shop by Brand
    And  I select "<Brand>" and find products
    Then I see the "<Brand>" that I selected
    When I select "<SubCategory>" to shop
    Then I can see "<Brand>" Brand PLP page

    Examples:
      | Brand          | SubCategory               |
      | Goodyear Tires | Shop for All-Season Tires |

  @at
  @dt
  @dtd
  @web
  @5414
  @6358
  @6359
  @8849
  @regression
  @searchRegression
  @filterSortingByPrice
  Scenario Outline: Search filter sorting by Price (ALM #5414, 6359, 6358, 8849)
    When I do a free text search for "yok"
    And  I select "<LinkText>" from the autocomplete dropdown of the search box
    Then I verify the PLP header message contains "Results for "yok""
    When I select from the "Tire Category" filter section, "single" option(s): "All-Season"
    And  I select the "<Value>" from the Sort By dropdown box
    Then I verify that the sort by dropdown value is set to "<Value>"
    And  I verify the results list is sorted in "<Order>" order by "price"

    Examples:
      | LinkText                | Value               | Order      |
      | View all search results | Price (High To Low) | Descending |
      | View all search results | Price (Low To High) | Ascending  |

  @at
  @dt
  @dtd
  @web
  @5356
  @regression
  @searchRegression
  @filterSortingClearAll
  Scenario Outline: Search filter sorting with Clear All (ALM #5356)
    When I do a free text search for "yok" and hit enter
    And  I select the "Price (High To Low)" from the Sort By dropdown box
    And  I select from the "Brands" filter section, "single" option(s): "Yokohama"
    And  I set the "Price Range" slider filter to the range: $"<Minimum Price>" - "<Maximum Price>"
    Then I verify that the search refinement filters contain the "multiple" value(s): "Yokohama, $<Minimum Price> - $<Maximum Price>"
    When I clear all the currently active filters on the PLP page
    Then I verify no search refinement filters are being applied

    Examples:
      | Minimum Price | Maximum Price |
      | 90            | 175           |

  @at
  @dt
  @dtd
  @5356
  @mobile
  @regression
  @searchRegression
  @filterSortingClearAll
  Scenario Outline: Search filter sorting with Clear All - Mobile (ALM #5356)
    When I do a free text search for "yok"
    And  I select the "Price (High To Low)" from the Sort By dropdown box
    And  I select from the "Brands" filter section, "multiple" option(s): "American Outlaw, BFGoodrich"
    And  I set the "Price Range" slider filter to the range: $"<Minimum Price>" - "<Maximum Price>"
    Then I verify that the search refinement filters contain the "multiple" value(s): "American Outlaw, BFGoodrich, $<Minimum Price> - $<Maximum Price>"
    When I clear all the currently active filters on the PLP page
    Then I verify no search refinement filters are being applied

    Examples:
      | Minimum Price | Maximum Price |
      | 90            | 175           |

  @at
  @dt
  @9929
  @searchTireFiltering
  @web
  Scenario Outline: Search Tire Filtering Brand Filtering Refinements Validation(ALM #9929)
    When I go to the homepage
    And  I open the "TIRES" navigation link
    And  I click the "All-Season Tires" menu option
    And  I do a "my vehicles" tire size search with details "<Width>" "<Ratio>" "<Diameter>"
    Then The search results show tires/wheels with the specified measurements "<Measurements>"
    And  I can see selected "<Refinement>" under refinements section on PLP page
    When I expand the "<Facet>" filter section
    And  I select the "<Refinement>" filter to uncheck the checkbox
    And  I expand the "<Facet>" filter section
    Then I verify the "<Refinement>" filter checkbox to be deselected
    And  I verify no search refinement filters are being applied

    Examples:
      | Width | Ratio | Diameter | Measurements | Refinement | Facet         |
      | 195   | 65    | 15       | 195 /65 R15  | All-Season | Tire Category |

  @at
  @dt
  @9776
  @9777
  @mobile
  @vehicleNotMatchTire
  Scenario Outline:  Mobile - Hybirs_Mobile_PLP_UI_Vehicle_Fitment_Not_Match_Tire_TC02 (ALM# 9776)
    When I go to the homepage
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "All tires"
    Then I verify the "PLP" banner color is "Green"
    And  I verify the "PLP" results banner message contains "These items fit your:"
    And  I verify the "PLP" results banner message contains "2015 Honda Accord Coupe EX"
    And  I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify that the sort by dropdown value is set to "Price (Low To High)"
    And  I verify PLP UI "filter / sorting"
    When I select the first "2" results to compare
    And  I verify the Add To Cart button is clickable and Red on "PLP" page
    When I enter "<Quantity>" into the first item quantity text box
    Then I see the Please Enter a Number error message
    And  I verify that each page displays 10 products and total number of pages is equal to total count / 10

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | Quantity |
      | 2015 | Honda | Accord | Coupe EX | none     | 0        |

  @at
  @dt
  @9776
  @mobile
  @vehiclePLPNotMatchTire
  Scenario Outline:  Mobile - Hybirs_Mobile_PLP_UI_Vehicle_Fitment_Not_Match_Tire_TC02 : 2 (ALM# 9776)
  """TODO - Brand selection will not work on QA until Nitto is updated to Nitto Tires"""
    When I go to the homepage
    And  I navigate to Shop by Brand
    And  I select "<Brand>" and find products
    And  I select "<SubCategory>" to shop
    Then I verify the "PLP" banner color is "Yellow"
    And  I verify the "PLP" results banner message contains "We're note sure if these items will fit."
    And  I verify that the sort by dropdown value is set to "Price (Low To High)"
    And  I verify PLP UI "filter / sorting"
    When I select the first "2" results to compare
    And  I verify the Add To Cart button is clickable and Red on "PLP" page
    When I enter "<Quantity>" into the first item quantity text box
    Then I see the Please Enter a Number error message
    And  I verify that each page displays 10 products and total number of pages is equal to total count / 10

    Examples:
      | Brand       | SubCategory               | Quantity |
      | Nitto Tires | Shop for All-Season Tires | 0        |

  @at
  @dt
  @9778
  @mobile
  @wheelPLPVehicleNotSelected
  Scenario Outline: Hybirs_Mobile_PLP_UI_Vehicle_Not_selected_Wheel_TC04 (ALM# 9778)
    When I go to the homepage
    And  I do a free text search for "<ItemCode>"
    Then I verify the "PLP" banner color is "Yellow"
    And  I verify the "PLP" results banner message contains "We're note sure if these items will fit."
    And  I verify PLP UI "filter / sorting"
    And  I verify the Add To Cart button is clickable and Red on "PLP" page
    When I enter "<Quantity>" into the first item quantity text box
    Then I see the Please Enter a Number error message
    And  I verify that each page displays 10 products and total number of pages is equal to total count / 10

    Examples:
      | ItemCode | Quantity |
      | 73745    | 0        |

  @at
  @dt
  @dtd
  @web
  @9066
  @bba
  @searchBBA
  Scenario: HYBRIS_164_Search_Search Tires by Filters and Sorting (ALM #9066)
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    And  I select the "MICHELIN TIRES" tire brand image
    And  I select "Shop for All-Season Tires" from the Product Brand page
    And  I select the "Name (descending)" from the Sort By dropdown box
    Then I verify that the sort by dropdown value is set to "Name (descending)"
    And  I verify the results list is sorted in "Descending" order by "name"


  @at
  @dt
  @bba
  @dtd
  @9066
  @mobile
  @searchBBA
  Scenario: Mobile - HYBRIS_164_Search_Search Tires by Filters and Sorting (ALM #9066)
    When I click the mobile homepage menu
    And  I click on "Tire" menu link
    And  I click on "Tire Brand" menu link
    And  I click on "View all" menu link
    And  I select the "Michelin Tires" tire brand image
    And  I select "Shop for All-Season Tires" from the Product Brand page
    And  I select the "Name (ascending)" from the Sort By dropdown box
    Then I verify that the sort by dropdown value is set to "Name (ascending)"
    And  I verify the results list is sorted in "Ascending" order by "name"

  @dt
  @at
  @web
  @8873
  @storeHourFormatting
  Scenario Outline: HYBRIS_162_Search_Search for Store hours (ALM #8873)
    When I click on "My Store" title
    Then I verify the "STORE HOURS" in the My Store popup
    When I go to the homepage
    And  I search for store within "75" miles of "<Zipcode>"
    Then I verify the "HOURS OF OPERATION" in the store location results
    When I select "Make this my store" for store #"2" in the store location results
    And  I click on Store details button in My Store popup
    Then I verify the "STORE HOURS" for the current store

    Examples:
      | Zipcode |
      | 86001   |
      | 96002   |

  @dt
  @at
  @8873
  @mobile
  @storeHourFormatting
  Scenario Outline: Mobile - HYBRIS_162_Search_Search for Store hours (ALM #8873)
    When I search for store within "75" miles of "<Zipcode>"
    Then I verify the "HOURS OF OPERATION" in the store location results
    When I select "<Store Code>" for store details
    Then I verify the "STORE HOURS" for the current store

    Examples:
      | Zipcode | Store Code |
      | 86001   | AZF 02     |
      | 96002   | CAN 19     |

  @at
  @dt
  @dtd
  @web
  @6609
  @6610
  @6613
  @6614
  @6615
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FILTER SORTING BestSelling (ALM #6609, 6610, 6613, 6614, 6615)
  """TODO - Fails in QA2 for AT/DT due to Defect #9534 (Sort By refreshes page and doesn't sort results)"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "<Product Type>" menu option
    And  I select the "<Product Category>" staggered menu option
    And  I select a fitment option "<Fitment Link>"
    Then I verify the PLP header message contains "<Header>"
    When I select the "Best Seller" from the Sort By dropdown box
    Then I verify that the sort by dropdown value is set to "Best Seller"

    Examples:
      | Year | Make      | Model    | Trim | Assembly | Product Type | Product Category | Fitment Link     | Header  |
      | 2010 | Chevrolet | Corvette | Base | none     | TIRES        | Front Tires      | All front tires  | Results |
      | 2010 | Chevrolet | Corvette | Base | none     | WHEELS       | Front Wheels     | All front wheels | Results |
      | 2010 | Chevrolet | Corvette | Base | none     | TIRES        | Rear Tires       | All rear tires   | Results |
      | 2010 | Chevrolet | Corvette | Base | none     | WHEELS       | Rear Wheels      | All rear wheels  | Results |

  @at
  @dt
  @dtd
  @web
  @6612
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FILTER SORTING BestSelling Best Selling Rear of Tires (ALM #6612)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "<Product Type>" menu option
    And  I select the "<Product Category>" staggered menu option
    And  I select a fitment option "<Fitment Link>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify that the sort by dropdown value is set to "Best Seller"

    Examples:
      | Year | Make      | Model    | Trim | Assembly | Product Type | Product Category | Fitment Link | Header  |
      | 2010 | Chevrolet | Corvette | Base | none     | WHEELS       | Rear Wheels      | Best selling | Results |

  @at
  @dt
  @dtd
  @web
  @6606
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FILTER SORTING BestSelling All Tires (ALM #6606)
  """TODO - Fails in QA2 for AT/DT due to Defect #9534 (Sort By refreshes page and doesn't sort results)"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "<Product Type>" menu option
    And  I select a fitment option "<Fitment Link>"
    Then I verify the PLP header message contains "<Header>"
    When I select the "Best Seller" from the Sort By dropdown box
    Then I verify that the sort by dropdown value is set to "Best Seller"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Product Type | Fitment Link        | Header       |
      | 2012 | Honda | Civic | Coupe DX | none     | TIRES        | All tires this size | tire results |

  @at
  @dt
  @dtd
  @web
  @6360
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FILTER SORTING FilterByInverntory In Stock (ALM #6360)
    When I do a free text search for "<Search Term>" and hit enter
    Then I verify the PLP header message contains "Results for "<Search Term>""
    When I select from the "<Filter Section>" filter section, "multiple" option(s): "<First Filter>, <Second Filter>"
    Then I verify that the search refinement filters contain the "multiple" value(s): "<First Filter>, <Second Filter>"

    Examples:
      | Search Term | Filter Section | First Filter | Second Filter |
      | Mich        | Quick Filters  | Tires        | In Stock      |

  @at
  @dt
  @dtd
  @web
  @5347
  @5642
  @5643
  @5644
  @15448
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FILTER SORTING FilterBySpeedRating Facet Filter on PLP (ALM #5347, 5642, 5643, 5644, 15448)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<Fitment Link>"
    Then I verify the PLP header message contains "Results"
    And  I verify the initial page displays products that match my tire size(s): "<Measurements>"
    When I select from the "<Filter Section>" filter section, "single" option(s): "<Filter Option>"
    Then I verify that the search refinement filters contain the "single" value(s): "<Filter Option>"

    Examples:
      | Year | Make   | Model  | Trim     | Assembly       | Fitment Link        | Measurements | Filter Section       | Filter Option     |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Speed Rating         | T - Up to 118 mph |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Tire Category        | All-Season        |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Mileage Warranty     | 60,000-70,000     |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Sidewall Description | Black Side Wall   |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Good Better Best     | Better            |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Load Range           | SL                |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Section Width        | 195               |
      | 2012 | Honda  | Civic  | Coupe DX | none           | All tires this size | 195 /65 R15  | Aspect Ratio         | 65                |
      | 2015 | Nissan | Altima | Sedan    | 215 /55 R17 SL | All tires this size | 215 /55 R17  | Diameter             | 17                |


  @at
  @dt
  @dtd
  @web
  @9237
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FILTER SORTING Validate Order Filters On PLP (ALM #9237)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "<Product Type>" menu option
    And  I select a fitment option "<Fitment Link>"
    Then I verify the PLP header message contains "Results"
    And  I verify the order of filter categories for "<Product Type>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Fitment Link         | Product Type |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires this size  | Tires        |
      | 2012 | Honda | Civic | Coupe DX | none     | All wheels this size | Wheels       |

  @at
  @dt
  @dtd
  @web
  @5349
  @regression
  @searchRegression
  Scenario: HYBRIS_SEARCH_SEARCH_FILTER SORTING WHEEL FilterByPromotional Offer (ALM #5349)
  """TODO - Fails in QA2 for all regions as no products are currently 'On Promotion'"""
    When I open the "WHEELS" navigation link
    And  I click the "Passenger Wheels" menu option
    And  I select "View All" from the Product Brand page
    And  I select from the "Quick Filters" filter section, "single" option(s): "On Promotion"
    Then I verify that the search refinement filters contain the "single" value(s): "On Promotion"
    And  I verify the displayed product(s) is/are on promotion

  @at
  @dt
  @dtd
  @web
  @5645
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FILTER SORTING Wheel FilterByWheelColor Facet Filter on PLP (ALM #5645)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "<Product Type>" menu option
    And  I select a fitment option "<Fitment Link>"
    Then I verify the PLP header message contains "wheel results"
    When I select from the "<Filter Section>" filter section, "single" option(s): "<Filter Option>"
    Then I verify that the search refinement filters contain the "single" value(s): "<Filter Option>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Product Type | Fitment Link         | Filter Section | Filter Option |
      | 2012 | Honda | Civic | Coupe DX | none     | WHEELS       | All wheels this size | Wheel Color    | Black         |

  @at
  @dt
  @dtd
  @web
  @9430
  @regression
  @searchRegression
  Scenario Outline: HYBRIS_SEARCH_SEARCH_FREE TEXT SEARCH Item With Special Character (ALM #9430)
    When I do a free text search for "<Search Term>" and hit enter
    Then I verify the PLP header message contains "Results for "<Search Term>""

    Examples:
      | Search Term |
      | Mich%       |
      | Mich&       |

  @at
  @dt
  @dtd
  @web
  @9258
  @regression
  @searchRegression
  Scenario: HYBRIS_SEARCH_SEARCH_Validate By Tire Brand View All Brand Images (ALM #9258)
  """TODO - Fails in QA due to defect #9532"""
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    Then I verify all listed brands have corresponding images

  @at
  @dt
  @web
  @9369
  @regression
  @searchRegression
  Scenario: HYBRIS_SEARCH_SEARCH_Validate Scheduling Appointment From My Store Service Appointment (ALM #9369)
    When I schedule an appointment for my current store
    Then I am brought to the page with header "Service Appointment"

  @at
  @dt
  @web
  @9367
  @regression
  @searchRegression
  Scenario: HYBRIS_SEARCH_SEARCH_Validate Scheduling Appointment From My Store Store Locator (ALM #9367)
    When I click on "My Store" title
    Then I verify the "My Store" popup contains controls: "CHANGE STORE, STORE DETAILS, SCHEDULE APPOINTMENT"
    When I click on Store details button in My Store popup
    And  I click "Schedule appointment" on Store Details page
    Then I am brought to the page with header "Service Appointment"

  @at
  @dt
  @web
  @15491
  Scenario Outline: HYBRIS_SEARCH_SEARCH_ Product Image should be Consistent for stagger vehicles (ALM #15491)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify that all products are displaying the full product image
    When I select "FRONT" staggered tab on PLP result page
    Then I verify that all products are displaying the full product image
    When I select "REAR" staggered tab on PLP result page
    Then I verify that all products are displaying the full product image

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption |
      | 2010 | Chevrolet | Corvette | Base | none     | All tire sets |

  @dt
  @at
  @dtd
  @web
  @15589
  Scenario Outline: HYBRIS_SEARCH_SEARCH_Wheel Configurator_Home Page_Validate (ALM #15589)
    When I click the discount tire logo
    Then I am brought to the homepage
    And  I verify the Wheel Configurator "<Text>" is displayed
    And  I verify the Wheel Configurator image is displayed
    And  I verify 'BROWSE WHEELS' is displayed

    Examples:
      | Text                            |
      | Find your perfect set of wheels |

  @dt
  @at
  @dtd
  @web
  @mobile
  @15686
  Scenario Outline: HYBRIS_SEARCH_SEARCH_Item Code Free Text Search Goes Directly To PDP(ALM #15686)
    When I do a free text search for "<ItemCode>" and hit enter
    Then I should see product detail page with "<ItemName>"
    When I do a free text search for "<Text>" and hit enter
    Then I verify the PLP header message contains "results"

    Examples:
      | ItemCode | ItemName     | Text    |
      | 34299    | Defender A/S | 10006   |
      | 50239    | Alpina       | Spartan |

  @dt
  @at
  @dtd
  @web
  @15719
  Scenario Outline: HYBRIS_SEARCH_Promotion from fitment modal on PLP (ALM #15719)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify that the search refinement filters contain the "single" value(s): "On Promotion"
    And  I verify the displayed product(s) is/are on promotion

    Examples:
      | Year | Make   | Model  | Trim     | Assembly | FitmentOption | Header        |
      | 2012 | HONDA  | CIVIC  | COUPE DX | none     | On promotion  | tire results  |

  @dt
  @at
  @dtd
  @web
  @15720
  Scenario: HYBRIS_Search_Search_Tires by TIRE TYPE (ALM #15720)
    When I open the "TIRES" navigation link
    And  I click the "All-Season Tires" menu option
    And  I select the "MICHELIN TIRES" tire brand image
    Then I verify the PLP header message contains "tire results"

  @dt
  @at
  @dtd
  @web
  @15721
  Scenario: HYBRIS_Search_Search_Tires by Vehicle TYPE (ALM #15721)
    When I open the "TIRES" navigation link
    And  I click the "ATV/UTV Tires" menu option
    And  I select the "CARLISLE" tire brand image
    Then I verify the PLP header message contains "tire results"

  @dt
  @at
  @dtd
  @web
  @15722
  Scenario: HYBRIS_Search_Search_Wheels by Wheel Style (ALM #15722)
    When I open the "WHEELS" navigation link
    And  I click the "Painted Wheels" menu option
    And  I select the "KONIG" tire brand image
    Then I verify the PLP header message contains "wheel results"

  @at
  @dt
  @web
  @15696
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify the inventory messaging rule on PLP with free text search and zero stock at store (ALM #15696)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a free text search for "<Brand>" and hit enter
    And  I select "View All" from the Product Brand page
    Then I see "<ItemCode>" on the product list page
    And  I verify the "<InventoryMessage>" displayed for "<ItemCode>" on "PLP" page
    And  I verify check nearby stores link is "not displayed" for "<ItemCode>" on "PLP" page

    Examples:
      | ZipCode | Store  | Brand       | ItemCode | InventoryMessage                               |
      | 85260   | AZP 29 | Continental | 19185    | Order now, available in 3 - 5 days at My Store |
      | 85260   | AZP 29 | Goodyear    | 12987    | Available today at My Store                    |
      | 86001   | AZF 01 | BFGoodrich  | 29888    | Available today at My Store                    |
      | 91710   | CAL 01 | Pirelli     | 41331    | Available today at My Store                    |
      | 90746   | CAL 07 | Pirelli     | 41331    | Available today at My Store                    |
      | 77022   | TXH 01 | Continental | 10003    | Available today at My Store                    |

  @at
  @dt
  @web
  @15715
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify the inventory messaging rule on PLP with fitment for regular vehicle (ALM #15715)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with results
    And  I see "<ItemCode>" on the product list page
    And  I verify the "<InventoryMessage>" displayed for "<ItemCode>" on "PLP" page
    And  I verify check nearby stores link is "displayed" for "<ItemCode>" on "PLP" page

    Examples:
      | ZipCode | Store  | Year | Make   | Model  | Trim         | Assembly       | FitmentOption | ItemCode | InventoryMessage                          |
      | 85260   | AZP 29 | 2012 | Honda  | Civic  | Coupe DX     | none           | All tires     | 37095    | Order now, available tomorrow at My Store |
      | 85260   | AZP 29 | 2012 | Honda  | Civic  | Coupe DX     | none           | All tires     | 19600    | Available today at My Store               |
      | 86001   | AZF 01 | 2015 | Nissan | Altima | Sedan        | 215 /55 R17 SL | All tires     | 36088    | Available today at My Store               |
      | 91710   | CAL 01 | 2016 | Honda  | Accord | Coupe EX     | none           | All tires     | 35215    | Available today at My Store               |
      | 90746   | CAL 07 | 2016 | Honda  | Accord | Coupe EX     | none           | All tires     | 35215    | Available today at My Store               |
      | 91710   | CAL 01 | 2016 | Ram    | 3500   | Dually 4WD   | none           | All tires     | 35365    | Available today at My Store               |
      | 90746   | CAL 07 | 2016 | Ram    | 3500   | Dually 4WD   | none           | All tires     | 35365    | Available today at My Store               |
      | 77022   | TXH 01 | 2016 | Ram    | 2500   | Mega Cab 4WD | 275 /70 R18 E1 | All tires     | 10204    | Available today at My Store               |

  @at
  @dt
  @web
  @15712
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify the inventory messaging rule on PLP with free text search and stock available at near by store (ALM #15712)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a free text search for "<Brand>" and hit enter
    And  I select "View All" from the Product Brand page
    Then I see "<ItemCode>" on the product list page
    And  I verify the "<InventoryMessage>" displayed for "<ItemCode>" on "PLP" page
    And  I verify check nearby stores link is "displayed" for "<ItemCode>" on "PLP" page

    Examples:
      | ZipCode | Store  | Brand       | ItemCode | InventoryMessage                          |
      | 85260   | AZP 29 | Hankook     | 11032    | Available today at My Store               |
      | 86001   | AZF 01 | Continental | 26533    | Order now, available tomorrow at My Store |
      | 91710   | CAL 01 | Goodyear    | 17989    | Order now, available tomorrow at My Store |
      | 90746   | CAL 07 | Goodyear    | 17989    | Order now, available tomorrow at My Store |
      | 77022   | TXH 01 | BFGoodrich  | 10390    | Available today at My Store               |
      | 77022   | TXH 01 | BFGoodrich  | 10410    | Order now, available tomorrow at My Store |
      | 86001   | AZF 01 | Nitto       | 10209    | Available today at My Store               |

  @at
  @dt
  @web
  @15735
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify the inventory messaging rule on PLP with with fitment for staggered vehicle (ALM #15735)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    And  I see "<ItemCodeA>" on the product list page
    And  I verify the "<InventoryMessageA>" for set displayed for "<ItemCodeA>" on "PLP" page
    And  I verify the "<InventoryMessageB>" for set displayed for "<ItemCodeB>" on "PLP" page
    And  I verify check nearby stores link is "displayed" having set "<ItemCodeA>" on "PLP" page
    And  I verify check nearby stores link is "displayed" having set "<ItemCodeB>" on "PLP" page

    Examples:
      | ZipCode | Store  | Year | Make          | Model     | Trim | Assembly | FitmentOption | ItemCodeA | ItemCodeB | InventoryMessageA                         | InventoryMessageB                          |
      | 85260   | AZP 29 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 86001   | AZF 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 91710   | CAL 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 90746   | CAL 07 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 77022   | TXH 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 77022   | TXH 01 | 2008 | Mercedes-Benz | SLK55 AMG | Base | none     | All tire sets | 17548     | 28695     | Order now, available tomorrow at My Store | Order now, available in 2 days at My Store |

  @at
  @dt
  @web
  @15737
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify the inventory messaging rule on PLP with with fitment for staggered vehicle with stock at mystore (ALM #15737)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    And  I see "<ItemCodeA>" on the product list page
    And  I verify the "<InventoryMessageA>" for set displayed for "<ItemCodeA>" on "PLP" page
    And  I verify the "<InventoryMessageB>" for set displayed for "<ItemCodeB>" on "PLP" page
    And  I verify check nearby stores link is "not displayed" having set "<ItemCodeA>" on "PLP" page
    And  I verify check nearby stores link is "displayed" having set "<ItemCodeB>" on "PLP" page

    Examples:
      | ZipCode | Store  | Year | Make          | Model     | Trim | Assembly | FitmentOption | ItemCodeA | ItemCodeB | InventoryMessageA           | InventoryMessageB                          |
      | 85260   | AZP 29 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 35454     | 35455     | Available today at My Store | Order now, available in 2 days at My Store |
      | 86001   | AZF 01 | 2008 | Mercedes-Benz | SLK55 AMG | Base | none     | All tire sets | 17548     | 28695     | Available today at My Store | Order now, available in 2 days at My Store |
      | 91710   | CAL 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 35454     | 35455     | Available today at My Store | Order now, available in 2 days at My Store |
      | 90746   | CAL 07 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 35454     | 35455     | Available today at My Store | Order now, available in 2 days at My Store |

  @dt
  @at
  @dtd
  @web
  @15718
  Scenario Outline: HYBRIS_SEARCH_SEARCH_Verify Red Banner on PDP page (ALM #15718)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I go to the homepage
    And  I open the "TIRES" navigation link
    And  I click the "Michelin Tires" menu option
    And  I select "<SubCategory>" to shop
    Then I verify the PLP header message contains "<Header>"
    And  I verify the "PLP" banner color is "Yellow"
    And  I verify the "PLP" results banner message contains "<String1>"
    When I select the first product result image
    Then I verify the "PDP" banner color is "Red"
    And  I verify the "PDP" results banner message contains "<String2>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Header        | String1                                | String2                | SubCategory               |
      | 2012 | Honda | Civic | Coupe DX | none     |  tire results | We're not sure if these items will fit | This will not fit your | Shop for All-Season Tires |


  @at
  @dt
  @web
  @15741
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify inventory messaging rule  on PDP with free text search and zero stock at store (ALM #15741)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a free text search for "<ItemCode>" and hit enter
    Then I should see product detail page with "<ItemName>"
    And  I verify the "<InventoryMessage>" displayed for "<ItemCode>" on "PDP" page
    And  I verify check nearby stores link is "not displayed" for "<ItemCode>" on "PDP" page
    And  I verify the stock count message contains "<StockCount> In Stock" on PDP

    Examples:
      | ZipCode | Store  | ItemCode | InventoryMessage                               | ItemName                  | StockCount   |
      | 85260   | AZP 29 | 19185    | Order now, available in 3 - 5 days at My Store | Control Contact Sport A/S | 0            |
      | 85260   | AZP 29 | 12987    | Available today at My Store                    | Assurance Touring         | More than 20 |
      | 86001   | AZF 01 | 29888    | Available today at My Store                    | G-Force Comp 2 A/S        | More than 20 |
      | 91710   | CAL 01 | 41331    | Available today at My Store                    | Scorpion Verde A/S        | More than 20 |
      | 90746   | CAL 07 | 41331    | Available today at My Store                    | Scorpion Verde A/S        | More than 20 |
      | 77022   | TXH 01 | 10003    | Available today at My Store                    | Motivo                    | More than 20 |

  @at
  @dt
  @web
  @15743
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify inventory messaging rule on PDP with fitment for regular vehicle (ALM #15743)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with results
    And  I see "<ItemCode>" on the product list page
    When I click on the product "<ItemCode>"
    Then I should see product detail page with "<ItemName>"
    And  I verify the "<InventoryMessage>" displayed for "<ItemCode>" on "PDP" page
    And  I verify check nearby stores link is "displayed" for "<ItemCode>" on "PDP" page
    And  I verify the stock count message contains "<StockCount> In Stock" on PDP

    Examples:
      | ZipCode | Store  | Year | Make   | Model  | Trim         | Assembly       | FitmentOption | ItemCode | InventoryMessage                          | ItemName                  | StockCount   |
      | 85260   | AZP 29 | 2012 | Honda  | Civic  | Coupe DX     | none           | All tires     | 37095    | Order now, available tomorrow at My Store | X/T                       | 2            |
      | 85260   | AZP 29 | 2012 | Honda  | Civic  | Coupe DX     | none           | All tires     | 19600    | Available today at My Store               | Premier A/S               | More than 20 |
      | 86001   | AZF 01 | 2015 | Nissan | Altima | Sedan        | 215 /55 R17 SL | All tires     | 36088    | Available today at My Store               | Blizzak WS70              | More than 20 |
      | 91710   | CAL 01 | 2016 | Honda  | Accord | Coupe EX     | none           | All tires     | 35215    | Available today at My Store               | Pilot Sport A/S Plus      | More than 20 |
      | 90746   | CAL 07 | 2016 | Honda  | Accord | Coupe EX     | none           | All tires     | 35215    | Available today at My Store               | Pilot Sport A/S Plus      | More than 20 |
      | 91710   | CAL 01 | 2016 | Ram    | 3500   | Dually 4WD   | none           | All tires     | 35365    | Available today at My Store               | LTX M/S2                  | 4            |
      | 90746   | CAL 07 | 2016 | Ram    | 3500   | Dually 4WD   | none           | All tires     | 35365    | Available today at My Store               | LTX M/S2                  | 4            |
      | 77022   | TXH 01 | 2016 | Ram    | 2500   | Mega Cab 4WD | 275 /70 R18 E1 | All tires     | 10204    | Available today at My Store               | Terra Grappler G2         | 4            |

  @at
  @dt
  @web
  @15742
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify inventory messaging rule on PDP with free text search and stock available at near by store (ALM #15742)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a free text search for "<ItemCode>" and hit enter
    Then I should see product detail page with "<ItemName>"
    And  I verify the "<InventoryMessage>" displayed for "<ItemCode>" on "PDP" page
    And  I verify check nearby stores link is "displayed" for "<ItemCode>" on "PDP" page
    And  I verify the stock count message contains "<StockCount> In Stock" on PDP

    Examples:
      | ZipCode | Store  | ItemCode | InventoryMessage                          | ItemName                                   | StockCount   |
      | 85260   | AZP 29 | 11032    | Available today at My Store               | Ventus A/S RH07                            | 4            |
      | 86001   | AZF 01 | 26533    | Order now, available tomorrow at My Store | Sport Contact                              | 2            |
      | 91710   | CAL 01 | 17989    | Order now, available tomorrow at My Store | Wrangler All Terrain Adventure with Kevlar | 2            |
      | 90746   | CAL 07 | 17989    | Order now, available tomorrow at My Store | Wrangler All Terrain Adventure with Kevlar | 2            |
      | 77022   | TXH 01 | 10390    | Available today at My Store               | KO2                                        | More than 20 |
      | 77022   | TXH 01 | 10410    | Order now, available tomorrow at My Store | KO2                                        | 2            |
      | 86001   | AZF 01 | 10209    | Available today at My Store               | Terra Grappler G2                          | 4            |

  @at
  @dt
  @web
  @15744
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify inventory messaging rule on PDP with fitment for staggered vehicle (ALM #15744)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    And  I see "<ItemCodeA>" on the product list page
    When I click on the product "<ItemCodeA>"
    Then I should see "<ItemCodeA>" on product details page
    And  I should see "<ItemCodeB>" on product details page
    And  I verify the "<InventoryMessageA>" for set displayed for "<ItemCodeA>" on "PDP" page
    And  I verify the "<InventoryMessageB>" for set displayed for "<ItemCodeB>" on "PDP" page
    And  I verify check nearby stores link is "displayed" having set "<ItemCodeA>" on "PDP" page
    And  I verify check nearby stores link is "displayed" having set "<ItemCodeB>" on "PDP" page

    Examples:
      | ZipCode | Store  | Year | Make          | Model     | Trim | Assembly | FitmentOption | ItemCodeA | ItemCodeB | InventoryMessageA                         | InventoryMessageB                          |
      | 85260   | AZP 29 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 86001   | AZF 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 91710   | CAL 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 90746   | CAL 07 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 77022   | TXH 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 27142     | 27141     | Order now, available tomorrow at My Store | Order now, available tomorrow at My Store  |
      | 77022   | TXH 01 | 2008 | Mercedes-Benz | SLK55 AMG | Base | none     | All tire sets | 17548     | 28695     | Order now, available tomorrow at My Store | Order now, available in 2 days at My Store |

  @at
  @dt
  @web
  @15745
  @extendedAssortment
  Scenario Outline: HYBRIS_SEARCH_SEARCH_EXTENDEDASSORTMENT_Verify inventory messaging rule on PDP with fitment for staggered vehicle with stock at mystore (ALM #15745)
    When I search for store within "25" miles of "<ZipCode>"
    And  I select make this "<Store>" my store
    And  I click the discount tire logo
    Then I am brought to the homepage
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    And  I see "<ItemCodeA>" on the product list page
    When I click on the product "<ItemCodeA>"
    Then I should see "<ItemCodeA>" on product details page
    And  I should see "<ItemCodeB>" on product details page
    And  I verify the "<InventoryMessageA>" for set displayed for "<ItemCodeA>" on "PDP" page
    And  I verify the "<InventoryMessageB>" for set displayed for "<ItemCodeB>" on "PDP" page
    And  I verify check nearby stores link is "not displayed" having set "<ItemCodeA>" on "PDP" page
    And  I verify check nearby stores link is "displayed" having set "<ItemCodeB>" on "PDP" page

    Examples:
      | ZipCode | Store  | Year | Make          | Model     | Trim | Assembly | FitmentOption | ItemCodeA | ItemCodeB | InventoryMessageA           | InventoryMessageB                          |
      | 85260   | AZP 29 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 35454     | 35455     | Available today at My Store | Order now, available in 2 days at My Store |
      | 86001   | AZF 01 | 2008 | Mercedes-Benz | SLK55 AMG | Base | none     | All tire sets | 17548     | 28695     | Available today at My Store | Order now, available in 2 days at My Store |
      | 91710   | CAL 01 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 35454     | 35455     | Available today at My Store | Order now, available in 2 days at My Store |
      | 90746   | CAL 07 | 2010 | Chevrolet     | Corvette  | Base | none     | All tire sets | 35454     | 35455     | Available today at My Store | Order now, available in 2 days at My Store |

  @dt
  @at
  @dtd
  @web
  @15565
  Scenario Outline: HYBRIS_Search_Search_PLP displays Top 3 Tiles for Standard Tires Default when all Tire data exists(OE,Our Recommendation,Best Seller and Highest Rated ) are returned ( ALM # 15565)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify the PLP displays 'Top 3 Tiles' below the PLP header
    And  I verify the PLP displays "Original Equipment","Our Recommendation","Best Seller" in the 'Top 3 Tiles'
    When I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "3" in the 'Top 3 Tiles'
    When I select the "Original Equipment" checkbox
    Then I verify Original Equipment tire is displayed on plp page
    When I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "1" in the 'Top 3 Tiles'
    And  I verify "Michelin" is displayed at position "2" in the 'Top 3 Tiles'

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | Header       |
      | 2012 | HONDA | CIVIC | COUPE DX | none     | All tires this size | tire results |

  @dt
  @at
  @dtd
  @web
  @15571
  Scenario Outline: HYBRIS_Search_Search_Top 3 Tiles display on PLP for Standard Tires when there is no OE
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify the PLP displays 'Top 3 Tiles' below the PLP header
    And  I verify "Original Equipment" does not display in the filters
    And  I verify the PLP displays "Highest Rated","Our Recommendation","Best Seller" in the 'Top 3 Tiles'
    When I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "3" in the 'Top 3 Tiles'
    When I select the "<Value>" from the Sort By dropdown box
    And  I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "1" in the 'Top 3 Tiles'
    And  I verify "Pirelli" is displayed at position "2" in the 'Top 3 Tiles'

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | FitmentOption       | Header       | Value         |
      | 2012 | HONDA | ACCORD | EX COUPE | none     | All tires this size | tire results | Highest Rated |

  @dt
  @at
  @dtd
  @web
  @15577
  Scenario Outline: HYBRIS_Search_Search_PLP displays Top 3 Tiles for staggered  Tires Default when all Tire data exists(OE,Our Recommendation,Best Seller and Highest Rated) are returned (ALM #15577)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I select "FRONT" staggered tab on PLP result page
    Then I verify the PLP header message contains "<Header>"
    And  I verify the PLP displays 'Top 3 Tiles' below the PLP header
    And  I verify the PLP displays "Original Equipment","Our Recommendation","Best Seller" in the 'Top 3 Tiles'
    When I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "3" in the 'Top 3 Tiles'
    When I select the "Original Equipment" checkbox
    Then I verify Original Equipment tire is displayed on plp page
    When I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "1" in the 'Top 3 Tiles'
    And  I verify "<Brand>" is displayed at position "2" in the 'Top 3 Tiles'
    When I select "REAR" staggered tab on PLP result page
    Then I verify the PLP header message contains "<Header>"
    And  I verify the PLP displays 'Top 3 Tiles' below the PLP header
    And  I verify the PLP displays "Original Equipment","Our Recommendation","Best Seller" in the 'Top 3 Tiles'
    When I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "3" in the 'Top 3 Tiles'
    When I select the "Original Equipment" checkbox
    Then I verify Original Equipment tire is displayed on plp page
    When I extract the product at position "1" from the products list page
    Then I verify the product is displayed at position "1" in the 'Top 3 Tiles'
    And  I verify "<Brand>" is displayed at position "2" in the 'Top 3 Tiles'

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption | Header       | Brand    |
      | 2010 | CHEVROLET | CORVETTE | BASE | none     | All tire sets | tire results | GOODYEAR |