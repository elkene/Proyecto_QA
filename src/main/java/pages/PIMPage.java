package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PIMPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By addButton         = By.xpath("//button[contains(., 'Add')]");
    private final By firstNameField    = By.cssSelector("input[name='firstName']");
    private final By lastNameField     = By.cssSelector("input[name='lastName']");
    private final By saveButton        = By.xpath("//button[contains(., 'Save')]");
    private final By searchNameField   = By.xpath("//input[@placeholder='Employee Name' or @class='oxd-input oxd-input--active']");
    private final By searchButton      = By.xpath("//button[contains(., 'Search')]");
    private final By tableRows         = By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By noRecordsLabel    = By.xpath("//*[contains(., 'No Records')]");
    private final By confirmDeleteBtn  = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By successToast      = By.cssSelector(".oxd-toast-content");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        waitForPageLoad();
    }

    public void fillFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void clickSave() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
        waitForPageLoad();
    }

    public boolean isSuccessToastVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successToast)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void searchEmployeeByName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchNameField)).clear();
        driver.findElement(searchNameField).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        waitForPageLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-table-body")));
    }

    public int getTableRowCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-table-body")));
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    public boolean isNoRecordsLabelVisible() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(., 'No Records')]")));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(., 'No Records')]"))).isDisplayed();
        } catch (Exception e) {
            System.out.println("No Records label not found: " + e.getMessage());
            return false;
        }
    }

    public void clickDeleteButton() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='oxd-icon-button oxd-table-cell-action-space']")));
            List<WebElement> buttons = driver.findElements(By.xpath("//button[@class='oxd-icon-button oxd-table-cell-action-space']"));
            if (!buttons.isEmpty()) {
                int deleteIndex = buttons.size() > 1 ? 1 : 0;
                wait.until(ExpectedConditions.elementToBeClickable(buttons.get(deleteIndex))).click();
                waitForPageLoad();
                wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDeleteBtn));
            }
        } catch (Exception e) {
            System.out.println("Error finding delete button: " + e.getMessage());
        }
    }

    public void confirmDelete() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
            waitForPageLoad();
        } catch (Exception e) {
            System.out.println("Error confirming delete: " + e.getMessage());
        }
    }

    public void editFirstEmployee() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='oxd-icon-button oxd-table-cell-action-space']")));
            List<WebElement> buttons = driver.findElements(By.xpath("//button[@class='oxd-icon-button oxd-table-cell-action-space']"));
            if (!buttons.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0))).click();
                waitForPageLoad();
            }
        } catch (Exception e) {
            System.out.println("Error finding edit button: " + e.getMessage());
        }
    }

    private void waitForPageLoad() {
        wait.until(webDriver -> {
            Object readyState = ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
            return readyState != null && readyState.equals("complete");
        });
    }
}
