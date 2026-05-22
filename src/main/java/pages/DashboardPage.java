package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By userDropdown  = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutOption  = By.xpath("//a[normalize-space()='Logout']");
    private final By sidebarMenu   = By.cssSelector(".oxd-sidepanel-body");
    private final By pimMenuItem   = By.xpath("//span[normalize-space()='PIM']");
    private final By leaveMenuItem = By.xpath("//span[normalize-space()='Leave']");
    private final By myInfoMenuItem = By.xpath("//span[normalize-space()='My Info']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDashboardLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarMenu)).isDisplayed();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutOption)).click();
    }

    public void navigateToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenuItem)).click();
    }

    public void navigateToLeave() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveMenuItem)).click();
    }

    public void navigateToMyInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(myInfoMenuItem)).click();
    }

    public boolean isSidebarVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarMenu)).isDisplayed();
    }

    public boolean isPIMMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pimMenuItem)).isDisplayed();
    }

    public boolean isLeaveMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(leaveMenuItem)).isDisplayed();
    }

    public boolean isMyInfoMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(myInfoMenuItem)).isDisplayed();
    }
}
