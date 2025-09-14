Feature: Addition of new Profile

Scenario: Verification of entering invalid inputs prevents profile addition
When user clicks first proceed button
And user clicks Second proceed button
And user clicks skip savings
Then user should see the upload prescription tab
And user clicks Add patient button
When the user enters the invalid first name as "<invalidfirstname>"
When the user enters the invalid last name as "<invalidLastname>"
When the user enters the invalid dob as "<invaliddob>"
When the user enters the invalid email 

 

Scenario: Verification of successful profile addition with all valid inputs

When the user enters the first name as "<firstname>"
When the user enters the last name as "<lastname>"
When the user enters the dob as "<dob>"
When the user choose the gender 
When the user choose the relation
When the user clicks on save and confirm button

