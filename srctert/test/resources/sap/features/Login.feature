@sap
@login
Feature: Login Tests

  Background:
    Given I go to the sap homepage
    When  I login with System, Client, Username, and Password
    
  @sapLogin
  Scenario Outline: SAP_Login (ALM# NONE)
	When I enter t-code "<TCode>" in the command field
	Then I am brought to the page with the title "Create Purchase Order"
	
	Examples:
      | TCode     |
      | me21n     |

  @navTesting
  Scenario: nav_Testing (ALM# NONE)
    When I click on the "Log Off" icon from the SAP navigation bar
