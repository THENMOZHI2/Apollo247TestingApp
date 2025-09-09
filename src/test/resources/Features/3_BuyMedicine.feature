Feature: Buy Medicines flow
@NeedsLogin
Scenario: Search and add medicine to cart with filters
  When user clicks on Buy Medicines tab
  Then user should see Buy Medicines and Essentials page
  When user searches for medicine "Paracip-650 Tablet 10's"
  And user applies in-stock and brand filters
  Then medicine "Paracip-650 Tablet 10's" should be available 
  And user adds the product to cart and increases quantity by 2
  And user clicks on View Cart button
  