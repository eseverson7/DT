@UiUx
Feature: UI/UX -(Header, Footer & Navigation)

  Background:
    Given I change to the default store

  @at
  @dt
  @web
  @9666
  @13085
  @regression
  @UiUxRegression
  Scenario: Verify working Apply Now footer form (ALM# 9666, 13085)
    When I select "footer" "Credit"
    Then I verify the "Home, Finance" link in the breadcrumb container
    When I click on the "Apply Now" button
    Then I verify the second level header "We Protect Your Information" on the page
    When I go to the homepage
    And  I select "footer" "Apply Now"
    Then I verify the second level header "We Protect Your Information" on the page

  @dt
  @dtd
  @web
  @5300
  @5276
  @5461
  @5306
  @5394
  @5459
  @5279
  @5287
  @5305
  @bba
  @UiUxBBA
  Scenario Outline:HYBRIS_GLOBAL AND NAVIGATION_FOOTER_Validate links(DT & DTD) (ALM #5300, 5276, 5461, 5306, 5394, 5459, 5279, 5287, 5305)
    When I browse to the "<Page>" page with defaults
    And  I select "footer" "<FooterLink>"
    Then I verify the current URL contains "<URL>"

    Examples:
      | Page               | FooterLink           | URL                                |
      | Homepage           | About Us             | /about-us                          |
      | Homepage           | Our Story            | /about-us/our-story                |
      | Homepage           | Motorsports          | /about-us/motorsports              |
      | Homepage           | Careers              | careers.discounttire.com/          |
      | Homepage           | Tire Safety          | /learn/tire-safety                 |
      | Homepage           | Tire Size Calculator | /learn/tire-size-calculator        |
      | Homepage           | Check Tire Pressure  | /learn/check-tire-pressure         |
      | Homepage           | More Topics...       | /learn                             |
      | Homepage           | Credit               | /customer-service/financing        |
      | Homepage           | Apply Now            | etail.mysynchrony.com/eapply/      |
      | Homepage           | Commercial Payments  | ww2.e-billexpress.com/ebpp/DTCORP/ |
      | Homepage           | Site Map             | /sitemap                           |
      | Homepage           | Customer Care        | /customer-service                  |
      | Homepage           | Find a store         | /store-locator                     |
      | Homepage           | Appointments         | /schedule-appointment              |
      | Homepage           | Return Policy        | /customer-service/return-policy    |
      | Homepage           | Tire Search          | /tires                             |
      | Homepage           | Wheel Search         | /wheels                            |
      | Homepage           | Services             | /services                          |
      | Homepage           | Deals And Rebates    | /promotions                        |
      | PLP                | About Us             | /about-us                          |
      | PLP                | Our Story            | /about-us/our-story                |
      | PLP                | Motorsports          | /about-us/motorsports              |
      | PLP                | Careers              | careers.discounttire.com/          |
      | PLP                | Tire Safety          | /learn/tire-safety                 |
      | PLP                | Tire Size Calculator | /learn/tire-size-calculator        |
      | PLP                | Check Tire Pressure  | /learn/check-tire-pressure         |
      | PLP                | More Topics...       | /learn                             |
      | PLP                | Credit               | /customer-service/financing        |
      | PLP                | Apply Now            | etail.mysynchrony.com/eapply/      |
      | PLP                | Commercial Payments  | ww2.e-billexpress.com/ebpp/DTCORP/ |
      | PLP                | Site Map             | /sitemap                           |
      | PLP                | Customer Care        | /customer-service                  |
      | PLP                | Find a store         | /store-locator                     |
      | PLP                | Appointments         | /schedule-appointment              |
      | PLP                | Return Policy        | /customer-service/return-policy    |
      | PLP                | Tire Search          | /tires                             |
      | PLP                | Wheel Search         | /wheels                            |
      | PLP                | Services             | /services                          |
      | PLP                | Deals And Rebates    | /promotions                        |
      | PDP                | About Us             | /about-us                          |
      | PDP                | Our Story            | /about-us/our-story                |
      | PDP                | Motorsports          | /about-us/motorsports              |
      | PDP                | Careers              | careers.discounttire.com/          |
      | PDP                | Tire Safety          | /learn/tire-safety                 |
      | PDP                | Tire Size Calculator | /learn/tire-size-calculator        |
      | PDP                | Check Tire Pressure  | /learn/check-tire-pressure         |
      | PDP                | More Topics...       | /learn                             |
      | PDP                | Credit               | /customer-service/financing        |
      | PDP                | Apply Now            | etail.mysynchrony.com/eapply/      |
      | PDP                | Commercial Payments  | ww2.e-billexpress.com/ebpp/DTCORP/ |
      | PDP                | Site Map             | /sitemap                           |
      | PDP                | Customer Care        | /customer-service                  |
      | PDP                | Find a store         | /store-locator                     |
      | PDP                | Appointments         | /schedule-appointment              |
      | PDP                | Return Policy        | /customer-service/return-policy    |
      | PDP                | Tire Search          | /tires                             |
      | PDP                | Wheel Search         | /wheels                            |
      | PDP                | Services             | /services                          |
      | PDP                | Deals And Rebates    | /promotions                        |
      | Shopping Cart      | About Us             | /about-us                          |
      | Shopping Cart      | Our Story            | /about-us/our-story                |
      | Shopping Cart      | Motorsports          | /about-us/motorsports              |
      | Shopping Cart      | Careers              | careers.discounttire.com/          |
      | Shopping Cart      | Tire Safety          | /learn/tire-safety                 |
      | Shopping Cart      | Tire Size Calculator | /learn/tire-size-calculator        |
      | Shopping Cart      | Check Tire Pressure  | /learn/check-tire-pressure         |
      | Shopping Cart      | More Topics...       | /learn                             |
      | Shopping Cart      | Credit               | /customer-service/financing        |
      | Shopping Cart      | Apply Now            | etail.mysynchrony.com/eapply/      |
      | Shopping Cart      | Commercial Payments  | ww2.e-billexpress.com/ebpp/DTCORP/ |
      | Shopping Cart      | Site Map             | /sitemap                           |
      | Shopping Cart      | Customer Care        | /customer-service                  |
      | Shopping Cart      | Find a store         | /store-locator                     |
      | Shopping Cart      | Appointments         | /schedule-appointment              |
      | Shopping Cart      | Return Policy        | /customer-service/return-policy    |
      | Shopping Cart      | Tire Search          | /tires                             |
      | Shopping Cart      | Wheel Search         | /wheels                            |
      | Shopping Cart      | Services             | /services                          |
      | Shopping Cart      | Deals And Rebates    | /promotions                        |
      | Order Confirmation | About Us             | /about-us                          |
      | Order Confirmation | Our Story            | /about-us/our-story                |
      | Order Confirmation | Motorsports          | /about-us/motorsports              |
      | Order Confirmation | Careers              | careers.discounttire.com/          |
      | Order Confirmation | Tire Safety          | /learn/tire-safety                 |
      | Order Confirmation | Tire Size Calculator | /learn/tire-size-calculator        |
      | Order Confirmation | Check Tire Pressure  | /learn/check-tire-pressure         |
      | Order Confirmation | More Topics...       | /learn                             |
      | Order Confirmation | Credit               | /customer-service/financing        |
      | Order Confirmation | Apply Now            | etail.mysynchrony.com/eapply/      |
      | Order Confirmation | Commercial Payments  | ww2.e-billexpress.com/ebpp/DTCORP/ |
      | Order Confirmation | Site Map             | /sitemap                           |
      | Order Confirmation | Customer Care        | /customer-service                  |
      | Order Confirmation | Find a store         | /store-locator                     |
      | Order Confirmation | Appointments         | /schedule-appointment              |
      | Order Confirmation | Return Policy        | /customer-service/return-policy    |
      | Order Confirmation | Tire Search          | /tires                             |
      | Order Confirmation | Wheel Search         | /wheels                            |
      | Order Confirmation | Services             | /services                          |
      | Order Confirmation | Deals And Rebates    | /promotions                        |

  @web
  @dt
  @dtd
  @bba
  @5284
  @UiUxBBA
  Scenario: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different categories related to WHEELS (ALM# 5284)
    When I open the "WHEELS" navigation link
    And  I click the "WHEELS BY VEHICLE" menu option
    And  I do a vehicle search with details "2012" "HONDA" "ACCORD" "EX COUPE" "none"
    Then I should see the fitment panel page with vehicle "2012 HONDA"
    When I close the fitment popup
    And  I remove my current vehicle
    And  I open the "WHEELS" navigation link
    And  I click the "WHEELS BY SIZE" menu option
    And  I do a "homepage" wheel size search with details "15" "7.0" "5-100.0 MM"
    Then I verify the "PLP" results banner message contains "15X7.0 5-100.0 mm"
    When I open the "WHEELS" navigation link
    And  I click the "WHEELS BY BRAND" menu option
    Then I am brought to the page with path "/wheels/brands"
    And  I verify all of the fitment menus are displayed

  @web
  @dt
  @dtd
  @bba
  @5284
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different brand categories related to WHEELS (ALM# 5284)
  """TODO: Unique Brand - fails in DTD due to Defect #9921"""
    When I open the "WHEELS" navigation link
    And  I click the "WHEELS BY BRAND" menu option
    And  I select the "<Brand>" tire brand image
    Then I am brought to the page with path "<Path>"
    And  I verify all of the fitment menus are displayed
    When I select "View All" from the Product Brand page
    Then I can see "<Brand>" Brand PLP page

    Examples:
      | Brand       | Path                     |
      | MB WHEELS   | /wheels/brands/mb-wheels |
      | UNIQUE      | /wheels/brands/unique    |
      | DRAG WHEELS | /wheels/brands/drag      |
      | KONIG       | /wheels/brands/konig     |

  @web
  @dt
  @dtd
  @bba
  @5284
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different Style and Type categories related to WHEELS (ALM# 5284)
    When I open the "WHEELS" navigation link
    And  I click the "<StyleTypeLink>" menu option
    Then I am brought to the page with path "<Path>"
    And  I verify all of the fitment menus are displayed
    When I select "View All" from the Product Brand page
    Then I can see "<StyleTypePLPPage>" PLP page

    Examples:
      | StyleTypeLink    | Path              | StyleTypePLPPage |
      | Painted Wheels   | /wheels/painted   | painted          |
      | Machined Wheels  | /wheels/machined  | machined         |
      | Chrome Wheels    | /wheels/chrome    | chrome           |
      | Mesh Wheels      | /wheels/mesh      | mesh             |
      | Trailer Wheels   | /wheels/trailer   | trailer          |
      | Passenger Wheels | /wheels/passenger | passenger        |
      | ATV/UTV Wheels   | /wheels/atv-utv   | atv-utv          |
      | Truck Wheels     | /wheels/truck     | truck            |

  @mobile
  @dt
  @dtd
  @bba
  @5284
  @UiUxBBA
  Scenario Outline: Mobile - HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different categories related to WHEELS (ALM# 5284)
    When I click the mobile homepage menu
    And  I click on "<Menu>" menu link
    And  I click on "<Sub Menu>" menu link
    And  I click on "<Link>" menu link
    Then I am brought to the mobile page with header "<Expected Header>"

    Examples:
      | Menu  | Sub Menu     | Link             | Expected Header |
      | Wheel | Wheel Brand  | MB Wheels        | MB WHEELS       |
      | Wheel | Wheel Brand  | Unique Wheels    | UNIQUE          |
      | Wheel | Wheel Brand  | Drag Wheels      | DRAG WHEELS     |
      | Wheel | Wheel Brand  | Konig Wheels     | KONIG           |
      | Wheel | Wheel Brand  | View all         | All Brands      |
      | Wheel | Wheel Style  | Painted Wheels   | Painted         |
      | Wheel | Wheel Style  | Machined Wheels  | Machined        |
      | Wheel | Wheel Style  | Chrome Wheels    | Chrome          |
      | Wheel | Wheel Style  | Mesh Wheels      | Mesh Wheels     |
      | Wheel | Wheel Style  | View all         | Wheels          |
      | Wheel | Vehicle Type | Trailer Wheels   | Trailer         |
      | Wheel | Vehicle Type | Passenger Wheels | Passenger       |
      | Wheel | Vehicle Type | ATV/UTV Wheels   | ATV / UTV       |
      | Wheel | Vehicle Type | Truck Wheels     | Truck           |
      | Wheel | Vehicle Type | View all         | Wheels          |

  @dt
  @dtd
  @bba
  @web
  @5283
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different catagories related to TIRES (ALM# 5283) (DT & DTD) - Fitment links
    When I browse to the "<Page>" page with defaults
    And  I open the "TIRES" navigation link
    And  I click the "TIRES BY VEHICLE" menu option
    And  I select "2014" from the "Year" dropdown
    And  I close the fitment popup
    And  I open the "TIRES" navigation link
    And  I click the "TIRES BY SIZE" menu option
    And  I select "205" from the "Tire Width" dropdown
    And  I close the fitment popup

    Examples:
      | Page          |
      | Homepage      |
      | PLP           |
      | PDP           |
      | Shopping Cart |

  @dt
  @web
  @5283
  @verifyTireFitmentLinks
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different catagories related to TIRES (ALM# 5283) (DT) - Fitment links
    When I browse to the "<Page>" page with defaults
    And  I open the "TIRES" navigation link
    And  I click the "TIRES BY VEHICLE" menu option
    And  I select "2014" from the "Year" dropdown
    And  I close the fitment popup
    And  I open the "TIRES" navigation link
    And  I click the "TIRES BY SIZE" menu option
    And  I select "205" from the "Tire Width" dropdown
    And  I close the fitment popup

    Examples:
      | Page        |
      | Appointment |

  @dtd
  @web
  @5283
  @bba
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different catagories related to TIRES (ALM# 5283) (DTD) - Fitment links
    When I browse to the "<Page>" page with defaults
    And  I open the "TIRES" navigation link
    And  I click the "TIRES BY VEHICLE" menu option
    And  I select "2014" from the "Year" dropdown
    And  I close the fitment popup
    And  I open the "TIRES" navigation link
    And  I click the "TIRES BY SIZE" menu option
    And  I select "205" from the "Tire Width" dropdown
    And  I close the fitment popup

    Examples:
      | Page               |
      | Order Confirmation |

  @dt
  @web
  @5283
  @verifyTireCenterSectionLinks
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different catagories related to TIRES (ALM# 5283) (DT) - Center section links
  """TODO - Data only works in STG as page headers differ between QA and STG;"""
    When I browse to the "<Page>" page with defaults
    And  I open the "TIRES" navigation link
    And  I click the "<Tire Link>" menu option
    Then I am brought to the page with path "<Expected Path>"
    And  I am brought to the page with header "<Expected Header>"

    Examples:
      | Page          | Tire Link         | Expected Path            | Expected Header  |
      | Homepage      | Michelin Tires    | /tires/brands/michelin   | Michelin Tires   |
      | PLP           | Falken Tires      | /tires/brands/falken     | Falken Tires     |
      | PDP           | Goodyear Tires    | /tires/brands/goodyear   | Goodyear Tires   |
      | Shopping Cart | BFGoodrich Tires  | /tires/brands/bfgoodrich | BFGoodrich Tires |
      | Appointment   | All-Season Tires  | /tires/all-season        | All-Season       |
      | PDP           | Performance Tires | /tires/performance       | Performance      |
      | PLP           | All-Terrain Tires | /tires/all-terrain       | All-Terrain      |
      | Appointment   | Winter Tires      | /tires/winter            | Winter           |
      | Homepage      | ATV/UTV Tires     | /tires/atv-utv           | ATV / UTV        |
      | Shopping Cart | Passenger Tires   | /tires/passenger         | Passenger        |
      | Homepage      | Trailer Tires     | /tires/trailer           | Trailer          |
      | Appointment   | Truck Tires       | /tires/truck             | Truck            |

  @dtd
  @web
  @bba
  @5283
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different catagories related to TIRES (ALM# 5283) (DTD) - Center section links
  """TODO - Data only works in STG as page headers differ between QA and STG;"""
    When I browse to the "<Page>" page with defaults
    And  I open the "TIRES" navigation link
    And  I click the "<Tire Link>" menu option
    Then I am brought to the page with path "<Expected Path>"
    And  I am brought to the page with header "<Expected Header>"

    Examples:
      | Page               | Tire Link         | Expected Path         | Expected Header |
      | Homepage           | Cooper Tires      | /tires/brands/cooper  | Cooper Tires    |
      | PLP                | Nitto Tires       | /tires/brands/nitto   | Nitto Tires     |
      | PDP                | Hankook Tires     | /tires/brands/hankook | Hankook Tires   |
      | Shopping Cart      | Falken Tires      | /tires/brands/falken  | Falken Tires    |
      | PDP                | Performance Tires | /tires/performance    | Performance     |
      | PLP                | All-Terrain Tires | /tires/all-terrain    | All-Terrain     |
      | Order Confirmation | Winter Tires      | /tires/winter         | Winter          |
      | Homepage           | ATV/UTV Tires     | /tires/atv-utv        | ATV / UTV       |
      | Shopping Cart      | Passenger Tires   | /tires/passenger      | Passenger       |
      | Order Confirmation | Trailer Tires     | /tires/trailer        | Trailer         |

  @dt
  @dtd
  @web
  @5283
  @bba
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate different catagories related to TIRES (ALM# 5283) (DT & DTD) - Center section View All links
  """TODO: View All link is not showing up for Tires - Vehicle Type in QA. Working with Web Team to get it resolved"""
    When I browse to the "<Page>" page with defaults
    And  I open the "TIRES" navigation link
    And  I click the "<Tire Section>" View All link in the header
    Then I am brought to the page with path "<Expected Path>"
    And  I am brought to the page with header "<Expected Header>"

    Examples:
      | Page          | Tire Section | Expected Path | Expected Header |
      | Homepage      | Tire Brand   | /tires/brands | All Brands      |
      | Shopping Cart | Tire Type    | /tires        | Tires           |
      | PLP           | Vehicle Type | /tires        | Tires           |

  @at
  @dt
  @dtd
  @web
  @5524
  @5308
  @bba
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate logo for American tire (ALM #5524)
    When I search for store within "25" miles of "Chino"
    Then I should be redirected to the site for "America's Tire"
    When I browse to the "<Page>" page with defaults
    Then I verify the site logo
    When I click the discount tire logo
    Then I am brought to the homepage
    And  I verify the site logo

    Examples:
      | Page          |
      | Homepage      |
      | PLP           |
      | PDP           |
      | Shopping Cart |
      | Appointment   |

  @at
  @dt
  @web
  @5524
  @5308
  @validateLogoAppointment
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate logo for American tire (ALM #5524)
    When I browse to the "<Page>" page with defaults
    Then I verify the site logo
    When I click the discount tire logo
    Then I am brought to the homepage

    Examples:
      | Page        |
      | Appointment |

  @dtd
  @web
  @5524
  @5308
  @bba
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate logo for American tire (ALM #5524)
    When I browse to the "<Page>" page with defaults
    Then I verify the site logo
    When I click the discount tire logo
    Then I am brought to the homepage

    Examples:
      | Page               |
      | Checkout           |
      | Order Confirmation |

  @dt
  @at
  @9230
  @verifyMobileDefaultDropdownValues
  @mobile
  Scenario: HYBRIS_FIND_VEHICLES_VALIDATION_Mobile_DT_and_DTD (ALM# 9230)
    Then I verify all of the fitment menus are displayed
    And  I verify the default values of the "Vehicle" dropdowns
    When I navigate to Shop By "Tire Size"
    Then I verify the default values of the "Tire Size" dropdowns
    When I navigate to Shop By "Wheel Size"
    And  I click the fitment "Wheels" radio button
    Then I verify the default values of the "Wheel Size" dropdowns
    When I navigate to Shop By "Brand"
    Then I verify the default values of the "Brand" dropdowns

  @dt
  @at
  @9703
  @verifyMobileShopByVehicle
  @mobile
  Scenario Outline: Hybris_Mobile_Fitment_Component_ShopBy_Vehicle_TC01 (ALM# 9703)
  """TODO: This fails in iOS 10 due to Appium bug 7868"""
    When I search for store within "10" miles of "60652"
    And  I select make this "ILC 24" my store
    And  I go to the homepage
    And  I open the fitment popup
    And  I do a vehicle search with details "<Year1>" "<Make1>" "<Model1>" "<Trim1>" "none"
    And  I go to the homepage
    Then I verify that the Search button is disabled
    And  I verify that the Search button color is "grey"
    When I click the disabled dropdown with value "Make"
    Then I verify that the dropdown menu "has not" expanded
    When I select "<Year2>" from the "Year" dropdown
    And  I type "H" in the "Make" dropdown
    Then I verify that the dropdown is limited to values that start with "H"
    When I select "<Make2>" from the "Make" dropdown
    And  I select "<Model2>" from the "Model" dropdown
    And  I select "<Trim2>" from the "Trim" dropdown
    Then I verify that the Search button is enabled
    And  I verify that the Search button color is "red"
    When I click the "Year" dropdown
    Then I verify that the dropdown menu "has" expanded
    And  I verify that the "<Year2>" value in the dropdown is selected
    When I click the "Year" dropdown
    And  I click the "Make" dropdown
    Then I verify that the dropdown menu "has" expanded
    And  I verify that the "<Make2>" value in the dropdown is selected
    When I click the "Make" dropdown
    And  I click the "Model" dropdown
    Then I verify that the dropdown menu "has" expanded
    And  I verify that the "<Model2>" value in the dropdown is selected
    When I click the "Model" dropdown
    And  I click the "Trim" dropdown
    Then I verify that the dropdown menu "has" expanded
    And  I verify that the "<Trim2>" value in the dropdown is selected
    When I click the "Trim" dropdown
    And  I click on the "Find Products" button
    Then I should see the fitment panel page with vehicle "<Year2> <Make2>"
    When I go to the homepage
    And  I click the mobile homepage menu
    And  I select the fitment vehicle "<Year1> <Make1>"
    Then I should see the fitment panel page with vehicle "<Year1> <Make1>"

    Examples:
      | Year1 | Make1  | Model1 | Trim1     | Year2 | Make2 | Model2 | Trim2    |
      | 2014  | Toyota | Camry  | Hybrid SE | 2015  | Honda | Accord | Coupe EX |

  @dt
  @at
  @9705
  @verifyMobileShopByBrandTire
  @mobile
  Scenario Outline: Hybris_Mobile_Fitment_Component_ShopBy_Brand_Tire_TC03 (ALM# 9705)
  """TODO: This fails in iOS 10 due to Appium bug 7868"""
    When I search for store within "25" miles of "86001"
    And  I select make this "AZF 01" my store
    And  I go to the homepage
    Then I verify that the Search button is disabled
    And  I verify that the Search button color is "grey"
    When I navigate to Shop By "Brand"
    And  I click on the "Tires" Link
    Then I verify that the "Tires" radio button is "enabled"
    And  I verify that the "Wheels" radio button is "disabled"
    When I type "MI" in the "brand" dropdown
    Then I verify that the items in the drop down display in alphabetically order
    When I select "<Brand>" from the expanded dropdown
    Then I verify that a dropdown has the value "<Brand>"
    And  I verify that the Search button color is "red"
    When I click on the "Find Tires" button
    Then I am brought to the page with header "<Brand>"
    When I navigate to Shop By "Brand"
    And  I click on the "View all brands" Link
    Then I am brought to the page with header "All Brands"

    Examples:
      | Brand    |
      | michelin |

  @dt
  @at
  @9704
  @verifyMobileShopByTireSize
  @mobile
  Scenario Outline: Hybris_Mobile_Fitment_Component_Home_ShopBy_Size_Tire_TC02 (ALM# 9704)
  """NOTE: Please refer to (ALM# 9703) for any necessary step explanations"""
    When I search for store within "10" miles of "60652"
    And  I select make this "ILC 24" my store
    And  I go to the homepage
    Then I verify that the Search button is disabled
    And  I navigate to Shop by Size
    And  I select "<TireWidth>" from the "Tire Width" dropdown
    And  I select "<AspectRatio>" from the "Aspect Ratio" dropdown
    And  I select "<TireDiameter>" from the "Tire Diameter" dropdown
    Then I verify that the Search button is enabled
    And  I verify that the Search button color is "red"
    When I click the "Tire Width" dropdown
    Then I verify that the dropdown menu "has" expanded
    And  I verify that the "<TireWidth>" value in the dropdown is selected
    When I click the "Tire Width" dropdown
    And  I click the "Aspect Ratio" dropdown
    Then I verify that the dropdown menu "has" expanded
    And  I verify that the "<AspectRatio>" value in the dropdown is selected
    When I click the "Aspect Ratio" dropdown
    And  I click the "Tire Diameter" dropdown
    Then I verify that the dropdown menu "has" expanded
    And  I verify that the "<TireDiameter>" value in the dropdown is selected
    When I click the "Tire Diameter" dropdown
    And  I click on the "Find Tires" button
    Then I verify the "PLP" results banner message contains "<String>"

    Examples:
      | TireWidth | AspectRatio | TireDiameter | String    |
      | 185       | 65          | 14           | 185/65-14 |

  @dt
  @9715
  @verifyMobileShopByVehicleStaggered
  @mobile
  Scenario Outline: Hybris_Mobile_Fitment_Component_ShopBy_Vehicle_Staggered (ALM# 9715)
    When I go to the homepage
    Then I verify that "Year" dropdown arrow is pointing "downwards"
    When I click the disabled dropdown with value "Make"
    Then I verify that the dropdown menu "has not" expanded
    And  I verify that the Search button is disabled
    And  I verify that the Search button color is "grey"
    When I click the "Year" dropdown
    Then I verify that "Year" dropdown arrow is pointing "upwards"
    When I select "<Year>" from the "Year" dropdown
    Then I verify that the "<Year>" value in the dropdown is selected
    And  I verify that "Make" dropdown arrow is pointing "upwards"
    When I select "<Make>" from the "Make" dropdown
    Then I verify that the "<Make>" value in the dropdown is selected
    And  I verify that "Make" dropdown arrow is pointing "downwards"
    And  I verify that "Model" dropdown arrow is pointing "upwards"
    When I select "<Model>" from the "Model" dropdown
    Then I verify that the "<Model>" value in the dropdown is selected
    And  I verify that "Model" dropdown arrow is pointing "downwards"
    And  I verify that "Trim" dropdown arrow is pointing "upwards"
    When I select "<Trim>" from the "Trim" dropdown
    Then I verify that the "<Trim>" value in the dropdown is selected
    And  I verify that "Trim" dropdown arrow is pointing "downwards"
    When I click on the "Select Assembly" button
    And  I click the "Assembly" dropdown
    Then I verify that "Assembly" dropdown arrow is pointing "upwards"
    When I select "<Assembly>" from the "Assembly" dropdown
    Then I verify that "Assembly" dropdown arrow is pointing "downwards"
    And  I verify that the "<Assembly>" value in the dropdown is selected
    And  I verify that the Search button is enabled
    And  I verify that the Search button color is "red"
    When I click on the "Find Products" button
    Then I should see the fitment panel page with vehicle "<Year> <Make>"

    Examples:
      | Year | Make   | Model | Trim       | Assembly                            |
      | 2010 | Nissan | 370Z  | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL |

  @dt
  @9717
  @mobile
  @mobilefitmentShopBySizeWheels
  Scenario Outline: Hybris_Mobile_Fitment_Component_Home_ShopBy_Size_Wheels (ALM# 9717)
    When I navigate to Shop by Size
    Then I verify that the Search button is disabled
    When I click the fitment "Wheels" radio button
    Then I verify that the "Wheels" radio button is "enabled"
    When I click the disabled dropdown with value "Wheel Width"
    Then I verify that the dropdown menu "has not" expanded
    When I select "<WheelDiameter>" from the "Wheel Diameter" dropdown
    And  I select "<WheelWidth>" from the "Wheel Width" dropdown
    And  I select "<BoltPattern>" from the "Bolt Pattern" dropdown
    Then I verify that the Search button is enabled
    And  I verify that the Search button color is "red"
    When I click on the "Find Wheels" button
    Then I verify the "PLP" results banner message contains "<String>"

    Examples:
      | WheelWidth | BoltPattern       | WheelDiameter | String                         |
      | 6.5        | 5-114.3 MM/5-4.5" | 15            | 15X6.5 5-114.3 mm/5-4.5"wheels |

  @dt
  @9706
  @mobile
  Scenario Outline: Hybris_Mobile_Fitment Modal_Menu_Shop By Vehicle_TC01 (ALM# 9706)
  """TODO: This fails in iOS 10 due to Appium bug 7868"""
    When I click the mobile homepage menu
    And  I click on "ADD VEHICLE" menu link
    Then I verify all of the fitment menus are displayed
    When I click the disabled dropdown with value "Make"
    Then I verify that the dropdown menu "has not" expanded
    And  I verify that the Search button is disabled
    And  I verify that the Search button color is "grey"
    When I click the "Year" dropdown
    Then I verify that "Year" dropdown arrow is pointing "upwards"
    When I select "<Year1>" from the "Year" dropdown
    Then I verify that the "<Year1>" value in the dropdown is selected
    And  I verify that "Make" dropdown arrow is pointing "upwards"
    When I type "T" in the "Make" dropdown
    Then I verify that the dropdown is limited to values that start with "T"
    When I select "<Make1>" from the "Make" dropdown
    Then I verify that the "<Make1>" value in the dropdown is selected
    And  I verify that "Make" dropdown arrow is pointing "downwards"
    And  I verify that "Model" dropdown arrow is pointing "upwards"
    When I select "<Model1>" from the "Model" dropdown
    Then I verify that the "<Model1>" value in the dropdown is selected
    And  I verify that "Model" dropdown arrow is pointing "downwards"
    And  I verify that "Trim" dropdown arrow is pointing "upwards"
    When I select "<Trim1>" from the "Trim" dropdown
    Then I verify that the "<Trim1>" value in the dropdown is selected
    And  I verify that "Trim" dropdown arrow is pointing "downwards"
    And  I verify that the Search button is enabled
    And  I verify that the Search button color is "red"
    When I click on the "Find Products" button
    Then I should see the fitment panel page with vehicle "<Year1> <Make1>"
    When I go to the homepage
    And  I click the mobile homepage menu
    Then I verify that My Vehicles displays "<Year1> <Make1>" as the current vehicle
    When I go to the homepage
    And  I do a vehicle search with details "<Year2>" "<Make2>" "<Model2>" "<Trim2>" "none"
    And  I go to the homepage
    And  I click the mobile homepage menu
    Then I verify that My Vehicles displays "<Year2> <Make2>" as the current vehicle
    When I select recent vehicle "<Year1> <Make1>"
    Then I should see the fitment panel page with vehicle "<Year1> <Make1>"
    When I go to the homepage
    And  I click the mobile homepage menu
    Then I verify that My Vehicles displays "<Year1> <Make1>" as the current vehicle
    When I go to the homepage
    And  I do a vehicle search with details "<Year3>" "<Make3>" "<Model3>" "<Trim3>" "none"
    When I go to the homepage
    And  I do a vehicle search with details "<Year4>" "<Make4>" "<Model4>" "<Trim4>" "none"
    And  I go to the homepage
    And  I click the mobile homepage menu
    Then I verify that My Vehicles displays the vehicle limit reached message

    Examples:
      | Year1 | Make1  | Model1 | Trim1     | Year2 | Make2 | Model2 | Trim2    | Year3 | Make3     | Model3   | Trim3 | Year4 | Make4 | Model4 | Trim4    |
      | 2014  | Toyota | Camry  | Hybrid SE | 2015  | Honda | Accord | Coupe EX | 2010  | Chevrolet | Corvette | Base  | 2012  | Honda | Civic  | Coupe DX |

  @dt
  @at
  @footerValidationWithoutBack
  @web
  Scenario: Verify working links in the footer
  """TODO: ALM NUMBER PENDING, fails do to Defect ID 5629 - Wheel vs. Wheels link text
    TODO: Verify if this is ever coming back. It existed
    when I select "footer" "Promotions"
    then I am brought to the page with header "Active Promotions""""
    When I select "footer" "Tire Search"
    Then I am brought to the page with header "Tires"
    When I select "footer" "Wheel Search"
    Then I am brought to the page with header "Wheels"
    When I select "footer" "Services"
    Then I am brought to the page with header "Wheel and Tire Services"
    When I select "footer" "Customer Care"
    Then I am brought to the page with header "Customer Service"
    When I select "footer" "Store Locator"
    Then I am brought to the page with header "Store Locator"
    When I select "footer" "Appointments"
    Then I am brought to the page with header "Schedule appointment"
    When I select "footer" "Return Policy"
    Then I am brought to the page with header "Our Return Policy"
    When I select "footer" "Tire Safety"
    Then I am brought to the page with header "Tire Safety"
    When I select "footer" "Tire Size Calculator"
    Then I am brought to the page with header "Tire Size and Conversion Calculator"
    When I select "footer" "Check Tire Pressure"
    Then I verify the second level header "HOW TO CHECK TIRE PRESSURE" on the page
    When I select "footer" "More Topics..."
    Then I am brought to the page with header "Tips & Guides"
    When I select "footer" "Site Map"
    Then I am brought to the page with header "Site Map"

  @dt
  @at
  @socialMediaValidation
  @web
  Scenario: Verify working social media links in the footer
  """TODO: ALM NUMBER PENDING"""
    When I select "footer" "facebook"
    Then I verify the current URL contains "facebook.com/DiscountTire"
    When I go to the homepage
    And  I select "footer" "twitter"
    Then I verify the current URL contains "twitter.com/DiscountTire"
    When I go to the homepage
    And  I select "footer" "pinterest"
    Then I verify the current URL contains "pinterest.com/tiresdotcom/"
    When I go to the homepage
    And  I select "footer" "google plus"
    Then I verify the current URL contains "plus.google.com/+DiscountTire"
    When I go to the homepage
    And  I select "footer" "instagram"
    Then I verify the current URL contains "instagram.com/discount_tire"

  @dt
  @at
  @footerWithBack
  @web
  Scenario: Verify working links in the footer - requires navigation 'back'
  """TODO: ALM NUMBER PENDING"""
    When I select "footer" "Apply Now"
    Then I verify "Please Read Before Applying" appears on the Apply Now page
    When I go to the homepage
    And  I select "footer" "Commercial Payments"
    Then I verify "Welcome to E-Bill Express!" appears on the Commercial Payment page

  @dt
  @at
  @footerWithAdditionalLinks
  @web
  Scenario: Verify working links in the footer - and internal page links
  """TODO: ALM NUMBER PENDING
    TODO: About US Content is missing in QA & STG env since 2 weeks
    TODO: Enable the validation step once content available otherwise it's causing cascading failures
    then I am brought to the page with header 'About Discount Tire'"""
    When I select "footer" "About Us"
    And  I select "footer" "Contact Us"
    Then I am brought to the page with header "Contact Us"
    When I select "footer" "Motorsports"
    Then I am brought to the page with header "Discount Tire Motorsports"
    When I select "footer" "Careers"
    Then I am brought to the page with header "Careers at Discount Tire"
    When I select "footer" "Regional Offices"
    Then I am brought to the page with header "Regional Offices"
    When I select "footer" "Our Story"
    Then I am brought to the page with header "Our Story: How We Started"
    When I click on the "Contact Us" Link
    Then I am brought to the page with header "Contact Us"
    When I click on the "Motorsports" Link
    Then I am brought to the page with header "Discount Tire Motorsports"
    When I click on the "Careers" Link
    Then I am brought to the page with header "Careers at Discount Tire"
    When I click on the "Our Story" Link
    Then I am brought to the page with header "Our Story: How We Started"
    When I click on the "Regional Offices" Link
    Then I am brought to the page with header "Regional Offices"
    When I click on the "Careers" Link
    Then I am brought to the page with header "Careers at Discount Tire"
    When I click on the "Career FAQs" Link
    Then I am brought to the page with header "Q&A: Working at Discount Tire"

  @dt
  @at
  @contactUs
  @web
  Scenario: Verify elements and working links on the Contact Us page
  """TODO: ALM NUMBER PENDING"""
    When I select "footer" "Contact Us"
    Then I am brought to the page with header "Contact Us"
    And  I verify the second level header "EMAIL" on the page
    And  I verify the second level header "REGIONAL OFFICES" on the page
    And  I verify the second level header "SOCIAL MEDIA" on the page
    When I click on the "Regional Offices" Link
    Then I am brought to the page with header "Regional Offices"
    When I click on the "Contact Us" Link
    Then I am brought to the page with header "Contact Us"
    When I click on the "Motorsports" Link
    Then I am brought to the page with header "Discount Tire Motorsports"
    When I click on the "Careers" Link
    Then I am brought to the page with header "Careers at Discount Tire"
    When I click on the "Our Story" Link
    Then I am brought to the page with header "Our Story: How We Started"
    When I click on the "Regional Offices" Link
    Then I am brought to the page with header "Regional Offices"
    When I click on the "Careers" Link
    Then I am brought to the page with header "Careers at Discount Tire"
    When I click on the "Career FAQs" Link
    Then I am brought to the page with header "Q&A: Working at Discount Tire"

  @dt
  @dtd
  @web
  @5287
  @bba
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate link for Tips and Guide (DT & DTD) (ALM #5287)
    When I browse to the "<Page>" page with defaults
    And  I open the "TIPS & GUIDES" navigation link
    Then I am brought to the page with header "Tips & Guides"

    Examples:
      | Page          |
      | Homepage      |
      | PLP           |
      | PDP           |
      | Shopping Cart |

  @dt
  @web
  @5287
  @tipsAndGuidesNavigationLinkAppointment
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate link for Tips and Guide (DT & DTD) (ALM #5287)
    When I browse to the "<Page>" page with defaults
    And  I open the "TIPS & GUIDES" navigation link
    Then I am brought to the page with header "Tips & Guides"

    Examples:
      | Page        |
      | Appointment |

  @dtd
  @web
  @5287
  @bba
  @UiUxBBA
  Scenario Outline: HYBRIS_GLOBAL AND NAVIGATION_HEADER_Validate link for Tips and Guide (DT & DTD) (ALM #5287)
    When I browse to the "<Page>" page with defaults
    And  I open the "TIPS & GUIDES" navigation link
    Then I am brought to the page with header "Tips & Guides"

    Examples:
      | Page               |
      | Order Confirmation |

  @dt
  @at
  @9678
  @checkAvailabilityHideInStock
  @web
  Scenario Outline: Check Product Availability Hide InStock (ALM #9678)
  """18012 used for DT + AT. 78157 used for STG"""
    When I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I select the "Check Inventory" link for item "<ItemCode>"
    Then I should verify that the Check Availability popup loaded
    And  I should verify that In Stock Label is not displayed on Check Inventory popup
    And  I should verify that default store MY STORE label is at top and visible
    When I close the Check Inventory popup
    Then I should see product detail page with "<ProductName>"

    Examples:
      | ProductName   | ItemCode |
      | Sincera SN828 | 18012    |
      | Solution      | 78157    |

  @dt
  @at
  @dtd
  @bba
  @9791
  @9802
  @checkAvailabilityChangeMessage
  @mobile
  @UiUxBBA
  Scenario Outline: Hybris_Mobile_Check_Availability_Page_Functionality_PLP_FreeText Search_TC01 (ALM# 9791)
    When I do a free text search for "<ItemCode>"
    And  I select the "Check Inventory" link for item "<ItemCode>"
    Then I should verify that the Check Availability popup loaded
    And  I should verify that the Check Inventory headline is "Check Availability"
    And  I should verify that the Check Inventory product image is displayed
    And  I should verify that the Product Info shows "<Brand>", "<ProductName>", and "<TireSize>"
    And  I should verify price is not blank
    When I enter a quantity of "100"
    Then I should verify that a "quantity" error message is displayed
    And  I should verify that the Add To Cart button is disabled
    When I enter a quantity of "F"
    Then I should verify that a "validity" error message is displayed
    And  I should verify that the Add To Cart button is disabled
    When I enter a quantity of "<Quantity>"
    Then I should verify that the show quantity filter is displayed with "<Quantity>"
    When I enter a zipcode of "1234567"
    Then I should verify that a zip code error message is displayed
    When I enter a zipcode of "df787"
    Then I should verify that a zip code error message is displayed
    When I enter a zipcode of "<Zipcode>"
    And  I click go and wait for results to load
    Then the first store listed should be within "75" miles
    When I click the "In stock" filter
    Then I should verify the first store has stock quantity greater than "0"
    When I click the "Show more" filter
    Then I should verify the first store has stock quantity greater than "<Quantity>"
    When I select store "2"
    Then I should verify that store "2" is now the current store
    When I close the Check Inventory popup
    Then I should see "<ProductName>" on the product list page

    Examples:
      | ItemCode | Brand    | TireSize   | ProductName  | Quantity | Zipcode |
      | 34302    | Michelin | 195/65 R15 | Defender A/S | 6        | 85250   |

  @bba
  @9665
  @greenBannerFormattingTires
  @web
  @UiUxBBA
  Scenario Outline: Verify Green Banner Formatting: Tires (ALM #9665)
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the fitment box option has a value of "<Default Tire Size>"
    When I select the "<New Tire Size>" fitment box option
    Then I verify the selected fitment box option has a value of "<New Tire Size>"
    When I select a fitment option "<TireSize>"
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PLP" banner color is "Green"
    When I select the first product result image
    Then I verify the message on the "Tires" "PDP" banner contains "<TireSize>" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PDP" banner color is "Green"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | TireSize  | Default Tire Size | New Tire Size |
      | 2012 | Honda | Civic | Coupe EX | none     | Optional Tire Sizes | 185/70-14 | 16                | 14            |

  @bba
  @9665
  @greenBannerFormattingTires
  @mobile
  @UiUxBBA
  Scenario Outline: Mobile - Verify Green Banner Formatting: Tires (ALM #9665)
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select a fitment option "<FitmentOption>"
    Then I verify the fitment box option has a value of "<Default Tire Size>"
    When I select the "<New Tire Size>" fitment box option
    Then I verify the selected fitment box option has a value of "<New Tire Size>"
    When I select a fitment option "<TireSize>"
    Then I verify the message on the "Tires" "PLP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PLP" banner color is "Green"
    When I select the first product result image
    Then I verify the message on the "Tires" "PDP" banner contains "" "<Year>" "<Make>" and "<Model>"
    And  I verify the "PDP" banner color is "Green"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption       | TireSize  | Default Tire Size | New Tire Size |
      | 2012 | Honda | Civic | Coupe EX | none     | Optional Tire Sizes | 185/70-14 | 16                | 14            |

  @dt
  @at
  @9679
  @checkAvailabilitySpecialOrderLabelVisible
  @web
  Scenario Outline: Check Product Availability Special Order Label Visible (ALM #9679)
  """TODO: Data issue: MESH X does not appear in QA - DEFECT_ID 6598
    TODO: "Special Order" label does not appear in STG environment"""
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
      | Mesh X        | 49240    |

  @dt
  @at
  @navigationPageUrlsAndBreadcrumbs
  @web
  Scenario Outline: Extra wheel and tire page URL path has a response code under 400 and expected breadcrumbs exist
    When I request the URL with "<Path>"
    Then I should get a response below 400
    And the breadcrumbs "<Breadcrumbs>" exist for path "<Path>"

    Examples:
      | Path                       | Breadcrumbs                        |
      | /tires/all-season-catalog  | Home, Tires, All-Season, Products  |
      | /tires/all-terrain-catalog | Home, Tires, All-Terrain, Products |
      | /tires/winter-catalog      | Home, Tires, Winter, Products      |
      | /tires/performance-catalog | Home, Tires, Performance, Products |
      | /tires/truck-catalog       | Home, Tires, Truck, Products       |
      | /tires/atv-utv-catalog     | Home, Tires, ATV / UTV, Products   |
      | /tires/trailer-catalog     | Home, Tires, Trailer, Products     |
      | /tires/passenger-catalog   | Home, Tires, Passenger, Products   |
      | /wheels/chrome-catalog     | Home, Wheels, Chrome, Products     |
      | /wheels/painted-catalog    | Home, Wheels, Painted, Products    |
      | /wheels/machined-catalog   | Home, Wheels, Machined, Products   |
      | /wheels/mesh-catalog       | Home, Wheels, Mesh, Products       |
      | /wheels/truck-catalog      | Home, Wheels, Truck, Products      |
      | /wheels/atv-utv-catalog    | Home, Wheels, ATV / UTV, Products  |
      | /wheels/trailer-catalog    | Home, Wheels, Trailer, Products    |
      | /wheels/passenger-catalog  | Home, Wheels, Passenger, Products  |

  @dt
  @at
  @tipsAndGuidesBreadcrumbs
  @web
  Scenario Outline: Tips & guides page URL path has a response code under 400 and expected breadcrumbs exist
    When I request the URL with "<Path>"
    Then I should get a response below 400
    And the breadcrumbs "<Breadcrumbs>" exist for path "<Path>"

    Examples:
      | Path                             | Breadcrumbs                                         |
      | /learn                           | Home, Tips & Guides                                 |
      | /learn/air-pressure-fuel-economy | Home, Tips & Guides, Air Pressure & Fuel Efficiency |
      | /learn/buying-tires              | Home, Tips & Guides, Choosing the Right Tire        |
      | /learn/tire-pressure             | Home, Tips & Guides, Correct Air Pressure           |
      | /learn/tire-types                | Home, Tips & Guides, Types of Tires                 |
      | /learn/tire-repair               | Home, Tips & Guides, Tire Repair Guidelines         |
      | /learn/load-range-load-index     | Home, Tips & Guides, Load Range & Load Index        |
      | /learn/plus-sizing               | Home, Tips & Guides, Plus Sizing Tires & Wheels     |
      | /learn/tire-balancing            | Home, Tips & Guides, Tire Balancing                 |
      | /learn/tire-rotations            | Home, Tips & Guides, Tire Rotations                 |
      | /learn/reading-tire-sidewall     | Home, Tips & Guides, Reading Tire Sidewall          |
      | /learn/replacing-2tires          | Home, Tips & Guides, Replacing Two Tires            |
      | /learn/road-force-balancing      | Home, Tips & Guides, Road Force Balancing           |
      | /learn/sidewall-inspection       | Home, Tips & Guides, Sidewall Inspection            |
      | /learn/speed-rating              | Home, Tips & Guides, Speed Ratings                  |
      | /learn/stopping-distance         | Home, Tips & Guides, Stopping Distance              |
      | /learn/tire-aging                | Home, Tips & Guides, The Life of a Tire             |
      | /learn/tire-construction         | Home, Tips & Guides, Tire Construction              |
      | /learn/tire-dimensions           | Home, Tips & Guides, Tire Dimensions                |
      | /learn/tire-ratings              | Home, Tips & Guides, Tire Ratings                   |
      | /learn/tire-safety               | Home, Tips & Guides, Tire Safety                    |
      | /learn/tire-sipes                | Home, Tips & Guides, Tire Sipes                     |
      | /learn/tire-size-calculator      | Home, Tips & Guides, Tire Size Calculator           |
      | /learn/tire-tread-depth          | Home, Tips & Guides, Measuring Tread Depth          |
      | /learn/tires-below-45            | Home, Tips & Guides, Tires Below 45 Degrees         |
      | /learn/trailer-tire-faqs         | Home, Tips & Guides, Trailer Tire FAQs              |
      | /learn/utqg                      | Home, Tips & Guides, UTQG                           |
      | /learn/winter-tire-changeover    | Home, Tips & Guides, Winter Tire Changeover         |
      | /learn/winter-tire-faqs          | Home, Tips & Guides, Winter Tire FAQs               |
      | /learn/change-a-tire             | Home, Tips & Guides, Changing a Tire                |
      | /learn/check-tire-pressure       | Home, Tips & Guides, Checking Air Pressure          |
      | /learn/clean-tires               | Home, Tips & Guides, Cleaning Tires                 |
      | /learn/clean-wheels              | Home, Tips & Guides, Cleaning Wheels                |
      | /learn/bolt-pattern              | Home, Tips & Guides, Bolt Patterns                  |
      | /learn/wheel-alignment           | Home, Tips & Guides, Wheel Alignment                |
      | /learn/wheel-construction        | Home, Tips & Guides, Wheel Construction             |
      | /learn/offset-backspace          | Home, Tips & Guides, Wheel Offset vs. Backspacing   |
      | /learn/wheel-size                | Home, Tips & Guides, Wheel Size                     |
      | /learn/wheel-torque              | Home, Tips & Guides, Wheel Torque                   |
      | /learn/fuel-calculator           | Home, Tips & Guides, Fuel Calculator                |
      | /learn/tire-glossary             | Home, Tips & Guides, Glossary                       |
      | /learn/tpms-facts                | Home, Tips & Guides, TPMS Facts                     |
      | /learn/tpms-rebuild-kits         | Home, Tips & Guides, TPMS Rebuild Kits              |

  @validateStatusOfAllHomepageLinks
  @web
  Scenario: Verify that all links on the homepage (footer links, navigation links, etc) have a response below 400
    Given All links on the page with path "/" should have a status below 400

  @bba
  @scheduleAppointmentCustomerInfoPageUrlValidation
  @web
  @mobile
  @UiUxBBA
  Scenario Outline: Verify element and breadcrumbs on Schedule Appointment Customer Info page
    When I schedule an appointment for my current store
    And  I select service option(s): "<ServiceOptions>"
    And  I select 'Set Appointment Details' for Date and Time
    And  I select first available appointment date
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    Then I verify the "<Breadcrumbs>" link in the breadcrumb container
    And  I should see text "<ElementText>" present in the page source

    Examples:
      | ServiceOptions                | Customer            | ElementText             | Breadcrumbs                     |
      | New Tires/Wheels Consultation | DEFAULT_CUSTOMER_AZ | Your appointment number | Home, Appointment, Confirmation |

  @bba
  @tireUrlsAndBreadcrumbs
  @web
  @UiUxBBA
  Scenario Outline: Verify Tire breadcrumbs are present and Url's are correct
  """TODO: Currently failes in QA due to Defect ID 6270"""
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    Then I am brought to the page with path "<Path>"
    And  I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Path          | Breadcrumbs             |
      | /tires/brands | Home, Tires, All Brands |

  @bba
  @tireUrlsAndBreadcrumbs
  @mobile
  @UiUxBBA
  Scenario Outline: Mobile - Verify Tire breadcrumbs are present and Url's are correct
    When I click the mobile homepage menu
    And  I click on "Tire" menu link
    And  I click on "Tire Brand" menu link
    And  I click on "Michelin Tires" menu link
    Then I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Breadcrumbs                       |
      | Home, Tires, All Brands, Michelin |

  @bba
  @wheelsUrlsAndBreadcrumbs
  @web
  @UiUxBBA
  Scenario Outline: Verify Wheel breadcrumbs are present and Url's are correct
    When I open the "WHEELS" navigation link
    And  I click the "WHEELS BY BRAND" menu option
    Then I am brought to the page with path "<Path>"
    And  I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Path           | Breadcrumbs              |
      | /wheels/brands | Home, Wheels, All Brands |

  @bba
  @wheelsUrlsAndBreadcrumbs
  @mobile
  @UiUxBBA
  Scenario Outline: Mobile - Verify Wheel breadcrumbs are present and Url's are correct
    When I click the mobile homepage menu
    And  I click on "Wheel" menu link
    And  I click on "Wheel Brand" menu link
    And  I click on "View all" menu link
    Then I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Breadcrumbs              |
      | Home, Wheels, All Brands |

  @bba
  @serviceUrlAndBreadcrumbs
  @web
  @UiUxBBA
  Scenario Outline: Verify Services page breadcrumbs are present and Url's are correct
    When I open the "SERVICES" navigation link
    And  I click on the "SCHEDULE APPOINTMENT" Link
    Then I verify the current URL contains "<Path>"
    And  I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Path                  | Breadcrumbs                 |
      | /schedule-appointment | Home, Appointment, Services |

  @bba
  @serviceUrlAndBreadcrumbs
  @mobile
  @UiUxBBA
  Scenario Outline: Mobile - Verify Services page breadcrumbs are present and Url's are correct
    When I click on "APPOINTMENTS" header link
    And  I click on the "SCHEDULE APPOINTMENT" Link
    Then I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Breadcrumbs                 |
      | Home, Appointment, Services |

  @bba
  @footerUrlsAndBreadcrumbs
  @web
  @UiUxBBA
  Scenario Outline: Verify About Us breadcrumbs are present and Url's are correct
  """TODO: Careers page got redesigned with new branding and No Breadcrumbs
    | Careers         | /careers                           | Home, Careers                      |"""
    When I select "footer" "<Link>"
    Then I verify the current URL contains "<Path>"
    And  I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Link             | Path                               | Breadcrumbs                        |
      | About Us         | /about-us                          | Home, About Us                     |
      | Our Story        | /about-us/our-story                | Home, About Us, Our Story          |
      | Motorsports      | /about-us/motorsports              | Home, About Us, Motorsports        |
      | Customer Care    | /customer-service                  | Home, Customer Care                |
      | Store Locator    | /store-locator                     | Home, Store Locator                |
      | Appointments     | /schedule-appointment              | Home, Appointment, Services        |
      | Return Policy    | /customer-service/return-policy    | Home, Customer Care, Return Policy |
      | Regional Offices | /customer-service/regional-offices | Home, About Us, Regional Offices   |
      | Contact Us       | /customer-service/contact-us       | Home, About Us, Contact Us         |
      | Services         | /services                          | Home, Services                     |

  @bba
  @footerUrlsAndBreadcrumbs
  @mobile
  @UiUxBBA
  Scenario Outline: Mobile - Verify About Us breadcrumbs are present and Url's are correct
  """TODO: Careers page got redesigned with new branding and No Breadcrumbs
    | Careers         | /careers                           | Home, Careers                      |"""
    When I select "footer" "<Link>"
    Then I verify the "<Breadcrumbs>" link in the breadcrumb container

    Examples:
      | Link             | Breadcrumbs                        |
      | About Us         | Home, About Us                     |
      | Our Story        | Home, About Us, Our Story          |
      | Motorsports      | Home, About Us, Motorsports        |
      | Customer Care    | Home, Customer Care                |
      | Store Locator    | Home, Store Locator                |
      | Appointments     | Home, Appointment, Services        |
      | Return Policy    | Home, Customer Care, Return Policy |
      | Regional Offices | Home, About Us, Regional Offices   |
      | Contact Us       | Home, About Us, Contact Us         |
      | Services         | Home, Services                     |

  @dt
  @at
  @tireSizeCalcValidateDisplay
  @web
  Scenario: Tire Size Calc validate default display
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Size Calculator"
    Then I verify Tire Size calculator controls and expected sections are displayed

  @dt
  @at
  @tireSizeCalcForCommonSizeUpdatesResultsTable
  @web
  Scenario Outline: Tire Size Calc for common tire size updates the results table
  """TODO: Tire size calculator not updating with IE"""
    When I select "footer" "Tire Size Calculator"
    Then I am brought to the page with header "Tire Size and Conversion Calculator"
    When I enter "current" tire size of "<CurrentTireDiameter>" / "<CurrentTireWidth>" R "<CurrentWheelDiameter>" into the Tire Size Calculator
    Then I verify the Tire Size Calculator results table is updated
    And  I verify the Side by Side Comparison section is updated with a size of "<CurrentTireDiameter>" / "<CurrentTireWidth>" R "<CurrentWheelDiameter>"
    When I enter "new" tire size of "<NewTireDiameter>" / "<NewTireWidth>" R "<NewWheelDiameter>" into the Tire Size Calculator
    Then I verify the Tire Size Calculator results table is updated
    And  I verify the Side by Side Comparison section is updated with a size of "<NewTireDiameter>" / "<NewTireWidth>" R "<NewWheelDiameter>"

    Examples:
      | CurrentTireDiameter | CurrentTireWidth | CurrentWheelDiameter | NewTireDiameter | NewTireWidth | NewWheelDiameter |
      | 215                 | 55               | 17                   | 215             | 60           | 16               |
      | 245                 | 75               | 17                   | 245             | 75           | 16               |

  @dt
  @at
  @tireSizeCalcSpeedometerReadingUpdatesWithNewTireSizes
  @web
  Scenario: Tire Size Calc speedometer reads section updates speed with new tire sizes
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Size Calculator"
    And  I enter "current" tire size of "125" / "70" R "16" into the Tire Size Calculator
    And  I enter "new" tire size of "125" / "80" R "16" into the Tire Size Calculator
    And  I enter a speed of "70" as the speedometer reading
    Then I verify the traveling speed is updated to "73"

  @dt
  @at
  @generalTg
  @web
  Scenario: Verify General section links
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Fuel Calculator"
    Then I am brought to the page with header "Fuel Calculator: The Cost of Low Air Pressure"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Glossary of Terms"
    Then I am brought to the page with header "Tire Glossary"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "TPMS Facts & Troubleshooting"
    Then I am brought to the page with header "Tire Pressure Monitoring Systems (TPMS) Facts"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "TPMS Rebuild Kits"
    Then I am brought to the page with header "TPMS Rebuild Kits"

  @dt
  @at
  @tiresTg
  @web
  Scenario: Verify Tires section links
  """TODO: revisit this when content returns to page (Defect ID 6253)
    then I am brought to the page with header "Correct Air Pressure
    TODO: Verify this list element has been purposefully removed
    and  I open the "TIPS & GUIDES" navigation link
    and  I click the list element "Proper Tire Rotation"
    then I am brought to the page with header 'The Basics of Tire Rotations'"""
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Air Pressure & Fuel Economy"
    Then I am brought to the page with header "Correct Tire Inflation Improves Fuel Economy"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Choosing The Right Tire"
    Then I am brought to the page with header "Choosing the Right Tire"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Air Pressure"
    And  I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Different Types of Tires"
    Then I am brought to the page with header "Different Types of Tires"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Flat Tire Repair"
    Then I am brought to the page with header "Proper Tire Repair"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Load Range vs Load Index"
    Then I am brought to the page with header "Load Range and Load Index"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Plus Sizing Tires and Wheels"
    Then I am brought to the page with header "Plus Sizing Tires & Wheels"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Balancing"
    Then I am brought to the page with header "Tire Balancing"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Reading The Tire Sidewall"
    Then I am brought to the page with header "How to Read a Tire Sidewall"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Replacing Two Tires"
    Then I am brought to the page with header "Replacing Two Tires"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Road Force Balancing"
    Then I am brought to the page with header "Road Force Balancing"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Sidewall Inspection"
    Then I am brought to the page with header "Sidewall Inspection"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Speed Ratings"
    Then I am brought to the page with header "What Are Tire Speed Ratings?"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Stopping Distance"
    Then I am brought to the page with header "Stopping Distance"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "The Life of a Tire"
    Then I am brought to the page with header "The Life of a Tire"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Construction"
    Then I am brought to the page with header "Tire Construction"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Dimensions"
    Then I am brought to the page with header "Tire Dimensions"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Ratings"
    Then I am brought to the page with header "Tire Ratings and Reviews"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Safety"
    Then I am brought to the page with header "Tire Safety"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Sipes"
    Then I am brought to the page with header "Tire Sipes"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Size Calculator"
    Then I am brought to the page with header "Tire Size and Conversion Calculator"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tire Tread Depth"
    Then I am brought to the page with header "Measuring Tread Depth"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Tires Below 45-Degrees"
    Then I am brought to the page with header "Tires Below 45 Degrees"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Trailer Tire FAQs"
    Then I am brought to the page with header "Safely Maintaining Trailer Tires"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "UTQG Standards"
    Then I am brought to the page with header "The UTQG System"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Winter Tire Changeover"
    Then I am brought to the page with header "Winter Tire Changeover & Installation"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Winter Tire FAQs"
    Then I am brought to the page with header "Winter Tire FAQs"

  @dt
  @at
  @wheelsTg
  @web
  Scenario: Verify Wheels section links
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Understanding Bolt Patterns"
    Then I am brought to the page with header "Understanding Bolt Patterns"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Wheel Alignment"
    Then I am brought to the page with header "Wheel Alignment"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Wheel Construction"
    Then I am brought to the page with header "Wheel Construction"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Wheel Offset & Backspacing"
    Then I am brought to the page with header "Understanding Wheel Offset and Backspacing"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Wheel Size"
    Then I am brought to the page with header "Wheel Size Basics"

  @dt
  @at
  @howToGuidesTg
  @web
  Scenario: Verify How-To-Guides section links
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "How to Change a Tire"
    Then I am brought to the page with header "Changing a Tire"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "How to Check Tire Pressure"
    Then I verify the second level header "HOW TO CHECK TIRE PRESSURE" on the page
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "How To Clean Tires"
    Then I am brought to the page with header "How To Clean Your Tires"
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "How To Clean Wheels"
    Then I am brought to the page with header "Proper Wheel Cleaning"

  @dt
  @at
  @wheelTorqueChart
  @web
  Scenario: Verify Wheel Torque Chart brand links
  """TODO: Step is failing due to Defect ID 5648"""
    When I open the "TIPS & GUIDES" navigation link
    And  I click the list element "Wheel Torque Chart"
    Then I am brought to the page with header "Wheel Torque Chart"
    And  I verify the brand links on the Torque Chart page

  @dt
  @at
  @siteMapSectionVerification
  @web
  Scenario Outline: Site Map section verification
    When I select "footer" "Site Map"
    Then I am brought to the page with header "Site Map"
    And  I click and verify all the links in the "<Section Name>" section of the Site Map page

    Examples:
      | Section Name      |
      | ABOUT US          |
      | CAREERS           |
      | CONTACT US        |
      | CUSTOMER SERVICES |
      | SERVICES          |
      | LEARN             |
      | TIRES             |
      | TIRE BRANDS       |
      | WHEELS            |
      | WHEEL BRANDS      |

  @dt
  @at
  @8670
  @checkAvailabilityByBrand
  @web
  Scenario Outline: HYBRIS_171_ANALYTICAL AND TAGGING_Check Availability_By Brand (ALM #8670)
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY BRAND" menu option
    And  I select the "<Brand>" tire brand image
    And  I select "View All" from the Product Brand page
    And  I select the "In Stock" checkbox
    And  I select the Check Inventory for the first item available
    Then I should verify that the Check Availability popup loaded
    When I close the Check Inventory popup
    Then I should see product list page with "Check Inventory at nearby stores"
    When I select the first product result image
    Then I should see product detail page with "Check Inventory at nearby stores"
    When I select the Check Inventory for the first item available
    Then I should verify that the Check Availability popup loaded
    When I close the Check Inventory popup
    Then I should see product list page with "Check Inventory at nearby stores"

    Examples:
      | Brand          |
      | Michelin Tires |

  @dt
  @9231
  @verifyMobileHeaderElements
  @mobile
  Scenario: HYBRIS_HEADER_VALIDATION_Mobile_DT (ALM# 9231)
    Given I go to the homepage
    Then I verify all of the mobile header elements are visible

  @dt
  @9709
  @mobileMakeThisMyStoreValidation
  @mobile
  Scenario Outline: Hybris_Mobile_Launching_TCOO (ALM# 9707)
    Given I go to the homepage
    When  I search for store within "10" miles of "<Zipcode>"
    And   I select make this "<StoreName>" my store
    Then  I verify the Current Store text color is Blue

    Examples:
      | Zipcode | StoreName |
      | 60652   | ILC 24    |

  @9404
  @dtd
  @web
  @mobile
  @bba
  @UiUxBBA
  Scenario Outline: Hybris_CONTENT_Search_By_Size_Before_PLP_Fitment_Message_Needs_To_Be_Changed (ALM # 9404)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the "PLP" banner color is "Green"
    And  I verify the "PLP" results banner message contains "<Year> <Make> <Model>"
    When I navigate back to previous page
    And  I select Change Vehicle
    And  I do a vehicle search with details "<NewYear>" "<NewMake>" "<NewModel>" "<NewTrim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<NewYear> <NewMake>"
    When I select a fitment option "<NewFitmentOption>"
    Then I verify the "PLP" banner color is "Green"
    And  I verify the "PLP" results banner message contains "<NewYear> <NewMake> <NewModel>"

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | FitmentOption | NewYear | NewMake | NewModel | NewTrim   | NewFitmentOption |
      | 2015 | Honda | Accord | Coupe EX | none     | All tires     | 2014    | Toyota  | Camry    | Hybrid SE | All tires        |

  @dt
  @9775
  @mobile
  @plpUiVehicleFitmentNotSelectedTire
  Scenario Outline: Hybirs_Mobile_PLP_UI_Vehicle_Fitment_Not_selected_Tire_TC01 (ALM #9775)
    When I go to the homepage
    And  I do a free text search for "18628"
    Then I should see "Defender A/S" in the search results
    And  I verify PLP UI "<Aspect>"

    Examples:
      | Aspect                 |
      | banner without vehicle |
      | basic controls         |
      | filter / sorting       |
      | pagination             |

  @dt
  @at
  @9682
  @9682_Tires
  @regression
  @UiUxRegression
  @web
  Scenario Outline: PLP In Stock Quick Filter State Check For Tires (ALM #9682)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the "In Stock" checkbox to be deselected by default

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     |
      | 2012 | Honda | Civic | Coupe DX | none     | Best selling  |
      | 2012 | Honda | Civic | Coupe DX | none     | On promotion  |

  @dt
  @at
  @9682
  @9682_Brand
  @regression
  @UiUxRegression
  @web
  Scenario Outline: PLP In Stock Quick Filter State Check For Tires (ALM #9682)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the "In Stock" quick filter is deselected by default for specific "TIRE" brands

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     |

  @dt
  @at
  @9682
  @9682_Wheels
  @regression
  @UiUxRegression
  @web
  Scenario Outline: PLP In Stock Quick Filter State Check For Wheels (ALM #9682)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select a fitment option "<FitmentOption>"
    Then I verify the "In Stock" quick filter is deselected by default for specific "WHEEL" brands

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption |
      | 2012 | Honda | Civic | Coupe DX | none     | All Wheels    |

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

  @9227
  @12599
  @web
  @dt
  @at
  @dtd
  @regression
  @UiUxRegression
  Scenario Outline: HYBRIS_UI_UI_FIND VEHICLE VALIDATION Desktop DT and DTD(ALM #9227)
    When I go to the homepage
    Then I verify the "TIRES" navigation link is displayed
    And  I verify the "WHEELS" navigation link is displayed
    And  I verify the default selection in the fitment component
    When I navigate to Shop By "Vehicle"
    Then the dropdown "Year, Make, Model, Trim" is visible on the page
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I navigate back to previous page
    And  I navigate to Shop By "Tire Size"
    Then the dropdown "Width, Ratio, Diameter" is visible on the page
    When I navigate to Shop By "Wheel Size"
    And  I click the fitment "Wheels" radio button
    Then the dropdown "Diameter, Wheel Width, Bolt Pattern" is visible on the page
    When I navigate to Shop by Brand
    Then I verify Shop By Brand default placeholder text
    When I select "<Brand>" and find products
    Then I see the "<Brand>" that I selected
    When I select "<SubCategory>" to shop
    Then I can see "<Brand>" Brand PLP page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | Brand          | SubCategory               |
      | 2012 | Honda | Civic | Coupe EX | none     | Michelin Tires | Shop for All-Season Tires |

  @9228
  @12599
  @web
  @dt
  @at
  @dtd
  @regression
  @UiUxRegression
  Scenario Outline: HYBRIS_UI_UI_FOOTER VALIDATION Desktop(ALM #9228, 15299)
    When I browse to the "<Page>" page with defaults
    Then I verify "Tire Search" footer link is displayed
    And  I verify "Wheel Search" footer link is displayed
    And  I verify "Deals And Rebates" footer link is displayed
    And  I verify "Tire Safety" footer link is displayed
    And  I verify "Tire Size Calculator" footer link is displayed
    And  I verify "Check Tire Pressure" footer link is displayed
    And  I verify "More Topics..." footer link is displayed
    And  I verify "Credit" footer link is displayed
    And  I verify "Apply Now" footer link is displayed
    And  I verify "Customer Care" footer link is displayed
    And  I verify "Return Policy" footer link is displayed
    And  I verify "About Us" footer link is displayed
    And  I verify "Contact Us" footer link is displayed
    And  I verify "Commercial Payments" footer link is displayed
    And  I verify "Store Locator" footer link is displayed
    And  I verify "Appointments" footer link is displayed
    And  I verify "Our Story" footer link is displayed
    And  I verify "Motorsports" footer link is displayed
    And  I verify "Careers" footer link is displayed
    And  I verify "Regional Offices" footer link is displayed

    Examples:
      | Page          |
      | Homepage      |
      | Shopping Cart |

  @dt
  @at
  @dtd
  @9346
  @regression
  @UiUxRegression
  @web
  Scenario Outline: UI_Verify page font on Filter search results for In Stock (ALM #9346)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify the "In Stock" checkbox to be deselected by default
    When I extract "In Stock" filter font size
    And  I select from the "Quick Filters" filter section, "single" option(s): "In Stock"
    Then I verify "In Stock" filter font size value before and after selection

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Header       |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | tire results |

  @dt
  @at
  @9345
  @regression
  @UiUxRegression
  @mobile
  Scenario Outline: HYBRIS_UI_UI_Mobile Verify the Shop by Vehicle functionality (ALM #9345)
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     |

  @dt
  @at
  @dtd
  @web
  @9290
  @regression
  @UiUxRegression
  Scenario: HYBRIS_UI_UI_Order of the Wheel Size Drop Down (ALM# 9290)
    When I open the "WHEELS" navigation link
    And  I click the "WHEELS BY SIZE" menu option
    And  I click the fitment "Wheels" radio button
    Then I verify wheels shop by size fields are in correct order left to right

  @dt
  @at
  @dtd
  @web
  @12551
  @12557
  @12560
  @regression
  @UiUxRegression
  Scenario Outline: HYBRIS_UI_GLOBAL AND NAVIGATION_Cart Checkout_GLOBALHEADER_Verify the header in Checkout page (ALM #12551,12557,12560)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify need help option is displayed
    And  I verify the site logo
    When I click on the "<NeedHelp>" Link
    Then I verify need help popup values email and phone number are displayed

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout            | NeedHelp   |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 34302    | without appointment | Need Help? |

  @dt
  @at
  @web
  @12568
  @12599
  @regression
  @UiUxRegression
  Scenario Outline: HYBRIS_UI_GLOBAL AND NAVIGATION_Cart Checkout_GLOBALHEADER_Verify navigation when Logo in the global header on checkout page is selected (ALM #12568, 15299)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify the site logo
    When I click the discount tire logo
    Then I am brought to the homepage
    And  I verify the "TIRES" navigation link is displayed
    And  I verify the "WHEELS" navigation link is displayed
    And  I verify the "SERVICES" navigation link is displayed
    And  I verify the "TIPS & GUIDES" navigation link is displayed
    And  I verify the default selection in the fitment component
    And  I verify all of the fitment menus are displayed

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout            |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 34302    | without appointment |

  @dt
  @at
  @web
  @12562
  @regression
  @UiUxRegression
  Scenario Outline: HYBRIS_UI_GLOBAL AND NAVIGATION_Cart Checkout_GLOBALFOOTER_Verify the contents in footer displayed in the checkout page (ALM #12562)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with fitment options
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "tire results"
    When I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I see "<ItemCode>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify need help option is displayed
    And  I verify the site logo
    And  I verify the Headline Text of footer is displayed
    And  I verify "Return Policy" footer link is displayed
    When I select "footer" "Return Policy"
    And  I navigate to newly opened next tab
    Then I am brought to the page with header "Our Return Policy"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Checkout            |
      | 2012 | Honda | CIVIC | COUPE DX | none     | All tires     | 34302    | without appointment |

  @web
  @dt
  @at
  @11245
  @regression
  @UiUxRegression
  Scenario Outline: HYBRIS_UI_GLOBAL AND NAVIGATION_ Global Nav Services Dropdown(ALM #11245)
    When I browse to the "Shopping Cart" page with defaults
    And  I open the "SERVICES" navigation link
    And  I click on the "<Learn About Services>" Link
    Then I verify the "Home, Services" link in the breadcrumb container
    When I navigate back to previous page
    And  I open the "SERVICES" navigation link
    And  I click on the "SCHEDULE APPOINTMENT" Link
    Then I verify the Third level header "Action needed" on the page

    Examples:
      | Learn About Services          |
      | Learn more about our services |

  @dt
  @at
  @dtd
  @web
  @15634
  @15635
  @15636
  @regression
  @UiUxRegression
  Scenario Outline: HYBRIS_UI_Finance, Promotions, Tips & guides  links in Header menu (ALM # 15634, 15635, 15636)
    When I click the discount tire logo
    Then I am brought to the homepage
    And  I verify the "<Header>" navigation link is displayed
    When I open the "<Header>" navigation link
    Then I verify the "<Breadcrumbs>" link in the breadcrumb container
    And  I verify the current URL contains "<Content>"

    Examples:
      | Header        | Breadcrumbs             | Content                    |
      | FINANCING     | Home, Finance           | customer-service/financing |
      | PROMOTIONS    | Home, Deals and Rebates | promotions                 |
      | TIPS & GUIDES | Home, TIPS & GUIDES     | learn                      |