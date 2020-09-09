@ovc
@login
Feature: Login tests

  Background:
    Given I go to the ovc homepage

  @ovcBBA
  @12822
  Scenario: Oneview_Sign_On_Invalid_User_Error_Message (ALM#12822)
    When  I login with Server, Username, and Invalid Password
    Then  I should see text "Enter valid username and password." present in the page

  @ovcBBA
  @12825
  Scenario: Oneview_Sign_Off_Successful_Log_Off (ALM#12825)
    When  I login with Server, Username, and Password
    Then  I verify the right nav pane title is "Main"
    When  I select the "LOGOFF" button on the "Home" page
    And   I select the "Yes" button on the "popup" page
    Then  I verify I am back on the login page

  @ovcBBA
  @12826
  Scenario: Oneview_Sign_Off_Successful_Log_Off (ALM#12826)
    When  I login with Server, Username, and Password
    Then  I verify the right nav pane title is "Main"
    When  I select the "LOGOFF" button on the "Home" page
    And   I select the "No" button on the "popup" page
    Then  I verify the right nav pane title is "Main"

  @ovcBBA
  @11602
  Scenario: Oneview_Sign_On_Invalid_User_Error_Message (ALM#11602)
    When  I enter Server, Username and Password and hit Login on the initial login page
    Then  I verify till is not present in the second login page