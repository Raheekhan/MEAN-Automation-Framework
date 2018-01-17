package test;

import base.BaseUtil;
import org.testng.annotations.Test;
import pages.home.Login;

import static org.testng.Assert.assertTrue;

@Test(groups = {"smoke"})
public class LoginTest extends BaseUtil {

    public void loginWithValidCredentials() {
        Login login = new Login(driver);
        login.withValidCredentials();
        assertTrue(login.isUserLoggedIn());
    }

    public void loginWthInvalidUsername() {
        Login login = new Login(driver);
        login.withInvalidUsername();
        assertTrue(login.invalidUsernameValidation());
    }

    public void loginWithInvalidPassword() {
        Login login = new Login(driver);
        login.withInvalidPassword();
        assertTrue(login.invalidPasswordValidation());
    }

    public void loginWithEmptyCredentials() {
        Login login = new Login(driver);
        login.emptyCredentials();
        assertTrue(login.emptyCredentialsValidation());
    }
}
