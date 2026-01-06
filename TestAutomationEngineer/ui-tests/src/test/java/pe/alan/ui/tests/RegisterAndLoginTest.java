package pe.alan.ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pe.alan.ui.core.BaseTest;
import pe.alan.ui.models.TestUser;
import pe.alan.ui.pages.HomePage;
import pe.alan.ui.pages.LoginPage;
import pe.alan.ui.pages.RegisterPage;
import pe.alan.ui.utils.UserFactory;

public class RegisterAndLoginTest extends BaseTest {

    @Test
    public void shouldRegisterThenLoginWithGeneratedUser() {
        TestUser user = UserFactory.newUser();

        HomePage home = new HomePage(driver, wait).open();
        RegisterPage register = home.goToRegister();

        register.register(user);

        String postRegisterTitle = home.rightTitle();
        Assert.assertTrue(postRegisterTitle.toLowerCase().contains("welcome"),
                "Post-registro no dice Welcome. Texto: " + postRegisterTitle);
        Assert.assertTrue(postRegisterTitle.toLowerCase().contains(user.username.toLowerCase()),
                "Post-registro no contiene el username. Texto: " + postRegisterTitle);

        Assert.assertTrue(home.isLoggedIn(), "Post-registro no se detectó Log Out.");

        home.logout();

        LoginPage login = new LoginPage(driver, wait);
        login.openHome();
        login.login(user.username, user.password);

        String postLoginTitle = login.rightTitle();
        Assert.assertTrue(postLoginTitle.toLowerCase().contains("accounts overview"),
                "Post-login no llegó a Accounts Overview. Texto: " + postLoginTitle);

        Assert.assertTrue(new HomePage(driver, wait).isLoggedIn(), "Post-login no se detectó Log Out.");

        String leftWelcome = new HomePage(driver, wait).leftWelcomeText();
        Assert.assertTrue(leftWelcome.toLowerCase().contains(user.fullName().toLowerCase()),
                "El Welcome del panel izquierdo no contiene el nombre completo. Texto: " + leftWelcome);
    }
}
