Feature: Checkout Process

  Scenario: Verification of checkout proceeds to payment tab
    When user clicks first proceed button
    And user clicks second proceed button
    Then user should see the payments tab
