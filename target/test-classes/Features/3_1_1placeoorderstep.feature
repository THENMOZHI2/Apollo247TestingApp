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
  And user clicks on change address
  And user clicks on the Add new Address
  Then user should see the Deliver To Tab
  And user enter the valid pincode and select the pincode
  And user enter the house number and clicks save and next
  And user fill the recipient details and save address
  And user clicks first proceed button
  And user clicks Second proceed button
  And user clicks skip savings
  Then user should see the upload prescription tab
  And user clicks Add patient button
  And user fill the patient form details
  And user clicks save button  and confirm button
  And user clicks  proceed(first one) and proceed(second one)
  Then user should see the payments tab 
  
 
  
  
  