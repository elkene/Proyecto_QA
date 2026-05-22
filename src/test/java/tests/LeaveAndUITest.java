package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LeavePage;
import pages.LoginPage;
import utils.BaseTest;

public class LeaveAndUITest extends BaseTest {

    private DashboardPage loginAndGetDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ADMIN_USER, ADMIN_PASS);
        return new DashboardPage(driver);
    }

    @Test(description = "TC-11: Navegar al modulo Leave carga correctamente")
    public void testNavegacionLeave() {
        DashboardPage dashboardPage = loginAndGetDashboard();
        dashboardPage.navigateToLeave();
        LeavePage leavePage = new LeavePage(driver);
        Assert.assertTrue(leavePage.isLeavePageLoaded(),
                "El modulo Leave debe cargar correctamente");
    }

    @Test(description = "TC-12: Seccion Apply Leave despliega el formulario de solicitud")
    public void testSolicitudLeave() {
        DashboardPage dashboardPage = loginAndGetDashboard();
        dashboardPage.navigateToLeave();
        LeavePage leavePage = new LeavePage(driver);
        leavePage.clickApplyLeave();
        Assert.assertTrue(leavePage.isApplyLeaveSectionVisible(),
                "El formulario de solicitud de leave debe ser visible tras hacer clic en Apply");
    }

    @Test(description = "TC-13: Modulo My Info muestra datos personales del usuario")
    public void testModuloMyInfo() {
        DashboardPage dashboardPage = loginAndGetDashboard();
        dashboardPage.navigateToMyInfo();
        LeavePage leavePage = new LeavePage(driver);
        Assert.assertTrue(leavePage.isMyInfoLoaded(),
                "El modulo My Info debe cargar y mostrar datos personales");
    }

    @Test(description = "TC-14: El menu principal muestra las opciones de navegacion esperadas")
    public void testMenuPrincipalVisible() {
        DashboardPage dashboardPage = loginAndGetDashboard();
        Assert.assertTrue(dashboardPage.isSidebarVisible(),
                "El panel lateral debe estar visible");
        Assert.assertTrue(dashboardPage.isPIMMenuVisible(),
                "La opcion PIM debe estar visible en el menu");
        Assert.assertTrue(dashboardPage.isLeaveMenuVisible(),
                "La opcion Leave debe estar visible en el menu");
        Assert.assertTrue(dashboardPage.isMyInfoMenuVisible(),
                "La opcion My Info debe estar visible en el menu");
    }

    @Test(description = "TC-15: Acceso directo a URL interna sin sesion redirige al login")
    public void testAccesoSinSesion() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                "Acceder a una URL protegida sin sesion debe redirigir al login");
    }
}
