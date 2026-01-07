package pe.alan.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsOverviewPage {

    private final WebDriver driver;

    private final By rightPanelH1 = By.cssSelector("#rightPanel h1");
    private final By logoutLink   = By.linkText("Log Out");

    public AccountsOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String title() {
        return driver.findElements(rightPanelH1).isEmpty() ? "" : driver.findElement(rightPanelH1).getText().trim();
    }

    public boolean isLoggedIn() {
        return !driver.findElements(logoutLink).isEmpty();
    }
}
