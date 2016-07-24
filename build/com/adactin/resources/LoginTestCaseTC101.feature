
Feature:To verify valid login details TC101
@Login
Scenario:Login the application

Given I enter hotel reservation application with "URL"
And I enter "username" as "user_name"
And I enter "password" as "pwd"
And I click on "login"
Then I should be able to login to the application
