package pages.user.product.packages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.user.product.Pricing;

public class Bronze {

    private WebDriver driver;
    private WebDriverWait wait;

    public Bronze(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public Pricing purchaseBronzePlan() {
        return new Pricing(driver).purchaseBronzePlan();
    }

    public boolean isOnPlanPage() {
        return new Pricing(driver).isOnPlanPage();
    }
}
