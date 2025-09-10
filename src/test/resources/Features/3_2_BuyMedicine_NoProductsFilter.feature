Feature: Buy Medicines Flow - Negative Scenario: Filters result in no products

  
  Scenario: Apply filters that result in no products
    When user clicks on Buy Medicines tab
    And user searches for medicine "Paracip-650 Tablet 10's"
    And user applies Inappropriate filter
    Then user should see "No products found" message
