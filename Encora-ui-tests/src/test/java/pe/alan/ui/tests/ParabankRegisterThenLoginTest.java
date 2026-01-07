package pe.alan.ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pe.alan.ui.models.TestUser;
import pe.alan.ui.pages.AccountsOverviewPage;
import pe.alan.ui.pages.HomePage;
import pe.alan.ui.pages.RegisterPage;
import pe.alan.ui.utils.UserFactory;

public class ParabankRegisterThenLoginTest extends BaseTest {

    @Test
    public void shouldRegisterAndLoginWithGeneratedUser() {
        TestUser user = UserFactory.newUser();

        // 1) Register
        RegisterPage register = new RegisterPage(driver).open().fill(user);
        AccountsOverviewPage afterRegister = register.submit();

        Assert.assertTrue(afterRegister.isLoggedIn(),
                "Luego de registrarse debería estar logueado (no veo Log Out). Title: " + afterRegister.title());

        // 2) Logout
        driver.get("https://parabank.parasoft.com/parabank/logout.htm");

        // 3) Login con el mismo username/password generado
        HomePage home = new HomePage(driver).open();
        AccountsOverviewPage afterLogin = home
                .typeUser(user.username)
                .typePass(user.password)
                .clickLogin();

        Assert.assertTrue(afterLogin.isLoggedIn(),
                "Login falló con user generado. Title: " + afterLogin.title() + " / error: " + home.getLoginErrorTextOrEmpty());
    }
}
