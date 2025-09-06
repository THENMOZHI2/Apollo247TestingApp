Feature: login

Scenario: Successfull of Login
Given user is on the login page
When user enters username and password and click login button
Then user validate the home page


Scenario Outline: Failure of Login
Given user is on the login page
When user enters username as "<Username>" and password as "<Password>" and click login button
Then user validate the home page

Examples:
|Username|Password|
|standard_user|secret_sauce1|