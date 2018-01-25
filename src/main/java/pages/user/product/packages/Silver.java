package pages.user.product.packages;

import org.openqa.selenium.WebDriver;
import pages.user.product.Pricing;

public class Silver extends Pricing {

    public Silver(WebDriver driver) {
        super(driver);
    }

//    private WebDriver driver;
//    private WebDriverWait wait;

//    public Silver(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, 5);
//    }

    public Pricing purchaseSilverPlan() {
        return pricingPurchaseSilverPlan();
    }

    public boolean isOnSilverPlanPage() {
        return isOnPlanPage();
    }
}
