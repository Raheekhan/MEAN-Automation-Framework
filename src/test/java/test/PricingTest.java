package test;

import base.BaseUtil;
import org.testng.annotations.Test;
import pages.user.product.packages.Bronze;
import pages.user.product.packages.Gold;
import pages.user.product.packages.Platinum;
import pages.user.product.packages.Silver;

import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class PricingTest extends BaseUtil {

    public void isAbleToSelectBronzeOnPricingPage() {
        Bronze bronze = new Bronze(driver);
        bronze.purchaseBronzePlan();
        assertTrue(bronze.isOnPlanPage());
    }

    public void isAbleToSelectGoldOnPricingPage() {
        Gold gold = new Gold(driver);
        gold.purchaseGoldPlan();
        assertTrue(gold.isOnPlanPage());
    }

    public void isAbleToSelectSilverOnPricingPage() {
        Silver silver = new Silver(driver);
        silver.purchaseSilverPlan();
        assertTrue(silver.isOnPlanPage());
    }

    public void isAbleToSelectPlatinumOnPricingPage() {
        Platinum platinum = new Platinum(driver);
        platinum.purchasePlatinumPlan();
        assertTrue(platinum.isOnPlanPage());
    }
}
