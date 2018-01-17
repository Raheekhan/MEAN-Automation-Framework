package pages.user.product.packages.purchase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.user.product.Pricing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PurchasePlan {

    private WebDriver driver;
    private WebDriverWait wait;

    Properties prop;

    public PurchasePlan(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    @FindBy(id = "cardnumber")
    WebElement cardNumber;

    @FindBy(id = "cardcvv")
    WebElement cardCVV;

    @FindBy(id = "month")
    WebElement months;

    // 2019 - 2025
    @FindBy(id = "year")
    WebElement years;

    @FindBy(id = "purchaseplan")
    WebElement purchasePlanButton;

    @FindBy(xpath = "//div[@class='alert flash-message alert-danger']//p[text()='Fill in all fields']")
    WebElement fieldsEmptyMessage;

    @FindBy(xpath = "//div[@class='alert flash-message alert-danger']//p[text()='Type in a valid Credit Card Number']")
    WebElement invalidCardNumberMessage;

    @FindBy(xpath = "//div[@class='alert flash-message alert-danger']//p[text()='Type in a valid Card CVV']")
    WebElement invalidCardCVVMessage;

    @FindBy(xpath = "//div[@class='alert flash-message alert-danger']//p[text()='Something went wrong!']")
    WebElement invalidDateMessage;

    @FindBy(xpath = "//div[@class='alert flash-message alert-success']//p[text()='Purchase successful!']")
    WebElement successfulPurchaseMessage;

    private Pricing purchaseBronzePlan() {
        return new Pricing(driver).purchaseBronzePlan();
    }

    private Pricing clickPurchaseButton() {
        return new Pricing(driver).clickPurchaseButton();
    }

    private void loadProperties() {
        String path = System.getProperty("user.dir") + "/src/test/resources/card.properties";
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            prop = new Properties();
            prop.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void withValidCard() {
        loadProperties();
        purchaseBronzePlan();
        clickPurchaseButton();
        wait.until(ExpectedConditions.elementToBeClickable(cardNumber));
        cardNumber.sendKeys(prop.getProperty("validCardNumber"));
        cardCVV.sendKeys(prop.getProperty("validCardCVV"));

        Select month = new Select(months);
        month.selectByValue("08");

        Select year = new Select(years);
        year.selectByValue("2020");
        purchasePlanButton.click();
    }

    public void withInvalidCardNumber() {
        loadProperties();
        purchaseBronzePlan();
        clickPurchaseButton();
        wait.until(ExpectedConditions.elementToBeClickable(cardNumber));
        cardNumber.sendKeys(prop.getProperty("invalidCardNumber"));
        cardCVV.sendKeys(prop.getProperty("validCardCVV"));

        Select month = new Select(months);
        month.selectByValue("08");

        Select year = new Select(years);
        year.selectByValue("2020");
        purchasePlanButton.click();
    }

    public void withInvalidCardCVV() {
        loadProperties();
        purchaseBronzePlan();
        clickPurchaseButton();
        wait.until(ExpectedConditions.elementToBeClickable(cardNumber));
        cardNumber.sendKeys(prop.getProperty("validCardNumber"));
        cardCVV.sendKeys(prop.getProperty("invalidCardCVV"));

        Select month = new Select(months);
        month.selectByValue("08");

        Select year = new Select(years);
        year.selectByValue("2020");
        purchasePlanButton.click();
    }

    public void withInvalidMonth() {
        loadProperties();
        purchaseBronzePlan();
        clickPurchaseButton();
        wait.until(ExpectedConditions.elementToBeClickable(cardNumber));
        cardNumber.sendKeys(prop.getProperty("validCardNumber"));
        cardCVV.sendKeys(prop.getProperty("invalidCardCVV"));

        Select year = new Select(years);
        year.selectByValue("2020");
        purchasePlanButton.click();
    }

    public void withInvalidYear() {
        loadProperties();
        purchaseBronzePlan();
        clickPurchaseButton();
        wait.until(ExpectedConditions.elementToBeClickable(cardNumber));
        cardNumber.sendKeys(prop.getProperty("validCardNumber"));
        cardCVV.sendKeys(prop.getProperty("invalidCardCVV"));

        Select month = new Select(months);
        month.selectByValue("08");
        purchasePlanButton.click();
    }

    public void withEmptyFields() {
        loadProperties();
        purchaseBronzePlan();
        clickPurchaseButton();
        wait.until(ExpectedConditions.elementToBeClickable(cardNumber));
        purchasePlanButton.click();
    }

    public boolean validCardValidation() {
        wait.until(ExpectedConditions.visibilityOf(successfulPurchaseMessage));
        if (successfulPurchaseMessage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean invalidCardNumberValidation() {
        wait.until(ExpectedConditions.visibilityOf(invalidCardNumberMessage));
        if (invalidCardNumberMessage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean invalidCardCVVValidation() {
        wait.until(ExpectedConditions.visibilityOf(invalidCardCVVMessage));
        if (invalidCardCVVMessage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean invalidDateValidation() {
        wait.until(ExpectedConditions.visibilityOf(fieldsEmptyMessage));
        if (fieldsEmptyMessage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean emptyFieldsValidation() {
        wait.until(ExpectedConditions.visibilityOf(fieldsEmptyMessage));
        if (fieldsEmptyMessage.isDisplayed()) {
            return true;
        }
        return false;
    }
}
