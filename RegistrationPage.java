package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
import java.io.IOException;


public class RegistrationPage extends BasePage {

    //Init of WebDriver in Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /*
    All elements which will be used in page FindBy
    */



    @FindBy(xpath="//div[1]/label/input")
    WebElement userNameInput;

    @FindBy(xpath="//div[2]/label/input")
    WebElement userEmailInput;

    @FindBy(xpath="//div[3]/label/input")
    WebElement userPasswordInput;

    @FindBy(xpath="//div[4]/label/input")
    WebElement validateUserPassword;

    @FindBy(xpath="//*[@class='bm-h1']")
    WebElement registrationPageTitle;


    //Asserting registration page by element text
    public void assertRegistrationPage(){
        Assertions.assertEquals("הרשמה",
                                registrationPageTitle.getText(),
                                "\"Couldn't go to registration page\"");
    }

    //Filling data from properties in all fields in registration page
    public void fillDataInFields() throws IOException {
        typeText(userNameInput, Utils.getProperty("userName"));
        typeText(userEmailInput, Utils.getProperty("userEmail"));
        typeText(userPasswordInput, Utils.getProperty("password"));
        typeText(validateUserPassword, Utils.getProperty("password"));
        js.executeScript("arguments[0].scrollIntoView(true);", userNameInput);
    }

}
