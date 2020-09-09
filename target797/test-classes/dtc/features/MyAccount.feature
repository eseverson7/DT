@myAccount
Feature: My Accounts
"""TODO: Current Customer Email Test Data will be replaced by Automation Test Data In Customer Data refactoring branch
   Once we reset mechanism setup by Developers"""

  Background:
    Given I change to the default store

  @dt
  @at
  @dtd
  @web
  @myAccountUI
  Scenario Outline: Verify the MyAccount Fields (ALM# -----)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    And  I should see email address field is displayed
    And  I should see password field is displayed
    And  I verify that "Sign-In" is disabled by default
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "Sign-In" is enabled
    And  I verify keep me signed-in option is displayed
    When I select keep me signed-in option
    And  I select sign-in
    Then I should see "<My First Name>" displayed on homepage header

    Examples:
      | My Email             | Password   | My First Name |
      | samuel123@hybris.com | Testing123 | Samuel        |

  @dt
  @at
  @dtd
  @14994
  @web
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Create Account_Field Validation (ALM# 14994)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    And  I should see email address field is displayed
    And  I should see password field is displayed
    And  I verify keep me signed-in option is displayed
    And  I verify that "Sign-In" is disabled by default
    When I select sign-up now
    Then I should see first name field is displayed
    And  I should see last name field is displayed
    And  I should see email address field is displayed
    And  I should see password field is displayed
    And  I verify that "CREATE AN ACCOUNT" is disabled by default
    When I enter "<First Name>" in first name field
    And  I enter "<Last Name>" in last name field
    And  I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "CREATE AN ACCOUNT" is enabled
    And  I verify email validation error message should display for all invalid email addresses
    And  I verify the password requirement validation for passed progress steps
    When I click on the "Sign-In" Link
    Then I verify my account sign-in modal is displayed

    Examples:
      | My Email             | Password   | First Name | Last Name |
      | samuel123@hybris.com | Testing123 | IMA        | QA USER   |

  @dt
  @at
  @dtd
  @web
  @6798
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Sign In to an Account_Happy Path (ALM# 6798)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "Sign-In" is enabled
    When I select sign-in
    Then I should see "<My First Name>" displayed on homepage header

    Examples:
      | My Email                     | Password   | My First Name |
      | ankit.arora@discounttire.com | Testing123 | Ankit         |

  @dt
  @at
  @dtd
  @15261
  @15037
  @web
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Create Account_Happy Path (ALM# 15261,15037)
    When I go to the homepage
    And  I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    When I select sign-up now
    Then I verify that "CREATE AN ACCOUNT" is disabled by default
    When I enter "<First Name>" in first name field
    And  I enter "<Last Name>" in last name field
    And  I generate and set random email address for user "<First Name>.<Last Name>"
    And  I enter "<Password>" in password field
    Then I should see "CREATE AN ACCOUNT" is enabled
    When I select create an account option
    Then I am brought to the page with header "<Header>"
    And  I verify email sent confirmation message displayed on account confirmation modal
    And  I verify my email address displayed on account confirmation modal
    And  I verify my email authentication link message displayed on account confirmation modal
    When I select continue shopping option
    Then I verify Join/Sign-in is displayed on homepage

    Examples:
      | Password   | First Name | Last Name | Header                                   |
      | Testing123 | IMA        | QAUSER    | You've successfully created your account |

  @dt
  @at
  @dtd
  @15002
  @web
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Sign In - Reset Password validation (ALM# 15002)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    And  I should see email address field is displayed
    And  I should see password field is displayed
    And  I verify keep me signed-in option is displayed
    And  I verify that "Sign-In" is disabled by default
    When I select forgot password option
    Then I verify Reset Your Password instruction displayed on forgot password modal
    And  I verify Reset Your Password email validation error message should display for all invalid email addresses
    When I enter "<My Email>" in reset password email address field
    And  I select reset password option
    Then I verify Reset Your Password instruction displayed after reset password

    Examples:
      | My Email             |
      | samuel123@hybris.com |

  @dt
  @at
  @dtd
  @6558
  @web
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Global Header(ALM #6558)
    When I do a vehicle search with details "<Year>" "<Make>" "<Model>" "<Trim>" "<Assembly>"
    Then I should see the fitment panel page with vehicle "<Year> <Make>"
    And  I verify Join/Sign-in is displayed on global header
    When I select a fitment option "<FitmentOption>"
    Then I verify the PLP header message contains "<Header>"
    And  I verify Join/Sign-in is displayed on global header
    When I go to the homepage
    And  I do a "homepage" wheel size search with details "<Diameter>" "<WheelWidth>" "<BoltPattern>"
    Then I verify the PLP header message contains "<Header1>"
    And  I verify Join/Sign-in is displayed on global header
    When I go to the homepage
    And  I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I add item to my cart and "View shopping cart"
    And  I select the checkout option "<Checkout>"
    Then I verify Join/Sign-in is displayed on global header
    When I go to the homepage
    And  I select "footer" "About Us"
    Then I verify Join/Sign-in is displayed on global header
    When I select "footer" "Contact Us"
    Then I verify Join/Sign-in is displayed on global header
    When I select "footer" "Credit"
    Then I verify Join/Sign-in is displayed on global header
    When I select "footer" "Customer Care"
    Then I verify Join/Sign-in is displayed on global header
    When I select "footer" "More Topics..."
    Then I verify Join/Sign-in is displayed on global header

    Examples:
      | Year | Make  | Model | Trim     | Assembly | FitmentOption | Header       | Diameter | WheelWidth | BoltPattern       | Header1       | ProductName  | ItemCode | Checkout |
      | 2012 | Honda | Civic | Coupe DX | none     | All tires     | tire results | 15       | 6.5        | 5-114.3 MM/5-4.5" | wheel results | Defender A/S | 34302    | default  |

  @dt
  @at
  @dtd
  @14979
  @web
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Sign In_Field Validation (ALM# 14979)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "Sign-In" is enabled
    When I select sign-in
    Then I verify email account not authenticated message displayed
    When I enter "<Wrong Email>" in email address field
    And  I enter "<Password>" in password field
    And  I select sign-in
    Then I should see invalid credentials error validation message
    When I enter "<Valid Email>" in email address field
    And  I enter "<Wrong Password>" in password field
    And  I select sign-in
    Then I should see invalid credentials error validation message

    Examples:
      | My Email  | Password   | Wrong Email | Valid Email                  | Wrong Password |
      | aa@aa.com | Testing123 | abc@xyz.com | ankit.arora@discounttire.com | T3STing789     |

  @dt
  @at
  @dtd
  @web
  @15034
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Profile Form UI Validation (ALM# 15034)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "Sign-In" is enabled
    When I select sign-in
    Then I should see "<My First Name>" displayed on homepage header
    When I select my profile and "View My Account"
    Then I am brought to the page with header "<Header>"
    And  I verify sign out option is displayed
    When I select my profile and "Sign out"
    Then I verify Join/Sign-in is displayed on homepage

    Examples:
      | My Email                     | Password   | My First Name | Header       |
      | ankit.arora@discounttire.com | Testing123 | Ankit         | Hello, Ankit |

  @dt
  @at
  @dtd
  @web
  @6800
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Profile - Sign In Tab- Edit Email Address (ALM# 6800)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "Sign-In" is enabled
    When I select sign-in
    Then I should see "<My First Name>" displayed on homepage header
    When I select my profile and "View My Account"
    Then I am brought to the page with header "<Header>"
    When I select edit for "Email Address"
    Then I should see my "<My Email>" populated in "Email" field

    Examples:
      | My Email                     | Password   | My First Name | Header       |
      | ankit.arora@discounttire.com | Testing123 | Ankit         | Hello, Ankit |

  @dt
  @at
  @dtd
  @web
  @6799
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Profile - Sign In Tab - Edit Password (ALM# 6799)
    When I go to the homepage
    Then I verify Join/Sign-in is displayed on homepage
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "Sign-In" is enabled
    When I select sign-in
    Then I should see "<My First Name>" displayed on homepage header
    When I select my profile and "View My Account"
    Then I am brought to the page with header "<Header>"
    When I select edit for "Email Address"
    Then I should see my "<My Email>" populated in "Email" field
    And  I verify that "DONE EDITING" is disabled by default
    When I close My Account modal popup
    And  I select edit for "Password"
    Then I verify that "DONE EDITING" is disabled by default
    When I enter "<Password>" in password field
    Then I should see "DONE EDITING" is enabled
    When I click on the "DONE EDITING" button
    Then I am brought to the page with header "<Header>"

    Examples:
      | My Email                     | Password   | My First Name | Header       |
      | ankit.arora@discounttire.com | Testing123 | Ankit         | Hello, Ankit |

  @dtd
  @web
  @5778
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Checkout Flow -  Sign In_DTD (ALM# 5778)
    When I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    Then I verify 'sign in to skip this step' is "displayed" in Checkout Page
    When I select 'sign in to skip this step'
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    And  I select sign-in
    Then I verify "<UserName>" is displayed on global header
    And  I am brought to the page with header "Checkout"
    And  I verify "First Name, Last Name, Address Line 1, Address Line 2, Zip Code, Email, Phone Number" values for "<Customer>" are now pre-populated

    Examples:
      | ItemCode | ProductName  | Checkout | My Email             | Password   | UserName | Customer             |
      | 34302    | Defender A/S | default  | ima@discounttire.com | Testing123 | IMA      | DEFAULT_CUSTOMER_DTD |

  @dt
  @at
  @web
  @15105
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Checkout Flow -Sign In_DT_Install without Appointment (ALM# 15105)
    When I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    And  I click next step for customer information
    Then I verify 'sign in to skip this step' is "displayed" in Checkout Page
    When I select 'sign in to skip this step'
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    And  I select sign-in
    Then I verify "<UserName>" is displayed on global header
    And  I am brought to the page with header "Checkout"
    And  I verify "First Name, Last Name, Address Line 1, Address Line 2, Zip Code, Email, Phone Number" values for "<Customer>" are now pre-populated
    When I place the order for "<Customer>"
    Then I am brought to the order confirmation page

    Examples:
      | ItemCode | ProductName  | Checkout | My Email             | Password   | UserName | Customer             | Reason                              |
      | 34302    | Defender A/S | default  | ima@discounttire.com | Testing123 | IMA      | DEFAULT_CUSTOMER_DTD | Make an appointment at a later time |

  @dt
  @at
  @web
  @15109
  Scenario Outline: HYBRIS_CUSTOMER_MY ACCOUNTS_Checkout Flow - No Sign In link_DT (ALM# 15109)
    When I select Join/Sign-in
    Then I verify my account sign-in modal is displayed
    When I enter "<My Email>" in email address field
    And  I enter "<Password>" in password field
    Then I should see "Sign-In" is enabled
    When I select sign-in
    Then I should see "<UserName>" displayed on homepage header
    When I do a free text search for "<ItemCode>"
    And  I select "<ProductName>" from the autocomplete dropdown of the search box
    And  I add item to my cart and "View shopping cart"
    Then I should see product "<ProductName>" on the cart page
    When I select the checkout option "<Checkout>"
    And  I select the checkout without install reason "<Reason>"
    And  I click next step for customer information
    Then I verify 'sign in to skip this step' is "not displayed" in Checkout Page
    And  I verify "First Name, Last Name, Address Line 1, Address Line 2, Zip Code, Email, Phone Number" values for "<Customer>" are now pre-populated

    Examples:
      | ItemCode | ProductName  | Checkout | My Email             | Password   | UserName | Customer             | Reason                              |
      | 34302    | Defender A/S | default  | ima@discounttire.com | Testing123 | IMA      | DEFAULT_CUSTOMER_DTD | Make an appointment at a later time |