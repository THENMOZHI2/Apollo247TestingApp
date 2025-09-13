Feature: Medicine Search Functionality

Scenario: Search with valid  medicine name
When the user enters valid medicine name in the search box
Then the system should display medicine name



Scenario: Search and add medicine to cart with filters
 
When user adds the product to cart and increases quantity by 2
When user clicks on View Cart button after adding products
Then user should see the My Cart page
  
 
Scenario: user add items in my cart

When user clicks add items in my cart
And the user enters valid medicine name in the search box
And user adds the product to cart and increases quantity by 2
Then user clicks on View Cart button after adding products


