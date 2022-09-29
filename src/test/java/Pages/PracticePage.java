package Pages;

import Common.BaseSetup;
import Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static Common.extentReport.ExtentTestManager.reporterLog;

public class PracticePage {
    private WebDriverWait wait;
    private WebDriver driver;
    private LogInPage logInPage;
    private DashboardPage dashboardPage;

    public PracticePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logInPage = new LogInPage(driver);
        dashboardPage = new DashboardPage(driver);

    }


    By testLogInPageLink = By.xpath("//a[normalize-space()='Test Login Page']");
    By testExceptionsLink = By.xpath("//a[normalize-space()='Test Exceptions']");
    By exceptionEditbtn = By.xpath("//button[@id='edit_btn']");
    By exceptionAddbtn = By.xpath("//button[@id='add_btn']");
    By exceptionSavebtnRow1 = By.xpath("//button[@id='save_btn']");
    By exceptionSavebtnRow2 = By.xpath("//div[@id='row2']//button[@id='save_btn']");
    By exceptionInputfieldRow1 = By.xpath("//input[@value='Pizza']");
    By exceptionInputfieldRow2 = By.xpath("//div[@id='row2']//input[@type='text']");
    By successAddingMessage = By.xpath("//div[@id='confirmation']");

    By successEditRow1Message = By.xpath("//div[@id='confirmation']");
    By exceptionRemoveRow2btn = By.xpath("//button[@id='remove_btn']");

    public void clickTestExceptions() {
        driver.findElement(testExceptionsLink).click();
    }

    public void clickEditRow() {
        driver.findElement(exceptionEditbtn).click();
    }

    public void clickAddRow() {
        driver.findElement(exceptionAddbtn).click();
    }

    public void clickSaveRow1() {
        driver.findElement(exceptionSavebtnRow1).click();
    }

    public void clickRow1InputField() {
        driver.findElement(exceptionInputfieldRow1).click();
    }

    public void inputRow1InputField(String row1_value) {
        driver.findElement(exceptionInputfieldRow1).sendKeys(row1_value);
    }

    public void inputRow2InputField(String row2_value) {
        driver.findElement(exceptionInputfieldRow2).sendKeys(row2_value);
    }

    public void clickSaveRow2() {
        driver.findElement(exceptionSavebtnRow2).click();
    }

    public void clickRow2InputField() {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(exceptionInputfieldRow2));
        driver.findElement(exceptionInputfieldRow2).click();
    }

    public void clickRemoveRow2() {
        driver.findElement(exceptionRemoveRow2btn).click();
    }


    public void verifyRow2AddingMessageDisplayed() {
        String successAddingMessageTxt = driver.findElement(successAddingMessage).getText();
        Assert.assertTrue(driver.findElement(exceptionInputfieldRow2).isDisplayed());
        Assert.assertEquals(successAddingMessageTxt, "Row 2 was saved");

    }

    public void verifyRow1EditedSuccessfully() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successEditRow1Message));
        Assert.assertTrue(driver.findElement(successEditRow1Message).isDisplayed());
        String compare = driver.findElement(successEditRow1Message).getText();
        Assert.assertEquals(compare, "Row 1 was saved");
    }
}
