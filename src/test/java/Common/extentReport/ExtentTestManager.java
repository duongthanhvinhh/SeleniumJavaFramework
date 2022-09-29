package Common.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<String> categoryName = new ThreadLocal<>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public synchronized static void createTest(String testName, String description) {
        extentTestMap.put((int) Thread.currentThread().getId(), extent.createTest(testName, description));
    }

    public static ThreadLocal<String> getCategoryName() {
        return categoryName;
    }

    public static void setCategoryName(String categoryName) {
        getCategoryName().set(categoryName);
    }

    public synchronized static void reporterLog(String log) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.PASS, log);
            Reporter.log(log + "<br/>");
        }
    }

}
