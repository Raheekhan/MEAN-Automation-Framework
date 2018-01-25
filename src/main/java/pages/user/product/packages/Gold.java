package pages.user.product.packages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.user.product.Pricing;

public class Gold extends Pricing {

    public Gold(WebDriver driver) {
        super(driver);
    }

//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public Gold(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, 5);
//    }

    public Pricing purchaseGoldPlan() {
        return pricingPurchaseGoldPlan();
    }

    public boolean isOnGoldPlanPage() {
        return isOnPlanPage();
    }
}
