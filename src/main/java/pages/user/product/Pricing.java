package pages.user.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Navigation;
import pages.home.Login;

public class Pricing {

    private WebDriver driver;
    private WebDriverWait wait;

    public Pricing(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

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

    @FindBy(id = "purchase")
    WebElement purchaseButton;

    @FindBy(id = "purchaseplan")
    WebElement purchasePlanButton;

    public Pricing goBack() {
        wait.until(ExpectedConditions.elementToBeClickable(goBackButton));
        goBackButton.click();
        return this;
    }

    public Pricing purchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        purchaseButton.click();
        return this;
    }

    private Navigation navigateTo() {
        return new Navigation(driver).pricingPage();
    }

    private Login loginAsValidUser() {
        return new Login(driver).withValidCredentials();
    }

    public Pricing purchasePlatinumPlan() {
        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buyPlatinum));
        buyPlatinum.click();
        return this;
    }

    public Pricing purchaseGoldPlan() {
        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buyGold));
        buyGold.click();
        return this;
    }

    public Pricing purchaseSilverPlan() {
        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buySilver));
        buySilver.click();
        return this;
    }

    public Pricing purchaseBronzePlan() {
        loginAsValidUser();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(buyBronze));
        buyBronze.click();
        return this;
    }

    public Pricing clickPurchaseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        purchaseButton.click();
        return this;
    }

    public boolean isOnPlanPage() {
        wait.until(ExpectedConditions.visibilityOf(purchaseButton));
        if (purchaseButton.isDisplayed()) {
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
