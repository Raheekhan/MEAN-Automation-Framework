package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Navigation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Login {

    private WebDriver driver;
    private WebDriverWait wait;

    Properties prop = null;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//div[@class='alert flash-message alert-success']//p[text()='You are now logged in']")
    WebElement successLoginMessage;

    @FindBy(xpath = "//div[@class='alert flash-message alert-danger']//p[text()='User not found']")
    WebElement invalidUsernameMessage;

    @FindBy(xpath = "//div[@class='alert flash-message alert-danger']//p[text()='Wrong password']")
    WebElement invalidPasswordMessage;

    private Navigation navigateTo() {
        return new Navigation(driver).loginPage();
    }

    public void loadProperties() {
        String path = System.getProperty("user.dir") + "/src/test/resources/credentials.properties";
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

    public Login withValidCredentials() {
        loadProperties();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameInput.sendKeys(prop.getProperty("validUsername"));
        passwordInput.sendKeys(prop.getProperty("validPassword"));
        loginBtn.click();
        return this;
    }

    public void withInvalidUsername() {
        loadProperties();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameInput.sendKeys(prop.getProperty("invalidUsername"));
        passwordInput.sendKeys(prop.getProperty("validPassword"));
        loginBtn.click();
    }

    public void withInvalidPassword() {
        loadProperties();
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameInput.sendKeys(prop.getProperty("validUsername"));
        passwordInput.sendKeys(prop.getProperty("invalidPassword"));
        loginBtn.click();
    }

    public void emptyCredentials() {
        navigateTo();
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        loginBtn.click();
    }

    public boolean isUserLoggedIn() {
        wait.until(ExpectedConditions.visibilityOf(successLoginMessage));
        if (successLoginMessage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean invalidUsernameValidation() {
        wait.until(ExpectedConditions.visibilityOf(invalidUsernameMessage));
        if (invalidUsernameMessage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean invalidPasswordValidation() {
        wait.until(ExpectedConditions.visibilityOf(invalidPasswordMessage));
        if (invalidPasswordMessage.isDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean emptyCredentialsValidation() {
        wait.until(ExpectedConditions.visibilityOf(invalidUsernameMessage));
        if (invalidUsernameMessage.isDisplayed()) {
            return true;
        }
        return false;
    }
}
