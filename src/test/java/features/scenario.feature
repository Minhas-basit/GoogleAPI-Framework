
Feature: Google API Testing
@AddPlace @Regression
  Scenario Outline: verify if place is being successfully added with AddPlaceAPI 
    Given add place payload "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" HTTP request
    Then api call get success with status code 200
    And "status" in response body is "OK"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name   | language | address  |
      | Ali 	 | Egnlish  | Karachi  |
     # | Shabir | Urdu 		| Lahore   |
     
 @DeletePlace @Regression    
 	Scenario: Verify if delete place functionality is working
		Given delete place payload
		When user calls "deletePlaceAPI" with "POST" HTTP request
		Then api call get success with status code 200
		And "status" in response body is "OK"
 
