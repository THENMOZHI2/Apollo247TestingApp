Feature: Invalid address management

Scenario: User enters invalid text for searching area
  Given user clicks on change
  When user validates deliver to tab
  And user clicks on add button
  Then user types invalid pincode and validates that no results found

Scenario: User unable to save flat number

  When  user enters the valid pincode from text data
  And user enters a single character flat number
  Then user validates that minimum two characters are required

Scenario: User is able to save address with invalid recipient name
  
  When user enters valid character flat number and clicks save
  Then user sees an information message that home address type must be selected
  And user enters an invalid recipient name "@@@@@"
  And user clicks on Save Address








