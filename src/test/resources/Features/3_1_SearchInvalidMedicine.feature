Feature: Buy Medicines flow - Negative Scenarios
  @NeedsLogin
  Scenario: Search for unavailable medicine
    When user clicks on Buy Medicines tab
    Then user should see Buy Medicines and Essentials page
    When user searches for medicine "@@@!!!"
    Then medicine should not be available in search results