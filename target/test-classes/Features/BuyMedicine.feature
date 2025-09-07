Feature: Buy Medicines functionality

  Scenario: Search and add medicine to cart
    When user clicks on Buy Medicine tab
    Then Buy Medicines page should be displayed
    When user searches for "Paracip-650 Tablet 10's"
    And user applies In-stock filter
    And user applies Dolo brand filter
    Then product results should be displayed
    When user adds the product to cart
    And user increases quantity by 2
    And user clicks on View Cart button
