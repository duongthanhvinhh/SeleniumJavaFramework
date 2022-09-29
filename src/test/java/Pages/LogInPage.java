package Pages;

import Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class LogInPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashboardPage;
    public LogInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        dashboardPage = new DashboardPage(driver);
    }
    private final By usernameTxt = By.id("username");
    private final By passwordTxt = By.id("password");
    private final By submitBtn = By.id("submit");
    private final By logInSuccessMessage = By.xpath("//h1[normalize-space()='Logged In Successfully']");
    private final By logInErrorMessage = By.id("error");

    private final String logInSuccessURL = "https://practicetestautomation.com/logged-in-successfully/";
    private final String logInSuccessMessageTxt = "Logged In Successfully";
    private final String logInErrorMessageUsernameTxt = "Your username is invalid!";
    private final String logInErrorMessagePasswordTxt = "Your password is invalid!";

    public void inputUsername(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameTxt));
        driver.findElement(usernameTxt).sendKeys(username);
    }

    public void inputPassword(String password){
        driver.findElement(passwordTxt).sendKeys(password);
    }

    public void clickLogIn(){
        driver.findElement(submitBtn).click();
    }

    public void verifySuccessMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInSuccessMessage));
        boolean successMessageElement = driver.findElement(logInSuccessMessage).isDisplayed();
        Assert.assertTrue(successMessageElement);
        Assert.assertEquals(driver.findElement(logInSuccessMessage).getText(),logInSuccessMessageTxt);
    }

    public void verifyErrorUserMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInErrorMessage));
        boolean errorMessageElement = driver.findElement(logInErrorMessage).isDisplayed();
        Assert.assertTrue(errorMessageElement);
        Assert.assertEquals(driver.findElement(logInErrorMessage).getText(),logInErrorMessageUsernameTxt);
    }

    public void verifyErrorPassMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logInErrorMessage));
        boolean errorMessageElement = driver.findElement(logInErrorMessage).isDisplayed();
        Assert.assertTrue(errorMessageElement);
        Assert.assertEquals(driver.findElement(logInErrorMessage).getText(),logInErrorMessagePasswordTxt);
    }

}
