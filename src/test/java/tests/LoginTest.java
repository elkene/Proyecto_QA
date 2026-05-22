package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test(description = "TC-01: Login exitoso con credenciales validas")
    public void testLoginExitoso() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ADMIN_USER, ADMIN_PASS);
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),
                "La URL debe contener 'dashboard' tras login exitoso");
    }

    @Test(description = "TC-02: Login con contrasena incorrecta muestra error")
    public void testLoginContrasenaIncorrecta() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ADMIN_USER, "contrasenaErronea999");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Invalid credentials",
                "Debe mostrar 'Invalid credentials' con contrasena incorrecta");
    }

    @Test(description = "TC-03: Login con campo usuario vacio muestra validacion")
    public void testLoginUsuarioVacio() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", ADMIN_PASS);
        Assert.assertTrue(loginPage.isUsernameValidationVisible(),
                "Debe mostrar mensaje de validacion cuando el usuario esta vacio");
    }

    @Test(description = "TC-04: Login con ambos campos vacios muestra validaciones")
    public void testLoginAmbosVacios() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isUsernameValidationVisible(),
                "Debe mostrar validaciones cuando ambos campos estan vacios");
    }

    @Test(description = "TC-05: Logout redirige a la pantalla de login")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ADMIN_USER, ADMIN_PASS);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                "Debe redirigir a la pantalla de login tras cerrar sesion");
    }
}
