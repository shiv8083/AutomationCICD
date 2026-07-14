package shiv.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shiv.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public OrdersPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public Boolean verifyOrdersDisplay(String productName)
    {
        Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }

}
