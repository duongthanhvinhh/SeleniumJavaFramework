package Pages;

import Common.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage{

    private WebDriver driver;


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    By HOMEbtn = By.xpath("//a[normalize-space()='Home']");
    By PRACTICEbtn = By.xpath("//a[normalize-space()='Practice']");
    By COURSESbtn = By.xpath("//a[normalize-space()='Courses']");
    By BLOGbtn = By.xpath("//a[normalize-space()='Blog']");
    By CONTACTbtn = By.xpath("//a[normalize-space()='Contact']");
    public By logOutBtn = By.xpath("//a[normalize-space()='Log out']");

    public void clickLogoutBtn() {
        driver.findElement(logOutBtn).click();
    }

    public void clickHOME() {
        driver.findElement(HOMEbtn).click();
    }

    public void clickPRACTICE() {
        driver.findElement(PRACTICEbtn).click();
    }

    public void clickCOURSES() {
        driver.findElement(COURSESbtn).click();
    }

    public void clickBLOG() {
        driver.findElement(BLOGbtn).click();
    }

    public void clickCONTACT() {
        driver.findElement(CONTACTbtn).click();
    }
}
