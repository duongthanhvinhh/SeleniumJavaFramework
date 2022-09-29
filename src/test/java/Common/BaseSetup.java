package Common;

import Pages.DashboardPage;
import Pages.LogInPage;
import Utils.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BaseSetup {

    public static WebDriver driver;
    public static String driverpath = "src\\test\\resources\\drivers\\";
    public static WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver setDriver(String browserType) {
        switch (browserType.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                break;
            case "firefox":
                driver = initFirefoxDriver();
                wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                break;
            case "edge":
                driver = initEdgeDriver();
                wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                break;
            default:
                Log.info("Browser: " + browserType + " is invalid, Launching Chrome as default browser");
                driver = initChromeDriver();
                wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        }
        return driver;
    }


    public static WebDriver initEdgeDriver() {
        Log.info("Launching Edge browser ...");
        System.setProperty("webdriver.edge.driver", driverpath + "edge\\msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("headless");
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        Log.info("Launching Edge browser Successfully");
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        return driver;
    }

    public static WebDriver initFirefoxDriver() {
        Log.info("Launching FireFox browser ...");
        System.setProperty("webdriver.gecko.driver", driverpath + "firefox\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        Log.info("Launching FireFox browser Successfully");
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        return driver;
    }

    public static WebDriver initChromeDriver() {
        Log.info("Launching Chrome browser ...");
        System.setProperty("webdriver.chrome.driver", driverpath + "chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        Log.info("Launching Chrome browser Successfully");
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void navigatetoURL(String url) {
        driver.get(url);
    }

    public void switchPage(WebDriver driver, String titlePage) {
        Log.info("Access switch page function");
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            String title = driver.getTitle();
            Log.info(title.trim().toString());
            if (title.trim().toString().equals(titlePage)) {
                Log.info("Switch to Page has title is: " + titlePage);
                return;
            }
        }
    }

}
