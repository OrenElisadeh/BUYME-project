package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.util.Set;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    //General Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 8);
        js = (JavascriptExecutor) driver;
    }

    //General click (strong with wait + scroll into)
    public void clickElement(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //General send keys + clear before
    public void typeText(WebElement element, String text){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    //Switch to new window (after open it)
    public void switchToNewWindow(){
        String baseHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();

        for (String h: handles){
            if (!h.equals(baseHandle))
                driver.switchTo().window(h);
        }
    }

    //Switch to new window (after open it)
    public void closeAndSwitchToMainWindow(){
        String baseHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        driver.close();

        for (String h: handles){
            if (!h.equals(baseHandle))
                driver.switchTo().window(h);
        }
    }

    //Asserting pages in test method
    public void assertingPagesByURL(String pageName, String currentURL) throws Exception {
        logger.info("Moving and asserting " + pageName);
        Thread.sleep(500);
        Assertions.assertNotEquals(currentURL, driver.getCurrentUrl());
        logger.info("Taking " + pageName + " snap Shot");
        Thread.sleep(500);
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath(pageName));
    }

}
