package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {

    private WebDriver driver;
    private WebDriverWait wait;

    public SignUp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    @FindBy(id = "")
    WebElement a;

    @FindBy(id = "")
    WebElement b;

    @FindBy(id = "")
    WebElement c;
}
