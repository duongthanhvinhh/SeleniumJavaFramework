package TestCases;

import Common.BaseSetup;
import Common.listeners.Listener;
import Pages.DashboardPage;
import Pages.LogInPage;
import Pages.PracticePage;
import Utils.PropertiesFile;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Common.extentReport.ExtentTestManager.reporterLog;

@Listeners(Listener.class)
@Epic("LogIn suite")
@Feature("All TCs in Practice Page")
@Owner("VinhTester")
public class PracticeTest extends BaseSetup {
    private LogInPage logInPage;
    private DashboardPage dashboardPage;
    private PracticePage practicePage;

    @BeforeClass
    public void SetupTest() {
        //call function to initial file properties
        PropertiesFile.setPropertiesFile();
        //Read data from file properties with key is "browserType"
        driver = new BaseSetup().setDriver(PropertiesFile.getPropValue("browser"));
        navigatetoURL("https://practicetestautomation.com/practice-test-login/");
        logInPage = new LogInPage(driver);
        dashboardPage = new DashboardPage(driver);
        practicePage = new PracticePage(driver);
    }

    @AfterClass
    void clearTest() {
        closeDriver();
    }


    @Test
    void TC_001_AddAndRemoveRow2() {
        logInPage.inputUsername("student");
        reporterLog("input student to username field");
        logInPage.inputPassword("Password123");
        reporterLog("input Password123 to password field");
        logInPage.clickLogIn();
        reporterLog("click login button");
        logInPage.verifySuccessMessage();
        reporterLog("verify success message displayed after login success");
        dashboardPage.clickPRACTICE();
        reporterLog("click PRACTICE buton");
        practicePage.clickTestExceptions();
        reporterLog("click test exceptions");
        practicePage.clickAddRow();
        reporterLog("click add row");
        practicePage.clickRow2InputField();
        reporterLog("click row2 input field");
        practicePage.inputRow2InputField("VinhTester");
        reporterLog("input VinhTester to row2 input field");
        practicePage.clickSaveRow2();
        reporterLog("click save button");
        practicePage.verifyRow2AddingMessageDisplayed();
        reporterLog("verify row2 added successfully");
    }

    @Test
    void TC_002_EditRow1() throws InterruptedException {
        dashboardPage.clickPRACTICE();
        reporterLog("click PRACTICE");
        practicePage.clickTestExceptions();
        reporterLog("click test exceptions");
        practicePage.clickEditRow();
        reporterLog("click edit button");
        practicePage.clickRow1InputField();
        reporterLog("click Row 1 input field");
        practicePage.inputRow1InputField("This is Row 1");
        reporterLog("input This is Row 1 to Row 1 input field");
        practicePage.clickSaveRow1();
        reporterLog("click save button");
        practicePage.verifyRow1EditedSuccessfully();
        reporterLog("verify Row 1 edited successfully");
    }
}
