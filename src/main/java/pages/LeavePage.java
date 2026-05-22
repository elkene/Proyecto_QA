package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LeavePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By leavePageHeader  = By.xpath("//h5[normalize-space()='Leave List']");
    private final By applyLeaveButton = By.xpath("//a[normalize-space()='Apply']");
    private final By leaveTypeSelect  = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private final By fromDateField    = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By toDateField      = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private final By submitButton     = By.xpath("//button[normalize-space()='Apply']");
    private final By myInfoHeader     = By.xpath("//h6[normalize-space()='Personal Details']");

    public LeavePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isLeavePageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(leavePageHeader)).isDisplayed();
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("leave");
        }
    }

    public void clickApplyLeave() {
        wait.until(ExpectedConditions.elementToBeClickable(applyLeaveButton)).click();
    }

    public boolean isApplyLeaveSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(leaveTypeSelect)).isDisplayed();
    }

    public boolean isMyInfoLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(myInfoHeader)).isDisplayed();
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("myInfo");
        }
    }
}
