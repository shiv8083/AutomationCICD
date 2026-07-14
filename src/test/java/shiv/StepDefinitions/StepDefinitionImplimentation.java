package shiv.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import shiv.PageObjects.*;
import shiv.TestComponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImplimentation extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce application")
    public void I_landed_on_Ecommerce_application() throws IOException {
        landingPage = launchApplication();
    }
    @Given("I logged in with username (.+) and password (.+)$")
    public void I_logged_in_with_username_and_password(String username, String password)
    {
        productCatalogue = landingPage.loginApplication(username,password);
    }
    @When("^I wants to add the product (.+) to cart$")
    public void I_wants_to_add_the_product_to_cart(String productName)
    {
        productCatalogue.addProductToCart(productName);
    }
    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName)
        {
            CartPage cartPage = productCatalogue.goToCartPage();
            Boolean match = cartPage.verifyProductDisplay(productName);
            Assert.assertTrue(match);
            CheckoutPage checkoutPage = cartPage.goToCheckout();
            checkoutPage.selectCountry("india");
            confirmationPage = checkoutPage.submitOrder();
        }
    @Then("Verify the confirmation message {string} is displayed on confirmation page")
    public void Verify_the_confirmation_message_is_displayed_on_confirmation_page(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.quit();
    }
    @Then("Verify the error message {string} is displayed on the screen")
    public void Verify_the_error_message_is_displayed_on_the_screen(String string)
    {
        Assert.assertEquals(landingPage.getErrorMessage(), string);
        driver.quit();
    }



}
