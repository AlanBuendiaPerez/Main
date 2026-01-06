package pe.alan.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pe.alan.ui.config.TestConfig;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By registerLink = By.cssSelector("a[href*='register.htm']");

    // Right panel title: Welcome username (post-register) / Accounts Overview (post-login)
    private final By rightPanelTitle = By.cssSelector("#rightPanel h1.title");

    // Left panel welcome: Welcome + Full Name
    private final By leftWelcome = By.cssSelector("#leftPanel p.smallText");

    // Session marker
    private final By logoutLink = By.cssSelector("#leftPanel a[href*='logout.htm']");

    public HomePage open() {
        driver.get(TestConfig.baseUrl() + "/index.htm");
        return this;
    }

    public RegisterPage goToRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new RegisterPage(driver, wait);
    }

    public String rightTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(rightPanelTitle))
                .getText().trim();
    }

    public String leftWelcomeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(leftWelcome))
                .getText().trim();
    }

    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
