package shiv.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shiv.AbstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".totalRow button")
    WebElement CheckoutEle;
    @FindBy(xpath = "//div[@class='cartSection']/h3")
    private List<WebElement> productTitles;

    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public Boolean verifyProductDisplay(String productName)
    {
        Boolean match = productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
    public CheckoutPage goToCheckout()
    {
        CheckoutEle.click();
        return new CheckoutPage(driver);
    }

}
