package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Navigation {

    String url = "http://localhost:3000";

    public WebDriver driver;
    public WebDriverWait wait;

    public Navigation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(id = "home")
    WebElement home;

    @FindBy(id = "contact")
    WebElement contact;

    @FindBy(id = "pricing")
    WebElement pricing;

    @FindBy(id = "cart")
    WebElement cart;

    @FindBy(id = "dashboard")
    WebElement dashboard;

    @FindBy(id = "profile")
    WebElement profile;

    @FindBy(id = "logout")
    WebElement logout;

    @FindBy(id = "login")
    WebElement login;

    @FindBy(id = "register")
    WebElement register;

    public void pagesValidationAsGuest() {
        int count = 0;
        String[] expected = {"Home", "Contact", "Login", "Register"};
        List<WebElement> pages = driver.findElements(By.xpath("//nav[@class='navbar navbar-default navbar-fixed-top']//li"));

        for (WebElement page : pages) {
            for (int i = 0; i < expected.length; i++) {
                if (page.getText().equals(expected[i])) {
                    count++;
                }
            }
        }
        if (count == expected.length) {
            System.out.println("Matched count " + pages.size());
        } else {
            System.out.println("Not matched");
        }
    }

    public void pagesValidationAsMember() {
        int count = 0;
        String[] expected = {"Home", "Contact", "Pricing", "Cart", "Dashboard", "Profile", "Logout"};
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> pages = driver.findElements(By.xpath("//nav[@class='navbar navbar-default navbar-fixed-top']//li"));

        for (WebElement page : pages) {
            for (int i = 0; i < expected.length; i++) {
                if (page.getText().equals(expected[i])) {
                    count++;
                }
            }
        }
        if (count == expected.length) {
            System.out.println("Matched count " + pages.size());
        } else {
            System.out.println("Not matched");
        }
    }

    public Navigation homePage() {
        wait.until(ExpectedConditions.elementToBeClickable(home));
        home.click();
        return this;
    }

    public Navigation contactPage() {
        wait.until(ExpectedConditions.elementToBeClickable(contact));
        contact.click();
        return this;
    }

    public Navigation pricingPage() {
        wait.until(ExpectedConditions.elementToBeClickable(pricing));
        pricing.click();
        return this;
    }

    public Navigation cartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(cart));
        cart.click();
        return this;
    }

    public Navigation dashboardPage() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboard));
        dashboard.click();
        return this;
    }

    public Navigation profilePage() {
        wait.until(ExpectedConditions.elementToBeClickable(profile));
        profile.click();
        return this;
    }

    public Navigation logoutPage() {
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        logout.click();
        return this;
    }

    public Navigation loginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(login));
        login.click();
        return this;
    }

    public Navigation registerPage() {
        wait.until(ExpectedConditions.elementToBeClickable(register));
        register.click();
        return this;
    }

    public boolean isOnHomePage() {
        if (driver.getCurrentUrl().equals(url)) {
            return true;
        }
        return false;
    }

    public boolean isOnContactPage() {
        if (driver.getCurrentUrl().endsWith("contact")) {
            return true;
        }
        return false;
    }

    public boolean isOnPricingPage() {
        if (driver.getCurrentUrl().endsWith("pricing")) {
            return true;
        }
        return false;
    }

    public boolean inOnCartPage() {
        if (driver.getCurrentUrl().endsWith("cart")) {
            return true;
        }
        return false;
    }

    public boolean isOnDashboardPage() {
        if (driver.getCurrentUrl().endsWith("dashboard")) {
            return true;
        }
        return false;
    }

    public boolean isOnProfilePage() {
        if (driver.getCurrentUrl().endsWith("profile")) {
            return true;
        }
        return false;
    }

    public boolean isOnLoginPage() {
        if (driver.getCurrentUrl().endsWith("login")) {
            return true;
        }
        return false;
    }

    public boolean isOnRegisterPage() {
        if (driver.getCurrentUrl().endsWith("register")) {
            return true;
        }
        return false;
    }
}
