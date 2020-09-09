@bba
@dt
@at
@taxonomy
Feature: Taxonomy

  Background:
    Given I change to the default store

  @web
  @bba
  @9609
  @9610
  @taxonomyBBA
  Scenario Outline: URL path has a response code under 400, expected element and breadcrumbs exist (ALM #9609,9610)
  """ TODO: MG 3-27-18 left in comments to keep test data per request by Ankit
      Home Page, step 1
      | /                                            | Schedule an appointment         | none                                    |
       Schedule Appointment page, step 3
      | /schedule-appointment                        | Next step: Store and time       | Home, Appointment, Services             |
       Schedule an Appointment page, step 4
      | /schedule-appointment/date?isPickupOnly=true | Next step: Customer Information | Home, Reservation, Store And Time       |
       Customer Information page, step 5
      | /schedule-appointment/customer-info          | Make Reservation                | Home, Reservation, Customer Information |
       Product Details page, step 9
      | /buy-tires/goodyear-eagle-f1-asymmetric-as/p/14990?all-vehicles=true# | Add to cart | Home, Product Details              |
       Tire Review page, step 11
      | /review/product/comparison?productCode=34302&allVehicles=true | Excellent | Home, Compare Tires                          |
       Compare Tire Reviews page, step 12
      Currently example table 1
      Tires All Brands page, step 13
      | /tires/brands                                | Goodyear                          | Home, Tires, All Brands               |
       Tires Type All & Vehicle Type View All page, step 14,15
      | /tires                                       | All-Season Tires                  | Home, Tires                           |
       Wheel All Brands page, step 16
      | /wheels/brands                               | Popular Brands                    | Home, Wheels, All Brands              |
       Wheels Style & Vehicle Type View All page, step 17
      | /wheels                                      | Wheels                            | Home, Wheels                          |
       Check Inventory page, step 18
      | /wheels/painted-catalog?q=%3Aprice-asc%3Abrands%3Abrand-KON%3Abrands%3Abrand-MBM&text=&true=on | Add to cart | Home, Wheels, Painted, Products |
       Found Lower page, step 20
      | http://www.founditlower.com/request/discounttire.php?product=Wheel&prodcode=75905&typecode=&manufacturer=MB%20WHEELS&model=14&size=15X6.5%205-100.00%2038SL%20MBM%2014&price=73.0&rc=&fitment=&zip= | Lower Price | none |
       Store Locator page (Change Store), step 21
      | /store-locator                               | Search                          | Home, Store Locator                     |
       Store Details page, step 22
      | /store/az/scottsdale/s/1344?lat=33.628572&long=-111.891134 | Make this my store | Home, Store Locator, Discount Tire Store (AZP 29) |
       Cart page, step 27
      | /cart                                        | Shopping cart                   | Home, Cart                              |
       Checkout page, step 28
      | /checkout/info                               | Next step: Customer Information | Home, Checkout, Store Info              |
       Schedule Appointment Date and Time page (NO STEP)
      | /checkout/appointment-info                   | Next step: Customer Information | Home, Checkout, Appointment Info        |
       Schedule Appointment Customer Info page (NO STEP)
      | /checkout/customer-info                      | Schedule Appointment            | Home, Checkout, Customer Info           |
       Schedule Appointment Store Info page (NO STEP)
      | /checkout/info                               | Schedule Appointment            | Home, Checkout, Store Info              |
    """
    When I request the URL with "<Path>"
    Then I should get a response below 400
    And  an element with text "<ElementText>" exists for path "<Path>"
    And  the breadcrumbs "<Breadcrumbs>" exist for path "<Path>"

    Examples:
      | Path                                                                     | ElementText | Breadcrumbs           |
      | /buy-tires/michelin-defender-a-s/p/34302?all-vehicles=true#/read-reviews | Add to cart | Home, Product Details |

  @homeVehicleFitmentResultsPageUrlValidation
  @9609
  @9610
  @web
  @bba
  @mobile
  @taxonomyBBA
  Scenario Outline: Verify element and breadcrumbs on Home Vehicle Fitment popup results page (ALM #9609,9610, step 26)
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see text "<Element>" present in the page source

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Element                      |
      | 2012 | Honda | Civic | Coupe DX | none     | fitment-box__close icon-open |

  @storeLocatorVehicleFitmentResultsPageUrlValidation
  @9609
  @9610
  @web
  @mobile
  @bba
  @taxonomyBBA
  Scenario Outline: Verify element and breadcrumbs on Store Locator Vehicle Fitment popup results page (ALM #9609,9610, step 2)
    When I open the Store Locator page
    And  I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I should see text "<Element>" present in the page source

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Element                      |
      | 2012 | Honda | Civic | Coupe DX | none     | fitment-box__close icon-open |

  @addVehicleFitmentPageUrlValidation
  @9609
  @9610
  @web
  @mobile
  @bba
  @taxonomyBBA
  Scenario: Verify element and breadcrumbs on Vehicle Fitment popup page (ALM #9609,9610, steps 23,24,25)
    When I open the fitment popup
    And  I navigate to Shop By "Vehicle"
    Then the dropdown "Year, Make, Model, Trim" is visible on the page
    When I navigate to Shop By "Tire Size"
    Then the dropdown "Width, Ratio, Diameter" is visible on the page
    When I navigate to Shop By "Wheel Size"
    And  I click the fitment "Wheels" radio button
    Then the dropdown "Diameter, Wheel Width, Bolt Pattern" is visible on the page

  @makeReservationCustomerInfoPageUrlValidation
  @9609
  @9610
  @web
  @bba
  @taxonomyBBA
  Scenario Outline: Verify element and breadcrumbs on Make Reservation Customer Info page (ALM #9609,9610, steps 6 and 7)
    When I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I add item to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    And  I reserve items and complete checkout for "<Customer>"
    Then I should see text "<ElementText>" present in the page source

    Examples:
      | ProductName  | ItemCode | Checkout | Customer            | ElementText              | Reason                              |
      | Defender A/S | 34302    | default  | DEFAULT_CUSTOMER_AZ | Thank you for your order | Make an appointment at a later time |

  @makeReservationCustomerInfoPageUrlValidation
  @9609
  @9610
  @mobile
  @bba
  @taxonomyBBA
  Scenario Outline: Mobile - Verify element and breadcrumbs on Make Reservation Customer Info page (ALM #9609,9610, steps 6 and 7)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I reserve items for "<Customer>"
    Then I verify the "<Breadcrumbs>" link in the breadcrumb container
    And  I should see text "<ElementText>" present in the page source

    Examples:
      | ProductName  | ItemCode | Checkout            | Customer            | ElementText                 | Breadcrumbs                  |
      | Defender A/S | 34302    | without appointment | DEFAULT_CUSTOMER_AZ | Continue shopping for tires | Home, Checkout, Confirmation |

  @checkInventoryPageUrlValidation
  @9609
  @9610
  @web
  @bba
  @taxonomyBBA
  Scenario Outline: Verify element and breadcrumbs on Check Inventory page (ALM #9609,9610, step 18)
    When I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I select the "Check Inventory" link for item "<ItemCode>"
    Then I should verify that the Check Availability popup loaded

    Examples:
      | ProductName  | ItemCode |
      | Defender A/S | 34302    |

  @checkInventoryPageUrlValidation
  @9609
  @9610
  @mobile
  @bba
  @taxonomyBBA
  Scenario Outline: Mobile - Verify element and breadcrumbs on Check Inventory page (ALM #9609,9610, step 18)
    When I do a free text search for "<ItemCode>"
    Then I should see "<ProductName>" in the search results
    When I select the "Check Inventory" link for item "<ItemCode>"
    Then I should verify that the Check Availability popup loaded

    Examples:
      | ProductName  | ItemCode |
      | Defender A/S | 34302    |