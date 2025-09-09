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
  And user should see the My cart page
  And user clicks on chnage address
  Then user should see the Deliver To Tab
  And user clicks on the Add new Address
  And user enter the valid pincode and select the pincode
  Then user should see enter address details
  And user enter the house number and clicks save and next
  And user fill the recipient details
  And user clicks the save address
  And user clicks proceed and again clicks proceed 
  Then user should see the Delivery Type Tab
  And user clicks proceed 
  And user should see the payments tab
  