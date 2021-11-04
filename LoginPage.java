package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
import java.io.IOException;


public class LoginPage extends BasePage {

    //Initialize WebDriver in Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    //Initialize page variables
    String errorMassage = "כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה";

    /*
    All elements which will be used in page FindBy
    */

    @FindBy(xpath="//div[1]/div[3]/div[1]/span")
    WebElement linkToRegistration;

    @FindBy(xpath="//*[@class='bm-h1']")
    WebElement loginPageTitle;

    @FindBy(xpath="//form/div[1]/label/input")
    WebElement mailField;

    @FindBy(xpath="//div[2]/label/input")
    WebElement passwordField;

    @FindBy(xpath="//form/button")
    WebElement loginButton;

    @FindBy(xpath="//header/div[1]/div/ul[1]/li[3]/a/span")
    WebElement menuDropDown;

    @FindBy(xpath="//div[1]/label/ul/li")
    WebElement mailError;

    @FindBy(xpath="//div[2]/label/ul/li")
    WebElement passwordError;


    //Asserting login page by title text
    public void assertLoginPage(){
        Assertions.assertEquals("כניסה",
                                loginPageTitle.getText(),
                                "\"Couldn't go to login page\"");
    }

    //Move to registration page by click on element with try and catch
    public void goToRegistrationPage(){
        try{clickElement(linkToRegistration);}
        catch(Exception e){logger.error("Couldn't go to registration page");}
    }

    //Login to the system with user name and password from properties
    public void loginToSystem() throws IOException {
        typeText(mailField, Utils.getProperty("userEmail"));
        typeText(passwordField, Utils.getProperty("password"));
        clickLoginButton();
    }

    //Asserting login by element text that appears only when login to the system
    public void assertLogin() throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertEquals("החשבון שלי", menuDropDown.getText(),
                                "Login failed");
    }

    //Click on login button
    public void clickLoginButton(){
        clickElement(loginButton);
    }

    //Asserting login page fields error massages
    public void assertErrorMassages() throws Exception {
        Thread.sleep(1000);
        Assertions.assertEquals(errorMassage, mailError.getText());
        Assertions.assertEquals(errorMassage, passwordError.getText());
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("LoginErrorMassages"));
    }

}
