Feature: Invalid address management

Scenario: User enters invalid text for searching area
  Given user clicks on change
  When user validates that the new sidebar opens
  And user clicks on add button
  Then user types invalid pincode and validates that no results found

Scenario: User unable to save flat number
  Given user clicks on change
  When user clicks on add button
  And user enters the valid pincode "600003"
  And user enter single character flat number
  Then user validates that minimum two characters required

Scenario: User able to save address as part
  Given user clicks on change
  When user clicks on add button
  And user enters the valid pincode "600003"
  And user enter valid character flat number and cick save
  Then user  select any option in save address it shows an information that select any one
  And user enters invalid recipient details and click save address
  Then user validates after clicking the page returned to cart page

Scenario: user provides address for delivering the products using valid credentials

When user clicks on change in my cart 
When user see the deliverto and click add new address
And user selects the address by typing in the textarea
And user validates the enter address details page
And user enters house number and and click save and next
And  choose save the address as
And user enters recipient and click save address
Then user validates after clicking the page returned to cart page