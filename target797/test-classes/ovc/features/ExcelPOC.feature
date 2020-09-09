@ovc
@excelPOC
Feature: POC for pulling data from Excel

  Scenario: Oneview_Fitment_No_Customer_Present_On_VR (ALM# 11616)
    When I go to the ovc homepage
    And  I login with Server, Username, and Password
    When I select the "Fitment" icon from the Global Header
    And  I select vehicle data for feature "ExcelPOC" and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select "2" sizes from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    Then I verify the refined fitment product results display on page