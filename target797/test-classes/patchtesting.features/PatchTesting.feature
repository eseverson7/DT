@security
Feature: Security Patch Testing

  Background:
    Given I go to the 'Knowledge Center' home page
    And   I log in to the 'Knowledge Center' site

  @safety
  Scenario Outline: Verify safety videos
    When I select "<MenuItem>" sub-menu item under the "<MenuItem>" menu header option
    And  I select the video display box under the word SAFETY
    Then I verify the video appears on the center of screen
    When I switch to the popup video iframe window in position "1"
    Then I verify the popup video is playing
    When I switch back to the default content window
    And  I close the currently playing video
    And  I select the "Core Materials" link on the page
    And  I select the "Safety Leader Orientation Presentation" link on the page
    And  I select the "Safety Leader Participant Guide" link on the page
    Then I verify the "New" URL is displayed as "http://portalprd.discounttire.com/portal/server.pt/document/1911650/Safety+Leader+Participant+Guide"

    Examples:
      | MenuItem |
      | Safety   |

  @humanResources
  Scenario Outline: HR Videos
    When I select "HR Home" sub-menu item under the "Human Resources" menu header option
    And  I select the "<Link>" link on the page
    And  I verify the "<FileName>" file has downloaded to the Downloads folder
    Then I remove the "<FileName>" from the computer

    Examples:
      | Link                   | FileName                    |
      | 2018 PTO Form (Office) | 2018+PTO+Form+(Office).xlsx |
      | Employee Anniversaries | Employee+Anniversaries.xlsx |

  @dtd
  Scenario Outline: DTD
    When I select "Sales & Marketing" sub-menu item under the "DTD" menu header option
    And  I select the "Warranty" link on the page
    And  I select the "DTD Out of State Road Hazard Replacement Policy" link on the page
    And  I verify the "<DocumentTitle>" file has downloaded to the Downloads folder
    Then I remove the "<DocumentTitle>" from the computer

    Examples:
      | DocumentTitle                                       |
      | DTD+Out+of+State+Road+Hazard+Replacement+Policy.doc |

  @regions
  Scenario Outline: Regions
    When I select "Arizona" sub-menu item under the "Regions" menu header option
    And  I select the "<Link>" link on the page
    And  I verify the "<FileName>" file has downloaded to the Downloads folder
    Then I remove the "<FileName>" from the computer

    Examples:
      | Link                           | FileName                       |
      | AZP Regional Training Calendar | AZP+Regional+Training+Calendar |

  @epic
  Scenario: Epic test
    When I select "EPIC" sub-menu item under the "EPIC" menu header option
    Then I verify EPIC video appears on the center of screen
    And  I click on video displayed in the middle of the screen and verify it is playing

  @corporateHaha
  Scenario Outline: Corporate test
    When I select "Corporate Home" sub-menu item under the "Corporate" menu header option
    And  I select the "Discount Tire Fax Template" link on the page
    And  I select the "Discount Tire Memo Template" link on the page
    Then I verify the "<FileName1>" file has downloaded to the Downloads folder
    When I remove the "<FileName1>" from the computer
    Then I verify the "<FileName2>" file has downloaded to the Downloads folder
    When I remove the "<FileName2>" from the computer

    Examples:
      | FileName1                      | FileName2                       |
      | Discount+Tire+Fax+Template.doc | Discount+Tire+Memo+Template.doc |

  @leadSpeedTrust
  Scenario Outline: Lead Speed of Trust
    When I select "LEAD" sub-menu item under the "<MenuItem>" menu header option
    And  I click on video displayed in the middle of the screen and verify it is playing
    And  I select the "<Link2>" link on the page
    And  I switch to the newly opened window
    Then I verify the "New" URL is displayed as "http://portaldocs.dtcphx.com/learning/LEAD/tutorials/LEAD_Portal_Project_WIGs/story.html"

    Examples:
      | MenuItem            | Link2        |
      | LEAD/Speed of Trust | Project WIGs |

  @certifiedBestPractices
  Scenario Outline: Best Practices Tests
    When I select the "<MenuItem>" link on the page
    Then I click on video displayed in the middle of the screen and verify it is playing

    Examples:
      | MenuItem                 |
      | Certified Best Practices |

  @ces
  Scenario Outline: CES Tests
    When I select "3 Phase CES & ETV" sub-menu item under the "CES" menu header option
    Then I click on video displayed in the middle of the screen and verify it is playing
    When I select "Appointments Next in Bay" sub-menu item under the "CES" menu header option
    And  I select the "<Link>" link on the page
    And  I switch to the newly opened window
    Then I verify the "New" URL is displayed as "http://portalprd.discounttire.com/portal/server.pt/document/1931085/Appointment+Change+FAQs.pdf"

    Examples:
      | Link                    |
      | Appointment Change FAQs |

  @dtu1
  Scenario Outline: DTU1 Tests
    When I select "<SubMenu>" sub-menu item under the "<MenuItem>" menu header option
    And  I switch to the newly opened window
    Then I verify the "New" URL is displayed as "http://portalprd.discounttire.com/portal/server.pt/document/1918798/Resetting+DTU+Password.pdf"

    Examples:
      | MenuItem | SubMenu            |
      | DTU      | Reset LMS Password |

  @dtu2
  Scenario Outline: DTU2 Tests
    When I select "<SubMenu>" sub-menu item under the "<MenuItem>" menu header option
    And  I select the video element in the center of the DTU screen
    And  I switch to the popup video iframe window in position "1"
    And  I select the large play button in the middle of the popup video
    Then I verify the popup video is playing
    When I switch back to the default content window

    Examples:
      | MenuItem | SubMenu      |
      | DTU      | Competencies |

  @calculatorsPDF
  Scenario Outline: Calculators Tests
    When I select the "<MenuItem>" link on the page
    And  I select "<CalcMainMenu>" sub-menu item after selecting the "Special Orders" menu header option on the Calculators page
    And  I switch to the newly opened window
    Then I verify the "New" URL is displayed as "<URL>"

    Examples:
      | MenuItem    | CalcMainMenu           | URL                                                                                            |
      | Calculators | Purchase Decision Tree | http://portalprd.discounttire.com/portal/server.pt/document/1893372/Purchase+Decision+Tree.pdf |
      | Calculators | Wheel Vendors          | http://portalprd.discounttire.com/portal/server.pt/document/1808823/Wheel+Vendors+QRG.pdf      |

  @calculatorsVideo
  Scenario Outline: Calculators Video Tests
    When I select the "<MenuItem>" link on the page
    And  I select "<CalcMainMenu>" sub-menu item after selecting the "Education" menu header option on the Calculators page
    Then I verify the video appears on the center of screen
    When I switch to the popup video iframe window in position "2"
    And  I select the large play button in the middle of the popup video
    Then I verify the popup video is playing

    Examples:
      | MenuItem    | CalcMainMenu       |
      | Calculators | Air Pressure Video |

  @contactsExcel
  Scenario Outline: Contacts with Excel files Tests
    When I select the "<MenuItem>" link on the page
    And  I select the "<Link>" link on the page
    Then I verify the "<FileName>" file has downloaded to the Downloads folder
    And  I remove the "<FileName>" from the computer

    Examples:
      | MenuItem | Link                      | FileName                              |
      | Contacts | All Corporate Departments | Corporate+Numbers.xls                 |
      | Contacts | Accounts Payable          | AP-Inventory+Analyst+Contact+List.xls |
      | Contacts | Accounts Receivable       | AR+Contact+List.xls                   |
      | Contacts | Inventory Management      | Inventory+Contact+List.XLSX           |

  @contactsPDF
  Scenario Outline: Contacts with PDF Tests
    When I select the "<MenuItem>" link on the page
    And  I select the "<Link>" link on the page
    And  I switch to the newly opened window
    Then I verify the current URL contains "<FileName>"

    Examples:
      | MenuItem | Link              | FileName                                                                                  |
      | Contacts | Business Segments | http://portalprd.discounttire.com/portal/server.pt/document/1916301/Business+Segments.pdf |

  @forms
  Scenario Outline: Forms Tests
    When I select the "<MenuItem>" link on the page
    And  I select the "<Link1>" link on the page
    And  I select the "<Link2>" link on the page
    Then I verify the "<FileName>" file has downloaded to the Downloads folder
    And  I remove the "<FileName>" from the computer

    Examples:
      | MenuItem | Link1                  | Link2                          | FileName                       |
      | Forms    | Building & Maintenance | Maintenance Work Order Request | Building+Maintenance+Form.html |

  @orders
  Scenario Outline: Orders Tests
    When I select the "<MenuItem>" link on the page
    And  I select the "<Link1>" link on the page
    And  I select the "<Link2>" link on the page
    And  I switch to the newly opened window
    Then I verify the "New" URL is displayed as "<FileName>"

    Examples:
      | MenuItem | Link1                                                                                 | Link2        | FileName                                                                |
      | Orders   | Equipment, Productive Supplies, Cleaning Supplies, Regional and Warehouse Order Forms | Grainger     | http://portaldocs.dtcphx.com/storeops/equipment/grainger/grainger.shtml |
      | Orders   | Equipment, Productive Supplies, Cleaning Supplies, Regional and Warehouse Order Forms | Office Depot | https://business.officedepot.com                                        |

  @salesPromotions
  Scenario Outline: Sales Promotions Tests
    When I select the "<MenuItem>" link on the page
    And  I select the "<Link>" link on the page
    Then I verify the "Same" URL is displayed as "http://portalprd.discounttire.com/portal/server.pt/document/1901853/PC+87994+%2450_%24100+DTD+Event+Offer+%23+26077"

    Examples:
      | MenuItem         | Link                                      |
      | Sales Promotions | PC 87994 $50_$100 DTD Event Offer # 26077 |

  @websites
  Scenario Outline: Websites Tests
    When I select the "<MenuItem>" link on the page
    And  I select the "<Link1>" link on the page
    And  I select the "<Link2>" link on the page
    Then I verify the "Same" URL is displayed as "<URL>"

    Examples:
      | MenuItem | Link1                                     | Link2                     | URL                                           |
      | Websites | Transfer Programs, Shipping & Sales Tools | Cleveland Tire (S&S)      | https://cleveland.tireweb.com                 |
      | Websites | Transfer Programs, Shipping & Sales Tools | Capital Tire Inc          | https://www.capitaltire.net/storefrontB2BWEB/ |
      | Websites | Transfer Programs, Shipping & Sales Tools | Hesselbein Tire Southwest | https://dandk.tireweb.com/                    |

  @contactUs
  Scenario Outline: Contact Us Tests
    When I select the "<MenuItem>" link on the page
    Then I verify "Contact Us" is displayed on the page

    Examples:
      | MenuItem   |
      | Contact Us |

  @bibExpress
  Scenario Outline: Bib Express Tests
    When I select the image with alt text of "<AltText>"
    And  I select the "<MenuItem>" link on the page
    And  I switch to the newly opened window
    Then I verify the "New" URL is displayed as "http://portalprd.discounttire.com/portal/server.pt/document/1944126/Transfer+Program+-+MAST+and+TIPS.pdf"
    When I navigate to the "<URL>"
    Then I verify "<Message>" is displayed on the page

    Examples:
      | AltText          | MenuItem    | URL                        | Message                                                      |
      | Knowledge Center | Bib Express | https://tcitips.com/TIPS3/ | Welcome to TIPS. Register NOW to start ordering tires today! |

  @blackburnWheels
  Scenario Outline: Blackburn Wheels Tests
    When I select the image with alt text of "<AltText>"
    When I select the "<MenuItem>" link on the page
    And  I switch to the newly opened window
    Then I verify "<Message>" is displayed on the page
    When I select the "<Button>" link on the page
    And  I switch to the newly opened window
    Then I verify the "New" URL is displayed as "https://www.mywheelfinder.com/themes/Blackburns/Content/Discount%20Tire%20Tutorial%20Final3.pdf"

    Examples:
      | AltText          | MenuItem         | Message                         | Button          |
      | Knowledge Center | Blackburn Wheels | ENTER YOUR SITE NUMBER TO LOGIN | View User Guide |

  @eColtiLink
  Scenario Outline: eColtiLink Tests
    When I select the image with alt text of "<AltText>"
    And  I select the "<MenuItem>" link on the page
    And  I switch to the newly opened window
    Then I verify "Log In" is displayed on the page

    Examples:
      | AltText          | MenuItem   |
      | Knowledge Center | eContiLink |