@ovc
@fitment
Feature: Fitment Tests

  Background:
    Given I go to the ovc homepage
    When  I login with Server, Username, and Password

  @ovcSmokeTest
  @ovcBBA
  @11616
  Scenario Outline: Oneview_Fitment_No_Customer_Present_On_VR (ALM# 11616)
    When I select the "Fitment" icon from the Global Header
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select selections from feature "<Feature>" from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    Then I verify the refined fitment product results display on page

    Examples:
      | Feature |
      | Fitment |

  @ovcBBA
  @11621
  Scenario Outline: Oneview_Fitment_Staggered (ALM# 11621)
    When I select the "Fitment" icon from the Global Header
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select selections from feature "<Feature>" from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    Then I verify the refined fitment product results display on page

    Examples:
      | Feature |
      | Fitment |

  @ovcRegression
  @ovcBBA
  @12913
  Scenario Outline: Oneview_Orders_Sales_Certificates_add_fitment (ALM# 12913)
    When I select the "Fitment" icon from the Global Header
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select selections from feature "<Feature>" from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    Then I verify the refined fitment product results display on page
    When I select the "Customer" icon from the Global Header
    Then I verify "First Name" field is displayed on customer page
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Vehicles" button on the "Home" page
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the vehicle description for feature "<Feature>" appears on the home page
    When I enter "ItemNumber" for feature "<Feature>" in the article search box
    And  I extract the "Balance" total from the home page
    And  I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    Then I verify tender amount equals the order price total
    When I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature |
      | Fitment |

  @ovcBBA
  @11619
  Scenario Outline: Oneview_Fitment_Vehicle_Change (ALM# 11619)
    When I select the "Fitment" icon from the Global Header
    Then I am brought to the page with header "Select or Enter Vehicle"
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select "2" sizes from the Tires and Wheels results table
    And  I select the "Cancel" button on the "Fitment" page
    Then I am brought to the page with header "Select or Enter Vehicle"

    Examples:
      | Feature |
      | Fitment |

  @11620
  @ovcBBA
  Scenario Outline: Oneview_Fitment_Warning_Fitment_Message (ALM# 11620)
  """TODO: Fails at 'I verify the FitmentValue appears'  due to Defect_ID 8828 - Item description shows as 'NULL'"""
    When I select the "Fitment" icon from the Global Header
    And  I select vehicle data for feature "<Feature>" and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select selections from feature "<Feature>" from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    Then I verify the refined fitment product results display on page
    When I enter "<VRItem1>" into the "SKU" textbox and send Enter key
    And  I select the "Search" button on the "Lookup" page
    And  I select "VRItem1" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "FitmentValue" appears in the feature "<Feature>" Fitment Qualifiers popup
    When I close the Fitment Qualifier popup window
    Then I verify the "VRItem1" of the item for feature "<Feature>" appears on the home page
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    Then I verify customer for feature "<Feature>" added to the virtual receipt
    When I select the "Vehicles" button on the "Home" page
    And  I select the "NewVehicle" button for feature "<Feature>" on the "Popup" page
    Then I verify that the "NewVehicle" description for feature "<Feature>" appears on the home page
    And  I verify the Yellow Warning triangle "Does" appear in the row with "VRItem1" for feature "<Feature>"
    When I select the "Checkout" button on the "Home" page
    Then I verify the dialog contains "Fitment Warning"
    When I select the "Cancel" button on the "Popup" page
    And  I select the "NewVehicle" description for the Home page feature
    And  I select the "CustomerVehicle" button for feature "<Feature>" on the "Popup" page
    And  I select the "Home" icon from the Global Header
    Then I verify the Yellow Warning triangle "Does Not" appear in the row with "VRItem1" for feature "<Feature>"
    When I select the "Checkout" button on the "Home" page
    And  I select the "Continue" button on the "Popup" page
    And  I select the "Cash" link from the Checkout menu
    And  I select the "Next" button on the "Popup" page
    And  I select the "Service Later/Complete" button on the "Popup" page
    Then I save the order receipt after verifying that the order number is visible

    Examples:
      | Feature | VRItem1 |
      | Fitment | 37031   |

  @11617
  Scenario Outline: Oneview_Fitment_Customer_Present_On_VR (ALM# 11617)
  """ Blue bar on bottom of screen - Defect ID 9430 """
    When I select the "Customer" icon from the Global Header
    And  I enter the Full Name for the customer in feature "<Feature>" into the textbox
    And  I select the "Search" button on the "Customer" page
    And  I select the customer for feature "<Feature>" from the Customer table
    And  I select the "Select" button on the "Customer" page
    And  I select the "Fitment" icon from the Global Header
    And  I select the "CustomerVehicle" vehicle element from the Fitment page
    And  I select the "Fitment" button on the "Fitment" page
    And  I select "1" sizes from the Tires and Wheels results table
    And  I select selections from feature "<Feature>" from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    Then I verify the refined fitment product results display on page

    Examples:
      | Feature |
      | Fitment |

  @ovcBBA
  @11846
  Scenario Outline: Oneview_Product_Lookup_Staggered_Tires (ALM#11846)
    When I select the "Fitment" icon from the Global Header
    And  I select vehicle data for feature "<Feature>" with Staggered options and complete a vehicle search
    And  I select the "Fitment" button on the "Fitment" page
    And  I select Staggered selections from feature "<Feature>" from the Tires and Wheels results table
    And  I select the "Product Results" button on the "Fitment" page
    And  I select "StaggeredItemNumber" for feature "<Feature>" from the Lookup results screen
    And  I select the "Add" button on the "Lookup" page
    Then I verify the "StaggeredItemNumber" of the item for feature "<Feature>" appears on the home page

    Examples:
      | Feature |
      | Fitment |