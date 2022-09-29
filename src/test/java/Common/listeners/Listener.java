package Common.listeners;

import Common.BaseSetup;
import Common.RecordHelpers;
import Common.extentReport.ExtentManager;
import Common.extentReport.ExtentTestManager;
import Utils.Log;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Common.extentReport.ExtentManager.OUTPUT_FOLDER_SCREENSHOTS;

public class Listener implements ITestListener {
    private static long endTime;

    private static void setStartTime(long startTime) {
    }

    private static void setEndTime(long endTime) {
        Listener.endTime = endTime;
    }

    @Override
    public synchronized void onStart(ITestContext context) {

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        setStartTime(context.getStartDate().getTime());
        setEndTime(context.getEndDate().getTime());
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        Log.info("--------- Executing :- " + getSimpleMethodName(result) + " ---------");
        ExtentTestManager.createTest(result.getName(), result.getMethod().getDescription());
        ExtentTestManager.setCategoryName(getSimpleClassName(result));
        try {
            RecordHelpers.startRecord(result.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
        addExtentLabelToTest(result);
        ExtentTestManager.endTest();
        try {
            RecordHelpers.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public synchronized void onTestFailure(ITestResult result) {
//        ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
//        ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " Test is failed" +result.getThrowable());
//        addExtentLabelToTest(result);
//        ExtentTestManager.endTest();
//    }

    private static synchronized String takeScreenshot(String methodName) {
        DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss_SSS");
        Date date = new Date();
        String dateName = dateFormat.format(date);
        String filePathExtent = OUTPUT_FOLDER_SCREENSHOTS + "Web" + methodName + "_" + dateName + ".png";
        String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
        String encodedBase64 = null;
        try {
            File screenshotFile = ((TakesScreenshot) BaseSetup.getDriver()).getScreenshotAs(OutputType.FILE);
            FileInputStream fileInputStreamReader;
            fileInputStreamReader = new FileInputStream(screenshotFile);
            byte[] bytes = new byte[(int) screenshotFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = Base64.encodeBase64String(bytes);
            FileUtils.copyFile(screenshotFile, new File(filePath));
        } catch (IOException e) {
            e.getStackTrace();
            Reporter.log("Failed To Take screenshot " + e, true);
        }
        return encodedBase64;
    }
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
        ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " Test is failed" + result.getThrowable());
        try {
            ExtentTestManager.getTest().fail("<br><font color= red>" + "Screenshot of Web" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(getSimpleMethodName(result))).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        addExtentLabelToTest(result);
        ExtentTestManager.endTest();

        try {
            RecordHelpers.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " Test is Skipped" + result.getThrowable());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    private synchronized String getSimpleClassName(ITestResult result) {
        return result.getMethod().getRealClass().getSimpleName();
    }

    private synchronized String getSimpleMethodName(ITestResult result) {
        return result.getName();
    }

    private synchronized void addExtentLabelToTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS)
            ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
        else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
        } else
            ExtentTestManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
    }

}
