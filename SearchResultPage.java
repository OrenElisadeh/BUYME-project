package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchResultPage extends BasePage {

    //Initialize WebDriver in Constructor
    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    /*
    All elements which will be used in page FindBy
    */

    @FindBy(xpath="//div[@class=\"top\"]")
    WebElement selectGift;


    //Choosing specific gift from results
    public void choosingGift() throws InterruptedException {
        clickElement(selectGift);
        Thread.sleep(1000);
    }

}
