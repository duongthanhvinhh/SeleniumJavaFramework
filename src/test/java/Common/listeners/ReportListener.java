//package Common.listeners;
//
//import Common.BaseSetup;
//import Common.CaptureHelpers;
//import Common.RecordHelpers;
//import Common.extentReport.ExtentTestManager;
//import Utils.Log;
//import com.aventstack.extentreports.Status;
//import io.qameta.allure.Allure;
//import io.qameta.allure.Attachment;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//
//import static Common.extentReport.ExtentManager.getExtentReports;
//
//public class ReportListener implements ITestListener {
//    WebDriver driver;
//
//    public String getTestName(ITestResult result) {
//        return result.getTestName() != null ? result.getTestName()
//                : result.getMethod().getConstructorOrMethod().getName();
//    }
//
//    public String getTestDescription(ITestResult result) {
//        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
//    }
//
//    //  Text attachments for Allure
//    @Attachment(value = "{0}", type = "text/plain")
//    public static String saveTextLog(String message) {
//        return message;
//    }
//
//    //  HTML attachments for Allure
//    @Attachment(value = "{0}", type = "text/html")
//    public static String attachHtml(String html) {
//        return html;
//    }
//
//    //  Text attachments for Allure
//    @Attachment(value = "Page screenshot", type = "image/png")
//    public byte[] saveScreenshotPNG(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//
//    @Override
//    public void onStart(ITestContext iTestContext) {
//        driver = BaseSetup.getDriver();
//        Log.info("Start testing " + iTestContext.getName());
//        iTestContext.setAttribute("WebDriver", driver);
//        //Call startRecord function in RecordHelpers class
////        try {
////            RecordHelpers.startRecord(iTestContext.getName());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    public void onFinish(ITestContext iTestContext) {
//        Log.info("######## End testing " + iTestContext.getName() + "########");
//        //Terminate and start Extents Report
//        getExtentReports().flush();
//        //Call stopRecord video function in RecordVideo class
////        try {
////            RecordHelpers.stopRecord();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    public void onTestStart(ITestResult iTestResult) {
//        Log.info("######## " + getTestName(iTestResult) + " is starting... ########");
//        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());
//        try {
//            RecordHelpers.startRecord(iTestResult.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult iTestResult) {
//        Log.info("########" + getTestName(iTestResult) + " is passed. ########");
//        //ExtentReports log operation for passed tests.
//        ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
//        try {
//            RecordHelpers.stopRecord();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onTestFailure(ITestResult iTestResult) {
//        driver = BaseSetup.getDriver();
//        Log.error("########" + getTestName(iTestResult) + " is failed. ########");
//        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
//        ExtentTestManager.logMessage(Status.FAIL, getTestDescription(iTestResult));
//        try {
//            RecordHelpers.stopRecord();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        CaptureHelpers.captureScreenshot(BaseSetup.getDriver(), getTestName(iTestResult));
//        Log.error("Screenshot captured for test case: " + getTestName(iTestResult));
//        saveScreenshotPNG(driver);
//        //Save a log on Allure report
//        saveTextLog(getTestName(iTestResult) + " failed and screenshot taken !");
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult iTestResult) {
//        Log.warn("########" + getTestName(iTestResult) + " is skipped. ########");
//        ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
//        try {
//            RecordHelpers.stopRecord();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//        Log.error("######## Test failed but in defined success ratio " + getTestName(iTestResult) + "########");
//        ExtentTestManager.logMessage("Test failed but in defined success ratio " + getTestName(iTestResult));
//        try {
//            RecordHelpers.stopRecord();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
