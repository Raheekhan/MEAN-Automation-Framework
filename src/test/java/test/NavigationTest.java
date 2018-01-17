package test;

import base.BaseUtil;
import org.testng.annotations.Test;
import pages.Navigation;
import pages.home.Login;

import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class NavigationTest extends BaseUtil {

    public void checkNavbarAsGuest() {
        Navigation navigation = new Navigation(driver);
        navigation.pagesValidationAsGuest();
    }

    public void checkNavbarAsMember() {
        Navigation navigation = new Navigation(driver);
        navigation.loginPage();
        Login login = new Login(driver);
        login.withValidCredentials();
        navigation.pagesValidationAsMember();
    }
}
