package Utils;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFile {

    private static Properties properties;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;

    //Get path of current project
        static String projectPath = System.getProperty("user.dir") + "/";


    //Create path to file configs.properties
    private static String propertiesFilePathRoot = "src/test/resources/configs.properties";

    public static void setPropertiesFile(){
        properties = new Properties();
        try {
            //Initial object value of class FileInputStream
//            fileIn = new FileInputStream(projectPath + propertiesFilePathRoot);
            fileIn = new FileInputStream(propertiesFilePathRoot);
            //Load properties file
            properties.load(fileIn);
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }

    public static String getPropValue(String KeyProp){
        String value = null;
        try {
            //get values from properties file
            value = properties.getProperty(KeyProp);
            return value;
        }catch (Exception exp){
            Log.error(exp.getMessage());
            Log.error(exp.getCause());
            exp.printStackTrace();
        }
        return value;
    }

    public static void setPropValue(String KeyProp, String Value){
        try {
            //Initial value for object of class FileOutputStream
//            fileOut = new FileOutputStream(projectPath + propertiesFilePathRoot);
            fileOut = new FileOutputStream(projectPath + propertiesFilePathRoot);
            //Load the current properties file to mapping value with key
            properties.setProperty(KeyProp,Value);
            //Save Key and Value into properties file
            properties.store(fileOut,"Set new value in properties file");
            Log.info("Set new value in properties file success");
        }catch (Exception exp){
            Log.error(exp.getCause());
            Log.error(exp.getMessage());
            exp.printStackTrace();
        }
    }
}
