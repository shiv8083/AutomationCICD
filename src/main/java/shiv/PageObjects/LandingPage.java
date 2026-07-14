package shiv.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shiv.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")
    WebElement EmailID;
    @FindBy(id = "userPassword")
    WebElement Password;
    @FindBy(id = "login")
    WebElement LoginButton;
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password)
    {
        EmailID.sendKeys(email);
        Password.sendKeys(password);
        LoginButton.click();
        return new ProductCatalogue(driver);
    }
    public String getErrorMessage()
    {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }
    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
