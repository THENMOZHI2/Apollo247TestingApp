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

Scenario: User unable to save address as part
  Given user clicks on change
  When user clicks on add button
  And user enters the valid pincode "600003"
  And user enter valid character flat number and cick save
  Then user  select any option in save address it shows an information that select any one

# defect
Scenario: User can able to enter invalid credentials to save address
  Given user clicks on change
  When user clicks on add button
  And user enters the valid pincode "600003"
  And user enter valid character flat number and cick save
  And user enter invalid name in the receipent textfield
  Then user can save the address
