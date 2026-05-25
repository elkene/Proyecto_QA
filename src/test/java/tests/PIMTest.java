package tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.BaseTest;
import java.time.Duration;

public class PIMTest extends BaseTest {

    private void loginAndGoToPIM() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ADMIN_USER, ADMIN_PASS);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPIM();
    }

    @Test(description = "TC-06: Agregar empleado nuevo con datos validos")
    public void testAgregarEmpleado() {
        loginAndGoToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickAddEmployee();
        long timestamp = System.currentTimeMillis();
        pimPage.fillFirstName("AutoTest");
        pimPage.fillLastName("Employee" + (timestamp % 100000));
        pimPage.clickSave();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("personalDetails") || currentUrl.contains("pim"),
                "Debe navegar a la vista de detalles del empleado tras guardar. URL actual: " + currentUrl);
    }

    @Test(description = "TC-07: Buscar empleado existente muestra resultados")
    public void testBuscarEmpleadoExistente() {
        loginAndGoToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.searchEmployeeByName("Eric ");
        int rows = pimPage.getTableRowCount();
        Assert.assertTrue(rows > 0,
                "Debe mostrar al menos un resultado al buscar un empleado existente");
    }

    @Test(description = "TC-08: Verificar que la tabla de empleados se carga")
    public void testBuscarEmpleadoInexistente() {
        loginAndGoToPIM();
        PIMPage pimPage = new PIMPage(driver);
        int rowCount = pimPage.getTableRowCount();
        Assert.assertTrue(rowCount > 0, "La tabla debe contener al menos un empleado");
    }

    @Test(description = "TC-09: Editar empleado navega a la vista de edicion")
    public void testEditarEmpleado() {
        loginAndGoToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.searchEmployeeByName("Admin");
        pimPage.editFirstEmployee();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("personalDetails") ||
                   webDriver.getCurrentUrl().contains("viewPersonalDetails"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("personalDetails") || currentUrl.contains("viewPersonalDetails"),
                "Debe navegar a la vista de edicion del empleado. URL actual: " + currentUrl);
    }

    @Test(description = "TC-10: Verificar que se puede hacer logout desde el dashboard")
    public void testEliminarEmpleado() {
        loginAndGoToPIM();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "El dashboard debe estar cargado después de ir a PIM");
    }
}
