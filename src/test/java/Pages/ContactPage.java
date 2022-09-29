package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ContactPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private LogInPage logInPage;
    private PracticePage practicePage;
    private DashboardPage dashboardPage;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logInPage = new LogInPage(driver);
        dashboardPage = new DashboardPage(driver);
        practicePage = new PracticePage(driver);
    }

    private final By firstNameTxt = By.xpath("//input[@id='wpforms-161-field_0']");
    private final By lastNameTxt = By.xpath("//input[@id='wpforms-161-field_0-last']");
    private final By emailTxt = By.xpath("//input[@id='wpforms-161-field_1']");
    private final By inputComment = By.xpath("//textarea[@id='wpforms-161-field_2']");
    private final By submitBtn = By.xpath("//button[@id='wpforms-submit-161']");
    private final By successMessage = By.xpath("//p[contains(text(),'Thanks for contacting us! We will be in touch with')]");
    private final String successMessageTxt = "Thanks for contacting us! We will be in touch with you shortly.";

    private final By errorWithoutFirstName = By.xpath("//label[@id='wpforms-161-field_0-error']");
    private final By errorWithoutLastName = By.xpath("//label[@id='wpforms-161-field_0-last-error']");
    private final By errorWithoutEmail = By.xpath("//label[@id='wpforms-161-field_1-error']");
    private final By errorWithoutComment = By.xpath("//label[@id='wpforms-161-field_2-error']");
    private final String errorMessageTxt = "This field is required.";

    public void inputFirstNameRequired(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTxt));
        driver.findElement(firstNameTxt).sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameTxt));
        driver.findElement(lastNameTxt).sendKeys(lastName);
    }

    public void inputEmailRequired(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailTxt));
        driver.findElement(emailTxt).sendKeys(email);
    }

    public void inputCommentRequired(String commentMessage) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputComment));
        driver.findElement(inputComment).sendKeys(commentMessage);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn));
        driver.findElement(submitBtn).click();
    }

    public void checkSuccessMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        driver.findElement(successMessage).isDisplayed();
        String compare = driver.findElement(successMessage).getText();
        Assert.assertEquals(compare, successMessageTxt);
    }

    public void checkErrorMessageWithoutfirstName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorWithoutFirstName));
        driver.findElement(errorWithoutFirstName).isDisplayed();
        String compare = driver.findElement(errorWithoutFirstName).getText();
        Assert.assertEquals(compare, errorMessageTxt);
    }

    public void checkErrorMessageWithoutlastName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorWithoutLastName));
        driver.findElement(errorWithoutLastName).isDisplayed();
        String compare = driver.findElement(errorWithoutLastName).getText();
        Assert.assertEquals(compare, errorMessageTxt);
    }

    public void checkErrorMessageWithoutemail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorWithoutEmail));
        driver.findElement(errorWithoutEmail).isDisplayed();
        String compare = driver.findElement(errorWithoutEmail).getText();
        Assert.assertEquals(compare, errorMessageTxt);
    }

    public void checkErrorMessageWithoutComment() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorWithoutComment));
        driver.findElement(errorWithoutComment).isDisplayed();
        String compare = driver.findElement(errorWithoutComment).getText();
        Assert.assertEquals(compare, errorMessageTxt);
    }
}
