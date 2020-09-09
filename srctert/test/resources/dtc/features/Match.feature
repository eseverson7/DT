@dt
@match
Feature: Match

  Background:
    Given I change to the default store

  @bba
  @8791
  @at
  @dtd
  @tpmsCartValidation
  @mobile
  @web
  @matchBBA
  Scenario Outline: Verify the TPMS line item appears in the shopping cart (ALM # 8791)
  """Availability of TPMS Rebuilt message is linked with Vehicle As per Rule, If vehicle is not selected, TPMS will
    not appear in any environment"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I add item "<ItemCode>" of type "none" to my cart and "View shopping cart"
    Then I verify "ItemCode" "<ItemCode>" is in the cart
    And  I should see quantity is set to "<Quantity>" in the cart
    And  I verify the "TPMS Rebuild Kits" label present on the shopping cart page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | ItemCode | Quantity |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | 34302    | 4        |

  @bba
  @dtd
  @at
  @8665
  @web
  @matchBBA
  Scenario Outline: Verify the TPMS line item appears in the shopping cart for Tire (ALM # 8665)
  """Availability of TPMS Rebuilt message is linked with Vehicle As per Rule, If vehicle is not selected, TPMS will
    not appear in any environment"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I do a free text search for "<ItemCode>"
    Then I should see product list page with "<ProductName>"
    When I select "<ProductName>" from the autocomplete dropdown of the search box
    Then I should see product detail page with "<ProductName>"
    When I add item to my cart and "View shopping cart"
    Then I verify "ItemCode" "<ItemCode>" is in the cart
    And  I verify the "TPMS Rebuild Kits" label present on the shopping cart page

    Examples:
      | Year | Make  | Model | Trim     | Assembly | ItemCode | ProductName  | FitmentOption |
      | 2012 | Honda | Civic | Coupe DX | none     | 34302    | Defender A/S | All tires     |

  @web
  @8781
  @at
  @dtd
  @bba
  @matchBBA
  Scenario Outline: HYBRIS_130-131_Match_Fitment Search_Optional Sizes_Tires (ALM #8781)
  """TODO: BUG 8566 - TireSize 205/65-15 missing in STG and PROD"""
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
      | 2012 | Honda | Civic | Coupe EX | none     | Optional Tire Sizes | 15         | 205/65-15 |
      | 2012 | Honda | Civic | Coupe DX | none     | Optional Tire Sizes | 16         | 205/55-16 |

  @mobile
  @8781
  @at
  @dtd
  @bba
  @matchBBA
  Scenario Outline: Mobile - HYBRIS_130-131_Match_Fitment Search_Optional Sizes_Tires (ALM #8781)
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
      | 2012 | Honda | Civic | Coupe EX | none     | Optional Tire Sizes | 15         | 205/65-15 |
      | 2012 | Honda | Civic | Coupe DX | none     | Optional Tire Sizes | 16         | 205/55-16 |

  @bba
  @8369
  @at
  @searchAndVerifyFitmentOptionsLinks
  @web
  @mobile
  @9616
  @matchBBA
  Scenario Outline: Fitment Search from Home Page (Wheels)(Tires) (ALM #8369, 9616)
  """| 2012 | Honda  | Civic | Coupe DX   | None                                | Staggered     |"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I am brought to the fitment page with the "<Staggering>" vehicle message
    And  I verify all the "<Staggering>" fitment option links by clicking on them

    Examples:
      | Year | Make   | Model | Trim       | Assembly                            | Staggering    |
      | 2010 | Nissan | 370Z  | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL | Non Staggered |

  @8380
  @8663
  @searchForTiresViaHeaderTiresByVehicle
  @web
  Scenario Outline: Fitment Search for Tires by Vehicle under Shop for Regular (Tires) (ALM #8380, 8663)
  """Also, Fitment Search for Tires by Vehicle type under Category Page (Tires) (ALM #8663) TODO: ALM testcase 8663 is
    marked for REVIEW due to inconsistency between title and test steps.
    First data table covers 'Fitment Search from Tires by Vehicle under Shop (Tires)' and 'Fitment Search from Tires by
    Vehicle under Shop (Normal /Regular Vehicle Tires)'
    Second data table covers '118_Match_Fitment Search_Shop Modal_By Vehicle_Staggered Tires_DT' TODO: AB 4/10/2017,
    "All tire sets" was not an available option
    Third data table covers 'Fitment Search from Tires by Vehicle under Shop (Light Truck (LT) Vehicle Tires)' TODO:
    This data only exists on staging"""
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY VEHICLE" menu option
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"

    Examples:
      | Year | Make      | Model          | Trim       | Assembly                            | FitmentOption | Header       |
      | 2015 | Honda     | Accord         | Coupe EX   | none                                | All tires     | tire results |
      | 2015 | Chevrolet | Silverado 2500 | Crew Cab   | 245 /75 R17 E1                      | All tires     | tire results |

  @8380
  @8663
  @searchForTiresViaHeaderTiresByVehicle
  @web
  Scenario Outline: Fitment Search for Tires by Vehicle under Shop for staggered (Tires) (ALM #8380, 8663)
  """Also, Fitment Search for Tires by Vehicle type under Category Page (Tires) (ALM #8663) TODO: ALM testcase 8663 is
    marked for REVIEW due to inconsistency between title and test steps.
    First data table covers 'Fitment Search from Tires by Vehicle under Shop (Tires)' and 'Fitment Search from Tires by
    Vehicle under Shop (Normal /Regular Vehicle Tires)'
    Second data table covers '118_Match_Fitment Search_Shop Modal_By Vehicle_Staggered Tires_DT' TODO: AB 4/10/2017,
    "All tire sets" was not an available option
    Third data table covers 'Fitment Search from Tires by Vehicle under Shop (Light Truck (LT) Vehicle Tires)' TODO:
    This data only exists on staging"""
    When I open the "TIRES" navigation link
    And  I click the "TIRES BY VEHICLE" menu option
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    Then I verify the product list page is displayed with sets

    Examples:
      | Year | Make      | Model          | Trim       | Assembly                            | FitmentOption |
      | 2010 | Nissan    | 370Z           | Coupe Base | F 245 /40 R19 SL - R 275 /35 R19 SL | All tire sets |

  @8393
  @searchByVehicleForWheelsViaHomepageMenuAndTestPagination
  @web
  Scenario Outline: Match_Fitment Search_Shop Category_By Vehicle_Wheels_DT (ALM #8393)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify that each page displays 10 products and total number of pages is equal to total count / 10
    And  I verify that the sort by dropdown value is set to "Relevance"

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | FitmentOption | Header        |
      | 2015 | Honda | Accord | Coupe EX | none     | All Wheels    | wheel results |

  @dtd
  @at
  @8399
  @9020
  @web
  @mobile
  @bba
  @matchBBA
  Scenario Outline: Search Vehicle In Session Shop By Size Yellow Warning Notification(ALM #8399, 9020)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I go to the homepage
    And  I navigate to Shop by Size
    And  I select "<TireWidth>" from the "Tire Width" dropdown
    And  I select "<AspectRatio>" from the "Aspect Ratio" dropdown
    And  I select "<TireDiameter>" from the "Tire Diameter" dropdown
    And  I click on the "Find Tires" button
    Then I verify the "PLP" banner color is "Yellow"
    And  I verify the "PLP" results banner message contains "<String>"

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | TireWidth | AspectRatio | TireDiameter | String    |
      | 2015 | Honda | Accord | Coupe EX | none     | 185       | 65          | 14           | 185/65-14 |

  @bba
  @8400
  @at
  @dtd
  @searchByOptionalTireSelectionViaHomepage
  @searchByOptionalWheelSelectionViaHomepage
  @web
  @mobile
  @matchBBA
  Scenario Outline: Fitment Search for Optional Tire/Wheel Sizes (ALM #8400)
  """TODO - Updated Test Data as per parameters values @8400 Test Id"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "<FitmentOption>"
    And  I select a fitment option "<SizeOption>"
    Then I verify the PLP header message contains "<Header>"

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption        | SizeOption | Header        |
      | 2012 | Honda | Civic | Coupe DX | None     | Optional Tire Sizes  | 215/60-15  | tire results  |
      | 2012 | Honda | Civic | Coupe DX | None     | Optional Wheel Sizes | 20"        | wheel results |

  @bba
  @at
  @dtd
  @8783
  @searchBBA
  Scenario Outline: HYBRIS_111_Match_Fitment Search_Home Page_DTD (ALM #8783)
  """TODO: Data only exists in STG; Request entered into Data spreadsheet"""
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I am brought to the fitment page with the "Non Staggered" vehicle message
    And  I verify all the "Basic Non Staggered" fitment option links by clicking on them

    Examples:
      | Year | Make   | Model | Trim      | Assembly |
      | 2016 | Toyota | Camry | Hybrid LE | none     |

  @9010
  @at
  @searchGlobalNavigation
  @web
  @mobile
  Scenario Outline: Fitment Match Search Global Navigation (ALM #9010)
    When I go to the homepage
    When I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "All tires"
    Then I am brought to the page with path "/fitmentresult/tires"
    When I navigate back to previous page
    And  I select a fitment option "Best Selling"
    Then I am brought to the page with path "/fitmentresult/tires?sort=bestSeller-asc"
    When I navigate back to previous page
    And  I select a fitment option "Tires on promotion"
    Then I am brought to the page with path "/fitmentresult/tires?sort=onpromotion-asc"
    When I navigate back to previous page
    And  I select a fitment option "All Wheels"
    Then I am brought to the page with path "/fitmentresult/wheels"
    When I navigate back to previous page
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "Optional Tire Sizes"
    And  I select a fitment option "<TireSize>"
    Then I am brought to the page with path "/fitmentresult/tires/optionalPlusSize/215/55-17"
    When I go to the homepage
    And  I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select a fitment option "Optional Wheel Sizes"
    And  I select a fitment option "17" (+0)"
    Then I am brought to the page with path "/fitmentresult/wheels/optionalPlusSize/17"

    Examples:
      | Year | Make  | Model  | Trim     | Assembly | TireSize  |
      | 2015 | Honda | Accord | Coupe EX | none     | 215/55-17 |

  @5764
  @dt
  @web
  @regression
  @matchRegression
  Scenario Outline: Fitment Match Search Global Navigation (ALM #9010)
    When I go to the homepage
    And  I open the fitment popup
    And  I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    When I select the "WHEELS" menu option
    And  I select a fitment option "<FitmentOption>"
    Then I am brought to the page with path "/fitmentresult/wheels/staggered/front"
    And  I verify "FRONT" staggered option tab is displayed on PLP result page
    And  I verify "REAR" staggered option tab is displayed on PLP result page
    And  I verify "FRONT" wheel diameter matches with the size of each product on the results page
    When I select "REAR" staggered tab on PLP result page
    Then I verify "REAR" wheel diameter matches with the size of each product on the results page

    Examples:
      | Year | Make      | Model    | Trim | Assembly | FitmentOption    |
      | 2010 | Chevrolet | Corvette | Base | none     | All front wheels |