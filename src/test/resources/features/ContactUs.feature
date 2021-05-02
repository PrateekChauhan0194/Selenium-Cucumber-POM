@regression
Feature: Contact Us Feature

  Scenario Outline: Validate Contact Us form
    Given User is on the contact us page
    When User enters the data for "<username>" from "<sheetName>" sheet
    And User clicks on the send button
    Then A success message is presented to the user

    Examples: 
      | sheetName | username |
      | contactUs | Prateek  |
      | contactUs | Jna      |
      | contactUs | Vishal   |
