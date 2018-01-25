package test;

import base.BaseUtil;
import org.testng.annotations.Test;
import pages.home.Login;

import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class LoginTest extends BaseUtil {

    Login login;

    public void loginWithValidCredentials() {
        login = new Login(driver);
        login.withValidCredentials();
        assertTrue(login.isUserLoggedIn());
    }

    public void loginWthInvalidUsername() {
        login = new Login(driver);
        login.withInvalidUsername();
        assertTrue(login.invalidUsernameValidation());
    }

    public void loginWithInvalidPassword() {
        login = new Login(driver);
        login.withInvalidPassword();
        assertTrue(login.invalidPasswordValidation());
    }

    public void loginWithEmptyCredentials() {
        login = new Login(driver);
        login.emptyCredentials();
        assertTrue(login.emptyCredentialsValidation());
    }
}
