@domElementsExtractor
@web
Feature: Page DOM Elements Extraction and Export to Excel with JSoup


Background:
    Given I go to the homepage

  Scenario Outline: Page DOM Elements Extraction and Export to Excel with JSoup
    When I navigate to the "<Path>" url
    Then I extract the DOM elements from "<PageName>" page and store in "<ExcelName>" excel spreadsheet

    Examples:
      | Path                                                                                           | ExcelName                  | PageName               |
      | /                                                                                              | Home_Page                  | homePage               |
      | /schedule-appointment                                                                          | Schedule_Appointment_Page  | Schedule Appointment   |
      | /schedule-appointment/date?isPickupOnly=true                                                   | SA_Select_DataTime_Page    | SA_Select_DataTime     |
      | /schedule-appointment/customer-info                                                            | Customer_Information_Page  | Customer Information   |
      | /buy-tires/goodyear-eagle-f1-asymmetric-as/p/14990?all-vehicles=true#                          | Product_Details_Page       | Product_Details        |
      | /review/product/comparison?productCode=34302&allVehicles=true                                  | Tire Review Page           | Tire_Review            |
      | /tires/brands                                                                                  | Tire_All_Brands_Page       | Tire_Brands            |
      | /tires                                                                                         | Tires_Page                 | Tires                  |
      | /wheels/brands                                                                                 | All_Brands_Page            | All Brands             |
      | /wheels                                                                                        | Wheels_Page                | Wheels                 |
      | /wheels/painted-catalog?q=%3Aprice-asc%3Abrands%3Abrand-KON%3Abrands%3Abrand-MBM&text=&true=on | Check_Inventory_Page       | Check_Inventory        |
      | /store-locator                                                                                 | Store_Locator_Page         | Store_Locator          |
      | /store/az/scottsdale/s/1344?lat=33.628572&long=-111.891134                                     | Store_Details_Page         | Store_Details          |
      | /cart                                                                                          | Cart_Page                  | Cart                   |
      | /checkout/info                                                                                 | Checkout_Page              | Checkout               |
      | /checkout/appointment-info                                                                     | Checkout_Appointment_Page  | Checkout_Appointment   |
      | /checkout/customer-info                                                                        | Checkout_Customer_Info_Page| Checkout_Customer_Info |
      | /checkout/info                                                                                 | Checkout_Store_Info_Page   | Checkout_Store_Info    |
