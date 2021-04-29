@ignore @regression
Feature: Energy Australia Login Feature
	
  Scenario: Validate Crisis message
    Given User is on the EA Login page
    Then User sees the crisis message on the screen
    And User validates the crisis message text
    And User validates the crisis message close link text
    When User clicks on the Hide message button
    Then User validate the absence of crisis message on the screen

  Scenario: Validate Page Header
    Given User is on the EA Login page
    Then User sees the page Header
    And User validate the page Header Text
  	