package shiv.Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shiv.PageObjects.*;
import shiv.TestComponents.BaseTest;
import shiv.TestComponents.Retry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    @Test(dataProvider = "getData",groups = {"Purchase"},retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String>input) throws IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
    @Test(dependsOnMethods = {"submitOrder"},dataProvider = "getData",retryAnalyzer = Retry.class)
    public void orderHistoryTest(HashMap<String,String>input)
    {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        OrdersPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrdersDisplay(input.get("productName")));
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//shiv//test//Data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }
//    @DataProvider
//    public Object[][] getData()
//    {
//        HashMap<String,String> map0 = new HashMap<String,String>();
//        map0.put("email","shivprasadtelvekar1219@gmail.com");
//        map0.put("password","Shiv@8083");
//        map0.put("productName","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","stelvekar1996@gmail.com");
//        map1.put("password","Shiv@8082");
//        map1.put("productName","ADIDAS ORIGINAL");
//
//        return new Object[][]{{map0},{map1}};
//    }

//    @DataProvider
//    public Object[][] getData()
//    {
//        return new Object[][]{{"shivprasadtelvekar1219@gmail.com","Shiv@8083","ZARA COAT 3"},{"stelvekar1996@gmail.com","Shiv@8082","ADIDAS ORIGINAL"}};
//    }
}
