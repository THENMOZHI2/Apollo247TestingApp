Feature: Buy Medicines flow - Negative Scenarios
@NeedsLogin


Scenario: Enter invalid pincode while adding new address
    When user clicks on Buy Medicines tab
    And user searches for medicine "Paracip-650 Tablet 10's"
    And user adds the product to cart and increases quantity by 1
    And user clicks on View Cart button
    Then user should see the My cart page
    And user clicks on change address
    And user clicks on the Add new Address
    Then user should see the Deliver To Tab
    And user enter an invalid pincode "00000000000"
    Then user should see an error message for invalid pincode