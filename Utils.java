package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.*;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Utils {

    public static WebDriver getDriver(String browser, String baseURL){
        WebDriver driver = null;

        if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\data\\jars\\Drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        }
        else if (browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\data\\jars\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else
            System.out.println("No browser found");

        //add implicit wait to driver object
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //get base url for testing
        driver.get(baseURL);

        //Max window
        //driver.manage().window().maximize();
        return driver;
    }

    //Check if file exist in storage
    //import java.io.File;
    public static boolean isFileExist(String path){
        File tmpDir = new File(path);
        boolean exists = tmpDir.exists();
        return exists;
    }

    //Check if file exist in storage
    //import java.io.File;
    public static boolean removeFile(String path){
        File tmpDir = new File(path);
        tmpDir.delete();
        boolean exists = tmpDir.exists();
        return !exists;
    }

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public static String getProperty(String key) throws IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream(System.getProperty("user.dir") +"\\resources\\" +"Settings.properties");
        prop.load(input);
        String value = prop.getProperty(key);
        return value;
    }

    public static String getFullPath(String imageName){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "\\images\\" + timestamp.toString().replace(':', ';')
                + " " + imageName + ".jpeg";
    }

}
