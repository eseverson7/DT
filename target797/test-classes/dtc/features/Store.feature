@store
Feature: Store

  Background:
    Given I go to the homepage

  @dt
  @myStoreGeopip
  @8550
  @web
  Scenario Outline: Verify store location based on GeopIP (ALM #8550)
  """TODO: This data does not match what is in staging or QA."""
    When I click on "My Store" title
    Then I verify the default "<StoreAddress>" in the popup
    When I click on Store details button in My Store popup
    Then I verify the "<StoreTitle>", "<StoreAddress>", "<Days>" of the current store

    Examples:
      | StoreAddress                   | StoreTitle                   | Days                 |
      | 8799 E Frank Lloyd Wright Blvd | Discount Tire Store (AZP 29) | Mon-Fri:, Sat:, Sun: |

  @dt
  @bba
  @storeLocatorRedirectDtToAtBackToDt
  @8555
  @web
  @mobile
  @storeBBA
  Scenario Outline: Store Locator redirect from DT to AT back to DT (ALM #8555)
  """TODO: Need to interact with Modal that appears for Service Area Change"""
    When I search for store within "<Range>" miles of "<A Non-Discount Tire Service Area>"
    Then I should be redirected to the site for "America's Tire"
    When I search for store within "<Range>" miles of "<A Discount Tire Service Area>"
    Then I should be redirected to the site for "Discount Tire"

    Examples:
      | Range | A Non-Discount Tire Service Area | A Discount Tire Service Area |
      | 25    | 96002                            | 85250                        |

  @dt
  @storeLocatorRedirectToDtd
  @8556
  @9388
  @web
  Scenario Outline: Store Locator greater than 75 miles redirect from DT to DTD (ALM #8555,9388)
    When I search for store within "<Range>" miles of "<A Non-Discount Tire Service Area>"
    Then I should see a prompt for "Discount Tire Direct"
    When I select "Continue"
    Then I am brought to the "Discount Tire Direct" site

    Examples:
      | Range | A Non-Discount Tire Service Area |
      | 75    | New York                         |
      | 75    | 11397                            |

  @bba
  @dtd
  @8566
  @web
  @storeBBA
  Scenario Outline: DTD Installer locator shows non discount tires installer partner list(ALM #8566)
    When I search for store within "<Range>" miles of "New York"
    Then I should see this partner installer "<Partner Installer>" is present in the displayed stores list
    And  I should confirm schedule appointment option is not available to users on DTD store locator page
    When I select "Directions" for store #"1" in the store location results
    And  I navigate to newly opened next tab
    Then I am brought to the page with path "www.google.com/maps/dir//619+Classon+Ave,+Brooklyn,+NY+11238"
    When I navigate to previous browser tab

    Examples:
      | Range | Partner Installer                |
      | 75    | Savage Wheels & Things Tire Shop |

  @at
  @dt
  @8578
  @mobile
  @web
  Scenario Outline: HYBRIS_139_Store_StoreLocator Send Store Adderss to Customer as Text (ALM #8578)
    When I search for store within "25" miles of "<ZipCode>"
    And  I close the Welcome Popup
    And  I select "Send to phone" for store #"1" in the store location results
    Then I verify "<Address>" appears in the Send to Phone dialog message
    When I enter phone number: "<PhoneNumber>" in the Send to Phone dialog
    Then I confirm the Send to Phone results popup

    Examples:
      | ZipCode | PhoneNumber  | Address          |
      | 86001   | 555-555-5555 | 1230 S MILTON RD |

  @dt
  @web
  @8565
  Scenario Outline: HYBRIS_140_Store_StoreLocator Make Appointment to the Store (ALM #8565)
    When I search for store within "25" miles of "86001"
    And  I select "Schedule appointment" for store #"1" in the store location results
    And  I select service option(s): "<ServiceOptions>"
    And  I select default date and time
    And  I click continue for appointment customer details page
    And  I select "Schedule Appointment" after entering customer information for "<Customer>"
    Then I should see an appointment confirmation message for "<Customer>" with service options: "<ServiceOptions>"
    And  I store the order number

    Examples:
      | ServiceOptions            | Customer            |
      | Tire Rotation and Balance | default_customer_az |