@customer
Feature: Customer

  Background:
    Given I go to the homepage

  @dtd
  @web
  @6999
  @smoketest
  @regression
  @customerSmoke
  @customerRegression
  Scenario Outline: Wheel size order email validation (ALM #6999)
    When I do a "my vehicles" wheel size search with details "<Diameter>" "<WheelWidth>" "<BoltPattern>"
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
      | Diameter | WheelWidth | BoltPattern | ItemCode | Checkout | Customer                  | ShippingOption |
      | 16       | 7.0        | 5-100.0 MM  | 75911    | default  | email_validation_customer | UPS Ground     |

  @dt
  @at
  @dtd
  @web
  @9905
  Scenario Outline: HYBRIS_Email_Verification_Is_Case_Sensitive_Appointment_TC_01 (ALM #9905)
    When I open the "SERVICES" navigation link
    And  I click the "SCHEDULE APPOINTMENT" menu option
    And  I select service option(s): "<ServiceOption>"
    And  I select 'Set Appointment Details' for Date and Time
    Then I verify selected service option(s): "<ServiceOption>" is displayed on Service Appointment page
    And  I verify default store on the customer details appointment page
    When I select first available appointment date
    And  I extract date and time for validation
    And  I click continue for appointment customer details page
    Then I verify date and time on the customer details appointment page
    When I select "Schedule Appointment" after entering customer information for "<Customer>"
    Then I verify the Appointment Confirmation email address is "LOWERCASE"
    And  I should see an appointment confirmation message for "<Customer>" with service options: "<ServiceOption>"
    And  I should see my previously selected store, date and time, in the appointment details section
    And  I store the order number
    And  I confirm "<Customer>" receives an email for the order

    Examples:
      | ServiceOption      | Customer            |
      | Winter Tire Change | DEFAULT_CUSTOMER_GA |


  @at
  @dt
  @web
  @8276
  @smoketest
  @customerSmoke
  Scenario Outline: HYBRIS_CREATE_Service_Appointment_HomePage_Header_Flow_No_Product (ALM #8276)
    When I change to the default store
    And  I open the "SERVICES" navigation link
    And  I click the "SCHEDULE APPOINTMENT" menu option
    And  I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select first available appointment date
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    And  I store the order number
    Then I confirm "<Customer>" receives an email for the appointment

    Examples:
      | ServiceOptions  | Customer                  |
      | Tire Inspection | EMAIL_VALIDATION_CUSTOMER |

  @at
  @dt
  @web
  @9613
  @9617
  Scenario Outline: HYBRIS_CREATE_Service_Appointment_HomePage_Command_Button_Flow_No_Product (ALM #9613, 9617)
    When I schedule an appointment for my current store
    And  I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select first available appointment date
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    And  I store the order number
    Then I confirm "<Customer>" receives an email for the appointment

    Examples:
      | ServiceOptions     | Customer                  |
      | Winter Tire Change | EMAIL_VALIDATION_CUSTOMER |

  @at
  @dt
  @web
  @9614
  Scenario Outline: HYBRIS_CREATE_Service_Appointment_With_Address_HomePage_Command_Button_Flow_No_Product (ALM #9614)
    When I open the "SERVICES" navigation link
    And  I click the "Schedule an appointment" menu option
    And  I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select first available appointment date
    And  I click continue for appointment customer details page
    And  I click reserve after entering customer information, including the address, for "<Customer>"
    Then I store the order number
    And  I confirm "<Customer>" receives an email for the appointment

    Examples:
      | ServiceOptions | Customer                  |
      | Flat Repair    | EMAIL_VALIDATION_CUSTOMER |

  @at
  @dt
  @web
  @9615
  Scenario Outline: HYBRIS_CREATE_Service_Appointment_HomePage_MyStore_Change_Store_Flow_No_Product_AT (ALM #9615)
    When I search for store within "25" miles of "92324"
    And  I select "Schedule appointment" for store #"2" in the store location results
    And  I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select first available appointment date
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    And  I store the order number
    Then I confirm "<Customer>" receives an email for the appointment

    Examples:
      | ServiceOptions            | Customer                  |
      | Tire Rotation and Balance | EMAIL_VALIDATION_CUSTOMER |

  @at
  @dt
  @web
  @8274
  @regression
  @customerRegression
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_Product CompareTireReviewsChart Link PLP Staggered (ALM #8274)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    And  I verify Customer Rating and reviews are displayed for listed products on PLP result page
    And  I verify compare tire reviews link is displayed for products that have reviews
    When I click compare tire reviews link
    Then I verify the compare tire reviews page displays
    When I navigate back to previous page
    Then I verify the product list page is displayed with sets
    When I select "FRONT" staggered tab on PLP result page
    Then I verify Customer Rating and reviews are displayed for listed products on PLP result page
    And  I verify compare tire reviews link is displayed for products that have reviews
    When I navigate back to previous page
    Then I verify the product list page is displayed with sets
    When I select "REAR" staggered tab on PLP result page
    Then I verify Customer Rating and reviews are displayed for listed products on PLP result page
    And  I verify compare tire reviews link is displayed for products that have reviews

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption |
      | 2010 | Chevrolet | Corvette | Base | none     | All tire sets |

  @at
  @dt
  @web
  @6893
  @regression
  @customerRegression
  Scenario: HYBRIS_CUSTOMER_REVIEWS_Store Solicited Review form validation desktop (ALM #6893)
  """TODO:  Verify Terms and Conditions will fail for America's Tire stores due to defect 9508."""
    When I navigate to Store Review page
    Then I verify store logo is displayed in store info section
    And  I verify store name, address, and phone number are displayed in store info section
    And  I verify "Store Hours" are displayed in store info section
    And  I verify store review page header text
    And  I verify "All fields required unless marked as optional" displayed
    And  I verify store ratings headings
    And  I verify "Would You Recommend This Store" displayed with YES NO buttons
    And  I verify "Comments About Your Experience" displayed
    And  I verify "Comments" text area displayed with "10000" character limit
    And  I verify "Yes I agree to Terms and Conditions" text displayed with checkbox and Terms and Conditions link
    When I click "Terms and Conditions" link
    Then I verify "CUSTOMER RATINGS AND REVIEW TERMS OF USE" pop-up is displayed
    When I close "CUSTOMER RATINGS AND REVIEW TERMS OF USE" pop-up
    Then I verify the "Submit Review" button is displayed
    When I click "Submit Review" button
    Then I verify the "Please provide feedback for the following" pop-up is displayed
    When I close the "Please provide feedback for the following" pop-up

  @at
  @dt
  @web
  @7250
  @regression
  @customerRegression
  Scenario: HYBRIS_CUSTOMER_REVIEWS_Store Solicited Review Submission (ALM #7250)
  """ The "verify error messages on page" step will fail in IE or Safari after entering text in the Comments field,
        clicking Submit, and closing pop-up window. The !Required label is retained in IE and Safari - defect 9654 """
    When I navigate to Store Review page
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "store"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "store"
    When I select "random" stars for "Employee Knowledge/Friendliness" store rating category
    And  I select "random" stars for "Store Cleanliness" store rating category
    And  I select "random" stars for "Overall Rating" store rating category
    And  I "select" "Yes, I agree to Discount Tire's Terms & Conditions" checkbox
    And  I enter "test comment" into the Comments text box
    And  I click "Submit Review" button
    Then I verify "Store Recommendation" is the only item listed in the "Please provide feedback for the following" pop-up
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "store"
    When I select "random" for "Would You Recommend" question
    And  I click "Submit Review" button
    Then I verify completed review message displayed for "store"

  @at
  @dt
  @dtd
  @web
  @6891
  @8266
  @regression
  @customerRegression
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_Product Solicited Review Submission (ALM #6891, #8266-staggered)
  """ The "verify error messages on page" step will fail in IE or Safari after entering valid value(s) in the Zip Code
      and/or Miles Driven  field(s), clicking Submit, and closing pop-up window. The !Required label is retained
      in IE and Safari - defect 9656 """
    When I navigate to Product Review page with vehicle details for "<Year>" "<Make>" "<Model>" "<ProductId>"
    Then I verify product review page header text with product name "<ProductName>"
    When I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    And  I verify "Pros" text area displayed with "150" character limit
    And  I verify "Cons" text area displayed with "150" character limit
    And  I verify "Comments" text area displayed with "10000" character limit
    When I select "random" stars for "RIDE COMFORT" product rating category
    And  I select "random" stars for "CORNERING & STEERING" product rating category
    And  I select "random" stars for "RIDE NOISE" product rating category
    And  I select "random" stars for "TREAD LIFE" product rating category
    And  I select "random" stars for "DRY TRACTION" product rating category
    And  I select "random" stars for "WET TRACTION" product rating category
    And  I select "random" stars for "WINTER WEATHER TRACTION" product rating category
    And  I enter "valid" Driving zip code "86001"
    And  I enter "15000" Miles driven on tires
    And  I enter "test comment" into the Comments text box
    And  I select "random" from "Driving conditions" dropdown list
    And  I select "random" from "Type of driving" dropdown list
    And  I "select" "Yes, I agree to Discount Tire's Terms & Conditions" checkbox
    And  I click "Submit Review" button
    Then I verify "Product Recommendation" is the only item listed in the "Please provide feedback for the following" pop-up
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    When I select "YES" for "Would You Recommend" question
    And  I click "Submit Review" button
    Then I verify completed review message displayed for "product"
    When I navigate to Product Review page with vehicle Id for "<VehicleId>" "<ProductId>"
    Then I verify product review page header text with product name "<ProductName>"
    When I select "random" stars for "RIDE COMFORT" product rating category
    And  I select "random" stars for "CORNERING & STEERING" product rating category
    And  I select "random" stars for "RIDE NOISE" product rating category
    And  I select "random" stars for "TREAD LIFE" product rating category
    And  I select "random" stars for "DRY TRACTION" product rating category
    And  I select "random" stars for "WET TRACTION" product rating category
    And  I select "random" stars for "WINTER WEATHER TRACTION" product rating category
    And  I enter "valid" Driving zip code "86001"
    And  I enter "15000" Miles driven on tires
    And  I enter "test comment" into the Comments text box
    And  I select "random" from "Driving conditions" dropdown list
    And  I select "random" from "Type of driving" dropdown list
    And  I select "NO" for "Would You Recommend" question
    And  I "select" "Yes, I agree to Discount Tire's Terms & Conditions" checkbox
    And  I click "Submit Review" button
    Then I verify completed review message displayed for "product"

    Examples:
      | Year | Make      | Model    | ProductId | ProductName          | VehicleId |
      | 2012 | Honda     | Civic    | 26899     | Pro Contact          | 105163    |
      | 2010 | Chevrolet | Corvette | 37068     | G-Force Sport Comp 2 | 105167    |

  @at
  @dt
  @dtd
  @web
  @6776
  @regression
  @customerRegression
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_Product Solicited Review FormValidation Regular (ALM #6776)
    When I navigate to Product Review page with vehicle details for "<Year>" "<Make>" "<Model>" "<ProductId>"
    Then I verify product review page header text with product name "<ProductName>"
    And  I verify product rating headings displayed with five stars
    And  I verify the "Submit Review" button is displayed
    When I click "Submit Review" button
    Then I verify the "Please provide feedback for the following" pop-up is displayed
    And  I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    When I select "random" stars for "RIDE COMFORT" product rating category
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    And  I verify "Would you recommend this product to family and friends" question displayed with YES NO buttons
    And  I verify "Driving zip code" field displayed
    And  I verify "Miles driven on tires" field displayed
    And  I verify "Driving conditions" list box displayed
    And  I verify "Type of driving" list box displayed
    And  I verify "Yes I agree to Terms and Conditions" text displayed with checkbox and Terms and Conditions link
    When I enter "invalid" Driving zip code "0"
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    And  I verify "5 digit zip code required" error message displayed
    When I enter "invalid" Driving zip code "ababa"
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    And  I verify "5 digit zip code required" error message displayed
    When I enter "valid" Driving zip code "86001"
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    When I enter "valid" Driving zip code "T1Y1H3"
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    When I enter "invalid" Driving zip code ""
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    When I enter "0" Miles driven on tires
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    And  I verify "Please type a number between 1 and 150000" error message displayed
    When I enter "1" Miles driven on tires
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    When I enter "150001" Miles driven on tires
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    And  I verify "Please type a number between 1 and 150000" error message displayed
    When I enter "150000" Miles driven on tires
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"
    When I enter "" Miles driven on tires
    And  I click "Submit Review" button
    Then I verify items listed in "Please provide feedback for the following" pop-up for "product"
    When I close the "Please provide feedback for the following" pop-up
    Then I verify error messages on page for "product"

    Examples:
      | Year | Make  | Model | ProductId | ProductName |
      | 2012 | Honda | Civic | 26899     | Pro Contact |

  @at
  @dt
  @dtd
  @web
  @7980
  @7887
  @regression
  @customerRegression
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_Product Compare Tire Reviews Chart Sort (ALM #7980, 7887)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I click compare tire reviews link
    Then I verify the compare tire reviews page displays
    And  I verify "Overall Rating" sort option is displayed
    And  I verify "Ride Comfort" sort option is displayed
    And  I verify "Cornering / Steering" sort option is displayed
    And  I verify "Ride Noise" sort option is displayed
    And  I verify "Tread Life" sort option is displayed
    And  I verify "Dry Traction" sort option is displayed
    And  I verify "Wet Traction" sort option is displayed
    And  I verify "Winter Traction" sort option is displayed
    And  I verify "Buy Tire Again" sort option is displayed
    And  I verify the result list for "Overall Rating" is sorted in "Descending" order
    When I select the arrow on the sort option
    Then I verify the result list for "Overall Rating" is sorted in "Ascending" order

    Examples:
      | Year | Make      | Model    | Trim     | Assembly | FitmentOption       |
      | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets       |
      | 2012 | Honda     | Civic    | Coupe DX | none     | All tires this size |

  @at
  @dt
  @web
  @7978
  @regression
  @customerRegression
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_Product CompareTireReviewsChart ContentDisplay (ALM #7978)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I click compare tire reviews link
    Then I verify the compare tire reviews page displays
    And  I verify the "TIRES" navigation link is displayed
    And  I verify the "WHEELS" navigation link is displayed
    And  I verify the "SERVICES" navigation link is displayed
    And  I verify the "TIPS & GUIDES" navigation link is displayed
    And  I verify the "<Breadcrumbs>" link in the breadcrumb container
    And  I verify the second level header "Compare tire reviews" on the page
    When I click add to cart for the first tire on the compare tire reviews page
    Then I verify item added to your cart popup contains selected tires
    When I click on the "Continue Shopping" Link
    And  I click on the "Tires for my vehicle" Link
    And  I select "footer" "Site Map"
    Then I am brought to the page with header "Site Map"

      Examples:
        | Year | Make      | Model    | Trim     | Assembly | FitmentOption       | Breadcrumbs         |
        | 2010 | Chevrolet | Corvette | Base     | none     | All tire sets       | Home, Compare Tires |
        | 2012 | Honda     | Civic    | Coupe DX | none     | All tires this size | Home, Compare Tires |

  @at
  @dt
  @dtd
  @web
  @7979
  @regression
  @customerRegression
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_Product CompareTireReviewsChart OverallRating Staggered (ALM #7979)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets
    When I click compare tire reviews link
    Then I verify the compare tire reviews page displays
    And  I verify the overall rating for the selected tire on the compare tire reviews page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption |
      | 2010 | Chevrolet | Corvette | Base | none     | All tire sets |

  @at
  @dt
  @web
  @15426
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_My Store PopUp_Sort By defaults to Most Recent (ALM #15426)
    When I click on "My Store" title
    And  I select Read Reviews link in the popup
    Then I verify the second level header "<Header>" on the page
    And  I verify the "Store Reviews" sort by dropdown value is set to "<SortValue>"
    And  I verify the result list for "<SortValue>" is sorted in "Descending" order

    Examples:
      | SortValue   | Header           |
      | Most Recent | customer reviews |

  @at
  @dt
  @mobile
  @15426
  Scenario Outline: MOBILE - HYBRIS_CUSTOMER_REVIEWS_Store_Reviews Sort By defaults to Most Recent (ALM #15426)
    When I search for store within "25" miles of "flagstaff"
    And  I select "Read reviews" for store #"1" in the store location results
    Then I verify the "Store Reviews" sort by dropdown value is set to "<SortValue>"
    And  I verify the result list for "Most Recent" is sorted in "Descending" order

    Examples:
      | SortValue   |
      | Most Recent |

  @at
  @dt
  @web
  @15459
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_My Store PopUp_Sort By changes are not retained (ALM #15459)
    When I click on "My Store" title
    And  I select Read Reviews link in the popup
    Then I verify the second level header "<Header>" on the page
    And  I verify the "Store Reviews" sort by dropdown value is set to "<SortValue2>"
    When I select Sort By dropdown
    And  I sort the reviews by "<SortValue1>"
    Then I verify the "Store Reviews" sort by dropdown value is set to "<SortValue1>"
    And  I verify the "Store Reviews" result list for "<SortValue1>" is sorted in "Ascending" order
    When I go to the homepage
    And  I click on "My Store" title
    And  I select Read Reviews link in the popup
    Then I verify the second level header "<Header>" on the page
    And  I verify the "Store Reviews" sort by dropdown value is set to "<SortValue2>"
    And  I verify the result list for "Most Recent" is sorted in "Descending" order

    Examples:
      | SortValue1   | SortValue2  | Header           |
      | Lowest Rated | Most Recent | customer reviews |

  @at
  @dt
  @mobile
  @15459
  Scenario Outline: MOBILE - HYBRIS_CUSTOMER_REVIEWS_Store_Reviews_Sort By changes are not retained (ALM #15459)
    When I search for store within "25" miles of "flagstaff"
    And  I select "Read reviews" for store #"1" in the store location results
    And  I select Sort By dropdown
    And  I sort the reviews by "<SortValue1>"
    Then I verify the "Store Reviews" sort by dropdown value is set to "<SortValue1>"
    And  I verify the result list for "<SortValue1>" is sorted in "Ascending" order
    When I go to the homepage
    And  I search for store within "25" miles of "flagstaff"
    And  I select "Read reviews" for store #"1" in the store location results
    Then I verify the "Store Reviews" sort by dropdown value is set to "<SortValue2>"
    And  I verify the result list for "<SortValue2>" is sorted in "Descending" order

    Examples:
      | SortValue1   | SortValue2  |
      | Lowest Rated | Most Recent |

  @at
  @dt
  @dtd
  @web
  @mobile
  @15625
  Scenario Outline:HYBRIS_CUSTOMER_REVIEWS_Product Reviews_Sort By defaults to Most Recent (ALM #15625)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I am brought to the page with path "/fitmentresult/tires"
    And  I verify Customer Rating and reviews are displayed for listed products on PLP result page
    When I select the 'Read Reviews' link on the PLP
    Then I verify PDP page is displayed
    And  I verify the second level header "Customer reviews" on the page
    And  I verify the "Product Reviews" sort by dropdown value is set to "Most Recent"
    And  I verify the "Product Reviews" result list for "Most Recent" is sorted in "Descending" order

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires this size |

  @at
  @dt
  @web
  @15531
  Scenario Outline: HYBRIS_CUSTOMER_REVIEWS_My Store PopUp_Store Reviews filterable by radio buttons (ALM #15531)
    When I click on "My Store" title
    And  I select Read Reviews link in the popup
    Then I verify the second level header "customer reviews" on the page
    And  I verify the 'Filter Reviews By Rating' section is displayed
    When I select the "<Number Rating>" star rating checkbox to filter 'Store Reviews'
    Then I verify the "<Number Rating>" star rating checkbox is the only selected filter for 'Store Reviews'
    And  I verify the 'Store Reviews' are filtered by "<Number Rating>" star rating

    Examples:
      | Number Rating |
      | five          |
      | one           |