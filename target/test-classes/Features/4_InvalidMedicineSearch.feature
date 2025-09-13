Feature: Medicine Search Functionality


Scenario: Search with a very long medicine name
When the user enters numeric value  in the search box
Then the system should display an error message 


Scenario: Search with invalid characters
When the user enters special characters in the search box
Then the system should display an error message 
 

Scenario: Search for a medicine that is out of stock
When user searches for a medicine from test data marked as out of stock
And user applies the "In-stock" filter
Then the system should display an error message 


