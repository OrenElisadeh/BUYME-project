package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;


public class SupplierPage extends BasePage {

    //Initialize WebDriver in Constructor
    public SupplierPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    /*
    All elements which will be used in page FindBy
    */

    @FindBy(xpath="//li[2]/a//div[2]//button")
    WebElement selectSupplier;

    @FindBy(xpath="//footer/div[2]")
    WebElement footer;


    //Choosing specific supplier from results
    public void choosingSupplier() throws InterruptedException {
        clickElement(selectSupplier);
        Thread.sleep(1000);
    }

    //Scrolling down by js execute and Asserting bottom of the page by taking snap shot
    public void assertBottomPage() throws Exception {
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("bottomSupplierPage"));
        Assertions.assertTrue(footer.isDisplayed(), "Page bottom is not display");
    }

}
