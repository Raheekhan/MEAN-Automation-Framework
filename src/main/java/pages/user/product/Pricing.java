package pages.user.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Navigation;
import pages.home.Login;

public class Pricing extends Navigation {

    public Pricing(WebDriver driver) {
        super(driver);
    }

//    private WebDriver driver;
//    public WebDriverWait wait;
//
//    public Pricing(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, 5);
//    }

    @FindBy(id = "platinum")
    WebElement buyPlatinum;

    @FindBy(id = "gold")
    WebElement buyGold;

    @FindBy(id = "silver")
    WebElement buySilver;

    @FindBy(id = "bronze")
    WebElement buyBronze;

    @FindBy(id = "goback")
    WebElement goBackButton;

    // For the Purchase Button prior to Card payment page
    @FindBy(xpath = "//table[@id='cart']//a[@id='purchase']")
    WebElement pricingPagePurchaseButton;

    // For the Purchase Button in the Card Payment page
    @FindBy(id = "purchaseplan")
    WebElement purchasePlanButton;

    public Pricing goBack() {
        wait.until(ExpectedConditions.elementToBeClickable(goBackButton));
        goBackButton.click();
        return this;
    }

    public Pricing purchase() {
        wait.until(ExpectedConditions.elementToBeClickable(pricingPagePurchaseButton));
        pricingPagePurchaseButton.click();
        return this;
    }

    private Navigation navigateTo() {
        return pricingPage();
    }

    private Login loginAsValidUser() {
        return new Login(driver).withValidCredentials();
    }

    public Pricing pricingPurchasePlatinumPlan() {
//        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buyPlatinum));
        buyPlatinum.click();
        return this;
    }

    public Pricing pricingPurchaseGoldPlan() {
//        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buyGold));
        buyGold.click();
        return this;
    }

    public Pricing pricingPurchaseSilverPlan() {
//        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buySilver));
        buySilver.click();
        return this;
    }

    public Pricing pricingPurchaseBronzePlan() {
//        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buyBronze));
        buyBronze.click();
        return this;
    }

    public Pricing clickPurchaseButtonOnPricingPage() {
        wait.until(ExpectedConditions.elementToBeClickable(pricingPagePurchaseButton));
        pricingPagePurchaseButton.click();
        return this;
    }

    public boolean isOnPlanPage() {
        wait.until(ExpectedConditions.visibilityOf(pricingPagePurchaseButton));
        if (pricingPagePurchaseButton.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isOnCardPage() {
        wait.until(ExpectedConditions.visibilityOf(purchasePlanButton));
        if (purchasePlanButton.isDisplayed()) {
            return true;
        }
        return false;
    }
}
