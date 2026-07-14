package shiv.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import shiv.PageObjects.CartPage;
import shiv.PageObjects.ProductCatalogue;
import shiv.TestComponents.BaseTest;
import shiv.TestComponents.Retry;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {
    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException {
        landingPage.loginApplication("shivprasadtelvekar1219@gmail.co","Shiv@8082");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }
    @Test(retryAnalyzer = Retry.class)
    public void productErrorValidation() throws IOException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("shivprasadtelvekar1219@gmail.com","Shiv@8083");
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}