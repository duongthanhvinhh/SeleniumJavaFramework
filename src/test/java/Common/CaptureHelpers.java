//package Common;
//
//import Utils.Log;
//import Utils.PropertiesFile;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.io.FileHandler;
//import org.testng.Reporter;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class CaptureHelpers {
//
//
//    static String projectPath = System.getProperty("user.dir") + "/";
//
//    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
//
//    public static void captureScreenshot(WebDriver driver, String screenName) {
//        PropertiesFile.setPropertiesFile();
//        try {
//            Reporter.log("Driver for Screenshot: " + driver);
//
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            // call getScreenshotAs function to convert image to file
//            File source = ts.getScreenshotAs(OutputType.FILE);
//
//            File theDir = new File(projectPath + PropertiesFile.getPropValue("exportCapturePath"));
//            if (!theDir.exists()){
//                theDir.mkdirs();
//            }
//            // the screeName variable will be included in the name of image
//            FileHandler.copy(source, new File(projectPath + PropertiesFile.getPropValue("exportCapturePath") + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png"));
//            Log.info("Screenshot taken: " + screenName);
//            Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
//        } catch (Exception e) {
//            Log.error("Exception while taking screenshot: " + e.getMessage());
//        }
//    }
//
//}
