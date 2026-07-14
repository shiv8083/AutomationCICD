@tag
  Feature: Purchase the order from Ecommerce Website
    I want to use this template for my feature file

    Background:
      Given I landed on Ecommerce application
  @Regression
  Scenario Outline: Positive Test Of Submitting The Order
    Given I logged in with username <name> and password <password>
    When I wants to add the product <productName> to cart
    And Checkout <productName> and submit the order
    Then Verify the confirmation message "THANKYOU FOR THE ORDER." is displayed on confirmation page
    Examples:
      | name                             | password  | productName     |
      | shivprasadtelvekar1219@gmail.com | Shiv@8083 | ZARA COAT 3     |
      | stelvekar1996@gmail.com          | Shiv@8082 | ADIDAS ORIGINAL |