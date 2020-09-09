@pdl
Feature: PDL Smoke Tests And Regression Tests

  Background:
    Given I go to the pdl homepage

  @smoketest_pdl
  @10720
  Scenario Outline: PDL_Auto_Driving Detials UI (ALM #10720)
    Then I verify that the "Enter" button is disabled
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    And I verify the zip code is set to "<ZipCode>"
    And I verify the city and state are set to "<CityState>"
    And I verify the number of "car" dropdowns is "4"
    And I verify the default values of the car dropdowns
    And I verify that the Miles Driven value is "15k"
    And I verify that the Tire Size value is "<TireSize>"
    And I verify that the "Typical" Driving Priority is selected
    And I verify the "Typical" Driving Priorities
    And I verify that the "View Tire Recommendations" button is disabled

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | TireSize  |
      | AZP20   | 919919    | 85250   | SCOTTSDALE, AZ | XXX/XX XX |

  @smoketest_pdl
  @10728
  @10166
  Scenario Outline: PDL_Auto_Recommended Tires (ALM #10728)
    Then I verify that the "Enter" button is disabled
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    And I verify the zip code is set to "<ZipCode>"
    And I verify the city and state are set to "<CityState>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And I verify the summary zip code is set to "<ZipCode>"
    And I verify the summary car is set to "<Year> <Make> <Model>"
    And I verify the summary Miles Driven is set to "<MilesDriven>"
    And I verify the summary Tire Size is set to "<TireSize>"
    And I verify the "Typical" summary Driving Priorities
    And I verify that the "Compare Tires" button is disabled
    And I verify that the "Filter Results" button is enabled
    And I verify that the Top Recommendation ribbon is displayed only for the first tire in the list

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | Year | Make  | Model | Trim     | Assembly | TireSize   | MilesDriven |
      | AZP20   | 919919    | 85250   | SCOTTSDALE, AZ | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 15000       |


  @smoketest_pdl
  @9817
  @9974
  @9840
  @DrivingPrioritiesPerformance
  Scenario Outline: PDL_DD2A_Driving Priorities_Performance (ALM #9817)
    When  I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I switch to performance for driving priorities
    Then I verify the "performance" Driving Priorities
    And I verify the zip code is set to "<ZipCode>"
    And I verify the city and state are set to "<CityState>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | Year | Make  | Model | Trim     | Assembly | TireSize   | MilesDriven |
      | MNM29   | 919919    | 55125   | SAINT PAUL, MN | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 15000       |

  @smoketest_pdl
  @10840
  @standalonevehicleoptionaltirestaggered
  Scenario Outline: PDL_DD5-05_Vehicle-OE TIRE SIZE - StandAlone_Vehicle Optional tire_Staggered (ALM #10840)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    And  I select Not your tire under Tire Size
    When I select staggered set "<Set>"
    And  I select optional tire sizes for front "<FrontTireSize>" and for rear "<RearTireSize>"
    Then I verify that the "optional" tire size category is displayed
    And  I verify that the Tire Size value is "<NewTireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | FrontTireSize | RearTireSize | Set   | NewTireSize                       |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 265/35-18     | 285/35-18    | 18-18 | Front: 265/35 R18Rear: 285/35 R18 |

  @smoketest_pdl
  @10169
  @vehicleoeoptionaltirestandard
  Scenario Outline: PDL_DD5-05_Vehicle-OE TIRE SIZE - StandAlone_Vehicle Optional tire (ALM #10169)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I verify that the "oe" tire size category is displayed
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    When I select Not your tire under Tire Size
    And  I select optional tire size fitment "<OptionalTireSize>"
    Then I verify that the "optional" tire size category is displayed
    And  I verify that the Tire Size value is "<NewTireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | Year | Make   | Model      | Trim     | Assembly  | TireSize   | OptionalTireSize | NewTireSize |
      | MNM29   | 919919    | 2014 | Toyota | Highlander | Base FWD | 245/55-19 | 245/55 R19 | 225/50-18        | 225/50 R18  |


  @smoketest_pdl
  @10838
  @tirecomparisonengagestaggered
  Scenario Outline: PDL_RE3-01_RESULTS_Tire Comparison Engage_Staggered (ALM #10838)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    And  I select Not your tire under Tire Size
    When I select staggered set "<Set>"
    And  I select optional tire sizes for front "<FrontTireSize>" and for rear "<RearTireSize>"
    Then I verify that the "optional" tire size category is displayed
    And  I verify that the Tire Size value is "<NewTireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify that compare tires button is "disabled" on results page
    When I select the product checkbox at position "1" from the products list results
    And  I select the product checkbox at position "2" from the products list results
    Then I verify that compare tires button is "enabled" on results page
    And  I verify the status of product checkbox at position "3" is "disabled"
    When I select the compare tires button
    Then I verify tire comparison page header is "TIRE COMPARISON"

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | FrontTireSize | RearTireSize | Set   | NewTireSize                       |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 265/35-18     | 285/35-18    | 18-18 | Front: 265/35 R18Rear: 285/35 R18 |

  @smoketest_pdl
  @10835
  @standalonevehicleoetirestaggered
  Scenario Outline: PDL_DD5-04_Vehicle-OE TIRE SIZE - StandAlone_Vehicle OE Tire_Staggered (ALM #10835)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    And  I select Not your tire under Tire Size
    When I click on optional tire size modal close icon
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 |

  @smoketest_pdl
  @10836
  @results1ConfirmMainStaggered
  Scenario Outline: PDL_RE1-01_RESULTS_1Confirm MAIN_Staggered (ALM #10836)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    And  I select Not your tire under Tire Size
    When I click on optional tire size modal close icon
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<Miles>"
    And  I verify the summary Tire Size is set to "<ResultsTireSize>"
    And  I verify the "Typical" summary Driving Priorities
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the tire brand "<Brand>" is listed on results page
    And  I verify the tire name "<TireName>" is listed on results page
    And  I verify the front tire size "<FrontSize>" is listed on results page
    And  I verify the rear tire size "<RearSize>" is listed on results page
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify the item stock status label is present for all the product items on results page
    And  I verify the item qty stock status label is present for all the product items on results page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | ZipCode | Miles | ResultsTireSize | Brand   | TireName    | FrontSize              | RearSize                |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 55125   | 15000 | 255/35 R19      | General | G-MAX AS-03 | 255 /35 R19 96W XL BSW | 275 /35 R19 100W XL BSW |

  @regression_pdl
  @smoketest_pdl
  @10839
  @tiredetailstaggered
  Scenario Outline:  PDL_TD1-01_Tire Details_Staggered (ALM #10839)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    When I select Not your tire under Tire Size
    And  I click on optional tire size modal close icon
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    When I select view details for the tire "<TireName>" from the results page
    Then I verify tire details page header is "TIRE DETAILS"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the tire image and alt text is displayed on tire details page
    And  I verify the tire brand "<Brand>" is listed on tire details page
    And  I verify the tire name "<TireName>" is listed on tire details page
    And  I verify the front tire size "<FrontTireSize>" is listed on tire details page
    And  I verify the rear tire size "<RearTireSize>" is listed on tire details page
    And  I verify the front tire item id "<FrontItemId>" is listed on tire details page
    And  I verify the rear tire item id "<RearItemId>" is listed on tire details page
    And  I verify the front tire quantity is "<Quantity>" on tire details page
    And  I verify the rear tire quantity is "<Quantity>" on tire details page
    And  I verify the stopping distance ratings labels are listed on tire details page
    And  I verify distance to stop ratings are in 0 to "<MaxRange>" range on tire details page
    And  I verify the ride ratings labels are listed on tire details page
    And  I verify ride ratings are in 0 to "<MaxRange>" range on tire details page
    And  I verify the expected tire life range labels are listed on tire details page
    And  I verify the cost rating miles per dollar label is listed on tire details page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | Brand    | TireName               | FrontTireSize          | RearTireSize           | FrontItemId | RearItemId | MaxRange | Quantity |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | Michelin | Pilot Sport A/S 3 Plus | 255 /35 R19 96Y XL BSW | 275 /35 R19 96Y SL BSW | 26022       | 25962      | 5        | 2        |

  @smoketest_pdl
  @9891
  @tiredetails
  Scenario Outline: PDL_TD1-01_Tire Details (ALM #9891)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    When I select Not your tire under Tire Size
    And  I click on optional tire size modal close icon
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    When I select view details for the tire "<TireName>" from the results page
    Then I verify tire details page header is "TIRE DETAILS"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the tire image and alt text is displayed on tire details page
    And  I verify the tire brand "<Brand>" is listed on tire details page
    And  I verify the tire name "<TireName>" is listed on tire details page
    And  I verify the front tire size "<FrontTireSize>" is listed on tire details page
    And  I verify the rear tire size "<RearTireSize>" is listed on tire details page
    And  I verify the front tire item id "<FrontItemId>" is listed on tire details page
    And  I verify the rear tire item id "<RearItemId>" is listed on tire details page
    And  I verify the front tire quantity is "<Quantity>" on tire details page
    And  I verify Rating label is present on tire details page
    And  I verify Distance To Stop section header is present on tire details page
    And  I verify Expected Tire Life Range section header is present on tire details page
    And  I verify Cost section header is present on tire details page
    And  I verify Ride section header is present on tire details page
    And  I verify the rear tire quantity is "<Quantity>" on tire details page
    And  I verify the stopping distance ratings labels are listed on tire details page
    And  I verify distance to stop ratings are in 0 to "<MaxRange>" range on tire details page
    And  I verify the ride ratings labels are listed on tire details page
    And  I verify ride ratings are in 0 to "<MaxRange>" range on tire details page
    And  I verify the expected tire life range labels are listed on tire details page
    And  I verify the cost rating miles per dollar label is listed on tire details page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | Brand    | TireName               | FrontTireSize          | RearTireSize           | FrontItemId | RearItemId | MaxRange | Quantity |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | Michelin | Pilot Sport A/S 3 Plus | 255 /35 R19 96Y XL BSW | 275 /35 R19 96Y SL BSW | 26022       | 25962      | 5        | 2        |

  @smoketest_pdl
  @10832
  @9842
  @ResultsConfirmMain
  Scenario Outline:  PDL_RE1-01_RESULTS_1Confirm MAIN (ALM #9842)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the Driving Details is displayed on the page
    And  I am brought to the page with header "RESULTS"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<Miles>"
    And  I verify the summary Tire Size is set to "<TireSize>"
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify all tire images are on results page
    And  I verify the item stock status label is present for all the product items on results page
    And  I verify the item qty stock status label is present for all the product items on results page

    Examples:
      | StoreID | PayrollID | ZipCode | Year | Make  | Model | Trim     | Assembly | TireSize   | Miles |
      | MNM29   | 919919    | 55125   | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 15000 |

  @smoketest_pdl
  @9846
  Scenario Outline: PDL_RE2-02_RESULTS_FILTER RESULTS-Brands (ALM # 9846)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    When I select the filter results button
    Then I verify that the filter window "displays"
    When I select filter brand "<Brand1>"
    And  I select Apply Filter
    Then I verify that the filter window "closes"
    And  I am brought to the page with header "RESULTS"
    And  I verify that only tires of brand "<Brand1>" are listed in results
    And  I verify that only item codes "<ItemCodes1>" are listed in results
    When I select the filter results button
    And  I select Reset All
    And  I select filter brand "<Brand2>"
    And  I select Apply Filter
    Then I verify that only tires of brand "<Brand2>" are listed in results
    And  I verify that only item codes "<ItemCodes2>" are listed in results

    Examples:
      | StoreID | PayrollID | Year | Make  | Model | Trim     | Assembly | Brand1   | ItemCodes1  | Brand2 | ItemCodes2 |
      | MNM29   | 919919    | 2012 | Honda | Civic | Coupe DX | none     | Michelin | 19600,34302 | Falken | 28449      |

  @regression_pdl
  @10911
  @results1ConfirmMainTypical02Priorities
  Scenario Outline: PDL_RE1-01_RESULTS_1Confirm MAIN_Typical 02 priorities (ALM #10911)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    And  I select Not your tire under Tire Size
    When I click on optional tire size modal close icon
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the Driving Details is displayed on the page
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<Miles>"
    And  I verify the summary Tire Size is set to "<ResultsTireSize>"
    And  I verify the "Typical" summary Driving Priorities
    And  I verify the tire brand "<Brand>" is listed on results page
    And  I verify the tire name "<TireName>" is listed on results page
    And  I verify the front tire size "<FrontSize>" is listed on results page
    And  I verify the rear tire size "<RearSize>" is listed on results page
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify the item stock status label is present for all the product items on results page
    And  I verify the product article number label is present for all the product items on results page
    And  I verify the item qty stock status label is present for all the product items on results page
    And  I verify all tire images are on results page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | ZipCode | Miles | ResultsTireSize | Brand   | TireName    | FrontSize              | RearSize                |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 55125   | 15000 | 255/35 R19      | General | G-MAX AS-03 | 255 /35 R19 96W XL BSW | 275 /35 R19 100W XL BSW |

  @regression_pdl
  @9896
  @resultsStaggeredOnPromotion
  Scenario Outline: PDL_RE2-06_RESULTS_FILTER RESULTS - On Promotion (ALM# 9896)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSizeF>"
    And  I verify that the Tire Size value is "<TireSizeR>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    When I select the filter results button
    And  I select the Other checkbox with "<Text>"
    Then I verify the No Products Match error on the Filter popup
    When I select Apply Filter
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSizeF  | TireSizeR  | Text         |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | 255/35 R19 | 275/35 R19 | On Promotion |

  @regression_pdl
  @10940
  @primaryDrivingLocationStaggered
  Scenario Outline: PDL_DD4-04_Primary Driving Location - change zip code_Staggered (ALM #10940)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I update the zip code to "<ZipCode>"
    Then I verify the zip code is set to "<ZipCode>"
    When I set miles driven per year to "<Miles>"
    Then I verify that the Miles Driven value is "5k"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "View Tire Recommendations" button is enabled
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | ZipCode | Miles |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 85250   | 5K    |

  @regression_pdl
  @10985
  @resultsConfirmMainStaggeredChangeZipcode
  Scenario Outline: PDL_RE1-01_RESULTS_1Confirm MAIN_Staggered_Change Zipcode (ALM #10985)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I update the zip code to "<ZipCode>"
    Then I verify the zip code is set to "<ZipCode>"
    When I set miles driven per year to "<Miles>"
    Then I verify that the Miles Driven value is "5k"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "View Tire Recommendations" button is enabled
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the Driving Details is displayed on the page
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<MilesDriven>"
    And  I verify the summary Tire Size is set to "<ResultsTireSize>"
    And  I verify the "Typical" summary Driving Priorities
    And  I verify all tire images are on results page
    And  I verify the tire brand "<Brand>" is listed on results page
    And  I verify the tire name "<TireName>" is listed on results page
    And  I verify the front tire size "<FrontSize>" is listed on results page
    And  I verify the rear tire size "<RearSize>" is listed on results page
    And  I verify the item code "<ItemCode1>" is listed on results page
    And  I verify the item code "<ItemCode2>" is listed on results page
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify the item stock status label is present for all the product items on results page
    And  I verify the product article number label is present for all the product items on results page
    And  I verify the item qty stock status label is present for all the product items on results page


    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | ZipCode | Miles | MilesDriven | ResultsTireSize | Brand   | TireName    | FrontSize              | RearSize                | ItemCode1 | ItemCode2 |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 85250   | 5K    | 5000        | 255/35 R19      | General | G-MAX AS-03 | 255 /35 R19 96W XL BSW | 275 /35 R19 100W XL BSW | 32942     | 32943     |

  @smoketest_pdl
  @9976
  @primaryDrivingLocationChangeInZipCode
  Scenario Outline: PDL_DD4-04_Primary Driving Location - change zip code(ALM #9976)
    Then I verify that the "Enter" button is disabled
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    And  I verify the number of "car" dropdowns is "4"
    And  I verify the default values of the car dropdowns
    And  I verify that the Miles Driven value is "15k"
    And  I verify that the Tire Size value is "<DefaultTireSize>"
    And  I verify that the "Typical" Driving Priority is selected
    And  I verify the "Typical" Driving Priorities
    And  I verify that the "View Tire Recommendations" button is disabled
    When I update the zip code to "<ZipCode>"
    Then I verify the zip code is set to "<ZipCode>"
    And  I verify the city and state are set to "<CityState>"
    When I set miles driven per year to "<Miles>"
    Then I verify that the Miles Driven value is "<Miles>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the "oe" tire size category is displayed
    And  I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "View Tire Recommendations" button is enabled
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | DefaultTireSize | Year | Make  | Model | Trim     | Assembly | TireSize   | CityState      | ZipCode | Miles |
      | MNM29   | 919919    | XXX/XX XX       | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | SCOTTSDALE, AZ | 85250   | 30K   |

  @smoketest_pdl
  @10833
  @results1ConfirmMainChangedZipCode
  Scenario Outline: PDL_RE1-01_RESULTS_1Confirm MAIN_Changed Zipcode (ALM #10833)
    Then I verify that the "Enter" button is disabled
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    And  I verify the number of "car" dropdowns is "4"
    And  I verify the default values of the car dropdowns
    And  I verify that the Miles Driven value is "15k"
    And  I verify that the Tire Size value is "<DefaultTireSize>"
    And  I verify that the "Typical" Driving Priority is selected
    And  I verify the "Typical" Driving Priorities
    And  I verify that the "View Tire Recommendations" button is disabled
    When I update the zip code to "<ZipCode>"
    Then I verify the zip code is set to "<ZipCode>"
    And  I verify the city and state are set to "<CityState>"
    When I set miles driven per year to "<Miles>"
    Then I verify that the Miles Driven value is "<Miles>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the "oe" tire size category is displayed
    And  I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "View Tire Recommendations" button is enabled
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the Driving Details is displayed on the page
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<MilesDriven>"
    And  I verify the summary Tire Size is set to "<TireSize>"
    And  I verify the "Typical" summary Driving Priorities
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the tire brand "<Brand>" is listed on results page
    And  I verify the tire name "<TireName>" is listed on results page
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify all tire images are on results page
    And  I verify the item stock status label is present for all the product items on results page

    Examples:
      | StoreID | PayrollID | DefaultTireSize | Year | Make  | Model | Trim     | Assembly | TireSize   | CityState      | ZipCode | Miles | MilesDriven | Brand    | TireName    |
      | MNM29   | 919919    | XXX/XX XX       | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | SCOTTSDALE, AZ | 85250   | 30K   | 30000       | Michelin | Premier A/S |

  @smoketest_pdl
  @10834
  @resultsConfirmMainPerformance
  Scenario Outline: PDL_RE1-01_RESULTS_1Confirm MAIN_Performance (ALM #10834)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I switch to performance for driving priorities
    Then I verify the "performance" Driving Priorities
    And  I verify the zip code is set to "<ZipCode>"
    And  I verify the city and state are set to "<CityState>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the Driving Details is displayed on the page
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<MilesDriven>"
    And  I verify the summary Tire Size is set to "<TireSize>"
    And  I verify the "performance" summary Driving Priorities
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the tire brand "<Brand>" is listed on results page
    And  I verify the tire name "<TireName>" is listed on results page
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify all tire images are on results page

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | Year | Make  | Model | Trim     | Assembly | TireSize   | MilesDriven | Brand    | TireName    |
      | MNM29   | 919919    | 55125   | SAINT PAUL, MN | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 15000       | Michelin | Premier A/S |

  @smoketest_pdl
  @10841
  @resultsConfirmMainStaggeredOptional
  Scenario Outline: PDL_RE1-01_RESULTS_1Confirm MAIN_Staggered_Optional (ALM #10841)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    And  I verify that the "not your tire" is displayed on driving details page
    And  I select Not your tire under Tire Size
    When I select staggered set "<Set>"
    And  I select optional tire sizes for front "<FrontTireSize>" and for rear "<RearTireSize>"
    Then I verify that the "optional" tire size category is displayed
    And  I verify that the Tire Size value is "<NewTireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the Driving Details is displayed on the page
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<MilesDriven>"
    And  I verify the summary Tire Size is set to "<ResultsTireSize>"
    And  I verify the "Typical" summary Driving Priorities
    And  I verify all tire images are on results page
    And  I verify the tire brand "<Brand>" is listed on results page
    And  I verify the tire name "<TireName>" is listed on results page
    And  I verify the front tire size "<FrontSize>" is listed on results page
    And  I verify the rear tire size "<RearSize>" is listed on results page
    And  I verify the item code "<FrontItemId>" is listed on results page
    And  I verify the item code "<RearItemId>" is listed on results page
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify the item stock status label is present for all the product items on results page
    And  I verify the product article number label is present for all the product items on results page
    And  I verify the item qty stock status label is present for all the product items on results page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | FrontTireSize | RearTireSize | Set   | NewTireSize                       | ZipCode | Miles | MilesDriven | ResultsTireSize | Brand    | TireName               | FrontSize              | RearSize               | FrontItemId | RearItemId |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 265/35-18     | 285/35-18    | 18-18 | Front: 265/35 R18Rear: 285/35 R18 | 55125   | 5K    | 15000       | 265/35 R18      | Michelin | Pilot Sport A/S 3 Plus | 265 /35 R18 97Y XL BSW | 285 /35 R18 97Y SL BSW | 26043       | 26002      |


  @regression_pdl
  @10987
  @resultsConfirmMainStaggeredPerformance
  Scenario Outline: PDL_RE1-01_RESULTS_1Confirm MAIN_Staggered_Peformance (ALM #10987)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I switch to performance for driving priorities
    Then I verify the "performance" Driving Priorities
    And  I verify the zip code is set to "<ZipCode>"
    And  I verify the city and state are set to "<CityState>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the Driving Details is displayed on the page
    And  I verify that the Top Recommendation ribbon is displayed only for the first tire in the list
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the summary zip code is set to "<ZipCode>"
    And  I verify the summary car is set to "<Year> <Make> <Model>"
    And  I verify the summary Miles Driven is set to "<MilesDriven>"
    And  I verify the summary Tire Size is set to "<ResultsTireSize>"
    And  I verify the "performance" summary Driving Priorities
    And  I verify all tire images are on results page
    And  I verify the tire brand "<Brand>" is listed on results page
    And  I verify the tire name "<TireName>" is listed on results page
    And  I verify the front tire size "<FrontSize>" is listed on results page
    And  I verify the rear tire size "<RearSize>" is listed on results page
    And  I verify the item code "<ItemCode1>" is listed on results page
    And  I verify the item code "<ItemCode2>" is listed on results page
    And  I verify the "wet climate stop" label is listed only once on results page
    And  I verify the "expected tire life" label is listed only once on results page
    And  I verify all View Details buttons are enabled on results page
    And  I verify the item stock status label is present for all the product items on results page
    And  I verify the product article number label is present for all the product items on results page
    And  I verify the item qty stock status label is present for all the product items on results page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | TireSize                          | ZipCode | CityState      | MilesDriven | ResultsTireSize | Brand   | TireName    | FrontSize              | RearSize                | ItemCode1 | ItemCode2 |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | Front: 255/35 R19Rear: 275/35 R19 | 55125   | SAINT PAUL, MN | 5000        | 255/35 R19      | General | G-MAX AS-03 | 255 /35 R19 96W XL BSW | 275 /35 R19 100W XL BSW | 32942     | 32943     |

  @smoketest_pdl
  @9841
  @tireComparisonEngage
  Scenario Outline: PDL_RE3-01_RESULTS_Tire Comparison engage (ALM #9841)
    When  I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I switch to performance for driving priorities
    Then I verify the "performance" Driving Priorities
    And I verify the zip code is set to "<ZipCode>"
    And I verify the city and state are set to "<CityState>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId1>" from the products list results
    Then I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId2>" from the products list results
    Then I verify that compare tires button is "enabled" on results page
    And  I verify the status of product checkbox for item "<ItemId3>" is "disabled"
    When I select the compare tires button
    Then I verify tire comparison page header is "TIRE COMPARISON"

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | Year | Make  | Model | Trim     | Assembly | TireSize   | ItemId1 | ItemId2 | ItemId3 |
      | MNM29   | 919919    | 55125   | SAINT PAUL, MN | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 19661   | 27334   | 28449   |

  @regression_pdl
  @11290
  Scenario Outline: PDL_DD1-05_1StandAlone_PayrollIDError (ALM #11290)
    Then I verify Store ID is displayed on the home page
    And  I verify Payroll ID is displayed on the home page
    And  I verify that the "Enter" button is disabled
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I verify "Payroll ID" message on home page

    Examples:
      | StoreID | PayrollID |
      | MNM29   | 1234567   |

  @regression_pdl
  @10913
  Scenario Outline: PDL_DD1-05_1StandAlone_PayrollIDError (ALM #10913)
    Then I verify Store ID is displayed on the home page
    And  I verify Payroll ID is displayed on the home page
    And  I verify that the "Enter" button is disabled
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I verify "Store ID" message on home page

    Examples:
      | StoreID | PayrollID |
      | AUTO 01 | 123456    |

  @smoketest_pdl
  @10837
  @tireComparisonStaggered
  Scenario Outline: PDL_TC1-01_Tire Comparison_Staggered (ALM #10837)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I switch to performance for driving priorities
    Then I verify the "performance" Driving Priorities
    And  I verify the zip code is set to "<ZipCode>"
    And  I verify the city and state are set to "<CityState>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId1>" from the products list results
    Then I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId2>" from the products list results
    Then I verify that compare tires button is "enabled" on results page
    And  I verify the status of product checkbox for item "<ItemId3>" is "disabled"
    When I select the compare tires button
    Then I verify tire comparison page header is "TIRE COMPARISON"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the back button is labeled "Results"
    And  I verify all tire images are on the compare page
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId1>"
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId2>"
    And  I verify each tire in all comparison sections contains a graph
    And  I verify each comparison section of the tire comparison page contains at least one green ribbon
    When I click the Results button to return to the Results screen
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | Year | Make  | Model | Trim     | Assembly | TireSize   | ItemId1 | ItemId2 | ItemId3 |
      | MNM29   | 919919    | 55125   | SAINT PAUL, MN | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 19661   | 27334   | 28449   |

  @regression_pdl
  @smoketest_pdl
  @9890
  @10992
  @tireComparison
  Scenario Outline: PDL_TC1-01_Tire Comparison  (ALM #9890)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I switch to performance for driving priorities
    Then I verify the "performance" Driving Priorities
    And  I verify the zip code is set to "<ZipCode>"
    And  I verify the city and state are set to "<CityState>"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I verify that the Tire Size value is "<TireSize>"
    When I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId1>" from the products list results
    Then I verify that compare tires button is "disabled" on results page
    When I select the product checkbox for item "<ItemId2>" from the products list results
    Then I verify that compare tires button is "enabled" on results page
    And  I verify the status of product checkbox for item "<ItemId3>" is "disabled"
    When I select the compare tires button
    Then I verify tire comparison page header is "TIRE COMPARISON"
    And  I verify the vehicle year is set to "<Year>" at upper left header
    And  I verify the vehicle make is set to "<Make>" at upper left header
    And  I verify the vehicle model is set to "<Model>" at upper left header
    And  I verify the vehicle trim is set to "<Trim>" at upper left header
    And  I verify the back button is labeled "Results"
    And  I verify all tire images are on the compare page
    And  I verify each tire contains customer rating stars
    And  I verify each tire contains customer recommendation numbers
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId1>"
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId2>"
    And  I verify each tire in all comparison sections contains a graph
    And  I verify each comparison section of the tire comparison page contains at least one green ribbon
    When I click the Results button to return to the Results screen
    Then I am brought to the page with header "RESULTS"

    Examples:
      | StoreID | PayrollID | ZipCode | CityState      | Year | Make  | Model | Trim     | Assembly | TireSize   | ItemId1 | ItemId2 | ItemId3 |
      | MNM29   | 919919    | 55125   | SAINT PAUL, MN | 2012 | Honda | Civic | Coupe DX | none     | 195/65 R15 | 19661   | 27334   | 28449   |

  @regression_pdl
  @10991
  @tireComparisonStaggeredPerformance
  Scenario Outline: PDL_TC1-01_Tire Comparison_Staggered_Performance (ALM #10991)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    And  I switch to performance for driving priorities
    Then I verify the "performance" Driving Priorities
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select Not your tire under Tire Size
    And  I select staggered set "<Set>"
    And  I select optional tire sizes for front "<FrontTireSize>" and for rear "<RearTireSize>"
    And  I select View Tire Recommendations
    And  I select the product checkbox for item "<ItemId1>" from the products list results
    And  I select the product checkbox for item "<ItemId2>" from the products list results
    And  I select the compare tires button
    Then I verify the back button is labeled "Results"
    And  I verify all tire images are on the compare page
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId1>"
    And  I verify vendor, model, size, and price of the tire with item id "<ItemId2>"
    And  I verify each tire in all comparison sections contains a graph
    And  I verify each comparison section of the tire comparison page contains at least one green ribbon
    When I select "View Details" for item id "<ItemId1>" from the compare page
    Then I verify tire details page header is "TIRE DETAILS"
    And  I verify the tire image and alt text is displayed on tire details page
    And  I verify the tire brand "<Brand>" is listed on tire details page
    And  I verify the tire name "<TireName>" is listed on tire details page
    And  I verify the front tire item id "<ItemId1>" is listed on tire details page
    And  I verify the front tire quantity is "2" on tire details page
    And  I verify the rear tire quantity is "2" on tire details page
    And  I verify the stopping distance ratings labels are listed on tire details page
    And  I verify the expected tire life range labels are listed on tire details page
    And  I verify the cost rating miles per dollar label is listed on tire details page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | FrontTireSize | RearTireSize | Set   | ItemId1 | ItemId2 | Brand       | TireName                   |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | 265/35-18     | 285/35-18    | 18-18 | 36251   | 26043   | Bridgestone | POTENZA S-04 POLE POSITION |

  @regression_pdl
  @9843
  Scenario Outline: PDL_RE2-02_RESULTS_FILTER RESULTS-Brands (ALM # 9843)
    When I login with Store ID "<StoreID>" and Payroll ID "<PayrollID>"
    Then I am brought to the page with header "DRIVING DETAILS"
    When I select a vehicle with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    And  I select View Tire Recommendations
    Then I am brought to the page with header "RESULTS"
    And  I verify the item code "<ItemCode1>" is listed on results page
    And  I verify the item code "<ItemCode2>" is listed on results page
    And  I verify the item code "<ItemCode3>" is listed on results page
    When I select the filter results button
    And  I verify the item code "<ItemCode1>" is listed on results page
    And  I verify the item code "<ItemCode2>" is listed on results page
    And  I verify the item code "<ItemCode3>" is listed on results page

    Examples:
      | StoreID | PayrollID | Year | Make | Model | Trim  | Assembly                  | ItemCode1 | ItemCode2 | ItemCode3 |
      | MNM29   | 919919    | 2015 | BMW  | M3    | Sedan | F 255/35-19 - R 275/35-19 | 18596     | 18606     | 26022     |