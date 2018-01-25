package test;

import base.BaseUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.home.Login;
import pages.user.product.packages.purchase.PurchasePlan;

import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class PurchasePlanTest extends BaseUtil {

    @BeforeMethod
    public void login() {
        Login login = new Login(driver);
        login.withValidCredentials();

    }

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
