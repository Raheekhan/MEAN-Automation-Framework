package test;

import base.BaseUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.home.Login;
import pages.user.product.packages.Bronze;
import pages.user.product.packages.Gold;
import pages.user.product.packages.Platinum;
import pages.user.product.packages.Silver;

import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class PricingTest extends BaseUtil {

    @BeforeMethod
    public void login() {
        Login login = new Login(driver);
        login.withValidCredentials();

    }

    public void isAbleToSelectBronzeOnPricingPage() {
        Bronze bronze = new Bronze(driver);
        bronze.purchaseBronzePlan();
        assertTrue(bronze.isOnBronzePlanPage());
    }

    public void isAbleToSelectGoldOnPricingPage() {
        Gold gold = new Gold(driver);
        gold.purchaseGoldPlan();
        assertTrue(gold.isOnGoldPlanPage());
    }

    public void isAbleToSelectSilverOnPricingPage() {
        Silver silver = new Silver(driver);
        silver.purchaseSilverPlan();
        assertTrue(silver.isOnSilverPlanPage());
    }

    public void isAbleToSelectPlatinumOnPricingPage() {
        Platinum platinum = new Platinum(driver);
        platinum.purchasePlatinumPlan();
        assertTrue(platinum.isOnPlatinumPlanPage());
    }
}
