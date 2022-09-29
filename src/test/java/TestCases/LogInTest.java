package TestCases;

import Common.BaseSetup;
import Common.listeners.Listener;
import Pages.DashboardPage;
import Pages.LogInPage;
import Utils.PropertiesFile;
import Utils.clearOldDatas;
import io.qameta.allure.*;
import org.testng.annotations.*;

import static Common.extentReport.ExtentTestManager.reporterLog;

@Listeners(Listener.class)
@Epic("LogIn suite")
@Feature("All TCs in LogIn Page")
@Owner("VinhTester")
public class LogInTest extends BaseSetup {
    private LogInPage logInPage;
    private DashboardPage dashboardPage;

    @BeforeSuite
    @Description("Clearing all old datas before starting the test suite")
    void clearAllOldOutput() {
        // ! Clear all old datas (logs, reports, screenshots,...)
        clearOldDatas.deleteOldlogs();
        reporterLog("Clear all old log4j logs");
        clearOldDatas.deleteOldtestRecordings();
        reporterLog("Clear all old records");
        clearOldDatas.deleteOldExportData();
        reporterLog("Clear all old snapshots");
        clearOldDatas.deleteOldExtentReports();
        reporterLog("Clear all old extent reports");
        clearOldDatas.deleteOldAllureResults();
        reporterLog("Clear all old allure reports");
    }

    @BeforeClass
    @Description("Initial browser and all related classes.")
    public void setupTest() throws Exception {
        //call function to initial file properties
        PropertiesFile.setPropertiesFile();
        //Read data from file properties with key is "browserType"
        driver = new BaseSetup().setDriver(PropertiesFile.getPropValue("browser"));
        navigatetoURL("https://practicetestautomation.com/practice-test-login/");
        logInPage = new LogInPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @AfterClass
    void clearTest() {
        closeDriver();
    }

    @Test
    public void TC_001_LogInSuccess() throws Exception {
        logInPage.inputUsername("student");
        reporterLog("input username student to username field");
        logInPage.inputPassword("Password123");
        reporterLog("input password Password123 to password field");
        logInPage.clickLogIn();
        reporterLog("click login button");
        logInPage.verifySuccessMessage();
        reporterLog("verify success login message is displayed correctly");
        dashboardPage.clickLogoutBtn();
        reporterLog("click logout button");
    }

    @Test
    public void TC_002_LogInWrongUsername() {
        logInPage.inputUsername("wrongusername");
        reporterLog("input username wrongusername to username field");
        logInPage.inputPassword("Password123");
        reporterLog("input password Password123 to password field");
        logInPage.clickLogIn();
        reporterLog("click login button");
        logInPage.verifyErrorUserMessage();
        reporterLog("verify error login message is displayed correctly");
    }

    @Test
    public void TC_003_LogInWrongPassword() {
        logInPage.inputUsername("student");
        reporterLog("input username student to username field");
        logInPage.inputPassword("wrongpassword");
        reporterLog("input password wrongpassword to password field");
        logInPage.clickLogIn();
        reporterLog("click login button");
        logInPage.verifyErrorPassMessage();
        reporterLog("verify error login message is displayed correctly");
    }

    @Test
    public void TC_004_LogInEmptyUsername() {
        logInPage.inputUsername("");
        reporterLog("input empty username - do not input user in username field");
        logInPage.inputPassword("Password123");
        reporterLog("input password Password123 to password field");
        logInPage.clickLogIn();
        reporterLog("click login button");
        logInPage.verifyErrorUserMessage();
        reporterLog("verify error login message is displayed correctly");
    }

    @Test
    public void TC_005_LogInEmptyPassword() {
        logInPage.inputUsername("student");
        reporterLog("input username student to username field");
        logInPage.inputPassword("");
        reporterLog("input empty password - do not input pass in password field");
        logInPage.clickLogIn();
        reporterLog("click login button");
        logInPage.verifyErrorPassMessage();
        reporterLog("verify error login message is displayed correctly");
    }
}
