package TestCases;

import Common.BaseSetup;
import Pages.ContactPage;
import Pages.DashboardPage;
import Pages.LogInPage;
import Pages.PracticePage;
import Utils.PropertiesFile;
import jdk.jfr.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Common.extentReport.ExtentTestManager.reporterLog;

public class ContactTest extends BaseSetup {
    private LogInPage logInPage;
    private DashboardPage dashboardPage;
    private PracticePage practicePage;
    private ContactPage contactPage;

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
        contactPage = new ContactPage(driver);
    }

    @AfterClass
    void clearTest() {
        closeDriver();
    }

    @Test(description = "Verify leave a question with full information successfully")
    public void TC_001_QuestionWithFullInformation() {
        dashboardPage.clickCONTACT();
        reporterLog("click CONTACT");
        contactPage.inputFirstNameRequired("Vinh");
        reporterLog("input Vinh to firstName field");
        contactPage.inputLastName("Tester");
        reporterLog("input Tester to lastName field");
        contactPage.inputEmailRequired("duongvinh1706@gmail.com");
        reporterLog("input duongvinh1706@gmail.com to email field");
        contactPage.inputCommentRequired("We have a question here, can you please help us to clarify !");
        reporterLog("input We have a question here, can you please help us to clarify to comment box");
        contactPage.clickSubmit();
        reporterLog("click submit");
        contactPage.checkSuccessMessageDisplayed();
        reporterLog("verify success message displayed correctly");
    }

    @Test(description = "Verify can not leave a question without lastName")
    public void TC_002_QuestionWithoutLastNameNegative() {
        dashboardPage.clickCONTACT();
        reporterLog("click CONTACT");
        contactPage.inputFirstNameRequired("Vinh");
        reporterLog("input Vinh to firstName field");
        contactPage.inputLastName("");
        reporterLog("input empty lastName to lastName field");
        contactPage.inputEmailRequired("duongvinh1706@gmail.com");
        reporterLog("input duongvinh1706@gmail.com to email field");
        contactPage.inputCommentRequired("I have a question here, can you please help me to clarify");
        reporterLog("Input I have a question here, can you please help me to clarify to comment box");
        contactPage.clickSubmit();
        reporterLog("click submit");
        contactPage.checkErrorMessageWithoutlastName();
        reporterLog("verify error message displayed correctly, because the field - lastName is required");
    }

    @Test(description = "Verify can not leave a question without firstName")
    public void TC_003_QuestionWithoutfirstNameNegative() {
        dashboardPage.clickCONTACT();
        reporterLog("click CONTACT");
        contactPage.inputCommentRequired("");
        reporterLog("Leave the required field firstName empty");
        contactPage.inputLastName("Tester");
        reporterLog("Input Tester to lastName field");
        contactPage.inputEmailRequired("duongvinh1706@gmail.com");
        reporterLog("input duongvinh1706@gmail.com to email field");
        contactPage.inputCommentRequired("I have a question here, can you please help me to clarify");
        reporterLog("input I have a question here, can you please help me to clarify to comment box");
        contactPage.clickSubmit();
        reporterLog("click submit");
        contactPage.checkErrorMessageWithoutfirstName();
        reporterLog("verify error message displayed correctly, because the field - firstName is required");
    }

    @Test(description = "Verify can not leave a question without Email")
    public void TC_004_QuestionWithoutEmailNegative() {
        dashboardPage.clickCONTACT();
        reporterLog("click CONTACT");
        contactPage.inputFirstNameRequired("Vinh");
        reporterLog("Input Vinh to firstName field");
        contactPage.inputLastName("Tester");
        reporterLog("Input Tester to lastName field");
        contactPage.inputEmailRequired("");
        reporterLog("Leave the emai field empty");
        contactPage.inputCommentRequired("I have a question here, can you please help me to clarify");
        reporterLog("input I have a question here, can you please help me to clarify to comment box");
        contactPage.clickSubmit();
        reporterLog("click submit");
        contactPage.checkErrorMessageWithoutemail();
        reporterLog("verify error message displayed correctly, because the field - Email field is required");
    }

    @Test(description = "Verify can not leave a question without Comment")
    public void TC_005_QuestionWithoutCommentNegative() {
        dashboardPage.clickCONTACT();
        reporterLog("click CONTACT");
        contactPage.inputFirstNameRequired("Vinh");
        reporterLog("Input Vinh to firstName field");
        contactPage.inputLastName("Tester");
        reporterLog("Input Tester to lastName field");
        contactPage.inputEmailRequired("duongvinh1706@gmail.com");
        reporterLog("Input duongvinh1706@gmail.com to email  field");
        contactPage.inputCommentRequired("");
        reporterLog("Leave the comment box empty");
        contactPage.clickSubmit();
        reporterLog("click submit");
        contactPage.checkErrorMessageWithoutComment();
        reporterLog("verify error message displayed correctly, because the field - Comment box is required");
    }
}
