package pe.alan.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pe.alan.ui.config.TestConfig;
import pe.alan.ui.models.TestUser;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By registerLink = By.cssSelector("a[href*='register.htm']");

    private final By firstName = By.id("customer.firstName");
    private final By lastName  = By.id("customer.lastName");
    private final By address   = By.id("customer.address.street");
    private final By city      = By.id("customer.address.city");
    private final By state     = By.id("customer.address.state");
    private final By zipCode   = By.id("customer.address.zipCode");
    private final By phone     = By.id("customer.phoneNumber");
    private final By ssn       = By.id("customer.ssn");

    private final By username  = By.id("customer.username");
    private final By password  = By.id("customer.password");
    private final By confirm   = By.id("repeatedPassword");

    private final By submitBtn = By.cssSelector("input[value='Register']");

    private final By rightPanelTitle = By.cssSelector("#rightPanel h1.title");
    private final By accountCreatedMsg = By.xpath("//*[contains(text(),'Your account was created successfully')]");

    public void open() {
        driver.get(TestConfig.baseUrl() + "/index.htm");
        driver.findElement(registerLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public void register(TestUser u) {
        driver.findElement(firstName).sendKeys(u.firstName);
        driver.findElement(lastName).sendKeys(u.lastName);
        driver.findElement(address).sendKeys(u.address);
        driver.findElement(city).sendKeys(u.city);
        driver.findElement(state).sendKeys(u.state);
        driver.findElement(zipCode).sendKeys(u.zipCode);
        driver.findElement(phone).sendKeys(u.phone);
        driver.findElement(ssn).sendKeys(u.ssn);

        driver.findElement(username).sendKeys(u.username);
        driver.findElement(password).sendKeys(u.password);
        driver.findElement(confirm).sendKeys(u.password);

        driver.findElement(submitBtn).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(rightPanelTitle, "Welcome"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedMsg));
    }
}
