package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    protected static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    protected static final String ADMIN_USER = "Admin";
    protected static final String ADMIN_PASS = "admin123";

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.get(BASE_URL);

        org.openqa.selenium.support.ui.WebDriverWait wait =
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
            .presenceOfElementLocated(org.openqa.selenium.By.cssSelector("input[name='username']")));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
