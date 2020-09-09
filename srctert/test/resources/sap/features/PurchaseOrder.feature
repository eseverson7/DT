@sap
@purchaseOrder
Feature: Purchase Order Test

  Background:
    Given I go to the sap homepage
    When  I login with System, Client, Username, and Password

  @poReceipt
  Scenario Outline: Purchase_Order_Receipt (ALM# None)
    When I enter t-code "<TCode>" in the command field
    Then I am brought to the page with the title "Goods Receipt Purchase Order"
    And  I verify the dropdown boxes are defaulted to "Goods Receipt" and "Purchase Order"
    When I enter the purchase order number in the entry field
    Then I verify the purchase order number is in the page title
    When I select the "Item Okay" checkbox for each article in the purchase order
    And  I click on the "Save" icon from the SAP navigation bar
    Then I verify the alert is displayed for the article document being posted

    Examples:
      | TCode |
      | MIGO  |

  @poConfirmation
  Scenario Outline: Purchase_Order_Confirmation (ALM# None)
    When I enter t-code "<TCode>" in the command field
    And  I send the shortcut for the "Other Purchase Order" page
    And  I enter the purchase order number into the popup search field
    And  I select the "Other Document" button on the popup
    Then I am brought to the page with the title "Store Merch PO"
    When I loop through each article and fill out the Confirmation Header and Order Acknowledgement
    And  I click on the "Save" icon from the SAP navigation bar
    Then I verify the alert is displayed for the store merch po being changed

    Examples:
      | TCode |
      | ME22N |

  @poCreation
  Scenario Outline: Purchase_Order_Creation (ALM# None)
    When I enter t-code "<TCode>" in the command field
    And  I select the PO type "<POType>"
    And  I enter the purchase order value for "Vendor" to "<Vendor>"
    And  I enter the purchase order value for "Purchase Group" to "<PurchaseGroup>"
    And  I enter the purchase order value for "Article Number" to "<ArticleNumber>"
    And  I enter the purchase order value for "Quantity" to "<Quantity>"
    And  I enter the purchase order value for "Site" to "<Site>"
    And  I click on the "Save" icon from the SAP navigation bar
    Then I save the purchase order number to the scenario data

    Examples:
      | TCode | POType         | Vendor | PurchaseGroup | ArticleNumber | Quantity | Site |
      | ME21N | Store Merch PO | 22055  | 01            | 17899         | 1        | 9102 |
      
  @poValidate
  Scenario Outline: Purchase_Order_Validation (ALM# None)
    When I enter t-code "<TCode>" in the command field
    And  I search the desired PO number
    And  I select the "Other Document" button on the popup

    Examples:
      | TCode | POType         | Vendor | PurchaseGroup | ArticleNumber | Quantity | Site |
      | ME23N | Store Merch PO | 22055  | 01            | 17899         | 1        | 9102 |