package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    //Initialize WebDriver in Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    /*
    All elements which will be used in page FindBy
    */


    @FindBy(xpath="//div/ul[1]/li[3]/a/span[1]")
    WebElement linkToLogin;

    @FindBy(linkText="careers")
    WebElement linkToCareers;

    @FindBy(xpath="//form/div[1]/a")
    WebElement giftCost;

    @FindBy(xpath="//form/div[1]/div/ul/li[2]")
    WebElement selectGiftCost;

    @FindBy(xpath="//form/div[2]/a")
    WebElement giftArea;

    @FindBy(xpath="//div[2]/div/ul/li[3]")
    WebElement selectGiftArea;

    @FindBy(xpath="//form/div[3]/a")
    WebElement giftCategory;

    @FindBy(xpath="//div[3]/div/ul/li[11]")
    WebElement selectGiftCategory;

    @FindBy(xpath="//form/a")
    WebElement searchButton;


    //Click on login page button with try and catch (Exception e)
    public void goToLoginPage() {
        try{clickElement(linkToLogin);}
        catch(Exception e){logger.error("Couldn't go to login page");
        }
    }

    //Click on careers page button with try and catch (Exception e)
    public void goToCareersPage() {
        try{
            clickElement(linkToCareers);
        }
        catch(Exception e){logger.error("Couldn't go to careers page");
        }
    }

    //Choosing search filters
    public void selectSearchFilters() {
        clickElement(giftCost);
        clickElement(selectGiftCost);
        clickElement(giftArea);
        clickElement(selectGiftArea);
        clickElement(giftCategory);
        clickElement(selectGiftCategory);
        clickElement(searchButton);
    }

}
