package pe.alan.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pe.alan.ui.models.TestUser;

import static pe.alan.ui.config.TestConfig.REGISTER_URL;

public class RegisterPage {

    private final WebDriver driver;

    // form fields
    private final By firstName   = By.id("customer.firstName");
    private final By lastName    = By.id("customer.lastName");
    private final By address     = By.id("customer.address.street");
    private final By city        = By.id("customer.address.city");
    private final By state       = By.id("customer.address.state");
    private final By zipCode     = By.id("customer.address.zipCode");
    private final By phone       = By.id("customer.phoneNumber");
    private final By ssn         = By.id("customer.ssn");

    private final By regUsername = By.id("customer.username");
    private final By regPassword = By.id("customer.password");
    private final By confirmPass = By.id("repeatedPassword");

    private final By registerBtn = By.cssSelector("input[value='Register']");

    // post-register
    private final By logoutLink   = By.linkText("Log Out");
    private final By rightPanelH1 = By.cssSelector("#rightPanel h1");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage open() {
        driver.get(REGISTER_URL);
        return this;
    }

    public RegisterPage fill(TestUser u) {
        type(firstName, u.firstName);
        type(lastName, u.lastName);
        type(address, u.address);
        type(city, u.city);
        type(state, u.state);
        type(zipCode, u.zipCode);
        type(phone, u.phone);
        type(ssn, u.ssn);

        type(regUsername, u.username);
        type(regPassword, u.password);
        type(confirmPass, u.password);
        return this;
    }

    public AccountsOverviewPage submit() {
        driver.findElement(registerBtn).click();
        return new AccountsOverviewPage(driver);
    }

    public boolean isLoggedInAfterRegister() {
        return !driver.findElements(logoutLink).isEmpty();
    }

    public String title() {
        return driver.findElements(rightPanelH1).isEmpty()
                ? ""
                : driver.findElement(rightPanelH1).getText().trim();
    }

    private void type(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
}
