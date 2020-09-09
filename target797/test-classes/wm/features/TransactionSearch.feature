@wm
@transactionSearch
Feature: Transaction Search in WM Tests

  Background:
    Given I go to the wm homepage
    When  I login with Username and Password
    
  @wmTransactionSearch
  Scenario Outline: WM_Login (ALM# NONE)
	When I read the transaction number from "ExtendedAssortment" excel
	And  I search for transaction number on interface monitor page
	And  I select the date range "<DateRange>" 
	And  I complete the search
	Then I verify transaction number appears with the status of completed
	
	Examples:
		| DateRange     |
		| This Day      |