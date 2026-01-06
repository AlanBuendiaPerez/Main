package pe.alan.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pe.alan.ui.config.TestConfig;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("input[value='Log In']");
    private final By rightPanelTitle = By.cssSelector("#rightPanel h1.title");

    public void openHome() {
        driver.get(TestConfig.baseUrl() + "/index.htm");
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    }

    public void login(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);

        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);

        driver.findElement(loginBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rightPanelTitle));
    }

    public String rightTitle() {
        return driver.findElement(rightPanelTitle).getText().trim();
    }
}
