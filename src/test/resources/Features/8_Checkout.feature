Feature: Checkout Process

  Scenario: Verification of checkout proceeds to payment tab
    When user clicks third proceed button
    And user clicks fourth proceed button
    Then user should see the payments tab
