 Feature: Buy Medicines Flow - Negative Scenario: Mandatory recipient details
 Scenario: Leave mandatory fields empty in recipient details
    When user clicks on Buy Medicines tab
    And user searches for medicine "Paracip-650 Tablet 10's"
    And user adds the product to cart
    And user clicks on View Cart button
    Then user should see the My cart page
    And user clicks on change address
    And user clicks on Add new Address
    And user enters valid pincode and selects
    And user leaves recipient name empty
    Then user should see an error message "Recipient name is required"