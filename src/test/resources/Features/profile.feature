Feature: Login functionality

  Scenario: Successful Login with valid phone number and OTP
    Then The Apollo homepage should be displayed
    When user clicks on Login icon
    And user enters valid phone number
    And user clicks Continue button
    And user enters the OTP received
    And user clicks Verify OTP button
    Then user should be navigated to the Home Page
