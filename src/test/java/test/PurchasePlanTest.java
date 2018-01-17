package test;

import base.BaseUtil;
import org.testng.annotations.Test;
import pages.user.product.packages.purchase.PurchasePlan;

import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class PurchasePlanTest extends BaseUtil {

    /**
     * These Test Cases are focused on the actual Card Purchase process.
     * The vaidation for actually logging in as a valid user and selecting
     * a plan is left for other test cases.
     *
     * Behind the scenes in PurchasePlan Class, I am calling in methods
     * from Pricing Class which also calls on Login Class.
     */

    public void purchasePlanWithValidCard() {
        PurchasePlan purchase = new PurchasePlan(driver);
        purchase.withValidCard();
        assertTrue(purchase.validCardValidation());
    }

    public void purchasePlanWithInvalidCard() {
        PurchasePlan purchase = new PurchasePlan(driver);
        purchase.withInvalidCardNumber();
        assertTrue(purchase.invalidCardNumberValidation());
    }

    public void purchasePlanWithInvalidCardCVV() {
        PurchasePlan purchase = new PurchasePlan(driver);
        purchase.withInvalidCardCVV();
        assertTrue(purchase.invalidCardCVVValidation());
    }

    public void purchaseWithInvalidCardMonth() {
        PurchasePlan purchase = new PurchasePlan(driver);
        purchase.withInvalidMonth();
        assertTrue(purchase.invalidDateValidation());
    }

    public void purchaseWithInvalidCardYear() {
        PurchasePlan purchase = new PurchasePlan(driver);
        purchase.withInvalidYear();
        assertTrue(purchase.invalidDateValidation());
    }
}
