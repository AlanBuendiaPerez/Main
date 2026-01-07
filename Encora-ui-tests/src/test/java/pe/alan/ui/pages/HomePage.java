package pe.alan.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pe.alan.ui.config.TestConfig.HOME_URL;

public class HomePage {

    private final WebDriver driver;

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("input[value='Log In']");
    private final By logoutLink = By.linkText("Log Out");

    // mensaje de error típico (cuando login falla)
    private final By loginError = By.cssSelector("#rightPanel p");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage open() {
        driver.get(HOME_URL);
        return this;
    }

    public HomePage typeUser(String user) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        return this;
    }

    public HomePage typePass(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        return this;
    }

    public AccountsOverviewPage clickLogin() {
        driver.findElement(loginBtn).click();
        return new AccountsOverviewPage(driver);
    }

    public boolean isLoggedIn() {
        return !driver.findElements(logoutLink).isEmpty();
    }

    public String getLoginErrorTextOrEmpty() {
        return driver.findElements(loginError).isEmpty() ? "" : driver.findElement(loginError).getText().trim();
    }
}
