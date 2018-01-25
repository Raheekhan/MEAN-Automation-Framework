package pages.user.product.packages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.user.product.Pricing;

public class Bronze extends Pricing {

    public Bronze(WebDriver driver) {
        super(driver);
    }

//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public Bronze(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, 5);
//    }

    public Pricing purchaseBronzePlan() {
        return pricingPurchaseBronzePlan();
    }

    public boolean isOnBronzePlanPage() {
        return isOnPlanPage();
    }
}
