@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Positive Test Of Submitting The Order
    Given I landed on Ecommerce application
    When I logged in with username <name> and password <password>
    Then Verify the error message "Incorrect email or password." is displayed on the screen
    Examples:
      | name                             | password  |
      | shivprasadtelvekar1219@gmail.com | Shiv@8080 |
      | stelvekar1996@gmail.com          | Shiv@8080 |