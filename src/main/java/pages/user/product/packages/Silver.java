package pages.user.product.packages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.user.product.Pricing;

public class Silver {

    private WebDriver driver;
    private WebDriverWait wait;

    public Silver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public Pricing purchaseSilverPlan() {
        return new Pricing(driver).purchaseSilverPlan();
    }

    public boolean isOnPlanPage() {
        return new Pricing(driver).isOnPlanPage();
    }
}
