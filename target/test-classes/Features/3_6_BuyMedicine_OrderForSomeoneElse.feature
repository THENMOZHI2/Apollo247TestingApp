Feature: Buy Medicines Flow - Order for Someone Else

 
  Scenario: Search, add to cart, and place order for someone else
    When user clicks on Buy Medicines tab
    Then user should see Buy Medicines and Essentials page
    When user searches for medicine "Paracip-650 Tablet 10's"
    And user applies in-stock and brand filters
    Then medicine "Paracip-650 Tablet 10's" should be available
    And user adds the product to cart and increases quantity by 1
    And user clicks on View Cart button
    Then user should see the My cart page
    And user clicks on change address
    And user clicks on Add new Address
    Then user should see the Deliver To Tab
    And user enters valid pincode and selects
    And user enters house number and clicks save and next
    And user selects "Someone Else" as recipient
    And user fills recipient name and details
    And user clicks save and confirm
    And user clicks first proceed button
    And user clicks second proceed button
    And user clicks skip savings
    Then user should see the upload prescription tab
    And user clicks Add patient button
    And user fills the patient form details
    And user clicks save and confirm
    And user clicks proceed (first) and proceed (second)
    Then user should see the payments tab
